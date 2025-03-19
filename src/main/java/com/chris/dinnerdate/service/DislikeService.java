package com.chris.dinnerdate.service;

import com.chris.dinnerdate.model.Dislike;
import com.chris.dinnerdate.repository.DislikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DislikeService {

    private final DislikeRepository dislikeRepository;

    public void dislikeRecipe(long recipeId) {
        Dislike dislike = new Dislike();
        dislike.setRecipeId(recipeId);
        dislikeRepository.save(dislike);
    }

    public List<Dislike> getDislikes() {
        List<Dislike> dislikes = new ArrayList<>();
        dislikeRepository.findAll().forEach(dislikes::add);
        return dislikes;
    }
}
