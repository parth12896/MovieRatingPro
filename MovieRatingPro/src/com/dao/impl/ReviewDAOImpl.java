package com.dao.impl;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;

import com.constants.ReviewConstantsIF;
import com.dao.inter.ReviewDAOIF;
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
import com.model.MovieNameList;
import com.model.MovieTypeInfo;
import com.model.PaginationConfigVO;
import com.model.ReviewModel;
import com.model.ReviewModelObj;
import com.model.StatusInfo;
import com.model.StopWordsVO;
import com.model.TokenVO;
import com.model.TotalPolarityModel;

public class ReviewDAOImpl implements ReviewDAOIF {

	protected SimpleJdbcTemplate template;
	protected NamedParameterJdbcTemplate namedJdbcTemplate;
	private DataSource dataSource;
	@Autowired
	protected MessageSource sqlProperties;
	protected JdbcTemplate jdbcTemplate;

	/**
	 * 
	 */
	public void init() {
		template = new SimpleJdbcTemplate(dataSource);
		namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	protected boolean update(String sqlKey, Map<String, Object> map) {
		System.out.println("Class-->RoutingDaoImpl:Method-->update");
		String sql = sqlProperties.getMessage(sqlKey, null, null);
		System.out.println("SQL" + sql);
		boolean value = false;
		try {
			namedJdbcTemplate.update(sql, map);
			value = true;
		} catch (Exception e) {
			System.out.println("Exception" + e.getMessage());
		}
		return value;
	}

	/**
	 * @param sqlKey
	 * @param map
	 * @return true if sucessfully updated
	 */
	protected boolean insert(String sqlKey, Map<String, Object> map) {
		System.out.println("Class-->RoutingDaoImpl:Method-->update");
		String sql = sqlProperties.getMessage(sqlKey, null, null);
		System.out.println("SQL" + sql);
		boolean value = false;
		try {
			namedJdbcTemplate.update(sql, map);
			value = true;
		} catch (Exception e) {
			System.out.println("Exception" + e.getMessage());
		}
		return value;
	}

	/**
	 * @param sqlQuery
	 * @param sqlKey
	 * @param map
	 * @return true if sucessfully updated
	 */
	protected boolean insertBasedOnQuery(String sqlQuery, Map<String, Object> map) {
		System.out.println("Class-->RoutingDaoImpl:Method-->update");
		boolean insertQuery = false;
		try {
			namedJdbcTemplate.update(sqlQuery, map);
			insertQuery = true;
		} catch (Exception e) {
			System.out.println("Exception" + e.getMessage());
		}
		return insertQuery;
	}

	/**
	 * @param <T>
	 * @param sqlKey
	 * @param paramMap
	 * @param rowMapper
	 * @return Object
	 */
	protected <T> T executeForObject(String sqlKey, Map<String, ? extends Object> paramMap, RowMapper<T> rowMapper) {
		String sql = sqlProperties.getMessage(sqlKey, null, null);
		return namedJdbcTemplate.queryForObject(sql, paramMap, rowMapper);
	}

	protected <T> T executeForObjectUsingQuery(String sql, Map<String, ? extends Object> paramMap,
			RowMapper<T> rowMapper) {
		return namedJdbcTemplate.queryForObject(sql, paramMap, rowMapper);
	}

	/**
	 * @param sqlKey
	 * @param params
	 * @param whereClause
	 * @return int once the statement gets executed
	 */
	protected int executeForInt(String sqlKey, Map<String, String> params, String whereClause) {
		String sql = sqlProperties.getMessage(sqlKey, null, null);
		sql = sql.concat(whereClause);
		System.out.println(sql);

		return namedJdbcTemplate.queryForInt(sql, params);
	}

	/**
	 * @param sqlKey
	 * @param params
	 * @return List of String objects
	 */
	protected List<String> executeForListOfString(String sqlKey, Map<String, Object> params) {
		String sql = sqlProperties.getMessage(sqlKey, null, null);
		System.out.println(sql);
		System.out.println(params);

		return namedJdbcTemplate.queryForList(sql, params, String.class);
	}

	/**
	 * @param sqlKey
	 * @param params
	 * @return List of integer values
	 */
	protected List<Integer> executeForListOfInt(String sqlKey, Map<String, Object> params) {
		String sql = sqlProperties.getMessage(sqlKey, null, null);
		System.out.println(sql);
		System.out.println(params);

		return namedJdbcTemplate.queryForList(sql, params, Integer.class);
	}

	/**
	 * @return template
	 */
	public SimpleJdbcTemplate getTemplate() {
		return template;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * @return Named Parameter JDBC Template
	 */
	public NamedParameterJdbcTemplate getNamedJdbcTemplate() {
		return namedJdbcTemplate;
	}

	/**
	 * @return the SQL properties
	 */
	public MessageSource getSqlProperties() {
		return sqlProperties;
	}

	/**
	 * @param sqlProperties
	 */
	public void setSqlProperties(MessageSource sqlProperties) {
		this.sqlProperties = sqlProperties;
	}

	@Override
	public StatusInfo insertReview(ReviewModel reviewModel) {

		StatusInfo statusInfo = new StatusInfo();
		try {
			String sqlKey = ReviewConstantsIF.SQLS.INSERT_REVIEW_SQL;
			String sql = sqlProperties.getMessage(sqlKey, null, null);
			jdbcTemplate
					.update(sql,
							new Object[] { new SqlLobValue(reviewModel.getReviewDetails()), reviewModel.getMovieid(),
									reviewModel.getMovietype() },
							new int[] { Types.BLOB, Types.INTEGER, Types.INTEGER });
			statusInfo.setStatus(true);

		} catch (Exception e) {
			System.out.println("Exception is:");
			System.out.println(e.getMessage());
			e.printStackTrace();
			statusInfo.setErrMsg(ReviewConstantsIF.Message.MESSAGE_INTERNAL);
			statusInfo.setExceptionMsg(e.getMessage());
			statusInfo.setStatus(false);
			return statusInfo;

		}
		return statusInfo;
	}

	@Override
	public List<ReviewModel> retriveAllReviews() {

		try {

			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_REVIEWS_SQL, null, null);
			return jdbcTemplate.query(sql, new ReviewModelVOMapper());
		} catch (Exception e) {
			System.out.println("Exception" + e);
			return null;
		}
	}

	private final class ReviewModelVOMapper implements RowMapper<ReviewModel> {

		public ReviewModel mapRow(ResultSet rs, int arg1) throws SQLException {

			ReviewModel reviewModel = new ReviewModel();

			reviewModel.setReviewId(rs.getInt("REVIEWID"));
			reviewModel.setMovieid(rs.getInt(ReviewConstantsIF.COLUMNNAMES.MOVIEID_COL));
			reviewModel.setMovietype(rs.getInt("CATID"));

			// Blob to String
			Blob blob = rs.getBlob(ReviewConstantsIF.COLUMNNAMES.REVIEWDETAILS_COL);
			byte[] bdata = blob.getBytes(1, (int) blob.length());
			String reviewDetailsStr = new String(bdata);
			reviewModel.setReviewDetails(reviewDetailsStr);
			return reviewModel;

		}

	}

	@Override
	public List<MovieInfo> retriveAllMovieInfo() {
		try {

			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_MOVIEINFO_SQL, null, null);
			return jdbcTemplate.query(sql, new MovieInfoListMapper());
		} catch (Exception e) {
			System.out.println("Exception" + e);
			return null;
		}
	}

	private final class MovieInfoListMapper implements RowMapper<MovieInfo> {

		public MovieInfo mapRow(ResultSet rs, int arg1) throws SQLException {

			MovieInfo MovieInfo = new MovieInfo();
			MovieInfo.setMovieId(rs.getInt(ReviewConstantsIF.COLUMNNAMES.MOVIEID__PK_COL));
			MovieInfo.setMovieName(rs.getString(ReviewConstantsIF.COLUMNNAMES.MOVIENAME_COL));
			MovieInfo.setMovieType(rs.getInt(ReviewConstantsIF.COLUMNNAMES.MOVIETYPE_FK_COL));
			return MovieInfo;

		}

	}

