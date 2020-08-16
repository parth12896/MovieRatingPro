package com.model;

import java.io.Serializable;
import java.util.List;

public class PolarityComputationTempObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<PolarityModel> reviewBasedPolarity;

	private List<PolarityModel> productBasedPolarity;

	public List<PolarityModel> getReviewBasedPolarity() {
		return reviewBasedPolarity;
	}

	public void setReviewBasedPolarity(List<PolarityModel> reviewBasedPolarity) {
		this.reviewBasedPolarity = reviewBasedPolarity;
	}

	public List<PolarityModel> getProductBasedPolarity() {
		return productBasedPolarity;
	}

	public void setProductBasedPolarity(List<PolarityModel> productBasedPolarity) {
		this.productBasedPolarity = productBasedPolarity;
	}

}
