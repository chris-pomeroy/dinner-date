import {FlatList, SafeAreaView} from 'react-native';
import {useQuery} from "@tanstack/react-query";
import {getLikes, Like} from "@/api/requests";
import MatchResult from "@/components/MatchResult";

export default function MatchesScreen() {

  const { data: likes } = useQuery<Like[]>({ queryKey: ['likes'], queryFn: () => getLikes() });

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
