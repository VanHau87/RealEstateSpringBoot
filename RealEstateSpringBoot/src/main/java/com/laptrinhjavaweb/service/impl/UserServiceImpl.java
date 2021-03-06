package com.laptrinhjavaweb.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserConverter userConverter;
	@Override
	public List<UserDTO> findByBuilding(Integer buildingId) {
//		Spring dataSpring Jpa
//		List<UserEntity> entities = userRepository.findByBuildings_Id(buildingId);
		
//		HQL
//		List<UserEntity> entities = userRepository.findByBuildingId(buildingId);
		
//		SQL Native
		List<UserEntity> entities = userRepository.findByBuildingId(buildingId);
		List<UserDTO> result = entities.stream().map(entity -> userConverter.entity2DTO(entity))
				.collect(Collectors.toList());
		return result;
	}
	
}
