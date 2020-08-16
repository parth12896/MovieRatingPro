package com.model;

import java.sql.Blob;

public class CleanReviewUIModel {
	
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


	
	public void setCleanReviewDetails(String cleanReviewDetails) {
		this.cleanReviewDetails = cleanReviewDetails;
	}

	public String getCleanReviewDetails() {
		return cleanReviewDetails;
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
	private String cleanReviewDetails;
	
	private int movieId;  
	
	private int movieType;

}
