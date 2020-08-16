package com.model;

import java.io.Serializable;

public class FeatureTypeInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getFeatureType() {
		return featureType;
	}

	public void setFeatureType(String featureType) {
		this.featureType = featureType;
	}

	public int getMovieType() {
		return movieType;
	}

	public void setMovieType(int movieType) {
		this.movieType = movieType;
	}

	private String featureType;

	private int movieType;

}
