Ext.onReady( function() {
    Ext.QuickTips.init();
    
    function login(){
    	if(!fs.getForm().isValid()){
			showErr("数据校验未通过！");
			return;
		}
    	
    	var subdata = fs.getForm().getValues();
		Gnt.submit(
				subdata, 
				"../login/login.json", 
				"登录验证，请稍后...", 
				function(jsonData,params){
					var data = jsonData.list[0];
					root = data.root;
					var msg = data.khmsg;
					if(msg && msg!="">0){
						showInfo(msg,function(){
							goIndex();
						}); 
					}else{
						//跳转
						goIndex();
					}
				}
		);
	}
    
    function goIndex(){
    	window.document.location.href = "../index";
    }
    
	function calogin(){
		mask.show();
		window.location='calogin.jsp'
       	//fs.getForm().submit({
		//	url:'calogin.jsp'
		//});
	}
    var fs = new Ext.FormPanel({
        id: 'login-form-panel',
        frame: true,
        labelAlign: 'right',
        bodyStyle:'padding:5px',
        border: true,
        width: 400,
        items: [{
            xtype: 'fieldset',
            defaultType: 'textfield',
            autoHeight: true,
        	title: '用户登录',            
            width: 350,
            border: true,
            items: [{
                name: 'usercode',
                id:'usercode',
                allowBlank: false,
                value:'HZADMIN',
                fieldLabel: '用户名'
            },{
                name: 'pwd',
                id:'pwd',
                fieldLabel: '密码',
                allowBlank: false,
                inputType: 'password',
                listeners:{
					specialkey:function(field,e){
						if (e.getKey()==Ext.EventObject.ENTER){   
							Ext.getCmp("btn-submit").fireEvent('click');
						}   
					}   
				}   
            }]
        }],
		buttons: [{
			id: 'btn-submit',
			name: 'btn-submit',
            text: '确定',
			handler:function(){
				login();
			}
        },{
			id: 'btn-submit1',
			name: 'btn-submit1',
            text: 'PKI登录',
			handler:function(){
				login();
			}
        }],
        method: 'POST',        
		renderTo: 'gui'
    });
    
    new Ext.KeyMap(Ext.get("usercode"), {
    	key:13,
    	fn:function(key,keyEvent){
    		Ext.get("pwd").dom.focus();
    	}
	});
    new Ext.KeyMap(Ext.get("pwd"), {
    	key:13,
    	fn:function(key,keyEvent){
    	login();
    	}
	});
	
	Ext.get("usercode").dom.focus();
    var mask = new Ext.LoadMask(fs.el, {msg:"正在验证身份，请等待..."});
});
