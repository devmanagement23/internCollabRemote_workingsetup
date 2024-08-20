package com.ict.internCollab.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ict.internCollab.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>{
	
	Optional<Users> findByEmail(String email);

}
