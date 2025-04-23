import {useRandomRecipeQuery} from "@/hooks/queries/useRandomRecipeQuery";
import {RecipeCard} from "@/components/RecipeCard";
import {View} from "react-native";

const RECIPES_TO_CACHE = 5

export default function CardStack() {

    const {recipes, popRecipe} = useRandomRecipeQuery(RECIPES_TO_CACHE)

    return (
        <View style={{
            width: "90%",
            height: "90%",
            position: "relative",
            alignItems: "center",
            justifyContent: "center"
        }}>
            {recipes.map((recipe, index) => {
                return (
                    <RecipeCard
                        recipe={recipe}
                        onSwipe={popRecipe}
                        zIndex={RECIPES_TO_CACHE - index}
                        key={recipe.key}
                    />
                )
            })}
        </View>
    )
}