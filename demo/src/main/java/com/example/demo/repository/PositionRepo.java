package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.Entity.Employee;
import com.example.demo.Entity.Position;




public interface PositionRepo extends PagingAndSortingRepository<Position, String>{
	
	ArrayList<Position> findByOrderByEmployeesSalaryDesc();

}
