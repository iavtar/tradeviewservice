package com.iavtar.tradeviewservice.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iavtar.tradeviewservice.entity.TradingAccount;
import com.iavtar.tradeviewservice.entity.TradingJournal;
import com.iavtar.tradeviewservice.entity.User;
import com.iavtar.tradeviewservice.model.CreateTradeJournalRequest;
import com.iavtar.tradeviewservice.model.CreateTradingAccountRequest;
import com.iavtar.tradeviewservice.model.DeleteTradingJournalListRequest;
import com.iavtar.tradeviewservice.model.ServiceFailureResponse;
import com.iavtar.tradeviewservice.model.ServiceSuccessResponse;
import com.iavtar.tradeviewservice.model.UpdateTradeJournalRequest;
import com.iavtar.tradeviewservice.model.UpdateTradingAccountRequest;
import com.iavtar.tradeviewservice.repository.TradingAccountRepository;
import com.iavtar.tradeviewservice.repository.TradingJournalRepository;
import com.iavtar.tradeviewservice.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author indra singh
 */
@Service
@Slf4j
public class TradeServiceImpl implements TradeService {

	@Autowired
	private TradingAccountRepository tradingAccountRepository;

	@Autowired
	private TradingJournalRepository tradingJournalRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseEntity<?> createTradingAccount(@Valid CreateTradingAccountRequest request, String username) {

		ResponseEntity<?> response;
		ServiceSuccessResponse successResponse = new ServiceSuccessResponse();
		ServiceFailureResponse failureResponse = new ServiceFailureResponse();

		try {

			User user = userRepository.findByUsername(username);

			TradingAccount account = new TradingAccount();
			account.setUser(user);
			account.setCode(request.getCode());
			account.setBalance(request.getBalance());
			account.setCurrency(request.getCurrency());

			tradingAccountRepository.save(account);

			successResponse.setMessage("Account successfully Created");

			response = new ResponseEntity<ServiceSuccessResponse>(successResponse, HttpStatus.CREATED);

		} catch (Exception e) {

			log.error(e.toString());
			failureResponse.setMessage("There is an error while creating your account! Please try later!");
			response = new ResponseEntity<ServiceFailureResponse>(failureResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return response;
	}

	@Override
	public ResponseEntity<?> getAllUserTradingAccount(String name) {

		ResponseEntity<?> response;
		ServiceFailureResponse failureResponse = new ServiceFailureResponse();

		try {
			User user = userRepository.findByUsername(name);
			List<TradingAccount> allUserTradingAccount = tradingAccountRepository
					.findAllUserTradingAccount(user.getId());

			response = new ResponseEntity<List<TradingAccount>>(allUserTradingAccount, HttpStatus.OK);
		} catch (Exception e) {
			log.error("There is an error while fetching all trading account of users");
			failureResponse.setMessage("Error fetching all trading account");
			response = new ResponseEntity<ServiceFailureResponse>(failureResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return response;
	}

	@Override
	public ResponseEntity<?> findById(Long accountid) {

		ResponseEntity<?> response;
		ServiceFailureResponse failureResponse = new ServiceFailureResponse();

		try {
			Optional<TradingAccount> account = tradingAccountRepository.findById(accountid);

			response = new ResponseEntity<Optional<TradingAccount>>(account, HttpStatus.FOUND);

		} catch (Exception e) {
			failureResponse.setMessage("Internal Server Error");
			response = new ResponseEntity<ServiceFailureResponse>(failureResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@Override
	public ResponseEntity<?> deleteTradingAccountById(Long accountid) {

		ResponseEntity<?> response;
		ServiceSuccessResponse successResponse = new ServiceSuccessResponse();
		ServiceFailureResponse failureResponse = new ServiceFailureResponse();

		try {

			tradingAccountRepository.deleteById(accountid);
			successResponse.setMessage("Account Successfully Deleted");
			response = new ResponseEntity<ServiceSuccessResponse>(successResponse, HttpStatus.OK);
		} catch (Exception e) {
			log.error("There is an error while deleting account " + e.toString());
			failureResponse.setMessage("Internal Server Error");
			response = new ResponseEntity<ServiceFailureResponse>(failureResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@Override
	public ResponseEntity<?> updateTradingAccountById(Long accountid, UpdateTradingAccountRequest request) {

		ResponseEntity<?> response;
		ServiceSuccessResponse successResponse = new ServiceSuccessResponse();
		ServiceFailureResponse failureResponse = new ServiceFailureResponse();

		try {
			TradingAccount account = tradingAccountRepository.findById(accountid).orElse(null);
			account.setCode(request.getCode());
			account.setBalance(request.getBalance());
			account.setCurrency(request.getCurrency());

			successResponse.setMessage("Account Successfully Updated");

			response = new ResponseEntity<ServiceSuccessResponse>(successResponse, HttpStatus.OK);
			tradingAccountRepository.save(account);
		} catch (Exception e) {
			log.error(e.toString());

			failureResponse.setMessage("Internal Server Error");
			response = new ResponseEntity<ServiceFailureResponse>(failureResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return response;
	}

	@Override
	public ResponseEntity<?> createTadeJornal(@Valid CreateTradeJournalRequest request, Long accountid) {

		ResponseEntity<?> response;
		ServiceSuccessResponse successResponse = new ServiceSuccessResponse();
		ServiceFailureResponse failureResponse = new ServiceFailureResponse();

		try {
			TradingAccount tradingAccount = tradingAccountRepository.findById(accountid).orElse(null);
			TradingJournal tradingJournal = new TradingJournal();
			tradingJournal.setTradingAccount(tradingAccount);
			tradingJournal.setTicker(request.getTicker());
			tradingJournal.setDuration(request.getDuration());
			tradingJournal.setDirection(request.getDirection());
			tradingJournal.setProfit(request.getProfit());
			tradingJournal.setCommission(request.getCommission());
			tradingJournal.setVolume(request.getVolume());
			tradingJournal.setEnterPrice(request.getEnterPrice());
			tradingJournal.setExitPrice(request.getExitPrice());
			tradingJournal.setEnterReason(request.getEnterReason());
			tradingJournal.setExitReason(request.getExitReason());
			tradingJournal.setComment(request.getComment());
			tradingJournal.setStatus("Opened");

			tradingJournalRepository.save(tradingJournal);

			successResponse.setMessage("Trading Journal Created Successfully");
			response = new ResponseEntity<ServiceSuccessResponse>(successResponse, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error(e.toString());

			failureResponse.setMessage("Internal Server Error");
			response = new ResponseEntity<ServiceFailureResponse>(failureResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return response;
	}

	@Override
	public ResponseEntity<?> getAllTradingJournals(Long accountid) {

		ResponseEntity<?> response;
		ServiceFailureResponse failureResponse = new ServiceFailureResponse();

		try {

			List<TradingJournal> allTradingJournals = tradingJournalRepository.findAllByTradingAccountID(accountid);

			response = new ResponseEntity<List<TradingJournal>>(allTradingJournals, HttpStatus.FOUND);
		} catch (Exception e) {

			failureResponse.setMessage("Internal Server Error");
			log.error("There is an error while fetching all Trading Journals!");
			response = new ResponseEntity<ServiceFailureResponse>(failureResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return response;
	}

	@Override
	public ResponseEntity<?> deleteTradeJournalById(Long journalId) {

		ResponseEntity<?> response;
		ServiceSuccessResponse successResponse = new ServiceSuccessResponse();
		ServiceFailureResponse failureResponse = new ServiceFailureResponse();

		try {
			tradingJournalRepository.deleteById(journalId);

			successResponse.setMessage("Journal Successfully Deleted");
			response = new ResponseEntity<ServiceSuccessResponse>(successResponse, HttpStatus.OK);
		} catch (Exception e) {
			log.error("There is an error while deleting the journal " + e.toString());
			failureResponse.setMessage("Internal Server Error");
			response = new ResponseEntity<ServiceFailureResponse>(failureResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@Override
	public ResponseEntity<?> updateTradingJournalById(Long journalId, UpdateTradeJournalRequest request) {

		ResponseEntity<?> response;
		ServiceSuccessResponse successResponse = new ServiceSuccessResponse();
		ServiceFailureResponse failureResponse = new ServiceFailureResponse();

		try {
			TradingJournal journal = tradingJournalRepository.findById(journalId).orElse(null);
			journal.setTicker(request.getTicker());
			journal.setDuration(request.getDuration());
			journal.setDirection(request.getDirection());
			journal.setProfit(request.getProfit());
			journal.setCommission(request.getCommission());
			journal.setVolume(request.getVolume());
			journal.setEnterPrice(request.getEnterPrice());
			journal.setExitPrice(request.getExitPrice());
			journal.setEnterReason(request.getEnterReason());
			journal.setExitReason(request.getExitReason());
			journal.setComment(request.getComment());
			journal.setStatus("Opened");

			tradingJournalRepository.save(journal);

			successResponse.setMessage("Trading Journal Successfully Updated");
			response = new ResponseEntity<ServiceSuccessResponse>(successResponse, HttpStatus.OK);
		} catch (Exception e) {
			log.error("There is an error while updating the journal " + e.toString());
			failureResponse.setMessage("Internal Server Error");
			response = new ResponseEntity<ServiceFailureResponse>(failureResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@Override
	public ResponseEntity<?> gettradingjournalbyId(Long journalid) {

		ResponseEntity<?> response;
		ServiceFailureResponse failureResponse = new ServiceFailureResponse();

		try {
			TradingJournal journal = tradingJournalRepository.findById(journalid).orElse(null);

			response = new ResponseEntity<TradingJournal>(journal, HttpStatus.OK);
		} catch (Exception e) {
			log.error("There is an error while fetching the journal " + e.toString());
			failureResponse.setMessage("Internal Server Error");
			response = new ResponseEntity<ServiceFailureResponse>(failureResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@Override
	public ResponseEntity<?> deleteTradingJournalList(DeleteTradingJournalListRequest request) {

		ResponseEntity<?> response;
		ServiceSuccessResponse successResponse = new ServiceSuccessResponse();
		ServiceFailureResponse failureResponse = new ServiceFailureResponse();

		try {
			for (int i = 0; i < request.getJournalIds().length; i++) {
				tradingJournalRepository.deleteById(request.getJournalIds()[i]);
			}
			successResponse.setMessage("Mutiple Journals deleted");
			response = new ResponseEntity<ServiceSuccessResponse>(successResponse, HttpStatus.OK);
		} catch (Exception e) {
			log.error("There is an error while deleting multiple journals " + e.toString());
			failureResponse.setMessage("Internal Server Error");
			response = new ResponseEntity<ServiceFailureResponse>(failureResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

}
