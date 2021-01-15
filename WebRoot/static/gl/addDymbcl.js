Ext.onReady(function(){
	var p = new Ext.Panel({
		layout:'column',
		height:600,
		//heightAuto:true,
		items:[{
		    title: '模板属性设置',
		    columnWidth: .4,
		    height:'100%',
		    layout:'form',
		    bodyStyle:'padding:5px',
		    defaults:{
		    	bodyStyle:'padding:5 0 0 0',
		    	labelWidth:200,
    			border:false,
    			frame:false
    		},
		    items:[
		    	{
	                columnWidth:1,
    	           	layout: 'form',
    	           	bodyStyle:'padding:5 0 0 0',
        	       	items: [{
						xtype:'textfield',
						anchor:'100%',
						id:'lodopId',
						name:'lodopId',
						allowBlank:false,
						fieldLabel:'请输入模板id(只能英文字母)'
					}]
				},{
	                columnWidth:1,
    	           	layout: 'form',
    	           	bodyStyle:'padding:5 0 0 0',
        	       	items: [{
						xtype:'textfield',
						anchor:'100%',
						id:'lodopName',
						name:'lodopName',
						allowBlank:false,
						fieldLabel:'模板名称'
					}]
				},{
	                columnWidth:1,
    	           	layout: 'form',
    	           	bodyStyle:'padding:5 0 0 0',
        	       	items: [{
						xtype:'textfield',
						anchor:'100%',
						id:'version',
						name:'version',
						value:'v1.1',
						allowBlank:false,
						fieldLabel:'版本号'
					}]
				},{
	                columnWidth:1,
    	           	layout: 'form',
    	           	bodyStyle:'padding:5 0 0 0',
        	       	items: [{
						xtype:'textfield',
						anchor:'100%',
						id:'position',
						name:'position',
						value:'0,0,801,600',
						allowBlank:false,
						fieldLabel:'打印初始化位置偏移量((Top,Left,Width,Height)单位mm)'
					}]
				},{
	                columnWidth:1,
    	           	layout: 'form',
    	           	bodyStyle:'padding:5 0 0 0',
        	       	items: [{
						xtype:'textfield',
						anchor:'100%',
						id:'pageSize',
						name:'pageSize',
						value:'(1,0,0,"")',
						fieldLabel:'(intOrient, PageWidth, PageHeight,strPageName)'
					}]
				},{
	                columnWidth:1,
    	           	layout: 'form',
    	           	heightAuto:true,
    	           	
    	           	bodyStyle:'padding:5 0 0 0',
        	       	items: [{
						anchor:'100%',
						height:450,
						allowBlank:false,
						html:'intOrient：</br>'+
							'打印方向及纸张类型，数字型，</br>'+
							'1---纵(正)向打印，固定纸张；</br>'+ 
							'2---横向打印，固定纸张；</br> '+
							'3---纵(正)向打印，宽度固定，高度按打印内容的高度自适应；</br>'+
							'0(或其它)----打印方向由操作者自行选择或按打印机缺省设置；</br>'+
							'PageWidth(自定义设定纸张宽度),PageHeight(固定纸张时设定纸张高)：</br>'+
							'整数或字符型，整数时缺省长度单位为0.1mm, 数值等于0时本参数无效。'+
							'宽或高无效时下面的strPageName才起作用。</br>'+
							'strPageName：</br>'+
							'所选纸张类型名，字符型。不同打印机所支持的纸张可能不一样，这里的名称同操作系统内打印机属性中的纸张名称，支持操作系统内的自定义纸张。</br>'+
							'关键字“CreateCustomPage”会按以上宽度和高度自动建立一个自定义纸张，所建立的纸张名固定为“LodopCustomPage”，多次建立则刷新该纸张的大小值。'+
							'PageWidth、PageHeight 和strPageName都无效时，本函数对纸张大小不起作用',
						fieldLabel:'设定纸张大小帮助'
					}]
				}
		    	]
			},{
			    title: '',
			    columnWidth: .6,
			    height:500,
			    items:[new Ext.form.TextArea({
                    id:"modulNr",
                    height:'100%',
                    width:'80%',
                    fieldLabel: '模板内容'
                   })]
			}
			],
			buttons:[
				new Ext.Button({
					text:'预览',
					minWidth:80,
					handler:function(){
						var lodopIdTemp = Ext.getCmp('lodopId').getValue();
						var versionTemp = Ext.getCmp('version').getValue();
						var positionTemp = Ext.getCmp('position').getValue();
						if(!lodopIdTemp||!versionTemp||!positionTemp){
							alert("模板基础设置信息(lodopId,version,position)必填");
							return;
						}else{
							previeInsertwModul(Ext.getCmp('modulNr').getValue().replace(/[\r\n]/g,""));
						}
					}
				}),
				new Ext.Button({
					text:'新增',
					minWidth:80,
					handler:function(){
						if(window.confirm('请确认能预览成功后，再保存！')){
							if(Ext.getCmp('lodopName').getValue().length == 0||Ext.getCmp('lodopId').getValue().length == 0||Ext.getCmp('modulNr').getValue().length == 0){
								alert("模板名称或模板内容不能为空!");
								return;
							}
							if(Ext.getCmp('version').getValue().length == 0||Ext.getCmp('position').getValue().length == 0){
								alert("版本号或打印初始化位置偏移量不能为空!");
								return;
							}
							Gnt.submit(
									{
										lodopId:Ext.getCmp('lodopId').getValue(),
										lodopName:Ext.getCmp('lodopName').getValue(),
										version:Ext.getCmp('version').getValue(),
										position:Ext.getCmp('position').getValue(),
										printpageSize:Ext.getCmp('pageSize').getValue(),
										nr:Ext.getCmp('modulNr').getValue()
									}, 
									"gl/dymbzxwh/addDymb", 
									"正在进行打印模板新增，请稍后...", 
									function(jsonData,params){
										showInfo("新增成功！",function(){
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