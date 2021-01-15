Gnt.ux.SelectBssqModify= Ext.extend(Ext.Window, {
	title:'本市市区修改',
	closeAction:'hide',
	modal:true,
	width:500,
	height:200,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(selRes){
		this.data = selRes.data;
		Gnt.ux.SelectBssqModify.superclass.show.call(this);
	},
	setSelRes:function(selRes){
		this.selRes = selRes;
		if(selRes){
			this.fs.getForm().setValues({
				sqbz: selRes.data.sqbz,
				qhdm:selRes.data.qhdm,
				gxbz: selRes.data.gxbz
			});
		}
	},
	resetData:function(){
		this.fs.getForm().reset();
	},
	initComponent : function(){
		
		var fs = new Ext.form.FormPanel({
			height: 80,
	    	region: 'north',
	    	anchor:'100% 100%',
	    	buttonAlign:'right',
	    	labelAlign:'right',
	    	frame:true,
	    	border:false,
	    	fileUpload: true, 
	    	margins:'0',
	        layout:'form',
	        labelWidth:100,
			
			items:[{
                columnWidth:1,
	           	layout: 'form',
	           	bodyStyle:'padding:5 0 0 0',
    	       	items: [{
    	       		xtype : "combo",
            		store : [['0','0-市区外'],['1','1-市区内']],
        			border:false,
        			frame:false,
        			name:'sqbz',
        			id:'sqbzmodify',
        			allowBlank:false,
        			fieldLabel:"市区标志",
        			triggerAction:"all",
        			maxHeight : 80,
        			anchor : '100%'
				}]
			},{
                columnWidth:1,
	           	layout: 'form',
	           	bodyStyle:'padding:5 0 0 0',
    	       	items: [{
    	       		xtype : "combo",
            		store : [['0','0-非管辖区'],['1','1-管辖区']],
        			border:false,
        			frame:false,
        			name:'gxbz',
        			id:'gxbzmodify',
        			allowBlank:false,
        			fieldLabel:"管辖标志",
        			triggerAction:"all",
        			maxHeight : 80,
        			anchor : '100%'
				}]
			},{
				columnWidth:1,
	           	layout: 'form',
	           	bodyStyle:'padding:5 0 0 0',
            	items:[{
					xtype:'search_combox',
					anchor:'100%',
					searchUrl:'dict/utils/searchXzqh.json?qybz=1',
					fields:["code","name"],
					valueField: "code",
					displayField: "name",
//						id:'xzqhbdm',
					allowBlank:false,
					fieldLabel:'所属区划',
					hiddenName:'qhdm'
				}
			]
		}],
			buttons:[{
		        text:'确定',
				minWidth:75,
				handler:function(){
					var rootWin = this.findParentByType("modifybssqWindow");
					var form = rootWin.items.get(0);
	 				if(!form.getForm().isValid()){
						showErr("本市市区信息必须填写！");
						return;
					}
	 				var lhdz = form.getForm().getValues();
	 				lhdz.sqid=rootWin.data.sqid;
	 				lhdz.sqbz=Ext.getCmp('sqbzmodify').getValue();
	 				lhdz.gxbz=Ext.getCmp('gxbzmodify').getValue();
					if(rootWin.callback){
						rootWin.callback('bssqdata', lhdz);
					}
					rootWin.hide();
				}
		    },{
		        text:'关闭',
				minWidth:75,
				handler:function(){
					var win = this.findParentByType("modifybssqWindow");
					
					win.hide();
				}
		    }]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.SelectBssqModify.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('modifybssqWindow', Gnt.ux.SelectBssqModify);
