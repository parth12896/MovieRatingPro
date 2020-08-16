package com.dao.inter;

import java.util.List;

import com.model.CleanReviewModel;
import com.model.FeatureTypeInfo;
import com.model.FeatureVO;
import com.model.FrequencyVO;
import com.model.KeywordInfo;
import com.model.MovieStatus;
import com.model.PolarityModel;
import com.model.PolarityOrderInfo;
import com.model.MovieInfo;
import com.model.MovieModel;
import com.model.MovieTypeInfo;
import com.model.PaginationConfigVO;
import com.model.ReviewModel;
import com.model.ReviewModelObj;
import com.model.StatusInfo;
import com.model.StopWordsVO;
import com.model.TokenVO;
import com.model.TotalPolarityModel;

public interface ReviewDAOIF {

	public StatusInfo insertReview(ReviewModel reviewModel);

	public List<ReviewModel> retriveAllReviews();

	public List<MovieInfo> retriveAllMovieInfo();

	public List<MovieInfo> retriveSpecficMoviesInfo(int MovieType);

	public List<MovieTypeInfo> retriveAllMovieTypes();

	public MovieTypeInfo retriveSpecificMovieTypeName(int MovieType);

	public String retriveMovieNameForId(int MovieId);

	public List<KeywordInfo> retrivePositiveKeywords();

	public List<KeywordInfo> retriveNegativeKeywords();

	public List<String> retriveNegativeKeywordsOnly();

	public StatusInfo insertNegativeKeywords(String keywordV);

	public List<String> retrivePositiveKeywordsOnly();

	public StatusInfo insertPositiveKeywords(String keywordV);

	public StatusInfo deleteSentimentAnalyzer(int type);

	public StatusInfo deleteTotalPolarity(int type);

	public List<ReviewModelObj> retriveReviewList(int type);

	public StatusInfo insertPolarity(PolarityModel polarityModel);

	public List<Integer> retriveUniqueMovieIdsFromSentimentAnalyzer(int type);

	public int retriveTotalPositiveRatingForMovie(Integer MovieIdTemp, int MovieType);

	public int retriveTotalNegativeRatingForMovie(Integer MovieIdTemp, int MovieType);

	public int retriveTotalNeutralRatingForMovie(Integer MovieIdTemp, int type);

	public StatusInfo insertTotalPolarity(TotalPolarityModel totalPolarityModel);

	public List<Integer> retriveMovieIdsIDSORderBy(int type);

	public List<PolarityModel> retrivePolarity(int type);

	public List<PolarityModel> retriveTotalPolarity(int type);

	public List<MovieModel> retriveFVForMovieType(int type);

	public List<Integer> retriveDistinctMovieIdsFromReviews(int type);

	public List<String> retriveFeatureTypes();

	public int retriveCountForFeatureUsingLike(String featureType, Integer reviewIdTemp, int type);

	public List<String> removePositiveKeywordOnly();

	public StatusInfo removePositiveKeyword(String stopWord);

	public List<String> removeNegativeKeywordOnly();

	public StatusInfo removeNegativeKeyword(String stopWord);

	public int retriveTotalPositiveRatingForMovieAndFeatureType(Integer MovieIdTemp, int type, String featureType);

	public int retriveTotalNegativeRatingForMovieAndFeatureType(Integer MovieIdTemp, int type, String featureType);

	public int retriveTotalNeutralRatingForMovieAndFeatureType(Integer MovieIdTemp, int type, String featureType);

	public int retriveTotalFeatureForMovie(Integer MovieIdTemp, int type, String featureType);

	public StatusInfo insertPolarityOrderInfo(PolarityOrderInfo polarityOrderInfo);

	List<PolarityOrderInfo> retriveAllPolarityOrderInfoRankBy();

	public List<PolarityModel> viewTotalPolarityByType(String type);

	public StatusInfo deletePOLARITYORDER();

	public StatusInfo deleteFromReviews();

	public StatusInfo removeHashTags();

	public List<Integer> retriveUniqueMovieIdsForType(String type);

	public StatusInfo insertMovieStatus(MovieStatus movieStatus);

	public StatusInfo deleteMovieStatus();

	int retriveTotalPositiveRatingForAllFeaturesForMovieFromTotalPolarity(Integer movieId, Integer movieType);

	int retriveTotalNegativeRatingForAllFeaturesForMovieFromTotalPolarity(Integer movieId, Integer movieType);

	int retriveTotalNuetralRatingForAllFeaturesForMovieFromTotalPolarity(Integer movieId, Integer movieType);

	public List<MovieStatus> viewClassifyMovies();

	public List<MovieStatus> viewClassifyMoviesForMovieId(int parseInt);

	public String retriveMovieTypeNameForId(int movietype);

	public StatusInfo retriveAllReviewsForPaginationConfig(PaginationConfigVO paginationConfigVO);

	public int retriveLastReviewID();

	public StatusInfo insertBatchReviews(List<ReviewModel> reviewModels);

	public List<String> retriveFeatureTypesForMovieType(int productType);

	public StatusInfo insertBatchPolarity(List<PolarityModel> reviewBasedPolarity);

	public StatusInfo deletePolarityForMovieIdAndMovieType(int movieid, int movietype);

	public StatusInfo insertMovieBasedBatchPolarity(List<PolarityModel> productBasedPolarity);

	public PolarityModel retrivePolarityForMovieandMovieTypeAndFeatureTypeFromTotalPolarity(int productId,
			int productType, String feature);

	public StatusInfo retriveAllPolarityForPaginationConfig(PaginationConfigVO paginationConfigVO);

	public StatusInfo retriveTotalPolarityForPaginationConfig(PaginationConfigVO paginationConfigVO);

	public List<FeatureTypeInfo> retriveFeaturesForSpecficType(int type);

	public List<PolarityModel> viewTotalPolarityByMovieypeAndFeatureType(String movieType, String featureType);

	public StatusInfo deleteFromBestFV(int type);

	public List<String> retriveMovieNames();

	public StatusInfo insertMovieName(String moviename);

	public StatusInfo retriveAllMoviesForPagination(PaginationConfigVO paginationConfigVO);

	public PolarityModel retrivePolarityForMovieandMovieTypeFromTotalPolarityWithoutFeature(int movieId, int movieType);

	public StatusInfo insertBatchPolarityReviewBasedWithoutFeature(List<PolarityModel> reviewBasedPolarity);

	public StatusInfo insertMovieBasedBatchPolarityWithoutFeature(List<PolarityModel> productBasedPolarity);

	public StatusInfo deletePolarityForMovieIdAndMovieTypeWithoutFeature(int movieid, int movietype);

	public StatusInfo retriveAllPolarityWithoutFeatureForPaginationConfig(PaginationConfigVO paginationConfigVO);

	public StatusInfo retriveAllTotalPolarityWithoutFeatureForPaginationConfig(PaginationConfigVO paginationConfigVO);

	public List<Integer> retriveDistinctMovieIdsFromNoFeatureTotalPolarity(int type);

	public int retriveTotalPositiveRatingForMovieAndMovieTypeWithoutFeature(Integer movieIdTemp, int type);

	public int retriveTotalNegativeRatingForMovieAndMovieTypeWithoutFeature(Integer movieIdTemp, int type);

	public int retriveTotalNeutralRatingForMovieAndMovieTypeWithoutFeature(Integer movieIdTemp, int type);

}
