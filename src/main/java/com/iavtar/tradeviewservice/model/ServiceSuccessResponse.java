package com.iavtar.tradeviewservice.model;

import lombok.Getter;
import lombok.Setter;


/**
 * @author indra singh
 * */
@Getter
@Setter
public class ServiceSuccessResponse {

	private String message;

	public ServiceSuccessResponse() {

		this.message = "Request Successfully Completed";
	}

}
