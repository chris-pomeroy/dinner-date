import {Image, Linking, Text, TouchableOpacity, View} from "react-native";
import {Like} from "@/api/requests";
import React from "react";
import {getImageUrl} from "@/api/authFetch";
import {openBrowserAsync} from "expo-web-browser";

type Props = {
    like: Like;
}

export default function MatchResult({like}: Props) {
    return (
        <TouchableOpacity
            onPress={() => openBrowserAsync(like.recipe.recipeUrl)}
            style={{
                width: "100%",
                flexDirection: "row",
                justifyContent: "center",
            }}>
            <View style={{
                width: "90%",
                height: 250,
                borderRadius: 10,
                overflow: "hidden"
            }}>
                <Image style={{
                    width: "100%",
                    height: "100%"
                }} src={getImageUrl(like.recipe.imageName)}/>
                <Text style={{
                    fontSize: 20,
                    fontWeight: "bold",
                    color: "white",
                    position: "absolute",
                    bottom: 10,
                    left: 10,
                    right: 10
                }}>{like.recipe.title}</Text>
            </View>
        </TouchableOpacity>
    )
}