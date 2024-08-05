package com.airbnb.Dto;

import java.util.List;

import com.airbnb.Entity.Property;
import com.airbnb.Entity.Review;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(unique = true, nullable = false)
	    @Size(min = 3,max = 30,message = "Name must be between 3 to 30 character")
	    @NotBlank(message = "username is must required")
	    private String username;

	    @Column(nullable = false)
	    @NotBlank(message = "password is must required")
	    private String password;

	    @Column(nullable = false)
	    @NotBlank(message = "Email is must required")
	    private String email;

	    @Column(nullable = false)
	    private String fullName;

	    private String phoneNumber;

	    @OneToMany(mappedBy = "owner")
	    private List<Property> properties;

	    @OneToMany(mappedBy = "user")
	    private List<Review> reviews;
}
