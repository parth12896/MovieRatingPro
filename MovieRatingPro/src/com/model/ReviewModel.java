package com.model;

import java.io.Serializable;

public class ReviewModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int movieid;

	public String getReviewDetails() {
		return reviewDetails;
	}

	public void setReviewDetails(String reviewDetails) {
		this.reviewDetails = reviewDetails;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrlType(String webUrlType) {
		this.webUrlType = webUrlType;
	}

	public String getWebUrlType() {
		return webUrlType;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getXpath() {
		return xpath;
	}

	public void setXpath(String xpath) {
		this.xpath = xpath;
	}

	public int getMovietype() {
		return movietype;
	}

	public void setMovietype(int movietype) {
		this.movietype = movietype;
	}

	public int getMovieid() {
		return movieid;
	}

	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieTypeName() {
		return movieTypeName;
	}

	public void setMovieTypeName(String movieTypeName) {
		this.movieTypeName = movieTypeName;
	}

	private int movietype;

	private String reviewDetails;

	private String webUrl;

	private String webUrlType;

	private int reviewId;

	private String xpath;
	
	private String movieName;
	
	private String movieTypeName;

}
