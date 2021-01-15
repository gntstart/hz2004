//html页面种通过调用这个函数显示上传窗体
//其他窗体需要进行上传文件只需要调用这个函数就可以
/*
 * winTitle 弹出框标题    
 * pzType 凭证类型：1收费凭证 2 缴费凭证
 * sfxxbid 收费信息表ID
 * 
 */
function btnUpload_click(winTitle,pzType,sfxxbid,sfxxbidList, callback) {
    win.setTitle(winTitle);
    win.sfxxbidList = sfxxbidList;
    win.pzType = pzType;
    win.callback = callback;
    if(pzType == 1){
        win.image = 'yw/common/img/sfRender/'+sfxxbid;
    }else{
        win.image = 'yw/common/img/jfRender/'+sfxxbid;
    }
    win.doLayout();
    win.show();
    document.getElementById("logoPic").src = win.image;
}

var formpanel = new Ext.form.FormPanel({
    title: '',
    width: '95%',
    height: '95%',
    x: 5,
    y: 5,
    layout: "form",
    bodyPadding: "5",
    defaultType: "textfield",
    allowBlank:false,
    fileUpload: true,
    fieldDefaults: {labelAlign: "left", labelWidth: 55},
    items: [
        {
            xtype: 'textfield',
            id: 'logoFile',
            name: 'logoFile',
            inputType: 'file',
            fieldLabel: '照片文件',
            width: 280,
            listeners: {
                'render': function () {
                    var logoFileCmp = Ext.get('logoFile');
                    logoFileCmp.on('change',
                        function (field, newValue, oldValue) {
                        var picPath = logoFileCmp.getValue();
                        if(picPath == ""){
                            return;
                        }
                        var url = 'file:///' + picPath;
                        var arr = url.split('.');
                        if (!((arr[arr.length - 1] == 'jpg') || (arr[arr.length - 1] == 'png') || (arr[arr.length - 1] == 'bmp'))) {
                            alert('文件不合法，请上传后缀为*.jpg;*.png;*.bmp的照片文件!');
                            return;
                        }
                        if (Ext.isIE) {
                            var image = Ext.get('logoPic').dom;
                            image.src = Ext.BLANK_IMAGE_URL;
                            image.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = url;
                        } else {
                            //支持FF
                            var oFReader = new FileReader();
                            oFReader.readAsDataURL(document.getElementById("logoFile").files[0]);
                            oFReader.onload = function (oFREvent) {
                                document.getElementById("logoPic").src = oFREvent.target.result;
                            }
                        }
                    }, this);
                }
            }
        },
        {
            xtype: 'box',
            id: 'logoPic',
            width: 150,
            height: 200,
            autoEl: {
                tag: 'img',
                src: '',
                style: 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);'
            }
        }
    ],
    buttons: [
        {
            text: "上传",
            handler: function () {
                var photoName = document.getElementById("logoFile").value;
                if (photoName) {
                    formpanel.getForm().submit({
                        url: 'yw/common/uploadQdZp',
                        method:'post',
                        params: {
                            pzType: win.pzType,
                            sfxxbidList: win.sfxxbidList
                        },
                        success: function (form, action) {
                            showInfo("照片上传成功！");
                            Ext.getCmp('sfqdImg').body.update("<IMG SRC='images/no_pic.gif' width='100%' height='100%' />");
                            win.hide();
                            if(win.callback){
                                win.callback();
                            }else{
                                showInfo("回调函数不存在!");
                            }
                        },
                        failure: function (form, action) {
                            showInfo("照片上传失败！");
                        }
                    });
                } else {
                    showInfo("必须先上传有效照片后才能提交!");
                    return;
                }

            }
        }, {
            text: '关闭',
            handler: function () {
               // var obj = document.getElementById("logoFile").value = '';
//                obj.outerHTML = obj.outerHTML;
                win.hide();
            }
        }
    ]

});
//窗体
var win = new Ext.Window({
    title: '上传文件窗口',
    width: 476,
    height: 374,
    resizable: false,
    modal: false,
    closable: false,
    closeAction: 'hide',
    items: [formpanel]
});


