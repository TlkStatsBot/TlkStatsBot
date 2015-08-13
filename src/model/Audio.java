package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import util.PrintHelper;

@Entity
public class Audio implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("file_id")
	@Id
	private String fileId;
	
	@JsonProperty
	@Column(nullable = false)
	private int duration;
	
	@JsonProperty("mime_type")
	private String mimeType;
	
	@JsonProperty("file_size")
	private Integer fileSize;
	
	@Override
	public int hashCode() {
		return fileId.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Audio) {
			return fileId.equals(((Audio) obj).fileId);
		}
		return false;
	}
	
	
	public String toString() {
		return PrintHelper.toString(this);
	}
}
