package com.iavtar.tradeviewservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iavtar.tradeviewservice.entity.User;


/**
 * @author indra singh
 * */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);
	User getById(Long id);

}
