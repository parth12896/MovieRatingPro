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
				href="<%=request.getContextPath()%>/jsp/welcome.jsp"><span>Home</span></a></li>
			<li class='has-sub '><a href='#'>Web Crawler<span></span></a>
				<ul>
				
					<li><a
						href='<%=request.getContextPath()%>/jsp/onlinereview.jsp'><span>
								Online Review Collector </span></a></li>
									<li><a
						href='<%=request.getContextPath()%>/jsp/viewreviews.jsp'><span>
								View Reviews</span></a></li>

				</ul>
			</li>
		</ul>
	</div>

</body>
</html>