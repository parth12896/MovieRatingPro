package com.constants;


public interface ReviewConstantsIF {
	
	String LOGIN_VALIDATE_ENDPOINT = EndPoints.BASE_END_POINT + "/login";

	interface SQLS {
		public static final String INSERT_REVIEW_SQL = "INSERT_REVIEW_SQL";
		public static final String RETRIVE_REVIEWS_SQL = "RETRIVE_REVIEWS_SQL";
		public static final String RETRIVE_MOVIEINFO_SQL = "RETRIVE_MOVIEINFO_SQL";
		public static final String RETRIVE_MOVIEINFO_FOR_MOVIETYPE_SQL = "RETRIVE_MOVIEINFO_FOR_MOVIETYPE_SQL";
		public static final String RETRIVE_MOVIETYPEINFO_SQL = "RETRIVE_MOVIETYPEINFO_SQL";
		public static final String RETRIVE_MOVIETYPEINFO_FOR_MOVIETYPEID_SQL = "RETRIVE_MOVIETYPEINFO_FOR_MOVIETYPEID_SQL";
		public static final String RETRIVE_MOVIENAME_FOR_MOVIEID = "RETRIVE_MOVIENAME_FOR_MOVIEID";
		public static final String RETRIVE_POSITIVE_KEYWORDS_SQL = "RETRIVE_POSITIVE_KEYWORDS_SQL";
		public static final String RETRIVE_NEGATIVE_KEYWORDS_SQL = "RETRIVE_NEGATIVE_KEYWORDS_SQL";
		public static final String INSERT_NEGATIVEKEYWORDS_SQL = "INSERT_NEGATIVEKEYWORDS_SQL";
		public static final String INSERT_POSTIVEKEYWORDS_SQL = "INSERT_POSTIVEKEYWORDS_SQL";
		public static final String RETRIVE_NEGATIVEKEYWORDS_SQL = "RETRIVE_NEGATIVEKEYWORDS_SQL";
		public static final String RETRIVE_POSITIVEKEYWORDS_SQL = "RETRIVE_POSITIVEKEYWORDS_SQL";
		public static final String DELETE_SENTIMENTANALYZER_SQL = "DELETE_SENTIMENTANALYZER_SQL";
		public static final String DELETE_SENTIMENTTOTAL_SQL = "DELETE_SENTIMENTTOTAL_SQL";
		public static final String RETRIVE_ALLREVIEWS_SQL = "RETRIVE_ALLREVIEWS_SQL";
		public static final String INSERT_POLARITY_SQL = "INSERT_POLARITY_SQL";
		public static final String RETRIVE_UNIQUE_MOVIEIDS_SQL = "RETRIVE_UNIQUE_MOVIEIDS_SQL";
		public static final String RETRIVE_TOTALPOSITIVEPOLARITY_WHERE_MOVIEID_MOVIEYPE_SQL = "RETRIVE_TOTALPOSITIVEPOLARITY_WHERE_MOVIEID_MOVIEYPE_SQL";
		public static final String RETRIVE_TOTALNEGATIVEPOLARITY_WHERE_MOVIEID_MOVIEYPE_SQL = "RETRIVE_TOTALNEGATIVEPOLARITY_WHERE_MOVIEID_MOVIEYPE_SQL";
		public static final String RETRIVE_TOTALNEUTRALPOLARITY_WHERE_MOVIEID_MOVIEYPE_SQL = "RETRIVE_TOTALNEUTRALPOLARITY_WHERE_MOVIEID_MOVIEYPE_SQL";
		public static final String INSERT_TOTALPOLARITY_SQL = "INSERT_TOTALPOLARITY_SQL";
		public static final String RETRIVE_MOVIEIDS_ORDERBY_WHERE_MOVIEYPE_SQL = "RETRIVE_MOVIEIDS_ORDERBY_WHERE_MOVIEYPE_SQL";
		public static final String RETRIVE_ALLPOLARITY_WHERE_MOVIETYPE_SQL = "RETRIVE_ALLPOLARITY_WHERE_MOVIETYPE_SQL";
		public static final String RETRIVE_TOTALPOLARITY_WHERE_MOVIETYPE_SQL = "RETRIVE_TOTALPOLARITY_WHERE_MOVIETYPE_SQL";
		public static final String INSERT_STOPWORD_SQL = "INSERT_STOPWORD_SQL";
		public static final String RETRIVE_STOPWORDS_FULL_SQL = "RETRIVE_STOPWORDS_FULL_SQL";
		public static final String RETRIVE_STOPWORDS_SQL = "RETRIVE_STOPWORDS_SQL";
		public static final String INSERT_CLEANDETAILS_SQL = "INSERT_CLEANDETAILS_SQL";
		public static final String DELETE_CLEANREVIEWS_SQL = "DELETE_CLEANREVIEWS_SQL";
		public static final String DELETE_ALLCLEANREVIEWS_SQL = "DELETE_ALLCLEANREVIEWS_SQL";
		public static final String RETRIVE_ALLCLEANREVIEWS_WHERE_MOVIEYPE_SQL = "RETRIVE_ALLCLEANREVIEWS_WHERE_MOVIEYPE_SQL";
		public static final String INSERT_TOKENS_SQL = "INSERT_TOKENS_SQL";
		public static final String DELETE_ALLTOKENS_WHERE_MOVIEYPE_SQL = "DELETE_ALLTOKENS_WHERE_MOVIEYPE_SQL";
		public static final String DELETE_FREQUENCY_WHERE_MOVIETYPE_SQL = "DELETE_FREQUENCY_WHERE_MOVIETYPE_SQL";
		public static final String INSERT_FREQUENCY_WHERE_MOVIETYPE_SQL = "INSERT_FREQUENCY_WHERE_MOVIETYPE_SQL";
		public static final String RETRIVE_ALLTOKENS_WHERE_MOVIETYPE_SQL = "RETRIVE_ALLTOKENS_WHERE_MOVIETYPE_SQL";
		public static final String RETRIVE_REVIEWIDS_WHERE_MOVIETYPE_SQL = "RETRIVE_REVIEWIDS_WHERE_MOVIETYPE_SQL";
		public static final String RETRIVE_COUNT_WHERE_TOKENNAME_REVIEWID_MOVIETYPE_SQL = "RETRIVE_COUNT_WHERE_TOKENNAME_REVIEWID_MOVIETYPE_SQL";
		public static final String RETRIVE_DISTINCTTOKENS_WHERE_REVIEWID_MOVIETYPE_SQL = "RETRIVE_DISTINCTTOKENS_WHERE_REVIEWID_MOVIETYPE_SQL";
		public static final String RETRIVE_MOVIEID_WHERE_REVIEWID_MOVIETYPE_SQL = "RETRIVE_MOVIEID_WHERE_REVIEWID_MOVIETYPE_SQL";
		public static final String INSERT_FEATUREVECTOR_SQL = "INSERT_FEATUREVECTOR_SQL";
		public static final String RETRIVE_FEATUREVO_WHERE_TYPE_SQL = "RETRIVE_FEATUREVO_WHERE_TYPE_SQL";
		public static final String DELETE_FV_WHERE_MOVIETYPE_SQL = "DELETE_FV_WHERE_MOVIETYPE_SQL";
		public static final String RETRIVE_COUNT_WHERE_MOVIEID_TOKENNAME_MOVIETYPE_SQL = "RETRIVE_COUNT_WHERE_MOVIEID_TOKENNAME_MOVIETYPE_SQL";
		public static final String RETRIVE_FREQ_FROM_FREQ_WHERE_REVIEWID_MOVIEID_TOKENNAME_MOVIETYPE_SQL = "RETRIVE_FREQ_FROM_FREQ_WHERE_REVIEWID_MOVIEID_TOKENNAME_MOVIETYPE_SQL";
		public static final String RETRIVE_TOKENS_WHERE_REVIEWID_MOVIETYPE_FROM_FREQ_SQL = "RETRIVE_TOKENS_WHERE_REVIEWID_MOVIETYPE_FROM_FREQ_SQL";
		public static final String RETRIVE_UNIQUE_MOVIEIDS_FROM_FREQ_WHERE_MOVIETYPE_SQL = "RETRIVE_UNIQUE_MOVIEIDS_FROM_FREQ_WHERE_MOVIETYPE_SQL";
		public static final String RETRIVE_UNIQUE_REVIEWIDS_FROM_FREQ_WHERE_MOVIEID_MOVIETYPE_SQL = "RETRIVE_UNIQUE_REVIEWIDS_FROM_FREQ_WHERE_MOVIEID_MOVIETYPE_SQL";
		public static final String RETRIVE_ALLFREQUENCY_WHERE_MOVIETYPE_SQL = "RETRIVE_ALLFREQUENCY_WHERE_MOVIETYPE_SQL";
		public static final String DELETE_BESTFV_WHERE_MOVIETYPE_SQL = "DELETE_BESTFV_WHERE_MOVIETYPE_SQL";
		public static final String RETRIVE_DISTINCTMOVIESIDS_FROM_FV_WHERE_MOVIETYPE_SQL = "RETRIVE_DISTINCTMOVIESIDS_FROM_FV_WHERE_MOVIETYPE_SQL";
		public static final String RETRIVE_DISTINCTREVIEWSIDS_WHERE_MOVIEID_MOVIETYPE_FROM_FV_SQL = "RETRIVE_DISTINCTREVIEWSIDS_WHERE_MOVIEID_MOVIETYPE_FROM_FV_SQL";
		public static final String RETRIVE_FV_FROM_FV_WHERE_TOEKNNAME_REVIEWID_MOVIETYPE_SQL = "RETRIVE_FV_FROM_FV_WHERE_TOEKNNAME_REVIEWID_MOVIETYPE_SQL";
		public static final String RETRIVE_FREQ_FROM_FV_WHERE_TOKENNAME_REVIEWID_MOVIETYPE_SQL = "RETRIVE_FREQ_FROM_FV_WHERE_TOKENNAME_REVIEWID_MOVIETYPE_SQL";
		public static final String INSERT_BESTFV_SQL = "INSERT_BESTFV_SQL";
		public static final String RETRIVE_BESTMOVIE_WHERE_MOVIETYPE_SQL = "RETRIVE_BESTMOVIE_WHERE_MOVIETYPE_SQL";
		public static final String RETRIVE_MOVIEINFO_WHERE_MOVIETYPE_SQL = "RETRIVE_MOVIEINFO_WHERE_MOVIETYPE_SQL";
		public static final String RETRIVE_UNIQUE_MOVIEIDS_FROM_REVIEW_SQL = "RETRIVE_UNIQUE_MOVIEIDS_FROM_REVIEW_SQL";
		public static final String DELETE_FEATUREBASED_FV_WHERE_MOVIETYPE_SQL = "DELETE_FEATUREBASED_FV_WHERE_MOVIETYPE_SQL";
		public static final String RETRIVE_FEATURETYPES_SQL = "RETRIVE_FEATURETYPES_SQL";
		public static final String REMOVE_STOPWORD_SQL = "REMOVE_STOPWORD_SQL";
		public static final String RETRIVE_FREQ_WHERE_TOKENNAMELIKE_FEATURETYPE_AND_REVIEWID_AND_MOVIETYPE_SQL = "RETRIVE_FREQ_WHERE_TOKENNAMELIKE_FEATURETYPE_AND_REVIEWID_AND_MOVIETYPE_SQL";
		public static final String REMOVE_POSITIVEKEYWORD_SQL = "REMOVE_POSITIVEKEYWORD_SQL";
		public static final String REMOVE_NEGATIVEKEYWORD_SQL = "REMOVE_NEGATIVEKEYWORD_SQL";
		public static final String RETRIVE_POSITIVEKEYWORDS_ONLY_SQL = "RETRIVE_POSITIVEKEYWORDS_ONLY_SQL";
		public static final String RETRIVE_NEGATIVEKEYWORDS_ONLY_SQL = "RETRIVE_NEGATIVEKEYWORDS_ONLY_SQL";
		public static final String RETRIVE_TOTALPOSITIVEPOLARITY_WHERE_MOVIEID_MOVIEYPE_FEATURETYPE_SQL = "RETRIVE_TOTALPOSITIVEPOLARITY_WHERE_MOVIEID_MOVIEYPE_FEATURETYPE_SQL";
		public static final String RETRIVE_TOTALNEGATIVEPOLARITY_WHERE_MOVIEID_MOVIEYPE_FEATURETYPE_SQL = "RETRIVE_TOTALNEGATIVEPOLARITY_WHERE_MOVIEID_MOVIEYPE_FEATURETYPE_SQL";
		public static final String RETRIVE_TOTALNEUTRALPOLARITY_WHERE_MOVIEID_MOVIEYPE_FEATURETYPE_SQL = "RETRIVE_TOTALNEUTRALPOLARITY_WHERE_MOVIEID_MOVIEYPE_FEATURETYPE_SQL";
		public static final String RETRIVE_FREQ_FROM_FREQ_WHERE_MOVIEID_MOVIETYPE_FEATURETYPE_SQL = "RETRIVE_FREQ_FROM_FREQ_WHERE_MOVIEID_MOVIETYPE_FEATURETYPE_SQL";
		public static final String INSERT_POLARITYORDER_SQL = "INSERT_POLARITYORDER_SQL";
		public static final String RETRIVE_POLARITYORDERINFO_RANK_SQL = "RETRIVE_POLARITYORDERINFO_RANK_SQL";
		public static final String RETRIVE_TOTALPOLARITY_WHERE_FEATURETYPE_SQL = "RETRIVE_TOTALPOLARITY_WHERE_FEATURETYPE_SQL";
		public static final String DELETE_POLARITYORDER_SQL = "DELETE_POLARITYORDER_SQL";
		public static final String RETRIVEALL_MOVIE_INFO_SQL = "RETRIVEALL_MOVIE_INFO_SQL";
		public static final String RETRIVE_HASHTAGS_SQL = "RETRIVE_HASHTAGS_SQL";
		public static final String INSERT_HASHTAG_SQL = "INSERT_HASHTAG_SQL";
		public static final String RETRIVE_HASHTAGS_FULL_SQL = "RETRIVE_HASHTAGS_FULL_SQL";
		public static final String DELETE_REVIEW_SQL = "DELETE_REVIEW_SQL";
		public static final String REMOVE_ALL_HASHTAGS_SQL = "REMOVE_ALL_HASHTAGS_SQL";
		public static final String RETRIVE_UNIQUE_MOVIEIDS_FROM_SENTIMENTTOTAL_WHERE_MOVIETYPE_SQL = "RETRIVE_UNIQUE_MOVIEIDS_FROM_SENTIMENTTOTAL_WHERE_MOVIETYPE_SQL";
		public static final String RETRIVE_TOTALPOSITIVEPOLARITY_FOR_ALL_FEATURES_WHERE_MOVIEID_MOVIETYPE_SQL = "RETRIVE_TOTALPOSITIVEPOLARITY_FOR_ALL_FEATURES_WHERE_MOVIEID_MOVIETYPE_SQL";
		public static final String RETRIVE_TOTALNEGATIVEPOLARITY_FOR_ALL_FEATURES_WHERE_MOVIEID_MOVIETYPE_SQL = "RETRIVE_TOTALNEGATIVEPOLARITY_FOR_ALL_FEATURES_WHERE_MOVIEID_MOVIETYPE_SQL";
		public static final String RETRIVE_TOTALNEUTRALPOLARITY_FOR_ALL_FEATURES_WHERE_MOVIEID_MOVIETYPE_SQL = "RETRIVE_TOTALNEUTRALPOLARITY_FOR_ALL_FEATURES_WHERE_MOVIEID_MOVIETYPE_SQL";
		public static final String INSERT_MOVIESTATUS_SQL = "INSERT_MOVIESTATUS_SQL";
		public static final String DELETE_MOVIESTATUS_SQL = "DELETE_MOVIESTATUS_SQL";
		public static final String RETRIVE_MOVIESTATUS_SQL = "RETRIVE_MOVIESTATUS_SQL";
		public static final String RETRIVE_MOVIESTATUS_FOR_MOVIEID_SQL = "RETRIVE_MOVIESTATUS_FOR_MOVIEID_SQL";
		public static final String RETRIVE_REVIEWS_PAGINATED_SQL = "RETRIVE_REVIEWS_PAGINATED_SQL";
		public static final String RETRIVE_TOTAL_REVIEW_COUNT_SQL = "RETRIVE_TOTAL_REVIEW_COUNT_SQL";
		public static final String RETRIVE_MOVIETYPENAME_FOR_MOVIETYPE_SQL = "RETRIVE_MOVIETYPENAME_FOR_MOVIETYPE_SQL";
		public static final String RETRIVE_LAST_REVIEW_ID = "RETRIVE_LAST_REVIEW_ID";
		public static final String INSERT_REVIEW_CUSTOM_ID_SQL = "INSERT_REVIEW_CUSTOM_ID_SQL";
		public static final String RETRIVE_FEATURE_TYPES_FOR_MOVIE_TYPE_SQL = "RETRIVE_FEATURE_TYPES_FOR_MOVIE_TYPE_SQL";
		public static final String INSERT_REVIEW_POLARITY_NAMED_SQL = "INSERT_REVIEW_POLARITY_NAMED_SQL";
		public static final String DELETE_SENTIMENTTOTAL_WHERE_MOVIEID_MOVIETYPE_SQL = "DELETE_SENTIMENTTOTAL_WHERE_MOVIEID_MOVIETYPE_SQL";
		public static final String INSERT_MOVIE_POLARITY_NAMED_SQL = "INSERT_MOVIE_POLARITY_NAMED_SQL";
		public static final String RETRIVE_POLARITY_FOR_MOVIEID_MOVIE_TYPE_FEATURE_TYPE_SQL = "RETRIVE_POLARITY_FOR_MOVIEID_MOVIE_TYPE_FEATURE_TYPE_SQL";
		public static final String RETRIVE_POLARITY_PAGINATED_SQL = "RETRIVE_POLARITY_PAGINATED_SQL";
		public static final String RETRIVE_TOTAL_POLARITY_COUNT_SQL = "RETRIVE_TOTAL_POLARITY_COUNT_SQL";
		public static final String RETRIVE_TOTALPOLARITY_PAGINATED_SQL = "RETRIVE_TOTALPOLARITY_PAGINATED_SQL";
		public static final String RETRIVE_TOTAL_TOTALPOLARITY_COUNT_SQL = "RETRIVE_TOTAL_TOTALPOLARITY_COUNT_SQL";
		public static final String RETRIVE_FEATURETYPES_FOR_MOVIETYPE_SQL = "RETRIVE_FEATURETYPES_FOR_MOVIETYPE_SQL";
		public static final String RETRIVE_TOTALPOLARITY_WHERE_MOVIETYPE_AND_FEATURE_TYPE_SQL = "RETRIVE_TOTALPOLARITY_WHERE_MOVIETYPE_AND_FEATURE_TYPE_SQL";
		public static final String RETRIVE_MOVIENAMES_SQL = "RETRIVE_MOVIENAMES_SQL";
		public static final String INSERT_MOVIE_SQL = "INSERT_MOVIE_SQL";
		public static final String RETRIVE_MOVIES_PAGINATED_SQL = "RETRIVE_MOVIES_PAGINATED_SQL";
		public static final String RETRIVE_TOTAL_MOVIES_COUNT_SQL = "RETRIVE_TOTAL_MOVIES_COUNT_SQL";
		public static final String RETRIVE_POLARITY_FOR_MOVIEID_MOVIE_TYPE_WITHOUTFEATURE_SQL = "RETRIVE_POLARITY_FOR_MOVIEID_MOVIE_TYPE_WITHOUTFEATURE_SQL";
		public static final String DELETE_SENTIMENTTOTALWITHOUTFEATURE_WHERE_MOVIEID_MOVIETYPE_SQL = "DELETE_SENTIMENTTOTALWITHOUTFEATURE_WHERE_MOVIEID_MOVIETYPE_SQL";
		public static final String INSERT_REVIEW_POLARITY_WITHOUTFEATURE_NAMED_SQL = "INSERT_REVIEW_POLARITY_WITHOUTFEATURE_NAMED_SQL";
		public static final String INSERT_MOVIE_POLARITY_WITHOUTFEATURE_NAMED_SQL = "INSERT_MOVIE_POLARITY_WITHOUTFEATURE_NAMED_SQL";
		public static final String RETRIVE_POLARITY_WITHOUTFEATURE_PAGINATED_SQL = "RETRIVE_POLARITY_WITHOUTFEATURE_PAGINATED_SQL";
		public static final String RETRIVE_TOTAL_POLARITY_COUNT_WITHOUTFEATURE_SQL = "RETRIVE_TOTAL_POLARITY_COUNT_WITHOUTFEATURE_SQL";
		public static final String RETRIVE_ALL_TOTALPOLARITY_WITHOUTFEATURE_PAGINATED_SQL = "RETRIVE_ALL_TOTALPOLARITY_WITHOUTFEATURE_PAGINATED_SQL";
		public static final String RETRIVE_MOVIE_TOTAL_POLARITY_COUNT_WITHOUTFEATURE_SQL = "RETRIVE_MOVIE_TOTAL_POLARITY_COUNT_WITHOUTFEATURE_SQL";
		public static final String RETRIVE_UNIQUE_MOVIEIDS_FROM_TOTALPOLARITYWITHOUTFEATURE_SQL = "RETRIVE_UNIQUE_MOVIEIDS_FROM_TOTALPOLARITYWITHOUTFEATURE_SQL";
		public static final String RETRIVE_TOTALPOSITIVEPOLARITY_WHERE_MOVIEID_MOVIEYPE_WITHOUTFEATURE_SQL = "RETRIVE_TOTALPOSITIVEPOLARITY_WHERE_MOVIEID_MOVIEYPE_WITHOUTFEATURE_SQL";
		public static final String RETRIVE_TOTALNEGATIVEPOLARITY_WHERE_MOVIEID_MOVIEYPE_WITHOUTFEATURE_SQL = "RETRIVE_TOTALNEGATIVEPOLARITY_WHERE_MOVIEID_MOVIEYPE_WITHOUTFEATURE_SQL";
		public static final String RETRIVE_TOTALNEUTRALPOLARITY_WHERE_MOVIEID_MOVIEYPE_WITHOUTFEATURE_SQL = "RETRIVE_TOTALNEUTRALPOLARITY_WHERE_MOVIEID_MOVIEYPE_WITHOUTFEATURE_SQL";  

	}
 
