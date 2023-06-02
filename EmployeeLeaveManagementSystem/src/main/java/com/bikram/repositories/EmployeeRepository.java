package com.bikram.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bikram.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
