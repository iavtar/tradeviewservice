package com.iavtar.tradeviewservice.model;

import lombok.Getter;
import lombok.Setter;


/**
 * @author indra singh
 * */
@Getter
@Setter
public class InvalidLoginResponse {

	private String username;
	private String password;

	public InvalidLoginResponse() {
		this.username = "Invalid Username";
		this.password = "Invalid Password";
	}

}
