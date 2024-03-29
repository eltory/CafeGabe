package com.rest.api.entity.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * @author lsh
 *
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_normal")
public class User implements UserDetails {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@Column (nullable = false, unique = true, length = 30)
	private String uid;

	@JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
	@Column (nullable = true, length = 100)
	private String password;

	@Column (nullable = false, length = 100)
	private String name;
	
	@Column (length = 100)
	private String provider;
	
	@Column
	private LocalDateTime registeredAt;
	
	@Column
	private LocalDateTime modifiedAt;
	
	@Column
	private LocalDateTime lastLoginAt;

	@ElementCollection (fetch = FetchType.EAGER)
	@Builder.Default
	private List<String> roles = new ArrayList<>();

	/**
	 * 
	 */
	@JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public String getUsername() {
		return this.uid;
	}

	/**
	 * 
	 */
	@JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * 
	 */
	@JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * 
	 */
	@JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 
	 */
	@JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isEnabled() {
		return true;
	}

	/**
	 * 
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}	
}

