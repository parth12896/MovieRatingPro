package com.model;

import java.sql.Blob;

public class ReviewModelObj {

	private int reviewId;

	private int movieId;

	private String reviewDetails;

	private int movieType;

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewDetails(String reviewDetails) {
		this.reviewDetails = reviewDetails;
	}

	public String getReviewDetails() {
		return reviewDetails;
	}

	public void setReviewDetailsBlob(Blob reviewDetailsBlob) {
		this.reviewDetailsBlob = reviewDetailsBlob;
	}

	public Blob getReviewDetailsBlob() {
		return reviewDetailsBlob;
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

	private Blob reviewDetailsBlob;

}
