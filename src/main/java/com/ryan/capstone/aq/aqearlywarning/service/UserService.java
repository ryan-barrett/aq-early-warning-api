package com.ryan.capstone.aq.aqearlywarning.service;

import com.ryan.capstone.aq.aqearlywarning.domain.UserAccount;
import com.ryan.capstone.aq.aqearlywarning.domain.UserSettings;
import com.ryan.capstone.aq.aqearlywarning.repository.UserAccountRepository;
import com.ryan.capstone.aq.aqearlywarning.repository.UserSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Transactional
public class UserService {
    private final UserAccountRepository userAccountRepository;
    private final UserSettingsRepository userSettingsRepository;

    UserService(@Autowired UserAccountRepository userAccountRepository, @Autowired UserSettingsRepository userSettingsRepository) {
        this.userAccountRepository = userAccountRepository;
        this.userSettingsRepository = userSettingsRepository;
    }

    public Mono<UserAccount> getUserAccount(Integer id) {
        return userAccountRepository.findById(id)
                .map(userAccount -> {
                    userAccount.setEmail(userAccount.getEmail().trim());
                    userAccount.setFirstName(userAccount.getFirstName().trim());
                    userAccount.setLastName(userAccount.getLastName().trim());
                    return userAccount;
                });
    }

    public Mono<UserSettings> getUserSettings(Integer id) {
        return userSettingsRepository.findByUserId(id).last();
    }

    public Mono<UserAccount> createUserAccount(String email, String firstName, String lastName) {
        LocalDateTime now = LocalDateTime.now();
        return userAccountRepository.save(new UserAccount(email, firstName, lastName, now));
    }

    public Mono<UserSettings> createUserSettings(Integer userId, Integer maxAqi, Float longitude, Float latitude) {
        return userSettingsRepository.save(new UserSettings(userId, maxAqi, longitude, latitude));
    }

    public Mono<UserAccount> updateUserAccount(UserAccount updatedUser) {
        if (updatedUser.getLastChecked() == null) {
            updatedUser.setLastChecked(LocalDateTime.now());
        }
        return userAccountRepository.save(updatedUser);
    }

    public Mono<UserSettings> updateUserSettings(UserSettings userSettings) {
        return userSettingsRepository.save(userSettings);
    }
}
