package com.chris.dinnerdate.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "matches")
public class Match {

    @Id
    private Long id;

    private Recipe recipe;

    private LocalDateTime matchedAt;
    private LocalDate localDate;
    private Long userId;

    public static Match of(long recipeId, long userId, LocalDate localDate, LocalDateTime matchedAt) {
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);

        Match swipe = new Match();
        swipe.setRecipe(recipe);
        swipe.setMatchedAt(matchedAt);
        swipe.setLocalDate(localDate);
        swipe.setUserId(userId);
        return swipe;
    }
}