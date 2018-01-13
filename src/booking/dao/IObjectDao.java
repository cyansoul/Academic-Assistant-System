package booking.dao;

import java.io.Serializable;
import java.util.List;

import booking.util.Page;

public interface IObjectDao<T extends Serializable> {

	public void insert(T object);

	public void delete(T object);

	public void update(T newObj);

	public T getOne(T object);

	public List<T> getAll();

	public List<T> getByParam(T object);

	public Long queryCount(T object);

	public List<T> getByParamPage(T object, Page page);
}
