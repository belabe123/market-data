package it.bela.market.repository;

import org.springframework.data.repository.CrudRepository;

import it.bela.market.entity.BorsaItalianaData;
import it.bela.market.entity.Symbol;

public interface BorsaItalianaDataRepository extends CrudRepository<BorsaItalianaData, Long> {

	BorsaItalianaData findBySymbolAndRetrievedEntityStatus(Symbol symbol, String status);

}
