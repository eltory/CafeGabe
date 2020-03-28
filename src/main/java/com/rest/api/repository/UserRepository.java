package com.rest.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.api.entity.User;


/**
 * 
 * @author lsh
 *
 */
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUid(String email);
	Optional<User> findByUidAndProvider(String uid, String provider);
}
