package com.chris.dinnerdate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "lobby_memberships")
@IdClass(LobbyMembership.class)
public class LobbyMembership {

    @Id
    private Long userId;

    @Id
    private Long lobbyId;
}