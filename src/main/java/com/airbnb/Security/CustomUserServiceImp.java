package com.airbnb.Security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.airbnb.Entity.User;
import com.airbnb.Exception.ResourceNotFoundException;
import com.airbnb.Repository.UserRepository;

@Service
public class CustomUserServiceImp implements UserDetailsService{
	@Autowired
private UserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	User user=	this.userRepo.findByUsername(username);
	if(user==null) {
		throw new ResourceNotFoundException("user not found of this username");
	}
	List<GrantedAuthority> authority=new ArrayList<>();
	
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authority);
	}
 
}
