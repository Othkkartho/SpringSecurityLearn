package com.example.springsecuritylearn.controller.user;

import com.example.springsecuritylearn.domain.dto.AccountDto;
import com.example.springsecuritylearn.domain.entity.Account;
import com.example.springsecuritylearn.repository.RoleRepository;
import com.example.springsecuritylearn.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class UserController {
	private UserService userService;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void setUserController(UserService userService, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping(value="/users")
	public String createUser() {
		return "/user/login/register";
	}

	@PostMapping(value="/users")
	public String createUser(AccountDto accountDto) {
		ModelMapper modelMapper = new ModelMapper();
		Account account = modelMapper.map(accountDto, Account.class);
		account.setPassword(passwordEncoder.encode(accountDto.getPassword()));

		userService.createUser(account);

		return "redirect:/";
	}

	@GetMapping(value="/mypage")
	public String myPage(@AuthenticationPrincipal Account account, Authentication authentication, Principal principal) {
		return "user/mypage";
	}
}
