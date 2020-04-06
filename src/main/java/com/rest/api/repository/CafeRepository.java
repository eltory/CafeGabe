package com.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.api.entity.cafe.Cafe;

public interface CafeRepository extends JpaRepository<Cafe, Long>{

}
