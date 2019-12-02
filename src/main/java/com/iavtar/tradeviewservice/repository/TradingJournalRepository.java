package com.iavtar.tradeviewservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iavtar.tradeviewservice.entity.TradingJournal;


/**
 * @author indra singh
 * */
@Repository
public interface TradingJournalRepository extends CrudRepository<TradingJournal, Long> {

	//List<TradingJournal> findAllByTradingAccountID(TradingAccount account);

	@Query("from TradingJournal where trading_account_id=:accountid")
	List<TradingJournal> findAllByTradingAccountID(@Param("accountid")Long accountid);

}
