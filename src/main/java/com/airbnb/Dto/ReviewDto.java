package com.airbnb.Dto;

import com.airbnb.Entity.Property;
import com.airbnb.Entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ReviewDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String comment;

	@Column(nullable = false)
	private int rating;

	@ManyToOne
	@JoinColumn(name = "property_id", nullable = false)
	private Property property;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
}
