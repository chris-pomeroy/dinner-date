package com.chris.dinnerdate.controller;

import com.chris.dinnerdate.config.UserContext;
import com.chris.dinnerdate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MeController {

    public record MeResponse(String firstName, String email, List<LobbyMember> lobbyMembers) {}
    public record LobbyMember(String firstName, String email) {}

    private final UserService userService;

    @GetMapping("/me")
    public MeResponse getMe() {
        return new MeResponse(
                UserContext.getFirstName(),
                UserContext.getEmail(),
                userService.getLobbyMembers()
        );
    }
}
