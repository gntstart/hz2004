
package com.hzjc.hz2004.util;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.hzjc.hz2004.base.Constants;
import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.encode.Hex;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.exception.ActionException;
import com.hzjc.hz2004.exception.CommonException;
import com.hzjc.hz2004.po.PoHJSP_BGSPXXB;
import com.hzjc.hz2004.po.PoXT_YHXXB;
import com.hzjc.hz2004.vo.VoBggzxx;
import com.hzjc.hz2004.vo.VoBggzxxEx;
import com.hzjc.hz2004.vo.VoXtsjfw;
import com.hzjc.wsstruts.exception.ServiceException;
import com.hzjc.wsstruts.exception.WSErrCode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CommonUtil {
    public static void main(String[] arg) {
        String bggzxx = "[{\"rynbid\":\"3407000001001536151\","
        		+ "\"bggzxxList\":["
        		+ "{\"rynbid\":\"3407000001001536151\",\"xm\":\"张莹\",\"bggzxm\":\"zp\",\"bggzxm_mc\":\"zp\",\"bggzqnr\":\"''\",\"bggzhnr\":\"abc\",\"bggzlb\":\"91\",\"flag\":\"1\"}"
        		+ ",{\"rynbid\":\"3407000001001536151\",\"xm\":\"张/莹\",\"bggzxm\":\"zy\",\"bggzxm_mc\":\"职业\",\"bggzqnr\":\"''\",\"bggzhnr\":\"拣字工\",\"bggzlb\":\"91\",\"flag\":\"1\"},"
        		+ "{\"rynbid\":\"3407000001001536151\",\"xm\":\"张莹\",\"bggzxm\":\"zylb\",\"bggzxm_mc\":\"职业类别\",\"bggzqnr\":\"''\",\"bggzhnr\":\"014\",\"bggzlb\":\"91\",\"flag\":\"1\"},"
        		+ "{\"rynbid\":\"3407000001001536151\",\"xm\":\"张莹\",\"bggzxm\":\"fwcs\",\"bggzxm_mc\":\"服务处所\",\"bggzqnr\":\"''\",\"bggzhnr\":\"432432\",\"bggzlb\":\"91\",\"flag\":\"1\"}]}]";
        CommonUtil.getVoBggzxxEx(bggzxx);
    }
    
    /**
     * 获取数据范围错误提示
     * @param yh
     * @param sjfwList
     * @return
     */
    static public String getSjfwError(List<?> sjfwList){
    	PoXT_YHXXB yh = BaseContext.getBaseUser().getUser();
    	String yhdlm = null;
    	if(yh!=null)
    		yhdlm = yh.getYhdlm();
    	
    	String sjfw = null;
    	for(Object obj: sjfwList){
    		VoXtsjfw vo = (VoXtsjfw)obj;
    		if(sjfw==null)
    			sjfw = vo.getSjfw();
    		else
    			sjfw += "," + vo.getSjfw();
    	}
    	
    	return  "用户【" + yhdlm + "】的数据范围【" + sjfw + "】被限制";
    }
    
	static public List<VoBggzxxEx> getVoBggzxxEx(String bggzxx){
		 List<VoBggzxxEx> voBggzxxEx = null;
		if(CommonUtil.isNotEmpty(bggzxx) && !bggzxx.equalsIgnoreCase("null")){
			TypeToken<List<VoBggzxxEx>> typeToken = new TypeToken<List<VoBggzxxEx>>(){};
			
			voBggzxxEx = JSONUtil.getJsonData(typeToken, bggzxx);
			if(voBggzxxEx != null && voBggzxxEx.size() > 0) {
				//TypeToken<List<VoBggzxx>> typeToken1 = new TypeToken<List<VoBggzxx>>(){};
				for(int i=0;i<voBggzxxEx.size();i++){
					List<VoBggzxx> voBggzxx = new ArrayList<VoBggzxx>();
					List<?> list2 = voBggzxxEx.get(i).getBggzxxList();
					if(list2.size()>0){
						for(Object obj:list2){
							if(obj instanceof String){
								VoBggzxx v = JSONUtil.getJsonData((String)obj, null, VoBggzxx.class);
								voBggzxx.add(v);
							}else if(obj instanceof Map){
								@SuppressWarnings("unchecked")
								VoBggzxx v = ObjectUtil.copyInfo(VoBggzxx.class, (Map)obj);
								if(v.getBggzxm().equals("zp")){
									v.setZp(v.getBggzhnr());
									v.setBggzhnr(null);
								}
								voBggzxx.add(v);
							}else{
								VoBggzxx v = JSONUtil.getJsonData((String)obj.toString(), null, VoBggzxx.class);
								if(v.getBggzxm().equals("zp")){
									v.setZp(v.getBggzhnr());
									v.setBggzhnr(null);
								}
								
								voBggzxx.add(v);
							}
						}
					}
					//voBggzxx = JSONUtil.getJsonData(typeToken1, voBggzxxEx.get(i).getBggzxxList().toString());
					voBggzxxEx.get(i).setBggzxxList(voBggzxx);
				}
			}
		}
		
		if(voBggzxxEx==null)
			voBggzxxEx = new ArrayList<VoBggzxxEx>();
		
		return voBggzxxEx;
	}
	
	public static Object getParamToObject(String key,String value){
		if(value==null)
			return null;
		
		if(CommonUtil.isEmpty(value.trim()))
			return null;
		
		if(key.startsWith("to_daysdate_") || key.startsWith("to_startdate_") || key.startsWith("to_enddate_") || key.startsWith("to_date_")){
			Date date = null;
			SimpleDateFormat df = new SimpleDateFormat();
			df.applyPattern("yyyy-MM-dd HH:mm:ss");
			try{
				if(key.startsWith("to_startdate_")){
					date = df.parse(value + " 00:00:00");
				}else if(key.startsWith("to_enddate_")){
					date = df.parse(value + " 23:59:59");
				}else if(key.startsWith("to_date_")){
					date = DateConverter.convertDate(value);
				}else{
					Calendar c = Calendar.getInstance();
					c.add(Calendar.DAY_OF_YEAR, 0-Integer.parseInt(value));
					c.set(Calendar.HOUR_OF_DAY, 0);
					c.set(Calendar.MINUTE, 0);
					c.set(Calendar.SECOND, 0);
					
					date = c.getTime();
				}
			}catch(Exception ex){
				throw new ActionException("转换日期条件[" + key + "=" + value + "]时发生错误，错误信息：" + ex.getMessage());
			}
			
			return date;
		}else if(key.startsWith("to_long_")){
			return new Long(value);
		}else if(key.startsWith("to_double_")){
			return new Double(value);
		}else if(key.startsWith("to_int_")){
			return new Integer(value);
		}
		
		return value;
	}
	
	static public ExtMap<String,Object> getRequestParamesObject(HttpServletRequest request){
		ExtMap<String,Object> map = new ExtMap<String,Object>();
		
		for(java.util.Enumeration<?> e = request.getParameterNames(); e.hasMoreElements();){
			String key = (String)e.nextElement();
			String value = request.getParameter(key);
			
			if(CommonUtil.isEmpty(value))
				continue;
			
			Object obj = CommonUtil.getParamToObject(key,value);
			if(obj==null)
				continue;
			
			map.put(key, obj);
		}
		
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		int size,index;
		if(CommonUtil.isNotEmpty(start)&& CommonUtil.isNotEmpty(limit) && !limit.equals("0")){
			size = Integer.parseInt(limit);
			index = Integer.parseInt(start)/size + 1;
		}else{
			size = 20;
			index = 1;
		}
		//不让它覆盖已经有参数 2014-8-8
		if(!map.containsKey(ExtUtils.pageSize))
			map.put(ExtUtils.pageSize,size+"");
		if(!map.containsKey(ExtUtils.pageIndex))
			map.put(ExtUtils.pageIndex,index+"");

		/**
		 * 默认获取参数的时候，自动将用户上下文加入到参数中
		 */
		map.put("authToken", BaseContext.getUser());
		
		return map;
	}
	
	public static java.sql.Timestamp getTimestamp(Date date){
		return new java.sql.Timestamp(date==null?System.currentTimeMillis():date.getTime());
	}
	
    public static Map<String, String> getParameterMapUrlDecode(String param) {
        Map<String, String> parmMap = new HashMap<String, String>();
        if (CommonUtil.isEmpty(param))
            return parmMap;

        String[] temp = param.split("&");
        // 保存字典参数<String,String>
        for (int i = 0; i < temp.length; i++) {
            String[] parm = temp[i].split("=");
            if (parm.length == 2) {
                String parmName = parm[0];
                String parmValue = parm[1];

                try {
                    parmValue = java.net.URLDecoder.decode(parmValue, "UTF-8");
                } catch (Exception e) {
                    throw new CommonException("参数编码错误！");
                }

                parmMap.put(parmName, parmValue);
            } else if (parm.length == 1) {
                parmMap.put(parm[0], "");
            }
        }

        return parmMap;
    }

    /**
     * 获取request参数
     * @param request
     * @return
     */
    public static final Map<String,String> getParameterMap(HttpServletRequest request){
        Map<String,String> params = new HashMap<String,String>();
        Enumeration<String> it = request.getParameterNames();
        for(;it.hasMoreElements();){
        	String name = it.nextElement();
        	params.put(name, request.getParameter(name));
        }
        return params;
    }
    /**
     * 判断字符串s是否不为空。
     *
     * @param s 需要判断空值的字符串。
     * @return false    字符串s为null或者长度为0<BR>
     * true		字符串s不为null，并且长度大于0<BR>
     */
    public static final boolean isNotEmpty(String s) {
        return s != null && s.length() > 0;
    }

    /**
     * 判断集合collection不为空。
     *
     * @param collection 需要判断不为空的集合collection。
     * @return true        集合不为null,并且长度大于0.<br>
     * false	集合为nll，或者长度等于0。
     */
    public static final boolean isNotEmpty(Collection<?> collection) {
        return collection != null && collection.size() > 0;
    }

    /**
     * 判断某字符串s是否为空，和isNotEmpty(String s)相反。
     *
     * @param s
     * @return
     */
    public static final boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

    /**
     * 判断集合是否为空，和isNotEmpty(Collection collection)相反。
     *
     * @param collection
     * @return
     */
    public static final boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }


    /**
     * 判断字符串是否为空，包括空格
     * @param str
     * @return
     */
    public static final boolean isBlank(String str){
        return StringUtils.isBlank(str);
    }


    /**
     * 判断字符串是否不为空，空格为空
     * @param str
     * @return
     */
    public static final boolean isNotBlank(String str){
        return StringUtils.isNotBlank(str);
    }


    /**
     * 将对象转换为字节数组
     *
     * @param value
     * @return
     * @throws Exception
     */
    static public String getObjectToBytes(Object value) throws Exception {
        if (value == null)
            return null;
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(value);
        byte[] buff = bout.toByteArray();
        bout.close();
        out.close();
        return new String(Hex.encode(buff));
    }

    /**
     * 将字节数组转换为对象
     *
     * @param value
     * @return
     * @throws Exception
     */
    static public Object getBytesToObject(String value) throws Exception {
        if (value == null)
            return null;
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(
                Hex.decode(value.getBytes(Constants.CHARSET_UTF8))));
        Object obj = in.readObject();
        in.close();
        return obj;
    }


    /**
     * 生成不带横杠的UUID
     *
     * @return
     */
    public static String generateId() {
        return generatUUid().replaceAll("-", "");
    }

    /**
     * 生成UUID
     *
     * @return
     */
    public static String generatUUid() {
        return UUID.randomUUID().toString().toLowerCase();
    }

    /**
     * 地区编码计算上级地区编码
     *
     * @param area_code
     * @return
     */
    public static String getParentAreaCode(String area_code) {
        if (isNotEmpty(area_code)
                && area_code.length() == 6) {
            if (area_code.endsWith("0000"))
                area_code = "";
            else if (area_code.endsWith("00"))
                area_code = area_code.substring(0, 2) + "0000";
            else
                area_code = area_code.substring(0, 4) + "00";
            return area_code;
        }
        return "";
    }

    /**
     * 区域code处理一下,用于like%
     *
     * @param area_code
     * @return
     */
    public static String getAreaCodeLike(String area_code) {
        if (CommonUtil.isNotEmpty(area_code)) {
            if (area_code.endsWith("0000"))
                area_code = area_code.substring(0, 2);
            else if (area_code.endsWith("00"))
                area_code = area_code.substring(0, 4);
        }
        return area_code;
    }

    /**
     * 检查参数对象是否为空，为空抛出参数错误异常
     *
     * @param value
     */
    public static void checkObjectIsNull(Object value) {
        if (value == null) {
            throw new IllegalArgumentException("必需参数不能为空");
        }
    }

    /**
     * 检查url地址
     * @param url
     * @return
     */
    public static boolean isUrl(String url){
     return CommonUtil.isNotEmpty(url)&&(url.startsWith("http://")||url.startsWith("https://"));
    }

    /**
     * 检查是否为手机号
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone){
        if(CommonUtil.isBlank(phone)){
            return false;
        }
        Pattern p = Pattern.compile("^(1[3|4|5|7|8])\\d{9}$");
        Matcher m = p.matcher(phone);
        return  m.matches();
    }

    /**
     * 检查是否为邮箱
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        Pattern p = Pattern.compile("^[_a-z\\d\\-\\./]+@[_a-z\\d\\-]+(\\.[_a-z\\d\\-]+)*(\\.(info|biz|com|edu|gov|net|am|bz|cn|cx|hk|jp|tw|vc|vn))$");
        Matcher m = p.matcher(email);
        return  m.matches();
    }
  
    /**
     * 检查密码格式是否正确,必须包含数字+字母,且不能使用特殊字符6-20位
     * @param pwd
     * @return
     */
    public static boolean isPwd(String pwd){
        Pattern p = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$");
        Matcher m = p.matcher(pwd);
        return  m.matches();
    }

    /**
     * 检查字符串参数是否为空，为空抛出参数错误异常
     *
     * @param value
     */
    public static void checkStringIsNull(String value) {
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException("必需参数不能为空");
        }
    }

    /**
     * 对象转数组
     *
     * @param obj
     * @return
     */
    public static byte[] object2ByteArray(Object obj) throws Exception {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (Exception ex) {
            throw ex;
        }
        return bytes;
    }

    /**
     * 数组转对象
     *
     * @param bytes
     * @return
     */
    public static Object byteArray2Object(byte[] bytes) throws Exception {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (Exception ex) {
            throw ex;
        }
        return obj;
    }

    /**
     * 获取一个非127.0.0.1的IP地址
     *
     * @return
     */
    public static String getLocalIp() {
        String re = null;
        List<String> iplist = getLocalIPList();
        for (String ip : iplist) {
            if (re == null) re = ip;

            if (!ip.equals("127.0.0.1"))
                re = ip;
            if (ip.startsWith("192")) {
                re = ip;
                break;
            }
        }

        return re;
    }

    /**
     * 获取本地IP地址列表
     *
     * @return
     */
    public static List<String> getLocalIPList() {
        List<String> ipList = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            NetworkInterface networkInterface;
            Enumeration<InetAddress> inetAddresses;
            InetAddress inetAddress;
            String ip;
            while (networkInterfaces.hasMoreElements()) {
                networkInterface = networkInterfaces.nextElement();
                inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    inetAddress = inetAddresses.nextElement();
                    if (inetAddress != null && inetAddress instanceof Inet4Address) { // IPV4
                        ip = inetAddress.getHostAddress();
                        ipList.add(ip);
                    }
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        return ipList;
    }

    /**
     * 获取当前进程的PID
     * @return
     */
    public static String getPid() {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        String pid = name.split("@")[0];
        return pid;
    }
    
    public static String getTokey(){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String validTokey = "shjc!@" + sdf.format(new Date());
		String TokeyMD5 = CommonUtil.StringToMd5(validTokey);
		
		return TokeyMD5;
    }
    
	public static String StringToMd5(String psw) {
        {
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                md5.update(psw.getBytes("UTF-8"));
                byte[] encryption = md5.digest();
 
                StringBuffer strBuf = new StringBuffer();
                for (int i = 0; i < encryption.length; i++) {
                    if (Integer.toHexString(0xff & encryption[i]).length() == 1) {
                        strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                    } else {
                        strBuf.append(Integer.toHexString(0xff & encryption[i]));
                    }
                }
 
                return strBuf.toString();
            } catch (NoSuchAlgorithmException e) {
                return "";
            } catch (UnsupportedEncodingException e) {
                return "";
            }
        }
    }
	/**
     * 半角转全角
     * @param input String.
     * @return 全角字符串.
     */
    public static String ToSBC(String input) {
             char c[] = input.toCharArray();
             for (int i = 0; i < c.length; i++) {
               if (c[i] == ' ') {
                 c[i] = '\u3000';
               } else if (c[i] < '\177') {
                 c[i] = (char) (c[i] + 65248);

               }
             }
             return new String(c);
    }

    /**
     * 全角转半角
     * @param input String.
     * @return 半角字符串
     */
    public static String ToDBC(String input) {
        

             char c[] = input.toCharArray();
             for (int i = 0; i < c.length; i++) {
               if (c[i] == '\u3000') {
                 c[i] = ' ';
               } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
                 c[i] = (char) (c[i] - 65248);

               }
             }
        String returnString = new String(c);
        
             return returnString;
    }
}
  