package com.example.springsecuritylearn.repository;

import com.example.springsecuritylearn.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Account, Long> {
}
