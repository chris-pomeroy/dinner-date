// import Constants from "expo-constants";

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