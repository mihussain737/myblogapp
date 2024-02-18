package com.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myblog.entity.Post;

public interface PostRepository extends JpaRepository<Post, String>{

}
