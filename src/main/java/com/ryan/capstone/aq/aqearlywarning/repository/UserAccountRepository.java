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
    @Query("SELECT us.max_aqi, us.longitude, us.latitude, ua.id, ua.email, ua.first_name, ua.last_name FROM user_account AS ua INNER JOIN user_settings AS us on ua.id = us.user_id WHERE last_checked < :earlierDate LIMIT 5000")
    Flux<UserDTO> findUsersNeedingUpdate(LocalDateTime earlierDate);

    @Query("SELECT * FROM user_account where apple_id = :appleId")
    Mono<UserAccount> findByAppleId(String appleId);
}
