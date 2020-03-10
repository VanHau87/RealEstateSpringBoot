package com.laptrinhjavaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.BuildingService;
import com.laptrinhjavaweb.service.UserService;


@RestController
public class BuildingAPI{
	
	@Autowired
	private BuildingService buildingService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/building")
	public List<BuildingDTO> searchBuilding(@RequestParam Map<String, String> model,
											@RequestParam(name = "types") String[] types){
		BuildingSearchBuilder builder = new BuildingSearchBuilder.Builder()
				.setName(model.get("name"))
				.setDistrict(model.get("district"))
				.setFloorArea(model.get("floorArea"))
				.setNumberOfBasement(model.get("numberOfBasement"))
				.setRentAreaFrom(model.get("rentAreaFrom"))
				.setRentAreaTo(model.get("rentAreaTo"))
				.setRentCostFrom(model.get("rentCostFrom"))
				.setRentCostTo(model.get("rentCostTo"))
				.setTypes(types)
				.setStaffId(Integer.parseInt(model.get("staffId")))
				.build();
		List<BuildingDTO> results = buildingService.findByBuilder(builder);
		return results;
	}
	
	@GetMapping(value = "/building/{buildingid}/users")
	public List<UserDTO> getStaffByBuilding(@PathVariable("buildingid") Integer buildingid){
		List<UserDTO> result = userService.findByBuilding(buildingid);
		return result;
	}
}
