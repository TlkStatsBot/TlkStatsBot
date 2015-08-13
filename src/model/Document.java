package model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

import util.PrintHelper;

@Entity
public class Document implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("file_id")
	@Id
	private String fileId;
	
	@JsonProperty
	@ManyToOne(cascade = CascadeType.MERGE)
	private PhotoSize thumb;
	
	@JsonProperty("file_name")
	private String fileName;
	
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
		if (obj instanceof Document) {
			return ((Document) obj).fileId.equals(this.fileId);
		}
		return false;
	}
	
	public String toString() {
		return PrintHelper.toString(this);
	}
}
