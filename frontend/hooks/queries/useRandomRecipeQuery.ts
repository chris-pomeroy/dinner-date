import {useQuery} from "@tanstack/react-query";
import {authFetch} from "@/api/authFetch";
import {useEffect, useState} from "react";

export type Recipe = {
    id: number;
    title: string;
    description: string;
    imageName: string;
    recipeUrl: string;
}

export const useRandomRecipeQuery = (recipesToCache: number) => {
    const [recipes, setRecipes] = useState<Recipe[]>([]);

    const recipesToQuery = recipesToCache - recipes.length;

    const {refetch} = useQuery<Recipe[]>({
        queryKey: ['recipe'],
        queryFn: () => authFetch(`/recipes/random?count=${recipesToQuery}`),
        enabled: false
    });

    useEffect(() => {
        if (recipesToQuery > 0) {
            refetch().then(({data}) => {
                setRecipes(prev => [...prev, ...(data ?? [])])
            })
        }
    }, [recipes.length])

    const popRecipe = () => setRecipes(prev => prev.slice(1));

    return {recipes, popRecipe}
}