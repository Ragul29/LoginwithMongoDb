package io.login.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.login.project.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {
//	 List<Customer> findByName(String name); 

}
