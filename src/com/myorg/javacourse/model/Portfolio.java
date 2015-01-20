package com.myorg.javacourse.model;

/**
 * A portfolio which will manage different stocks.
 * @author Elad.Avidan
 *
 */
public class Portfolio {

	private String title;
	private Stock[] stocks;
	private int portfolioSize;
	
	private final int MAX_PORTFOLIO_SIZE = 5;
	
	/**
	 * Empty constructor
	 */
	public Portfolio() {
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.title = "Portfolio title";
		this.portfolioSize = 0;
	}
	
	/**
	 * Creates a new constructor with the given title and stocks array.
	 * @param title - The title of this portfolio.
	 * @param stocks - An array of stocks.
	 */
	public Portfolio(String title, Stock[] stocks) {
		this();
		this.title = title;
		
		while (this.portfolioSize < MAX_PORTFOLIO_SIZE) {
			if (stocks[this.portfolioSize] == null) {
				break;
			}

			this.stocks[this.portfolioSize] = new Stock(stocks[this.portfolioSize]);
			this.portfolioSize++;
		}
	}
	
	/**
	 * Copy constructor
	 * @param portfolio
	 */
	public Portfolio(Portfolio portfolio) {
		this(portfolio.title, portfolio.stocks);
	}
	
	/**
	 * Adds the given stock to the stocks array.
	 * @param stock - the stock to be added to the stocks array.
	 */
	public void addStock(Stock stock) {
		this.stocks[this.portfolioSize] = stock;
		this.portfolioSize++;
	}
	
	/**
	 * Removes a stock from the required index. Shifts all the stocks that their indexes are bigger than 
	 * the given index, one position down, such that a stock at index 1 will move to index 0, if the given
	 * index of the stock to be removed was 0.
	 * @param index - the index of the stocks array which points to the stock that will be removed.
	 * @throws Exception in case the given index doesn't point to a stock in the stocks array.
	 */
	public void removeStock(int index) throws Exception {
		if (index < 0 || index >= this.portfolioSize) {
			throw new Exception("There's no stocks to be removed at this index.");
		}
		
		while (index < this.portfolioSize - 1) {
			this.stocks[index] = this.stocks[index + 1];
			index++;
		}
		
		this.stocks[index] = null;
		this.portfolioSize = index;
	}
	
	public Stock[] getStocks() {
		return this.stocks;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public int getPortfolioSize() {
		return this.portfolioSize;
	}
	
	/**
	 * Builds an HTML string which presents this portfolio details.
	 * @return an HTML string which presents this portfolio details.
	 */
	public String getHtmlString() {
		StringBuilder sb = new StringBuilder(String.format("<h1>%s</h1>", this.title));
		for (int i = 0 ; i < this.portfolioSize ; i++) {
			sb.append(String.format("<br>%s", getStocks()[i].getHtmlDescription()));
		}
		
		return sb.toString();
	}
}