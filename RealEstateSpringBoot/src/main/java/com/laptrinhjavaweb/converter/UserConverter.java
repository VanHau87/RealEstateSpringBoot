package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;

@Component
public class UserConverter {
	@Autowired
	private ModelMapper modelMapper;
	
	public UserEntity dto2Entity(UserDTO dto) {
		return modelMapper.map(dto, UserEntity.class);
	}
	public UserDTO entity2DTO(UserEntity entity) {
		return modelMapper.map(entity, UserDTO.class);
	}
}
