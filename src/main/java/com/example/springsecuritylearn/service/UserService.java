package com.example.springsecuritylearn.service;

import com.example.springsecuritylearn.domain.entity.Account;
import com.example.springsecuritylearn.domain.dto.AccountDto;

import java.util.List;

public interface UserService {
    void createUser(Account account);

    void modifyUser(AccountDto accountDto);

    List<Account> getUsers();
    AccountDto getUser(Long id);

    void deleteUser(Long idx);
}
