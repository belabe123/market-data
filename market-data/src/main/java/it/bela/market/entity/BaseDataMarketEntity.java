package it.bela.market.entity;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseDataMarketEntity extends BaseRetrievedEntity {

	@ManyToOne
	protected Symbol symbol;
	
	public Symbol getSymbol() {
		return symbol;
	}
	
	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}
}
