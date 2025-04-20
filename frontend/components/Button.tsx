import {ActivityIndicator, Text, TouchableOpacity} from "react-native";
import {IconSymbol} from "@/components/ui/IconSymbol";
import React from "react";


type Props = {
    onPress: () => void,
    text: string,
    isLoading: boolean,
    isError: boolean,
}

export default function Button({onPress, text, isLoading, isError}: Props) {

    return (
        <TouchableOpacity
            onPress={onPress}
            style={{
                height: 50,
                width: "80%",
                backgroundColor: "turquoise",
                alignItems: "center",
                justifyContent: "center",
                borderRadius: 5,
            }}>
            {
                isLoading ? (
                    <ActivityIndicator
                        size="small"
                        color="white"
                        animating={true}
                    />
                ) :
                isError ? (
                    <IconSymbol
                        size={28}
                        name="exclamationmark.circle"
                        color="white"
                    />
                ) : (
                    <Text style={{
                        fontWeight: "bold",
                        color: "white"
                    }}>{text}</Text>
                )
            }
        </TouchableOpacity>
    )
}