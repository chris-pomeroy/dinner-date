package com.chris.dinnerdate.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
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