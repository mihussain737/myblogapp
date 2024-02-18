package com.myblog.service;

import com.myblog.payload.CommentDto;

public interface CommentService {
	
	CommentDto saveComment(CommentDto commentDto,String postId);

}
