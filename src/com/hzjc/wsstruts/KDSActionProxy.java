package com.hzjc.wsstruts;

import java.io.*;
import com.hzjc.util.StringUtils;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hzjc.wsstruts.exception.WSErrCode;
import com.hzjc.wsstruts.exception.BeanCreateException;
import com.hzjc.wsstruts.type.TPacket;
import com.hzjc.wsstruts.exception.ServiceException;
import com.hzjc.wsstruts.exception.ActionException;
import com.hzjc.wsstruts.exception.ControlException;
import com.hzjc.wsstruts.exception.DAOException;
import org.dom4j.io.SAXReader;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hzjc.hz2004.base.SpringContextHolder;
import com.hzjc.hz2004.po.PoHJSP_HJSPSQB;
import com.hzjc.hz2004.po.PoHZ_ZJ_SB;
import com.hzjc.hz2004.po.PoPOST_KDQCFKB;
import com.hzjc.hz2004.po.PoXT_XTKZCSB;
import com.hzjc.hz2004.util.CommonUtil;

public class KDSActionProxy {
  public static String toJSON(Object obj){
    ByteArrayOutputStream  bout = null;
    ObjectOutputStream oo = null;
    try{
      bout = new ByteArrayOutputStream();
      oo = new ObjectOutputStream(bout);
      oo.writeObject(obj);
      byte[] buff = bout.toByteArray();
      String objString = StringUtils.encodeHex(buff);
      return objString;
    }catch(Exception ex){
      ex.printStackTrace();
    }finally{
      try{
        if(bout!=null) bout.close();
        if (oo != null) oo.close();
      }catch(Exception ex){
        ;
      }
    }

    //net.sf.json.JSONObject j= net.sf.json.JSONObject.fromObject(obj);
    //return j.toString();
    return null;
  }

  public static Object toObject(String jsonMessage){
    if(jsonMessage==null)
      return null;

    //net.sf.json.JSONObject j= net.sf.json.JSONObject.fromObject(jsonMessage);
    //System.out.println(j);
    ObjectInputStream ois = null;
    ByteArrayInputStream in = null;

    try{
      byte[] buff = StringUtils.decodeHex(jsonMessage);
      ois = new ObjectInputStream(new ByteArrayInputStream(buff));
      Object obj = ois.readObject();
      return obj;
    }catch(Exception e){
      e.printStackTrace();
    }finally{
      try{
        if(ois!=null) ois.close();
      }catch(Exception ex){
        ;
      }
    }

    return null;
  }

  //人员信息查询GyRyxxHqAction.Execute
  public String GyRyxxHqActionExecute(String params_string){
    TPacket respPacket = null;
    try {
      //从包中得到动作请求的功能点代码。
      String strActionCode = "F1001";
      //从配置文件中根据ActionCode功能点，工厂创建得到动作实例
      //WSAction action = (WSAction) BeanFactoryWrapper.getInstance().getBean(strActionCode);
      //action.setKes(true);

      //执行动作返回响应包ResponsePacket
     // System.out.println(action);
      //Object obj = action.execute(reqPacket);
      //System.out.println(obj);

      return "";
    }
    //捕捉BeanFactoryWrapper.getInstance().getBean()异常
    catch (BeanCreateException ex) {
      throw new ControlException(WSErrCode.ERR_CTRL_ACTION_NOTEXIST, ex);
    }
    //以下捕捉，Action.execute()的异常
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (ActionException ex) {
      throw ex;
    }

    /*
    Map params = (Map)KDSActionProxy.toObject(params_string);
    if(params==null)
      params = new HashMap();

    VoQueryResult voQueryResult = null;
    VoPage voPage = new VoPage();

    //处理人员信息获取业务
    GyService gyService = Hz2004ServiceLocator.getInstance().getGyService();
    //Hz2004ServiceLocator.getInstance().getAuthTokenService().getWebAuthToken()

    //gyService.setUserInfo(this.getUserInfo());
    String where = (String)params.get("strHQL");
    voPage = (VoPage)params.get("voPage");
    if(voPage==null)
      voPage= new VoPage();

    try{
      AuthToken auth = Hz2004ServiceHelper.getKDSUser("HZADMIN");
      gyService.setUserInfo(auth);
    }catch(Exception e){
      e.printStackTrace();
    }
    voQueryResult = gyService.queryRyxx(where, voPage, PublicConstant.GNBH_HJ_RYXXHQ);

    return toJSON(voQueryResult);
    */
  }

