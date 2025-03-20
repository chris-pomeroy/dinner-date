package com.chris.dinnerdate.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "dislikes")
public class Dislike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, insertable = false, updatable = false)
    private LocalDateTime timeStamp;

    @ManyToOne
    private Recipe recipe;

    public static Dislike withRecipeId(long recipeId) {
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        Dislike dislike = new Dislike();
        dislike.setRecipe(recipe);
        return dislike;
    }
}
