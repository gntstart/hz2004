Gnt.ux.SelectDwxxWhAdd= Ext.extend(Ext.Window, {
	title:'单位信息新增',
	closeAction:'hide',
	modal:true,
	width:600,
	height:400,
	margins:'5',
	autoScroll : true,	
	layout:'border',
	html:'',
	show:function(){
		Gnt.ux.SelectDwxxWhAdd.superclass.show.call(this);
	},
	setSelRes:function(errorFlag){
		if(errorFlag){
			this.fs.getForm().setValues({
				qhdm: '',
				dm: '',
				mc:'',
				zwpy: '',
				wbpy: '',
				dwjgdm:'',
				csb1mc: '',
				fjjgdm: '',
				fjjgmc:'',
				dz:'',
				bz:''
			});
		}
		
	},
	resetData:function(){
		this.fs.getForm().reset();
	},
	initComponent : function(){
		
		this.fs = new Ext.form.FormPanel({
			height: 200,
	    	region: 'center',
	    	anchor:'100% 100%',
	    	buttonAlign:'right',
	    	labelAlign:'right',
	    	frame:true,
	    	border:false,
	    	autoScroll : true,
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
			                columnWidth:.5,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'search_combox',
								anchor:'100%',
								searchUrl:'dict/utils/searchXzqh.json?qybz=1',
								fields:["code","name"],
								valueField: "code",
								displayField: "name",
//								id:'xzqhbdm',
								allowBlank:false,
								fieldLabel:'所属区划',
								hiddenName:'qhdm'
//								hiddenName:'xzqhbdm',
//								name:'xzqhbmc'
							}]
						},{
				                columnWidth:.5,
			    	           	layout: 'form',
			    	           	bodyStyle:'padding:5 0 0 0',
			        	       	items: [{
									xtype:'textfield',
									anchor:'100%',
									name:'dm',
									allowBlank:false,
									fieldLabel:'单位代码'
								}]
						},{
			                columnWidth:.5,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'mc',
								allowBlank:false,
								fieldLabel:'单位名称',
			                    listeners: {
			                    	change: function (me, newValue, oldValue, eOpts) {
			                    		Ext.getCmp('zwpy1').setValue(pinyin.getCamelChars(newValue));
							        }
			                    }
							}]
						},{
			                columnWidth:.5,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								id:'zwpy1',
								name:'zwpy',
								allowBlank:false,
								fieldLabel:'单位中文拼音',
								listeners:{
								}
							}]
						},{
			                columnWidth:.5,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'wbpy',
								fieldLabel:'单位五笔拼音'
							}]
						},{
			                columnWidth:.5,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'dwjgdm',
								autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '12'},
								allowBlank:false,
								fieldLabel:'单位机构代码'
							}]
						},{
			                columnWidth:.5,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype : "combo",
			            		store : [['0','派出所级'],['1','其他级']],
		            			border:false,
		            			frame:false,
		            			id:'newcdwjb',
		            			name:'csb1mc',
		            			fieldLabel:"单位级别",
		            			width:60,
		            			allowBlank:false,
		            			//editable:false,
		            			triggerAction:"all",
		            			maxHeight : 80,
		            			anchor : '100%'
							}]
						},{
			                columnWidth:.5,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'fjjgdm',
								autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '12'},
								allowBlank:false,
								fieldLabel:'分局机构代码'
							}]
						},{
			                columnWidth:.5,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'fjjgmc',
								allowBlank:false,
								fieldLabel:'分局机构名称'
							}]
						},{
			                columnWidth:.5,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'dz',
								fieldLabel:'地址'
							}]
						},{
			                columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textarea',
								height:60,
								anchor:'100%',
								name:'bz',
//								allowBlank:false,
								fieldLabel:'备注'
							}]
						}
					]
				}]
			}]
		});
		this.zpformpanel = new Ext.form.FormPanel({
			region: 'south',
		    title: '',
		    width: 600,
		    height: 180,
		    x: 5,
		    y: 5,
		    autoScroll : true,	
		    layout: "form",
		    bodyPadding: "5",
		    defaultType: "textfield",
		    allowBlank:false,
		    fileUpload: true,
		    fieldDefaults: {labelAlign: "left", labelWidth: 55},
		    items: [
		        {
		            xtype: 'textfield',
		            id: 'logoFile',
		            name: 'logoFile',
		            inputType: 'file',
		            fieldLabel: '派出所公章',
		            width: 80,
		            listeners: {
		                'render': function () {
		                    var logoFileCmp = Ext.get('logoFile');
		                    logoFileCmp.on('change',
		                        function (field, newValue, oldValue) {
		                        var picPath = logoFileCmp.getValue();
		                        if(picPath == ""){
		                            return;
		                        }
		                        var url = 'file:///' + picPath;
		                        var arr = url.split('.');
		                        if (!((arr[arr.length - 1] == 'jpg') || (arr[arr.length - 1] == 'png') || (arr[arr.length - 1] == 'bmp'))) {
		                            alert('文件不合法，请上传后缀为*.jpg;*.png;*.bmp的照片文件!');
		                            return;
		                        }
		                        if (Ext.isIE) {
		                            var image = Ext.get('logoPic').dom;
		                            image.src = Ext.BLANK_IMAGE_URL;
		                            image.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = url;
		                        } else {
		                            //支持FF
		                            var oFReader = new FileReader();
		                            oFReader.readAsDataURL(document.getElementById("logoFile").files[0]);
		                            oFReader.onload = function (oFREvent) {
		                                document.getElementById("logoPic").src = oFREvent.target.result;
		                            }
		                        }
		                    }, this);
		                }
		            }
		        },
		        {
		            xtype: 'box',
		            id: 'logoPic',
		            width: 100,
		            height: 100,
		            autoEl: {
		                tag: 'img',
		                src: '',
		                style: 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);'
		            }
		        }
		    ],
		    buttons: [
		        {
		            text: "确定",
		            handler: function () {
		            	var win = this.findParentByType("adddwxxwh_window");
		            	var form = win.items.get(0);
    	 				if(!form.getForm().isValid()){
							showErr("单位信息必须填写！");
							return;
						}
    	 				var lhdz = form.getForm().getValues();
    	 				lhdz.dwjb = Ext.getCmp('newcdwjb').getValue();
		                var photoName = document.getElementById("logoFile").value;
		                	win.zpformpanel.getForm().submit({
		                        url: 'gl/xtmbgl/dwxxwh/addDwDm',
		                        method:'post',
		                        params: lhdz,
		                        success: function (form, action) {
		                            showInfo("照片上传成功！");
		                            if(win.callback){
		                            	win.callback('lh', lhdz);
									}
		                            win.hide();
		                        },
		                        failure: function (form, action) {
		                            showInfo(action.response.responseText);
		                        }
		                    });

		            }
		        }, {
		            text: '关闭',
		            handler: function () {
		            	var win = this.findParentByType("adddwxxwh_window");
		                win.hide();
		            }
		        }
		    ]

		});
		this.items = [this.fs, this.zpformpanel];
		Gnt.ux.SelectDwxxWhAdd.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('adddwxxwh_window', Gnt.ux.SelectDwxxWhAdd);
