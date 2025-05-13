import {useMutation, useQueryClient} from "@tanstack/react-query";
import {authPost, setAuthToken} from "@/api/authFetch";
import {useAuthContext} from "@/contexts/AuthContext";

type RegisterRequest = {
    firstName: string;
    email: string;
    password: string;
}

type RegisterResponse = {
    sessionId: string
}

export const useRegisterMutation = () => {
    const queryClient = useQueryClient()
    const {setIsLoggedIn} = useAuthContext()

    return useMutation({
        mutationFn: async (req: RegisterRequest) => {
            const response : RegisterResponse = await authPost(`/register`, req)
            await setAuthToken(response.sessionId)
            return response
        },
        onSuccess: () => {
            queryClient.refetchQueries()
            setIsLoggedIn(true)
        }
    })
}