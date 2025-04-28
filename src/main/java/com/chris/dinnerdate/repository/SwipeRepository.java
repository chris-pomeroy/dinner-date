package com.chris.dinnerdate.repository;

import com.chris.dinnerdate.model.Swipe;
import com.chris.dinnerdate.model.SwipeProjection;
import com.chris.dinnerdate.model.SwipeType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwipeRepository extends JpaRepository<Swipe, Long> {

    List<Swipe> findByUserIdAndSwipeType(Long userId, SwipeType swipeType, Pageable pageable);

    @Query("""
    SELECT DISTINCT s.user.id AS userId, s.recipe.id AS recipeId
    FROM Swipe s
    WHERE s.swipeType = 'LIKE'
    AND s.recipe.id = :recipeId
    AND s.user.id IN :userIds
    """)
    List<SwipeProjection> findLikesByRecipeIdAndUserIdInList(Long recipeId, List<Long> userIds);
}
