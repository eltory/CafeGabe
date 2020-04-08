package com.rest.api.service.user;

import java.util.Collections;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.advice.exception.CommunicationException;
import com.rest.api.advice.exception.UserExistException;
import com.rest.api.advice.exception.UserNotFoundException;
import com.rest.api.config.security.JwtTokenProvider;
import com.rest.api.entity.user.User;
import com.rest.api.model.response.CommonResult;
import com.rest.api.model.response.SingleResult;
import com.rest.api.model.social.kakao.KakaoProfile;
import com.rest.api.repository.UserRepository;
import com.rest.api.service.ResponseService;
import com.rest.api.service.social.KakaoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;


/**
 * 
 * @author lsh
 *
 */
@RequiredArgsConstructor
@RestController
@Service
public class SignService {

	private final UserRepository userRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final ResponseService responseService;
	private final KakaoService kakaoService;
	
	/**
	 * 
	 * @param provider
	 * @param accessToken
	 * @return
	 */
	@ApiOperation(value = "Social login", notes = "Social login")
	@PostMapping(value = "/signin/{provider}")
	public SingleResult<String> signinByProvider(
			@ApiParam(value = "Provider", required = true, defaultValue = "kakao") @PathVariable String provider,
			@ApiParam(value = "Access_token", required = true) @RequestParam String accessToken) {

		KakaoProfile profile = kakaoService.getKakaoProfile(accessToken);
		User user = userRepository.findByUidAndProvider(String.valueOf(profile.getId()), provider)
				.orElseThrow(CommunicationException::new);
		return responseService
				.getSingleResult(jwtTokenProvider.createToken(String.valueOf(user.getId()), user.getRoles()));
	}

	/**
	 * 
	 * @param provider
	 * @param accessToken
	 * @param name
	 * @return
	 */
	@ApiOperation(value = "Register by social provider", notes = "Register user")
	@PostMapping(value = "/signup/{provider}")
	public CommonResult signupByProvider(
			@ApiParam(value = "provider", required = true, defaultValue = "kakao") @PathVariable String provider,
			@ApiParam(value = "access_token", required = true) @RequestParam String accessToken,
			@ApiParam(value = "name", required = true) @RequestParam String name) {
		KakaoProfile profile = kakaoService.getKakaoProfile(accessToken);
		Optional<User> user = userRepository.findByUidAndProvider(String.valueOf(profile.getId()), provider);
		if (user.isPresent())
			throw new UserExistException();
		userRepository.save(User.builder().uid(String.valueOf(profile.getId())).provider(provider).name(name)
				.roles(Collections.singletonList("ROLE_USER")).build());
		return responseService.getSuccessResult();
	}
}
