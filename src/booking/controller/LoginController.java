package booking.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import booking.bean.User;
import booking.service.IBaseService;
import booking.util.FileUtil;
import booking.util.SessionUtil;

@Controller
@RequestMapping("/welcome")
public class LoginController extends BaseController {
	
	@Resource
	private IBaseService<User> userService;

	public IBaseService<User> getUserService() {
		return userService;
	}

	public void setUserService(IBaseService<User> userService) {
		this.userService = userService;
	}

	/**
	 * jump to register page
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/register")
	public String register(HttpServletRequest request, User user) {
		// avoid submitting form repeatedly
		String randomCode = SessionUtil.createValidateCode(request);
		request.setAttribute("randomCode", randomCode);
		request.setAttribute("role", user.getRole());
		return "register";
	}

	@RequestMapping(value = "/doRegister", method = RequestMethod.POST)
	public void doRegister(User user, HttpServletRequest request, HttpServletResponse response, Model model)
			throws IOException {
		try {
			String randomCode = request.getParameter("randomCode");
			if (!SessionUtil.valideRandomCode(request, randomCode)) {
				model.addAttribute("message", "Don't submit the form again!");
				response.sendRedirect(request.getContextPath() + "/welcome/register");
				return;
			}
			SessionUtil.destroyRandomCode(request);

			// initiate applicationContext and pass it to
			// CommonsMultiparResolver
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());

			// check if there is enctype="multipart/form-date" in form
			if (multipartResolver.isMultipart(request)) {

				// transfer request to multiRequest
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

				// get all file names in multiRequest
				Iterator iter = multiRequest.getFileNames();

				while (iter.hasNext()) {
					// get all files
					MultipartFile file = multiRequest.getFile(iter.next().toString());
					if (file != null) {
						String photoPath = FileUtil.uploadImage(file,
								request.getServletContext().getRealPath("/upload"));
						user.setPhoto(photoPath);
						userService.insert(user);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
			response.sendRedirect(request.getContextPath() + "/register");
			return;
		}
		response.sendRedirect(request.getContextPath() + "/welcome/login");
	}

	@RequestMapping(value = "/handleLogin", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> handleLogin(User user, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		User query = new User();
		query.setUsername(user.getUsername());
		query.setPassword(user.getPassword());
		User currentUser = userService.getOne(query);
		if (currentUser != null) {
			SessionUtil.saveUerInSession(request, currentUser);
			map.put("result", "success");
			map.put("role", currentUser.getRole());
		} else {
			map.put("result", "fail");
			map.put("message", "username or password is error!");
		}
		return map;

	}

	@RequestMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// avoid submitting form repeatedly
		User user = (User) request.getSession().getAttribute(SessionUtil.KEY_USER_INFO);
		if (user != null) {
			SessionUtil.destoryUser(request);
		}
		if (user.getRole() == 1) {
			response.sendRedirect(request.getContextPath() + "/student");
		} else {

			response.sendRedirect(request.getContextPath() + "/professor");
		}
	}

	@RequestMapping("/dispatch")
	public View dispatch(HttpServletRequest request, Model model) {
		Set<String> roles = AuthorityUtils
				.authorityListToSet(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		if (roles.contains("ROLE_PROFESSOR")) {
			return new RedirectView(request.getContextPath() + "/professor");
		} else {
			return new RedirectView(request.getContextPath() + "/student");
		}
	}

	/**
	 * jump to login page
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		return "login";
	}
}
