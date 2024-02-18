package com.myblog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myblog.payload.PostDto;
import com.myblog.payload.PostResponse;
import com.myblog.repository.PostRepository;
import com.myblog.service.PostService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/posts")
@AllArgsConstructor
public class PostController {
	
	private PostService postService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	//http://localhost:8080/api/posts
	public ResponseEntity<?> savePosts(@Valid @RequestBody PostDto postDto){
		
		PostDto savePost = postService.savePost(postDto);
		return new ResponseEntity<>(savePost,HttpStatus.CREATED);
	}
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping
	public PostResponse getAllPosts(
			@RequestParam(value="pageNo",defaultValue="0",required=false) int pageNo,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="sortBy",defaultValue="id",required=false) String sortBy,
			@RequestParam(value="sortDir",defaultValue="asc",required=false) String sortDir
			){
		PostResponse postResponse = postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
		return postResponse;
	}
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("id") String postId){
		PostDto postById = postService.getPostById(postId);
		return new ResponseEntity<>(postById,HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteByPostId(@PathVariable("id") String postId){
		postService.deleteByPostId(postId);
		return new ResponseEntity("Post Deleted with postId:-"+postId,HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("{id}")
	public ResponseEntity<PostDto>updatePostByPostId(@Valid @PathVariable("id") String postId,@RequestBody PostDto postDto){
		PostDto updatePostByPostId = postService.updatePostByPostId(postId, postDto);
		return new ResponseEntity<>(updatePostByPostId,HttpStatus.OK);
		
	}
	

}
