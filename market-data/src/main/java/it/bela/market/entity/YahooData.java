package it.bela.market.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import it.bela.market.annotation.FieldSelector;
import it.bela.market.annotation.PageUrl;

@Entity
@Table(name = "yahoo_data")
@PageUrl("https://query.yahooapis.com/v1/public/yql?env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20%3D%20")
public class YahooData extends BaseDataMarketEntity {
	
	private String ask;
	private String averageDailyVolume;
	private String bid;
	private String askRealtime;
	private String bidRealtime;
	private String bookValue;
	private String changePercentChange;
	private String changeVal;
	private String commission;
	private String currency;
	private String changeRealtime;
	private String afterHoursChangeRealtime;
	private String dividendShare;
	private String lastTradeDate;
	private String tradeDate;
	private String earningsShare;
	private String errorIndicationreturnedforsymbolchangedinvalid;
	private String ePSEstimateCurrentYear;
	private String ePSEstimateNextYear;
	private String ePSEstimateNextQuarter;
	private String daysLow;
	private String daysHigh;
	private String yearLow;
	private String yearHigh;
	private String holdingsGainPercent;
	private String annualizedGain;
	private String holdingsGain;
	private String holdingsGainPercentRealtime;
	private String holdingsGainRealtime;
	private String moreInfo;
	private String orderBookRealtime;
	private String marketCapitalization;
	private String marketCapRealtime;
	private String eBITDA;
	private String changeFromYearLow;
	private String percentChangeFromYearLow;
	private String lastTradeRealtimeWithTime;
	private String changePercentRealtime;
	private String changeFromYearHigh;
	private String percebtChangeFromYearHigh;
	private String lastTradeWithTime;
	private String lastTradePriceOnly;
	private String highLimit;
	private String lowLimit;
	private String daysRange;
	private String daysRangeRealtime;
	private String fiftydayMovingAverage;
	private String twoHundreddayMovingAverage;
	private String changeFromTwoHundreddayMovingAverage;
	private String percentChangeFromTwoHundreddayMovingAverage;
	private String changeFromFiftydayMovingAverage;
	private String percentChangeFromFiftydayMovingAverage;
	private String name;
	private String notes;
	private String open;
	private String previousClose;
	private String pricePaid;
	private String changeinPercent;
	private String priceSales;
	private String priceBook;
	private String exDividendDate;
	private String pERatio;
	private String dividendPayDate;
	private String pERatioRealtime;
	private String pEGRatio;
	private String priceEPSEstimateCurrentYear;
	private String priceEPSEstimateNextYear;
	private String symbolCode;
	private String sharesOwned;
	private String shortRatio;
	private String lastTradeTime;
	private String tickerTrend;
	private String oneyrTargetPrice;
	private String volume;
	private String holdingsValue;
	private String holdingsValueRealtime;
	private String yearRange;
	private String daysValueChange;
	private String daysValueChangeRealtime;
	private String stockExchange;
	private String dividendYield;

	
	public String getAsk() {
		return ask;
	}
	
	@FieldSelector("Ask")
	public void setAsk(String ask) {
		this.ask = ask;
	}
	public String getAskRealtime() {
		return askRealtime;
	}
	
	@FieldSelector("AskRealtime")
	public void setAskRealtime(String askRealtime) {
		this.askRealtime = askRealtime;
	}

	public String getAverageDailyVolume() {
		return averageDailyVolume;
	}

	@FieldSelector("AverageDailyVolume")
	public void setAverageDailyVolume(String averageDailyVolume) {
		this.averageDailyVolume = averageDailyVolume;
	}

	public String getBid() {
		return bid;
	}

	@FieldSelector("Bid")
	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getBidRealtime() {
		return bidRealtime;
	}

	@FieldSelector("BidRealtime")
	public void setBidRealtime(String bidRealtime) {
		this.bidRealtime = bidRealtime;
	}

	public String getBookValue() {
		return bookValue;
	}

	@FieldSelector("BookValue")
	public void setBookValue(String bookValue) {
		this.bookValue = bookValue;
	}

	public String getChangePercentChange() {
		return changePercentChange;
	}

	@FieldSelector("Change_PercentChange")
	public void setChangePercentChange(String changePercentChange) {
		this.changePercentChange = changePercentChange;
	}

	public String getChangeVal() {
		return changeVal;
	}

	@FieldSelector("Change")
	public void setChangeVal(String changeVal) {
		this.changeVal = changeVal;
	}

