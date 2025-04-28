package com.chris.dinnerdate.service;

import com.chris.dinnerdate.config.UserContext;
import com.chris.dinnerdate.model.Recipe;
import com.chris.dinnerdate.model.Swipe;
import com.chris.dinnerdate.model.SwipeType;
import com.chris.dinnerdate.repository.SwipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.TypedSort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SwipeService {

    private final SwipeRepository swipeRepository;

    public void likeRecipe(long recipeId) {
        saveSwipe(SwipeType.LIKE, recipeId);
    }

    public void dislikeRecipe(long recipeId) {
        saveSwipe(SwipeType.DISLIKE, recipeId);
    }

    private void saveSwipe(SwipeType swipeType, long recipeId) {
        Swipe swipe = Swipe.of(recipeId, UserContext.getId(), swipeType);
        swipeRepository.save(swipe);
    }

    public List<Recipe> getLikes(int page) {
        Sort sort = TypedSort.sort(Swipe.class)
                .by(Swipe::getTimeStamp)
                .descending();
        PageRequest pageRequest = PageRequest.of(page, 10, sort);
        return swipeRepository.findByUserIdAndSwipeType(UserContext.getId(), SwipeType.LIKE, pageRequest)
                .stream()
                .map(Swipe::getRecipe)
                .toList();
    }
}
