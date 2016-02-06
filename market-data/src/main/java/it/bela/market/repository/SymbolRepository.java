package it.bela.market.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.bela.market.entity.Symbol;

public interface SymbolRepository extends CrudRepository<Symbol, Long> {

	List<Symbol> findByRetrievedEntityStatus(String status);

}
