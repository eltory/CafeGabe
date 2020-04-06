package com.rest.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author lsh
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * 
	 * @return
	 */
	@Bean
	public Docket Api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.rest.api.controller"))
				.build()
				.useDefaultResponseMessages(false);
	}
	
	/**
	 * 
	 * @return
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("REST API Documentation")
				.description("서버 REST API 문서")
				.version("1")
				.build();
	}
}
