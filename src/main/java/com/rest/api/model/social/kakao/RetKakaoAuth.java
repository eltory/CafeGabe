package com.rest.api.model.social.kakao;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author lsh
 *
 */
@Getter
@Setter
public class RetKakaoAuth {

	private String access_token;
	private String token_type;
	private String refresh_token;
	private long expires_in;
	private String scope;
}