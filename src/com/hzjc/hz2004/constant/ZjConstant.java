package com.hzjc.hz2004.constant;

/**
 * 证件定义的常量
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author Kansan Ku(kgb_hz@126.com)
 * @version 1.0
 */

public class ZjConstant {

  //-证件业务参数(5000-8999)
  //'9999','5000','身份证有效期限(一代证)','名称中文拼音','0');
  //'9999','5001','办证类别(一代证)','名称中文拼音','0');
  //'9999','5002','办证原因(一代证)','名称中文拼音','0');
  //'9999','5003','办证类型(一代证)','名称中文拼音','0');
  //'9999','5004','办理标志(一代证)','名称中文拼音','0');
  //'9999','5005','底卡打印标志(一代证)','名称中文拼音','0');
  //'9999','5006','申领原因(二代证)','名称中文拼音','0');
  //'9999','5007','收交原因(二代证)','名称中文拼音','0');
  //'9999','5008','制证类型(二代证)','名称中文拼音','0');
  //'9999','5009','领证方式(二代证)','名称中文拼音','0');
  //'9999','5010','收费类型(二代证)','名称中文拼音','收费金额','对应申领原因','0');
  //'9999','5011','办理标志(二代证)','名称中文拼音','0');
  //'9999','5012','证件类别','名称中文拼音','0');
  //'9999','5013','证件状态','名称中文拼音','0');
  //'9999','5014','重号处理方式','名称中文拼音','0');
  //'9999','5015','重号记录状态','名称中文拼音','0');
  //'9999','5016','证件质量','名称中文拼音','0');
  //'9999','5017','制证信息错误类别','名称中文拼音','0');
  //'9999','5018','身份号码分配状态','名称中文拼音','0');
  //'9999','5019','领取发放类型','名称中文拼音','0');
  //'9999','5020','审核签发类型','名称中文拼音','0');

  //证件验收状态
  public final static String ZJ_YSZT_PCS = "1"; //派出所验收
  public final static String ZJ_YSZT_QX = "2"; //区县验收
  public final static String ZJ_YSZT_DS = "3"; //地市验收
  public final static String ZJ_YSZT_ST = "4"; //省厅验收

