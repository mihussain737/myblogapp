package com.myblog.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="posts")
@Setter
@Getter
@NoArgsConstructor
public class Post {
	
	@Id
	private String id;
	
	@Column(name="title", nullable=false)
	private String title;
	
	@Column(name="description", nullable=false)
	private String description;
	
	@Column(name="content" , nullable=false)
	private String content;
	
	@OneToMany(mappedBy = "post",cascade=CascadeType.ALL,orphanRemoval = true)
	private List<Comment>comments=new ArrayList<>();

}
