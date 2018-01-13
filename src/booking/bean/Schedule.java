package booking.bean;

import java.io.Serializable;
import java.util.Date;

public class Schedule implements Serializable {
	private Long id;
	private User student;
	private User professor;
	private Date bookingDate;
	private String context;
	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	private Date startDate;
	private Date endDate;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public User getProfessor() {
		return professor;
	}

	public void setProfessor(User professor) {
		this.professor = professor;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	@Override
	public String toString() {
		return "Schedule [id=" + id + ", student=" + student + ", Professor=" + professor + ", bookingDate="
				+ bookingDate + "]";
	}
}