	interface CONSTANTS {

		public static final String SPACE = "  ";
		public static final String REVIEWDIVID_AMAZON = "div.reviewText";
		public static final String REVIEWDIVID_FLIPKART = "p";
		public static final String REVIEWCLASS_FLIPKART = "line bmargin10";
		public static final String AMAZON = "AMAZON";
		public static final String FLIPKART = "FLIPKART";

	}

	interface Message {

		public static final String REVIEW_FAILED = "Review could not be Stored";
		public static final String MESSAGE_INTERNAL = "An internal has Ocuured. Please Contact System Administrator";
		public static final String NOAMAZON_FLIPKART = "Keyword is neither Amazon nor Flipkart";
		public static final String REVIEW_RETRIVE_SUCESSFUL = "Reviews Retrived Sucessfully";
		public static final String NO_REVIEWS_FOUND = "No Reviews Found at this point of time";
		public static final String INTERNAL_ERROR = "Please Contact System Adminitrator. An Internal Error has Ocuurred";
		public static final String TOURISTPACKLIST_EMPTY = "Tourist Pack List cannot be Empty";
		public static final String FIRSTNAME_EMPTY = "First Name cannot be Empty";
		public static final String LASTNAME_EMPTY = "Last Name cannot be Empty";
		public static final String USERID_EMPTY = "User ID Cannot be Empty";
		public static final String EMAIL_EMPTY = "Email Cannot be Empty";
		public static final String PASSWORD_EMPTY = "Password Cannot be Empty";
		public static final String USERREGISTERED_SUCESS_MSG = "User Has Registered Sucessfully";
		public static final String USERALREADY_EXIST = "User Already Exist";
		public static final String NO_USER_EXISTS = "No User Already Exist";
		public static final String PASSWORD_WRONG = "Password does not exist";
		public static final String USER_LOGIN_SUCESS = "User Login is Sucessful";
		public static final String ACCNO_EMPTY = "Account No Cannot be Empty";
		public static final String IPIN_EMPTY = "IPIN Cannot be Empty";
		public static final String ACCNOLSIT_EMPTY = "Account No Not Found/List is Empty";
		public static final String INVALID_CREDENTIALS = "Could not Apply the Tourist Package as the Credentails were Invalid";
		public static final String INSUFFICENT_FUNDS = "Insufficient Funds";
		public static final String BALANCE_UPDATE_FAILED = "Balance Could not be Updated";
		public static final String INSERT_TRANS_FAILED = "Transaction Insertion has Failed";
		public static final String COMPLETE_TOURISM = "Tourist Package has been applied Sucessfully";
		public static final String REVIEWDETAILS_EMPTY = "Review Details Cannot be Empty";
		public static final String REVIEW_STORED_SUCESSFULLY = "Review Stored Sucessfully";
		public static final String KEYWORD_EMPTY = "Keyword Cannot be Empty";
		public static final String KEYWORD_STORAGE_FAILED = "The Keyword Could not be Stored";
		public static final String ADD_POSITIVEKEYWORD_SUCESS = "Storage of Positive Keyword is Sucessful";
		public static final String ADD_NEGATIVEKEYWORD_SUCESS = "Storage of Negative Keyword is Sucessful";
		public static final String EMPTY_NEGATIVEKEYWORDS = "No Negative Keywords Found in the Application";
		public static final String NEGATIVE_KEYWORDS_SUCESS = "Negative Keyword Retrival is Sucessful";
		public static final String EMPTY_POSITIVEKEYWORDS = "No Positive Keywords Found in the Application";
		public static final String POSITIVE_KEYWORDS_SUCESS = "Positive Keyword Retrival is Sucessful";
		public static final String EMPTY_REVIEWSLIST = "Reviews List is Empty";
		public static final String REVIEWS_FETCH_SUCESS = "Reviews Fetched Sucessfully";
		public static final String POSITIVE_KEYWORD_EXIST = "Positive Keyword Already Exist";
		public static final String NEGATIVE_KEYWORD_EXIST = "Negative Keyword Already Exist";
		public static final String RANKING_FAILED_TOURISTPACK = "Ranking of MOVIEs  have Failed using Semantic Analyzer Algorithm";
		public static final String RANK_POLARITY_SUCESS = "Ranking with Respect to Polarity is Sucessful";
		public static final String EMPTY_POLARITY = "Polarity Computation is Empty at this point of Time";
		public static final String POLARITY_KEYWORDS_SUCESS = "Polarity Keyword retrival is Sucessful";
		public static final String EMPTY_STOPWORD = "Stopword Cannot be Empty";
		public static final String STOPWORD_EXIST = "Stopword Already Exist";
		public static final String EMPTY_STOPWORDS = "Stop Words are Empty";
		public static final String STOPWORD_SUCESS = "Retrival of Stop Words is sucessful";
		public static final String STOPWORD_ADD_FAILED = "Failed to Add Stop Word";
		public static final String STOPWORD_ADD_SUCESS = "Stop Word Added Sucessfully";
		public static final String CLEANMODEL_FAILED = "Clean Model Insertion has Falied";
		public static final String CLEANREVIEWS_SUCESS = "Clean of Reviews is Sucessful";
		public static final String TOKENS_SUCESS = "Tokenization Process has been completed Sucessfully";
		public static final String CLEANREVIEWS_EMPTY = "Clean Reviews are Empty";
		public static final String INSERT_TOKENS_FAILED = "Insertion of Tokens has Failed";
		public static final String EMPTY_TOKENS = "Tokens Are Empty";
		public static final String TOKENRETRIVAL_SUCESS = "Retrival of Tokens is Sucessful";
		public static final String FREQ_SUCESS = "Frequency Computation is Sucessful";
		public static final String TOKENS_EMPTY = "Token List  Cannot be Empty";
		public static final String COULDNOT_DELETE_FREQUENCY = "Could not delete the Frequency Contents";
		public static final String COULDNOT_FIND_REVIEWS = "Could not Find Reviews";
		public static final String COULDNOT_FIND_TOKENS = "Could not Find Tokens For the Review";
		public static final String NO_TOKEN_FOUND = "No Token Found";
		public static final String COULDNOT_INSERT_FREQUENCY = "Could not insert into Frequency";
		public static final String FREQ_COMPUTATION_SUCESS = "Frequency Computation is Sucessful";
		public static final String FREQUENCYLIST_EMPTY = "Frequency List is Empty";
		public static final String COULDNOT_COMPUTE_FREQUENCY = "Could not Compute Frequency";
		public static final String INVALID_TOURPACKID = "Tourist Pack ID is Invalid";
		public static final String FEATUREVECTORSUCESS_VIEW = "Feature Vector has been Sucessfully Computed";
		public static final String COULDNOTFIND_MOVIEIDS_FROM_FREQ = "Could not find Movie Ids From Frequency";
		public static final String COULDNOTFIND_REVIEWIDS_FREQ = "Could not find Review Ids From Frequency ";
		public static final String COULDNOTFIND_TOKENS_FREQ = "Could not find Tokens for the Specfic Review";
		public static final String COULDNOTFIND_REVIEWLIST = "Could not Find Reviews for Token";
		public static final String FEATURE_VECTOR_EMPTY = "Feature Vector cannot be Empty";
		public static final String EMPTY_FEATUREVECTOR_LIST = "Feature Vector List is Empty";
		public static final String FEATUREVECTOR_FETCH_SUCESS = "Feature Vector Fecthced Sucessfully";
		public static final String SEARCH_EMPTY = "Search Criteria is Empty";
		public static final String COULD_NOT_RANK = "Could not Rank at this Point of time";
		public static final String DELETE_FV = "Could not Delete Feature Vector";
		public static final String STOPWORD_REMOVE_FAILED = "Stopword Removal Failed";
		public static final String NO_STOPWORD_EXIST = "Stopword does not exist";
		public static final String STOPWORD_REMOVE_SUCESS = "Stopwords removed sucessfully";
		public static final String EMPTY_POSITIVEKEYWORD = "Positive Keyword Cannot be Empty";
		public static final String POSITIVEKEYWORD_REMOVE_FAILED = "Removal of Positive Keyword has Failed";
		public static final String KEYWORD_REMOVE_SUCESS = "Removal of Keyword is Sucessful";
		public static final String EMPTY_NEGATIVEKEYWORD = "Negative Keyword Cannot be Empty";
		public static final String NEGATIVEKEYWORD_REMOVE_FAILED = "Negative Keyword removal Failed";
		public static final String NO_POSITIVEKEYWORD_EXIST = "No Positive Keyword Exist";
		public static final String NO_NEGATIVEKEYWORD_EXIST = "No Negative Keyword Exist";
		public static final String RANKING_FAILED_POLARITY = "Rank of Review Polarity is Failed";
		public static final String WEBURL_CANNOT_BE_EMPTY = "Web URL Cannot be Empty";
		public static final String XPATH_CANNOT_BE_EMPTY = "X Path Cannot be Empty";
		public static final String EMPTY_HASHTAGS = "No Hash tags Found";
		public static final String HASHTAGS_SUCESS = "Hash Tags Retrival is Sucessful";
		public static final String EMPTY_HASHTAG = "Hash Tag Cannot be Empty";
		public static final String HASHTAG_ADD_FAILED = "Hash Tag Addition has Failed";
		public static final String HASHTAG_ADD_SUCESS = "Hash Tag Addition is Sucessful";
		public static final String HASHTAG_EXIST = "Hash Tag Already Exist";
		public static final String DELETE_REVIEW_SQL = "Deletion of Reviews Failed";
		public static final String HASHTAGS_EMPTY = "Hash Tags are Empty";
		public static final String COULD_NOT_INSERT_REVIEW = "Could not Insert Review";
		public static final String TABLEDATA_REMOVE_SUCESS = "Table Data Removed Sucessfully";
		public static final String TABLEDATA_REMOVE_FAILED = "Table Data Removal Failed";
		public static final String EMPTY_TABLE = "No Enitity has been Selected";
		public static final String CLASSIFICATION_FAILED = "Classification has Failed";
		public static final String CLASSIFICTAION_SUCESS = "Classification is Sucessful";
		public static final String PLEASE_PERFORM_RANKING_TOTALSENTIMENTS_FOR_MOVIE = "Please Perform Raning for Total Sentiments";
		public static final String COULD_NOT_INSERT_MOVIESTATUS = "Could not Find the Movie Status";
		public static final String COULD_NOT_DELETE_MOVIESTATUS = "Could not Delete Movie Status";
		public static final String MOVIESTATUS_SUCESS = "Movie Status retrived Sucessfully";
		public static final String EMPTY_MOVIESTATUS = "No Movie Status Found";
		public static final String COULD_NOT_SAVE_MOVIE_POLARITY = "Could not Save Movie Polarity";
		public static final String COULD_NOT_REMOVE_OLD_POLARITY = "Could not remove Movie Polarity";
		public static final String COULD_NOT_SAVE_REVIEW_POLARITY = "Could not Save Review Based Polarity";
		public static final String COULD_NOT_SAVE_REVIEWS = "Could not Save Reviews";
		public static final String REVIEW_POLARITY_SUCESSFUL = "Retrival of Polarity is Sucessful";
		public static final String NO_POLARITY_FOUND = "No polarity has been Found";
		public static final String REVIEW_TOTALPOLARITY_SUCESSFUL = "Retrival of Total Polarity is Sucessful";
		public static final String NO_TOTALPOLARITY_FOUND = "No Total Polarity has been Found";
		public static final String RETRIVE_POLAIRITY_SUCESS = "Retrival of Polarity is Successful";
		public static final String SAVE_MOVIE_SUCESS = "Save of Movie is Sucessful";
		public static final String SAVE_MOVIE_FAILED = "Saving of Movie has Failed";
		public static final String MOVIE_NAME_EXISTS = "Movie Name Already Exists";
		public static final String RET_MOVIES_SUCESSFUL = "Movies Retrive Sucessful";
		public static final String MOVIE_NAME_CAANOT_BE_EMPTY = "Please Provide Movie Name";
		public static final String ANSWER_SUCESSFUL = "Answer Retrival is Sucessful";
		public static final String NO_ANSWER_FOUND = "No Tests Would have been taken";

	}

