Ext.require(['Ext.grid.*', 'Ext.data.*', 'Ext.form.*', 'Ext.layout.container.Column', 'Ext.tab.Panel']);
Ext.Loader.setConfig({
    enabled: true
});
Ext.tip.QuickTipManager.init();
var reviewColumns=[
					{
         				header : 'Review ID',
         				dataIndex : 'reviewId',
         				sortable:false,
         				width:100
         			},
         			{
         				header : 'Movie Name',
         				dataIndex : 'movieName',
         				sortable:true,
         				width    :100
         			},
         			 {
         				header : 'Review Details',
         				dataIndex : 'reviewDetails',
         				sortable:true,
         				width    :600,
         				renderer : function(value, metadata, record, rowIndex, colIndex, store) {
         					metadata.tdAttr = 'data-qtip="' + value + '"';
         					return value;
         				}
         			 }
         	    	];



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
	
Ext.onReady(function () {
   Ext.define('reviewModel', {
				extend : 'Ext.data.Model',
				fields : [ 
							{name:'movietype', mapping:'movietype',type:'string'},
				           {name:'reviewId', mapping:'reviewId',type:'int'},
				           {name:'movieName', mapping:'movieName',type:'string'},
				           {name:'reviewDetails', mapping:'reviewDetails',type:'string'}
						  ]
			
			});
	
	var reviewStore = Ext.create('Ext.data.Store', {
				id : 'reviewStoreId',
				name : 'reviewStoreName',
				model : 'reviewModel',
				autoLoad: {start: 0, limit: 15},
				proxy : {
					type : 'ajax',
					url :contextPath+'/review/retriveAllReviews.do',
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
				}			
				},
				autoLoad : true
			});
	reviewStore.load();
	
	var reviewGrid = Ext.create('Ext.grid.Panel', {
		collapsible:true,
		title:'Reviews Collected using Web Crawler',
		forceFit : true,
		id : 'reviewGrid',
		store : reviewStore,
		columns :reviewColumns,
		height : 600,
		width : 1200,
		autoFit : true,
		stripRows:true,
		renderTo : 'reviewGridContainer',
		 bbar: Ext.create('Ext.PagingToolbar', {
	            store: reviewStore,
	            displayInfo: true,
	            displayMsg: 'Displaying topics {0} - {1} of {2}',
	            emptyMsg: "No topics to display",
	            inputItemWidth: 35
	     })
	});

});
	
	
	
