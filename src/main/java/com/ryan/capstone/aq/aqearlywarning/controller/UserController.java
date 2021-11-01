package com.ryan.capstone.aq.aqearlywarning.controller;

import com.ryan.capstone.aq.aqearlywarning.domain.UserAccount;
import com.ryan.capstone.aq.aqearlywarning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/create")
    public Mono<UserAccount> createUser() {
        return userService.createUserAccount("test@test", "test1", "test2");
    }
}
