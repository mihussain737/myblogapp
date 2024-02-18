package com.myblog.service;

import java.util.List;

import com.myblog.payload.PostDto;
import com.myblog.payload.PostResponse;

public interface PostService {
	
	PostDto savePost(PostDto postDto);
	PostResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir);
	PostDto getPostById(String postId);
	void deleteByPostId(String postId);
	PostDto updatePostByPostId(String postId,PostDto postDto);

}
