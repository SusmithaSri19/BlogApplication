package com.omnify.blogs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.omnify.blogs.model.BlogModel;
import com.omnify.blogs.repository.BlogRepository;

@Service
public class BlogService {
	 @Autowired
	    private BlogRepository blogRepository;

	   

	    public void saveBlog(BlogModel blog) {
	        blogRepository.save(blog);
	    }
	    
	    public Page<BlogModel> getBlogs(Pageable pageable) {
	        return blogRepository.findAll(pageable);
	    }
	    
	    public BlogModel getBlogById(Long id) {
	        return blogRepository.findById(id).orElse(null);
	    }
	    
	    public void deleteBlog(Long id) {
	        blogRepository.deleteById(id);
	    }
	    
	    public Page<BlogModel> getPaginatedBlogs(int page, int size) {
	        Pageable pageable = PageRequest.of(page, size);
	        return blogRepository.findAll(pageable);
	    }

}