	public String getCommission() {
		return commission;
	}

	@FieldSelector("Commission")
	public void setCommission(String commission) {
		this.commission = commission;
	}

	public String getCurrency() {
		return currency;
	}

	@FieldSelector("Currency")
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getChangeRealtime() {
		return changeRealtime;
	}

	@FieldSelector("ChangeRealtime")
	public void setChangeRealtime(String changeRealtime) {
		this.changeRealtime = changeRealtime;
	}

	public String getAfterHoursChangeRealtime() {
		return afterHoursChangeRealtime;
	}

	@FieldSelector("AfterHoursChangeRealtime")
	public void setAfterHoursChangeRealtime(String afterHoursChangeRealtime) {
		this.afterHoursChangeRealtime = afterHoursChangeRealtime;
	}

	public String getDividendShare() {
		return dividendShare;
	}

	@FieldSelector("DividendShare")
	public void setDividendShare(String dividendShare) {
		this.dividendShare = dividendShare;
	}

	public String getLastTradeDate() {
		return lastTradeDate;
	}

	@FieldSelector("LastTradeDate")
	public void setLastTradeDate(String lastTradeDate) {
		this.lastTradeDate = lastTradeDate;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	@FieldSelector("TradeDate")
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getEarningsShare() {
		return earningsShare;
	}

	@FieldSelector("EarningsShare")
	public void setEarningsShare(String earningsShare) {
		this.earningsShare = earningsShare;
	}

	public String getErrorIndicationreturnedforsymbolchangedinvalid() {
		return errorIndicationreturnedforsymbolchangedinvalid;
	}

	@FieldSelector("ErrorIndicationreturnedforsymbolchangedinvalid")
	public void setErrorIndicationreturnedforsymbolchangedinvalid(String errorIndicationreturnedforsymbolchangedinvalid) {
		this.errorIndicationreturnedforsymbolchangedinvalid = errorIndicationreturnedforsymbolchangedinvalid;
	}

	public String getePSEstimateCurrentYear() {
		return ePSEstimateCurrentYear;
	}

	@FieldSelector("EPSEstimateCurrentYear")
	public void setePSEstimateCurrentYear(String ePSEstimateCurrentYear) {
		this.ePSEstimateCurrentYear = ePSEstimateCurrentYear;
	}

	public String getePSEstimateNextYear() {
		return ePSEstimateNextYear;
	}

	@FieldSelector("EPSEstimateNextYear")
	public void setePSEstimateNextYear(String ePSEstimateNextYear) {
		this.ePSEstimateNextYear = ePSEstimateNextYear;
	}

	public String getePSEstimateNextQuarter() {
		return ePSEstimateNextQuarter;
	}

	@FieldSelector("EPSEstimateNextQuarter")
	public void setEPSEstimateNextQuarter(String ePSEstimateNextQuarter) {
		this.ePSEstimateNextQuarter = ePSEstimateNextQuarter;
	}

	public String getDaysLow() {
		return daysLow;
	}

	@FieldSelector("DaysLow")
	public void setDaysLow(String daysLow) {
		this.daysLow = daysLow;
	}

	public String getDaysHigh() {
		return daysHigh;
	}

	@FieldSelector("DaysHigh")
	public void setDaysHigh(String daysHigh) {
		this.daysHigh = daysHigh;
	}

	public String getYearLow() {
		return yearLow;
	}

	@FieldSelector("YearLow")
	public void setYearLow(String yearLow) {
		this.yearLow = yearLow;
	}

	public String getYearHigh() {
		return yearHigh;
	}

	@FieldSelector("YearHigh")
	public void setYearHigh(String yearHigh) {
		this.yearHigh = yearHigh;
	}

	public String getHoldingsGainPercent() {
		return holdingsGainPercent;
	}

	@FieldSelector("HoldingsGainPercent")
	public void setHoldingsGainPercent(String holdingsGainPercent) {
		this.holdingsGainPercent = holdingsGainPercent;
	}

	public String getAnnualizedGain() {
		return annualizedGain;
	}

	@FieldSelector("AnnualizedGain")
	public void setAnnualizedGain(String annualizedGain) {
		this.annualizedGain = annualizedGain;
	}

	public String getHoldingsGain() {
		return holdingsGain;
	}

	@FieldSelector("HoldingsGain")
	public void setHoldingsGain(String holdingsGain) {
		this.holdingsGain = holdingsGain;
	}

	public String getHoldingsGainPercentRealtime() {
		return holdingsGainPercentRealtime;
	}

	@FieldSelector("HoldingsGainPercentRealtime")
	public void setHoldingsGainPercentRealtime(String holdingsGainPercentRealtime) {
		this.holdingsGainPercentRealtime = holdingsGainPercentRealtime;
	}

	public String getHoldingsGainRealtime() {
		return holdingsGainRealtime;
	}

	@FieldSelector("HoldingsGainRealtime")
	public void setHoldingsGainRealtime(String holdingsGainRealtime) {
		this.holdingsGainRealtime = holdingsGainRealtime;
	}

	public String getMoreInfo() {
		return moreInfo;
	}

	@FieldSelector("MoreInfo")
	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}

