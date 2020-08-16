Ext.require( [ 'Ext.grid.*', 'Ext.data.*', 'Ext.form.*',
		'Ext.layout.container.Column', 'Ext.tab.Panel' ]);

Ext.Loader.setConfig( {
	enabled : true
});

var hideConfirmationMsg;
var showConfirmationMsg;
var loadMask;
var contentPanel;
/* Hide the Confirmation Message */
hideConfirmationMsg = function() {
	var confMsgDiv = Ext.get('confirmationMessage');
	confMsgDiv.dom.innerHTML = "";
	confMsgDiv.dom.style.display = 'none';
}
/* Show Confirmation Message */
showConfirmationMsg = function(msg) {
	var confMsgDiv = Ext.get('confirmationMessage');
	confMsgDiv.dom.innerHTML = msg;
	confMsgDiv.dom.style.display = 'inline-block';
}
function generateJSONRequestForAddingReview()
{
	var reviewGen={};
	
	var obj=Ext.getCmp('catid');
	
	var movieid=obj.getValue();
	if(movieid!=null)
	{
		reviewGen.movieid=movieid;
	}
	var movietype=1;
	if(movietype!=null)
	{
		reviewGen.movietype=movietype;
	}
	
	var webUrl=Ext.getCmp('webUrl').getValue();
	if(webUrl!=null)
	{
		reviewGen.webUrl=webUrl;
	}
	
	var xpath=Ext.getCmp('xpath').getValue();
	if(xpath!=null){
		reviewGen.xpath=xpath;
	}
	
	return reviewGen;
}


function doReviewGenerationRequest(reviewGen, urlLink)
{
loadMask = new Ext.LoadMask(Ext.getBody(), {msg:"Loading"});
loadMask.show();
Ext.Ajax.request({	
method: 'POST',
processData: false,
contentType:'application/json',
jsonData: Ext.encode(reviewGen),
url:urlLink, 
success: function(response) {
var data;
if (response){
			 
			var JsonData = Ext.decode(response.responseText);
				if(JsonData.ebErrors != null){
					var errorObj=JsonData.ebErrors;
					for(i=0;i<errorObj.length;i++)
					{
							var value=errorObj[i].msg;
							showConfirmationMsg(value);
					}
					loadMask.hide();
				}
				else
				{
					var value=JsonData.message;
					showConfirmationMsg(value);
					contentPanel.hide();
					loadMask.hide();
				}
			}
},
failure : function(data) {
loadMask.hide();
}
});
}



Ext
		.onReady(function() {
			
			
			
			Ext.define('movietypeModel', {
				extend : 'Ext.data.Model',
				fields : [ 
				           {name:'movietypeId', mapping:'movietypeId',type:'int'},
				           {name:'movieName', mapping:'movieName',type:'string'}
						 ],
				idProperty: 'movietypeId'
			});
			
			
			var movietypeStore = Ext.create('Ext.data.Store', {
				id : 'movietypeStoreId',
				name : 'movietypeStoreName',
				model : 'movietypeModel',
				proxy : {
					type : 'ajax',
					url :contextPath+'/review/retriveAllMovieTypes.do',
					actionMethods:{
						read:'POST'
					},
					reader : {
						type :'json'
					}
				},
				listeners:
				{
					'load':function(store, records){
				}			
				},
				autoLoad : true
			});
			movietypeStore.load();
			
			    
			Ext.define('movieModel', {
				extend : 'Ext.data.Model',
				fields : [ 
				           {name:'movieid', mapping:'movieId',type:'int'},
				           {name:'movieName', mapping:'movieName',type:'string'}
						 ],
				idProperty: 'movieid'
			});
			
			
			var genericStore = Ext.create('Ext.data.Store', {
				id : 'genericStoreId',
				name : 'genericStoreName',
				model : 'movieModel',
				proxy : {
					type : 'ajax',
					url :contextPath+'/review/retriveAllMoviesForMovieType.do',
					extraParams:{
					 	type:1
						},
					actionMethods:{
						read:'POST'
					},
					reader : {
						type :'json'
					}
					
				},
				listeners:
				{
					'load':function(store, records){
				}			
				},
				autoLoad : true
			});
			genericStore.load();
			    
			 
			 
			 
			 
			

			contentPanel = Ext
					.create(
							'Ext.form.Panel',
							{
								title : 'Automated Web Crawler',
								collapsible:true,
								width : 1000,
								height : 300,
								autoScroll : true,
								renderTo:'container',
								defaults : {
									padding : '15 100 15 100',
									labelAlign : 'top'
								},
								layout : {
									type : 'table',
									columns : 2
								},
								items : [
										
										{
											xtype : 'textfield',
											fieldLabel : 'Enter the Web Site Url',
											id : 'webUrl',
											name : 'webUrl',
											labelAlign : 'top',
											width : 250,
											allowBlank:false,
											blankText:'Please enter the Web Site Url'
											},{
												xtype : 'textfield',
												fieldLabel : 'Enter the Xpath for Review',
												id : 'xpath',
												name : 'xpath',
												labelAlign : 'top',
												width : 250,
												allowBlank:false,
												blankText:'Please enter the Xpath',
												value:'div#feature-bullets  span.a-list-item'
												},
																				{
											xtype : 'combo',
											labelAlign : 'top',
											width : 150,
											fieldLabel : 'Movie Name',
											id : 'catid',
											name : 'catid',
											queryMode : 'local',
											editable : false,
											displayField : 'movieName',
											valueField : 'movieid',
											triggerAction : 'all',
											store :genericStore,
											listeners : {	
												'select' : function(combo,records) {
											
													
													}
												}
										},
										{
											xtype : 'button',
											text : 'Store Review',
											id : 'Save',
											disabled : false,
											handler : function(store, btn, args) {

												var reviewGenFormat = generateJSONRequestForAddingReview();
												urlLink = contextPath + '/review/storereviewForUrl.do';
												hideConfirmationMsg();
												doReviewGenerationRequest(reviewGenFormat,urlLink);
											}
										
										} ]
							});
							

			function sendmovietypeAndGetMovieInfo(selValue)
			{
				var store=Ext.getCmp('catid').getStore();
				store.load(
					{
						url :contextPath+'/review/retriveAllMoviesForMovietype.do',
						params:{
									type:selValue
								}
					}
				);
			}
	
		});
		
		