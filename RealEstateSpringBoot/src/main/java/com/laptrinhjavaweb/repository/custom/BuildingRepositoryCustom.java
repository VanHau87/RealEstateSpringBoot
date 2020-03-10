package com.laptrinhjavaweb.repository.custom;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;

public interface BuildingRepositoryCustom {
	List<BuildingEntity> findByBuilder(Map<String, Object> params, BuildingSearchBuilder builder);
}
