Gnt.ux.SelectDwxxWhModify= Ext.extend(Ext.Window, {
	title:'单位信息修改',
	closeAction:'hide',
	modal:true,
	width:600,
	height:400,
	margins:'5',
	layout:'border',
	html:'',
	show:function(selRes){
		this.data = selRes.data;
		Gnt.ux.SelectDwxxWhModify.superclass.show.call(this);
	},
	setSelRes:function(selRes){
		this.selRes = selRes;
		if(selRes){
			this.fs.getForm().setValues({
				qhdm: selRes.data.xzqhbdm,
				dm: selRes.data.dm,
				mc: selRes.data.mc,
				dhhm:selRes.data.dhhm,////表单增加电话号码   20210220
				zwpy: selRes.data.zwpy,
				wbpy:selRes.data.wbpy,
				dwjgdm:selRes.data.dwjgdm,
				csb1mc: selRes.data.csb1dm,
				fjjgdm:selRes.data.fjjgdm,
				fjjgmc:selRes.data.fjjgmc,
				dz:selRes.data.dz,
				bz:selRes.data.bz
			});
			if(selRes.data.zp){
				document.getElementById("logoPicModify").src = 'yw/common/img/dwrender/'+selRes.data.dm;
			}else{
				document.getElementById("logoPicModify").src ='';
			}
			this.doLayout();
			//selRes.data.zpstr;
			//Ext.getCmp('logoPicModify').body.update('<div ><img src="data:image/jpg;base64,' + selRes.data.zp + '" width="100%" /></DIV>');
		}
	},
	resetData:function(){
		this.fs.getForm().reset();
	},
	initComponent : function(){
		
		this.fs = new Ext.form.FormPanel({
	    	height: 400,
	    	region: 'center',
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
		                columnWidth:.5,
	    	           	layout: 'form',
	    	           	bodyStyle:'padding:5 0 0 0',
	        	       	items: [{
							xtype:'textfield',
							anchor:'100%',
							name:'qhdm',
							allowBlank:false,
							disabled:true,
							fieldLabel:'所属区划'
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
								disabled:true,
								fieldLabel:'单位代码'
							}]
						},{
			                columnWidth:.5,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'dwjgdm',
								disabled:true,
								fieldLabel:'单位机构代码'
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
			                    		Ext.getCmp('zwpy2').setValue(pinyin.getCamelChars(newValue));
							        }
			                    }
							}]
						},{//表单增加电话号码   20210220
			                columnWidth:.5,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'dhhm',
								allowBlank:false,
								fieldLabel:'电话号码'
							}]
					},{
			                columnWidth:.5,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								id:'zwpy2',
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
								xtype : "combo",
			            		store : [['0','派出所级'],['1','其他级']],
		            			border:false,
		            			frame:false,
		            			id:'newmc',
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
		    	           	bodyStyle:'padding:0 0 0 0',
		        	       	items: [{
								xtype:'textarea',
								height:60,
								anchor:'100%',
								name:'bz',
								fieldLabel:'备注'
							}]
						}
				]
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
		            id: 'logoFileModify',
		            name: 'logoFile',
		            inputType: 'file',
		            fieldLabel: '派出所公章',
		            width: 80,
		            listeners: {
		                'render': function () {
		                    var logoFileCmp = Ext.get('logoFileModify');
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
		                            var image = Ext.get('logoPicModify').dom;
		                            image.src = Ext.BLANK_IMAGE_URL;
		                            image.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = url;
		                        } else {
		                            //支持FF
		                            var oFReader = new FileReader();
		                            oFReader.readAsDataURL(document.getElementById("logoFileModify").files[0]);
		                            oFReader.onload = function (oFREvent) {
		                                document.getElementById("logoPicModify").src = oFREvent.target.result;
		                            }
		                        }
		                    }, this);
		                }
		            }
		        },
		        {
		            xtype: 'box',
		            id: 'logoPicModify',
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

    	 				var win = this.findParentByType("modifydwxxwh_window");
    	 				var form = win.items.get(0);
    	 				if(!form.getForm().isValid()){
    						showErr("数据字典必须填写！");
    						return;
    					}
    	 				var lhdz = form.getForm().getValues();
//						if(rootWin.moreData){
//    	 					for(o in rootWin.moreData)
//    	 						lhdz[o] = rootWin.moreData[o];
//    	 				}
    	 				lhdz.qhdm = win.data.xzqhbdm;
    	 				lhdz.dm = win.data.dm;
    	 				lhdz.dwjgdm = win.data.dwjgdm;
    	 				lhdz.dwjb = Ext.getCmp('newmc').getValue();
		                var photoName = document.getElementById("logoFileModify").value;
	                	win.zpformpanel.getForm().submit({
	                        url: 'gl/xtmbgl/dwxxwh/modifyDwDm',
	                        method:'post',
	                        params: lhdz,
	                        success: function (form, action) {
	                            showInfo("单位修改成功！");
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
    	 				var win = this.findParentByType("modifydwxxwh_window");
    	 				win.hide();
    	 			}
		        }
		    ]

		});
		this.items = [this.fs, this.zpformpanel];
		Gnt.ux.SelectDwxxWhModify.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('modifydwxxwh_window', Gnt.ux.SelectDwxxWhModify);
