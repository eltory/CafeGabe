package com.rest.api.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

/**
 * 
 * @author lsh
 *
 */
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final JwtTokenProvider jwtTokenProvider;

	/**
	 * 
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * 
	 */
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/v2/api-docs", "/swagger-resource/**", "/swagger-ui.html", "/webjars/**",
				"/swagger/**");
	}

	/**
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.httpBasic().disable()	// rest api
		.csrf().disable()		// rest api
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)	// jwt token base
		.and()
		.authorizeRequests()	// next request
		.antMatchers("/*/signin", "/*/signup").permitAll()	// register, sign in for anyone
		.antMatchers(HttpMethod.GET, "hello/**").permitAll()
		.anyRequest().permitAll();
//		.anyRequest().hasRole("USER")
//		.and()
//		.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
	}

}
