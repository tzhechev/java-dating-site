package db.entities;

public class City {

	private Long cityId;
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long id) {
		this.cityId = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	private String city;
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getCity();
	}
	
}
