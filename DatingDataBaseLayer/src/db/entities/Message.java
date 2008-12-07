package db.entities;

public class Message {
	private Long messageId;
	private Long fromUserId;
	private Long toUserId;
	private String text;
	private User fromUser;
	private User toUser;
	public void setFromUserId(Long fromUserId) {
		this.fromUserId = fromUserId;
	}
	public Long getFromUserId() {
		return fromUserId;
	}
	public void setToUserId(Long toUserId) {
		this.toUserId = toUserId;
	}
	public Long getToUserId() {
		return toUserId;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getText() {
		return text;
	}
	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
	public Long getMessageId() {
		return messageId;
	}
	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}
	public User getFromUser() {
		return fromUser;
	}
	public void setToUser(User toUser) {
		this.toUser = toUser;
	}
	public User getToUser() {
		return toUser;
	}
}
