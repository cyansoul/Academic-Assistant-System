package booking.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import booking.bean.User;
import booking.service.IBaseService;
import booking.util.SessionUtil;

public class RequestInterceptor implements HandlerInterceptor {

	@Autowired
	private IBaseService<User> userService;

	public IBaseService<User> getUserService() {
		return userService;
	}

	public void setUserService(IBaseService<User> userService) {
		this.userService = userService;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView arg3)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SessionUtil.KEY_USER_INFO);
		String accept = request.getHeader("accept");
		if (user != null) { // if user has logged in
			User currentUser = userService.getOne(user);
			if (currentUser == null) {
				// user does not exist in database
				session.invalidate();
				return false;
			}
			session.setAttribute(SessionUtil.KEY_USER_INFO, currentUser);
		} else {
			String requestUrl = request.getRequestURI();
			if (accept != null && accept.startsWith("text/html")) { // check if it is request for static resource

				// if request is not login page, jump to login page
				if (requestUrl == null || requestUrl.indexOf("/welcome/login") == -1) {
					response.sendRedirect(request.getContextPath() + "/welcome/login");
					return false;
				}
			}
		}
		return true;
	}

}
