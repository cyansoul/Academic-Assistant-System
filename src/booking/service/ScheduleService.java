package booking.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import booking.bean.Schedule;
import booking.dao.IObjectDao;
import booking.util.DateTimeUtil;

@Transactional
@Service
public class ScheduleService implements IBaseService<Schedule> {
	@Resource
	private IObjectDao<Schedule> scheduleDao;

	@Override
	public void insert(Schedule object) {
		scheduleDao.insert(object);
	}

	@Override
	public void delete(Schedule object) {
		scheduleDao.delete(object);
	}

	@Override
	public void update(Schedule newObj) {
		scheduleDao.update(newObj);
	}

	@Override
	public Schedule getOne(Schedule object) {
		List<Schedule> list = scheduleDao.getByParam(object);
		if (list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Schedule> getAll() {
		return scheduleDao.getAll();
	}

	@Override
	public void saveObjects(List<Schedule> objs) {

	}

	@Override
	public List<Schedule> queryByParm(Schedule obj) {
		return scheduleDao.getByParam(obj);
	}

	/**
	 * generate professor's booked status through start and end date
	 * @param startDate
	 * @param endDate
	 * @param list
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public List<Map<String, Object>> createBookingStatus(Date startDate, Date endDate, List<Schedule> schedulelist) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Date currentDate = startDate;
		while (currentDate.getTime() <= endDate.getTime()) {
			Map<String, Object> monthStatus = new HashMap<String, Object>();
			monthStatus.put("week", currentDate.getDay());
			monthStatus.put("status", false);
			for (Schedule schedule : schedulelist) {
				if (schedule.getBookingDate().getTime() == currentDate.getTime()) {
					monthStatus.put("status", true);
					monthStatus.put("scheduleId", schedule.getId());
				}
			}
			monthStatus.put("date", DateTimeUtil.FormatNoTime(new Timestamp(currentDate.getTime())));
			list.add(monthStatus);
			currentDate.setDate(currentDate.getDate() + 1);
		}
		return list;
	}

	/**
	 * get one day's detail by ID
	 * @param schedule
	 * @return
	 */
	public Schedule getById(Schedule schedule) {
		return scheduleDao.getOne(schedule);
	}
}
