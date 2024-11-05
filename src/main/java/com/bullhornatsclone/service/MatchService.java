package com.bullhornatsclone.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.bullhornatsclone.domain.Match;
import com.bullhornatsclone.dto.MatchDTO;
import com.bullhornatsclone.dto.MatchSearchDTO;
import com.bullhornatsclone.dto.MatchPageDTO;
import com.bullhornatsclone.dto.MatchConvertCriteriaDTO;
import com.bullhornatsclone.service.GenericService;
import com.bullhornatsclone.dto.common.RequestDTO;
import com.bullhornatsclone.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface MatchService extends GenericService<Match, Integer> {

	List<Match> findAll();

	ResultDTO addMatch(MatchDTO matchDTO, RequestDTO requestDTO);

	ResultDTO updateMatch(MatchDTO matchDTO, RequestDTO requestDTO);

    Page<Match> getAllMatchs(Pageable pageable);

    Page<Match> getAllMatchs(Specification<Match> spec, Pageable pageable);

	ResponseEntity<MatchPageDTO> getMatchs(MatchSearchDTO matchSearchDTO);
	
	List<MatchDTO> convertMatchsToMatchDTOs(List<Match> matchs, MatchConvertCriteriaDTO convertCriteria);

	MatchDTO getMatchDTOById(Integer matchId);







}





