import {FlatList, SafeAreaView, TouchableOpacity} from 'react-native';
import MatchResult from "@/components/MatchResult";
import {useLikesQuery} from "@/hooks/queries/useLikesQuery";
import useLogoutMutation from "@/hooks/mutations/useLogoutMutation";
import Button from "@/components/Button";

export default function MatchesScreen() {

    const {mutate: logout, isError, isPending} = useLogoutMutation();

    return (
        <SafeAreaView style={{
            height: "100%",
            alignItems: "center",
            justifyContent: "center"
        }}>
            <Button
                onPress={logout}
                text="Sign out"
                isError={isError}
                isLoading={isPending}
            />
        </SafeAreaView>
    );
}
