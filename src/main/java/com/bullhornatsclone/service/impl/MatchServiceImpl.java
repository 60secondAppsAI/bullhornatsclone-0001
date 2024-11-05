package com.bullhornatsclone.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.bullhornatsclone.dao.GenericDAO;
import com.bullhornatsclone.service.GenericService;
import com.bullhornatsclone.service.impl.GenericServiceImpl;
import com.bullhornatsclone.dao.MatchDAO;
import com.bullhornatsclone.domain.Match;
import com.bullhornatsclone.dto.MatchDTO;
import com.bullhornatsclone.dto.MatchSearchDTO;
import com.bullhornatsclone.dto.MatchPageDTO;
import com.bullhornatsclone.dto.MatchConvertCriteriaDTO;
import com.bullhornatsclone.dto.common.RequestDTO;
import com.bullhornatsclone.dto.common.ResultDTO;
import com.bullhornatsclone.service.MatchService;
import com.bullhornatsclone.util.ControllerUtils;





@Service
public class MatchServiceImpl extends GenericServiceImpl<Match, Integer> implements MatchService {

    private final static Logger logger = LoggerFactory.getLogger(MatchServiceImpl.class);

	@Autowired
	MatchDAO matchDao;

	


	@Override
	public GenericDAO<Match, Integer> getDAO() {
		return (GenericDAO<Match, Integer>) matchDao;
	}
	
	public List<Match> findAll () {
		List<Match> matchs = matchDao.findAll();
		
		return matchs;	
		
	}

	public ResultDTO addMatch(MatchDTO matchDTO, RequestDTO requestDTO) {

		Match match = new Match();

		match.setMatchId(matchDTO.getMatchId());


		match.setMatchScore(matchDTO.getMatchScore());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		match = matchDao.save(match);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Match> getAllMatchs(Pageable pageable) {
		return matchDao.findAll(pageable);
	}

	public Page<Match> getAllMatchs(Specification<Match> spec, Pageable pageable) {
		return matchDao.findAll(spec, pageable);
	}

	public ResponseEntity<MatchPageDTO> getMatchs(MatchSearchDTO matchSearchDTO) {
	
			Integer matchId = matchSearchDTO.getMatchId(); 
  			String sortBy = matchSearchDTO.getSortBy();
			String sortOrder = matchSearchDTO.getSortOrder();
			String searchQuery = matchSearchDTO.getSearchQuery();
			Integer page = matchSearchDTO.getPage();
			Integer size = matchSearchDTO.getSize();

	        Specification<Match> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, matchId, "matchId"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Match> matchs = this.getAllMatchs(spec, pageable);
		
		//System.out.println(String.valueOf(matchs.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(matchs.getTotalPages()));
		
		List<Match> matchsList = matchs.getContent();
		
		MatchConvertCriteriaDTO convertCriteria = new MatchConvertCriteriaDTO();
		List<MatchDTO> matchDTOs = this.convertMatchsToMatchDTOs(matchsList,convertCriteria);
		
		MatchPageDTO matchPageDTO = new MatchPageDTO();
		matchPageDTO.setMatchs(matchDTOs);
		matchPageDTO.setTotalElements(matchs.getTotalElements());
		return ResponseEntity.ok(matchPageDTO);
	}

	public List<MatchDTO> convertMatchsToMatchDTOs(List<Match> matchs, MatchConvertCriteriaDTO convertCriteria) {
		
		List<MatchDTO> matchDTOs = new ArrayList<MatchDTO>();
		
		for (Match match : matchs) {
			matchDTOs.add(convertMatchToMatchDTO(match,convertCriteria));
		}
		
		return matchDTOs;

	}
	
	public MatchDTO convertMatchToMatchDTO(Match match, MatchConvertCriteriaDTO convertCriteria) {
		
		MatchDTO matchDTO = new MatchDTO();
		
		matchDTO.setMatchId(match.getMatchId());

	
		matchDTO.setMatchScore(match.getMatchScore());

	

		
		return matchDTO;
	}

	public ResultDTO updateMatch(MatchDTO matchDTO, RequestDTO requestDTO) {
		
		Match match = matchDao.getById(matchDTO.getMatchId());

		match.setMatchId(ControllerUtils.setValue(match.getMatchId(), matchDTO.getMatchId()));

		match.setMatchScore(ControllerUtils.setValue(match.getMatchScore(), matchDTO.getMatchScore()));



        match = matchDao.save(match);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public MatchDTO getMatchDTOById(Integer matchId) {
	
		Match match = matchDao.getById(matchId);
			
		
		MatchConvertCriteriaDTO convertCriteria = new MatchConvertCriteriaDTO();
		return(this.convertMatchToMatchDTO(match,convertCriteria));
	}







}
