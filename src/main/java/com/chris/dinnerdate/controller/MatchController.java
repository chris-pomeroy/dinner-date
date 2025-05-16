package com.chris.dinnerdate.controller;

import com.chris.dinnerdate.model.Recipe;
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

    // TODO this endpoint not currently in use
    @GetMapping("/today")
    public List<Recipe> getTodayMatches(@RequestParam ZoneId timeZone) {
        return matchService.getTodayMatches(timeZone);
    }

    @GetMapping("/all")
    public List<Recipe> getMatches(@RequestParam(defaultValue = "0") int page) {
        return matchService.getMatches(page);
    }
}
