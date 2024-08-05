package com.airbnb.Dto;

import java.math.BigDecimal;
import java.util.List;

import com.airbnb.Entity.Review;
import com.airbnb.Entity.User;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
public class PropertyDto {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;
	    private String description;
	    private String address;
	    private BigDecimal pricePerNight;
	    private int numberOfBedrooms;
	    private int numberOfBathrooms;
	    private boolean isAvailable;
	    private boolean drinkAllowed;
	    private boolean petAllowed;
	    private int maxCheckoutTimeInNights;
	    private BigDecimal extraCharges;

	    @OneToMany(mappedBy = "property")
	    private List<Review> reviews;

	    @ManyToOne
	    @JoinColumn(name = "owner_id")
	    private User owner;
}
