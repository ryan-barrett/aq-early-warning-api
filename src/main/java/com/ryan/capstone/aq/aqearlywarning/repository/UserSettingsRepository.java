package com.ryan.capstone.aq.aqearlywarning.repository;

import com.ryan.capstone.aq.aqearlywarning.domain.UserSettings;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserSettingsRepository extends ReactiveCrudRepository<UserSettings, Integer> {
    @Query("SELECT * FROM user_settings WHERE user_id = :userId")
    Flux<UserSettings> findByUserId(int userId);
}
