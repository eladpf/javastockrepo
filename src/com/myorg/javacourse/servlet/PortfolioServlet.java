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
		Portfolio portfolio = portfolioManager.getPortfolio();
		
		resp.getWriter().println(portfolio.getHtmlString());
	}
}