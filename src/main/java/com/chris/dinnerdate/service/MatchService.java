package com.chris.dinnerdate.service;

import com.chris.dinnerdate.config.UserContext;
import com.chris.dinnerdate.model.Match;
import com.chris.dinnerdate.model.Recipe;
import com.chris.dinnerdate.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;

    public List<Recipe> getTodayMatches(ZoneId timeZone) {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
        LocalDate localDate = now.withZoneSameInstant(timeZone)
                .toLocalDate();

        return matchRepository.findByUserIdAndLocalDate(UserContext.getId(), localDate)
                .stream()
                .map(Match::getRecipe)
                .toList();
    }

    public List<Recipe> getMatches(int page) {
        Sort sort = Sort.TypedSort.sort(Match.class)
                .by(Match::getMatchedAt)
                .descending();
        PageRequest pageRequest = PageRequest.of(page, 10, sort);
        return matchRepository.findByUserId(UserContext.getId(), pageRequest)
                .stream()
                .map(Match::getRecipe)
                .toList();
    }
}
