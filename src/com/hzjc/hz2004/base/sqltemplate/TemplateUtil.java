package com.hzjc.hz2004.base.sqltemplate;


import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hzjc.hz2004.base.login.AuthToken;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.base.login.HSession;
import com.hzjc.hz2004.constant.PublicConstant;
import com.hzjc.hz2004.exception.CommonException;
import com.hzjc.hz2004.po.PoXT_YHSJFWB;
import com.hzjc.hz2004.service.impl.XtywqxServiceImpl;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.DateHelper;

import java.io.DataInputStream;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 用于VIEW层的数据处理，现主要用于数据模板处理 这里的名字尽量短小
 *
 */
public class TemplateUtil {
    static Logger logger = LoggerFactory.getLogger(TemplateUtil.class);

    public static String replaceObject(String str, Map<String,Object> objs) {
        try {
            Velocity.init();
            VelocityContext velocityContext = new VelocityContext();
            velocityContext.put("ut", new TemplateUtil());
            Iterator<?> it = objs.keySet().iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                velocityContext.put(key, objs.get(key));
            }
            StringWriter sw = new StringWriter();
            Velocity.evaluate(velocityContext, sw, "LOG", str);
            return sw.toString();
        } catch (Exception e) {
            if(logger.isErrorEnabled()){
                logger.error("生成模板内容出错！",e);
            }
            throw new CommonException("生成内容出错!", e);
        }
    }

    /**
     * 模板对象值替换
     *
     * @param  str
     *  模板字符串
     * @param objs
     * 模板里需要的对象列表
     * @return
     */
    public static String replace(String str, Map<String,String> objs) {
        try {
            Velocity.init();
            VelocityContext velocityContext = new VelocityContext();
            velocityContext.put("ut", new TemplateUtil());
            Iterator<String> it = objs.keySet().iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                velocityContext.put(key, objs.get(key));
            }
            StringWriter sw = new StringWriter();
            Velocity.evaluate(velocityContext, sw, null, str);
            return sw.toString();
        } catch (Exception e) {
            if(logger.isErrorEnabled()){
                logger.error("生成模板内容出错！",e);
            }
            throw new CommonException("生成内容出错!", e);
        }
    }

    /**
     * 自动为指定的字符串变量增加rt方法
     *
     * @return
     */
    public static String art(String str) {
        if (str == null || str.indexOf('{') < 0)
            return str;
        Pattern p = Pattern.compile("\\$\\!?\\{([a-zA-Z_0-9\\.]+)\\}");
        Matcher m = p.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            if ("tfr".equalsIgnoreCase(m.group(1)))
                m.appendReplacement(sb, "\\$" + m.group(1));
            else
                m.appendReplacement(sb, "\\$!{ut.rt(\\$" + m.group(1) + ")}");
        }
        m.appendTail(sb);
        return sb.toString();
    }

    /**
     * 替换指定的字符串中的tab符为空格
     *
     * @param str
     * @return
     */
    public Object rt(Object str) {
        if (str == null)
            return null;
        if (str instanceof String)
            return str.toString().replaceAll("\\t", "    ").replaceAll(
                    "\\r\\n", "\n").replaceAll("\\r", "\n").replaceAll("\\n",
                    "@r@");
        return str;
    }

    /**
     * 返回格式化日期
     *
     * @param date
     * @return
     */
    public String sd(Date date) {
        if (date == null)
            return null;
        return DateHelper.formateDate(date, DateHelper.PRINT_DATE_STYLE);
    }

    /**
     * 返回格式化日期时间
     *
     * @param date
     * @return
     */
    public String sdt(Date date) {
        if (date == null)
            return null;
        return DateHelper.formateDate(date, DateHelper.PRINT_DATETIME_STYLE);
    }

    /**
     * 返回小数点后两位的代码
     *
     * @param dnum
     * @return
     */
    public String dltstr(Double dnum) {
        if (dnum == null)
            return null;
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        return decimalFormat.format(dnum);
    }

    /**
     * 根据身份证号获取出生日期
     *
     * @param sfzh
     * @return
     */
    public String csrqBySfzh(String sfzh) {
        if (sfzh == null)
            return "";
        sfzh = sfzh.trim();
        if (sfzh.length() == 18) {
            return sfzh.substring(6, 10) + "年" + riYue(sfzh, 10, 12) + "月"
                    + riYue(sfzh, 12, 14) + "日";
        } else if (sfzh.length() == 15) {
            return "19" + sfzh.substring(6, 8) + "年" + riYue(sfzh, 8, 10) + "月"
                    + riYue(sfzh, 10, 12) + "日";
        }
        return "";
    }

    private int riYue(String str, int b, int e) {
        return Integer.parseInt(str.substring(b, e));
    }

    /**
     * 根据出生日期获取当年年龄
     *
     * @param date
     *            出生日期
     * @return
     */
    public String nl(Date date) {
        if (date == null)
            return "";
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int intAge = DateHelper.getAge(calendar.get(Calendar.YEAR));

        // Added by Albert on 200801-21
        Date today = new Date(System.currentTimeMillis());
        GregorianCalendar calToday = new GregorianCalendar();
        calToday.setTime(today);
        if (!isFebruaryLastDay(calendar) || !isFebruaryLastDay(calToday)) {// 都是二月底则年龄不减1
            calToday.set(Calendar.YEAR, calendar.get(Calendar.YEAR));// 将当前日期的年号设为出生日期的年号，便于比较。
            if (calendar.after(calToday)) {// 在忽略年号的情况下，如果出生日期在当前日期之前，则当前年龄减 1
                // 岁
                intAge--;
            }
        }

        // End of addition
        return String.valueOf(intAge);
    }

    // Added by Albert on 200801-21
    private boolean isFebruaryLastDay(GregorianCalendar calendar) {
        boolean blnFlag = false;
        if (1 == calendar.get(Calendar.MONTH)) {
            calendar.roll(Calendar.DATE, 1);
            if (2 == calendar.get(Calendar.MONTH)) {
                blnFlag = true;
            }
            calendar.roll(Calendar.DATE, -1);
        }
        return blnFlag;
    }

    // End of addition

    // Added by Albert on 2008-03-14

    /**
     * 获取指定日期的中国日期格式(精确到时)：如：2004年5月4日21时
     */
    public String cnHour(Date date) {
        return DateHelper.formateDate(date, DateHelper.CN_HOUR_STYLE);
    }

    /**
     * 获取指定日期的中国日期格式(精确到分)：如：2004年5月4日21时04分
     */
    public String cnMinute(Date date) {
        return DateHelper.formateDate(date, DateHelper.CN_MINUTE_STYLE);
    }

    // End of addition

    public String cnSecond(Date date) {
        return DateHelper.formateDate(date, DateHelper.CN_SECOND_STYLE);
    }

    /**
     * style=yyyy年M月d日/yyyy年M月d日HH时mm分 获取指定日期的中国日期格式：如：2004年5月4号
     */
    public String cnrqByStyle(Date date, String style) {
        return DateHelper.formateDate(date, style);
    }

    /**
     * 获取指定日期的中国日期格式：如：2004年5月4号
     *
     * @param date
     * @return
     */
    public String cnrq(Date date) {
        String rs = DateHelper.formateDate(date, DateHelper.CN_DATE_STYLE);
        if (date == null)
            return "";
        return rs;
    }

    public String cnrq() {
        return DateHelper.formateDate(new Date(), DateHelper.CN_DATE_STYLE);
    }

    public static Integer toInteger(String value) {
        if(CommonUtil.isEmpty(value))
            return 0;

        return Integer.parseInt(value);
    }

    public static Long toLong(String value) {
        if(CommonUtil.isEmpty(value))
            return 0l;

        return Long.parseLong(value);
    }

    /**
     * 将小写RMB转化为大写格式（精确到分）
     */
    public static String UpInt(Object ob) {
        char[] hunit = { '拾', '佰', '仟' }; // 段内位置表示
        char[] vunit = { '万', '亿' }; // 段名表示
        char[] digit = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' }; // 数字表示
        String value = "0";
        if (ob != null) {
            value = ob.toString(); // 传入参数由object转化为string类型
        }

        double value1 = Double.parseDouble(value); // String转为double
        long midVal = (long) (value1 * 100); // double转为long
        String valStr = String.valueOf(midVal); // 转化成字符串
        String head = "0"; // 取整数部分
        String rail = "0"; // 取小数部分
        if (!valStr.equals("0")) {
            head = valStr.substring(0, valStr.length() - 2);
            rail = valStr.substring(valStr.length() - 2);
        }

        String prefix = ""; // 整数部分转化的结果
        String suffix = ""; // 小数部分转化的结果
        // 处理小数点后面的数
        if (rail.equals("00")) {
            // 如果小数部分为0
            suffix = "整";
        } else if (rail.equals("0")) {
            suffix = "";
        } else {
            if ((rail.charAt(1) - '0') == 0) {
                suffix = digit[rail.charAt(0) - '0'] + "角";
            } else {
                suffix = digit[rail.charAt(0) - '0'] + "角"
                        + digit[rail.charAt(1) - '0'] + "分"; // 否则把角分转化出来
            } // 否则把角分转化出来
        }
        // 处理小数点前面的数
        char[] chDig = head.toCharArray(); // 把整数部分转化成字符数组
        char zero = '0'; // 标志'0'表示出现过0
        byte zeroSerNum = 0; // 连续出现0的次数
        for (int i = 0; i < chDig.length; i++) {
            // 循环处理每个数字
            int idx = (chDig.length - i - 1) % 4;
            // 取段内位置
            int vidx = (chDig.length - i - 1) / 4;
            // 取段位置
            if (chDig[i] == '0') {
                // 如果当前字符是0
                zeroSerNum++;
                // 连续0次数递增
                if (zero == '0') {
                    // 标志
                    zero = digit[0];
                } else if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
                    prefix += vunit[vidx - 1];
                    zero = '0';
                }
                continue;
            }
            zeroSerNum = 0; // 连续0次数清零
            if (zero != '0') {
                // 如果标志不为0,则加上,例如万,亿什么的
                prefix += zero;
                zero = '0';
            }
            prefix += digit[chDig[i] - '0'];
            // 转化该数字表示
            if (idx > 0)
                prefix += hunit[idx - 1];
            if (idx == 0 && vidx > 0) {
                prefix += vunit[vidx - 1];
                // 段结束位置应该加上段名如万,亿
            }
        }
        if (prefix.length() > 0)
            prefix += '元';
        // 如果整数部分存在,则有圆的字样
        return prefix + suffix; // 返回正确表示
    }

    /**
     * 将long型的整数转化为中文大写的数字
     *
     * @param srjyqx
     * @return
     */
    public String numToUpCase(Long srjyqx) {
        if (srjyqx == null) {
            return "";
        }
        String strresult = ""; // 最终的返回字符串
        char[] uUnit = { '拾', '佰', '仟' };
        char[] hUnit = { '万', '亿' };
        char[] digitUnit = { '〇', '一', '二', '三', '四', '五', '六', '七', '八', '九' };
        String strsryj = srjyqx.toString();
        char[] chDig = strsryj.toCharArray(); // 将整数整理为字符串数组
        char zero = '0'; // 标志'0'表示出现过0
        byte zeronum = 0; // 连续零出现的次数
        for (int i = 0; i < strsryj.length(); i++) {
            // 处理每个整数数字
            int idx = (chDig.length - i - 1) % 4; // 取段内位置
            int videx = (chDig.length - i - 1) / 4; // 取段位置
            if (chDig[i] == '0') {
                zeronum++;
                if (zero == '0') {
                    zero = digitUnit[0];
                } else if (idx == 0 && videx > 0 && zeronum < 4) { // 取超过个位0的数
                    strresult += hUnit[videx - 1];
                    zero = '0';
                }
                continue;
            }
            zeronum = 0; // 连续0清零
            if (zero != '0') {
                strresult += zero;
                zero = '0';
            }
            strresult += digitUnit[chDig[i] - '0'];
            if (idx > 0)
                strresult += uUnit[idx - 1];
            if (idx == 0 && videx > 0) {
                strresult += hUnit[videx - 1];
                // 段结束位置应该加上段名如万,亿
            }
        }
        return strresult;
    }

    /**
     * 将long型的整数转化为中文大写的数字
     *
     * @param srjyqx
     * @return
     */
    public String numToUpCase2(Long srjyqx) {
        if (srjyqx == null) {
            return "";
        }
        String strresult = ""; // 最终的返回字符串
        char[] uUnit = { '拾', '佰', '仟' };
        char[] hUnit = { '万', '亿' };
        char[] digitUnit = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' };
        String strsryj = srjyqx.toString();
        char[] chDig = strsryj.toCharArray(); // 将整数整理为字符串数组
        char zero = '0'; // 标志'0'表示出现过0
        byte zeronum = 0; // 连续零出现的次数
        for (int i = 0; i < strsryj.length(); i++) {
            // 处理每个整数数字
            int idx = (chDig.length - i - 1) % 4; // 取段内位置
            int videx = (chDig.length - i - 1) / 4; // 取段位置
            if (chDig[i] == '0') {
                zeronum++;
                if (zero == '0') {
                    zero = digitUnit[0];
                } else if (idx == 0 && videx > 0 && zeronum < 4) { // 取超过个位0的数
                    strresult += hUnit[videx - 1];
                    zero = '0';
                }
                continue;
            }
            zeronum = 0; // 连续0清零
            if (zero != '0') {
                strresult += zero;
                zero = '0';
            }
            strresult += digitUnit[chDig[i] - '0'];
            if (idx > 0)
                strresult += uUnit[idx - 1];
            if (idx == 0 && videx > 0) {
                strresult += hUnit[videx - 1];
                // 段结束位置应该加上段名如万,亿
            }
        }
        return strresult;
    }

    /**
     * 将String型的整型数字转化为中文大写的数字
     *
     * @param srjyqx
     * @return
     */
    public String numToUpCase(String srjyqx) {
        if (srjyqx == null || srjyqx.trim().length() == 0) {
            return "";
        }
        String strresult = ""; // 最终的返回字符串
        char[] uUnit = { '拾', '佰', '仟' };
        char[] hUnit = { '万', '亿' };
        char[] digitUnit = { '〇', '一', '二', '三', '四', '五', '六', '七', '八', '九' };
        String strsryj = srjyqx.toString();
        char[] chDig = strsryj.toCharArray(); // 将整数整理为字符串数组
        char zero = '0'; // 标志'0'表示出现过0
        byte zeronum = 0; // 连续零出现的次数
        for (int i = 0; i < strsryj.length(); i++) {
            // 处理每个整数数字
            int idx = (chDig.length - i - 1) % 4; // 取段内位置
            int videx = (chDig.length - i - 1) / 4; // 取段位置
            if (chDig[i] == '0') {
                zeronum++;
                if (zero == '0') {
                    zero = digitUnit[0];
                } else if (idx == 0 && videx > 0 && zeronum < 4) { // 取超过个位0的数
                    strresult += hUnit[videx - 1];
                    zero = '0';
                }
                continue;
            }
            zeronum = 0; // 连续0清零
            if (zero != '0') {
                strresult += zero;
                zero = '0';
            }
            strresult += digitUnit[chDig[i] - '0'];
            if (idx > 0)
                strresult += uUnit[idx - 1];
            if (idx == 0 && videx > 0) {
                strresult += hUnit[videx - 1];
                // 段结束位置应该加上段名如万,亿
            }
        }
        return strresult;
    }

    /**
     * 将long型的整数转化为中文大写的数字
     *
     * @param srjyqx
     * @return
     */
    public String numToUpCase(Integer srjyqx) {
        if (srjyqx == null) {
            return "";
        }
        String strresult = ""; // 最终的返回字符串
        char[] uUnit = { '拾', '佰', '仟' };
        char[] hUnit = { '万', '亿' };
        char[] digitUnit = { '〇', '一', '二', '三', '四', '五', '六', '七', '八', '九' };
        String strsryj = srjyqx.toString();
        char[] chDig = strsryj.toCharArray(); // 将整数整理为字符串数组
        char zero = '0'; // 标志'0'表示出现过0
        byte zeronum = 0; // 连续零出现的次数
        for (int i = 0; i < strsryj.length(); i++) {
            // 处理每个整数数字
            int idx = (chDig.length - i - 1) % 4; // 取段内位置
            int videx = (chDig.length - i - 1) / 4; // 取段位置
            if (chDig[i] == '0') {
                zeronum++;
                if (zero == '0') {
                    zero = digitUnit[0];
                } else if (idx == 0 && videx > 0 && zeronum < 4) { // 取超过个位0的数
                    strresult += hUnit[videx - 1];
                    zero = '0';
                }
                continue;
            }
            zeronum = 0; // 连续0清零
            if (zero != '0') {
                strresult += zero;
                zero = '0';
            }
            strresult += digitUnit[chDig[i] - '0'];
            if (idx > 0)
                strresult += uUnit[idx - 1];
            if (idx == 0 && videx > 0) {
                strresult += hUnit[videx - 1];
                // 段结束位置应该加上段名如万,亿
            }
        }
        return strresult;
    }

    /**
     * 功能描述：删除空格
     *
     * @param str
     * @return
     * @author zgd
     */
    public static String trim(String str) {
        if (str == null)
            str = "";
        String returnValue = str.trim();
        while (returnValue.startsWith("　")) {
            returnValue = returnValue.substring(1);
        }
        return returnValue;
    }

    /**
     * 功能描述：为空被替换
     *
     * @param str
     * @return
     * @author zgd
     */
    public static String nullToTag(String str, String tag) {
        if (str == null || "".equals(str))
            str = tag;
        return str;
    }

    /**
     * 对于科学计算法的浮点性的转换
     *
     * @param num
     * @return
     * @zap
     */
    public static String dts(Double num) {
        String str = null;
        if (num == null)
            return str = "";
        DecimalFormat df = new DecimalFormat("#.00");
        // double dd = Double.parseDouble(num);
        str = df.format(num);
        return str;
    }

    public static boolean isns(Object str){
        if(str==null)
            return true;

        return CommonUtil.isEmpty(str.toString());
    }
    public static Date getEDate(Date d){
        return DateHelper.getEndDate(d);
    }
    public static Date getSDate(Date d){
        return DateHelper.getStartDate(d);
    }
    public static Date getSDate(String str){
        String tmp = str + " 00:00:00";
        return  DateHelper.toDate(tmp, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date getEDate(String str){
        String tmp = str + " 23:59:59";
        return DateHelper.toDate(tmp, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date getSDate(String str,String dateFormat){
        Date d =  DateHelper.toDate(str,dateFormat);
        return DateHelper.getStartDate(d);
    }

    public static Date getEDate(String str,String dateFormat){
        Date d =  DateHelper.toDate(str, dateFormat);
        return DateHelper.getEndDate(d);
    }

    public static String toU(String str){
        if(str==null)
            return null;

        return str.toUpperCase();
    }

    /**
     * sql 关键词in参数拼接
     * @param list
     * @return
     */
    public static String in(List<String> list){
        if(CommonUtil.isEmpty(list)){
            return "''";
        }
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<list.size();i++){
            if(i>0)sb.append(",");
            sb.append("'").append(list.get(i)).append("'");
        }
        return sb.toString();
    }
    /**
     * sql 关键词in参数拼接
     * @param str
     * @return
     */
    public static String in(String str){
        if(CommonUtil.isEmpty(str)){
            return "''";
        }
        return "'"+(str.replace(",","','"))+"'";
    }

    public static String toL(String str){
        if(str==null)
            return null;

        return str.toLowerCase();
    }

    public static Date testMethod(String name,String format,String str){
        return new Date();
    }

    public static Date getDate(String str){
        String tmp = str + " 23:59:59";
        return DateHelper.toDate(tmp, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date getDate(String str,String dateFormat){
        Date d =  DateHelper.toDate(str,dateFormat);
        return d;
    }

    /**
     * 生成全字段查询查询sql
     * @param className
     * @param alias
     * @return
     */
    public static String pojo(String className,String alias ){
        try {
            StringBuffer sb=new StringBuffer();
            Class<?> clz=Class.forName(className);
            Field[] field=clz.getDeclaredFields();
            int num=0;
            for(int i=0;i<field.length;i++){
                String name=field[i].getName().toUpperCase();
                if(name.indexOf("SERIALVERSIONUID")>-1||name.startsWith("BASEPOJO")){
                    continue;
                }
                if(num>0){
                    sb.append(",");
                }
                if(CommonUtil.isNotEmpty(alias)){
                    sb.append(alias+"."+name+" AS "+alias+"_"+name);
                }else{
                    sb.append(name);
                }
                num++;
            }
            return sb.toString();
        } catch (Exception e) {
            if(logger.isErrorEnabled()){
                logger.error("生成模板内容出错！",e);
            }
            throw new CommonException("生成全字段查询sql出错");
        }
    }
    
	public static String linkSjfw(String pname){
		if(CommonUtil.isEmpty(pname))
			pname = "c.dwdm";
		
   		//and ((1 = 0) or (c.DWDM = '340702003') or (substr(c.DWDM, 0, 6) = '340720'))
		
		if(BaseContext.getBaseUser().isAdmin()) return "";
		
		List<PoXT_YHSJFWB> list = BaseContext.getUser().getSjfw();
		Iterator<PoXT_YHSJFWB> it = list.iterator();
		while(it.hasNext()){
		    PoXT_YHSJFWB x = it.next();
		    if(x.getXqlx().equals(PublicConstant.XQLX_XQW)){
		        it.remove();
		    }
		}
		if(CommonUtil.isEmpty(list))
			throw new RuntimeException("此用户没有数据范围，无法查询！");
		
		String where = " and ( 0=1 ";
		
		for(PoXT_YHSJFWB o:list){
			String sjfwstr = o.getSjfw();
//			if(!o.getXqlx().equals(PublicConstant.XQLX_XQN)) {
//				continue;
//			}
			if(CommonUtil.isNotEmpty(sjfwstr)){
				Set<String> set = new HashSet<String>();
				
				String sjfw[] = sjfwstr.split("\\|");
				for(int i = 0;i<sjfw.length;i++){
					String bm = sjfw[i];
					bm = bm.trim();
					
					if(CommonUtil.isEmpty(bm) || set.contains(bm))
						continue;
					if(sjfw.length==3&&i==2&&bm.length()==12) {
						where += " or a.jcwh= '" + bm + "'";
					}
					if(sjfw.length==2&&i==1&&bm.length()==9) {
						where += " or a.pcs= '" + bm + "'";
					}
					if(sjfw.length==1&&i==0&&bm.length()==6) {
						where += " or a.ssxq = '" + bm + "'";
					}
					set.add(bm);
				}
				
//				for(String bm:sjfw){
//					bm = bm.trim();
//					
//					if(CommonUtil.isEmpty(bm) || set.contains(bm))
//						continue;
//					
//					if(bm.length()==6)
//						where += " or substr(" + pname + ", 0, 6) = '" + bm.substring(0, 6) + "'";
//					else if(bm.length()==9)
//						where += " or " + pname + "= '" + bm + "'";
//					
//					set.add(bm);
//				}
			}
		}
		
		where += ")";
		//340702|340702003|||340720||||
		return where;
	}
	public static String linkSjfwNew(String pname){
		if(CommonUtil.isEmpty(pname))
			pname = "c.dwdm";
		
   		//and ((1 = 0) or (c.DWDM = '340702003') or (substr(c.DWDM, 0, 6) = '340720'))
		
		if(BaseContext.getBaseUser().isAdmin()) return "";
		
		List<PoXT_YHSJFWB> list = BaseContext.getUser().getSjfw();
		if(CommonUtil.isEmpty(list))
			return " and 0=1 ";
		
		String where = " and ( 0=1 ";
		
		for(PoXT_YHSJFWB o:list){
			String sjfwstr = o.getSjfw();
			if(o.getXqlx().equals(PublicConstant.XQLX_XQW)) {
				continue;
			}
			if(CommonUtil.isNotEmpty(sjfwstr)){
				Set<String> set = new HashSet<String>();
				String sjfw[] = sjfwstr.split("\\|");
				for(String bm:sjfw){
					bm = bm.trim();
					
					if(CommonUtil.isEmpty(bm) || set.contains(bm))
						continue;
					
					if(bm.length()==6&&sjfw.length==1)
						where += " or substr(" + pname + ", 0, 6) = '" + bm.substring(0, 6) + "'";
					else if(bm.length()==9)
						where += " or substr(" + pname + ", 0, 9) = '" + bm.substring(0, 9) + "'";
					
					set.add(bm);
				}
			}
		}
		
		where += ")";
		//340702|340702003|||340720||||
		return where;
	}	
	//立户 数据范围   先取当前用户的数据范围，然后再从中过滤剩辖区内的
	//有详细派出所就取派出所  只到区县的，就取区县的
	public static String linklhSjfw(String pname){
		if(CommonUtil.isEmpty(pname))
			pname = "c.dwdm";
		if(BaseContext.getBaseUser().isAdmin()) return "";
		
		List<PoXT_YHSJFWB> list = BaseContext.getUser().getSjfw();
		List<PoXT_YHSJFWB> listTemp = new ArrayList<PoXT_YHSJFWB>();
		//add by zjm 20190122 立户取数据范围属于辖区内的数据
		for(Object o:list) {
			PoXT_YHSJFWB temp  = (PoXT_YHSJFWB)o;
			if(temp.getXqlx().equals("1")) {
				listTemp.add(temp);
			}
		}
		if(CommonUtil.isEmpty(listTemp))
			return " and 0=1 ";
		
		String where = " and ( 0=1 ";
		
		for(PoXT_YHSJFWB o:listTemp){
			String sjfwstr = o.getSjfw();
			if(CommonUtil.isNotEmpty(sjfwstr)){
				Set<String> set = new HashSet<String>();
				String sjfw[] = sjfwstr.split("\\|");
				String bm="";
				if(sjfw.length>1) {
					bm = sjfw[1].trim();
					if(bm.length()==9) {
						where += " or " + pname + "= '" + bm + "'";
					}
				}else if(sjfw.length==1){
					bm = sjfw[0].trim();
					if(bm.length()==6) {
						where += " or substr(" + pname + ", 0, 6) = '" + bm.substring(0, 6) + "'";
					}
				}
//				for(String bm:sjfw){
//					bm = bm.trim();
//					
//					if(CommonUtil.isEmpty(bm) || set.contains(bm))
//						continue;
//					
//					if(bm.length()==6)
//						where += " or substr(" + pname + ", 0, 6) = '" + bm.substring(0, 6) + "'";
//					else if(bm.length()==9)
//						where += " or " + pname + "= '" + bm + "'";
//					
//					set.add(bm);
//				}
			}
		}
		
		where += ")";
		//340702|340702003|||340720||||
		return where;
	}
	/**
	 * 特殊，只能用于人口查询的户政业务查询
	 * @param sfzs
	 * @param pageIndex
	 * @return
	 */
	public static String allsfz(String sfzs, String pageIndex, String hlx){
		if(CommonUtil.isEmpty(sfzs) || CommonUtil.isEmpty(hlx) || !hlx.endsWith("2"))
			return "";
		
		String sql = "";
		if(CommonUtil.isNotEmpty(sfzs)){
			String sfz[] = sfzs.split(",");
			for(String s:sfz){
				if(CommonUtil.isEmpty(s))
					continue;
				
				if(sql.equals("")){
					sql = " and ( a.gmsfhm='" + s + "'";
				}else{
					sql += " or a.gmsfhm='" + s + "'";
				}
			}
			if(!sql.equals("")){
				sql += " or a.yhzgx='01' or a.yhzgx='02' or a.yhzgx='03')";
			}
		}
		return sql;
	}
	
	public static String n_allsfz(String sfzs, String pageIndex, String hlx){
		if(CommonUtil.isEmpty(sfzs) || CommonUtil.isEmpty(hlx) || !hlx.endsWith("2"))
			return "  ";
		
		String sql = "";
		if(CommonUtil.isNotEmpty(sfzs)){
			String sfz[] = sfzs.split(",");
			for(String s:sfz){
				if(CommonUtil.isEmpty(s))
					continue;
				
				if(sql.equals("")){
					sql = " and ( a.gmsfhm!='" + s + "'";
				}else{
					sql += " and a.gmsfhm!='" + s + "'";
				}
			}
			if(!sql.equals("")){
				sql += " and a.yhzgx!='01' and a.yhzgx!='02' and a.yhzgx!='03')";
			}
		}
		return sql;
	}
	
	public static String new_allsfz(String sfzs, String pageIndex, String hlx){
		String sql = "and (a.yhzgx='01' or a.yhzgx='02' or a.yhzgx='03' ";
		if(CommonUtil.isNotEmpty(sfzs)){
			String sfz[] = sfzs.split(",");
			for(String s:sfz){
				if(CommonUtil.isEmpty(s))
					continue;

				sql += " or a.gmsfhm='" + s + "'";
			}
		}
		
		sql += ")";
		
		return sql;
	}
	
	public static String new_n_allsfz(String sfzs, String pageIndex, String hlx){
		String sql = "and (a.yhzgx!='01' and a.yhzgx!='02' and a.yhzgx!='03' ";
		if(CommonUtil.isNotEmpty(sfzs)){
			String sfz[] = sfzs.split(",");
			for(String s:sfz){
				if(CommonUtil.isEmpty(s))
					continue;

					sql += " and a.gmsfhm!='" + s + "'";
			}
		}
		sql += ")";
		
		return sql;
	}
	
    static public void main(String argc[]) throws Exception{
		DataInputStream in = new DataInputStream(TemplateUtil.class.getResourceAsStream("/config/template/test.txt"));
		byte[] buff = new byte[in.available()];
		in.readFully(buff);
		String txt = new String(buff);

		//列表数据
		Map<String,Object> data = new HashMap<String,Object>();

		Map<String,String> childMap = new HashMap<String,String>();
		List<TestBean> list = new ArrayList<TestBean>();
		for(int i=0;i<=10;i++){
            TestBean user = new TestBean();
			user.setName("某医生xxx" + i);
			user.setPhonenumber("138xxxxxx" + i);
			list.add(user);
			childMap.put(user.getName(), "哈哈"+i);
		}
		data.put("userlist", list);

		//MAP数据
		data.put("aliasmap", childMap);

		//对象数据
        TestBean hz = new TestBean();
		hz.setAddress("某xxxx路某xxxx号");
		hz.setName("某xxx");
		hz.setPhonenumber("1888888xxx");
		hz.setCityname("上海市");
		data.put("hz", hz);

		//直接数据
		data.put("title", "这里是标题");
		String str = TemplateUtil.replaceObject(txt, data);
		System.out.println(str);
    }
}