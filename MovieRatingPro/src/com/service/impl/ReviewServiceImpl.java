package com.service.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.deeplearning4j.optimize.api.Category;
import org.deeplearning4j.optimize.api.CategoryTrain;
import org.deeplearning4j.optimize.api.RNNClassifier;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.constants.ReviewConstantsIF;
import com.dao.inter.ReviewDAOIF;
import com.model.FeatureTypeInfo;
import com.model.KeywordInfo;
import com.model.PolarityModel;
import com.model.PolarityOrderInfo;
import com.model.ProductRankInfo;
import com.model.RankMoviesInput;
import com.model.MovieInfo;
import com.model.MovieNameModel;
import com.model.MovieRankInfo;
import com.model.MovieTypeInfo;
import com.model.PaginationConfigVO;
import com.model.PolarityComputationTempObject;
import com.model.ReviewDetailModel;
import com.model.ReviewModel;
import com.model.ReviewModelObj;
import com.model.StatusInfo;
import com.model.StopWordsVO;
import com.noiseremove.model.RemoveNoisyData;
import com.reviewinfo.ReviewProcessing;
import com.service.inter.ReviewServiceIF;

import com.util.FeatureCheckUtil;
import com.util.GenerateConjuctionSplitSentences;

public class ReviewServiceImpl implements ReviewServiceIF {

	@Autowired
	private ReviewDAOIF reviewDao;

	public ReviewDAOIF getReviewDao() {
		return reviewDao;
	}

	public void setReviewDao(ReviewDAOIF reviewDao) {
		this.reviewDao = reviewDao;
	}

	@Override
	public StatusInfo storeReviews(ReviewModel reviewModel) {

		StatusInfo statusInfo = null;
		try {

			statusInfo = reviewDao.insertReview(reviewModel);

		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setExceptionMsg(e.getMessage());
			return statusInfo;
		}
		return statusInfo;
	}

	public String getTextDivData(String url) {

		StringBuilder stringBuilder = new StringBuilder();
		try {
			Document doc;
			doc = Jsoup.connect(url).get();
			Elements links = doc.select("div");
			for (Element link : links) {

				String linkText = link.text();
				stringBuilder.append(linkText);
				stringBuilder.append(ReviewConstantsIF.CONSTANTS.SPACE);
			}
		} catch (Exception e) {
			System.out.println("EXCEPTION--->" + e);
		}
		return stringBuilder.toString();

	}

	/*
	 * public static void main(String s[]) { try {
	 * ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
	 * "review.xml"); ReviewServiceIF reviewServiceIF = (ReviewServiceIF) ctx
	 * .getBean("reviewServiceBean");
	 * 
	 * List<PolarityModel> rerviewModel=reviewServiceIF.retrivePolarity(1);
	 * 
	 * for(PolarityModel rerviewModelTemp:rerviewModel) {
	 * System.out.println("VALUE"+rerviewModelTemp.getNegativeRating()); }
	 * 
	 * 
	 * } catch (Exception e) { e.printStackTrace();
	 * System.out.println("EXCEPTION--->" + e); } }
	 */

	@Override
	public List<ReviewDetailModel> obtainAllReviews() {

		List<ReviewDetailModel> reviewModelList = null;
		try {

			List<ReviewModel> reviewModelListTemp = reviewDao.retriveAllReviews();
			if (null == reviewModelListTemp || reviewModelListTemp.isEmpty() || reviewModelListTemp.size() == 0) {

				return null;

			}
			reviewModelList = new ArrayList<ReviewDetailModel>();
			for (ReviewModel reviewModelTemp : reviewModelListTemp) {

				MovieTypeInfo movieTypeInfo = reviewDao.retriveSpecificMovieTypeName(reviewModelTemp.getMovietype());

				String movieName = reviewDao.retriveMovieNameForId(reviewModelTemp.getMovieid());

				ReviewDetailModel reviewDetailModel = new ReviewDetailModel();

				reviewDetailModel.setMovieName(movieName);
				reviewDetailModel.setMovietype(reviewModelTemp.getMovietype());
				reviewDetailModel.setMovieid(reviewModelTemp.getMovieid());
				reviewDetailModel.setReviewDetails(reviewModelTemp.getReviewDetails());
				reviewDetailModel.setReviewId(reviewModelTemp.getReviewId());

				reviewModelList.add(reviewDetailModel);

			}

			return reviewModelList;

		} catch (Exception e) {
			System.out.println("Exception e" + e.getMessage());

		}
		return reviewModelList;

	}

