/**
 * 标准地址选择,必须先加载commFrames.js
 */
var rhflid = 0;
var selRhflRes = null;
//var rhflType =1;//默认更新为1,新增为2
Gnt.ux.RhflWindow = Ext.extend(Ext.Window, {
	title:'人户分离',
	closeAction:'hide',
	modal:true,
	width:700,
	height:550,
	margins:'5',
	layout:'border',
	pageSize:30,
	resetData:function(){
		this.qrForm.getForm().reset();
		this.grid10010.store.removeAll();
	},
	initData:function(selRes){
		var grid10010 = this.grid10010;
		var store = grid10010.store;
		this.ryObj = selRes.data;
		store.baseParams = {
				rynbid:selRes.data.rynbid,
				start:0,
				limit:20
		};
		store.load();	
//		store.on("load",function(store) {  
//			if(store.data.length > 0){
//				curIndex = 0;
//				dwxxGrid.fireEvent("rowclick",grid10010,curIndex);
//				dwxxGrid.getSelectionModel().selectRange(curIndex,curIndex);
//			}
//		},grid10010); 
		
//		var rr = new this.grid10010.store.reader.recordType({rhflid: rhflid,xm:'cess'}, rhflid);
//		grid10010.store.add([rr]);
//		var index = grid10010.store.getCount() - 1;
//		grid10010.fireEvent("rowclick",grid10010,index);
//		grid10010.getSelectionModel().selectRange(index,index);
//		rhflid++;
	},
	initComponent : function(){
		if(!Gnt.loadSjpzb("10010",function(){}))
			return;
		
		var returnTitleText = this.returnTitleText;
		if(!returnTitleText || returnTitleText=="") returnTitleText = "户信息查询";
		this.returnTitleText = returnTitleText;
		this.setTitle(returnTitleText);
		
		this.grid10010 = new Gnt.ux.SjpzGrid({
			title: '人户分离信息列表',
			region : 'north',
			url: 'yw/common/queryRhfl.json',
			pzlb: '10010',
			pkname: 'rhflid',
			loadCallback:function(res, store, bind_grid){
				var win = bind_grid.findParentByType("rhfl_window");
			},
			listeners:{
	    		rowclick:function(g, rowIndex, e){
	    			var win = this.findParentByType("rhfl_window");
	    			selRhflRes = g.store.getAt(rowIndex);
					curIndex = rowIndex;
					var form_yw = win.qrForm;
					form_yw.getForm().reset();
					if(selRhflRes){
						form_yw.getForm().loadRecord(selRhflRes);
					}

	    		}
	    	}
		});
		
		this.qrForm = new Gnt.ux.SjpzForm({
			closable: false,
			pkname: 'rhflid',
			region : 'center',
			title:'人户分离信息编辑',
			pzlb: '10010',
			cols:2,
			height:100,
			labelWidth :  120,
			bindViewGrid:this.grid10010,
			getDictCust:function(cmb,visiontype){
				return;
			},
			changeDictCust:function(cmb,res){
				return;
			},
			fieldFocus:function(field){
				if(!selRhflRes){
					var win = this.findParentByType("rhfl_window");
					var grid = win.grid10010;
					var rr = new grid.store.reader.recordType({rhflid: rhflid}, rhflid);
					grid.store.add([rr]);
					var index = grid.store.getCount() - 1;
					grid.fireEvent("rowclick",grid,index);
					grid.getSelectionModel().selectRange(index,index);
				}
				
			},
	        title: '',
	        buttons:[]
		});
		this.items = [this.qrForm, this.grid10010];
		
		Gnt.ux.RhflWindow.superclass.initComponent.call(this);
	},
    buttons:[{
        text:'增加',
		minWidth:75,
		handler:function(){
			var win = this.findParentByType("rhfl_window");
			var grid = win.grid10010;
			var form = win.qrForm;
			var data = [];
			if(selRhflRes){
				for(var index=0;index<grid.store.getCount();index++){
					var griddata = grid.store.getAt(index).data;
					if(!griddata.xm){
						alert("姓名必填！");
						return;
					}
					if(!griddata.rhflzt){
						alert("人户分离状态必填！");
						return;
					}
					if(griddata.rhflid==0){
						griddata.rhflid="";
						griddata.rynbid = win.ryObj.rynbid;
					}
					
					data.push(griddata);
				}
				
//				var rs = grid.store.getModifiedRecords();
//				if(rs.length==0){
//					alert("form表单数据没填！");
//				}
//				for (var i = 0, ln = rs.length; i < ln; i++) {
//					if(!rs[i].data.xm){
//						alert("姓名必填！");
//						return;
//					}
//					if(!rs[i].data.rhflzt){
//						alert("人户分离状态必填！");
//						return;
//					}
//					if(rs[i].data.rhflid==0){
//						rs[i].data.rhflid="";
//						rs[i].data.rynbid=win.ryObj.rynbid;
//					}
//					data.push(rs[i].data);
//				}
			}else{
				var formData = form.getForm().getValues();
				if(!formData.xm){
					alert("姓名必填！");
					return;
				}
				if(!formData.rhflzt){
					alert("人户分离状态必填！");
					return;
				}
				
			}
			var subdata = {
					VoRhflywfhxx: data
			};
			for(o in subdata){
				if(subdata[o]){
					subdata[o] = Ext.encode(subdata[o]);
				}
			}
			Gnt.submit(
					subdata, 
					"yw/common/addRhfl.json", 
					"正在处理人户分离业务信息，请稍后...", 
					function(jsonData,params){
						showInfo("人户分离信息新增成功！",function(){
							//form 清空     grid刷新    selRhflRes置为null
							grid.store.reload(); 
							form.getForm().reset();
							selRhflRes = null;
						});
					}
			);
			
		}
    },{
        text:'删除',
		minWidth:75,
		handler:function(){
			var win = this.findParentByType("rhfl_window");
			var grid = win.grid10010;
			var form = win.qrForm;
			var selectedRow = grid.getSelectionModel().getSelected();
			if(selectedRow){
				Gnt.submit(
						{rhflid:selectedRow.data.rhflid}, 
						"yw/common/delRhfl.json", 
						"正在处理人户分离业务信息，请稍后...", 
						function(jsonData,params){
							showInfo("人户分离信息删除成功！",function(){
								//form 清空     grid刷新    selRhflRes置为null
								grid.store.reload(); 
//								grid.store.remove(selectedRow); 
								form.getForm().reset();
								selRhflRes = null;
							});
						}
				);
			}else{
				alert("请先选中一条待删除的数据，再点击删除按钮！");
			}
			
			
		}
    },{
        text:'关闭',
		minWidth:75,
		handler:function(){
			var win = this.findParentByType("rhfl_window");
			
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
Ext.reg('rhfl_window', Gnt.ux.RhflWindow);
