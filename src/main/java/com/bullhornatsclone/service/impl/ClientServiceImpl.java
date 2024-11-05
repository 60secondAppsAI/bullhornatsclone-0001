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
import com.bullhornatsclone.dao.ClientDAO;
import com.bullhornatsclone.domain.Client;
import com.bullhornatsclone.dto.ClientDTO;
import com.bullhornatsclone.dto.ClientSearchDTO;
import com.bullhornatsclone.dto.ClientPageDTO;
import com.bullhornatsclone.dto.ClientConvertCriteriaDTO;
import com.bullhornatsclone.dto.common.RequestDTO;
import com.bullhornatsclone.dto.common.ResultDTO;
import com.bullhornatsclone.service.ClientService;
import com.bullhornatsclone.util.ControllerUtils;





@Service
public class ClientServiceImpl extends GenericServiceImpl<Client, Integer> implements ClientService {

    private final static Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

	@Autowired
	ClientDAO clientDao;

	


	@Override
	public GenericDAO<Client, Integer> getDAO() {
		return (GenericDAO<Client, Integer>) clientDao;
	}
	
	public List<Client> findAll () {
		List<Client> clients = clientDao.findAll();
		
		return clients;	
		
	}

	public ResultDTO addClient(ClientDTO clientDTO, RequestDTO requestDTO) {

		Client client = new Client();

		client.setClientId(clientDTO.getClientId());


		client.setName(clientDTO.getName());


		client.setContactEmail(clientDTO.getContactEmail());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		client = clientDao.save(client);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Client> getAllClients(Pageable pageable) {
		return clientDao.findAll(pageable);
	}

	public Page<Client> getAllClients(Specification<Client> spec, Pageable pageable) {
		return clientDao.findAll(spec, pageable);
	}

	public ResponseEntity<ClientPageDTO> getClients(ClientSearchDTO clientSearchDTO) {
	
			Integer clientId = clientSearchDTO.getClientId(); 
 			String name = clientSearchDTO.getName(); 
 			String contactEmail = clientSearchDTO.getContactEmail(); 
 			String sortBy = clientSearchDTO.getSortBy();
			String sortOrder = clientSearchDTO.getSortOrder();
			String searchQuery = clientSearchDTO.getSearchQuery();
			Integer page = clientSearchDTO.getPage();
			Integer size = clientSearchDTO.getSize();

	        Specification<Client> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, clientId, "clientId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, contactEmail, "contactEmail"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("contactEmail")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Client> clients = this.getAllClients(spec, pageable);
		
		//System.out.println(String.valueOf(clients.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(clients.getTotalPages()));
		
		List<Client> clientsList = clients.getContent();
		
		ClientConvertCriteriaDTO convertCriteria = new ClientConvertCriteriaDTO();
		List<ClientDTO> clientDTOs = this.convertClientsToClientDTOs(clientsList,convertCriteria);
		
		ClientPageDTO clientPageDTO = new ClientPageDTO();
		clientPageDTO.setClients(clientDTOs);
		clientPageDTO.setTotalElements(clients.getTotalElements());
		return ResponseEntity.ok(clientPageDTO);
	}

	public List<ClientDTO> convertClientsToClientDTOs(List<Client> clients, ClientConvertCriteriaDTO convertCriteria) {
		
		List<ClientDTO> clientDTOs = new ArrayList<ClientDTO>();
		
		for (Client client : clients) {
			clientDTOs.add(convertClientToClientDTO(client,convertCriteria));
		}
		
		return clientDTOs;

	}
	
	public ClientDTO convertClientToClientDTO(Client client, ClientConvertCriteriaDTO convertCriteria) {
		
		ClientDTO clientDTO = new ClientDTO();
		
		clientDTO.setClientId(client.getClientId());

	
		clientDTO.setName(client.getName());

	
		clientDTO.setContactEmail(client.getContactEmail());

	

		
		return clientDTO;
	}

	public ResultDTO updateClient(ClientDTO clientDTO, RequestDTO requestDTO) {
		
		Client client = clientDao.getById(clientDTO.getClientId());

		client.setClientId(ControllerUtils.setValue(client.getClientId(), clientDTO.getClientId()));

		client.setName(ControllerUtils.setValue(client.getName(), clientDTO.getName()));

		client.setContactEmail(ControllerUtils.setValue(client.getContactEmail(), clientDTO.getContactEmail()));



        client = clientDao.save(client);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ClientDTO getClientDTOById(Integer clientId) {
	
		Client client = clientDao.getById(clientId);
			
		
		ClientConvertCriteriaDTO convertCriteria = new ClientConvertCriteriaDTO();
		return(this.convertClientToClientDTO(client,convertCriteria));
	}







}
