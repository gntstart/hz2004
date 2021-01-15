/**
 * 标准地址选择,必须先加载commFrames.js
 */
Gnt.ux.SelectSfhfpbl = Ext.extend(Ext.Window, {
	title:'身份号码分配补录',
	closeAction:'hide',
	modal:true,
	width:700,
	height:400,
	margins:'5',
	layout:'border',
	pageSize:30,
	setHzyw:function(hzywjo){
		this.hzywjo = hzywjo;
	},
	resetData:function(){
		this.sfhfpblForm.getForm().reset();
	},
	initComponent : function(){
		if(!Gnt.loadSjpzb("10008",function(){})){
			return;
		}
		
		var ywStore = new Gnt.store.SjpzStore({
			pzlb:'10008',
			pkname:'ryid'
		});
		
		var sfzGrid = new Gnt.ux.SjpzGrid({
			pkname: 'rynbid',
			pzlb: '20000',
			title: '',
			url: 'yw/hjyw/bggz/getSfz.json'
		});
		
		this.sfhfpblForm = new Gnt.ux.SjpzForm({
			title: '补录信息录入',
			closable: false,
			region : 'center',
			pzlb: '10008',
			cols:1,
//			height:100,
//			formType:'query',
			bindStore:this.ywStore,
			labelWidth :  160,
			isModify:function(flag){
				var win = this.findParentByType("sfhfpbl_window");
				
				var checkFlag = true;
				/**
					判断表单是否都有值
				 */
				var formValues = win.sfhfpblForm.getForm().getValues();
				
				if(!Ext.util.Format.trim(formValues["csrq"])){
					checkFlag = false;
				}
				
				if(!Ext.util.Format.trim(formValues["xb"])){
					checkFlag = false;
				}
				
				if(!Ext.util.Format.trim(formValues["sxh"])){
					checkFlag = false;
				}
				
				if(!Ext.util.Format.trim(formValues["gmsfhm"])){
					checkFlag = false;
				}
				
				if(!Ext.util.Format.trim(formValues["xm"])){
					checkFlag = false;
				}
				/*
				if(!Ext.util.Format.trim(formValues["dwdm"])){
					checkFlag = false;
				}
				*/
				
				if(checkFlag){
					Ext.getCmp('saveId').setDisabled(false);
				}else{
					Ext.getCmp('saveId').setDisabled(true);
				}
				
			},
	    	fieldBlur:function(field){
	    		var win = this.findParentByType("sfhfpbl_window");
	        	if(field.name=='gmsfhm'){
	        		
	        		if(field.getValue()){
	        			
	        			/**
	        				自动设置身份证顺序号
	        			 */
	        			var formValues = win.sfhfpblForm.getForm().getValues();
	        			if(field.getValue().length == 14){
	        				field.setValue(field.getValue() + formValues["sxh"]) ;
	        			}
	        			
	        			/**
	        				自动将最后一位补上
	        			 */
	        			if(field.getValue().length == 17){
	        				field.setValue(field.getValue() + Gnt.date.getCheckCode(field.getValue())) ;
	        			}
	        			
	        			/**
	        				设置出生日期
	        			 */
	        			if(!win.sfhfpblForm.getForm().findField("csrq").getValue()){
	        				win.sfhfpblForm.getForm().findField("csrq").setValue(Gnt.date.getBirth(field.getValue()));
	        			}
	        			
	        			/**
		    				设置性别
		    			 */
	        			if(!win.sfhfpblForm.getForm().findField("xb").getValue()){
	        				win.sfhfpblForm.getForm().findField("xb").setValue(Gnt.date.getSexCode(field.getValue()));
	        			}
	        			
	        		}
	        		
	        	}
	    		
	    	}/*,
			fieldChange:function(field){
	    		
				var win = this.findParentByType("sfhfpbl_window");
				
	    		var form = win.sfhfpblForm.getForm();
	    		
	    		*//**
	    			暂时只修改性别时分配身份证信息
	    			
	    			修改了性别后获取身份证号特别特别慢
	    		 *//*
	    		if(field.name=="xb"　||　field.name=="xm" || field.name=="csrq"){
	    			var store = sfzGrid.store;
					store.baseParams = {
						rynbid:selectRynbid,
						ryxm:form.findField("xm").value,
						ryxb:form.findField("xb").value,
						csrq:form.findField("csrq").value
					};
					store.load();
					
					store.on("load",function(store) {
						if(store.data.length > 0){
							curIndex = 0;
							
							form.setValues({gmsfhm:store.getAt(0).data.gmsfhm});
						}else{
							showInfo("未获取到新身份证信息!");
						}
					},sfzGrid);
					
	    		}
	    		
	    	}*/
		});
		
		this.items = [this.sfhfpblForm];
		
		Gnt.ux.SelectSfhfpbl.superclass.initComponent.call(this);
	},
    buttons:[{
    	id:'saveId',
        text:'保存',
		minWidth:75,
    	disabled:true,
		handler:function(){
			var win = this.findParentByType("sfhfpbl_window");
			
			win.hide();
			
			var subdata = {
					sfzblxx: new Array()
			};
			var f = win.items.items[0];
			var params = f.getForm().getValues();
			var tmp = Gnt.date.validateCard(params.gmsfhm, params.xb, params.csrq);
    		if (tmp!=true) {
    			Gnt.MsgBox.showWarn(tmp);
    			return;
    		}
//			var data = "[" +
//					"{" +
//						"gmsfhm:" + win.sfhfpblForm.getForm().findField("gmsfhm").getValue() + "," +
//						"xb:" + win.sfhfpblForm.getForm().findField("xb").getValue() + "," +
//						"xm:" + win.sfhfpblForm.getForm().findField("xm").getValue() + "," +
////						"dwdm:" + win.sfhfpblForm.getForm().findField("dwdm").getValue() + "" +
//						"dwdm:\"340721050\"" +
//					"}" +
//				"]";
			
			subdata.sfzblxx=params;
			for(o in subdata){
				if(subdata[o]){
					subdata[o] = Ext.encode(subdata[o]);
				}
			}
			log_code = "F1014";
			Gnt.submit(
					subdata,
					"yw/hjyw/sfhfpxxbl/processSfhmfpblyw.json",
					"请稍后...", 
					function(jsonData,params){
						if(win.callback){
//							win.callback('rh', hcyList, selectHcy.data, win.hxx, jsonData.list[0]);
						}
						win.sfhfpblForm.getForm().reset();
						Ext.getCmp('saveId').setDisabled(true);
						alert('身份证号分配补录成功！');
						win.hide();
					}
					
			);
		}
    },{
        text:'关闭',
		minWidth:75,
		handler:function(){
			var win = this.findParentByType("sfhfpbl_window");
			
			win.sfhfpblForm.getForm().reset();
			Ext.getCmp('saveId').setDisabled(true);
			
			win.hide();
		}
    }]
});
Ext.reg('sfhfpbl_window', Gnt.ux.SelectSfhfpbl);
