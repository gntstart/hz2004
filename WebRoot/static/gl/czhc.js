Ext.onReady(function(){
	
	var myStore = new Ext.data.JsonStore({
 		proxy: new Ext.data.HttpProxy({
 			url: 'gl/czhc/reload',//yw/hjyw/hzywcl/queryHzywcl
			method: "POST",
			disableCaching: true
	    }),
        root: 'list',
        id:'id',
        totalProperty:'totalCount',
        listeners:{
			loadexception:function(mystore,options,response,error){
				if(error){
					var json = Ext.decode(response.responseText);
					Ext.Msg.alert("提示",json.messages[0]);
				}else{
					Ext.Msg.alert("提示",response.responseText);
				}
			}
        }
    });
    
	var fs = new Ext.form.FormPanel({
		id:'form1',
    	title:'',
    	anchor:'100% 100%',
    	buttonAlign:'right',
    	labelAlign:'right',
    	frame:true,
    	border:false,
        layout:'form',
        labelWidth:75,
       	items:[{
        		layout:'column',
    			frame:false,
    			border:false,
        		defaults:{
        			border:false,
        			frame:false
        		},
            	items:[{
		           	columnWidth:1,
	    	        layout: 'form',
	    	        bodyStyle:'padding:10 10 10 10',
	        	    items: [new Ext.Button({
	        	    	id:'czAllId',
						text:'全部重载',
						minWidth:100,
						handler:function(){
							
							var subdata = {reloadFlag:0};
							
							subdata.reloadFlag = Ext.encode(subdata.reloadFlag);
							
							Gnt.submit(
								subdata, 
								"gl/czhc/reload", 
								"正在重载所有缓存，请稍后...", 
								function(jsonData,params){
									showInfo("重载缓存成功！",function(){
										
									});
								},
								function(json){
									if(json.message){
										showInfo("重载缓存失败！",function(){
											
										});
									}
								}
							);
						}
		    	    })]
				},{
//					columnWidth:.5,
		           	columnWidth:1,
	    	        layout: 'form',
	    	        bodyStyle:'padding:10 10 10 10',
	        	    items: [new Ext.Button({
	        	    	id:'czZzjgId',
						text:'重载组织机构',
						minWidth:100,
						handler:function(){
							
							var subdata = {reloadFlag:1};
							
							subdata.reloadFlag = Ext.encode(subdata.reloadFlag);
							
							Gnt.submit(
								subdata, 
								"gl/czhc/reload", 
								"正在重载所有缓存，请稍后...", 
								function(jsonData,params){
									showInfo("重载缓存成功！",function(){
										
									});
								},
								function(json){
									if(json.message){
										showInfo("重载缓存失败！",function(){
											
										});
									}
								}
							);
						}
		    	    })]
				},{
//					columnWidth:.5,
		           	columnWidth:1,
	    	        layout: 'form',
	    	        bodyStyle:'padding:10 10 10 10',
	        	    items: [new Ext.Button({
	        	    	id:'czZzjyId',
						text:'重载组织警员',
						minWidth:100,
						handler:function(){
							
							var subdata = {reloadFlag:2};
							
							subdata.reloadFlag = Ext.encode(subdata.reloadFlag);
							
							Gnt.submit(
								subdata, 
								"gl/czhc/reload", 
								"正在重载所有缓存，请稍后...", 
								function(jsonData,params){
									showInfo("重载缓存成功！",function(){
										
									});
								},
								function(json){
									if(json.message){
										showInfo("重载缓存失败！",function(){
											
										});
									}
								}
							);
						}
		    	    })]
				}]
		}]
	});
	
	new Ext.Viewport({
	    layout: 'border',
	    items: [{
	        region: 'center',
	        border: false,
	        closable:true,
	        margins: '5 5 5 5',
	        layout:'fit',
	        items:[fs]
	    }]
	});
    
});