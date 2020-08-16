package com.model;

import java.io.Serializable;
import java.sql.Blob;

public class CleanReviewModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int cleanId;
	
	public int getCleanId() {
		return cleanId;
	}

	public void setCleanId(int cleanId) {
		this.cleanId = cleanId;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	
	public Blob getCleanReviewDetails() {
		return cleanReviewDetails;
	}

	public void setCleanReviewDetails(Blob cleanReviewDetails) {
		this.cleanReviewDetails = cleanReviewDetails;
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



	private int reviewId;

	private int movieId;
	
	private int movieType;
	
	
	private Blob cleanReviewDetails;

}
