package com.airbnb.Service;

import java.util.List;

import com.airbnb.Dto.PropertyDto;


public interface PropertyService {
    PropertyDto createProperty(PropertyDto propertyDto);
    PropertyDto getPropertyById(Long id);
    List<PropertyDto> getAllProperties();
    PropertyDto updateProperty(Long id, PropertyDto propertyDtoDetails);
    void deleteProperty(Long id);
    List<PropertyDto> getPropertiesByUserId(Long userId);
    
    
}
