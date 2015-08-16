package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import util.PrintHelper;

@Entity
public class Voice implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("file_id")
	@Id
	private String id;
	
	@JsonProperty
	@Column(nullable = false)
	private int duration;
	
	@JsonProperty("mime_type")
	private String mimeType;
	
	@JsonProperty("file_size")
	private Integer size;
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Voice) {
			return id.equals(((Voice) obj).id);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return PrintHelper.toString(this);
	}

	public String getId() {
		return id;
	}

	public int getDuration() {
		return duration;
	}

	public String getMimeType() {
		return mimeType;
	}

	public Integer getSize() {
		return size;
	}
}
