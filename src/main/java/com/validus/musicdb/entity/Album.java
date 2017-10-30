package com.validus.musicdb.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Album entity class.
 * 
 * @author raymond.qiu
 *
 */
@Entity
@Table(name = "album")
public class Album extends BaseModel {
	private String name;
	private int yearReleased;
	@OneToMany(
	        cascade = CascadeType.ALL, 
	        orphanRemoval = true,
	        fetch = FetchType.EAGER
	    )
    @JoinColumn(name = "album_id")	
	private List<Song> songs = new ArrayList<>();;
	
	public Album() {}
	
	public Album(long id, Date createdDate, Date lastModifiedDate, String name, int yearReleased) {
		super(id, createdDate, lastModifiedDate);
		this.name = name;
		this.yearReleased = yearReleased;		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYearReleased() {
		return yearReleased;
	}

	public void setYearReleased(int yearReleased) {
		this.yearReleased = yearReleased;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((songs == null) ? 0 : songs.hashCode());
		result = prime * result + yearReleased;
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
		Album other = (Album) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (songs == null) {
			if (other.songs != null)
				return false;
		} else if (!songs.equals(other.songs))
			return false;
		if (yearReleased != other.yearReleased)
			return false;
		return true;
	}	
	
}
