package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;

@Component
public class BuildingConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public BuildingEntity dto2Entity(BuildingDTO dto) {
		return modelMapper.map(dto, BuildingEntity.class);
	}
	public BuildingDTO entity2DTO(BuildingEntity entity) {
		return modelMapper.map(entity, BuildingDTO.class);
	}
}
