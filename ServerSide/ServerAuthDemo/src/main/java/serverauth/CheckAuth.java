/*
 * Servlet filter which works on all routes to check whether a page can be accessed based on current user state
 */
package serverauth;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class CheckAuth extends HttpFilter implements Filter {
    
	private static final long serialVersionUID = 1L;
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRes = (HttpServletResponse) response;
		
		// Disabling caching in order to prevent the user from visiting restricted pages when not intended
		httpRes.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
		httpRes.setHeader("Pragma", "no-cache"); // HTTP 1.0
		httpRes.setDateHeader("Expires", 0); // Proxy Server
		
		String[] parts = httpReq.getServletPath().split("/");
		
		String page = parts[parts.length - 1];
		
		HttpSession session = httpReq.getSession();
		
		// If user is not logged in then prevent user from accessing welcome page
		// Else if user is logged in then only allow welcome page to be accessed
		if (page.equals("welcome.jsp") && session.getAttribute("user") == null) {
			httpRes.sendRedirect(httpReq.getContextPath() + "/index.jsp");
			return;
		} else if (!page.equals("welcome.jsp") && !page.equals("logout") && session.getAttribute("user") != null) {
			httpRes.sendRedirect(httpReq.getContextPath() + "/pages/welcome.jsp");
			return;
		}
		
		chain.doFilter(request, response);
	}


}
