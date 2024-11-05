package com.bullhornatsclone.dao;

import java.util.List;

import com.bullhornatsclone.dao.GenericDAO;
import com.bullhornatsclone.domain.Job;





public interface JobDAO extends GenericDAO<Job, Integer> {
  
	List<Job> findAll();
	






}


