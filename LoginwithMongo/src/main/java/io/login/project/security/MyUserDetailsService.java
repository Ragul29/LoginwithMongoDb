package io.login.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.login.project.model.User;
import io.login.project.repository.Userrepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	
	@Autowired
	private Userrepository userrep;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=userrep.findByusername(username);
		
		if (user==null)
			throw new UsernameNotFoundException("Username not found");
		
		
		return new UserPrincipals(user);
	}

}
