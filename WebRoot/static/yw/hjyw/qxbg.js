var selRes = null;
var curIndex = -1;
var hzywjo_list;
var hzywjo;
var selectRynbid = "";
var selectHhnbid = "";

Ext.onReady(function () {
    Ext.QuickTips.init();

    var hzywid = getQueryParam("hzywid");
    var pch = getQueryParam("pch");

    //本业务需要加载的配置
    if (!Gnt.loadSjpzb("10016,20016,10036,10002", function () {
    }))
        return;

    //本业务需要加载的字典
    Gnt.ux.Dict.loadDict(['CS_HLX', 'CS_XB', 'CS_JTGX', 'CS_MZ', 'CS_HYZK', 'CS_XX'], function () {
    });

    //人员选择
    var p1 = new Gnt.ux.SelectRyxxPanel({
        returnBtnText: '确定',
        returnBtnMsg: '必须选择变更人员',
        select: function (list) {
            curIndex = 0;

            //点击确认后，执行此方法
            Ext.getCmp('card').getLayout().setActiveItem(1);
            //界面更新到下一步
            //v.remove(v.getComponent(0));
            //v.add(p2);
            v.doLayout();

            //户成员初始化
            var store = hcyGrid.store;
            store.removeAll();

            for (var i = 0; i < list.length; i++) {
                var data = list[i];
                var rr = new store.reader.recordType(data, data.rynbid);
                store.add([rr]);

                if (data._sel == '1') {

                    /**
                     暂时全项变更没有户政业务
                     */
                    if (hzywjo_list) {
                        Ext.each(hzywjo_list, function (jo) {
                            if (jo.bsqrsfz == data.gmsfhm) {

                                if (jo.whcd) {
                                    rr.set("whcd", jo.whcd);
                                }
                                Gnt.util.copyHzywToRyzl(rr, jo);
                                /*
                                 * 20190916 add by zjm
                                 * 拟变更身份证
                                 */
                                if (!Gnt.util.isEmpty(jo.nbggmsfhm)) {
                                    rr.set("gmsfhm", jo.nbggmsfhm);
                                }

                                /**
                                 * ydd 20200430
                                 * 全项变更业务，要从中间表带入
                                 * 何时迁来，何因迁来，
                                 * 何省市县迁来，何祥址迁来
                                 * ，与户主关系，
                                 * 监护人一，监护人一监护关系，
                                 * 监护人二，监护人二监护关系
                                 * 出生地省县，籍贯省县11个字段
                                 *
                                 */
                                if (!Gnt.util.isEmpty(jo.hsql)) {
                                    rr.set("hsql", jo.hsql);
                                }
                                if (!Gnt.util.isEmpty(jo.hyql)) {
                                    rr.set("hyql", jo.hyql);
                                }
                                if (!Gnt.util.isEmpty(jo.hssxqql)) {
                                    rr.set("hssxqql", jo.hssxqql);
                                }
                                if (!Gnt.util.isEmpty(jo.hxzql)) {
                                    rr.set("hxzql", jo.hxzql);
                                }
                                if (!Gnt.util.isEmpty(jo.yhzgx)) {
                                    rr.set("yhzgx", jo.yhzgx);
                                }
                                if (!Gnt.util.isEmpty(jo.jhryxm)) {
                                    rr.set("jhryxm", jo.jhryxm);
                                    if (!Gnt.util.isEmpty(jo.jhryjhgx)) {
                                        rr.set("jhryjhgx", jo.jhryjhgx);
                                    }
                                }

                                if (!Gnt.util.isEmpty(jo.jhrexm)) {
                                    rr.set("jhrexm", jo.jhrexm);
                                    if (!Gnt.util.isEmpty(jo.jhrejhgx)) {
                                        rr.set("jhrejhgx", jo.jhrejhgx);
                                    }
                                }

                                if (!Gnt.util.isEmpty(jo.csdssxq)) {
                                    rr.set("csdssxq", jo.csdssxq);
                                }
                                if (!Gnt.util.isEmpty(jo.jgssxq)) {
                                    rr.set("jgssxq", jo.jgssxq);
                                }

                                //add by zjm 20200402 中间表血型字段带过来
                                if (!Gnt.util.isEmpty(jo.xx) && Gnt.ux.Dict.getDictLable('CS_XX', jo.xx) != jo.xx) {
                                    rr.set("xx", jo.xx);
                                }
                                if (!Gnt.util.isEmpty(jo.rkzp)) {
                                    rr.set("zp", jo.rkzpBase64);
                                    rkzpMap[data.rynbid] = jo.rkzpBase64;
                                }
                            }
                        });
                    }

                }
                SetReadOnly(form_yw, 'gmsfhm', true);
            }

            (function () {
                hcyGrid.fireEvent("rowclick", hcyGrid, 0);
                hcyGrid.getSelectionModel().selectRange(0, 0);
            }).defer(200);

            if (list.length == 1) {
                Ext.getCmp('prvBtn').disable();
                Ext.getCmp('nextBtn').disable();
            }
        }
    });

    var hcyGrid = new Gnt.ux.SjpzGrid({
        pkname: 'rynbid',
        pzlb: '10036',
        region: 'west',
        title: '户成员列表',
        width: 200,
        showPaging: false,
        listeners: {
            rowclick: function (g, rowIndex, e) {
                selRes = g.store.getAt(rowIndex);
                curIndex = rowIndex;

                selectRynbid = selRes.data.rynbid;
                selectHhnbid = selRes.data.hhnbid;

                if (curIndex == (hcyGrid.store.getCount() - 1)) {
                    Ext.getCmp('nextBtn').disable();
                } else {
                    Ext.getCmp('nextBtn').enable();
                }

                if (curIndex == 0) {
                    Ext.getCmp('prvBtn').disable();
                } else {
                    Ext.getCmp('prvBtn').enable();
                }

                //人员基本资料更新
                form_yw.getForm().reset();
                form_yw.getForm().loadRecord(selRes);

                if (selRes.data._sel == "1") {
                    if (rkzpMap[selRes.data.rynbid]) {
                        selRes.data.zp = rkzpMap[selRes.data.rynbid];
                        Gnt.photo.setPhoto(null, true);
                        Gnt.photo.setPhoto(selRes, true);
                    } else {
                        Gnt.photo.setPhoto(null, true);
                        Gnt.photo.setPhoto(selRes, true);
                    }
                } else {
                    Gnt.photo.setPhoto(selRes, false);
                }

            }
        }
    });

    var sfzGrid = new Gnt.ux.SjpzGrid({
        pkname: 'rynbid',
        pzlb: '20000',
        title: '',
        url: 'yw/hjyw/bggz/getSfz.json'
    });

    var qxbgStore = new Gnt.store.SjpzStore({
        pzlb: '10036',
        pkname: 'rynbid'
    });

    var form_yw = new Gnt.ux.SjpzForm({
        title: '户成员信息',
        closable: false,
        /*region:'north',
        height:450,*/
        pzlb: '10036',
        labelWidth: 120,
        cols: 2,
        trackResetOnLoad: true,
        //当修改表单的时候，能够POST到store，如果不指定那么采用bindViewGrid.store
        bindViewGrid: hcyGrid,
        //当bindViewGrid点击的时候，初始化form，有些只需要bindStore，比如业务中的登记没有grid
        changeDictCust: function (cmb, res) {
            //当选择人后，为其它域赋值
            if (cmb.name == "fqxm") {
                this.getForm().setValues({fqgmsfhm: res.data.data});
                selRes.set("fqgmsfhm", res.data.data);
            }
            if (cmb.name == "mqxm") {
                this.getForm().setValues({mqgmsfhm: res.data.data});
                selRes.set("mqgmsfhm", res.data.data);
            }
            if (cmb.name == "jhryxm") {
                this.getForm().setValues({jhrygmsfhm: res.data.data});
                selRes.set("jhrygmsfhm", res.data.data);
            }
            if (cmb.name == "jhrexm") {
                this.getForm().setValues({jhregmsfhm: res.data.data});
                selRes.set("jhregmsfhm", res.data.data);
            }
            if (cmb.name == "poxm") {
                Gnt.validpoxb(form_yw, selRes, res, true);
                //add by zjm 20190710 配偶下拉框选中人员后，配偶身份证自动带入
                this.getForm().setValues({pogmsfhm: res.data.data});
                selRes.set("pogmsfhm", res.data.data);
            }

            return;
        },
        isModify: function (flag) {
            if (flag) {
                Ext.getCmp("cancelUpdId").setDisabled(false);
            } else {
                Ext.getCmp("cancelUpdId").setDisabled(true);
            }
        },
        fieldChange: function (field) {

            var form = form_yw.getForm();

            /**
             暂时只修改性别时分配身份证信息
             */
            if (field.name == "xb"　/*||　field.name=="xm" || field.name=="csrq"*/) {
                var store = sfzGrid.store;
                store.baseParams = {
                    rynbid: selectRynbid,
                    ryxm: form.findField("xm").value,
                    ryxb: form.findField("xb").value,
                    csrq: form.findField("csrq").value
                };
                store.load();

                store.on("load", function (store) {
                    if (store.data.length > 0) {
                        curIndex = 0;

                        form.setValues({gmsfhm: store.getAt(0).data.gmsfhm});
                    }
                }, sfzGrid);

            }

        },
        fieldBlur: function (field) {
            var form = form_yw.getForm();
            var r = hcyGrid.getSelectionModel().getSelected();

            if (field.name == 'yxqxqsrq') {
                if (field.value && form.findField("csrq").getValue()) {

                    if (form.findField("csrq").getValue() > form.findField("yxqxqsrq").getValue()) {
                        showInfo("有效期限起始日期不能早于出生日期！");
//        				form.findField("yxqxqsrq").setValue("");
                        form_yw.fieldSetValue(form.findField("yxqxqsrq"), "");

                        return false;
                    }

                    var csrq = form.findField("csrq").value;
                    var yxqxqsrq = form.findField("yxqxqsrq").value;

                    var nl = Number(Gnt.date.getNlc(csrq, yxqxqsrq));

//        			var nl = Number(Gnt.date.jsGetAge(csrq)) - Number(Gnt.date.jsGetAge(yxqxqsrq));

                    var vDay = field.value.substring(6, 8);//日
                    var vMonth = field.value.substring(4, 6);//月
                    var vYear = field.value.substring(0, 4);//年

                    if (nl < 16) {
                        vYear = Number(vYear) + 5;

                        //form.findField("yxqxjzrq").setValue(vYear + vMonth + vDay);

                        form_yw.fieldSetValue(form.findField("yxqxjzrq"), vYear + vMonth + vDay);

                    } else if (nl >= 16 && nl <= 25) {
                        vYear = Number(vYear) + 10;

//        				form.findField("yxqxjzrq").setValue(vYear + vMonth + vDay);
                        form_yw.fieldSetValue(form.findField("yxqxjzrq"), vYear + vMonth + vDay);
                    } else if (nl >= 26 && nl <= 45) {
                        vYear = Number(vYear) + 20;

//        				form.findField("yxqxjzrq").setValue(vYear + vMonth + vDay);
                        form_yw.fieldSetValue(form.findField("yxqxjzrq"), vYear + vMonth + vDay);
                    } else if (nl >= 46) {

                        form.findField("yxqxjzrq").setValue("");
                    }

                }
            }

            if (field.name == 'hyzk' && r.isModified("hyzk")) {
                Gnt.validpo(form_yw, field);
            }

            if (field.name == 'pogmsfhm' && r.isModified("pogmsfhm")) {
                Gnt.validposfzh(form_yw, field, true);
            }

        }
        , fieldFocus: function (field) {
            Gnt.filterField(field);
        }
    });

    var form_sbr = new Gnt.ux.SjpzForm({
        closable: false,
        /*region:'center',
        height:60,*/
        pzlb: '10002',
        labelWidth: 120,
        cols: 2,
        //选择人的来源
        bindSelectRyStore: hcyGrid.store,
        changeDictCust: function (cmb, res) {
            //当选择人后，为其它域赋值
            this.getForm().setValues({sbrgmsfhm: res.data.data});
            return;
        },
        title: '申报人信息'
    });

    var p2 = new Ext.Panel({
        layout: 'border',
        items: [hcyGrid, {
            region: 'center',
            //禁止横向滚动条
            layout: 'border',
            border: false,
            frame: false,
            items: [form_yw, {
                layout: 'border',
                region: 'south',
                height: 60,
                border: false,
                frame: false,
                items: [form_sbr]
            }]
        }, {
            region: 'east',
            width: 150,
            layout: 'table',
            align: 'center',
            layoutConfig: {
                columns: 1
            },
            margins: '5',
            defaults: {
                width: '100%',
                margins: '15'
            },
            items: [{
                title: '',
                height: '100%',
                bodyStyle: 'padding:10px 10px 10px 10px',
                html: '<div id="_PHOTO_ID">照片</DIV>',
                width: 150,
                height: 180,
                rowspan: 1,
                colspan: 1
            }, {
                html: '',
                bodyStyle: 'padding:10px 25px 0px 20px',
                layout: 'table',
                align: 'center',
                border: false,
                frame: false,
                layoutConfig: {
                    columns: 1
                },
                items: [new Ext.Button({
                    text: '获取照片',
                    minWidth: 100,
                    id: 'zphqImgBtn',
                    width: 150,
                    handler: function () {
                        Gnt.photo.Huoqu();
                    }
                }), {
                    height: 3,
                    border: false,
                    frame: false
                }, new Ext.Button({
                    id: 'zpcjImgBtn',
                    text: '照片采集',
                    minWidth: 100,
                    width: 150,
                    handler: function () {
                        Gnt.photo.OpenEdit();
                    }
                }), {
                    height: 3,
                    border: false,
                    frame: false
                }, new Ext.Button({
                    id: 'zpscImgBtn',
                    text: '照片删除',
                    disabled: true,
                    minWidth: 100,
                    handler: function () {
                        Gnt.photo.changeImageCallback("");
                    }
                }), {
                    height: 3,
                    border: false,
                    frame: false
                }, new Ext.Button({
                    text: '详细信息',
                    minWidth: 100,
                    handler: function () {
                        czr = {
                            ryid: null,
                            rynbid: selectRynbid,
                            hhnbid: selectHhnbid
                        }
                        Gnt.toRyxx(czr);
                    }
                }), {
                    height: 3,
                    border: false,
                    frame: false
                }, new Ext.Button({
                    id: 'cancelUpdId',
                    text: '取消修改',
                    disabled: true,
                    minWidth: 100,
                    handler: function () {

                        form_yw.getForm().reset();

                        hcyGrid.store.rejectChanges();

                        Ext.getCmp("cancelUpdId").setDisabled(true);

                    }
                }), {
                    height: 3,
                    border: false,
                    frame: false
                }, new Ext.Button({
                    text: '下一个',
                    minWidth: 100,
                    id: 'nextBtn',
                    handler: function () {
                        curIndex++;
                        hcyGrid.fireEvent("rowclick", hcyGrid, curIndex);
                        hcyGrid.getSelectionModel().selectRange(curIndex, curIndex);
                    }
                }), {
                    height: 3,
                    border: false,
                    frame: false
                }, new Ext.Button({
                    text: '上一个',
                    disabled: true,
                    minWidth: 100,
                    id: 'prvBtn',
                    handler: function () {
                        curIndex--;
                        hcyGrid.fireEvent("rowclick", hcyGrid, curIndex);
                        hcyGrid.getSelectionModel().selectRange(curIndex, curIndex);
                    }
                }), {
                    height: 3,
                    border: false,
                    frame: false
                }, {
                    xtype: 'SfzhyButton',
                    form: form_yw
                }, {
                    height: 3,
                    border: false,
                    frame: false
                }, {
                    height: 50,
                    border: false,
                    frame: false
                }, new Ext.Button({
                    text: '保存',
                    minWidth: 100,
                    disabled: user.flag > 0 ? true : false,
                    handler: function () {
                        save();
                    }
                }), {
                    height: 3,
                    border: false,
                    frame: false
                }, new Ext.Button({
                    text: '新办理',
                    minWidth: 100,
                    handler: function () {
                        showQuestion("数据未保存，确定放弃办理吗？", function (btnType) {
                            if (btnType == "yes") {
                                /*
                                Ext.getCmp('card').getLayout().setActiveItem(0);
                                v.doLayout();
                                p1.newYewu();
                                */
                                window.location.reload();
                            }
                        });
                    }
                }), {
                    height: 3,
                    border: false,
                    frame: false
                }, new Ext.Button({
                    text: '关闭',
                    minWidth: 100,
                    handler: function () {
                        showQuestion("数据未保存，确定关闭吗？", function (btnType) {
                            if (btnType == "yes") {
                                if (window.parent.closeWorkSpace) {
                                    window.parent.closeWorkSpace();
                                }
                            }
                        });
                    }
                })
                ]
            }
            ]
        }]
    });

    var p3 = new Ext.Panel({
        layout: 'border',
        frame: false,
        borde: false,
        items: [
            {
                region: 'north',
                height: 100,
                html: '',
                frame: false,
                borde: false
            }, {
                region: 'center',
                title: '',
                frame: false,
                borde: false,
                html: '<center><br/><br/><br/><br/><br/><br/><br/><br/><h2>全项变更业务办理完成！</h2></center>'
            }, {
                region: 'south',
                height: 100,
                html: '',
                frame: false,
                borde: false,
                buttons: [
                    new Ext.Button({
                        text: '常表户口簿打印',
                        id: 'cbPrint',
                        minWidth: 100,
                        width: 150,
                        handler: function () {
                            printRk(3, selectHhnbid, selectRynbid, [true, true, true, true, true], [true, true, false, false, false], [true, true, true, true, true]);
                        }
                    }), new Ext.Button({
                        text: '新办理',
                        minWidth: 100,
                        width: 150,
                        disabled: hzywid ? true : false,
                        handler: function () {
                            window.location.reload();
                        }
                    }), new Ext.Button({
                        text: '关闭',
                        minWidth: 100,
                        width: 150,
                        handler: function () {
                            if (parent && parent.closeWorkSpace) {
                                parent.closeWorkSpace();
                            } else {
                                window.close();
                            }
                        }
                    })
                ]
            }
        ]
    });

    function save() {
        if (!form_yw.checkInput(false, "6", false, hcyGrid))
            return;
//			if(!form_yw.checkInput(false,'4', true, hcyGrid))
//				return;
        if (!form_sbr.getForm().isValid()) {
            showErr("申报人信息必须填写！");
            return;
        }

        if (!Gnt.validHz(null, form_yw, form_yw)) {
            return;
        }

        //判断非户必须存在户主
        var exists = false;
        var icount = 0;
        hcyGrid.store.each(function (r) {
            var data = r.data;
            /*
            if(data._sel=="1"){
                //成员
            }else{
                icount++;
                if(data.yhzgx=="01" || data.yhzgx=="02" || data.yhzgx=="03")
                    exists = true;
            }
            */
            icount++;
            if (data.yhzgx == "01" || data.yhzgx == "02" || data.yhzgx == "03")
                exists = true;
        });

        if (!exists && icount > 0) {
            showErr("户主已迁出，请选择新户主！");
            return;
        }

        //初始化变更更正数据
        Gnt.yw.initBggzStore(hcyGrid, bggzWin.grid.store,null,null,hcyGrid.pzlb);

        if (bggzWin.grid.store.getCount() > 0) {
            bggzWin.show();
        } else {
            //submitData();
            showInfo("数据未有变动，不能提交变更更正业务！");
        }

    }

    //变更更正对话框
    bggzWin = new Gnt.ux.BggzDialog({
        hcyGrid: hcyGrid, //变更更正户成员数据集
        submitData: submitData,	//变更更正确认回调方法
        title: '确认变更更正项目'
    });

    //提交数据
    function submitData(bggzxx) {
        var subdata = {
            bggzxx: bggzxx,
            sbjbxx: {},
            ryList: new Array()
        };

        //人员内部id和上笔户籍业务的关系
        var map = {};
        hcyGrid.store.each(
            function (rec) {
                map[rec.data.rynbid] = rec.data.cjhjywid;
            }, hcyGrid
        );

        //申报人信息
        subdata.sbjbxx = form_sbr.getForm().getValues();
        //add by zjm 20200318 增加参数hzywid
        if (hzywjo) {
            subdata.sbjbxx.hzywid = hzywjo.id;
        }
        subdata.sbjbxx = Ext.encode(subdata.sbjbxx);
        subdata.bggzxx = Ext.encode(subdata.bggzxx);
        ///yw/hjyw/swzx
        //记录日志的操作码
        log_code = "F1013";
        Gnt.submit(
            subdata,
            "yw/hjyw/bggz/processBggzyw",
            "正在处理全项变更信息，请稍后...",
            function (jsonData, params) {
                showInfo("全项变更成功！", function () {

                    Ext.getCmp('card').getLayout().setActiveItem(2);
                    Ext.getCmp('vp').doLayout();

                    bggzWin.hide();
                    selectHhnbid = "";
                    selectRynbid = "";
                    if (jsonData && jsonData.list[0]) {
                        for (var i = 0; i < jsonData.list[0].voBggzfhxx.length; i++) {
                            selectRynbid += jsonData.list[0].voBggzfhxx[i].rynbid + ",";
                            selectHhnbid = jsonData.list[0].voBggzfhxx[0].hhnbid;
                        }
                        for (var i = 0; i < jsonData.list[0].voHcygxtzfhxx.length; i++) {
                            selectRynbid += jsonData.list[0].voHcygxtzfhxx[i].rynbid + ",";
                            selectHhnbid = jsonData.list[0].voHcygxtzfhxx[0].hhnbid;
                        }
                        if (selectRynbid.length > 0) {
                            selectRynbid = selectRynbid.substr(0, selectRynbid.length - 1);
                        }
                        if(hzywjo&&hzywjo.id){
                        	Gnt.ux.Dict.getKzcs('10036', function(pz, user) {
                        		if(pz.kzz==1){
                        			pjq('ES',user.ip,hzywjo.id);
                        		}
                        	});
                        }
                        printRk(3, selectHhnbid, selectRynbid, [true, true, true, true, true], [true, true, false, false, false], [true, true, true, true, true]);
                    }
                    //printRk(1,selectHhnbid,null,[true,true,true,true,true],[true,true,false,false,false],[true,true,true,true,true]);
                    //关闭当前窗口
                    if (parent.closeActiveWorkSpace)
                        parent.closeActiveWorkSpace(jsonData);
                });
            }
        );
    }

    var v = new Ext.Viewport({
        layout: 'fit',
        id: 'vp',
        border: false,
        items: {
            layout: 'card',
            border: false,
            id: 'card',
            frame: false,
            activeItem: 0,
            xtype: 'panel',
            //bodyStyle: 'padding:2px',
            items: [p1, p2, p3]
        }
    });

    //户政业务处理
    if (hzywid && hzywid != "") {
        Gnt.makeHzyw({
            hzywid: hzywid,
            pch: pch,
            cmpids: [],
            callback: function (jo, jolist) {
                //成功处理返回，合并数据
                hzywjo_list = [];
                hzywjo_list.push(jo);
                Ext.each(jolist, function (d) {
                    hzywjo_list.push(d);
                });
                hzywjo = jo;

                p1.setHzyw(hzywjo, hzywjo_list);
            }
        });
    }

});