import {useQuery} from '@tanstack/react-query';
import {authGet, FetchError} from "@/api/authFetch";

type Me = {
    firstName: string;
    email: string;
    lobbyMembers: LobbyMember[];
}

type LobbyMember = {
    firstName: string;
    email: string;
}

export const useMeQuery = () => {
    return useQuery<Me, FetchError>({
        queryKey: ['me'],
        queryFn: () => authGet(`/me`),
        retry: false
    });
};