	public String getOrderBookRealtime() {
		return orderBookRealtime;
	}

	@FieldSelector("OrderBookRealtime")
	public void setOrderBookRealtime(String orderBookRealtime) {
		this.orderBookRealtime = orderBookRealtime;
	}

	public String getMarketCapitalization() {
		return marketCapitalization;
	}

	@FieldSelector("MarketCapitalization")
	public void setMarketCapitalization(String marketCapitalization) {
		this.marketCapitalization = marketCapitalization;
	}

	public String getMarketCapRealtime() {
		return marketCapRealtime;
	}

	@FieldSelector("MarketCapRealtime")
	public void setMarketCapRealtime(String marketCapRealtime) {
		this.marketCapRealtime = marketCapRealtime;
	}

	public String geteBITDA() {
		return eBITDA;
	}

	@FieldSelector("EBITDA")
	public void seteBITDA(String eBITDA) {
		this.eBITDA = eBITDA;
	}

	public String getChangeFromYearLow() {
		return changeFromYearLow;
	}

	@FieldSelector("ChangeFromYearLow")
	public void setChangeFromYearLow(String changeFromYearLow) {
		this.changeFromYearLow = changeFromYearLow;
	}

	public String getPercentChangeFromYearLow() {
		return percentChangeFromYearLow;
	}

	@FieldSelector("PercentChangeFromYearLow")
	public void setPercentChangeFromYearLow(String percentChangeFromYearLow) {
		this.percentChangeFromYearLow = percentChangeFromYearLow;
	}

	public String getLastTradeRealtimeWithTime() {
		return lastTradeRealtimeWithTime;
	}

	@FieldSelector("LastTradeRealtimeWithTime")
	public void setLastTradeRealtimeWithTime(String lastTradeRealtimeWithTime) {
		this.lastTradeRealtimeWithTime = lastTradeRealtimeWithTime;
	}

	public String getChangePercentRealtime() {
		return changePercentRealtime;
	}

	@FieldSelector("ChangePercentRealtime")
	public void setChangePercentRealtime(String changePercentRealtime) {
		this.changePercentRealtime = changePercentRealtime;
	}

	public String getChangeFromYearHigh() {
		return changeFromYearHigh;
	}

	@FieldSelector("ChangeFromYearHigh")
	public void setChangeFromYearHigh(String changeFromYearHigh) {
		this.changeFromYearHigh = changeFromYearHigh;
	}

	public String getPercebtChangeFromYearHigh() {
		return percebtChangeFromYearHigh;
	}

	@FieldSelector("PercebtChangeFromYearHigh")
	public void setPercebtChangeFromYearHigh(String percebtChangeFromYearHigh) {
		this.percebtChangeFromYearHigh = percebtChangeFromYearHigh;
	}

	public String getLastTradeWithTime() {
		return lastTradeWithTime;
	}

	@FieldSelector("LastTradeWithTime")
	public void setLastTradeWithTime(String lastTradeWithTime) {
		this.lastTradeWithTime = lastTradeWithTime;
	}

	public String getLastTradePriceOnly() {
		return lastTradePriceOnly;
	}

	@FieldSelector("LastTradePriceOnly")
	public void setLastTradePriceOnly(String lastTradePriceOnly) {
		this.lastTradePriceOnly = lastTradePriceOnly;
	}

	public String getHighLimit() {
		return highLimit;
	}

	@FieldSelector("HighLimit")
	public void setHighLimit(String highLimit) {
		this.highLimit = highLimit;
	}

	public String getLowLimit() {
		return lowLimit;
	}

	@FieldSelector("LowLimit")
	public void setLowLimit(String lowLimit) {
		this.lowLimit = lowLimit;
	}

