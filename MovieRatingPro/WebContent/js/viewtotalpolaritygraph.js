Ext.require(['Ext.grid.*', 'Ext.data.*', 'Ext.form.*', 'Ext.layout.container.Column', 'Ext.tab.Panel']);
Ext.Loader.setConfig({
    enabled: true
});
Ext.tip.QuickTipManager.init();

var productColumns = [ {
	header : 'Movie ID',
	dataIndex : 'movieId',
	sortable : true,
	width : 80
}, {
	header : 'Movie Name',
	dataIndex : 'movieName',
	sortable : true,
	width : 80
},

];

var keycolumns=[
         			{
         				header : 'Movie Name',
         				dataIndex : 'movieName',
         				sortable:true,
         				width:80
         			},
         			{
         				header : 'Movie Type',
         				dataIndex : 'movieTypeName',
         				sortable:true,
         				width:80
         			},
         			{
         				header : 'Positive Rating',
         				dataIndex : 'positiveRating',
         				sortable:true,
         				width    :80
         			},
         			{
         				header : 'Negative Rating',
         				dataIndex : 'negativeRating',
         				sortable:true,
         				width    :80
         			},
         			{
         				header : 'Neutral Rating',
         				dataIndex : 'neutralRating',
         				sortable:true,
         				width    :80
         			},{
         				header : 'Feature Type',
         				dataIndex : 'featureType',
         				sortable:true,
         				width    :80
         			}];

var hideConfirmationMsg;
var showConfirmationMsg;
/* Hide the Confirmation Message */
	hideConfirmationMsg = function() {
		var confMsgDiv = Ext.get('confirmationMessage');
		confMsgDiv.dom.innerHTML = "";
		confMsgDiv.dom.style.display = 'none';
	}
	/* Show Confirmation Message */
	showConfirmationMsg = function(msg) {
		var confMsgDiv = Ext.get('confirmationMessage');
		confMsgDiv.dom.innerHTML =  msg;
		confMsgDiv.dom.style.display = 'inline-block';		
	}
	var keyStore;
