Gnt.ux.Selectywbbwhup= Ext.extend(Ext.Window, {
	title:'业务报表修改',
	closeAction:'hide',
	modal:true,
	width:450,
	height:380,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(){
		Gnt.initCellWeb('cllup','Cellup');
		Gnt.initCellWeb('cllup_zs','Cellup_zs');
		Gnt.ux.Selectywbbwhup.superclass.show.call(this);
	},

	setSelRes:function(bblb,bbmb,bbmc){

		this.fs.getForm().setValues({
			ywbblb:bblb,
			bbcm :bbmc,
			file: ''
		});

		var cell = document.getElementById("Cellup");
		//cell.Login("浙江金铖华元信息技术有限公司自定义报表-WEB版","","13040351","1340-1275-0110-0004");
		cell.ReadFromBase64Str(bbmb);
		
	},
	resetData:function(){
		this.fs.getForm().reset();
	},
	initComponent : function(){
		
		var fs = new Ext.form.FormPanel({
			 id:'formup',
	         region : 'center',  
	         labelWidth : 100,  
	         frame : true,  
	         bodyStyle : 'padding:5px', 
	         border : true,  
	         layout:'form',
	         //style:'margin-left:150px',
	         fileUpload: true,
	         items : [
	                  {
	             xtype : 'hidden',  
	             fieldLabel : '报表模板',  
	             id : 'ywbbmb',  
	             name : 'ywbbmb', 
	             allowBlank : false   
	             //anchor : '90%'  
	         },{
	             xtype : 'textfield',  
	             fieldLabel : '报表类别',  
	             id : 'bblbup',  
	             name : 'ywbblb', 
	             allowBlank : false   
	             //anchor : '90%'  
	         },
	         {  
	             xtype : 'textfield',  
	             fieldLabel : '报表名称',  
	             id : 'bbmcup',  
	             name : 'bbcm', 
	             allowBlank : false   
	             //anchor : '90%'  
	         },
	         {  
	        	 xtype: 'textfield',
	        	 fieldLabel: '文件名',
	        	 id : 'fileup',
	        	 name: 'file',
	        	 inputType: 'file'
	        	  
	         },
	         {
	        	    id:'cllup',
					frame:false,
					border:false,
					html:"<OBJECT id=\"Cellup\"  WIDTH=400 HEIGHT=200    classid=\"clsid:3F166327-8030-4881-8BD2-EA25350E574A\" />"								
	 		}
				],
				 listeners : {    
	                 'render' : function(f) {  
	                     this.form.findField('fileup').on('render', function() {                    	
	                         Ext.get('fileup').on('change',  
	                             function(field, newValue, oldValue) {	                
	                        	 var cell = document.getElementById("Cellup");
	                        	 cell.Login("浙江金铖华元信息技术有限公司自定义报表-WEB版","","13040351","1340-1275-0110-0004");//1注册成功。0是注册失败
	                             var url =Ext.get('fileup').dom.value;  
	                             var bbmbContent=document.getElementById("ywbbmb").value;	                            	                           
	                              var isok=cell.OpenFile(url,"");
	                              var strmb=cell.SaveToBase64Str();	                              
	                             
	                         }, this);     
	                     }, this);     
	                 }  
	             },
				 buttons : [{  
		             text : '确认',
		             style:'margin-left:100px',
		             handler : function() { 
		            	 var winpar = this.findParentByType("upywbbwh_window");
	    	 				var form = winpar.items.get(0);
	    	 				if (!form.getForm().isValid()) {return;}  
	    	 				var ywbbmc = Ext.getCmp("bbmcup").getValue();
                       	    var cell = document.getElementById("Cellup");
                       	    var xmlcontent = cell.SaveToXML('');            
                       	    var bbmbContent=document.getElementById("Cellup").SaveToBase64Str();                      	                          	    
	    	 				if(winpar.callback){
	    	 					winpar.callback(ywbbmc,bbmbContent);
							}
	    	 				winpar.hide();  
		             }  
		         }, {  
		             text : '取消',  
		             handler : function() {
		            	 Ext.getCmp("formup").form.reset();
		            	 document.getElementById("Cellup").ResetContent();
		            	 var win = this.findParentByType("upywbbwh_window");
		            	 win.hide();
		                 
		             }  
		         }]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.Selectywbbwhup.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('upywbbwh_window', Gnt.ux.Selectywbbwhup);

//制式报表修改
Gnt.ux.Selectzsbbwhup= Ext.extend(Ext.Window, {
	title:'制式报表修改',
	closeAction:'hide',
	modal:true,
	width:450,
	height:380,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(){
		Gnt.ux.Selectzsbbwhup.superclass.show.call(this);
	},
	setSelRes:function(){
		this.fs.getForm().setValues({
			ywbblb:'',
			bbcm :'',
			file: ''
		});
	},
	resetData:function(){
		this.fs.getForm().reset();
	},
	initComponent : function(){
		
		var fs = new Ext.form.FormPanel({
			 id:'formup_zs',
	         region : 'center',  
	         labelWidth : 100,  
	         frame : true,  
	         bodyStyle : 'padding:5px', 
	         border : true,  
	         layout:'form',
	         //style:'margin-left:150px',
	         fileUpload: true,
	         items : [{
	             xtype : 'textfield',  
	             fieldLabel : '报表类别',  
	             id : 'bblbup_zs',  
	             name : 'ywbblb_zs', 
	             allowBlank : false   
	             //anchor : '90%'  
	         },
	         {  
	             xtype : 'textfield',  
	             fieldLabel : '报表名称',  
	             id : 'bbmcup_zs',  
	             name : 'bbcm_zs', 
	             allowBlank : false   
	             //anchor : '90%'  
	         },
	         {  
	        	 xtype: 'textfield',
	        	 fieldLabel: '文件名',
	        	 id : 'fileup_zs',
	        	 name: 'file_zs',
	        	 inputType: 'file'
	        	  
	         },
	         {
	        	    id:'cllup_zs',
					frame:false,
					border:false,
					html:"<OBJECT id=\"Cellup_zs\"  WIDTH=400 HEIGHT=200    classid=\"clsid:3F166327-8030-4881-8BD2-EA25350E574A\" />"								
	 		}
				],
				 listeners : {    
	                 'render' : function(f) {  
	                     this.form.findField('fileup_zs').on('render', function() {                    	
	                         Ext.get('fileup_zs').on('change',  
	                             function(field, newValue, oldValue) {
	                        	 
	                        	 var cell = document.getElementById("Cellup_zs");
	                        	 //cell.Login("浙江金铖华元信息技术有限公司自定义报表-WEB版","","13040351","1340-1275-0110-0004");//1注册成功。0是注册失败
	                             var url =Ext.get('fileup_zs').dom.value;                         
	                              var isok=cell.OpenFile(url,""); 	                                  	                             	                             
	                            //cell.ReadFromXML(xmlcontent);                            
	                            
	                            
	                         }, this);     
	                     }, this);     
	                 }     
	             },
				 buttons : [{  
		             text : '确认',
		             style:'margin-left:100px',
		             handler : function() { 
		            	 var winpar = this.findParentByType("upzsbbwh_window");
	    	 				var form = winpar.items.get(0);
	    	 				if (!form.getForm().isValid()) {return;}  
	    	 				var ywbbmc = Ext.getCmp("bbmcup_zs").getValue();
                       	    var cell = document.getElementById("Cellup_zs");
                       	    var xmlcontent = cell.SaveToXML('');                       	    	 				
	    	 				if(winpar.callback){
	    	 					winpar.callback(ywbbmc);
							}
	    	 				winpar.hide();  
		             }  
		         }, {  
		             text : '取消',  
		             handler : function() {
		            	 Ext.getCmp("formup_zs").form.reset();
		            	 document.getElementById("Cellup_zs").ResetContent();
		            	 var win = this.findParentByType("upzsbbwh_window");
		            	 win.hide();
		                 
		             }  
		         }]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.Selectzsbbwhup.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('upzsbbwh_window', Gnt.ux.Selectzsbbwhup);
