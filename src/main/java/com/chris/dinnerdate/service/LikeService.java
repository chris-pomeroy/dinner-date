package com.chris.dinnerdate.service;

import com.chris.dinnerdate.model.Like;
import com.chris.dinnerdate.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.TypedSort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    public void likeRecipe(long recipeId) {
        likeRepository.save(Like.withRecipeId(recipeId));
    }

    public List<Like> getLikes(int page) {
        Sort sort = TypedSort.sort(Like.class)
                .by(Like::getTimeStamp)
                .descending();
        return likeRepository.findBy(PageRequest.of(page, 10, sort));
    }
}
