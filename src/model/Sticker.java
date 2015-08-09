package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sticker {

	@JsonProperty("file_id")
	private String id;
	
	@JsonProperty
	private int width;
	
	@JsonProperty
	private int height;
	
	@JsonProperty
	private PhotoSize thumb;
	
	@JsonProperty("file_size")
	private int fileSize;
	
	public String toString() {
		return PrintHelper.toString(this);
	}
}
