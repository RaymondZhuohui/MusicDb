package com.validus.musicdb.repository;

import org.springframework.data.repository.CrudRepository;

import com.validus.musicdb.entity.Album;

/**
 * Repository class for Album.
 * 
 * @author raymond.qiu
 *
 */
public interface AlbumRepository extends CrudRepository<Album, Long> {

}
