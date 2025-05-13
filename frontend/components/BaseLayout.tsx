import {Stack} from "expo-router";
import {StatusBar} from "expo-status-bar";
import LoginScreen from "@/components/LoginScreen";
import {useAuthContext} from "@/contexts/AuthContext";
import {useMeQuery} from "@/hooks/queries/useMeQuery";
import {useEffect} from "react";

export default function BaseLayout() {

    const {isLoggedIn, setIsLoggedIn} = useAuthContext()
    const {isSuccess} = useMeQuery()

    useEffect(() => {
        if (isSuccess) {
            setIsLoggedIn(true)
        }
    }, [isSuccess, setIsLoggedIn])

    if (!isLoggedIn) {
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