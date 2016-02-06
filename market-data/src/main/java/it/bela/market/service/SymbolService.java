package it.bela.market.service;

import java.util.List;

import it.bela.market.entity.Symbol;

public interface SymbolService {
	void saveSymbolList(List<Symbol> symbols);
	void refreshSymbolsList();
	
	List<Symbol> findByRetrievedEntityStatus(String string);
}
