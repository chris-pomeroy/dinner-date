package com.chris.dinnerdate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "invites")
public class Invite {

    @Id
    private String inviteToken;
    private Long userId;

    @Column(nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;
}