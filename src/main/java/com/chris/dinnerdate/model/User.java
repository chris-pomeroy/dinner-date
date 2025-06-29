package com.chris.dinnerdate.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String email;
    private String passwordHash;
    private String sessionId;
    private Long lobbyId;

    public static User withId(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }
}