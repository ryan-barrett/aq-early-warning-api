package com.ryan.capstone.aq.aqearlywarning.repository;

import com.ryan.capstone.aq.aqearlywarning.domain.UserAccount;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends ReactiveCrudRepository<UserAccount, Integer> {
}
