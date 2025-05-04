import {useQuery} from '@tanstack/react-query';
import {authGet, FetchError} from "@/api/authFetch";

export const useMeQuery = () => {
    const query = useQuery<{ email: string }, FetchError>({
        queryKey: ['me'],
        queryFn: () => authGet(`/me`),
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
