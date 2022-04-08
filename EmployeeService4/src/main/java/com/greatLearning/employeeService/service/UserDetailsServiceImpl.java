package com.greatLearning.employeeService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greatLearning.employeeService.dao.UserRepository;
import com.greatLearning.employeeService.entity.User;
import com.greatLearning.employeeService.security.MyUserDetails;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException{
		
		User user = userRepository.getUserByUsername(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("Could not find user");
		}
		
		return new MyUserDetails(user);
	}
	
}
