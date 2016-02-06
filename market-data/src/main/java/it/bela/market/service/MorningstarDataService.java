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

import it.bela.market.entity.MorningstarData;
import it.bela.market.entity.RetrieverExecution;
import it.bela.market.entity.Symbol;
import it.bela.market.repository.MorningstarDataRepository;
import it.bela.market.utils.RetrievedEntityStatus;
import it.bela.market.utils.RetrieverBinder;
import it.bela.market.utils.RetrieverExecutorStatus;
import it.bela.market.utils.RetrieverExecutorType;

@Service
public class MorningstarDataService {

	private static Log logger = LogFactory.getLog(MorningstarDataService.class);
	
	@Autowired
	private MorningstarDataRepository morningstarDataRepository;
	
	@Autowired
	@Qualifier("symbolServiceImpl")
	private SymbolService symbolService;
	
	@Autowired
	private RetrieverExeutionService retrieverExeutionService;
	
	@Autowired
	private RetrieverBinder<MorningstarData> retrieverBinder;
	
	public void saveMorningstarData(MorningstarData morningstarData) {
		morningstarDataRepository.save(morningstarData);		
	}
	
	@Async
	public void refreshMarketData() {
		RetrieverExecution retrieverExecution = new RetrieverExecution(new Date(), RetrieverExecutorType.MORNINGSTAR_DATA, RetrieverExecutorStatus.STARTED);
		retrieverExeutionService.save(retrieverExecution);

		try {
			List<Symbol> symbols = symbolService.findByRetrievedEntityStatus(RetrievedEntityStatus.FRESH.toString());
			for (Symbol symbol : symbols) {
				refreshMarketData(symbol, retrieverExecution);
			}
			
			retrieverExecution.setStatus(RetrieverExecutorStatus.COMPLETED.toString());
			
		} catch (Exception e){
			logger.error("Error retrivig Morningstar Data.", e);
			retrieverExecution.setStatus(RetrieverExecutorStatus.ERROR.toString());
			retrieverExecution.setErrorMessage(e.getMessage());
		}

		retrieverExecution.setEndExecution(new Date());
		retrieverExeutionService.save(retrieverExecution);
	}

	@Transactional
	private void refreshMarketData(Symbol symbol, RetrieverExecution retrieverExecution)
			throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, ParseException {
		
		MorningstarData morningstarDataOld = findBySymbolAndRetrievedEntityStatus(symbol, RetrievedEntityStatus.FRESH.toString());
		if (morningstarDataOld != null){
			morningstarDataOld.setRetrievedEntityStatus(RetrievedEntityStatus.OLD.toString());
			saveMorningstarData(morningstarDataOld);
		}
		
		MorningstarData morningstarData = retrieverBinder.parse(MorningstarData.class, Parser.htmlParser(), symbol.getCode());
		
		morningstarData.setSymbol(symbol);
		morningstarData.setRetrieverExecution(retrieverExecution);
		morningstarData.setRetrievedEntityStatus(RetrievedEntityStatus.FRESH.toString());
		saveMorningstarData(morningstarData);
		
	}

	private MorningstarData findBySymbolAndRetrievedEntityStatus(Symbol symbol, String status) {
		return morningstarDataRepository.findBySymbolAndRetrievedEntityStatus(symbol, status);
	}
}
