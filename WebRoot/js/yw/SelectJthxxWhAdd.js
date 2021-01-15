Gnt.ux.SelectJthxxWhAdd= Ext.extend(Ext.Window, {
	title:'街路巷信息新增',
	closeAction:'hide',
	modal:true,
	width:400,
	height:550,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(){
		Gnt.ux.SelectJthxxWhAdd.superclass.show.call(this);
	},
	setSelRes:function(){
		this.fs.getForm().setValues({
			qhdm: '',
			dm: '',
			mc:'',
			zwpy: '',
			wbpy: '',
			bz:''
		});
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
	        		layout:'column',
	    			frame:false,
	    			border:false,
	        		defaults:{
	        			border:false,
	        			frame:false
	        		},
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
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
		        	       		
		        	       		id:'qhdmA',
								anchor:'100%',
								xtype:'tree_comboBox',
								searchUrl:'dict/utils/searchXzqhNew',
								fields:["mc","dm","dwList"],
								valueField: "dm",
								displayField: "mc",
								treeUrl:'dict/utils/searchTreeXzqh',
								allowBlank:false,
								fieldLabel:'所属区划',
								hiddenName:'qhdm',
								listeners:{
									select:function(combo, res, eOpts ){
										var win = this.findParentByType("addjthxxwh_window");
										
										var data = res.data;
										var f = combo.findParentByType("form").getForm().findField("pcs");
										f.setValue("");
										f.store.removeAll();
										if(data.dwList && data.dwList.length>0){
											var list = new Array();
											for(var i=0;i<data.dwList.length;i++){
												list[i] = new Array();
												list[i][0]=data.dwList[i].dm;
												list[i][1]=data.dwList[i].mc;
												list[i][2]=data.dwList[i];
											}
											f.store.loadData(list);
										}
										
										
										var xzStroe = new Ext.data.JsonStore({
									        url: 'dict/utils/searchXxb?visiontype=XZJDXXB',
									        combox:this,
									        root: 'list',
									        id: this.valueField,
									        totalProperty:'totalCount',
									        fields: ["code","name"]
										});
										
										var x = combo.findParentByType("form").getForm().findField("xzjd");
										x.setValue("");
										x.store.removeAll();
										xzStroe.load({params:{'search_code':res.data.dm}});
										
										xzStroe.on("load",function(store) {
											if(store.data.length > 0){
												var list = new Array();
												for(var i=0;i<store.data.length;i++){
													var map = store.data.items;
													list[i] = new Array();
													list[i][0]=map[i].data.code;
													list[i][1]=map[i].data.name;
													list[i][2]=map[i];
												}
												x.store.loadData(list);
											}
										});
										
									}
								}
		        	       		
		        	       		/*
								xtype:'search_combox',
								anchor:'100%',
								searchUrl:'dict/utils/searchXzqh.json?qybz=1',
								fields:["code","name"],
								valueField: "code",
								displayField: "name",
								id:'qhdmA',
								allowBlank:false,
								fieldLabel:'所属区划',
								hiddenName:'qhdm'
//								hiddenName:'xzqhbdm',
//								name:'xzqhbmc'
								*/
							}]
						},{
				                columnWidth:1,
			    	           	layout: 'form',
			    	           	bodyStyle:'padding:5 0 0 0',
			        	       	items: [{
									xtype:'textfield',
									anchor:'100%',
									name:'dm',
									allowBlank:false,
									fieldLabel:'街路巷代码'
								}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'mc',
								allowBlank:false,
								fieldLabel:'街路巷名称',
			                    listeners: {
			                    	change: function (me, newValue, oldValue, eOpts) {
			                    		Ext.getCmp('zwpy1').setValue(pinyin.getCamelChars(newValue));
							        }
			                    }
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								id:'zwpy1',
								name:'zwpy',
								allowBlank:false,
								fieldLabel:'街路巷中文拼音',
								listeners:{
								}
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'wbpy',
								fieldLabel:'街路巷五笔拼音'
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:0 0 0 0',
		        	       	items: [{
								xtype:'textarea',
								height:100,
								anchor:'100%',
								name:'bz',
//								allowBlank:false,
								fieldLabel:'备注'
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
		        	       		xtype:'dict_combox',
								dict:'VisionType=_BLANK',
								anchor:'100%',
								name:'xzjd',
								fieldLabel:'乡镇街道',
								allowBlank:false,
								hiddenName:'xzjd',
								listeners:{
									select:function(combo, res, eOpts ){
										
										/**
											选了乡镇街道后默认选择一个派出所
										 */
										combo.findParentByType("form").getForm().findField("pcs").setValue(res.data.code);
										
										var stroe1 = new Ext.data.JsonStore({
											url: 'dict/utils/searchXxb?visiontype=JWHXXB',
											combox:this,
											root: 'list',
											id: this.valueField,
											totalProperty:'totalCount',
											fields: this.fields?this.fields:[this.valueField,this.displayField]
										});
										
										var f = combo.findParentByType("form").getForm().findField("jwh");
										f.setValue("");
										f.store.removeAll();
										stroe1.load({params:{'search_code':res.data.code}});
										
										stroe1.on("load",function(store) {  
											if(store.data.length > 0){
												var list = new Array();
												for(var i=0;i<store.data.length;i++){
													var map =store.data.items;
													list[i] = new Array();
													list[i][0]=map[i].data.code;
													list[i][1]=map[i].data.name;
													list[i][2]=map[i];
												}
												f.store.loadData(list);
											}
										});
										
										var stroe2 = new Ext.data.JsonStore({
									        url: 'dict/utils/searchXxb?visiontype=JWZRQXXB',
									        combox:this,
									        root: 'list',
									        id: this.valueField,
									        totalProperty:'totalCount',
									        fields: this.fields?this.fields:[this.valueField,this.displayField]
										});
										
										var x = combo.findParentByType("form").getForm().findField("zrq");
										x.setValue("");
										x.store.removeAll();
										stroe2.load({params:{'search_code':res.data.code}});
										
										stroe2.on("load",function(store) {  
											if(store.data.length > 0){
												var list = new Array();
												for(var i=0;i<store.data.length;i++){
													var map =store.data.items;
													list[i] = new Array();
													list[i][0]=map[i].data.code;
													list[i][1]=map[i].data.name;
													list[i][2]=map[i];
												}
												x.store.loadData(list);
											}
										});
										
										
										
									}
								}
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
		        	       		xtype:'dict_combox',
								dict:'VisionType=_BLANK',
								anchor:'100%',
								name:'pcs',
								hiddenName:'pcs',
								fieldLabel:'派出所',
								listeners:{
									select:function(combo, res, eOpts ){
										
										//combo.findParentByType("form").getForm().findField("xzjd").setValue(res.data.code);
										
										var stroe1 = new Ext.data.JsonStore({
									        url: 'dict/utils/searchXxb?visiontype=JWHXXB',
									        combox:this,
									        root: 'list',
									        id: this.valueField,
									        totalProperty:'totalCount',
									        fields: this.fields?this.fields:[this.valueField,this.displayField]
										});
										
										var f = combo.findParentByType("form").getForm().findField("jwh");
										f.setValue("");
										f.store.removeAll();
										stroe1.load({params:{'search_code':res.data.code}});
										
										stroe1.on("load",function(store) {  
											if(store.data.length > 0){
												var list = new Array();
												for(var i=0;i<store.data.length;i++){
													var map =store.data.items;
													list[i] = new Array();
													list[i][0]=map[i].data.code;
													list[i][1]=map[i].data.name;
													list[i][2]=map[i];
												}
												f.store.loadData(list);
											}
										});
										
										var stroe2 = new Ext.data.JsonStore({
									        url: 'dict/utils/searchXxb?visiontype=JWZRQXXB',
									        combox:this,
									        root: 'list',
									        id: this.valueField,
									        totalProperty:'totalCount',
									        fields: this.fields?this.fields:[this.valueField,this.displayField]
										});
										
										var x = combo.findParentByType("form").getForm().findField("zrq");
										x.setValue("");
										x.store.removeAll();
										stroe2.load({params:{'search_code':res.data.code}});
										
										stroe2.on("load",function(store) {  
											if(store.data.length > 0){
												var list = new Array();
												for(var i=0;i<store.data.length;i++){
													var map =store.data.items;
													list[i] = new Array();
													list[i][0]=map[i].data.code;
													list[i][1]=map[i].data.name;
													list[i][2]=map[i];
												}
												x.store.loadData(list);
											}
										});
										
									}
								}
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
		        	       		id:'jwhId',
		        	       		xtype:'dict_combox',
								dict:'VisionType=_BLANK',
								anchor:'100%',
								name:'jwh',
								hiddenName:'jwh',
								fieldLabel:'居(村)委会',
								allowBlank:false,
								listeners:{
									select:function(combo, res, eOpts){
										/*
										var stroe = new Ext.data.JsonStore({
									        url: 'dict/utils/searchXxb?visiontype=XZJDXXB',
									        combox:this,
									        root: 'list',
									        id: this.valueField,
									        totalProperty:'totalCount',
									        fields: this.fields?this.fields:[this.valueField,this.displayField]
										});
										
										var f = combo.findParentByType("form").getForm().findField("xzjd");
										f.setValue("");
										f.store.removeAll();
										stroe.load({params:{'search_code':res.data.code}});
										
										stroe.on("load",function(store) {  
											if(store.data.length > 0){
												var list = new Array();
												for(var i=0;i<store.data.length;i++){
													var map =store.data.items;
													list[i] = new Array();
													list[i][0]=map[i].data.code;
													list[i][1]=map[i].data.name;
													list[i][2]=map[i];
												}
												f.store.loadData(list);
											}
										});
										*/
									}
								}
		        	       		
		        	       		
		        	       		/*
								xtype:'search_combox',
								anchor:'100%',
								searchUrl:'dict/utils/searchJwhPcsXzjd',
								fields:["mc","dm","dwdm","dwdm_name","dwdm_bz","xzjddm","xzjddm_name"],
								valueField: "dm",
								displayField: "mc",
								id:'cjwhId',
								allowBlank:false,
								fieldLabel:'居(村)委会',
								hiddenName:'cjwh',
								listeners:{
									select:function(combo, res, eOpts){
										var data = res.data;
										
										Ext.getCmp('pcsId').setValue(data.dwdm_name);
										Ext.getCmp('xzjdId').setValue(data.xzjddm_name);
										
									},
									beforequery:function(event){
										
										this.store.removeAll();
										
										this.store.baseParams["ssxq"] = Ext.getCmp('qhdmA').getValue();
										
										this.store.baseParams["pageSize"] = 10000;
									}
								}
		        	       		*/
		        	       		
		        	       		
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
		        	       		id:'zrqId',
		        	       		xtype:'dict_combox',
								dict:'VisionType=_BLANK',
								anchor:'100%',
								name:'zrq',
								hiddenName:'zrq',
								fieldLabel:'责任区'
//								,allowBlank:false
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'dm',
								allowBlank:false,
								fieldLabel:'门楼牌号'
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'dm',
								allowBlank:false,
								fieldLabel:'门楼详址'
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
	    	 				var rootWin = this.findParentByType("addjthxxwh_window");
	    	 				var form = rootWin.items.get(0);
	    	 				if(!form.getForm().isValid()){
	    						showErr("街路巷信息必须填写！");
	    						return;
	    					}
	    	 				var lhdz = form.getForm().getValues();
	    	 				//alert(Ext.getCmp('qhdmA').getValue());
	    	 				//lhdz.qhdm = Ext.getCmp('qhdmA').getValue();
	    	 				if(rootWin.callback){
								rootWin.callback('lh', lhdz);
							}
							rootWin.hide();
	    	 			}
	    	 		},{
	    	 			text:'取消',
	    	 			minWidth:75,
	    	 			handler:function(){
	    	 				var win = this.findParentByType("addjthxxwh_window");
	    	 				win.hide();
	    	 			}
	    	 		}
	    	 	]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.SelectJthxxWhAdd.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('addjthxxwh_window', Gnt.ux.SelectJthxxWhAdd);
