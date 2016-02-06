package it.bela.market.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import it.bela.market.entity.RetrieverExecution;
import it.bela.market.entity.Symbol;
import it.bela.market.entity.YahooData;
import it.bela.market.repository.YahooDataRepository;
import it.bela.market.utils.RetrievedEntityStatus;
import it.bela.market.utils.RetrieverBinder;
import it.bela.market.utils.RetrieverExecutorStatus;
import it.bela.market.utils.RetrieverExecutorType;

@Service("yahooDataService")
public class YahooDataService {

	private static Log logger = LogFactory.getLog(YahooDataService.class); 
	
	@Autowired
	private RetrieverExeutionService retrieverExeutionService;
	
	@Autowired
	private YahooDataRepository yahooDataRepository;
	
	@Autowired
	@Qualifier("symbolServiceImpl")
	private SymbolService symbolService;
	
	@Autowired
	private RetrieverBinder<YahooData> retrieverBinder;
	
	@Async
	public void refreshMarketData() {
		RetrieverExecution retrieverExecution = new RetrieverExecution(new Date(), RetrieverExecutorType.YAHOO_DATA, RetrieverExecutorStatus.STARTED);
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
			retrieverExecution.setErrorMessage(e.getMessage());
		}

		retrieverExecution.setEndExecution(new Date());
		retrieverExeutionService.save(retrieverExecution);
	}

	@Transactional
	private void refreshMarketData(Symbol symbol, RetrieverExecution retrieverExecution)
			throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, ParseException {
		
		YahooData yahooDataOld = findBySymbolAndRetrievedEntityStatus(symbol, RetrievedEntityStatus.FRESH.toString());
		if (yahooDataOld != null){
			yahooDataOld.setRetrievedEntityStatus(RetrievedEntityStatus.OLD.toString());
			saveYahooData(yahooDataOld);
		}
		
		YahooData yahooData = retrieverBinder.parse(YahooData.class, Parser.xmlParser(), URLEncoder.encode("\"" + symbol.getCode() + ".MI\"", "UTF-8"));
		
		yahooData.setSymbol(symbol);
		yahooData.setRetrieverExecution(retrieverExecution);
		yahooData.setRetrievedEntityStatus(RetrievedEntityStatus.FRESH.toString());
		saveYahooData(yahooData);
		
	}
	
	@Transactional
	private void refreshMarketData(RetrieverExecution retrieverExecution, YahooData yahooData) {
		YahooData yahooDataOld = findBySymbolAndRetrievedEntityStatus(yahooData.getSymbol(), RetrievedEntityStatus.FRESH.toString());
		if (yahooDataOld != null){
			yahooDataOld.setRetrievedEntityStatus(RetrievedEntityStatus.OLD.toString());
			saveYahooData(yahooDataOld);
		}
		
		yahooData.setRetrieverExecution(retrieverExecution);
		yahooData.setRetrievedEntityStatus(RetrievedEntityStatus.FRESH.toString());
		saveYahooData(yahooData);
	}

	public YahooData findBySymbolAndRetrievedEntityStatus(Symbol symbol, String status) {
		return yahooDataRepository.findBySymbolAndRetrievedEntityStatus(symbol, status);
	}

	public void saveYahooData(List<YahooData> yahooDataList) {
		yahooDataRepository.save(yahooDataList);		
	}
	
	public void saveYahooData(YahooData yahooData) {
		yahooDataRepository.save(yahooData);		
	}

	public List<YahooData> getYahooDataFromYql(List<Symbol> symbols) throws IOException{
		List<YahooData> yahooDataList = new ArrayList<YahooData>();
		
		
		return yahooDataList;
	}
	
	public List<YahooData> getYahooData(List<Symbol> symbols) throws IOException{
		List<YahooData> yahooDataList = new ArrayList<YahooData>();

		ICsvBeanReader beanReader = null;
		try {
			
			try {
				TimeUnit.SECONDS.sleep(new Random().nextInt(90));
			} catch (InterruptedException e) {
				logger.error("Error sleeping.", e);
			}
			
			URL yahooUrl = new URL("http://download.finance.yahoo.com/d/quotes.csv?s=" + getSymbolsCodeList(symbols) + "&f=c8g3a0b2a5a2b0b3b6b4c1c0m7m5k4j5p2k2c6c3c4h0g0m0m2w1w4r1d0y0e0j4e7e9e8q0m3f6l2g4g1g5g6v1v7d1l1k1k3t1l0l3j1j3i0n0n4t8o0i5r5r0r2m8m6k5j6p0p6r6r7p1p5s6s1j2s7x0s0t7d2t6f0m4v0k0j0w0&e=.csv");
		    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(yahooUrl.openStream()));
			beanReader = new CsvBeanReader(bufferedReader, CsvPreference.STANDARD_PREFERENCE);
			
			// the header elements are used to map the values to the bean (names must match)
			final String[] header = getHeader();
			final CellProcessor[] processors = getProcessors(header.length);

			YahooData yahooData;
			while((yahooData = beanReader.read(YahooData.class, header, processors)) != null ) {
				
				//TODO: rimuovere commento
//				yahooData.setSymbol(getSymbolFromList(yahooData.getCode(), symbols));
				yahooDataList.add(yahooData);
			}

		} catch (FileNotFoundException e) {
			logger.error("Error retrivig Yahoo Data.", e);
			throw e;
		} catch (IOException e) {
			logger.error("Error retrivig Yahoo Data.", e);
			throw e;
		} finally {
			if( beanReader != null ) {
				try {
					beanReader.close();
				} catch (IOException e) {
					logger.error("Error retrivig Yahoo Data.", e);
				}
			}
		}

		return yahooDataList;
	}

	private String[] getHeader() {
		String[] header = {"afterHoursChangeRealtime", "annualizedGain", "ask", "askRealtime", "askSize", "averageDailyVolume", "bid", "bidRealtime", "bidSize", "bookValuePerShare", "changeStr", "changeChangeInPercent", "changeFromFiftydayMovingAverage", "changeFromTwoHundreddayMovingAverage", "changeFromYearHigh", "changeFromYearLow", "changeInPercent", "changeInPercentRealtime", "changeRealtime", "commission", "currency", "daysHigh", "daysLow", "daysRange", "daysRangeRealtime", "daysValueChange", "daysValueChangeRealtime", "dividendPayDate", "trailingAnnualDividendYield", "trailingAnnualDividendYieldInPercent", "dilutedEPS", "eBITDA", "ePSEstimateCurrentYear", "ePSEstimateNextQuarter", "ePSEstimateNextYear", "exDividendDate", "fiftydayMovingAverage", "sharesFloat", "highLimit", "holdingsGain", "holdingsGainPercent", "holdingsGainPercentRealtime", "holdingsGainRealtime", "holdingsValue", "holdingsValueRealtime", "lastTradeDate", "lastTradePriceOnly", "lastTradeRealtimeWithTime", "lastTradeSize", "lastTradeTime", "lastTradeWithTime", "lowLimit", "marketCapitalization", "marketCapRealtime", "moreInfo", "name", "notes", "oneyrTargetPrice", "open", "orderBookRealtime", "pEGRatio", "pERatio", "pERatioRealtime", "percentChangeFromFiftydayMovingAverage", "percentChangeFromTwoHundreddayMovingAverage", "changeInPercentFromYearHigh", "percentChangeFromYearLow", "previousClose", "priceBook", "priceEPSEstimateCurrentYear", "priceEPSEstimateNextYear", "pricePaid", "priceSales", "revenue", "sharesOwned", "sharesOutstanding", "shortRatio", "stockExchange", "code", "tickerTrend", "tradeDate", "tradeLinks", "tradeLinksAdditional", "twoHundreddayMovingAverage", "volume", "yearHigh", "yearLow", "yearRange"};
		return header;
	}

	private String getSymbolsCodeList(List<Symbol> symbols) {
		String symbolsCodeList = "";
		for (Symbol symbol : symbols) {
			symbolsCodeList = symbolsCodeList + (!symbolsCodeList.equals("") ? "," : "") + symbol.getCode() + ".MI";
		}
				
		return symbolsCodeList;
	}

	private Symbol getSymbolFromList(String code, List<Symbol> symbols) {
		for (Symbol symbol : symbols) {
			if (code.indexOf(".") != -1 && symbol.getCode().equalsIgnoreCase(code.substring(0, code.indexOf("."))))
				return symbol;
		}
		return null;
	}

	private CellProcessor[] getProcessors(int length) {

		CellProcessor[] processors = new CellProcessor[length];
		for (CellProcessor cellProcessor : processors) {
			cellProcessor = new NotNull();
		}
		
		return processors;
	}

}
