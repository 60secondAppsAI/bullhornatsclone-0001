package com.bullhornatsclone.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.bullhornatsclone.domain.Candidate;
import com.bullhornatsclone.dto.CandidateDTO;
import com.bullhornatsclone.dto.CandidateSearchDTO;
import com.bullhornatsclone.dto.CandidatePageDTO;
import com.bullhornatsclone.dto.CandidateConvertCriteriaDTO;
import com.bullhornatsclone.service.GenericService;
import com.bullhornatsclone.dto.common.RequestDTO;
import com.bullhornatsclone.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CandidateService extends GenericService<Candidate, Integer> {

	List<Candidate> findAll();

	ResultDTO addCandidate(CandidateDTO candidateDTO, RequestDTO requestDTO);

	ResultDTO updateCandidate(CandidateDTO candidateDTO, RequestDTO requestDTO);

    Page<Candidate> getAllCandidates(Pageable pageable);

    Page<Candidate> getAllCandidates(Specification<Candidate> spec, Pageable pageable);

	ResponseEntity<CandidatePageDTO> getCandidates(CandidateSearchDTO candidateSearchDTO);
	
	List<CandidateDTO> convertCandidatesToCandidateDTOs(List<Candidate> candidates, CandidateConvertCriteriaDTO convertCriteria);

	CandidateDTO getCandidateDTOById(Integer candidateId);







}





