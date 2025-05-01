import {useMutation} from "@tanstack/react-query";
import {authPost} from "@/api/authFetch";

export type Swipe = {
    recipeId: number;
    timeZone: string;
}

export const useLikeMutation = () => {
    return useMutation({
        mutationFn: (id: number) => authPost<unknown,Swipe>(`/like`, {
            recipeId: id,
            timeZone: getTimeZone()
        })
    })
}

export function getTimeZone() : string {
    return Intl.DateTimeFormat().resolvedOptions().timeZone
}