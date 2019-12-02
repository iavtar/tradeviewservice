package com.iavtar.tradeviewservice.model;

import lombok.Getter;
import lombok.Setter;


/**
 * @author indra singh
 * */
@Getter
@Setter
public class UpdateTradingAccountRequest {

	private String code;

	private double balance;

	private String currency;
	
}
