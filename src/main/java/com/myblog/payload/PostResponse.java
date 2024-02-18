package com.myblog.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
	
	private List<PostDto>content;
	private int pageNo;
	private int pageSize;
	private int totalElements;
	private int totalPages;
	private boolean last;

}