  //一代证办理标志:5004
  public final static String ZJ_BLBZ_1ID_CS = "00"; // 初始,'CS','0'
  public final static String ZJ_BLBZ_1ID_SJWZ = "01"; //数据完整,'SJWZ','0'
  public final static String ZJ_BLBZ_1ID_ZF = "02"; //'作废','ZF','0'
  public final static String ZJ_BLBZ_1ID_XQXSC = "03"; //'向区县上传','XQXSC','0'
  public final static String ZJ_BLBZ_1ID_SCZQX = "04"; //'上传至区县','SCZQX','0'
  public final static String ZJ_BLBZ_1ID_CH = "10"; //'重号','CH','0'
  public final static String ZJ_BLBZ_1ID_SHTG = "11"; //'审核通过','SHTG','0'
  public final static String ZJ_BLBZ_1ID_SHBTG = "12"; //'审核不通过','SHBTG','0'
  public final static String ZJ_BLBZ_1ID_YQF = "13"; //'已签发','YQF','0'
  public final static String ZJ_BLBZ_1ID_XDSSC = "14"; //'向地市上传','XDSSC','0'
  public final static String ZJ_BLBZ_1ID_SCZDS = "15"; ///'上传至地市','SCZDS','0'
  public final static String ZJ_BLBZ_1ID_DSSHTG = "16"; //'地市审核通过','DSSHTG','0'
  public final static String ZJ_BLBZ_1ID_DSSHBTG = "17"; //'地市审核不通过','DSSHBT','0'
  public final static String ZJ_BLBZ_1ID_XSTSC = "18"; //'向省厅上传','XSTSC','0'
  public final static String ZJ_BLBZ_1ID_TQZ = "20"; //'提取中','TQZ','0'
  public final static String ZJ_BLBZ_1ID_SCZST = "21"; //'上传至省厅','SCZST','0'
  public final static String ZJ_BLBZ_1ID_STSHTG = "22"; //'省厅审核通过','STSHTG','0'
  public final static String ZJ_BLBZ_1ID_STSHBTG = "23"; //'省厅审核不通过','STSHBT','0'
  public final static String ZJ_BLBZ_1ID_XZZZXSC = "24"; //'向制证中心上传','XZZZXS','0'
  public final static String ZJ_BLBZ_1ID_DBSZLBHG = "25"; //打包时质量不合格
  public final static String ZJ_BLBZ_1ID_ZLBHG = "30"; //'质量不合格','ZLBHG','0'
  public final static String ZJ_BLBZ_1ID_YZZ = "31"; //'已制证','YZZ','0'
  public final static String ZJ_BLBZ_1ID_ZJBHG = "32"; //'证件不合格','ZJBHG','0'
  public final static String ZJ_BLBZ_1ID_STJS = "60"; //'省厅接收','STJS','0'
  public final static String ZJ_BLBZ_1ID_STZJYSTG = "61"; //'省厅证件验收通过','STZJYS','0'
  public final static String ZJ_BLBZ_1ID_STZJYSBTG = "62"; //'省厅证件验收不通过','STZJYS','0'
  public final static String ZJ_BLBZ_1ID_STYFF = "63"; //'省厅已分发','STYFF','0'
  public final static String ZJ_BLBZ_1ID_DSJS = "70"; //'地市接收','DSJS','0'
  public final static String ZJ_BLBZ_1ID_DSZJYSTG = "71"; //'地市证件验收通过','DSZJYS','0'
  public final static String ZJ_BLBZ_1ID_DSZJYSBTG = "72"; //'地市证件验收不通过','DSZJYS','0'
  public final static String ZJ_BLBZ_1ID_DSYFF = "73"; //'地市已分发','DSYFF','0'
  public final static String ZJ_BLBZ_1ID_QXJS = "80"; //'区县接收','QXJS','0'
  public final static String ZJ_BLBZ_1ID_QXZJYSTG = "81"; //'区县证件验收通过','QXZJYS','0'
  public final static String ZJ_BLBZ_1ID_QXZJYSBTG = "82"; //'区县证件验收不通过','QXZJYS','0'
  public final static String ZJ_BLBZ_1ID_QXYFF = "83"; //'区县已分发','QXYFF','0'
  public final static String ZJ_BLBZ_1ID_PCSJS = "89"; //'派出所接收','PCSJS','0'
  public final static String ZJ_BLBZ_1ID_PCSYSTG = "90"; //'派出所验收通过','PCSYST','0'
  public final static String ZJ_BLBZ_1ID_PCSYSBTG = "91"; //'派出所验收不通过','PCSYSB','0'
  public final static String ZJ_BLBZ_1ID_YDYCLT = "92"; //'已打印催领通知单','YDYCLT','0'
  public final static String ZJ_BLBZ_1ID_YLZ = "93"; //'已领证','YLZ','0'
  public final static String ZJ_BLBZ_1ID_YGS = "94"; //'已挂失','YLZ','0'
  public final static String ZJ_BLBZ_1ID_YSJ = "95"; //'已收交','YLZ','0'
  public final static String ZJ_BLBZ_1ID_YXH = "96"; //'已销毁','YLZ','0'

