package com.chris.dinnerdate.service;

import com.chris.dinnerdate.config.UserContext;
import com.chris.dinnerdate.controller.SwipeController.SwipeRequest;
import com.chris.dinnerdate.model.*;
import com.chris.dinnerdate.repository.MatchRepository;
import com.chris.dinnerdate.repository.SwipeRepository;
import com.chris.dinnerdate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.TypedSort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SwipeService {

    private final SwipeRepository swipeRepository;
    private final UserRepository userRepository;
    private final MatchRepository matchRepository;

    public void likeRecipe(SwipeRequest swipeRequest) {
        saveSwipe(SwipeType.LIKE, swipeRequest);

        if (UserContext.getLobbyId() == null) {
            return;
        }

        // find users in lobby
        List<Long> users = userRepository.findByLobbyId(UserContext.getLobbyId())
                .stream()
                .map(User::getId)
                .toList();

        if (users.size() == 1) {
            userRepository.updateCurrentLobbyId(null, UserContext.getId());
            return;
        }

        // find likes same recipe ID and user ID in list
        List<SwipeProjection> matchingLikes = swipeRepository.findLikesByRecipeIdAndUserIdInList(swipeRequest.recipeId(), users);

        // check distinct likes size same as number of users
        if (matchingLikes.size() != users.size()) {
            return;
        }

        // save match for each user
        LocalDateTime now = LocalDateTime.now();
        List<Match> matches = matchingLikes.stream()
                .map(swipe -> Match.of(swipe.getRecipeId(), swipe.getUserId(), now))
                .toList();

        matchRepository.saveAll(matches);
    }

    public void dislikeRecipe(SwipeRequest swipeRequest) {
        saveSwipe(SwipeType.DISLIKE, swipeRequest);
    }

    private void saveSwipe(SwipeType swipeType, SwipeRequest swipeRequest) {
        // Get the current timestamp in UTC and the current date in the user's time zone
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
        LocalDate localDate = now.withZoneSameInstant(swipeRequest.timeZone())
                .toLocalDate();

        swipeRepository.save(Swipe.builder()
                .recipe(Recipe.withId(swipeRequest.recipeId()))
                .user(User.withId(UserContext.getId()))
                .swipeType(swipeType)
                .createdAt(now)
                .timeZone(swipeRequest.timeZone())
                .localDate(localDate)
                .build());
    }

    public List<Recipe> getLikes(int page) {
        Sort sort = TypedSort.sort(Swipe.class)
                .by(Swipe::getCreatedAt)
                .descending();
        PageRequest pageRequest = PageRequest.of(page, 10, sort);
        return swipeRepository.findByUserIdAndSwipeType(UserContext.getId(), SwipeType.LIKE, pageRequest)
                .stream()
                .map(Swipe::getRecipe)
                .toList();
    }
}
