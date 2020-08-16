package com.model;

import java.io.Serializable;

public class MovieStatus implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int movieId;
	
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

	public int getTotalNeutral() {
		return totalNeutral;
	}

	public void setTotalNeutral(int totalNeutral) {
		this.totalNeutral = totalNeutral;
	}

	public int getMovieStatusId() {
		return movieStatusId;
	}

	public void setMovieStatusId(int movieStatusId) {
		this.movieStatusId = movieStatusId;
	}

	public double getTotalRating() {
		return totalRating;
	}

	public void setTotalRating(double totalRating) {
		this.totalRating = totalRating;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private String movieName;
	
	private int totalPositive;
	
	private int totalNegative;
	
	private int totalNeutral;
	
	private int movieStatusId;
	
	private double totalRating;
	
	private String status;
	
	private int maximumPositive;
	
	public int getMaximumPositive() {
		return maximumPositive;
	}

	public void setMaximumPositive(int maximumPositive) {
		this.maximumPositive = maximumPositive;
	}

	public int getMaximumNegative() {
		return maximumNegative;
	}

	public void setMaximumNegative(int maximumNegative) {
		this.maximumNegative = maximumNegative;
	}

	public int getMaximumNeutral() {
		return maximumNeutral;
	}

	public void setMaximumNeutral(int maximumNeutral) {
		this.maximumNeutral = maximumNeutral;
	}

	

	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}

	private int maximumNegative;
	
	private int maximumNeutral;
	
	private double threshold;
	

}
