package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Update {

	@JsonProperty("update_id")
	private int id;
	
	@JsonProperty
	private Message message;
	
	public String toString() {
		return PrintHelper.toString(this);
	}
}
