import {FlatList, SafeAreaView} from 'react-native';
import MatchResult from "@/components/MatchResult";
import {useLikesQuery} from "@/hooks/queries/useLikesQuery";

export default function MatchesScreen() {

  const { data: likes } = useLikesQuery()

  return (
      <SafeAreaView>
        <FlatList
            data={likes}
            keyExtractor={(item) => `${item.id}`}
            contentContainerStyle={{
                gap: 20,
                width: "100%",
            }}
            renderItem={({ item }) => <MatchResult like={item}/>}
        />
      </SafeAreaView>
  );
}
