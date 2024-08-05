package com.airbnb.Exception;

import lombok.Builder;

@Builder
public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException() {
		super("resource Not Found");
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
