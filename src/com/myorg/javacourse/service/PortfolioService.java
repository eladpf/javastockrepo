package com.myorg.javacourse.service;

import java.util.Calendar;

import com.myorg.javacourse.model.Portfolio;
import com.myorg.javacourse.model.Stock;

/**
 * This service is intend to manage portfolios
 * @author Elad.Avidan
 *
 */
public class PortfolioService {

	/**
	 * Creates 3 different stocks, does some operations on them and returns their portfolio.
	 * @return a new portfolio with different stocks.
	 */
	public Portfolio getPortfolio() {
		Portfolio portfolio = new Portfolio();
		portfolio.setTitle("Exercise 7 portfolio");
		portfolio.updateBalance(10000);
		
		Calendar cal = Calendar.getInstance();
		cal.set(2014, Calendar.DECEMBER, 15);
		
		portfolio.buyStock(new Stock("PIH", 10.0f, 8.5f, cal.getTime()), 20);
		portfolio.buyStock(new Stock("AAL", 30.0f, 25.5f, cal.getTime()), 30);
		portfolio.buyStock(new Stock("CAAS", 20.0f, 15.5f, cal.getTime()), 40);
		
		portfolio.sellStock("AAL", -1);
		portfolio.removeStock("CAAS");
		return portfolio;
	}
}
