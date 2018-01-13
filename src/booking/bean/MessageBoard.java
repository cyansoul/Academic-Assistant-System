package booking.bean;

import java.io.Serializable;
import java.util.Date;

public class MessageBoard implements Serializable{
	private Long id;
	private String title;
	private String context;
	private MessageBoard parent;
	private User sender;
	private User accepter;
	private Date buildDate;
	private Integer status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public MessageBoard getParent() {
		return parent;
	}
	public void setParent(MessageBoard parent) {
		this.parent = parent;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public User getAccepter() {
		return accepter;
	}
	public void setAccepter(User accepter) {
		this.accepter = accepter;
	}
	public Date getBuildDate() {
		return buildDate;
	}
	public void setBuildDate(Date buildDate) {
		this.buildDate = buildDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
