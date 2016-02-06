package it.bela.market.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import it.bela.market.annotation.FieldSelector;
import it.bela.market.annotation.PageUrl;

@Entity
@Table(name = "morningstar_data")
@PageUrl(value = "http://tools.morningstar.it/it/etfscreener/results.aspx?LanguageId=it-IT&Universe=ETEXG%24XMIL&Site=IT&search=", 
		urlToRetrieveSelectors = {
				"td.msDataText.gridFundName.Shrink > a",
				"#snapshotTabNewDiv > table:nth-child(1) > tbody > tr:nth-child(4) > td > a",
				"#snapshotTabNewDiv > table:nth-child(1) > tbody > tr:nth-child(5) > td > a"
		})
public class MorningstarData extends BaseDataMarketEntity {
	
	private String name;
	private String morningstarCategory;
	private String morningstarRating;
	private String ytdPercent;
	private String cost;
	private String price;
	private String currency;
	
	@Type(type="text")
	private String targetInfo;
	
	private String oneDayReturn;
	private String oneWeekReturn;
	private String oneMonthReturn;
	private String threeMonthsReturn;
	private String sixMonthsReturn;
	private String oneYearReturn;
	private String morningstarRisk;

	public String getName() {
		return name;
	}
	
	@FieldSelector("td.msDataText.gridFundName.Shrink > a")
	public void setName(String name) {
		this.name = name;
	}
	public String getMorningstarCategory() {
		return morningstarCategory;
	}
	
	@FieldSelector("td.msDataText.gridCategoryName.Shrink")
	public void setMorningstarCategory(String morningStarCategory) {
		this.morningstarCategory = morningStarCategory;
	}
	public String getMorningstarRating() {
		return morningstarRating;
	}
	
	@FieldSelector(value = "td.msDataText.gridStarRating > img", attribute = "src", pattern="[0-9]")
	public void setMorningstarRating(String morningStarRating) {
		this.morningstarRating = morningStarRating;
	}
	public String getYtdPercent() {
		return ytdPercent;
	}
	
	@FieldSelector(value = "td.msDataNumeric.gridYTD > span")
	public void setYtdPercent(String ytdPercent) {
		this.ytdPercent = ytdPercent;
	}
	public String getCost() {
		return cost;
	}
	
	@FieldSelector(value = "td.msDataNumeric.gridOngoingCharge")
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getPrice() {
		return price;
	}
	
	@FieldSelector(value = "td.msDataNumeric.gridClosePrice")
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCurrency() {
		return currency;
	}
	
	@FieldSelector(value = "td.msDataText.gridClosePriceCurrency > span")
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getTargetInfo() {
		return targetInfo;
	}

	@FieldSelector(elementIndex = 1, value = "#overviewObjectiveDiv > table > tbody > tr:nth-child(2) > td")
	public void setTargetInfo(String targetInfo) {
		this.targetInfo = targetInfo;
	}

	public String getOneDayReturn() {
		return oneDayReturn;
	}

	@FieldSelector(elementIndex = 2, value = "#returnsTrailingDiv > table > tbody > tr:nth-child(3) > td.col2.value.number")
	public void setOneDayReturn(String oneDayReturn) {
		this.oneDayReturn = oneDayReturn;
	}

	public String getOneWeekReturn() {
		return oneWeekReturn;
	}

	@FieldSelector(elementIndex = 2, value = "#returnsTrailingDiv > table > tbody > tr:nth-child(4) > td.col2.value.number")
	public void setOneWeekReturn(String oneWeekReturn) {
		this.oneWeekReturn = oneWeekReturn;
	}

	public String getOneMonthReturn() {
		return oneMonthReturn;
	}

	@FieldSelector(elementIndex = 2, value = "#returnsTrailingDiv > table > tbody > tr:nth-child(5) > td.col2.value.number")
	public void setOneMonthReturn(String oneMonthReturn) {
		this.oneMonthReturn = oneMonthReturn;
	}

	public String getThreeMonthsReturn() {
		return threeMonthsReturn;
	}

	@FieldSelector(elementIndex = 2, value = "#returnsTrailingDiv > table > tbody > tr:nth-child(6) > td.col2.value.number")
	public void setThreeMonthsReturn(String threeMonthsReturn) {
		this.threeMonthsReturn = threeMonthsReturn;
	}

	public String getSixMonthsReturn() {
		return sixMonthsReturn;
	}

	@FieldSelector(elementIndex = 2, value = "#returnsTrailingDiv > table > tbody > tr:nth-child(7) > td.col2.value.number")
	public void setSixMonthsReturn(String sixMonthsReturn) {
		this.sixMonthsReturn = sixMonthsReturn;
	}

	public String getOneYearReturn() {
		return oneYearReturn;
	}

	@FieldSelector(elementIndex = 2, value = "#returnsTrailingDiv > table > tbody > tr:nth-child(9) > td.col2.value.number")
	public void setOneYearReturn(String oneYearReturn) {
		this.oneYearReturn = oneYearReturn;
	}

	public String getMorningstarRisk() {
		return morningstarRisk;
	}

	@FieldSelector(elementIndex = 3, value = "#ratingRatingDiv > table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(5) > td:nth-child(3)")
	public void setMorningstarRisk(String morningstarRisk) {
		this.morningstarRisk = morningstarRisk;
	}
}
