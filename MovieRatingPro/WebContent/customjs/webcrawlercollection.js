    angular.module("app", ["kendo.directives"]).controller("webCrawlerController", function($scope,$http) {
    	
    	$scope.showInfoMessage =false;
    
    	$scope.showSucessMessage=false;
    	
    	$scope.showFailureMessage=false;
    	
    	$http.get(contextPath+'/review/retriveAllMovies.do')
        .then(function(response) {
            $scope.movieNames = response.data;
        	$scope.movieNameDataSource={
        		 data:  $scope.movieNames,
                 schema: {
                	 id: "movieId",
                     model: {
                         fields: {
                        	 movieId: { type: "number" },
                        	 movieName: { type: "string" },
                         }
                     }
                 }
        	};
            
        });
    	
    	$scope.performCrawler=function(){
    		
    		
    		 var dropdownlist = $("#movieName").data("kendoDropDownList");
    		 
    		let value= $("#movieName").val();
    		
    		console.log('Value');
    		console.log(value);
    		
    		$scope.showInfoMessage =true;
    		var obj={};
    		obj.movieid=value;
    		obj.movietype=1;
    		obj.xpath=$scope.xpath;
    		obj.webUrl=$scope.webUrl;
    		
    		
    		console.log('obj');
    		console.log(obj);
    		
    		$scope.failureMsg='';
    		$scope.sucessMsg='';
    		$scope.showSucessMessage=false;
    		$scope.showFailureMessage=false;
    		
    		$http({
    		    method: 'POST',
    		    url: contextPath+'/review/storereviewForUrl.do',
    		    data: obj,
    		    headers: {'Content-Type': 'application/json'}
    		}).then(function(response){
    			console.log('Response');
    			$scope.showInfoMessage =false;
    			console.log(response);
    			var JsonData = response.data;
    			
    			if (JsonData.ebErrors != null) {
					var errorObj = JsonData.ebErrors;
					for (i = 0; i < errorObj.length; i++) {
						var value = errorObj[i].msg;
						$scope.showFailureMessage=true;	
						$scope.failureMsg=value;
					}
					
				} else {
					$scope.showInfoMessage =false;
					var value = JsonData.message;
					$scope.showSucessMessage=true;
					$scope.sucessMsg=value;
				}
    			
    		});
    		
    	};
    	

    	
    	
    	
    	
	
		
    });