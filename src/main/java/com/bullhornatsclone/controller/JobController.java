package com.bullhornatsclone.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.bullhornatsclone.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.bullhornatsclone.domain.Job;
import com.bullhornatsclone.dto.JobDTO;
import com.bullhornatsclone.dto.JobSearchDTO;
import com.bullhornatsclone.dto.JobPageDTO;
import com.bullhornatsclone.service.JobService;
import com.bullhornatsclone.dto.common.RequestDTO;
import com.bullhornatsclone.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/job")
@RestController
public class JobController {

	private final static Logger logger = LoggerFactory.getLogger(JobController.class);

	@Autowired
	JobService jobService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Job> getAll() {

		List<Job> jobs = jobService.findAll();
		
		return jobs;	
	}

	@GetMapping(value = "/{jobId}")
	@ResponseBody
	public JobDTO getJob(@PathVariable Integer jobId) {
		
		return (jobService.getJobDTOById(jobId));
	}

 	@RequestMapping(value = "/addJob", method = RequestMethod.POST)
	public ResponseEntity<?> addJob(@RequestBody JobDTO jobDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = jobService.addJob(jobDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/jobs")
	public ResponseEntity<JobPageDTO> getJobs(JobSearchDTO jobSearchDTO) {
 
		return jobService.getJobs(jobSearchDTO);
	}	

	@RequestMapping(value = "/updateJob", method = RequestMethod.POST)
	public ResponseEntity<?> updateJob(@RequestBody JobDTO jobDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = jobService.updateJob(jobDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
