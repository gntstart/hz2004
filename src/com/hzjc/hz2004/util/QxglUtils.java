package com.hzjc.hz2004.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.hzjc.hz2004.base.SystemConfig;

public class QxglUtils {
	/**
	 * 获取QXXT的数据库连接对象
	 * 
	 * @return
	 * @throws SQLException
	 */
	static public Connection getQxxtConnection() throws SQLException {
		return getQxxtConnection("jdbc");
	}
	
	static public Connection getQxxtConnection(String name) throws SQLException {
		String url = SystemConfig.getJdbcConfig(name + ".url");
		String uid = SystemConfig.getJdbcConfig(name + ".user");
		String pwd = SystemConfig.getJdbcConfig(name + ".password");
		
		Connection conn = DriverManager.getConnection(
				url,
				uid, 
				pwd);
		return conn;
	}
}
