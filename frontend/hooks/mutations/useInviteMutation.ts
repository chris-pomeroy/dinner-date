import {useMutation} from "@tanstack/react-query";
import {authPost} from "@/api/authFetch";

type InviteResponse = {
    inviteToken: string;
}

export const useInviteMutation = () => {
    return useMutation({
        mutationFn: () : Promise<InviteResponse> => authPost('/invite')
    })
}