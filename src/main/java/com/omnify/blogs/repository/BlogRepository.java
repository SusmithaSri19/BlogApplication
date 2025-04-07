package com.omnify.blogs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.omnify.blogs.model.BlogModel;

@Repository
public interface BlogRepository extends JpaRepository<BlogModel, Long>{
	Page<BlogModel> findAll(Pageable pageable);
}
