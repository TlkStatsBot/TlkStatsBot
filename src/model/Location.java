package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {

	@JsonProperty
	private int longitude;
	
	@JsonProperty
	private int latitude;
	
	public String toString() {
		return PrintHelper.toString(this);
	}
}
