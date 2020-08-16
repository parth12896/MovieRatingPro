package com.delegate.impl;

import java.util.List;

import com.delegate.inter.ReviewDelegateIF;
import com.model.CleanReviewUIModel;
import com.model.FeatureTypeInfo;
import com.model.FeatureVO;
import com.model.FrequencyVO;
import com.model.KeywordInfo;
import com.model.MovieStatus;
import com.model.PolarityModel;
import com.model.RankMoviesInput;
import com.model.MovieInfo;
import com.model.MovieModel;
import com.model.MovieNameModel;
import com.model.MovieRankInfo;
import com.model.MovieTypeInfo;
import com.model.PaginationConfigVO;
import com.model.ReviewDetailModel;
import com.model.ReviewModel;
import com.model.StatusInfo;
import com.model.StopWordsVO;
import com.model.TokenVO;
import com.service.inter.ReviewServiceIF;

public class ReviewDelegateImpl implements ReviewDelegateIF {

	private ReviewServiceIF reviewService;

	@Override
	public StatusInfo storeReviews(ReviewModel reviewModel) {
		return reviewService.storeReviews(reviewModel);
	}

	public void setReviewService(ReviewServiceIF reviewService) {
		this.reviewService = reviewService;
	}

	public ReviewServiceIF getReviewService() {
		return reviewService;
	}

	@Override
	public StatusInfo storeReviewsUsingBasedOnUrl(ReviewModel reviewModel) {
		return reviewService.obtainReviewsAndStore(reviewModel, reviewModel.getWebUrlType());
	}

	@Override
	public StatusInfo retriveReviews(PaginationConfigVO paginationConfigVO) {
		return reviewService.obtainAllReviewsForPagination(paginationConfigVO);
	}

	@Override
	public List<MovieTypeInfo> retriveAllMovieTypes() {
		return reviewService.retriveAllMovieTypes();
	}

	@Override
	public List<MovieInfo> retriveSpecficMoviesInfo(int movieType) {
		return reviewService.retriveSpecficMoviesInfo(movieType);
	}

	@Override
	public StatusInfo insertNegativeKeyword(String keywordV) {
		return reviewService.insertNegativeKeyword(keywordV);
	}

	@Override
	public StatusInfo insertPositiveKeyword(String keywordV) {
		return reviewService.insertPositiveKeyword(keywordV);
	}

	@Override
	public List<KeywordInfo> retriveNegativeKeywords() {
		return reviewService.retriveNegativeKeywords();
	}

	@Override
	public List<KeywordInfo> retrivePositiveKeywords() {
		return reviewService.retrivePositiveKeywords();
	}

	@Override
	public StatusInfo removeTableData(String tableDataToRemove) {
		return reviewService.removeTableData(tableDataToRemove);
	}

	
	public StatusInfo performAnalysisAndStoreReviews(ReviewModel reviewModel){
		return reviewService.performAnalysisAndStoreReviews(reviewModel);
	}

	@Override
	public StatusInfo retriveAllPolarityForPagination(PaginationConfigVO paginationConfigVO) {
		return reviewService.retriveAllPolarityForPagination(paginationConfigVO);
	}

	@Override
	public StatusInfo retriveTotalPolarityForPagination(PaginationConfigVO paginationConfigVO) {
		return reviewService.retriveTotalPolarityForPagination(paginationConfigVO);
	}

	@Override
	public List<FeatureTypeInfo> retriveFeaturesForSpecficType(int type) {
		return reviewService.retriveFeaturesForSpecficType(type);
	}

	@Override
	public List<PolarityModel> viewTotalPolarityByMovieypeAndFeatureType(String movieType, String featureType) {
		return reviewService.viewTotalPolarityByMovieypeAndFeatureType(movieType,featureType);
	}

	@Override
	public MovieRankInfo rankBasedOnCorrelation(RankMoviesInput rankMoviesInput) {
		return reviewService.rankBasedOnCorrelation(rankMoviesInput);
	}

	@Override
	public StatusInfo saveMovie(MovieNameModel movieNameModel) {
		return reviewService.saveMovie(movieNameModel);
	}

	@Override
	public StatusInfo retriveAllMoviesForPagination(PaginationConfigVO paginationConfigVO) {
		return reviewService.retriveAllMoviesForPagination(paginationConfigVO);
	}

	@Override
	public StatusInfo retriveAllPolarityWithoutFeatureForPagination(PaginationConfigVO paginationConfigVO) {
		return reviewService.retriveAllPolarityWithoutFeatureForPagination(paginationConfigVO);
	}

	@Override
	public StatusInfo retriveAllTotalPolarityWithoutFeatureForPagination(PaginationConfigVO paginationConfigVO) {
		return reviewService.retriveAllTotalPolarityWithoutFeatureForPagination(paginationConfigVO);
	}

}
