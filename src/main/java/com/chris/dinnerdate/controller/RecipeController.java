package com.chris.dinnerdate.controller;

import com.chris.dinnerdate.model.Recipe;
import com.chris.dinnerdate.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/random")
    public Iterable<Recipe> getRandomRecipes(@RequestParam(defaultValue = "1") int count) {
        return recipeService.getRandomRecipes(count);
    }
}
