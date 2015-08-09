package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("User")
public class User {

	@JsonProperty
	private int id;
	
	@JsonProperty("first_name")
	private String firstName;
	
	@JsonProperty("last_name")
	private String lastName;
	
	@JsonProperty("username")
	private String userName;
	
	public String toString() {
		return PrintHelper.toString(this);
	}
}
