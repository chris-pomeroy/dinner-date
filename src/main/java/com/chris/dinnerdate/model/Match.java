package com.chris.dinnerdate.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Recipe recipe;

    private LocalDateTime matchedAt;
    private Long userId;

    public static Match of(long recipeId, long userId, LocalDateTime matchedAt) {
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);

        Match swipe = new Match();
        swipe.setRecipe(recipe);
        swipe.setUserId(userId);
        swipe.setMatchedAt(matchedAt);
        return swipe;
    }
}