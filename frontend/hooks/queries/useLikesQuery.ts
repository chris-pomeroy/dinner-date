import {useQuery} from "@tanstack/react-query";
import {authGet} from "@/api/authFetch";
import {Recipe} from "@/hooks/queries/useRandomRecipeQuery";

export type Like = {
    id: number;
    recipe: Recipe;
}

export const useLikesQuery = () => {
    return useQuery<Like[]>({
        queryKey: ['likes'],
        queryFn: () => authGet(`/likes`)
    });
}