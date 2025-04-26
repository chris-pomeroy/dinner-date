package com.chris.dinnerdate.model;

import com.chris.dinnerdate.config.UserContext;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, insertable = false, updatable = false)
    private LocalDateTime timeStamp;

    @ManyToOne
    private Recipe recipe;

    @ManyToOne
    private User user;

    public static Like withRecipeId(long recipeId) {
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        Like like = new Like();
        like.setRecipe(recipe);
        User user = new User();
        user.setId(UserContext.getId());
        like.setUser(user);
        return like;
    }
}
