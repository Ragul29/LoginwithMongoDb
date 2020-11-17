package io.login.project.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@Order(1)
public class UserSecurity extends WebSecurityConfigurerAdapter{
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers("/css/**");
	    web.ignoring().antMatchers("/js/**");
	    web.ignoring().antMatchers("/img/**");
//	    web.ignoring().antMatchers("/register/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/login","/register","/registration-success","/logout").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()		
	    .loginPage("/login").permitAll()
	    .failureHandler(getAuthenticationFailureHandler())
	  .and()
		.logout().invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/logout").permitAll();
		
	}

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public AuthenticationProvider Auth()
	{
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}
	
	@Bean
	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
	    return new MyAuthenticationFailureHandler();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	@Override
//	protected UserDetailsService userDetailsService() {
//		
//		
//		List<UserDetails> list=new ArrayList<>();
//		list.add(User.withDefaultPasswordEncoder().username("ragul").password("ragul").roles("ADMIN").build());
//		
//		return new InMemoryUserDetailsManager(list);
//	}

	
	
	
	
	

}
