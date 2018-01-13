package booking.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import booking.bean.MessageBoard;
import booking.bean.Schedule;
import booking.bean.User;
import booking.service.IBaseService;
import booking.service.MessageBoardService;
import booking.service.ScheduleService;
import booking.util.SessionUtil;

@Controller
@RequestMapping("/professor")
public class ProfessorController extends BaseController {

	@Autowired
	private IBaseService<User> userService;

	@Autowired
	private ScheduleService scheduleSevice;

	@Autowired
	private MessageBoardService messageBoardService;

	@RequestMapping("")
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = (User) request.getSession().getAttribute(SessionUtil.KEY_USER_INFO);
		model.addAttribute("user", user);

		Schedule schedule = new Schedule();
		schedule.setProfessor(user);
		schedule.setStartDate(new Date());
		schedule.setStatus(0);

		List<Schedule> list = scheduleSevice.queryByParm(schedule);
		int count = list != null ? list.size() : 0;

		model.addAttribute("scheduleCount", count);

		MessageBoard messageBoard = new MessageBoard();
		messageBoard.setAccepter(user);
		messageBoard.setStatus(0);

		long messageCount = messageBoardService.queryCount(messageBoard);
		model.addAttribute("messageCount", messageCount);
		return "professor/index";
	}

	@RequestMapping("/list")
	public String list() {
		return "professor/schedule/list";
	}

	@RequestMapping("/main")
	public String main(HttpServletRequest request, Model model) {
		User professor = (User) request.getSession().getAttribute(SessionUtil.KEY_USER_INFO);
		Schedule schedule = new Schedule();
		schedule.setProfessor(professor);
		schedule.setStartDate(new Date());
		List<Schedule> list = scheduleSevice.queryByParm(schedule);
		request.setAttribute("list", list);
		return "professor/main";
	}

	@RequestMapping(value = "/load_booking", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loadBooking(Schedule schedule, HttpServletRequest request,
			HttpServletResponse response) {
		if (schedule == null) {
			schedule = new Schedule();
		}
		User professor = (User) request.getSession().getAttribute(SessionUtil.KEY_USER_INFO);
		schedule.setProfessor(professor);
		List<Schedule> scheduleList = scheduleSevice.queryByParm(schedule);

		// generate professor's booking status
		List<Map<String, Object>> list = scheduleSevice.createBookingStatus(schedule.getStartDate(),
				schedule.getEndDate(), scheduleList);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		map.put("data", list);
		return map;
	}

	@RequestMapping(value = "/query_schedule", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> querySchedule(Schedule schedule) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (schedule == null) {
			schedule = new Schedule();
		}
		schedule = scheduleSevice.getById(schedule);
		if (schedule != null && schedule.getStatus() == 0) {
			schedule.setStatus(1);
			scheduleSevice.update(schedule);
		}
		map.put("success", true);
		map.put("data", schedule);
		if (schedule == null) {
			map.put("success", false);
		}
		return map;
	}
}
