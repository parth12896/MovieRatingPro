package com.delegate.inter;

import java.util.List;

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
 
public interface ReviewDelegateIF {

	public StatusInfo storeReviews(ReviewModel reviewModel);

	public StatusInfo storeReviewsUsingBasedOnUrl(ReviewModel reviewModel);

	public StatusInfo retriveReviews(PaginationConfigVO paginationConfigVO);

	public List<MovieTypeInfo> retriveAllMovieTypes();

	public List<MovieInfo> retriveSpecficMoviesInfo(int movieType);

	public List<KeywordInfo> retrivePositiveKeywords();

	public StatusInfo insertNegativeKeyword(String keywordV);

	public StatusInfo insertPositiveKeyword(String keywordV);

	public List<KeywordInfo> retriveNegativeKeywords();

	public StatusInfo removeTableData(String tableDataToRemove);

	public StatusInfo performAnalysisAndStoreReviews(ReviewModel reviewModel);

	public StatusInfo retriveAllPolarityForPagination(PaginationConfigVO paginationConfigVO);

	public StatusInfo retriveTotalPolarityForPagination(PaginationConfigVO paginationConfigVO);

	public List<FeatureTypeInfo> retriveFeaturesForSpecficType(int parseInt);

	public List<PolarityModel> viewTotalPolarityByMovieypeAndFeatureType(String movieType, String featureType);

	public MovieRankInfo rankBasedOnCorrelation(RankMoviesInput rankMoviesInput);

	public StatusInfo saveMovie(MovieNameModel movieNameModel);

	public StatusInfo retriveAllMoviesForPagination(PaginationConfigVO paginationConfigVO);

	public StatusInfo retriveAllPolarityWithoutFeatureForPagination(PaginationConfigVO paginationConfigVO);

	public StatusInfo retriveAllTotalPolarityWithoutFeatureForPagination(PaginationConfigVO paginationConfigVO);


}
