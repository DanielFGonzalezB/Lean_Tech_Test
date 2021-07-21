package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Employee;
import com.example.demo.repository.EmployeeRepo;


@Service
public class EmployeeServices {

	@Autowired
	EmployeeRepo employeeRepo;
	
	public ArrayList<Employee> getEmployees(){
		return (ArrayList<Employee>) employeeRepo.findAll();
	}
	
	public Employee addEmployee(Employee newEmployee) {
		Employee emp = new Employee(newEmployee.getId(), newEmployee.getPerson(),newEmployee.getPosition(),newEmployee.getSalary());
		return employeeRepo.save(emp);
	}
	
	public void deleteEmployee(int id) {
		Optional<Employee> emp = employeeRepo.findById(id);
		if (emp.isEmpty() == false) {
			employeeRepo.delete(emp.get());
		}
	
	}
	
	public Employee updateEmployee(Employee newEmployee) {
		Integer id = newEmployee.getId();
		deleteEmployee(id);
		return addEmployee(newEmployee);
	}

}
