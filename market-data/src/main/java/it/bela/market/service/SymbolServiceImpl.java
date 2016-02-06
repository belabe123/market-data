package it.bela.market.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.bela.market.entity.Symbol;
import it.bela.market.repository.SymbolRepository;

@Service("symbolServiceImpl")
public class SymbolServiceImpl implements SymbolService {

	@Autowired
	private SymbolRepository symbolRepository;
	
	@Override
	@Transactional
	public void saveSymbolList(List<Symbol> symbols) {		
		symbolRepository.save(symbols);
	}

	@Override
	public void refreshSymbolsList() {
		throw new UnsupportedOperationException("Non Implementato");
	}

	@Override
	public List<Symbol> findByRetrievedEntityStatus(String status) {
		return symbolRepository.findByRetrievedEntityStatus(status);
	}

}
