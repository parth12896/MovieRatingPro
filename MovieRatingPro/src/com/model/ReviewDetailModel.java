package com.model;

public class ReviewDetailModel extends ReviewModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String movieName;

	private String movieType;

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieType() {
		return movieType;
	}

	public void setMovieType(String movieType) {
		this.movieType = movieType;
	}

}
