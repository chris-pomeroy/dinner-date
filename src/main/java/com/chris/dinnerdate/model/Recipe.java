package com.chris.dinnerdate.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(name = "recipes")
public class Recipe {

    @Id
    private Long id;
    private String title;
    private String description;
    private String imageName;
    private String recipeUrl;

    public static Recipe withId(Long id) {
        Recipe recipe = new Recipe();
        recipe.setId(id);
        return recipe;
    }
}
