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
    								text:'变更更正审批',
    								minWidth:75,
    								handler:function(){
    									var url = xmdz + "yw/spgl/spcl/bggzsp";
    					    			if(window&&window.parent&&window.parent.openWorkSpace)
    					    				window.parent.openWorkSpace(url,  "审批处理【变更更正审批】", url);
    					    			else
    					    				window.location.href = url;
    								}
    							}),
    							new Ext.Button({
    								text:'户别变更审批',
    								minWidth:75,
    								handler:function(){
    									var url = xmdz + "yw/spgl/spcl/hbbgsp";
    					    			if(window&&window.parent&&window.parent.openWorkSpace)
    					    				parent.openWorkSpace(url,  "审批处理【户别变更审批】", url);
    					    			else
    					    				window.location.href = url;
    								}
    							}),
    							new Ext.Button({
    								text:'户籍补录审批',
    								minWidth:75,
    								handler:function(){
    									var url = xmdz + "yw/spgl/spcl/hjblsp";
    					    			if(window&&window.parent&&window.parent.openWorkSpace)
    					    				parent.openWorkSpace(url,  "审批处理【户籍补录审批】", url);
    					    			else
    					    				window.location.href = url;
    								}
    							}),
    							new Ext.Button({
    								text:'户籍删除审批',
    								minWidth:75,
    								handler:function(){
    									var url = xmdz + "yw/spgl/spcl/hjscsp";
    					    			if(window&&window.parent&&window.parent.openWorkSpace)
    					    				parent.openWorkSpace(url,  "审批处理【户籍删除审批】", url);
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