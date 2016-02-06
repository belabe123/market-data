package it.bela.market.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.bela.market.entity.BorsaItalianaData;
import it.bela.market.entity.RetrieverExecution;
import it.bela.market.entity.Symbol;
import it.bela.market.repository.BorsaItalianaDataRepository;
import it.bela.market.utils.RetrievedEntityStatus;
import it.bela.market.utils.RetrieverBinder;
import it.bela.market.utils.RetrieverExecutorStatus;
import it.bela.market.utils.RetrieverExecutorType;

@Service
public class BorsaItalianaDataService {

	private static Log logger = LogFactory.getLog(BorsaItalianaDataService.class);
	
	@Autowired
	private BorsaItalianaDataRepository borsaItalianaDataRepository;
	
	@Autowired
	@Qualifier("symbolServiceImpl")
	private SymbolService symbolService;
	
	@Autowired
	private RetrieverExeutionService retrieverExeutionService;
	
	@Autowired
	private RetrieverBinder<BorsaItalianaData> retrieverBinder;
	
	@Async
	public void refreshMarketData() {
		RetrieverExecution retrieverExecution = new RetrieverExecution(new Date(), RetrieverExecutorType.BORSA_ITALIANA_DATA, RetrieverExecutorStatus.STARTED);
		retrieverExeutionService.save(retrieverExecution);

		try {
			List<Symbol> symbols = symbolService.findByRetrievedEntityStatus(RetrievedEntityStatus.FRESH.toString());
			for (Symbol symbol : symbols) {
				refreshMarketData(symbol, retrieverExecution);
			}
			
			retrieverExecution.setStatus(RetrieverExecutorStatus.COMPLETED.toString());
			
		} catch (Exception e){
			logger.error("Error retrivig BorsaItaliana Data.", e);
			retrieverExecution.setStatus(RetrieverExecutorStatus.ERROR.toString());
			retrieverExecution.setErrorMessage(e.toString() + " - " + e.getMessage());
		}

		retrieverExecution.setEndExecution(new Date());
		retrieverExeutionService.save(retrieverExecution);
	}
	
	@Transactional
	private void refreshMarketData(Symbol symbol, RetrieverExecution retrieverExecution) throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException {
		
		BorsaItalianaData borsaItalianaDataOld = findBySymbolAndRetrievedEntityStatus(symbol, RetrievedEntityStatus.FRESH.toString());
		if (borsaItalianaDataOld != null){
			borsaItalianaDataOld.setRetrievedEntityStatus(RetrievedEntityStatus.OLD.toString());
			saveBorsaItalianaData(borsaItalianaDataOld);
		}
		
		BorsaItalianaData borsaItalianaData = retrieverBinder.parse(BorsaItalianaData.class, Parser.htmlParser(), symbol.getIsin());
		
		borsaItalianaData.setSymbol(symbol);
		borsaItalianaData.setRetrieverExecution(retrieverExecution);
		borsaItalianaData.setRetrievedEntityStatus(RetrievedEntityStatus.FRESH.toString());
		saveBorsaItalianaData(borsaItalianaData);
		
	}

	public BorsaItalianaData findBySymbolAndRetrievedEntityStatus(Symbol symbol, String status) {
		return borsaItalianaDataRepository.findBySymbolAndRetrievedEntityStatus(symbol, status);
	}

	public void saveBorsaItalianaData(BorsaItalianaData BorsaItalianaData) {
		borsaItalianaDataRepository.save(BorsaItalianaData);		
	}
}
