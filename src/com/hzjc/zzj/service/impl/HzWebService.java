package com.hzjc.zzj.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hzjc.hz2004.base.SpringContextHolder;
import com.hzjc.hz2004.base.SystemConfig;
import com.hzjc.hz2004.po.entity.*;
import com.hzjc.hz2004.service.hzpt.Hz2004ServiceZzj;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.zzj.WebServiceUtil;
import com.hzjc.zzj.bean.*;
import org.apache.axis.client.Call;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HzWebService {
    private String url;

    public HzWebService() {
        System.out.println("HzWebService 初始化......");
//        url = "http://192.168.0.155:9999/hz2004query/services/hz2004WebService";
        url = SystemConfig.getSystemConfig("hz2004query");
    }

    static private void saveLog(LgApiLog log) {
        Hz2004ServiceZzj apiService = (Hz2004ServiceZzj) SpringContextHolder.getBean("hz2004Service");
        apiService.saveLog2(log);
    }

    /**
     * @param pcsbm 需要使用接口验证参数
     * @param type     字典code
     * @param code     所属id
     * @return
     */
    public String getDictValue(String pcsbm,String sbh,String md5, String type, String code) {
        String value = null;
        Call call2 = null;
        try {
            call2 = new Call(url);
            call2.setOperationName("getDictValue");
            // type = CS_SWZXLB code = 0100
            value = (String) call2.invoke(new Object[]{pcsbm, sbh, md5, type, code});
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return value;
    }

    /**
     * @param pcsbm 需要使用接口验证参数
     * @param type     字典code
     * @return
     */
    public String getDict(String pcsbm,String sbh,String md5, String type) {
        String value = null;
        Call call2 = null;
        try {
            call2 = new Call(url);
            call2.setOperationName("getDict");
            // type = CS_ZJXY
            value = (String) call2.invoke(new Object[]{pcsbm, sbh, md5, type});
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return value;
    }

    /**
     * 新增/修改 出生登记记录
     *
     * @param pcsbm 需要使用接口验证参数
     * @param type     记录类型(暂无使用)
     * @param json     出生登记记录的JSON编码，编码格式见文档说明。
     * @return 返回处理结果的JSON，格式如下： {
     * "success": "true|false之一，表示成功或者失败",
     * "message":"错误消息",
     * "postid":"json数据包含的用户第三方ID值，用于返回对应",
     * "lkbm":"如果成功，那么返回入住流水号，必须回填此流水号，退房或者修改入住信息必须使用此凭证"
     * }
     */
    public String postCSDJRecrod(String pcsbm,String sbh,String md5, String type, String json) {
        LgApiLog log = new LgApiLog();
        log.setApiname("postCSDJRecrod");
        log.setBz("type=" + type + json);
        log.setLgbm(pcsbm);
        log.setLogsj(new Date());

        ReturnBean re = new ReturnBean();
        try {
            //进行设备验证
            WebServiceUtil.checkUser(pcsbm,sbh,md5);
            WebServiceUtil.checkUserLogin(pcsbm);

            String ip = WebServiceUtil.getIpaddress();
            log.setIp(ip);
            Hz2004ServiceZzj hz2004Service = (Hz2004ServiceZzj) SpringContextHolder.getBean("hz2004ServiceZzj");
            Object obj = null;

            obj = WebServiceUtil.getJsonData(VcsdjBean.class, json);

            re = postCSDJRecrod(ip, pcsbm, type, hz2004Service, obj);
        } catch (Exception e) {
            re.setMessage(e.getMessage());
            re.setSuccess(false);
        }

        log.setHs(new Date().getTime() - log.getLogsj().getTime());
        log.setSfcg(re.isSuccess() ? "1" : "0");
        try {
            saveLog(log);
        } catch (Exception e) {
            ;
        }

        return WebServiceUtil.toJson(re);
    }

    /**
     * 出生登记保存或者修改的通用方法
     *
     * @param ip
     * @param pcsbm
     * @param type
     * @param nwbService
     * @param bean
     * @return
     */

    private ReturnBean postCSDJRecrod(String ip, String pcsbm, String type, Hz2004ServiceZzj nwbService, Object bean) {
        String ywlsh = null;
        ReturnBean re = new ReturnBean();
        String postid = null;
        try {

            VcsdjBean nb = (VcsdjBean) bean;
            postid = nb.getPostid();
            ywlsh = nb.getYwlsh();

            //下面模拟nwbaction里面的流程，保持nwbservice的业务流程不变
            boolean c_u = true;

            //如果第三方没有带业务流水号，那么默认为新增
            if (CommonUtil.isNotEmpty(nb.getYwlsh())) {
                c_u = false;
            } else {
                //判断数据库是否已经记录过此记录KEY
                ApiYs ys = nwbService.getApiYsByPostid(pcsbm, type, postid);
                if (ys != null) {
                    //如果有，那么修改原记录(用户不带，那么智能判断)
                    c_u = false;
                    nb.setYwlsh(ys.getLkbm());
                }
            }
            String hhnbid = nb.getHhnbid();
            //将nbbean转换为lknb对象
            Vcsdj lk = WebServiceUtil.getCsdj(nwbService, ip, pcsbm, nb);


            if (c_u) {
                //saveNb(nb,  postid);
                nwbService.saveNb(lk, postid,hhnbid);
                WebServiceUtil.processCsdjyw(lk, hhnbid);
                //.saveNb(lk, null, postid);
            } else {
                Vcsdj oldnb = nwbService.queryNbByLkbm(lk.getYwlsh());
                if (oldnb == null) {
                    throw new Exception("业务流水号[" + lk.getYwlsh() + "]不存在！");
                }

                nwbService.updateNb(lk);
            }

            //返回设置流水号
            re.setLkbm(lk.getYwlsh());

            re.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            re.setSuccess(false);
            re.setMessage(e.getMessage());

            //如果是修改，那么在异常之后，还需要添加上旅客编码
            if (CommonUtil.isEmpty(re.getLkbm()) && CommonUtil.isNotEmpty(ywlsh)) {
                re.setLkbm(ywlsh);
            }
        }
        re.setPostid(postid);

        return re;
    }

    /**
     * 一次提交不超过100条内出生登记记录（注意，不能混合调用）
     *
     * @param pcsbm  见postCSDJRecrods接口同名参数
     * @param type      见postCSDJRecrods接口同名参数
     * @param list_json 多条记录，格式：[{},{},{}...]
     * @return 返回如下： {
     * "success":true,												//成功返回true，失败返回false
     * "message":"",												//success=false, 返回错误消息
     * "list":[															//返回对list_json的对应处理情况，数组和list_json提交的数据长度一致
     * {...},
     * {...},
     * {...}
     * ],
     * "totalCount":3
     * }
     * list包含的元素对象值参加postNWBRecrod调用返回值如下：
     * {
     * "success": "true|false之一，表示成功或者失败",
     * "message":"错误消息",
     * "postid":"json数据包含的用户第三方ID值，用于返回对应",
     * "lkbm":"如果成功，那么返回入住流水号，必须回填此流水号，退房或者修改入住信息必须使用此凭证"
     * }
     */
    public String postCSDJRecrods(String pcsbm,String sbh,String md5, String type, String list_json) {
        LgApiLog log = new LgApiLog();
        log.setApiname("postCSDJRecrods");
        log.setBz("type=" + type + list_json);
        log.setLgbm(pcsbm);
        log.setLogsj(new Date());

        Gson gson = new GsonBuilder().create();
        String ip = WebServiceUtil.getIpaddress();
        log.setIp(ip);
        Hz2004ServiceZzj hz2004Service = (Hz2004ServiceZzj) SpringContextHolder.getBean("hz2004Service");

        List<ReturnBean> relist = new ArrayList<ReturnBean>();
        SimpleJson json = new SimpleJson();
        json.setSuccess(false);
        try {
            //进行设备验证
            WebServiceUtil.checkUser(pcsbm,sbh,md5);
            WebServiceUtil.checkUserLogin(pcsbm);

            if (type.equals("1")) {
                List<VcsdjBean> list = gson.fromJson(list_json, new TypeToken<List<VcsdjBean>>() {
                }.getType());
                if (list.size() > 100) {
                    throw new Exception("批量一次不能超过100条记录！");
                }

                for (VcsdjBean b : list) {
                    //ReturnBean re = postNWBRecrod(ip, lgbm, type, nwbService, b);
                    ReturnBean re = null;

                    re = postCSDJRecrod(ip, pcsbm, type, hz2004Service, b);


                    relist.add(re);
                }
            }
            json.setList(relist);
            json.setSuccess(true);
        } catch (Exception e) {
            json.setSuccess(false);
            json.setMessage(e.getMessage());
        }

        log.setHs(new Date().getTime() - log.getLogsj().getTime());
        log.setSfcg("1");
        try {
            saveLog(log);
        } catch (Exception e) {
            ;
        }

        return WebServiceUtil.toJson(json);
    }

    /**
     * 夫妻投靠 一次提交不超过100条
     *
     * @param pcsbm 需要使用接口验证参数
     * @param type     记录类型(暂无使用)
     * @param json      夫妻投靠json数据
     * @return
     */
    public String postFQTKRecrod(String pcsbm,String sbh,String md5, String type, String json) {
        String value = null;
        Call call2;
        try {
            call2 = new Call(url);
            call2.setOperationName("postFQTKRecrod");
            value = (String) call2.invoke(new Object[]{pcsbm, sbh, md5, type, json});
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return value;
    }

    /**
     * 夫妻投靠
     *
     * @param pcsbm 需要使用接口验证参数
     * @param type     记录类型(暂无使用)
     * @param list_json      夫妻投靠list_json数据
     * @return
     */
    public String postFQTKRecrods(String pcsbm,String sbh,String md5, String type, String list_json) {
        String value = null;
        Call call2;
        try {
            call2 = new Call(url);
            call2.setOperationName("postFQTKRecrods");
            value = (String) call2.invoke(new Object[]{pcsbm, sbh, md5, type, list_json});
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return value;
    }

    /**
     * 是户籍证明接口
     * 也是地市常口信息查询接口
     *
     * @param pcsbm 需要使用接口验证参数
     * @param type     记录类型(暂无使用)
     * @param sfzh     身份证号码
     * @return
     */
    public String queryRkjbxxBysfz(String pcsbm,String sbh,String md5, String sfzh, String type) {
        String value = null;
        Call call2;
        try {
            call2 = new Call(url);
            call2.setOperationName("queryRkjbxxBysfz");
            value = (String) call2.invoke(new Object[]{pcsbm, sbh, md5, sfzh, type});
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return value;
    }

    /**
     * 直系亲属关系证明接口
     *
     * @param pcsbm 需要使用接口验证参数
     * @param type     记录类型(暂无使用)
     * @param sfzh1    身份证号码1
     * @param sfzh2    身份证号码2
     * @return
     */
    public String queryRkjbxxQsgxBysfz(String pcsbm,String sbh,String md5,String sfzh1, String sfzh2, String type) {
        String value = null;
        Call call2;
        try {
            call2 = new Call(url);
            call2.setOperationName("queryRkjbxxQsgxBysfz");
            value = (String) call2.invoke(new Object[]{pcsbm, sbh, md5, sfzh1, sfzh2, type});
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return value;
    }


    /**
     * 查询全国人口信息 by sfzh
     *
     * @param pcsbm 需要使用接口验证参数
     * @param sfzh     身份证号码
     * @return
     */
    public String queryQgRkjbxxBysfz(String pcsbm,String sbh,String md5, String sfzh) {
        String value = null;
        Call call2;
        try {

            call2 = new Call(url);
            call2.setOperationName("queryQgRkjbxxBysfz");
            value = (String) call2.invoke(new Object[]{pcsbm, sbh, md5, sfzh});
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return value;
    }


    /**
     * 户口注销证明接口
     * 也是死亡注销证明
     *
     * @param pcsbm 需要使用接口验证参数
     * @param type     记录类型(暂无使用)
     * @param sfzh     身份证号码
     * @return
     */
    public String queryRkjbxxZxxxBysfz(String pcsbm,String sbh,String md5, String sfzh, String type) {
        String value = null;
        Call call2;
        try {
            call2 = new Call(url);
            call2.setOperationName("queryRkjbxxZxxxBysfz");
            value = (String) call2.invoke(new Object[]{pcsbm, sbh, md5, sfzh, type});
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return value;
    }

    /**
     * 查询临时身份记录
     *
     * @param pcsbm 需要使用接口验证参数
     * @param type     记录类型(暂无使用)
     * @param sfzh     身份证号码
     * @return
     */
    public String queryRkjbxxLssfBysfz(String pcsbm,String sbh,String md5, String sfzh, String type) {
        String value = null;
        Call call2;
        try {
            call2 = new Call(url);
            call2.setOperationName("queryRkjbxxLssfBysfz");
            value = (String) call2.invoke(new Object[]{pcsbm, sbh, md5, sfzh, type});
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return value;
    }

    /**
     * 同户关系证明接口
     *
     * @param pcsbm 需要使用接口验证参数
     * @param type     记录类型(暂无使用)
     * @param sfzh1    身份证号码1
     * @param sfzh2    身份证号码2
     * @return
     */
    public String queryThzmBysfz(String pcsbm,String sbh,String md5, String sfzh1, String sfzh2, String type) {
        String value = null;
        Call call2;
        try {
            call2 = new Call(url);
            call2.setOperationName("queryThzmBysfz");
            value = (String) call2.invoke(new Object[]{pcsbm, sbh, md5, sfzh1, sfzh2, type});
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return value;
    }

    /**
     * 附件材料上传
     *
     * @param pcsbm 需要使用接口验证参数
     * @param type     记录类型(暂无使用)
     * @param list_json 附件材料list_json数据
     * @return
     */
    public String postFjclRecrod(String pcsbm,String sbh,String md5, String type, String list_json) {
        String value = null;
        Call call2;
        try {
            call2 = new Call(url);
            call2.setOperationName("postFjclRecrod");
            value = (String) call2.invoke(new Object[]{pcsbm, sbh, md5, type, list_json});
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return value;
    }


    /**
     * 变更更正记录查询
     *
     * @param pcsbm 需要使用接口验证参数
     * @param type     记录类型(暂无使用)
     * @param sfzh     身份证号码
     * @return
     */
    public String queryBggzjlBysfz(String pcsbm,String sbh,String md5, String sfzh, String type) {
        String value = null;
        Call call2;
        try {
            call2 = new Call(url);
            call2.setOperationName("queryBggzjlBysfz");
            value = (String) call2.invoke(new Object[]{pcsbm, sbh, md5, sfzh, type});
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return value;
    }

    /**
     * 户成员记录查询
     *
     * @param pcsbm 需要使用接口验证参数
     * @param type     记录类型(暂无使用)
     * @param hh       hh
     * @return
     */
    public String queryHcyByhhnbid(String pcsbm,String sbh,String md5, String hh, String type) {
        String value = null;
        Call call2;
        try {
            call2 = new Call(url);
            call2.setOperationName("queryHcyByhhnbid");
            value = (String) call2.invoke(new Object[]{pcsbm, sbh, md5, hh, type});
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return value;
    }

    /**
     * 准迁证核验
     *
     * @param pcsbm 需要使用接口验证参数
     * @param sfzh      sfzh
     * @param zqzbh     准迁证编码
     * @param ssxq      省市县区代码
     * @param type
     * 1：代表准迁证核验  ssxq=迁往地省市县区代码
     * 2：迁移证核验		ssxq=迁出地省市县区代码
     * 3：出生证核验(暂不提供)
     * @return
     */
    public String queryQzHy(String pcsbm,String sbh,String md5, String sfzh, String zqzbh, String ssxq, String type) {
        String value = null;
        Call call2;
        try {
            call2 = new Call(url);
            call2.setOperationName("queryQzHy");
            value = (String) call2.invoke(new Object[]{pcsbm, sbh, md5, sfzh, zqzbh,ssxq,type});
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return value;
    }


    /**
     * 办理死亡注销登记
     *
     * @param pcsbm 需要使用接口验证参数
     * @param type     记录类型(暂未使用)
     * @param json     办理死亡注销记录的JSON编码，编码格式见文档说明。
     * @return 返回处理结果的JSON，格式如下： {
     * "success": "true|false之一，表示成功或者失败",
     * "message":"错误消息",
     * }
     */
    public String postSwzxRecrod(String pcsbm,String sbh,String md5, String type, String json) {
        LgApiLog log = new LgApiLog();
        log.setApiname("postSwzxRecrod");
        log.setBz("type=" + type);
        log.setLgbm(pcsbm);
        log.setLogsj(new Date());
        //返回的对象
        ReturnBean re = new ReturnBean();
        try {
            WebServiceUtil.checkUser(pcsbm,sbh,md5);
            WebServiceUtil.checkUserLogin(pcsbm);

            String ip = WebServiceUtil.getIpaddress();
            log.setIp(ip);
            Hz2004ServiceZzj hz2004Service = (Hz2004ServiceZzj) SpringContextHolder.getBean("hz2004ServiceZzj");

            VswzxywBean vswzxyw = WebServiceUtil.getJsonData(VswzxywBean.class, json);
            vswzxyw.setSbh(sbh);

            re = postSwzxYwRecrod(ip, pcsbm, type, hz2004Service, vswzxyw);
        } catch (Exception e) {
            re.setMessage(e.getMessage());
            re.setSuccess(false);
        }
        log.setHs(new Date().getTime() - log.getLogsj().getTime());
        log.setSfcg(re.isSuccess() ? "1" : "0");
        try {
            saveLog(log);
        } catch (Exception e) {
            ;
        }

        return WebServiceUtil.toJson(re);
    }

    /**
     * 死亡注销的通用方法
     *
     * @param ip
     * @param pcsbm
     * @param type
     * @param nwbService
     * @param bean
     * @return
     */
    private ReturnBean postSwzxYwRecrod(String ip, String pcsbm, String type, Hz2004ServiceZzj nwbService, VswzxywBean bean) {
        String ywlsh = null;
        ReturnBean re = new ReturnBean();
        String postid = null;
        try {
            postid = bean.getPostid();
            ywlsh = bean.getYwlsh();

            //下面模拟nwbaction里面的流程，保持nwbservice的业务流程不变
            boolean c_u = true;

            //如果第三方没有带业务流水号，那么默认为新增
            if (CommonUtil.isNotEmpty(bean.getYwlsh())) {
                c_u = false;
            } else {
                //判断数据库是否已经记录过此记录KEY
                ApiYs ys = nwbService.getApiYsByPostid(pcsbm, type, postid);
                if (ys != null) {
                    //如果有，那么修改原记录(用户不带，那么智能判断)
                    c_u = false;
                    bean.setYwlsh(ys.getLkbm());
                }
            }

            //将nbbean转换为lknb对象
            Vswzxyw vs = WebServiceUtil.getSwzx(nwbService, ip, pcsbm, bean);


            if (c_u) {
                //nwbService.saveVswzxyw(vs, postid);
                WebServiceUtil.processSwzxyw(vs);
            } else {
                Vswzxyw oldnb = nwbService.queryVswzxywByYwlsh(vs.getYwlsh());
                if (oldnb == null) {
                    throw new Exception("业务流水号[" + vs.getYwlsh() + "]不存在！");
                }
                nwbService.updateVswzxyw(vs);
            }
            //返回设置流水号
            re.setLkbm(vs.getYwlsh());
            re.setSuccess(true);
        } catch (Exception e) {
            re.setSuccess(false);
            re.setMessage(e.getMessage());

            //如果是修改，那么在异常之后，还需要添加上业务流水号
            if (CommonUtil.isEmpty(re.getLkbm()) && CommonUtil.isNotEmpty(ywlsh)) {
                re.setLkbm(ywlsh);
            }
        }
        re.setPostid(postid);

        return re;
    }


    /**
     * 辅项变更记录
     *
     * @param pcsbm 需要使用接口验证参数
     * @param type     记录类型：（暂无使用）
     * @param json     辅项变更记录的JSON编码，编码格式见文档说明。
     * @return 返回处理结果的JSON，格式如下： {
     * "success": "true|false之一，表示成功或者失败",
     * "message":"错误消息",
     * }
     */
    public String postBggzxxbRecrod(String pcsbm,String sbh,String md5, String type, String json) {
        LgApiLog log = new LgApiLog();
        log.setApiname("postBggzxxbRecrod");
        log.setBz("type=" + type);
        log.setLgbm(pcsbm);
        log.setLogsj(new Date());
        //返回的对象
        ReturnBean re = new ReturnBean();
        try {
            WebServiceUtil.checkUser(pcsbm,sbh,md5);
            WebServiceUtil.checkUserLogin(pcsbm);

            String ip = WebServiceUtil.getIpaddress();
            log.setIp(ip);
            Hz2004ServiceZzj hz2004Service = (Hz2004ServiceZzj) SpringContextHolder.getBean("hz2004ServiceZzj");

            VbggzxxbBean vswzxyw = WebServiceUtil.getJsonData(VbggzxxbBean.class, json);
            vswzxyw.setSbh(sbh);
            re = postBggzxxbRecrod(ip, pcsbm, type, hz2004Service, vswzxyw);
        } catch (Exception e) {
            re.setMessage(e.getMessage());
            re.setSuccess(false);
        }
        log.setHs(new Date().getTime() - log.getLogsj().getTime());
        log.setSfcg(re.isSuccess() ? "1" : "0");
        try {
            saveLog(log);
        } catch (Exception e) {
            ;
        }

        return WebServiceUtil.toJson(re);
    }

    /**
     * 辅项变更的通用方法
     *
     * @param ip
     * @param pcsbm
     * @param type
     * @param nwbService
     * @param bean
     * @return
     */
    private ReturnBean postBggzxxbRecrod(String ip, String pcsbm, String type, Hz2004ServiceZzj nwbService, VbggzxxbBean bean) {
        String ywlsh = null;
        ReturnBean re = new ReturnBean();
        String postid = null;
        try {
            postid = bean.getPostid();
            ywlsh = bean.getYwlsh();

            //下面模拟nwbaction里面的流程，保持nwbservice的业务流程不变
            boolean c_u = true;

            //如果第三方没有带业务流水号，那么默认为新增
            if (CommonUtil.isNotEmpty(bean.getYwlsh())) {
                c_u = false;
            } else {
                //判断数据库是否已经记录过此记录KEY
                ApiYs ys = nwbService.getApiYsByPostid(pcsbm, type, postid);
                if (ys != null) {
                    //如果有，那么修改原记录(用户不带，那么智能判断)
                    c_u = false;
                    bean.setYwlsh(ys.getLkbm());
                }
            }

            //将nbbean转换为lknb对象
            Vbggzxxb vs = WebServiceUtil.getVbggzxxb(nwbService, ip, pcsbm, bean);


            if (c_u) {
                //nwbService.saveVbggzxxb(vs, postid);
                WebServiceUtil.processBggzyw(bean);
            } else {
                Vbggzxxb oldnb = nwbService.queryVbggzxxbByYwlsh(vs.getYwlsh());
                if (oldnb == null) {
                    throw new Exception("业务流水号[" + vs.getYwlsh() + "]不存在！");
                }
                nwbService.updateVbggzxxb(vs);
            }
            //返回设置流水号
            re.setLkbm(vs.getYwlsh());
            re.setSuccess(true);
        } catch (Exception e) {
            re.setSuccess(false);
            re.setMessage(e.getMessage());

            //如果是修改，那么在异常之后，还需要添加上业务流水号
            if (CommonUtil.isEmpty(re.getLkbm()) && CommonUtil.isNotEmpty(ywlsh)) {
                re.setLkbm(ywlsh);
            }
        }
        re.setPostid(postid);

        return re;
    }

    /**
     * 获取未来 第 past 天的日期
     *
     * @param past 传递int 为几  就加几天
     * @return
     */
    public static String getFetureDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        System.out.println(result);
        return result;
    }
}
