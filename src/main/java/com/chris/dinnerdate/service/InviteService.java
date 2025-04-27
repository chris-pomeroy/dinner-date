package com.chris.dinnerdate.service;

import com.chris.dinnerdate.config.TokenGenerator;
import com.chris.dinnerdate.config.UserContext;
import com.chris.dinnerdate.model.Invite;
import com.chris.dinnerdate.repository.InviteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class InviteService {

    private static final int INVITE_TOKEN_ENTROPY_IN_BYTES = 8;
    private static final Duration INVITE_TOKEN_EXPIRY = Duration.ofDays(7);

    private final InviteRepository inviteRepository;
    private final TokenGenerator tokenGenerator;

    public String createInvite() {
        String inviteToken = tokenGenerator.generate(INVITE_TOKEN_ENTROPY_IN_BYTES);

        Invite invite = new Invite();
        invite.setUserId(UserContext.getId());
        invite.setInviteToken(inviteToken);

        inviteRepository.save(invite);
        return inviteToken;
    }


    public long validateToken(String inviteToken) {
        Invite invite = inviteRepository.findById(inviteToken)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invite token is not valid"));

        if (LocalDateTime.now().isAfter(invite.getCreatedAt().plus(INVITE_TOKEN_EXPIRY))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invite token has expired");
        }

        return invite.getUserId();
    }
}
