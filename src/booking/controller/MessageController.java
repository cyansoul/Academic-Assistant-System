package booking.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import booking.bean.MessageBoard;
import booking.bean.User;
import booking.service.MessageBoardService;
import booking.util.Page;
import booking.util.SessionUtil;

@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {

	@Autowired
	private MessageBoardService messageBoardService;

	@RequestMapping("/list")
	public String list(Page page, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(SessionUtil.KEY_USER_INFO);
		MessageBoard messageBoard = new MessageBoard();
		messageBoard.setAccepter(user);
		long count = messageBoardService.queryCount(messageBoard);
		long total = (long) (Math.ceil(count / (double) page.getPageSize()));

		if (page.getCurrentPage() == 0) {
			page.setPageSize(5);
			page.setCurrentPage(1);
		}

		page.setTotal(total);

		List<MessageBoard> list = messageBoardService.queryByParmPage(messageBoard, page);
		messageBoardService.updateMessageBoard(list);
		request.setAttribute("messageList", list);
		request.setAttribute("page", page);
		if (user.getRole() == 1) {
			return "student/message/list";
		} else if (user.getRole() == 2) {
			return "professor/message/list";
		} else {
			return "";
		}
	}

	@RequestMapping(value = "/query_message", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryMessage(MessageBoard message) {
		Map<String, Object> map = new HashMap<String, Object>();
		message = messageBoardService.getOne(message);
		map.put("success", true);
		map.put("data", message);
		if (message == null) {
			map.put("success", false);
		}
		return map;
	}

	@RequestMapping(value = "/add_message", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addMessage(MessageBoard message, HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			User user = (User) request.getSession().getAttribute(SessionUtil.KEY_USER_INFO);
			message.setSender(user);
			message.setBuildDate(new Date());
			message.setStatus(0);
			messageBoardService.insert(message);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		return map;
	}
}
