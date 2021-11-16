package com.ryan.capstone.aq.aqearlywarning.controller;

import com.ryan.capstone.aq.aqearlywarning.domain.UserAccount;
import com.ryan.capstone.aq.aqearlywarning.domain.UserSettings;
import com.ryan.capstone.aq.aqearlywarning.service.AuthService;
import com.ryan.capstone.aq.aqearlywarning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/user")
public class UserController {
    private final AuthService authService;
    private final UserService userService;

    UserController(@Autowired AuthService authService, @Autowired UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public Mono<UserAccount> getUserAccount(@RequestHeader("authorization") String token,
                                            @PathVariable int id) throws AuthenticationException {
        authService.iosAuth(token);
        return userService.getUserAccount(id);
    }

    @GetMapping("/{id}/settings")
    public Mono<UserSettings> getUserSettings(@RequestHeader("authorization") String token,
                                              @PathVariable int id) throws AuthenticationException {
        authService.iosAuth(token);
        return userService.getUserSettings(id);
    }

    @PostMapping()
    public Mono<UserAccount> createUser(@RequestHeader("authorization") String token,
                                        @RequestBody UserAccount account) throws AuthenticationException {
        authService.iosAuth(token);
        return userService.createUserAccount(account.getEmail(), account.getFirstName(), account.getLastName());
    }

    @PutMapping("/{id}")
    public Mono<UserAccount> updateUserAccount(@RequestHeader("authorization") String token,
                                               @PathVariable int id, @RequestBody UserAccount account) throws AuthenticationException {
        authService.iosAuth(token);
        account.setId(id);
        return userService.updateUserAccount(account);
    }

    @PutMapping("/{id}/settings")
    public Mono<UserSettings> updateUserSettings(@RequestHeader("authorization") String token,
                                                 @PathVariable int id, @RequestBody UserSettings settings) throws AuthenticationException {
        authService.iosAuth(token);
        Integer maxAqi = settings.getMaxAqi();

        if (maxAqi < 1 || maxAqi > 5) {
            throw new IllegalArgumentException("max aqi must be between 1 and 5");
        }

        settings.setUserId(id);
        return userService.updateUserSettings(settings);
    }

    @PutMapping("/{id}/settings/maxAqi/{aqi}")
    public Mono<UserSettings> updateUserSettings(@RequestHeader("authorization") String token,
                                                 @PathVariable int id, @PathVariable int aqi) throws AuthenticationException {
        authService.iosAuth(token);

        if (aqi < 1 || aqi > 5) {
            throw new IllegalArgumentException("max aqi must be between 1 and 5");
        }
        return userService.updateUserSettingsAqi(id, aqi);
    }

    @Scheduled(fixedRate = 60000)
    public void notifyUsersInDanger() {
        userService.notifyUsersInDanger().subscribe();
    }
}
