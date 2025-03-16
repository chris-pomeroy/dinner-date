import {Image, Text, View} from 'react-native';
import {IconSymbol} from "@/components/ui/IconSymbol";
import React from "react";
import {getImageUrl, getRandomRecipe} from "@/api/requests";
import {useQuery} from "@tanstack/react-query";

export function RecipeCard() {

    const {data: recipe} = useQuery({queryKey: ['recipe'], queryFn: getRandomRecipe});

    return (
        <View style={{
            borderRadius: 20,
            overflow: "hidden",
            backgroundColor: "lightgrey",
            width: "90%",
            height: "90%"
        }}>
            <Image
                style={{
                    width: "100%",
                    height: "100%"
                }}
                src={getImageUrl(recipe?.imageName || "")}
            />
            <View style={{
                position: "absolute",
                bottom: 10,
                left: 10,
                right: 10
            }}>
                <Text style={{
                    fontSize: 30,
                    fontWeight: "bold",
                    color: "white"
                }}>{recipe?.title}</Text>
                <Text style={{color: "white"}}>{recipe?.description}</Text>
            </View>
        </View>
    );
}

