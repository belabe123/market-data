package it.bela.market.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "symbols")
public class Symbol extends BaseRetrievedEntity {

	private String code;
	private String name;
	private String isin;
	private String category;
	private String market;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getIsin() {
		return isin;
	}
	public void setIsin(String isin) {
		this.isin = isin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
}