	public String getDaysRange() {
		return daysRange;
	}

	@FieldSelector("DaysRange")
	public void setDaysRange(String daysRange) {
		this.daysRange = daysRange;
	}

	public String getDaysRangeRealtime() {
		return daysRangeRealtime;
	}

	@FieldSelector("DaysRangeRealtime")
	public void setDaysRangeRealtime(String daysRangeRealtime) {
		this.daysRangeRealtime = daysRangeRealtime;
	}

	public String getFiftydayMovingAverage() {
		return fiftydayMovingAverage;
	}

	@FieldSelector("FiftydayMovingAverage")
	public void setFiftydayMovingAverage(String fiftydayMovingAverage) {
		this.fiftydayMovingAverage = fiftydayMovingAverage;
	}

	public String getTwoHundreddayMovingAverage() {
		return twoHundreddayMovingAverage;
	}

	@FieldSelector("TwoHundreddayMovingAverage")
	public void setTwoHundreddayMovingAverage(String twoHundreddayMovingAverage) {
		this.twoHundreddayMovingAverage = twoHundreddayMovingAverage;
	}

	public String getChangeFromTwoHundreddayMovingAverage() {
		return changeFromTwoHundreddayMovingAverage;
	}

	@FieldSelector("ChangeFromTwoHundreddayMovingAverage")
	public void setChangeFromTwoHundreddayMovingAverage(String changeFromTwoHundreddayMovingAverage) {
		this.changeFromTwoHundreddayMovingAverage = changeFromTwoHundreddayMovingAverage;
	}

	public String getPercentChangeFromTwoHundreddayMovingAverage() {
		return percentChangeFromTwoHundreddayMovingAverage;
	}

	@FieldSelector("PercentChangeFromTwoHundreddayMovingAverage")
	public void setPercentChangeFromTwoHundreddayMovingAverage(String percentChangeFromTwoHundreddayMovingAverage) {
		this.percentChangeFromTwoHundreddayMovingAverage = percentChangeFromTwoHundreddayMovingAverage;
	}

	public String getChangeFromFiftydayMovingAverage() {
		return changeFromFiftydayMovingAverage;
	}

	@FieldSelector("ChangeFromFiftydayMovingAverage")
	public void setChangeFromFiftydayMovingAverage(String changeFromFiftydayMovingAverage) {
		this.changeFromFiftydayMovingAverage = changeFromFiftydayMovingAverage;
	}

	public String getPercentChangeFromFiftydayMovingAverage() {
		return percentChangeFromFiftydayMovingAverage;
	}

	@FieldSelector("PercentChangeFromFiftydayMovingAverage")
	public void setPercentChangeFromFiftydayMovingAverage(String percentChangeFromFiftydayMovingAverage) {
		this.percentChangeFromFiftydayMovingAverage = percentChangeFromFiftydayMovingAverage;
	}

	public String getName() {
		return name;
	}

