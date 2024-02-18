package com.myblog.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.myblog.Exception.ResourceNotFoundException;
import com.myblog.entity.Post;
import com.myblog.payload.PostDto;
import com.myblog.payload.PostResponse;
import com.myblog.repository.PostRepository;
import com.myblog.service.PostService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService{
	
	private PostRepository postRepository;
	private ModelMapper modelMapper;
	
	@Override
	public PostDto savePost(PostDto postDto) {
		postDto.setId(UUID.randomUUID().toString());
		Post post = modelMapper.map(postDto, Post.class);
		Post savedPost = postRepository.save(post);
	return	modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public PostResponse getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir) {
		Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize,sort);
		Page<Post> findAll = postRepository.findAll(pageable);
		List<Post> posts = findAll.getContent();
	List<PostDto> collect = posts.stream().map(post->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	PostResponse postResponse=new PostResponse();
	postResponse.setContent(collect);
	postResponse.setPageNo(findAll.getNumber());
	postResponse.setPageSize(findAll.getSize());
	postResponse.setTotalPages(findAll.getTotalPages());
	postResponse.setTotalElements(findAll.getNumberOfElements());
	postResponse.setLast(findAll.isLast());
	return postResponse;
	
	}

	@Override
	public PostDto getPostById(String postId) {
		Post post = postRepository.findById(postId).orElseThrow(
				()-> new ResourceNotFoundException("Post Not Found")
				);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public void deleteByPostId(String postId) {
		postRepository.findById(postId).orElseThrow(
				()-> new ResourceNotFoundException("Post Not Found")
				);
		postRepository.deleteById(postId);
	}

	@Override
	public PostDto updatePostByPostId(String postId, PostDto postDto) {
		Post existingPost=postRepository.findById(postId).orElseThrow(
				()->new ResourceNotFoundException("POST NOT FOUND")
				);
		existingPost.setId(postId);
		existingPost.setTitle(postDto.getTitle());
		existingPost.setDescription(postDto.getDescription());
		existingPost.setContent(postDto.getContent());
		Post savePostDto = postRepository.save(existingPost);
		return modelMapper.map(savePostDto, PostDto.class);
	}


}
