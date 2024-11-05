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
import com.bullhornatsclone.dao.CandidateDAO;
import com.bullhornatsclone.domain.Candidate;
import com.bullhornatsclone.dto.CandidateDTO;
import com.bullhornatsclone.dto.CandidateSearchDTO;
import com.bullhornatsclone.dto.CandidatePageDTO;
import com.bullhornatsclone.dto.CandidateConvertCriteriaDTO;
import com.bullhornatsclone.dto.common.RequestDTO;
import com.bullhornatsclone.dto.common.ResultDTO;
import com.bullhornatsclone.service.CandidateService;
import com.bullhornatsclone.util.ControllerUtils;





@Service
public class CandidateServiceImpl extends GenericServiceImpl<Candidate, Integer> implements CandidateService {

    private final static Logger logger = LoggerFactory.getLogger(CandidateServiceImpl.class);

	@Autowired
	CandidateDAO candidateDao;

	


	@Override
	public GenericDAO<Candidate, Integer> getDAO() {
		return (GenericDAO<Candidate, Integer>) candidateDao;
	}
	
	public List<Candidate> findAll () {
		List<Candidate> candidates = candidateDao.findAll();
		
		return candidates;	
		
	}

	public ResultDTO addCandidate(CandidateDTO candidateDTO, RequestDTO requestDTO) {

		Candidate candidate = new Candidate();

		candidate.setCandidateId(candidateDTO.getCandidateId());


		candidate.setName(candidateDTO.getName());


		candidate.setEmail(candidateDTO.getEmail());


		candidate.setPhone(candidateDTO.getPhone());


		candidate.setSkills(candidateDTO.getSkills());


		candidate.setResume(candidateDTO.getResume());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		candidate = candidateDao.save(candidate);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Candidate> getAllCandidates(Pageable pageable) {
		return candidateDao.findAll(pageable);
	}

	public Page<Candidate> getAllCandidates(Specification<Candidate> spec, Pageable pageable) {
		return candidateDao.findAll(spec, pageable);
	}

	public ResponseEntity<CandidatePageDTO> getCandidates(CandidateSearchDTO candidateSearchDTO) {
	
			Integer candidateId = candidateSearchDTO.getCandidateId(); 
 			String name = candidateSearchDTO.getName(); 
 			String email = candidateSearchDTO.getEmail(); 
 			String phone = candidateSearchDTO.getPhone(); 
 			String skills = candidateSearchDTO.getSkills(); 
 			String resume = candidateSearchDTO.getResume(); 
 			String sortBy = candidateSearchDTO.getSortBy();
			String sortOrder = candidateSearchDTO.getSortOrder();
			String searchQuery = candidateSearchDTO.getSearchQuery();
			Integer page = candidateSearchDTO.getPage();
			Integer size = candidateSearchDTO.getSize();

	        Specification<Candidate> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, candidateId, "candidateId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, email, "email"); 
			
			spec = ControllerUtils.andIfNecessary(spec, phone, "phone"); 
			
			spec = ControllerUtils.andIfNecessary(spec, skills, "skills"); 
			
			spec = ControllerUtils.andIfNecessary(spec, resume, "resume"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("email")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("phone")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("skills")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("resume")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Candidate> candidates = this.getAllCandidates(spec, pageable);
		
		//System.out.println(String.valueOf(candidates.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(candidates.getTotalPages()));
		
		List<Candidate> candidatesList = candidates.getContent();
		
		CandidateConvertCriteriaDTO convertCriteria = new CandidateConvertCriteriaDTO();
		List<CandidateDTO> candidateDTOs = this.convertCandidatesToCandidateDTOs(candidatesList,convertCriteria);
		
		CandidatePageDTO candidatePageDTO = new CandidatePageDTO();
		candidatePageDTO.setCandidates(candidateDTOs);
		candidatePageDTO.setTotalElements(candidates.getTotalElements());
		return ResponseEntity.ok(candidatePageDTO);
	}

	public List<CandidateDTO> convertCandidatesToCandidateDTOs(List<Candidate> candidates, CandidateConvertCriteriaDTO convertCriteria) {
		
		List<CandidateDTO> candidateDTOs = new ArrayList<CandidateDTO>();
		
		for (Candidate candidate : candidates) {
			candidateDTOs.add(convertCandidateToCandidateDTO(candidate,convertCriteria));
		}
		
		return candidateDTOs;

	}
	
	public CandidateDTO convertCandidateToCandidateDTO(Candidate candidate, CandidateConvertCriteriaDTO convertCriteria) {
		
		CandidateDTO candidateDTO = new CandidateDTO();
		
		candidateDTO.setCandidateId(candidate.getCandidateId());

	
		candidateDTO.setName(candidate.getName());

	
		candidateDTO.setEmail(candidate.getEmail());

	
		candidateDTO.setPhone(candidate.getPhone());

	
		candidateDTO.setSkills(candidate.getSkills());

	
		candidateDTO.setResume(candidate.getResume());

	

		
		return candidateDTO;
	}

	public ResultDTO updateCandidate(CandidateDTO candidateDTO, RequestDTO requestDTO) {
		
		Candidate candidate = candidateDao.getById(candidateDTO.getCandidateId());

		candidate.setCandidateId(ControllerUtils.setValue(candidate.getCandidateId(), candidateDTO.getCandidateId()));

		candidate.setName(ControllerUtils.setValue(candidate.getName(), candidateDTO.getName()));

		candidate.setEmail(ControllerUtils.setValue(candidate.getEmail(), candidateDTO.getEmail()));

		candidate.setPhone(ControllerUtils.setValue(candidate.getPhone(), candidateDTO.getPhone()));

		candidate.setSkills(ControllerUtils.setValue(candidate.getSkills(), candidateDTO.getSkills()));

		candidate.setResume(ControllerUtils.setValue(candidate.getResume(), candidateDTO.getResume()));



        candidate = candidateDao.save(candidate);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public CandidateDTO getCandidateDTOById(Integer candidateId) {
	
		Candidate candidate = candidateDao.getById(candidateId);
			
		
		CandidateConvertCriteriaDTO convertCriteria = new CandidateConvertCriteriaDTO();
		return(this.convertCandidateToCandidateDTO(candidate,convertCriteria));
	}







}
