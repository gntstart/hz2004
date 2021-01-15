/**
 * 标准地址选择,必须先加载commFrames.js
 */
Gnt.ux.SelectHglgx = Ext.extend(Ext.Window, {
	title:'户关联关系输入',
	closeAction:'hide',
	modal:true,
	width:400,
	height:250,
	margins:'5',
	layout:'border',
	initComponent : function(){
		
		this.myPanel = new Ext.Panel({
			region : 'center',
			
			items:[
				{
					border:false,
					frame: false,
					id: "panelHtmlId" ,
					html: '<div class="text"><h2>请输入户关联关系:</ h2></div>',
					region:'north',
					bodyStyle:'padding:15px'
				},{

					border:false,
					frame: false,
					region:'center',
					bodyStyle:'padding:0 0 0 15px',
					items:[{
	                    xtype: "textarea",
	                    width: 350,
	                    height:110,
	                    enableKeyEvents: true,
	                    listeners: {
	                        keyup: function(src, evt){
	                        	var win = this.findParentByType("hglgx_window");
	                        	if(src.getValue()){
	                        		win.myPanel.buttons[0].enable();
	                        	}else{
	                        		win.myPanel.buttons[0].disable();
	                        	}
	                        }   
	                    }
	                }]
				
				}
				
			],
			buttons:[{
		        text:'确定',
		        disabled:true,
				minWidth:75,
				handler:function(){
					var win = this.findParentByType("hglgx_window");
					
					if(win.callback){
						win.callback(win.myPanel.items.items[1].items.items[0].getValue());
					}
					
					win.myPanel.items.items[1].items.items[0].setValue(null);
					
					win.hide();
					
				}
		    },{
		        text:'取消',
				minWidth:75,
				handler:function(){
					var win = this.findParentByType("hglgx_window");
					
					/**
						清空
					 */
					win.myPanel.items.items[1].items.items[0].setValue(null);
					
					win.hide();
				}
		    }]
		});
		
		
		this.items = [this.myPanel];
		
		Gnt.ux.SelectHglgx.superclass.initComponent.call(this);
	}
    
});
Ext.reg('hglgx_window', Gnt.ux.SelectHglgx);
