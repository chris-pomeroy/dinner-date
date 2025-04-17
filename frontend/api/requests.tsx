// const devServer = Constants.expoConfig?.hostUri ? Constants.expoConfig.hostUri.split(':')[0] : "localhost";
// const api = `http://${devServer}:8080`;

const api = `https://dinner-date.onrender.com`;

// TODO store this securely across restarts
const headers : {Authorization?: string, "Content-Type": string} = {
    "Content-Type": "application/json",
}

export type LoginRequest = {
    email: string,
    password: string
}

type LoginResponse = {
    sessionId: string
}

export async function login(req: LoginRequest) {
    const response = await fetch(`${api}/login`, {
        method: "POST",
        headers: headers,
        body: JSON.stringify(req)
    })
    const responseBody : LoginResponse = await response.json()
    headers.Authorization = `Bearer ${responseBody.sessionId}`
}

export type Recipe = {
    id: number;
    title: string;
    description: string;
    imageName: string;
    recipeUrl: string;
}

export async function getRandomRecipe() : Promise<Recipe> {
    const response  = await fetch(`${api}/recipes/random`, {
        headers: headers
    })
    return await response.json()
}

export async function likeRecipe(recipeId: number) {
    return await fetch(`${api}/like?recipeId=${recipeId}`, {
        method: "POST",
        headers: headers
    })
}

export async function dislikeRecipe(recipeId: number) {
    return await fetch(`${api}/dislike?recipeId=${recipeId}`, {
        method: "POST",
        headers: headers
    })
}

export function getImageUrl(imageName: string) {
    return `${api}/recipe-images/${imageName}`;
}

export type Like = {
    id: number;
    timeStamp: Date;
    recipe: Recipe;
}

export async function getLikes(page = 0) : Promise<Like[]> {
    const response = await fetch(`${api}/likes?page=${page}`, {
        headers: headers
    })
    return await response.json()
}
