package com.iavtar.tradeviewservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iavtar.tradeviewservice.model.JWTLoginSuccessResponse;
import com.iavtar.tradeviewservice.model.LoginRequest;
import com.iavtar.tradeviewservice.model.RegisterUserRequest;
import com.iavtar.tradeviewservice.security.JwtTokenProvider;
import com.iavtar.tradeviewservice.security.SecurityConstants;
import com.iavtar.tradeviewservice.service.MapValidationErrorService;
import com.iavtar.tradeviewservice.service.UserService;


/**
 * @author indra singh
 * */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/users/")
public class UserController {

	@Autowired
	private MapValidationErrorService mapValidationService;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request, BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationService.mapValidationService(result);

		if (errorMap != null) {
			return errorMap;
		}

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = SecurityConstants.TOKEN_PREFIX + tokenProvider.generateToken(authentication);
		
		return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserRequest request, BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationService.mapValidationService(result);

		if (errorMap != null) {
			return errorMap;
		}

		ResponseEntity<?> response = userService.registerUser(request);

		return response;

	}
	
}
