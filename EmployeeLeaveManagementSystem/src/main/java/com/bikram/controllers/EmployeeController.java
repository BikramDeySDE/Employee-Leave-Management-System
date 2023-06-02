package com.bikram.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bikram.entities.Employee;
import com.bikram.services.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	// get all employees
	@GetMapping("/get-all-employees")
	public ResponseEntity<List<Employee>> getAllEmployees(){
		
		List<Employee> employees = employeeService.getAllEmployees();
		
		return new ResponseEntity<List<Employee>>(employees,HttpStatus.OK);
	}
	
	// get a single employee
		@GetMapping("/get-employee/{employeeId}")
		public ResponseEntity<Employee> getEmployeeById(@PathVariable int employeeId){
			
			Employee employee = employeeService.getEmployeeById(employeeId);
			
			return new ResponseEntity<Employee>(employee,HttpStatus.OK);
		}
	
	// create an employee
	@PostMapping("/register-employee")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
		
		Employee createdEmployee = employeeService.createEmployee(employee);
		
		return new ResponseEntity<Employee>(employee,HttpStatus.CREATED);
	}
	
	
	// update an employee
	@PostMapping("/update-employee/{employeeId}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable int employeeId){
		
		Employee updatedEmployee = employeeService.updateEmployee(employee, employeeId);
		
		return new ResponseEntity<Employee>(updatedEmployee,HttpStatus.OK);
	}
	
	
	// delete an employee
	@DeleteMapping("/delete-employee/{employeeId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int employeeId){
		
		String message = employeeService.deleteEmployee(employeeId);
		
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
}
