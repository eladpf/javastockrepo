package com.myorg.javacourse;
import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class StocksAppServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//		int num1 = 4;
//		int num2 = 3;
//		int num3 = 7;
//		int result = (num1 + num2) * num3;
//		String resultStr = String.format("<h1>Result of (%d + %d) * %d = %d</h1>", num1, num2, num3, result);
		
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
