import {authPost, clearAuthToken} from "@/api/authFetch";
import {useMutation, useQueryClient} from "@tanstack/react-query";
import {useAuthContext} from "@/contexts/AuthContext";

export default function() {
    const queryClient = useQueryClient()
    const {setIsLoggedIn} = useAuthContext()

    return useMutation({
        mutationFn: async () => {
            await authPost("/logout")
            await clearAuthToken()
            setIsLoggedIn(false)
            queryClient.clear()
        }
    })
}
