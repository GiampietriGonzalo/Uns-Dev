package ayds.tp2.ej2;

public class AlbumRepository  {
	
	public static final int MAX_TIME = 10; // max time to keep a local record before refreshing form remote, in days
	
	private final AlbumLocalSource albumLocalSource;
	private final AlbumRemoteSource albumRemoteSource;
	
	public AlbumRepository(AlbumLocalSource albumLocalSource, AlbumRemoteSource albumRemoteSource) {
		super();
		this.albumLocalSource = albumLocalSource;
		this.albumRemoteSource = albumRemoteSource;
	}

	public AlbumLocalSource getAlbumLocalSource() {
		return albumLocalSource;
	}

	public AlbumRemoteSource getAlbumRemoteSource() {
		return albumRemoteSource;
	}
}
