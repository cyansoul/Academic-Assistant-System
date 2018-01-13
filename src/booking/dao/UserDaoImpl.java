package booking.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import booking.bean.User;
import booking.exception.CustomeException;
import booking.util.CommUtils;
import booking.util.Page;

@Repository(value="userDao")
public class UserDaoImpl extends AbstractDao implements IObjectDao<User> {

	@Override
	public void insert(User object) {
		if(object==null){
			throw new CustomeException("Please input an unempty entity!");
		}
		this.getSession().saveOrUpdate(object);
	}

	@Override
	public void delete(User object) {
		if(object==null){
			throw new CustomeException("Please input an unempty entity!");
		}
		StringBuffer sb = new StringBuffer("delete from User where 1=1");
		if(object.getId()!=null&&object.getId()>0){
			sb.append(" and id=:id");
		}
		Query query = this.getSession().createSQLQuery(sb.toString());
		if(object.getId()!=null&&object.getId()>0){
			query.setParameter("id", object.getId());
		}
		query.executeUpdate();
	}

	@Override
	public void update(User newObj) {
		this.getSession().update(newObj);
	}

	@Override
	public User getOne(User object) {
		return this.getSession().get(User.class, object.getId());
	}

	@Override
	public List<User> getAll() {
		List<User> list = this.getSession().createQuery("from " + User.class.getName()).list();
		return list;
		
	}

	@Override
	public List<User> getByParam(User object) {
		StringBuffer sql = new StringBuffer(" from User where 1=1");
		if(object!=null){
			if(object.getUsername()!=null && CommUtils.hasLength(object.getUsername())){
				sql.append(" and username=:username");
			}
			if(object.getPassword()!=null && CommUtils.hasLength(object.getPassword())){
				sql.append(" and password =:password");
			}
			if(object.getRole()!=0){
				sql.append(" and role =:role");
			}
		}
		Query query = this.getSession().createQuery(sql.toString());
		if(object!=null){
			if(object.getUsername()!=null && CommUtils.hasLength(object.getUsername())){
				query.setParameter("username", object.getUsername());
			}
			if(object.getPassword()!=null && CommUtils.hasLength(object.getPassword())){
				query.setParameter("password", object.getPassword());
			}
			if(object.getRole()!=0){
				query.setParameter("role", object.getRole());
			}
		}
		return query.list();
	}

	@Override
	public Long queryCount(User object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getByParamPage(User object, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

}