	interface Keys {
		public static final String OBJ = "obj";
		public static final int ADMIN_TYPE = 1;
		public static final String STOPWORDS_SYMBOL = "[^a-zA-Z]+";
		public static final String SPACE = "  ";
		public static final String FLOP_STATUS = "FLOP";
		public static final String HIT_STATUS = "HIT";
		public static final String SUPERHIT_STATUS = "SUPERHIT";
		public static final int CUSTOMER_TYPE = 2;
		public static final String POSITIVE = "POSITIVE";
		public static final String NEGATIVE = "NEGATIVE";
		String LOGINID_SESSION = "LOGINID_SESSION";
		String LOGINTYPE_SESSION = "LOGINTYPE_SESSION";
		String APP_NAME = "APP+91-9900723211";

	}

	interface COLUMNNAMES {
		/*
		 * These are the column names for review table
		 */
		public static final String MOVIEID_COL = "MOVIEID";
		public static final String MOVIETYPE_COL = "CATID";
		public static final String REVIEWDETAILS_COL = "REVIEWDETAILS";
		/*
		 * These are the column names for the MOVIE table
		 */
		public static final String MOVIEID__PK_COL = "MOVIEID_PK";
		public static final String MOVIENAME_COL = "MOVIENAME";
		public static final String MOVIETYPE_FK_COL = "MOVIETYPE_FK";
		/*
		 * These are the column names for the MOVIE Type table
		 */
		public static final String MOVIEYPEID_PK_COL = "MOVIETYPEID_PK";
		public static final String MOVIEYPENAME_COL = "MOVIETYPENAME";
		public static final String MOVIE_TYPE_COL = "MOVIETYPE_FK";

	}

