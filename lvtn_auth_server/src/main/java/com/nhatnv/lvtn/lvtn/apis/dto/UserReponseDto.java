package com.nhatnv.lvtn.lvtn.apis.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserReponseDto {
	private String username;
	private String firstName;
	private String lastName;
	private String email;
}
