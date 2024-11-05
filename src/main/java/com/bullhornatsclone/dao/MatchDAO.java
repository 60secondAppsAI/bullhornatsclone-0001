package com.bullhornatsclone.dao;

import java.util.List;

import com.bullhornatsclone.dao.GenericDAO;
import com.bullhornatsclone.domain.Match;





public interface MatchDAO extends GenericDAO<Match, Integer> {
  
	List<Match> findAll();
	






}


