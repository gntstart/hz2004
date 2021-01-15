/**
 * 数据配置Store
 * 依据配置，创建store
 *  
 * pzlb 			必须，配置类别，来自数据配置表
 * pkname		必须，主键字段，必须位于pzlb之中
 * url				可选，数据加载位置
 *
 	使用样例：
 	迁出登记store
	
	var qcdjStore = new Gnt.store.SjpzStore({
		pzlb:'10024',					//必须，配置类别，来自数据配置表
		pkname:'rynbid',			//必须，主键字段，必须位于pzlb之中
		url: 'ryxx/queryRyxx'		//可选，数据加载位置
	});
 */
Gnt.store.SjpzStore = function(c){
	if(!c.pkname || c.pkname==""){
		alert("Gnt.store.SjpzStore必须提供pkname指定主键！");
	}
	
	if(!c.pzlb || c.pzlb==""){
		alert("Gnt.store.SjpzStore必须提供pzlb！");
	}
	this.pzlb = pzlb;
	
	var pzlb = c.pzlb;
	var ary = Gnt.ux.Dict.getSjpzData(pzlb);
	var items = new Array();
	
	//选择标志
	items.push({
		name: "_sel",
		type:"string"
	});
	
	var ex = false;
	var fieldMap = {};
	
	if(ary instanceof Array){
		for(var i=0;i<ary.length;i++){
			items.push(Gnt.getRecordField(ary[i], c));
			
			if(ary[i].fieldname==c.pkname){
				ex = true;
			}
			
			//字典，添加一个文本显示区域，_txt_作为前缀
			var dsname = ary[i].dsname;
			if(dsname && dsname!=""){
				var pz = ary[i];
				var f = {
						name: "_dict_text_" + pz.fieldname,
						type:"string"
				};
				items.push(f);
			}
			
			fieldMap[ary[i].fieldname] = ary[i].displayname;
		}
	}else{
		alert("Gnt.store.SjpzStore配置" + pzlb + "本地缓存不存在！");
	}
	
	if(!ex){
		alert(c.pkname + "不存在配置" + pzlb + "！");
	}
	
	Gnt.store.SjpzStore.superclass.constructor.call(this, Ext.apply(c, {
			url : c.url,
			root: 'list',
			id: c.pkname,
			totalProperty: 'totalCount',
			//successProperty: "success",
			//success: true,
			fieldMap: fieldMap,
			fields: items,
			listeners:{
				beforeload:function(store, options){
					if(store.bind_grid && store.bind_grid.beforeload){
						return store.bind_grid.beforeload(store, options );
					}
					return true;
				},
				load:function(store, res){
					store.init = "1";
					if(store.loadCallback){
						store.loadCallback(res, store, store.bind_grid);
					}
				},
				loadexception:function(mystore,options,response,error){
					if(error){
						var json = Ext.decode(response.responseText);
						if(json.message)
							Ext.Msg.alert("提示",json.message);
						else
							Ext.Msg.alert("提示",error.message);
					}else{
						Ext.Msg.alert("提示",response.responseText);
					}
				}
			}
    }));
};
Ext.extend(Gnt.store.SjpzStore, Ext.data.JsonStore);
