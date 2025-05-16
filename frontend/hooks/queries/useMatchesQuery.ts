import {useQuery} from "@tanstack/react-query";
import {addKeysToRecipes, authGet} from "@/api/authFetch";
import {Recipe, RecipeWithKey} from "@/hooks/queries/useRandomRecipeQuery";

export const useMatchesQuery = () => {
    return useQuery<RecipeWithKey[]>({
        queryKey: ['matches'],
        queryFn: async () => {
            const recipes : Recipe[] = await authGet('/matches/all')
            return addKeysToRecipes(recipes)
        }
    });
}