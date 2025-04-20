import {useQuery} from "@tanstack/react-query";
import {authFetch} from "@/api/authFetch";
import {Recipe} from "@/hooks/useRandomRecipeQuery";

export type Like = {
    id: number;
    timeStamp: Date;
    recipe: Recipe;
}

export const useLikesQuery = () => {
    return useQuery<Like[]>({
        queryKey: ['likes'],
        queryFn: () => authFetch(`/likes`)
    });
}