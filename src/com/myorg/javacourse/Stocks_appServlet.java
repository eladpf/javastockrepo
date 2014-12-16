package com.myorg.javacourse;
import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Stocks_appServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int num1 = 4;
		int num2 = 3;
		int num3 = 7;
		int result = (num1 + num2) * num3;
		String resultStr = String.format("<h1>Result of (%d + %d) * %d = %d</h1>", num1, num2, num3, result);
		
		resp.setContentType("text/html");
		resp.getWriter().println(resultStr);
	}
}
