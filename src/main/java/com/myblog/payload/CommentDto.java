package com.myblog.payload;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
	private Long id;
	@NotEmpty(message="name can't be null")
	private String name;
	@Email(message="email is invalid")
	@NotEmpty(message="email cant be null")
	private String email;
	@Size(min=5, message="comment should be min 5 characters")
	private String body;
	private String postId;

}
