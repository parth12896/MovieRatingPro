package com.model;

import java.io.Serializable;

public class MovieInfo implements  Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int movieId;
	
	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public int getMovieType() {
		return movieType;
	}

	public void setMovieType(int movieType) {
		this.movieType = movieType;
	}

	private String movieName;
	
	private int movieType;

	

}
