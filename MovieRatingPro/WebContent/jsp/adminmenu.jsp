<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/css/styles.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id='cssmenu'>
		<ul>
			<li class='active '><a
				href="<%=request.getContextPath()%>/jsp/admin.jsp"><span>Home</span></a></li>
				
				
			<li class='has-sub '><a href='#'>Movier<span></span></a>
				<ul>
				
					<li><a
						href='<%=request.getContextPath()%>/jsp/addmovie.jsp'><span>
								Add Movie </span></a></li>
									<li><a
						href='<%=request.getContextPath()%>/jsp/viewmovies.jsp'><span>
								View Movies</span></a></li>

				</ul>
			</li>	
				
				
			<li class='has-sub '><a href='#'>Web Crawler<span></span></a>
				<ul>
				
					<li><a
						href='<%=request.getContextPath()%>/jsp/webcrawlercollection.jsp'><span>
								Execute Web Crawler </span></a></li>
									<li><a
						href='<%=request.getContextPath()%>/jsp/viewreviews.jsp'><span>
								View Reviews</span></a></li>

				</ul>
			</li>
			
			
			<li class='has-sub '><a href='#'>Review RNN Algorithm<span></span></a>
				<ul>
					<li class='active '><a
						href="<%=request.getContextPath()%>/jsp/viewpolarity.jsp"><span>View
								Review Polarity</span></a></li>
					<li class='active '><a
						href="<%=request.getContextPath()%>/jsp/viewtotalpolarity.jsp"><span>Total
								Polarity</span></a></li>
				</ul></li>


				<li class='has-sub '><a href='#'>Review RNN Without Feature Algorithm<span></span></a>
				<ul>
					<li class='active '><a
						href="<%=request.getContextPath()%>/jsp/viewpolaritywithoutfeature.jsp"><span>View
								Review Polarity</span></a></li>
					<li class='active '><a
						href="<%=request.getContextPath()%>/jsp/viewtotalpolaritywithoutfeature.jsp"><span>Total
								Polarity</span></a></li>
				</ul></li>


			<li class='has-sub '><a href='#'><span>Polarity Graph</span></a>
				<ul>
					<li class='active '><a
						href="<%=request.getContextPath()%>/jsp/viewtotalpolaritygraph.jsp"><span>Total
								Polarity</span></a></li>
					<li class='active '><a
						href="<%=request.getContextPath()%>/jsp/viewtotalpolaritygraphwithoutfeature.jsp"><span>Total
								Polarity With Feature</span></a></li>
				</ul>
			</li>
			
			
			<li class='has-sub '><a
				href='<%=request.getContextPath()%>/review/logout.do'>Logout<span></span></a></li>
		</ul>
	</div>

</body>
</html>