	@Override
	public List<MovieInfo> retriveSpecficMoviesInfo(int MovieType) {
		try {

			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_MOVIEINFO_FOR_MOVIETYPE_SQL, null,
					null);
			return jdbcTemplate.query(sql, new MovieInfoListMapper(), MovieType);
		} catch (Exception e) {
			System.out.println("Exception" + e);
			return null;
		}
	}

	@Override
	public List<MovieTypeInfo> retriveAllMovieTypes() {
		try {

			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_MOVIETYPEINFO_SQL, null, null);
			return jdbcTemplate.query(sql, new MovieTypeInfoListMapper());
		} catch (Exception e) {
			System.out.println("Exception" + e);
			return null;
		}
	}

	private final class MovieTypeInfoListMapper implements RowMapper<MovieTypeInfo> {

		public MovieTypeInfo mapRow(ResultSet rs, int arg1) throws SQLException {

			MovieTypeInfo MovieInfo = new MovieTypeInfo();

			MovieInfo.setMovieTypeId(rs.getInt(ReviewConstantsIF.COLUMNNAMES.MOVIEYPEID_PK_COL));
			MovieInfo.setMovieName(rs.getString(ReviewConstantsIF.COLUMNNAMES.MOVIEYPENAME_COL));

			return MovieInfo;

		}

	}

	@Override
	public MovieTypeInfo retriveSpecificMovieTypeName(int MovieType) {
		try {

			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_MOVIETYPEINFO_FOR_MOVIETYPEID_SQL,
					null, null);
			return jdbcTemplate.queryForObject(sql, new MovieTypeInfoListMapper(), MovieType);
		} catch (Exception e) {
			System.out.println("Exception" + e);
			return null;
		}
	}

	@Override
	public String retriveMovieNameForId(int MovieId) {
		try {

			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_MOVIENAME_FOR_MOVIEID, null, null);
			return jdbcTemplate.queryForObject(sql, String.class, MovieId);
		} catch (Exception e) {
			System.out.println("Exception" + e);
			return null;
		}
	}

	@Override
	public StatusInfo insertNegativeKeywords(String keywordV) {
		StatusInfo negativeKeywords = null;
		try {
			negativeKeywords = new StatusInfo();
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.INSERT_NEGATIVEKEYWORDS_SQL, null, null);
			jdbcTemplate.update(sql, new Object[] { keywordV }, new int[] { Types.VARCHAR });
			negativeKeywords.setStatus(true);
			return negativeKeywords;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			negativeKeywords = new StatusInfo();
			negativeKeywords.setErrMsg(e.getMessage());
			negativeKeywords.setStatus(false);
			return negativeKeywords;

		}
	}

	@Override
	public StatusInfo insertPositiveKeywords(String keywordV) {
		StatusInfo insertPositiveKeywords = null;
		try {
			insertPositiveKeywords = new StatusInfo();
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.INSERT_POSTIVEKEYWORDS_SQL, null, null);
			jdbcTemplate.update(sql, new Object[] { keywordV }, new int[] { Types.VARCHAR });
			insertPositiveKeywords.setStatus(true);
			return insertPositiveKeywords;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			insertPositiveKeywords = new StatusInfo();
			insertPositiveKeywords.setErrMsg(e.getMessage());
			insertPositiveKeywords.setStatus(false);
			return insertPositiveKeywords;
		}
	}

	@Override
	public List<KeywordInfo> retriveNegativeKeywords() {
		List<KeywordInfo> packAssociationList = null;
		try {
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_NEGATIVEKEYWORDS_SQL, null, null);
			return jdbcTemplate.query(sql, new NegativeKeywordInfoMapper());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	private final class NegativeKeywordInfoMapper implements RowMapper<KeywordInfo> {

		public KeywordInfo mapRow(ResultSet rs, int arg1) throws SQLException {
			KeywordInfo keywordInfo = new KeywordInfo();
			keywordInfo.setKeywordId(rs.getInt(ReviewConstantsIF.DatabaseColumns.NEGATIVEKEYID_COL));
			keywordInfo.setKeyword(rs.getString(ReviewConstantsIF.DatabaseColumns.NEGATIVEKEYWORD_COL));
			return keywordInfo;
		}

	}

	@Override
	public List<String> retriveNegativeKeywordsOnly() {
		try {
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_NEGATIVE_KEYWORDS_SQL, null, null);

			return jdbcTemplate.queryForList(sql, String.class);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}

	}

	@Override
	public List<KeywordInfo> retrivePositiveKeywords() {
		List<KeywordInfo> packAssociationList = null;
		try {
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_POSITIVEKEYWORDS_SQL, null, null);
			return jdbcTemplate.query(sql, new PositiveKeywordInfoMapper());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	private final class PositiveKeywordInfoMapper implements RowMapper<KeywordInfo> {

		public KeywordInfo mapRow(ResultSet rs, int arg1) throws SQLException {
			KeywordInfo keywordInfo = new KeywordInfo();
			keywordInfo.setKeywordId(rs.getInt(ReviewConstantsIF.DatabaseColumns.PKEYID_COL));
			keywordInfo.setKeyword(rs.getString(ReviewConstantsIF.DatabaseColumns.PKEYWORDS_COL));
			return keywordInfo;
		}

	}

	@Override
	public List<String> retrivePositiveKeywordsOnly() {
		try {
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_POSITIVE_KEYWORDS_SQL, null, null);

			return jdbcTemplate.queryForList(sql, String.class);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public StatusInfo deleteSentimentAnalyzer(int type) {
		StatusInfo statusInfo = null;

		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.DELETE_SENTIMENTANALYZER_SQL, null, null);
			jdbcTemplate.update(sql, type);
			statusInfo.setStatus(true);
			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}
	}

	@Override
	public StatusInfo deleteTotalPolarity(int type) {
		StatusInfo statusInfo = null;

		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.DELETE_SENTIMENTTOTAL_SQL, null, null);
			jdbcTemplate.update(sql, type);
			statusInfo.setStatus(true);
			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}
	}

	@Override
	public List<ReviewModelObj> retriveReviewList(int type) {

		try {
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_ALLREVIEWS_SQL, null, null);
			return jdbcTemplate.query(sql, new ReviewModelMapper(), type);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	private final class ReviewModelMapper implements RowMapper<ReviewModelObj> {

		public ReviewModelObj mapRow(ResultSet rs, int arg1) throws SQLException {
			ReviewModelObj reviewModel = new ReviewModelObj();
			reviewModel.setMovieId(rs.getInt(ReviewConstantsIF.DatabaseColumns.MOVIEID_COL));
			reviewModel.setReviewId(rs.getInt(ReviewConstantsIF.DatabaseColumns.REVIEWID_COL));
			reviewModel.setReviewDetailsBlob(rs.getBlob(ReviewConstantsIF.DatabaseColumns.REVIEWDETAILS_COL));
			reviewModel.setMovieType(rs.getInt(ReviewConstantsIF.DatabaseColumns.CATID_COL));

			return reviewModel;
		}

	}

	@Override
	public StatusInfo insertPolarity(PolarityModel polarityModel) {
		StatusInfo insertPolarity = null;
		try {
			insertPolarity = new StatusInfo();
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.INSERT_POLARITY_SQL, null, null);

			jdbcTemplate.update(sql,
					new Object[] { polarityModel.getReviewId(), polarityModel.getPositiveRating(),
							polarityModel.getNegativeRating(), polarityModel.getNeutralRating(),
							polarityModel.getMovieId(), polarityModel.getMovieType(), polarityModel.getFeatureType() },
					new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER,
							Types.INTEGER, Types.VARCHAR });
			insertPolarity.setStatus(true);
			return insertPolarity;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			insertPolarity = new StatusInfo();
			insertPolarity.setErrMsg(e.getMessage());
			insertPolarity.setStatus(false);
			return insertPolarity;

		}
	}

	@Override
	public List<Integer> retriveUniqueMovieIdsFromSentimentAnalyzer(int type) {
		try {
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_UNIQUE_MOVIEIDS_SQL, null, null);

			return jdbcTemplate.queryForList(sql, Integer.class, type);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public int retriveTotalPositiveRatingForMovie(Integer MovieIdTemp, int MovieType) {
		try {
			String sql = sqlProperties.getMessage(
					ReviewConstantsIF.SQLS.RETRIVE_TOTALPOSITIVEPOLARITY_WHERE_MOVIEID_MOVIEYPE_SQL, null, null);
			return jdbcTemplate.queryForInt(sql, MovieIdTemp, MovieType);

		} catch (Exception e) {
			System.out.println("Exception" + e);
			return -1;
		}

	}

	@Override
	public int retriveTotalNegativeRatingForMovie(Integer MovieIdTemp, int MovieType) {
		try {
			String sql = sqlProperties.getMessage(
					ReviewConstantsIF.SQLS.RETRIVE_TOTALNEGATIVEPOLARITY_WHERE_MOVIEID_MOVIEYPE_SQL, null, null);
			return jdbcTemplate.queryForInt(sql, MovieIdTemp, MovieType);

		} catch (Exception e) {
			System.out.println("Exception" + e);
			return -1;
		}
	}

	@Override
	public int retriveTotalNeutralRatingForMovie(Integer MovieIdTemp, int MovieType) {
		try {
			String sql = sqlProperties.getMessage(
					ReviewConstantsIF.SQLS.RETRIVE_TOTALNEUTRALPOLARITY_WHERE_MOVIEID_MOVIEYPE_SQL, null, null);
			return jdbcTemplate.queryForInt(sql, MovieIdTemp, MovieType);

		} catch (Exception e) {
			System.out.println("Exception" + e);
			return -1;
		}
	}

	@Override
	public StatusInfo insertTotalPolarity(TotalPolarityModel totalPolarityModel) {
		StatusInfo insertPackAssociationStatus = null;
		try {
			insertPackAssociationStatus = new StatusInfo();
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.INSERT_TOTALPOLARITY_SQL, null, null);

			jdbcTemplate.update(sql,
					new Object[] { totalPolarityModel.getMovieId(), totalPolarityModel.getPositiveRating(),
							totalPolarityModel.getNegativeRating(), totalPolarityModel.getNeutralRating(),
							totalPolarityModel.getMovieType(), totalPolarityModel.getTotalFeature(),
							totalPolarityModel.getFeatureType() },
					new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER,
							Types.INTEGER, Types.VARCHAR });
			insertPackAssociationStatus.setStatus(true);
			return insertPackAssociationStatus;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			insertPackAssociationStatus = new StatusInfo();
			insertPackAssociationStatus.setErrMsg(e.getMessage());
			insertPackAssociationStatus.setStatus(false);
			return insertPackAssociationStatus;
		}
	}

	@Override
	public List<Integer> retriveMovieIdsIDSORderBy(int type) {
		try {
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_MOVIEIDS_ORDERBY_WHERE_MOVIEYPE_SQL,
					null, null);
			return jdbcTemplate.queryForList(sql, Integer.class, type);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public List<PolarityModel> retrivePolarity(int type) {
		try {
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_ALLPOLARITY_WHERE_MOVIETYPE_SQL, null,
					null);
			return jdbcTemplate.query(sql, new PolarityModelMapper(), type);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	private final class PolarityModelMapper implements RowMapper<PolarityModel> {

		public PolarityModel mapRow(ResultSet rs, int arg1) throws SQLException {
			PolarityModel polarityModel = new PolarityModel();

			polarityModel.setReviewId(rs.getInt(ReviewConstantsIF.DatabaseColumns.REVIEWID_COL));

			polarityModel.setMovieId(rs.getInt(ReviewConstantsIF.DatabaseColumns.MOVIEID_COL));

			polarityModel.setPositiveRating(rs.getInt(ReviewConstantsIF.DatabaseColumns.POSITIVERATING_COL));
			polarityModel.setNegativeRating(rs.getInt(ReviewConstantsIF.DatabaseColumns.NEGATIVERATING_COL));
			polarityModel.setNeutralRating(rs.getInt(ReviewConstantsIF.DatabaseColumns.NEUTRALRATING_COL));

			polarityModel.setMovieType(rs.getInt(ReviewConstantsIF.DatabaseColumns.MOVIE_TYPE_COL));

			polarityModel.setFeatureType(rs.getString(ReviewConstantsIF.DatabaseColumns.FEATURETYPE_COL));

			return polarityModel;
		}
	}

	@Override
	public List<PolarityModel> retriveTotalPolarity(int type) {
		try {
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_TOTALPOLARITY_WHERE_MOVIETYPE_SQL,
					null, null);
			return jdbcTemplate.query(sql, new PolarityTotalModelMapper(), type);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	// SELECT MOVIEID,SUM(POSITIVEPOLARITY) AS
	// POSITIVEPOLARITY,SUM(NEGATIVEPOLARITY) AS
	// NEGATIVEPOLARITY,SUM(NEUTRALPOLARITY) AS
	// NEUTRALPOLARITY,MOVIETYPE,SUM(FEATURESCORE)
	// AS FEATURESCORE,FEATURETYPE FROM SENTIMENTTOTAL WHERE MOVIETYPE=? AND
	// FEATURETYPE=? GROUP BY MOVIEID

	private final class PolarityTotalModelMapper implements RowMapper<PolarityModel> {

		public PolarityModel mapRow(ResultSet rs, int arg1) throws SQLException {
			PolarityModel polarityModel = new PolarityModel();

			polarityModel.setMovieId(rs.getInt(ReviewConstantsIF.DatabaseColumns.MOVIEID_COL));

			polarityModel.setPositiveRating(rs.getInt(ReviewConstantsIF.DatabaseColumns.POSITIVEPOLARITY_COL));
			polarityModel.setNegativeRating(rs.getInt(ReviewConstantsIF.DatabaseColumns.NEGATIVEPOLARITY_COL));
			polarityModel.setNeutralRating(rs.getInt(ReviewConstantsIF.DatabaseColumns.NEUTRALPOLARITY_COL));

			polarityModel.setMovieType(rs.getInt(ReviewConstantsIF.DatabaseColumns.MOVIE_TYPE_COL));

		
			polarityModel.setFeatureType(rs.getString(ReviewConstantsIF.DatabaseColumns.FEATURETYPE_COL));

			return polarityModel;
		}
	}

	private final class MovieModelGraphMapper implements RowMapper<MovieModel> {

		public MovieModel mapRow(ResultSet rs, int arg1) throws SQLException {
			MovieModel MovieModelVO = new MovieModel();
			MovieModelVO.setMovieId(rs.getInt(ReviewConstantsIF.DatabaseColumns.MOVIEID_COL));
			MovieModelVO.setFeatureVector(rs.getDouble(ReviewConstantsIF.DatabaseColumns.FEATUREVECTOR_COL));
			MovieModelVO.setMovieType(rs.getInt(ReviewConstantsIF.DatabaseColumns.MOVIE_TYPE_COL));
			return MovieModelVO;

		}
	}

	@Override
	public List<Integer> retriveDistinctMovieIdsFromReviews(int type) {
		try {
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_UNIQUE_MOVIEIDS_FROM_REVIEW_SQL, null,
					null);

			return jdbcTemplate.queryForList(sql, Integer.class, type);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public List<String> retriveFeatureTypes() {
		List<String> featureTypes = null;
		try {

			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_FEATURETYPES_SQL, null, null);

			return jdbcTemplate.queryForList(sql, String.class);

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;

		}
	}

	@Override
	public List<String> removePositiveKeywordOnly() {
		try {
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_POSITIVEKEYWORDS_ONLY_SQL, null, null);
			return jdbcTemplate.queryForList(sql, String.class);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public StatusInfo removePositiveKeyword(String stopWord) {
		StatusInfo statusInfo = null;

		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.REMOVE_POSITIVEKEYWORD_SQL, null, null);
			jdbcTemplate.update(sql, stopWord);
			statusInfo.setStatus(true);
			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}
	}

	@Override
	public List<String> removeNegativeKeywordOnly() {
		try {
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_NEGATIVEKEYWORDS_ONLY_SQL, null, null);
			return jdbcTemplate.queryForList(sql, String.class);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public StatusInfo removeNegativeKeyword(String stopWord) {
		StatusInfo statusInfo = null;

		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.REMOVE_NEGATIVEKEYWORD_SQL, null, null);
			jdbcTemplate.update(sql, stopWord);
			statusInfo.setStatus(true);
			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}
	}

	@Override
	public int retriveTotalPositiveRatingForMovieAndFeatureType(Integer MovieIdTemp, int type, String featureType) {
		try {
			String sql = sqlProperties.getMessage(
					ReviewConstantsIF.SQLS.RETRIVE_TOTALPOSITIVEPOLARITY_WHERE_MOVIEID_MOVIEYPE_FEATURETYPE_SQL, null,
					null);
			return jdbcTemplate.queryForInt(sql, MovieIdTemp, type, featureType);

		} catch (Exception e) {
			System.out.println("Exception" + e);
			return -1;
		}
	}

	@Override
	public int retriveTotalNegativeRatingForMovieAndFeatureType(Integer MovieIdTemp, int type, String featureType) {
		try {
			String sql = sqlProperties.getMessage(
					ReviewConstantsIF.SQLS.RETRIVE_TOTALNEGATIVEPOLARITY_WHERE_MOVIEID_MOVIEYPE_FEATURETYPE_SQL, null,
					null);
			return jdbcTemplate.queryForInt(sql, MovieIdTemp, type, featureType);

		} catch (Exception e) {
			System.out.println("Exception" + e);
			return -1;
		}
	}

	@Override
	public int retriveTotalNeutralRatingForMovieAndFeatureType(Integer MovieIdTemp, int type, String featureType) {
		try {
			String sql = sqlProperties.getMessage(
					ReviewConstantsIF.SQLS.RETRIVE_TOTALNEUTRALPOLARITY_WHERE_MOVIEID_MOVIEYPE_FEATURETYPE_SQL, null,
					null);
			return jdbcTemplate.queryForInt(sql, MovieIdTemp, type, featureType);

		} catch (Exception e) {
			System.out.println("Exception" + e);
			return -1;
		}
	}

	@Override
	public int retriveTotalFeatureForMovie(Integer MovieIdTemp, int type, String featureType) {
		try {
			String sql = sqlProperties.getMessage(
					ReviewConstantsIF.SQLS.RETRIVE_FREQ_FROM_FREQ_WHERE_MOVIEID_MOVIETYPE_FEATURETYPE_SQL, null, null);
			return jdbcTemplate.queryForInt(sql, MovieIdTemp, type, featureType);
		} catch (Exception e) {
			System.out.println("Exception" + e);
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public StatusInfo insertPolarityOrderInfo(PolarityOrderInfo polarityOrderInfo) {
		StatusInfo insertTokenStatus = null;
		try {
			insertTokenStatus = new StatusInfo();
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.INSERT_POLARITYORDER_SQL, null, null);

			jdbcTemplate.update(sql,
					new Object[] { polarityOrderInfo.getMovieId(), polarityOrderInfo.getTotalNegative(),
							polarityOrderInfo.getTotalNeutral(), polarityOrderInfo.getTotalPositive(),
							polarityOrderInfo.getType() },
					new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER });
			insertTokenStatus.setStatus(true);
			return insertTokenStatus;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			insertTokenStatus = new StatusInfo();
			insertTokenStatus.setErrMsg(e.getMessage());
			insertTokenStatus.setStatus(false);
			return insertTokenStatus;

		}
	}

	@Override
	public List<PolarityOrderInfo> retriveAllPolarityOrderInfoRankBy() {

		try {

			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_POLARITYORDERINFO_RANK_SQL, null,
					null);
			return jdbcTemplate.query(sql, new PolarityOrderInfoMapper());
		} catch (Exception e) {
			System.out.println("Exception" + e);
			return null;
		}
	}

	private final class PolarityOrderInfoMapper implements RowMapper<PolarityOrderInfo> {

		public PolarityOrderInfo mapRow(ResultSet rs, int arg1) throws SQLException {

			PolarityOrderInfo freqVO = new PolarityOrderInfo();

			freqVO.setMovieId(rs.getInt("MovieID"));
			freqVO.setTotalNegative(rs.getInt("TOTALNEGATIVE"));
			freqVO.setTotalNeutral(rs.getInt("TOTALNEUTRAL"));
			freqVO.setTotalPositive(rs.getInt("TOTALPOSITIVE"));
			freqVO.setType(rs.getInt("MovieTYPE"));

			return freqVO;

		}

	}

	@Override
	public List<PolarityModel> viewTotalPolarityByType(String type) {
		List<PolarityModel> polarityModelList = null;
		try {
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_TOTALPOLARITY_WHERE_FEATURETYPE_SQL,
					null, null);
			return jdbcTemplate.query(sql, new PolarityTotalModelMapper(), type);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public StatusInfo deletePOLARITYORDER() {
		StatusInfo statusInfo = null;

		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.DELETE_POLARITYORDER_SQL, null, null);
			jdbcTemplate.update(sql);
			statusInfo.setStatus(true);
			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}
	}

	@Override
	public StatusInfo deleteFromReviews() {
		StatusInfo statusInfo = null;

		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.DELETE_REVIEW_SQL, null, null);
			jdbcTemplate.update(sql);
			statusInfo.setStatus(true);
			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}
	}

	@Override
	public StatusInfo removeHashTags() {
		StatusInfo statusInfo = null;

		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.REMOVE_ALL_HASHTAGS_SQL, null, null);
			SqlParameterSource parammap = null;
			namedJdbcTemplate.update(sql, parammap);

			statusInfo.setStatus(true);
			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}
	}

	@Override
	public List<Integer> retriveUniqueMovieIdsForType(String type) {
		try {
			String sql = sqlProperties.getMessage(
					ReviewConstantsIF.SQLS.RETRIVE_UNIQUE_MOVIEIDS_FROM_SENTIMENTTOTAL_WHERE_MOVIETYPE_SQL, null, null);

			return jdbcTemplate.queryForList(sql, Integer.class, type);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public int retriveTotalPositiveRatingForAllFeaturesForMovieFromTotalPolarity(Integer movieId, Integer movieType) {
		try {
			String sql = sqlProperties.getMessage(
					ReviewConstantsIF.SQLS.RETRIVE_TOTALPOSITIVEPOLARITY_FOR_ALL_FEATURES_WHERE_MOVIEID_MOVIETYPE_SQL,
					null, null);
			return jdbcTemplate.queryForInt(sql, movieId, movieType);

		} catch (Exception e) {
			System.out.println("Exception" + e);
			return -1;
		}
	}

	@Override
	public int retriveTotalNegativeRatingForAllFeaturesForMovieFromTotalPolarity(

			Integer movieId, Integer movieType) {
		try {
			String sql = sqlProperties.getMessage(
					ReviewConstantsIF.SQLS.RETRIVE_TOTALNEGATIVEPOLARITY_FOR_ALL_FEATURES_WHERE_MOVIEID_MOVIETYPE_SQL,
					null, null);
			return jdbcTemplate.queryForInt(sql, movieId, movieType);

		} catch (Exception e) {
			System.out.println("Exception" + e);
			return -1;
		}
	}

	@Override
	public int retriveTotalNuetralRatingForAllFeaturesForMovieFromTotalPolarity(Integer movieId, Integer movieType) {
		try {
			String sql = sqlProperties.getMessage(
					ReviewConstantsIF.SQLS.RETRIVE_TOTALNEUTRALPOLARITY_FOR_ALL_FEATURES_WHERE_MOVIEID_MOVIETYPE_SQL,
					null, null);
			return jdbcTemplate.queryForInt(sql, movieId, movieType);

		} catch (Exception e) {
			System.out.println("Exception" + e);
			return -1;
		}
	}

	// INSERT INTO
	// MOVIESTATUS(MOVIEID,MOVIENAME,TOTALPOSITIVE,TOTALNEGATIVE,TOTALNEUTRAL,TOTALRATING,
	// MOVIESTATUS,MAXPOSITIVE,MAXNEGATIVE,MAXNEUTRAL,THRESHOLD)
	// VALUES(?,?,?,?,?,?,?,?,?,?,?)
	@Override
	public StatusInfo insertMovieStatus(MovieStatus movieStatus) {
		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.INSERT_MOVIESTATUS_SQL, null, null);

			jdbcTemplate.update(sql,
					new Object[] { movieStatus.getMovieId(), movieStatus.getMovieName(), movieStatus.getTotalPositive(),
							movieStatus.getTotalNegative(), movieStatus.getTotalNeutral(), movieStatus.getTotalRating(),
							movieStatus.getStatus(), movieStatus.getMaximumPositive(), movieStatus.getMaximumNegative(),
							movieStatus.getMaximumNeutral(), movieStatus.getThreshold() },
					new int[] { Types.INTEGER, Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.DOUBLE,
							Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.DOUBLE });
			statusInfo.setStatus(true);
			return statusInfo;

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
	public StatusInfo deleteMovieStatus() {
		StatusInfo statusInfo = null;

		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.DELETE_MOVIESTATUS_SQL, null, null);
			jdbcTemplate.update(sql);
			statusInfo.setStatus(true);
			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}
	}

	@Override
	public List<MovieStatus> viewClassifyMovies() {
		try {

			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_MOVIESTATUS_SQL, null, null);
			return jdbcTemplate.query(sql, new ClassifyMovieMapper());
		} catch (Exception e) {
			System.out.println("Exception" + e);
			return null;
		}
	}

	private final class ClassifyMovieMapper implements RowMapper<MovieStatus> {

		public MovieStatus mapRow(ResultSet rs, int arg1) throws SQLException {
			MovieStatus movieStatus = new MovieStatus();

			movieStatus.setMaximumNegative(rs.getInt("MAXNEGATIVE"));
			movieStatus.setMaximumNeutral(rs.getInt("MAXNEUTRAL"));
			movieStatus.setMaximumPositive(rs.getInt("MAXPOSITIVE"));
			movieStatus.setMovieId(rs.getInt("MOVIEID"));
			movieStatus.setMovieName(rs.getString("MOVIENAME"));
			movieStatus.setStatus(rs.getString("MOVIESTATUS"));
			movieStatus.setThreshold(rs.getDouble("THRESHOLD"));
			movieStatus.setTotalNegative(rs.getInt("TOTALNEGATIVE"));
			movieStatus.setTotalNeutral(rs.getInt("TOTALNEUTRAL"));
			movieStatus.setTotalPositive(rs.getInt("TOTALPOSITIVE"));
			movieStatus.setTotalRating(rs.getDouble("TOTALRATING"));

			return movieStatus;

		}
	}

	@Override
	public List<MovieStatus> viewClassifyMoviesForMovieId(int movieId) {
		try {

			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_MOVIESTATUS_FOR_MOVIEID_SQL, null,
					null);
			return jdbcTemplate.query(sql, new ClassifyMovieMapper(), movieId);
		} catch (Exception e) {
			System.out.println("Exception" + e);
			return null;
		}
	}

	@Override
	public String retriveMovieTypeNameForId(int movietype) {
		try {
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_MOVIETYPENAME_FOR_MOVIETYPE_SQL, null,
					null);
			return jdbcTemplate.queryForObject(sql, String.class, movietype);
		} catch (Exception e) {
			System.out.println("Exception" + e);
			return null;
		}
	}

	@Override
	public StatusInfo retriveAllReviewsForPaginationConfig(PaginationConfigVO paginationConfigVO) {
		StatusInfo statusInfo = new StatusInfo();

		try {

			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_REVIEWS_PAGINATED_SQL, null, null);
			List<ReviewModel> reviewModels = jdbcTemplate.query(sql, new ReviewModelVOMapper(),
					new Object[] { paginationConfigVO.getLimit(), paginationConfigVO.getStart() });

			if (null == reviewModels) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg("No Reviews Found");
				return statusInfo;

			}
			// Once it is done get the total as well
			String sql1 = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_TOTAL_REVIEW_COUNT_SQL, null, null);

			int total = jdbcTemplate.queryForInt(sql1);

			if (total <= 0) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg("No Reviews Found");
				return statusInfo;
			}

			statusInfo.setStatus(true);
			statusInfo.setModel(reviewModels);
			statusInfo.setTotal(total);
		} catch (Exception e) {
			System.out.println("Exception" + e);
			statusInfo.setStatus(false);
			statusInfo.setExceptionMsg(e.getMessage());
			return statusInfo;
		}
		return statusInfo;
	}

	@Override
	public int retriveLastReviewID() {
		try {
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_LAST_REVIEW_ID, null, null);

			Map<String, Object> paramMap = null;

			return namedJdbcTemplate.queryForInt(sql, paramMap);

		} catch (Exception e) {
			System.out.println("Exception" + e);
			return -1;
		}
	}

	@Override
	public StatusInfo insertBatchReviews(List<ReviewModel> reviewModels) {

		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();

			String sqlKey = ReviewConstantsIF.SQLS.INSERT_REVIEW_CUSTOM_ID_SQL;
			String sql = sqlProperties.getMessage(sqlKey, null, null);

			for (ReviewModel reviewModel : reviewModels) {
				jdbcTemplate.update(sql,
						new Object[] { reviewModel.getReviewId(), new SqlLobValue(reviewModel.getReviewDetails()),
								reviewModel.getMovieid(), reviewModel.getMovietype() },
						new int[] { Types.INTEGER, Types.BLOB, Types.INTEGER, Types.INTEGER });
			}

			statusInfo.setStatus(true);

			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}
	}

	@Override
	public List<String> retriveFeatureTypesForMovieType(int movieType) {
		try {
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_FEATURE_TYPES_FOR_MOVIE_TYPE_SQL, null,
					null);

			Map<String, Object> queryMap = new HashMap<String, Object>();

			queryMap.put("MOVIETYPE", movieType);

			return namedJdbcTemplate.queryForList(sql, queryMap, String.class);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	// INSERT INTO
	// SENTIMENTANALYZER(REVIEWID,POSITIVERATING,NEGATIVERATING,NEUTRALRATING,MOVIEID,MOVIETYPE,FEATURETYPE)
	// VALUES(:REVIEWID,:POSITIVERATING,:NEGATIVERATING,:NEUTRALRATING,:MOVIEID,:MOVIETYPE,:FEATURETYPE)

	@Override
	public StatusInfo insertBatchPolarity(List<PolarityModel> polarityModels) {
		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.INSERT_REVIEW_POLARITY_NAMED_SQL, null, null);

			List<Map<String, Object>> batchValues = new ArrayList(polarityModels.size());
			for (PolarityModel polarity : polarityModels) {
				batchValues.add(new MapSqlParameterSource("REVIEWID", polarity.getReviewId())
						.addValue("MOVIEID", polarity.getMovieId()).addValue("MOVIETYPE", polarity.getMovieType())
						.addValue("POSITIVERATING", polarity.getPositiveRating())
						.addValue("NEGATIVERATING", polarity.getNegativeRating())
						.addValue("NEUTRALRATING", polarity.getNeutralRating())
						.addValue("FEATURETYPE", polarity.getFeatureType()).getValues());
			}

			int[] updateCounts = namedJdbcTemplate.batchUpdate(sql,
					batchValues.toArray(new Map[polarityModels.size()]));

			if (updateCounts.length > 0) {
				statusInfo.setStatus(true);
			} else {
				statusInfo.setStatus(false);
			}

			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}
	}

	// DELETE FROM SENTIMENTTOTAL WHERE MOVIEID=:MOVIEID AND
	// MOVIETYPE=:MOVIETYPE
	@Override
	public StatusInfo deletePolarityForMovieIdAndMovieType(int movieid, int movietype) {
		StatusInfo statusInfo = null;

		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties
					.getMessage(ReviewConstantsIF.SQLS.DELETE_SENTIMENTTOTAL_WHERE_MOVIEID_MOVIETYPE_SQL, null, null);

			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("MOVIEID", movieid);
			queryMap.put("MOVIETYPE", movietype);

			namedJdbcTemplate.update(sql, queryMap);
			statusInfo.setStatus(true);
			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}
	}

	// INSERT INTO
	// SENTIMENTTOTAL(MOVIEID,POSITIVEPOLARITY,NEGATIVEPOLARITY,NEUTRALPOLARITY,MOVIETYPE,FEATURESCORE,FEATURETYPE)
	// VALUES(:MOVIEID,:POSITIVEPOLARITY,:NEGATIVEPOLARITY,:NEUTRALPOLARITY,:MOVIETYPE,:FEATURESCORE,:FEATURETYPE)

	@Override
	public StatusInfo insertMovieBasedBatchPolarity(List<PolarityModel> productBasedPolarity) {
		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.INSERT_MOVIE_POLARITY_NAMED_SQL, null, null);

			List<Map<String, Object>> batchValues = new ArrayList(productBasedPolarity.size());
			for (PolarityModel polarity : productBasedPolarity) {
				batchValues.add(new MapSqlParameterSource("MOVIEID", polarity.getMovieId())
						.addValue("MOVIETYPE", polarity.getMovieType())
						.addValue("POSITIVEPOLARITY", polarity.getPositiveRating())
						.addValue("NEGATIVEPOLARITY", polarity.getNegativeRating())
						.addValue("NEUTRALPOLARITY", polarity.getNeutralRating())
						.addValue("FEATURETYPE", polarity.getFeatureType()).getValues());
			}

			int[] updateCounts = namedJdbcTemplate.batchUpdate(sql,
					batchValues.toArray(new Map[productBasedPolarity.size()]));

			if (updateCounts.length > 0) {
				statusInfo.setStatus(true);
			} else {
				statusInfo.setStatus(false);
			}

			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}
	}

	// SELECT
	// POSITIVEPOLARITY,NEGATIVEPOLARITY,NEUTRALPOLARITY,MOVIEID,MOVIETYPE,FEATURETYPE
	// FROM SENTIMENTTOTAL
	// WHERE MOVIEID=:MOVIEID AND MOVIETYPE=:MOVIETYPE AND
	// FEATURETYPE=:FEATURETYPE

	@Override
	public PolarityModel retrivePolarityForMovieandMovieTypeAndFeatureTypeFromTotalPolarity(int movieId, int movieType,
			String feature) {
		try {

			String sql = sqlProperties.getMessage(
					ReviewConstantsIF.SQLS.RETRIVE_POLARITY_FOR_MOVIEID_MOVIE_TYPE_FEATURE_TYPE_SQL, null, null);

			Map<String, Object> paramMap = new HashMap<String, Object>();

			paramMap.put("MOVIEID", movieId);
			paramMap.put("MOVIETYPE", movieType);
			paramMap.put("FEATURETYPE", feature);

			return namedJdbcTemplate.queryForObject(sql, paramMap, new PolarityTotalModelMapper());
		} catch (Exception e) {
			System.out.println("Exception" + e);
			return null;
		}
	}

	@Override
	public List<MovieModel> retriveFVForMovieType(int type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int retriveCountForFeatureUsingLike(String featureType, Integer reviewIdTemp, int type) {
		// TODO Auto-generated method stub
		return 0;
	}

	// SELECT
	// REVIEWID,POSITIVERATING,NEGATIVERATING,NEUTRALRATING,MOVIEID,MOVIETYPE,FEATURETYPE
	// FROM SENTIMENTANALYZER limit ? offset ?
	@Override
	public StatusInfo retriveAllPolarityForPaginationConfig(PaginationConfigVO paginationConfigVO) {
		StatusInfo statusInfo = new StatusInfo();

		try {

			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_POLARITY_PAGINATED_SQL, null, null);
			List<PolarityModel> reviewModels = jdbcTemplate.query(sql, new PolarityModelMapper(),
					new Object[] { paginationConfigVO.getLimit(), paginationConfigVO.getStart() });

			if (null == reviewModels) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg("No Polarity Found Found");
				return statusInfo;

			}
			// Once it is done get the total as well
			String sql1 = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_TOTAL_POLARITY_COUNT_SQL, null, null);

			int total = jdbcTemplate.queryForInt(sql1);

			if (total <= 0) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg("No Reviews Found");
				return statusInfo;
			}

			statusInfo.setStatus(true);
			statusInfo.setModel(reviewModels);
			statusInfo.setTotal(total);
		} catch (Exception e) {
			System.out.println("Exception" + e);
			statusInfo.setStatus(false);
			statusInfo.setExceptionMsg(e.getMessage());
			return statusInfo;
		}
		return statusInfo;
	}

	// SELECT
	// MOVIEID,POSITIVEPOLARITY,NEGATIVEPOLARITY,NEUTRALPOLARITY,MOVIETYPE,FEATURESCORE,FEATURETYPE
	// FROM SENTIMENTTOTAL limit ? offset ?
	@Override
	public StatusInfo retriveTotalPolarityForPaginationConfig(PaginationConfigVO paginationConfigVO) {
		StatusInfo statusInfo = new StatusInfo();

		try {

			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_TOTALPOLARITY_PAGINATED_SQL, null,
					null);
			List<PolarityModel> totalPolarity = jdbcTemplate.query(sql, new PolarityTotalModelMapper(),
					new Object[] { paginationConfigVO.getLimit(), paginationConfigVO.getStart() });

			if (null == totalPolarity) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg("No Polarity Found ");
				return statusInfo;

			}
			// Once it is done get the total as well
			String sql1 = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_TOTAL_TOTALPOLARITY_COUNT_SQL, null,
					null);

			int total = jdbcTemplate.queryForInt(sql1);

			if (total <= 0) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg("No Reviews Found");
				return statusInfo;
			}

			statusInfo.setStatus(true);
			statusInfo.setModel(totalPolarity);
			statusInfo.setTotal(total);
		} catch (Exception e) {
			System.out.println("Exception" + e);
			statusInfo.setStatus(false);
			statusInfo.setExceptionMsg(e.getMessage());
			return statusInfo;
		}
		return statusInfo;
	}

	@Override
	public List<FeatureTypeInfo> retriveFeaturesForSpecficType(int type) {

		List<FeatureTypeInfo> featureTypes = null;
		try {

			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_FEATURETYPES_FOR_MOVIETYPE_SQL, null,
					null);

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("MOVIETYPE", type);

			return namedJdbcTemplate.query(sql, paramMap, new FeatureTypeInfoMapper());

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;

		}
	}

	private final class FeatureTypeInfoMapper implements RowMapper<FeatureTypeInfo> {

		public FeatureTypeInfo mapRow(ResultSet rs, int arg1) throws SQLException {

			FeatureTypeInfo featureTypeInfo = new FeatureTypeInfo();
			featureTypeInfo.setFeatureType(rs.getString("FEATURETYPE"));
			featureTypeInfo.setMovieType(rs.getInt("MOVIETYPE"));

			return featureTypeInfo;
		}

	}

	@Override
	public List<PolarityModel> viewTotalPolarityByMovieypeAndFeatureType(String movieType, String featureType) {
		List<PolarityModel> polarityModelList = null;
		try {
			String sql = sqlProperties.getMessage(
					ReviewConstantsIF.SQLS.RETRIVE_TOTALPOLARITY_WHERE_MOVIETYPE_AND_FEATURE_TYPE_SQL, null, null);
			return jdbcTemplate.query(sql, new PolarityTotalModelMapper(), Integer.parseInt(movieType), featureType);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public StatusInfo deleteFromBestFV(int type) {
		StatusInfo statusInfo = null;

		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.DELETE_BESTFV_WHERE_MOVIETYPE_SQL, null, null);
			jdbcTemplate.update(sql, type);
			statusInfo.setStatus(true);
			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}
	}

	@Override
	public List<String> retriveMovieNames() {
		try {
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_MOVIENAMES_SQL, null, null);

			return jdbcTemplate.queryForList(sql, String.class);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	// INSERT INTO
	// MOVIEID(MOVIENAME,MOVIETYPE_FK)VALUES(:MOVIENAME,:MOVIETYPE_FK)
	@Override
	public StatusInfo insertMovieName(String moviename) {
		StatusInfo statusInfo = new StatusInfo();
		try {
			String sqlKey = ReviewConstantsIF.SQLS.INSERT_MOVIE_SQL;
			String sql = sqlProperties.getMessage(sqlKey, null, null);

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("MOVIENAME", moviename);
			paramMap.put("MOVIETYPE_FK", 1);

			namedJdbcTemplate.update(sql, paramMap);

		} catch (Exception e) {
			System.out.println("Exception is:");
			System.out.println(e.getMessage());
			e.printStackTrace();
			statusInfo.setErrMsg(ReviewConstantsIF.Message.MESSAGE_INTERNAL);
			statusInfo.setExceptionMsg(e.getMessage());
			statusInfo.setStatus(false);
			return statusInfo;

		}
		statusInfo.setStatus(true);
		return statusInfo;
	}

	// SELECT MOVIENAME,MOVIETYPE_FK,MOVIEID_PK FROM MOVIEID limit :LIMIT offset
	// :OFFSET
	@Override
	public StatusInfo retriveAllMoviesForPagination(PaginationConfigVO paginationConfigVO) {
		StatusInfo statusInfo = new StatusInfo();

		try {

			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_MOVIES_PAGINATED_SQL, null, null);

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("LIMIT", paginationConfigVO.getLimit());
			paramMap.put("OFFSET", paginationConfigVO.getStart());

			List<MovieNameList> movienameList = namedJdbcTemplate.query(sql, paramMap, new MovieMappers());

			if (null == movienameList) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg("No Movie Names Information Found");
				return statusInfo;

			}
			// Once it is done get the total as well
			String sql1 = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_TOTAL_MOVIES_COUNT_SQL, null, null);

			int total = jdbcTemplate.queryForInt(sql1);

			if (total <= 0) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg("No Movies Count Found");
				return statusInfo;
			}

			statusInfo.setStatus(true);
			statusInfo.setModel(movienameList);
			statusInfo.setTotal(total);
		} catch (Exception e) {
			System.out.println("Exception" + e);
			statusInfo.setStatus(false);
			statusInfo.setExceptionMsg(e.getMessage());
			return statusInfo;
		}
		return statusInfo;
	}

	private final class MovieMappers implements RowMapper<MovieNameList> {

		public MovieNameList mapRow(ResultSet rs, int arg1) throws SQLException {

			MovieNameList movieNameList = new MovieNameList();
			movieNameList.setMovieId(rs.getInt("MOVIEID_PK"));
			movieNameList.setMovieName(rs.getString("MOVIENAME"));
			movieNameList.setMovieType(rs.getInt("MOVIETYPE_FK"));

			return movieNameList;

		}
	}

	// SELECT
	// POSITIVEPOLARITY,NEGATIVEPOLARITY,NEUTRALPOLARITY,MOVIEID,MOVIETYPE FROM
	// SENTIMENTTOTAL WHERE MOVIEID=:MOVIEID AND MOVIETYPE=:MOVIETYPE
	@Override
	public PolarityModel retrivePolarityForMovieandMovieTypeFromTotalPolarityWithoutFeature(int movieId,
			int movieType) {
		try {

			String sql = sqlProperties.getMessage(
					ReviewConstantsIF.SQLS.RETRIVE_POLARITY_FOR_MOVIEID_MOVIE_TYPE_WITHOUTFEATURE_SQL, null, null);

			Map<String, Object> paramMap = new HashMap<String, Object>();

			paramMap.put("MOVIEID", movieId);
			paramMap.put("MOVIETYPE", movieType);

			return namedJdbcTemplate.queryForObject(sql, paramMap, new PolarityTotalModelWithoutFTMapper());
		} catch (Exception e) {
			System.out.println("Exception" + e);
			return null;
		}
	}

	private final class PolarityTotalModelWithoutFTMapper implements RowMapper<PolarityModel> {

		public PolarityModel mapRow(ResultSet rs, int arg1) throws SQLException {
			PolarityModel polarityModel = new PolarityModel();

			polarityModel.setMovieId(rs.getInt(ReviewConstantsIF.DatabaseColumns.MOVIEID_COL));

			polarityModel.setPositiveRating(rs.getInt(ReviewConstantsIF.DatabaseColumns.POSITIVEPOLARITY_COL));
			polarityModel.setNegativeRating(rs.getInt(ReviewConstantsIF.DatabaseColumns.NEGATIVEPOLARITY_COL));
			polarityModel.setNeutralRating(rs.getInt(ReviewConstantsIF.DatabaseColumns.NEUTRALPOLARITY_COL));

			polarityModel.setMovieType(rs.getInt(ReviewConstantsIF.DatabaseColumns.MOVIE_TYPE_COL));

			return polarityModel;
		}
	}

	//INSERT INTO SENTIMENTANALYZERWITHOUTFEATURE(REVIEWID,POSITIVERATING,NEGATIVERATING,NEUTRALRATING,MOVIEID,MOVIETYPE)
	//VALUES(:REVIEWID,:POSITIVERATING,:NEGATIVERATING,:NEUTRALRATING,:MOVIEID,:MOVIETYPE)

	@Override
	public StatusInfo insertBatchPolarityReviewBasedWithoutFeature(List<PolarityModel> reviewBasedPolarity) {
		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.INSERT_REVIEW_POLARITY_WITHOUTFEATURE_NAMED_SQL, null, null);

			List<Map<String, Object>> batchValues = new ArrayList(reviewBasedPolarity.size());
			for (PolarityModel polarity : reviewBasedPolarity) {
				batchValues.add(new MapSqlParameterSource("REVIEWID", polarity.getReviewId())
						.addValue("MOVIEID", polarity.getMovieId())
						.addValue("MOVIETYPE", polarity.getMovieType())
						.addValue("POSITIVERATING", polarity.getPositiveRating())
						.addValue("NEGATIVERATING", polarity.getNegativeRating())
						.addValue("NEUTRALRATING", polarity.getNeutralRating())
						.getValues());
			}

			int[] updateCounts = namedJdbcTemplate.batchUpdate(sql,
					batchValues.toArray(new Map[reviewBasedPolarity.size()]));

			if (updateCounts.length > 0) {
				statusInfo.setStatus(true);
			} else {
				statusInfo.setStatus(false);
			}

			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}
	}
	
	
	//INSERT INTO SENTIMENTTOTALWITHOUTFEATURE(MOVIEID,POSITIVEPOLARITY,NEGATIVEPOLARITY,NEUTRALPOLARITY,MOVIETYPE)
	//VALUES(:MOVIEID,:POSITIVEPOLARITY,:NEGATIVEPOLARITY,:NEUTRALPOLARITY,:MOVIETYPE)


	@Override
	public StatusInfo insertMovieBasedBatchPolarityWithoutFeature(List<PolarityModel> movieBasedPolarity) {
		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.INSERT_MOVIE_POLARITY_WITHOUTFEATURE_NAMED_SQL, null, null);

			List<Map<String, Object>> batchValues = new ArrayList(movieBasedPolarity.size());
			for (PolarityModel polarity : movieBasedPolarity) {
				batchValues.add(new MapSqlParameterSource("MOVIEID", polarity.getMovieId())
						.addValue("MOVIETYPE", polarity.getMovieType())
						.addValue("POSITIVEPOLARITY", polarity.getPositiveRating())
						.addValue("NEGATIVEPOLARITY", polarity.getNegativeRating())
						.addValue("NEUTRALPOLARITY", polarity.getNeutralRating())
						.getValues());
			}

			int[] updateCounts = namedJdbcTemplate.batchUpdate(sql,
					batchValues.toArray(new Map[movieBasedPolarity.size()]));

			if (updateCounts.length > 0) {
				statusInfo.setStatus(true);
			} else {
				statusInfo.setStatus(false);
			}

			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}
	}

	@Override
	public StatusInfo deletePolarityForMovieIdAndMovieTypeWithoutFeature(int movieid, int movietype) {
		StatusInfo statusInfo = null;

		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties
					.getMessage(ReviewConstantsIF.SQLS.DELETE_SENTIMENTTOTALWITHOUTFEATURE_WHERE_MOVIEID_MOVIETYPE_SQL, null, null);

			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("MOVIEID", movieid);
			queryMap.put("MOVIETYPE", movietype);

			namedJdbcTemplate.update(sql, queryMap);
			statusInfo.setStatus(true);
			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}
	}

	@Override
	public StatusInfo retriveAllPolarityWithoutFeatureForPaginationConfig(PaginationConfigVO paginationConfigVO) {
		StatusInfo statusInfo = new StatusInfo();

		try {

			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_POLARITY_WITHOUTFEATURE_PAGINATED_SQL, null, null);
			List<PolarityModel> reviewModels = jdbcTemplate.query(sql, new PolarityModelWithoutFTMapper(),
					new Object[] { paginationConfigVO.getLimit(), paginationConfigVO.getStart() });

			if (null == reviewModels) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg("No Polarity Found Found");
				return statusInfo;

			}
			// Once it is done get the total as well
			String sql1 = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_TOTAL_POLARITY_COUNT_WITHOUTFEATURE_SQL, null, null);

			int total = jdbcTemplate.queryForInt(sql1);

			if (total <= 0) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg("No Reviews Found");
				return statusInfo;
			}

			statusInfo.setStatus(true);
			statusInfo.setModel(reviewModels);
			statusInfo.setTotal(total);
		} catch (Exception e) {
			System.out.println("Exception" + e);
			statusInfo.setStatus(false);
			statusInfo.setExceptionMsg(e.getMessage());
			return statusInfo;
		}
		return statusInfo;
	}
	
	private final class PolarityModelWithoutFTMapper implements RowMapper<PolarityModel> {

		public PolarityModel mapRow(ResultSet rs, int arg1) throws SQLException {
			PolarityModel polarityModel = new PolarityModel();
			
			polarityModel.setReviewId(rs.getInt(ReviewConstantsIF.DatabaseColumns.REVIEWID_COL));

			polarityModel.setMovieId(rs.getInt(ReviewConstantsIF.DatabaseColumns.MOVIEID_COL));

			polarityModel.setPositiveRating(rs.getInt(ReviewConstantsIF.DatabaseColumns.POSITIVERATING_COL));
			polarityModel.setNegativeRating(rs.getInt(ReviewConstantsIF.DatabaseColumns.NEGATIVERATING_COL));
			polarityModel.setNeutralRating(rs.getInt(ReviewConstantsIF.DatabaseColumns.NEUTRALRATING_COL));

			polarityModel.setMovieType(rs.getInt(ReviewConstantsIF.DatabaseColumns.MOVIE_TYPE_COL));

			return polarityModel;
		}
	}

	@Override
	public StatusInfo retriveAllTotalPolarityWithoutFeatureForPaginationConfig(PaginationConfigVO paginationConfigVO) {
		StatusInfo statusInfo = new StatusInfo();

		try {

			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_ALL_TOTALPOLARITY_WITHOUTFEATURE_PAGINATED_SQL, null, null);
			List<PolarityModel> reviewModels = jdbcTemplate.query(sql, new PolarityTotalModelWithoutFTMapper(),
					new Object[] { paginationConfigVO.getLimit(), paginationConfigVO.getStart() });

			if (null == reviewModels) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg("No Polarity Found Found");
				return statusInfo;

			}
			// Once it is done get the total as well
			String sql1 = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_MOVIE_TOTAL_POLARITY_COUNT_WITHOUTFEATURE_SQL, null, null);

			int total = jdbcTemplate.queryForInt(sql1);

			if (total <= 0) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg("No Reviews Found");
				return statusInfo;
			}

			statusInfo.setStatus(true);
			statusInfo.setModel(reviewModels);
			statusInfo.setTotal(total);
		} catch (Exception e) {
			System.out.println("Exception" + e);
			statusInfo.setStatus(false);
			statusInfo.setExceptionMsg(e.getMessage());
			return statusInfo;
		}
		return statusInfo;
	}

	@Override
	public List<Integer> retriveDistinctMovieIdsFromNoFeatureTotalPolarity(int type) {
		try {
			String sql = sqlProperties.getMessage(ReviewConstantsIF.SQLS.RETRIVE_UNIQUE_MOVIEIDS_FROM_TOTALPOLARITYWITHOUTFEATURE_SQL, null,
					null);

			return jdbcTemplate.queryForList(sql, Integer.class);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	//SELECT POSITIVEPOLARITY FROM SENTIMENTTOTALWITHOUTFEATURE WHERE MOVIEID=? AND MOVIETYPE=?
	@Override
	public int retriveTotalPositiveRatingForMovieAndMovieTypeWithoutFeature(Integer movieIdTemp, int type) {
		try {
			String sql = sqlProperties.getMessage(
					ReviewConstantsIF.SQLS.RETRIVE_TOTALPOSITIVEPOLARITY_WHERE_MOVIEID_MOVIEYPE_WITHOUTFEATURE_SQL, null,
					null);
			return jdbcTemplate.queryForInt(sql, movieIdTemp, type);

		} catch (Exception e) {
			System.out.println("Exception" + e);
			return 0;
		}
	}

	@Override
	public int retriveTotalNegativeRatingForMovieAndMovieTypeWithoutFeature(Integer movieIdTemp, int type) {
		try {
			String sql = sqlProperties.getMessage(
					ReviewConstantsIF.SQLS.RETRIVE_TOTALNEGATIVEPOLARITY_WHERE_MOVIEID_MOVIEYPE_WITHOUTFEATURE_SQL, null,
					null);
			return jdbcTemplate.queryForInt(sql, movieIdTemp, type);

		} catch (Exception e) {
			System.out.println("Exception" + e);
			return 0;
		}
	}

	@Override
	public int retriveTotalNeutralRatingForMovieAndMovieTypeWithoutFeature(Integer movieIdTemp, int type) {
		try {
			String sql = sqlProperties.getMessage(
					ReviewConstantsIF.SQLS.RETRIVE_TOTALNEUTRALPOLARITY_WHERE_MOVIEID_MOVIEYPE_WITHOUTFEATURE_SQL, null,
					null);
			return jdbcTemplate.queryForInt(sql, movieIdTemp, type);

		} catch (Exception e) {
			System.out.println("Exception" + e);
			return 0;
		}
	}

}
