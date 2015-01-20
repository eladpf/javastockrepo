package com.myorg.javacourse.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A Stock class that contains all the necessary information of a stock and the actions which can be 
 * performed on such a stock.
 * @author Elad.Avidan
 *
 */
public class Stock {

	private String symbol;
	private float ask;
	private float bid;
	private Date date;
	private int recommendation;
	private int stockQuantity;
	
	public static final int BUY = 0;
	public static final int SELL = 1;
	public static final int REMOVE = 2;
	public static final int HOLD = 3;
	
	/**
	 * Empty constructor
	 */
	public Stock() { }
	
	/**
	 * Constructor which initializes some of the stock's fields.
	 * @param symbol - stock's symbol
	 * @param ask - stock's ask
	 * @param bid - how much bid this stock has
	 * @param date - stock's creation date
	 */
	public Stock(String symbol, float ask, float bid, Date date) {
		this.symbol = symbol;
		this.ask = ask;
		this.bid = bid;
		this.date = new Date();
		this.date.setTime(date.getTime());
	}
	
	/**
	 * Constructor which initializes all of the stock's fields.
	 *@param symbol - stock's symbol
	 * @param ask - stock's ask
	 * @param bid - how much bid this stock has
	 * @param date - stock's creation date
	 * @param recommendation - number of recommendation
	 * @param stockQuantity - stock's quantity
	 */
	public Stock(String symbol, float ask, float bid, Date date, int recommendation, int stockQuantity) {
		this(symbol, ask, bid, date);
		this.recommendation = recommendation;
		this.stockQuantity = stockQuantity;
	}
	
	/**
	 * Copy constructor
	 * @param stock - copy all of the given stock's details
	 */
	public Stock(Stock stock) {
		this(stock.symbol, stock.ask, stock.bid, stock.date, stock.recommendation, stock.stockQuantity);
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public float getAsk() {
		return ask;
	}

	public void setAsk(float ask) {
		this.ask = ask;
	}

	public float getBid() {
		return bid;
	}

	public void setBid(float bid) {
		this.bid = bid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * Builds an HTML string which presents this stock details.
	 * @return an HTML string which presents this stock details.
	 */
	public String getHtmlDescription() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");    
		return String.format("<b>Stock symbol</b>: %s, <b>ask</b>: %s, <b>bid</b>: %s, <b>date</b>: %s", getSymbol(), getAsk(), getBid(), sdf.format(getDate()));
	}
}
