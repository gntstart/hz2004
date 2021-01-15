Gnt.ux.SfzdyDialog= Ext.extend(Ext.Window, {
	title:'身份证打印',
	closeAction:'hide',
	modal:true,
	width:650,
	height:400,
	margins:'5',
	layout:'border',
	show:function(){
		
		Gnt.ux.SfzdyDialog.superclass.show.call(this);
	},
	setSelRes:function(sel){
		this.selRes = sel;
		//items.items[0].
		this.p2.getForm().setValues({
			xm: sel.xm,
			xb: Gnt.ux.Dict.getDictLable('CS_XB', sel.xb),
			csrq:sel.csrq,
			fzrq:sel.fzrq,
			yxqx:sel.yxqx,
			gmsfzm:sel.gmsfhm,
			slyy: Gnt.ux.Dict.getDictLable('CS_YDZBZYY', sel.bzyy),
			jlxdm: sel.jlx,
			bzfs:Gnt.ux.Dict.getDictLable('CS_YDZBZLB', sel.bzfs)
		});
		Ext.getCmp('pic2Image').body.update("<div id='_PHOTO_ID_new'><IMG SRC='yw/common/img/render/" + sel.zpid + "' width='100%' height='100%' /></DIV>");
		this.p3.items.items[1].items.items[1].items.items[0].setValue(sel.pcs);
		if(sel.bzfs==1){
			Ext.getCmp('kzjsx').disable();
		}else if(sel.bzfs==2){
			Ext.getCmp('kzjsx').enable();
		}
	},
	resetData:function(){
		this.fs.getForm().reset();
	},
	initComponent : function(){
		//10030 Hj_迁入审批信息；10031 Hj_迁入审批子信息；
		if(!Gnt.loadSjpzb("10030,10031",function(){}))
			return;
		
		var returnTitleText = this.returnTitleText;
		if(!returnTitleText || returnTitleText=="") returnTitleText = "身份证打印";
		this.returnTitleText = returnTitleText;
		this.setTitle(returnTitleText);
		this.p2 = new Ext.form.FormPanel({
			region: 'center',
			width:'50%',
            border:false,
            frame:false,
            title:'该人业务已经办理完毕，是否办理如下打印业务？',
            bodyStyle:'padding:5 0 0 10',
	    	height: 80,
	    	labelAlign:'right',
	    	frame:true,
	    	border:false,
	    	fileUpload: true, 
	    	margins:'0',
	        layout:'form',
	        labelWidth:60,
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
							columnWidth:0.4,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
		        				title: '',
		        				id:'pic2Image',
		        				//height:'100%',
		        				bodyStyle:'padding:10px 10px 10px 10px',
		        				html: '<div id="_PHOTO_ID_new">照片</DIV>',
		        				width:150,
		        				height:150,
		        				rowspan: 1,
		        				colspan:1
		        			}]
						},{
			                columnWidth:0.6,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'xm',
								fieldLabel:'姓名'
							}]
						},{
							columnWidth:0.6,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
		        	       		anchor:'100%',
								xtype:'textfield',
								fieldLabel:'性别',
								name:'xb'
							}]
						},{
							columnWidth:0.6,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'csrq',
								fieldLabel:'出生年月'
							}]
						},{
							columnWidth:0.6,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'fzrq',
								allowBlank:false,
								fieldLabel:'发证日期'
							}]
						},{
							columnWidth:0.6,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'yxqx',
								fieldLabel:'有限期限'
							}]
						},{
							columnWidth:0.5,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'bzfs',
								allowBlank:false,
								fieldLabel:'类型'
							}]
						},{
							columnWidth:0.5,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'slyy',
								allowBlank:false,
								fieldLabel:'申领原因'
							}]
						},{
							columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
		        	       		xtype:'search_combox',
								anchor:'100%',
								searchUrl:'dict/utils/searchXxb?visiontype=JLXXXB',
								fields:["code","name"],
								valueField: "code",
								displayField: "name",
								allowBlank:false,
								fieldLabel:'住址',
								hiddenName:'jlxdm'
									
//								xtype:'textfield',
//								anchor:'100%',
//								name:'zz',
//								allowBlank:false,
//								fieldLabel:'住址'
							}]
						},{
							columnWidth:1,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'gmsfzm',
								allowBlank:false,
								fieldLabel:'证号'
							}]
						}
					]
				}]
			}]
		});
		this.p3 = new Ext.Panel({
			region : 'east',
			width:'50%',
			bodyStyle : 'padding:0 10 0 10',
			items:[
				{
	    	    	height:10,
	    	    	border:false,
	    	    	frame:false
	    	    },
				{
	            	title: '身份证领取单相关内容',
	            	region:'center',
		            xtype: 'fieldset',
		            layout:'column',
					height:150,
            		bodyStyle : 'padding:0 10 0 10',
            		defaults:{
	        			border:false,
	        			frame:false
	        		},
		            items: [{
		            	xtype:'checkbox',
		            	columnWidth: 1,
		            	boxLabel: '交回旧证',
		            	name:'jhjz',
		            	listeners:{
		            		'check':function (ck, checked){
		            			if (checked) {
		            			}else{
		            			}
		            		}
		            	}
		            },{
		                columnWidth:1,
	    	           	layout: 'form',
	    	           	bodyStyle:'padding:0 0 0 0',
	        	       	items: [{
							xtype:'search_combox',
							anchor:'100%',
							searchUrl:'dict/utils/searchXxb?visiontype=DWXXB',
							fields:["code","name"],
							valueField: "code",
							displayField: "name",
							allowBlank:false,
							fieldLabel:'派出所',
							hiddenName:'pcs'	
								
						}]
					},{
		                columnWidth:1,
	    	           	layout: 'form',
	        	       	items: [{
							xtype:'textarea',
							anchor:'100%',
							name:'nr',
							labelWidth:30,
							fieldLabel:'内容',
							value:user.personSet.sfzlqdxgnr_lsbz
						}]
					}]
				},{
	    	    	height:10,
	    	    	border:false,
	    	    	frame:false
	    	    },{
	    	    	region:'south',
	    	    	layout:'column',
	    	    	border:false,
	    	    	frame:false,
	    	    	items:[{
	    	    		columnWidth:0.5,
	    	    		border:false,
	    		    	frame:false,
				    	items:[{
					    	region:'center',
					    	 width:180,
					    	 layout:'table',
					    	 layoutConfig: {
					    		columns: 1
					    	 },
					    	 items: [{
			    	    		html:'',
			    	    		bodyStyle:'padding:10px 25px 10px 20px',
				   	    	 	layout:'table',
				   	    	 	align:'east',
				   	    	 	border:false,
				   	    	 	frame:false,
				   	    	 	layoutConfig: {
				   	    	 		columns: 1
				   	    	 	},
				   	    	 	items:[{
						    	    	height:3,
						    	    	border:false,
						    	    	frame:false
						    	    },{
						            	xtype:'checkbox',
						            	columnWidth: 1,
						            	boxLabel: '套打底卡',
						            	name:'tddk',
						            	id:'tddk',
						            	listeners:{
						            		'check':function (ck, checked){
						            			if (checked) {
						            			}else{
						            			}
						            		}
						            	}
						            },{
						    	    	height:3,
						    	    	border:false,
						    	    	frame:false
						    	    },{
						            	xtype:'checkbox',
						            	columnWidth: 1,
						            	boxLabel: '住址折行',
						            	name:'zzzh',
						            	id:'zzzh',
						            	listeners:{
						            		'check':function (ck, checked){
						            			if (checked) {
						            			}else{
						            			}
						            		}
						            	}
						            },{
						    	    	height:3,
						    	    	border:false,
						    	    	frame:false
						    	    },new Ext.Button({
										text:'底卡打印',
										minWidth:100,
										handler:function(){
											var selRes = this.findParentByType("sfzdy_window").selRes;
											if(selRes){
												var arrayTemp=[];
												var o={};
												if(Ext.getCmp("tddk").getValue()){
													o.directTable="dkdyzd";
												}else{
													o.directTable="dkdytd";
												}
												if(Ext.getCmp("zzzh").getValue()){
													o.zzzh=true;
												}else{
													o.zzzh=false;
												}
												o.bzslid =selRes.bzslid;
												arrayTemp.push(o);
												printfunction(0,arrayTemp);
											}
										}
						    	    }),{
						    	    	height:3,
						    	    	border:false,
						    	    	frame:false
						    	    },new Ext.Button({
										text:'快证介绍信',
										minWidth:100,
										id:'kzjsx',
										handler:function(){
											var selRes = this.findParentByType("sfzdy_window").selRes;
											if(selRes){
												var arrayTemp=[];
												var o={};
												o.directTable="kzjsx";
												o.bzslid =selRes.bzslid;
												arrayTemp.push(o);
												printfunction(0,arrayTemp);
											}
										}
									})
						    	 ]
					    	    }]
					    }]
	    	    	},{
	    	    		columnWidth:0.5,
	    	    		border:false,
	    		    	frame:false,
				    	items:[{
					    	region:'east',
					    	 width:180,
					    	 layout:'table',
					    	 layoutConfig: {
					    		columns: 1
					    	 },
					    	 items: [{
			    	    		html:'',
			    	    		bodyStyle:'padding:10px 25px 10px 20px',
				   	    	 	layout:'table',
				   	    	 	align:'east',
				   	    	 	border:false,
				   	    	 	frame:false,
				   	    	 	layoutConfig: {
				   	    	 		columns: 1
				   	    	 	},
				   	    	 	items:[{
						    	    	height:3,
						    	    	border:false,
						    	    	frame:false
						    	    },new Ext.Button({
										text:'身份证领取单',
										minWidth:100,
										handler:function(){
											
										}
						    	    }),{
						    	    	height:3,
						    	    	border:false,
						    	    	frame:false
						    	    },new Ext.Button({
										text:'照相袋',
										minWidth:100,
										handler:function(){
											
										}
						    	    }),{
						    	    	height:3,
						    	    	border:false,
						    	    	frame:false
						    	    },new Ext.Button({
										text:'居民身份证申请表',
										minWidth:100,
										handler:function(){
											window.parent.closeWorkSpace();
										}
						    	    }),{
						    	    	height:3,
						    	    	border:false,
						    	    	frame:false
						    	    },new Ext.Button({
										text:'退出',
										minWidth:100,
										handler:function(){
											var win = this.findParentByType("sfzdy_window");
											win.hide();
										}
									})
						    	 ]
					    	    }]
					    }]
	    	    	}]
	    	    }
			]
		});
		this.items = [this.p2,this.p3];
		
		Gnt.ux.SfzdyDialog.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('sfzdy_window', Gnt.ux.SfzdyDialog);
function CurentTime(type)//  type 0 按本月底  1 按本月初   2 按半年    3 按年度  4 按出生月底   5 其他日期
{ 
    var now = new Date();
    var year = now.getFullYear();       //年
    year = year;
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日
   
    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分
   
    var clock = year + "-";
   
    if(month < 10)
    	month = "0"+month;
        //clock += "0";
   
    clock += month + "-";
   
    if(day < 10)
    	day = "0"+day;
        //clock += "0";
       
    clock += day + " ";
    if(type==0){
    	if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
    		day = 31;
    	}else if(month==4||month==6||month==9||month==11){
    		day = 30;
    	}else if(month==2){
    		day = 28;
    	}
    	return year+""+month+""+day;
    }else if(type==1){
    	return year+""+month+""+'01';
    }else if(type==2){
    	return year+""+'12'+""+'31';
    }else if(type==3){
    	return year+""+'12'+""+'31';
    }else if(type==4){
    	return year+""+'12'+""+'31';
    }else if(type==5){
    	return year+""+month+""+day;
    } 
    
    return clock; 
} 