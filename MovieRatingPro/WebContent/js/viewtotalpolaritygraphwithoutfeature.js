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
	
	
	
	
	
		
		
	Ext.define('keywordModel',{
		extend : 'Ext.data.Model',
		fields : [ 
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
			url :contextPath+'/review/viewTotalPolarityWithoutFeature.do',
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
	


	

});
	
	
	
