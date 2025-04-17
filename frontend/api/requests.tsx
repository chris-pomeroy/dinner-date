
// const devServer = Constants.expoConfig?.hostUri ? Constants.expoConfig.hostUri.split(':')[0] : "localhost";
// const api = `http://${devServer}:8080`;
const api = `https://dinner-date.onrender.com`;

export type Recipe = {
    id: number;
    title: string;
    description: string;
    imageName: string;
    recipeUrl: string;
}

export async function getRandomRecipe() : Promise<Recipe> {
    const response  = await fetch(`${api}/recipes/random`)
    return await response.json()
}

export async function likeRecipe(recipeId: number) {
    return await fetch(`${api}/like?recipeId=${recipeId}`, {
        method: "POST"
    })
}

export async function dislikeRecipe(recipeId: number) {
    return await fetch(`${api}/dislike?recipeId=${recipeId}`, {
        method: "POST"
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
    const response = await fetch(`${api}/likes?page=${page}`)
    return await response.json()
}

export async function login(email: string, password: string) {
    const response = await fetch(`${api}/login`, {
        method: "POST",
        body: JSON.stringify({email, password})
    })
    console.log(response.headers.get("Authorization"));
}