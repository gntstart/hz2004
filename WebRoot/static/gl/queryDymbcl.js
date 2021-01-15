Ext.onReady(function(){
	//alert(getQueryParam("lodopId"));
	CreateFormPage(getQueryParam("lodopId"),getQueryParam("id"));
	var p = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[
			{
				id:"p2CenterId",
				border:false,
				frame: false,
				region:'center',
				width:'100%',
				items:[new Ext.form.TextArea({
                    id:"modulNr",
                    height:'80%',
                    width:'70%',
                    fieldLabel: '模板内容'
                   })]
			}
			],
			buttons:[
				new Ext.Button({
					text:'预览',
					minWidth:80,
					handler:function(){
						previeQuerywModul(Ext.getCmp('modulNr').getValue().replace(/[\r\n]/g,""));
					}
				}),
				new Ext.Button({
					text:'保存',
					minWidth:80,
					handler:function(){
						if(window.confirm('请确认能预览成功后，再保存！')){
							
							Gnt.submit(
									{
										lodopId : getQueryParam("lodopId"),
										id : getQueryParam("id"),
										nr:Ext.getCmp('modulNr').getValue()
									}, 
									"gl/dymbzxwh/saveDymb", 
									"正在进行打印模板保存，请稍后...", 
									function(jsonData,params){
										showInfo("保存成功！",function(){
											window.parent.parent.closeWorkSpace();
										});
									}
								);
						}else{
							return;
						}
					}
				}),
				new Ext.Button({
					text:'删除',
					minWidth:80,
					disabled: !user.isadmin?true:false,
					handler:function(){
						if(window.confirm('请确认是否要删除该打印模板！')){
							
							Gnt.submit(
									{
										lodopId : getQueryParam("lodopId"),
										id : getQueryParam("id"),
										nr:Ext.getCmp('modulNr').getValue()
									}, 
									"gl/dymbzxwh/delDymb", 
									"正在进行打印模板删除，请稍后...", 
									function(jsonData,params){
										showInfo("删除成功！",function(){
											window.parent.parent.closeWorkSpace();
										});
									}
								);
						}else{
							return;
						}
					}
				}),
				new Ext.Button({
					text:'关闭',
					minWidth:80,
					handler:function(){
						window.parent.parent.closeWorkSpace();
					}
				})
				]
	});
	
	var v = new Ext.Viewport({
        layout:'fit',
        id:'vp',
        border:false,
        items: {
        	layout:'card',
        	border:false,
        	id:'card',
        	frame:false,
        	activeItem: 0,
        	xtype: 'panel',
        	items:[p]
        }
    });
});