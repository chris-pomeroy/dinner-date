package com.chris.dinnerdate.service;

import com.chris.dinnerdate.model.Recipe;
import com.chris.dinnerdate.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final Long recipeCount;

    public RecipeService(RecipeRepository recipeRepository) {
        recipeCount = recipeRepository.count();
        this.recipeRepository = recipeRepository;
    }

    public Recipe getRandomRecipe() {
        Long randomID = ThreadLocalRandom.current().nextLong(recipeCount) + 1;
        return recipeRepository.findById(randomID)
                .orElseThrow();
    }
}
