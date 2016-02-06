package it.bela.market.bean;

import it.bela.market.annotation.FieldSelector;
import it.bela.market.entity.Symbol;

public class SymbolYahoo extends Symbol {
	
	@FieldSelector("td:nth-child(1)")
	public void setCode(String code) {
		
		if (code.indexOf(".") != -1){
			code = code.substring(0, code.indexOf("."));
		}
		
		super.setCode(code);
	}

	@FieldSelector("td:nth-child(3)")
	public void setIsin(String isin) {
		super.setIsin(isin);
	}
	
	@FieldSelector("td:nth-child(2)")
	public void setName(String name) {
		super.setName(name);
	}
	
	@FieldSelector("td:nth-child(5)")
	public void setCategory(String category) {
		super.setCategory(category);
	}

	@FieldSelector("td:nth-child(6)")
	public void setMarket(String market) {
		super.setMarket(market);
	}
	
}
