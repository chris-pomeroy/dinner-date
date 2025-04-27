package com.chris.dinnerdate.controller;

import com.chris.dinnerdate.service.InviteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/invite")
public class InviteController {

    public record CreateInviteResponse(String inviteToken) {}

    private final InviteService inviteService;

    @PostMapping("/create")
    public CreateInviteResponse createInvite() {
        return new CreateInviteResponse(inviteService.createInvite());
    }
}
