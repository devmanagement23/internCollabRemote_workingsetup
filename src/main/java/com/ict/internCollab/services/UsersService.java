package com.ict.internCollab.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.internCollab.entity.JobSeekerProfile;
import com.ict.internCollab.entity.RecruiterProfile;
import com.ict.internCollab.entity.Users;
import com.ict.internCollab.repository.JobSeekerProfileRepository;
import com.ict.internCollab.repository.RecruiterProfileRepository;
import com.ict.internCollab.repository.UsersRepository;

@Service
public class UsersService {

	private final UsersRepository usersRepository;
	
	private final JobSeekerProfileRepository jobSeekerProfileRepository;
	private final RecruiterProfileRepository recruiterProfileRepository;
	
	//constructor injection
/* 
	@Autowired
	public UsersService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
		
	}
	 */

	//constructor injection
	@Autowired
	public UsersService(UsersRepository usersRepository, RecruiterProfileRepository recruiterProfileRepository, JobSeekerProfileRepository jobSeekerProfileRepository) {
		this.usersRepository = usersRepository;
		this.jobSeekerProfileRepository = jobSeekerProfileRepository;
		this.recruiterProfileRepository = recruiterProfileRepository;
		
	}
	
	/* 
	public Users addNew(Users users) {
		users.setActive(true);
		users.setRegistrationDate(new Date(System.currentTimeMillis()));
		return usersRepository.save(users);
	}
	
	 */
	

	public Users addNew(Users users) {
		users.setActive(true);
		users.setRegistrationDate(new Date(System.currentTimeMillis()));
		
		Users savedUser = usersRepository.save(users);
		
		int userTypeId = users.getUserTypeId().getUserTypeId();
		
		if(userTypeId == 1) {
			System.out.println(userTypeId);
			
			recruiterProfileRepository.save(new RecruiterProfile(savedUser));
			
		}else {
			System.out.println(userTypeId);
			
			jobSeekerProfileRepository.save(new JobSeekerProfile(savedUser));
		}

				
		return savedUser;
	}
	
	
	public Optional<Users> getUserByEmail(String email){
		return usersRepository.findByEmail(email);
	}
}
