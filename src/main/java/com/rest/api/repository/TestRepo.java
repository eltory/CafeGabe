package com.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.api.entity.cafe.Test;

public interface TestRepo extends JpaRepository<Test, Long> {

}
