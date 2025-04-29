package com.chris.dinnerdate.controller;

import com.chris.dinnerdate.model.Match;
import com.chris.dinnerdate.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @GetMapping("/matches")
    public List<Match> getMatches() {
        return matchService.getMatches();
    }
}
