package com.chris.dinnerdate.service;

import com.chris.dinnerdate.config.TokenGenerator;
import com.chris.dinnerdate.config.UserContext;
import com.chris.dinnerdate.model.Lobby;
import com.chris.dinnerdate.model.LobbyMembership;
import com.chris.dinnerdate.repository.LobbyMembershipRepository;
import com.chris.dinnerdate.repository.LobbyRepository;
import com.chris.dinnerdate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class LobbyService {

    private static final Duration LOBBY_JOIN_CODE_EXPIRY = Duration.ofDays(7);
    private static final int JOIN_CODE_ENTROPY_IN_BYTES = 8;

    private final LobbyRepository lobbyRepository;
    private final UserRepository userRepository;
    private final TokenGenerator tokenGenerator;
    private final LobbyMembershipRepository lobbyMembershipRepository;

    // Create a new lobby and add the user who created it
    public String createLobby() {
        String joinCode = tokenGenerator.generate(JOIN_CODE_ENTROPY_IN_BYTES);

        Lobby lobby = new Lobby();
        lobby.setJoinCode(joinCode);
        lobby.setJoinCodeExpiry(LocalDateTime.now().plus(LOBBY_JOIN_CODE_EXPIRY));
        lobbyRepository.save(lobby);

        userRepository.updateCurrentLobbyIdById(lobby.getId(), UserContext.getId());

        LobbyMembership lobbyMembership = new LobbyMembership();
        lobbyMembership.setUserId(UserContext.getId());
        lobbyMembership.setLobbyId(lobby.getId());

        lobbyMembershipRepository.save(lobbyMembership);
        return joinCode;
    }
}
