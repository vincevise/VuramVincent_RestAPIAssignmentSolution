package com.greatLearning.employeeService.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greatLearning.employeeService.entity.Employee;
import com.greatLearning.employeeService.entity.Role;
import com.greatLearning.employeeService.entity.User;
import com.greatLearning.employeeService.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	 private EmployeeService employeeService;
	
	 @Autowired
	 public EmployeeRestController(EmployeeService theEmployeeService) {
		 employeeService = theEmployeeService;
	 }
	 
	 @PostMapping("/user")
	 public User saveUser(@RequestBody User user) {
		 return employeeService.saveUser(user);
	 }
	 
	 @PostMapping("/role")
	 public Role saveRole(@RequestBody Role role) {
		 return employeeService.saveRole(role);
	 }
	 
	 // get employee list
	 @GetMapping("/employees")
	 public List<Employee> findAll(){
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 Collection<? extends GrantedAuthority> currentPrincipalName = authentication.getAuthorities();
		 System.out.println(currentPrincipalName);
		 return employeeService.findAll();
	 }
	 
	 // get a single employee
	 @GetMapping("/employees/{employeeId}")
	 public Employee getEmployee(@PathVariable int employeeId) {
		 Employee theEmp = employeeService.findById(employeeId);
		 if(theEmp == null) {
			 throw new RuntimeException("Employee id not found -" + employeeId);
		 }
		 return theEmp;
	 }
	 
	 // save employee
	 @PostMapping("/employees")
	 public Employee addEmployee(@RequestBody Employee theEmployee) {
		 //also just in case they pass an id in JSON .. set id to 0
		 // this is to force a save of new item ... instead of update
		 theEmployee.setId(0);
		 employeeService.save(theEmployee);
		 return theEmployee;
	 }
	 
	 //update employee
	 @PutMapping("/employees")
	 public Employee updateEmployee(@RequestBody Employee theEmployee) {
		 employeeService.save(theEmployee);
		 return theEmployee;
	 }
	 
	 // delete employee
	 @DeleteMapping("/employees/{employeeId}")
	 public String deleteEmployee(@PathVariable int employeeId) {
		 Employee tempEmployee = employeeService.findById(employeeId);
		 // throw exception if null
		 if(tempEmployee==null) {
			 throw new RuntimeException("Employee id not found - " + employeeId);
		 }
		 
		 employeeService.deleteById(employeeId);
		 
		 return "Deleted employee id - " + employeeId;
	 }
	 
	 @GetMapping("/employees/search/{firstName}")
	 public List<Employee> searchByFirstName(@PathVariable String firstName){
		 return employeeService.searchByFirstName(firstName);
	 }
	 
	 @GetMapping("/employees/sort")
	 public List<Employee> SortByFirstName(@RequestParam(name = "order") String order){
		return employeeService.sortByFirstName(order); 
	 }
}	
