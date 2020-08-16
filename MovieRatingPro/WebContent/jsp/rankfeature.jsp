<%@page import="com.model.MovieRankInfo"%>
<%@page import="com.model.PolarityOrderInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@page import="java.util.List"%>

<%@page import="com.constants.ReviewConstantsIF"%>
<%@page import="com.model.AJAXResponse"%>
<%@page import="com.model.Message"%>
<%@page import="com.model.ProductModel"%>
<%@page import="com.model.ReviewCorrelationVO"%>




<%@page import="com.model.FeatureVO"%>
<%@page import="com.model.ProductRankInfo"%><html>
<head>
<link rel="stylesheet"
	href="https://kendo.cdn.telerik.com/2017.2.621/styles/kendo.common.min.css" />
<link rel="stylesheet"
	href="https://kendo.cdn.telerik.com/2017.2.621/styles/kendo.rtl.min.css" />
<link rel="stylesheet"
	href="https://kendo.cdn.telerik.com/2017.2.621/styles/kendo.silver.min.css" />
<link rel="stylesheet"
	href="https://kendo.cdn.telerik.com/2017.2.621/styles/kendo.mobile.all.min.css" />

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://kendo.cdn.telerik.com/2017.2.621/js/angular.min.js"></script>
<script
	src="https://kendo.cdn.telerik.com/2017.2.621/js/kendo.all.min.js"></script>

<script>
	/*
	    This demo renders the grid in "DejaVu Sans" font family, which is
	    declared in kendo.common.css. It also declares the paths to the
	    fonts below using <tt>kendo.pdf.defineFont</tt>, because the
	    stylesheet is hosted on a different domain.
	 */
	kendo.pdf
			.defineFont({
				"DejaVu Sans" : "https://kendo.cdn.telerik.com/2016.2.607/styles/fonts/DejaVu/DejaVuSans.ttf",
				"DejaVu Sans|Bold" : "https://kendo.cdn.telerik.com/2016.2.607/styles/fonts/DejaVu/DejaVuSans-Bold.ttf",
				"DejaVu Sans|Bold|Italic" : "https://kendo.cdn.telerik.com/2016.2.607/styles/fonts/DejaVu/DejaVuSans-Oblique.ttf",
				"DejaVu Sans|Italic" : "https://kendo.cdn.telerik.com/2016.2.607/styles/fonts/DejaVu/DejaVuSans-Oblique.ttf",
				"WebComponentsIcons" : "https://kendo.cdn.telerik.com/2017.1.223/styles/fonts/glyphs/WebComponentsIcons.ttf"
			});
</script>
<!-- Load Pako ZLIB library to enable PDF compression -->
<script
	src="https://kendo.cdn.telerik.com/2017.3.1026/js/pako_deflate.min.js"></script>

</head>
<body>
	<jsp:include page="/jsp/customermenu.jsp"></jsp:include>


<%
	AJAXResponse ajaxResponse = (AJAXResponse) request
			.getAttribute(ReviewConstantsIF.Keys.OBJ);
	if (null == ajaxResponse) {

	} else {
		boolean status = ajaxResponse.isStatus();
%>
<%
	if (!status) {
			List<Message> msg = ajaxResponse.getEbErrors();
%>
<%
	for (int i = 0; i < msg.size(); i++) {
				Message tempMsg = msg.get(i);
%>

<div class="errMsg"><%=tempMsg.getMsg()%></div>

<%
	}

		}
	}

	MovieRankInfo movierankinfo = (MovieRankInfo) ajaxResponse
			.getModel();

	if (null == movierankinfo) {
%>
<font color="red">Ranking of Movies Could not be Done
at this point of Time</font>
<%
	} else {
		
		List<PolarityOrderInfo> rankedList=movierankinfo.getPolarityOrderInfos();
		
		if(null==rankedList || rankedList.isEmpty() || rankedList.size()==0)
		{
%>

<div class="errMsg">Ranked Movies Could not be Found at this point of Time</div>

<%
		}
		else
		{
		
%>

<div class="container">

<div class="panel panel-primary">

<div class="panel-heading">Recommendations for End User</div>

<div class="panel-body">

<div class="row">
<div class="col-xs-1"></div>
<div class="col-xs-4"><strong>Movie Name</strong></div>
<div class="col-xs-2"><strong>Total Positive</strong></div>
<div class="col-xs-2"><strong>Total Negative</strong></div>
<div class="col-xs-2"><strong>Total Neutral</strong></div>
<div class="col-xs-1"></div>

</div>
	<%
		for (PolarityOrderInfo movierankinfotemp : rankedList) {
	%>
	<div class="row">
	<div class="col-xs-1"></div>
		<div class="col-xs-4">
			<%=movierankinfotemp.getMovieName()%>
		</div>
	
		<div class="col-xs-2">
			<%=movierankinfotemp.getTotalPositive()%>
		</div>
		
		
		<div class="col-xs-2">
			<%=movierankinfotemp.getTotalNegative()%>
		</div>
		
		<div class="col-xs-2">
			<%=movierankinfotemp.getTotalNeutral()%>
		</div>
		<div class="col-xs-1"></div>
	
			
	</div>

	<%
		}
	%>
</div>
</div>
</div>
<%
	}
		
	}
	
%>





</body>
</html>