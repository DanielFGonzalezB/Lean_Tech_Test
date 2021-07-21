package com.example.demo.Entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;



@Entity
public class Position {
	@JsonView({EmployeView.info.class, RolesView.info.class})
	private int id;
	
	@Id
	@JsonView({EmployeView.info.class, RolesView.info.class})
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "position")
	@JsonView(RolesView.info.class)
	private Set<Employee> employees;
	
	
	public Position() {
	}


	public Position(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
		
		for (Employee emp : employees) {
			emp.setPosition(this);
		}
	}	
	
}
