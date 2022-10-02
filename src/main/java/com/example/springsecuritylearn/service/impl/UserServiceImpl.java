package com.example.springsecuritylearn.service.impl;

import com.example.springsecuritylearn.domain.Account;
import com.example.springsecuritylearn.repository.UserRepository;
import com.example.springsecuritylearn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    private void setUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void createUser(Account account) {
        userRepository.save(account);
    }
}
