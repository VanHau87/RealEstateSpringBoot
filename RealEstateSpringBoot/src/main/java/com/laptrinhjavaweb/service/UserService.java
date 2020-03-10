package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.dto.UserDTO;

public interface UserService {
	List<UserDTO> findByBuilding(Integer buildingId);
}
