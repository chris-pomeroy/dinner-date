package com.chris.dinnerdate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    private Long id;
    private String title;
    private String description;
    private String imageName;
    private String recipeUrl;
}
