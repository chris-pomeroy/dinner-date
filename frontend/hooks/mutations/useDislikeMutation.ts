import {useMutation} from "@tanstack/react-query";
import {authPost} from "@/api/authFetch";


export const useDislikeMutation = () => {
    return useMutation({
        mutationFn: (id: number) => authPost(`/dislike?recipeId=${id}`)
    })
}