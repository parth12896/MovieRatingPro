package com.model;

import java.io.Serializable;

public class MovieModel  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private int movieId;
	
	private int movieType;
	
	private String movieName;

	public void setFeatureVector(double featureVector) {
		this.featureVector = featureVector;
	}

	public double getFeatureVector() {
		return featureVector;
	}

	
	public void setFeatureCorrelation(double featureCorrelation) {
		this.featureCorrelation = featureCorrelation;
	}

	public double getFeatureCorrelation() {
		return featureCorrelation;
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




	private double featureVector;
	
	
	
	
	private double featureCorrelation;
	
	
	
	

}
