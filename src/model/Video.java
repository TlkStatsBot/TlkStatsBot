package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Video {

	@JsonProperty("file_id")
	private String id;
	
	@JsonProperty
	private int width;
	
	@JsonProperty
	private int height;
	
	@JsonProperty
	private int duration;
	
	@JsonProperty
	private PhotoSize thumb;
	
	@JsonProperty("mime_type")
	private String mimeType;
	
	@JsonProperty("file_size")
	private int size;
	
	public String toString() {
		return PrintHelper.toString(this);
	}
}
