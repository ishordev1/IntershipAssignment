package com.airbnb.Exception;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.airbnb.Helper.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler( ResourceNotFoundException ex){
		String message=ex.getMessage();
		ApiResponse apiResponse =new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
		
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String,Object>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex){
	              List<ObjectError> allErrors =  ex.getBindingResult().getAllErrors();
	              Map<String,Object> response = new HashMap<>();
	              allErrors.stream().forEach(ObjectError ->{
	                      String message = ObjectError.getDefaultMessage();
	                      String feild = ((FieldError)ObjectError).getField();
	                      response.put(feild,message);
	              });
	              return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	    }
	    
	    @ExceptionHandler(BadCredentialsException.class)
	    public ResponseEntity<ApiResponse> handleBadCredentialsException(BadCredentialsException ex) {
	        String message = "Invalid username or password";
	        ApiResponse apiResponse = new ApiResponse(message, false);
	        return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
	    }
}
