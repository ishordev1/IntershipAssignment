package com.airbnb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airbnb.Dto.UserDto;
import com.airbnb.Security.CustomUserServiceImp;
import com.airbnb.Security.JwtProvider;
import com.airbnb.Security.JwtRequest;
import com.airbnb.Security.JwtResponse;
import com.airbnb.Service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/auth")
@Tag(name="public Api")
public class AuthController {
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
@Autowired
private CustomUserServiceImp customUserService;
	@PostMapping("/signup")
	ResponseEntity<UserDto> register(@Validated @RequestBody UserDto userDto) {
		UserDto ud = this.userService.createUser(userDto);
		return new ResponseEntity<>(ud, HttpStatus.CREATED);
	}
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> signIn(@RequestBody JwtRequest request){
		Authentication authentication=authenticate(request.getUsername(),request.getPassword());
		String token=JwtProvider.generateToken(authentication);
		JwtResponse jwtResponse = new JwtResponse(token, "login Success.");
		return new ResponseEntity<>(jwtResponse,HttpStatus.OK);
	}

	private Authentication authenticate(String username, String password) {
		UserDetails userDetails=this.customUserService.loadUserByUsername(username);
		if(userDetails==null) {
			throw new BadCredentialsException("invalid user and password");
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("invalid password");
		}
		return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
	}
}
