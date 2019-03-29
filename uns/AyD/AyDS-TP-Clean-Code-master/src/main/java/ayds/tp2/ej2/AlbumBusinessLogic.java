package ayds.tp2.ej2;

public class AlbumBusinessLogic {

	private final AlbumRepository repo;
	
	public AlbumBusinessLogic(AlbumRepository repo) {
		super();
		this.repo = repo;
	}

	public Album getAlbumById(int id) {
		
		Album a =  repo.getAlbumLocalSource().getAlbum(id);
		
		// If it's not locally saved or too old, fetch it from remote and save it locally
		if(a == null || System.currentTimeMillis() - a.getTimeStamp() > AlbumRepository.MAX_TIME * 24 * 60 * 1000) {
			a = repo.getAlbumRemoteSource().getRemoteAlbum(id);
			repo.getAlbumLocalSource().saveAlbum(a);
		}
		
		return a;
	}
}
