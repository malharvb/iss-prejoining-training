/*
 * Demonstrate basics of Servlets
 */
package serverpack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter htmlWriter = response.getWriter();
		response.setContentType("text/html");
		htmlWriter.write("<h1>Hello World!</h1>");
		htmlWriter.write("<h2>Malhar Kajale</h2>");
	}

}
