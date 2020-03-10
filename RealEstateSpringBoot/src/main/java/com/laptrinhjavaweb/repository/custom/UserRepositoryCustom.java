package com.laptrinhjavaweb.repository.custom;

import java.util.List;

import com.laptrinhjavaweb.entity.UserEntity;

public interface UserRepositoryCustom {
	//HQL
//	List<UserEntity> findByBuildingId(Integer buildingId);
	
//	SQL Native
	List<UserEntity> findByBuildingId(Integer buildingId);
}
