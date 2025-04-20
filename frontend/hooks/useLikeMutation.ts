import {useMutation} from "@tanstack/react-query";
import {authPost} from "@/api/authFetch";

export const useLikeMutation = () => {
    return useMutation({
        mutationFn: (id: number) => authPost(`/like?recipeId=${id}`)
    })
}