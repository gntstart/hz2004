package com.hzjc.hz2004.constant;

import java.util.*;

/**
 * 系统公共常量类
 * <p>Title: hz2004</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class PublicConstant {

  //户政2004中件层版本号
  public final static String HZ2004_VERSION = "2.1.00.01";

  /////////////////////////////////////////////
  //默认值
  public final static int PAGE_DEFAULT_OFFSET = 0; //默认的查询页
  public final static int PAGE_DEFAULT_SIZE = 20; //默认的查询页大小

  ///////////////////////////////////////////////
  //记录标志
  public final static String JLBZ_ZX = "1"; //最新
  public final static String JLBZ_LS = "2"; //历史

  //////////////////////////////////////////////////////
  //受理状态
  public final static String SLZT_WSL = "0"; //未受理
  public final static String SLZT_YSL = "1"; //已受理

  /////////////////////////////////////////////////////////
  //户籍业务流水_业务类型
  public final static String HJYWLS_YWLX_GR = "1"; //个人
  public final static String HJYWLS_YWLX_QH = "2"; //全户
  public final static String HJYWLS_YWLX_QHTZ = "3"; //区划调整
  public final static String HJYWLS_YWLX_DZCZ = "4"; //地址操作

  //单位级别
  public final static String DWJB_PCS = "1"; //派出所
  public final static String DWJB_FJ = "2"; //分局
  public final static String DWJB_QX = "3"; //区县
  public final static String DWJB_DS = "4"; //地市
  public final static String DWJB_ST = "5"; //省厅
  //辖区类型
  public final static String XQLX_XQN = "1"; //辖区内
  public final static String XQLX_XQW = "2"; //辖区外
  //启用标志
  public final static String QYBZ_BQY = "0"; //不启用
  public final static String QYBZ_QY = "1"; //启用

  //用户状态9007
  public final static String YHZT_ZC = "0"; //正常
  public final static String YHZT_DJ = "1"; //冻结
  public final static String YHZT_ZX = "2"; //注销

  //区县证件审核方式(系统控制参数5004)
  //kgb add 2004-05-17
  public final static String XTKZCS_XTQXZJSHFS_ZSH = "1"; //只审核
  public final static String XTKZCS_XTQXZJSHFS_SHBQF = "2"; //审核并签发

  //kgb add 2004-06-23
  //日志类型代码
  public final static String RZLX_XTCZ = "1"; //系统操作
  public final static String RZLX_CWMS = "2"; //错误描述日志

  //注销标志 : select 'public final static String CXBZ_' || zwpy || ' = "' || dm || '"; //' ||  mc as 冲销标志 from xt_xtcsb where cslb='9001'
  public final static String CXBZ_FCX = "0"; //非冲销
  public final static String CXBZ_SJCX = "1"; //实际冲销
  public final static String CXBZ_PDCX = "2"; //配对冲销

  //////////////////////////////////////////////////
  //系统控制参数
  public final static String XTKZCS_SHQYQRSPPD = "1000"; //是否启用迁入审批判断(0-不启用/1-启用)
  public final static String XTKZCS_SHQYQRDZDJPD = "1001"; //是否启用迁入地址冻结判断(0-不启用/1-启用)
  public final static String XTKZCS_SHQYGZDXPD = "1002"; //是否启用工作对象判断 0 - 不启用 1 - 启用
  public final static String XTKZCS_SHQYCSDZDJPD = "1003"; //是否启用出生地址冻结判断 0 - 不启用 1 - 启用
  public final static String XTKZCS_SHQYZZBDSPPD = "1004"; //是否启用住址变动审批判断 0 - 不启用 1 - 启用
  public final static String XTKZCS_SHQYZZBDDJPD = "1005"; //是否启用住址变动地址冻结判断 0 - 不启用 1 - 启用
  public final static String XTKZCS_SHQYHBBGSPPD = "1006"; //是否启用户别变更审批判断 0 - 不启用 1 - 启用
  public final static String XTKZCS_SHQYZXBGSP = "1007"; //是否启用主项变更审批 0 - 不启用 1 - 启用
  public final static String XTKZCS_SHQYZDQYPD = "1008"; //是否启用自动迁移判断 0 - 不启用 1 - 启用
  public final static String XTKZCS_BGGZXM = "1009"; //变更更正项目
  public final static String XTKZCS_XTBLZJYWSX = "5000"; //系统办理证件业务属性 1 - 一代证 2 - 二代证
  public final static String XTKZCS_GZDXBZHQFS = "5001"; //工作对象标志获取方式 0 - 内部获得 1 - 外部获得
  public final static String XTKZCS_XTQYFPWSDFS = "5002"; //系统起用分配尾数段方式 1 - 根据尾数段分配 2 - 按行政区划统一分配
  public final static String XTKZCS_EDZBLDCHPDFS = "5003"; //二代证办理的重号判断方式 1 - 有重号人不许办 2 - 有重号人且办二代证不许办
  public final static String XTKZCS_XTQXZJSHFS = "5004"; //系统区县证件审核方式 1 - 只审核 2 - 审核并签发
  public final static String XTKZCS_YDZSFYXCHBZ = "5005"; //一代证是否允许重号办证 0 - 不允许 1 - 允许
  public final static String XTKZCS_HJYWCXSJFW = "5008"; //户籍业务冲销时间范围控制(0 - 无范围限制 1 - 本月业务可冲销 2 - 本季度业务可冲销 3 - 本年度业务可冲销) Add By MHB 2005/01/05 11:11:00
  public final static String XTKZCS_ZJSLHLSHQZZ = "5009"; //证件受理号流水号前缀值
  public final static String XTKZCS_SHSCSMKZPBXX = "5010"; //是否生成市民卡照片表信息(0-不生成，1-生成)
  public final static String XTKZCS_DZPZFS = "5021"; //地址拼装方式
  public final static String XTKZCS_CSYWTBZHSBS = "5022"; //出生业务填本址还是本市(0-本址，1-本市)
  public final static String XTKZCS_XTSYSJC = "9004"; //系统使用省简称
  //ADD BY KGB 2004-11-22
  public final static String XTKZCS_XTZBFHZDJLS = "6000"; //系统组包返回最大记录数

  //add by kgb 2004-06-09
  public final static String XTKZCS_DZPZFS_BCHRZZ = "1001"; //被重号人住址
  public final static String XTKZCS_DZPZFS_EDZSLXX = "1002"; //二代证受理信息表地址（组合）
  public final static String XTKZCS_DZPZFS_YDZSLXX = "1003"; //一代证受理信息表地址（拆分）
  public final static String XTKZCS_DZPZFS_QCDXZ = "1004"; //迁出地详址
  public final static String XTKZCS_DZPZFS_CZRKDJBZZ = "1005"; //常住人口登记表住址
  public final static String XTKZCS_DZPZFS_BDQXZ = "1006"; //变动前详址
  public final static String XTKZCS_DZPZFS_BDHXZ = "1007"; //变动后详址
  //add by kgb 2004-06-16
  public final static String XTKZCS_EDZBLDCHPDFS_NO_ALL = "0"; //重号双方均不许办证
  public final static String XTKZCS_EDZBLDCHPDFS_YES_ALL = "1"; //双方均允许办证
  public final static String XTKZCS_EDZBLDCHPDFS_YES_ONLY_FIRST = "2"; //有重号人，第一个人允许办二代证
  public final static String XTKZCS_YDZSFYXCHBZ_NO_ALL = "0"; //不允许
  public final static String XTKZCS_YDZSFYXCHBZ_YES_ALL = "1"; //允许

  public final static String XTKZCS_BQY = "0"; //不启用
  public final static String XTKZCS_QY = "1"; //启用
  public final static String XTKZCS_GJWSDFP = "1"; //根据尾数段分配
  public final static String XTKZCS_AXZQHTYFP = "2"; //按行政区划统一分配
  //add kgb 2004-05-17
  public final static String XTKZCS_DBID_BEFORE_3 = "9000"; //DBID的前三位
  public final static String XTKZCS_DBID_AFTER_6 = "9001"; //DBID的后六位
  //add by kgb 2004-06-04
  public final static String XTKZCS_XTYYFW = "5006"; //系统应用范围
  public final static String XTKZCS_XTYYFW_SJZ = "0"; //省集中
  public final static String XTKZCS_XTYYFW_DSJZ = "1"; //地市集中
  public final static String XTKZCS_XTYYFW_QXJZ = "2"; //区县集中
  public final static String XTKZCS_XTSYDWJGDM = "9002"; //系统使用单位机构代码
  public final static String XTKZCS_XTSYDWJGMC = "9003"; //系统使用单位机构名称
  //ADD HB 2006-07-31
  public final static String XTKZCS_SDRYSFYXQHTZ = "1019"; //锁定人员是否允许区划调整 0 - 可以  1 - 不可以
  public final static String XTKZCS_QHTZMCTZDZDRS = "1023"; //区划调整每次调整的最大人数
  //add hb 2006-09-05
  public final static String XTKZCS_SFCSJKQSJ = "1026";//取中间件时间或数据库时间 0-中间件时间 1-数据库时间
  //add hb 2006-09-12
  public final static String XTKZCS_BBTJZDRS = "7001";//报表统计最多人数
  public final static String XTKZCS_DQBBTJRS = "7002";//当前报表统计人数
  //add hb 2006-12-14
  public final static String XTKZCS_QYRPTJNDI = "1029"; //是否起用报表统计JNDI
  //add hubin 2012-10-23
  public final static String XTKZCS_HQPOXXJY = "1032"; //迁入业务类别婚迁时，配偶信息完整信校验 0-不校验 1-校验

  /////////////////////////////////////////////////////////////////////
  //系统参数
  ////////////////////////////////////////////////////////////////////
  public final static String XTCS_QXDYQFJG = "1049"; //区县对应签发机关

  ////////////////////////////////////////////
  //系统
  public final static int XT_BZ_SUCC = 100; //成功
  public final static int XT_BZ_ERROR = -1; //失败
  public final static String XT_BZ_QY = "1"; //启用标志---启用
  public final static String XT_BZ_BQY = "0"; //启用标志---停止使用
  public final static String XT_BZ_XZ = "1"; //限制标志---限制
  public final static String XT_BZ_BXZ = "0"; //限制标志---不限制
  public final static String XT_BDLX_ADD = "I"; //变动类型--增加
  public final static String XT_BDLX_UPDATE = "U"; //变动类型--修改
  public final static String XT_BDLX_DELETE = "D"; //变动类型--删除
  public final static String XT_XGBZ = "3"; //修改标志
  public final static int XT_PAGE = 0; //默认页
  public final static int XT_PAGESIZE = -1; //默认页大小
  public final static String XT_SJCSZ = "0"; //数据初始值
  public final static String XT_ZCSB = "0"; //模板流程动作标志--最初始步
  public final static String XT_ZJB = "1"; //模板流程动作标志--中间步
  public final static String XT_JSB = "9"; //模板流程动作标志--结束步
  public final static String XT_QX_XZQH = "1"; //权限标志--行政区划
  public final static String XT_QX_PCS = "2"; //权限标志--居委会
  public final static String XT_QX_JWH = "3"; //权限标志--派出所
  public final static String XT_CJYHMC = "超级用户"; //超级用户角色ID
  public final static String XT_CJYHDLM = "HZADMIN"; //超级用户
  ///////////////////////////////////////////////
  //功能编号
  //户籍 F1
  public final static String GNBH_HJ_RYXXHQ = "F1001"; //人员信息获取
  public final static String GNBH_HJ_RYXXHQ_ZZBD = "F1101"; //人员信息获取(住址变动)
  public final static String GNBH_HJ_RYXXHQ_CX = "F1201"; //人员信息获取(查询)
  public final static String GNBH_HJ_RYXXHQ_HZ = "F1301"; //人员信息获取(户主)
  public final static String GNBH_HJ_RYZPHQ = "F1002"; //人员照片获取
  public final static String GNBH_HJ_HDXXHQ = "F1003"; //户地信息获取
  public final static String GNBH_HJ_HCYXXHQ = "F1004"; //户成员信息获取
  public final static String GNBH_HJ_HCYXXEXHQ = "F1104"; //户成员信息获取_增强
  public final static String GNBH_HJ_CSDJYW = "F1005"; //出生登记业务 **
  public final static String GNBH_HJ_QRDJYW = "F1006"; //迁入登记业务 **
  public final static String GNBH_HJ_ZZBDYW = "F1106"; //住址变动业务 ****
  public final static String GNBH_HJ_QCZXYW = "F1007"; //迁出注销业务 **
  public final static String GNBH_HJ_SWZXYW = "F1008"; //死亡注销业务 **
  public final static String GNBH_HJ_HJBLYW = "F1009"; //户籍补录业务 **
  public final static String GNBH_HJ_HJSCYW = "F1010"; //户籍删除业务 **
  public final static String GNBH_HJ_QCCLYW = "F1011"; //迁出处理业务 **
  public final static String GNBH_HJ_HBBGYW = "F1012"; //户别变更业务 **
  public final static String GNBH_HJ_BGGZYW = "F1013"; //变更更正业务 **
  public final static String GNBH_HJ_SFHMFPBLYW = "F1014"; //身份号码分配补录业务 **
  public final static String GNBH_HJ_SFHMFPSCYW = "F1015"; //身份号码分配删除业务 **
  public final static String GNBH_HJ_RHFLYW = "F1016"; //人户分离业务 **
  public final static String GNBH_HJ_SFHMFPXXHQ = "F1017"; //身份号码分配信息获取
  public final static String GNBH_HJ_BGGZXXHQ = "F1018"; //变更更正信息获取
  public final static String GNBH_HJ_CHCLXXHQ = "F1019"; //重号处理信息获取
  public final static String GNBH_HJ_CSDJXXHQ = "F1020"; //出生登记信息获取
  public final static String GNBH_HJ_HBBGXXHQ = "F1021"; //户别变更信息获取
  public final static String GNBH_HJ_HCYBDXXHQ = "F1022"; //户成员变动信息获取
  public final static String GNBH_HJ_HJBLXXHQ = "F1023"; //户籍补录信息获取
  public final static String GNBH_HJ_HJSCXXHQ = "F1024"; //户籍删除信息获取
  public final static String GNBH_HJ_HXXHQ = "F1025"; //户信息获取
  public final static String GNBH_HJ_QCCLXXHQ = "F1026"; //迁出处理信息获取
  public final static String GNBH_HJ_QCZXXXHQ = "F1027"; //迁出注销信息获取
  public final static String GNBH_HJ_QRDJXXHQ = "F1028"; //迁入登记信息获取
  public final static String GNBH_HJ_RHFLXXHQ = "F1029"; //人户分离信息获取
  public final static String GNBH_HJ_SWZXXXHQ = "F1030"; //死亡注销信息获取
  public final static String GNBH_HJ_ZZBDXXHQ = "F1031"; //住址变动信息获取
  public final static String GNBH_HJ_PZBCXXHQ = "F1032"; //拍照保存信息获取
  public final static String GNBH_HJ_PZBCYW = "F1033"; //拍照保存业务 **
  public final static String GNBH_HJ_RYZPXXHQ = "F1034"; //人员照片信息获取
  public final static String GNBH_HJ_QHBGYW = "F1035"; //全户变更业务 **
  public final static String GNBH_HJ_CZRKDJBXXHQ = "F1036"; //常住人口登记表信息获取
  public final static String GNBH_HJ_QYZDYXXHQ = "F1037"; //迁移证打印信息获取
  public final static String GNBH_HJ_QYZBHHTYW = "F1038"; //迁移证编号回填业务 **
  public final static String GNBH_HJ_DBXXHQ = "F1039"; //单表信息获取
  public final static String GNBH_HJ_RYBDXXHQ = "F1040"; //人员变动信息获取
  public final static String GNBH_HJ_ZPSCYW = "F1041"; //照片删除业务 **
  public final static String GNBH_HJ_PZRZXXHQ = "F1042"; //拍照日志信息查询
  public final static String GNBH_HJ_PZRZXXHQ_YCL = "F1043"; //已处理的拍照日志信息查询
  public final static String GNBH_HJ_PZZPSCYW = "F1044"; //拍照照片删除业务
  //add hb 2006-09-12
  public final static String GNBH_HJ_DYXXBC = "F3419";//打印信息保存
  public final static String GNBH_HJ_DYXXCX = "F3430";//打印信息查询
  //add hb 2006-11-24
  public final static String GNBH_HJ_LSRYBDXXHQ = "F1400";//历史人员变动信息获取
  public final static String GNBH_HJ_LSRYBGXXHQ = "F1401";//历史变更信息获取
  public final static String GNBH_HJ_LSQRDJXXHQ = "F1402";//历史迁入登记信息获取

  //地址 F2
  public final static String GNBH_DZ_DZSCYW = "F2001"; //地址删除业务 **
  public final static String GNBH_DZ_DZXGYW = "F2002"; //地址修改业务 **
  public final static String GNBH_DZ_DZZJYW = "F2003"; //地址增加业务 **
  public final static String GNBH_DZ_DZZXYW = "F2004"; //地址注销业务 **
  public final static String GNBH_DZ_DZDJZJYW = "F2005"; //地址冻结增加业务 **
  public final static String GNBH_DZ_DZDJXGYW = "F2006"; //地址冻结修改业务 **
  public final static String GNBH_DZ_DZDJSCYW = "F2007"; //地址冻结删除业务 **
  public final static String GNBH_DZ_DZXXHQ = "F2008"; //地址信息获取
  public final static String GNBH_DZ_DZDJXXHQ = "F2009"; //地址冻结信息获取
  public final static String GNBH_DZ_HGXZJYW = "F2010"; //户关系增加业务 **
  public final static String GNBH_DZ_HGXSCYW = "F2011"; //户关系删除业务 **
  public final static String GNBH_DZ_HGXXXHQ = "F2012"; //户关系信息获取
  public final static String GNBH_DZ_QHTZYW = "F2101"; //区划调整业务 **

  //冲销 F4
  public final static String GNBH_CX_HJHFYW = "F4001"; //户籍恢复 **
  public final static String GNBH_CX_HJZXYW = "F4002"; //户籍注销 **

  //审批 F5
  public final static String GNBH_SP_BGSPDJYW = "F5001"; //变更审批登记业务 **
  public final static String GNBH_SP_BGSPXXHQ = "F5002"; //变更审批信息获取
  public final static String GNBH_SP_BGSPZXXHQ = "F5003"; //变更审批子信息获取
  public final static String GNBH_SP_BGSPSPYW = "F5004"; //变更审批审批业务 **
  public final static String GNBH_SP_BGSPJGCLYW = "F5005"; //变更审批结果处理业务 **
  public final static String GNBH_SP_QRSPDJYW = "F5006"; //迁入审批登记业务 **
  public final static String GNBH_SP_QRSPXXHQ = "F5007"; //迁入审批信息获取
  public final static String GNBH_SP_QRSPZXXHQ = "F5008"; //迁入审批子信息获取
  public final static String GNBH_SP_QRSPSPYW = "F5009"; //迁入审批审批业务 **
  public final static String GNBH_SP_ZQZBHHTYW = "F5010"; //准迁证编号回填业务 **
  public final static String GNBH_SP_YH_BGSPXXHQ = "F5011"; //登录用户能审批的变更审批信息获取
  public final static String GNBH_SP_YH_QRSPXXHQ = "F5012"; //登录用户能审批的迁入审批信息获取
  public final static String GNBH_SP_BGSPSPXXHQ = "F5013"; //变更审批审批信息获取
  public final static String GNBH_SP_QRSPSPXXHQ = "F5014"; //迁入审批审批信息获取
  public final static String GNBH_SP_HJBLSPDJYW = "F5015"; //户籍补录审批登记业务 **
  public final static String GNBH_SP_HJBLSPSPYW = "F5016"; //户籍补录审批审批业务 **
  public final static String GNBH_SP_HJBLSPXXHQ = "F5017"; //户籍补录审批信息获取
  public final static String GNBH_SP_YH_HJBLSPXXHQ = "F5018"; //登录用户能审批的户籍补录审批信息获取
  public final static String GNBH_SP_HJSCSPDJYW = "F5019"; //户籍删除审批登记业务 **
  public final static String GNBH_SP_HJSCSPSPYW = "F5020"; //户籍删除审批审批业务 **
  public final static String GNBH_SP_HBBGSPDJYW = "F5021"; //户别变更审批登记业务 **
  public final static String GNBH_SP_HBBGSPSPYW = "F5022"; //户别变更审批审批业务 **
  public final static String GNBH_SP_HJSCSPXXHQ = "F5023"; //户籍删除审批信息获取
  public final static String GNBH_SP_YH_HJSCSPXXHQ = "F5024"; //登录用户能审批的户籍删除审批信息获取
  public final static String GNBH_SP_HBBGSPXXHQ = "F5025"; //户别变更审批信息获取
  public final static String GNBH_SP_YH_HBBGSPXXHQ = "F5026"; //登录用户能审批的户别变更审批信息获取
  public final static String GNBH_SP_HJSCSPSPXXHQ = "F5027"; //户籍删除审批审批信息获取
  public final static String GNBH_SP_HJBLSPSPXXHQ = "F5028"; //户籍补录审批审批信息获取
  public final static String GNBH_SP_HBBGSPSPXXHQ = "F5029"; //户别变更审批审批信息获取
  public final static String GNBH_SP_BGSPZFYW = "F5030"; //变更审批作废业务 **
  public final static String GNBH_SP_SPFDCLXGYW = "F5031"; //审批附带材料修改业务 **

  ///////////////////////////////////////////////////////////////////////////
  //证件 F3
  ///////////////////////////////////////////////////////////////////////////
  //一代证业务（F3100-F3199）
  public final static String GNBH_ZJ_1DZSLYW = "F3101"; //一代证受理业务
  public final static String GNBH_ZJ_1DZCBDDYYW = "F3102"; //一代证催办单打印业务
  public final static String GNBH_ZJ_1DZDKDYYW = "F3103"; //一代证底卡打印
  public final static String GNBH_ZJ_1DZSLSCYW = "F3104"; //一代证受理删除业务
  //一代证查询(F3200-F3299)
  public final static String GNBH_ZJ_1DZSLXXCX = "F3201"; //一代证受理信息查询

  //二代证业务(F3300-F3399)
  public final static String GNBH_ZJ_2DZSLYW = "F3301"; //二代证受理业务
  public final static String GNBH_ZJ_ZJYSYW = "F3302"; //证件验收
  public final static String GNBH_ZJ_ZJSFJYYW = "F3303"; //证件身份校验业务
  public final static String GNBH_ZJ_ZJLQFFYW = "F3304"; //证件领取分发业务
  public final static String GNBH_ZJ_ZJGSYW = "F3305"; //证件挂失业务
  public final static String GNBH_ZJ_ZJSJYW = "F3306"; //证件收交业务
  public final static String GNBH_ZJ_ZJXHYW = "F3307"; //证件销毁业务
  public final static String GNBH_ZJ_2DZCBDDYYW = "F3308"; //二代证催办单打印业务
  public final static String GNBH_ZJ_QXZJSHYW = "F3309"; //区县证件审核
  public final static String GNBH_ZJ_QXZJQFYW = "F3310"; //区县证件签发
  public final static String GNBH_ZJ_DSZJSHYW = "F3311"; //地市证件审核
  public final static String GNBH_ZJ_STZJSHYW = "F3312"; //省厅证件审核
  public final static String GNBH_ZJ_PCSZJYSYW = "F3313"; //派出所证件验收业务
  public final static String GNBH_ZJ_QXZJYSYW = "F3314"; //区县证件验收业务
  public final static String GNBH_ZJ_DSZJYSYW = "F3315"; //地市证件验收业务
  public final static String GNBH_ZJ_STZJYSYW = "F3316"; //省厅证件验收业务
  public final static String GNBH_ZJ_2DZCLDDYYW = "F3319"; //二代证催领单打印业务

  public final static String GNBH_ZJ_XML_ZZXX = "F3321"; //居民身份证信息制证信息
  public final static String GNBH_ZJ_XML_ZZXX_CALLBACK = "F3322"; //居民身份证信息制证信息--确认回馈信息
  public final static String GNBH_ZJ_XML_ZLHK = "F3331"; //质量回馈信息
  public final static String GNBH_ZJ_XML_ZZHK = "F3341"; //制证回馈信息

  public final static String GNBH_ZJ_ZJSLZFYW = "F3350"; //证件受理作废业务
  public final static String GNBH_ZJ_ZJDBZFYW = "F3351"; //证件打包作废业务
  public final static String GNBH_ZJ_YDZJSLXXYW = "F1601";//异地证件受理业务
  public final static String GNBH_ZJ_YDZJLQFFYW= "F1603" ; //异地证件领取发放业务

  //二代证查询(F3400-F3499)
  public final static String GNBH_ZJ_2DZSLXXCX = "F3401"; //二代证受理信息查询
  public final static String GNBH_ZJ_SJXXCX = "F3402"; //收交信息查询
  public final static String GNBH_ZJ_YSXXCX = "F3403"; //验收信息查询
  public final static String GNBH_ZJ_LQFFXXCX = "F3404"; //领取发放信息查询
  public final static String GNBH_ZJ_GSXXCX = "F3405"; //挂失信息查询
  public final static String GNBH_ZJ_SHXXCX = "F3406"; //审核信息查询
  public final static String GNBH_ZJ_XHXXCX = "F3407"; //销毁信息查询
  public final static String GNBH_ZJ_TDXXCX = "F3408"; //投递信息查询
  public final static String GNBH_ZJ_ZZHKXXCX = "F3409"; //制证反馈信息查询
  public final static String GNBH_ZJ_ZLKZHKXXCX = "F3410"; //质量反馈信息查询
  public final static String GNBH_ZJ_JMSFZXXCX = "F3411"; //身份证信息查询
  public final static String GNBH_ZJ_JMSFZBDXXCX = "F3412"; //身份证变动信息查询
  public final static String GNBH_ZJ_GMSFHMSXMFPXXCX = "F3413"; //身份号分配信息查询
  public final static String GNBH_ZJ_CHXXCX = "F3414"; //重号信息查询
  public final static String GNBH_ZJ_CHCLXXCX = "F3415"; //重号处理信息查询
  public final static String GNBH_ZJ_ZJSLSLZTFZXXCX = "F3416"; //二代证件受理信息受理状态分组信息查询
  public final static String GNBH_ZJ_YDZJSLXXCX = "F1602";//异地证件受理信息查询

  //一代证、二代证
  public final static String GNBH_ZJ_EDZDQRYCX = "F3420"; //二代证到期人员查询
  public final static String GNBH_ZJ_YDZDQRYCX = "F3421"; //一代证到期人员查询
  public final static String GNBH_ZJ_HLEDZRYCX = "F3422"; //换领二代证人员查询
  public final static String GNBH_ZJ_M16SSLEDZRYCX = "F3423"; //满16岁需申领二代证人员查询

  /////////////////////////////////////////////
  //系统 F9
  public final static String GNBH_XT_CSZJ = "F9003"; //系统参数增加
  public final static String GNBH_XT_CSXG = "F9004"; //系统参数修改
  public final static String GNBH_XT_CSSC = "F9005"; //系统参数删除
  public final static String GNBH_XT_KZCSXG = "F9008"; //系统控制参数修改
  public final static String GNBH_XT_SJZDZJ = "F9011"; //系统数据字典增加
  public final static String GNBH_XT_SJZDXG = "F9012"; //系统数据字典修改
  public final static String GNBH_XT_SJZDSC = "F9013"; //系统数据字典删除
  // public final static String GNBH_XT_GBCSZJ = "F9014"; //国标参数增加
  // public final static String GNBH_XT_GBCSXG = "F9015"; //国标参数修改
  // public final static String GNBH_XT_GBCSSC = "F9016"; //国标参数删除
  public final static String GNBH_XT_XZQHZJ = "F9016"; //行政区划增加
  public final static String GNBH_XT_XZQHXG = "F9017"; //行政区划修改
  public final static String GNBH_XT_XZQHSC = "F9018"; //行政区划删除
  public final static String GNBH_XT_DWXXZJ = "F9021"; //单位信息增加
  public final static String GNBH_XT_DWXXXG = "F9022"; //单位信息修改
  public final static String GNBH_XT_DWXXSC = "F9023"; //单位信息删除
  public final static String GNBH_XT_JWZRQZJ = "F9026"; //警务责任区信息增加
  public final static String GNBH_XT_JWZRQXG = "F9027"; //警务责任区信息修改
  public final static String GNBH_XT_JWZRQSC = "F9028"; //警务责任区信息删除
  public final static String GNBH_XT_XZJDZJ = "F9031"; //乡镇街道信息增加
  public final static String GNBH_XT_XZJDXG = "F9032"; //乡镇街道信息修改
  public final static String GNBH_XT_XZJDSC = "F9033"; //乡镇街道信息删除
  public final static String GNBH_XT_JWHZJ = "F9036"; //居委会信息增加
  public final static String GNBH_XT_JWHXG = "F9037"; //居委会信息修改
  public final static String GNBH_XT_JWHSC = "F9038"; //居委会信息删除
  public final static String GNBH_XT_JLXZJ = "F9041"; //街路巷信息增加
  public final static String GNBH_XT_JLXXG = "F9042"; //街路巷信息修改
  public final static String GNBH_XT_JLXSC = "F9043"; //街路巷信息删除
  public final static String GNBH_XT_JLXJWHDZZJ = "F9046"; //街路巷居委会对照信息增加
  public final static String GNBH_XT_JLXJWHDZXG = "F9047"; //街路巷居委会对照信息修改
  public final static String GNBH_XT_JLXJWHDZSC = "F9048"; //街路巷居委会对照信息删除
  public final static String GNBH_XT_YHIPYXZJ = "F9051"; //用户IP允许信息增加
  public final static String GNBH_XT_YHIPYXXG = "F9052"; //用户IP允许信息修改
  public final static String GNBH_XT_YHIPYXSC = "F9053"; //用户IP允许信息删除
  public final static String GNBH_XT_HHXLZJ = "F9056"; //户号序列信息增加
  public final static String GNBH_XT_HHXLXG = "F9057"; //户号序列信息修改
  public final static String GNBH_XT_HHXLSC = "F9058"; //户号序列信息删除
  public final static String GNBH_XT_BSSQZJ = "F9061"; //本市市区信息增加
  public final static String GNBH_XT_BSSQXG = "F9062"; //本市市区信息修改
  public final static String GNBH_XT_BSSQSC = "F9063"; //本市市区信息删除
  public final static String GNBH_XT_LNWSDZJ = "F9065"; //历年尾数段信息增加
  public final static String GNBH_XT_LNWSDXG = "F9066"; //历年尾数段信息修改
  public final static String GNBH_XT_LNWSDSC = "F9067"; //历年尾数段信息删除
  public final static String GNBH_XT_QYSZZJ = "F9069"; //迁移设置信息增加
  public final static String GNBH_XT_QYSZXG = "F9070"; //迁移设置信息修改
  public final static String GNBH_XT_QYSZSC = "F9071"; //迁移设置信息删除
  public final static String GNBH_XT_SPDZZJ = "F9073"; //审批动作信息增加
  public final static String GNBH_XT_SPDZXG = "F9074"; //审批动作信息修改
  public final static String GNBH_XT_SPDZSC = "F9075"; //审批动作信息删除
  public final static String GNBH_XT_SPMBZJ = "F9077"; //审批模板信息增加
  public final static String GNBH_XT_SPMBXG = "F9078"; //审批模板信息修改
  public final static String GNBH_XT_SPMBSC = "F9079"; //审批模板信息删除
  public final static String GNBH_XT_SPLZJ = "F9081"; //审批流程信息增加
  public final static String GNBH_XT_SPLXG = "F9082"; //审批流程信息修改
  public final static String GNBH_XT_SPLSC = "F9083"; //审批流程信息删除

  public final static String GNBH_XT_YHXXZJ = "F9086"; //用户信息增加
  public final static String GNBH_XT_YHXXXG = "F9087"; //用户信息修改
  public final static String GNBH_XT_YHXXSC = "F9088"; //用户信息删除
  public final static String GNBH_XT_YHDTQXZJ = "F9090"; //用户等同权限信息增加
  public final static String GNBH_XT_YHDTQXSC = "F9091"; //用户等同权限删除
  public final static String GNBH_XT_YHDZQXZJ = "F9093"; //用户动作权限信息增加
  public final static String GNBH_XT_YHDZQXSC = "F9094"; //用户动作权限删除
  public final static String GNBH_XT_YHSJFWZJ = "F9096"; //用户数据范围增加
  public final static String GNBH_XT_YHSJFWSC = "F9097"; //用户数据范围删除
  public final static String GNBH_XT_JSXXZJ = "F9099"; //角色信息增加
  public final static String GNBH_XT_JSXXXG = "F9100"; //角色信息修改
  public final static String GNBH_XT_JSXXSC = "F9101"; //角色信息删除

  public final static String GNBH_XT_YHJSXXZJ = "F9103"; //用户角色信息增加
  public final static String GNBH_XT_YHJSXXXG = "F9104"; //用户角色信息修改
  public final static String GNBH_XT_YHJSXXSC = "F9105"; //用户角色信息删除

  public final static String GNBH_XT_JSYWBBXXZJ = "F9153"; //角色业务报表信息增加
  public final static String GNBH_XT_JSYWBBXXXG = "F9154"; //角色业务报表信息修改
  public final static String GNBH_XT_JSYWBBXXSC = "F9155"; //角色业务报表信息删除

  public final static String GNBH_XT_JSZSBBXXZJ = "F9157"; //角色制式报表信息增加
  public final static String GNBH_XT_JSZSBBXXXG = "F9158"; //角色制式报表信息修改
  public final static String GNBH_XT_JSZSBBXXSC = "F9159"; //角色制式报表信息删除

  public final static String GNBH_XT_JSCDQXZJ = "F9161"; //角色菜单权限信息增加
  public final static String GNBH_XT_JSCDQXXG = "F9162"; //角色菜单权限信息修改
  public final static String GNBH_XT_JSCDQXSC = "F9163"; //角色菜单权限信息删除

  public final static String GNBH_XT_JSGNQXZJ = "F9165"; //角色功能权限信息增加
  public final static String GNBH_XT_JSGNQXXG = "F9166"; //角色功能权限信息修改
  public final static String GNBH_XT_JSGNQXSC = "F9167"; //角色功能权限信息删除

  public final static String GNBH_XT_SJPZZJ = "F9107"; //数据配置信息增加
  public final static String GNBH_XT_SJPZXG = "F9108"; //数据配置信息修改
  public final static String GNBH_XT_SJPZSC = "F9109"; //数据配置信息删除

  public final static String GNBH_XT_YWBBMBXXZJ = "F9112"; //业务报表模板信息增加
  public final static String GNBH_XT_YWBBMBXXXG = "F9113"; //业务报表模板信息修改
  public final static String GNBH_XT_YWBBMBXXSC = "F9114"; //业务报表模板信息删除
  public final static String GNBH_XT_ZSBBMBXXZJ = "F9116"; //制试报表模板信息增加
  public final static String GNBH_XT_ZSBBMBXXXG = "F9117"; //制试报表模板信息修改
  public final static String GNBH_XT_ZSBBMBXXSC = "F9118"; //制试报表模板信息删除
  public final static String GNBH_XT_ZSBBXXZJ = "F9120"; //制试报表信息增加
  public final static String GNBH_XT_ZSBBXXXG = "F9121"; //制试报表信息修改
  public final static String GNBH_XT_ZSBBXXSC = "F9122"; //制试报表信息删除

  public final static String GNBH_XT_YWQXKZZJ = "F9126"; //业务权限控制增加
  public final static String GNBH_XT_YWQXKZXG = "F9127"; //业务权限控制修改
  public final static String GNBH_XT_YWQXKZSC = "F9128"; //业务权限控制删除

  public final static String GNBH_XT_YWBLXZZJ = "F9130"; //业务办理限制信息增加
  public final static String GNBH_XT_YWBLXZXG = "F9131"; //业务办理限制信息修改
  public final static String GNBH_XT_YWBLXZSC = "F9132"; //业务办理限制信息删除
  public final static String GNBH_XT_YWBLXZMXZJ = "F9134"; //业务办理限制明细增加
  public final static String GNBH_XT_YWBLXZMXXG = "F9135"; //业务办理限制明细修改
  public final static String GNBH_XT_YWBLXZMXSC = "F9136"; //业务办理限制明细删除

  public final static String GNBH_XT_SLHXLZJ = "F9141"; //受理号序列信息增加
  public final static String GNBH_XT_SLHXLXG = "F9142"; //受理号序列信息修改
  public final static String GNBH_XT_SLHXLSC = "F9143"; //受理号序列信息删除

  public final static String GNBH_XT_BGDYKZZJ = "F9145"; //变更打印控制信息增加
  public final static String GNBH_XT_BGDYKZXG = "F9146"; //变更打印控制信息修改
  public final static String GNBH_XT_BGDYKZSC = "F9147"; //变更打印控制信息删除
  public final static String GNBH_XT_BGSPKZZJ = "F9149"; //变更审批控制信息增加
  public final static String GNBH_XT_BGSPKZXG = "F9150"; //变更审批控制信息修改
  public final static String GNBH_XT_BGSPKZSC = "F9151"; //变更审批控制信息删除

  //bini_min@hotmail.com _begin
  public final static String GNBH_XT_CHTBYW = "F9901"; //重号同步业务
  public final static String GNBH_XT_GMSFHMWSSCYW = "F9902"; //公民身份号码尾数生成业务
  //_end
  //add hb 2006-08-31
  public final static String GNBH_XT_CXJLZS = "F3418";//查询记录总数

  //临时身份证 add by hh 20051026
  public final static String GNBH_LSSFZ_SLYW = "F7001"; //临时身份证受理
  public final static String GNBH_LSSFZ_SLCX = "F7002"; //临时身份证受理信息查询
  public final static String GNBH_LSSFZ_DYBC = "F7003"; //临时身份证打印保存
  //外围接口 add by hh 20060207
  public final static String GNBH_WW_INTERFACE = "F9900"; //外围接口调用
  public final static String GNBH_WW_SH = "F9801"; //审核
  public final static String GNBH_WW_BDJGHQ = "F9802"; //比对结果获取
  public final static String GNBH_WW_BDJGCL = "F9803"; //比对结果处理
  //add hb 2006-10-17
  public final static String GNBH_WW_FJJJGBC = "F9810";//分检机结果保存
  //add hb 2007-01-19
  public final static String GNBH_WW_DSFFZJ = "F9812";//地市分发证件
  public final static String GNBH_WW_QXJSZJ = "F9813";//区县接收证件
  public final static String GNBH_WW_QXFFZJ = "F9814";//区县发放证件
  public final static String GNBH_WW_PCSJSZJ = "F9815";//派出所接收证件
  public final static String GNBH_WW_FJJGCX = "F9816";//分检结果查询
  public final static String GNBH_WW_JSFFCX = "F9817";//接收发放查询
  public final static String GNBH_WW_JSFFHF = "F9818";//接收发放恢复

  //bini_min@hotmail.com _begin
  //不允许变更更正的项目(字段名称)
  public final static Map HZ2004_SYS_KEY_FIELDS_MAP = new HashMap();
  static {
    HZ2004_SYS_KEY_FIELDS_MAP.put("RYNBID", null);
    HZ2004_SYS_KEY_FIELDS_MAP.put("RYID", null);
    HZ2004_SYS_KEY_FIELDS_MAP.put("HHNBID", null);
    HZ2004_SYS_KEY_FIELDS_MAP.put("HHID", null);
    HZ2004_SYS_KEY_FIELDS_MAP.put("MLPNBID", null);
    HZ2004_SYS_KEY_FIELDS_MAP.put("MLPID", null);
    HZ2004_SYS_KEY_FIELDS_MAP.put("CXBZ", null);
    HZ2004_SYS_KEY_FIELDS_MAP.put("JLBZ", null);
    HZ2004_SYS_KEY_FIELDS_MAP.put("ZPID", null);
    HZ2004_SYS_KEY_FIELDS_MAP.put("NBSFZID", null);
    HZ2004_SYS_KEY_FIELDS_MAP.put("CJHJYWID", null);
    HZ2004_SYS_KEY_FIELDS_MAP.put("CCHJYWID", null);
    HZ2004_SYS_KEY_FIELDS_MAP.put("QYSJ", null);
    HZ2004_SYS_KEY_FIELDS_MAP.put("JSSJ", null);
    HZ2004_SYS_KEY_FIELDS_MAP.put("RYSDZT", null);
    HZ2004_SYS_KEY_FIELDS_MAP.put("YWNR", null);
  }
  //_end



  //add hb 2006-08-31
  public static java.util.Date DefaultServiceTime = null;//数据库时间
  public static java.util.Date DefaultSystemTime = null;//中间件时间
  //add hb 2006-10-10
  public final static Object objReport = new Object();//报表统计操作人数同步控制参数
  //add hb 2007-12-27
  public final static String GNBH_SMK_DKRZXXBC = "F1720";//市民卡读卡信息保存
  public final static String GNBH_SMK_DKRZXXCX = "F1721";//市民卡读卡信息查询
  public final static String GNBH_SMK_XKRZXXBC = "F1722";//市民卡写卡信息保存
  public final static String GNBH_SMK_XKRZXXCX = "F1723";//市民卡写卡信息查询
  public final static String GNBH_ZZZJ_ZZZJRZCX = "F8011";//住址追加日志信息查询

  //new 2006
  //批量数据导出
  public final static String GNBH_DC_CZRK = "F3700"; //常住人口查询导出
  public final static String GNBH_DC_RYBD = "F3701"; //四变信息查询导出
  public final static String XTKZCS_PLSJDCML = "1028"; //批量数据导出目录
  public final static Object objDownload = new Object(); //批量下载操作人数同步控制参数

  //add hb 2006-12-14
  public final static String RPT_JNDI = "rptjndi"; //报表统计JNDI连接

  //同步标志
  public final static String TBBZ_WTQ = "0"; //不提取
  public final static String TBBZ_YTQ = "1"; //已提取

}
