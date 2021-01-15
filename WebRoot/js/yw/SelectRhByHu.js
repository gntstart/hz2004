/**
 * 标准地址选择,必须先加载commFrames.js
 */
Gnt.ux.SelectRhByHu = Ext.extend(Ext.Window, {
	title:'户信息查询',
	closeAction:'hide',
	modal:true,
	width:700,
	height:600,
	margins:'5',
	layout:'border',
	pageSize:30,
	setHzyw:function(hzywjo){
		this.hzywjo = hzywjo;
		if(hzywjo!=null){
			//存在户政业务处理，那么初始化
			this.qrForm.getForm().setValues({
				gmsfhm: hzywjo.lhsfz?hzywjo.lhsfz:hzywjo.sqrsfz
			});
			this.qrForm.buttons[0].handler();
		}
	},
	resetData:function(){
		this.qrForm.getForm().reset();
		this.grid10019.store.removeAll();
		this.grid20001.store.removeAll();
		this.hxx = null;
	},
	initComponent : function(){
		
		if(!Gnt.loadSjpzb("10016,10019,20001",function(){}))
			return;
		
		var returnTitleText = this.returnTitleText;
		if(!returnTitleText || returnTitleText=="") returnTitleText = "户信息查询";
		
		this.returnTitleText = returnTitleText;
		
		this.setTitle(returnTitleText);
		
		
		this.qrhForm = new Ext.form.FormPanel({
			margins:'5px 5px 5px 5px',
			region : 'north',
			height:175,
			layout : 'column',
			title : '',
			frame: false,
			border:  false,
			autoScroll : true,
	    	buttonAlign:'right',
	    	labelAlign:'right',
			defaults : {
				frame:false,
				border:false
			},
			items:[{
				layout : 'column',
				title : '',
				margins:'0',
				bodyStyle : 'padding:5px 0px 0px 0px',
				defaults : {
					frame:false,
					border:false,
					labelWidth : 80,
					bodyStyle : 'padding:0px 5px 0px 5px'
				},
				items:[{
	                columnWidth:.5,
    	           	layout: 'form',
        	       	items: [{
        	       		fieldLabel:'户号',
						xtype:'textfield',
						anchor:'100%',
						name:'hh'
					}]
				},{
	                columnWidth:.5,
    	           	layout: 'form',
        	       	items: [{
        	       		anchor:'100%',
						xtype:'dict_combox',
						dict:'VisionType=CS_HLX',
						value:"2",
						name:'hlxmc',
						maxLength:40,
						hiddenName:'hlx',
						readOnly : false,
						editable:false,
						allowBlank:false,
						fieldLabel:'户类型'
					}]
				},{
	                columnWidth:.5,
    	           	layout: 'form',
        	       	items: [{
        	       		fieldLabel:'集体户名称',
						xtype:'textfield',
						anchor:'100%',
						name:'jthmc'
					}]
				},{
	                columnWidth:.5,
    	           	layout: 'form',
        	       	items: [{
        	       		fieldLabel:'派出所',
        	       		xtype:'search_combox',
        	       		searchUrl:'dict/utils/searchXxb?visiontype=DWXXB',
						anchor:'100%',
						hiddenName:'pcs'
					}]
				},{
	                columnWidth:.5,
    	           	layout: 'form',
        	       	items: [{
        	       		fieldLabel:'责任区',
        	       		xtype:'search_combox',
        				anchor:'100%',
        				hiddenName:'zrq',
        				searchUrl : "dict/utils/searchXxb?visiontype=JWZRQXXB&optype=eq"
					}]
				},{
	                columnWidth:.5,
    	           	layout: 'form',
        	       	items: [{
        	       		fieldLabel:'乡镇街道',
        	       		xtype:'search_combox',
        				anchor:'100%',
        				hiddenName:'xzjd',
        	       		searchUrl : "dict/utils/searchXxb?visiontype=XZJDXXB"
					}]
				},{
	                columnWidth:.5,
    	           	layout: 'form',
        	       	items: [{
        	       		fieldLabel:'居委会',
        	       		xtype:'search_combox',
        	       		anchor:'100%',
        	       		hiddenName:'jcwh',
        	       		searchUrl:'dict/utils/searchXxb?visiontype=JWHXXB'
					}]
				},{
	                columnWidth:.5,
    	           	layout: 'form',
        	       	items: [{
        	       		fieldLabel:'街路巷',
        	       		xtype:'search_combox',
        	       		anchor:'100%',
        	       		hiddenName:'jlx',
        	       		searchUrl : "dict/utils/searchXxb?visiontype=JLXXXB"
					}]
				},{
	                columnWidth:.5,
    	           	layout: 'form',
        	       	items: [{
        	       		fieldLabel:'门楼牌号',
						xtype:'textfield',
						anchor:'100%',
						name:'mlph',
	                    listeners: {
	                        blur:function(field){
								field.setValue(Gnt.ToDBC(field.getValue(),false));
							}
	                    }
					}]
				},{
	                columnWidth:.5,
    	           	layout: 'form',
        	       	items: [{
        	       		fieldLabel:'门楼详址',
						xtype:'textfield',
						anchor:'100%',
						name:'mlxz',
	                    listeners: {
	                        blur:function(field){
								field.setValue(Gnt.ToDBC(field.getValue(),false));
							}
	                    }
					}]
				}]
			}],
	        buttons:[{
	            text:'查询',
				minWidth:75,
				handler:function(){
					var win = this.findParentByType("rhbh_window");
	
					var form = win.qrhForm;
					var gridHcy = win.grid10019;
					var grid = win.grid20001;
					
					if(!form.getForm().isValid()){
						showInfo("数据校验没有通过，请检查！");
						return;
					}
					
					var data = form.getForm().getValues();
					var icount = 0;
					for(o in data){
						if(data[o]!=''){
							icount++;
						}
					}
					/*
					if(icount==0){
						showErr("必须输入至少一个查询条件！");
						return;
					}
					*/
					gridHcy.store.removeAll();
					grid.store.removeAll();
					
					data.pzlb = grid.store.pzlb;
					
					grid.store.baseParams = data;
					
					Ext.apply(grid.store.baseParams, {hlx:2,hhzt:0,queryXx:"queryhxxxx"});
					
					grid.store.load({params:{start:0, limit:grid.store.pageSize}});
				}
	        }]
	
		});
		
		this.grid20001 = new Gnt.ux.SjpzGrid({
			title: '户信息列表',
			region : 'center',
			url: 'cx/hjjbxx/hxxcx/getHxx.json',
			pzlb: '20001',
			pkname: 'hhnbid',
			loadCallback:function(res, store, bind_grid){
					var win = bind_grid.findParentByType("rhbh_window");
					if(win.hzywjo){
						//如果存在户政业务，那么自动化处理
						if(res.length>0){
							bind_grid.fireEvent("rowclick",bind_grid,0);
						}else{
							showInfo("没有找到相关的人员资料，户政业务无法处理！");
						}
					}
			},
			listeners:{
	    		rowclick:function(g, rowIndex, e){
	    			var win = this.findParentByType("rhbh_window");
	    			
	    			var gridHcy = win.grid10019;
	    			
	    			var data = g.store.getAt(rowIndex).data;
	    			win.hxx = data;
	    			
	    			var store = gridHcy.store;
					store.baseParams = {
						pzlb: store.pzlb,
						hhnbid: data.hhnbid
					};
					store.load({params:{start:0, limit:40}});
					
					/**
						住址变动业务需要
					 */
					if(win.rowclickBack){
						win.rowclickBack('rh', data);
					}
	    		}
	    	}
		});
		
		this.grid20001.colModel.moveColumn(6,2);	//动态移动列的位置
		this.grid20001.colModel.setColumnHeader(2,"街路巷(集体户名称)");	//动态修改列名
		this.grid20001.colModel.setColumnWidth(2,120);	//动态修改列宽
		this.grid20001.colModel.setColumnWidth(0,70);
		
		this.grid20001.colModel.setHidden(9,true);
		this.grid20001.colModel.setHidden(10,true);
		this.grid20001.colModel.setHidden(11,true);
		this.grid20001.colModel.setHidden(12,true);
		
		this.grid10019 = new Gnt.ux.SjpzGrid({
			title: '户成员列表',
			region : 'south',
			height:150,
			url: 'yw/common/queryRyxx.json',
			pzlb: '10019',
			pageSize:40,
			loadCallback:function(res, store, bind_grid){
				var win = bind_grid.findParentByType("rhbh_window");
				if(win.hzywjo){
					//如果存在户政业务，那么自动化处理
					if(res.length>0){
						(function(){
							win.buttons[0].handler();
						}).defer(200);
					}
				}
			},
			pkname: 'rynbid'
		});
		
		this.items = [this.qrhForm, this.grid20001, this.grid10019];
		
		Gnt.ux.SelectRhByHu.superclass.initComponent.call(this);
	},
    buttons:[{
        text:'确定',
		minWidth:75,
		handler:function(){
			var win = this.findParentByType("rhbh_window");
			var grid = win.grid10019;
			/*
			//var grid = win.items.get(2);
			if(grid.store.getCount()<=0){
				showErr("请先查询户信息！");
				return;
			}
			*/
			win.hide();
			
			var selectHcyData = null;
			
			var hcyList = new Array();
			grid.store.each(function(r){
				hcyList.push(r.data);
				
				//默认选择人员是户主
				if(r.data.yhzgx=="01" || r.data.yhzgx=="02" || r.data.yhzgx=="03"){
					selectHcyData = r.data;
				}
			});
			
			if(win.select_type=='1' && grid.store.getCount() > 0){
				//选择申报人时，可以寻找非户主
				selectHcyData = grid.getSelectionModel().getSelected().data;
			}
			
			if(!selectHcyData && grid.store.getCount() > 0){
				selectHcyData = grid.store.getAt(0).data;
			}
			
			/**
				以集体户为单位迁入时,选择的户可能没有户成员
				selectHcy.data会导致报错
			 */
			
			Gnt.submit(
					win.hxx,
					"yw/common/getDzxz",
					"请稍后...", 
					function(jsonData,params){
						if(win.callback && jsonData.list && jsonData.list.length>0){
							try{
								win.callback('rh', hcyList, selectHcyData, win.hxx, jsonData.list[0]);
							}catch(e){
								showInfo(e);
							}
						}
					}
			);
		}
    },{
        text:'关闭',
		minWidth:75,
		handler:function(){
			var win = this.findParentByType("rhbh_window");
			
			/**
				住址变动业务需要
				关闭后清空信息
			 */
			if(win.closeWin)
				win.closeWin('rh');
			
			win.hide();
		}
    }]
});
Ext.reg('rhbh_window', Gnt.ux.SelectRhByHu);
