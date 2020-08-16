package com.model;

public class FrequencyVO {
	
	private int freqId;
	
	public int getFreqId() {
		return freqId;
	}

	public void setFreqId(int freqId) {
		this.freqId = freqId;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getTokenName() {
		return tokenName;
	 }

	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	
	public void setFreq(int freq) {
		this.freq = freq;
	}

	public int getFreq() {
		return freq;
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
	
	private String tokenName;
	
	
	private int freq;
	
	private int movieId;
	
	private int movieType;
	
	

}
