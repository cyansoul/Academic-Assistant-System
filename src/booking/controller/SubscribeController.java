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
@RequestMapping("/student")
public class SubscribeController extends BaseController {

	@Autowired
	private IBaseService<User> userService;

	@Autowired
	private ScheduleService scheduleSerice;

	@Autowired
	private MessageBoardService messageBoardService;

	@RequestMapping("")
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = (User) request.getSession().getAttribute(SessionUtil.KEY_USER_INFO);
		model.addAttribute("user", user);
		return "student/index";
	}

	@RequestMapping("/list")
	public String list() {
		return "student/subscribe/list";
	}

	/**
	 * jump to login page
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		return "student/login";
	}

	@RequestMapping(value = "/search_user", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> searchUser(User user, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		User currentUser = (User) request.getSession().getAttribute(SessionUtil.KEY_USER_INFO);
		user = userService.getOne(user);
		if (user != null) {
			if (currentUser.getId() == user.getId()) {
				map.put("result", "fail");
				map.put("message", "Don't send message to youself!");
			} else {
				map.put("result", "success");
				map.put("user", user);
			}
		} else {
			map.put("result", "fail");
			map.put("message", "It does not exist!");
		}
		return map;
	}

	@RequestMapping(value = "/load_booking", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loadBooking(@RequestParam String proId, Schedule schedule, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Long professorId = Long.valueOf(proId);
		if (schedule == null) {
			schedule = new Schedule();
		}
		User professor = new User();
		professor.setId(professorId);
		schedule.setProfessor(professor);
		User student = (User) request.getSession().getAttribute(SessionUtil.KEY_USER_INFO);
		List<Schedule> scheduleList = scheduleSerice.queryByParm(schedule);

		// generate professor's booked status
		List<Map<String, Object>> list = scheduleSerice.createBookingStatus(schedule.getStartDate(),
				schedule.getEndDate(), scheduleList);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		map.put("data", list);
		return map;
	}

	@RequestMapping(value = "/addSchedule", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addSchedule(String professorId, Schedule schedule, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Long proId = Long.valueOf(professorId);
			if (schedule == null) {
				schedule = new Schedule();
			}

			User professor = new User();
			professor.setId(proId);
			User student = (User) request.getSession().getAttribute(SessionUtil.KEY_USER_INFO);
			schedule.setProfessor(professor);
			schedule.setStudent(student);
			schedule.setStatus(0);
			scheduleSerice.insert(schedule);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		return map;
	}
}
