package com.ryan.capstone.aq.aqearlywarning.controller;

import com.ryan.capstone.aq.aqearlywarning.domain.AuthenticationResponse;
import com.ryan.capstone.aq.aqearlywarning.domain.apple.IOSAuthPayload;
import com.ryan.capstone.aq.aqearlywarning.domain.apple.IOSAuthResponse;
import com.ryan.capstone.aq.aqearlywarning.service.AuthService;
import com.ryan.capstone.aq.aqearlywarning.service.UserService;
import com.ryan.capstone.aq.aqearlywarning.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/authenticate")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    AuthController(@Autowired AuthService authService, @Autowired UserService userService, @Autowired JwtUtil jwtUtil) {
        this.authService = authService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public Object authenticate(@RequestBody IOSAuthPayload payload) throws AuthenticationException {
        IOSAuthResponse loginResponse = authService.iosLogin(payload.getToken(), payload);
        authService.iosAuth(payload.getToken());

        return userService.getUserAccountByEmail(payload.getEmail())
                .flatMap(userDetails -> Mono.just(jwtUtil.generateToken(userDetails))
                        .map(jwt -> new AuthenticationResponse(jwt, loginResponse)));
    }

    @PostMapping("/apple")
    public Object appleAuthentication(@RequestHeader("authorization") String token, @RequestBody IOSAuthPayload payload) {
        return authService.iosLogin(token, payload);
    }
}
