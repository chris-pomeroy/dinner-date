import {useQuery} from "@tanstack/react-query";
import {addKeysToRecipes, authFetch} from "@/api/authFetch";
import {Recipe, RecipeWithKey} from "@/hooks/queries/useRandomRecipeQuery";

export const useLikesQuery = () => {
    return useQuery<RecipeWithKey[]>({
        queryKey: ['likes'],
        queryFn: async () => {
            const recipes : Recipe[] = await authFetch(`/likes`)
            return addKeysToRecipes(recipes)
        }
    });
}