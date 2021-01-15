Gnt.ux.ZjblOneDialog= Ext.extend(Ext.Window, {
	title:'请选择签发日期',
	closeAction:'hide',
	modal:true,
	width:500,
	height:350,
	margins:'5',
	layout:'border',
	show:function(){
		
		Gnt.ux.ZjblOneDialog.superclass.show.call(this);
	},
	setSelRes:function(sel){
		this.selRes = sel;
		Ext.getCmp('fzrq').setValue(CurentTime(0));
		this.p2.items.items[0].items.items[0].setValue(true);
		this.p2.items.items[0].items.items[1].setValue(false);
		this.p2.items.items[0].items.items[2].setValue(false);
		this.p2.items.items[0].items.items[3].setValue(false);
		this.p2.items.items[0].items.items[4].setValue(false);
		this.p2.items.items[0].items.items[5].setValue(false);
		//this.p2.items.items[0].items.get(0).setValue(true);
		//this.p2.items.items[0].items.items[0].checked=true;
		//this.p2.items.items[0].items.get(0).setValue(true);
	},
	resetData:function(){
		this.fs.getForm().reset();
	},
	initComponent : function(){
		//10030 Hj_迁入审批信息；10031 Hj_迁入审批子信息；
		if(!Gnt.loadSjpzb("10030,10031",function(){}))
			return;
		
		var returnTitleText = this.returnTitleText;
		if(!returnTitleText || returnTitleText=="") returnTitleText = "请选择签发日期";
		
		this.returnTitleText = returnTitleText;
		
		this.setTitle(returnTitleText);
		
		
		this.fs = new Ext.Panel({
			xtype: 'panel',
			border:false,
			frame: false,
	    	region:'north',
	    	height: 35,
	    	bodyStyle: 'padding:5px',
			layout:'table',
			layoutConfig: {
	        	columns: 30
	        },
	        items:[
				{
					frame:false,
					border:false,
					xtype:'panel',
					html:'',
					width:10
	    		},{
						xtype:'textfield',
						anchor:'100%',
						id:'fzrq',
						name:'fzrq',
						//disabled:true,
						fieldLabel:'发证日期为'
				},{
					frame:false,
					border:false,
					xtype:'panel',
					html:'',
					width:10
	    		},{
	    			frame:false,
					border:false,
    	       		id:'blfsValue',
					html:'普通证'
				}]
	    });
		
		this.p2 = new Ext.Panel({
			region: 'center',
			width:'50%',
            border:false,
            frame:false,
            bodyStyle:'padding:5 0 0 10',
            items:[
            	{
	    	    	height:20,
	    	    	border:false,
	    	    	frame:false
	    	    },
				{
	    	    	id:'fwId',
	            	title: '',
	            	region:'center',
		            xtype: 'fieldset',
		            layout:'column',
		            autoHeight: true,
            		bodyStyle : 'padding:0 10 0 10',
					defaults:{
		            	columnWidth: 1,
		            	xtype:'radio',
		            	fieldLabel: '',
		            	name: 'fw'
		            },
		            items: [{
		            	id:'r11',
		            	boxLabel: '按本月底',
		            	name:'fw',
		            	inputValue : "1",
		            	listeners:{
		            		'check' : function(checkbox, checked){ 
		                         if(checked){//只有在点击时触发
		                        	 Ext.getCmp('qtrq').disable();
		                        	 Ext.getCmp('fzrq').setValue(CurentTime(0));
		                         }  
		                    }
		                //监听事件
			            }
		            },{
		            	id:'r12',
		            	boxLabel: '按本月初',
		            	name:'fw',
		            	inputValue : "2",
		            	listeners:{
		            		'check' : function(checkbox, checked){ 
		                         if(checked){//只有在点击时触发
		                        	 Ext.getCmp('qtrq').disable();
		                        	 Ext.getCmp('fzrq').setValue(CurentTime(1));
		                        	// Ext.getCmp('fzrq').body.update(CurentTime(1));
		                         }  
		                    }
		                //监听事件
			            }
		            },{
		            	id:'r13',
		            	boxLabel: '按半年',
		            	name:'fw',
		            	inputValue : "3",
		            	listeners:{
		            		'check' : function(checkbox, checked){ 
		                         if(checked){//只有在点击时触发
		                        	 Ext.getCmp('qtrq').disable();
		                        	 Ext.getCmp('fzrq').setValue(CurentTime(2))
		                         }  
		                    }
		                //监听事件
			            }
		            },{
		            	id:'r14',
		            	boxLabel: '按年度',
		            	name:'fw',
		            	inputValue : "4",
		            	listeners:{
		            		'check' : function(checkbox, checked){ 
		                         if(checked){//只有在点击时触发
		                        	 Ext.getCmp('qtrq').disable();
		                        	 Ext.getCmp('fzrq').setValue(CurentTime(3))
		                         }  
		                    }
		                //监听事件
			            }
		            },{
		            	id:'r15',
		            	boxLabel: '按出生月底',
		            	name:'fw',
		            	inputValue : "5",
		            	listeners:{
		            		'check' : function(checkbox, checked){ 
		                         if(checked){//只有在点击时触发
		                        	 Ext.getCmp('qtrq').disable();
		                        	 Ext.getCmp('fzrq').setValue(CurentTime(4))
		                         }  
		                    }
		                //监听事件
			            }
		            },{
		            	id:'r16',
		            	boxLabel: '其他日期',
		            	name:'fw',
		            	inputValue : "6",
		            	listeners:{
		            		'check' : function(checkbox, checked){ 
		                         if(checked){//只有在点击时触发
		                        	 Ext.getCmp('qtrq').enable();
		                        	 Ext.getCmp('fzrq').setValue(CurentTime(5))
		                        	 Ext.getCmp('qtrq').setValue(CurentTime(5))
		                         }  
		                    }
		                //监听事件
			            }
		            }]
				},{
	        		xtype:'datefield',
					format:'Ymd',
					anchor:'100%',
					id:'qtrq',
					name:'qtrq',
					disabled:true,
					fieldLabel:'其他日期',
	            	listeners:{
	            		'check' : function(checkbox, checked){ 
	                         if(checked){//只有在点击时触发
	                        	 Ext.getCmp('fzrq').setValue(CurentTime(0))
	                         }  
	                    }
	                //监听事件
		            }
	            }
//            	{
//            	id:'fwId',
//            	title: '',
////				columnWidth: 3,
//	            xtype: 'fieldset',
//	            autoHeight: true,
//	            layout:'border',
//	            frame:false,
//    	    	border:false,
//	            defaults:{
//	            	xtype:'radio',
//	            	columnWidth: .3,
//	            	border:false,
//	                frame:false,
//	            	fieldLabel: '',
//	            	name: 'fw'
//	            },
//	            items: [{
//	            	id:'r11',
//	            	boxLabel: '按本月底',
//	            	name:'fw',
//	            	inputValue : "1",
//	            	listeners:{
//	            		'check' : function(checkbox, checked){ 
//	                         if(checked){//只有在点击时触发
//	                        	 Ext.getCmp('qtrq').disable();
//	                        	 Ext.getCmp('fzrq').setValue(CurentTime(0));
//	                         }  
//	                    }
//	                //监听事件
//		            }
//	            },{
//	            	id:'r12',
//	            	boxLabel: '按本月初',
//	            	name:'fw',
//	            	inputValue : "2",
//	            	listeners:{
//	            		'check' : function(checkbox, checked){ 
//	                         if(checked){//只有在点击时触发
//	                        	 Ext.getCmp('qtrq').disable();
//	                        	 Ext.getCmp('fzrq').setValue(CurentTime(1));
//	                        	// Ext.getCmp('fzrq').body.update(CurentTime(1));
//	                         }  
//	                    }
//	                //监听事件
//		            }
//	            },{
//	            	id:'r13',
//	            	boxLabel: '按半年',
//	            	name:'fw',
//	            	inputValue : "3",
//	            	listeners:{
//	            		'check' : function(checkbox, checked){ 
//	                         if(checked){//只有在点击时触发
//	                        	 Ext.getCmp('qtrq').disable();
//	                        	 Ext.getCmp('fzrq').setValue(CurentTime(2))
//	                         }  
//	                    }
//	                //监听事件
//		            }
//	            },{
//	            	id:'r14',
//	            	boxLabel: '按年度',
//	            	name:'fw',
//	            	inputValue : "4",
//	            	listeners:{
//	            		'check' : function(checkbox, checked){ 
//	                         if(checked){//只有在点击时触发
//	                        	 Ext.getCmp('qtrq').disable();
//	                        	 Ext.getCmp('fzrq').setValue(CurentTime(3))
//	                         }  
//	                    }
//	                //监听事件
//		            }
//	            },{
//	            	id:'r15',
//	            	boxLabel: '按出生月底',
//	            	name:'fw',
//	            	inputValue : "5",
//	            	listeners:{
//	            		'check' : function(checkbox, checked){ 
//	                         if(checked){//只有在点击时触发
//	                        	 Ext.getCmp('qtrq').disable();
//	                        	 Ext.getCmp('fzrq').setValue(CurentTime(4))
//	                         }  
//	                    }
//	                //监听事件
//		            }
//	            },{
//	            	id:'r16',
//	            	boxLabel: '其他日期',
//	            	name:'fw',
//	            	inputValue : "6",
//	            	listeners:{
//	            		'check' : function(checkbox, checked){ 
//	                         if(checked){//只有在点击时触发
//	                        	 Ext.getCmp('qtrq').enable();
//	                        	 Ext.getCmp('fzrq').setValue(CurentTime(5))
//	                        	 Ext.getCmp('qtrq').setValue(CurentTime(5))
//	                         }  
//	                    }
//	                //监听事件
//		            }
//	            }]
//			},{
//        		xtype:'datefield',
//				format:'Ymd',
//				anchor:'100%',
//				id:'qtrq',
//				name:'qtrq',
//				disabled:true,
//				fieldLabel:'其他日期',
//            	listeners:{
//            		'check' : function(checkbox, checked){ 
//                         if(checked){//只有在点击时触发
//                        	 Ext.getCmp('fzrq').setValue(CurentTime(0))
//                         }  
//                    }
//                //监听事件
//	            }
//            }
			]
        });
		this.p3 = new Ext.Panel({
			region : 'east',
			width:'50%',
			bodyStyle : 'padding:5',
			items:[
				{
	    	    	height:10,
	    	    	border:false,
	    	    	frame:false
	    	    },
				{
	            	id:'centerId',
	            	title: '办理方式',
	            	region:'center',
		            xtype: 'fieldset',
		            layout:'column',
					height:80,
            		bodyStyle : 'padding:0 10 0 10',
					defaults:{
		            	columnWidth: 1,
		            	xtype:'radio',
		            	fieldLabel: '',
		            	name: 'fs'
		            },
		            items: [{
		            	id:'blfs1',
		            	boxLabel: '普通证',
		            	name:'fs',
		            	inputValue : "1",
		            	checked:true,
		            	listeners:{
		            		'check' : function(checkbox, checked){ 
		                         if(checked){//只有在点击时触发
		                        	 Ext.getCmp('blfsValue').body.update('普通证');
		                         }  
		                    }
		                //监听事件
			            }
		            },{
		            	id:'blfs2',
		            	boxLabel: '快证',
		            	name:'fs',
		            	inputValue : "2",
		            	listeners:{
		            		'check' : function(checkbox, checked){ 
		                         if(checked){//只有在点击时触发
		                        	 Ext.getCmp('blfsValue').body.update('快证');
		                         }  
		                    }
		                //监听事件
			            }
		            }]
				},{
	            	id:'c21',
	            	xtype:'checkbox',
	            	boxLabel: '打印居民身份证申请表',
	            	name:'dyjmsfzsqb',
	            	checked:user.personSet.dyjmsfzsqb_lsbz==1?true:false,
	            	listeners:{
	            		'check':function (ck, checked){
	            			if (checked) {
	            			}else{
	            			}
	            		}
	            	}
	            },{
	    	    	height:10,
	    	    	border:false,
	    	    	frame:false
	    	    },{
	            	html: '选择办证原因',
	            	border:false,
					frame:false	            	
	            },{
					xtype : 'dict_combox',
					dict:"VisionType=CS_YDZBZYY",
					anchor : '100%',
					name : 'ydzbzyy',
					hiddenName: 'ydzbzyy',
					listeners:{
						select:function(combo,record,index){
							if(index==0){
								Ext.getCmp('queren').disable();
							}else if(index>0){
								Ext.getCmp('queren').enable();
							}
							
			            }
		            }
				},{
	    	    	height:10,
	    	    	border:false,
	    	    	frame:false
	    	    },{
	            	title: '特快专递',
//					columnWidth: 3,
	            	region:'south',
		            xtype: 'fieldset',
		            autoHeight: true,
		            layout:'column',
		            defaults:{
		            	xtype:'radio',
		            	columnWidth: .3,
		            	fieldLabel: '',
		            	name: 'tkzd'
		            },
		            items: [{
		            	id:'d21',
		            	boxLabel: '否',
		            	name:'tkzd',
		            	inputValue : "1",
		            	checked:true
		            },{
		            	id:'d22',
		            	boxLabel: '是',
		            	name:'tkzd',
		            	inputValue : "2"
		            }]
				}
			],
			buttons:[{
		        text:'确定',
		        id:'queren',
		        disabled:true,
				minWidth:75,
				handler:function(){
					var rootWin = this.findParentByType("zjblOne_window");
					var backData = {};
					backData = rootWin.selRes.data;
					//签发日期选择前五的单选框时，值取弹出框最上面的发证日期
					if(rootWin.p2.items.items[0].items.items[0].checked||rootWin.p2.items.items[0].items.items[1].checked||
							rootWin.p2.items.items[0].items.items[2].checked||rootWin.p2.items.items[0].items.items[3].checked||
							rootWin.p2.items.items[0].items.items[4].checked){
						backData.fzrq = rootWin.fs.items.items[0].items.items[0].items.items[0].items.items[0].value;
					}else{
						backData.fzrq = rootWin.p2.items.items[1].value;
					}
					if(rootWin.p3.items.items[1].items.items[0].checked){//普通证  cslb  5001 dm 1
						backData.bzfs = "1";
					}else{//快证    cslb  5001 dm 2
						backData.bzfs = "2";
					}
					if(rootWin.p3.items.items[2].checked){
						backData.dyjmsfzsqb = true;
					}else{
						backData.dyjmsfzsqb = false;
					}
					backData.bzyy = rootWin.p3.items.items[5].value;
					if(rootWin.p3.items.items[7].items.items[0].checked){
						backData.tkzd = 0;
					}else{
						backData.tkzd = 1;
					}
					if(rootWin.callback){
						rootWin.callback('callback', backData);
					}
					rootWin.hide();
				}
		    },{
		        text:'取消',
				minWidth:75,
				handler:function(){
					var win = this.findParentByType("zjblOne_window");
					win.hide();
				}
		    }]
		});
		this.items = [this.fs,this.p2,this.p3];
		
		Gnt.ux.ZjblOneDialog.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('zjblOne_window', Gnt.ux.ZjblOneDialog);
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