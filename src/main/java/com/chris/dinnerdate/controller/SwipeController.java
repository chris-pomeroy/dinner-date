package com.chris.dinnerdate.controller;

import com.chris.dinnerdate.model.Recipe;
import com.chris.dinnerdate.service.SwipeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SwipeController {

    public record SwipeRequest(
            @NotNull Long recipeId,
            @NotNull ZoneId timeZone
    ) {}

    private final SwipeService swipeService;

    @PostMapping("like")
    public void postLike(@Valid @RequestBody SwipeRequest swipeRequest) {
        swipeService.likeRecipe(swipeRequest);
    }

    @GetMapping("likes")
    public List<Recipe> getLikes(@RequestParam(defaultValue = "0") int page) {
        return swipeService.getLikes(page);
    }

    @PostMapping("dislike")
    public void postDislike(@Valid @RequestBody SwipeRequest swipeRequest) {
        swipeService.dislikeRecipe(swipeRequest);
    }
}
