package com.chris.dinnerdate.controller;

import com.chris.dinnerdate.service.InviteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

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

    // TODO make this redirect to prod or dev
    @GetMapping("/join/{inviteToken}")
    public String redirect(@PathVariable String inviteToken) {
        // Validate this is a Base64 string
        Base64.getUrlDecoder().decode(inviteToken);
        inviteToken = URLEncoder.encode(inviteToken, UTF_8);
        return "redirect:exp://10.0.0.49:8081/--/my-account?token=" + inviteToken;
    }
}
