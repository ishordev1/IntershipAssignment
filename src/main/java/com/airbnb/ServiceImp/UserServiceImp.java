package com.airbnb.ServiceImp;

import com.airbnb.Dto.UserDto;
import com.airbnb.Entity.User;
import com.airbnb.Exception.ResourceNotFoundException;
import com.airbnb.Repository.UserRepository;
import com.airbnb.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {

		User user = modelMapper.map(userDto, User.class);
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		User savedUser = userRepository.save(user);
		return modelMapper.map(savedUser, UserDto.class);
	}

	@Override
	public UserDto getUserById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user not found in this id"));
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto getUserByUserName(String userName) {
		User user = userRepository.findByUsername(userName);
		if (user == null) {
			throw new ResourceNotFoundException("User not found with username: " + userName);
		}
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
	}

	@Override
	public UserDto updateUser(Long id, UserDto userDto) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setFullName(userDto.getFullName());
		user.setPhoneNumber(userDto.getPhoneNumber());
		if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
			user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		}

		User updatedUser = userRepository.save(user);
		return modelMapper.map(updatedUser, UserDto.class);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
}