	interface Views {
		public static final String VIEW_CUSTOMER_WELCOMEPAGE = "custwelcome";
		public static final String VIEW_ADMIN_WELCOMEPAGE = "admin";
		public static final String VIEW_ERROR_PAGE = "error";
		public static final String APPLICATION_WELCOME_PAGE = "applicationwelcome";
		public static final String VIEW_SUCESS_PAGE = "sucess";
		public static final String APPLYREVIEW_VIEW = "applyReview";
		public static final String ADD_POSITIVEKEYWORD_VIEW = "addpositivekeyword";
		public static final String ADD_NEGATIVEKEYWORD_VIEW = "addnegativekeyword";
		public static final String VIEW_ADMIN_SUCESS_PAGE = "adminsucess";
		public static final String VIEW_ADMIN_ERROR_PAGE = "adminerror";
		public static final String VIEW_POLARITY_PACK = "polarity";
		public static final String STOPWORD_INPUT = "addStopword";
		public static final String RANKFV_INPUT = "rankfv";
		public static final String RANKFV_OUTPUT = "rankfvout";
		public static final String RANKCORRELATION_MOBILE_INPUT = "rankCorrelationMobile";
		public static final String RANKCORRELATION_OUTPUT = "rankCorrelationOutput";
		public static final String REMOVESTOPWORD_INPUT = "removeStopword";
		public static final String REMOVEPOSITIVEKEYWORD_INPUT = "removepositivekeyword";
		public static final String REMOVENEGATIVEKEYWORD_INPUT = "removenegativekeyword";
		public static final String RANKFEATURE_OUTPUT = "rankfeature";
		public static final String DELETEDATA_INPUT_VIEW = "deleteData";
		public static final String ONLINE_TWEETS_VIEW = "onlinetweets";
		public static final String SUCESS_VIEW = "sucess";
	}

