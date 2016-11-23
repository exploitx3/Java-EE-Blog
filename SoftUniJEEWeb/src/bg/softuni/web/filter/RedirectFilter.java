package bg.softuni.web.filter;

import bg.softuni.entity.UserModel;
import bg.softuni.entity.enums.UserTypes;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;


public class RedirectFilter implements Filter, Serializable {


	private static final long serialVersionUID = 1L;

	public static final String PATH_INDEX = "/index.jsp";

	public static final String PATH_PROFILE = "/blog/user/profile.html";


	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void destroy() {
	}

	/**
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		String requestedPath = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

		/*
		 * Skip the action with path "index.jsp" - there is no logged user, but
		 * the user is redirected to login screen
		 */
		if (PATH_INDEX.equals(requestedPath)) {
			chain.doFilter(request, response);
			return;
		}

				/*
		 * Get logged user from the HttpSession
		 */
		HttpSession session = httpRequest.getSession();
		UserModel loggedUser = (UserModel) session.getAttribute("LOGGED_USER");


		/*
		 * Redirect to login page if there is no logged user and trying to access protected resource
		 */

		if (loggedUser != null){
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_PROFILE);
			requestDispatcher.forward(request, response);
			return;
		} else {
			chain.doFilter(request, response);
			return;
		}
	}
}
