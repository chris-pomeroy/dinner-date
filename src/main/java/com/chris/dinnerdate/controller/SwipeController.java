package com.chris.dinnerdate.controller;

import com.chris.dinnerdate.model.Recipe;
import com.chris.dinnerdate.service.SwipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SwipeController {

    private final SwipeService swipeService;

    @PostMapping("like")
    public void postLike(@RequestParam long recipeId) {
        swipeService.likeRecipe(recipeId);
    }

    @GetMapping("likes")
    public List<Recipe> getLikes(@RequestParam(defaultValue = "0") int page) {
        return swipeService.getLikes(page);
    }

    @PostMapping("dislike")
    public void postDislike(@RequestParam long recipeId) {
        swipeService.dislikeRecipe(recipeId);
    }
}
