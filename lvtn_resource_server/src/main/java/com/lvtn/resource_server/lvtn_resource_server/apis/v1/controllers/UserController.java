package com.lvtn.resource_server.lvtn_resource_server.apis.v1.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/user", produces = "application/json")
@CrossOrigin
public class UserController {
	@GetMapping(path = "/user-info")
	public ResponseEntity<?> getAuthUserInfo() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Jwt jwt = (Jwt) authentication.getPrincipal();
		return ResponseEntity.ok(jwt.getClaims());
	}
}
