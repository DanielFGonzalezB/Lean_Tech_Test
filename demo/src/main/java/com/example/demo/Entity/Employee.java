package com.example.demo.Entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Employee  {
	
	@Id
	@JsonView({EmployeView.info.class, RolesView.info.class})
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "position")
	@JsonView(EmployeView.info.class)
	private Position position;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "employee")
	@JsonView({EmployeView.info.class, RolesView.info.class})
	private Person person;
	
	@JsonView({EmployeView.info.class, RolesView.info.class})
	private String salary;
	
	public Employee(int id,Person person, Position position, String salary) {
		super();
		this.id = id;
		this.person = person;
		this.position = position;
		this.salary = salary;
		this.person.setEmployee(this);
	}
	
	public Employee() {
		
	}
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}

	
}
