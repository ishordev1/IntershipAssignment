package com.airbnb.Controller;

import com.airbnb.Dto.PropertyDto;
import com.airbnb.Helper.ApiResponse;
import com.airbnb.Service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

	@Autowired
	private PropertyService propertyService;

	@PostMapping
	public ResponseEntity<PropertyDto> createProperty(@RequestBody PropertyDto propertyDto) {
		PropertyDto createdProperty = propertyService.createProperty(propertyDto);
		return new ResponseEntity<>(createdProperty,HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PropertyDto> getPropertyById(@PathVariable Long id) {
		PropertyDto property = propertyService.getPropertyById(id);
		return new ResponseEntity<>(property,HttpStatus.OK);
	}
	

	@GetMapping
	public ResponseEntity<List<PropertyDto>> getAllProperties() {
		List<PropertyDto> properties = propertyService.getAllProperties();
		return new ResponseEntity<>(properties,HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PropertyDto> updateProperty(@PathVariable Long id, @RequestBody PropertyDto propertyDto) {
		PropertyDto updatedProperty = propertyService.updateProperty(id, propertyDto);
		return new ResponseEntity<>(updatedProperty,HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteProperty(@PathVariable Long id) {
		propertyService.deleteProperty(id);
		ApiResponse ar=new ApiResponse("Property deleted successfully",true);
		return new ResponseEntity<>(ar,HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<PropertyDto>> getPropertiesByUserId(@PathVariable Long userId) {
		List<PropertyDto> properties = propertyService.getPropertiesByUserId(userId);
		return new ResponseEntity<>(properties,HttpStatus.OK);
	}
}
