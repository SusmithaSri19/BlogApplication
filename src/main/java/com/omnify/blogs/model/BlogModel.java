package com.omnify.blogs.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="blogs")
public class BlogModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@NotBlank(message = "title is required")
	private String title;
	
	@NotBlank(message = "Content is required")
	private String content;
	private LocalDateTime createdAt=LocalDateTime.now();
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserModel author;
}
