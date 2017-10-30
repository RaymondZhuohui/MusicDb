package com.validus.musicdb.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This error controller class.
 * 
 * @author raymond.qiu
 *
 */
@RestController
public class MusicDbErrorController implements ErrorController{

    private static final String PATH = "/error";
    private static final String USAGE_STRING = "Usages: <p>"
    		+ "(GET)    /musicdb/songs/{Id} to get a song by Id<p>"
    		+ "(GET)    /musicdb/songs to get all songs<p>"
    		+ "(POST)   /musicdb/songs to create a song<p>"
    		+ "(PUT)    /musicdb/songs to update a song<p>"
    		+ "(DELETE) /musicdb/songs/{Id} to delete a song by Id<p>"
    		+ "(DELETE) /musicdb/songs to delete all songs<p>"
    		+ "(GET)    /musicdb/artists/{Id} to get an artist by Id<p>"
    		+ "(GET)    /musicdb/artists to get all artists<p>"
    		+ "(POST)   /musicdb/artists to create an artist<p>"
    		+ "(PUT)    /musicdb/artists to update an artist<p>"
    		+ "(DELETE) /musicdb/artists/{Id} to delete an artist by Id<p>"
    		+ "(DELETE) /musicdb/artists to delete all artists<p>"    		
    		+ "(GET)    /musicdb/albums/{Id} to get an album by Id<p>"
    		+ "(GET)    /musicdb/albums to get all albums<p>"
    		+ "(POST)   /musicdb/albums to create an album<p>"
    		+ "(PUT)    /musicdb/albums to update an album<p>"
    		+ "(DELETE) /musicdb/albums/{Id} to delete an album by Id<p>"
    		+ "(DELETE) /musicdb/albums to delete all albums<p>";

    @RequestMapping(value = PATH)
    public String error() {
        return USAGE_STRING;
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}