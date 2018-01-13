package booking.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import booking.bean.MessageBoard;
import booking.util.Page;

@Transactional
public interface IBaseService<T> {

	public void insert(T object);

	public void delete(T object);

	public void update(T newObj);

	public T getOne(T object);

	public List<T> getAll();

	public List<T> queryByParm(T obj);

	public void saveObjects(List<T> objs);
}
