import {useMutation} from "@tanstack/react-query";
import {authPost} from "@/api/authFetch";
import {useLoginMutation} from "@/hooks/mutations/useLoginMutation";

type RegisterRequest = {
    firstName: string;
    email: string;
    password: string;
}

export const useRegisterMutation = () => {
    const {mutate: postLogin} = useLoginMutation();

    return useMutation({
        mutationFn: (req: RegisterRequest) => authPost('/register', {
            body: JSON.stringify(req)
        }),
        onSuccess: (_, req) => postLogin(req),
    })
}