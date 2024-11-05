package com.bullhornatsclone.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.bullhornatsclone.domain.Client;
import com.bullhornatsclone.dto.ClientDTO;
import com.bullhornatsclone.dto.ClientSearchDTO;
import com.bullhornatsclone.dto.ClientPageDTO;
import com.bullhornatsclone.dto.ClientConvertCriteriaDTO;
import com.bullhornatsclone.service.GenericService;
import com.bullhornatsclone.dto.common.RequestDTO;
import com.bullhornatsclone.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ClientService extends GenericService<Client, Integer> {

	List<Client> findAll();

	ResultDTO addClient(ClientDTO clientDTO, RequestDTO requestDTO);

	ResultDTO updateClient(ClientDTO clientDTO, RequestDTO requestDTO);

    Page<Client> getAllClients(Pageable pageable);

    Page<Client> getAllClients(Specification<Client> spec, Pageable pageable);

	ResponseEntity<ClientPageDTO> getClients(ClientSearchDTO clientSearchDTO);
	
	List<ClientDTO> convertClientsToClientDTOs(List<Client> clients, ClientConvertCriteriaDTO convertCriteria);

	ClientDTO getClientDTOById(Integer clientId);







}





