package db.entities;

public class Interest {
	
	private Long InterestId;
	private String Interest;
	/**
	 * Used to set InterestID for the current object/row.
	 * This should only be used by Hibernate itself, do not use this explicitly.
	 * @param interestId The id.
	 */
	public void setInterestId(Long interestId) {
		InterestId = interestId;
	}
	/**
	 * @return InterestID for the current object/row, the primary key for the Interests table.
	 */
	public Long getInterestId() {
		return InterestId;
	}
	/**
	 * Used to set an interests text block for a user.
	 * @param interest
	 */
	public void setInterest(String interest) {
		Interest = interest;
	}
	/**
	 * Used to access the interest text block associated with a user.
	 * @return The interests text block.
	 */
	public String getInterest() {
		return Interest;
	}
}
