/**
 * 变更更正
 * 
 * BggzDialog.js替代
 * 
 */
Gnt.store.BggzStore = function(c){
	Gnt.store.BggzStore.superclass.constructor.call(this, Ext.apply(c, {
			root: 'list',
			id:'tmpid',
			totalProperty:'totalCount',
			fields: ["tmpid", "rynbid","xm","bggzxm_mc","bggzxm", "bggzqnr","bggzhnr","bggzlb"],
			listeners:{
				loadexception:function(mystore,options,response,error){
					if(error){
						var json = Ext.decode(response.responseText);
						if(json.messages)
							Ext.Msg.alert("提示",json.messages[0]);
						else
							Ext.Msg.alert("提示",error.message);
					}else{
						Ext.Msg.alert("提示",response.responseText);
					}
				}
			}
    }));
};
Ext.extend(Gnt.store.BggzStore, Ext.data.JsonStore);
