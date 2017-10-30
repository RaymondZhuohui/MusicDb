package com.validus.musicdb;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.validus.musicdb.entity.Album;
import com.validus.musicdb.entity.Artist;
import com.validus.musicdb.entity.Song;
import com.validus.musicdb.repository.AlbumRepository;
import com.validus.musicdb.repository.ArtistRepository;
import com.validus.musicdb.repository.SongRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebLayerTest extends SetupTest{

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ArtistRepository artistRepository;
	
	@MockBean
	private AlbumRepository albumRepository;
	
	@MockBean
	private SongRepository songRepository;

	@Test
	public void testFindSongById() throws Exception {
		when(songRepository.findOne(song1.getId())).thenReturn(song1);
		this.mockMvc.perform(get("/musicdb/songs/1")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(songString1)));
	}

	@Test
	public void testFindAllSongs() throws Exception {
		List<Song> songList = new ArrayList<>();
		songList.add(song1);
		songList.add(song2);
		songList.add(song3);
		when(songRepository.findAll()).thenReturn(songList);
		this.mockMvc.perform(get("/musicdb/songs")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(songString1)))
				.andExpect(content().string(containsString(songString2)))
				.andExpect(content().string(containsString(songString3)));
	}

	@Test
	public void testCreateSong() throws Exception {
		when(songRepository.save(song1)).thenReturn(song1);
		this.mockMvc.perform(post("/musicdb/songs").contentType(MediaType.APPLICATION_JSON).content(songString1))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString(songString1)));
	}
	
	@Test
	public void testUpdateSong() throws Exception {
		when(songRepository.findOne(song1.getId())).thenReturn(song1);
		when(songRepository.save(song1)).thenReturn(song1);	
		this.mockMvc.perform(put("/musicdb/songs").contentType(MediaType.APPLICATION_JSON).content(songString1))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString(songString1)));
	}
	
	@Test
	public void testDeleteSong() throws Exception {
		when(songRepository.findOne(1L)).thenReturn(song1);
		doAnswer(new Answer<Void>() {

			  @Override
			  public Void answer(InvocationOnMock invocation) throws Throwable {
			    return null;
			  }

			}).when(songRepository).delete(song1.getId());
		this.mockMvc.perform(delete("/musicdb/songs/1").contentType(MediaType.APPLICATION_JSON).content(songString1))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString(songString1)));
	}	
	
	@Test
	public void testFindArtistById() throws Exception {
		when(artistRepository.findOne(artist1.getId())).thenReturn(artist1);
		this.mockMvc.perform(get("/musicdb/artists/1")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(artistString1)));
	}

	@Test
	public void testFindAllArtists() throws Exception {
		List<Artist> artistList = new ArrayList<>();
		artistList.add(artist1);
		artistList.add(artist2);
		when(artistRepository.findAll()).thenReturn(artistList);
		this.mockMvc.perform(get("/musicdb/artists")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(artistString1)))
				.andExpect(content().string(containsString(artistString2)));
	}

	@Test
	public void testCreateArtist() throws Exception {
		when(artistRepository.save(artist1)).thenReturn(artist1);
		this.mockMvc.perform(post("/musicdb/artists").contentType(MediaType.APPLICATION_JSON).content(artistString1))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString(artistString1)));
	}
	
	@Test
	public void testUpdateArtist() throws Exception {
		when(artistRepository.findOne(artist1.getId())).thenReturn(artist1);
		when(artistRepository.save(artist1)).thenReturn(artist1);	
		this.mockMvc.perform(put("/musicdb/artists").contentType(MediaType.APPLICATION_JSON).content(artistString1))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString(artistString1)));
	}
	
	@Test
	public void testDeleteArtist() throws Exception {
		when(artistRepository.findOne(artist1.getId())).thenReturn(artist1);
		doAnswer(new Answer<Void>() {

			  @Override
			  public Void answer(InvocationOnMock invocation) throws Throwable {
			    return null;
			  }

			}).when(artistRepository).delete(artist1.getId());
		this.mockMvc.perform(delete("/musicdb/artists/1").contentType(MediaType.APPLICATION_JSON).content(artistString1))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString(artistString1)));
	}
	
	@Test
	public void testFindAlbumById() throws Exception {
		when(albumRepository.findOne(album1.getId())).thenReturn(album1);
		this.mockMvc.perform(get("/musicdb/albums/1")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(albumString1)));
	}

	@Test
	public void testFindAllAlbums() throws Exception {
		List<Album> albumList = new ArrayList<>();
		albumList.add(album1);
		albumList.add(album2);
		when(albumRepository.findAll()).thenReturn(albumList);
		this.mockMvc.perform(get("/musicdb/albums")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(albumString1)))
				.andExpect(content().string(containsString(albumString2)));
	}

	@Test
	public void testCreateAlbum() throws Exception {
		when(albumRepository.save(album1)).thenReturn(album1);
		this.mockMvc.perform(post("/musicdb/albums").contentType(MediaType.APPLICATION_JSON).content(albumString1))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString(albumString1)));
	}
	
	@Test
	public void testUpdateAlbum() throws Exception {
		when(albumRepository.findOne(album1.getId())).thenReturn(album1);
		when(albumRepository.save(album1)).thenReturn(album1);	
		this.mockMvc.perform(put("/musicdb/albums").contentType(MediaType.APPLICATION_JSON).content(albumString1))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString(albumString1)));
	}
	
	@Test
	public void testDeleteAlbum() throws Exception {
		when(albumRepository.findOne(album1.getId())).thenReturn(album1);
		doAnswer(new Answer<Void>() {

			  @Override
			  public Void answer(InvocationOnMock invocation) throws Throwable {
			    return null;
			  }

			}).when(albumRepository).delete(album1.getId());
		this.mockMvc.perform(delete("/musicdb/albums/1").contentType(MediaType.APPLICATION_JSON).content(albumString1))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString(albumString1)));
	}	
}