	interface DatabaseColumns {


		public static final String NEGATIVEKEYID_COL = "NEGATIVEKEYID";

		public static final String NEGATIVEKEYWORD_COL = "NEGATIVEKEYWORD";

		public static final String PKEYID_COL = "PKEYID";

		public static final String PKEYWORDS_COL = "PKEYWORDS";

		public static final String REVIEWID_COL = "REVIEWID";

		public static final String REVIEWDETAILS_COL = "REVIEWDETAILS";

		public static final String POSITIVERATING_COL = "POSITIVERATING";

		public static final String NEGATIVERATING_COL = "NEGATIVERATING";

		public static final String NEUTRALRATING_COL = "NEUTRALRATING";

		public static final String STOPWORDID_COL = "STOPWORDID";

		public static final String STOPWORD_COL = "STOPWORD";

		public static final String CLEANID_COL = "CLEANID";

		public static final String CLEANREVIEW_COL = "CLEANREVIEW";

		public static final String TOKENID_COL = "TOKENID";

		public static final String TOKENNAME_COL = "TOKENNAME";

		public static final String FREQID_COL = "FREQID";

		public static final String FREQ_COL = "FREQ";

		public static final String NOOFREVIEWS_COL = "NOOFREVIEWS";

		public static final String IDFT_COL = "IDFT";

