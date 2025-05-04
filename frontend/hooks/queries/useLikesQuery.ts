import {useQuery} from "@tanstack/react-query";
import {addKeysToRecipes, authGet} from "@/api/authFetch";
import {Recipe, RecipeWithKey} from "@/hooks/queries/useRandomRecipeQuery";

export const useLikesQuery = () => {
    return useQuery<RecipeWithKey[]>({
        queryKey: ['likes'],
        queryFn: async () => {
            const recipes : Recipe[] = await authGet(`/likes`)
            return addKeysToRecipes(recipes)
        }
    });
}