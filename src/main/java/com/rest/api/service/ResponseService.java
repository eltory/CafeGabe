package com.rest.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rest.api.model.response.CommonResult;
import com.rest.api.model.response.ListResult;
import com.rest.api.model.response.SingleResult;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 
 * @author lsh
 *
 */
@Service
public class ResponseService {

	@AllArgsConstructor
	@Getter
	public enum CommonResponse {
		SUCCESS (0, "success"),
		FAIL (-1, "fail");
		
		private int code;
		private String msg;
	}
	
	/**
	 * 
	 * @param <T>
	 * @param data
	 * @return
	 */
	public <T> SingleResult<T> getSingleResult(T data) {
		SingleResult<T> result = new SingleResult<>();
		result.setData(data);
		setSuccessResult(result);
		return result;
	}
	
	/**
	 * 
	 * @param <T>
	 * @param list
	 * @return
	 */
	public <T> ListResult<T> getListResult(List<T> list) {
		ListResult<T> result = new ListResult<>();
		result.setList(list);
		setSuccessResult(result);
		return result;
	}
	
	/**
	 * 
	 * @return
	 */
	public CommonResult getSuccessResult() {
		CommonResult result = new CommonResult();
		setSuccessResult(result);
		return result;
	}
	
	/**
	 * 
	 * @return
	 */
	public CommonResult getFailResult(int code, String msg) {
		CommonResult result = new CommonResult();
		result.setSuccess(false);
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
	
	/**
	 * 
	 * @param result
	 */
	private void setSuccessResult(CommonResult result) {
		result.setSuccess(true);
		result.setCode(CommonResponse.SUCCESS.getCode());
		result.setMsg(CommonResponse.SUCCESS.getMsg());
	}
}
