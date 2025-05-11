package com.chris.dinnerdate.controller;

import com.chris.dinnerdate.service.InviteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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

    @GetMapping("/join/{inviteToken}")
    public RedirectView redirect(@PathVariable String inviteToken, @RequestParam boolean isDev) {
        // Validate this is a Base64 string
        Base64.getUrlDecoder().decode(inviteToken);
        inviteToken = URLEncoder.encode(inviteToken, UTF_8);

        if (isDev) {
            return new RedirectView("exp://10.0.0.49:8081/--/my-account?token=" + inviteToken);
        }
        return new RedirectView("exp://u.expo.dev/951e7181-6010-4cbf-8673-b5b2b3acf104?runtime-version=1.0.0&channel-name=main/--/my-account?token=" + inviteToken);
    }
}
