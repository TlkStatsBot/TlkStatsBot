package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Audio {

	@JsonProperty("file_id")
	private String fileId;
	
	@JsonProperty
	private int duration;
	
	@JsonProperty("mime_type")
	private String mimeType;
	
	@JsonProperty("file_size")
	private int fileSize;
	
	public String toString() {
		return PrintHelper.toString(this);
	}
}
