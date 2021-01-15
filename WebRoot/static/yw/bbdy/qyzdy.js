var selectYw = 1;
var queryFlag = null;
var rynbid = null;

var selectRynbid = null;
var selectHhnbid = null;
var selectHjywid = null;
Ext.onReady(function(){
	Ext.QuickTips.init();
	//本业务需要加载的配置
	if(!Gnt.loadSjpzb("10019,20021",function(){}))
		return;
	
	//本业务需要加载的字典
	Gnt.ux.Dict.loadDict(['CS_HLX','CS_XB','CS_JTGX','CS_MZ','CS_HYZK','CS_XX'],function(){});
	
	/**
		点击下一步后显示的内容
		暂时用户成员信息代替
	 */
	var hcyGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '10019',
    	region:'south',
		height:150,
    	title: '',
		url: 'yw/common/queryRyxx.json',
    	showPaging:false,
		listeners:{
			rowclick:function(g, rowIndex, e){}
		}
	});
	var p1 = new Ext.Panel({
		layout:'border',
		newYewu: function(){
			
		},
		items:[{
				id:"centerId",
				title: '',
	        	region:'center',
	        	xtype: 'fieldset',
	            autoHeight : true, 
	            labelWidth : 280,
	            bodyStyle : 'background-color:white',
		    	items: [{
					id: "panelHtmlId" ,
					html: '<div class="text" style="text-align:center;"><h2>迁移证打印</h2></div>',
					height: 80,
					region:'north',
					bodyStyle:'padding:15px'
				},{
					xtype: 'fieldset',
					autoHeight: true,
					height:480,
					bodyStyle:'padding:35px',
			        layout:'column',
					items:[{
		            	id:'southId',
		            	title: '请设置查询的条件',
		            	region:'south',
			            xtype: 'fieldset',
			            width:'30%',
			            autoHeight : true, 
			            labelWidth : 80,
			            items: [
			            	{
			            		id:'name',
		            			xtype : 'textfield',
		            			border:false,
		            			frame:false,
		            			fieldLabel:"姓名",
		            			width:60,
		            			anchor : '98%',
		            			enableKeyEvents: true,
		//            			grow:true,
		            			name:'name',
		            			listeners:{
				            		'keyup' : function(src, evt){ 
				            			if(src.getValue()||Ext.getCmp('card_no').getValue()){
				            				Ext.getCmp('queryId').enable();
	        							}else{
	        								Ext.getCmp('queryId').disable();
	        							}
				                    }
				                //监听事件
					            }
			            	},{
			            		id:'card_no',
		            			xtype : 'textfield',
		            			border:false,
		            			frame:false,
		            			fieldLabel:"身份号码",
		            			width:60,
		            			anchor : '98%',
		//            			grow:true,
		            			enableKeyEvents : true,
		            			name:'card_no',
		            			listeners:{
				            		'keyup' : function(src, evt){ 
				            			if(src.getValue()||Ext.getCmp('name').getValue()){
				            				Ext.getCmp('queryId').enable();
	        							}else{
	        								Ext.getCmp('queryId').disable();
	        							}
				                    }
				                //监听事件
					            }
			            	}
			            ]
					},{
		            	border:false,
						frame: false,
						width:'1%'
		            },{
		            	title: '选择迁移证重新打印的业务',
		            	region:'center',
			            xtype: 'fieldset',
			            layout:'column',
			            width:'20%',
						height:100,
	            		bodyStyle : 'padding:0 0 0 10',
						defaults:{
			            	columnWidth: 1,
			            	fieldLabel: '',
			            	name: 'fs'
			            },
			            items: [{
			            	id:'type',
			    	    	height:10,
			    	    	border:false,
			    	    	frame:false
			    	    },{
			            	id:'r11',
			            	xtype:'radio',
			            	boxLabel: '迁出业务',
			            	name:'fs',
			            	inputValue : "1",
			            	checked:true,
			            	listeners:{
			            		'check' : function(checkbox, checked){ 
			                         if(checked){//只有在点击时触发
			                        	 if(Ext.getCmp('r11').getValue()){
			                        		 selectYw = 1;
			                        	 }
			                         }  
			                    }
			                //监听事件
				            }
			            },{
			    	    	height:10,
			    	    	border:false,
			    	    	frame:false
			    	    },{
			            	id:'r12',
			            	xtype:'radio',
			            	boxLabel: '住址变动业务',
			            	name:'fs',
			            	inputValue : "2",
			            	listeners:{
			            		'check' : function(checkbox, checked){ 
			                         if(checked){//只有在点击时触发
			                        	 if(Ext.getCmp('r12').getValue()){
			                        		 selectYw = 2;
			                        	 }
			                         }  
			                    }
			                //监听事件
				            }
			            }]
					}]
				},{
					border:false,
					frame: false,
		        	region:'south',
		        	height: 40,
		        	width:'80%',
		        	bodyStyle: 'padding:5px',
					layout:'table',
//					layoutConfig: {
//			        	columns: 10
//			        },
			    	items: [
						{
							frame:false,
							border:false,
							xtype:'panel',
							html:'',
							width:10
			    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		    		    	id:'queryId',
		        			text:'查询',
		        			disabled:queryFlag == null?true:false,
		        			minWidth:60,
		        			handler:function(){
		        				if(Ext.getCmp('card_no').getValue()){
		        					if(!checkCard()){
			        					return
			        				}
		        				}
		        				var data ={};
		        				data.gmsfhm = Ext.getCmp('card_no').getValue();
		        				data.xm = Ext.getCmp('name').getValue();
		        				var store = null;
		        				if(selectYw==1){
		        					store= qczxGrid.store;
		        					store.removeAll();
		        					store.baseParams = data;
		        					store.load({params:{start:0, limit:20}});
		        					store.on('load',function(s,records){
		        						qczxGrid.fireEvent("rowclick",qczxGrid,0);
		        						qczxGrid.getSelectionModel().selectRange(0,0);
			        					
			        				});
		        					Ext.getCmp('card').getLayout().setActiveItem(1);
		        				}else if(selectYw==2){
		        					store= zzbdGrid.store;
		        					store.removeAll();
		        					store.baseParams = data;
		        					store.load({params:{start:0, limit:20}});
		        					store.on('load',function(s,records){
		        						zzbdGrid.fireEvent("rowclick",zzbdGrid,0);
		        						zzbdGrid.getSelectionModel().selectRange(0,0);
			        					
			        				});
		        					Ext.getCmp('card').getLayout().setActiveItem(2);
		        				}else{
		        					return;
		        				}
		        			}
		        		})]
		    		},{
						frame:false,
						border:false,
						xtype:'panel',
						html:'',
						width:10
		    		},{
						border:false,
						frame: false,
		    		    items:[new Ext.Button({
		        			text:'关闭',
		        			minWidth:60,
		        			handler:function(){
		        				window.parent.closeWorkSpace();
		        			}
		        		})]
		    		}
			    		 
			    ]
					
				}
	    	]
	        }
		]
	});
	var zzbdGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '20007',
		url:'cx/hjywxx/zzbdcx/getZzbdxx.json',
		region:'center',
		height:540,
		title: '',
		showPaging:true,
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
				selectRynbid = selRes.data.rynbid;
				selectHhnbid = selRes.data.hhnbid;
				selectHjywid = selRes.data.hjywid;
				
				
			},
			rowdblclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
				selectRynbid = selRes.data.rynbid;
				selectHhnbid = selRes.data.hhnbid;
				selectHjywid = selRes.data.hjywid;
				selectRyid = selRes.data.ryid;
				selectYhhnbid = selRes.data.yhhnbid;
			}
		}
	});	
	var qczxGrid = new Gnt.ux.SjpzGrid({
		pkname: 'rynbid',
		pzlb: '20004',
		url:'cx/hjywxx/qczxcx/getQcxx.json',
		region:'center',
//		height:540,
		title: '',
		showPaging:true,
		listeners:{
			rowclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
				selectRynbid = selRes.data.rynbid;
				selectHhnbid = selRes.data.hhnbid;
				selectHjywid = selRes.data.hjywid;
				
				
			},
			rowdblclick:function(g, rowIndex, e){
				selRes = g.store.getAt(rowIndex);
				curIndex = rowIndex;
				
				selectRynbid = selRes.data.rynbid;
				selectHhnbid = selRes.data.hhnbid;
				selectHjywid = selRes.data.hjywid;
			}
		}
	});
	var p2 = new Ext.Panel({
		layout:'border',
		items:[qczxGrid],
			buttons:[
				new Ext.Button({
					text:'迁移证',
					minWidth:80,
					handler:function(){
						qyzPrint(selectHjywid);
					}
				}),
				new Ext.Button({
					text:'关闭',
					minWidth:80,
					handler:function(){
						window.parent.parent.closeWorkSpace();
					}
				}),
				new Ext.Button({
					text:'返回',
					minWidth:80,
					handler:function(){
						Ext.getCmp('card').getLayout().setActiveItem(0);
					}
				})
				]
	});
	var p3 = new Ext.Panel({
		layout:'border',
		items:[zzbdGrid],
			buttons:[
				new Ext.Button({
					text:'迁移证',
					minWidth:80,
					handler:function(){
						qyzPrint(selectHjywid);
					}
				}),
				new Ext.Button({
					text:'关闭',
					minWidth:80,
					handler:function(){
						window.parent.parent.closeWorkSpace();
					}
				}),
				new Ext.Button({
					text:'返回',
					minWidth:80,
					handler:function(){
						Ext.getCmp('card').getLayout().setActiveItem(0);
					}
				})
				]
	});
	//定义分页面板
	
	function getRynbid(rynbid){
		if(rynbid != null){
			return rynbid;
		}else{
			return getQueryParam("rynbid");
		}
	}
	
	var v = new Ext.Viewport({
        layout:'fit',
        id:'vp',
        border:false,
        items: {
        	layout:'card',
        	border:false,
        	id:'card',
        	frame:false,
        	activeItem: 0,
        	xtype: 'panel',
        	items:[p1,p2,p3]
        }
    });
    
	var vcity={ 11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",  
            21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",  
            33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",  
            42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",  
            51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",  
            63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"  
           };  
  
