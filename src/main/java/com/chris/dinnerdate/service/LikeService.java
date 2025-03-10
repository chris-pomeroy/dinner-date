package com.chris.dinnerdate.service;

import com.chris.dinnerdate.model.Like;
import com.chris.dinnerdate.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    public void likeRecipe(long recipeId) {
        Like like = new Like();
        like.setRecipeId(recipeId);
        likeRepository.save(like);
    }
}
