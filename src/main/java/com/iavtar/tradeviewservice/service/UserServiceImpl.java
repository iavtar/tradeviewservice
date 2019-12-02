package com.iavtar.tradeviewservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.iavtar.tradeviewservice.entity.User;
import com.iavtar.tradeviewservice.model.RegisterUserRequest;
import com.iavtar.tradeviewservice.model.ServiceFailureResponse;
import com.iavtar.tradeviewservice.model.ServiceSuccessResponse;
import com.iavtar.tradeviewservice.repository.UserRepository;


/**
 * @author indra singh
 * */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public ResponseEntity<?> registerUser(RegisterUserRequest request) {

		ResponseEntity<?> response;

		ServiceSuccessResponse successResponse = new ServiceSuccessResponse();
		ServiceFailureResponse failureResponse = new ServiceFailureResponse();
		try {

			User user = userRepository.findByUsername(request.getUsername());

			if (!(user == null)) {
				failureResponse.setMessage("username already exists please try with a different username!");
				response = new ResponseEntity<ServiceFailureResponse>(failureResponse, HttpStatus.CONFLICT);
			} else if (!(request.getPassword().equalsIgnoreCase(request.getConfirmPassword()))) {
				failureResponse.setMessage("please enter the same password in confirm password field!");
				response = new ResponseEntity<ServiceFailureResponse>(failureResponse, HttpStatus.BAD_REQUEST);
			}else {
				User newUser = new User();
				newUser.setUsername(request.getUsername());
				newUser.setFirstName(request.getFirstName());
				newUser.setLastName(request.getLastName());
				newUser.setGender(request.getGender());
				newUser.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
				newUser.setAddressLine(request.getAddressLine());
				newUser.setCity(request.getCity());
				newUser.setCountry(request.getCountry());
				userRepository.save(newUser);
				successResponse.setMessage("User Registered Successfully");
				response = new ResponseEntity<ServiceSuccessResponse>(successResponse, HttpStatus.CREATED);
			}

		} catch (Exception e) {
			failureResponse.setMessage("There is an error while signup");
			response = new ResponseEntity<ServiceFailureResponse>(failureResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

}
