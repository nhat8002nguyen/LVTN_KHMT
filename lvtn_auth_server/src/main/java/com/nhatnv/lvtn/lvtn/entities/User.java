package com.nhatnv.lvtn.lvtn.entities;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity(name = "users")
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class User implements UserDetails {
	public static long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	@NotBlank
	private final String email;

	@NotNull
	@NotBlank
	private final String username;

	@NotNull
	@NotBlank
	@Size(max = 50, message = "First name should has at most 50 charactor long")
	private String firstName;

	@Size(max = 50, message = "First name should has at most 50 charactor long")
	@NotNull
	@NotBlank
	private String lastName;

	@NotNull
	@NotBlank
	private final String password;

	@Pattern(regexp = "^\\d{8,}$", message = "Please enter phone number with at least 8 number")
	private String phone;

	@Size(max = 1000, message = "Bio of a user must have at most 1000 characters long")
	private String about;

	private String imageUrl;

	private final String role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (role == "ROLE_USER" || role == "ROLE_ADMIN") {
			return Collections.singletonList(new SimpleGrantedAuthority(role));
		}
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
