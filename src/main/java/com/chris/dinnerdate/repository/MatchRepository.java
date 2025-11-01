package com.chris.dinnerdate.repository;

import com.chris.dinnerdate.model.Match;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {

    List<Match> findByUserIdAndLocalDate(Long userId, LocalDate localDate);

    List<Match> findByUserId(Long userId, Pageable pageable);
}