  //二代证办理标志:5011
  public final static String ZJ_BLBZ_2ID_CS = "00"; // 初始,'CS','0'
  public final static String ZJ_BLBZ_2ID_SJWZ = "01"; //数据完整,'SJWZ','0'
  public final static String ZJ_BLBZ_2ID_ZF = "02"; //'作废','ZF','0'
  public final static String ZJ_BLBZ_2ID_XQXSC = "03"; //'向区县上传','XQXSC','0'
  public final static String ZJ_BLBZ_2ID_SCZQX = "04"; //'上传至区县','SCZQX','0'
  public final static String ZJ_BLBZ_2ID_CH = "10"; //'重号','CH','0'
  public final static String ZJ_BLBZ_2ID_QXSHTG = "11"; //'审核通过','SHTG','0'
  public final static String ZJ_BLBZ_2ID_QXSHBTG = "12"; //'审核不通过','SHBTG','0'
  public final static String ZJ_BLBZ_2ID_YQF = "13"; //'已签发','YQF','0'
  public final static String ZJ_BLBZ_2ID_XDSSC = "14"; //'向地市上传','XDSSC','0'
  public final static String ZJ_BLBZ_2ID_SCZDS = "15"; ///'上传至地市','SCZDS','0'
  public final static String ZJ_BLBZ_2ID_DSSHTG = "16"; //'地市审核通过','DSSHTG','0'
  public final static String ZJ_BLBZ_2ID_DSSHBTG = "17"; //'地市审核不通过','DSSHBT','0'
  public final static String ZJ_BLBZ_2ID_XSTSC = "18"; //'向省厅上传','XSTSC','0'
  public final static String ZJ_BLBZ_2ID_TQZ = "20"; //'提取中','TQZ','0'
  public final static String ZJ_BLBZ_2ID_SCZST = "21"; //'上传至省厅','SCZST','0'
  public final static String ZJ_BLBZ_2ID_STSHTG = "22"; //'省厅审核通过','STSHTG','0'
  public final static String ZJ_BLBZ_2ID_STSHBTG = "23"; //'省厅审核不通过','STSHBT','0'
  public final static String ZJ_BLBZ_2ID_XZZZXSC = "24"; //'向制证中心上传','XZZZXS','0'
  public final static String ZJ_BLBZ_2ID_DBSZLBHG = "25"; //打包时质量不合格
  public final static String ZJ_BLBZ_2ID_ZLBHG = "30"; //'质量不合格','ZLBHG','0'
  public final static String ZJ_BLBZ_2ID_YZZ = "31"; //'已制证','YZZ','0'
  public final static String ZJ_BLBZ_2ID_ZJBHG = "32"; //'证件不合格','ZJBHG','0'
  public final static String ZJ_BLBZ_2ID_STJS = "60"; //'省厅接收','STJS','0'
  public final static String ZJ_BLBZ_2ID_STZJYSTG = "61"; //'省厅证件验收通过','STZJYS','0'
  public final static String ZJ_BLBZ_2ID_STZJYSBTG = "62"; //'省厅证件验收不通过','STZJYS','0'
  public final static String ZJ_BLBZ_2ID_STYFF = "63"; //'省厅已分发','STYFF','0'
  public final static String ZJ_BLBZ_2ID_DSJS = "70"; //'地市接收','DSJS','0'
  public final static String ZJ_BLBZ_2ID_DSZJYSTG = "71"; //'地市证件验收通过','DSZJYS','0'
  public final static String ZJ_BLBZ_2ID_DSZJYSBTG = "72"; //'地市证件验收不通过','DSZJYS','0'
  public final static String ZJ_BLBZ_2ID_DSYFF = "73"; //'地市已分发','DSYFF','0'
  public final static String ZJ_BLBZ_2ID_DSYFJ = "74"; //'地市已分检','DSFJ','0'
  public final static String ZJ_BLBZ_2ID_QXYJS = "80"; //'区县已接收','QXJS','0'
  public final static String ZJ_BLBZ_2ID_QXZJYSTG = "81"; //'区县证件验收通过','QXZJYS','0'
  public final static String ZJ_BLBZ_2ID_QXZJYSBTG = "82"; //'区县证件验收不通过','QXZJYS','0'
  public final static String ZJ_BLBZ_2ID_QXYFF = "83"; //'区县已分发','QXYFF','0'
  public final static String ZJ_BLBZ_2ID_PCSJS = "89"; //'派出所接收','PCSJS','0'
  public final static String ZJ_BLBZ_2ID_PCSZJYSTG = "90"; //'派出所验收通过','PCSYST','0'
  public final static String ZJ_BLBZ_2ID_PCSZJYSBTG = "91"; //'派出所验收不通过','PCSYSB','0'
  public final static String ZJ_BLBZ_2ID_YDYCLT = "92"; //'已打印催领通知单','YDYCLT','0'
  public final static String ZJ_BLBZ_2ID_YLZ = "93"; //'已领证','YLZ','0'
  public final static String ZJ_BLBZ_2ID_YGS = "94"; //'已挂失','YLZ','0'
  public final static String ZJ_BLBZ_2ID_YSJ = "95"; //'已收交','YLZ','0'
  public final static String ZJ_BLBZ_2ID_YXH = "96"; //'已销毁','YLZ','0'
  public final static String ZJ_BLBZ_2ID_YCB = "97"; //'已重办','YLZ','0'
  public final static String ZJ_BLBZ_2ID_ZFGD = "98"; //'作废归档','ZFGD','0'

