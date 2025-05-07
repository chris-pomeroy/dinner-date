import {useMutation} from "@tanstack/react-query";
import {authPost} from "@/api/authFetch";
import {useMeQuery} from "@/hooks/queries/useMeQuery";

export const useJoinMutation = () => {
    const {refetch} = useMeQuery()

    return useMutation({
        mutationFn: (inviteToken: string) => authPost(`/join/${inviteToken}`),
        onSuccess: () => refetch()
    })
}