package it.bela.market.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import it.bela.market.annotation.FieldSelector;
import it.bela.market.annotation.PageUrl;

@Entity
@Table(name = "borsa_italiana_data")
@PageUrl("http://www.borsaitaliana.it/borsa/etf/dettaglio.html?lang=it&isin=")
public class BorsaItalianaData extends BaseDataMarketEntity {

	private String name;
	private String perf1Month;
	private String perf6Months;
	private String perf1Yar;
	
	public String getName() {
		return name;
	}
	
	@FieldSelector("#fullcontainer > div.column_full_display > div:nth-child(11) > table > tbody > tr:nth-child(1) > td:nth-child(2)")
	public void setName(String name) {
		this.name = name;
	}
	public String getPerf1Month() {
		return perf1Month;
	}
	
	@FieldSelector("#fullcontainer > div.column_full_display > div:nth-child(13) > table:nth-child(2) > tbody > tr:nth-child(1) > td:nth-child(2)")
	public void setPerf1Month(String perf1Month) {
		this.perf1Month = perf1Month;
	}
	public String getPerf6Months() {
		return perf6Months;
	}
	
	@FieldSelector("#fullcontainer > div.column_full_display > div:nth-child(13) > table:nth-child(2) > tbody > tr:nth-child(2) > td:nth-child(2)")
	public void setPerf6Months(String perf6Months) {
		this.perf6Months = perf6Months;
	}
	public String getPerf1Yar() {
		return perf1Yar;
	}
	
	@FieldSelector("#fullcontainer > div.column_full_display > div:nth-child(13) > table:nth-child(2) > tbody > tr:nth-child(4) > td:nth-child(2)")
	public void setPerf1Yar(String perf1Yar) {
		this.perf1Yar = perf1Yar;
	}
}
