import {DarkTheme, DefaultTheme, ThemeProvider} from '@react-navigation/native';
import * as SplashScreen from 'expo-splash-screen';
import 'react-native-reanimated';

import {useColorScheme} from '@/hooks/useColorScheme';
import {QueryClient} from "@tanstack/query-core";
import {QueryClientProvider} from "@tanstack/react-query";
import {SafeAreaProvider} from "react-native-safe-area-context";
import BaseLayout from "@/components/BaseLayout";
import {AuthProvider} from "@/contexts/AuthContext";

// Prevent the splash screen from auto-hiding before asset loading is complete.
SplashScreen.preventAutoHideAsync();

export default function RootLayout() {
    const colorScheme = useColorScheme();
    const queryClient = new QueryClient()

    return (
        <QueryClientProvider client={queryClient}>
            <ThemeProvider value={colorScheme === 'dark' ? DarkTheme : DefaultTheme}>
                <AuthProvider>
                    <SafeAreaProvider>
                        <BaseLayout/>
                    </SafeAreaProvider>
                </AuthProvider>
            </ThemeProvider>
        </QueryClientProvider>
    );
}