  //证件类别5012
  public final static String ZJ_ZJLB_YDZ = "1"; //'一代证','YDZ','0'
  public final static String ZJ_ZJLB_EDZ = "2"; //'二代证','EDZ','0'
  public final static String ZJ_ZJLB_LSYDZ = "3"; //'临时一代证','LSYDZ','0'
  public final static String ZJ_ZJLB_LSEDZ = "4"; //'临时二代证','LSEDZ','0'

  //证件状态5013
  public final static String ZJ_ZJZT_ZC = "1"; //'正常','BLZ','0'
  public final static String ZJ_ZJZT_GS = "2"; //'已挂失','YGS','0'
  public final static String ZJ_ZJZT_SH = "3"; //'已收回','YSH','0'
  public final static String ZJ_ZJZT_XH = "4"; // '已销毁','YXH','0'
  public final static String ZJ_ZJZT_QT = "9"; //'9','其他','QT','0'

  //证件质量 5016
  //制证信息错误类别 5017
  //审核情况5022
  public final static String ZJ_SHQK_WSH = "0"; //未审核
  public final static String ZJ_SHQK_HG = "1"; //合格
  public final static String ZJ_SHQK_BHG = "2"; //不合格

  //审核签发类型 5020
  public final static String ZJ_SHQFLX_QXSH = "1"; //区县审核
  public final static String ZJ_SHQFLX_DSSH = "2"; //地市审核
  public final static String ZJ_SHQFLX_STSH = "3"; //省厅审核

  //制证信息错误类别
  public static final String ZJ_ZZXXCWLB_HG = "00"; //'合格'

  //一代证有效期限
  public static final String YXQX_DL = "0"; //待领
  public static final String YXQX_10N = "1"; //10年
  public static final String YXQX_20N = "2"; //20年
  public static final String YXQX_CQ = "3"; //长期
  public static final String YXQX_HL = "4"; //缓领
  public static final String YXQX_MF = "5"; //免发
  public static final String YXQX_BF = "9"; //不发

  //证件照片来源类型
  public static final String ZPLYLX_RYZPB = "0"; //来源于人员照片表
  public static final String ZPLYLX_ZPLSB = "1"; //来源于照片临时表
  public static final String ZPLYLX_SKYDCJZPB = "2"; //来源于省厅异地采集照片表

  //身份证信息增加类型
  public static final String SFZXX_ZJLX_LQ = "1"; //领取增加
  public static final String SFZXX_ZJLX_QR = "2"; //迁入增加
  public static final String SFZXX_ZJLX_DZZJ = "3"; //地址追加增加
  public static final String SFZXX_ZJLX_HJBL = "4"; //户籍补录增加
  //临时身份证打印标志
  public static final String LSSFZ_DYBZ_WDY = "0"; //未打印
  public static final String LSSFZ_DYBZ_YDY = "1"; //已打印
  public static final String LSSFZ_DYBZ_ZF = "2"; //作废
}
