package com.rest.api.controller.v1;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.advice.exception.PUserNotFoundException;
import com.rest.api.entity.User;
import com.rest.api.model.response.CommonResult;
import com.rest.api.model.response.ListResult;
import com.rest.api.model.response.SingleResult;
import com.rest.api.repository.UserRepository;
import com.rest.api.service.ResponseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;


/**
 * 
 * @author lsh
 *
 */
@Api (tags = {"1. User"})
@RequiredArgsConstructor
@RestController
@RequestMapping (value = "/v1")
public class UserController {
	
	private final UserRepository userRepository;
	private final ResponseService responseService;
	
	/**
	 * @apiNote CREATE USER
	 * 
	 * @param uid
	 * @param name
	 * @return
	 */
	@ApiOperation (value = "register user", notes = "register user")
	@PostMapping (value = "/user")
	public SingleResult<User> register(
			@ApiParam (value = "uid", required = true) @RequestParam String uid,
			@ApiParam (value = "name", required = true) @RequestParam String name) {
		User user = User.builder()
				.uid(uid)
				.name(name)
				.build();
		return responseService.getSingleResult(userRepository.save(user));
	}
	
	/**
	 * @apiNote PUT USER
	 * 
	 * @param uid
	 * @param name
	 * @return
	 */
	@ApiOperation (value = "modify user", notes = "modify user")
	@PutMapping (value = "/user")
	public SingleResult<User> modify(
			@ApiParam (value = "id", required = true) @RequestParam Long id,
			@ApiParam (value = "uid", required = true) @RequestParam String uid,
			@ApiParam (value = "name", required = true) @RequestParam String name) {
		User user = User.builder()
				.id(id)
				.uid(uid)
				.name(name)
				.build();
		return responseService.getSingleResult(userRepository.save(user));
	}
	
	/**
	 * @apiNote DELETE USER
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation (value = "delete user", notes = "delete user")
	@DeleteMapping (value = "/user/{id}")
	public CommonResult delete(
			@ApiParam (value = "id", required = true) @RequestParam Long id) {
		userRepository.deleteById(id);
		return responseService.getSuccessResult();
	}
	
	/**
	 * @apiNote FIND USER BY ID
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation (value = "search user", notes = "search user by id")
	@GetMapping (value = "/user/{id}")
	public SingleResult<User> findUserById(@ApiParam (value = "id", required = true) @PathVariable Long id) {
		return responseService.getSingleResult(userRepository.findById(id).orElseThrow(PUserNotFoundException::new));
	}
	
	/**
	 * @apiNote FIND ALL USER
	 * 
	 * @return
	 */
	@ApiOperation (value = "search all users", notes = "search all users")
	@GetMapping (value = "/user")
	public ListResult<User> findAllUser() {
		return responseService.getListResult(userRepository.findAll());
	}
}
