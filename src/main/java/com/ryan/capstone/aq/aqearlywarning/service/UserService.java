package com.ryan.capstone.aq.aqearlywarning.service;

import com.ryan.capstone.aq.aqearlywarning.domain.UserAccount;
import com.ryan.capstone.aq.aqearlywarning.domain.UserSettings;
import com.ryan.capstone.aq.aqearlywarning.domain.dto.UserDTO;
import com.ryan.capstone.aq.aqearlywarning.repository.UserAccountRepository;
import com.ryan.capstone.aq.aqearlywarning.repository.UserSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Transactional
public class UserService {
    private final UserAccountRepository userAccountRepository;
    private final UserSettingsRepository userSettingsRepository;
    private final WeatherService weatherService;

    UserService(@Autowired UserAccountRepository userAccountRepository,
                @Autowired UserSettingsRepository userSettingsRepository,
                @Autowired WeatherService weatherService) {
        this.userAccountRepository = userAccountRepository;
        this.userSettingsRepository = userSettingsRepository;
        this.weatherService = weatherService;
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

    public Mono<UserSettings> createUserSettings(Integer userId, Integer maxAqi, Double longitude, Double latitude) {
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

    // TODO: NotificationService
    public Flux<UserDTO> notifyUsersInDanger() {
        return getUsersWithExpiredLastCheck()
                .filter(user -> user.getMaxAqi() != null)
                .flatMap(this::getCurrentUserAqi)
                .filter(user -> user.getCurrentAqi() >= user.getMaxAqi());
    }

    public Flux<UserDTO> getUsersWithExpiredLastCheck() {
        return userAccountRepository.findUsersNeedingUpdate(LocalDateTime.now().minusSeconds(1)) // TODO: more time
                .map(user -> {
                    user.setEmail(user.getEmail().trim());
                    user.setFirstName(user.getFirstName().trim());
                    user.setLastName(user.getLastName().trim());
                    return user;
                });
    }

    private Mono<UserDTO> getCurrentUserAqi(UserDTO user) {
        return weatherService.getPollution(user.getLatitude(), user.getLongitude())
                .map(pollution -> {
                    UserDTO newUser = new UserDTO(user);
                    newUser.setCurrentAqi(pollution.getAqi());
                    return newUser;
                });
    }
}
