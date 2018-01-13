package booking.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import booking.bean.MessageBoard;
import booking.bean.Schedule;
import booking.bean.User;
import booking.service.IBaseService;
import booking.service.MessageBoardService;
import booking.service.ScheduleService;
import booking.util.SessionUtil;

@Controller
@RequestMapping()
public class MainController extends BaseController {

	@Autowired
	private IBaseService<User> userService;

	@Autowired
	private ScheduleService scheduleSerice;

	@Autowired
	private MessageBoardService messageBoardService;

	@RequestMapping()
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = (User) request.getSession().getAttribute(SessionUtil.KEY_USER_INFO);
		model.addAttribute("user", user);
		if (user.getRole() == 1) {
			return "student/index";
		} else {
			Schedule schedule = new Schedule();
			schedule.setProfessor(user);
			schedule.setStartDate(new Date());
			schedule.setStatus(0);
			List<Schedule> list = scheduleSerice.queryByParm(schedule);
			int count = list != null ? list.size() : 0;
			model.addAttribute("scheduleCount", count);

			MessageBoard messageBoard = new MessageBoard();
			messageBoard.setAccepter(user);
			messageBoard.setStatus(0);
			long messageCount = messageBoardService.queryCount(messageBoard);
			model.addAttribute("messageCount", messageCount);
			return "professor/index";
		}
	}

	@RequestMapping("/main")
	public String main() {
		return "main";
	}
}
