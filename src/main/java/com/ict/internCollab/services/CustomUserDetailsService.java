package com.ict.internCollab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ict.internCollab.entity.Users;
import com.ict.internCollab.repository.UsersRepository;

import util.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private final UsersRepository usersRepository;
	
	//Constructor injection
	@Autowired
	public CustomUserDetailsService(UsersRepository usersRepository) {
		
		this.usersRepository = usersRepository;
	}

	//tell spring security how to retrieve the users from the database
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users user = usersRepository.findByEmail(username).orElseThrow( () -> new UsernameNotFoundException("Could not found user"));
		
		return new CustomUserDetails(user);
	}

	

}
