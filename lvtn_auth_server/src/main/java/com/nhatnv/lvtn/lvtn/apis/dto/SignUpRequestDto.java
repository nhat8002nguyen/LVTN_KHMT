package com.nhatnv.lvtn.lvtn.apis.dto;

import lombok.Data;

@Data
public class SignUpRequestDto {
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
}