	private StatusInfo obtainReviewsFromIMDBAndStore(ReviewModel reviewModel) {

		ReviewProcessing reviewProcessing = new ReviewProcessing();

		StatusInfo statusInfo = null;
		try {

			Document doc = Jsoup.connect(reviewModel.getWebUrl())
					.userAgent(
							"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
					.timeout(10 * 1000).get();

			Elements divContent = doc.select(reviewModel.getXpath());
			for (Element ele : divContent) {

				String reviewDetails = ele.text();
				if (reviewDetails != null && !reviewDetails.isEmpty()) {

					ReviewModel reviewModelTemp = new ReviewModel();

					reviewModelTemp.setMovieid(reviewModel.getMovieid());
					reviewModelTemp.setMovietype(reviewModel.getMovietype());
					/*
					 * reviewModelTemp.setReviewDetails(
					 * reviewProcessing.obtainCorrectDataFromReview(reviewModel.
					 * getReviewDetails()));
					 */
					reviewModelTemp.setReviewDetails(reviewDetails);

					StatusInfo insertReview = reviewDao.insertReview(reviewModelTemp);

					if (!insertReview.isStatus()) {
						return insertReview;
					}

				}
			}

			statusInfo = new StatusInfo();
			statusInfo.setStatus(true);
			return statusInfo;

		} catch (Exception e) {
			System.out.println("EXCEPTION--->" + e);
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(ReviewConstantsIF.Message.MESSAGE_INTERNAL);
			statusInfo.setExceptionMsg(e.getMessage());
			return statusInfo;
		}
	}

	@Override
	public StatusInfo obtainReviewsAndStore(ReviewModel reviewModel, String reviewUrl) {

		StatusInfo statusInfo = null;
		try {
			return obtainReviewsFromIMDBAndStore(reviewModel);

		} catch (Exception e) {
			System.out.println("Exception" + e.getMessage());
			e.printStackTrace();
			StatusInfo statusInfo1 = new StatusInfo();
			statusInfo1.setStatus(false);
			statusInfo1.setErrMsg(ReviewConstantsIF.Message.MESSAGE_INTERNAL);
			statusInfo1.setExceptionMsg(e.getMessage());

		}
		return statusInfo;
	}

	@Override
	public List<MovieTypeInfo> retriveAllMovieTypes() {

		List<MovieTypeInfo> movieTypesInfo = null;
		try {
			return reviewDao.retriveAllMovieTypes();
		} catch (Exception e) {
			return movieTypesInfo;
		}

	}

	@Override
	public List<MovieInfo> retriveSpecficMoviesInfo(int productType) {

		List<MovieInfo> productTypeInfos = null;
		try {
			return reviewDao.retriveSpecficMoviesInfo(productType);
		} catch (Exception e) {
			return productTypeInfos;
		}

	}

	@Override
	public StatusInfo insertNegativeKeyword(String keywordV) {
		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();
			List<String> keyWordList = reviewDao.retriveNegativeKeywordsOnly();

			if (null == keyWordList || keyWordList.isEmpty()) {
				statusInfo = reviewDao.insertNegativeKeywords(keywordV);
				statusInfo.setStatus(true);
				return statusInfo;
			}
			if (keyWordList.contains(keywordV)) {
				statusInfo.setErrMsg(ReviewConstantsIF.Message.NEGATIVE_KEYWORD_EXIST);
				statusInfo.setStatus(false);
				return statusInfo;
			} else {
				statusInfo = reviewDao.insertNegativeKeywords(keywordV);
				statusInfo.setStatus(true);
				return statusInfo;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			statusInfo = new StatusInfo();
			statusInfo.setErrMsg(e.getMessage());
			statusInfo.setStatus(false);
			return statusInfo;
		}
	}

	@Override
	public StatusInfo insertPositiveKeyword(String keywordV) {
		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();

			List<String> keyWordList = reviewDao.retrivePositiveKeywordsOnly();

			if (null == keyWordList || keyWordList.isEmpty()) {
				statusInfo = reviewDao.insertPositiveKeywords(keywordV);
				statusInfo.setStatus(true);
				return statusInfo;
			}

			if (keyWordList.contains(keywordV)) {
				statusInfo.setErrMsg(ReviewConstantsIF.Message.POSITIVE_KEYWORD_EXIST);
				statusInfo.setStatus(false);
				return statusInfo;
			} else {
				statusInfo = reviewDao.insertPositiveKeywords(keywordV);
				statusInfo.setStatus(true);
				return statusInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			statusInfo = new StatusInfo();
			statusInfo.setErrMsg(e.getMessage());
			statusInfo.setStatus(false);
			return statusInfo;
		}
	}

	@Override
	public List<KeywordInfo> retriveNegativeKeywords() {
		List<KeywordInfo> negativeKeyList = null;
		try {
			negativeKeyList = reviewDao.retriveNegativeKeywords();
			if (null == negativeKeyList) {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			return null;
		}
		return negativeKeyList;
	}

	@Override
	public List<KeywordInfo> retrivePositiveKeywords() {
		List<KeywordInfo> positiveKeyList = null;
		try {
			positiveKeyList = reviewDao.retrivePositiveKeywords();
			if (null == positiveKeyList) {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			return null;
		}
		return positiveKeyList;
	}

	private List<ReviewModelObj> retriveReviewList(int type) {
		List<ReviewModelObj> reviewModelList = null;
		try {
			List<ReviewModelObj> reviewModel = reviewDao.retriveReviewList(type);

			if (null == reviewModel) {
				return null;
			} else {
				reviewModelList = new ArrayList<ReviewModelObj>();

				for (ReviewModelObj revModelTemp : reviewModel) {
					ReviewModelObj reviewModelObj = new ReviewModelObj();
					reviewModelObj.setMovieId(revModelTemp.getMovieId());
					reviewModelObj.setMovieType(revModelTemp.getMovieType());
					reviewModelObj.setReviewId(revModelTemp.getReviewId());
					String reviewDetails = convertFromBlobToString(revModelTemp.getReviewDetailsBlob());
					reviewModelObj.setReviewDetails(reviewDetails);
					reviewModelList.add(reviewModelObj);
				}

				return reviewModelList;

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			return null;
		}
	}

	private String convertFromBlobToString(Blob blob) {

		byte[] bdata = null;
		try {
			bdata = blob.getBytes(1, (int) blob.length());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String text = new String(bdata);
		return text;
	}

	@Override
	public StatusInfo removePositiveKeyword(String stopWord) {
		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();

			List<String> keyWordList = reviewDao.removePositiveKeywordOnly();

			if (!keyWordList.contains(stopWord)) {
				statusInfo.setErrMsg(ReviewConstantsIF.Message.NO_POSITIVEKEYWORD_EXIST);
				statusInfo.setStatus(false);
				return statusInfo;
			} else {
				statusInfo = reviewDao.removePositiveKeyword(stopWord);
				statusInfo.setStatus(true);
				return statusInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			statusInfo = new StatusInfo();
			statusInfo.setErrMsg(e.getMessage());
			statusInfo.setStatus(false);
			return statusInfo;
		}
	}

	@Override
	public StatusInfo removeNegativeKeyword(String stopWord) {
		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();

			List<String> keyWordList = reviewDao.removeNegativeKeywordOnly();

			if (!keyWordList.contains(stopWord)) {
				statusInfo.setErrMsg(ReviewConstantsIF.Message.NO_NEGATIVEKEYWORD_EXIST);
				statusInfo.setStatus(false);
				return statusInfo;
			} else {
				statusInfo = reviewDao.removeNegativeKeyword(stopWord);
				statusInfo.setStatus(true);
				return statusInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			statusInfo = new StatusInfo();
			statusInfo.setErrMsg(e.getMessage());
			statusInfo.setStatus(false);
			return statusInfo;
		}
	}

	@Override
	public List<PolarityModel> viewTotalPolarityByType(String type) {
		List<PolarityModel> polarityList = null;
		try {
			polarityList = reviewDao.viewTotalPolarityByType(type);
			if (null == polarityList) {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			return null;
		}
		return polarityList;
	}

	@Override

	public StatusInfo removeTableData(String tableDataToRemove) {
		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();

			if (tableDataToRemove.equals("review")) {
				statusInfo = reviewDao.deleteFromReviews();
			}

			if (tableDataToRemove.equals("hashtag")) {
				statusInfo = reviewDao.removeHashTags();
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			statusInfo = new StatusInfo();
			statusInfo.setErrMsg(e.getMessage());
			statusInfo.setStatus(false);
			return statusInfo;
		}
		return statusInfo;
	}

	@Override
	public StatusInfo obtainAllReviewsForPagination(PaginationConfigVO paginationConfigVO) {
		StatusInfo stat = new StatusInfo();

		try {

			stat = reviewDao.retriveAllReviewsForPaginationConfig(paginationConfigVO);

			if (stat.isStatus()) {

				List<ReviewModel> reviewModelList = (List<ReviewModel>) stat.getModel();

				if (reviewModelList != null && !reviewModelList.isEmpty()) {

					for (ReviewModel reviewModel : reviewModelList) {

						String productName = reviewDao.retriveMovieNameForId(reviewModel.getMovieid());

						String productTypeName = reviewDao.retriveMovieTypeNameForId(reviewModel.getMovietype());

						reviewModel.setMovieName(productName);
						reviewModel.setMovieTypeName(productTypeName);
					}

				}

				stat.setModel(reviewModelList);

			}

		} catch (Exception e) {
			System.out.println("Exception e" + e.getMessage());
			stat.setStatus(false);
			stat.setExceptionMsg(e.getMessage());

			return stat;

		}

		stat.setStatus(true);
		return stat;
	}

	@Override
	public StatusInfo performAnalysisAndStoreReviews(ReviewModel reviewModel) {

		StatusInfo statusInfo = null;
		try {

			int latestCountOfReview = reviewDao.retriveLastReviewID();

			if (latestCountOfReview <= 0) {
				latestCountOfReview = 1;
			} else {
				latestCountOfReview = latestCountOfReview + 1;
			}

			Document doc = Jsoup.connect(reviewModel.getWebUrl())
					.userAgent(
							"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
					.timeout(10 * 1000).get();

			Elements divContent = doc.select(reviewModel.getXpath());

			if (null == divContent) {

				statusInfo = new StatusInfo();
				statusInfo.setStatus(false);
				statusInfo.setErrMsg("The Website has Blocked Access.Please try a diffrent website");
				return statusInfo;
			}

			List<ReviewModel> reviewModels = new ArrayList<ReviewModel>();

			boolean isEmpty = true;

			RemoveNoisyData removeNoisyData = new RemoveNoisyData();

			for (Element ele : divContent) {

				isEmpty = false;

				String reviewDetails = ele.text();
				if (reviewDetails != null && !reviewDetails.isEmpty()) {

					ReviewModel reviewModelTemp = new ReviewModel();

					reviewModelTemp.setMovieid(reviewModel.getMovieid());
					reviewModelTemp.setMovietype(reviewModel.getMovietype());

					reviewDetails = removeNoisyData.removeNoisyData(reviewDetails);

					reviewModelTemp.setReviewDetails(reviewDetails);
					reviewModelTemp.setReviewId(latestCountOfReview);

					latestCountOfReview = latestCountOfReview + 1;

					reviewModels.add(reviewModelTemp);
				}
			}

			if (reviewModels != null && !reviewModels.isEmpty()) {

				// Now Store the Review Model

				StatusInfo statusInfo2 = reviewDao.insertBatchReviews(reviewModels);

				if (statusInfo2.isStatus()) {

					// Now Retrive the Reviews

					PolarityComputationTempObject polarityModels = performReviewBasedSentimentComputation(reviewModels,
							reviewModel.getMovietype(), reviewModel.getMovieid());

					// Now Store the Review Based Sentiments

					statusInfo2 = reviewDao.insertBatchPolarity(polarityModels.getReviewBasedPolarity());

					if (statusInfo2.isStatus()) {

						// Delete the Old Polarity Data
						statusInfo2 = reviewDao.deletePolarityForMovieIdAndMovieType(reviewModel.getMovieid(),
								reviewModel.getMovietype());

						if (statusInfo2.isStatus()) {

							statusInfo2 = reviewDao
									.insertMovieBasedBatchPolarity(polarityModels.getProductBasedPolarity());

							if (statusInfo2.isStatus()) {

							} else {

								statusInfo = new StatusInfo();
								statusInfo.setStatus(false);
								statusInfo.setErrMsg(ReviewConstantsIF.Message.COULD_NOT_SAVE_MOVIE_POLARITY);
								return statusInfo;
							}

						} else {
							statusInfo = new StatusInfo();
							statusInfo.setStatus(false);
							statusInfo.setErrMsg(ReviewConstantsIF.Message.COULD_NOT_REMOVE_OLD_POLARITY);
							return statusInfo;
						}

					} else {

						statusInfo = new StatusInfo();
						statusInfo.setStatus(false);
						statusInfo.setErrMsg(ReviewConstantsIF.Message.COULD_NOT_SAVE_REVIEW_POLARITY);
						return statusInfo;
					}

				} else {
					statusInfo = new StatusInfo();
					statusInfo.setStatus(false);
					statusInfo.setErrMsg(ReviewConstantsIF.Message.COULD_NOT_SAVE_REVIEWS);
					return statusInfo;
				}

				// Now Perform the review based Sentimennts

				PolarityComputationTempObject polarityComputationTempWithoutFeature = performReviewBasedSentimentComputationWithoutFeature(
						reviewModels, reviewModel.getMovietype(), reviewModel.getMovieid());

				statusInfo = savePolarityWithoutFeature(polarityComputationTempWithoutFeature, reviewModel);

				if (!statusInfo.isStatus()) {

					return statusInfo;
				}

			} else {

				statusInfo = new StatusInfo();
				statusInfo.setStatus(false);
				statusInfo.setErrMsg("The Website has Blocked Access.Please try a diffrent website");
				return statusInfo;
			}

			if (isEmpty) {

				statusInfo = new StatusInfo();
				statusInfo.setStatus(false);
				statusInfo.setErrMsg("The Website has Blocked Access.Please try a diffrent website");
				return statusInfo;

			}

			statusInfo = new StatusInfo();
			statusInfo.setStatus(true);
			return statusInfo;

		} catch (Exception e) {
			System.out.println("EXCEPTION--->" + e);
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(ReviewConstantsIF.Message.MESSAGE_INTERNAL);
			statusInfo.setExceptionMsg(e.getMessage());
			return statusInfo;
		}

	}

	private StatusInfo savePolarityWithoutFeature(PolarityComputationTempObject polarityModels,
			ReviewModel reviewModel) {

		StatusInfo statusInfo2 = reviewDao
				.insertBatchPolarityReviewBasedWithoutFeature(polarityModels.getReviewBasedPolarity());

		if (statusInfo2.isStatus()) {

			// Delete the Old Polarity Data
			statusInfo2 = reviewDao.deletePolarityForMovieIdAndMovieTypeWithoutFeature(reviewModel.getMovieid(),
					reviewModel.getMovietype());

			if (statusInfo2.isStatus()) {

				statusInfo2 = reviewDao
						.insertMovieBasedBatchPolarityWithoutFeature(polarityModels.getProductBasedPolarity());

				if (statusInfo2.isStatus()) {

					return statusInfo2;
				} else {

					StatusInfo statusInfo = new StatusInfo();
					statusInfo.setStatus(false);
					statusInfo.setErrMsg(ReviewConstantsIF.Message.COULD_NOT_SAVE_MOVIE_POLARITY);
					return statusInfo;
				}

			} else {
				StatusInfo statusInfo = new StatusInfo();
				statusInfo.setStatus(false);
				statusInfo.setErrMsg(ReviewConstantsIF.Message.COULD_NOT_REMOVE_OLD_POLARITY);
				return statusInfo;
			}

		} else {

			StatusInfo statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(ReviewConstantsIF.Message.COULD_NOT_SAVE_REVIEW_POLARITY);
			return statusInfo;
		}
	}

	private PolarityComputationTempObject performReviewBasedSentimentComputation(List<ReviewModel> reviewModelObjList,
			int movieType, int movieId) throws IOException {

		PolarityComputationTempObject polarityComputationTempObject = new PolarityComputationTempObject();

		List<PolarityModel> polarityModels = new ArrayList<PolarityModel>();

		List<String> positiveKeyWordList = reviewDao.retrivePositiveKeywordsOnly();

		if (null == positiveKeyWordList) {
			return null;
		}

		List<String> negativeKeyWordList = reviewDao.retriveNegativeKeywordsOnly();

		List<String> featureTypeList = reviewDao.retriveFeatureTypesForMovieType(movieType);

		if (null == negativeKeyWordList) {
			return null;
		}

		Map<String, Integer> featureTypePolarityPositive = new HashMap<String, Integer>();
		Map<String, Integer> featureTypePolarityNegative = new HashMap<String, Integer>();
		Map<String, Integer> featureTypePolarityNeutral = new HashMap<String, Integer>();

		for (String feature : featureTypeList) {

			PolarityModel polarityModel = reviewDao
					.retrivePolarityForMovieandMovieTypeAndFeatureTypeFromTotalPolarity(movieId, movieType, feature);

			if (null == polarityModel) {

				featureTypePolarityPositive.put(feature, 0);
				featureTypePolarityNegative.put(feature, 0);
				featureTypePolarityNeutral.put(feature, 0);
			} else {
				featureTypePolarityPositive.put(feature, polarityModel.getPositiveRating());
				featureTypePolarityNegative.put(feature, polarityModel.getNegativeRating());
				featureTypePolarityNeutral.put(feature, polarityModel.getNeutralRating());
			}

		}

		GenerateConjuctionSplitSentences generateConjuctionSplitSentences = new GenerateConjuctionSplitSentences();

		RNNClassifier rnnClassifier = new RNNClassifier();

		// Training the Classifier
		CategoryTrain categoryTrain = new CategoryTrain();
		categoryTrain.setPositiveLabel(ReviewConstantsIF.Keys.POSITIVE);
		categoryTrain.setPositiveTrainList(positiveKeyWordList);
		categoryTrain.setNegativeLabel(ReviewConstantsIF.Keys.NEGATIVE);
		categoryTrain.setNegativeTrainList(negativeKeyWordList);

		rnnClassifier.learn(categoryTrain);

		FeatureCheckUtil fetureCheckUtil = new FeatureCheckUtil();

		for (ReviewModel revModelObj : reviewModelObjList) {

			String reviewDetails = revModelObj.getReviewDetails();

			Set<String> sentences = new HashSet<String>();

			sentences = generateConjuctionSplitSentences.obtainSentencesBasedOnConjuctionList(reviewDetails);

			for (String featureType : featureTypeList) {

				int positivePolarity = 0;

				int negativePolarity = 0;

				int neutralPolarity = 0;

				for (String reviewStatement : sentences) {

					try {

						boolean isFeature = fetureCheckUtil.checkStatementHasFeature(reviewStatement, featureType);

						if (isFeature) {

							List<Category> categoryList = rnnClassifier.predict(reviewStatement);

							if (categoryList != null) {
								if (categoryList.size() > 1) {
									neutralPolarity = neutralPolarity + 1;
								} else {
									Category catLabel = categoryList.get(0);

									String catName = catLabel.getCategory();

									if (catName != null && catName.equals(ReviewConstantsIF.Keys.POSITIVE)) {
										positivePolarity = positivePolarity + 1;
									} else {
										negativePolarity = negativePolarity + 1;
									}
								}
							}
						} else {

						}

					} catch (Exception e) {

					}
				}

				if (positivePolarity == 0 && negativePolarity == 0) {
					neutralPolarity = neutralPolarity + 1;
				}

				// Creating an Object of Polarity and Inserting

				PolarityModel polarityModel = new PolarityModel();

				polarityModel.setReviewId(revModelObj.getReviewId());
				polarityModel.setPositiveRating(positivePolarity);
				polarityModel.setNegativeRating(negativePolarity);
				polarityModel.setNeutralRating(neutralPolarity);
				polarityModel.setMovieId(movieId);
				polarityModel.setMovieType(movieType);
				polarityModel.setFeatureType(featureType);
				// Compute For all the Reviews
				int oldValue = featureTypePolarityPositive.get(featureType);

				int newValue = oldValue + positivePolarity;

				featureTypePolarityPositive.put(featureType, newValue);

				oldValue = featureTypePolarityNegative.get(featureType);

				newValue = oldValue + negativePolarity;

				featureTypePolarityNegative.put(featureType, newValue);

				oldValue = featureTypePolarityNeutral.get(featureType);

				newValue = oldValue + neutralPolarity;

				featureTypePolarityNeutral.put(featureType, newValue);

				polarityModels.add(polarityModel);

			}

		}

		// Now Run the HashMap and Fill the Feature Types

		List<PolarityModel> moveieWisePolarity = new ArrayList<PolarityModel>();

		for (String featureT : featureTypeList) {

			PolarityModel polarityModel = new PolarityModel();

			polarityModel.setFeatureType(featureT);
			polarityModel.setNegativeRating(featureTypePolarityNegative.get(featureT));
			polarityModel.setPositiveRating(featureTypePolarityPositive.get(featureT));
			polarityModel.setNeutralRating(featureTypePolarityNeutral.get(featureT));
			polarityModel.setMovieId(movieId);
			polarityModel.setMovieType(movieType);

			moveieWisePolarity.add(polarityModel);
		}

		polarityComputationTempObject.setProductBasedPolarity(moveieWisePolarity);

		polarityComputationTempObject.setReviewBasedPolarity(polarityModels);

		return polarityComputationTempObject;

	}

	private PolarityComputationTempObject performReviewBasedSentimentComputationWithoutFeature(
			List<ReviewModel> reviewModelObjList, int movieType, int movieId) throws IOException {

		PolarityComputationTempObject polarityComputationTempObject = new PolarityComputationTempObject();

		List<PolarityModel> polarityModels = new ArrayList<PolarityModel>();

		List<String> positiveKeyWordList = reviewDao.retrivePositiveKeywordsOnly();

		if (null == positiveKeyWordList) {
			return null;
		}

		List<String> negativeKeyWordList = reviewDao.retriveNegativeKeywordsOnly();

		if (null == negativeKeyWordList) {
			return null;
		}

		int totalPositiveMovieBased = 0;
		int totalNegativeMovieBased = 0;
		int totalNeutralMovieBased = -0;

		PolarityModel polarityModelWithoutFeature = reviewDao
				.retrivePolarityForMovieandMovieTypeFromTotalPolarityWithoutFeature(movieId, movieType);

		if (polarityModelWithoutFeature != null) {
			totalPositiveMovieBased = polarityModelWithoutFeature.getPositiveRating();
			totalNegativeMovieBased = polarityModelWithoutFeature.getNegativeRating();
			totalNeutralMovieBased = polarityModelWithoutFeature.getNeutralRating();
		}
		GenerateConjuctionSplitSentences generateConjuctionSplitSentences = new GenerateConjuctionSplitSentences();

		RNNClassifier rnnClassifier = new RNNClassifier();

		// Training the Classifier
		CategoryTrain categoryTrain = new CategoryTrain();
		categoryTrain.setPositiveLabel(ReviewConstantsIF.Keys.POSITIVE);
		categoryTrain.setPositiveTrainList(positiveKeyWordList);
		categoryTrain.setNegativeLabel(ReviewConstantsIF.Keys.NEGATIVE);
		categoryTrain.setNegativeTrainList(negativeKeyWordList);

		rnnClassifier.learn(categoryTrain);

		FeatureCheckUtil fetureCheckUtil = new FeatureCheckUtil();

		for (ReviewModel revModelObj : reviewModelObjList) {

			String reviewDetails = revModelObj.getReviewDetails();

			Set<String> sentences = new HashSet<String>();

			sentences = generateConjuctionSplitSentences.obtainSentencesBasedOnConjuctionList(reviewDetails);

			int positivePolarity = 0;

			int negativePolarity = 0;

			int neutralPolarity = 0;

			for (String reviewStatement : sentences) {

				try {

					List<Category> categoryList = rnnClassifier.predict(reviewStatement);

					if (categoryList != null) {
						if (categoryList.size() > 1) {
							neutralPolarity = neutralPolarity + 1;
						} else {
							Category catLabel = categoryList.get(0);

							String catName = catLabel.getCategory();

							if (catName != null && catName.equals(ReviewConstantsIF.Keys.POSITIVE)) {
								positivePolarity = positivePolarity + 1;
							} else {
								negativePolarity = negativePolarity + 1;
							}
						}
					}

				} catch (Exception e) {

				}
			}

			if (positivePolarity == 0 && negativePolarity == 0) {
				neutralPolarity = neutralPolarity + 1;
			}

			// Creating an Object of Polarity and Inserting

			PolarityModel polarityModel = new PolarityModel();

			polarityModel.setReviewId(revModelObj.getReviewId());
			polarityModel.setPositiveRating(positivePolarity);
			polarityModel.setNegativeRating(negativePolarity);
			polarityModel.setNeutralRating(neutralPolarity);
			polarityModel.setMovieId(movieId);
			polarityModel.setMovieType(movieType);
			// Compute For all the Reviews

			totalPositiveMovieBased = totalPositiveMovieBased + positivePolarity;

			totalNegativeMovieBased = totalNegativeMovieBased + negativePolarity;

			totalNeutralMovieBased = totalNeutralMovieBased + neutralPolarity;

			polarityModels.add(polarityModel);

		}

		// Now Run the HashMap and Fill the Feature Types

		List<PolarityModel> moveieWisePolarity = new ArrayList<PolarityModel>();

		PolarityModel polarityModel = new PolarityModel();

		polarityModel.setNegativeRating(totalNegativeMovieBased);
		polarityModel.setPositiveRating(totalPositiveMovieBased);
		polarityModel.setNeutralRating(totalNeutralMovieBased);
		polarityModel.setMovieId(movieId);
		polarityModel.setMovieType(movieType);

		moveieWisePolarity.add(polarityModel);

		polarityComputationTempObject.setProductBasedPolarity(moveieWisePolarity);

		polarityComputationTempObject.setReviewBasedPolarity(polarityModels);

		return polarityComputationTempObject;

	}

	@Override
	public StatusInfo retriveAllPolarityForPagination(PaginationConfigVO paginationConfigVO) {
		StatusInfo stat = new StatusInfo();

		try {

			stat = reviewDao.retriveAllPolarityForPaginationConfig(paginationConfigVO);

			if (stat.isStatus()) {

				List<PolarityModel> polarityModelList = (List<PolarityModel>) stat.getModel();

				if (polarityModelList != null && !polarityModelList.isEmpty()) {

					for (PolarityModel polarityModel : polarityModelList) {

						String movieName = reviewDao.retriveMovieNameForId(polarityModel.getMovieId());

						String movieTypeName = reviewDao.retriveMovieTypeNameForId(polarityModel.getMovieType());

						polarityModel.setMovieName(movieName);
						polarityModel.setMovieTypeName(movieTypeName);
					}

				}

				stat.setModel(polarityModelList);

			}

		} catch (Exception e) {
			System.out.println("Exception e" + e.getMessage());
			stat.setStatus(false);
			stat.setExceptionMsg(e.getMessage());

			return stat;

		}

		stat.setStatus(true);
		return stat;
	}

	@Override
	public StatusInfo retriveTotalPolarityForPagination(PaginationConfigVO paginationConfigVO) {
		StatusInfo stat = new StatusInfo();

		try {

			stat = reviewDao.retriveTotalPolarityForPaginationConfig(paginationConfigVO);

			if (stat.isStatus()) {

				List<PolarityModel> polarityModelList = (List<PolarityModel>) stat.getModel();

				if (polarityModelList != null && !polarityModelList.isEmpty()) {

					for (PolarityModel polarityModel : polarityModelList) {

						String movieName = reviewDao.retriveMovieNameForId(polarityModel.getMovieId());

						String movieTypeName = reviewDao.retriveMovieTypeNameForId(polarityModel.getMovieType());

						polarityModel.setMovieName(movieName);
						polarityModel.setMovieTypeName(movieTypeName);
					}

				}

				stat.setModel(polarityModelList);

			}

		} catch (Exception e) {
			System.out.println("Exception e" + e.getMessage());
			stat.setStatus(false);
			stat.setExceptionMsg(e.getMessage());

			return stat;

		}

		stat.setStatus(true);
		return stat;
	}

	@Override
	public List<FeatureTypeInfo> retriveFeaturesForSpecficType(int type) {
		List<FeatureTypeInfo> featureTypeInfoList = null;
		try {
			featureTypeInfoList = reviewDao.retriveFeaturesForSpecficType(type);
			if (null == featureTypeInfoList) {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			return null;
		}
		return featureTypeInfoList;
	}

	@Override
	public List<PolarityModel> viewTotalPolarityByMovieypeAndFeatureType(String movieType, String featureType) {
		List<PolarityModel> polarityList = null;
		try {
			polarityList = reviewDao.viewTotalPolarityByMovieypeAndFeatureType(movieType, featureType);
			if (null == polarityList) {
				return null;
			}

			if (polarityList != null && !polarityList.isEmpty()) {

				for (PolarityModel polarityModel : polarityList) {

					String movieName = reviewDao.retriveMovieNameForId(polarityModel.getMovieId());

					String movieTypeName = reviewDao.retriveMovieTypeNameForId(polarityModel.getMovieType());

					polarityModel.setMovieName(movieName);
					polarityModel.setMovieTypeName(movieTypeName);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			return null;
		}
		return polarityList;
	}

	@Override
	public MovieRankInfo rankBasedOnCorrelation(RankMoviesInput rankMoviesInput) {
		MovieRankInfo productRankInfo = null;
		try {

			StatusInfo statusInfo = reviewDao.deleteFromBestFV(rankMoviesInput.getType());
			if (!statusInfo.isStatus()) {
				return null;
			}

			List<String> featureTypeList = new ArrayList<String>();

			if (rankMoviesInput.isActor()) {

				featureTypeList.add(ReviewConstantsIF.FeatureTypes.ACTOR_FEATURETYPE);

			}

			if (rankMoviesInput.isActress()) {

				featureTypeList.add(ReviewConstantsIF.FeatureTypes.ACTRESS_FEATURETYPE);

			}

			if (rankMoviesInput.isDialogues()) {

				featureTypeList.add(ReviewConstantsIF.FeatureTypes.DIALOGUES_FEATURETYPE);

			}

			if (rankMoviesInput.isDirection()) {

				featureTypeList.add(ReviewConstantsIF.FeatureTypes.DIRECTION_FEATURETYPE);

			}

			if (rankMoviesInput.isProduction()) {

				featureTypeList.add(ReviewConstantsIF.FeatureTypes.PRODUCTION_FEATURETYPE);

			}

			if (rankMoviesInput.isSongs()) {

				featureTypeList.add(ReviewConstantsIF.FeatureTypes.SONGS_FEATURETYPE);

			}

			boolean flag = (featureTypeList != null && featureTypeList.size() >= 1) ? true : false;

			if (!flag) {

				productRankInfo = obtainMovieRankInfoWithoutFeature(rankMoviesInput.getType());

			} else {

				String[] featureTypesStr = new String[featureTypeList.size()];
				featureTypesStr = featureTypeList.toArray(featureTypesStr);

				productRankInfo = obtainMovieRankInfo(rankMoviesInput.getType(), featureTypesStr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception" + e.getMessage());
			return null;

		}

		return productRankInfo;
	}

	private MovieRankInfo obtainMovieRankInfoWithoutFeature(int type) {
		
		MovieRankInfo movieRankInfo = new MovieRankInfo();

		StatusInfo statusInfo = reviewDao.deletePOLARITYORDER();

		if (!statusInfo.isStatus()) {

			movieRankInfo.setErrMsg("COULD NOT RANK DUE TO SOME ISSUES");
			return movieRankInfo;
		}

	
		List<Integer> movieIds = reviewDao.retriveDistinctMovieIdsFromNoFeatureTotalPolarity(type);

		for (Integer movieIdTemp : movieIds) {

			int positive = reviewDao.retriveTotalPositiveRatingForMovieAndMovieTypeWithoutFeature(movieIdTemp, type);

		
			int negative = reviewDao.retriveTotalNegativeRatingForMovieAndMovieTypeWithoutFeature(movieIdTemp, type);

		
			int neutral = reviewDao.retriveTotalNeutralRatingForMovieAndMovieTypeWithoutFeature(movieIdTemp, type);

		
			populateObjectAndStore(positive, negative, neutral, movieIdTemp);

		}
		List<PolarityOrderInfo> polarityOrderInfos = reviewDao.retriveAllPolarityOrderInfoRankBy();

		List<PolarityOrderInfo> polarityOrderInfosListDisplay = new LinkedList<PolarityOrderInfo>();

		if (polarityOrderInfos != null && !polarityOrderInfos.isEmpty()) {

			for (PolarityOrderInfo polarityOrderInfo : polarityOrderInfos) {

				PolarityOrderInfo polarityOrderInfoNew = populatePolarityOrderInfo(polarityOrderInfo);

				polarityOrderInfosListDisplay.add(polarityOrderInfoNew);

			}

		}

		movieRankInfo.setBasedOnFeature(true);

		movieRankInfo.setPolarityOrderInfos(polarityOrderInfosListDisplay);
		return movieRankInfo;

	}

	private MovieRankInfo obtainMovieRankInfo(int type, String[] obtainFeatures) {

		MovieRankInfo movieRankInfo = new MovieRankInfo();

		try {

			if (obtainFeatures != null && obtainFeatures.length >= 1) {

				StatusInfo statusInfo = reviewDao.deletePOLARITYORDER();

				if (!statusInfo.isStatus()) {

					movieRankInfo.setErrMsg("COULD NOT RANK DUE TO SOME ISSUES");
					return movieRankInfo;
				}

			

				List<Integer> movieIds = reviewDao.retriveDistinctMovieIdsFromReviews(type);

				for (Integer movieIdTemp : movieIds) {
					
					int totalPositive = 0;
					int totalNegative = 0;
					int totalNeutral = 0;

					for (String featureType : obtainFeatures) {

						int positive = reviewDao.retriveTotalPositiveRatingForMovieAndFeatureType(movieIdTemp, type,
								featureType);

						totalPositive = totalPositive + positive;

						int negative = reviewDao.retriveTotalNegativeRatingForMovieAndFeatureType(movieIdTemp, type,
								featureType);

						totalNegative = totalNegative + negative;

						int neutral = reviewDao.retriveTotalNeutralRatingForMovieAndFeatureType(movieIdTemp, type,
								featureType);

						totalNeutral = totalNeutral + neutral;

					}

					// Now insert into Database

					populateObjectAndStore(totalPositive, totalNegative, totalNeutral, movieIdTemp);

				}

				List<PolarityOrderInfo> polarityOrderInfos = reviewDao.retriveAllPolarityOrderInfoRankBy();

				List<PolarityOrderInfo> polarityOrderInfosListDisplay = new LinkedList<PolarityOrderInfo>();

				if (polarityOrderInfos != null && !polarityOrderInfos.isEmpty()) {

					for (PolarityOrderInfo polarityOrderInfo : polarityOrderInfos) {

						PolarityOrderInfo polarityOrderInfoNew = populatePolarityOrderInfo(polarityOrderInfo);

						polarityOrderInfosListDisplay.add(polarityOrderInfoNew);

					}

				}

				movieRankInfo.setBasedOnFeature(true);

				movieRankInfo.setPolarityOrderInfos(polarityOrderInfosListDisplay);

			}
		} catch (Exception e) {

			System.out.println("Exception " + e.getMessage());
			e.printStackTrace();

		}

		return movieRankInfo;
	}

	private void populateObjectAndStore(int totalPositive, int totalNegative, int totalNeutral, Integer movieId) {
		PolarityOrderInfo polarityOrderInfo = new PolarityOrderInfo();

		polarityOrderInfo.setMovieId(movieId);
		polarityOrderInfo.setTotalNegative(totalNegative);
		polarityOrderInfo.setTotalPositive(totalPositive);
		polarityOrderInfo.setTotalNeutral(totalNeutral);

		StatusInfo statusInfo2 = reviewDao.insertPolarityOrderInfo(polarityOrderInfo);
	}

	private PolarityOrderInfo populatePolarityOrderInfo(PolarityOrderInfo polarityOrderInfo) {
		PolarityOrderInfo polarityOrderInfoNew = new PolarityOrderInfo();

		polarityOrderInfoNew.setMovieId(polarityOrderInfo.getMovieId());
		polarityOrderInfoNew.setTotalFeature(polarityOrderInfo.getTotalFeature());
		polarityOrderInfoNew.setTotalNegative(polarityOrderInfo.getTotalNegative());
		polarityOrderInfoNew.setTotalNeutral(polarityOrderInfo.getTotalNeutral());
		polarityOrderInfoNew.setTotalPositive(polarityOrderInfo.getTotalPositive());
		polarityOrderInfoNew.setType(polarityOrderInfo.getType());

		// Obtain Movie Name

		String movieName = reviewDao.retriveMovieNameForId(polarityOrderInfo.getMovieId());

		polarityOrderInfoNew.setMovieName(movieName);

		return polarityOrderInfoNew;
	}

	@Override
	public StatusInfo saveMovie(MovieNameModel movieNameModel) {

		StatusInfo statusInfo = new StatusInfo();
		try {

			List<String> movienames = reviewDao.retriveMovieNames();

			if (null == movienames) {

				statusInfo = reviewDao.insertMovieName(movieNameModel.getMoviename());

				if (!statusInfo.isStatus()) {
					statusInfo.setStatus(false);
					statusInfo.setErrMsg(ReviewConstantsIF.Message.SAVE_MOVIE_FAILED);
					return statusInfo;
				}

				statusInfo.setStatus(true);
				return statusInfo;
			}

			//

			if (movienames.contains(movieNameModel.getMoviename())) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg(ReviewConstantsIF.Message.MOVIE_NAME_EXISTS);
				return statusInfo;

			}

			statusInfo = reviewDao.insertMovieName(movieNameModel.getMoviename());

			if (!statusInfo.isStatus()) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg(ReviewConstantsIF.Message.SAVE_MOVIE_FAILED);
				return statusInfo;
			}

			statusInfo.setStatus(true);

		} catch (Exception e) {

			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			return statusInfo;
		}

		return statusInfo;

	}

	@Override
	public StatusInfo retriveAllMoviesForPagination(PaginationConfigVO paginationConfigVO) {
		StatusInfo stat = new StatusInfo();

		try {

			stat = reviewDao.retriveAllMoviesForPagination(paginationConfigVO);

		} catch (Exception e) {
			System.out.println("Exception e" + e.getMessage());
			stat.setStatus(false);
			stat.setExceptionMsg(e.getMessage());

			return stat;

		}

		stat.setStatus(true);
		return stat;
	}

	@Override
	public StatusInfo retriveAllPolarityWithoutFeatureForPagination(PaginationConfigVO paginationConfigVO) {
		StatusInfo stat = new StatusInfo();

		try {

			stat = reviewDao.retriveAllPolarityWithoutFeatureForPaginationConfig(paginationConfigVO);

			if (stat.isStatus()) {

				List<PolarityModel> polarityModelList = (List<PolarityModel>) stat.getModel();

				if (polarityModelList != null && !polarityModelList.isEmpty()) {

					for (PolarityModel polarityModel : polarityModelList) {

						String movieName = reviewDao.retriveMovieNameForId(polarityModel.getMovieId());

						String movieTypeName = reviewDao.retriveMovieTypeNameForId(polarityModel.getMovieType());

						polarityModel.setMovieName(movieName);
						polarityModel.setMovieTypeName(movieTypeName);
					}

				}

				stat.setModel(polarityModelList);

			}

		} catch (Exception e) {
			System.out.println("Exception e" + e.getMessage());
			stat.setStatus(false);
			stat.setExceptionMsg(e.getMessage());

			return stat;

		}

		stat.setStatus(true);
		return stat;
	}

	@Override
	public StatusInfo retriveAllTotalPolarityWithoutFeatureForPagination(PaginationConfigVO paginationConfigVO) {
		StatusInfo stat = new StatusInfo();

		try {

			stat = reviewDao.retriveAllTotalPolarityWithoutFeatureForPaginationConfig(paginationConfigVO);

			if (stat.isStatus()) {

				List<PolarityModel> polarityModelList = (List<PolarityModel>) stat.getModel();

				if (polarityModelList != null && !polarityModelList.isEmpty()) {

					for (PolarityModel polarityModel : polarityModelList) {

						String movieName = reviewDao.retriveMovieNameForId(polarityModel.getMovieId());

						String movieTypeName = reviewDao.retriveMovieTypeNameForId(polarityModel.getMovieType());

						polarityModel.setMovieName(movieName);
						polarityModel.setMovieTypeName(movieTypeName);
					}

				}

				stat.setModel(polarityModelList);

			}

		} catch (Exception e) {
			System.out.println("Exception e" + e.getMessage());
			stat.setStatus(false);
			stat.setExceptionMsg(e.getMessage());

			return stat;

		}

		stat.setStatus(true);
		return stat;
	}

}
