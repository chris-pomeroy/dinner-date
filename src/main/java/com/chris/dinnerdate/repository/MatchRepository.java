package com.chris.dinnerdate.repository;

import com.chris.dinnerdate.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByUserIdAndLocalDate(Long userId, LocalDate localDate);
}
