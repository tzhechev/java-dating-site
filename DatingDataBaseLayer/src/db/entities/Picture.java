package db.entities;


public class Picture {
	private Long pictureId;
	private User user;
	private org.hibernate.lob.SerializableBlob picture;
	public void setPictureId(Long pictureId) {
		this.pictureId = pictureId;
	}
	public Long getPictureId() {
		return pictureId;
	}
	public void setPicture(org.hibernate.lob.SerializableBlob picture) {
		this.picture = picture;
	}
	public org.hibernate.lob.SerializableBlob getPicture() {
		return picture;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
}
