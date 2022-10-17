package com.nhatnv.lvtn.lvtn.apis.controllers.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nhatnv.lvtn.lvtn.apis.dto.SignUpRequestDto;
import com.nhatnv.lvtn.lvtn.apis.dto.SignUpResponseDto;
import com.nhatnv.lvtn.lvtn.apis.dto.UserReponseDto;
import com.nhatnv.lvtn.lvtn.entities.User;
import com.nhatnv.lvtn.lvtn.repositories.UserRepository;

@RestController
@RequestMapping(path = "/api/v1/auth")
@CrossOrigin
public class AuthController {
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public AuthController(UserRepository userRepository, PasswordEncoder encoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = encoder;
	}

	@PostMapping(path = "/sign-up")
	public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto cred) {
		try {

			User user = User.builder()
					.username(cred.getUsername())
					.firstName(cred.getFirstName())
					.lastName(cred.getLastName())
					.email(cred.getEmail())
					.password(passwordEncoder.encode(cred.getPassword()))
					.role("ROLE_USER")
					.build();

			User createdUser = userRepository.save(user);

			SignUpResponseDto response = SignUpResponseDto.builder()
					.user(
							UserReponseDto.builder()
									.userId(createdUser.getId())
									.email(createdUser.getEmail())
									.username(createdUser.getUsername())
									.firstName(createdUser.getFirstName())
									.lastName(createdUser.getLastName())
									.build())
					.message("Sign up successfully !")
					.status("Success")
					.build();

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(
					SignUpResponseDto.builder()
							.status("Failure")
							.message("Can not sign up, please try again later!")
							.build());
		}
	}
}
