/*
 * Servlet to perform user registration
 */
package serverauth;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import serverauth.dao.UserDao;
import serverauth.model.UserModel;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm-password");
		
		HttpSession session = request.getSession();
		UserModel user = null;
		
		session.removeAttribute("registerErrorMsg");
		
		// Error handling performed on back-end as well as front-end to prevent users from manipulating JS on front-end and sending invalid data
		String errorMsg = validateFormData(email, password, confirmPassword);
		
		if (errorMsg != null) {
			session.setAttribute("registerErrorMsg", errorMsg);
			
			response.sendRedirect(request.getContextPath() + "/pages/register.jsp");
			return;
		}
		
		try {
			user = UserDao.addUser(email, password);
		} catch (SQLException ex) {
			ErrorLogger.log(ex.getMessage());
			
			if (ex.getErrorCode() == 1062) {
				session.setAttribute("registerErrorMsg", "Please use another email id. The given email id is already in use.");
			} else {
				session.setAttribute("registerErrorMsg", ex);
			}
			
			response.sendRedirect(request.getContextPath() + "/pages/register.jsp");
			return;
		}

		// Redirect to welcome page after successful registration and maintain session
		session.setAttribute("user", user);
		response.sendRedirect(request.getContextPath() + "/pages/welcome.jsp");
	}
	
	private String validateFormData(String email, String password, String confirmPassword) throws IOException {
		
		// Email Validation
		boolean isVaildEmail = Pattern.matches("^\\S+@\\S+\\.\\S+$", email);
		
		if (!isVaildEmail) {
			return "Please enter a valid email";
		}
		
		ArrayList<String> passwordError = new ArrayList<>();

        // Lower-case letter validation
        if (!password.matches(".*[a-z].*")) {
            passwordError.add("It must have a lower-case letter");
        }

        // Upper-case letter validation
        if (!password.matches(".*[A-Z].*")) {
            passwordError.add("It must have an upper-case letter");
        }

        // Numbers validation
        if (!password.matches(".*[0-9].*")) {
            passwordError.add("It must have a number");
        }

        // Length validation
        if (password.length() <= 8) {
            passwordError.add("It must be longer than 8 characters");
        }

        // Matches confirm password validation
        if (!password.equals(confirmPassword)) {
            passwordError.add("Password and confirm password fields should match");
        }

        if (!passwordError.isEmpty()) {
            StringBuilder errorMsg = new StringBuilder();
            errorMsg.append("Please make sure your password meets the below criteria:\n");

            for (String error : passwordError) {
            	errorMsg.append("- ").append(error).append("\n");
            }
            
            
            return errorMsg.toString();
        }
		
		return null;
	}

}
