package io.login.project.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import io.login.project.model.User;

@Repository
public interface Userrepository extends MongoRepository<User, String> {

	
User findByusername(String username); 


	
	 
}
