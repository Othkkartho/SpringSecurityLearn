package com.example.springsecuritylearn.controller.user;

import com.example.springsecuritylearn.domain.Account;
import com.example.springsecuritylearn.domain.AccountDto;
import com.example.springsecuritylearn.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    private void setUserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "/mypage")
    public String myPage() {
        return "/user/mypage";
    }

    @GetMapping("/users")
    public String createUser() {
        return "/user/login/register";
    }

    @PostMapping("/users")
    public String createUser(AccountDto accountDto) {
        ModelMapper modelMapper = new ModelMapper();
        Account account = modelMapper.map(accountDto, Account.class);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        userService.createUser(account);

        return "redirect:/";
    }
}
