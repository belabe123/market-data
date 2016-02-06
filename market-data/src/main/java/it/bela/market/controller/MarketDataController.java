package it.bela.market.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import it.bela.market.service.BorsaItalianaDataService;
import it.bela.market.service.MorningstarDataService;
import it.bela.market.service.SymbolService;
import it.bela.market.service.YahooDataService;

@Controller
public class MarketDataController {

	private static Log logger = LogFactory.getLog(MarketDataController.class);
	
	@Autowired
	@Qualifier("symbolServiceYahooImpl")
	private SymbolService symbolService;
	
	@Autowired
	private YahooDataService yahooDataService;
	
	@Autowired
	private MorningstarDataService morningstarDataService;
	
	@Autowired
	private BorsaItalianaDataService borsaItalianaDataService;
	
	@RequestMapping("/refreshSymbolsList")
	@ResponseBody
	public String refreshSymbolList() {
		
		try {
			symbolService.refreshSymbolsList();
			
		} catch (Exception ex) {
			return "Error retrieving the symbols list: " + ex.toString();
		}
		
		logger.info("Symbols list retriever started.");
		
		return "Symbols list retriever started.";
	}
	
	@RequestMapping("/refreshYahooMarketData")
	@ResponseBody
	public String refreshYahooMarketData() {
		try {
			yahooDataService.refreshMarketData();
			
		} catch (Exception ex) {
			return "Error retrieving Yahoo Market Data: " + ex.toString();
		}
		
		logger.info("Yahoo Market Data retriever started.");
		return "Yahoo Market Data retriever started.";
	}
	
	@RequestMapping("/refreshMorningstarMarketData")
	@ResponseBody
	public String refreshMorningstarMarketData() {
		try {
			morningstarDataService.refreshMarketData();
			
		} catch (Exception ex) {
			return "Error retrieving Morningstar Market Data: " + ex.toString();
		}
		
		logger.info("Morningstar Market Data retriever started.");
		return "Morningstar Market Data retriever started.";
	}
	
	@RequestMapping("/refreshBorsaItalianaMarketData")
	@ResponseBody
	public String refreshBorsaItalianaMarketData() {
		try {
			borsaItalianaDataService.refreshMarketData();
			
		} catch (Exception ex) {
			return "Error retrieving BorsaItaliana Market Data: " + ex.toString();
		}
		
		logger.info("BorsaItaliana Market Data retriever started.");
		return "BorsaItaliana Market Data retriever started.";
	}
}
