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
import com.bullhornatsclone.dao.JobDAO;
import com.bullhornatsclone.domain.Job;
import com.bullhornatsclone.dto.JobDTO;
import com.bullhornatsclone.dto.JobSearchDTO;
import com.bullhornatsclone.dto.JobPageDTO;
import com.bullhornatsclone.dto.JobConvertCriteriaDTO;
import com.bullhornatsclone.dto.common.RequestDTO;
import com.bullhornatsclone.dto.common.ResultDTO;
import com.bullhornatsclone.service.JobService;
import com.bullhornatsclone.util.ControllerUtils;





@Service
public class JobServiceImpl extends GenericServiceImpl<Job, Integer> implements JobService {

    private final static Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);

	@Autowired
	JobDAO jobDao;

	


	@Override
	public GenericDAO<Job, Integer> getDAO() {
		return (GenericDAO<Job, Integer>) jobDao;
	}
	
	public List<Job> findAll () {
		List<Job> jobs = jobDao.findAll();
		
		return jobs;	
		
	}

	public ResultDTO addJob(JobDTO jobDTO, RequestDTO requestDTO) {

		Job job = new Job();

		job.setJobId(jobDTO.getJobId());


		job.setTitle(jobDTO.getTitle());


		job.setDescription(jobDTO.getDescription());


		job.setLocation(jobDTO.getLocation());


		job.setSalary(jobDTO.getSalary());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		job = jobDao.save(job);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Job> getAllJobs(Pageable pageable) {
		return jobDao.findAll(pageable);
	}

	public Page<Job> getAllJobs(Specification<Job> spec, Pageable pageable) {
		return jobDao.findAll(spec, pageable);
	}

	public ResponseEntity<JobPageDTO> getJobs(JobSearchDTO jobSearchDTO) {
	
			Integer jobId = jobSearchDTO.getJobId(); 
 			String title = jobSearchDTO.getTitle(); 
 			String description = jobSearchDTO.getDescription(); 
 			String location = jobSearchDTO.getLocation(); 
  			String sortBy = jobSearchDTO.getSortBy();
			String sortOrder = jobSearchDTO.getSortOrder();
			String searchQuery = jobSearchDTO.getSearchQuery();
			Integer page = jobSearchDTO.getPage();
			Integer size = jobSearchDTO.getSize();

	        Specification<Job> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, jobId, "jobId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, title, "title"); 
			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			
			spec = ControllerUtils.andIfNecessary(spec, location, "location"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("title")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("location")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Job> jobs = this.getAllJobs(spec, pageable);
		
		//System.out.println(String.valueOf(jobs.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(jobs.getTotalPages()));
		
		List<Job> jobsList = jobs.getContent();
		
		JobConvertCriteriaDTO convertCriteria = new JobConvertCriteriaDTO();
		List<JobDTO> jobDTOs = this.convertJobsToJobDTOs(jobsList,convertCriteria);
		
		JobPageDTO jobPageDTO = new JobPageDTO();
		jobPageDTO.setJobs(jobDTOs);
		jobPageDTO.setTotalElements(jobs.getTotalElements());
		return ResponseEntity.ok(jobPageDTO);
	}

	public List<JobDTO> convertJobsToJobDTOs(List<Job> jobs, JobConvertCriteriaDTO convertCriteria) {
		
		List<JobDTO> jobDTOs = new ArrayList<JobDTO>();
		
		for (Job job : jobs) {
			jobDTOs.add(convertJobToJobDTO(job,convertCriteria));
		}
		
		return jobDTOs;

	}
	
	public JobDTO convertJobToJobDTO(Job job, JobConvertCriteriaDTO convertCriteria) {
		
		JobDTO jobDTO = new JobDTO();
		
		jobDTO.setJobId(job.getJobId());

	
		jobDTO.setTitle(job.getTitle());

	
		jobDTO.setDescription(job.getDescription());

	
		jobDTO.setLocation(job.getLocation());

	
		jobDTO.setSalary(job.getSalary());

	

		
		return jobDTO;
	}

	public ResultDTO updateJob(JobDTO jobDTO, RequestDTO requestDTO) {
		
		Job job = jobDao.getById(jobDTO.getJobId());

		job.setJobId(ControllerUtils.setValue(job.getJobId(), jobDTO.getJobId()));

		job.setTitle(ControllerUtils.setValue(job.getTitle(), jobDTO.getTitle()));

		job.setDescription(ControllerUtils.setValue(job.getDescription(), jobDTO.getDescription()));

		job.setLocation(ControllerUtils.setValue(job.getLocation(), jobDTO.getLocation()));

		job.setSalary(ControllerUtils.setValue(job.getSalary(), jobDTO.getSalary()));



        job = jobDao.save(job);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public JobDTO getJobDTOById(Integer jobId) {
	
		Job job = jobDao.getById(jobId);
			
		
		JobConvertCriteriaDTO convertCriteria = new JobConvertCriteriaDTO();
		return(this.convertJobToJobDTO(job,convertCriteria));
	}







}
