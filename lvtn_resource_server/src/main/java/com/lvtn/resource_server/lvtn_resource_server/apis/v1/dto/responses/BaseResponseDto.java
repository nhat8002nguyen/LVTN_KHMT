package com.lvtn.resource_server.lvtn_resource_server.apis.v1.dto.responses;

import lombok.Data;

@Data
public class BaseResponseDto<T> {
	private T metadata;
	private Boolean success;
	private String code;
	private String message;

	public static <T> BaseResponseDto<T> ofSucceed(T data, String code, String message) {
		BaseResponseDto<T> response = new BaseResponseDto<>();
		response.metadata = data;
		response.success = true;
		response.code = code;
		response.message = message;
		return response;
	}

	public static <T> BaseResponseDto<T> ofSucceed(T data) {
		return ofSucceed(data, null, null);
	}

	public static <T> BaseResponseDto<T> ofSucceed(T data, String code) {
		return ofSucceed(data, code, null);
	}

	public static <T> BaseResponseDto<T> ofFail(String code, String message) {
		BaseResponseDto<T> response = new BaseResponseDto<>();
		response.metadata = null;
		response.success = false;
		response.code = code;
		response.message = message;
		return response;
	}

	public static <T> BaseResponseDto<T> ofFail(String message) {
		return ofSucceed(null, message);
	}
}
