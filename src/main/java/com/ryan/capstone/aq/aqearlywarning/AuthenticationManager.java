package com.ryan.capstone.aq.aqearlywarning;

import com.ryan.capstone.aq.aqearlywarning.service.UserService;
import com.ryan.capstone.aq.aqearlywarning.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    UserService userService;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();

        if (jwtUtil.validateToken(token)) {
            return Mono.just(authentication);
        } else {
            return Mono.empty();
        }
    }
}
