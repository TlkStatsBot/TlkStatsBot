package model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

import util.PrintHelper;

@Entity
public class Sticker implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("file_id")
	@Id
	private String id;
	
	@JsonProperty
	@Column(nullable = false)
	private int width;
	
	@JsonProperty
	@Column(nullable = false)
	private int height;
	
	@JsonProperty
	@ManyToOne(cascade = CascadeType.MERGE)
	private PhotoSize thumb;
	
	@JsonProperty("file_size")
	private Integer fileSize;
	
	public String toString() {
		return PrintHelper.toString(this);
	}

	public String getId() {
		return id;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public PhotoSize getThumb() {
		return thumb;
	}

	public Integer getFileSize() {
		return fileSize;
	}
}
