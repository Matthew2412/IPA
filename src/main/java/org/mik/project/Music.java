package org.mik.project;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
public class Music {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotBlank(message = "Title is mandatory")
	private String title;

	@NotBlank(message = "Artist is mandatory")
	private String artist;	
	@NotEmpty
	private String[] genre;
	
	public Music( @NotBlank(message = "Name is mandatory") String name,
			@NotBlank(message = "artist is mandatory") String artist,@NotEmpty(message="genre is mandatory")String[] genre) {
		super();
		this.title = name;
		this.artist = artist;
		this.genre = genre;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String[] getGenre() {
		return genre;
	}

	public void setGenre(String[] genre) {
		this.genre = genre;
	}

	public Music() {
	}

	// standard constructors / setters / getters / toString
	
}
