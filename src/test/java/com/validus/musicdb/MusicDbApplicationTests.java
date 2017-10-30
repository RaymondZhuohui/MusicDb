package com.validus.musicdb;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.validus.musicdb.controller.MusicDbController;
import com.validus.musicdb.entity.Album;
import com.validus.musicdb.entity.Artist;
import com.validus.musicdb.entity.Song;

/**
 * Test class to test MusicDb rest service app.
 * 
 * @author raymond.qiu
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MusicDbApplicationTests extends SetupTest{
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
    @Autowired
    private MusicDbController controller;
    
	public void initTest() {
		super.initTest();

		// recreate empty tables
        jdbcTemplate.execute("drop table artist if exists");
        jdbcTemplate.execute("create table artist(id long, name varchar(255), created_date Date, last_modified_date Date)");

        jdbcTemplate.execute("drop table album if exists");
        jdbcTemplate.execute("create table album(id long, name varchar(255), year_released int, created_date Date, last_modified_date Date)");
        
        jdbcTemplate.execute("drop table artist_albums if exists");
        jdbcTemplate.execute("create table artist_albums(artist_id long, albums_id long)");
        
        jdbcTemplate.execute("drop table song if exists");
        jdbcTemplate.execute("create table song(id long, album_id long, track int, name varchar(255), created_date Date, last_modified_date Date)");		
	}
	
	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}
	
	@Test
	public void testSongCRUD() {
		// delete all songs first
		controller.deleteAllSongs();
		assertEquals(0, controller.getAllSongs().size());
		// test create a song
		assertEquals(song1, controller.addSong(song1));
		assertEquals(song2, controller.addSong(song2));
		// test read a song
		assertEquals(song1, controller.getSong(song1.getId()));
		assertEquals(song2, controller.getSong(song2.getId()));
		// test read all songs
		List<Song> songList = controller.getAllSongs();
		assertEquals(2, songList.size());
		boolean[] found = new boolean[] {false, false};
		for(Song song : songList) {
			if (song.equals(song1)) {
				found[0] = true;
			} else if (song.equals(song2)){
				found[1] = true;
			}
		}
		assertTrue("Song1 was not found", found[0]);
		assertTrue("Song2 was not found", found[1]);
		// test update a song
		Song song = new Song(song1.getId(), null, null, 100, "New Song1");
		controller.updateSong(song);
		assertEquals(song, controller.getSong(song.getId()));
		// test delete a song
		controller.deleteSong(song1.getId());
		assertEquals(null, controller.getSong(song1.getId()));
		// test delete all songs
		controller.deleteAllSongs();
		assertEquals(0, controller.getAllSongs().size());
	}
	
	@Test
	public void testArtistCRUD() {
		// delete all songs first
		controller.deleteAllArtists();
		assertEquals(0, controller.getAllArtists().size());
		// test create an Artist
		assertEquals(artist1, controller.addArtist(artist1));
		assertEquals(artist2, controller.addArtist(artist2));
		// test read an Artist
		assertEquals(artist1, controller.getArtist(artist1.getId()));
		assertEquals(artist2, controller.getArtist(artist2.getId()));
		// test read all Artists
		List<Artist> artistsList = controller.getAllArtists();
		assertEquals(2, artistsList.size());
		boolean[] found = new boolean[] {false, false};
		for(Artist artist : artistsList) {
			if (artist1.equals(artist)) {
				found[0] = true;
			} else if (artist2.equals(artist)){
				found[1] = true;
			}
		}
		assertTrue("Artist1 was not found", found[0]);
		assertTrue("Artist2 was not found", found[1]);
		// test update an artist
		Artist artist = new Artist(artist1.getId(), null, null, "New Artist1");
		controller.updateArtist(artist);
		assertEquals(artist, controller.getArtist(artist.getId()));
		// test delete an artist
		controller.deleteArtist(artist1.getId());
		assertEquals(null, controller.getSong(artist1.getId()));
		// test delete all artists
		controller.deleteAllArtists();
		assertEquals(0, controller.getAllArtists().size());
	}	
	
	@Test
	public void testAlbumCRUD() {
		// delete all albums first
		controller.deleteAllAlbums();
		assertEquals(0, controller.getAllAlbums().size());
		// test create an album
		assertEquals(album1, controller.addAlbum(album1));
		assertEquals(album2, controller.addAlbum(album2));
		// test read an album
		assertEquals(album1, controller.getAlbum(album1.getId()));
		assertEquals(album2, controller.getAlbum(album2.getId()));
		// test read all albums
		List<Album> albumList = controller.getAllAlbums();
		assertEquals(2, albumList.size());
		boolean[] found = new boolean[] {false, false};
		for(Album album : albumList) {
			if (album1.equals(album)) {
				found[0] = true;
			} else if (album2.equals(album)){
				found[1] = true;
			}
		}
		assertTrue("Album1 was not found", found[0]);
		assertTrue("Abum2 was not found", found[1]);
		// test update an album
		Album album = new Album(album1.getId(), null, null, "New Song1", 2019);
		controller.updateAlbum(album);
		assertEquals(album, controller.getAlbum(album.getId()));
		// test delete an album
		controller.deleteAlbum(album1.getId());
		assertEquals(null, controller.getAlbum(album1.getId()));
		// test delete all albums
		controller.deleteAllAlbums();
		assertEquals(0, controller.getAllAlbums().size());
	}	
}
