package com.greatLearning.employeeService.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.greatLearning.employeeService.service.UserDetailsServiceImpl;
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
		public UserDetailsService userDetailsService()
		{
			return new UserDetailsServiceImpl();
		}
	
	@Bean
		public BCryptPasswordEncoder passwordEncoder()
		{
			return new BCryptPasswordEncoder(); 
		}
	
	@Bean 
	public AuthenticationManager authnticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
	
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder()); 
	 }
	
	
	protected void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests()
			.antMatchers("/h2-console/**").permitAll()
			.antMatchers("api/user","/api/role").hasAnyAuthority("ADMIN")
//			.antMatchers("/api/user","/api/role").permitAll()
			.antMatchers(HttpMethod.POST,"/api/employees").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.PUT,"/api/amployees").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.DELETE,"/api/employees/*").hasAuthority("ADMIN") 
			.anyRequest().authenticated()
			.and().httpBasic()
			.and()
			.cors().and().csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/h2-console/**");
	}
	
	
}
