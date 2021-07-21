package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.EmployeView;
import com.example.demo.Entity.Employee;
import com.example.demo.Entity.Position;
import com.example.demo.Entity.RolesView;
import com.example.demo.services.EmployeeServices;
import com.example.demo.services.RoleServices;
import com.fasterxml.jackson.annotation.JsonView;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeServices employeeServices;
	
	@Autowired
	RoleServices rolesServices;
	
	
	
	public ArrayList<Employee> getEMmployee(){
		return employeeServices.getEmployees();
	}
	
	@PostMapping()
	@JsonView(EmployeView.info.class)
	public Employee addEmployee(@RequestBody Employee employee){
		int positionCount = rolesServices.countPositions();
		ArrayList<Position> positions = rolesServices.getPosition();
		
		if (positions.isEmpty()) {
			rolesServices.setPosition(employee.getPosition());
			return employeeServices.addEmployee(employee);
		}
		
		for (Position position : positions) {
			if(employee.getPosition().getName().equals(position.getName())) {
				employee.getPosition().setId(position.getId());
				employee.setPosition(position);
			}else {
				employee.getPosition().setId(positionCount +1);
				rolesServices.setPosition(employee.getPosition());
			}
		}
		
		return employeeServices.addEmployee(employee);
	}
	
	@DeleteMapping("/removeEmployee/{id}")
	public void deleteEmployee(@PathVariable(value = "id") Integer id) {
		employeeServices.deleteEmployee(id);
	}
	
	@PutMapping("/updateEmployee")
	@JsonView(EmployeView.info.class)
	public Employee updateEmployee(@RequestBody Employee employee){
		int positionCount = rolesServices.countPositions();
		ArrayList<Position> positions = rolesServices.getPosition();
		
		if (positions.isEmpty()) {
			rolesServices.setPosition(employee.getPosition());
			return employeeServices.addEmployee(employee);
		}
		
		for (Position position : positions) {
			if(employee.getPosition().getName().equals(position.getName())) {
				employee.getPosition().setId(position.getId());
				employee.setPosition(position);
			}else {
				employee.getPosition().setId(positionCount +1);
				rolesServices.setPosition(employee.getPosition());
			}
		}
		
		return employeeServices.updateEmployee(employee);
	}
	
	
	@GetMapping(value = "/ListEmployees")
	@JsonView(EmployeView.info.class)
	public ArrayList<Employee> getEMmployeeByNameRole(@RequestParam(value = "name", required = false) Optional<String> name, 
                                                      @RequestParam(value = "role", required = false) Optional<String> role){
		ArrayList<Employee> AllEmp = employeeServices.getEmployees();
		ArrayList<Employee> FilteredEmp = new ArrayList<Employee>();
		if (!name.isEmpty()) {	
			for (Employee emp : AllEmp) {
				if (emp.getPerson().getName().equals(name.get())) {
				FilteredEmp.add(emp);
				}
			}
			return FilteredEmp;
			
		}else if (!role.isEmpty()) {
			for (Employee emp : AllEmp) {
				if (emp.getPosition().getName().equals(role.get())) {
				FilteredEmp.add(emp);
				}
			}
			return FilteredEmp;
		}
		
		return AllEmp;	
	}
	
	
	@GetMapping(value = "/roles")
	@JsonView(RolesView.info.class)
	public ArrayList<Position> getRoles(){
		 
		return rolesServices.getPosition();
	}
	
	
}
