import {authFetch, authPost, setAuthToken} from "@/api/authFetch";
import {useMutation, useQueryClient} from "@tanstack/react-query";
import {LoginRequest, useLoginMutation} from "@/hooks/mutations/useLoginMutation";
import {useMemo} from "react";
import {useMeQuery} from "@/hooks/queries/useMeQuery";

export default function() {
    const queryClient = useQueryClient();

    return useMutation({
        mutationFn: async () => {
            await authPost("/logout")
            setAuthToken(null)
            await queryClient.invalidateQueries();
        }
    })
}
