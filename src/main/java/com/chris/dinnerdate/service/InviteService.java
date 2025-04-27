package com.chris.dinnerdate.service;

import com.chris.dinnerdate.config.TokenGenerator;
import com.chris.dinnerdate.config.UserContext;
import com.chris.dinnerdate.model.Invite;
import com.chris.dinnerdate.model.Lobby;
import com.chris.dinnerdate.model.User;
import com.chris.dinnerdate.repository.InviteRepository;
import com.chris.dinnerdate.repository.LobbyRepository;
import com.chris.dinnerdate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InviteService {

    private static final int INVITE_TOKEN_ENTROPY_IN_BYTES = 8;
    private static final Duration INVITE_TOKEN_EXPIRY = Duration.ofDays(7);

    private final InviteRepository inviteRepository;
    private final LobbyRepository lobbyRepository;
    private final UserRepository userRepository;
    private final TokenGenerator tokenGenerator;

    public String createInvite() {
        String inviteToken = tokenGenerator.generate(INVITE_TOKEN_ENTROPY_IN_BYTES);

        Invite invite = Invite.of(UserContext.getId(), inviteToken);
        inviteRepository.save(invite);
        return inviteToken;
    }

    public void joinInvite(String inviteToken) {
        Invite invite = inviteRepository.findById(inviteToken)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invite token is not valid"));

        if (LocalDateTime.now().isAfter(invite.getCreatedAt().plus(INVITE_TOKEN_EXPIRY))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invite token has expired");
        }

        User inviter = invite.getUser();
        if (inviter.getLobbyId() != null) {
            userRepository.updateCurrentLobbyId(inviter.getLobbyId(), UserContext.getId());
            return;
        }

        Lobby lobby = lobbyRepository.save(new Lobby());
        userRepository.updateCurrentLobbyId(lobby.getId(), List.of(inviter.getId(), UserContext.getId()));
    }
}