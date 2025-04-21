import {useQuery} from '@tanstack/react-query';
import {authFetch, FetchError} from "@/api/authFetch";

export const useMeQuery = () => {
    const query = useQuery<{ email: string }, FetchError>({
        queryKey: ['me'],
        queryFn: () => authFetch(`/me`),
        retry: (failureCount, error) => {
            if (error.status === 401) {
                return false;
            }
            return failureCount < 3;
        },
    });

    return {
        ...query,
        isLoggedIn: query.data?.email,
    };
};
