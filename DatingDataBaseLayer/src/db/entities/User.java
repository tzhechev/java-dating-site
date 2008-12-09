/**
 * 
 */
package db.entities;

/**
 * @author Hadoryu
 *
 */
public class User {
	private Long UserId;
	private String name;
	private String password;
	private String fullName;
	private String city;
	private String gender;
	private Long age;
	private String starSign;
	private Long profileVisits;
	private String online;
	private Picture picture;
	private Interest interests;
	/**
	 * @return
	 */
	public Long getUserId() {
		return UserId;
	}
	/**
	 * @param userId
	 */
	public void setUserId(Long userId) {
		UserId = userId;
	}
	/**
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * @param fullName
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * @return
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return
	 */
	public Long getAge() {
		return age;
	}
	/**
	 * @param age
	 */
	public void setAge(Long age) {
		this.age = age;
	}
	/**
	 * @return
	 */
	public String getStarSign() {
		return starSign;
	}
	/**
	 * @param starSign
	 */
	public void setStarSign(String starSign) {
		this.starSign = starSign;
	}
	/**
	 * @return
	 */
	public Long getProfileVisits() {
		return profileVisits;
	}
	/**
	 * @param profileVisits
	 */
	public void setProfileVisits(Long profileVisits) {
		this.profileVisits = profileVisits;
	}
	/**
	 * @return
	 */
	public String getOnline() {
		return online;
	}
	/**
	 * @param online
	 */
	public void setOnline(String online) {
		this.online = online;
	}
	/**
	 * @return
	 */
	public Picture getPicture() {
		return picture;
	}
	/**
	 * @param picture
	 */
	public void setPicture(Picture picture) {
		this.picture = picture;
	}
	/**
	 * @return
	 */
	public Interest getInterests() {
		return interests;
	}
	/**
	 * @param interests
	 */
	public void setInterests(Interest interests) {
		this.interests = interests;
	}
	/**
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return
	 */
	public String getCity() {
		return city;
	}
	
}

