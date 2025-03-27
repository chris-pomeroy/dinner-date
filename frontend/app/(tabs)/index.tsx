import {SafeAreaView} from 'react-native';

import {RecipeCard} from "@/components/RecipeCard";

export default function HomeScreen() {
    return (
        <SafeAreaView style={{
            alignItems: "center",
            justifyContent: "center",
        }}>
            <RecipeCard/>
        </SafeAreaView>
    );
}
