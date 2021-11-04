package com.ryan.capstone.aq.aqearlywarning.controller;

import com.ryan.capstone.aq.aqearlywarning.domain.UserAccount;
import com.ryan.capstone.aq.aqearlywarning.domain.UserSettings;
import com.ryan.capstone.aq.aqearlywarning.domain.dto.UserDTO;
import com.ryan.capstone.aq.aqearlywarning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public Mono<UserAccount> getUserAccount(@PathVariable Integer id) {
        return userService.getUserAccount(id);
    }

    @GetMapping("/{id}/settings")
    public Mono<UserSettings> getUserSettings(@PathVariable Integer id) {
        return userService.getUserSettings(id);
    }

    @PostMapping()
    public Mono<UserAccount> createUser(@RequestBody UserAccount account) {
        return userService.createUserAccount(account.getEmail(), account.getFirstName(), account.getLastName());
    }

    @PostMapping("/{id}/settings")
    public Mono<UserSettings> createUserSettings(@PathVariable Integer id, @RequestBody UserSettings settings) {
        Integer maxAqi = settings.getMaxAqi();

        if (maxAqi < 1 || maxAqi > 5) {
            throw new IllegalArgumentException("max aqi must be between 1 and 5");
        }

        return userService.createUserSettings(id, settings.getMaxAqi(), settings.getLongitude(), settings.getLatitude());
    }

    @PutMapping("/{id}")
    public Mono<UserAccount> updateUserAccount(@PathVariable Integer id, @RequestBody UserAccount account) {
        account.setId(id);
        return userService.updateUserAccount(account);
    }

    @PutMapping("/{id}/settings")
    public Mono<UserSettings> updateUserSettings(@PathVariable Integer id, @RequestBody UserSettings settings) {
        Integer maxAqi = settings.getMaxAqi();

        if (maxAqi < 1 || maxAqi > 5) {
            throw new IllegalArgumentException("max aqi must be between 1 and 5");
        }

        settings.setUserId(id);
        return userService.updateUserSettings(settings);
    }

    // TODO: remove
    @GetMapping("/check")
    public Flux<UserDTO> getUsersNeedingUpdate() {
        return userService.notifyUsers();
    }
}
