package com.chris.dinnerdate.service;

import com.chris.dinnerdate.model.Like;
import com.chris.dinnerdate.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    public void likeRecipe(long recipeId) {
        Like like = new Like();
        like.setRecipeId(recipeId);
        likeRepository.save(like);
    }

    public List<Like> getLikes() {
        List<Like> likes = new ArrayList<>();
        likeRepository.findAll().forEach(likes::add);
        return likes;
    }
}
