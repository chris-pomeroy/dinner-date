package com.chris.dinnerdate.controller;

import com.chris.dinnerdate.service.LobbyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lobby")
@RequiredArgsConstructor
public class LobbyController {

    private final LobbyService lobbyService;

    public record CreateLobbyResponse(String joinCode) {}

    @PostMapping("/create")
    public CreateLobbyResponse createLobby() {
        return new CreateLobbyResponse(lobbyService.createLobby());
    }
}
