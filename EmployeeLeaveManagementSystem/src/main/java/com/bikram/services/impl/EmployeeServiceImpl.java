package com.bikram.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.bikram.entities.Employee;
import com.bikram.exceptions.ResourceNotFoundException;
import com.bikram.repository.EmployeeRepository;
import com.bikram.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	// get-all-employees
	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		return employees;
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee Not Found with Employee-Id : " + employeeId));
		return employee;
	}

	@Override
	public Employee createEmployee(Employee employee) {

		employee.setSickLeave(15);
		employee.setCasualLeave(15);
		Employee createdEmployee = employeeRepository.save(employee);
		return createdEmployee;
	}

	@Override
	public Employee updateEmployee(Employee employee, int employeeId) {
		Employee employeeFromDB = employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee Not Found with Employee-Id : " + employeeId));
		employeeFromDB.setEmployeeName(employee.getEmployeeName());
		Employee updatedEmployee = employeeRepository.save(employeeFromDB);
		return updatedEmployee;
	}

	@Override
	public String deleteEmployee(int employeeId) {
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee Not Found with Employee-Id : " + employeeId));
		employeeRepository.delete(employee);
		String message = "Employee has been deleted with Employee-Id : " + employeeId;
		return message;
	}

}
