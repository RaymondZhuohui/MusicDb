package com.validus.musicdb.repository;

import org.springframework.data.repository.CrudRepository;

import com.validus.musicdb.entity.Artist;

/**
 * Repository class for Artist.
 * 
 * @author raymond.qiu
 *
 */
public interface ArtistRepository extends CrudRepository<Artist, Long> {

}
