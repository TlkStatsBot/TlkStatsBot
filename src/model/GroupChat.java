package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import util.PrintHelper;

@Entity
public class GroupChat implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty
	@Id
	private int id;
	
	@JsonProperty
	@Column(nullable = false)
	private String title;
	
	public String toString() {
		return PrintHelper.toString(this);
	}
}
