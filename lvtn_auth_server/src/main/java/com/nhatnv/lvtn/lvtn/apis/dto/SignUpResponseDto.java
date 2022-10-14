package com.nhatnv.lvtn.lvtn.apis.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpResponseDto {
	private UserReponseDto user;
	private String status;
	private String message;
}
