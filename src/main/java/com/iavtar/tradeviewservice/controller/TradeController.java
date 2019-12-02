package com.iavtar.tradeviewservice.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iavtar.tradeviewservice.model.CreateTradeJournalRequest;
import com.iavtar.tradeviewservice.model.CreateTradingAccountRequest;
import com.iavtar.tradeviewservice.model.DeleteTradingJournalListRequest;
import com.iavtar.tradeviewservice.model.UpdateTradeJournalRequest;
import com.iavtar.tradeviewservice.model.UpdateTradingAccountRequest;
import com.iavtar.tradeviewservice.service.MapValidationErrorService;
import com.iavtar.tradeviewservice.service.TradeService;

/**
 * @author indra singh
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/trade")
public class TradeController {

	@Autowired
	private TradeService tradeService;
	@Autowired
	private MapValidationErrorService mapValidationService;

	@PostMapping("/createtradingaccount")
	public ResponseEntity<?> createTradingAccount(@Valid @RequestBody CreateTradingAccountRequest request,
			Principal principal, BindingResult result) {

		String username = principal.getName();

		ResponseEntity<?> errorMap = mapValidationService.mapValidationService(result);

		if (errorMap != null) {
			return errorMap;
		}

		ResponseEntity<?> response = tradeService.createTradingAccount(request, username);

		return response;
	}

	@GetMapping("/getallusertradingaccount")
	public ResponseEntity<?> getAllTradingAccount(Principal principal) {

		ResponseEntity<?> response = tradeService.getAllUserTradingAccount(principal.getName());

		return response;
	}

	@GetMapping("/gettradingaccount/{accountid}")
	public ResponseEntity<?> getTradingAccount(@PathVariable("accountid") Long accountid) {

		ResponseEntity<?> response = tradeService.findById(accountid);

		return response;
	}

	@GetMapping("/deletetradingaccount/{accountid}")
	public ResponseEntity<?> deleteTradingAccount(@PathVariable("accountid") Long accountid) {

		ResponseEntity<?> response = tradeService.deleteTradingAccountById(accountid);

		return response;
	}

	@PostMapping("/updatetradingaccount/{accountid}")
	public ResponseEntity<?> updateTradingAccount(@PathVariable("accountid") Long accountid,
			@RequestBody UpdateTradingAccountRequest request) {

		ResponseEntity<?> response = tradeService.updateTradingAccountById(accountid, request);

		return response;

	}

	@PostMapping("/createTradingJournal/{accountid}")
	public ResponseEntity<?> createTradingJornal(@Valid @RequestBody CreateTradeJournalRequest request,
			@PathVariable("accountid") Long accountid, BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationService.mapValidationService(result);

		if (errorMap != null) {
			return errorMap;
		}

		ResponseEntity<?> response = tradeService.createTadeJornal(request, accountid);

		return response;

	}

	@GetMapping("/gettradingjournalbyId/{journalid}")
	public ResponseEntity<?> gettradingjournalbyId(@PathVariable("journalid") Long journalid) {

		ResponseEntity<?> response = tradeService.gettradingjournalbyId(journalid);

		return response;
	}

	@GetMapping("/alltradingjournal/{accountid}")
	public ResponseEntity<?> getAllTradingJournal(@PathVariable("accountid") Long accountid) {

		ResponseEntity<?> response = tradeService.getAllTradingJournals(accountid);

		return response;

	}

	@GetMapping("/deletetradingjournal/{journalId}")
	public ResponseEntity<?> deleteTradingJournal(@PathVariable("journalId") Long journalId) {

		ResponseEntity<?> response = tradeService.deleteTradeJournalById(journalId);

		return response;

	}

	@PostMapping("/updatetradingjournal/{journalId}")
	public ResponseEntity<?> updateTradingJournalById(@PathVariable("journalId") Long journalId,
			@RequestBody UpdateTradeJournalRequest request) {

		ResponseEntity<?> response = tradeService.updateTradingJournalById(journalId, request);

		return response;

	}
	
	@PostMapping("/deletetradejournalinlist")
	public ResponseEntity<?> deleteTradingJournalList(@RequestBody DeleteTradingJournalListRequest request){
		
		ResponseEntity<?> response = tradeService.deleteTradingJournalList(request);
		
		return response;
		
	}

}
