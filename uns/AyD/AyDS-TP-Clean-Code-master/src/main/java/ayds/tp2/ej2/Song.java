package ayds.tp2.ej2;

public class Song {
	
	private String name; // song name
	private int duration; // song length in seconds
	private String url; // remote file url
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
