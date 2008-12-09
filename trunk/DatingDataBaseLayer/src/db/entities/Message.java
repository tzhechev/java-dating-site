package db.entities;

import java.util.Date;

public class Message {
	private Long messageId;
	private Long fromUserId;
	private Long toUserId;
	private String text;
	private User fromUser;
	private User toUser;
	private Date time;
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	/**
	 * Use this when you want to work directly with the ID. Use setFromUser otherwise.
	 * @param fromUserId The ID of the user the message is from.
	 */
	public void setFromUserId(Long fromUserId) {
		this.fromUserId = fromUserId;
	}
	/**
	 * Use this when you want to directly get the ID. User getFromUser otherwise.
	 * @return The ID of the user the message is from.
	 */
	public Long getFromUserId() {
		return fromUserId;
	}
	/**
	 * Use this when you want to work directly with the ID. Use setToUser otherwise.
	 * @param toUserId The ID of the user the message is to.
	 */
	public void setToUserId(Long toUserId) {
		this.toUserId = toUserId;
	}
	/**
	 * Use this when you want to directly get the ID. User getToUser otherwise.
	 * @return The ID of the user the message is to.
	 */
	public Long getToUserId() {
		return toUserId;
	}
	/**
	 * Set the message contents with this method.
	 * @param text The contents of the message.
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return The contents of the message.
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * This is used to set the MessageID (the primary key for the Messages table).
	 * This is for internal use only.
	 * @param messageId The ID of the row in the table.
	 */
	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
	/**
	 * You can use this to get the ID of the row of this message in the Messages table.
	 * You shouldn't need to use this. If you wish to compare messages, use equals(Message).
	 * @return The ID of the row in the table.
	 */
	public Long getMessageId() {
		return messageId;
	}
	/**
	 * Use this to set the message sender.
	 * @param fromUser The User sending the message.
	 */
	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}
	/**
	 * Use this to get the message sender.
	 * @return The User sending the message.
	 */
	public User getFromUser() {
		return fromUser;
	}
	/**
	 * Use this to set the message receiver.
	 * @param toUser The User receiving the message.
	 */
	public void setToUser(User toUser) {
		this.toUser = toUser;
	}
	/**
	 * Use this to get the message receiver
	 * @return The User receiving the message.
	 */
	public User getToUser() {
		return toUser;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Message){
			return messageId.equals(((Message)obj).getMessageId());
		}
		return super.equals(obj);
		
	}
	
}
