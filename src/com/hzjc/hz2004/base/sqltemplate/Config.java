package com.hzjc.hz2004.base.sqltemplate;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hzjc.hz2004.base.sqltemplate.sql.SqlParam;
import com.hzjc.hz2004.base.sqltemplate.sql.SqlParse;


public class Config {
    private static final Logger logger = LoggerFactory.getLogger(Config.class);
    private static final String DEF_CONFNAME = "/conf/sql-segment.xml";

    static Map<String, XMLConfiguration> sqlconf = new HashMap<String, XMLConfiguration>();

    static {
        XMLConfiguration.setDefaultListDelimiter('~');
    }

    private static Configuration getSqlConfiguration(String config_name) {
        if (StringUtils.isEmpty(config_name))
            config_name = DEF_CONFNAME;

        if (!DEF_CONFNAME.equals(config_name)&&!config_name.startsWith("/conf/segment")) {
            config_name = "/conf/segment/" + config_name;
        }

        if (!config_name.endsWith(".xml")) {
            config_name = config_name + ".xml";
        }

        XMLConfiguration conf = sqlconf.get(config_name);
        if (conf != null)
            return conf;

        try {
            FileChangedReloadingStrategy f = new FileChangedReloadingStrategy();
            //f.setRefreshDelay(1000);
            conf = new XMLConfiguration(Config.class.getResource(config_name));
            conf.setReloadingStrategy(f);

            sqlconf.put(config_name, conf);
        } catch (Exception e) {
            if(logger.isErrorEnabled()){
                logger.error("加载" + config_name + "文件出现错误！", e);
            }
        }

        return conf;
    }

    public static String getSql(String configName, String key) {
        Configuration config = Config.getSqlConfiguration(configName);
        if (config == null)
            return null;

        String sql = config.getString(key);
        return sql;
    }

    public static String getSql(String key) {
        String sql = Config.getSqlConfiguration(null).getString(key);
        return sql;
    }

    public static void main(String[] args) {
        String hql = getSqlConfiguration(null).getString("T_SMS_INSERT");
        SqlParam param = new SqlParse(hql).bind("lgbm", "value : ajjzid").parse();
        System.out.println(param.getSql());
        for (Object obj : param.getParams()) {
            System.out.println(obj.toString());
        }
    }
}
