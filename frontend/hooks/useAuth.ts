import { useQuery } from '@tanstack/react-query';
import {getMe, Me} from "@/api/requests";

export const useAuth = () => {
    const query = useQuery<Me>({
        queryKey: ['me'],
        queryFn: getMe
    });

    return {
        ...query,
        isLoggedIn: query.isSuccess && query.data.email,
    };
};
