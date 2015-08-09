package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PhotoSize {

	@JsonProperty("file_id")
	private String fileId;
	
	@JsonProperty
	private int width;
	
	@JsonProperty
	private int height;
	
	@JsonProperty("file_size")
	private int fileSize;
	
	public String toString() {
		return PrintHelper.toString(this);
	}
}
