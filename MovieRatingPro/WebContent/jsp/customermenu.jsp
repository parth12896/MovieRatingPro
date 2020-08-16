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
				href="<%=request.getContextPath()%>/jsp/customerhome.jsp"><span>Home</span></a></li>

			<li class='active '><a
				href="<%=request.getContextPath()%>/jsp/rankCorrelationMovie.jsp"><span>Rank
						Correlation Movie</span></a></li>
			<li class='has-sub '><a
				href="<%=request.getContextPath()%>/jsp/viewtotalpolaritygraphcustomer.jsp"><span>Recommendations
						Graph With Feature</span></a>
						
			<li class='has-sub '><a
				href="<%=request.getContextPath()%>/jsp/viewtotalpolaritygraphwithoutfeaturecustomer.jsp"><span>Recommendations
						Graph Without Feature</span></a>
						
						
			<li class='has-sub '><a
				href='<%=request.getContextPath()%>/review/logout.do'>Logout<span></span></a></li>


		</ul>
	</div>

</body>
</html>