  public KDSActionProxy() {
  }

  static public String POST(String url_str, String params, String char_set){
    java.net.HttpURLConnection httpUrlConnection = null;
    DataOutputStream dos = null;
    try{
    	/*
  	  //创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url_str);
  	  Map<String,String> map = CommonUtil.getParameterMapUrlDecode(params);
  	  List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if(map!=null){
            for (Entry<String, String> entry : map.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        //设置参数到请求对象中
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, char_set));
        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        
        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpPost);
        //获取结果实体
        String body = null;
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity, char_set);
        }
        EntityUtils.consume(entity);
        //释放链接
        response.close();
        System.out.println(body);
        */
    	
      java.net.URL url = new java.net.URL(url_str);

      java.net.URLConnection rulConnection = url.openConnection();// 此处的urlConnection对象实际上是根据URL的
      // 请求协议(此处是http)生成的URLConnection类
      // 的子类HttpURLConnection,故此处最好将其转化
      // 为HttpURLConnection类型的对象,以便用到
      // HttpURLConnection更多的API.如下:
      httpUrlConnection = (java.net.HttpURLConnection) rulConnection;

      // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
      // http正文内，因此需要设为true, 默认情况下是false;
      httpUrlConnection.setDoOutput(true);

      // 设置是否从httpUrlConnection读入，默认情况下是true;
      httpUrlConnection.setDoInput(true);

      // Post 请求不能使用缓存
      httpUrlConnection.setUseCaches(false);

      // 设定请求的方法为"POST"，默认是GET
      httpUrlConnection.setRequestMethod("POST");
      httpUrlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=" + char_set);
      httpUrlConnection.setRequestProperty("Connection", "Keep-Alive");
      // 维持长连接
      httpUrlConnection.setRequestProperty("Charset", char_set);

      String param = params;//new String(params.getBytes(),char_set);//URLEncoder.encode(params, char_set);

      // 连接，从上述第2条中url.openConnection()至此的配置必须要在connect之前完成，
      try{
        httpUrlConnection.connect();
      }catch(Exception ex){
        ex.printStackTrace();
        throw new java.lang.RuntimeException(url_str + "访问失败！",ex);
      }

      dos = new DataOutputStream(httpUrlConnection.getOutputStream());
      //dos.writeBytes(param);
      if(param!=null)
    	  dos.write(param.getBytes(char_set));
      dos.flush();
      dos.close();
      //获得响应状态
      int resultCode=httpUrlConnection.getResponseCode();
      if(HttpURLConnection.HTTP_OK==resultCode){
        StringBuffer sb=new StringBuffer();
        String readLine=new String();
        BufferedReader responseReader=new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream(), char_set));
        while((readLine=responseReader.readLine())!=null){
          sb.append(readLine).append("\n");
        }
        responseReader.close();

        return sb.toString();
      }
    }catch(Exception e){
      e.printStackTrace();
      throw new java.lang.RuntimeException(e.getMessage());
    }finally{
      try{
        if(httpUrlConnection!=null) httpUrlConnection.disconnect();
      }catch(Exception ex){
        ex.printStackTrace();
      }
    }
    return null;
  }

  static public String readAppConfig(){
      try {
    	  	String hql = "from PoXT_XTKZCSB where kzlb='10028'";
  			List<?> list = SpringContextHolder.getCommonService().queryAll(hql);
  			if(list.size()>0){
  				PoXT_XTKZCSB cs = (PoXT_XTKZCSB)list.get(0);
  				if(CommonUtil.isNotEmpty(cs.getCdkzz()))
  					return cs.getCdkzz();
  			}

        //加载XML配置文件,经测试DOM4J不支持GBK编码，气愤；不行去下补丁
        InputStream is = WSErrCode.class.getClassLoader().getResourceAsStream("conf/app.config.xml");
        SAXReader reader = new SAXReader();
        Document doc = reader.read(is);
        //处理XML-Document文档
        if (doc != null) {
          Element root = doc.getRootElement();
          if (root != null) {
            System.out.println(root.getText());
            return root.getText();
            //循环处理所有的错误配置
            /*
            for (Iterator iter = root.elementIterator(); iter.hasNext(); ) {
              Element eErr = (Element) iter.next();
            }
           */
          }
        }
      }
      catch (Exception ex) {
        throw new ControlException(WSErrCode.ERR_CTRL_LOAD_CFGEXCEPTION, ex);
      }

      return "";
  }

  static public final String APP_CONFIG = readAppConfig();
  static public final JSONObject APP_CONFIG_JSON = JSONObject.parseObject(APP_CONFIG);

  /**
   * 获取唯一许可证
   * @param year String
   * @param lx String
   * @return String
   */
  static public String getXlh(String year,String lx){
    String url = APP_CONFIG_JSON.getString("all_qxglurl");
    url += "/yw/kdqqy.do?method=requestXLH";
    String params = "year=" + year + "&zjlx=" + lx;

    String str = KDSActionProxy.POST(url, params , "UTF-8");
    JSONObject json = JSONObject.parseObject(str);
    String xlh = json.getString("other");

    if(xlh==null || xlh.equals("null"))
      return null;

    return xlh;
  }

  /**
   * 获取用户地区编码
   * @param sfzh String
   * @return String
   */
  static public String getCzrkjbxxAndDqbm(String sfzh){
    String url = APP_CONFIG_JSON.getString("all_qxglurl");
    url += "/yw/kdqqy.do?method=requestDqbmAndCzrkjbxx";
    String params = "gmsfhm=" + sfzh;

    String str = KDSActionProxy.POST(url, params , "UTF-8");

    return str;
  }

  /**
   * 获取用户地区编码
   * @param sfzh String
   * @return String
   */
  static public String getDqbm(String sfzh){
    String url = APP_CONFIG_JSON.getString("all_qxglurl");
    url += "/yw/kdqqy.do?method=requestDqbm";
    String params = "gmsfhm=" + sfzh;

    String str = KDSActionProxy.POST(url, params , "UTF-8");
    if(str==null)
    	return null;
    
    JSONObject json = JSONObject.parseObject(str);
    String xlh = json.getString("other");
    if(xlh==null || xlh.equals("null"))
      return null;

    return xlh;
  }

  /**
   * 保存跨地区审批业务到异地
   * @param dqbm String
   * @param sfzh String
   */
  static public final Map kdsMap = new HashMap();

  /**
   * 获取跨地市配置
   * @param dqbm String
   * @return String
   */
  static public final JSONObject getRemoteConfig(String dqbm){
    if(kdsMap.size()==0){
      JSONArray arry = KDSActionProxy.APP_CONFIG_JSON.getJSONArray("kdsqyList");
      for (int i = 0; i < arry.size(); i++) {
        JSONObject obj = arry.getJSONObject(i);
        String dqcode = obj.getString("dqbm");
        if (!dqcode.equals(""))
          kdsMap.put(dqcode, obj);
      }
    }
    
    JSONObject conf = (JSONObject) kdsMap.get(dqbm);
    if (conf == null) {
      throw new RuntimeException("没有找到地区[" + dqbm + "]的配置，无法完成异地同步！");
    }

    return conf;
  }

  static public void postKdqSpyw(PoHJSP_HJSPSQB po, String sfzh,String xm, String zqzbh, String qrdz,String qyldyy,String qczxlb){
    String dqbm = po.getKdqqy_qcdqbm();
    JSONObject conf = getRemoteConfig(dqbm);
    if(conf==null){
      throw new RuntimeException("没有找到地区[" + dqbm + "]的配置，无法完成异地同步！");
    }

    String remoteUrl = conf.getString("hz2004");
    if(remoteUrl==null || remoteUrl.equals(""))
      throw new RuntimeException("没有找到地区[" + dqbm + "]的hz2004配置，无法完成异地同步！");

    if(!remoteUrl.endsWith("/"))
      remoteUrl += "/";

    remoteUrl += "util/other/service";

    String remoteDqbm = KDSActionProxy.APP_CONFIG_JSON.getString("kdsqyLocalDQBM");

    if(qczxlb==null) qczxlb = "";
    if(qyldyy==null) qyldyy = "";
    if(qrdz==null) qrdz = "";

    String params = "autoLogin=1&serviceName=SpService&methodName=syncKdqSpyw&dqbm=" + remoteDqbm + "&qcsfzh=" + sfzh + "&qcxm=" + xm;
    params += "&sqrxm=" + po.getSqrxm() + "&sqrsfz=" + po.getSqrgmsfhm();
    params += "&czdw=" + po.getKdqqy_czydw() + "&czyh=" + po.getKdqqy_czyxm();
    params += "&ywid=" + po.getSpywid() + "&ywlx=kdqqysp";
    params += "&zqzbh=" + zqzbh + "&qrdqh=" + po.getQrdqx() + "&qrdz=" + qrdz + "&qyldyy=" + qyldyy + "&qczxlb=" + qczxlb;

    String returnString = KDSActionProxy.POST(remoteUrl, params, "UTF-8");

    if(returnString!=null){
      JSONObject json = JSONObject.parseObject(returnString);
      if(json.getBooleanValue("success")){
        return;
      }else{
        throw new RuntimeException("保存异步迁出失败，错误内容：" + json.getString("message"));
      }
    }else{
      throw new RuntimeException("保存异步迁出失败，错误内容：" + returnString);
    }
  }

  /**
   * 一站式反馈提交
   * @param po PoPOST_KDQCFKB
   */
  static public void postKDQCFKB(PoPOST_KDQCFKB po){
    //迁入地
    String dqbm = po.getQrdq();

    JSONObject conf = getRemoteConfig(dqbm);
    if(conf==null){
      throw new RuntimeException("没有找到地区[" + dqbm + "]的配置，无法完成异地同步！");
    }

    String remoteUrl = conf.getString("hz2004");
    if(remoteUrl==null || remoteUrl.equals(""))
      throw new RuntimeException("没有找到地区[" + dqbm + "]的hz2004配置，无法完成异地同步！");

    if(!remoteUrl.endsWith("/"))
      remoteUrl += "/";

    remoteUrl += "util/other/service";

    //String remoteDqbm = KDSActionProxy.APP_CONFIG_JSON.optString("kdsqyLocalDQBM","");

    String params = "autoLogin=1&serviceName=SpService&methodName=syncKdqFkYZS&dqbm=" + dqbm;
    params += "&ywid=" + po.getSpywid() + "&qyzbh=" + (po.getQyzbh()==null?"":po.getQyzbh());
    params += "&lscxfldm=" + (po.getCxfldm()==null?"":po.getCxfldm());

    String returnString = KDSActionProxy.POST(remoteUrl, params, "UTF-8");

    if(returnString!=null){
      JSONObject json = JSONObject.parseObject(returnString);
      if(json.getBooleanValue("success")){
        return;
      }else{
        throw new RuntimeException("保存异步迁出反馈失败，错误内容：" + json.getString("message"));
      }
    }else{
      throw new RuntimeException("保存异步迁出反馈失败，错误内容：" + returnString);
    }
  }

  /**
   * 迁入核验身份证检查
   * @param sfz 逗号分割的迁入人员身份证号码
   */
  static public String qrywGmsfhmCheck(String qr_dqbm, String sfz){
    //迁入地:
    JSONObject conf = getRemoteConfig(qr_dqbm);
    if(conf==null){
      throw new RuntimeException("没有找到地区[" + qr_dqbm + "]的配置，无法完成异地迁出核查！");
    }

    String remoteUrl = conf.getString("hz2004");
    if(remoteUrl==null || remoteUrl.equals(""))
      throw new RuntimeException("没有找到地区[" + qr_dqbm + "]的hz2004配置，无法完成异地迁出核查！");

    if(!remoteUrl.endsWith("/"))
      remoteUrl += "/";

    remoteUrl += "util/other/service";

    //String remoteDqbm = KDSActionProxy.APP_CONFIG_JSON.optString("kdsqyLocalDQBM","");

    String params = "autoLogin=1&serviceName=HjService&methodName=gmsfhmCheck&sfz=" + sfz;
    String returnString = null;
    try{
    	returnString = KDSActionProxy.POST(remoteUrl, params, "UTF-8");
    }catch(Exception e){
    	throw new java.lang.RuntimeException("检查省内身份证失败：" + e.getMessage());
    }

    if(returnString!=null){
	     JSONObject json = JSONObject.parseObject(returnString);
	     if(json.getBooleanValue("success")){
	       return "";
	     }else{
	       return (String)json.getString("message");
	     }
	   }else{
	     return "迁入失败，无法核验迁出地人员状态，错误内容：" + returnString;
	   }
  }

  static public void postKdqSpywFK(PoHZ_ZJ_SB po){
      //迁入地
      String dqbm = po.getKdqqy_qrdqbm();

      JSONObject conf = getRemoteConfig(dqbm);
      if(conf==null){
        throw new RuntimeException("没有找到地区[" + dqbm + "]的配置，无法完成异地同步！");
      }

      String remoteUrl = conf.getString("hz2004");
      if(remoteUrl==null || remoteUrl.equals(""))
        throw new RuntimeException("没有找到地区[" + dqbm + "]的hz2004配置，无法完成异地同步！");

      if(!remoteUrl.endsWith("/"))
        remoteUrl += "/";

      remoteUrl += "util/other/service";

      //String remoteDqbm = KDSActionProxy.APP_CONFIG_JSON.optString("kdsqyLocalDQBM","");

      String params = "autoLogin=1&serviceName=SpService&methodName=syncKdqFk&dqbm=" + dqbm;
      params += "&ywid=" + po.getKdqqy_qrywid() + "&ywlx=" + po.getKdqqy_qrywlx() + "&qyzbh=" + (po.getKdqqy_qyzbh()==null?"":po.getKdqqy_qyzbh())
          + "&lscxfldm=" + (po.getKdqqy_lscxfldm()==null?"":po.getKdqqy_lscxfldm()) + "&clbs=" + po.getClbs();

      String returnString = KDSActionProxy.POST(remoteUrl, params, "UTF-8");

      if(returnString!=null){
        JSONObject json = JSONObject.parseObject(returnString);
        if(json.getBooleanValue("success")){
          return;
        }else{
          throw new RuntimeException("保存异步迁出反馈失败，错误内容：" + json.getString("message"));
        }
      }else{
        throw new RuntimeException("保存异步迁出反馈失败，错误内容：" + returnString);
      }
  }

  static public String getKdqSpywRyxx(String dqbm, String sfzh){
    JSONObject conf = getRemoteConfig(dqbm);
    if(conf==null){
      throw new RuntimeException("没有找到地区[" + dqbm + "]的配置，无法完成异地同步！");
    }

    String remoteUrl = conf.getString("hz2004");
    if(remoteUrl==null || remoteUrl.equals(""))
      throw new RuntimeException("没有找到地区[" + dqbm + "]的hz2004配置，无法完成异地同步！");

    if(!remoteUrl.endsWith("/"))
      remoteUrl += "/";

    remoteUrl += "util/other/service";

    String params = "autoLogin=1&serviceName=SpService&methodName=getKdqSpywQrryByHzZjsb&sfz=" + sfzh;
    String returnString = KDSActionProxy.POST(remoteUrl, params, "UTF-8");

    return returnString;
  }

  static public void main(String[] argc){
	  String str = KDSActionProxy.POST("http://127.0.0.1:8080/hz2004/util/other/service", "autoLogin=1&serviceName=SpService&methodName=syncKdqFkYZS&dqbm=3407&ywid=null&qyzbh=&lscxfldm=", "UTF-8");
	  System.out.println(str);
	  
    System.out.println(KDSActionProxy.getXlh("2016","zqz"));
    System.out.println(KDSActionProxy.getXlh("2016","qyz"));

    System.out.println(KDSActionProxy.getXlh("2017","zqz"));
    System.out.println(KDSActionProxy.getXlh("2016","qyz"));
  }
}
