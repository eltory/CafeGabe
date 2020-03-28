package com.rest.api.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rest.api.advice.exception.PCommunicationException;
import com.rest.api.advice.exception.PUserExistException;
import com.rest.api.advice.exception.PUserNotFoundException;
import com.rest.api.model.response.CommonResult;
import com.rest.api.service.ResponseService;

import lombok.RequiredArgsConstructor;

/**
 * 
 * @author lsh
 *
 */
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

	private final ResponseService responseService;
	private final MessageSource messageSource;

	/**
	 * 
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public CommonResult defaultException(HttpServletRequest request, Exception e) {
		return responseService.getFailResult(Integer.valueOf(getMessage("unKnown.code")), getMessage("unKnown.msg") + "(" + e.getMessage() + ")");
	}

	/**
	 * 
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler(PUserNotFoundException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public CommonResult userNotFoundException(HttpServletRequest request, PUserNotFoundException e) {
		return responseService.getFailResult(Integer.valueOf(getMessage("userNotFound.code")),
				getMessage("userNotFound.msg"));
	}

	/**
	 * 
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler(PCommunicationException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public CommonResult communicationException(HttpServletRequest request, PCommunicationException e) {
		return responseService.getFailResult(Integer.valueOf(getMessage("communicationError.code")),
				getMessage("communicationError.msg"));
	}

	/**
	 * 
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler(PUserExistException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public CommonResult communicationException(HttpServletRequest request, PUserExistException e) {
		return responseService.getFailResult(Integer.valueOf(getMessage("existingUser.code")),
				getMessage("existingUser.msg"));
	}

	/**
	 * 
	 * @param code
	 * @return
	 */
	private String getMessage(String code) {
		return getMessage(code, null);
	}

	/**
	 * 
	 * @param code
	 * @param args
	 * @return
	 */
	private String getMessage(String code, Object[] args) {
		return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
	}
}
