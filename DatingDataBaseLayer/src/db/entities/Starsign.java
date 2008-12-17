package db.entities;

public class Starsign {
	private Long starsignId;
	private String starsign;
	public String getStarsign() {
		return starsign;
	}
	public void setStarsign(String starsign) {
		this.starsign = starsign;
	}
	public void setStarsignId(Long starsignId) {
		this.starsignId = starsignId;
	}
	public Long getStarsignId() {
		return starsignId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getStarsign();
	}
	
}
