package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Entity.Employee;



public interface EmployeeRepo extends CrudRepository<Employee, Integer>{
	
	 ArrayList<Employee> findByOrderBySalaryDesc();

}
