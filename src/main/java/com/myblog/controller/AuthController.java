package com.myblog.controller;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myblog.entity.Role;
import com.myblog.entity.User;
import com.myblog.payload.SignUpDto;
import com.myblog.repository.RoleRepository;
import com.myblog.repository.UserRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	private ModelMapper modelMapper;

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody SignUpDto signupDto) {

		if (userRepository.existsByUsername(signupDto.getUsername())) {
			return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
		}
		if (userRepository.existsByEmail(signupDto.getEmail())) {
			return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
		}
		
		User user = new User();
		user.setName(signupDto.getName());
		user.setUsername(signupDto.getUsername());
		user.setEmail(signupDto.getEmail());
		user.setPassword(passwordEncoder.encode(signupDto.getPassword()));
		Role roles = roleRepository.findByName("ROLE_USER").get();
		Set<Role> role=new HashSet<>();
		role.add(roles);
		user.setRoles(role);
		User savedUser = userRepository.save(user);
		
		SignUpDto dto = new SignUpDto();
		dto.setName(savedUser.getName());
		dto.setUsername(savedUser.getUsername());
		dto.setEmail(savedUser.getEmail());
		return new ResponseEntity<>(dto,HttpStatus.CREATED);
	}

}
