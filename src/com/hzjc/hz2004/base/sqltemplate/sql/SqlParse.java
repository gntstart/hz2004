package com.hzjc.hz2004.base.sqltemplate.sql;

import java.io.Serializable;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.mvel2.MVEL;

import com.hzjc.hz2004.base.sqltemplate.Config;
import com.hzjc.hz2004.base.sqltemplate.TemplateUtil;
import com.hzjc.hz2004.exception.CommonException;


public class SqlParse implements Serializable {
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(SqlParse.class);

    private boolean hideSQL = true;
    private String osql;
    private Map<String, Object> oparams = new HashMap<String, Object>();

    public SqlParse(String config_path, String confg_key) {
    	 this.osql = Config.getSql(config_path, confg_key);
    }
    
    public SqlParse(String sql) {
        this.osql = sql;
    }

    public SqlParse(String sql, Map<String, Object> params) {
        this.osql = sql;
        this.bind(params);
    }

    public boolean isHideSQL() {
        return hideSQL;
    }

    public void setHideSQL(boolean hideSQL) {
        this.hideSQL = hideSQL;
    }

    public SqlParse bind(String key, Object param) {
        this.oparams.put(key, param);
        return this;
    }

    public SqlParse bind(Map<String, Object> paramMap) {
        if (paramMap == null)
            paramMap = new HashMap<String, Object>();

        this.oparams.putAll(paramMap);
        return this;
    }

    public SqlParam parse() {
        try {
            //第一次解析
            VelocityContext velocityContext = new VelocityContext(oparams);
            velocityContext.put("ut", new TemplateUtil());
            StringWriter sw = new StringWriter();
            if (StringUtils.isEmpty(osql))
                throw new CommonException("SQL配置不存在！");

            if(!isHideSQL())
                logger.info(osql);

            Velocity.evaluate(velocityContext, sw, "LOG", osql);

            //如果第一次解析之前含有include方法，那么会包含其它配置的原生代码，那么重新再解析一遍
            String resolve = sw.toString();
            if (osql.indexOf("$ut.include(") >= 0) {
                sw = new StringWriter();
                Velocity.evaluate(velocityContext, sw, "LOG", resolve);
                resolve = sw.toString();
            }

            SqlParam sq = parseParam(resolve);


            if (!isHideSQL() && logger.isDebugEnabled()) {
                logger.debug(Arrays.toString(sq.getParams().toArray()));
            }
            /**
             if(SystemConfig.getSystemConfig("DEBUG", "0").equals("1")){
             System.out.println(sq.getSql());
             int i = 0;
             for(Object o:sq.getParams()){
             if(o instanceof Date){
             System.out.println(i + ":" + DateHelper.formateDate((Date)o, DateHelper.PRINT_DATETIME_STYLE2));
             }else{
             System.out.println(i + ":" + o);
             }
             i++;
             }
             }

             **/
            return sq;
        } catch (Exception e) {
            System.out.println("osql:"+osql+"  oparams:"+ ToStringBuilder.reflectionToString(oparams));
            e.printStackTrace();
            logger.error("解析sql[{}]发生错误", e);
            throw new RuntimeException("解析sql发生错误", e);
        }
    }

    private SqlParam parseParam(String str) {
        List<Object> po = new ArrayList<Object>();
        //Pattern pattern = Pattern.compile(":([a-z_0-9A-Z\\.\\[\\]%]+)");
        Pattern pattern = Pattern.compile(":(ut\\.[a-zA-Z]+\\([^)]+\\)|[a-z_0-9A-Z\\.\\[\\]%]+)");
        //:(ut\\.[a-zA-Z]+\\([^)]+\\)|[a-z_0-9A-Z\\.\\[\\]%]+)
        //logger.debug(str);
        Matcher m = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String v = m.group(1);
            //过滤日期特殊格式中的:符号
            if ("0123456789".indexOf(v.charAt(1)) < 0) {
                po.add(getParmenterValue(v));
                m.appendReplacement(sb, "?");
            }
        }
        m.appendTail(sb);
        String sql = sb.toString().replaceAll("@", ":");
        return new SqlParam(sql, po);
    }

    private Object getParmenterValue(String parmenterName) {
        Object value = null;
        if (parmenterName.startsWith("ut.")) {
            //如果以ut.为前缀，那么使用模板
            int start = parmenterName.indexOf("(");
            int end = parmenterName.lastIndexOf(")");
            if (start < 0 || end < 0)
                return null;

            Class<?> threadClazz = TemplateUtil.class;
            String methodname = parmenterName.substring(3, start);

            String str[] = parmenterName.substring(start + 1, end).split(",");
            List<Object> list = new ArrayList<Object>();

            for (String key : str) {
                key = key.trim();
                if (key.startsWith(":")) {
                    //参数
                    key = key.substring(1);
                    if (!oparams.containsKey(key)) {
                        list.add("");
                    } else {
                        list.add(oparams.get(key));
                    }
                } else {
                    //常量
                    if (key.startsWith("\"") && key.endsWith("\"")) {
                        key = key.substring(1, key.length() - 1);
                    }
                    list.add(key);
                }
            }

            Class<?>[] argsClass = new Class[list.size()];
            for (int i = 0; i < list.size(); i++) {
                argsClass[i] = list.get(i).getClass();
            }
            try {
                Method method = threadClazz.getMethod(methodname, argsClass);
                value = method.invoke(null, list.toArray());
            } catch (Exception ex) {
                if (ex instanceof NoSuchMethodException) {
                    String msg = threadClazz.getSimpleName() + "." + methodname + "(";
                    int i = 0;
                    for (Class<?> c : argsClass) {
                        msg += (i++ == 0 ? "" : ", ") + c.getSimpleName();
                    }
                    msg += ")";
                    throw new RuntimeException("方法" + msg + "没有找到！");
                }
            }

            if (value == null) {
                throw new RuntimeException(parmenterName + "解析不能返回空值！");
            }

            return value;
        }

        //其它
        if (parmenterName.matches("^(?:(?:%\\w+)|(?:\\w+%)|(?:%\\w+%))$")) {
            Matcher parmenterKeyMatcher = Pattern.compile("^%*(\\w+)%*$").matcher(parmenterName);
            if (parmenterKeyMatcher.matches()) {
                String parmenterKey = parmenterKeyMatcher.group(1);
                //System.out.println("getParmenterValue() : " + parmenterKey);
                value = parmenterName.replace(parmenterKey, MVEL.eval(parmenterKey, oparams).toString());
            }
        } else {
            value = MVEL.eval(parmenterName, oparams);
        }
        if(!isHideSQL())
            logger.debug(parmenterName + "\t:\t" + value);

        return value;
    }

    static public void main(String[] argc) {
        String str = "select to_char(mydate,'yyyy-mm-dd HH24:mi:ss') as mydate from t1 where t1.sex_code=:ut.trim(:cs, :cs2, \"yyyy-mm-dd\") and t1.name=:%name% and t1.sex_code2= :ut.tonumber(:cs) and x='1'";

        Pattern pattern = Pattern.compile(":(ut\\.[a-zA-Z]+\\([^)]+\\)|[a-z_0-9A-Z\\.\\[\\]%]+)");
        logger.debug(str);
        Matcher m = pattern.matcher(str);
        while (m.find()) {
            String v = "";
            if (m.groupCount() > 1) {
                v = m.group(1);
            } else {
                v = m.group(0);
            }
            System.out.println(v);
        }
    }
}

