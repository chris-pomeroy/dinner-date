import {useQuery} from '@tanstack/react-query';
import {authFetch} from "@/api/authFetch";

export const useMeQuery = () => {
    const query = useQuery<{ email: string }>({
        queryKey: ['me'],
        queryFn: () => authFetch(`/me`)
    });

    return {
        ...query,
        isLoggedIn: query.isSuccess && query.data.email,
    };
};
