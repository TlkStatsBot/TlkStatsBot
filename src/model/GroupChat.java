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

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof GroupChat) {
			return id == ((GroupChat) obj).id;
		}
		return false;
	}
	
	public String toString() {
		return PrintHelper.toString(this);
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}
}
