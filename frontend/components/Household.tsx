import {useMeQuery} from "@/hooks/queries/useMeQuery";
import HouseholdMember from "@/components/HouseholdMember";
import HouseholdPlaceholder from "@/components/HouseholdPlaceholder";

export default function Household() {

    const MAX_HOUSEHOLD_MEMBERS = 4

    const {data} = useMeQuery()

    if (!data) {
        // TODO loading skeleton
        return;
    }

    return (
        <>
            {data.lobbyMembers.map(member =>
                <HouseholdMember
                    firstName={member.firstName}
                    key={member.email}
                />
            )}
            {Array.from({length: Math.max(0, MAX_HOUSEHOLD_MEMBERS - data.lobbyMembers.length)}, (_, i) =>
                <HouseholdPlaceholder key={`placeholder-${i}`}/>)
            }
        </>
    );
}
