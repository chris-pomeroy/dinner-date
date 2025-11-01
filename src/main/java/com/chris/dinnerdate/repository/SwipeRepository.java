package com.chris.dinnerdate.repository;

import com.chris.dinnerdate.model.Swipe;
import com.chris.dinnerdate.model.SwipeProjection;
import com.chris.dinnerdate.model.SwipeType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SwipeRepository extends CrudRepository<Swipe, Long> {

    @Query("""
    SELECT *
    FROM swipes s
    WHERE s.user_id = :userId
    AND s.swipe_type = :swipeType
    """)
    List<Swipe> findByUserIdAndSwipeType(Long userId, SwipeType swipeType, Pageable pageable);

    @Query("""
    SELECT DISTINCT s.user_id AS userId, s.recipe_id AS recipeId
    FROM swipes s
    WHERE s.swipe_type = 'LIKE'
    AND s.recipe_id = :recipeId
    AND s.localDate = :localDate
    AND s.user_id IN :userIds
    """)
    List<SwipeProjection> findLikesByRecipeIdAndLocalDateAndUserIdInList(Long recipeId, LocalDate localDate, List<Long> userIds);
}
