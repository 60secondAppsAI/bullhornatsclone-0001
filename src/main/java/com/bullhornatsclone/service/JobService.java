package com.bullhornatsclone.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.bullhornatsclone.domain.Job;
import com.bullhornatsclone.dto.JobDTO;
import com.bullhornatsclone.dto.JobSearchDTO;
import com.bullhornatsclone.dto.JobPageDTO;
import com.bullhornatsclone.dto.JobConvertCriteriaDTO;
import com.bullhornatsclone.service.GenericService;
import com.bullhornatsclone.dto.common.RequestDTO;
import com.bullhornatsclone.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface JobService extends GenericService<Job, Integer> {

	List<Job> findAll();

	ResultDTO addJob(JobDTO jobDTO, RequestDTO requestDTO);

	ResultDTO updateJob(JobDTO jobDTO, RequestDTO requestDTO);

    Page<Job> getAllJobs(Pageable pageable);

    Page<Job> getAllJobs(Specification<Job> spec, Pageable pageable);

	ResponseEntity<JobPageDTO> getJobs(JobSearchDTO jobSearchDTO);
	
	List<JobDTO> convertJobsToJobDTOs(List<Job> jobs, JobConvertCriteriaDTO convertCriteria);

	JobDTO getJobDTOById(Integer jobId);







}





