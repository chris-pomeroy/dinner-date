import {Stack} from "expo-router";
import {StatusBar} from "expo-status-bar";
import {useMeQuery} from "@/hooks/queries/useMeQuery";
import LoginScreen from "@/components/LoginScreen";
import {authTokenIsSet} from "@/api/authFetch";

export default function BaseLayout() {

    const {isLoggedIn} = useMeQuery();

    if (!isLoggedIn || !authTokenIsSet()) {
        return <LoginScreen />
    }

    return (
        <>
            <Stack>
                <Stack.Screen name="(tabs)" options={{headerShown: false}}/>
                <Stack.Screen name="+not-found"/>
            </Stack>
            <StatusBar style="auto"/>
        </>
    )
}