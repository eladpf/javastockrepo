package com.myorg.javacourse.model;

import com.myorg.javacourse.Stock;

public class Portfolio {

	private String title;
	private Stock[] stocks;
	private int portfolioSize = 0;
	
	private final int MAX_PORTFOLIO_SIZE = 5;
	
	public Portfolio() {
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.title = "Portfolio title";
	}
	
	public void addStock(Stock stock) {
		this.stocks[this.portfolioSize] = stock;
		this.portfolioSize++;
	}
	
	public Stock[] getStocks() {
		return this.stocks;
	}
	
	public String getHtmlString() {
		StringBuilder sb = new StringBuilder(String.format("<h1>%s</h1>", this.title));
		for (int i = 0 ; i < this.portfolioSize ; i++) {
			sb.append(String.format("<br>%s", getStocks()[i].getHtmlDescription()));
		}
		
		return sb.toString();
	}
}