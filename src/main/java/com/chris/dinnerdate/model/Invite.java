package com.chris.dinnerdate.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
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

    private User user;
    private LocalDateTime createdAt;
}