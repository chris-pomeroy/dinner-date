package com.chris.dinnerdate.controller;

import com.chris.dinnerdate.service.InviteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InviteController {

    public record CreateInviteResponse(String inviteToken) {}

    private final InviteService inviteService;

    @PostMapping("/invite")
    public CreateInviteResponse createInvite() {
        return new CreateInviteResponse(inviteService.createInvite());
    }

    @PostMapping("/join/{inviteToken}")
    public void joinInvite(@PathVariable String inviteToken) {
        inviteService.joinInvite(inviteToken);
    }
}
