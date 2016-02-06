package it.bela.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.bela.market.entity.RetrieverExecution;
import it.bela.market.repository.RetrieverExecutionRepository;

@Service
public class RetrieverExeutionService {

	@Autowired
	private RetrieverExecutionRepository retrieverExecutionRepository;
	
	public void save(RetrieverExecution retrieverExecution ) {
		retrieverExecutionRepository.save(retrieverExecution);
	}
}
