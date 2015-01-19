package com.myorg.javacourse.service;

import java.util.Calendar;

import com.myorg.javacourse.Stock;
import com.myorg.javacourse.model.Portfolio;

public class PortfolioManager {

	public Portfolio getPortfolio() {
		Portfolio portfolio = new Portfolio();
		
		Calendar cal = Calendar.getInstance();
		cal.set(2014, Calendar.NOVEMBER, 15);
		
		portfolio.addStock(new Stock("PIH", 13.1f, 12.4f, cal.getTime()));
		portfolio.addStock(new Stock("AAL", 5.78f, 5.5f, cal.getTime()));
		portfolio.addStock(new Stock("CAAS", 32.2f, 31.5f, cal.getTime()));
		return portfolio;
	}
}
