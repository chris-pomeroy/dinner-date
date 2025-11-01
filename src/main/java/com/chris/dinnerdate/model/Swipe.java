package com.chris.dinnerdate.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "swipes")
public class Swipe {

    @Id
    private Long id;

    private Recipe recipe;
    private Long userId;
    private SwipeType swipeType;

    private ZonedDateTime createdAt;
    private ZoneId timeZone;
    private LocalDate localDate;
}
