package db.entities;

public class Interest {
	private Long InterestId;
	private User user;
	private org.hibernate.lob.SerializableClob Interest;
	public void setInterestId(Long interestId) {
		InterestId = interestId;
	}
	public Long getInterestId() {
		return InterestId;
	}
	public void setInterest(org.hibernate.lob.SerializableClob interest) {
		Interest = interest;
	}
	public org.hibernate.lob.SerializableClob getInterest() {
		return Interest;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
}
