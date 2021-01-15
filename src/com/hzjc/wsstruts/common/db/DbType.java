package com.hzjc.wsstruts.common.db;

/**
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口管理系统Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 浙江金铖华元新技术有限公司</p>
 * @author kgb_hz@126.com,kgb@primetech.com.cn
 * @version 1.0
 */

public class DbType {
  private DbType() {
  }

  public static final String DB_TYPE_ORACLE = "oracle";
  public static final String DB_TYPE_SQLSERVER = "sqlserver";
  public static final String DB_TYPE_DB2 = "db2";
  public static final String DB_TYPE_INFORMIX = "informix";
  public static final String DB_TYPE_MYSQL = "mysql";
  public static final String DB_TYPE_ACCESS = "access";

}