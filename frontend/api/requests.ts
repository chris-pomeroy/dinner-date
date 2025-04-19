import {authFetch, setAuthToken} from "@/api/authFetch";

export type LoginRequest = {
    email: string,
    password: string
}

type LoginResponse = {
    sessionId: string
}

export async function login(req: LoginRequest) {
    const response : LoginResponse = await authFetch(`/login`, {
        method: "POST",
        body: JSON.stringify(req)
    })
    setAuthToken(response.sessionId)
    return response
}

export type Recipe = {
    id: number;
    title: string;
    description: string;
    imageName: string;
    recipeUrl: string;
}

export function getRandomRecipe() : Promise<Recipe> {
    return authFetch(`/recipes/random`)
}

export function likeRecipe(recipeId: number) {
    return authFetch(`/like?recipeId=${recipeId}`, {
        method: "POST"
    })
}

export function dislikeRecipe(recipeId: number) {
    return authFetch(`/dislike?recipeId=${recipeId}`, {
        method: "POST",
    })
}

export type Like = {
    id: number;
    timeStamp: Date;
    recipe: Recipe;
}

export function getLikes(page = 0) : Promise<Like[]> {
    return authFetch(`/likes?page=${page}`)
}
