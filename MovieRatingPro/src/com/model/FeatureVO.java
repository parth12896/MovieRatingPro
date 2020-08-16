package com.model;

import java.io.Serializable;

public class FeatureVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int getFeatureId() {
		return featureId;
	}

	public void setFeatureId(int featureId) {
		this.featureId = featureId;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	
	private int featureId;

	private int reviewId;

	private int movieId;

	private int movieType;

	private String featureType;

	private int featureBasedFreq;

	public String getFeatureType() {
		return featureType;
	}

	public void setFeatureType(String featureType) {
		this.featureType = featureType;
	}

	public int getFeatureBasedFreq() {
		return featureBasedFreq;
	}

	public void setFeatureBasedFreq(int featureBasedFreq) {
		this.featureBasedFreq = featureBasedFreq;
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

}
