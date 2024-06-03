/*
 * Servlet to perform user logout operation
 */
package serverauth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Remove user attribute from session to remove user login connection to session
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		
		// Remove any previous server error messages
		session.removeAttribute("registerErrorMsg");
		session.removeAttribute("loginErrorMsg");
		
		// Finally invalidate any remaining session objects and redirect to home page
		session.invalidate();
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

}
