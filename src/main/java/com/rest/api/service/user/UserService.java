package com.rest.api.service.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rest.api.advice.exception.UserNotFoundException;
import com.rest.api.entity.user.User;
import com.rest.api.model.response.CommonResult;
import com.rest.api.model.response.ListResult;
import com.rest.api.model.response.SingleResult;
import com.rest.api.repository.UserRepository;
import com.rest.api.service.ResponseService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author lsh
 *
 */
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService{

	private final UserRepository userRepository;
	private final ResponseService responseService;
	
	/**
	 * @apiNote CREATE USER
	 * 
	 * @param uid
	 * @param name
	 * @return
	 */
	public User register(
			@ApiParam (value = "uid", required = true) @RequestParam String uid,
			@ApiParam (value = "name", required = true) @RequestParam String name) {
		User user = User.builder()
				.uid(uid)
				.name(name)
				.build();
		return userRepository.save(user);
	}
	
	/**
	 * @apiNote PUT USER
	 * 
	 * @param uid
	 * @param name
	 * @return
	 */
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
	public SingleResult<User> findUserById(@ApiParam (value = "id", required = true) @PathVariable Long id) {
		return responseService.getSingleResult(userRepository.findById(id).orElseThrow(UserNotFoundException::new));
	}
	
	/**
	 * @apiNote FIND ALL USER
	 * 
	 * @return
	 */
	public ListResult<User> findAllUser() {
		return responseService.getListResult(userRepository.findAll());
	}
	
	@Override
	public UserDetails loadUserByUsername(String userPk) {
		return (UserDetails) userRepository.findById(Long.valueOf(userPk)).orElseThrow(UserNotFoundException::new);
	}

}
