package com.ict.internCollab.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.internCollab.entity.UsersType;
import com.ict.internCollab.repository.UsersTypeRepository;

@Service
public class UsersTypeService {
	 
	private final UsersTypeRepository usersTypeRepository;

	//constructor injection
	@Autowired
	public UsersTypeService(UsersTypeRepository usersTypeRepository) {
		
		this.usersTypeRepository = usersTypeRepository;
	}
	
	//function to get all data from database based upon userstype
	public List<UsersType> getAll(){
		return usersTypeRepository.findAll();
	}
	
	
	

}
