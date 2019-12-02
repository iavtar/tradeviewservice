package com.iavtar.tradeviewservice.model;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;


/**
 * @author indra singh
 * */
@Getter
@Setter
public class RegisterUserRequest {

	@Email(message = "username needs to be an email")
	@NotBlank(message = "username is required")
	@Column(name = "user_name")
	private String username;

	@NotBlank(message = "Please enter your firstname")
	@Column(name = "first_name")
	private String firstName;

	@NotBlank(message = "Please enter your lastname")
	@Column(name = "last_name")
	private String lastName;

	@Column(name = "gender")
	private char gender;

	@NotBlank(message = "Please enter a valid pasword")
	@Column(name = "password")
	private String password;

	@Transient
	private String confirmPassword;

	@Column(name = "address_line")
	private String addressLine;

	@Column(name = "city")
	private String city;
	
	@Column(name = "country")
	private String country;

}