checkCard = function()  
{  
    var card = Ext.getCmp('card_no').getValue();
    //document.getElementById('gmsfhm').value;  
    //是否为空  
    if(card === '')  
    {  
        alert('请输入身份证号，身份证号不能为空');  
        document.getElementById('card_no').focus;  
        return false;  
    }  
    //校验长度，类型  
    if(isCardNo(card) === false)  
    {  
        alert('您输入的身份证号码不正确，请重新输入');  
        document.getElementById('card_no').focus;  
        return false;  
    }  
    //检查省份  
    if(checkProvince(card) === false)  
    {  
        alert('您输入的身份证号码不正确,请重新输入');  
        document.getElementById('card_no').focus;  
        return false;  
    }  
    //校验生日  
    if(checkBirthday(card) === false)  
    {  
        alert('您输入的身份证号码生日不正确,请重新输入');  
        document.getElementById('card_no').focus();  
        return false;  
    }  
    //检验位的检测  
    if(checkParity(card) === false)  
    {  
        alert('您的身份证校验位不正确,请重新输入');  
        document.getElementById('card_no').focus();  
        return false;  
    }  
    return true;  
};  
  
  
//检查号码是否符合规范，包括长度，类型  
isCardNo = function(card)  
{  
    //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
    var reg = /(^\d{15}$)|(^\d{17}(\d|X)$)/;  
    if(reg.test(card) === false)  
    {  
        return false;  
    }  
  
    return true;  
};  
  
