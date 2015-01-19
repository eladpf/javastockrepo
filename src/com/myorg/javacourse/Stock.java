package com.myorg.javacourse;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	public Stock() { }
	
	public Stock(String symbol, float ask, float bid, Date date) {
		this.symbol = symbol;
		this.ask = ask;
		this.bid = bid;
		this.date = new Date();
		this.date.setTime(date.getTime());
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
	
	public String getHtmlDescription() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");    
		return String.format("<b>Stock symbol</b>: %s, <b>ask</b>: %s, <b>bid</b>: %s, <b>date</b>: %s", getSymbol(), getAsk(), getBid(), sdf.format(getDate()));
	}
}
