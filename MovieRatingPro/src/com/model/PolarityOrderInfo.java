package com.model;

import java.io.Serializable;

public class PolarityOrderInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getTotalPositive() {
		return totalPositive;
	}

	public void setTotalPositive(int totalPositive) {
		this.totalPositive = totalPositive;
	}

	public int getTotalNegative() {
		return totalNegative;
	}

	public void setTotalNegative(int totalNegative) {
		this.totalNegative = totalNegative;
	}

	public int getTotalFeature() {
		return totalFeature;
	}

	public void setTotalFeature(int totalFeature) {
		this.totalFeature = totalFeature;
	}

	public int getTotalNeutral() {
		return totalNeutral;
	}

	public void setTotalNeutral(int totalNeutral) {
		this.totalNeutral = totalNeutral;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	private int movieId;

	private int type;

	private int totalPositive;

	private int totalNegative;

	private int totalFeature;

	private int totalNeutral;

	private String movieName;

}
