package com.myblog.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myblog.payload.CommentDto;
import com.myblog.service.CommentService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentController {
	
	private CommentService commentService;
	
	@PostMapping("{postId}")
	public ResponseEntity<?> saveComment(@Valid @RequestBody CommentDto commentDto, @PathVariable String postId,BindingResult result){
		if (result.hasErrors()) {
	        return new ResponseEntity<>(result.getFieldError()
	        		.getDefaultMessage(), 
	        		HttpStatus.BAD_REQUEST);
	    }
		CommentDto saveComment = commentService.saveComment(commentDto, postId);
		return new ResponseEntity<>(saveComment,HttpStatus.CREATED);
	}

}
