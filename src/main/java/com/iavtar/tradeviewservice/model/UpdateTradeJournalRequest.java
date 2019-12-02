package com.iavtar.tradeviewservice.model;

import lombok.Setter;

import lombok.Getter;

/**
 * @author indra singh
 * */
@Getter
@Setter
public class UpdateTradeJournalRequest {

	private String ticker;

	private int duration;

	private int direction;

	private double profit;

	private double commission;

	private double volume;

	private double enterPrice;

	private double exitPrice;

	private String enterReason;

	private String exitReason;

	private String comment;

	private String status;
	
}
