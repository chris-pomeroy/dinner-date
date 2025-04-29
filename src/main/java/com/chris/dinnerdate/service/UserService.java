package com.chris.dinnerdate.service;

import com.chris.dinnerdate.config.UserContext;
import com.chris.dinnerdate.controller.MeController.LobbyMember;
import com.chris.dinnerdate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<LobbyMember> getLobbyMembers() {
        return userRepository.findByLobbyId(UserContext.getLobbyId())
                .stream()
                .map(user -> new LobbyMember(user.getFirstName(), user.getEmail()))
                .toList();
    }
}
