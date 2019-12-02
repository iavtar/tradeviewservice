package com.iavtar.tradeviewservice.model;

import lombok.Getter;
import lombok.Setter;


/**
 * @author indra singh
 * */
@Getter
@Setter
public class ServiceFailureResponse {

	private String message;

	public ServiceFailureResponse() {

		this.message = "Internal Server Error";
	}

}
