package com.chris.dinnerdate.controller;

import com.chris.dinnerdate.service.DislikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DislikeController {

    private final DislikeService dislikeService;

    @PostMapping("dislike")
    public void postDislike(@RequestParam long recipeId) {
        dislikeService.dislikeRecipe(recipeId);
    }
}
