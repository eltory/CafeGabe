package com.rest.api.service.social;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.rest.api.advice.exception.CommunicationException;
import com.rest.api.model.social.kakao.KakaoProfile;
import com.rest.api.model.social.kakao.RetKakaoAuth;

import lombok.RequiredArgsConstructor;

/**
* 
* @author lsh
*
*/
@RequiredArgsConstructor
@Service
public class KakaoService {

	private final RestTemplate restTemplate;
	private final Environment env;
	private final Gson gson;

	@Value("${spring.url.base}")
	private String baseUrl;

	@Value("${spring.social.kakao.client_id}")
	private String kakaoClientId;

	@Value("${spring.social.kakao.redirect}")
	private String kakaoRedirect;

	/**
	 * 
	 * @param accessToken
	 * @return
	 */
	public KakaoProfile getKakaoProfile(String accessToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.set("Authorization", "Bearer " + accessToken);
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, headers);
		try {
			ResponseEntity<String> response = restTemplate.postForEntity(env.getProperty("spring.social.kakao.url.profile"), request, String.class);
			if(response.getStatusCode() == HttpStatus.OK)
				return gson.fromJson(response.getBody(), KakaoProfile.class);
		} catch (Exception e) {
			throw new CommunicationException();
		}
		throw new CommunicationException();
	}
	
	/**
	 * 
	 * @param code
	 * @return
	 */
	public RetKakaoAuth getKakaoTokenInfo(String code) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", kakaoClientId);
		params.add("redirect_uri", baseUrl + kakaoRedirect);
		params.add("code", code);
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(env.getProperty("spring.social.kakao.url.token"), request, String.class);
		if(response.getStatusCode() == HttpStatus.OK)
			return gson.fromJson(response.getBody(), RetKakaoAuth.class);
		return null;
	}
}
