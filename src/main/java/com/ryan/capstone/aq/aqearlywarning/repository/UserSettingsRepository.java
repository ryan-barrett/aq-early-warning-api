package com.ryan.capstone.aq.aqearlywarning.repository;

import com.ryan.capstone.aq.aqearlywarning.domain.UserSettings;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSettingsRepository extends ReactiveCrudRepository<UserSettings, Integer> {
}
