import Constants from "expo-constants";

// Get the development server IP and port
const devServer = Constants.expoConfig?.hostUri ? Constants.expoConfig.hostUri.split(':')[0] : "localhost";
const api = `http://${devServer}:8080`;


type Recipe = {
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

export function getImageUrl(imageName: string) {
    return `${api}/recipe-images/${imageName}`;
}