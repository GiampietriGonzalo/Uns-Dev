package ayds.tp2.tests;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import ayds.tp2.ej2.*;

public class Ej2Test {

	@Test
	public void test() {

		// Arrange.
		AlbumLocalSource localSource = getTestingLocalSource();
		AlbumRemoteSource remoteSource = getTestingRemoteSource();
		AlbumRepository repo = new AlbumRepository(localSource, remoteSource);
		AlbumBusinessLogic albumLogic = new AlbumBusinessLogic(repo);

		// Act & Assert.
		
		
		// Test non local album to fetch and save locally for the first time
		
		assertEquals(localSource.getAlbum(1), null); // initially not in local repo
		
		Album a =  albumLogic.getAlbumById(1);
		
		assertNotEquals(a, null);
		assertEquals(a.getId(), 1);
		assertEquals(a.getName(), "Lazaretto");
		assertEquals(a.getBy(), "Jack White");
		assertEquals(a.getYear(), 2014);
		
		assertNotEquals(localSource.getAlbum(1), null); // should be in local repo after first fetch
		
		
		// Test non existing album
		
		Album b = albumLogic.getAlbumById(999);
		
		assertEquals(b, null);
		
		// Test old local copy that should be re fetched
		Album oldAlbum = new Album(2, "TenOld", "Pearl Jam Old", 1900);
		localSource.saveAlbum(oldAlbum);
		oldAlbum.setTimeStamp(0); // override the timestamp set when saving
		
		assertNotEquals(localSource.getAlbum(2), null);
		
		Album c =  albumLogic.getAlbumById(2);
		
		assertNotEquals(c, null);
		assertEquals(c.getId(), 2);
		assertEquals(c.getName(), "Ten");
		assertEquals(c.getBy(), "Pearl Jam");
		assertEquals(c.getYear(), 1991);
	}

	private AlbumLocalSource getTestingLocalSource() {
		return new AlbumLocalSource() {
			private Map<Integer, Album> albums =  new HashMap<Integer, Album>();
			@Override
			public void saveAlbum(Album a) {
				if(a != null) {
					a.setTimeStamp(System.currentTimeMillis());
					albums.put(a.getId(), a);
				}
			}

			@Override
			public Album getAlbum(int id) {
				return albums.get(id);
			}
		};
	}

	private AlbumRemoteSource getTestingRemoteSource() {
		return new AlbumRemoteSource() {
			@Override
			public Album getRemoteAlbum(int id) {
				switch(id) {
				case 1 : return new Album(1, "Lazaretto", "Jack White", 2014);
				case 2 : return new Album(2, "Ten", "Pearl Jam", 1991);
				}
				return null;
			}
		};
	}
}
