package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import util.PrintHelper;

@Entity
public class Contact implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("phone_number")
	@Id
	private String phoneNumber;
	
	@JsonProperty("first_name")
	@Column(nullable = false)
	private String firstName;
	
	@JsonProperty("last_name")
	private String lastName;
	
	@JsonProperty("user_id")
	private int userId;
	
	public String toString() {
		return PrintHelper.toString(this);
	}
}
