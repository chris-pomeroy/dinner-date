package com.chris.dinnerdate.service;

import com.chris.dinnerdate.model.Recipe;
import com.chris.dinnerdate.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.LongStream;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final Long recipeCount;

    public RecipeService(RecipeRepository recipeRepository) {
        recipeCount = recipeRepository.count();
        this.recipeRepository = recipeRepository;
    }

    public Iterable<Recipe> getRandomRecipes(int count) {
        List<Long> randomIds = LongStream.generate(() -> ThreadLocalRandom.current().nextLong(recipeCount) + 1)
                .limit(count)
                .boxed()
                .toList();
        return recipeRepository.findAllById(randomIds);
    }
}
