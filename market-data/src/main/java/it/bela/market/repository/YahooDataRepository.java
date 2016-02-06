package it.bela.market.repository;

import org.springframework.data.repository.CrudRepository;

import it.bela.market.entity.Symbol;
import it.bela.market.entity.YahooData;

public interface YahooDataRepository extends CrudRepository<YahooData, Long> {

	YahooData findBySymbolAndRetrievedEntityStatus(Symbol symbol, String status);

}
