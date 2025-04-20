import {useQuery} from "@tanstack/react-query";
import {authFetch} from "@/api/authFetch";

export type Recipe = {
    id: number;
    title: string;
    description: string;
    imageName: string;
    recipeUrl: string;
}

export const useRandomRecipeQuery = () => {
    return useQuery<Recipe>({
        queryKey: ['recipe'],
        queryFn: () => authFetch(`/recipes/random`)
    });
}