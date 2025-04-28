package com.chris.dinnerdate.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "swipes")
public class Swipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, insertable = false, updatable = false)
    private LocalDateTime timeStamp;

    @ManyToOne
    private Recipe recipe;

    @ManyToOne
    private User user;

    @JdbcType(PostgreSQLEnumJdbcType.class)
    private SwipeType swipeType;

    public static Swipe of(long recipeId, long userId, SwipeType swipeType) {
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);

        User user = new User();
        user.setId(userId);

        Swipe swipe = new Swipe();
        swipe.setRecipe(recipe);
        swipe.setUser(user);
        swipe.setSwipeType(swipeType);
        return swipe;
    }
}
