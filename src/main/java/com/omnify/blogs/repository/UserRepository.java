package com.omnify.blogs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omnify.blogs.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{
	Optional<UserModel> findByEmail(String email);
}
