package booking.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import booking.bean.User;
import booking.dao.IObjectDao;

@Transactional
@Service
public class UserService implements IBaseService<User> {
	@Resource
	private IObjectDao<User> userDao;

	public IObjectDao<User> getUserDao() {
		return userDao;
	}

	public void setUserDao(IObjectDao<User> userDao) {
		this.userDao = userDao;
	}

	@Override
	public void insert(User object) {
		userDao.insert(object);
	}

	@Override
	public void delete(User object) {

	}

	@Override
	public void update(User newObj) {

	}

	@Override
	public User getOne(User object) {
		List<User> list = userDao.getByParam(object);
		if (list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<User> getAll() {
		return null;
	}

	@Override
	public void saveObjects(List<User> objs) {

	}

	@Override
	public List<User> queryByParm(User obj) {
		return null;
	}

}