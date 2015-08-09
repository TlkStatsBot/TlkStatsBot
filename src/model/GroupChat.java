package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("GroupChat")
public class GroupChat {

	@JsonProperty
	private int id;
	
	@JsonProperty
	private String title;
	
	public String toString() {
		return PrintHelper.toString(this);
	}
}
