import {Stack} from "expo-router";
import {StatusBar} from "expo-status-bar";
import LoginScreen from "@/components/LoginScreen";
import {useAuthContext} from "@/contexts/AuthContext";
import {useMeQuery} from "@/hooks/queries/useMeQuery";
import {useEffect} from "react";
import * as SplashScreen from "expo-splash-screen";

export default function BaseLayout() {

    const {isLoggedIn, setIsLoggedIn} = useAuthContext()
    const {isSuccess, isError} = useMeQuery()

    useEffect(() => {
        if (isSuccess) {
            setIsLoggedIn(true)
        }
    }, [isSuccess, setIsLoggedIn])

    useEffect(() => {
        if (isSuccess || isError) {
            SplashScreen.hideAsync();
        }
    }, [isSuccess, isError]);

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