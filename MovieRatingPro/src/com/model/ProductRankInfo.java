package com.model;

import java.util.List;

public class ProductRankInfo {
	
	private List<ReviewCorrelationVO> reviewCollecctionVOList;

	public void setReviewCollecctionVOList(List<ReviewCorrelationVO> reviewCollecctionVOList) {
		this.reviewCollecctionVOList = reviewCollecctionVOList;
	}

	public List<ReviewCorrelationVO> getReviewCollecctionVOList() {
		return reviewCollecctionVOList;
	}
	
	public void setProductModel(List<ProductModel> productModel) {
		this.productModel = productModel;
	}

	public List<ProductModel> getProductModel() {
		return productModel;
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

	private List<ProductModel> productModel;
	
	private boolean isBasedOnFeature;
	
	
	private List<PolarityOrderInfo> polarityOrderInfos;
	
	private String errMsg;

}
