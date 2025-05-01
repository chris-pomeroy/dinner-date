import {useMutation} from "@tanstack/react-query";
import {authPost} from "@/api/authFetch";
import {getTimeZone, Swipe} from "@/hooks/mutations/useLikeMutation";

export const useDislikeMutation = () => {
    return useMutation({
        mutationFn: (id: number) => authPost<unknown,Swipe>(`/dislike`, {
            recipeId: id,
            timeZone: getTimeZone()
        })
    })
}