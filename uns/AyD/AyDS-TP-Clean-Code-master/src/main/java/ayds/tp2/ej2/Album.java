package ayds.tp2.ej2;

import java.awt.Image;
import java.util.List;

public class Album {
	
	private int id; // unique identifier
	private String name; // album name
	private String by; // author
	private int year; // release year
	private Image cover; // album cover
	private List<Song> tracks; // song list
	
	private long timeStamp; // local repo time stamp
	
	public Album(int id, String name, String by, int year) {
		super();
		this.id = id;
		this.name = name;
		this.by = by;
		this.year = year;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBy() {
		return by;
	}
	public void setBy(String by) {
		this.by = by;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Image getCover() {
		return cover;
	}
	public void setCover(Image cover) {
		this.cover = cover;
	}
	public List<Song> getTracks() {
		return tracks;
	}
	public void setTracks(List<Song> tracks) {
		this.tracks = tracks;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
}
