package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;


public interface BuildingService {
	List<BuildingDTO> findByBuilder(BuildingSearchBuilder builder);
//	BuildingDTO saveBuilding(BuildingDTO dto);
}
