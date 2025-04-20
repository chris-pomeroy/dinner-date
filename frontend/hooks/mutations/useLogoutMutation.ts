import {authPost, setAuthToken} from "@/api/authFetch";
import {useMutation, useQueryClient} from "@tanstack/react-query";

export default function() {
    const queryClient = useQueryClient();

    return useMutation({
        mutationFn: async () => {
            await authPost("/logout")
            setAuthToken(null)
            await queryClient.setQueryData(['me'], null);
        }
    })
}
