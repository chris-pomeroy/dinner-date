import {useMutation} from "@tanstack/react-query";
import {authPost} from "@/api/authFetch";
import {Share} from "react-native";

type InviteResponse = {
    inviteToken: string;
}

export const useInviteMutation = () => {
    return useMutation({
        mutationFn: () : Promise<InviteResponse> => authPost('/invite'),
        onSuccess: ({inviteToken}) => {
            Share.share({
                message: `https://dinner-date.onrender.com/join/${inviteToken}`
            })
        }
    })
}