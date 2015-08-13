package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import util.PrintHelper;


@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty
	@Id
	private int id;
	
	@JsonProperty("first_name")
	@Column(nullable = false)
	private String firstName;
	
	@JsonProperty("last_name")
	private String lastName;
	
	@JsonProperty("username")
	private String userName;
	
	@Override
	public int hashCode() {
		return id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			return id == ((User) obj).id;
		}
		return false;
	}
	
	public String toString() {
		return PrintHelper.toString(this);
	}
}
