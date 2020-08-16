    angular.module("app", ["kendo.directives"]).controller("addMovieController", function($scope,$http) {
    	
    	$scope.showInfoMessage =false;
    
    	$scope.showSucessMessage=false;
    	
    	$scope.showFailureMessage=false;
    	
    
     
    	
    	$scope.performMovie=function(){
    		
    		let movieName =$scope.movieName;
    		
    		if(!$scope.movieName){
    			$scope.showFailureMessage=true;
    			$scope.failureMsg="Please Provide Movie Name";
    			return;
    		}
    		
    		$scope.showInfoMessage =true;
    		var obj={};
    		obj.moviename=movieName;
    		
    		
    		
    		
    		$scope.failureMsg='';
    		$scope.sucessMsg='';
    		$scope.showSucessMessage=false;
    		$scope.showFailureMessage=false;
    		
    		$http({
    		    method: 'POST',
    		    url: contextPath+'/review/saveMovie.do',
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