var pagesize = 20;
var selRes = null;
var _postParams = null;
var qcryGrid = null;
var fnQueryFs = null;
var czrkGrid = null;
var jtcyGrid = null;
var all_qxglurl = null;
var tabs = null;

//利用IFRAME填充容器
Ext.ux.IFrameComponent = Ext.extend(Ext.BoxComponent, {
    initComponent: function () {
        Ext.BoxComponent.superclass.initComponent.call(this);
    },
    onRender: function (ct, position) {
        //注意：页面文件必须遵循本书规范，否则就可能发生页面不能打开的错误
        var id = 'iframe-' + this.id;
        this.el = ct.createChild({
            tag: 'iframe',
            closable: true,
            id: id,
            frameBorder: 0,
            src: this.url
        });
        Ext.fly(id).on("load", function () {
            (function () {

            }).defer(500);
        })
    }
});

//打开指定工作区
function createWorkSpace(isclose, url, name, key, desc, parm) {
    p = new Ext.ux.IFrameComponent({
        xtype: 'panel',
        id: key,
        url: url,
        closable: isclose,
        frame: false,
        iconCls: 'otherfile',
        title: name,
        tabTip: desc ? desc : name
    });

    return p;
}

function openWorkSpaceBeforeClose(tabs, isclose, url, name, key, desc, parm) {
    //var tabs = Ext.getCmp("tabs");
    //如果文件已经打开，那么设置为活动分页，并返回
    if (tabs.findById(key)) {
        tabs.remove(tabs.findById(key));
    }

    var p = createWorkSpace(isclose, url, name, key, desc, parm);
    //添加分页，并且设置为活动分页
    tabs.add(p);
    tabs.setActiveTab(p);
    return;
}

function closeActiveWorkSpace(jsonData, tab_key) {
    var tab = tab_key ? tabs.getItem(tab_key) : tabs.getActiveTab();
    if (tab)
        tabs.remove(tab);
}

//释放iframe占用资源
function fixIFrame(o, p) {
    var iFrame = p.getEl().dom;
    if (iFrame.src) {
        iFrame.src = "javascript:false";
    }
}

var dqbm = null;
var u = null;
Ext.onReady(function () {
    Ext.QuickTips.init();

    Gnt.ux.Dict.loadDict(['CS_HLX', 'CS_XB', 'CS_JTGX', 'CS_MZ', 'CS_HYZK', 'CS_XX'], function () {
    });

    Gnt.ux.Dict.getUser(function (user) {
        u = user;
        dqbm = user.dwdm.substr(0, 4);
    });

    var hjspGrid = new Gnt.ux.KdqHjspywGrid({
        region: 'center',
        id: 'czrkGrid',
        title: '待处理跨地区迁出业务',
        height: 200,
        showPaging: true,
        pagesize: 40,
        listeners: {
            rowclick: function (g, rowIndex, e) {

            },
            rowdblclick: function (g, rowIndex, e) {
                if (dqbm == null) {
                    showErr("位置地区编码，请检查是否已经登录！");
                    return;
                }

                var params = "authToken=" + u.authToken + "&sfzh=" + u.gmsfhm + "&yhxm=" + encodeURIComponent(u.xm) + "&yhid=" + u.yhid + "&dwdm=" + u.dwdm + "&yhdlm=" + u.yhdlm;

                selRes = g.store.getAt(rowIndex);
                var url = all_qxglurl + "yw/qc_new.jsp?formdq="
                    + selRes.data.kdqqy_qcdqbm + "&todq=" + dqbm + "&spywid=" + selRes.data.spywid + "&" + params;

                openWorkSpaceBeforeClose(tabs, true, url, "跨地市迁出处理", "kdsqccl");
            }
        },
        url: 'yw/common/queryKDQHjspyw',
        buttons: [
            new Ext.Button({
                text: '刷新',
                minWidth: 75,
                handler: function () {
                    hjspGrid.store.load({params: {start: 0, limit: 40}});
                }
            })
        ]
    });

    //定义分页面板
    tabs = new Ext.TabPanel({
        id: 'tabs',
        activeTab: 0,
        width: 500,
        height: 250,
        resizeTabs: false,
        enableTabScroll: true,
        plain: false,
        listeners: {
            //在关闭分页的时候，调用函数释放iframe占用资源
            beforeremove: fixIFrame.createDelegate(this)
        },
        defaults: {
            autoScroll: false,
            frame: false,
            shim: false,
            xtype: 'panel'
        },
        items: [{
            //本页不允许关闭
            closable: false,
            title: '跨地区省内迁出',
            layout: 'fit',
            tabTip: '',
            items: [
                hjspGrid
            ]
        }, {
            id: "kdqswqc",
            title: "跨地区省外迁入迁出",
            listeners: {// 添加监听器，点击此页面的tab时候要重新加载（刷新功能）
                activate: function () {
                    Gnt.ux.Dict.getKzcs('10038', function(config, userObj, kzdata){
                        if(config){
                            var yhdlm = encodeURI(u.yhdlm);
                            var params = "authToken=" + u.authToken + "&sfzh=" + u.gmsfhm + "&yhxm=" + encodeURIComponent(u.xm) + "&yhid=" + u.yhid + "&dwdm=" + u.dwdm + "&isAdmin=" + isAdmin + "&yhdlm=" + yhdlm + '&rkurl=' + xmdz;
                            //config.bz
                            var url = config.bz + "/yw/yzsydqc.jsp?" + params;
                            var htmlStr = '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="' + url + '"></iframe>';
                            Ext.getCmp('kdqswqc').body.update(htmlStr);
                            this.getUpdater().refresh();
                        }
                    });

                }
            }
        }]
    });

    new Ext.Viewport({
        layout: 'fit',
        id: 'vp',
        items: [
            tabs
        ]
    });

    var kds_config = null;

    Gnt.submit(
        {},
        "util/other/service/queryConfig",
        "获取配置，请稍后...",
        function (jsonData, params) {
            if (jsonData && jsonData.list && jsonData.list.length > 0) {
                kds_config = jsonData.list[0];
                all_qxglurl = kds_config.all_qxglurl;
                if (all_qxglurl.substring(all_qxglurl.length - 1, all_qxglurl.length) != "/") {
                    all_qxglurl += "/";
                }
            } else {
                showErr("获取跨地市配置失败！");
            }
        }, function () {
        },
        false
    );
    hjspGrid.store.baseParams = {
        spywid: getQueryParam("spywid")
    };
    hjspGrid.store.load({params: {start: 0, limit: 40}});
    if (getQueryParam("jumpyzsydqc") == 1) {
        var store = hjspGrid.store;
        store.on("load", function (store) {
            if (store.data.length > 0) {
                for (var i = 0; i < store.data.length; i++) {
                    var res = store.getAt(i);
                    var spywidTemp = res.data.spywid;
                    if (spywidTemp == getQueryParam("spywid")) {
                        hjspGrid.fireEvent("rowdblclick", hjspGrid, i);//rowdblclick
                    }
                }

            }
        }, hjspGrid);
    }
});
