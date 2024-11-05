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

import com.bullhornatsclone.domain.Match;
import com.bullhornatsclone.dto.MatchDTO;
import com.bullhornatsclone.dto.MatchSearchDTO;
import com.bullhornatsclone.dto.MatchPageDTO;
import com.bullhornatsclone.service.MatchService;
import com.bullhornatsclone.dto.common.RequestDTO;
import com.bullhornatsclone.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/match")
@RestController
public class MatchController {

	private final static Logger logger = LoggerFactory.getLogger(MatchController.class);

	@Autowired
	MatchService matchService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Match> getAll() {

		List<Match> matchs = matchService.findAll();
		
		return matchs;	
	}

	@GetMapping(value = "/{matchId}")
	@ResponseBody
	public MatchDTO getMatch(@PathVariable Integer matchId) {
		
		return (matchService.getMatchDTOById(matchId));
	}

 	@RequestMapping(value = "/addMatch", method = RequestMethod.POST)
	public ResponseEntity<?> addMatch(@RequestBody MatchDTO matchDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = matchService.addMatch(matchDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/matchs")
	public ResponseEntity<MatchPageDTO> getMatchs(MatchSearchDTO matchSearchDTO) {
 
		return matchService.getMatchs(matchSearchDTO);
	}	

	@RequestMapping(value = "/updateMatch", method = RequestMethod.POST)
	public ResponseEntity<?> updateMatch(@RequestBody MatchDTO matchDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = matchService.updateMatch(matchDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
