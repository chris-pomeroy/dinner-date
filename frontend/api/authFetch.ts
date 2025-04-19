// const devServer = Constants.expoConfig?.hostUri ? Constants.expoConfig.hostUri.split(':')[0] : "localhost";
// const api = `http://${devServer}:8080`;
const api = `https://dinner-date.onrender.com`;

let authToken: string | null = null;

export const setAuthToken = (token: string | null) => {
    authToken = token;
};

export class FetchError extends Error {
    status: number;
    body: unknown;

    constructor(message: string, status: number, body: unknown) {
        super(message);
        this.status = status;
        this.body = body;
    }
}

export const authFetch = async <T>(
    input: string,
    init: RequestInit = {}
): Promise<T> => {
    const headers: HeadersInit = {
        'Content-Type': 'application/json',
        ...(authToken ? { Authorization: `Bearer ${authToken}` } : {}),
        ...init.headers,
    };

    const res = await fetch(api + input, {
        ...init,
        headers,
    });

    const contentType = res.headers.get('content-type');
    const isJson = contentType?.includes('application/json');
    const body = isJson ? await res.json() : await res.text();

    if (!res.ok) {
        throw new FetchError(res.statusText || 'Fetch error', res.status, body);
    }

    return body as T;
};

export function getImageUrl(imageName: string) {
    return `${api}/recipe-images/${imageName}`;
}