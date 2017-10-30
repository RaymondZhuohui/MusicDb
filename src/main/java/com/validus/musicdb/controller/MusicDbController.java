package com.validus.musicdb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.validus.musicdb.entity.Album;
import com.validus.musicdb.entity.Artist;
import com.validus.musicdb.entity.Song;
import com.validus.musicdb.repository.AlbumRepository;
import com.validus.musicdb.repository.ArtistRepository;
import com.validus.musicdb.repository.SongRepository;

/**
 * The controller class of the MusicDb app.
 * 
 * @author raymond.qiu
 *
 */
@RestController
public class MusicDbController {
	private ArtistRepository artistRepository;
	private AlbumRepository albumRepository;
	private SongRepository songRepository;
	
	public MusicDbController(ArtistRepository artistRepository, AlbumRepository albumRepository, SongRepository songRepository) {
		this.artistRepository = artistRepository;
		this.albumRepository = albumRepository;
		this.songRepository = songRepository;
	}
	
	@RequestMapping(path="/musicdb/songs", method = RequestMethod.POST)
	public Song addSong(@RequestBody Song song) {
		Song existingSong = getSong(song.getId());
		if (existingSong != null) {
			throw new RuntimeException("Song [id=" + song.getId() + "] already exists");
		}
		return songRepository.save(song);
	}
	
	@RequestMapping(path="/musicdb/songs", method = RequestMethod.PUT)
	public Song updateSong(@RequestBody Song song) {
		Song existingSong = getSong(song.getId());
		if (existingSong == null) {
			throw new RuntimeException("Song [id=" + song.getId() + "] does not exist");
		}
		existingSong.setCreatedDate(song.getCreatedDate());
		existingSong.setLastModifiedDate(song.getLastModifiedDate());
		existingSong.setName(song.getName());
		existingSong.setTrack(song.getTrack());
		return songRepository.save(existingSong);
	}
	
	@RequestMapping(path="/musicdb/songs/{songId}", method = RequestMethod.DELETE)
	public Song deleteSong(@PathVariable("songId") long id) {
		Song existingSong = getSong(id);
		if (existingSong == null) {
			throw new RuntimeException("Song [id=" + id + "] does not exist");
		}
		songRepository.delete(id);
		return existingSong;
	}
	
	@RequestMapping(path="/musicdb/songs", method = RequestMethod.DELETE)
	public String deleteAllSongs() {
		songRepository.deleteAll();
		return "All songs are deleted";
	}
	
	@RequestMapping(path="/musicdb/songs/{songId}", method=RequestMethod.GET)
	public Song getSong(@PathVariable("songId") long id) {
		return songRepository.findOne(id);
	}
	
	@RequestMapping(path="/musicdb/songs", method=RequestMethod.GET)
	public List<Song> getAllSongs() {
		List<Song> songList = new ArrayList<>();
		songRepository.findAll().forEach(song -> songList.add(song));
		return songList;
	}
	
	@RequestMapping(path="/musicdb/artists", method = RequestMethod.POST)
	public Artist addArtist(@RequestBody Artist artist) {
		Artist existingArtist = getArtist(artist.getId());
		if (existingArtist != null) {
			throw new RuntimeException("Artist [id=" + artist.getId() + "] already exists");
		}
		return artistRepository.save(artist);
	}
	
	@RequestMapping(path="/musicdb/artists", method = RequestMethod.PUT)
	public Artist updateArtist(@RequestBody Artist artist) {
		Artist existingArtist = getArtist(artist.getId());
		if (existingArtist == null) {
			throw new RuntimeException("Artist [id=" + artist.getId() + "] does not exist");
		}
		existingArtist.setCreatedDate(artist.getCreatedDate());
		existingArtist.setLastModifiedDate(artist.getLastModifiedDate());
		existingArtist.setName(artist.getName());
		existingArtist.setAlbums(artist.getAlbums());
		return artistRepository.save(existingArtist);
	}
	
	@RequestMapping(path="/musicdb/artists/{artistId}", method = RequestMethod.DELETE)
	public Artist deleteArtist(@PathVariable("artistId") long id) {
		Artist existingArtist = getArtist(id);
		if (existingArtist == null) {
			throw new RuntimeException("Artist [id=" + id + "] does not exist");
		}
		artistRepository.delete(id);
		return existingArtist;
	}
	
	@RequestMapping(path="/musicdb/artists", method = RequestMethod.DELETE)
	public String deleteAllArtists() {
		artistRepository.deleteAll();
		return "All artists are deleted";
	}
	
	@RequestMapping(path="/musicdb/artists/{artistId}", method=RequestMethod.GET)
	public Artist getArtist(@PathVariable("artistId") long id) {
		return artistRepository.findOne(id);
	}
	
	@RequestMapping(path="/musicdb/artists", method=RequestMethod.GET)
	public List<Artist> getAllArtists() {
		List<Artist> artistList = new ArrayList<>();
		artistRepository.findAll().forEach(artist -> artistList.add(artist));
		return artistList;
	}	
	
	@RequestMapping(path="/musicdb/albums", method = RequestMethod.POST)
	public Album addAlbum(@RequestBody Album album) {
		Album existingAlbum = getAlbum(album.getId());
		if (existingAlbum != null) {
			throw new RuntimeException("Album [id=" + album.getId() + "] already exists");
		}
		return albumRepository.save(album);
	}
	
	@RequestMapping(path="/musicdb/albums", method = RequestMethod.PUT)
	public Album updateAlbum(@RequestBody Album album) {
		Album existingAlbum = getAlbum(album.getId());
		if (existingAlbum == null) {
			throw new RuntimeException("Album [id=" + album.getId() + "] does not exist");
		}
		existingAlbum.setCreatedDate(album.getCreatedDate());
		existingAlbum.setLastModifiedDate(album.getLastModifiedDate());
		existingAlbum.setName(album.getName());
		existingAlbum.setYearReleased(album.getYearReleased());
		existingAlbum.setSongs(album.getSongs());
		return albumRepository.save(existingAlbum);
	}
	
	@RequestMapping(path="/musicdb/albums/{albumId}", method = RequestMethod.DELETE)
	public Album deleteAlbum(@PathVariable("albumId") long id) {
		Album existingAlbum = getAlbum(id);
		if (existingAlbum == null) {
			throw new RuntimeException("AlbumId [id=" + id + "] does not exist");
		}
		albumRepository.delete(id);
		return existingAlbum;
	}
	
	@RequestMapping(path="/musicdb/albums", method = RequestMethod.DELETE)
	public String deleteAllAlbums() {
		albumRepository.deleteAll();
		return "All albums are deleted";
	}	
	
	@RequestMapping(path="/musicdb/albums/{albumId}", method=RequestMethod.GET)
	public Album getAlbum(@PathVariable("albumId") long id) {
		Album album = albumRepository.findOne(id);
		return album;
	}
	
	@RequestMapping(path="/musicdb/albums", method=RequestMethod.GET)
	public List<Album> getAllAlbums() {
		List<Album> albumList = new ArrayList<>();
		albumRepository.findAll().forEach(album -> albumList.add(album));
		return albumList;
	}
}
