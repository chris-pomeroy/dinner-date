import {Image, PanResponder, Text, View} from 'react-native';
import React, {useMemo, useState} from "react";
import {getImageUrl} from "@/api/authFetch";
import {RecipeWithKey} from "@/hooks/queries/useRandomRecipeQuery";
import {useLikeMutation} from "@/hooks/mutations/useLikeMutation";
import {useDislikeMutation} from "@/hooks/mutations/useDislikeMutation";

type Props = {
    recipe: RecipeWithKey;
    onSwipe: () => void;
    zIndex: number;
}

export function RecipeCard({recipe, onSwipe, zIndex}: Props) {

    const DX_THRESHOLD = 100;

    const {mutate: like} = useLikeMutation();
    const {mutate: dislike} = useDislikeMutation();

    const [dx, setDx] = useState(0);

    const panResponder = useMemo(() => PanResponder.create({
        onMoveShouldSetPanResponder: () => true,
        onPanResponderMove: (_, gestureState) => setDx(gestureState.dx),
        onPanResponderRelease: (_, gestureState) => {
            if (!recipe) {
                return
            }
            if (gestureState.dx > DX_THRESHOLD) {
                like(recipe.id)
                onSwipe()
            }
            if (gestureState.dx < -DX_THRESHOLD) {
                dislike(recipe.id)
                onSwipe()
            }
            setDx(0)
        }
    }), [recipe]);

    return (
        <View {...panResponder.panHandlers} style={{
            borderRadius: 20,
            overflow: "hidden",
            backgroundColor: "lightgrey",
            width: "100%",
            height: "100%",
            position: "absolute",
            left: dx,
            transform: [{rotate: `${dx * 0.03}deg`}],
            zIndex: zIndex
        }}>
            <Image style={{
                width: "100%",
                height: "100%"
            }}
                   src={getImageUrl(recipe?.imageName || "")}
            />
            <View style={{
                position: "absolute",
                top: 25,
                left: 20,
                padding: 5,
                borderWidth: 5,
                borderColor: "green",
                borderRadius: 10,
                transform: [{rotate: "-15deg"}],
                display: dx > DX_THRESHOLD ? "flex" : "none"
            }}>
                <Text style={{
                    fontSize: 60,
                    fontWeight: "bold",
                    color: "green"
                }}>LIKE</Text>
            </View>
            <View style={{
                position: "absolute",
                top: 25,
                right: 20,
                padding: 5,
                borderWidth: 5,
                borderColor: "red",
                borderRadius: 10,
                transform: [{rotate: "15deg"}],
                display: dx < -DX_THRESHOLD ? "flex" : "none"
            }}>
                <Text style={{
                    fontSize: 60,
                    fontWeight: "bold",
                    color: "red"
                }}>NOPE</Text>
            </View>
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

