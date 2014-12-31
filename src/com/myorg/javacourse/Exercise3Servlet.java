package com.myorg.javacourse;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Exercise3Servlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		
		// 1. Calculate circle:
		int radius = 50;
		double area = Math.PI * Math.pow(radius, 2);
		
		// 2. Calculate the length of triangles ‘opposite’:
		int bAngleDegrees = 30;
		int hypotenuseLength = 50;
		double bAngleInRadians = Math.toRadians(bAngleDegrees);
		int oppositeLength = (int) Math.ceil(Math.sin(bAngleInRadians) * hypotenuseLength);
		
		// 3. Calculate the power of base=20 and exponent=13:
		int base = 20;
		int exp = 13;
		double power = Math.pow(base, exp);
		
		String line1 = String.format("calculation 1: Area of circle with radius %d is: %s square-cm.", radius, area);
		String line2 = String.format("calculation 2: Length of opposite where angle B is %d degrees and Hypotenuse length is %d cm is: %s cm.", bAngleDegrees, hypotenuseLength, oppositeLength);
		String line3 = String.format("calculation 3: Power of %d with exp of %d is: %s", base, exp, power);

		String resultStr = String.format("<b>%s<br>%s<br>%s<b>", line1, line2, line3);
		resp.getWriter().println(resultStr);
	}
}
