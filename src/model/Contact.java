package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Contact {

	@JsonProperty("phone_number")
	private String phoneNumber;
	
	@JsonProperty("first_name")
	private String firstName;
	
	@JsonProperty("last_name")
	private String lastName;
	
	@JsonProperty("user_id")
	private int userId;
	
	public String toString() {
		return PrintHelper.toString(this);
	}
}
