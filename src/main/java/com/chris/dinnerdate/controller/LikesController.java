package com.chris.dinnerdate.controller;

import com.chris.dinnerdate.model.Like;
import com.chris.dinnerdate.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LikesController {

    private final LikeService likeService;

    @PostMapping("like")
    public void postLike(@RequestParam long recipeId) {
        likeService.likeRecipe(recipeId);
    }

    @GetMapping("likes")
    public List<Like> getLikes(@RequestParam(defaultValue = "0") int page) {
        return likeService.getLikes(page);
    }
}
