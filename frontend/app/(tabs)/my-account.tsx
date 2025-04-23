import {SafeAreaView} from 'react-native';
import useLogoutMutation from "@/hooks/mutations/useLogoutMutation";
import Button from "@/components/Button";

export default function MyAccountScreen() {

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
