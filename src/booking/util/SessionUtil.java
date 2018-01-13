package booking.util;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import booking.bean.User;

public class SessionUtil {
	public static final String KEY_USER_INFO = "USER_IN_SESSION";
	public static final String RANDOM_IN_SESSION = "RANDOM_IN_SESSION";
	
	/**
	 * generate random code and save it in session
	 * @param request
	 * @return random code
	 */
	public static String createValidateCode(HttpServletRequest request){
		String randomCode = UUID.randomUUID().toString();
		request.getSession().setAttribute(RANDOM_IN_SESSION, randomCode);
		return randomCode;
		
	}
	
	/**
	 * check if random code in session are the same
	 * @param request
	 * @param randomCode
	 * @return
	 */
	public static boolean valideRandomCode(HttpServletRequest request,String randomCode){
		String codeInSession = (String) request.getSession().getAttribute(RANDOM_IN_SESSION);
		if(randomCode.equals(codeInSession)){
			return true;
		}
		return false;
	}
	
	/**
	 * destroy random code in session
	 * @param request
	 */
	public static void destroyRandomCode(HttpServletRequest request){
		request.getSession().setAttribute(RANDOM_IN_SESSION, null);
	}
	
	/**
	 * save user in session
	 * @param request
	 * @param user
	 */
	public static void saveUerInSession(HttpServletRequest request,User user){
		request.getSession().setAttribute(KEY_USER_INFO, user);
	}
	public static void destoryUser(HttpServletRequest request){
		request.getSession().setAttribute(KEY_USER_INFO, null);
	}
}
