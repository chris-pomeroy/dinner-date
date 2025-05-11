import {useMeQuery} from "@/hooks/queries/useMeQuery";
import HouseholdMember from "@/components/HouseholdMember";
import HouseholdPlaceholder from "@/components/HouseholdPlaceholder";
import {useLocalSearchParams} from "expo-router";
import {useEffect} from "react";
import {useJoinMutation} from "@/hooks/mutations/useJoinMutation";

export default function Household() {

    const MAX_HOUSEHOLD_MEMBERS = 4

    const {data} = useMeQuery()
    const {token} = useLocalSearchParams()
    const {mutate: join} = useJoinMutation()

    useEffect(() => {
        if (!token) {
            return;
        }
        const joinToken = Array.isArray(token) ? token[0] : token
        join(joinToken)
    }, [join, token])

    if (!data) {
        // TODO loading skeleton
        return;
    }

    return (
        <>
            <HouseholdMember
                firstName={data.firstName}
                key={data.email}
            />
            {data.lobbyMembers.map(member =>
                <HouseholdMember
                    firstName={member.firstName}
                    key={member.email}
                />
            )}
            {Array.from({length: Math.max(0, MAX_HOUSEHOLD_MEMBERS - 1 - (data.lobbyMembers ? data.lobbyMembers.length : 0))}, (_, i) =>
                <HouseholdPlaceholder key={`placeholder-${i}`}/>)
            }
        </>
    );
}
