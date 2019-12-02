package com.iavtar.tradeviewservice.model;

import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;


/**
 * @author indra singh
 * */
@Getter
@Setter
public class UpdateUserRequest {

	private String email;

	@Column(name = "address_line")
	private String addressLine;

	@Column(name = "city")
	private String city;
	
	@Column(name = "country")
	private String country;
}
