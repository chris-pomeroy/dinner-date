import {addKeysToRecipes, authGet} from "@/api/authFetch";
import {useEffect, useState} from "react";

export type Recipe = {
    id: number;
    title: string;
    description: string;
    imageName: string;
    recipeUrl: string;
}

export type RecipeWithKey = Recipe & {
    key: string;
}

export const useRandomRecipeQuery = (recipesToCache: number) => {
    const [recipes, setRecipes] = useState<RecipeWithKey[]>([]);

    const recipesToQuery = recipesToCache - recipes.length;

    useEffect(() => {
        if (recipesToQuery < 1) {
            return
        }
        async function refetch() {
            const recipes : Recipe[] = await authGet('/recipes/random', {count: recipesToQuery})
            const recipesWithKeys = addKeysToRecipes(recipes)
            setRecipes(prev => [...prev, ...recipesWithKeys])
        }
        refetch()
    }, [recipesToQuery])

    const popRecipe = () => setRecipes(prev => prev.slice(1));

    return {recipes, popRecipe}
}