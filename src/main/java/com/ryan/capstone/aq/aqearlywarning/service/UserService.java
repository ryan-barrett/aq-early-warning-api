package com.ryan.capstone.aq.aqearlywarning.service;

import com.ryan.capstone.aq.aqearlywarning.domain.UserAccount;
import com.ryan.capstone.aq.aqearlywarning.domain.UserSettings;
import com.ryan.capstone.aq.aqearlywarning.repository.UserAccountRepository;
import com.ryan.capstone.aq.aqearlywarning.repository.UserSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class UserService {
    private final UserAccountRepository userAccountRepository;
    private final UserSettingsRepository userSettingsRepository;

    UserService(@Autowired UserAccountRepository userAccountRepository, @Autowired UserSettingsRepository userSettingsRepository) {
        this.userAccountRepository = userAccountRepository;
        this.userSettingsRepository = userSettingsRepository;
    }

    public Mono<UserAccount> createUserAccount(String email, String firstName, String lastName) {
        return userAccountRepository.save(new UserAccount(email, firstName, lastName));
    }
}
