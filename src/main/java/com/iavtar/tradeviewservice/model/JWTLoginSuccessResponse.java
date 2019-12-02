package com.iavtar.tradeviewservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * @author indra singh
 * */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class JWTLoginSuccessResponse {

	private boolean success;
	private String token;
	
}
