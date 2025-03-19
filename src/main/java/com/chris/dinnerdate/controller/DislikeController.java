package com.chris.dinnerdate.controller;

import com.chris.dinnerdate.model.Dislike;
import com.chris.dinnerdate.service.DislikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DislikeController {

    private final DislikeService dislikeService;

    @PostMapping("dislike")
    public void postDislike(@RequestParam long recipeId) {
        dislikeService.dislikeRecipe(recipeId);
    }

    @GetMapping("dislikes")
    public List<Dislike> getDislikes() {
        return dislikeService.getDislikes();
    }
}
