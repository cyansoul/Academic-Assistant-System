package booking.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import booking.bean.MessageBoard;
import booking.exception.CustomeException;
import booking.util.Page;
@Repository(value="messageBoardDao")
public class MessageBoardDaoImpl extends AbstractDao implements IObjectDao<MessageBoard> {

	@Override
	public void insert(MessageBoard object) {
		if(object==null){
			throw new CustomeException("Please input an unempty entity!");
		}
		this.getSession().saveOrUpdate(object);
		
	}

	@Override
	public void delete(MessageBoard object) {
		if(object==null){
			throw new CustomeException("Please input an unempty entity!");
		}
		StringBuffer sb = new StringBuffer("delete from MessageBoard where 1=1");
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
	public void update(MessageBoard newObj) {
		this.getSession().update(newObj);
	}

	@Override
	public MessageBoard getOne(MessageBoard object) {
		return this.getSession().get(MessageBoard.class, object.getId());
	}

	@Override
	public List<MessageBoard> getAll() {
		List<MessageBoard> list = this.getSession().createQuery("from " + MessageBoard.class.getName()).list();
		return list;
	}

	@Override
	public List<MessageBoard> getByParam(MessageBoard object) {
		StringBuffer sql = new StringBuffer(" from MessageBoard where 1=1");
		if(object!=null){
			if(object.getId()!=null){
				sql.append(" and id=:id");
			}
			if(object.getStatus()!=null){
				sql.append(" and status=:status");
			}
			if(object.getParent()!=null && object.getParent().getId()!=null){
				sql.append(" and parent.id=:parent");
			}
			if(object.getSender()!=null && object.getSender().getId()!=null){
				sql.append(" and sender.id=:sender");
			}
			if(object.getAccepter()!=null && object.getAccepter().getId()!=null){
				sql.append(" and accepter.id=:accepter");
			}
		}
		sql.append(" order by status asc");
		Query query = this.getSession().createQuery(sql.toString());
		if(object!=null){
			if(object.getId()!=null){
				query.setParameter("id", object.getId());
			}
			if(object.getStatus()!=null){
				query.setParameter("status", object.getStatus());
			}
			if(object.getParent()!=null && object.getParent().getId()!=null){
				query.setParameter("parent", object.getParent().getId());
			}
			if(object.getSender()!=null && object.getSender().getId()!=null){
				query.setParameter("sender", object.getSender().getId());
			}
			if(object.getAccepter()!=null && object.getAccepter().getId()!=null){
				query.setParameter("accepter", object.getAccepter().getId());
			}
		}
		return query.list();
	}

	@Override
	public Long queryCount(MessageBoard object) {
		StringBuffer sql = new StringBuffer("select count(id) from MessageBoard where 1=1");
		if(object!=null){
			if(object.getId()!=null){
				sql.append(" and id=:id");
			}
			if(object.getStatus()!=null){
				sql.append(" and status=:status");
			}
			if(object.getParent()!=null && object.getParent().getId()!=null){
				sql.append(" and parent.id=:parent");
			}
			if(object.getSender()!=null && object.getSender().getId()!=null){
				sql.append(" and sender.id=:sender");
			}
			if(object.getAccepter()!=null && object.getAccepter().getId()!=null){
				sql.append(" and accepter.id=:accepter");
			}
		}
		Query query = this.getSession().createQuery(sql.toString());
		if(object!=null){
			if(object.getId()!=null){
				query.setParameter("id", object.getId());
			}
			if(object.getStatus()!=null){
				query.setParameter("status", object.getStatus());
			}
			if(object.getParent()!=null && object.getParent().getId()!=null){
				query.setParameter("parent", object.getParent().getId());
			}
			if(object.getSender()!=null && object.getSender().getId()!=null){
				query.setParameter("sender", object.getSender().getId());
			}
			if(object.getAccepter()!=null && object.getAccepter().getId()!=null){
				query.setParameter("accepter", object.getAccepter().getId());
			}
		}
		return (Long) query.list().get(0);
	}

	@Override
	public List<MessageBoard> getByParamPage(MessageBoard object, Page page) {
		StringBuffer sql = new StringBuffer(" from MessageBoard where 1=1");
		if(object!=null){
			if(object.getId()!=null){
				sql.append(" and id=:id");
			}
			if(object.getStatus()!=null){
				sql.append(" and status=:status");
			}
			if(object.getParent()!=null && object.getParent().getId()!=null){
				sql.append(" and parent.id=:parent");
			}
			if(object.getSender()!=null && object.getSender().getId()!=null){
				sql.append(" and sender.id=:sender");
			}
			if(object.getAccepter()!=null && object.getAccepter().getId()!=null){
				sql.append(" and accepter.id=:accepter");
			}
		}
		sql.append(" order by status asc,buildDate desc");
		Query query = this.getSession().createQuery(sql.toString());
		if(object!=null){
			if(object.getId()!=null){
				query.setParameter("id", object.getId());
			}
			if(object.getStatus()!=null){
				query.setParameter("status", object.getStatus());
			}
			if(object.getParent()!=null && object.getParent().getId()!=null){
				query.setParameter("parent", object.getParent().getId());
			}
			if(object.getSender()!=null && object.getSender().getId()!=null){
				query.setParameter("sender", object.getSender().getId());
			}
			if(object.getAccepter()!=null && object.getAccepter().getId()!=null){
				query.setParameter("accepter", object.getAccepter().getId());
			}
		}
		if(page!=null){
			int offset = (page.getCurrentPage() - 1) * page.getPageSize();
			query.setFirstResult(offset);
			query.setMaxResults(page.getPageSize());
		}
		return query.list();
	}

}