Ext.onReady(function () {

	var loadMask = new Ext.LoadMask(Ext.getBody(), {msg:"Loading"});
	loadMask.show();
	
	Ext.define('productTypeModel',{
		extend : 'Ext.data.Model',
		fields : [ 
		          {name:'movieTypeId',mapping:'movieTypeId',type:'int'},
		          {name:'movieName',mapping:'movieName',type:'string'}
		          ]
		
	});
	
	var productTypeStore = Ext.create('Ext.data.Store', {
		id : 'productTypeStoreId',
		name : 'productTypeStoreName',
		model : 'productTypeModel',
		proxy : {
			type : 'ajax',
			url : contextPath + '/review/retriveAllMovieTypes.do',
			extraParams : {},
			actionMethods : {
				read : 'POST'
			},
			reader : {
				type : 'json',
				root : 'model'
			}
		},
		listeners : {
			'load' : function(store, records) {

			}
		},
		autoLoad : true
	});
	
	
	Ext.define('productModel',{
		extend : 'Ext.data.Model',
		fields : [ 
		          {name:'movieId',mapping:'movieId',type:'int'},
		          {name:'movieName',mapping:'movieName',type:'string'},
		          {name:'movieType',mapping:'movieType',type:'int'}
		          ]
		
	});
	
	var productStore = Ext.create('Ext.data.Store', {
		id : 'productStoreId',
		name : 'productStoreName',
		model : 'productModel',
		proxy : {
			type : 'ajax',
			url : contextPath + '/review/retriveAllMoviesForMovieType.do',
			extraParams : {
				type:1
			},
			actionMethods : {
				read : 'POST'
			},
			reader : {
				type : 'json',
				root : 'model'
			}
		},
		listeners : {
			'load' : function(store, records) {

			}
		},
		autoLoad : true
	});
	
	
	Ext.define('featureModel',{
		extend : 'Ext.data.Model',
		fields : [ 
		          {name:'featureType',mapping:'featureType',type:'string'},
		          {name:'movieType',mapping:'movieType',type:'int'}
		         ]
		
	});
	
	var featureModelStore = Ext.create('Ext.data.Store', {
		id : 'featureModelStoreId',
		name : 'featureModelStoreName',
		model : 'featureModel',
		proxy : {
			type : 'ajax',
			url : contextPath + '/review/retriveAllFeaturesForMovieType.do',
			extraParams : {
				type:1
			},
			actionMethods : {
				read : 'POST'
			},
			reader : {
				type : 'json',
				root : 'model'
			}
		},
		listeners : {
			'load' : function(store, records) {

			}
		},
		autoLoad : true
	});

	
	Ext.define('keywordModel',{
		extend : 'Ext.data.Model',
		fields : [ 
			{name:'reviewId', mapping:'reviewId',type:'int'},
			{name:'movieId',mapping:'movieId',type:'int'},
			{name:'movieType',mapping:'movieType',type:'int'},	
			{name:'positiveRating',mapping:'positiveRating',type:'int'},
			{name:'negativeRating',mapping:'negativeRating',type:'int'},
			{name:'neutralRating',mapping:'neutralRating',type:'int'},
			{name:'featureType',mapping:'featureType',type:'string'},
			{name:'movieTypeName', mapping:'movieTypeName',type:'string'},
			{name:'movieName', mapping:'movieName',type:'string'}
			
		          ]
		
	});

	keyStore = Ext.create('Ext.data.Store', {
		id : 'keyStoreId',
		name : 'keyStoreName',
		model : 'keywordModel',
		autoLoad: {start: 0, limit: 50},
		proxy : {
			type : 'ajax',
			url :contextPath+'/review/viewTotalPolarity.do',
			actionMethods:{
				read:'POST'
			},
			reader : {
				type :'json',
				root:'model',
				totalProperty: 'total'
			}
		},
		listeners:
		{
			'load':function(store, records){
			
			var count=store.getCount();
			if(count<=0)
			{
				showConfirmationMsg("Polarity Computation Could not be Done");
			}
						
				loadMask.hide();
			}
		},
		autoLoad : true
	});
	
	
	var graphPanel = Ext
	.create(
			'Ext.form.Panel',
			{
				id : 'graphPanel',
				height : 220,
				width : 500,
				margin:'25 25 25 25',
				style : {
					border : 0
				},
				title:'Find Graphs',
				layout: {
				type: 'table',
				columns: 1
				},
				items : [
					
					{
						xtype : 'combo',
						labelAlign : 'top',
						fieldLabel : 'Feature Types',
						id : 'featureType',
						name : 'featureType',
						queryMode : 'local',
						displayField : 'featureType',
						valueField : 'featureType',
						triggerAction : 'all',
						store : featureModelStore,
						width:400,
						listeners : {	
							'select' : function(combo,records) {
						
									var urlLink = contextPath + '/review/viewTotalPolarityForMovieTypeAndFeatureType.do';
									var obj={};
									obj.movieType=1;
									obj.featureType=Ext.getCmp('featureType').getValue();
									findPolarityByTypeAndFeatureType(urlLink,obj);
								}
							}
					}],
				renderTo : 'habitatContainer'
			});

	function findPolarityByTypeAndFeatureType(urlLink,obj)
	{
		keyStore.load(
			{
				url :urlLink,
				params:{
							movieType:obj.movieType,
							featureType:obj.featureType
						}
			}
		);
			
	}
	
	function findFeaturesForProductType(urlLink,obj)
	{
		var store=Ext.getCmp('featureType').getStore();
		store.load(
			{
				url :urlLink,
				params:{
							type:obj.type,
						}
			}
		);
			
	}
	
	function findProductsForProductType(urlLink,obj){
	
		var store=Ext.getCmp('reviewGrid').getStore();
		store.load(
			{
				url :urlLink,
				params:{
							type:obj.type,
						}
			}
		);
		
	}
	
	
	Ext.create('Ext.chart.Chart', {
		renderTo : 'content',
		width : 500,
		height : 300,
		animate : true,
		store : keyStore,
		axes : [ {
			type : 'Numeric',
			position : 'bottom',
			fields : [ 'positiveRating' ],
			label : {
				renderer : Ext.util.Format.numberRenderer('0,0')
			},
			title : 'Positive Rating',
			grid : true,
			minimum : 0
		}, {
			type : 'Category',
			position : 'left',
			fields : [ 'movieName' ],
			title : 'Movie Name'
		} ],
		series : [ {
			type : 'bar',
			axis : 'bottom',
			highlight : true,
			tips : {
				trackMouse : true,
				width : 140,
				height : 28,
				renderer : function(storeItem, item) {
					this.setTitle(storeItem.get('movieName') + ': '
							+ storeItem.get('positiveRating')
							+ ' Positive Rating');
				}
			},
			label : {
				display : 'insideEnd',
				field : 'rating',
				renderer : Ext.util.Format.numberRenderer('0'),
				orientation : 'horizontal',
				color : '#333',
				'text-anchor' : 'middle'
			},
			xField : 'movieName',
			yField : 'positiveRating'
		} ]
	});

	Ext.create('Ext.chart.Chart', {
		renderTo : 'negativecontent',
		width : 500,
		height : 300,
		animate : true,
		store : keyStore,
		axes : [ {
			type : 'Numeric',
			position : 'bottom',
			fields : [ 'negativeRating' ],
			label : {
				renderer : Ext.util.Format.numberRenderer('0,0')
			},
			title : 'Negative Rating',
			grid : true,
			minimum : 0
		}, {
			type : 'Category',
			position : 'left',
			fields : [ 'movieName' ],
			title : 'Movie Name'
		} ],
		series : [ {
			type : 'bar',
			axis : 'bottom',
			highlight : true,
			tips : {
				trackMouse : true,
				width : 140,
				height : 28,
				renderer : function(storeItem, item) {
					this.setTitle(storeItem.get('movieName') + ': '
							+ storeItem.get('negativeRating')
							+ ' Negative Rating');
				}
			},
			label : {
				display : 'insideEnd',
				field : 'rating',
				renderer : Ext.util.Format.numberRenderer('0'),
				orientation : 'horizontal',
				color : '#333',
				'text-anchor' : 'middle'
			},
			xField : 'movieName',
			yField : 'negativeRating'
		} ]
	});

	Ext.create('Ext.chart.Chart', {
		renderTo : 'neutralcontent',
		width : 500,
		height : 300,
		animate : true,
		store : keyStore,
		axes : [ {
			type : 'Numeric',
			position : 'bottom',
			fields : [ 'neutralRating' ],
			label : {
				renderer : Ext.util.Format.numberRenderer('0,0')
			},
			title : 'Neutral Rating',
			grid : true,
			minimum : 0
		}, {
			type : 'Category',
			position : 'left',
			fields : [ 'movieName' ],
			title : 'Movie Name'
		} ],
		series : [ {
			type : 'bar',
			axis : 'bottom',
			highlight : true,
			tips : {
				trackMouse : true,
				width : 140,
				height : 28,
				renderer : function(storeItem, item) {
					this.setTitle(storeItem.get('movieName') + ': '
							+ storeItem.get('neutralRating')
							+ ' Neutral Rating');
				}
			},
			label : {
				display : 'insideEnd',
				field : 'rating',
				renderer : Ext.util.Format.numberRenderer('0'),
				orientation : 'horizontal',
				color : '#333',
				'text-anchor' : 'middle'
			},
			xField : 'movieName',
			yField : 'neutralRating'
		} ]
	});	
	var genericStore = Ext.create('Ext.data.Store', {
		id : 'genericStoreId',
		name : 'genericStoreName',
		model : 'productModel',
		proxy : {
			type : 'ajax',
			url : contextPath
					+ '/review/retriveAllMoviesForMovieType.do',
			extraParams : {
				type : 1
			},
			actionMethods : {
				read : 'POST'
			},
			reader : {
				type : 'json'
			}

		},
		listeners : {
			'load' : function(store, records) {
			}
		},
		autoLoad : true
	});
	genericStore.load();


	

});
	
	
	
