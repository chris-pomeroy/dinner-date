package com.chris.dinnerdate.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "invites")
public class Invite {

    public static Invite of(Long userId, String inviteToken) {
        User user = new User();
        user.setId(userId);

        Invite invite = new Invite();
        invite.setInviteToken(inviteToken);
        invite.setUser(user);

        return invite;
    }

    @Id
    private String inviteToken;

    @ManyToOne
    private User user;

    @Column(nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;
}