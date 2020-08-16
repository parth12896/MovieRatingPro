package com.controller.inter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.model.AJAXResponse;
import com.model.FeatureTypeInfo;
import com.model.KeywordInfo;
import com.model.MovieInfo;
import com.model.MovieNameModel;
import com.model.MovieTypeInfo;
import com.model.PaginationConfigVO;
import com.model.RankMoviesInput;
import com.model.ReviewModel;

public interface ReviewControllerIF {

	public @ResponseBody AJAXResponse obtainRealTimeReviewsAndStore(@RequestBody ReviewModel reviewModel);

	public @ResponseBody List<MovieInfo> retriveMovieInfoForSpecficType(String type);

	public @ResponseBody List<MovieTypeInfo> retriveAllMovieTypes();

	List<MovieInfo> retriveAllMovies();

	AJAXResponse obtainAllReviews(PaginationConfigVO paginationConfigVO);

	AJAXResponse viewPolarity(HttpServletRequest request, PaginationConfigVO paginationConfigVO);

	AJAXResponse viewTotalPolarity(HttpServletRequest request, PaginationConfigVO paginationConfigVO);

	List<FeatureTypeInfo> retriveFeaturesForSpecficType(String type);

	AJAXResponse viewTotalPolarityByFeatureTypeAndMovieType(HttpServletRequest request, String productType,
			String featureType);

	ModelAndView generalCorrelation(HttpServletRequest request, RankMoviesInput rankMoviesInput);

	AJAXResponse saveMovie(HttpServletRequest request, MovieNameModel paginationConfigVO);

	AJAXResponse viewMovies(HttpServletRequest request, PaginationConfigVO paginationConfigVO);

	AJAXResponse viewPolarityWithoutFeature(HttpServletRequest request, PaginationConfigVO paginationConfigVO);

	AJAXResponse viewTotalPolarityWithoutFeature(HttpServletRequest request, PaginationConfigVO paginationConfigVO);

}
