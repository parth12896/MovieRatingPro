Ext.require(['Ext.grid.*', 'Ext.data.*', 'Ext.form.*', 'Ext.layout.container.Column', 'Ext.tab.Panel']);
Ext.Loader.setConfig({
    enabled: true
});
Ext.tip.QuickTipManager.init();



var keycolumns=[
		{
			header : 'Movie Name',
			dataIndex : 'movieName',
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
	
	
	
	
	
	var keyGrid = Ext.create('Ext.grid.Panel', {
		title:'Total Polarity Output',
		forceFit : true,
		id : 'keyGrid',
		store : keyStore,
		columns : keycolumns,
		width:800,
		height:300,
		autoFit : true,
		autoscroll:true,
		stripRows:true,
		renderTo : 'keyContainer',
		collapsible:true,
		overflowY:'auto',
		 bbar: Ext.create('Ext.PagingToolbar', {
	            store: keyStore,
	            displayInfo: true,
	            displayMsg: 'Displaying Total Polarity {0} - {1} of {2}',
	            emptyMsg: "No topics to display",
	            inputItemWidth: 35
	     })
	});

});
	
	
	
