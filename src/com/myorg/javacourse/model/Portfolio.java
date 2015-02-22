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
	private float balance;
	public enum ALGO_RECOMMENDATION {BUY, SELL, REMOVE, HOLD}
	
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
	 * Adds the given stock to the stocks array. If stock already exists - does nothing.
	 * @param stock - the stock to be added to the stocks array.
	 * @return true if operation successful. Otherwise, false.
	 */
	private boolean addStock(Stock stock) {
		if (this.portfolioSize == MAX_PORTFOLIO_SIZE) {
			System.out.println(String.format("Can’t add new stock, portfolio can have only %d stocks", MAX_PORTFOLIO_SIZE));
			return false;
		}
		
		if (getStockIndexBySymbol(stock.getSymbol()) < 0) {
			stock.setQuantity(0);
			this.stocks[this.portfolioSize] = stock;
			this.portfolioSize++;
		}
		
		return true;
	}

	/**
	 * Returns the index of the stock which matches the given symbol. If no such stock was found, returns -1.
	 * @param symbol - the symbol of the stock to get its index in the stocks array.
	 * @return the stock's index or -1 if wasn't found.
	 */
	private int getStockIndexBySymbol(String symbol) {
		for (int i = 0; i < this.portfolioSize; i++) {
			if (this.stocks[i].getSymbol().equals(symbol)) {
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * Removes a stock which matches the given symbol. Shifts all the stocks that their indexes are bigger than 
	 * the index of the stock to be removed, one position down, such that a stock at index 1 will move to index 0, 
	 * if the given index of the stock to be removed was 0.
	 * @param symbol - the symbol of the stock that will be removed.
	 * @return true if operation successful. Otherwise, false.
	 */
	public boolean removeStock(String symbol) {
		int index = getStockIndexBySymbol(symbol);
		if (index < 0) {
			System.out.println(String.format("There's no stocks that match the symbol %s to be removed.", symbol));
			return false;
		}
		
		boolean isSuccessfulSell = sellStock(symbol, -1);
		if (!isSuccessfulSell) {
			System.out.println(String.format("Failed to sell %s.", this.stocks[index].toString()));
			return false;
		}
		
		// shift stocks with higher index:
		while (index < this.portfolioSize - 1) {
			this.stocks[index] = this.stocks[index + 1];
			index++;
		}
		
		this.stocks[index] = null;
		this.portfolioSize = index;
		
		return true;
	}
	
	/**
	 * Sell a number of stocks that matches the given symbol by the given quantity. If quantity=(1)
	 * then sell the whole quantity of this stock.
	 * @param symbol - the symbol’s stock to sell.
	 * @param quantity - number of this symbol’s stock to sell.
	 * @return true if operation successful. Otherwise, false.
	 */
	public boolean sellStock(String symbol, int quantity) {
		if (quantity < -1) {
			System.out.println("Quantity must be positive or -1 in order to sell whole quantity of this stock.");
			return false;
		}
		
		int index = getStockIndexBySymbol(symbol);
		if (index < 0) {
			System.out.println(String.format("There's no stocks that match the symbol %s to sell.", symbol));
			return false;
		}
		
		Stock stock = this.stocks[index];
		if (stock.getQuantity() < quantity) {
			System.out.println("Not enough stocks to sell.");
			return false;
		}
		
		if (quantity == -1) {
			quantity = stock.getQuantity();
		}
		
		stock.setQuantity(stock.getQuantity() - quantity);
		this.balance += quantity * stock.getBid();
		return true;
	}
	
	/**
	 * Buy a number of stocks that matches the given symbol by the given quantity. If quantity=(1)
	 * then buy this stock with the full balance sum. If the balance is not in exact multiplication of 
	 * stock value (the “ask” value) the reminder will be left in balance. For example, if the ask value
	 * is 30$ and the balance is 100$, the buyStock with quantity of 1 will purchase 3 stocks and leave 
	 * 10$ in balance.
	 * @param stock - the stock to buy.
	 * @param quantity - number of stocks to buy.
	 * @return true if operation successful. Otherwise, false.
	 */
	public boolean buyStock(Stock stock, int quantity) {
		if (quantity < -1) {
			System.out.println("Quantity must be positive or -1 in order to buy this stock with the full balance sum.");
			return false;
		}
		
		if (quantity == -1) {
			quantity = stock.getQuantity();
		}
		
		// check if it's possible to purchase the required quantity
		int ableToPurchase = (int) (this.balance / stock.getAsk());
		if (ableToPurchase < quantity) {
			quantity = ableToPurchase;
		}
		
		int index = getStockIndexBySymbol(stock.getSymbol());
		if (index < 0) {
			stock = new Stock(stock);
			addStock(stock);
		} else {
			stock = this.stocks[index];
			stock.setQuantity(stock.getQuantity() + quantity);
		}
		
		stock.setQuantity(quantity);
		this.balance -= quantity * stock.getAsk();;
		return true;
	}
	
	/**
	 * Updates the balance with the given amount.
	 * @param amount - the amount to add to the portfolyo's balance (can be negative amount).
	 * @return true if operation successful. Otherwise, false.
	 */
	public boolean updateBalance(float amount) {
		float temp = this.balance + amount;
		if (temp < 0) {
			System.out.println("Not enough balance to complete purchase.");
			return false;
		}
		
		this.balance = temp;
		return true;
	}
	
	/**
	 * Gets the total value of all stocks
	 * @return the total value of all stocks.
	 */
	public float getStocksValue() {
		float value = 0;
		for (int i = 0; i < this.portfolioSize ;i++) {
			value += this.stocks[i].getAsk() * this.stocks[i].getQuantity();
		}
		
		return value;
	}
	
	/**
	 * Gets the sum of all stocks’ value and portfolio’s balance.
	 * @return the sum of all stocks’ value and portfolio’s balance.
	 */
	public float getTotalValue() {
		return this.balance + getStocksValue();
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
	
	public float getBalance() {
		return this.balance;
	}
	
	/**
	 * Builds an HTML string which presents this portfolio details.
	 * @return an HTML string which presents this portfolio details.
	 */
	public String getHtmlString() {
		StringBuilder sb = new StringBuilder(String.format("<h1>%s</h1>", this.title));
		sb.append(String.format("<br>Total portfolio value: %s$, Total stocks value: %s$, Balance: %s$", this.getTotalValue(), this.getStocksValue(), this.balance));
		for (int i = 0 ; i < this.portfolioSize ; i++) {
			sb.append(String.format("<br>%s", getStocks()[i].getHtmlDescription()));
		}
		
		return sb.toString();
	}
}