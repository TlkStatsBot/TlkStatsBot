package model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

import util.PrintHelper;

@Entity
public class Update implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("update_id")
	@Id
	public int id;
	
	@JsonProperty
	@OneToOne(cascade = CascadeType.MERGE)
	private Message message;
	
	@Override
	public int hashCode() {
		return id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Update) {
			return id == ((Update) obj).id;
		}
		return false;
	}
	public String toString() {
		return PrintHelper.toString(this);
	}
}
