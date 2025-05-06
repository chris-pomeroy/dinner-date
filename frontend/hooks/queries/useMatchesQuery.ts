import {useQuery} from "@tanstack/react-query";
import {addKeysToRecipes, authGet} from "@/api/authFetch";
import {Recipe, RecipeWithKey} from "@/hooks/queries/useRandomRecipeQuery";
import {getTimeZone} from "@/hooks/mutations/useLikeMutation";

export const useMatchesQuery = () => {
    return useQuery<RecipeWithKey[]>({
        queryKey: ['matches'],
        queryFn: async () => {
            const recipes : Recipe[] = await authGet(`/matches/today`, {
                timeZone: getTimeZone()
            })
            return addKeysToRecipes(recipes)
        }
    });
}