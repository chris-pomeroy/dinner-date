import {Pressable, StyleSheet, Text, View} from "react-native";
import React from "react";

type Props = {
    isSwitched: boolean;
    setIsSwitched: (isSwitched: boolean) => void;
}

export default function Toggle({ isSwitched, setIsSwitched }: Props) {

    return (
        <View style={{
            alignItems: "center"
        }}>
            <View style={{
                height: 40,
                width: "90%",
                borderRadius: 10,
                marginBottom: 10,
                backgroundColor: "turquoise",
                padding: 5,
                flexDirection: "row",
                alignItems: "center",
                justifyContent: "center"
            }}>
                <Pressable
                    onPress={() => setIsSwitched(false)}
                    style={[
                        styles.pressable,
                        !isSwitched && styles.pressableActive
                    ]}>
                    <Text style={[
                        styles.text,
                        !isSwitched && styles.textActive
                    ]}>Matches</Text>
                </Pressable>
                <Pressable
                    onPress={() => setIsSwitched(true)}
                    style={[
                        styles.pressable,
                        isSwitched && styles.pressableActive
                    ]}>
                    <Text style={[
                        styles.text,
                        isSwitched && styles.textActive
                    ]}>Likes</Text>
                </Pressable>
            </View>
        </View>
    )
}

const styles = StyleSheet.create({
    pressable: {
        flex: 1,
        alignItems: "center",
        justifyContent: "center",
        borderRadius: 5,
        height: "100%",
    },
    pressableActive: {
        backgroundColor: "white",
    },
    text: {
        color: "white",
        fontWeight: "bold",
    },
    textActive: {
        color: "turquoise",
    }
});