package com.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.api.entity.post.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}
