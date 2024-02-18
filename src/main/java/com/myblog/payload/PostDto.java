package com.myblog.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

	private String id;
	@Size(min=2,message="Post Title should have at least 2 characters")
	private String title;
	@Size(min=2,message="Post Description should have at least 2 characters")
	private String description;
	@Size(min=2,message="Post Content should have at least 2 characters")
	private String content;

}
