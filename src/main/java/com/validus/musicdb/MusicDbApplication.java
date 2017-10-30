package com.validus.musicdb;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * The main class of the MusicDb rest service app.
 * 
 * @author raymond.qiu
 *
 */
@SpringBootApplication
public class MusicDbApplication {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(MusicDbApplication.class, args);
	}
	
    @PostConstruct
    private void initDb() {
    	// create artist table
        jdbcTemplate.execute("drop table artist if exists");
        jdbcTemplate.execute("create table artist(id long, name varchar(255), created_date Date, last_modified_date Date)");
        jdbcTemplate.execute("insert into artist(id, name) values (1, 'Muse')");
        jdbcTemplate.execute("insert into artist(id, name) values (2, 'AC/DC')");
        jdbcTemplate.execute("insert into artist(id, name) values (3, 'Def Leppard')");
        jdbcTemplate.execute("insert into artist(id, name) values (4, 'Van Halen')");
        jdbcTemplate.execute("insert into artist(id, name) values (5, 'Duran Duran')");
    	// create album table
        jdbcTemplate.execute("drop table album if exists");
        jdbcTemplate.execute("create table album(id long, name varchar(255), year_released int, created_date Date, last_modified_date Date)");
        jdbcTemplate.execute("insert into album(id, name, year_released) values (1, 'Drones', 2015)");
        jdbcTemplate.execute("insert into album(id, name, year_released) values (2, 'Origin of Symmetry', 2001)");
    	// create artist_album table
        jdbcTemplate.execute("drop table artist_albums if exists");
        jdbcTemplate.execute("create table artist_albums(artist_id long, albums_id long)");
        jdbcTemplate.execute("insert into artist_albums(artist_id, albums_id) values(1, 1)");
        jdbcTemplate.execute("insert into artist_albums(artist_id, albums_id) values(1, 2)");
        // create song table
        jdbcTemplate.execute("drop table song if exists");
        jdbcTemplate.execute("create table song(id long, album_id long, track int, name varchar(255), created_date Date, last_modified_date Date)");
        jdbcTemplate.execute("insert into song(id, album_id, track, name) values(1, 1, 1, 'Dead Inside')");
        jdbcTemplate.execute("insert into song(id, album_id, track, name) values(2, 1, 2, 'Drill Sargeant')");
        jdbcTemplate.execute("insert into song(id, album_id, track, name) values(3, 1, 3, 'Psycho')");
        jdbcTemplate.execute("insert into song(id, album_id, track, name) values(4, 1, 4, 'Mercy')");
        jdbcTemplate.execute("insert into song(id, album_id, track, name) values(5, 1, 5, 'Reapers')");
        jdbcTemplate.execute("insert into song(id, album_id, track, name) values(6, 1, 6, 'The Handler')");
        jdbcTemplate.execute("insert into song(id, album_id, track, name) values(7, 1, 7, 'JFK')");
        jdbcTemplate.execute("insert into song(id, album_id, track, name) values(8, 1, 8, 'Defector')");
        jdbcTemplate.execute("insert into song(id, album_id, track, name) values(9, 1, 9, 'Revolt')");
        jdbcTemplate.execute("insert into song(id, album_id, track, name) values(10, 1, 10, 'Aftermath')");
        jdbcTemplate.execute("insert into song(id, album_id, track, name) values(11, 1, 11, 'The Globalist')");
        jdbcTemplate.execute("insert into song(id, album_id, track, name) values(12, 1, 12, 'Drones')");
        jdbcTemplate.execute("insert into song(album_id, track, name) values(2, 1, 'Intro')");
    }	
}
