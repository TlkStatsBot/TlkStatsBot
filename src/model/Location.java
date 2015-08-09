package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import util.PrintHelper;

@Entity
public class Location implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty
	@Id
	private float longitude;
	
	@JsonProperty
	@Id
	private float latitude;
	
	public String toString() {
		return PrintHelper.toString(this);
	}
}
