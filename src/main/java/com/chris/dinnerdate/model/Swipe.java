package com.chris.dinnerdate.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "swipes")
public class Swipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Recipe recipe;

    @ManyToOne
    private User user;

    @JdbcType(PostgreSQLEnumJdbcType.class)
    private SwipeType swipeType;

    private ZonedDateTime createdAt;
    private ZoneId timeZone;
    private LocalDate localDate;
}
