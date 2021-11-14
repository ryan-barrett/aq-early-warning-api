package com.ryan.capstone.aq.aqearlywarning.controller;

import com.ryan.capstone.aq.aqearlywarning.domain.apple.IOSAuthPayload;
import com.ryan.capstone.aq.aqearlywarning.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticate")
public class AuthController {
    private final AuthService authService;

    AuthController(@Autowired AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/apple")
    public Object authenticate(@RequestHeader("authorization") String token, @RequestBody IOSAuthPayload payload) {
        return authService.iosLogin(token, payload);
    }
}
