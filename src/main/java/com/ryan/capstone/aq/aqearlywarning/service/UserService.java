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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

@Transactional
@Service
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserAccountRepository userAccountRepository;
    private final UserSettingsRepository userSettingsRepository;
    private final WeatherService weatherService;
    private final NotificationService notificationService;

    @Autowired
    UserService(UserAccountRepository userAccountRepository,
                UserSettingsRepository userSettingsRepository,
                WeatherService weatherService,
                NotificationService notificationService) {
        this.userAccountRepository = userAccountRepository;
        this.userSettingsRepository = userSettingsRepository;
        this.weatherService = weatherService;
        this.notificationService = notificationService;
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
        return userAccountRepository.save(new UserAccount(email, firstName, lastName, now))
                .map(user -> {
                    createUserSettings(user.getId(), null, null, null).subscribe();
                    return user;
                });
    }

    private Mono<UserSettings> createUserSettings(Integer userId, Integer maxAqi, Double longitude, Double latitude) {
        return userSettingsRepository.save(new UserSettings(userId, maxAqi, longitude, latitude));
    }

    public Mono<UserAccount> updateUserAccount(UserAccount updatedUser) {
        logger.info("updating user account: " + updatedUser);
        if (updatedUser.getLastChecked() == null) {
            updatedUser.setLastChecked(LocalDateTime.now());
        }
        return userAccountRepository.save(updatedUser);
    }

    public Mono<UserSettings> updateUserSettings(UserSettings userSettings) {
        return userSettingsRepository.save(userSettings);
    }

    public Flux<Boolean> notifyUsersInDanger() {
        logger.info("notifying users in danger");
        return getUsersWithExpiredLastCheck()
                .filter(user -> user.getMaxAqi() != null)
                .flatMap(this::getCurrentUserAqi)
                .filter(user -> user.getCurrentAqi() >= user.getMaxAqi())
                .map(user -> {
                    UserAccount updatedAccount = new UserAccount(user);
                    updateUserAccount(updatedAccount).subscribe();
                    return notificationService.sendIosPushNotification(user.toString());
                });
    }

    private Flux<UserDTO> getUsersWithExpiredLastCheck() {
        logger.info("fetching users with expired last_check");
        return userAccountRepository.findUsersNeedingUpdate(LocalDateTime.now().minusSeconds(1)) // TODO: more time
                .map(user -> {
                    logger.info("expired user found: " + user);
                    user.setEmail(user.getEmail().trim());
                    user.setFirstName(user.getFirstName().trim());
                    user.setLastName(user.getLastName().trim());
                    return user;
                });
    }

    private Mono<UserDTO> getCurrentUserAqi(UserDTO user) {
        logger.info("getting current AQI for " + user);
        return weatherService.getPollution(user.getLatitude(), user.getLongitude())
                .map(pollution -> {
                    logger.info("pollution for user: " + user + " " + pollution);
                    UserDTO newUser = new UserDTO(user);
                    newUser.setCurrentAqi(pollution.getAqi());
                    return newUser;
                });
    }
}
