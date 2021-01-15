//业务报表
Gnt.ux.Selectywbbwhadd= Ext.extend(Ext.Window, {
	title:'业务报表增加',
	closeAction:'hide',
	modal:true,
	width:450,
	height:380,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(){
		Gnt.initCellWeb('cll','Cell');
		Gnt.initCellWeb('cll_zs','Cell_zs');
		Gnt.ux.Selectywbbwhadd.superclass.show.call(this);
	},
	setSelRes:function(){
		this.fs.getForm().setValues({
			
			bbcm :'',
			file: ''
		});
	},
	resetData:function(){
		this.fs.getForm().reset();
	},
	initComponent : function(){
		
		var fs = new Ext.form.FormPanel({
			 id:'form1',
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
	             id : 'bblb',  
	             name : 'ywbblb', 
	             allowBlank : false   
	             //anchor : '90%'  
	         },
	         {  
	             xtype : 'textfield',  
	             fieldLabel : '报表名称',  
	             id : 'bbmc',  
	             name : 'bbcm', 
	             allowBlank : false   
	             //anchor : '90%'  
	         },
	         {  
	        	 xtype: 'textfield',
	        	 fieldLabel: '文件名',
	        	 id : 'file1',
	        	 name: 'file',
	        	 inputType: 'file'
	        	  
	         },
	         {
	        	    id:'cll',
					frame:false,
					border:false,
					html:"<OBJECT id=\"Cell\"  WIDTH=400 HEIGHT=200    classid=\"clsid:3F166327-8030-4881-8BD2-EA25350E574A\" />"								
	 		}
				],
				 listeners : {    
	                 'render' : function(f) {  
	                     this.form.findField('file1').on('render', function() {                    	
	                         Ext.get('file1').on('change',  
	                             function(field, newValue, oldValue) {
	                        	 
	                        	 var cell = document.getElementById("Cell");
	                        	 //cell.Login("浙江金铖华元信息技术有限公司自定义报表-WEB版","","13040351","1340-1275-0110-0004");//1注册成功。0是注册失败
	                             var url =Ext.get('file1').dom.value;                          
	                              var isok=cell.OpenFile(url,""); 

	                                  
	                             
	                            //var xmlcontent = cell.SaveToXML('');
	                           // alert(xmlcontent);
	                            //cell.ReadFromXML(xmlcontent);                            
	                            
	                            
	                         }, this);     
	                     }, this);     
	                 }     
	             },
				 buttons : [{  
		             text : '确认',
		             style:'margin-left:100px',
		             handler : function() { 
		                 
		                 var winpar = this.findParentByType("addywbbwh_window");
	    	 				var form = winpar.items.get(0);
	    	 				if (!form.getForm().isValid()) {return;}  
	    	 				var ywbbmc = Ext.getCmp("bbmc").getValue();	
	    	 				var ywbbmb = document.getElementById("Cell").SaveToBase64Str();	 				
	    	 				if(winpar.callback){
	    	 					winpar.callback(ywbbmc,ywbbmb);
							}
	    	 				winpar.hide();  
							
		             }  
		         }, {  
		             text : '取消',  
		             handler : function() {
		            	  Ext.getCmp("form1").form.reset();
		            	 //var file=document.getElementById("file1");
		            	 //file.outerHTML = file.outerHTML;
		            	 //file.select(); 
		            	//document.selection.clear();
		            	  document.getElementById("Cell").ResetContent();
		            	  var win = this.findParentByType("addywbbwh_window");
		            	 win.hide();
		                 
		             }  
		         }]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.Selectywbbwhadd.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('addywbbwh_window', Gnt.ux.Selectywbbwhadd);

//制式报表
Gnt.ux.Selectzsbbwhadd= Ext.extend(Ext.Window, {
	title:'制式报表增加',
	closeAction:'hide',
	modal:true,
	width:450,
	height:380,
	margins:'5',
	layout:'fit',
	html:'',
	show:function(){
		Gnt.ux.Selectzsbbwhadd.superclass.show.call(this);
	},
	resetData:function(){
		this.fs.getForm().reset();
	},
	initComponent : function(){
		
		var fs = new Ext.form.FormPanel({
			 id:'form_zs',
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
	             id : 'bblb_zs',  
	             name : 'bblb_zs', 
	             allowBlank : false   
	             //anchor : '90%'  
	         },
	         {  
	             xtype : 'textfield',  
	             fieldLabel : '报表名称',  
	             id : 'bbmc_zs',  
	             name : 'bbcm_zs', 
	             allowBlank : false   
	             //anchor : '90%'  
	         },
	         {  
	        	 xtype: 'textfield',
	        	 fieldLabel: '文件名',
	        	 id : 'file1_zs',
	        	 name: 'file_zs',
	        	 inputType: 'file'
	        	  
	         },
	         {
	        	    id:'cll_zs',
					frame:false,
					border:false,
					html:"<OBJECT id=\"Cell_zs\"  WIDTH=400 HEIGHT=200    classid=\"clsid:3F166327-8030-4881-8BD2-EA25350E574A\" />"								
	 		}
				],
				 listeners : {    
	                 'render' : function(f) {  
	                     this.form.findField('file1_zs').on('render', function() {                    	
	                         Ext.get('file1_zs').on('change',  
	                             function(field, newValue, oldValue) {
	                        	 
	                        	 var cell = document.getElementById("Cell_zs");
	                        	 //cell.Login("浙江金铖华元信息技术有限公司自定义报表-WEB版","","13040351","1340-1275-0110-0004");//1注册成功。0是注册失败
	                             var url =Ext.get('file1_zs').dom.value;                          
	                              var isok=cell.OpenFile(url,""); 

	                                  
	                             
	                            var xmlcontent = cell.SaveToXML('');
	                           // alert(xmlcontent);
	                            //cell.ReadFromXML(xmlcontent);                            
	                            
	                            
	                         }, this);     
	                     }, this);     
	                 }     
	             },
				 buttons : [{  
		             text : '确认',
		             style:'margin-left:100px',
		             handler : function() { 
		                 
		                 var winpar = this.findParentByType("addzsbbwh_window");
	    	 				var form = winpar.items.get(0);
	    	 				if (!form.getForm().isValid()) {return;}  
	    	 				var zsbbmc = Ext.getCmp("bbmc_zs").getValue();				 	
	    	 				if(winpar.callback){
	    	 					winpar.callback(zsbbmc);
							}
	    	 				winpar.hide();  
							
		             }  
		         }, {  
		             text : '取消',  
		             handler : function() {
		            	  Ext.getCmp("form_zs").form.reset();
		            	 //var file=document.getElementById("file1");
		            	 //file.outerHTML = file.outerHTML;
		            	 //file.select(); 
		            	//document.selection.clear();
		            	  document.getElementById("Cell_zs").ResetContent();
		            	 var win = this.findParentByType("addzsbbwh_window");
		            	 win.hide();
		                 
		             }  
		         }]
		});
		
		this.fs = fs;
		this.items = fs;
		
		Gnt.ux.Selectzsbbwhadd.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('addzsbbwh_window', Gnt.ux.Selectzsbbwhadd);
