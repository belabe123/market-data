package it.bela.market.repository;

import org.springframework.data.repository.CrudRepository;

import it.bela.market.entity.MorningstarData;
import it.bela.market.entity.Symbol;

public interface MorningstarDataRepository extends CrudRepository<MorningstarData, Long> {

	MorningstarData findBySymbolAndRetrievedEntityStatus(Symbol symbol, String status);

}
