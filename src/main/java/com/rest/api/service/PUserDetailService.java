package com.rest.api.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.rest.api.advice.exception.PUserNotFoundException;
import com.rest.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;


/**
 * 
 * @author lsh
 *
 */
@RequiredArgsConstructor
@Service
public class PUserDetailService implements UserDetailsService {

	private final UserRepository userRepository;
	
	public UserDetails loadUserByUsername(String userPk) {
		return (UserDetails) userRepository.findById(Long.valueOf(userPk)).orElseThrow(PUserNotFoundException::new);
	}
}
