package com.myorg.javacourse.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myorg.javacourse.model.Portfolio;
import com.myorg.javacourse.service.PortfolioService;

/**
 * This portfolio's servlet will receive requests from the client to do manipulations on portfolios
 * @author Elad.Avidan
 *
 */
@SuppressWarnings("serial")
public class PortfolioServlet extends HttpServlet {

	/**
	 * An HTTP GET request that currently doesn't do anything important.
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");

		PortfolioService portfolioManager = new PortfolioService();
		Portfolio portfolio1 = portfolioManager.getPortfolio();
		portfolio1.setTitle("Portfolio #1");
		Portfolio portfolio2 = new Portfolio(portfolio1);
		portfolio2.setTitle("Portfolio #2");

		resp.getWriter().println("<br><h1>Portfolios after copying Portfolio #2 from Portfolio #1:<h1>");
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		
		try {
			portfolio1.removeStock(0);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
		resp.getWriter().println("<br><h1>Portfolios after removing first stock from Portfolio #1:<h1>");
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		
		portfolio2.getStocks()[portfolio2.getPortfolioSize() - 1].setBid(55.55f);
		
		resp.getWriter().println("<br><h1>Portfolios after setting last stock's bid of Portfolio #2 to 55.55:<h1>");
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
	}
}