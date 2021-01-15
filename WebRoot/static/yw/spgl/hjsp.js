Ext.onReady( function() {
    Ext.QuickTips.init();

	var v = new Ext.Viewport({
        layout:'fit',
        id:'vp',
        border:false,
        items:{
        	layout: 'border',
    		newYewu: function(){
    			//初始化
    		},
    		items:[
    		   	{
    				title: '审批方式选择',
    				html:'',
    				region:'north',
    				height:40,
    				border:false,
    				frame:true,
    				buttonAlign:'left',
    				buttons:[
    							new Ext.Button({
    								text:'迁入审批',
    								minWidth:75,
    								handler:function(){
    					    			var url = xmdz + "yw/spgl/hjsp/qrsp";
    					    			if(parent.openWorkSpace)
    					    				parent.openWorkSpace(url,  "户籍审批【迁入审批】", url);
    					    			else
    					    				window.location.href = url;
    								}
    							}),
    							new Ext.Button({
    								text:'变更更正审批',
    								minWidth:75,
    								handler:function(){
    									var url = xmdz + "yw/spgl/hjsp/bggzsp";
    					    			if(parent&&parent.openWorkSpace)
    					    				parent.openWorkSpace(url,  "户籍审批【变更更正审批】", url);
    					    			else
    					    				window.location.href = url;
    								}
    							}),
    							new Ext.Button({
    								text:'户别变更审批',
    								minWidth:75,
    								handler:function(){
    									var url = xmdz + "yw/spgl/hjsp/hbbgsp";
    					    			if(parent&&parent.openWorkSpace)
    					    				parent.openWorkSpace(url,  "户籍审批【户别变更审批】", url);
    					    			else
    					    				window.location.href = url;
    								}
    							}),
    							new Ext.Button({
    								text:'户籍补录审批',
    								minWidth:75,
    								handler:function(){
    									var url = xmdz + "yw/spgl/hjsp/hjblsp";
    					    			if(parent&&parent.openWorkSpace)
    					    				parent.openWorkSpace(url,  "户籍审批【户籍补录审批】", url);
    					    			else
    					    				window.location.href = url;
    								}
    							}),
    							new Ext.Button({
    								text:'户籍删除审批',
    								minWidth:75,
    								handler:function(){
    									var url = xmdz + "yw/spgl/hjsp/hjscsp";
    					    			if(parent&&parent.openWorkSpace)
    					    				parent.openWorkSpace(url,  "户籍审批【户籍删除审批】", url);
    					    			else
    					    				window.location.href = url;
    								}
    							})
    				]
    			},{
    				html: '请选择业务办理方式！',
    				region:'center',
    				border:false,
    				frame:false,
    				bodyStyle:'padding:15px'
    			}
    		]
        }
    });
});