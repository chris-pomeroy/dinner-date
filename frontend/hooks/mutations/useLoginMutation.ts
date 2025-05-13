import {authPost, setAuthToken} from "@/api/authFetch";
import {useMutation, useQueryClient} from "@tanstack/react-query";
import {useAuthContext} from "@/contexts/AuthContext";

export type LoginRequest = {
    email: string,
    password: string
}

type LoginResponse = {
    sessionId: string
}

export const useLoginMutation = () => {
    const queryClient = useQueryClient()
    const {setIsLoggedIn} = useAuthContext()

    return useMutation({
        mutationFn: async (req: LoginRequest) => {
            const response : LoginResponse = await authPost(`/login`, req)
            await setAuthToken(response.sessionId)
            return response
        },
        onSuccess: () => {
            queryClient.refetchQueries()
            setIsLoggedIn(true)
        }
    })
}