import {FlatList, SafeAreaView} from 'react-native';
import MatchResult from "@/components/MatchResult";
import {useLikesQuery} from "@/hooks/queries/useLikesQuery";
import {useFocusEffect} from "expo-router";
import {useCallback} from "react";

export default function MatchesScreen() {

  const { data: likes, refetch } = useLikesQuery()

    useFocusEffect(
        useCallback(() => {
            refetch();
        }, [])
    )

  return (
      <SafeAreaView>
        <FlatList
            data={likes}
            keyExtractor={(item) => item.key}
            contentContainerStyle={{
                gap: 20,
                width: "100%",
            }}
            renderItem={({ item }) => <MatchResult recipe={item}/>}
        />
      </SafeAreaView>
  );
}
