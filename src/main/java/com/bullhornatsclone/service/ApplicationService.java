package com.bullhornatsclone.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.bullhornatsclone.domain.Application;
import com.bullhornatsclone.dto.ApplicationDTO;
import com.bullhornatsclone.dto.ApplicationSearchDTO;
import com.bullhornatsclone.dto.ApplicationPageDTO;
import com.bullhornatsclone.dto.ApplicationConvertCriteriaDTO;
import com.bullhornatsclone.service.GenericService;
import com.bullhornatsclone.dto.common.RequestDTO;
import com.bullhornatsclone.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ApplicationService extends GenericService<Application, Integer> {

	List<Application> findAll();

	ResultDTO addApplication(ApplicationDTO applicationDTO, RequestDTO requestDTO);

	ResultDTO updateApplication(ApplicationDTO applicationDTO, RequestDTO requestDTO);

    Page<Application> getAllApplications(Pageable pageable);

    Page<Application> getAllApplications(Specification<Application> spec, Pageable pageable);

	ResponseEntity<ApplicationPageDTO> getApplications(ApplicationSearchDTO applicationSearchDTO);
	
	List<ApplicationDTO> convertApplicationsToApplicationDTOs(List<Application> applications, ApplicationConvertCriteriaDTO convertCriteria);

	ApplicationDTO getApplicationDTOById(Integer applicationId);







}





