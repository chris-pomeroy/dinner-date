import {authPost, setAuthToken} from "@/api/authFetch";
import {useMutation, useQueryClient} from "@tanstack/react-query";

export type LoginRequest = {
    email: string,
    password: string
}

type LoginResponse = {
    sessionId: string
}

export const useLoginMutation = () => {
    const queryClient = useQueryClient();

    return useMutation({
        mutationFn: async (req: LoginRequest) => {
            const response : LoginResponse = await authPost(`/login`, {
                body: JSON.stringify(req)
            })
            await setAuthToken(response.sessionId)
            return response
        },
        onSuccess: () => queryClient.refetchQueries()
    })
}