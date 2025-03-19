import { Animated, Image, PanResponder, Text, View } from 'react-native';

import React, { useRef, useState } from "react";
import { getImageUrl, getRandomRecipe } from "@/api/requests";
import { useQuery } from "@tanstack/react-query";

export function RecipeCard() {

    const { data: recipe } = useQuery({ queryKey: ['recipe'], queryFn: getRandomRecipe });

    const [dx, setDx] = useState(0);

    const panResponder = useRef(PanResponder.create({
        onMoveShouldSetPanResponder: () => true,
        onPanResponderMove: (_, gestureState) => setDx(gestureState.dx),
        onPanResponderRelease: () => setDx(0)
    })).current;

    return (
        <View {...panResponder.panHandlers} style={{
            borderRadius: 20,
            overflow: "hidden",
            backgroundColor: "lightgrey",
            width: "90%",
            height: "90%",
            position: "relative",
            left: dx
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
                <Text style={{ color: "white" }}>{recipe?.description}</Text>
            </View>
        </View>
    );
}

