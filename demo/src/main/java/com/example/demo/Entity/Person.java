package com.example.demo.Entity;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;


@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({EmployeView.info.class, RolesView.info.class})
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "Employee_id")
    @MapsId
	Employee employee;
	
	@JsonView({EmployeView.info.class, RolesView.info.class})
    private String name;
	@JsonView({EmployeView.info.class, RolesView.info.class})
    private String lastName;
	@JsonView({EmployeView.info.class, RolesView.info.class})
	private String address;
	@JsonView({EmployeView.info.class, RolesView.info.class})
	private String cellPhone;
	@JsonView({EmployeView.info.class, RolesView.info.class})
	private String cityName;
	
	public Person() {
		
	}
	
	public Person(String name, String lastName, String address, String cellPhone, String cityName) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.address = address;
		this.cellPhone = cellPhone;
		this.cityName = cityName;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setEmployee(Employee emp) {
		this.employee = emp;
	}
	
	
	
}
