package com.chris.dinnerdate.service;

import com.chris.dinnerdate.model.Dislike;
import com.chris.dinnerdate.repository.DislikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DislikeService {

    private final DislikeRepository dislikeRepository;

    public void dislikeRecipe(long recipeId) {
        dislikeRepository.save(Dislike.withRecipeId(recipeId));
    }

    public List<Dislike> getDislikes(int page) {
        Sort sort = Sort.TypedSort.sort(Dislike.class)
                .by(Dislike::getTimeStamp)
                .descending();
        return dislikeRepository.findBy(PageRequest.of(page, 10, sort));
    }
}
