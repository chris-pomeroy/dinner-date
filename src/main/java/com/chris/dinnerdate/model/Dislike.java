package com.chris.dinnerdate.model;

import com.chris.dinnerdate.config.UserContext;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
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

    @ManyToOne
    private User user;

    public static Dislike withRecipeId(long recipeId) {
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        Dislike dislike = new Dislike();
        dislike.setRecipe(recipe);
        User user = new User();
        user.setId(UserContext.getId());
        dislike.setUser(user);
        return dislike;
    }
}
