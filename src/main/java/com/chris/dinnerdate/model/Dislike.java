package com.chris.dinnerdate.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "dislikes")
public class Dislike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long recipeId;
}
