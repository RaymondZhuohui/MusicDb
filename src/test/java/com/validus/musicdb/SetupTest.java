package com.validus.musicdb;

import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;

import com.validus.musicdb.entity.Album;
import com.validus.musicdb.entity.Artist;
import com.validus.musicdb.entity.Song;

/**
 * Test class to set up some common configurations.
 * 
 * @author raymond.qiu
 *
 */
@SpringBootTest
public class SetupTest {
	String artistString1;
	String artistString2;

	Artist artist1;
	Artist artist2;
	
	String albumString1;
	String albumString2;

	Album album1;
	Album album2;

	String songString1;
	String songString2;
	String songString3;

	Song song1;
	Song song2;
	Song song3;
	
	

	@Before
	public void initTest() {
		song1 = new Song(1l, null, null, 1, "Song1");
		song2 = new Song(2l, null, null, 2, "Song2");
		song3 = new Song(3l, null, null, 3, "Song3");
		
		songString1 = "{\"id\":1,\"createdDate\":null,\"lastModifiedDate\":null,\"track\":1,\"name\":\"Song1\"}";
		songString2 = "{\"id\":2,\"createdDate\":null,\"lastModifiedDate\":null,\"track\":2,\"name\":\"Song2\"}";
		songString3 = "{\"id\":3,\"createdDate\":null,\"lastModifiedDate\":null,\"track\":3,\"name\":\"Song3\"}";
		
		album1 = new Album(1L, null, null, "Album1", 2011);
		album2 = new Album(2L, null, null, "Album2", 2003);
		
		albumString1 = "{\"id\":1,\"createdDate\":null,\"lastModifiedDate\":null,\"name\":\"Album1\",\"yearReleased\":2011,\"songs\":[]}";
		albumString2 = "{\"id\":2,\"createdDate\":null,\"lastModifiedDate\":null,\"name\":\"Album2\",\"yearReleased\":2003,\"songs\":[]}";
		
		artist1 = new Artist(1L, null, null, "Artist1");
		artist2 = new Artist(2L, null, null, "Artist2");
		
		artistString1 = "{\"id\":1,\"createdDate\":null,\"lastModifiedDate\":null,\"name\":\"Artist1\",\"albums\":[]}";
		artistString2 = "{\"id\":2,\"createdDate\":null,\"lastModifiedDate\":null,\"name\":\"Artist2\",\"albums\":[]}";
	}
}
