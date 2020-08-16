<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@page import="java.util.List"%>
<%@page import="com.model.AJAXResponse,java.util.List,com.model.Message"%>
<%@page import="com.constants.ReviewConstantsIF"%>

<html>
<head>
<meta charset="utf-8" />
<title>Rank Mobile</title>

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
		AJAXResponse ajaxResponse = (AJAXResponse) request.getAttribute(ReviewConstantsIF.Keys.OBJ);
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

	<div class="errMsg">
		<%=tempMsg.getMsg()%>
	</div>

	<%
		}

			}
		}
	%>


	<div class="container">
		<div class="panel panel-primary">
			<div class="panel-heading">Recommendations</div>
			<div class="panel-body">
				<form
					action="<%=request.getContextPath()%>/review/rankCorrelation.do"
					method="post">

					<div class="row">

						<div class="col-xs-12">

							<div class="form-group">
								<label>Recommended Movies:</label> <input name="type" id="type"
									type="hidden" value="1" />
							</div>
							
							<div class="form-group">
							
							<label>Direction </label>
								<input type="checkbox" name="direction">
							</div>
							
							
							
							<div class="form-group">
							
							<label>Actor </label>
								<input type="checkbox" name="actor">
							</div>
							
							<div class="form-group">
							
							<label>Actress</label>
								<input type="checkbox" name="actress">
							</div>
							
							<div class="form-group">
							
							<label>Songs</label>
								<input type="checkbox" name="songs">
							</div>
							
							<div class="form-group">
							
							<label>Production</label>
								<input type="checkbox" name="production">
							</div>
							
							<div class="form-group">
							
							<label>Dialogues</label>
								<input type="checkbox" name="dialogues">
							</div>
							
						
							<div class="form-group">
								<button class="btn btn-primary">Search</button>
							</div>

						</div>

					</div>



				</form>
			</div>
		</div>
	</div>
</body>
</html>