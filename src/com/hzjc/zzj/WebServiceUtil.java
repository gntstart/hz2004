package com.hzjc.zzj;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hzjc.hz2004.base.SpringContextHolder;
import com.hzjc.hz2004.base.SystemConfig;
import com.hzjc.hz2004.base.encode.Hex;
import com.hzjc.hz2004.base.login.AuthToken;
import com.hzjc.hz2004.base.login.HSession;
import com.hzjc.hz2004.exception.ServiceException;
import com.hzjc.hz2004.po.PoXT_YHXXB;
import com.hzjc.hz2004.po.entity.*;
import com.hzjc.hz2004.service.HjService;
import com.hzjc.hz2004.service.LoginService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.JSONUtil;
import com.hzjc.hz2004.vo.*;
import com.hzjc.util.StringUtils;
import com.hzjc.zzj.bean.*;
import com.hzjc.hz2004.service.hzpt.Hz2004ServiceZzj;
import com.hzjc.zzj.util.DateConverter;
import com.hzjc.zzj.util.DateHelper;
import com.hzjc.zzj.util.SpringContainer;
import org.apache.axis.MessageContext;
import org.apache.axis.transport.http.HTTPConstants;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class WebServiceUtil {


    /**
     * 将API的bean转换为内宾对象
     *
     * @param nwbService
     * @param bean
     * @return private String    postid;	//第三方记录ID, 用于返回对应
     * private String	    lhsfz;	//落户身份证
     * private String	xm; 	//姓名，不超过40个字节
     * private String	xb;	//性别
     * private String	mz;	//民族
     * private String	csrq; //出生日期，格式：yyyyMMdd
     * private String  	ywlsh;	//业务流水号
     * private Date	cjsj;//创建时间
     * private String	csdssxq;	//出生地省市县/区
     * private String	cdsxz;	//出生地详细地址
     * private String	jgssxq;	//籍贯省市县/区
     * private String	hb;	//户别默认和户主一致
     * private String	jthzl;	//集体户种类（户别为集体户时必填）
     * private String	csdjlb;//出生登记类别
     * private String	cszmbh;//出生证明编号
     * private String	jhryxm;	//监护人一姓名
     * private String	jhrygmsfhm;//监护人一身份号码
     * private String	jhrylxdh;		//监护人一联系电话
     * private String	jhryjhgx;		//监护人一关系
     * private String	jhrexm;		//监护人二姓名
     * private String	jhregmsfhm;//监护人二身份号码
     * private String	jhrelxdh;		//监护人二联系电话
     * private String	jhrejhgx;		//监护人二关系
     * private String	fqxm;	//父亲姓名（父亲母亲信息两组必须填一组）
     * private String	Fqgmsfhm;	//父亲身份号码（父亲母亲信息两组必须填一组）
     * private String	Mqxm;		//母亲姓名（父亲母亲信息两组必须填一组）
     * private String	Mqgmsfhm;	//母亲身份号码（父亲母亲信息两组必须填一组）
     * private String	    Sbrxm;	//申报人姓名
     * private String	Sbrsfz;	//申报人身份证
     */
    static public Vcsdj getCsdj(Hz2004ServiceZzj nwbService, String ip, String pcsbm, VcsdjBean b) {
        Vcsdj nb = new Vcsdj();
        nb.setPostid(b.getPostid());
        nb.setLhsfz(b.getLhsfz());
        nb.setXm(b.getXm());
        nb.setXb(b.getXb());
        nb.setMz(b.getMz());
        nb.setYhzgx(b.getYhzgx());

        nb.setCsrq(b.getCsrq());
        nb.setCsdssxq(b.getCsdssxq());
        nb.setCssj(b.getCssj());
        nb.setCsdxz(b.getCsdxz());
        nb.setCsdjlb(b.getCsdjlb());
        nb.setCszmbh(b.getCszmbh());
        nb.setJgssxq(b.getJgssxq());
        nb.setHb(b.getHb());
        nb.setJthzl(b.getJthzl());
        nb.setJhryxm(b.getJhryxm());
        nb.setJhrygmsfhm(b.getJhrygmsfhm());
        nb.setJhrylxdh(b.getJhrylxdh());
        nb.setJhryjhgx(b.getJhryjhgx());
        nb.setJhrexm(b.getJhrexm());
        nb.setJhregmsfhm(b.getJhregmsfhm());
        nb.setJhrelxdh(b.getJhrelxdh());
        nb.setJhrejhgx(b.getJhrejhgx());
        nb.setFqxm(b.getFqxm());
        nb.setFqgmsfhm(b.getFqgmsfhm());
        nb.setMqxm(b.getMqxm());
        nb.setMqgmsfhm(b.getMqgmsfhm());
        nb.setSbrxm(b.getSbrxm());
        nb.setSbrsfz(b.getSbrsfz());
        nb.setSbrlxdh(b.getSbrlxdh());
        nb.setCjsj(DateHelper.formateDate(new Date(), "yyyyMMddHHmmss"));
        nb.setPcs(pcsbm);
        nb.setSbh(b.getSbh());
        if (CommonUtil.isEmpty(b.getYwlsh()))
            nb.setYwlsh(nwbService.updateLkbm(pcsbm));
        else
            nb.setYwlsh(b.getYwlsh());

        Date d = DateConverter.convertDateByFormat(b.getCsrq(), "yyyyMMdd");
        if (d == null)
            throw new ServiceException("出生日期格式错误，必须提供并且必须符合yyyyMMdd格式，比如19891223！");
        nb.setCsrq(b.getCsrq());


        return nb;
    }

    /**
     * 死亡注销bean 转 Vswzxyw
     * @param nwbService
     * @param ip
     * @param pcsbm
     * @param b
     * @return
     */
    static public Vswzxyw getSwzx(Hz2004ServiceZzj nwbService, String ip, String pcsbm, VswzxywBean b) {
        Vswzxyw vs = new Vswzxyw();
        vs.setId(b.getPostid());
        vs.setBsbrid(b.getBsbrid());
        vs.setBsbrname(b.getBsbrname());
        vs.setBsbridcard(b.getBsbridcard());
        vs.setSbrname(b.getSbrname());
        vs.setSbridcard(b.getSbridcard());
        vs.setSwzxlb(b.getSwzxlb());
        vs.setSwzmbh(b.getSwzmbh());
        vs.setSbrjtgx(b.getSbrjtgx());

        vs.setCjsj(DateHelper.formateDate(new Date(), "yyyyMMddHHmmss"));
        vs.setIp(ip);
        vs.setSbh(b.getSbh());
        vs.setPcs(pcsbm);
        if (CommonUtil.isEmpty(b.getYwlsh()))
            vs.setYwlsh(nwbService.updateLkbm(pcsbm));
        else
            vs.setYwlsh(b.getYwlsh());

        Date d = DateConverter.convertDateByFormat(b.getSwsj(), "yyyyMMdd");
        if (d == null)
            throw new ServiceException("死亡日期格式错误，必须提供并且必须符合yyyyMMdd格式，比如19891223！");
        vs.setSwsj(b.getSwsj());


        return vs;
    }

    /**
     * 变更更正bean 转 Vbggzxxb
     * @param nwbService
     * @param ip
     * @param pcsbm
     * @param b
     * @return
     */
    static public Vbggzxxb getVbggzxxb(Hz2004ServiceZzj nwbService, String ip, String pcsbm, VbggzxxbBean b) {
        Vbggzxxb vs = new Vbggzxxb();
        vs.setId(b.getPostid());
        vs.setRyid(b.getRyid());
        vs.setXm(b.getXm());
        vs.setGmsfhm(b.getGmsfhm());
        vs.setYhzgx(b.getYhzgx());
        vs.setDhhm(b.getDhhm());
        vs.setHyzk(b.getHyzk());
        if(CommonUtil.isNotEmpty(b.getZp())){
            BASE64Decoder d = new BASE64Decoder();
            try {
                byte[] data = d.decodeBuffer(b.getZp());
                vs.setZp(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        vs.setSg(b.getSg());
        vs.setMlxz(b.getMlxz());
        vs.setFwcs(b.getFwcs());
        vs.setWhcd(b.getWhcd());
        vs.setByzk(b.getByzk());
        vs.setXx(b.getXx());
        vs.setZy(b.getZy());
        vs.setZylb(b.getZylb());
        vs.setPoxm(b.getPoxm());
        vs.setPogmsfhm(b.getPogmsfhm());
        vs.setZjxy(b.getZjxy());
        vs.setLxdh(b.getLxdh());

        vs.setCjsj(DateHelper.formateDate(new Date(), "yyyyMMddHHmmss"));

        vs.setSbh(b.getSbh());
        vs.setPcs(pcsbm);
        if (CommonUtil.isEmpty(b.getYwlsh()))
            vs.setYwlsh(nwbService.updateLkbm(pcsbm));
        else
            vs.setYwlsh(b.getYwlsh());

        return vs;
    }

    /**
     * 对象---》字符串
     *
     * @param src
     * @return
     */
    static public String toJson(Object src) {
        GsonBuilder build = new GsonBuilder();
        Gson gson = build.create();
        return gson.toJson(src);
    }

    /**
     * 对象转换：字符串---》对象
     *
     * @param classOfT
     * @param data
     * @return
     */
    static public <T> T getJsonData(Class<T> classOfT, String data) {
        GsonBuilder build = new GsonBuilder();
        //if(this.dateStyle != null) {
        //	build.setDateFormat(this.dateStyle);
        //}
        Gson gson = build.create();
        return gson.fromJson(data, classOfT);
    }

    static public String MD5AndHex(String msginfo) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            byte[] buff = m.digest(msginfo.getBytes());
            return new String(Hex.encode(buff));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return msginfo;
    }

    static public String array2ToString(String[][] obj) {
        if (obj == null)
            return "";

        String str = "";
        for (int i = 0; i < obj.length; i++) {
            if (i > 0) str += ",";

            str += "{";
            for (int j = 0; j < obj[i].length; j++) {
                if (j > 0) str += ",";

                str += "\"" + obj[i][j] + "\"";
            }
            str += "}\n";
        }

        return str;
    }

    static public String arrayToString(String[] obj) {
        if (obj == null)
            return "";

        String str = "";
        for (int i = 0; i < obj.length; i++)
            str += "re[" + i + "]=" + obj[i] + ";";
        return str;
    }

    //Zzj设备验证
    static public boolean checkUser(String pcsbm,String sbh,String md5) throws Exception {
        Hz2004ServiceZzj apiService = (Hz2004ServiceZzj) SpringContainer.getObject("hz2004ServiceZzj");
        //Hz2004ServiceZzj apiService = (Hz2004ServiceZzj) SpringContextHolder.getBean("hz2004ServiceZzj",Hz2004ServiceZzj.class);
        XkzApi api = null;

        api = apiService.getXkzApi(pcsbm, sbh);
        if (api == null)
            throw new Exception("派出所编码[" + pcsbm + "]不是API开机用户！");

        MessageDigest m = MessageDigest.getInstance("MD5");
        byte[] buff = m.digest(api.getPwd().getBytes());
        String oldmd5 = new String(Hex.encode(buff));

        //检查MD5
        if (!oldmd5.equals(md5))
            throw new Exception("口令校验码错误！");

		/*if(CommonUtil.isNotEmpty(api.getIp())){
			String ip = getIpaddress();
			//检查IP
			if(api.getIp().indexOf(ip)<0)
				throw new Exception("错误，账号所在IP地址" + ip + "不符！");
		}*/

        if (api.getJssj() != null && api.getJssj().before(new Date()))
            throw new Exception("账号已经到期！");


        return true;

    }

    //进行ZZj办理业务时进行登录
    static public boolean checkUserLogin(String pcsbm) throws Exception {
        try{
            Hz2004ServiceZzj apiService = (Hz2004ServiceZzj) SpringContainer.getObject("hz2004ServiceZzj");
            PoXT_YHXXB api = null;
            //从配置文件中读取zzj登录
            String yhdlm = SystemConfig.getSystemConfig("zzjyh");
            api = apiService.getYhxxb(pcsbm, yhdlm);
            if (api == null)
                throw new Exception("派出所编码[" + pcsbm + "] 不是API开机用户, 获取人口系统登录ZZJ用户异常");

            if(CommonUtil.isEmpty(api.getYhdlm()) || CommonUtil.isEmpty(api.getDqbm())){
                throw new RuntimeException("必须提供yhdlm和dqbm参数！");
            }

            //参数yhdlm在GET方式提交之前，必须做2次urlencode
            String yhdlm1 = java.net.URLDecoder.decode(api.getYhdlm(), "UTF-8");

            //完成自动登录
            LoginService loginService = (LoginService)SpringContextHolder.getBean("loginService");
            AuthToken user = loginService.loginAuthToken(yhdlm, api.getDqbm());
            HSession.saveUser(user);
            return true;
        }catch(Exception e){
            throw new RuntimeException("登录异常，请稍后重试");
        }
    }

    static public String getIpaddress() {
        MessageContext mc = MessageContext.getCurrentContext();
        HttpServletRequest request = (HttpServletRequest) mc
                .getProperty(HTTPConstants.MC_HTTP_SERVLETREQUEST);
        String ip = request.getRemoteAddr();

        return ip;
    }

    /**
     * 获取推送数据
     * 办理 RKTX 出生业务
     *
     * @param lk 出生登记资料
     */
    static public String processCsdjyw(Vcsdj lk, String hhnbid) {
        /**
         * 处理迁入登记业务。
         * @param voLhhdxx - 立户户地信息
         * @param voRhhdxx - 入户户地信息
         * @param voSbjbxx - 申报基本信息
         * @param voQrdjxx[] - 迁入登记信息
         * @param voChxx[] - 重号信息
         * @param voBggzxx[] - 变更更正信息
         **/
        //立户户地信息
        String s1 = "";
        VoLhhdxx voLhhdxx = null;
        if (com.hzjc.hz2004.util.CommonUtil.isNotEmpty(s1)) {
            voLhhdxx = JSONUtil.getJsonData(s1, "yyyyMMdd", VoLhhdxx.class);
        }

        //入户户地信息
        VoRhhdxx voRhhdxx = null;
        if (com.hzjc.hz2004.util.CommonUtil.isNotEmpty(hhnbid)) {
            String hhnbidstr = "{\"hhnbid\": " + hhnbid + "}";
            voRhhdxx = JSONUtil.getJsonData(hhnbidstr, "yyyyMMdd", VoRhhdxx.class);
        }

        // 申报基本信息
        VoSbjbxx voSbjbxx = new VoSbjbxx();
        if (com.hzjc.hz2004.util.CommonUtil.isNotEmpty(lk.getSbrxm())) {
            voSbjbxx.setSbryxm(lk.getSbrxm());
            voSbjbxx.setSbrgmsfhm(lk.getSbrsfz());
            if (com.hzjc.hz2004.util.CommonUtil.isEmpty(voSbjbxx.getSbsj())) {
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                String format = df.format(new Date());
                voSbjbxx.setSbsj(format);
            }
        }

        //迁入登记信息
        List<VoCsdjxx> voCsdjxx = new ArrayList<VoCsdjxx>();
        if (com.hzjc.hz2004.util.CommonUtil.isNotEmpty(lk.getXm())) {
            TypeToken<List<VoCsdjxx>> typeToken = new TypeToken<List<VoCsdjxx>>() {
            };

            VoCsdjxx voCsdjxx1 = setVoCsdjxx(lk);
            voCsdjxx.add(voCsdjxx1);
        }

        //重号信息
        String s5 = "";
        List<VoChxx> voChxx = new ArrayList<VoChxx>();
        if (com.hzjc.hz2004.util.CommonUtil.isNotEmpty(s5)) {
            TypeToken<List<VoChxx>> typeToken = new TypeToken<List<VoChxx>>() {
            };
            voChxx = JSONUtil.getJsonData(typeToken, s5);
        }

        //变更更正信息
        String s6 = "";
        List<VoBggzxxEx> voBggzxxEx = com.hzjc.hz2004.util.CommonUtil.getVoBggzxxEx(s6);
        VoCsdjywfhxx re = null;
        HjService hjService = (HjService) SpringContainer.getObject("hjService");
        if (voBggzxxEx != null) {
            re = hjService.processCsdjyw(voLhhdxx, voRhhdxx, voSbjbxx, voCsdjxx.toArray(new VoCsdjxx[]{}), voBggzxxEx.toArray(new VoBggzxxEx[]{}));
        } else {
            re = hjService.processCsdjyw(voLhhdxx, voRhhdxx, voSbjbxx, voCsdjxx.toArray(new VoCsdjxx[]{}), null);
        }

        return toJson(re);
    }

    /**
     * 处理死亡注销业务
     * @return
     */
    static public String processSwzxyw(Vswzxyw vs){
        List<VoSwzxxx> VoSwzxxx = new ArrayList<VoSwzxxx>();
        VoSbjbxx voSbjbxx = new VoSbjbxx();

        if(CommonUtil.isNotEmpty(vs.getSbridcard())){
            voSbjbxx.setSbrgmsfhm(vs.getSbridcard());
            voSbjbxx.setSbryxm(vs.getSbrname());
            voSbjbxx.setSbsj("");
        }

        if(CommonUtil.isNotEmpty(vs.getBsbrid())){
            VoSwzxxx voSwzxxx1 = new VoSwzxxx();
            System.out.println(vs.getBsbrid());
            System.out.println(Long.parseLong(vs.getBsbrid().trim()));
            voSwzxxx1.setRynbid(Long.parseLong(vs.getBsbrid().trim()));
            voSwzxxx1.setSwrq(vs.getSwsj());
            voSwzxxx1.setSwzmbh(vs.getSwzmbh());
            voSwzxxx1.setSbrjtgx(vs.getSbrjtgx());
            voSwzxxx1.setSwzxlb(vs.getSwzxlb());
            VoSwzxxx.add(voSwzxxx1);
        }

        List<VoBggzxxEx> voBggzxxEx = CommonUtil.getVoBggzxxEx("");

        VoSwzxywfhxx fk = null;
        HjService hjService = (HjService) SpringContainer.getObject("hjService");
        if(voBggzxxEx != null) {
            fk = hjService.processSwzxyw(voSbjbxx, VoSwzxxx.toArray(new VoSwzxxx[]{}), voBggzxxEx.toArray(new VoBggzxxEx[]{}));
        }else {
            fk = hjService.processSwzxyw(voSbjbxx, VoSwzxxx.toArray(new VoSwzxxx[]{}), null);
        }

        return toJson(fk);
    }


    /**
     * 处理辅项变更业务
     * @return
     */
    static public String processBggzyw(VbggzxxbBean bean){
        VoSbjbxx voSbjbxx = new VoSbjbxx();
        if(CommonUtil.isNotEmpty(bean.getRyid())){
            //封装申报人的信息
            voSbjbxx.setSbryxm(bean.getSbryxm());
            voSbjbxx.setSbrgmsfhm(bean.getSbrgmsfhm());
            //voSbjbxx = JSONUtil.getJsonData(sbjbxx, "yyyyMMdd", VoSbjbxx.class);
            if(CommonUtil.isEmpty(voSbjbxx.getSbsj())) {
                voSbjbxx.setSbsj(StringUtils.getServiceDate());
            }
        }

        //通用处理变更更正信息:注意业务组装格式
        List<VoBggzxxEx> voBggzxxEx = getVoBggzxxEx(bean);

        VoBggzywfhxx fk = null;
        HjService hjService = (HjService) SpringContainer.getObject("hjService");
        if(voBggzxxEx != null) {
            fk = hjService.processBggzyw(voSbjbxx, voBggzxxEx.toArray(new VoBggzxxEx[]{}));
        }else {
            fk = hjService.processBggzyw(voSbjbxx, null);
        }

        return toJson(fk);
    }


    /**
     * 封装辅项变更VoBggzxxEx
     * @param bean
     * @return
     */
    static public List<VoBggzxxEx> getVoBggzxxEx(VbggzxxbBean bean){
        List<VoBggzxxEx> voBggzxxEx = new ArrayList<>();
        //封装 VoBggzxxEx
        VoBggzxxEx voBggzxxEx1 = new VoBggzxxEx();
        voBggzxxEx1.setFlag(1);
        voBggzxxEx1.setRynbid(Long.parseLong(bean.getRyid()));

        List<VoBggzxx> voBggzxx = getVoBggzxx(bean);
        voBggzxxEx1.setBggzxxList(voBggzxx);

        voBggzxxEx.add(voBggzxxEx1);
        return voBggzxxEx;
    }

    static public List<VoBggzxx> getVoBggzxx(VbggzxxbBean bean){
        List<VoBggzxx> list = new ArrayList<>();

        //判断与户主关系是否更改
        if(CommonUtil.isNotEmpty(bean.getYhzgx())){
            //封装修改信息
            VoBggzxx voBggzxx = new VoBggzxx();
            voBggzxx.setRynbid(Long.parseLong(bean.getRyid()));
            voBggzxx.setBggzlb("91");
            voBggzxx.setSfbczpdzplsb(true);
            //变更更正项目
            voBggzxx.setBggzxm("yhzgx");
            voBggzxx.setBggzhnr(bean.getYhzgx());
            list.add(voBggzxx);
        }
        //判断电话号码是否更改
        if(CommonUtil.isNotEmpty(bean.getDhhm())){
            //封装修改信息
            VoBggzxx voBggzxx = new VoBggzxx();
            voBggzxx.setRynbid(Long.parseLong(bean.getRyid()));
            voBggzxx.setBggzlb("91");
            voBggzxx.setSfbczpdzplsb(true);
            voBggzxx.setBggzxm("dhhm");
            voBggzxx.setBggzhnr(bean.getDhhm());
            list.add(voBggzxx);
        }
        //判断婚姻状况是否更改
        if(CommonUtil.isNotEmpty(bean.getHyzk())){
            //封装修改信息
            VoBggzxx voBggzxx = new VoBggzxx();
            voBggzxx.setRynbid(Long.parseLong(bean.getRyid()));
            voBggzxx.setBggzlb("91");
            voBggzxx.setSfbczpdzplsb(true);
            voBggzxx.setBggzxm("hyzk");
            voBggzxx.setBggzhnr(bean.getHyzk());
            list.add(voBggzxx);
        }
        //判断身高是否更改
        if(CommonUtil.isNotEmpty(bean.getSg())){
            //封装修改信息
            VoBggzxx voBggzxx = new VoBggzxx();
            voBggzxx.setRynbid(Long.parseLong(bean.getRyid()));
            voBggzxx.setBggzlb("91");
            voBggzxx.setSfbczpdzplsb(true);
            //变更更正项目
            voBggzxx.setBggzxm("sg");
            voBggzxx.setBggzhnr(bean.getSg());
            list.add(voBggzxx);
        }
        //判断门（楼）详址是否更改
        if(CommonUtil.isNotEmpty(bean.getMlxz())){
            //封装修改信息
            VoBggzxx voBggzxx = new VoBggzxx();
            voBggzxx.setRynbid(Long.parseLong(bean.getRyid()));
            voBggzxx.setBggzlb("91");
            voBggzxx.setSfbczpdzplsb(true);
            //变更更正项目
            voBggzxx.setBggzxm("mlxz");
            voBggzxx.setBggzhnr(bean.getMlxz());
            list.add(voBggzxx);
        }
        //判断服务处所是否更改
        if(CommonUtil.isNotEmpty(bean.getFwcs())){
            //封装修改信息
            VoBggzxx voBggzxx = new VoBggzxx();
            voBggzxx.setRynbid(Long.parseLong(bean.getRyid()));
            voBggzxx.setBggzlb("91");
            voBggzxx.setSfbczpdzplsb(true);

            voBggzxx.setBggzxm("fwcs");
            voBggzxx.setBggzhnr(bean.getFwcs());
            list.add(voBggzxx);
        }
        //判断文化程度是否更改
        if(CommonUtil.isNotEmpty(bean.getWhcd())){
            //封装修改信息
            VoBggzxx voBggzxx = new VoBggzxx();
            voBggzxx.setRynbid(Long.parseLong(bean.getRyid()));
            voBggzxx.setBggzlb("91");
            voBggzxx.setSfbczpdzplsb(true);

            voBggzxx.setBggzxm("whcd");
            voBggzxx.setBggzhnr(bean.getWhcd());
            list.add(voBggzxx);
        }
        //判断兵役状况是否更改
        if(CommonUtil.isNotEmpty(bean.getByzk())){
            //封装修改信息
            VoBggzxx voBggzxx = new VoBggzxx();
            voBggzxx.setRynbid(Long.parseLong(bean.getRyid()));
            voBggzxx.setBggzlb("91");
            voBggzxx.setSfbczpdzplsb(true);
            //变更更正项目
            voBggzxx.setBggzxm("byzk");
            voBggzxx.setBggzhnr(bean.getByzk());
            list.add(voBggzxx);
        }
        //判断血型是否更改
        if(CommonUtil.isNotEmpty(bean.getXx())){
            //封装修改信息
            VoBggzxx voBggzxx = new VoBggzxx();
            voBggzxx.setRynbid(Long.parseLong(bean.getRyid()));
            voBggzxx.setBggzlb("91");
            voBggzxx.setSfbczpdzplsb(true);
            //变更更正项目
            voBggzxx.setBggzxm("xx");
            voBggzxx.setBggzhnr(bean.getXx());
            list.add(voBggzxx);
        }
        //判断职业是否更改
        if(CommonUtil.isNotEmpty(bean.getZy())){
            //封装修改信息
            VoBggzxx voBggzxx = new VoBggzxx();
            voBggzxx.setRynbid(Long.parseLong(bean.getRyid()));
            voBggzxx.setBggzlb("91");
            voBggzxx.setSfbczpdzplsb(true);
            //变更更正项目
            voBggzxx.setBggzxm("zy");
            voBggzxx.setBggzhnr(bean.getZy());
            list.add(voBggzxx);
        }
        //判断职业类别是否更改
        if(CommonUtil.isNotEmpty(bean.getZylb())){
            //封装修改信息
            VoBggzxx voBggzxx = new VoBggzxx();
            voBggzxx.setRynbid(Long.parseLong(bean.getRyid()));
            voBggzxx.setBggzlb("91");
            voBggzxx.setSfbczpdzplsb(true);
            //变更更正项目
            voBggzxx.setBggzxm("zylb");
            voBggzxx.setBggzhnr(bean.getZylb());
            list.add(voBggzxx);
        }
        //判断配偶姓名是否更改
        if(CommonUtil.isNotEmpty(bean.getPoxm())){
            //封装修改信息
            VoBggzxx voBggzxx = new VoBggzxx();
            voBggzxx.setRynbid(Long.parseLong(bean.getRyid()));
            voBggzxx.setBggzlb("91");
            voBggzxx.setSfbczpdzplsb(true);
            //变更更正项目
            voBggzxx.setBggzxm("poxm");
            voBggzxx.setBggzhnr(bean.getPoxm());
            list.add(voBggzxx);
        }
        //判断配偶公民身份号码是否更改
        if(CommonUtil.isNotEmpty(bean.getPogmsfhm())){
            //封装修改信息
            VoBggzxx voBggzxx = new VoBggzxx();
            voBggzxx.setRynbid(Long.parseLong(bean.getRyid()));
            voBggzxx.setBggzlb("91");
            voBggzxx.setSfbczpdzplsb(true);
            //变更更正项目
            voBggzxx.setBggzxm("pogmsfhm");
            voBggzxx.setBggzhnr(bean.getPogmsfhm());
            list.add(voBggzxx);
        }
        //判断宗教信仰是否更改
        if(CommonUtil.isNotEmpty(bean.getZjxy())){
            //封装修改信息
            VoBggzxx voBggzxx = new VoBggzxx();
            voBggzxx.setRynbid(Long.parseLong(bean.getRyid()));
            voBggzxx.setBggzlb("91");
            voBggzxx.setSfbczpdzplsb(true);
            //变更更正项目
            voBggzxx.setBggzxm("zjxy");
            voBggzxx.setBggzhnr(bean.getZjxy());
            list.add(voBggzxx);
        }
        //判断联系电话是否更改
        if(CommonUtil.isNotEmpty(bean.getLxdh())){
            //封装修改信息
            VoBggzxx voBggzxx = new VoBggzxx();
            voBggzxx.setRynbid(Long.parseLong(bean.getRyid()));
            voBggzxx.setBggzlb("91");
            voBggzxx.setSfbczpdzplsb(true);
            //变更更正项目
            voBggzxx.setBggzxm("lxdh");
            voBggzxx.setBggzhnr(bean.getLxdh());
            list.add(voBggzxx);
        }
        return list;
    }


    /**
     * 封装 出生登记数据
     *
     * @param lk
     * @return
     */
    static public VoCsdjxx setVoCsdjxx(Vcsdj lk) {
        VoCsdjxx voCsdjxx = new VoCsdjxx();
        voCsdjxx.setXm(lk.getXm());
        voCsdjxx.setXb(lk.getXb());
        voCsdjxx.setMz(lk.getMz());
        voCsdjxx.setCsrq(lk.getCsrq());
        voCsdjxx.setCsdssxq(lk.getCsdssxq());
        voCsdjxx.setCsdxz(lk.getCsdxz());
        voCsdjxx.setJgssxq(lk.getJgssxq());
        voCsdjxx.setCsdjlb(lk.getCsdjlb());
        voCsdjxx.setHb(lk.getHb());
        voCsdjxx.setCszmbh(lk.getCszmbh());
        voCsdjxx.setYhzgx(lk.getYhzgx());
        voCsdjxx.setJhryxm(lk.getJhryxm());
        voCsdjxx.setJhrygmsfhm(lk.getJhrygmsfhm());
        voCsdjxx.setJhrylxdh(lk.getJhrylxdh());
        voCsdjxx.setJhryjhgx(lk.getJhryjhgx());

        voCsdjxx.setJhrexm(lk.getJhrexm());
        voCsdjxx.setJhregmsfhm(lk.getJhregmsfhm());
        voCsdjxx.setJhrelxdh(lk.getJhrelxdh());
        voCsdjxx.setJhrejhgx(lk.getJhrejhgx());

        voCsdjxx.setFqxm(lk.getFqxm());
        voCsdjxx.setFqgmsfhm(lk.getFqgmsfhm());
        voCsdjxx.setMqxm(lk.getMqxm());
        voCsdjxx.setMqgmsfhm(lk.getMqgmsfhm());

        return voCsdjxx;
    }

}
