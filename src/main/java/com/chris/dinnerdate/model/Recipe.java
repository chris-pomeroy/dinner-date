package com.chris.dinnerdate.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    private Long id;

    private String title;

    private String instructions;

    @ElementCollection
    @CollectionTable(name = "ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name = "ingredient")
    private List<String> ingredients;

    private String imageName;
}
