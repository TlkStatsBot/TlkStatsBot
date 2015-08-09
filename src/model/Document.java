package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Document {
	
	@JsonProperty("file_id")
	private String fileId;
	
	@JsonProperty
	private PhotoSize thumb;
	
	@JsonProperty("file_name")
	private String fileName;
	
	@JsonProperty("mime_type")
	private String mimeType;
	
	@JsonProperty("file_size")
	private int fileSize;
	
	public String toString() {
		return PrintHelper.toString(this);
	}
}
