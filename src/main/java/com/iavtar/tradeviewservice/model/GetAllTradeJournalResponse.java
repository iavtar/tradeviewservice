package com.iavtar.tradeviewservice.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


/**
 * @author indra singh
 * */
@Getter
@Setter
public class GetAllTradeJournalResponse {

	List<TradeJournalEntry> allTradeJournals;
	
}
