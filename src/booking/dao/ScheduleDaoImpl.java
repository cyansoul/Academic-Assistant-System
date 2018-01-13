package booking.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import booking.bean.Schedule;
import booking.exception.CustomeException;
import booking.util.Page;

@Repository(value = "ScheduleDao")
public class ScheduleDaoImpl extends AbstractDao implements IObjectDao<Schedule> {

	@Override
	public void insert(Schedule object) {
		if (object == null) {
			throw new CustomeException("Please input an unempty entity!");
		}
		this.getSession().saveOrUpdate(object);
	}

	@Override
	public void delete(Schedule object) {
		if (object == null) {
			throw new CustomeException("Please input an unempty entity!");
		}
		StringBuffer sb = new StringBuffer("delete from Schedule where 1=1");
		if (object.getId() != null && object.getId() > 0) {
			sb.append(" and id=:id");
		}
		Query query = this.getSession().createSQLQuery(sb.toString());
		if (object.getId() != null && object.getId() > 0) {
			query.setParameter("id", object.getId());
		}
		query.executeUpdate();
	}

	@Override
	public void update(Schedule newObj) {
		StringBuffer sql = new StringBuffer("update Schedule set `status`=1 where id=" + newObj.getId());
		this.getSession().createSQLQuery(sql.toString()).executeUpdate();
	}

	@Override
	public Schedule getOne(Schedule object) {
		return this.getSession().get(Schedule.class, object.getId());
	}

	@Override
	public List<Schedule> getAll() {
		List<Schedule> list = this.getSession().createQuery("from " + Schedule.class.getName()).list();
		return list;
	}

	@Override
	public List<Schedule> getByParam(Schedule object) {
		StringBuffer sql = new StringBuffer(" from Schedule where 1=1");
		if (object != null) {
			if (object.getStudent() != null && object.getStudent().getId() != 0) {
				sql.append(" and student.id=:student");
			}
			if (object.getProfessor() != null && object.getProfessor().getId() != 0) {
				sql.append(" and professor.id =:professor");
			}
			if (object.getStartDate() != null) {
				sql.append(" and bookingDate >=:startDate");
			}
			if (object.getEndDate() != null) {
				sql.append(" and bookingDate <=:endDate");
			}
			if (object.getStatus() != null) {
				sql.append(" and status =:status");
			}
		}
		sql.append(" order by status asc");
		Query query = this.getSession().createQuery(sql.toString());
		if (object != null) {
			if (object.getStudent() != null && object.getStudent().getId() != 0) {
				query.setParameter("student", object.getStudent().getId());
			}
			if (object.getProfessor() != null && object.getProfessor().getId() != 0) {
				query.setParameter("professor", object.getProfessor().getId());
			}
			if (object.getStartDate() != null) {
				query.setParameter("startDate", object.getStartDate());
			}
			if (object.getEndDate() != null) {
				query.setParameter("endDate", object.getEndDate());
			}
			if (object.getStatus() != null) {
				query.setParameter("status", object.getStatus());
			}
		}
		return query.list();
	}

	@Override
	public Long queryCount(Schedule object) {
		return null;
	}

	@Override
	public List<Schedule> getByParamPage(Schedule object, Page page) {
		return null;
	}

}