package com.rest.api.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.rest.api.entity.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable, ToEntity<User> {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4568432274318728267L;
	private Long id;
	private String uid;
	private String password;
	private String name;
	private String provider;
	private LocalDateTime registeredAt;
	private LocalDateTime modifiedAt;
	private LocalDateTime lastLoginAt;
	@Builder.Default
	private List<String> roles = new ArrayList<>();

	@Override
	public User toEntity() {
		return User.builder()
				.id(id)
				.name(name)
				.uid(uid)
				.password(password)
				.provider(provider)
				.registeredAt(registeredAt)
				.modifiedAt(modifiedAt)
				.lastLoginAt(lastLoginAt)
				.roles(roles)
				.build();
	}
}
