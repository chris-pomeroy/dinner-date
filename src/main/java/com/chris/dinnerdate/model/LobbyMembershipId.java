package com.chris.dinnerdate.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class LobbyMembershipId {

    private Long userId;
    private Long lobbyId;
}