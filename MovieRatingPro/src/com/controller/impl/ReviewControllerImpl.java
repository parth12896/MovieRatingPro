package com.controller.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.constants.ReviewConstantsIF;
import com.controller.inter.ReviewControllerIF;
import com.delegate.inter.ReviewDelegateIF;
import com.model.AJAXResponse;
import com.model.AJAXResponseLogin;
import com.model.FeatureTypeInfo;
import com.model.KeywordInfo;
import com.model.LoginModel;
import com.model.Message;
import com.model.MovieInfo;
import com.model.MovieNameModel;
import com.model.MovieRankInfo;
import com.model.MovieTypeInfo;
import com.model.PaginationConfigVO;
import com.model.PolarityModel;
import com.model.ProductRankInfo;
import com.model.RankMoviesInput;
import com.model.ReviewModel;
import com.model.StatusInfo;

@Controller
public class ReviewControllerImpl implements ReviewControllerIF {

	@Autowired
	private RestTemplate restTemplate;

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Autowired
	private ReviewDelegateIF reviewDelegate;

	public void setReviewDelegate(ReviewDelegateIF reviewDelegate) {
		this.reviewDelegate = reviewDelegate;
	}

	public ReviewDelegateIF getReviewDelegate() {
		return reviewDelegate;
	}

