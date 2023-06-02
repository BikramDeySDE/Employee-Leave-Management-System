package com.bikram.services;

import java.util.List;

import com.bikram.entities.Employee;

public interface EmployeeService {

	List<Employee> getAllEmployees();
	
	Employee getEmployeeById(int employeeId);
	
	Employee createEmployee(Employee employee);
	
	Employee updateEmployee(Employee employee, int employeeId);
	
	String deleteEmployee(int employeeId);
	
}
