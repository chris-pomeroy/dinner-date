import {Image, Text, TouchableOpacity, View} from "react-native";
import React from "react";
import {getImageUrl} from "@/api/authFetch";
import {openBrowserAsync} from "expo-web-browser";
import {Recipe} from "@/hooks/queries/useRandomRecipeQuery";

type Props = {
    recipe: Recipe;
}

export default function MatchResult({recipe}: Props) {
    return (
        <TouchableOpacity
            onPress={() => openBrowserAsync(recipe.recipeUrl)}
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
                }} src={getImageUrl(recipe.imageName)}/>
                <Text style={{
                    fontSize: 20,
                    fontWeight: "bold",
                    color: "white",
                    position: "absolute",
                    bottom: 10,
                    left: 10,
                    right: 10
                }}>{recipe.title}</Text>
            </View>
        </TouchableOpacity>
    )
}