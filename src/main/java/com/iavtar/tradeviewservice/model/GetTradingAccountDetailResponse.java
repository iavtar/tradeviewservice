package com.iavtar.tradeviewservice.model;

import lombok.Getter;
import lombok.Setter;


/**
 * @author indra singh
 * */
@Getter
@Setter
public class GetTradingAccountDetailResponse {

	private Long tradingAccountId;

	private String code;

	private double balance;

	private String currency;

}
