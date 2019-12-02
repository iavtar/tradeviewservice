package com.iavtar.tradeviewservice.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;


/**
 * @author indra singh
 * */
@Getter
@Setter
public class GetTradeJournalDetailResponse {

	private String ticker;

	private Date openedDate;

	private Date closedDate;

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
