package com.model;

import java.io.Serializable;

public class MovieTypeInfo implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int movieTypeId; 
	
	private String movieName;

	public int getMovieTypeId() {
		return movieTypeId;
	}

	public void setMovieTypeId(int movieTypeId) {
		this.movieTypeId = movieTypeId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	} 

}
