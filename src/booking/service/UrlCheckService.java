package booking.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import booking.bean.User;
import booking.dao.IObjectDao;

@Transactional
@Service
public class UrlCheckService {
	@Resource
	private IObjectDao<User> userDao;

}