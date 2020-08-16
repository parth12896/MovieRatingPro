<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Energy Panel</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/extjs41/resources/css/ext-all.css" />
<script type="text/javascript" >
var contextPath='<%=request.getContextPath()%>';
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/extjs41/bootstrap.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/viewtotalpolaritygraph.js"></script>

<style>
#content {
    width: 500px;
    padding: 10px;
    float: left;
    margin-left: 50px;
}
#negativecontent {
   width: 500px;
    padding: 10px;
    float: left;
    margin-left: 50px;
}

#neutralcontent{
	 width: 500px;
	 margin:100px;
	 padding:250px;

}
</style>

</head>
<body>
<jsp:include page="/jsp/customermenu.jsp"></jsp:include>



<div id="confirmationMessage" color="black" background="yellow" ></div>
<div id="keyContainer"></div>


<div id="reviewGridContainer"></div>

<div id="habitatContainer"></div>


<div id="content">
</div>

<div id="negativecontent"></div>

<div id="neutralcontent">
</div>

</body>
</html>