		public static final String FEATUREVECTOR_COL = "FEATUREVECTOR";

		public static final String FEATUREID_COL = "FEATUREID";

		public static final String MOVIEID_COL = "MOVIEID";

		public static final String CATID_COL = "CATID";

		public static final String MOVIE_TYPE_COL = "MOVIETYPE";

		public static final String POSITIVEPOLARITY_COL = "POSITIVEPOLARITY";

		public static final String NEGATIVEPOLARITY_COL = "NEGATIVEPOLARITY";

		public static final String NEUTRALPOLARITY_COL = "NEUTRALPOLARITY";

		public static final String FEATUREBASEDFREQ_COL = "FEATUREBASEDFREQ";

		public static final String FEATURETYPE_COL = "FEATURETYPE";

		public static final String TOTALFEATURE_COL = "FEATURESCORE";

		public static final String HASHTAGID_COL = "HASHTAGID";

		public static final String HASHTAG_COL = "HASHTAG";

	}

	interface EndPoints {

		//String BASE_END_POINT = "https://arcane-castle-84234.herokuapp.com/";
		
		String BASE_END_POINT="http://localhost:8081";

		// String
		// REGISTER_ENDPOINT="https://arcane-castle-84234.herokuapp.com/register";
		String REGISTER_ENDPOINT = BASE_END_POINT + "/register";

		String USERS_FETCH_ENDPOINT_WITH_APPNAME = BASE_END_POINT + "register/" + Keys.APP_NAME;
	}
	
	interface RegisterDetails{
		
		String DEFAULT_ADMIN_USER="movieadmin12345";
		
		String DEFAULT_ADMIN_PASS="movieadmin@0987654321";
		
	}
	
	
	interface FeatureTypes{
		
		String ACTOR_FEATURETYPE="ACTOR";
		
		String ACTRESS_FEATURETYPE="ACTRESS";
		
		String PRODUCTION_FEATURETYPE="PRODUCTION";
		
		String DIRECTION_FEATURETYPE="DIRECTION";
		
		String SONGS_FEATURETYPE="SONGS";
		
		String DIALOGUES_FEATURETYPE="DIALOGUES";
		
	}
	
	

}
