package com.rest.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.api.entity.user.User;


/**
 * 
 * @author lsh
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUid(String email);
	Optional<User> findByUidAndProvider(String uid, String provider);
}
