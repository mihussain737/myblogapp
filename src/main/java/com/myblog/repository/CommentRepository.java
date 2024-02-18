package com.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myblog.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long>{

}
