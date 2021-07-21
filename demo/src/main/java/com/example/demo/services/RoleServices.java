package com.example.demo.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.Entity.Position;
import com.example.demo.repository.PositionRepo;

@Service
public class RoleServices {
	
	@Autowired
	PositionRepo positionRepo;
	
	public ArrayList<Position> getPosition(){
		return (ArrayList<Position>) positionRepo.findAll() ;
	}
	
	public void setPosition(Position position) {
		positionRepo.save(position);
	}
	
	
	public int countPositions() {
		
		int positions = (int) positionRepo.count();
		
		return positions;
	}

}
