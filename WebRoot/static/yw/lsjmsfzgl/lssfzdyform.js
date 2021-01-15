var isblack=0;
var zpid=null;
var ld=null;
 var dbd= null;
 var issubmit="no";
Gnt.ux.lssfzdywin= Ext.extend(Ext.Window, {
	title:'临时身份证打印',
	closeAction:'hide',
	modal:true,
	width:600,
	height:420,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(type){
		this.type = type;
		Gnt.ux.lssfzdywin.superclass.show.call(this);
	},
	setSelRes:function(sfzhm,zpid){
		zpid=zpid;
		Ext.getCmp("gmsfhm").setValue(sfzhm);
		 Ext.getCmp('oldImage').body.update("<IMG SRC='yw/common/img/render/" + zpid + "' width='100%'  height='100%' />");
		 Ext.getCmp('newImage').body.update("<IMG SRC='yw/common/img/render/" + zpid + "' width='100%' onerror='javascript:isImage=0;' height='100%' />");
	},
	resetData:function(){
		this.fs.getForm().reset();
	},
	initComponent : function(){
		
		var fs = new Ext.form.FormPanel({
	    	height: 80,
	    	region: 'north',
	    	anchor:'100% 100%',
	    	buttonAlign:'center',
	    	labelAlign:'right',
	    	frame:false,
	    	border:false,
	    	//fileUpload: true, 
	    	margins:'0',
	        layout:'form',
	       	items:[{
	        		//layout:'column',
	        		 title:'临时居民身份证卡号设置',
			    	 xtype: 'fieldset',
	    			frame:false,
	    			border:true,
	    			layout : 'table',
			    	layoutConfig: {
            	    		columns: 3
            	    	 },
	            	items:[{
			                //columnWidth:.5,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		    	           	border:false,
		        	       	items: [{
								xtype:'textfield',
								width:200,
								anchor:'100%',
								id:'gmsfhm',
								name:'gmsfhm',
								allowBlank:true,
								disabled:true,
								fieldLabel:'公民身份号码'
							}]
						},{width:10,border:false,frame:false},
						{
			                //columnWidth:.5,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:0 0 0 0',
		    	        	border:false,
		        	       	items: [{
								xtype:'textfield',
								width:140,
								anchor:'100%',
								id:'lssfzkh',
								name:'lssfzkh',
								allowBlank:true,
								disabled:false,
								fieldLabel:'临时身份证卡号'
							}]
						}
				]
			},{
        		//layout:'column',
       		title:'照片打印调整',
		    xtype: 'fieldset',
   			frame:false,
   			border:true,
   			labelWidth:40,
   			layout : 'column',
		    	layoutConfig: {
       	    		columns: 1
       	    	 },
       	    	 items:[{
       	    		 layout:'table',
       	    		 border:false,
       	    		items:[{
       	    			layout: 'form',
       	    			border:false,
       	    			cls:'ld',
    			    	tooltip :'亮度'
    			    },{
		                //columnWidth:.5,
	    	           	layout: 'form',
	    	           	bodyStyle:'padding:0 0 0 0',
	    	           	border:false,
	        	       	items: [{
							xtype:'textfield',
							//cls:'ld',
							width:50,
							anchor:'100%',
							name:'ld',
							id:'ld',
							allowBlank:true,
							disabled:false,
							value:'1',
							 labelSeparator: ''
							//fieldLabel:''
						}]
					},{width:5,border:false,frame:false},
					{
       	    			layout: 'form',
       	    			border:false,
       	    			cls:'dbd',
    			    	tooltip :'对比度'
    			    },
					{
		                //columnWidth:.5,
	    	           	layout: 'form',
	    	           	bodyStyle:'padding:0 0 0 0',
	    	        	border:false,
	        	       	items: [{
	    			    	
							xtype:'textfield',
							//cls:'dbd',
							width:50,
							anchor:'100%',
							name:'dbd',
							id:'dbd',
							value:'20',
							allowBlank:true,
							disabled:false,
							 labelSeparator: ''
							//fieldLabel:''
						}]
					},{width:20,border:false,frame:false},{
		        		xtype:'checkbox',
		        		boxLabel : "把照片转换成黑白照片再打印",
		        		listeners:{
		    				'check': function(obj,checked){
		    					if(checked){
		    						isblack=1;
		    					}else{
		    						isblack=0;
		    					}
		    				}
		    			}
		        	}
			]
       	    	 },{
       	    		 layout:'table',
       	    		 border:false,
       	    		bodyStyle:'padding:10px 40px',
       	    		items:[{
		                //columnWidth:.5,
	    	           	layout: 'form',
	    	           	bodyStyle:'padding:0 10',
	    	           	border:false,
	        	       	items: [{html:'原照片',border:false,frame:false},{
							id:'oldImage',
							title: '',
							height:'100%',								
							html: '照片',
							width:150,
							height:180,
							rowspan: 1,
							colspan:1
						}]
					},{	bodyStyle:'padding:0 20px',border:false,frame:false},
					new Ext.Button({
     					 id:'cpdy',
       					text:'打印调整',
       					minWidth:80,
       					handler:function(){
       						 ld= Ext.getCmp("ld").getValue();
                     	     dbd= Ext.getCmp("dbd").getValue();                               	                 
         				    Ext.getCmp('newImage').body.update("<IMG SRC='yw/lsjmsfzgl/img/render2/" + zpid + "/"+ ld +"/"+ dbd +"/"+ isblack +"/"+ issubmit +"' width='100%' onerror='javascript:isImage=0;' height='100%' />");                     
       					}
       				}),
					{
		                //columnWidth:.5,
	    	           	layout: 'form',
	    	           	bodyStyle:'padding:0 40px',
	    	        	border:false,
	    	        	items: [{html:'调整之后的照片',border:false,frame:false},{
							id:'newImage',
							title: '',
							height:'100%',								
							html: '照片',
							width:150,
							height:180,
							rowspan: 1,
							colspan:1
						}]
					}
			]
       	    	 }]
          
		}],
	    	buttons:[
	    	 		{
	    	 			text:'确定',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var rootWin = this.findParentByType("lssfzdy_window");
	    	 				var form = rootWin.items.get(0);
	    	 				/*if(!form.getForm().isValid()){
	    						
	    						return;
	    					}*/
	    	 				/*var content = form.getForm().getValues();
	    	 				if(rootWin.callback){
								rootWin.callback(rootWin.type, content);
							}*/
	    	 				issubmit="yes";
	    	 				Ext.getCmp('newImage').body.update("<IMG SRC='yw/lsjmsfzgl/img/render2/" + zpid + "/"+ ld +"/"+ dbd +"/"+ isblack +"/"+ issubmit +"' width='100%' onerror='javascript:isImage=0;' height='100%' />");
							rootWin.hide();
	    	 			}
	    	 		},{
	    	 			text:'取消',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var win = this.findParentByType("lssfzdy_window");
	    	 				win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.lssfzdywin.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('lssfzdy_window', Gnt.ux.lssfzdywin);
