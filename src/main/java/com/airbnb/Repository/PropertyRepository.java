package com.airbnb.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airbnb.Entity.Property;
import com.airbnb.Entity.User;

public interface PropertyRepository extends JpaRepository<Property, Long> {
	  List<Property> findByOwner(User owner);
}
