package com.iavtar.tradeviewservice.model;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;


/**
 * @author indra singh
 * */
@Getter
@Setter
public class LoginRequest {

	@NotBlank(message = "username cannot be blank")
	private String username;
	@NotBlank(message = "password cannot be blank")
	private String password;
	
}