//取身份证前两位,校验省份  
checkProvince = function(card)  
{  
    var province = card.substr(0,2);  
    if(vcity[province] == undefined)  
    {  
        return false;  
    }  
    return true;  
};  
  
//检查生日是否正确  
checkBirthday = function(card)  
{  
    var len = card.length;  
    //身份证15位时，次序为省（3位）市（3位）年（2位）月（2位）日（2位）校验位（3位），皆为数字  
    if(len == '15')  
    {  
        var re_fifteen = /^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/;   
        var arr_data = card.match(re_fifteen);  
        var year = arr_data[2];  
        var month = arr_data[3];  
        var day = arr_data[4];  
        var birthday = new Date('19'+year+'/'+month+'/'+day);  
        return verifyBirthday('19'+year,month,day,birthday);  
    }  
    //身份证18位时，次序为省（3位）市（3位）年（4位）月（2位）日（2位）校验位（4位），校验位末尾可能为X  
    if(len == '18')  
    {  
        var re_eighteen = /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/;  
        var arr_data = card.match(re_eighteen);  
        var year = arr_data[2];  
        var month = arr_data[3];  
        var day = arr_data[4];  
        var birthday = new Date(year+'/'+month+'/'+day); 
        return verifyBirthday(year,month,day,birthday);  
    }  
    return false;  
};  
  
//校验日期  
verifyBirthday = function(year,month,day,birthday)  
{  
    var now = new Date();  
    var now_year = now.getFullYear();  
    //年月日是否合理  
    if(birthday.getFullYear() == year && (birthday.getMonth() + 1) == month && birthday.getDate() == day)  
    {  
        //判断年份的范围（3岁到100岁之间)  
        var time = now_year - year;  
        if(time >= 0 && time <= 100)  
        {  
            return true;  
        }  
        return false;  
    }  
    return false;  
};  
  
//校验位的检测  
checkParity = function(card)  
{  
    //15位转18位  
    card = changeFivteenToEighteen(card);  
    var len = card.length;  
    if(len == '18')  
    {  
        var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);   
        var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');   
        var cardTemp = 0, i, valnum;   
        for(i = 0; i < 17; i ++)   
        {   
            cardTemp += card.substr(i, 1) * arrInt[i];   
        }   
        valnum = arrCh[cardTemp % 11];   
        if (valnum == card.substr(17, 1))   
        {  
            return true;  
        }  
        return false;  
    }  
    return false;  
};  
  
//15位转18位身份证号  
changeFivteenToEighteen = function(card)  
{  
    if(card.length == '15')  
    {  
        var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);   
        var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');   
        var cardTemp = 0, i;     
        card = card.substr(0, 6) + '19' + card.substr(6, card.length - 6);  
        for(i = 0; i < 17; i ++)   
        {   
            cardTemp += card.substr(i, 1) * arrInt[i];   
        }   
        card += arrCh[cardTemp % 11];   
        return card;  
    }  
    return card;  
};
	
	
});