/**
 * 标准地址选择,必须先加载commFrames.js
 */
Gnt.store.KdqHjspywStore = function(c){
	Gnt.store.KdqHjspywStore.superclass.constructor.call(this, Ext.apply(c, {
			root: 'list',
			id:'rynbid',
			totalProperty:'totalCount',
			fields: [
"sqrgmsfhm","spmbid","sqrxm","sqrzzssxq","spywid","splx","slrq","xm","ryid","xb","gmsfhm","csrq",
"mz","jhny","yjrclx","yrdwjcyrysj","lxdh","byxx","whcd","bysj","zyzl","byzsh","zyjszc","jszsh","jsfzjg",
"fmzlmc","fmzlh","zlfzjg","hb","qrhhb","zzssxq","zzxz","hkszddjjg","qrdqx","qrdpcs","qrdjwzrq","qrdxzjd",
"qrdjwh","qrdjlx","qrdmlph","qrdz","qrdhkdjjg","qrdhhid","qrdhlx","rlhbz","sqqrly","sqrzzxz","bz","djrid",
"djsj","xydzid","spjg","sqrhkdjjg","lsbz","rynbid","hjywid","ysqrgx","spsm","bzdz","bzdzid","bzdzx","bzdzy",
"bzdzst","cxfldm","nyzyrklhczyydm","kdqqy_qcdqbm","kdqqy_qchjywid"],
			listeners:{
				loadexception:function(mystore,options,response,error){
					if(error){
						var json = Ext.decode(response.responseText);
						if(json.messages){
							Ext.Msg.alert("提示",json.messages[0]);
						}else{
							Ext.Msg.alert("提示",error.message);
						}
					}else{
						Ext.Msg.alert("提示",response.responseText);
					}
					
					if(this.error_callback){
						this.error_callback(mystore);
					}
				},
				load:function(mystore,res){
					if(this.success_callback){
						this.success_callback(mystore, res);
					}
				}
			}
    }));
};
Ext.extend(Gnt.store.KdqHjspywStore, Ext.data.JsonStore);
