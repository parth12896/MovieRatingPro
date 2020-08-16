<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" >
var contextPath='<%=request.getContextPath()%>';
</script>
    <meta charset="utf-8"/>
    <title>Data Collection </title>

    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.2.621/styles/kendo.common.min.css"/>
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.2.621/styles/kendo.rtl.min.css"/>
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.2.621/styles/kendo.silver.min.css"/>
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.2.621/styles/kendo.mobile.all.min.css"/>

   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://kendo.cdn.telerik.com/2017.2.621/js/angular.min.js"></script>
    <script src="https://kendo.cdn.telerik.com/2017.2.621/js/kendo.all.min.js"></script>
	
	 <script>
        /*
            This demo renders the grid in "DejaVu Sans" font family, which is
            declared in kendo.common.css. It also declares the paths to the
            fonts below using <tt>kendo.pdf.defineFont</tt>, because the
            stylesheet is hosted on a different domain.
        */
        kendo.pdf.defineFont({
            "DejaVu Sans"             : "https://kendo.cdn.telerik.com/2016.2.607/styles/fonts/DejaVu/DejaVuSans.ttf",
            "DejaVu Sans|Bold"        : "https://kendo.cdn.telerik.com/2016.2.607/styles/fonts/DejaVu/DejaVuSans-Bold.ttf",
            "DejaVu Sans|Bold|Italic" : "https://kendo.cdn.telerik.com/2016.2.607/styles/fonts/DejaVu/DejaVuSans-Oblique.ttf",
            "DejaVu Sans|Italic"      : "https://kendo.cdn.telerik.com/2016.2.607/styles/fonts/DejaVu/DejaVuSans-Oblique.ttf",
            "WebComponentsIcons"      : "https://kendo.cdn.telerik.com/2017.1.223/styles/fonts/glyphs/WebComponentsIcons.ttf"
        });
    </script>
	<!-- Load Pako ZLIB library to enable PDF compression -->
    <script src="https://kendo.cdn.telerik.com/2017.3.1026/js/pako_deflate.min.js"></script>
 
 <script src="<%=request.getContextPath()%>/customjs/webcrawlercollection.js"></script>
</head>
<body>
  <jsp:include page="/jsp/adminmenu.jsp"></jsp:include>
  
  
  
  <div class="container">
  
  <div class="col-xs-12">
    <div ng-app="app" ng-controller="webCrawlerController">
    
    	<div class="alert alert-info">
  		<strong>Info!</strong> Web Crawler Controller
		</div>
		
		
		
        
    <div class="panel panel-primary">
        
    <div class="panel-heading">Obtain Data using Crawler</div>
	<div class="panel-body">
        
        <div class="col-xs-12">
        
        <div ng-show="showInfoMessage">
        	<div class="alert alert-warning">
        		Please Wait Until Response Comes
        	</div>
        
        </div>
        
        <div ng-show="showSucessMessage">
		
			<div class="alert alert-success">
					{{sucessMsg}}
  			</div>
		
  		</div>
  		
  		<div ng-show="showFailureMessage">
		
			<div class="alert alert-danger">
				{{failureMsg}}
  			</div>
		
  		</div>
        
        </div>
        
        <div class="col-xs-12">
        

      
        
        <div class="col-xs-6">
        	<div class="form-group">
        		<label for="movieName">Movie Name:</label>
        		<select kendo-drop-down-list="movieName" id="movieName"
            	k-data-text-field="'movieName'"
            	k-data-value-field="'movieId'"
            	k-data-source="movieNameDataSource"
            	ng-model="movieId"
            	ng-disabled="showInfoMessage"
            	class="form-control"></select>
        	</div>
        </div>
        
        </div>
        
        <div class="col-xs-12">
        
        <div class="col-xs-6">
        	
        	<div class="form-group">
        		<label for="xpath">XPath:</label>
        	    <input type="text" ng-model="xpath" 
        	    	ng-disabled="showInfoMessage"
        	    class="form-control" id="xpath">
        	</div>
        
        </div>
        <div class="col-xs-6">
        	
        	  	<div class="form-group">
        		<label for="xpath">URL:</label>
        	    <input type="text" ng-model="webUrl" 
        	    ng-disabled="showInfoMessage"
        	    class="form-control" id="webUrl">
        	</div>
 
        
        </div>
        
        </div>
        
        
        
        <div class="col-xs-12">
        
        	<div class="col-xs-6">
        	<div class="form-group">
   			 <div class="col-sm-offset-6 col-sm-6">
      				<button type="submit" ng-disabled="showInfoMessage" class="btn btn-primary" ng-click="performCrawler()">Perform Web Crawler</button>
    		</div>
  			</div>
        	</div>
        	
        	</div>
        	
        	
        	<div class="col-xs-6">
        	</div>
        
        </div>
        
		
		
    </div>
    </div>
    </div>
    </div>
   
</body>
</html>