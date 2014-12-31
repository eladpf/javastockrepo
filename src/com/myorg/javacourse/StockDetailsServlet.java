package com.myorg.javacourse;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class StockDetailsServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		
		Calendar cal = Calendar.getInstance();
		cal.set(2014, Calendar.NOVEMBER, 15);
		
		Stock stock1 = new Stock("PIH", 13.1f, 12.4f, cal.getTime());
		Stock stock2 = new Stock("AAL", 5.78f, 5.5f, cal.getTime());
		Stock stock3 = new Stock("CAAS", 32.2f, 31.5f, cal.getTime());
		
		resp.getWriter().println(String.format("%s<br>%s<br>%s", stock1.getHtmlDescription(), stock2.getHtmlDescription(), stock3.getHtmlDescription()));
	}
}
