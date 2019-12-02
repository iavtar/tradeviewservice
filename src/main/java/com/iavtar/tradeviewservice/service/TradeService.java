package com.iavtar.tradeviewservice.service;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.iavtar.tradeviewservice.model.CreateTradeJournalRequest;
import com.iavtar.tradeviewservice.model.CreateTradingAccountRequest;
import com.iavtar.tradeviewservice.model.DeleteTradingJournalListRequest;
import com.iavtar.tradeviewservice.model.UpdateTradeJournalRequest;
import com.iavtar.tradeviewservice.model.UpdateTradingAccountRequest;


/**
 * @author indra singh
 * */
public interface TradeService {

	ResponseEntity<?> createTradingAccount(@Valid CreateTradingAccountRequest request, String username);

	ResponseEntity<?> getAllUserTradingAccount(String name);

	ResponseEntity<?> findById(Long accountid);

	ResponseEntity<?> deleteTradingAccountById(Long accountid);

	ResponseEntity<?> updateTradingAccountById(Long accountid, UpdateTradingAccountRequest request);

	ResponseEntity<?> createTadeJornal(@Valid CreateTradeJournalRequest request, Long accountid);

	ResponseEntity<?> getAllTradingJournals(Long accountid);

	ResponseEntity<?> deleteTradeJournalById(Long journalId);

	ResponseEntity<?> updateTradingJournalById(Long journalId, UpdateTradeJournalRequest request);

	ResponseEntity<?> gettradingjournalbyId(Long journalid);

	ResponseEntity<?> deleteTradingJournalList(DeleteTradingJournalListRequest request);

}
