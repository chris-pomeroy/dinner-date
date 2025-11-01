package com.chris.dinnerdate.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(name = "lobbies")
public class Lobby {

    @Id
    private Long id;
}