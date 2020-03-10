package com.laptrinhjavaweb.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {
	
	@Autowired
	private BuildingRepository buildingRepository;
	@Autowired
	private BuildingConverter buildingConverter;
	@Override
	public List<BuildingDTO> findByBuilder(BuildingSearchBuilder builder) {
		Map<String, Object> params = convertToMapProperties(builder);
		List<BuildingEntity> entities = buildingRepository.findByBuilder(params, builder);
		List<BuildingDTO> dtos = new ArrayList<BuildingDTO>();
		for (BuildingEntity buildingEntity : entities) {
			dtos.add(buildingConverter.entity2DTO(buildingEntity));
		}
		return dtos;
	}
	private Map<String, Object> convertToMapProperties(BuildingSearchBuilder builder) {
		Map<String, Object> properties = new HashMap<String, Object>();
		Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
		for (Field field : fields) {
			try {
				if (!field.getName().startsWith("rent") && !field.getName().equals("types")
						&& !field.getName().equals("staffId")) {
					field.setAccessible(true);
					if (field.get(builder) instanceof String) {
						properties.put(field.getName().toLowerCase(), field.get(builder));
					} else {
						Object value = (field.get(builder)!=null && StringUtils.isNotEmpty(field.get(builder).toString())) 
								? Integer.parseInt(field.get(builder).toString()) : null;
						properties.put(field.getName().toLowerCase(), value);
					}	
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return properties;
	}
	/*
	@Override
	public BuildingDTO saveBuilding(BuildingDTO dto) {
		Integer buildingId = buildingRepository.insert(BuildingConverter.dto2Entity(dto));
		//TODO findById, return buildingDTO dựa trên buildingId
		return null;
	}*/
	
}
