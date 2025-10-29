import {useQuery} from "@tanstack/react-query";
import {authGet} from "@/api/authFetch";
import {Recipe} from "@/hooks/queries/useRandomRecipeQuery";

export type Match = {
    id: number;
    recipe: Recipe;
}

export const useMatchesQuery = () => {
    return useQuery<Match[]>({
        queryKey: ['matches'],
        queryFn: () => authGet('/matches/all')
    });
}