package com.chris.dinnerdate.controller;

import com.chris.dinnerdate.model.Match;
import com.chris.dinnerdate.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/matches")
public class MatchController {

    private final MatchService matchService;

    @GetMapping("/today")
    public List<Match> getMatches(@RequestParam ZoneId timeZone) {
        return matchService.getTodayMatches(timeZone);
    }
}
