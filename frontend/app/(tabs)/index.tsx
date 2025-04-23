import {SafeAreaView} from 'react-native';

import CardStack from "@/components/CardStack";

export default function HomeScreen() {
    return (
        <SafeAreaView style={{
            alignItems: "center",
            justifyContent: "center"
        }}>
            <CardStack/>
        </SafeAreaView>
    );
}