	@Override
	@RequestMapping(value = "/storereviewForUrl.do", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse obtainRealTimeReviewsAndStore(@RequestBody ReviewModel reviewModel) {
		AJAXResponse ajaxRes = null;
		try {

			if (null == reviewModel.getWebUrl() || reviewModel.getWebUrl().isEmpty()) {

				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message msg = new Message();
				msg.setMsg(ReviewConstantsIF.Message.WEBURL_CANNOT_BE_EMPTY);
				ebErrors.add(msg);
				ajaxRes.setEbErrors(ebErrors);
				return ajaxRes;
			}

			if (null == reviewModel.getXpath() || reviewModel.getXpath().isEmpty()) {

				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message msg = new Message();
				msg.setMsg(ReviewConstantsIF.Message.XPATH_CANNOT_BE_EMPTY);
				ebErrors.add(msg);
				ajaxRes.setEbErrors(ebErrors);
				return ajaxRes;

			}

			ajaxRes = new AJAXResponse();
			StatusInfo statusInfo = new StatusInfo();
			statusInfo = reviewDelegate.performAnalysisAndStoreReviews(reviewModel);
			if (statusInfo.isStatus()) {
				ajaxRes.setStatus(true);
				ajaxRes.setMessage(ReviewConstantsIF.Message.REVIEW_STORED_SUCESSFULLY);
				return ajaxRes;
			} else {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message msg = new Message();
				msg.setMsg(statusInfo.getErrMsg());
				ebErrors.add(msg);
				ajaxRes.setEbErrors(ebErrors);
				return ajaxRes;
			}

		} catch (Exception e) {
			ajaxRes = new AJAXResponse();
			List<Message> ebErrors = new ArrayList<Message>();
			ajaxRes.setStatus(false);
			Message msg = new Message();
			msg.setMsg(ReviewConstantsIF.Message.MESSAGE_INTERNAL);
			ebErrors.add(msg);
			ajaxRes.setEbErrors(ebErrors);
			return ajaxRes;
		}
	}

	@Override
	@RequestMapping(value = "/retriveAllReviews.do", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse obtainAllReviews(@ModelAttribute PaginationConfigVO paginationConfigVO) {
		AJAXResponse ajaxRes = null;

		try {
			ajaxRes = new AJAXResponse();

			StatusInfo statusInfo = reviewDelegate.retriveReviews(paginationConfigVO);

			if (statusInfo.isStatus()) {
				ajaxRes.setModel(statusInfo.getModel());
				ajaxRes.setStatus(true);
				ajaxRes.setMessage(ReviewConstantsIF.Message.REVIEW_RETRIVE_SUCESSFUL);
				ajaxRes.setTotal(statusInfo.getTotal());
				return ajaxRes;
			} else {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message msg = new Message();
				msg.setMsg(ReviewConstantsIF.Message.NO_REVIEWS_FOUND);
				ebErrors.add(msg);
				ajaxRes.setEbErrors(ebErrors);
				return ajaxRes;
			}

		} catch (Exception e) {
			ajaxRes = new AJAXResponse();
			List<Message> ebErrors = new ArrayList<Message>();
			ajaxRes.setStatus(false);
			Message msg = new Message();
			msg.setMsg(ReviewConstantsIF.Message.MESSAGE_INTERNAL);
			ebErrors.add(msg);
			ajaxRes.setEbErrors(ebErrors);
			return ajaxRes;
		}
	}

	@Override
	@RequestMapping(value = "/retriveAllMoviesForMovieType.do", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody List<MovieInfo> retriveMovieInfoForSpecficType(@RequestParam String type) {

		List<MovieInfo> productInfoList = null;
		try {

			productInfoList = reviewDelegate.retriveSpecficMoviesInfo(Integer.parseInt(type));

		} catch (Exception e) {
			System.out.println("EXCEPTION IS" + e.getMessage());
		}

		return productInfoList;

	}

	@Override
	@RequestMapping(value = "/retriveAllFeaturesForMovieType.do", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody List<FeatureTypeInfo> retriveFeaturesForSpecficType(@RequestParam String type) {

		List<FeatureTypeInfo> featureTypeInfoList = null;
		try {

			featureTypeInfoList = reviewDelegate.retriveFeaturesForSpecficType(Integer.parseInt(type));

		} catch (Exception e) {
			System.out.println("EXCEPTION IS" + e.getMessage());
		}

		return featureTypeInfoList;

	}

	@Override
	@RequestMapping(value = "/retriveAllMovies.do", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody List<MovieInfo> retriveAllMovies() {

		List<MovieInfo> productInfoList = null;
		try {

			productInfoList = reviewDelegate.retriveSpecficMoviesInfo(Integer.parseInt("1"));

		} catch (Exception e) {
			System.out.println("EXCEPTION IS" + e.getMessage());
		}

		return productInfoList;

	}

	@Override
	@RequestMapping(value = "/retriveAllMovieTypes.do", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody List<MovieTypeInfo> retriveAllMovieTypes() {
		List<MovieTypeInfo> productTypeInfoList = null;
		try {

			productTypeInfoList = reviewDelegate.retriveAllMovieTypes();

		} catch (Exception e) {
			System.out.println("EXCEPTION IS" + e.getMessage());
		}

		return productTypeInfoList;
	}

	@RequestMapping(value = "/login.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView loginPage(Model model, @ModelAttribute LoginModel loginModel, HttpServletRequest request) {

		String loginPageName = "login";
		String customerPageName = "customerhome";
		String adminPageName = "admin";

		AJAXResponse ajaxResponse1 = new AJAXResponse();

		ajaxResponse1.setStatus(true);

		AJAXResponseLogin ajaxResponse = new AJAXResponseLogin();
		try {

			loginModel.setAppName(ReviewConstantsIF.Keys.APP_NAME);
			HttpEntity<LoginModel> request1 = new HttpEntity<>(loginModel);
			ResponseEntity<AJAXResponseLogin> response = restTemplate.exchange(
					ReviewConstantsIF.LOGIN_VALIDATE_ENDPOINT, HttpMethod.POST, request1, AJAXResponseLogin.class);

			ajaxResponse = response.getBody();

			if (ajaxResponse.isStatus()) {

				HttpSession session = request.getSession(true);
				session.setAttribute(ReviewConstantsIF.Keys.LOGINID_SESSION, loginModel.getUserName());
				session.setAttribute(ReviewConstantsIF.Keys.LOGINTYPE_SESSION, ajaxResponse.getModel().getLoginType());

				int loginType = ajaxResponse.getModel().getLoginType();

				System.out.println("Login Type = " + loginType);

				if (loginType == ReviewConstantsIF.Keys.CUSTOMER_TYPE) {
					model.addAttribute(ReviewConstantsIF.Keys.OBJ, ajaxResponse1);
					return new ModelAndView(customerPageName, ReviewConstantsIF.Keys.OBJ, ajaxResponse1);
				}
				if (loginType == ReviewConstantsIF.Keys.ADMIN_TYPE) {
					model.addAttribute(ReviewConstantsIF.Keys.OBJ, ajaxResponse1);
					return new ModelAndView(adminPageName, ReviewConstantsIF.Keys.OBJ, ajaxResponse1);
				}
			}

		} catch (Exception e) {

			model.addAttribute(ReviewConstantsIF.Keys.OBJ, ajaxResponse);
			return new ModelAndView(loginPageName, ReviewConstantsIF.Keys.OBJ, ajaxResponse);

		}
		model.addAttribute(ReviewConstantsIF.Keys.OBJ, ajaxResponse);
		return new ModelAndView(loginPageName, ReviewConstantsIF.Keys.OBJ, ajaxResponse);

	}

	@RequestMapping("/logout.do")
	public String logout(Model model, HttpServletRequest request) {
		AJAXResponse ajaxResponse = new AJAXResponse();

		try {

			ajaxResponse.setStatus(true);

			HttpSession session = request.getSession(false);
			session.invalidate();
			ajaxResponse.setStatus(true);
			model.addAttribute(ReviewConstantsIF.Keys.OBJ, new AJAXResponse());
			return "welcome";
		} catch (Exception e) {
			ajaxResponse.setStatus(true);
			model.addAttribute(ReviewConstantsIF.Keys.OBJ, new AJAXResponse());
			return "welcome";
		}

	}

	@Override
	@RequestMapping(value = "/viewPolarity.do", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse viewPolarity(HttpServletRequest request,
			@ModelAttribute PaginationConfigVO paginationConfigVO) {
		AJAXResponse ajaxRes = null;
		try {
			ajaxRes = new AJAXResponse();

			StatusInfo statusInfo = reviewDelegate.retriveAllPolarityForPagination(paginationConfigVO);

			if (statusInfo.isStatus()) {
				ajaxRes.setModel(statusInfo.getModel());
				ajaxRes.setStatus(true);
				ajaxRes.setMessage(ReviewConstantsIF.Message.REVIEW_POLARITY_SUCESSFUL);
				ajaxRes.setTotal(statusInfo.getTotal());
				return ajaxRes;
			} else {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message msg = new Message();
				msg.setMsg(ReviewConstantsIF.Message.NO_POLARITY_FOUND);
				ebErrors.add(msg);
				ajaxRes.setEbErrors(ebErrors);
				return ajaxRes;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxRes = new AJAXResponse();
			ajaxRes.setStatus(true);
			Message msg = new Message();
			msg.setMsg(ReviewConstantsIF.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxRes.setEbErrors(ebErrors);
			return ajaxRes;
		}
	}

	@Override
	@RequestMapping(value = "/viewTotalPolarity.do", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse viewTotalPolarity(HttpServletRequest request,
			@ModelAttribute PaginationConfigVO paginationConfigVO) {
		AJAXResponse ajaxRes = null;
		try {
			ajaxRes = new AJAXResponse();

			StatusInfo statusInfo = reviewDelegate.retriveTotalPolarityForPagination(paginationConfigVO);

			if (statusInfo.isStatus()) {
				ajaxRes.setModel(statusInfo.getModel());
				ajaxRes.setStatus(true);
				ajaxRes.setMessage(ReviewConstantsIF.Message.REVIEW_TOTALPOLARITY_SUCESSFUL);
				ajaxRes.setTotal(statusInfo.getTotal());
				return ajaxRes;
			} else {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message msg = new Message();
				msg.setMsg(ReviewConstantsIF.Message.NO_TOTALPOLARITY_FOUND);
				ebErrors.add(msg);
				ajaxRes.setEbErrors(ebErrors);
				return ajaxRes;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxRes = new AJAXResponse();
			ajaxRes.setStatus(true);
			Message msg = new Message();
			msg.setMsg(ReviewConstantsIF.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxRes.setEbErrors(ebErrors);
			return ajaxRes;
		}
	}

	@Override
	@RequestMapping(value = "/viewTotalPolarityForMovieTypeAndFeatureType.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody AJAXResponse viewTotalPolarityByFeatureTypeAndMovieType(HttpServletRequest request,
			@RequestParam String movieType, @RequestParam String featureType) {
		AJAXResponse ajaxResponse = null;
		try {
			ajaxResponse = new AJAXResponse();

			List<PolarityModel> polarityList = reviewDelegate.viewTotalPolarityByMovieypeAndFeatureType(movieType,
					featureType);
			if (null == polarityList) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(ReviewConstantsIF.Message.EMPTY_POLARITY);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return ajaxResponse;
			}
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(polarityList);
			ajaxResponse.setMessage(ReviewConstantsIF.Message.RETRIVE_POLAIRITY_SUCESS);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setMsg(ReviewConstantsIF.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return ajaxResponse;
		}
	}

	@Override
	@RequestMapping(value = "/rankCorrelation.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView generalCorrelation(HttpServletRequest request,
			@ModelAttribute RankMoviesInput rankMoviesInput) {
		AJAXResponse ajaxRes = null;
		try {

			MovieRankInfo moviesRankInfoList = reviewDelegate.rankBasedOnCorrelation(rankMoviesInput);

			if (null == moviesRankInfoList) {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg.setMsg(ReviewConstantsIF.Message.COULD_NOT_RANK);
				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(ReviewConstantsIF.Views.VIEW_ERROR_PAGE, ReviewConstantsIF.Keys.OBJ, ajaxRes);
			} else {

				ajaxRes = new AJAXResponse();
				ajaxRes.setStatus(true);
				ajaxRes.setModel(moviesRankInfoList);
				return new ModelAndView(ReviewConstantsIF.Views.RANKFEATURE_OUTPUT, ReviewConstantsIF.Keys.OBJ,
						ajaxRes);

			}

		} catch (Exception e) {
			ajaxRes = new AJAXResponse();
			List<Message> ebErrors = new ArrayList<Message>();
			ajaxRes.setStatus(false);
			Message webSiteUrlMsg = new Message();
			webSiteUrlMsg.setMsg(ReviewConstantsIF.Message.INTERNAL_ERROR);
			ebErrors.add(webSiteUrlMsg);
			ajaxRes.setEbErrors(ebErrors);
			return new ModelAndView(ReviewConstantsIF.Views.VIEW_ERROR_PAGE, ReviewConstantsIF.Keys.OBJ, ajaxRes);
		}
	}

	@Override
	@RequestMapping(value = "/saveMovie.do", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse saveMovie(HttpServletRequest request,
			@RequestBody MovieNameModel movieNameModel) {
		AJAXResponse ajaxRes = null;
		try {
			ajaxRes = new AJAXResponse();

			if (null == movieNameModel) {

				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message msg = new Message();
				msg.setMsg(ReviewConstantsIF.Message.MOVIE_NAME_CAANOT_BE_EMPTY);
				ebErrors.add(msg);
				ajaxRes.setEbErrors(ebErrors);
				return ajaxRes;
			}

			if (null == movieNameModel.getMoviename()) {

				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message msg = new Message();
				msg.setMsg(ReviewConstantsIF.Message.MOVIE_NAME_CAANOT_BE_EMPTY);
				ebErrors.add(msg);
				ajaxRes.setEbErrors(ebErrors);
				return ajaxRes;

			}

			if (movieNameModel.getMoviename() != null && movieNameModel.getMoviename().isEmpty()) {

				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message msg = new Message();
				msg.setMsg(ReviewConstantsIF.Message.MOVIE_NAME_CAANOT_BE_EMPTY);
				ebErrors.add(msg);
				ajaxRes.setEbErrors(ebErrors);
				return ajaxRes;

			}

			StatusInfo statusInfo = reviewDelegate.saveMovie(movieNameModel);

			if (statusInfo.isStatus()) {
				ajaxRes.setModel(statusInfo.getModel());
				ajaxRes.setStatus(true);
				ajaxRes.setMessage(ReviewConstantsIF.Message.SAVE_MOVIE_SUCESS);
				ajaxRes.setTotal(statusInfo.getTotal());
				return ajaxRes;
			} else {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message msg = new Message();
				msg.setMsg(statusInfo.getErrMsg());
				ebErrors.add(msg);
				ajaxRes.setEbErrors(ebErrors);
				return ajaxRes;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxRes = new AJAXResponse();
			ajaxRes.setStatus(true);
			Message msg = new Message();
			msg.setMsg(ReviewConstantsIF.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxRes.setEbErrors(ebErrors);
			return ajaxRes;
		}
	}

	@Override
	@RequestMapping(value = "/viewMovies.do", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse viewMovies(HttpServletRequest request,
			@ModelAttribute PaginationConfigVO paginationConfigVO) {
		AJAXResponse ajaxRes = null;
		try {
			ajaxRes = new AJAXResponse();

			StatusInfo statusInfo = reviewDelegate.retriveAllMoviesForPagination(paginationConfigVO);

			if (statusInfo.isStatus()) {
				ajaxRes.setModel(statusInfo.getModel());
				ajaxRes.setStatus(true);
				ajaxRes.setMessage(ReviewConstantsIF.Message.RET_MOVIES_SUCESSFUL);
				ajaxRes.setTotal(statusInfo.getTotal());
				return ajaxRes;
			} else {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message msg = new Message();
				msg.setMsg(statusInfo.getErrMsg());
				ebErrors.add(msg);
				ajaxRes.setEbErrors(ebErrors);
				return ajaxRes;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxRes = new AJAXResponse();
			ajaxRes.setStatus(true);
			Message msg = new Message();
			msg.setMsg(ReviewConstantsIF.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxRes.setEbErrors(ebErrors);
			return ajaxRes;
		}
	}

	@Override
	@RequestMapping(value = "/viewPolarityWithoutFeature.do", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse viewPolarityWithoutFeature(HttpServletRequest request,
			@ModelAttribute PaginationConfigVO paginationConfigVO) {
		AJAXResponse ajaxRes = null;
		try {
			ajaxRes = new AJAXResponse();

			StatusInfo statusInfo = reviewDelegate.retriveAllPolarityWithoutFeatureForPagination(paginationConfigVO);

			if (statusInfo.isStatus()) {
				ajaxRes.setModel(statusInfo.getModel());
				ajaxRes.setStatus(true);
				ajaxRes.setMessage(ReviewConstantsIF.Message.REVIEW_POLARITY_SUCESSFUL);
				ajaxRes.setTotal(statusInfo.getTotal());
				return ajaxRes;
			} else {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message msg = new Message();
				msg.setMsg(ReviewConstantsIF.Message.NO_POLARITY_FOUND);
				ebErrors.add(msg);
				ajaxRes.setEbErrors(ebErrors);
				return ajaxRes;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxRes = new AJAXResponse();
			ajaxRes.setStatus(true);
			Message msg = new Message();
			msg.setMsg(ReviewConstantsIF.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxRes.setEbErrors(ebErrors);
			return ajaxRes;
		}
	}

	@Override
	@RequestMapping(value = "/viewTotalPolarityWithoutFeature.do", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse viewTotalPolarityWithoutFeature(HttpServletRequest request,
			@ModelAttribute PaginationConfigVO paginationConfigVO) {
		AJAXResponse ajaxRes = null;
		try {
			ajaxRes = new AJAXResponse();

			StatusInfo statusInfo = reviewDelegate
					.retriveAllTotalPolarityWithoutFeatureForPagination(paginationConfigVO);

			if (statusInfo.isStatus()) {
				ajaxRes.setModel(statusInfo.getModel());
				ajaxRes.setStatus(true);
				ajaxRes.setMessage(ReviewConstantsIF.Message.REVIEW_POLARITY_SUCESSFUL);
				ajaxRes.setTotal(statusInfo.getTotal());
				return ajaxRes;
			} else {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message msg = new Message();
				msg.setMsg(ReviewConstantsIF.Message.NO_POLARITY_FOUND);
				ebErrors.add(msg);
				ajaxRes.setEbErrors(ebErrors);
				return ajaxRes;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxRes = new AJAXResponse();
			ajaxRes.setStatus(true);
			Message msg = new Message();
			msg.setMsg(ReviewConstantsIF.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxRes.setEbErrors(ebErrors);
			return ajaxRes;
		}
	}

}
