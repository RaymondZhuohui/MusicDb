package com.validus.musicdb.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.validus.musicdb.entity.Song;

/**
 * Repository class for Song.
 * 
 * @author raymond.qiu
 *
 */
public interface SongRepository extends CrudRepository<Song, Long>{
	Optional<Song> findByName(@Param("name") String name);
}
