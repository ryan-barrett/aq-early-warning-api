package com.ryan.capstone.aq.aqearlywarning.service;

import com.ryan.capstone.aq.aqearlywarning.domain.UserAccount;
import com.ryan.capstone.aq.aqearlywarning.domain.UserSettings;
import com.ryan.capstone.aq.aqearlywarning.domain.dto.UserDTO;
import com.ryan.capstone.aq.aqearlywarning.repository.UserAccountRepository;
import com.ryan.capstone.aq.aqearlywarning.repository.UserSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
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

    public Mono<UserAccount> getUserAccount(int id) {
        return userAccountRepository.findById(id)
                .map(this::formatUserAccount);
    }

    public Mono<UserAccount> getUserAccountByAppleId(String appleId) {
        return userAccountRepository.findByAppleId(appleId)
                .map(this::formatUserAccount);
    }

    public Mono<UserAccount> getUserAccountByEmail(String email) {
        return userAccountRepository.findByEmail(email)
                .map(this::formatUserAccount);
    }

    public Mono<UserDetails> getUserDetailsByEmail(String email) {
        return Mono.just((UserDetails) getUserAccountByEmail(email));
    }

    public Mono<UserSettings> getUserSettings(int id) {
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

    public Mono<UserAccount> createUserAccount(String appleId, String email, String firstName, String lastName) {
        LocalDateTime now = LocalDateTime.now();
        return userAccountRepository.save(new UserAccount(appleId, email, firstName, lastName, now))
                .map(user -> {
                    createUserSettings(user.getId(), null, null, null).subscribe();
                    return user;
                });
    }

    private Mono<UserSettings> createUserSettings(int userId, Integer maxAqi, Double longitude, Double latitude) {
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

    public Mono<UserSettings> updateUserSettingsAqi(int userId, int aqi) {
        return userSettingsRepository.findByUserId(userId)
                .flatMap(userSettings -> {
                    System.out.println("got here " + userSettings);
                    userSettings.setMaxAqi(aqi);
                    return userSettingsRepository.save(userSettings);
                }).last();
    }

    public Mono<UserSettings> updateUserLocation(int userId, double latitude, double longitude) {
        return userSettingsRepository.findByUserId(userId)
                .flatMap(userSettings -> {
                    System.out.println("got here " + userSettings);
                    userSettings.setLatitude(latitude);
                    userSettings.setLongitude(longitude);
                    return userSettingsRepository.save(userSettings);
                }).last();
    }

    public Flux<Boolean> notifyUsersInDanger() {
        logger.info("notifying users in danger");
        return getUsersWithExpiredLastCheck()
                .filter(user -> user.getMaxAqi() != null)
                .flatMap(this::getCurrentUserAqi)
                .map(user -> {
                    UserAccount updatedAccount = new UserAccount(user);
                    updateUserAccount(updatedAccount).subscribe();
                    return user;
                })
                .filter(user -> user.getCurrentAqi() >= user.getMaxAqi())
                .map(user -> notificationService.sendIosPushNotification(user.toString()));
    }

    private Flux<UserDTO> getUsersWithExpiredLastCheck() {
        logger.info("fetching users with expired last_check");
        return userAccountRepository.findUsersNeedingUpdate(LocalDateTime.now().minusHours(1))
                .map(user -> {
                    logger.info("expired user found: " + user);
                    return formatUserDTO(user);
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

    private UserDTO formatUserDTO(UserDTO user) {
        var newUser = new UserDTO(user);

        if (newUser.getEmail() != null) newUser.setEmail(newUser.getEmail().trim());
        if (newUser.getFirstName() != null) newUser.setFirstName(newUser.getFirstName().trim());
        if (newUser.getLastName() != null) newUser.setLastName(newUser.getLastName().trim());
        if (newUser.getAppleId() != null) newUser.setAppleId(newUser.getAppleId().trim());
        return newUser;
    }

    private UserAccount formatUserAccount(UserAccount userAccount) {
        var newUserAccount = new UserAccount(userAccount);

        if (newUserAccount.getEmail() != null) newUserAccount.setEmail(newUserAccount.getEmail().trim());
        if (newUserAccount.getFirstName() != null) newUserAccount.setFirstName(newUserAccount.getFirstName().trim());
        if (newUserAccount.getLastName() != null) newUserAccount.setLastName(newUserAccount.getLastName().trim());
        if (newUserAccount.getAppleId() != null) newUserAccount.setAppleId(newUserAccount.getAppleId().trim());
        return newUserAccount;
    }
}
