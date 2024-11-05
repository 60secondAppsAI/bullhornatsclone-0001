package com.bullhornatsclone.dao;

import java.util.List;

import com.bullhornatsclone.dao.GenericDAO;
import com.bullhornatsclone.domain.Application;





public interface ApplicationDAO extends GenericDAO<Application, Integer> {
  
	List<Application> findAll();
	






}


