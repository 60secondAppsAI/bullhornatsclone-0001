package com.bullhornatsclone.dao;

import java.util.List;

import com.bullhornatsclone.dao.GenericDAO;
import com.bullhornatsclone.domain.Client;





public interface ClientDAO extends GenericDAO<Client, Integer> {
  
	List<Client> findAll();
	






}


