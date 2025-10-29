import {useFocusEffect} from "expo-router";
import {useCallback} from "react";
import {FlatList} from "react-native";
import MatchResult from "@/components/MatchResult";
import {useMatchesQuery} from "@/hooks/queries/useMatchesQuery";

export default function MatchesList() {

    const {data: matches, refetch} = useMatchesQuery()

    useFocusEffect(
        useCallback(() => {
            refetch();
        }, [])
    )

    return (
        <FlatList
            data={matches}
            keyExtractor={(item) => `${item.id}`}
            contentContainerStyle={{
                gap: 20,
                width: "100%",
            }}
            renderItem={({item}) => <MatchResult recipe={item.recipe}/>}
        />
    );
}