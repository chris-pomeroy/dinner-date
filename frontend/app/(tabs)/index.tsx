import {StyleSheet, SafeAreaView, Text} from 'react-native';

import {SafeAreaProvider} from "react-native-safe-area-context";
import {RecipeCard} from "@/components/RecipeCard";

export default function HomeScreen() {
    return (
        <SafeAreaProvider>
            <SafeAreaView style={{
                alignItems: "center",
                justifyContent: "center",
            }}>
                <RecipeCard/>
            </SafeAreaView>
        </SafeAreaProvider>
    );
}

const styles = StyleSheet.create({
    titleContainer: {
        flexDirection: 'row',
        alignItems: 'center',
        gap: 8,
    },
    stepContainer: {
        gap: 8,
        marginBottom: 8,
    },
    reactLogo: {
        height: 178,
        width: 290,
        bottom: 0,
        left: 0,
        position: 'absolute',
    },
});
