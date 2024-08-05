package com.airbnb.ServiceImp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airbnb.Dto.PropertyDto;
import com.airbnb.Entity.Property;
import com.airbnb.Entity.User;
import com.airbnb.Exception.ResourceNotFoundException;
import com.airbnb.Repository.PropertyRepository;
import com.airbnb.Repository.UserRepository;
import com.airbnb.Service.PropertyService;

@Service
public class PropertyServiceImp implements PropertyService {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PropertyRepository propertyRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public PropertyDto createProperty(PropertyDto propertyDto) {
		Property property = dtoToProperty(propertyDto);
		Property savedProperty = propertyRepository.save(property);
		return propertyToDto(savedProperty);
	}

	@Override
	public PropertyDto getPropertyById(Long id) {
		Property property = propertyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Property not found on this Id"));
		return propertyToDto(property);
	}

	@Override
	public List<PropertyDto> getAllProperties() {
		List<Property> properties = propertyRepository.findAll();
		return properties.stream().map(property -> propertyToDto(property)).collect(Collectors.toList());
	}

	@Override
	public PropertyDto updateProperty(Long id, PropertyDto propertyDtoDetails) {
		Property property = propertyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Property not found on this Id"));
//
		property.setName(propertyDtoDetails.getName());
		property.setDescription(propertyDtoDetails.getDescription());
		property.setAddress(propertyDtoDetails.getAddress());
		property.setPricePerNight(propertyDtoDetails.getPricePerNight());
		property.setNumberOfBedrooms(propertyDtoDetails.getNumberOfBedrooms());
		property.setNumberOfBathrooms(propertyDtoDetails.getNumberOfBathrooms());
		property.setAvailable(propertyDtoDetails.isAvailable());
		property.setDrinkAllowed(propertyDtoDetails.isDrinkAllowed());
		property.setPetAllowed(propertyDtoDetails.isPetAllowed());
		property.setMaxCheckoutTimeInNights(propertyDtoDetails.getMaxCheckoutTimeInNights());
		property.setExtraCharges(propertyDtoDetails.getExtraCharges());
		Property updatedProperty = propertyRepository.save(property);

		return propertyToDto(updatedProperty);
	}

	@Override
	public void deleteProperty(Long id) {
		Property property = propertyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Property not found on this Id"));
		propertyRepository.delete(property);
	}

	@Override
	public List<PropertyDto> getPropertiesByUserId(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found on this Id"));
		List<Property> properties = propertyRepository.findByOwner(user);
		return properties.stream().map(property -> propertyToDto(property)).collect(Collectors.toList());
	}

	// DTO
	public Property dtoToProperty(PropertyDto propertyDto) {
		Property property = this.modelMapper.map(propertyDto, Property.class);
		return property;
	}

	public PropertyDto propertyToDto(Property property) {
		PropertyDto propertyDto = this.modelMapper.map(property, PropertyDto.class);
		return propertyDto;
	}

}
