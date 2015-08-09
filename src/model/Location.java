package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {

	@JsonProperty
	private float longitude;
	
	@JsonProperty
	private float latitude;
	
	public String toString() {
		return PrintHelper.toString(this);
	}
}
