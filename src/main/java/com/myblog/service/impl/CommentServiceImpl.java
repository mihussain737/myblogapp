package com.myblog.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.myblog.entity.Comment;
import com.myblog.payload.CommentDto;
import com.myblog.repository.CommentRepository;
import com.myblog.service.CommentService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
	
	private CommentRepository commentRepository;
	private ModelMapper modelMapper;

	@Override
	public CommentDto saveComment(CommentDto commentDto, String postId) {
		commentDto.setPostId(postId);
		Comment comment = modelMapper.map(commentDto, Comment.class);
		Comment savedComment = commentRepository.save(comment);
		return modelMapper.map(savedComment, CommentDto.class);
		
	}

}
