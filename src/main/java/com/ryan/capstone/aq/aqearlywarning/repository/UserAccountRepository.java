package com.ryan.capstone.aq.aqearlywarning.repository;

import com.ryan.capstone.aq.aqearlywarning.domain.UserAccount;
import com.ryan.capstone.aq.aqearlywarning.domain.dto.UserDTO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Repository
public interface UserAccountRepository extends ReactiveCrudRepository<UserAccount, Integer> {
    @Query("SELECT us.max_aqi, us.longitude, us.latitude, ua.id, ua.email, ua.first_name, ua.last_name, ua.apple_id, ua.is_safe FROM user_account AS ua INNER JOIN user_settings AS us on ua.id = us.user_id WHERE ((is_safe IS TRUE OR is_safe IS NULL) AND (last_checked < :earlierDate OR last_checked IS NULL)) AND us.max_aqi IS NOT NULL ORDER BY last_checked ASC LIMIT 3 FOR UPDATE")
    Flux<UserDTO> findSafeUsersNeedingUpdate(LocalDateTime earlierDate);

    @Query("SELECT us.max_aqi, us.longitude, us.latitude, ua.id, ua.email, ua.first_name, ua.last_name, ua.apple_id, ua.is_safe FROM user_account AS ua INNER JOIN user_settings AS us on ua.id = us.user_id WHERE ((is_safe IS FALSE) AND (last_checked < :earlierDate OR last_checked IS NULL)) AND us.max_aqi IS NOT NULL ORDER BY last_checked ASC LIMIT 3 FOR UPDATE")
    Flux<UserDTO> findUsersInDangerNeedingUpdate(LocalDateTime earlierDate);

    @Query("SELECT * FROM user_account where apple_id = :appleId")
    Mono<UserAccount> findByAppleId(String appleId);

    @Query("SELECT * FROM user_account where email = :email")
    Mono<UserAccount> findByEmail(String email);
}
