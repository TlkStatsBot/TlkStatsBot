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
	
	@Override
	public int hashCode() {
		return (int) (longitude * 31 + latitude);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Location) {
			Location o = (Location) obj;
			return o.longitude == longitude && o.latitude == latitude;
		}
		return false;
	}
	
	public String toString() {
		return PrintHelper.toString(this);
	}

	public float getLongitude() {
		return longitude;
	}

	public float getLatitude() {
		return latitude;
	}
}
