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

import com.bullhornatsclone.domain.Client;
import com.bullhornatsclone.dto.ClientDTO;
import com.bullhornatsclone.dto.ClientSearchDTO;
import com.bullhornatsclone.dto.ClientPageDTO;
import com.bullhornatsclone.service.ClientService;
import com.bullhornatsclone.dto.common.RequestDTO;
import com.bullhornatsclone.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/client")
@RestController
public class ClientController {

	private final static Logger logger = LoggerFactory.getLogger(ClientController.class);

	@Autowired
	ClientService clientService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Client> getAll() {

		List<Client> clients = clientService.findAll();
		
		return clients;	
	}

	@GetMapping(value = "/{clientId}")
	@ResponseBody
	public ClientDTO getClient(@PathVariable Integer clientId) {
		
		return (clientService.getClientDTOById(clientId));
	}

 	@RequestMapping(value = "/addClient", method = RequestMethod.POST)
	public ResponseEntity<?> addClient(@RequestBody ClientDTO clientDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = clientService.addClient(clientDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/clients")
	public ResponseEntity<ClientPageDTO> getClients(ClientSearchDTO clientSearchDTO) {
 
		return clientService.getClients(clientSearchDTO);
	}	

	@RequestMapping(value = "/updateClient", method = RequestMethod.POST)
	public ResponseEntity<?> updateClient(@RequestBody ClientDTO clientDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = clientService.updateClient(clientDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
