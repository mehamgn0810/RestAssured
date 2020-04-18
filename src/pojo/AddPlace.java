package pojo;

import java.util.List;

public class AddPlace {
	
	private Location location;
	private int accuracy;
	private String name;
	private String phone_number;
	private String address;
	private String website;
	private String language;
	private List<String> types;
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Location getLocation() {
		return location;
	}

	public int getAccuracy() {
		return accuracy;
	}

	public String getName() {
		return name;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public String getAddress() {
		return address;
	}

	public String getWebsite() {
		return website;
	}

	public String getLanguage() {
		return language;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public void setTypes(List<String> types) {
		this.types = types;
	}
	
	
	

}