	@FieldSelector("Name")
	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return notes;
	}

	@FieldSelector("Notes")
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getOpen() {
		return open;
	}

	@FieldSelector("Open")
	public void setOpen(String open) {
		this.open = open;
	}

	public String getPreviousClose() {
		return previousClose;
	}

	@FieldSelector("PreviousClose")
	public void setPreviousClose(String previousClose) {
		this.previousClose = previousClose;
	}

	public String getPricePaid() {
		return pricePaid;
	}

	@FieldSelector("PricePaid")
	public void setPricePaid(String pricePaid) {
		this.pricePaid = pricePaid;
	}

	public String getChangeinPercent() {
		return changeinPercent;
	}

	@FieldSelector("ChangeinPercent")
	public void setChangeinPercent(String changeinPercent) {
		this.changeinPercent = changeinPercent;
	}

	public String getPriceSales() {
		return priceSales;
	}

	@FieldSelector("PriceSales")
	public void setPriceSales(String priceSales) {
		this.priceSales = priceSales;
	}

	public String getPriceBook() {
		return priceBook;
	}

	@FieldSelector("PriceBook")
	public void setPriceBook(String priceBook) {
		this.priceBook = priceBook;
	}

	public String getExDividendDate() {
		return exDividendDate;
	}

	@FieldSelector("ExDividendDate")
	public void setExDividendDate(String exDividendDate) {
		this.exDividendDate = exDividendDate;
	}

	public String getpERatio() {
		return pERatio;
	}

	@FieldSelector("PERatio")
	public void setpERatio(String pERatio) {
		this.pERatio = pERatio;
	}

	public String getDividendPayDate() {
		return dividendPayDate;
	}

	@FieldSelector("DividendPayDate")
	public void setDividendPayDate(String dividendPayDate) {
		this.dividendPayDate = dividendPayDate;
	}

	public String getpERatioRealtime() {
		return pERatioRealtime;
	}

	@FieldSelector("PERatioRealtime")
	public void setpERatioRealtime(String pERatioRealtime) {
		this.pERatioRealtime = pERatioRealtime;
	}

	public String getpEGRatio() {
		return pEGRatio;
	}

	@FieldSelector("PEGRatio")
	public void setpEGRatio(String pEGRatio) {
		this.pEGRatio = pEGRatio;
	}

	public String getPriceEPSEstimateCurrentYear() {
		return priceEPSEstimateCurrentYear;
	}

	@FieldSelector("PriceEPSEstimateCurrentYear")
	public void setPriceEPSEstimateCurrentYear(String priceEPSEstimateCurrentYear) {
		this.priceEPSEstimateCurrentYear = priceEPSEstimateCurrentYear;
	}

	public String getPriceEPSEstimateNextYear() {
		return priceEPSEstimateNextYear;
	}

	@FieldSelector("PriceEPSEstimateNextYear")
	public void setPriceEPSEstimateNextYear(String priceEPSEstimateNextYear) {
		this.priceEPSEstimateNextYear = priceEPSEstimateNextYear;
	}

	public String getSymbolCode() {
		return symbolCode;
	}

	@FieldSelector("Symbol")
	public void setSymbolCode(String symbolCode) {
		this.symbolCode = symbolCode;
	}

	public String getSharesOwned() {
		return sharesOwned;
	}

	@FieldSelector("SharesOwned")
	public void setSharesOwned(String sharesOwned) {
		this.sharesOwned = sharesOwned;
	}

	public String getShortRatio() {
		return shortRatio;
	}

	@FieldSelector("ShortRatio")
	public void setShortRatio(String shortRatio) {
		this.shortRatio = shortRatio;
	}

	public String getLastTradeTime() {
		return lastTradeTime;
	}

	@FieldSelector("LastTradeTime")
	public void setLastTradeTime(String lastTradeTime) {
		this.lastTradeTime = lastTradeTime;
	}

	public String getTickerTrend() {
		return tickerTrend;
	}

	@FieldSelector("TickerTrend")
	public void setTickerTrend(String tickerTrend) {
		this.tickerTrend = tickerTrend;
	}

	public String getOneyrTargetPrice() {
		return oneyrTargetPrice;
	}

	@FieldSelector("OneyrTargetPrice")
	public void setOneyrTargetPrice(String oneyrTargetPrice) {
		this.oneyrTargetPrice = oneyrTargetPrice;
	}

	public String getVolume() {
		return volume;
	}

	@FieldSelector("Volume")
	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getHoldingsValue() {
		return holdingsValue;
	}

	@FieldSelector("HoldingsValue")
	public void setHoldingsValue(String holdingsValue) {
		this.holdingsValue = holdingsValue;
	}

	public String getHoldingsValueRealtime() {
		return holdingsValueRealtime;
	}

	@FieldSelector("HoldingsValueRealtime")
	public void setHoldingsValueRealtime(String holdingsValueRealtime) {
		this.holdingsValueRealtime = holdingsValueRealtime;
	}

	public String getYearRange() {
		return yearRange;
	}

	@FieldSelector("YearRange")
	public void setYearRange(String yearRange) {
		this.yearRange = yearRange;
	}

	public String getDaysValueChange() {
		return daysValueChange;
	}

	@FieldSelector("DaysValueChange")
	public void setDaysValueChange(String daysValueChange) {
		this.daysValueChange = daysValueChange;
	}

	public String getDaysValueChangeRealtime() {
		return daysValueChangeRealtime;
	}

	@FieldSelector("DaysValueChangeRealtime")
	public void setDaysValueChangeRealtime(String daysValueChangeRealtime) {
		this.daysValueChangeRealtime = daysValueChangeRealtime;
	}

	public String getStockExchange() {
		return stockExchange;
	}

	@FieldSelector("StockExchange")
	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}

	public String getDividendYield() {
		return dividendYield;
	}

	@FieldSelector("DividendYield")
	public void setDividendYield(String dividendYield) {
		this.dividendYield = dividendYield;
	}
	
}
