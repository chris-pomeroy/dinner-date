import {useQuery} from "@tanstack/react-query";
import {authFetch} from "@/api/authFetch";
import {useEffect, useState} from "react";
import {randomUUID} from "expo-crypto";

type Recipe = {
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

    const {refetch} = useQuery<Recipe[]>({
        queryKey: ['recipe'],
        queryFn: () => authFetch(`/recipes/random?count=${recipesToQuery}`),
        enabled: false
    });

    useEffect(() => {
        if (recipesToQuery < 1) {
            return
        }
        refetch().then(({data}) => {
            const dataWithKeys = (data ?? []).map(recipe => { return {
                ...recipe,
                key: randomUUID()
            }})
            setRecipes(prev => [...prev, ...dataWithKeys])
        })
    }, [recipesToQuery])

    const popRecipe = () => setRecipes(prev => prev.slice(1));

    return {recipes, popRecipe}
}