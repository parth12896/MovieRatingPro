package com.model;

import java.util.List;

public class MovieRankInfo {

	private List<ReviewCorrelationVO> reviewCollecctionVOList;

	public void setReviewCollecctionVOList(List<ReviewCorrelationVO> reviewCollecctionVOList) {
		this.reviewCollecctionVOList = reviewCollecctionVOList;
	}

	public List<ReviewCorrelationVO> getReviewCollecctionVOList() {
		return reviewCollecctionVOList;
	}

	public List<MovieModel> getProductModel() {
		return movieModel;
	}

	public boolean isBasedOnFeature() {
		return isBasedOnFeature;
	}

	public void setBasedOnFeature(boolean isBasedOnFeature) {
		this.isBasedOnFeature = isBasedOnFeature;
	}

	public List<PolarityOrderInfo> getPolarityOrderInfos() {
		return polarityOrderInfos;
	}

	public void setPolarityOrderInfos(List<PolarityOrderInfo> polarityOrderInfos) {
		this.polarityOrderInfos = polarityOrderInfos;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	private List<MovieModel> movieModel;

	public List<MovieModel> getMovieModel() {
		return movieModel;
	}

	public void setMovieModel(List<MovieModel> movieModel) {
		this.movieModel = movieModel;
	}

	private boolean isBasedOnFeature;

	private List<PolarityOrderInfo> polarityOrderInfos;

	private String errMsg;

}
