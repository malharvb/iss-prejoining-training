/*
 * Servlet to perform user login operation
 */
package serverauth;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import serverauth.dao.UserDao;
import serverauth.model.UserModel;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = (String) request.getParameter("password");
		
		HttpSession session = request.getSession();
		UserModel user = null;
		
		session.removeAttribute("loginErrorMsg");
		
		// Error handling performed on back-end as well as front-end to prevent users from manipulating JS on front-end and sending invalid data
		String errorMsg = validateFormData(email);
		
		if (errorMsg != null) {
			session.setAttribute("loginErrorMsg", errorMsg);
			
			response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
			return;
		}
		
		try {
			user = UserDao.getUser(email);
			
			// Password is null when no user is found with the given email
			if (user.getPassword() == null) {
				session.setAttribute("loginErrorMsg", "Please enter a registered email");
				
				response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
				return;
			}
			
			// Given password does not match with stored password
			if (!BCrypt.checkpw(password, user.getPassword())) {
				System.out.println(user.getPassword() + " " + password);
				session.setAttribute("loginErrorMsg", "Please make sure your password is correct");
				
				response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
				return;
			}
			
		} catch (SQLException ex) {
			ErrorLogger.log(ex.getMessage());
			session.setAttribute("loginErrorMsg", ex);
			response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
			return;
		}

		// Redirect to welcome page after successful login and maintain session
		session.setAttribute("user", user);
		response.sendRedirect(request.getContextPath() + "/pages/welcome.jsp");
	}
	
	
	private String validateFormData(String email) throws IOException {
		
		// Email Validation
		boolean isVaildEmail = Pattern.matches("^\\S+@\\S+\\.\\S+$", email);
		
		if (!isVaildEmail) {
			return "Please enter a valid email";
		}
		
		return null;
	}

}
