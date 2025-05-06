import {useCallback} from "react";
import {useLikesQuery} from "@/hooks/queries/useLikesQuery";
import {useFocusEffect} from "expo-router";
import {FlatList} from "react-native";
import MatchResult from "@/components/MatchResult";

export default function LikesList() {

    const {data: likes, refetch} = useLikesQuery()

    useFocusEffect(
        useCallback(() => {
            refetch();
        }, [])
    )

    return (
        <FlatList
            data={likes}
            keyExtractor={(item) => item.key}
            contentContainerStyle={{
                gap: 20,
                width: "100%",
            }}
            renderItem={({item}) => <MatchResult recipe={item}/>}
        />
    );
}