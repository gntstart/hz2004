Gnt.ux.SjpzUploadForm = Ext.extend(Ext.Panel, {
	layout: 'border',
//    border:true,
//    margins:'0 0 0 0',
//	frame:false,
//    border:true,
//    frame:true,
//	buttonAlign:'left',
	height:'100%',
	initComponent: function() {
		var me = this;
		var cols = me.cols;
		var formType = me.formType;	//表单类型：query 查询表单；edit 编辑表单（默认）
		if(!formType){
			formType = "edit";
			me.formType = formType;
		}
		if(me.pzlb){
			me.lbfields = Gnt.getUploadformFormItems(me, formType);
			me.xsfields = Gnt.ux.Dict.getSjpzData(me.pzlb);;
		}
		var lbitems = [{
			layout : 'column',
			//xtype : 'fieldset',
			title : '',
			margins:'0',
			bodyStyle : 'padding:5px 20px 0px 0px',
			defaults : {
				frame:false,
				border:false,
				labelWidth : me.labelWidth?me.labelWidth:100,
				bodyStyle : 'padding:0px 0px 0px 0px'
			},
			items:[]
		}];
		var xsitems = [{
			layout : 'column',
			//xtype : 'fieldset',
			title : '',
			margins:'0',
			bodyStyle : 'padding:5px 20px 0px 0px',
			defaults : {
				frame:false,
				border:false,
				labelWidth : me.labelWidth?me.labelWidth:100,
				bodyStyle : 'padding:0px 0px 0px 0px'
			},
			items:[]
		}];
		if(me.lbfields && !me.items){
			for(var i=0;i<me.lbfields.length;i++){
				var lbitem = me.lbfields[i];
				//只读
				if(lbitem.readOnly!=undefined && lbitem.readOnly){
					if(!lbitem.listeners){
						lbitem.listeners={};
					}
					lbitem.disabled = true;
				}
				//隐藏
				if(lbitem.xtype=='hidden'){
					lbitems[lbitems.length-1].items.push(lbitem);
					continue;
				}
				if(lbitem.labelWidth)
					;
				else
					lbitem.labelWidth = me.labelWidth?me.labelWidth:100;
				if(lbitem.isMust){
					if(lbitem.isMust==1){
						lbitem.html = '<span style="color:red">*' + lbitem.html + "</span>";
					}else if(lbitem.isMust==2){
						lbitem.html = '<span style="color:blue">*' + lbitem.html + "</span>";
					}else if(lbitem.isMust==3){
						lbitem.html = '<span style="color:black">' + lbitem.html + "</span>";
					}
					
				}
				
				lbitems[lbitems.length-1].items.push({
					layout : 'form',
					columnWidth : me.lbfields[i].columnWidth,
					items : [lbitem]
				});
		}
		for(var i=0;i<me.xsfields.length;i++){
			var item = me.xsfields[i];
			item.isMust = Gnt.isMust(item);
			item.collapsed = false;
			item.items=[{
				layout : 'column',
				//xtype : 'fieldset',
				title : '',
				margins:'0',
				bodyStyle : 'padding:5px 20px 0px 0px',
				defaults : {
					frame:false,
					border:false,
//					labelWidth : me.labelWidth?me.labelWidth:100,
					bodyStyle : 'padding:0px 0px 0px 0px'
				},
				items:[
					{html:111},{html:222},{html:444}
				]
			}];
			if(item.isMust){
				if(item.isMust==1){
					item.title = '<span style="color:red">*' + item.displayname + "</span>";
				}else if(item.isMust==2){
					item.title = '<span style="color:blue">*' + item.displayname + "</span>";
				}else if(item.isMust==3){
					item.title = '<span style="color:black">' + item.displayname + "</span>";
				}
				
			}
			xsitems[xsitems.length-1].items.push({
				layout : 'form',
				columnWidth : 1,
				listeners: {
	                render: function(c) {
	                c.body.on('click', function() { 
	                	c.titleCollapse = true;
	                	c.doLayout();
	            		zpItems = c.items.items[0].items.items[0].items.items;
	                	for(var i = 0; i < zpItems.length; i++){
	                        var zpItem = zpItems[i];
//	                        if(zpItem.hidden){
//	                        	zpItem.show();
//	                        }else{
//	                        	zpItem.hide();
//	                        }
	                    }
	                });
	                c.body.on('contextmenu',function(e){
	                    e.preventDefault();//阻止浏览器默认右键菜单
	                    customMenu.showAt(e.getXY());//展示自定义菜单
	                    });
	                },
	                scope: this
	            },
				items : [item]
			});
		}
		this.centerForm = new Ext.form.FormPanel({
	    	title:'',
	    	region:'center',
	    	height: 300,
	    	anchor:'100% 100%',
	    	buttonAlign:'right',
	    	labelAlign:'right',
	    	frame:false,
	    	margins:'10 10 0 0',
	    	border:false,
	        layout:'form',
	        labelWidth:75,
	       	items:lbitems
		});
		this.southForm = new Ext.form.FormPanel({
	    	title:'',
	    	region:'center',
	    	height: 400,
	    	anchor:'100% 100%',
	    	buttonAlign:'right',
	    	labelAlign:'right',
	    	frame:false,
	    	margins:'10 10 0 0',
	    	border:false,
	    	autoScroll: true,
	        layout:'form',
	        labelWidth:75,
	       	items:xsitems
		});
		this.items = [
		        {
		        	layout:'border',
		        	region:'center',
		        	items:[this.centerForm]
		        },{
		        	layout:'border',
		        	region:'south',
		        	height: 260,
		        	items:[this.southForm]
		        }
		];
		Gnt.ux.SjpzUploadForm.superclass.initComponent.call(this);
	}
	}
});

Ext.reg('sjpz_uploadform', Gnt.ux.SjpzUploadForm);