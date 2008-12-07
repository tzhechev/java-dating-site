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
	public Long getUserId() {
		return UserId;
	}
	public void setUserId(Long userId) {
		UserId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public String getStarSign() {
		return starSign;
	}
	public void setStarSign(String starSign) {
		this.starSign = starSign;
	}
	public Long getProfileVisits() {
		return profileVisits;
	}
	public void setProfileVisits(Long profileVisits) {
		this.profileVisits = profileVisits;
	}
	public String getOnline() {
		return online;
	}
	public void setOnline(String online) {
		this.online = online;
	}
	public Picture getPicture() {
		return picture;
	}
	public void setPicture(Picture picture) {
		this.picture = picture;
	}
	public Interest getInterests() {
		return interests;
	}
	public void setInterests(Interest interests) {
		this.interests = interests;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCity() {
		return city;
	}
	
}

