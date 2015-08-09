package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

import util.PrintHelper;

@Entity
public class Video implements Serializable {

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
	@Column(nullable = false)
	private int duration;
	
	@JsonProperty
	@ManyToOne
	private PhotoSize thumb;
	
	@JsonProperty("mime_type")
	private String mimeType;
	
	@JsonProperty("file_size")
	private Integer size;
	
	public String toString() {
		return PrintHelper.toString(this);
	}
}
