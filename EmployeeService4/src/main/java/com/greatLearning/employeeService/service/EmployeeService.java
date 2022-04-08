package com.greatLearning.employeeService.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.greatLearning.employeeService.entity.Employee;
import com.greatLearning.employeeService.entity.Role;
import com.greatLearning.employeeService.entity.User;
@Service
public interface EmployeeService {
	public List<Employee> findAll();
	public Employee findById(int theId);
	void save(Employee theEmp);
	void deleteById(int theId);
	List<Employee> searchByFirstName(String firstName);
	List<Employee> sortByFirstName(String order);
	public User saveUser(User user);
	public Role saveRole(Role role);
}
