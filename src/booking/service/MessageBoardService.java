package booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import booking.bean.MessageBoard;
import booking.dao.IObjectDao;
import booking.util.Page;

@Transactional
@Service
public class MessageBoardService implements IBaseService<MessageBoard> {

	@Autowired
	private IObjectDao<MessageBoard> messageBoardDao;

	@Override
	public void insert(MessageBoard object) {
		messageBoardDao.insert(object);
	}

	@Override
	public void delete(MessageBoard object) {
		messageBoardDao.delete(object);
	}

	@Override
	public void update(MessageBoard newObj) {
		messageBoardDao.update(newObj);
	}

	@Override
	public MessageBoard getOne(MessageBoard object) {
		return messageBoardDao.getOne(object);
	}

	@Override
	public List<MessageBoard> getAll() {
		return messageBoardDao.getAll();
	}

	@Override
	public List<MessageBoard> queryByParm(MessageBoard obj) {
		return messageBoardDao.getByParam(obj);
	}

	@Override
	public void saveObjects(List<MessageBoard> objs) {

	}

	/**
	 * get total number
	 * @param messageBoard
	 * @return
	 */
	public Long queryCount(MessageBoard messageBoard) {
		return messageBoardDao.queryCount(messageBoard);
	}

	public List<MessageBoard> queryByParmPage(MessageBoard obj, Page page) {
		return messageBoardDao.getByParamPage(obj, page);
	}

	/**
	 * update status
	 * @param list
	 */
	public void updateMessageBoard(List<MessageBoard> list) {
		for (MessageBoard messageBoard : list) {
			if (messageBoard.getStatus() == 0) {
				messageBoard.setStatus(1);
				this.update(messageBoard);
			}
		}
	}
}
