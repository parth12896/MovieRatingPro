package com.model;

import java.io.Serializable;

public class TotalPolarityModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int getPositiveRating() {
		return positiveRating;
	}

	public void setPositiveRating(int positiveRating) {
		this.positiveRating = positiveRating;
	}

	public int getNegativeRating() {
		return negativeRating;
	}

	public void setNegativeRating(int negativeRating) {
		this.negativeRating = negativeRating;
	}

	public int getNeutralRating() {
		return neutralRating;
	}

	public void setNeutralRating(int neutralRating) {
		this.neutralRating = neutralRating;
	}

	private int positiveRating;

	private int negativeRating;

	private int neutralRating;

	public String getFeatureType() {
		return featureType;
	}

	public void setFeatureType(String featureType) {
		this.featureType = featureType;
	}

	public int getTotalFeature() {
		return totalFeature;
	}

	public void setTotalFeature(int totalFeature) {
		this.totalFeature = totalFeature;
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

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	private String featureType;

	private int totalFeature;
	
	private int movieId;
	
	private int movieType;
	
	private String movieName;

}
