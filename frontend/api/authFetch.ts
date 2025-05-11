import {deleteItemAsync, getItemAsync, setItemAsync} from "expo-secure-store";
import {Recipe} from "@/hooks/queries/useRandomRecipeQuery";
import {randomUUID} from "expo-crypto";

// import Constants from 'expo-constants';
// const devServer = Constants.expoConfig?.hostUri ? Constants.expoConfig.hostUri.split(':')[0] : "localhost";
// const api = `http://${devServer}:8080`;
const api = `https://dinner-date.onrender.com`;

const SECURE_STORE_KEY = "sessionId";

let authToken: string | null = null;

export const setAuthToken = (token: string) => {
    authToken = token
    return setItemAsync(SECURE_STORE_KEY, token)
}

export const clearAuthToken = () => {
    authToken = null;
    return deleteItemAsync(SECURE_STORE_KEY);
}

export const authTokenIsSet = () => {
    return !!authToken;
}

export class FetchError extends Error {
    status: number;
    body: unknown;

    constructor(message: string, status: number, body: unknown) {
        super(message);
        this.status = status;
        this.body = body;
    }
}

const authFetch = async <T>(
    input: string,
    init: RequestInit = {}
): Promise<T> => {

    if (!authToken) {
        authToken = await getItemAsync(SECURE_STORE_KEY);
    }

    const res = await fetch(new URL(input,api), {
        ...init,
        headers: {
            'Content-Type': 'application/json',
            ...(authToken && { Authorization: `Bearer ${authToken}` }),
            ...init.headers,
        },
    });

    const contentType = res.headers.get('content-type');
    const isJson = contentType?.includes('application/json');
    const body = isJson ? await res.json() : await res.text();

    if (!res.ok) {
        throw new FetchError(res.statusText || 'Fetch error', res.status, body);
    }

    return body as T;
};

export const authGet = <T,Q>(
    input: string,
    queryParams?: Q,
    init: RequestInit = {}
): Promise<T> => {
    const queryParamString = queryParams ?
        `?${new URLSearchParams(queryParams).toString()}` : "";

    return authFetch(input + queryParamString, {
        ...init,
    })
}

export const authPost = <T,B>(
    input: string,
    body?: B,
    init: RequestInit = {}
): Promise<T> => {
    return authFetch(input, {
        method: "POST",
        ...(body && { body: JSON.stringify(body) }),
        ...init,
    })
}

export function getImageUrl(imageName: string) {
    return `${api}/recipe-images/${imageName}`;
}

export function addKeysToRecipes(recipes: Recipe[] = []) {
    return recipes.map(recipe => { return {
        ...recipe,
        key: randomUUID()
    }})
}