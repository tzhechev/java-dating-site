package db.entities;


public class Picture {
	private Long pictureId;
	private User user;
	private org.hibernate.lob.SerializableBlob picture;
	/**
	 * This method sets the ID (primary key) for the row in the Pictures table.
	 * For internal use only. 
	 * @param pictureId The ID for this row in the table.
	 */
	public void setPictureId(Long pictureId) {
		this.pictureId = pictureId;
	}
	/**
	 * This method returns the ID for the row in the Pictures table.
	 * This is for internal use. If you need to compare pictures, use equals(Picture).
	 * @return The ID for this row in the table.
	 */
	public Long getPictureId() {
		return pictureId;
	}
	/**
	 * This method is used to set the picture for a particular user.
	 * @param picture The picture that will be set.
	 */
	public void setPicture(org.hibernate.lob.SerializableBlob picture) {
		this.picture = picture;
	}
	/**
	 * This method is used to access the picture for a particular user.
	 * @return The picture instance.
	 */
	public org.hibernate.lob.SerializableBlob getPicture() {
		return picture;
	}
	/**
	 * This method is used to set the User of a Picture. 
	 * You should normally be using User.setPicture(Picture) instead.
	 * @param user The User for this Picture.
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * This method returns the User associated with this Picture.
	 * @return The User instance.
	 */
	public User getUser() {
		return user;
	}
}
