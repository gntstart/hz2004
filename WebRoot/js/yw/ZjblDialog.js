Gnt.ux.ZjblDialog= Ext.extend(Ext.Window, {
	title:'请选择签发日期',
	closeAction:'hide',
	modal:true,
	width:500,
	height:300,
	margins:'5',
	layout:'border',
	show:function(){
		Gnt.ux.ZjblDialog.superclass.show.call(this);
	},
	setSelRes:function(sel){
		this.yxqxqsrq=CurentTime(0,0);
		this.yxqxjzrq=CurentTime(10,0);
		
		this.fs.getForm().setValues({
			csrq: sel.data.csrq,//sel.data.csrq,
			age:jsGetAge(sel.data.csrq),
			qfrq: CurentTime(0,1),
			qx:CurentTime(10,0)
		});
		
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
		
		
		this.fs = new Ext.form.FormPanel({
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
				                columnWidth:0.5,
			    	           	layout: 'form',
			    	           	bodyStyle:'padding:5 0 0 0;',
			        	       	items: [{
									xtype:'textfield',
									anchor:'100%',
									name:'csrq',
									disabled:true,
									allowBlank:false,
									fieldLabel:'出生日期为'
								}]
						},{
			                columnWidth:0.5,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0;',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'age',
								disabled:true,
								allowBlank:false,
								fieldLabel:'年龄',
			                    listeners: {
			                    	change: function (me, newValue, oldValue, eOpts) {
			                    		
							        }
			                    }
							}]
						},{
			                columnWidth:0.5,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0;',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								name:'qfrq',
								allowBlank:false,
								fieldLabel:'签发日期为',
								disabled:true,
								listeners:{
								}
							}]
						},{
			                columnWidth:0.5,
		    	           	layout: 'form',
		    	           	bodyStyle:'padding:5 0 0 0;',
		        	       	items: [{
								xtype:'textfield',
								anchor:'100%',
								disabled:true,
								name:'qx',
								allowBlank:false,
								fieldLabel:'期限'
							}]
						}
					]
			}]
		});
		
		this.p2 = new Ext.Panel({
			region: 'center',
        	//height:250,
            border:false,
            frame:false,
            items:[{
            	id:'fwId',
            	title: '',
//				columnWidth: 3,
	            xtype: 'fieldset',
	            autoHeight: true,
	            layout:'border',
	            defaults:{
	            	xtype:'radio',
	            	columnWidth: .3,
	            	//disabled:true,
	            	fieldLabel: '',
	            	name: 'fw'
	            },
	            items: [{
	            	id:'r11',
	            	boxLabel: '按办证日期',
	            	name:'fw',
	            	inputValue : "1",
	            	checked:true
	            },{
	            	id:'r12',
	            	boxLabel: '按出生日期',
	            	name:'fw',
	            	inputValue : "2"
	            },{
	            	id:'r13',
	            	boxLabel: '其他日期',
	            	name:'fw',
	            	inputValue : "3"
	            }]
			}]
        });
		this.p3 = new Ext.Panel({
			region: 'south',
        	//height:250,
            border:false,
            frame:false,
	    	buttons:[
    	 		{
    	 			text:'确定',
    	 			minWidth:75,
    	 			handler:function(){
    	 				var rootWin = this.findParentByType("zjbl_window");
    	 				var form = rootWin.items.get(0);
    	 				var lhdz = form.getForm().getValues();
    	 				lhdz.yxqxqsrq = rootWin.yxqxqsrq;
    	 				lhdz.yxqxjzrq = rootWin.yxqxjzrq;
    	 				if(rootWin.callback){
							rootWin.callback('lh', lhdz);
						}
						rootWin.hide();
    	 			}
    	 		},{
    	 			text:'取消',
    	 			minWidth:75,
    	 			handler:function(){
    	 				var win = this.findParentByType("zjbl_window");
    	 				win.hide();
    	 			}
    	 		}
    	 	]
        });
		this.items = [this.fs, this.p2, this.p3];
		
		Gnt.ux.ZjblDialog.superclass.initComponent.call(this);
	}
	
	
});
Ext.reg('zjbl_window', Gnt.ux.ZjblDialog);
function CurentTime(num,type)
{ 
    var now = new Date();
    var year = now.getFullYear();       //年
    year = year+num;
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
    if(type==1){
    	if(hh < 10)
    	        clock += "0";
    	clock += hh + ":";
        if (mm < 10) clock += '0'; 
        clock += mm; 	
    }else if(type==0){
    	return year+""+month+""+day;
    } 
    
    return clock; 
} 
/*根据出生日期算出年龄*/
function jsGetAge(strBirthday){
	if(strBirthday.length!=8){
		alert("日期格式不对");
		return;
	}
    var returnAge;
    var birthYear = strBirthday.substring(0,4);
    var birthMonth = strBirthday.substring(4,6);
    var birthDay = strBirthday.substring(6,8);
    
    d = new Date();
    var nowYear = d.getFullYear();
    var nowMonth = d.getMonth() + 1;
    var nowDay = d.getDate();
    
    if(nowYear == birthYear){
        returnAge = 0;//同年 则为0岁
    }
    else{
        var ageDiff = nowYear - birthYear ; //年之差
        if(ageDiff > 0){
            if(nowMonth == birthMonth) {
                var dayDiff = nowDay - birthDay;//日之差
                if(dayDiff < 0)
                {
                    returnAge = ageDiff - 1;
                }
                else
                {
                    returnAge = ageDiff ;
                }
            }
            else
            {
                var monthDiff = nowMonth - birthMonth;//月之差
                if(monthDiff < 0)
                {
                    returnAge = ageDiff - 1;
                }
                else
                {
                    returnAge = ageDiff ;
                }
            }
        }
        else
        {
            returnAge = -1;//返回-1 表示出生日期输入错误 晚于今天
        }
    }
    
    return returnAge;//返回周岁年龄
    
}