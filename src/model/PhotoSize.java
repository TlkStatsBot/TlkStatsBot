package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import util.PrintHelper;

@Entity
public class PhotoSize implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("file_id")
	@Id
	private String fileId;
	
	@JsonProperty
	@Column(nullable = false)
	private int width;
	
	@JsonProperty
	@Column(nullable = false)
	private int height;
	
	@JsonProperty("file_size")
	private Integer fileSize;
	
	public String toString() {
		return PrintHelper.toString(this);
	}
}
