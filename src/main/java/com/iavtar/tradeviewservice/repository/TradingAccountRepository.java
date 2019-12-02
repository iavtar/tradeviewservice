package com.iavtar.tradeviewservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iavtar.tradeviewservice.entity.TradingAccount;


/**
 * @author indra singh
 * */
@Repository
public interface TradingAccountRepository extends CrudRepository<TradingAccount, Long>{

	@Query("from TradingAccount where user_id=:userId")
	List<TradingAccount> findAllUserTradingAccount(@Param("userId") Long userId);

}
