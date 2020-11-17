package io.login.project.model;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer")
public class Customer {

	@Id
   private String id;

	@NotBlank(message = "Name is mandatory")
   private String name;

	@NotBlank(message = "Email is mandatory")
   private String email;

	@NotBlank(message = "Message is mandatory")
   private String query;
    
  

    public Customer() {}

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getQuery() {
		return query;
	}



	public void setQuery(String query) {
		this.query = query;
	}

	public Customer(String name, String email, String query) {
		super();
		this.name = name;
		this.email = email;
		this.query = query;
	}



	


	
    

	
	}


