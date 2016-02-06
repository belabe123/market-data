package it.bela.market.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.bela.market.bean.SymbolYahoo;
import it.bela.market.entity.BorsaItalianaData;
import it.bela.market.entity.RetrieverExecution;
import it.bela.market.entity.Symbol;
import it.bela.market.repository.SymbolRepository;
import it.bela.market.utils.RetrievedEntityStatus;
import it.bela.market.utils.RetrieverBinder;
import it.bela.market.utils.RetrieverExecutorStatus;
import it.bela.market.utils.RetrieverExecutorType;

@Service("symbolServiceYahooImpl")
public class SymbolServiceYahooImpl extends SymbolServiceImpl {

	private static Log logger = LogFactory.getLog(SymbolServiceYahooImpl.class);
	
	@Value("${symbolServiceYahooImpl.baseUrl}") 
	private String baseUrl;
	@Value("${symbolServiceYahooImpl.resultsSelector}") 
	private String resultsSelector;
	@Value("${symbolServiceYahooImpl.nextPageLinkSelector}") 
	private String nextPageLinkSelector;
	@Value("${symbolServiceYahooImpl.urlRoot}") 
	private String urlRoot;

	@Autowired
	private RetrieverExeutionService retrieverExeutionService;
	
	@Autowired
	private HttpService httpService;
	
	@Autowired
	private SymbolRepository symbolRepository;
	
	@Autowired
	private RetrieverBinder<SymbolYahoo> retrieverBinder;

	@Async
	public void refreshSymbolsList() {

		RetrieverExecution retrieverExecution = new RetrieverExecution(new Date(), RetrieverExecutorType.SYMBOL, RetrieverExecutorStatus.STARTED);
		retrieverExeutionService.save(retrieverExecution);

		try {
			
			refreshSymbolsList(retrieverExecution);
			retrieverExecution.setStatus(RetrieverExecutorStatus.COMPLETED.toString());
			
		} catch (Exception e){
			logger.error("Error retrivig symbol list.", e);
			retrieverExecution.setStatus(RetrieverExecutorStatus.ERROR.toString());
		}

		retrieverExecution.setEndExecution(new Date());
		retrieverExeutionService.save(retrieverExecution);

	}

	@Transactional
	private void refreshSymbolsList(RetrieverExecution retrieverExecution) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException, ParseException {
		List<Symbol> oldSymbols = findByRetrievedEntityStatus(RetrievedEntityStatus.FRESH.toString());
		for (Symbol oldSymbol : oldSymbols) {
			oldSymbol.setRetrievedEntityStatus(RetrievedEntityStatus.OLD.toString());
		}
		saveSymbolList(oldSymbols);
		
		List<Symbol> symbols = retrieveSymbolList(baseUrl);
		
		for (Symbol symbol : symbols) {
			symbol.setRetrieverExecution(retrieverExecution);
			symbol.setRetrievedEntityStatus(RetrievedEntityStatus.FRESH.toString());
		}
		
		saveSymbolList(symbols);
		
	}
	
	@Transactional
	public void saveSymbolList(List<Symbol> symbols) {
		
		List<Symbol> symbolsNew = new ArrayList<Symbol>();
		
		for (Symbol symbol : symbols) {
			Symbol symbolNew = new Symbol();
			BeanUtils.copyProperties(symbol, symbolNew);
			symbolsNew.add(symbolNew);
		}
		
		symbolRepository.save(symbolsNew);
		
	}

	private List<Symbol> retrieveSymbolList(String pageUrl) throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException {

		String documentString = httpService.get(pageUrl);
		Document document = Jsoup.parse(documentString);
		List<Symbol> symbolsPageResults = getSymbolsPageResults(document);

		String nextPageUrl = getNextPageUrl(document);

		//TODO da decommentare
		if (nextPageUrl != null && !nextPageUrl.equals("")){
			List<Symbol> nextPageSymbols = retrieveSymbolList(nextPageUrl);
			symbolsPageResults.addAll(nextPageSymbols);
		}

		return symbolsPageResults;
	}

	private String getNextPageUrl(Document document){

		String nextPageUrl = null;
		Elements elements = document.getElementsContainingOwnText(nextPageLinkSelector);

		if (elements != null && !elements.isEmpty()){
			nextPageUrl = elements.get(0).attr("href");
		}
		
		if (nextPageUrl != null && !nextPageUrl.equals("") && nextPageUrl.startsWith("/")){
			nextPageUrl = urlRoot + nextPageUrl;
		}

		return nextPageUrl;
	}

	private List<Symbol> getSymbolsPageResults(Document document) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException {

		List<Symbol> symbols = new ArrayList<Symbol>();

		Elements tableResults = document.select(resultsSelector);
		Elements results = tableResults.get(0).children();

		if (results != null && !results.isEmpty()){
			for (Element result : results) {
				List<Element> elements = new ArrayList<Element>();
				elements.add(result);
				Symbol symbol = retrieverBinder.parse(elements, SymbolYahoo.class);
				symbols.add(symbol);
			}
		}

		return symbols;
	}

}
