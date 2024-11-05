package com.bullhornatsclone.dao;

import java.util.List;

import com.bullhornatsclone.dao.GenericDAO;
import com.bullhornatsclone.domain.Candidate;





public interface CandidateDAO extends GenericDAO<Candidate, Integer> {
  
	List<Candidate> findAll();
	






}


