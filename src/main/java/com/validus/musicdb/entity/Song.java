package com.validus.musicdb.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Song entity class.
 * 
 * @author raymond.qiu
 *
 */
@Entity
@Table(name = "song")
public class Song extends BaseModel {
	private int track;
	private String name;
	
	public Song() {}
	
	public Song(long id, Date createdDate, Date lastModifiedDate, int track, String name) {
		super(id, createdDate, lastModifiedDate);
		this.track = track;
		this.name = name;
	}
	
	public int getTrack() {
		return track;
	}

	public void setTrack(int track) {
		this.track = track;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return "id = " + getId() + "; name = " + name + "; track = " + track;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + track;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Song other = (Song) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (track != other.track)
			return false;
		return true;
	}
}
