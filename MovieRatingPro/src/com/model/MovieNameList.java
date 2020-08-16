package com.model;

import java.io.Serializable;

public class MovieNameList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String movieName;
	
	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public int getMovieType() {
		return movieType;
	}

	public void setMovieType(int movieType) {
		this.movieType = movieType;
	}

	private int movieId;
	
	private int movieType;


}
