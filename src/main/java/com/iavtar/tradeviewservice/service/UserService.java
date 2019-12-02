package com.iavtar.tradeviewservice.service;

import org.springframework.http.ResponseEntity;

import com.iavtar.tradeviewservice.model.RegisterUserRequest;


/**
 * @author indra singh
 * */
public interface UserService {

	ResponseEntity<?> registerUser(RegisterUserRequest request);


}
