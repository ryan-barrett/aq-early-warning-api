package com.ryan.capstone.aq.aqearlywarning.controller;

import com.ryan.capstone.aq.aqearlywarning.domain.UserAccount;
import com.ryan.capstone.aq.aqearlywarning.domain.UserSettings;
import com.ryan.capstone.aq.aqearlywarning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public Mono<UserAccount> getUserAccount(@PathVariable int id) {
        return userService.getUserAccount(id);
    }

    @GetMapping("/{id}/settings")
    public Mono<UserSettings> getUserSettings(@PathVariable int id) {
        return userService.getUserSettings(id);
    }

    @PostMapping()
    public Mono<UserAccount> createUser(@RequestBody UserAccount account) {
        return userService.createUserAccount(account.getEmail(), account.getFirstName(), account.getLastName());
    }

    @PutMapping("/{id}")
    public Mono<UserAccount> updateUserAccount(@PathVariable int id, @RequestBody UserAccount account) {
        account.setId(id);
        return userService.updateUserAccount(account);
    }

    @PutMapping("/{id}/settings")
    public Mono<UserSettings> updateUserSettings(@PathVariable int id, @RequestBody UserSettings settings) {
        Integer maxAqi = settings.getMaxAqi();

        if (maxAqi < 1 || maxAqi > 500) {
            throw new IllegalArgumentException("max aqi must be between 1 and 5");
        }

        settings.setUserId(id);
        return userService.updateUserSettings(settings);
    }

    @PutMapping("/{id}/settings/location")
    public Mono<UserSettings> updateUserLocation(@PathVariable int id, @RequestBody UserSettings settings) {
        return userService.updateUserLocation(id, settings.getLatitude(), settings.getLongitude());
    }

    @PutMapping("/{id}/settings/maxAqi/{aqi}")
    public Mono<UserSettings> updateUserSettings(@PathVariable int id, @PathVariable int aqi) {
        if (aqi < 1 || aqi > 500) {
            throw new IllegalArgumentException("max aqi must be between 1 and 500");
        }
        return userService.updateUserSettingsAqi(id, aqi);
    }

    @Scheduled(fixedRate = 10000)
    public void notifyUsersInDanger() {
        userService.notifyUsersInDanger().subscribe();
    }

    @Scheduled(fixedRate = 10000)
    public void notifyUsersTheyAreSafe() {
        userService.notifyUsersTheyAreSafe().subscribe();
    }
}
