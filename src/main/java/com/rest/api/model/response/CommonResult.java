package com.rest.api.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author lsh
 *
 */
@Getter
@Setter
public class CommonResult {

	@ApiModelProperty (value = "response result : true/false")
	private boolean success;
	
	@ApiModelProperty (value = "response code : >= 0 normal, < 0 error")
	private int code;
	
	@ApiModelProperty (value = "response message")
	private String msg;
}
