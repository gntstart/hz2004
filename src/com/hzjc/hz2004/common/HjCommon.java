package com.hzjc.hz2004.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.LockOptions;

import com.alibaba.fastjson.JSONObject;
import com.hzjc.hz2004.base.SpringContextHolder;
import com.hzjc.hz2004.base.login.AuthToken;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.constant.DzConstant;
import com.hzjc.hz2004.constant.HjConstant;
import com.hzjc.hz2004.constant.PublicConstant;
import com.hzjc.hz2004.constant.ZjConstant;
import com.hzjc.hz2004.dao.DAOFactory;
import com.hzjc.hz2004.dao.PojoInfo;
import com.hzjc.hz2004.po.*;
import com.hzjc.hz2004.service.impl.Hz2004BaseService;
import com.hzjc.hz2004.service.impl.XtywqxServiceImpl;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.ObjectUtil;
import com.hzjc.hz2004.vo.*;
import com.hzjc.util.StringUtils;
import com.hzjc.wsstruts.common.db.DbUtils;
import com.hzjc.wsstruts.exception.DAOException;
import com.hzjc.wsstruts.exception.ServiceException;
import com.hzjc.wsstruts.exception.WSErrCode;

/**
 * 户籍公共类
 * <p>Title: hz2004</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class HjCommon
    extends Hz2004BaseService {

/**
   * 计算月份所在的季度
   * @param yf - 月份(1-12)
   * @return 0 - 非法月份 / 1 - 一季度 / 2 - 二季度 / 3 - 三季度 / 4 - 四季度
   */
  private int caclYFJD(int yf) {
    int jd = 0;

    //一季度
    if (yf >= 1 && yf <= 3) {
      jd = 1;
    }
    //二季度
    else if (yf >= 4 && yf <= 6) {
      jd = 2;
    }
    //三季度
    else if (yf >= 7 && yf <= 9) {
      jd = 3;
    }
    //四季度
    else if (yf >= 10 && yf <= 12) {
      jd = 4;
    }

    return jd;
  }

  /**
   * 计算日期类型
   * @param rq - 日期
   * @return 0 - 非本年的日期 / 1 - 本月 / 2 - 本季 / 3 - 本年
   */
  protected int caclRQLX(String rq) throws ServiceException {
    int rqlx = 0;
    
    String now = StringUtils.getServiceTime();

    //日期校验
    if (rq == null || (rq != null && rq.length() < 8)) {
      return 0;
    }

    try {

      //本月
      if (now.substring(0, 6).equals(rq.substring(0, 6))) {
        rqlx = 1;
      }
      //本季
      else if (now.substring(0, 4).equals(rq.substring(0, 4)) &&
               this.caclYFJD(Integer.valueOf(now.substring(4, 6)).intValue()) ==
               this.caclYFJD(Integer.valueOf(rq.substring(4, 6)).intValue())) {
        rqlx = 2;
      }
      //本年
      else if (now.substring(0, 4).equals(rq.substring(0, 4))) {
        rqlx = 3;
      }
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return rqlx;
  }

  /**
   * 判断是否是关键字段
   * @param key - 字段名称
   * @return
   */
  protected boolean isKeyField(String key) {
    return PublicConstant.HZ2004_SYS_KEY_FIELDS_MAP.containsKey(key);
  }

  /**
   * 计算死亡年龄
   * @param csrq - 出生日期(yyyymmdd)
   * @param swrq - 死亡日期(yyyymmdd)
   * @return
   * @throws ServiceException
   */
  protected int calcSWNL(String csrq, String swrq) throws ServiceException {

    int csYear = 0;
    int csMonth = 0;
    int csDay = 0;
    int swYear = 0;
    int swMonth = 0;
    int swDay = 0;

    if (csrq == null || (csrq != null && csrq.length() < 8)) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                 "出生日期不可为空或格式不对，无法计算死亡年龄。", null);
    }
    if (swrq == null || (swrq != null && swrq.length() < 8)) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                 "死亡日期不可为空或格式不对，无法计算死亡年龄。", null);
    }

    try {
      csYear = Integer.parseInt(csrq.substring(0, 4));
      csMonth = Integer.parseInt(csrq.substring(4, 6));
      csDay = Integer.parseInt(csrq.substring(6, 8));
      swYear = Integer.parseInt(swrq.substring(0, 4));
      swMonth = Integer.parseInt(swrq.substring(4, 6));
      swDay = Integer.parseInt(swrq.substring(6, 8));

      if (swDay - csDay < 0) {
        swMonth--;
      }

      if (swMonth - csMonth < 0) {
        swYear--;
      }
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    
    return swYear - csYear;
  }

  /**
   * 保存证件业务流水信息
   * @param ywbz - 业务标志
   * @param czsj - 操作时间
   * @throws ServiceException
   * @throws DAOException
   */
  protected void saveZJYWLSXX(String ywbz, String slzt, String slh, String czsj) throws
      ServiceException, DAOException {
	  
    try {
      PojoInfo zjls_sfzywczbDAO = DAOFactory.createZJLS_SFZYWCZBDAO();

      PoZJLS_SFZYWCZB poSfzywczxx = new PoZJLS_SFZYWCZB();
      poSfzywczxx.setZjywid( (Long) zjls_sfzywczbDAO.getId());
      poSfzywczxx.setYwbz(ywbz);
      poSfzywczxx.setCzyid(this.getUser().getYhid());
      poSfzywczxx.setSlzt(slzt);
      poSfzywczxx.setSlh(slh);
      poSfzywczxx.setCzyxm(this.getUser().getYhxm());
      poSfzywczxx.setCzsj(czsj);
      poSfzywczxx.setCzip(BaseContext.getBaseUser().getIp());
      super.create(poSfzywczxx);
      
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return;
  }

  /**
   * 保存户籍业务流水信息
   * @param hjywid - 户籍业务ID
   * @param ywbz - 业务标志
   * @param ywlx - 业务类型
   * @param czsm - 操作数日
   * @param voSbjbxx - 申报基本信息
   * @param slsj - 受理时间
   * @throws ServiceException
   * @throws DAOException
   */
  public PoHJLS_HJYWLSB saveHJYWLSXX(Long hjywid, String ywbz, String ywlx,
                                     int czsm, VoSbjbxx voSbjbxx,
                                     String slsj) throws
      ServiceException, DAOException {

    PoHJLS_HJYWLSB poHJLS_HJYWLSB = null;

    try {
      PojoInfo hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();
      poHJLS_HJYWLSB = new PoHJLS_HJYWLSB();
      poHJLS_HJYWLSB.setHjywid(hjywid);
      poHJLS_HJYWLSB.setYwbz(ywbz);
      poHJLS_HJYWLSB.setYwlx(ywlx);
      poHJLS_HJYWLSB.setCzsm(new Long(czsm));
      if (voSbjbxx != null) {
        poHJLS_HJYWLSB.setSbryxm(voSbjbxx.getSbryxm());
        poHJLS_HJYWLSB.setSbrgmsfhm(voSbjbxx.getSbrgmsfhm());
        poHJLS_HJYWLSB.setSbsj(voSbjbxx.getSbsj());
      }
      poHJLS_HJYWLSB.setSldw(this.getUser().getDwdm());
      poHJLS_HJYWLSB.setSlrid(this.getUser().getYhid());
      poHJLS_HJYWLSB.setSlsj(slsj);
      poHJLS_HJYWLSB.setCzip(BaseContext.getUser().getIp());
      super.create(poHJLS_HJYWLSB);
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return poHJLS_HJYWLSB;
  }

  /**
   * 校验人信息的时效性
   * @param poRyxx - 人信息Po
   * @param hjxx_czrkjbxxbDAO - HJXX_CZRKJBXXB表的DAO
   * @param ywmc - 业务名称
   * @throws DAOException
   * @throws ServiceException
   */
  protected void checkRXX(PoHJXX_CZRKJBXXB poRyxx,
                          PojoInfo hjxx_czrkjbxxbDAO,
                          String ywmc) throws DAOException,
      ServiceException {

    if (poRyxx == null) {
      return;
    }

    try {
      //创建HJXX_CZRKJBXXB表的DAO
      if (hjxx_czrkjbxxbDAO == null) {
        hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
      }

      //时效性校验
      //hjxx_czrkjbxxbDAO.refresh(poRyxx, LockMode.UPGRADE); //锁记录
      if (! (PublicConstant.JLBZ_ZX.equals(poRyxx.getJlbz()) &&
             PublicConstant.CXBZ_FCX.equals(poRyxx.getCxbz()))) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                   "时效性错误，" + poRyxx.getXm() + "(" +
                                   poRyxx.getGmsfhm() +
                                   ")的最新记录可能在操作过程中被其他用户操作过，" + ywmc + "无法完成。", null);
      }
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return;
  }

  /**
   * 校验户信息的时效性
   * @param poHxx - 户信息Po
   * @param hjxx_hxxbDAO - HJXX_HXXB表的DAO
   * @param ywmc - 业务名称
   * @throws DAOException
   * @throws ServiceException
   */
  protected void checkHXX(PoHJXX_HXXB poHxx,
                          PojoInfo hjxx_hxxbDAO,
                          String ywmc) throws DAOException,
      ServiceException {

    if (poHxx == null) {
      return;
    }

    try {
      //创建HJXX_HXXB表的DAO
      if (hjxx_hxxbDAO == null) {
        hjxx_hxxbDAO = DAOFactory.createHJXX_HXXBDAO();
      }

      //时效性校验
      super.refresh(poHxx, LockOptions.UPGRADE); //锁记录
      if (! (PublicConstant.JLBZ_ZX.equals(poHxx.getJlbz()) &&
             PublicConstant.CXBZ_FCX.equals(poHxx.getCxbz()))) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                   "时效性错误，户号为" + poHxx.getHh() +
                                   "的最新记录可能在操作过程中被其他用户操作过，" + ywmc + "无法完成。", null);
      }
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return;
  }

  /**
   * 校验地信息的时效性
   * @param poMlpxxxx - 人信息Po
   * @param hjxx_mlpxxxxbDAO - HJXX_MLPXXXXB表的DAO
   * @param ywmc - 业务名称
   * @throws DAOException
   * @throws ServiceException
   */
  protected void checkDXX(PoHJXX_MLPXXXXB poMlpxxxx,
                          PojoInfo hjxx_mlpxxxxbDAO,
                          String ywmc) throws DAOException,
      ServiceException {

    if (poMlpxxxx == null) {
      return;
    }

    try {
      //创建HJXX_CZRKJBXXB表的DAO
      if (hjxx_mlpxxxxbDAO == null) {
        hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();
      }

      //时效性校验
      super.refresh(poMlpxxxx, LockOptions.UPGRADE); //锁记录
      if (!PublicConstant.JLBZ_ZX.equals(poMlpxxxx.getJlbz())) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                   "时效性错误，该地(" + poMlpxxxx.getMlpid() +
                                   ")的最新记录可能在操作过程中被其他用户操作过，" + ywmc +
                                   "无法完成。", null);
      }
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return;
  }

  /**
   * 判断变更更正控制标志(判断变更更正项目是否需要保存变更更正信息)
   * @param xm - 项目
   * @return
   */
  protected boolean checkBGGZ_DYKZBZ(String xm) {

    String strHQL = null;
    boolean bRET = true;

    try {
      PojoInfo hjxz_bgdyzdkzbDAO = DAOFactory.createHJXZ_BGDYZDKZBDAO();

      strHQL = "from PoHJXZ_BGDYZDKZB where kzbz='" + HjConstant.BGGZ_KZBZ_BYX +
          "' and zdmc='" + xm + "'";

      List lst = super.getObjectListByHql(strHQL);
      if (lst != null && lst.size() > 0) {
        bRET = false;
      }
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return bRET;
  }

  /**
   * 翻译单位代码
   * @param dm
   * @return
   * @throws DAOException
   */
  protected String transDWDM(String dm) throws DAOException {
    String mc = null;

    if (dm == null) {
      return null;
    }

    try {
    	PojoInfo xt_dwxxbDAO = DAOFactory.createXT_DWXXBDAO();
      PoXT_DWXXB po = (PoXT_DWXXB) super.get(PoXT_DWXXB.class, dm);
      if (po != null) {
        mc = po.getMc();
      }
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return mc;
  }

  /**
   * 翻译变更更正项目
   * @param xm
   * @param dm
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  protected String[] transBGGZXM(String xm, String dm) throws ServiceException,
      DAOException {

    String strRET[] = new String[2];
    String strHQL = null;

    try {
      PojoInfo xt_xtcsbDAO = DAOFactory.createXT_XTCSBDAO();
      PojoInfo xt_xzqhbDAO = DAOFactory.createXT_XZQHBDAO();

      PoXT_XTCSB poXtcsb = getXTCSB(PublicConstant.XTKZCS_BGGZXM, xm);
      if (poXtcsb != null) {
        strRET[0] = poXtcsb.getMc();
        //系统参数表
        if ("XT_XTCSB".equalsIgnoreCase(poXtcsb.getKzbzc())) {
          strHQL = "from PoXT_XTCSB where cslb='" + poXtcsb.getKzbzd() +
              "' and trim(dm)='" + dm + "' ";
          List xtcsbList = super.getObjectListByHql(strHQL);
          if (xtcsbList != null && xtcsbList.size() > 0) {
            PoXT_XTCSB po = (PoXT_XTCSB) xtcsbList.get(0);
            strRET[1] = po.getMc();
          }
        }
        else if ("XT_XZQHB".equalsIgnoreCase(poXtcsb.getKzbzc())) {
          strHQL = "from PoXT_XZQHB where dm='" + dm + "' ";
          List xzqhList = super.getObjectListByHql(strHQL);
          if (xzqhList != null && xzqhList.size() > 0) {
            PoXT_XZQHB po = (PoXT_XZQHB) xzqhList.get(0);
            strRET[1] = po.getMc();
          }
        }
        //胡斌 20060530 非代码类也填写入bgqdm,bghdm
        else if (poXtcsb.getKzbzc() == null) {
          strRET[1] = dm;
        }
      }
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return strRET;
  }

  /**
   * 得到系统控制参数
   * @param cslb - 参数类别
   * @param dm - 代码
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  protected PoXT_XTCSB getXTCSB(String cslb, String dm) throws ServiceException,
      DAOException {

    PoXT_XTCSB poXtcsb = null;
    String strHQL = null;

    try {
      PojoInfo xt_xtcsbDAO = DAOFactory.createXT_XTCSBDAO();

      strHQL = "from PoXT_XTCSB where cslb='" + cslb +
          "' and trim(dm)='" + dm + "' ";
      List xtcsbList = super.getObjectListByHql(strHQL);
      if (xtcsbList != null && xtcsbList.size() > 0) {
        poXtcsb = (PoXT_XTCSB) xtcsbList.get(0);
      }
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return poXtcsb;
  }

  /**
   * 得到系统控制参数
   * @param kzlb - 控制类别
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  protected String getXTKZCS(String kzlb) throws
      ServiceException,
      DAOException {

    String kzz = null;
    String strHQL = null;

    strHQL = "from PoXT_XTKZCSB where kzlb='" + kzlb + "' ";

    try {
      PojoInfo xt_xtkzcsbDAO = DAOFactory.createXT_XTKZCSBDAO();

      List lst = super.getObjectListByHql(strHQL);
      if (lst != null && lst.size() > 0) {
        PoXT_XTKZCSB poXT_XTKZCSB = (PoXT_XTKZCSB) lst.get(0);
        kzz = poXT_XTKZCSB.getKzz();
      }
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return kzz != null ? kzz.trim() : "";
  }

  /**
   * 通过公民身份号码得到人员信息
   * @param gmsfhm
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  protected List getRYXX(String gmsfhm) throws
      ServiceException,
      DAOException {

    StringBuffer strHQL = new StringBuffer();
    List ryxxList = null;

    strHQL.append("from PoHJXX_CZRKJBXXB ")
        .append("where gmsfhm='" + gmsfhm + "' ")
        .append("and jlbz='" + PublicConstant.JLBZ_ZX + "' ");
    //.append("and cxbz='" + PublicConstant.CXBZ_FCX + "' ");

    try {
      PojoInfo hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
      ryxxList = super.getObjectListByHql(strHQL.toString());
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return ryxxList;
  }

  /**
   * 通过公民身份号码得到人员信息记录数
   * @param gmsfhm
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  protected long countRYXX(String gmsfhm) throws ServiceException,
      DAOException {
    long count = 0;
    String strHQL = new String();

    if (gmsfhm == null) {
      return 0;
    }

    strHQL = "select count(*) from PoHJXX_CZRKJBXXB where gmsfhm='" + gmsfhm +
        "' and jlbz='" + PublicConstant.JLBZ_ZX + "' ";
    //and cxbz='" + PublicConstant.CXBZ_FCX + "' ";

    try {
      PojoInfo hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
      count = super.getCount(strHQL);
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return count;
  }

  /**
   * 通过公民身份号码得到重号人员信息
   * @param gmsfhm
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  protected List getCHRYXX(String gmsfhm) throws
      ServiceException,
      DAOException {

    StringBuffer strHQL = new StringBuffer();
    List ryxxList = null;

    strHQL.append("from PoHJXX_CZRKJBXXB ")
        .append("where gmsfhm='" + gmsfhm + "' ")
        .append("and jlbz='" + PublicConstant.JLBZ_ZX + "' ")
        .append("and ryzt='" + HjConstant.RYZT_ZC + "' ")
        .append("and cxbz='" + PublicConstant.CXBZ_FCX + "' ");

    try {
    	PojoInfo hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
      ryxxList = super.findAllByHQL(strHQL.toString());
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return ryxxList;
  }

  /**
   * 通过公民身份号码得到重号人员信息记录数
   * @param gmsfhm
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  protected long countCHRYXX(String gmsfhm) throws ServiceException,
      DAOException {
    long count = 0;
    String strHQL = new String();

    if (gmsfhm == null) {
      return 0;
    }
    strHQL = "select count(*) from PoHJXX_CZRKJBXXB where gmsfhm='" + gmsfhm +
        "' and jlbz='" + PublicConstant.JLBZ_ZX + "' and cxbz='" +
        PublicConstant.CXBZ_FCX + "' and ryzt='" + HjConstant.RYZT_ZC + "' ";

    try {
    	PojoInfo hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
      count = super.getCount(strHQL);
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return count;
  }

  /**
   * 分配公民身份号码
   * @param hjywid - 户籍业务ID
   * @param voGmsfhmfpsqxx - 公民身份号码分配申请信息
   * @return VoGmsfhmfpfhxx - 公民身份号码分配返回信息
   * @throws ServiceException
   * @throws DAOException
   */
  protected synchronized VoGmsfhmfpfhxx assignGMSFHM(Long hjywid,
      VoGmsfhmfpsqxx voGmsfhmfpsqxx, String psNow) throws
      ServiceException,
      DAOException {

    String errInfo;
    VoGmsfhmfpfhxx voGmsfhmfpfhxx = null;
    StringBuffer strHQL = new StringBuffer();
    List lnwsdList = null; //历年尾数段List
    int nWsdCount = 0;
    String now = psNow;
    if (psNow == null || "".equals(psNow)) {
      now = StringUtils.getServiceTime();

      //数据校验
    }
    if (hjywid == null || voGmsfhmfpsqxx == null || voGmsfhmfpsqxx.getCsrq() == null ||
        voGmsfhmfpsqxx.getPcs() == null ||
        voGmsfhmfpsqxx.getRyid() == null || voGmsfhmfpsqxx.getXb() == null ||
        voGmsfhmfpsqxx.getXzqh() == null) {
      return null;
    }

    try {
    	PojoInfo xt_lnwsdbDAO = DAOFactory.createXT_LNWSDBDAO();

      ///////////////////////////////////////////////////
      //得到该单位(出生日期)的尾数段分配信息
      //按乡镇街道分配尾数(2005/05/09 10:10:00 By MHB)
      strHQL.append("from PoXT_LNWSDB ")
          .append("where xzjd='" + voGmsfhmfpsqxx.getXzjd() + "' ")
          .append("and qyrq <='" + voGmsfhmfpsqxx.getCsrq() + "' ")
          .append("order by qyrq desc");
      lnwsdList = super.findAllByHQL(strHQL.toString());
      //如果按乡镇街道分配尾数没有设置则按派出所分配尾数
      if (lnwsdList == null || (lnwsdList != null && lnwsdList.size() <= 0)) {
        strHQL = new StringBuffer();
        strHQL.append("from PoXT_LNWSDB ")
            .append("where dwdm='" + voGmsfhmfpsqxx.getPcs() + "' ")
            .append("and qyrq <='" + voGmsfhmfpsqxx.getCsrq() + "' ")
            .append("order by qyrq desc");
        lnwsdList = super.findAllByHQL(strHQL.toString());
      }
      //有设置尾数
      if (lnwsdList != null && lnwsdList.size() > 0) {
        String qyrq = ( (PoXT_LNWSDB) lnwsdList.get(0)).getQyrq();
        nWsdCount = 1;
        //统计与第一个“启用日期”相同的个数
        for (int i = 1; i < lnwsdList.size(); i++) {
          PoXT_LNWSDB po = (PoXT_LNWSDB) lnwsdList.get(i);
          if (!qyrq.equals(po.getQyrq())) {
            break;
          }
          nWsdCount++;
        } //for
      }
      //没有设置尾数段
      else {
        errInfo = "该单位（" + voGmsfhmfpsqxx.getPcs() + "）出生日期为" +
            voGmsfhmfpsqxx.getCsrq() + "的身份号码历年尾数段没有设置，无法分配身份号码。";
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                   errInfo, null);
      }

      /////////////////////////////////////////////////////////////
      //分配身份号码
      for (int i = 0; i < nWsdCount; i++) {
        PoXT_LNWSDB po = (PoXT_LNWSDB) lnwsdList.get(i);
        voGmsfhmfpfhxx = assignGMSFHM(hjywid, po.getKsd().trim(),
                                      po.getJsd().trim(),
                                      po.getQhdm(), voGmsfhmfpsqxx, now);
        //分配成功
        if (voGmsfhmfpfhxx != null) {
          break;
        }
      }

      //尾数用完
      if (voGmsfhmfpfhxx == null) {
        errInfo = "该单位（" + voGmsfhmfpsqxx.getPcs() + "）出生日期为" +
            voGmsfhmfpsqxx.getCsrq() + "的身份号码历年尾数用完，无法分配身份号码。";
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                   errInfo, null);
      }
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return voGmsfhmfpfhxx;
  }

  /**
   * 根据详信息分配公民身份号码
   * @param hjywid - 户籍业务ID
   * @param strKsd - 开始段
   * @param strJsd - 结束段
   * @param sfhm6xzqh - 身份号码前的6位行政区划
   * @param voGmsfhmfpsqxx - 公民身份号码分配申请信息
   * @return VoGmsfhmfpfhxx - 公民身份号码分配返回信息(如果返回null则表示尾数段用光)
   */
  private VoGmsfhmfpfhxx assignGMSFHM(Long hjywid, String strKsd, String strJsd,
                                      String sfhm6xzqh,
                                      VoGmsfhmfpsqxx voGmsfhmfpsqxx,
                                      String psNow) throws
      ServiceException,
      DAOException {

    String errInfo;
    VoGmsfhmfpfhxx voGmsfhmfpfhxx = null;
    StringBuffer strHQL = new StringBuffer();
    List yfpdwsxxList = null; //已分配的尾数信息
    String now = psNow;
    if (psNow == null || "".equals(psNow)) {
      now = StringUtils.getServiceTime();

    }

    try {
    	PojoInfo hjyw_gmsfhmsxmfpxxbDAO = DAOFactory.
          createHJYW_GMSFHMSXMFPXXBDAO();
    	PojoInfo hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();

      //需要调用远程接口，比对重号
      List pzlist = super.findAllByHQL("from PoXT_XTKZCSB a where a.kzlb='10022'");
      PoXT_XTKZCSB xtpz = null;
      if(pzlist.size()>0)
        xtpz = (PoXT_XTKZCSB)pzlist.get(0);

      //strKsd补零(格式化成三位的,前加0)
      String strZero = new String();
      for (int i = 0; i < (3 - strKsd.length()); i++) {
        strZero = strZero + "0";
      }
      strKsd = strZero.concat(strKsd);

      //strJsd补零(格式化成三位的,前加0)
      strZero = new String();
      for (int i = 0; i < (3 - strJsd.length()); i++) {
        strZero = strZero + "0";
      }
      strJsd = strZero.concat(strJsd);

      //////////////////////////////////////////////////
      //得到身份号码前6位相同且同一天出生并且性别相同的已分配的尾数信息
      strHQL.append("from PoHJYW_GMSFHMSXMFPXXB ")
          .append("where gmsfhm like '" + sfhm6xzqh + voGmsfhmfpsqxx.getCsrq() +
                  "%' ")
          .append("and xb='" + voGmsfhmfpsqxx.getXb() + "' ")
          .append("and sxh >= '" + strKsd + "' and sxh <='" + strJsd + "' ")
          .append("order by sxh desc");
      yfpdwsxxList = super.getPageRecords(strHQL.toString(), new Object[]{}, 0, 1).getList();

      int nKsd = Integer.valueOf(strKsd).intValue();
      int nJsd = Integer.valueOf(strJsd).intValue();
      int nSXH = nKsd;

      //按最大值分配(2005/05/25 11:55:00 By MHB)
      boolean bYFPWS = false;
      if (yfpdwsxxList != null && yfpdwsxxList.size() > 0) {
        PoHJYW_GMSFHMSXMFPXXB po = (PoHJYW_GMSFHMSXMFPXXB) yfpdwsxxList.get(0);
        nSXH = Integer.parseInt(po.getSxh());
        bYFPWS = true;
      }

      //调整男女奇偶数
      //男
      if (voGmsfhmfpsqxx.getXb().equalsIgnoreCase(HjConstant.RYXB_NA)) {
        //取奇数
        if ( (nSXH % 2) == 0) {
          nSXH = nSXH + 1;
        }
        else if (bYFPWS) {
          nSXH += 2;
        }
      }
      //女
      else if (voGmsfhmfpsqxx.getXb().equalsIgnoreCase(HjConstant.RYXB_NV)) {
        //取偶数
        if ( (nSXH % 2) == 1) {
          nSXH = nSXH + 1;
        }
        else if (bYFPWS) {
          nSXH += 2;
        }
      }
      //性别不确定，无法分配身份号码
      else {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                   "性别错误，无法分配身份号码。", null);
      }

      //尾数段计算
      while (nSXH <= nJsd) {

        String strSXH = String.valueOf(nSXH);
        strZero = new String();
        for (int i = 0; i < (3 - strSXH.length()); i++) {
          strZero = strZero + "0";
        }
        //生成18位的公民身份号码
        StringBuffer sfhm17 = new StringBuffer();
        sfhm17.append(sfhm6xzqh)
            .append(voGmsfhmfpsqxx.getCsrq())
            .append(strZero)
            .append(strSXH);
        String sfhm18 = PID.IDConver(sfhm17.toString());
        //检查该公民身份号码是否是已分配的，如果是已分配的则保存尾数分配信息
        List ryxxList = this.getRYXX(sfhm18);
        if (ryxxList != null && ryxxList.size() > 0) {
          //保存尾数分配信息
          for (int i = 0; i < ryxxList.size(); i++) {
            PoHJXX_CZRKJBXXB poRxx = (PoHJXX_CZRKJBXXB) ryxxList.get(i);
            //处理重号只填其一的尾数(2005/05/24 17:00:00 By MHB)
            strHQL = new StringBuffer(512);
            strHQL.append(
                "select count(*) from PoHJYW_GMSFHMSXMFPXXB where gmsfhm='")
                .append(poRxx.getGmsfhm()).append("'");
            if (super.getCount(strHQL.toString()) > 0) {
              continue;
            }
            //保存尾数分配信息
            PoHJXX_MLPXXXXB poDxx = super.get(PoHJXX_MLPXXXXB.class, poRxx.getMlpnbid());
            PoHJYW_GMSFHMSXMFPXXB poFpxx = new PoHJYW_GMSFHMSXMFPXXB();
            poFpxx.setFpid( (Long) hjyw_gmsfhmsxmfpxxbDAO.getId());
            poFpxx.setRyid(poRxx.getRyid());
            poFpxx.setCsrq(poRxx.getCsrq());
            poFpxx.setXm(poRxx.getXm());
            poFpxx.setGmsfhm(poRxx.getGmsfhm());
            poFpxx.setXb(voGmsfhmfpsqxx.getXb());
            poFpxx.setDwdm(poDxx.getPcs());
            poFpxx.setSxh(strZero + strSXH);
            poFpxx.setCzlb(HjConstant.GMSFHMFPWS_CZLB_QT);
            poFpxx.setHjywid(hjywid);
            super.create(poFpxx);
          }
        }
        //分配成功
        else {
          String result = "";
          BufferedReader in = null;

          if(xtpz!=null){
            String url = xtpz.getBz();
            if(url.indexOf("?")<0)
              url += "?";

            url += "sfzh=" + sfhm18 + "&optype=count";
            try {
              URL realUrl = new URL(url);
              // 打开和URL之间的连接
              URLConnection connection = realUrl.openConnection();
              // 设置通用的请求属性
              connection.setRequestProperty("accept", "*/*");
              connection.setRequestProperty("connection", "Keep-Alive");
              connection.setRequestProperty("user-agent",
                  "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
              // 建立实际的连接
              connection.connect();
              // 定义 BufferedReader输入流来读取URL的响应
              in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
              String line;
              while ( (line = in.readLine()) != null) {
                result += line;
              }

              JSONObject jsonObject = JSONObject.parseObject(result.trim());
              String success = jsonObject.getString("success");
              String count = jsonObject.getString("count");
              String message = jsonObject.getString("message");
              if (success.equalsIgnoreCase("true")) {
                if (!count.equals("0")) {
                  //取下一个顺序号
                  nSXH += 2;
                  continue;
                }
              }
              else {
                throw new java.lang.RuntimeException(message);
              }
            }
            catch (Exception e) {
              //接口出错的实话，依然要能够继续业务
              ;//throw new java.lang.RuntimeException("调用重号比对接口" + url + "发生异常:" + e.getMessage());
            }
            finally {
              try {
                if (in != null) {
                  in.close();
                }
              }
              catch (Exception e2) {
                e2.printStackTrace();
              }
            }
          }

          //保存尾数分配信息
          PoHJYW_GMSFHMSXMFPXXB poFpxx = new PoHJYW_GMSFHMSXMFPXXB();
          poFpxx.setFpid( (Long) hjyw_gmsfhmsxmfpxxbDAO.getId());
          poFpxx.setRyid(voGmsfhmfpsqxx.getRyid());
          poFpxx.setCsrq(voGmsfhmfpsqxx.getCsrq());
          poFpxx.setXm(voGmsfhmfpsqxx.getXm());
          poFpxx.setGmsfhm(sfhm18);
          poFpxx.setXb(voGmsfhmfpsqxx.getXb());
          poFpxx.setDwdm(voGmsfhmfpsqxx.getPcs());
          poFpxx.setSxh(strZero + strSXH);
          poFpxx.setCzlb(HjConstant.GMSFHMFPWS_CZLB_FP);
          poFpxx.setHjywid(hjywid);
          super.create(poFpxx);

          voGmsfhmfpfhxx = new VoGmsfhmfpfhxx();
          voGmsfhmfpfhxx.setFpid(poFpxx.getFpid());
          voGmsfhmfpfhxx.setGmsfhm(poFpxx.getGmsfhm());

          break;
        } //分配成功

        //取下一个顺序号
        nSXH += 2;
      }

    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return voGmsfhmfpfhxx;
  }

  /**
   * 分配户号
   * @param dwdm - 单位代码(派出所)
   * @return String
   */
  protected synchronized static String assignHH(String dwdm) throws
      ServiceException,
      DAOException {

    String strHQL = new String();
    String strHhxlid = null;
    PoXT_HHXLB poXT_HHXLB = null;
    long hhxlid = 0;

    if (dwdm == null || (dwdm != null && dwdm.length() != 9)) {
      return null;
    }

    strHQL = "from PoXT_HHXLB where dwdm='" + dwdm +
        "' order by hhxlid desc";

    try {
      PojoInfo xt_hhxtlbDAO = DAOFactory.createXT_HHXLBDAO();

      //得到该派出所户号分配信息
      List lst = SpringContextHolder.getCommonService().queryLock(strHQL);
      if (lst != null && lst.size() > 0) {
        poXT_HHXLB = (PoXT_HHXLB) lst.get(0);
        //xt_hhxtlbDAO.refresh(poXT_HHXLB, LockMode.UPGRADE); //lock
      }
      //生成新的户号序列ID
      hhxlid = 0;
      if (poXT_HHXLB != null && poXT_HHXLB.getHhxlid() != null &&
          poXT_HHXLB.getHhxlid().length() == 9) {
        hhxlid = Long.valueOf(poXT_HHXLB.getHhxlid().substring(3, 9)).
            longValue();
        hhxlid++;
      }
      String strZero = new String();
      strHhxlid = new String();
      strHhxlid = String.valueOf(hhxlid);
      for (int i = 0; i < 6 - strHhxlid.length(); i++) {
        strZero = strZero + "0";
      }
      strHhxlid = strZero + strHhxlid;
      strHhxlid = dwdm.substring(6, 9) + strHhxlid;
      //新增记录
      if (poXT_HHXLB == null) {
        poXT_HHXLB = new PoXT_HHXLB();
        poXT_HHXLB.setXlid( (Long) xt_hhxtlbDAO.getId());
        poXT_HHXLB.setDwdm(dwdm);
        poXT_HHXLB.setHhxlid(strHhxlid);
        SpringContextHolder.getCommonService().insertObject(poXT_HHXLB);
      }
      //修改记录
      else {
        poXT_HHXLB.setHhxlid(strHhxlid);
        SpringContextHolder.getCommonService().updateObject(poXT_HHXLB);
      }

    }
    catch (DAOException ex) {
      strHhxlid = null;
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return strHhxlid;
  }

  /**
   * 校验数值
   * @param str
   * @return
   */
  public static boolean isNumber(String str) {
    boolean bRET = true;

    try {
      Long number = Long.valueOf(str);
    }
    catch (NumberFormatException ex) {
      bRET = false;
    }

    return bRET;
  }

  /**
   * 产生地信息排序号
   * @param mlph
   * @return String
   */
  public static String generatePXH(String mlph, String mlxz) {
    String gen = "";

    if (mlph != null) {
      gen = gen + mlph;
    }

    if (mlxz != null) {
      gen = gen + mlxz;
    }

    //将全角数值替换成半角数值
    if (gen != null) {
      gen = StringUtils.replace(gen, "一", "1");
      gen = StringUtils.replace(gen, "二", "2");
      gen = StringUtils.replace(gen, "三", "3");
      gen = StringUtils.replace(gen, "四", "4");
      gen = StringUtils.replace(gen, "五", "5");
      gen = StringUtils.replace(gen, "六", "6");
      gen = StringUtils.replace(gen, "七", "7");
      gen = StringUtils.replace(gen, "八", "8");
      gen = StringUtils.replace(gen, "九", "9");
      gen = StringUtils.replace(gen, "零", "0");

      gen = StringUtils.replace(gen, "１", "1");
      gen = StringUtils.replace(gen, "２", "2");
      gen = StringUtils.replace(gen, "３", "3");
      gen = StringUtils.replace(gen, "４", "4");
      gen = StringUtils.replace(gen, "５", "5");
      gen = StringUtils.replace(gen, "６", "6");
      gen = StringUtils.replace(gen, "７", "7");
      gen = StringUtils.replace(gen, "８", "8");
      gen = StringUtils.replace(gen, "９", "9");
      gen = StringUtils.replace(gen, "０", "0");
    }

    //拆分数值、字符字符串
    List strList = new ArrayList();
    String strString = "";
    String strNumber = "";
    for (int i = 0; i < gen.length(); i++) {
      String str = gen.substring(i, i + 1);
      if (isNumber(str)) {
        strNumber += str;
        if (strString.length() > 0) {
          strList.add(strString);
          strString = "";
        }
      }
      else {
        strString += str;
        if (strNumber.length() > 0) {
          strList.add(strNumber);
          strNumber = "";
        }
      }
    }
    if (strNumber.length() > 0) {
      strList.add(strNumber);
    }
    if (strString.length() > 0) {
      strList.add(strString);
    }

    //数值字符串补'0'
    for (int i = 0; i < strList.size(); i++) {
      String str = (String) strList.get(i);
      if (isNumber(str)) {
        StringBuffer zeroStr = new StringBuffer();
        for (int k = 0; k < (DzConstant.PXH_NUMBER_LENGTH - str.length()); k++) {
          zeroStr.append(DzConstant.PXH_FULL_CHARACTER);
        }
        zeroStr.append(str);
        strList.set(i, zeroStr.toString());
      }
    }

    //组合字符串
    StringBuffer genStr = new StringBuffer();
    for (int i = 0; i < strList.size(); i++) {
      genStr.append(strList.get(i));
    }

    return genStr.toString();
  }

  /**
   * 注销户
   * @param hjywid - 户籍业务ID
   * @param voZxhxx - 注销户信息
   * @return 注销的户(hhnbid)List
   * @throws ServiceException
   * @throws DAOException
   */
  protected List logoutH(Long hjywid, VoZxhxx voZxhxx[], String bdfw,
                         String bdyy, String ywnr, String czsj) throws
      ServiceException,
      DAOException {

    List hhnbidList = new ArrayList();
    StringBuffer strHQL = new StringBuffer();
    Map zxhxxMap = new HashMap();

    if (voZxhxx == null) {
      return null;
    }

    //得到所需要判断注销的户
    for (int i = 0; i < voZxhxx.length; i++) {
      zxhxxMap.put(voZxhxx[i].getOld_hhnbid(), "");
    }
    for (int i = 0; i < voZxhxx.length; i++) {
      zxhxxMap.remove(voZxhxx[i].getNew_hhnbid());
    }

    if (zxhxxMap.size() <= 0) {
      return null;
    }

    try {
      PojoInfo hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
      PojoInfo hjxx_hxxbDAO = DAOFactory.createHJXX_HXXBDAO();
      PojoInfo hjtj_hbdxxbDAO = DAOFactory.createHJTJ_HBDXXBDAO();

      //判断户是否可以注销
      for (Iterator iter = zxhxxMap.keySet().iterator(); iter.hasNext(); ) {
        Long hhnbid = (Long) iter.next();

        strHQL.append(
            "select count (*) from PoHJXX_CZRKJBXXB as HJXX_CZRKJBXXB ")
            .append("where hhnbid=" + hhnbid.toString() + " ")
            .append("and ryzt ='" + HjConstant.RYZT_ZC + "' ")
            .append("and jlbz='" + PublicConstant.JLBZ_ZX + "' ")
            .append("and cxbz='" + PublicConstant.CXBZ_FCX + "'");

        //空户,将户号状态置为注销状态
        if (super.getCount(strHQL.toString()) <= 0) {
          PoHJXX_HXXB poHxx = super.get(PoHJXX_HXXB.class, hhnbid);
          if (poHxx == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "找不到要注销的户信息，系统逻辑错误。", null);
          }
          //校验户信息的时效性
          this.checkHXX(poHxx, hjxx_hxxbDAO, "注销户");
          //将记录设为历史记录
          poHxx.setChlb(HjConstant.CHLB_YWZX);
          poHxx.setCchjywid(hjywid);
          poHxx.setJssj(czsj);
          poHxx.setJlbz(PublicConstant.JLBZ_LS);
          super.update(poHxx);
          //生成户注销记录
          PoHJXX_HXXB poHxxNew = new PoHJXX_HXXB();
          BeanUtils.copyProperties(poHxxNew, poHxx);
          poHxxNew.setHhnbid( (Long) hjxx_hxxbDAO.getId());
          poHxxNew.setChsj(czsj);
          poHxxNew.setHhzt(HjConstant.HHZT_ZX);
          poHxxNew.setBdfw(bdfw);
          poHxxNew.setBdyy(bdyy);
          poHxxNew.setQysj(czsj);
          poHxxNew.setJssj(null);
          poHxxNew.setChlb(null);
          poHxxNew.setJhlb(HjConstant.JHLB_YWSC);
          poHxxNew.setCchjywid(null);
          poHxxNew.setCjhjywid(hjywid);
          poHxxNew.setJlbz(PublicConstant.JLBZ_ZX);
          super.create(poHxxNew);

          //保存户变动信息
          PoHJTJ_HBDXXB poHbdxx = new PoHJTJ_HBDXXB();
          poHbdxx.setHbdid( (Long) hjtj_hbdxxbDAO.getId());
          poHbdxx.setBdfw(bdfw);
          poHbdxx.setBdsj(czsj);
          poHbdxx.setBdyy(bdyy);
          poHbdxx.setHhnbid(poHxxNew.getHhnbid());
          poHbdxx.setHzjs(new Long( -1));
          poHbdxx.setYwnr(ywnr);
          poHbdxx.setCxbz(PublicConstant.CXBZ_FCX);
          poHbdxx.setCxhjywid(null);
          poHbdxx.setHjywid(hjywid);
          super.create(poHbdxx);

          //生成返回信息
          hhnbidList.add(hhnbid);
        }
      } //for (Iterator iter = zxhxxMap.keySet().iterator(); iter.hasNext(); )

    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return hhnbidList != null && hhnbidList.size() == 0 ? null : hhnbidList;
  }

  /**
   * 通过地址信息得到地信息
   * @param voDzxx
   * @return
   */
  protected PoHJXX_MLPXXXXB getDXX(VoDzzjxx voDzxx) throws
      ServiceException,
      DAOException {

    StringBuffer strHQL = new StringBuffer();
    PoHJXX_MLPXXXXB poMlpxxxx = null;

    try {
      PojoInfo hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();

      strHQL.append("from PoHJXX_MLPXXXXB ")
          .append("where ssxq='" + voDzxx.getSsxq() + "' ")
          .append("and jlx='" + voDzxx.getJlx() + "' ")
          .append("and pcs='" + voDzxx.getPcs() + "' ")
          .append(voDzxx.getZrq() != null && voDzxx.getZrq().length() > 0 ?
                  "and zrq ='" + voDzxx.getZrq() + "' " :
                  "and (zrq ='' or zrq is null) ")
          .append("and jcwh='" + voDzxx.getJcwh() + "' ")
          .append("and jlbz='" + PublicConstant.JLBZ_ZX + "' ")
          .append("and mlpzt='" + DzConstant.MLPZT_ZC + "' "); //这行增加闵红斌(2005/04/19 10:20:00)，作用是只引用正常的地信息，例如：在立户时，如果立户所在的地信息与数据库中一条已经注销的地信息相同也不引用，重新创建一条地信息。

      //(mlxz=null && mlph=null) || (mlxz='' && mlph='')
      if ( (voDzxx.getMlxz() == null && voDzxx.getMlph() == null) ||
          (voDzxx.getMlxz() != null && voDzxx.getMlph() != null &&
           voDzxx.getMlxz().length() == 0 && voDzxx.getMlph().length() == 0)) {
        strHQL.append("and (mlph='' and mlxz ='' or mlph is null and mlxz is null or mlph='' and mlxz is null  or mlph is null and mlxz ='') ");
      }
      else {
        //modi by hh 20050906  地址判断不能用MLPH和MLXZ合并查询
        /*     String mlxz = voDzxx.getMlxz() != null &&
                 voDzxx.getMlxz().length() > 0 ? voDzxx.getMlxz() : "";
             String mlph = voDzxx.getMlph() != null &&
                 voDzxx.getMlph().length() > 0 ? voDzxx.getMlph() : "";
             strHQL.append("and (mlph || mlxz) ='" + mlph + mlxz + "' ");
         */
        strHQL.append(voDzxx.getMlph() != null && voDzxx.getMlph().length() > 0 ?
                      " and mlph='" + voDzxx.getMlph() + "'" :
                      " and (mlph is null or mlph='') ");
        strHQL.append(voDzxx.getMlxz() != null && voDzxx.getMlxz().length() > 0 ?
                      " and mlxz='" + voDzxx.getMlxz() + "'" :
                      " and (mlxz is null or mlxz='') ");

      }

      List list = super.getPageRecords(strHQL.toString(),new Object[]{}, 0, 1).getList();
      if (list != null && list.size() > 0) {
        poMlpxxxx = (PoHJXX_MLPXXXXB) list.get(0);
      }
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return poMlpxxxx;
  }

  /**
   * 立户(创建户地)
   * @param hjywid
   * @param voLhhdxx
   * @return VoLhhdfhxx
   */
  protected VoLhhdfhxx createH(Long hjywid, VoLhhdxx voLhhdxx, String bdfw,
                               String bdyy, String ywnr, String czsj) throws
      ServiceException,
      DAOException {

    VoLhhdfhxx voLhhdfhxx = null;
    StringBuffer strHQL = new StringBuffer();

    //数据校验
    if (voLhhdxx == null) {
      return null;
    }
    if (voLhhdxx.getSsxq() == null || voLhhdxx.getJlx() == null ||
        voLhhdxx.getPcs() == null || //voLhhdxx.getZrq() == null ||
        voLhhdxx.getJcwh() == null) {
      return null;
    }
    //add by hh 20060112 立户时判断派出所代码的后3位是否为0
    //为0则不允许立户
    if (voLhhdxx.getPcs().length() != 9 ||
        "000".equals(voLhhdxx.getPcs().substring(6, 9))) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                 "单位代码[" + voLhhdxx.getPcs() + "]不正确,立户无法完成。", null);
    }
    try {
      PojoInfo hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();
      PojoInfo hjxx_hxxbDAO = DAOFactory.createHJXX_HXXBDAO();
      PojoInfo hjtj_hbdxxbDAO = DAOFactory.createHJTJ_HBDXXBDAO();
      PojoInfo xt_jwhxxbDAO = DAOFactory.createXT_JWHXXBDAO();

      PoHJXX_MLPXXXXB poMlpxxxx = null;
      PoHJXX_HXXB poHxx = new PoHJXX_HXXB();

      /////////////////////////////////////////////////////////////////////////////
      //地信息判断，如果该地不存在则引用，否则创建。
      VoDzzjxx voDzxx = new VoDzzjxx();
      BeanUtils.copyProperties(voDzxx, voLhhdxx);
      poMlpxxxx = this.getDXX(voDzxx);
      if (poMlpxxxx == null) {
        poMlpxxxx = new PoHJXX_MLPXXXXB();
        poMlpxxxx.setMlpnbid( (Long) hjxx_mlpxxxxbDAO.getId());
        poMlpxxxx.setMlpid(poMlpxxxx.getMlpnbid());
        poMlpxxxx.setSsxq(voLhhdxx.getSsxq());
        poMlpxxxx.setJlx(voLhhdxx.getJlx());
        poMlpxxxx.setMlph(voLhhdxx.getMlph());
        poMlpxxxx.setMlxz(voLhhdxx.getMlxz());
        poMlpxxxx.setPcs(voLhhdxx.getPcs());
        poMlpxxxx.setZrq(voLhhdxx.getZrq());
        poMlpxxxx.setJcwh(voLhhdxx.getJcwh());
        poMlpxxxx.setBzdz(voLhhdxx.getBzdz());
        poMlpxxxx.setBzdzid(voLhhdxx.getBzdzid());
        poMlpxxxx.setBzdzx(voLhhdxx.getBzdzx());
        poMlpxxxx.setBzdzy(voLhhdxx.getBzdzy());
        poMlpxxxx.setBzdzst(voLhhdxx.getBzdzst());

        //Begin_修改于闵红斌(2004/11/15 17:25:00)
        /*
                 poMlpxxxx.setXzjd(voLhhdxx.getJcwh() != null &&
                          voLhhdxx.getJcwh().length() > 9 ?
                          voLhhdxx.getJcwh().substring(0, 9) : null);
         */
        PoXT_JWHXXB poXT_JWHXXB = (PoXT_JWHXXB) super.get(PoXT_JWHXXB.class, voLhhdxx.getJcwh()); //通过居委会代码得到乡镇街道代码
        if (poXT_JWHXXB == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                     "通过居委会代码无法得到乡镇街道代码,立户无法完成。", null);
        }
        poMlpxxxx.setXzjd(poXT_JWHXXB.getXzjddm());
        //End_修改于闵红斌(2004/11/15 17:25:00)
        poMlpxxxx.setPxh(this.generatePXH(voLhhdxx.getMlph(), voLhhdxx.getMlxz()));
        poMlpxxxx.setMlpzt(DzConstant.MLPZT_ZC);
        poMlpxxxx.setQysj(czsj);
        poMlpxxxx.setJssj(null);
        poMlpxxxx.setJdsj(czsj);
        poMlpxxxx.setJdlb(HjConstant.JDLB_YWJL);
        poMlpxxxx.setCchjywid(null);
        poMlpxxxx.setCjhjywid(hjywid);
        poMlpxxxx.setJlbz(PublicConstant.JLBZ_ZX);

        super.create(poMlpxxxx);
      }else{
        if(voLhhdxx.getBzdz()!=null && !voLhhdxx.getBzdz().equals("")){
          poMlpxxxx.setBzdz(voLhhdxx.getBzdz());
          poMlpxxxx.setBzdzid(voLhhdxx.getBzdzid());
          poMlpxxxx.setBzdzx(voLhhdxx.getBzdzx());
          poMlpxxxx.setBzdzy(voLhhdxx.getBzdzy());
          poMlpxxxx.setBzdzst(voLhhdxx.getBzdzst());

          super.update(poMlpxxxx);
        }
      }

      //判断门楼牌状态(注销代码2005/05/25 10:40:00)
      //if (!DzConstant.MLPZT_ZC.equals(poMlpxxxx.getMlpzt())) {
      //  throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
      //                             "门楼牌状态为非正常状态，建户无法完成。", null);
      //}

      //////////////////////////////////////////////////////////////////
      //保存户信息
      poHxx.setHhnbid( (Long) hjxx_hxxbDAO.getId());
      poHxx.setHhid(poHxx.getHhnbid());
      poHxx.setMlpnbid(poMlpxxxx.getMlpnbid());
      poHxx.setHh(this.assignHH(poMlpxxxx.getPcs()));
      poHxx.setHlx(voLhhdxx.getHlx());
      poHxx.setHhzt(HjConstant.HHZT_ZC);
      poHxx.setBdfw(bdfw);
      poHxx.setBdyy(bdyy);
      poHxx.setQysj(czsj);
      poHxx.setJssj(null);
      poHxx.setJhsj(czsj);
      poHxx.setJhlb(HjConstant.JHLB_YWSC);
      poHxx.setCchjywid(null);
      poHxx.setCjhjywid(hjywid);
      poHxx.setJlbz(PublicConstant.JLBZ_ZX);
      super.create(poHxx);

      //////////////////////////////////////////////////////
      //保存户变动信息
      PoHJTJ_HBDXXB poHbdxx = new PoHJTJ_HBDXXB();
      poHbdxx.setHbdid( (Long) hjtj_hbdxxbDAO.getId());
      poHbdxx.setBdfw(bdfw);
      poHbdxx.setBdsj(czsj);
      poHbdxx.setBdyy(bdyy);
      poHbdxx.setHhnbid(poHxx.getHhnbid());
      poHbdxx.setHzjs(new Long(1));
      poHbdxx.setYwnr(ywnr);
      poHbdxx.setCxbz(PublicConstant.CXBZ_FCX);
      poHbdxx.setCxhjywid(null);
      poHbdxx.setHjywid(hjywid);
      super.create(poHbdxx);

      ///////////////////////////////////////////////////
      //生成返回参数
      voLhhdfhxx = new VoLhhdfhxx();
      voLhhdfhxx.setHhnbid(poHxx.getHhnbid());
      voLhhdfhxx.setMlpnbid(poMlpxxxx.getMlpnbid());

      voLhhdfhxx.setPoHJXX_HXXB(poHxx);
      voLhhdfhxx.setPoHJXX_MLPXXXXB(poMlpxxxx);
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return voLhhdfhxx;
  }

  /**
   * 调整户成员关系
   * @param hjywid - 户籍业务ID
   * @param voSbjbxx - 申报基本信息
   * @param voHcygxtzxx - 户成员关系调整信息
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  protected VoHcygxtzfhxx[] adjustHCYGX(Long hjywid, VoSbjbxx voSbjbxx,
                                        VoHcygxtzxxEx voHcygxtzxxEx[],
                                        String psNow) throws
      ServiceException,
      DAOException {

    VoHcygxtzfhxx voHcygxtzfhxx[] = null;
    String now = psNow;
    if (psNow == null || "".equals(psNow)) {
      now = StringUtils.getServiceTime();

    }
    if (hjywid == null || voHcygxtzxxEx == null ||
        (voHcygxtzxxEx != null && voHcygxtzxxEx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo hjxx_czrkjbxxbDAO = DAOFactory.
          createHJXX_CZRKJBXXBDAO();
      PojoInfo hjyw_hcybdxxbDAO = DAOFactory.createHJYW_HCYBDXXBDAO();
      voHcygxtzfhxx = new VoHcygxtzfhxx[voHcygxtzxxEx.length];

      for (int i = 0; i < voHcygxtzxxEx.length; i++) {
        Long hhnbid = null;
        Long ryid = null;
        Long old_rynbid = null;
        Long new_rynbid = null;
        String oldYhzgx = null;
        String gmsfhm = null;
        String xm = null;
        //是否需要修改常住人口信息表中的对应记录(0-不修改/1-修改)
        if (voHcygxtzxxEx[i].getFlag() == 1) {
          if (voHcygxtzxxEx[i].getRynbid() == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                       "人员内部ID为空，户成员关系调整无法完成。", null);
          }
          //得到人员信息
          PoHJXX_CZRKJBXXB poRyxx = super.get(PoHJXX_CZRKJBXXB.class, voHcygxtzxxEx[i].getRynbid());
          //校验人信息的时效性
          this.checkRXX(poRyxx, hjxx_czrkjbxxbDAO, "户成员关系调整业务");
          //将原记录置为历史记录
          poRyxx.setJssj(now);
          poRyxx.setJlbz(PublicConstant.JLBZ_LS);
          super.update(poRyxx);
          //得到原与户主关系
          oldYhzgx = poRyxx.getYhzgx();
          //生成新记录
          PoHJXX_CZRKJBXXB poRyxxNew = new PoHJXX_CZRKJBXXB();

            BeanUtils.copyProperties(poRyxxNew, poRyxx);

          poRyxxNew.setRynbid( (Long) hjxx_czrkjbxxbDAO.getId());
          poRyxxNew.setYhzgx(voHcygxtzxxEx[i].getYhzgx()); //修改关系
          poRyxxNew.setQysj(now);
          poRyxxNew.setJssj(null);
          poRyxxNew.setXxqysj(now);//add by hb 20061027
          poRyxxNew.setJlbz(PublicConstant.JLBZ_ZX);
          super.create(poRyxxNew);

          hhnbid = poRyxx.getHhnbid();
          old_rynbid = poRyxx.getRynbid();
          new_rynbid = poRyxxNew.getRynbid();
          xm = poRyxxNew.getXm();
          gmsfhm = poRyxxNew.getGmsfhm();
          ryid = poRyxxNew.getRyid();
        }
        else {
          hhnbid = voHcygxtzxxEx[i].getHhnbid();
          old_rynbid = voHcygxtzxxEx[i].getRynbid();
          new_rynbid = voHcygxtzxxEx[i].getNew_rynbid();
          oldYhzgx = voHcygxtzxxEx[i].getOld_yhzgx();
          xm = voHcygxtzxxEx[i].getXm();
          gmsfhm = voHcygxtzxxEx[i].getGmsfhm();

          //ryid = voHcygxtzxxEx[i].getRyid();
          ryid = voHcygxtzxxEx[i].getRyid();
        }

        //add hb 20061027 查询最新人信息
        PoHJXX_CZRKJBXXB poRyxxZx = super.get(PoHJXX_CZRKJBXXB.class,
            new_rynbid);
        //add hb 20061204 如果是新建RYNBID会查不到人，因此用老的RYNBID查询
        if (poRyxxZx == null) {
          poRyxxZx = super.get(PoHJXX_CZRKJBXXB.class, old_rynbid);
        }

        //生成户成员变动信息
        PoHJYW_HCYBDXXB poHcybdxx = new PoHJYW_HCYBDXXB();
        if (voSbjbxx != null) {
          BeanUtils.copyProperties(poHcybdxx, voSbjbxx);
        }
        poHcybdxx.setCxbz(PublicConstant.CXBZ_FCX);
        poHcybdxx.setHcybdid( (Long) hjyw_hcybdxxbDAO.getId());
        poHcybdxx.setRynbid(new_rynbid);
        poHcybdxx.setRyid(ryid);
        poHcybdxx.setYgx(oldYhzgx);
        poHcybdxx.setXgx(voHcygxtzxxEx[i].getYhzgx());
        poHcybdxx.setHcybdlx(voHcygxtzxxEx[i].getHcybdlx());
        poHcybdxx.setHcybdlb(voHcygxtzxxEx[i].getHcybdlb());
        poHcybdxx.setHcybdrq(now.substring(0, 8));
        poHcybdxx.setHjywid(hjywid);
        poHcybdxx.setSldw(this.getUser().getDwdm());
        poHcybdxx.setSlrid(this.getUser().getYhid());
        poHcybdxx.setSlsj(now);
        //户成员变动信息表中增加人、地信息
        poHcybdxx.setGmsfhm(gmsfhm);
        poHcybdxx.setXm(xm);
        poHcybdxx.setXb(poRyxxZx.getXb());
        poHcybdxx.setMz(poRyxxZx.getMz());
        poHcybdxx.setCsrq(poRyxxZx.getCsrq());
        poHcybdxx.setCssj(poRyxxZx.getCssj());
        poHcybdxx.setCsdssxq(poRyxxZx.getCsdssxq());
        poHcybdxx.setZpid(poRyxxZx.getZpid());
        poHcybdxx.setMlpid(poRyxxZx.getMlpid());
        poHcybdxx.setSsxq(poRyxxZx.getSsxq());
        poHcybdxx.setJlx(poRyxxZx.getJlx());
        poHcybdxx.setMlph(poRyxxZx.getMlph());
        poHcybdxx.setMlxz(poRyxxZx.getMlxz());
        poHcybdxx.setPcs(poRyxxZx.getPcs());
        poHcybdxx.setZrq(poRyxxZx.getZrq());
        poHcybdxx.setXzjd(poRyxxZx.getXzjd());
        poHcybdxx.setJcwh(poRyxxZx.getJcwh());
        poHcybdxx.setPxh(poRyxxZx.getPxh());
        poHcybdxx.setMlpid(poRyxxZx.getMlpid());
        poHcybdxx.setHhid(poRyxxZx.getHhid());
        poHcybdxx.setHhnbid(poRyxxZx.getHhnbid());
        poHcybdxx.setHh(poRyxxZx.getHh());

        //lzh add here 20070214 添加户类型赋值
        poHcybdxx.setHlx(poRyxxZx.getHlx());

        super.create(poHcybdxx);

        //生成户成员关系调整返回信息
        voHcygxtzfhxx[i] = new VoHcygxtzfhxx();
        voHcygxtzfhxx[i].setHhnbid(hhnbid);
        voHcygxtzfhxx[i].setRynbid(new_rynbid);
        voHcygxtzfhxx[i].setRyid(ryid);
        voHcygxtzfhxx[i].setXm(xm);
        voHcygxtzfhxx[i].setGmsfhm(gmsfhm);
        voHcygxtzfhxx[i].setHcybdid(poHcybdxx.getHcybdid());
        voHcygxtzfhxx[i].setOld_rynbid(old_rynbid);
      }
    }
    catch (DAOException ex) {
      voHcygxtzfhxx = null;
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return voHcygxtzfhxx;
  }

  /**
   * 保存重号信息
   * @param hjywid - 户籍业务ID
   * @param chxxList - 重号信息
   * @throws ServiceException
   * @throws DAOException
   */
  protected void saveCHXX(Long hjywid, List chxxList, String psNow) throws
      ServiceException,
      DAOException {

    PoHJYW_CHCLXXB poChxx = null;
    String strHQL = null;
    String now = psNow;
    if (psNow == null || "".equals(psNow)) {
      now = StringUtils.getServiceTime();

    }
    if (chxxList == null || (chxxList != null && chxxList.size() <= 0)) {
      return;
    }

    try {
    	PojoInfo hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
    	PojoInfo hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();
    	PojoInfo hjyw_chclxxbDAO = DAOFactory.createHJYW_CHCLXXBDAO();

      //保存重号信息
      for (int i = 0; i < chxxList.size(); i++) {
        VoChxx voChxx = (VoChxx) chxxList.get(i);
        //得到重号的人员信息
        strHQL = "from PoHJXX_CZRKJBXXB where jlbz='" + PublicConstant.JLBZ_ZX +
            "' and cxbz='" + PublicConstant.CXBZ_FCX + "' and ryid=" +
            voChxx.getBchryid();
        List chryxxList = super.findAllByHQL(strHQL);
        PoHJXX_CZRKJBXXB poChryxx = null;
        if (chryxxList != null && chryxxList.size() > 0) {
          poChryxx = (PoHJXX_CZRKJBXXB) chryxxList.get(0);
        }
        if (poChryxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到重号人员信息，重号保存失败。", null);
        }
        //得到重号的人员所在的地信息
        PoHJXX_MLPXXXXB poChmlpxx = super.get(PoHJXX_MLPXXXXB.class,
            poChryxx.getMlpnbid());
        if (poChmlpxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到重号人员所在的地信息，重号保存失败。", null);
        }
        //生成重号人员与被重号人员对应的重号信息并保存
        List bchryxxList = this.getCHRYXX(voChxx.getChsfhm());
        if (bchryxxList != null && bchryxxList.size() > 0) {
          for (int c = 0; c < bchryxxList.size(); c++) {
            //得到被重号的人员信息
            PoHJXX_CZRKJBXXB poBchryxx = (PoHJXX_CZRKJBXXB) bchryxxList.get(c);
            //排除跟自身记录
            if (poChryxx.getRyid().equals(poBchryxx.getRyid())) {
              continue;
            }
            //得到被重号人员的地信息
            PoHJXX_MLPXXXXB poBchmlpxx = super.get(PoHJXX_MLPXXXXB.class, poBchryxx.getMlpnbid());
            if (poBchmlpxx == null) {
              throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                         "找不到被重号人员所在的地信息，重号保存失败。", null);
            }
            //判断记录是否存在
            strHQL = "select count(*) from PoHJYW_CHCLXXB where clfs='" +
                HjConstant.CHCLFS_WCL + "' and chsfhm='" + poChryxx.getGmsfhm() +
                "' and ryid=" + poChryxx.getRyid().toString() + " and bchryid=" +
                poBchryxx.getRyid().toString() + " and cxbz='" +
                PublicConstant.CXBZ_FCX + "' ";
            if (super.getCount(strHQL) <= 0) {
              //重号人 - 被重号人
              PoHJYW_CHCLXXB poChrclxx = new PoHJYW_CHCLXXB();
              poChrclxx.setChid( (Long) hjyw_chclxxbDAO.getId());
              poChrclxx.setCxbz(PublicConstant.CXBZ_FCX);
              poChrclxx.setRyid(poChryxx.getRyid());
              poChrclxx.setChsfhm(poChryxx.getGmsfhm());
              poChrclxx.setBchryid(poBchryxx.getRyid());
              poChrclxx.setBchrxm(poBchryxx.getXm());
              poChrclxx.setBchrzz(this.generateZZ(poBchmlpxx,
                                                  PublicConstant.XTKZCS_DZPZFS,
                                                  PublicConstant.
                                                  XTKZCS_DZPZFS_BCHRZZ));
              poChrclxx.setBchrszpcs(poBchmlpxx.getPcs());
              poChrclxx.setClfs(HjConstant.CHCLFS_WCL);
              poChrclxx.setHjywid(hjywid);
              super.create(poChrclxx);
            }
            //判断记录是否存在
            strHQL = "select count(*) from PoHJYW_CHCLXXB where clfs='" +
                HjConstant.CHCLFS_WCL + "' and chsfhm='" + poChryxx.getGmsfhm() +
                "' and bchryid=" + poChryxx.getRyid().toString() + " and ryid=" +
                poBchryxx.getRyid().toString() + " and cxbz='" +
                PublicConstant.CXBZ_FCX + "' ";
            if (super.getCount(strHQL) <= 0) {
              //被重号人 - 重号人
              PoHJYW_CHCLXXB poBchrclxx = new PoHJYW_CHCLXXB();
              poBchrclxx.setChid( (Long) hjyw_chclxxbDAO.getId());
              poBchrclxx.setCxbz(PublicConstant.CXBZ_FCX);
              poBchrclxx.setRyid(poBchryxx.getRyid());
              poBchrclxx.setChsfhm(poBchryxx.getGmsfhm());
              poBchrclxx.setBchryid(poChryxx.getRyid());
              poBchrclxx.setBchrxm(poChryxx.getXm());
              poBchrclxx.setBchrzz(this.generateZZ(poChmlpxx,
                  PublicConstant.XTKZCS_DZPZFS,
                  PublicConstant.XTKZCS_DZPZFS_BCHRZZ));
              poBchrclxx.setBchrszpcs(poChmlpxx.getPcs());
              poBchrclxx.setClfs(HjConstant.CHCLFS_WCL);
              poBchrclxx.setHjywid(hjywid);
              super.create(poBchrclxx);
            }
          } //for (int c = 0; c < bchryxxList.size(); c++)
        } //if (bchryxxList != null && bchryxxList.size() > 0)
      } //for (int i = 0; i < chxxList.size(); i++)
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return;
  }

  public String generateZZ(PoHJXX_MLPXXXXB poMlpxxxx, String cslb, String dm) throws
      ServiceException, DAOException {

    String zz = null;
    int zz_len = 0;

    try {

      //得到地址组成方式设置参数
      PoXT_XTCSB poXtcsb = this.getXTCSB(cslb, dm);
      if (poXtcsb != null) {
        zz = poXtcsb.getZwpy();
      }
      if (poXtcsb == null || zz == null || (zz != null && zz.length() <= 0)) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                   "地址组成方式没有设置，生成地址失败。", null);
      }

      //分离住址表达式和住址长度，如: [[mlph]][[mlxz]],80 分解成：Stirng zz=[[mlph]][[mlxz]] ,int zz_len=80
      int offset = zz.indexOf(",");
      if (offset >= 0) {
        zz_len = Integer.valueOf(zz.substring(offset + 1, zz.length())).
            intValue();
        zz = zz.substring(0, offset);
      }

      //字符串替换
      //省市省区[[SSXQ]]
      String ssxq = poMlpxxxx.getSsxq() != null ? poMlpxxxx.getSsxq() : "";
      if (ssxq.length() > 0) {
    	  PojoInfo xt_xzqhbDAO = DAOFactory.createXT_XZQHBDAO();
        PoXT_XZQHB poXzqhxx = (PoXT_XZQHB) super.get(PoXT_XZQHB.class,ssxq);
        ssxq = (poXzqhxx != null && poXzqhxx.getMc() != null) ? poXzqhxx.getMc() :
            "";
      }
      zz = StringUtils.replace(zz, "[[SSXQ]]", ssxq.trim());
      //街路巷[[JLX]]
      String jlx = poMlpxxxx.getJlx() != null ? poMlpxxxx.getJlx() : "";
      if (jlx.length() > 0) {
    	  PojoInfo xt_jlxxxbDAO = DAOFactory.createXT_JLXXXBDAO();
        PoXT_JLXXXB poJlxxx = (PoXT_JLXXXB) super.get(PoXT_JLXXXB.class, jlx);
        jlx = (poJlxxx != null && poJlxxx.getMc() != null) ? poJlxxx.getMc() :
            "";
      }
      zz = StringUtils.replace(zz, "[[JLX]]", jlx.trim());
      //门楼牌号[[MLPH]]
      String mlph = poMlpxxxx.getMlph() != null ? poMlpxxxx.getMlph() : "";
      zz = StringUtils.replace(zz, "[[MLPH]]", mlph.trim());
      //门楼详址[[MLXZ]]
      String mlxz = poMlpxxxx.getMlxz() != null ? poMlpxxxx.getMlxz() : "";
      zz = StringUtils.replace(zz, "[[MLXZ]]", mlxz.trim());
      //派出所[[PCS]]
      String pcs = poMlpxxxx.getPcs() != null ? poMlpxxxx.getPcs() : "";
      if (pcs.length() > 0) {
    	  PojoInfo xt_dwxxbDAO = DAOFactory.createXT_DWXXBDAO();
        PoXT_DWXXB poDwxx = (PoXT_DWXXB) super.get(PoXT_DWXXB.class, pcs);
        //modi by hh 20060301  派出所取备注
        //pcs = (poDwxx != null && poDwxx.getMc() != null) ? poDwxx.getMc() :
        pcs = (poDwxx != null && poDwxx.getBz() != null) ? poDwxx.getBz() :
            "";
      }
      zz = StringUtils.replace(zz, "[[PCS]]", pcs.trim());
      //责任区[[ZRQ]]
      String zrq = poMlpxxxx.getZrq() != null ? poMlpxxxx.getZrq() : "";
      if (zrq.length() > 0) {
    	  PojoInfo xt_jwzrqxxbDAO = DAOFactory.createXT_JWZRQXXBDAO();
        PoXT_JWZRQXXB poZrqxx = (PoXT_JWZRQXXB) super.get(PoXT_JWZRQXXB.class,
            zrq);
        zrq = (poZrqxx != null && poZrqxx.getMc() != null) ? poZrqxx.getMc() :
            "";
      }
      zz = StringUtils.replace(zz, "[[ZRQ]]", zrq.trim());
      //乡镇街道[[XZJD]]
      String xzjd = poMlpxxxx.getXzjd() != null ? poMlpxxxx.getXzjd() : "";
      if (xzjd.length() > 0) {
    	  PojoInfo xt_xzjdxxbDAO = DAOFactory.createXT_XZJDXXBDAO();
        PoXT_XZJDXXB poXzjdxx = (PoXT_XZJDXXB) super.get(PoXT_XZJDXXB.class,
            xzjd);
        //xzjd = (poXzjdxx != null && poXzjdxx.getMc() != null) ? poXzjdxx.getMc() : "";
        xzjd = (poXzjdxx != null && poXzjdxx.getBz() != null) ? poXzjdxx.getBz() :
            "";
      }
      zz = StringUtils.replace(zz, "[[XZJD]]", xzjd.trim());
      //居村委会[[JCWH]]
      String jcwh = poMlpxxxx.getJcwh() != null ? poMlpxxxx.getJcwh() : "";
      if (jcwh.length() > 0) {
    	  PojoInfo xt_jwhxxbDAO = DAOFactory.createXT_JWHXXBDAO();
        PoXT_JWHXXB poJwhxx = (PoXT_JWHXXB) super.get(PoXT_JWHXXB.class,jcwh);
        //jcwh = (poJwhxx != null && poJwhxx.getMc() != null) ? poJwhxx.getMc() : "";
        jcwh = (poJwhxx != null && poJwhxx.getBz() != null) ? poJwhxx.getBz() :
            "";
      }
      zz = StringUtils.replace(zz, "[[JCWH]]", jcwh.trim());
      //长度控制
      if (zz_len > 0 && zz.length() > zz_len) {
        zz = zz.substring(0, zz_len);
      }

    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return zz;
  }

  /**
   * 判断是不是住址变动
   * @param qrXzqh - 迁入行政区划
   * @param qcXzqh - 迁出行政区划
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  protected boolean checkZZBD(String qrXzqh, String qcXzqh) throws
      ServiceException,
      DAOException {

    StringBuffer strHQL = new StringBuffer();

    try {
    	PojoInfo xt_qyszbDAO = DAOFactory.createXT_QYSZBDAO();
    	PojoInfo xt_bssqbDAO = DAOFactory.createXT_BSSQBDAO();

      strHQL.append("select count(*) from PoXT_BSSQB ")
          .append("where qhdm in ('").append(qrXzqh).append("' ")
          .append(",'").append(qcXzqh).append("') ")
          .append("and qybz='" + PublicConstant.QYBZ_QY + "' ");

      //迁出地和迁入地都是本市
      long count = super.getCount(strHQL.toString());
      if (count == 2 || qrXzqh.equals(qcXzqh) && count == 1) {
        strHQL = new StringBuffer();
        strHQL.append("select count(*) from PoXT_QYSZB ")
            .append("where qhdma='" + qrXzqh + "' and qhdmb='" + qcXzqh + "' ")
            .append("or qhdma='" + qcXzqh + "' and qhdmb='" + qrXzqh + "' ")
            .append("and qybz='" + PublicConstant.QYBZ_QY + "' ");
        //迁出地和迁入地不在迁移设置表中
        if (super.getCount(strHQL.toString()) <= 0) {
          return true;
        }
      }
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return false;
  }

  protected PoHJXX_CZRKJBXXB findHZXX(List hcyxxList) {
    PoHJXX_CZRKJBXXB poHzxx = null;

    if (hcyxxList != null) {
      for (int i = 0; i < hcyxxList.size(); i++) {
        PoHJXX_CZRKJBXXB po = (PoHJXX_CZRKJBXXB) hcyxxList.get(i);
        if (PublicConstant.CXBZ_FCX.equals(po.getCxbz()) &&
            PublicConstant.JLBZ_ZX.equals(po.getJlbz()) &&
            "10".compareTo(po.getYhzgx()) >= 0) {
          poHzxx = po;
          if (HjConstant.RYZT_ZC.equals(poHzxx.getRyzt())) {
            break;
          }
        }
      }
    }

    return poHzxx;
  }

  /**
   * 修改户信息上的人信息
   * @param old_poHxx - 老的户信息
   * @param new_poHxx - 新的户信息
   * @param old_poMlpxxxx - 老的门楼牌详细信息
   * @param new_poMlpxxxx - 新的门楼牌详细信息
   * @param gnbh - 功能编号
   * @param qhtz_bdfw - 区划调整变动范围(如果是非区划调整业务则为null)
   * @param czsj - 操作时间
   * @throws ServiceException
   * @throws DAOException
   */
  protected synchronized void changeRXX_HXX(Long hjywid,
                                            PoHJXX_HXXB old_poHxx,
                                            PoHJXX_HXXB new_poHxx,
                                            PoHJXX_MLPXXXXB old_poMlpxxxx,
                                            PoHJXX_MLPXXXXB new_poMlpxxxx,
                                            String gnbh, String qhtz_bdfw,
                                            String czsj, VoSbjbxx voSbjbxx) throws
      ServiceException,
      DAOException {

    String strHQL = null;
    List chxxList = new ArrayList();
    List rybdxxList = new ArrayList();
    boolean bAdd = false;
    boolean bDec = false;
    String qhtz_sdkzz = null;
    PoXT_JWHXXB poJwhxxb = null;

    //数据校验
    if (hjywid == null || old_poHxx == null || new_poHxx == null ||
        old_poMlpxxxx == null || new_poMlpxxxx == null) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                 "传入的数据参数不够完整，修改户信息上的人信息无法完成。", null);
    }

    try {
    	PojoInfo hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
    	PojoInfo hjyw_zzbdxxbDAO = DAOFactory.createHJYW_ZZBDXXBDAO();
    	PojoInfo hjyw_chclxxbDAO = DAOFactory.createHJYW_CHCLXXBDAO();
    	PojoInfo hjyw_hcybdxxbDAO = DAOFactory.createHJYW_HCYBDXXBDAO();
    	PojoInfo xt_jwhxxbDAO = DAOFactory.createXT_JWHXXBDAO();

      poJwhxxb = (PoXT_JWHXXB) super.get(PoXT_JWHXXB.class,new_poMlpxxxx.getJcwh());
      if (poJwhxxb == null) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                           "找不到户所在的地居委会，出生登记业务无法完成。", null);
      }

      //修改该户信息上的人信息 -- 不能删除order by ,如果没有order by子句 HIBERNATE会把 for update 加入到 where子句的括号中去，错误语句如: select * from hjtj_ryywbltlxxb where (ryid=2312312123123132 for update)
      strHQL = "from PoHJXX_CZRKJBXXB where jlbz='" +
          PublicConstant.JLBZ_ZX + "' and hhnbid=" +
          old_poHxx.getHhnbid() + " and cxbz='" + PublicConstant.CXBZ_FCX +
          "' order by rynbid"; // for update
      
      List ryxxList = super.getObjectListByHqlAndLock(strHQL);
      qhtz_sdkzz = this.getXTKZCS("1019");
      if (ryxxList != null && ryxxList.size() > 0) {
        PoHJXX_CZRKJBXXB poHzxx = this.findHZXX(ryxxList);
        for (int k = 0; k < ryxxList.size(); k++) {
          PoHJXX_CZRKJBXXB poRyxx = (PoHJXX_CZRKJBXXB) ryxxList.get(k);
          //判断人员锁定状态是否为锁定状态
          //2006-5-26,胡斌,判断锁定人员是否允许区划调整的控制参数和是否区划调整。
          if (! ("F2101".equals(gnbh) && "0".equals(qhtz_sdkzz.trim()))) {
            if (!HjConstant.RYSDZT_ZC.equals(poRyxx.getRysdzt())) {
              throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                         poRyxx.getXm().trim() + "(" +
                                         poRyxx.getGmsfhm() +
                                         ")的人员状态为锁定，地址修改业务无法完成。", null);
            }
          }
          //判断人员状态是否为正常状态
          if (HjConstant.RYZT_ZC.equals(poRyxx.getRyzt())) {
            //置为历史记录
            poRyxx.setQczxlb(HjConstant.ZZBDLB_QT); //填充迁往地
            poRyxx.setQcrq(czsj.substring(0, 8));
            poRyxx.setQwdgjdq(null);
            poRyxx.setQwdssxq(new_poMlpxxxx.getSsxq());
            poRyxx.setQwdxz(this.generateZZ(new_poMlpxxxx,
                                            PublicConstant.XTKZCS_DZPZFS,
                                            PublicConstant.XTKZCS_DZPZFS_QCDXZ));
            poRyxx.setJssj(czsj);
            poRyxx.setCchjywid(hjywid);
            poRyxx.setJlbz(PublicConstant.JLBZ_LS);
            super.update(poRyxx);
            //保存新记录
            PoHJXX_CZRKJBXXB poRyxxNew = new PoHJXX_CZRKJBXXB();
            BeanUtils.copyProperties(poRyxxNew, poRyxx);
            BeanUtils.copyProperties(poRyxxNew, new_poHxx);
            BeanUtils.copyProperties(poRyxxNew, new_poMlpxxxx);
            poRyxxNew.setQczxlb(null);
            poRyxxNew.setQcrq(null);
            poRyxxNew.setQwdgjdq(null);
            poRyxxNew.setQwdssxq(null);
            poRyxxNew.setQwdxz(null);
            //Begin_注销代码原因是地址修改和全户变更都不修改常表何时来本址时间_洪亮_2005/03/17 14:00:00
            //if (PublicConstant.GNBH_DZ_DZXGYW.equals(gnbh)) {
            //  poRyxxNew.setHylbz(HjConstant.ZZBDLB_DZBD); //何因来本址
            // poRyxxNew.setHslbz(czsj.substring(0, 8)); //何时来本址
            // poRyxxNew.setHgjdqlbz(null); //何国家（地区）来本址
            // poRyxxNew.setHsssqlbz(old_poMlpxxxx.getSsxq()); //何省市县（区）来本址
            // poRyxxNew.setHxzlbz(this.generateZZ(old_poMlpxxxx,
            //                                      PublicConstant.XTKZCS_DZPZFS,
            //                                      PublicConstant.
            //                                      XTKZCS_DZPZFS_QCDXZ)); //何详址迁来
            //}
            //End_注销代码原因是地址修改和全户变更都不修改常表何时来本址时间_洪亮_2005/03/17 14:00:00
            poRyxxNew.setRynbid( (Long) hjxx_czrkjbxxbDAO.getId());
            if (new_poMlpxxxx != null) {
              poRyxxNew.setMlpnbid(new_poMlpxxxx.getMlpnbid());
            }
            poRyxxNew.setHhnbid(new_poHxx.getHhnbid());
            poRyxxNew.setJssj(null);
            poRyxxNew.setQysj(czsj);
            poRyxxNew.setXxqysj(czsj);//add by hb 20061027
            poRyxxNew.setYwnr(PublicConstant.GNBH_DZ_QHTZYW.equals(gnbh) ?
                              HjConstant.YWNR_QHTZ : HjConstant.YWNR_HSXBG);
            poRyxxNew.setCchjywid(null);
            poRyxxNew.setCjhjywid(hjywid);
            poRyxxNew.setJlbz(PublicConstant.JLBZ_ZX);
            //add by hh 20060327 区划调整记录变动范围
            poRyxxNew.setBdfw(qhtz_bdfw);

            //2015-12-13刘抢阳修改，冗余城乡属性
            poRyxxNew.setCxfldm(poJwhxxb.getCxfldm());

            super.create(poRyxxNew);
            //add by hh 20060323 全户信息需要保存到变更更正表中和住址变动表中
            if (PublicConstant.GNBH_HJ_QHBGYW.equals(gnbh)) {
              //保存变更更正信息
              VoBggzxxEx voBggzxxEx = new VoBggzxxEx();
              List lstBggz = new ArrayList();
              int nI = 0;
              boolean bIsNotHlx = false;
              if ( (old_poMlpxxxx.getJlx() != null && new_poMlpxxxx.getJlx() != null &&
                    !old_poMlpxxxx.getJlx().equals(new_poMlpxxxx.getJlx())) ||
                  (old_poMlpxxxx.getJlx() != null && new_poMlpxxxx.getJlx() == null) ||
                  (old_poMlpxxxx.getJlx() == null && new_poMlpxxxx.getJlx() != null)) {
                VoBggzxx voBggz = new VoBggzxx();
                voBggz.setRynbid(poRyxxNew.getRynbid());
                voBggz.setBggzhnr(new_poMlpxxxx.getJlx());
                voBggz.setBggzrq(czsj.substring(0, 8));
                voBggz.setBggzxm("jlx"); //街路巷
                voBggz.setBggzlb(HjConstant.BGGZLB_QT_BG);
                voBggz.setSbhjywid(hjywid);
                voBggz.setSfbczpdzplsb(false);
                voBggz.setZp(null);
                lstBggz.add(voBggz);
                bIsNotHlx = true;
              }
              if ( (old_poMlpxxxx.getMlph() != null && new_poMlpxxxx.getMlph() != null &&
                    !old_poMlpxxxx.getMlph().equals(new_poMlpxxxx.getMlph())) ||
                  (old_poMlpxxxx.getMlph() != null && new_poMlpxxxx.getMlph() == null) ||
                  (old_poMlpxxxx.getMlph() == null && (new_poMlpxxxx.getMlph() != null&&!new_poMlpxxxx.getMlph().equals("")))) {
                VoBggzxx voBggz = new VoBggzxx();
                voBggz.setRynbid(poRyxxNew.getRynbid());
                voBggz.setBggzhnr(new_poMlpxxxx.getMlph());
                voBggz.setBggzrq(czsj.substring(0, 8));
                voBggz.setBggzxm("mlph"); //门（楼）牌号
                voBggz.setBggzlb(HjConstant.BGGZLB_QT_BG);
                voBggz.setSbhjywid(hjywid);
                voBggz.setSfbczpdzplsb(false);
                voBggz.setZp(null);
                lstBggz.add(voBggz);
                bIsNotHlx = true;
              }
              if ( (old_poMlpxxxx.getMlxz() != null && new_poMlpxxxx.getMlxz() != null &&
                    !old_poMlpxxxx.getMlxz().equals(new_poMlpxxxx.getMlxz())) ||
                  (old_poMlpxxxx.getMlxz() != null && new_poMlpxxxx.getMlxz() == null) ||
                  (old_poMlpxxxx.getMlxz() == null && new_poMlpxxxx.getMlxz() != null)) {
                VoBggzxx voBggz = new VoBggzxx();
                voBggz.setRynbid(poRyxxNew.getRynbid());
                voBggz.setBggzhnr(new_poMlpxxxx.getMlxz());
                voBggz.setBggzrq(czsj.substring(0, 8));
                voBggz.setBggzxm("mlxz"); //门（楼）详址
                voBggz.setBggzlb(HjConstant.BGGZLB_QT_BG);
                voBggz.setSbhjywid(hjywid);
                voBggz.setSfbczpdzplsb(false);
                voBggz.setZp(null);
                lstBggz.add(voBggz);
                bIsNotHlx = true;
              }
              if ( (old_poHxx.getHlx() != null && new_poHxx.getHlx() != null &&
                    !old_poHxx.getHlx().equals(new_poHxx.getHlx())) ||
                  (old_poHxx.getHlx() != null && new_poHxx.getHlx() == null) ||
                  (old_poHxx.getHlx() == null && new_poHxx.getHlx() != null)) {
                VoBggzxx voBggz = new VoBggzxx();
                voBggz.setRynbid(poRyxxNew.getRynbid());
                voBggz.setBggzhnr(new_poHxx.getHlx());
                voBggz.setBggzrq(czsj.substring(0, 8));
                voBggz.setBggzxm("hlx"); //户类型
                voBggz.setBggzlb(HjConstant.BGGZLB_QT_BG);
                voBggz.setSbhjywid(hjywid);
                voBggz.setSfbczpdzplsb(false);
                voBggz.setZp(null);
                lstBggz.add(voBggz);
              }
              voBggzxxEx.setFlag(0);
              voBggzxxEx.setRynbid(poRyxxNew.getRynbid());
              voBggzxxEx.setSbhjywid(hjywid);
              voBggzxxEx.setBggzxxList(lstBggz);
              disposalBggzxx(hjywid, voSbjbxx, voBggzxxEx, poRyxx, gnbh, czsj);
              //保存住址变动信息
              if (bIsNotHlx) { //如果只有户类型变更不记录住址变动信息
                PoHJYW_ZZBDXXB poZzbdxx = new PoHJYW_ZZBDXXB();
                poZzbdxx.setZzbdid( (Long) hjyw_zzbdxxbDAO.getId());
                //modi by hh 20060327 住址变动表中需要记录人信息
                //poZzbdxx.setRynbid(poRyxxNew.getRynbid());
                BeanUtils.copyProperties(poZzbdxx, poRyxxNew);
                BeanUtils.copyProperties(poZzbdxx, poRyxxNew);
                poZzbdxx.setHb_q(poRyxx.getHb());
                poZzbdxx.setHh_q(poRyxx.getHh());
                poZzbdxx.setHlx_q(poRyxx.getHlx());
                poZzbdxx.setJcwh_q(poRyxx.getJcwh());
                poZzbdxx.setJlx_q(poRyxx.getJlx());
                poZzbdxx.setMlph_q(poRyxx.getMlph());
                poZzbdxx.setMlpid_q(poRyxx.getMlpid());
                poZzbdxx.setMlpnbid_q(poRyxx.getMlpnbid());
                poZzbdxx.setMlxz_q(poRyxx.getMlxz());
                poZzbdxx.setPcs_q(poRyxx.getPcs());
                poZzbdxx.setPxh_q(poRyxx.getPxh());
                poZzbdxx.setXzjd_q(poRyxx.getXzjd());
                poZzbdxx.setSsxq_q(poRyxx.getSsxq());
                poZzbdxx.setZrq_q(poRyxx.getZrq());
                poZzbdxx.setYhhid(poRyxx.getHhid());
                poZzbdxx.setSlsj(czsj);
                poZzbdxx.setSldw(this.getUser().getDwdm());
                poZzbdxx.setSlrid(this.getUser().getYhid());
                BeanUtils.copyProperties(poZzbdxx, voSbjbxx); //申报人信息
                poZzbdxx.setCdlb(new_poMlpxxxx.getCdlb());
                poZzbdxx.setJdlb(new_poMlpxxxx.getJdlb());
                poZzbdxx.setCdlb_q(old_poMlpxxxx.getCdlb());
                poZzbdxx.setJdlb_q(old_poMlpxxxx.getJdlb());
                poZzbdxx.setYhhnbid(poRyxx.getHhnbid());
                poZzbdxx.setZzbdlb(HjConstant.ZZBDLB_DZBD);
                poZzbdxx.setZzbdrq(czsj.substring(0, 8));
                poZzbdxx.setQyzbh(null);
                poZzbdxx.setBdfw(qhtz_bdfw);
                poZzbdxx.setHjywid(hjywid);
                poZzbdxx.setHzgmsfhm(poHzxx != null ? poHzxx.getGmsfhm() : null);
                poZzbdxx.setHzxm(poHzxx != null ? poHzxx.getXm() : null);
                poZzbdxx.setCzsm(new Long(0));
                poZzbdxx.setYwlx(PublicConstant.HJYWLS_YWLX_QHTZ);
                super.create(poZzbdxx);
              }
            }
            //区划调整保存住址变动信息
            if (PublicConstant.GNBH_DZ_QHTZYW.equals(gnbh)) {
              PoHJYW_ZZBDXXB poZzbdxx = new PoHJYW_ZZBDXXB();
              poZzbdxx.setZzbdid( (Long) hjyw_zzbdxxbDAO.getId());
              //modi by hh 20060327 住址变动表中需要记录人信息
              //poZzbdxx.setRynbid(poRyxxNew.getRynbid());
              BeanUtils.copyProperties(poZzbdxx, poRyxxNew);
              poZzbdxx.setHb_q(poRyxx.getHb());
              poZzbdxx.setHh_q(poRyxx.getHh());
              poZzbdxx.setHlx_q(poRyxx.getHlx());
              poZzbdxx.setJcwh_q(poRyxx.getJcwh());
              poZzbdxx.setJlx_q(poRyxx.getJlx());
              poZzbdxx.setMlph_q(poRyxx.getMlph());
              poZzbdxx.setMlpid_q(poRyxx.getMlpid());
              poZzbdxx.setMlpnbid_q(poRyxx.getMlpnbid());
              poZzbdxx.setMlxz_q(poRyxx.getMlxz());
              poZzbdxx.setPcs_q(poRyxx.getPcs());
              poZzbdxx.setPxh_q(poRyxx.getPxh());
              poZzbdxx.setXzjd_q(poRyxx.getXzjd());
              poZzbdxx.setSsxq_q(poRyxx.getSsxq());
              poZzbdxx.setZrq_q(poRyxx.getZrq());
              poZzbdxx.setYhhid(poRyxx.getHhid());
              poZzbdxx.setSlsj(czsj);
              poZzbdxx.setSldw(this.getUser().getDwdm());
              poZzbdxx.setSlrid(this.getUser().getYhid());
              if (voSbjbxx != null)
                BeanUtils.copyProperties(poZzbdxx, voSbjbxx); //申报人信息
              poZzbdxx.setCdlb(new_poMlpxxxx.getCdlb());
              poZzbdxx.setJdlb(new_poMlpxxxx.getJdlb());
              poZzbdxx.setCdlb_q(old_poMlpxxxx.getCdlb());
              poZzbdxx.setJdlb_q(old_poMlpxxxx.getJdlb());

              poZzbdxx.setYhhnbid(poRyxx.getHhnbid());
              poZzbdxx.setZzbdlb(HjConstant.ZZBDLB_DZBD);
              poZzbdxx.setZzbdrq(czsj.substring(0, 8));
              poZzbdxx.setQyzbh(null);
              poZzbdxx.setBdfw(qhtz_bdfw);
              poZzbdxx.setHjywid(hjywid);
              poZzbdxx.setHzgmsfhm(poHzxx != null ? poHzxx.getGmsfhm() : null);
              poZzbdxx.setHzxm(poHzxx != null ? poHzxx.getXm() : null);
              poZzbdxx.setCzsm(new Long(0));
              poZzbdxx.setYwlx(PublicConstant.HJYWLS_YWLX_QHTZ);
              poZzbdxx.setNyzyrklhczyydm("0");// 区划调整默认0  add  by zjm 20190419 
              poZzbdxx.setCxsxtz_pdbz("0");// 区划调整默认0    add  by zjm 20190419
              poZzbdxx.setJjqx_pdbz("0");// 区划调整默认0    add  by zjm 20190419
              poZzbdxx.setTbbz(0L);// 区划调整默认0    add  by zjm 20190419
              super.create(poZzbdxx);

              //重号信息消除(注销老的重号信息主要是处理重号所在的单位不同情况)
              strHQL = "from PoHJYW_CHCLXXB as HJYW_CHCLXXB where clfs='" +
                  HjConstant.CHCLFS_WCL + "' and chsfhm='" +
                  poRyxxNew.getGmsfhm() + "' and cxbz='" +
                  PublicConstant.CXBZ_FCX + "' ";
              List xcchxxList = super.findAllByHQL(strHQL);
              if (xcchxxList != null) {
                for (int j = 0; j < xcchxxList.size(); j++) {
                  PoHJYW_CHCLXXB poChclxx = (PoHJYW_CHCLXXB) xcchxxList.get(j);
                  //与别人重
                  if (poRyxxNew.getRyid().equals(poChclxx.getRyid())) {
                    poChclxx.setClfs(HjConstant.CHCLFS_BRQC);
                    poChclxx.setClhjywid(hjywid);
                    super.update(poChclxx);
                  }
                  //别人与自己重
                  if (poRyxxNew.getRyid().equals(poChclxx.getBchryid())) {
                    poChclxx.setClfs(HjConstant.CHCLFS_DFQC);
                    poChclxx.setClhjywid(hjywid);
                    super.update(poChclxx);
                  }
                } //for (int j = 0; j < chxxList.size(); j++)
              }
              //如果是重号则填充重号详细信息(增加新的重号信息主要是处理重号所在的单位不同情况)
              if (this.countCHRYXX(poRyxxNew.getGmsfhm()) > 1) {
                VoChxx ivoChxx = new VoChxx();
                ivoChxx.setBchryid(poRyxxNew.getRyid());
                ivoChxx.setChsfhm(poRyxxNew.getGmsfhm());
                chxxList.add(ivoChxx);
              }
              //add by hh 20060327 区划调整变动范围是派出所内调整的要生成户成员变动信息
              if (qhtz_bdfw != null &&
                  (Integer.parseInt(qhtz_bdfw) >=
                   Integer.parseInt(HjConstant.BDFW_BQNTS))) {
                //生成户成员变动信息
                PoHJYW_HCYBDXXB poHcybdxx_QC = new PoHJYW_HCYBDXXB();
                PoHJYW_HCYBDXXB poHcybdxx_QR = new PoHJYW_HCYBDXXB();
                if (voSbjbxx != null) {
                  BeanUtils.copyProperties(poHcybdxx_QC, voSbjbxx);
                  BeanUtils.copyProperties(poHcybdxx_QR, voSbjbxx);
                }
                //迁出户
                poHcybdxx_QC.setCxbz(PublicConstant.CXBZ_FCX);
                poHcybdxx_QC.setHcybdid( (Long) hjyw_hcybdxxbDAO.getId());
                poHcybdxx_QC.setRynbid(poRyxxNew.getRynbid());
                poHcybdxx_QC.setRyid(poRyxxNew.getRyid());
                poHcybdxx_QC.setYgx(poRyxx.getYhzgx());
                poHcybdxx_QC.setXgx(poRyxxNew.getYhzgx());
                poHcybdxx_QC.setHcybdlx(HjConstant.HCYBDLX_LH);
                poHcybdxx_QC.setHcybdlb(HjConstant.ZZBDLB_DZBD);
                poHcybdxx_QC.setHcybdrq(czsj.substring(0, 8));
                poHcybdxx_QC.setHjywid(hjywid);
                poHcybdxx_QC.setSldw(this.getUser().getDwdm());
                poHcybdxx_QC.setSlrid(this.getUser().getYhid());
                poHcybdxx_QC.setSlsj(czsj);
                //户成员变动信息表中增加人、地信息
                poHcybdxx_QC.setGmsfhm(poRyxxNew.getGmsfhm());
                poHcybdxx_QC.setXm(poRyxxNew.getXm());
                poHcybdxx_QC.setXb(poRyxxNew.getXb());
                poHcybdxx_QC.setMz(poRyxxNew.getMz());
                poHcybdxx_QC.setCsrq(poRyxxNew.getCsrq());
                poHcybdxx_QC.setCssj(poRyxxNew.getCssj());
                poHcybdxx_QC.setCsdssxq(poRyxxNew.getCsdssxq());
                poHcybdxx_QC.setZpid(poRyxxNew.getZpid());
                poHcybdxx_QC.setMlpid(poRyxxNew.getMlpid());
                poHcybdxx_QC.setSsxq(poRyxxNew.getSsxq());
                poHcybdxx_QC.setJlx(poRyxxNew.getJlx());
                poHcybdxx_QC.setMlph(poRyxxNew.getMlph());
                poHcybdxx_QC.setMlxz(poRyxxNew.getMlxz());
                poHcybdxx_QC.setPcs(poRyxxNew.getPcs());
                poHcybdxx_QC.setZrq(poRyxxNew.getZrq());
                poHcybdxx_QC.setXzjd(poRyxxNew.getXzjd());
                poHcybdxx_QC.setJcwh(poRyxxNew.getJcwh());
                poHcybdxx_QC.setPxh(poRyxxNew.getPxh());
                poHcybdxx_QC.setMlpid(poRyxxNew.getMlpid());
                poHcybdxx_QC.setHhid(poRyxxNew.getHhid());
                poHcybdxx_QC.setHhnbid(poRyxxNew.getHhnbid());
                poHcybdxx_QC.setHh(poRyxxNew.getHh());

                //lzh add here 20070214 添加户类型赋值
                poHcybdxx_QC.setHlx(poRyxxNew.getHlx());

                super.create(poHcybdxx_QC);
                //迁入户
                poHcybdxx_QR.setCxbz(PublicConstant.CXBZ_FCX);
                poHcybdxx_QR.setHcybdid( (Long) hjyw_hcybdxxbDAO.getId());
                poHcybdxx_QR.setRynbid(poRyxxNew.getRynbid());
                poHcybdxx_QR.setRyid(poRyxxNew.getRyid());
                poHcybdxx_QR.setYgx(poRyxx.getYhzgx());
                poHcybdxx_QR.setXgx(poRyxxNew.getYhzgx());
                poHcybdxx_QR.setHcybdlx(HjConstant.HCYBDLX_RH);
                poHcybdxx_QR.setHcybdlb(HjConstant.ZZBDLB_DZBD);
                poHcybdxx_QR.setHcybdrq(czsj.substring(0, 8));
                poHcybdxx_QR.setHjywid(hjywid);
                poHcybdxx_QR.setSldw(this.getUser().getDwdm());
                poHcybdxx_QR.setSlrid(this.getUser().getYhid());
                poHcybdxx_QR.setSlsj(czsj);
                //户成员变动信息表中增加人、地信息
                poHcybdxx_QR.setGmsfhm(poRyxxNew.getGmsfhm());
                poHcybdxx_QR.setXm(poRyxxNew.getXm());
                poHcybdxx_QR.setXb(poRyxxNew.getXb());
                poHcybdxx_QR.setMz(poRyxxNew.getMz());
                poHcybdxx_QR.setCsrq(poRyxxNew.getCsrq());
                poHcybdxx_QR.setCssj(poRyxxNew.getCssj());
                poHcybdxx_QR.setCsdssxq(poRyxxNew.getCsdssxq());
                poHcybdxx_QR.setZpid(poRyxxNew.getZpid());
                poHcybdxx_QR.setMlpid(poRyxxNew.getMlpid());
                poHcybdxx_QR.setSsxq(poRyxxNew.getSsxq());
                poHcybdxx_QR.setJlx(poRyxxNew.getJlx());
                poHcybdxx_QR.setMlph(poRyxxNew.getMlph());
                poHcybdxx_QR.setMlxz(poRyxxNew.getMlxz());
                poHcybdxx_QR.setPcs(poRyxxNew.getPcs());
                poHcybdxx_QR.setZrq(poRyxxNew.getZrq());
                poHcybdxx_QR.setXzjd(poRyxxNew.getXzjd());
                poHcybdxx_QR.setJcwh(poRyxxNew.getJcwh());
                poHcybdxx_QR.setPxh(poRyxxNew.getPxh());
                poHcybdxx_QR.setMlpid(poRyxxNew.getMlpid());
                poHcybdxx_QR.setHhid(poRyxxNew.getHhid());
                poHcybdxx_QR.setHhnbid(poRyxxNew.getHhnbid());
                poHcybdxx_QR.setHh(poRyxxNew.getHh());

                //lzh add here 20070214 添加户类型赋值
                poHcybdxx_QR.setHlx(poRyxxNew.getHlx());

                super.create(poHcybdxx_QR);

              }

              //生成人员变动信息(四变)
              //迁出
              VoRybdxx voRybdxxQC = new VoRybdxx();
              voRybdxxQC.setBdbid(poZzbdxx.getZzbdid());
              voRybdxxQC.setBdfw(poZzbdxx.getBdfw());
              voRybdxxQC.setBdyy(poZzbdxx.getZzbdlb());
              voRybdxxQC.setBdrq(poZzbdxx.getZzbdrq());
              voRybdxxQC.setBdqhb(null);
              voRybdxxQC.setBdq_hhnbid(poZzbdxx.getYhhnbid());
              voRybdxxQC.setBdh_hhnbid(poRyxxNew.getHhnbid());
              voRybdxxQC.setRynbid(poRyxx.getRynbid());
              voRybdxxQC.setRzjs(new Long( -1));

              if (!bDec) {
                bDec = true;
                voRybdxxQC.setHzjs(new Long( -1));
              }
              else {
                voRybdxxQC.setHzjs(new Long(0));
              }
              rybdxxList.add(voRybdxxQC);
              //迁入
              VoRybdxx voRybdxxQR = new VoRybdxx();
              voRybdxxQR.setBdbid(poZzbdxx.getZzbdid());
              voRybdxxQR.setBdfw(poZzbdxx.getBdfw());
              voRybdxxQR.setBdyy(poZzbdxx.getZzbdlb());
              voRybdxxQR.setBdrq(poZzbdxx.getZzbdrq());
              voRybdxxQR.setBdqhb(poRyxx.getHb());
              voRybdxxQR.setBdq_hhnbid(poZzbdxx.getYhhnbid());
              voRybdxxQR.setBdh_hhnbid(poRyxxNew.getHhnbid());
              voRybdxxQR.setRynbid(poRyxxNew.getRynbid());
              voRybdxxQR.setRzjs(new Long(1));
              if (!bAdd) {
                bAdd = true;
                voRybdxxQR.setHzjs(new Long(1));
              }
              else {
                voRybdxxQR.setHzjs(new Long(0));
              }
              rybdxxList.add(voRybdxxQR);
            } //if (PublicConstant.GNBH_DZ_QHTZYW.equals(gnbh))
          } //if(poRyxx.getRyzt().equalsIgnoreCase(HjConstant.RYZT_ZC))
        } //for (int k = 0; k < ryxxList.size(); k++)

        //保存重号信息
        this.saveCHXX(hjywid, chxxList, czsj);

        //////////////////////////////////////////
        //保存人员变动信息(四变)
        PoHJLS_HJYWLSB poHjywlsxx = new PoHJLS_HJYWLSB();
        poHjywlsxx.setHjywid(hjywid);
        poHjywlsxx.setCzsm(new Long(0));
        poHjywlsxx.setYwbz(PublicConstant.GNBH_DZ_QHTZYW);
        poHjywlsxx.setYwlx(PublicConstant.HJYWLS_YWLX_QHTZ);
        poHjywlsxx.setSldw(this.getUser().getDwdm());
        poHjywlsxx.setSlrid(this.getUser().getYhid());
        poHjywlsxx.setSlsj(czsj);
        this.saveRYBDXX(rybdxxList, poHjywlsxx);
      } //if(ryxxList!=null && ryxxList.size() >0)
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return;
  }

  /**
   * 修改地信息上的人户信息
   * @param old_poMlpxxxx - 旧的门(楼)牌号详细信息
   * @param new_poMlpxxxx - 新的门(楼)牌号内部ID
   * @param gnbh - 功能编号
   * @param qhtz_bdfw - 区划调整变动范围(如果是非区划调整业务则为null)
   * @param czsj - 操作时间
   */
  protected synchronized void changeRHXX_DXX(Long hjywid,
                                             PoHJXX_MLPXXXXB old_poMlpxxxx,
                                             PoHJXX_MLPXXXXB new_poMlpxxxx,
                                             String gnbh,
                                             String qhtz_bdfw, String czsj) throws
      ServiceException, DAOException {

    String strHQL = null;

    //数据校验
    if (hjywid == null || old_poMlpxxxx == null || new_poMlpxxxx == null) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                 "传入的数据参数不够完整，修改地信息上的人户信息无法完成。", null);
    }

    try {
    	PojoInfo hjxx_hxxbDAO = DAOFactory.createHJXX_HXXBDAO();

      //该SQL语句还可以加入"户号状态"不等于注销状态(2)的条件，这样注销的户就不会被加载到内存，性能会提高点。(闵红斌于2004/12/02)
      //修改该地信息上的户信息--不能删除order by ,如果没有order by子句 HIBERNATE会把 for update 加入到 where子句的括号中去，错误语句如: select * from hjtj_ryywbltlxxb where (ryid=2312312123123132 for update)
      strHQL = "from PoHJXX_HXXB where jlbz='" + PublicConstant.JLBZ_ZX +
          "' and mlpnbid=" + old_poMlpxxxx.getMlpnbid() + " and cxbz='" +
          PublicConstant.CXBZ_FCX + "' order by hhnbid "; //lock  for update 去除 20190419
      List hxxList = super.getObjectListByHql(strHQL);
      if (hxxList != null && hxxList.size() > 0) {
        for (int j = 0; j < hxxList.size(); j++) {
          PoHJXX_HXXB poHxx = (PoHJXX_HXXB) hxxList.get(j);
          //判断户号状态是否为锁定状态
          if (HjConstant.HHZT_DJBYXXG.equals(poHxx.getHhzt())) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "户信息中的户号状态为锁定不能修改，地址修改业务无法完成。", null);
          }
          //判断户号状态是否为正常状态
          if (HjConstant.HHZT_ZC.equals(poHxx.getHhzt())) {
            //置为历史记录
            poHxx.setJssj(czsj);
            poHxx.setChlb(PublicConstant.GNBH_DZ_QHTZYW.equals(gnbh) ?
                          HjConstant.CHLB_QHTZZX : HjConstant.CHLB_YWZX);
            poHxx.setCchjywid(hjywid);
            poHxx.setJlbz(PublicConstant.JLBZ_LS);
            super.update(poHxx);
            //保存新记录
            PoHJXX_HXXB poHxxNew = new PoHJXX_HXXB();
            BeanUtils.copyProperties(poHxxNew, poHxx);
            poHxxNew.setHhnbid( (Long) hjxx_hxxbDAO.getId());
            poHxxNew.setMlpnbid(new_poMlpxxxx.getMlpnbid());
            if (qhtz_bdfw != null && ! (HjConstant.BDFW_BJMWYH.equals(qhtz_bdfw) ||
                                        HjConstant.BDFW_BSNJWH.equals(qhtz_bdfw))) {
              poHxxNew.setHh(this.assignHH(new_poMlpxxxx.getPcs()));
            }
            poHxxNew.setQysj(czsj);
            poHxxNew.setJssj(null);
            poHxxNew.setJhlb(PublicConstant.GNBH_DZ_QHTZYW.equals(gnbh) ?
                             HjConstant.JHLB_QHTZSC : HjConstant.JHLB_YWSC);
            poHxxNew.setChlb(null);
            poHxxNew.setCchjywid(null);
            poHxxNew.setCjhjywid(hjywid);
            poHxxNew.setJlbz(PublicConstant.JLBZ_ZX);
            super.create(poHxxNew);

            //修改该户信息上的人信息
            this.changeRXX_HXX(hjywid, poHxx, poHxxNew, old_poMlpxxxx,
                               new_poMlpxxxx, gnbh, qhtz_bdfw, czsj, null);
          } //if (HjConstant.HHZT_ZC.equalsIgnoreCase(poHxx.getHhzt()))
        } //for(int j=0;j<hxxList.size();j++)
      } //if (hxxList != null && hxxList.size() > 0)

    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return;
  }

  /**
   * 校验户成员信息(户主和配偶)
   * @param hhnbid
   * @throws ServiceException
   * @throws DAOException
   */
  protected void checkHCYXX(Long hhnbid) throws
      ServiceException, DAOException {

    StringBuffer strHQL = null;

    if (hhnbid == null) {
      return;
    }

    try {
    	PojoInfo hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();

      //得到户成员数
      strHQL = new StringBuffer();
      strHQL.append("select count(*) from PoHJXX_CZRKJBXXB ")
          .append("where ryzt ='" + HjConstant.RYZT_ZC + "' ")
          .append("and jlbz ='" + PublicConstant.JLBZ_ZX + "' ")
          .append("and cxbz = '" + PublicConstant.CXBZ_FCX + "' ")
          .append("and hhnbid =" + hhnbid);
      long nCount = super.getCount(strHQL.toString());
      //没有户成员
      if (nCount <= 0) {
        return;
      }

      //户主
      strHQL = new StringBuffer();
      strHQL.append("select count(*) from PoHJXX_CZRKJBXXB ")
          .append("where yhzgx < '10' ")
          .append("and ryzt ='" + HjConstant.RYZT_ZC + "' ")
          .append("and jlbz ='" + PublicConstant.JLBZ_ZX + "' ")
          .append("and cxbz = '" + PublicConstant.CXBZ_FCX + "' ")
          .append("and hhnbid =" + hhnbid);
      nCount = super.getCount(strHQL.toString());
      if (nCount > 1) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_HCYHZ,
                                   "一个户有且仅有一个户主，系统检测到该户有 " +
                                   String.valueOf(nCount) + " 个户主，业务不能办理。", null);
      }
      else if (nCount < 1) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_HCYHZ,
                                   "检测到该户没有户主，业务不能办理。", null);
      }

      //配偶
      strHQL = new StringBuffer();
      strHQL.append("select count(*) from PoHJXX_CZRKJBXXB ")
          .append("where substr(yhzgx,1,1) = '1' ")
          .append("and ryzt ='" + HjConstant.RYZT_ZC + "' ")
          .append("and jlbz ='" + PublicConstant.JLBZ_ZX + "' ")
          .append("and cxbz ='" + PublicConstant.CXBZ_FCX + "' ")
          .append("and hhnbid =" + hhnbid.toString());
      nCount = super.getCount(strHQL.toString());
      if (nCount > 1) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_HCYPO,
                                   "一个户有且仅有一个配偶，系统检测到该户有 " +
                                   String.valueOf(nCount) + " 个配偶，业务不能办理。", null);
      }

    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return;
  }

  /**
   * 转换变更更正类别
   * @param xm - 项目
   * @param lb - 类别(1-变更/2-更正)
   * @return 变更更正类别
   */
  protected String converBGGZLB(String xm, String _lb) {

    String BG = "1";
    String GZ = "2";
    String bggzlb = null;
    String lb = _lb != null ? _lb : BG;

    //变更更正公民身份号码
    if ("gmsfhm".equalsIgnoreCase(xm)) {
      if (BG.equalsIgnoreCase(lb)) {
        bggzlb = HjConstant.BGGZLB_GMSFHM_JZCH;
      }
      else if (GZ.equalsIgnoreCase(lb)) {
        bggzlb = HjConstant.BGGZLB_GMSFHM_JZZH;
      }
      else {
        bggzlb = HjConstant.BGGZLB_GMSFHM_QT;
      }
    }
    else

    //变更更正姓名
    if ("xm".equalsIgnoreCase(xm)) {
      if (BG.equalsIgnoreCase(lb)) {
        bggzlb = HjConstant.BGGZLB_XM_BG;
      }
      else if (GZ.equalsIgnoreCase(lb)) {
        bggzlb = HjConstant.BGGZLB_XM_GZ;
      }
      else {
        bggzlb = HjConstant.BGGZLB_XM_BGCYM;
      }
    }
    else

    //变更更正性别
    if ("xb".equalsIgnoreCase(xm)) {
      if (BG.equalsIgnoreCase(lb)) {
        bggzlb = HjConstant.BGGZLB_XB_BG;
      }
      else if (GZ.equalsIgnoreCase(lb)) {
        bggzlb = HjConstant.BGGZLB_XB_GZ;
      }
      else {
        bggzlb = HjConstant.BGGZLB_XB_GZ;
      }
    }
    else

    //变更更正出生日期
    if ("csrq".equalsIgnoreCase(xm)) {
      if (BG.equalsIgnoreCase(lb)) {
        bggzlb = HjConstant.BGGZLB_CSRQ_BG;
      }
      else if (GZ.equalsIgnoreCase(lb)) {
        bggzlb = HjConstant.BGGZLB_CSRQ_QT;
      }
      else {
        bggzlb = HjConstant.BGGZLB_CSRQ_QT;
      }
    }
    else

    //变更更正民族
    if ("mz".equalsIgnoreCase(xm)) {
      if (BG.equalsIgnoreCase(lb)) {
        bggzlb = HjConstant.BGGZLB_MZ_BG;
      }
      else if (GZ.equalsIgnoreCase(lb)) {
        bggzlb = HjConstant.BGGZLB_MZ_GZ;
      }
      else {
        bggzlb = HjConstant.BGGZLB_MZ_GZ;
      }
    }
    else

    //变更更正出生地
    if ("CSDGJDQ".equalsIgnoreCase(xm) || "CSDSSXQ".equalsIgnoreCase(xm) ||
        "CSDXZ".equalsIgnoreCase(xm)) {
      if (BG.equalsIgnoreCase(lb)) {
        bggzlb = HjConstant.BGGZLB_CSD_BG;
      }
      else if (GZ.equalsIgnoreCase(lb)) {
        bggzlb = HjConstant.BGGZLB_CSD_QT;
      }
      else {
        bggzlb = HjConstant.BGGZLB_CSD_QT;
      }
    }
    else

    //变更更正籍贯
    if ("JGGJDQ".equalsIgnoreCase(xm) || "JGSSXQ".equalsIgnoreCase(xm)) {
      if (BG.equalsIgnoreCase(lb)) {
        bggzlb = HjConstant.BGGZLB_JG_BG;
      }
      else if (GZ.equalsIgnoreCase(lb)) {
        bggzlb = HjConstant.BGGZLB_JG_GZ;
      }
      else {
        bggzlb = HjConstant.BGGZLB_JG_GZ;
      }
    }
    else

    //变更更正信息级别
    if ("XXJB".equalsIgnoreCase(xm)) {
      if (BG.equalsIgnoreCase(lb)) {
        bggzlb = HjConstant.BGGZLB_XXJB_BG;
      }
      else if (GZ.equalsIgnoreCase(lb)) {
        bggzlb = HjConstant.BGGZLB_XXJB_BG;
      }
      else {
        bggzlb = HjConstant.BGGZLB_XXJB_BG;
      }
    }
    //变更更正其他项目
    else {
      if (BG.equalsIgnoreCase(lb)) {
        bggzlb = HjConstant.BGGZLB_QT_BG;
      }
      else if (GZ.equalsIgnoreCase(lb)) {
        bggzlb = HjConstant.BGGZLB_QT_GZ;
      }
      else {
        bggzlb = HjConstant.BGGZLB_QT_GZ;
      }

    }

    return bggzlb;
  }

  /**
   * 保存人员变动信息(四变)
   * @param rybdxxList - 人员变动信息Vo List
   * @param poHjywlsxx - 户籍业务流水信息Po
   * @throws ServiceException
   * @throws DAOException
   */
  protected void saveRYBDXX(List rybdxxList, PoHJLS_HJYWLSB poHjywlsxx) throws
      ServiceException, DAOException {

    String strHQL = null;

    //数据校验
    if (rybdxxList == null || poHjywlsxx == null ||
        (rybdxxList != null && rybdxxList.size() <= 0)) {
      return;
    }

    try {
    	PojoInfo hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
    	PojoInfo hjxx_hxxbDAO = DAOFactory.createHJXX_HXXBDAO();
    	PojoInfo hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();
    	PojoInfo hjtj_rybdxxbDAO = DAOFactory.createHJTJ_RYBDXXBDAO();

      for (int i = 0; i < rybdxxList.size(); i++) {
        PoHJXX_CZRKJBXXB poRyxx = null;
        PoHJXX_HXXB bdq_poHxx = null;
        PoHJXX_MLPXXXXB bdq_poMlpxxxx = null;
        PoHJXX_HXXB bdh_poHxx = null;
        PoHJXX_MLPXXXXB bdh_poMlpxxxx = null;

        VoRybdxx voRybdxx = (VoRybdxx) rybdxxList.get(i);
        //得到人信息
        poRyxx = super.get(PoHJXX_CZRKJBXXB.class, voRybdxx.getRynbid());
        //得到变动前户地信息
        if (voRybdxx.getBdq_hhnbid() != null) {
          bdq_poHxx = super.get(PoHJXX_HXXB.class, voRybdxx.getBdq_hhnbid());
          bdq_poMlpxxxx = super.get(PoHJXX_MLPXXXXB.class, bdq_poHxx.
              getMlpnbid());
        }
        //得到变动后户地信息
        if (voRybdxx.getBdh_hhnbid() != null) {
          bdh_poHxx = super.get(PoHJXX_HXXB.class, voRybdxx.getBdh_hhnbid());
          bdh_poMlpxxxx = super.get(PoHJXX_MLPXXXXB.class, bdh_poHxx.
              getMlpnbid());
        }

        //错误数据判断(2005/04/25 10:00:00 By MHB)
        if ("0000".equals(voRybdxx.getBdyy())) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "变动原因代码不能为'0000'，业务无法完成。", null);
        }

        //保存人员变动信息
        PoHJTJ_RYBDXXB poHJTJ_RYBDXXB = new PoHJTJ_RYBDXXB();
        BeanUtils.copyProperties(poHJTJ_RYBDXXB, voRybdxx); //拷贝人员变动信息(变动表ID、变动原因、变动范围、变动日期、变动前户别)
        BeanUtils.copyProperties(poHJTJ_RYBDXXB, poRyxx); //拷贝人信息 + 业务内容
        BeanUtils.copyProperties(poHJTJ_RYBDXXB, poHjywlsxx); //拷贝业务流水信息
        //add by hh 20051220 区划调整的业务内容应该为区划调整
        if (poHjywlsxx.getYwlx() == PublicConstant.HJYWLS_YWLX_QHTZ) {
          poHJTJ_RYBDXXB.setYwnr(HjConstant.YWNR_QHTZ);
        }
        //保存变动前户地信息
        if (bdq_poHxx != null && bdq_poMlpxxxx != null) {
          //变动前户信息
          poHJTJ_RYBDXXB.setBdqhhnbid(bdq_poHxx.getHhnbid());
          poHJTJ_RYBDXXB.setBdqhlx(bdq_poHxx.getHlx());
          poHJTJ_RYBDXXB.setBdqhh(bdq_poHxx.getHh());
          //变动前地信息
          poHJTJ_RYBDXXB.setBdqmlpnbid(bdq_poMlpxxxx.getMlpnbid());
          poHJTJ_RYBDXXB.setBdqssxq(bdq_poMlpxxxx.getSsxq());
          poHJTJ_RYBDXXB.setBdqjlx(bdq_poMlpxxxx.getJlx());
          poHJTJ_RYBDXXB.setBdqmlph(bdq_poMlpxxxx.getMlph());
          poHJTJ_RYBDXXB.setBdqmlxz(bdq_poMlpxxxx.getMlxz());
          poHJTJ_RYBDXXB.setBdqpcs(bdq_poMlpxxxx.getPcs());
          poHJTJ_RYBDXXB.setBdqzrq(bdq_poMlpxxxx.getZrq());
          poHJTJ_RYBDXXB.setBdqxzjd(bdq_poMlpxxxx.getXzjd());
          poHJTJ_RYBDXXB.setBdqjcwh(bdq_poMlpxxxx.getJcwh());
        }
        //保存变动后户地信息
        if (bdh_poHxx != null && bdh_poMlpxxxx != null) {
          //变动后户信息
          poHJTJ_RYBDXXB.setBdhhhnbid(bdh_poHxx.getHhnbid());
          poHJTJ_RYBDXXB.setBdhhlx(bdh_poHxx.getHlx());
          poHJTJ_RYBDXXB.setBdhhh(bdh_poHxx.getHh());
          //变动后地信息
          poHJTJ_RYBDXXB.setBdhmlpnbid(bdh_poMlpxxxx.getMlpnbid());
          poHJTJ_RYBDXXB.setBdhssxq(bdh_poMlpxxxx.getSsxq());
          poHJTJ_RYBDXXB.setBdhjlx(bdh_poMlpxxxx.getJlx());
          poHJTJ_RYBDXXB.setBdhmlph(bdh_poMlpxxxx.getMlph());
          poHJTJ_RYBDXXB.setBdhmlxz(bdh_poMlpxxxx.getMlxz());
          poHJTJ_RYBDXXB.setBdhpcs(bdh_poMlpxxxx.getPcs());
          poHJTJ_RYBDXXB.setBdhzrq(bdh_poMlpxxxx.getZrq());
          poHJTJ_RYBDXXB.setBdhxzjd(bdh_poMlpxxxx.getXzjd());
          poHJTJ_RYBDXXB.setBdhjcwh(bdh_poMlpxxxx.getJcwh());
        }
        //迁出注销业务
        if (PublicConstant.GNBH_HJ_QCZXYW.equals(poHjywlsxx.getYwbz())) {
          poHJTJ_RYBDXXB.setBdhssxq(voRybdxx.getQwdssxq());
          poHJTJ_RYBDXXB.setBdhmlxz(voRybdxx.getQwdxz());
        }
        //迁入登记业务
        else if (PublicConstant.GNBH_HJ_QRDJYW.equals(poHjywlsxx.getYwbz())) {
          poHJTJ_RYBDXXB.setBdqssxq(voRybdxx.getQcdssxq());
          poHJTJ_RYBDXXB.setBdqmlxz(voRybdxx.getQcdxz());
        }

        //冲销标志
        if (HjConstant.YWNR_HFHK.equals(poRyxx.getYwnr())) {
          poHJTJ_RYBDXXB.setCxbz(PublicConstant.CXBZ_PDCX);
        }

        //户主信息
        PoHJXX_CZRKJBXXB poHzxx = null;
        if (PublicConstant.JLBZ_ZX.equals(poRyxx.getJlbz()) &&
            HjConstant.RYZT_ZC.equals(poRyxx.getRyzt())) {
          strHQL = "from PoHJXX_CZRKJBXXB where yhzgx < '10' and ryzt='" +
              HjConstant.RYZT_ZC + "' and jlbz='" +
              PublicConstant.JLBZ_ZX + "' and cxbz='" + PublicConstant.CXBZ_FCX +
              "' and hhnbid =" + poRyxx.getHhnbid();
        }
        else {
          strHQL = "from PoHJXX_CZRKJBXXB where yhzgx < '10' and hhnbid =" +
              poRyxx.getHhnbid() + " and qysj <= '" + poRyxx.getQysj() +
              "' and cxbz='" + PublicConstant.CXBZ_FCX + "' order by qysj desc";
        }
        List hzxxList = super.findAllByHQL(strHQL);
        if (hzxxList != null && hzxxList.size() > 0) {
          poHzxx = (PoHJXX_CZRKJBXXB) hzxxList.get(0);
          //填充户主信息
          poHJTJ_RYBDXXB.setHzgmsfhm(poHzxx.getGmsfhm());
          poHJTJ_RYBDXXB.setHzxm(poHzxx.getXm());
        }

        //保存
        poHJTJ_RYBDXXB.setRybdid( (Long) hjtj_rybdxxbDAO.getId());
        super.create(poHJTJ_RYBDXXB);
      } //for (int i = 0; i < rybdxxList.size(); i++)

    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return;
  }

  /**
   * 处理变更更正信息
   * @param hjywid - 户籍业务ID
   * @param voSbjbxx - 申报基本信息
   * @param voBggzxxEx - 变更更正信息
   * @param poRyxxNew - 需要变更更正的人员信息Po(rynbid是新分配的id)
   * @param gnbh - 功能编号
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  //modi by hh 20060322 增加参数psNow,使每个业务的操作时间一致
  protected VoBggzfhxxEx disposalBggzxx(Long hjywid, VoSbjbxx voSbjbxx,
                                        VoBggzxxEx voBggzxxEx,
                                        PoHJXX_CZRKJBXXB poRyxxNew,
                                        String gnbh, String psNow) throws
      DAOException, ServiceException {

    VoBggzfhxxEx voBggzfhxxEx = null;
    List yhzgxList = new ArrayList();
    VoHcygxtzfhxx voHcygxtzfhxx[] = null;
    List bggzfhxxList = new ArrayList();
    String now = psNow;
    if (psNow == null || "".equals(psNow)) {
      now = StringUtils.getServiceTime();

    }
    if (voBggzxxEx == null || poRyxxNew == null ||
        (voBggzxxEx != null && voBggzxxEx.getBggzxxList() != null &&
         voBggzxxEx.getBggzxxList().size() < 1)) {
      return null;
    }

    String bgqxm = poRyxxNew.getXm();
    String bgqgmsfhm = poRyxxNew.getGmsfhm();

    try {
    	PojoInfo hjyw_bggzxxbDAO = DAOFactory.createHJYW_BGGZXXBDAO();
    	PojoInfo hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();
    	PojoInfo hjyw_chclxxbDAO = DAOFactory.createHJYW_CHCLXXBDAO();
    	PojoInfo hjxx_ryzpxxbDAO = DAOFactory.createHJXX_RYZPXXBDAO();
    	PojoInfo hjyw_gmsfhmsxmfpxxbDAO = DAOFactory.
          createHJYW_GMSFHMSXMFPXXBDAO();
    	PojoInfo hjxx_hxxbDAO = DAOFactory.createHJXX_HXXBDAO();
    	PojoInfo hjxx_zplsbDAO = DAOFactory.createHJXX_ZPLSBDAO();

      /////////////////////////////////////////////
      //得到户信息
      PoHJXX_HXXB poHxx = super.get(PoHJXX_HXXB.class, poRyxxNew.getHhnbid());
      if (poHxx == null) {
        throw new ServiceException(WSErrCode.
                                   ERR_SERVICE_BUSSINESSLOGIC,
                                   "数据库中没有找到变更更正人员对应的户信息，变更更正业务无法完成。", null);
      }

      ////////////////////////////////////////////
      //得到地信息
      PoHJXX_MLPXXXXB poMlpxxxx = super.get(PoHJXX_MLPXXXXB.class,
          poRyxxNew.getMlpnbid());
      if (poMlpxxxx == null) {
        throw new ServiceException(WSErrCode.
                                   ERR_SERVICE_BUSSINESSLOGIC,
                                   "数据库中没有找到变更更正人员对应的地信息，变更更正业务无法完成。", null);
      }

      ///////////////////////////////////////
      //数据范围限制
      if (!PublicConstant.GNBH_HJ_ZZBDYW.equals(gnbh) &&
          !PublicConstant.GNBH_CX_HJZXYW.equals(gnbh) &&
          !PublicConstant.GNBH_CX_HJHFYW.equals(gnbh)) {
        List sjfwList = new ArrayList();
        VoXtsjfw voXtsjfw = new VoXtsjfw();
        voXtsjfw.setSjfw(poMlpxxxx.getJcwh());
        voXtsjfw.setSjfwbz(PublicConstant.XT_QX_JWH);
        sjfwList.add(voXtsjfw);
        boolean bLimit = false;
        bLimit = XtywqxServiceImpl.VerifyDataRange(this.getUser().getYhid().
            toString(),
            PublicConstant.GNBH_HJ_BGGZYW,
            sjfwList);
        if (!bLimit) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                   CommonUtil.getSjfwError(sjfwList)  + "，变更更正业务无法完成。", null);
        }
      }

      //非"变更审批结果处理业务"才判断审批是否受限
      if (!PublicConstant.GNBH_SP_BGSPJGCLYW.equals(gnbh)) {
        //变更审批限制处理
        VoRhdxx voRhdxxSP = new VoRhdxx(poRyxxNew, poHxx, poMlpxxxx,
                                        BaseContext.getUser());
        VoXtywxz voLimitSP = XtywqxServiceImpl.VerifyBggzSpLimit(PublicConstant.
            GNBH_HJ_BGGZYW, voRhdxxSP, voBggzxxEx.getBggzxxList());
        if (voLimitSP.getLimitflag()) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BGGZSXYSP,
                                     "变更更正业务审批受限，受限信息：" +
                                     voLimitSP.getLimitinfo() + ";对应的审批模板ID：" +
                                     voLimitSP.getSpmbid(), null);
        }
        voRhdxxSP = null;
      }

      /*
        变更更正公民身份号码时依赖人信息中的出生日期(csrq)、性别(xb)、
        姓名(xm)、省市县区(ssxq)、派出所(pcs)五项目信息，而在变更更
        正中有可能变更其中的项目，分配新公民身份号码时要用到上述变更后
        的信息，所以将变更更正公民身份号码放置到最后进行变更处理。
       */
      //将变更更正项目公民身份号码放置到最后
      int max = voBggzxxEx.getBggzxxList().size() - 1;
      for (int i = 0; i < max; i++) {
        VoBggzxx vo = (VoBggzxx) voBggzxxEx.getBggzxxList().get(i);
        if ("gmsfhm".equalsIgnoreCase(vo.getBggzxm())) {
          VoBggzxx last_vo = (VoBggzxx) voBggzxxEx.getBggzxxList().get(max);
          voBggzxxEx.getBggzxxList().set(i, last_vo);
          voBggzxxEx.getBggzxxList().set(max, vo);
          break;
        }
      }

      /////////////////////////////////////////////////////////
      //处理变更更正项目s
      for (int i = 0; i < voBggzxxEx.getBggzxxList().size(); i++) {
        VoBggzxx voBggzxx = (VoBggzxx) voBggzxxEx.getBggzxxList().get(i);
        VoHcygxtzxxEx voHcygxtzxxEx = null;
        String bggzqnr = null; //变更更正前内容
        String bggzhnr = null; //变更更正后内容
        String bggzxm = null; //变更更正项目

        //校验
        if (voBggzxx == null || (voBggzxx != null && voBggzxx.getBggzxm() == null) ||
            (voBggzxx != null && voBggzxx.getBggzxm() != null &&
             isKeyField(voBggzxx.getBggzxm().toUpperCase()))) {
          continue;
        }

        //得到变更更正项目
        bggzxm = voBggzxx.getBggzxm();
        //得到变更更正后内容
        bggzhnr = voBggzxx.getBggzhnr();

        //变更:公民身份号码
        if (bggzxm != null && bggzxm.equalsIgnoreCase("gmsfhm")) {
          //得到变更更正前内容
          bggzqnr = poRyxxNew.getGmsfhm();

          //重号信息消除
          String strHQL = "from PoHJYW_CHCLXXB as HJYW_CHCLXXB where clfs='" +
              HjConstant.CHCLFS_WCL + "' and chsfhm='" + bggzqnr +
              "' and cxbz='" + PublicConstant.CXBZ_FCX + "' ";
          List chxxList = super.findAllByHQL(strHQL);
          if (chxxList != null) {
            for (int j = 0; j < chxxList.size(); j++) {
              PoHJYW_CHCLXXB poChclxx = (PoHJYW_CHCLXXB) chxxList.get(j);
              //与别人重
              if (poRyxxNew.getRyid().equals(poChclxx.getRyid())) {
                poChclxx.setClfs(HjConstant.CHCLFS_BRZHXG);
                poChclxx.setClhjywid(hjywid);
                super.update(poChclxx);
              }
              //别人与自己重
              if (poRyxxNew.getRyid().equals(poChclxx.getBchryid())) {
                poChclxx.setClfs(HjConstant.CHCLFS_DFZHXG);
                poChclxx.setClhjywid(hjywid);
                super.update(poChclxx);
              }
            } //for (int j = 0; j < chxxList.size(); j++)
          }

          //校验身份号码、重号判断及保存身份号码尾数
          if (bggzhnr != null && bggzhnr.length() > 0) {
            //身份号码校验
            if (!PID.IDCheck(bggzhnr)) {
              throw new ServiceException(WSErrCode.
                                         ERR_SERVICE_BUSSINESSLOGIC,
                                         "公民身份号码(" + bggzhnr +
                                         ")有误，变更更正业务无法完成。", null);
            }
            //重号判断
            if (this.countCHRYXX(bggzhnr) > 0) {
              throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                         "公民身份号码(" + bggzhnr +
                                         ")重号，变更更正业务无法完成。", null);
            }
            //保存身份号码尾数信息
            strHQL =
                "select count(*) from PoHJYW_GMSFHMSXMFPXXB where gmsfhm='" +
                bggzhnr + "'";
            if (super.getCount(strHQL) <= 0) {
              PoHJYW_GMSFHMSXMFPXXB poFpxx = new PoHJYW_GMSFHMSXMFPXXB();
              poFpxx.setFpid( (Long) hjyw_gmsfhmsxmfpxxbDAO.getId());
              poFpxx.setRyid(poRyxxNew.getRyid());
              poFpxx.setCsrq(poRyxxNew.getCsrq());
              poFpxx.setXm(poRyxxNew.getXm());
              poFpxx.setGmsfhm(bggzhnr);
              poFpxx.setXb(poRyxxNew.getXb());
              poFpxx.setDwdm(poMlpxxxx.getPcs());
              poFpxx.setSxh(bggzhnr.substring(14, 17));
              poFpxx.setCzlb(HjConstant.GMSFHMFPWS_CZLB_LR);
              poFpxx.setHjywid(hjywid);
              super.create(poFpxx);
            }

            poRyxxNew.setGmsfhm(bggzhnr);
          }
          //没有身份号码，分配新的身份号码
          else {
            VoGmsfhmfpfhxx voGmsfhmfpfhxx = null;
            VoGmsfhmfpsqxx voGmsfhmfpsqxx = new VoGmsfhmfpsqxx();

            //设置公民身份号码分配申请信息
            voGmsfhmfpsqxx.setCsrq(poRyxxNew.getCsrq());
            voGmsfhmfpsqxx.setRyid(poRyxxNew.getRyid());
            voGmsfhmfpsqxx.setXb(poRyxxNew.getXb());
            voGmsfhmfpsqxx.setXm(poRyxxNew.getXm());
            voGmsfhmfpsqxx.setXzqh(poMlpxxxx.getSsxq());
            voGmsfhmfpsqxx.setPcs(poMlpxxxx.getPcs());
            voGmsfhmfpsqxx.setXzjd(poMlpxxxx.getXzjd());

            //调用公民身份号码分配功能
            voGmsfhmfpfhxx = this.assignGMSFHM(hjywid, voGmsfhmfpsqxx, now);
            if (voGmsfhmfpfhxx == null ||
                (voGmsfhmfpfhxx != null && voGmsfhmfpfhxx.getGmsfhm() == null) ||
                (voGmsfhmfpfhxx != null && voGmsfhmfpfhxx.getGmsfhm() != null &&
                 voGmsfhmfpfhxx.getGmsfhm().length() != 18)) {
              throw new ServiceException(WSErrCode.
                                         ERR_SERVICE_BUSSINESSLOGIC,
                                         "分配公民身份号码失败，变更更正业务无法完成。", null);
            }

            bggzhnr = voGmsfhmfpfhxx.getGmsfhm();
            poRyxxNew.setGmsfhm(bggzhnr);
          }
        }
        //////////////////////////////////////////////////
        //变更:照片
        else if (bggzxm != null && bggzxm.equalsIgnoreCase("zp")) {
          VoHJXX_RYZPXXB voRyzpxx = null;
          //得到变更更正前内容
          bggzqnr = null; //BeanUtils.getProperty(poRyxxNew, "zpid");

          //非"变更审批结果处理业务"的照片处理
          if (!PublicConstant.GNBH_SP_BGSPJGCLYW.equalsIgnoreCase(gnbh)) {
            if (voBggzxx.getZp() != null && voBggzxx.getZp().length() > 0) {
              //保存照片数据
              Long zpid = (Long) hjxx_ryzpxxbDAO.getId();
              voRyzpxx = new VoHJXX_RYZPXXB();
              voRyzpxx.setZpid(zpid);
              voRyzpxx.setLrrq(now);
              voRyzpxx.setRyid(poRyxxNew.getRyid());
              voRyzpxx.setXm(poRyxxNew.getXm());
              voRyzpxx.setGmsfhm(poRyxxNew.getGmsfhm());
              //voRyzpxx.setZp(bggzhnr);
              voRyzpxx.setZp(voBggzxx.getZp());
              super.create(voRyzpxx.toPoHJXX_RYZPXXB());

              bggzhnr = null; //zpid.toString();
              poRyxxNew.setZpid(zpid);
            }
            else {
              throw new ServiceException(WSErrCode.
                                         ERR_SERVICE_BUSSINESSLOGIC,
                                         "变更更正项目为照片却没发送照片数据，变更更正业务无法完成。", null);
            }
          }
          //"变更审批结果处理业务"的照片处理
          else {
            poRyxxNew.setZpid(Long.valueOf(voBggzxx.getBggzhnr()));
          }

          //修改证件受理信息
          PojoInfo zjyw_slxxbDAO = DAOFactory.createZJYW_SLXXBDAO();
          String strHQL = "from PoZJYW_SLXXB where ryid=" +
              poRyxxNew.getRyid() + " and (slzt='" +
              ZjConstant.ZJ_BLBZ_2ID_CS + "' or slzt='" +
              ZjConstant.ZJ_BLBZ_2ID_SJWZ + "') ";
          List slxxList =super.findAllByHQL(strHQL);
          if (slxxList != null) {
            for (int a = 0; a < slxxList.size(); a++) {
              PoZJYW_SLXXB poSlxx = (PoZJYW_SLXXB) slxxList.get(a);
              poSlxx.setZpid(poRyxxNew.getZpid());
              poSlxx.setSlzt(ZjConstant.ZJ_BLBZ_2ID_SJWZ);
              super.update(poSlxx);

              //保存照片到拍照临时表(2005/04/25 17:40:00 By MHB)
              if (voBggzxx.isSfbczpdzplsb()) {
                VoHJXX_ZPLSB voZplsxx = new VoHJXX_ZPLSB();
                voZplsxx.setZplsid( (Long) hjxx_zplsbDAO.getId());
                voZplsxx.setSlh(poSlxx.getSlh());
                voZplsxx.setGmsfhm(poRyxxNew.getGmsfhm());
                voZplsxx.setRyid(poSlxx.getRyid());
                voZplsxx.setRynbid(poSlxx.getRynbid());
                voZplsxx.setZp(voRyzpxx != null ? voRyzpxx.getZp() : null);
                voZplsxx.setZpid(voRyzpxx != null ? voRyzpxx.getZpid() : null);
                voZplsxx.setCzrid(this.getUser().getYhid());
                voZplsxx.setBcsj(now);
                super.create(voZplsxx.toPoHJXX_ZPLSB());
              }

              //保存证件业务流水信息
              this.saveZJYWLSXX(PublicConstant.GNBH_HJ_BGGZYW,
                                poSlxx.getSlzt(),
                                poSlxx.getSlh(), now);
            } //for (int a = 0; a < slxxList.size(); a++) {
          } //if (slxxList != null) {

          bggzhnr = null;
        }
        ////////////////////////////////////////////////////////////
        //变更:与户主关系
        else if (bggzxm != null && bggzxm.equalsIgnoreCase("yhzgx")) {
          //得到变更更正前内容
          bggzqnr = poRyxxNew.getYhzgx();

          //生成变更更正人员信息的户关系调整信息
          voHcygxtzxxEx = new VoHcygxtzxxEx();
          if (voBggzxxEx.getFlag() == 1 || voBggzxxEx.getFlag() == -1) {
            voHcygxtzxxEx.setFlag(0); //是否需要修改常住人口信息表中的对应记录(0-不修改/1-修改)
            voHcygxtzxxEx.setHcybdlx(HjConstant.HCYBDLX_JTGXBD);
            voHcygxtzxxEx.setHcybdlb(voBggzxx.getBggzlb());
            voHcygxtzxxEx.setOld_yhzgx(bggzqnr); //变更前
            voHcygxtzxxEx.setYhzgx(bggzhnr); //变更后
            voHcygxtzxxEx.setRynbid(voBggzxx.getRynbid());
            voHcygxtzxxEx.setRyid(poRyxxNew.getRyid());
            voHcygxtzxxEx.setHhnbid(poRyxxNew.getHhnbid());
            voHcygxtzxxEx.setNew_rynbid(poRyxxNew.getRynbid());
            voHcygxtzxxEx.setGmsfhm(poRyxxNew.getGmsfhm());
            voHcygxtzxxEx.setXm(poRyxxNew.getXm());
            voHcygxtzxxEx.setSbhjywid(null);
            yhzgxList.add(voHcygxtzxxEx);
          }

          poRyxxNew.setYhzgx(bggzhnr);
        }
        ////////////////////////////////////////////////////////////
        //变更:其它项目
        else {
          //得到变更更正前内容
        
          bggzqnr = (String)ObjectUtil.getProperty(poRyxxNew, bggzxm);

          ObjectUtil.setProperty(poRyxxNew, bggzxm, bggzhnr);
        }

        //变更"与户主关系"不允许生成变更更正信息(不允许打印的不需保存变更更正信息)
        if (voHcygxtzxxEx == null && checkBGGZ_DYKZBZ(bggzxm.toUpperCase())) {
          //保存变更更正信息
          PoHJYW_BGGZXXB poBggzxx = new PoHJYW_BGGZXXB();
          if (voSbjbxx != null) {
            BeanUtils.copyProperties(poBggzxx, voSbjbxx);
          }
          poBggzxx.setBggzid( (Long) hjyw_bggzxxbDAO.getId());
          poBggzxx.setRynbid(poRyxxNew.getRynbid());
          poBggzxx.setRyid(poRyxxNew.getRyid());
          poBggzxx.setBgqxm(bgqxm);
          poBggzxx.setBgqgmsfhm(bgqgmsfhm);
          //add by mhb 2005/06/27 15:21:00
          String qnr = bggzqnr == null ? "" : bggzqnr;
          String hnr = bggzhnr == null ? "" : bggzhnr;
          String bgxm = bggzxm == null ? "" : bggzxm;
          if (!bgxm.toUpperCase().equals("ZP") && hnr.equals(qnr)&&voBggzxxEx.getFlag()==1) {//add by zjm  20191106 增加限制条件flag为1，才判断
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "不允许变更前后内容相同，变更更正业务无法完成。", null);
          }

          //poBggzxx.setBggzlb(this.converBGGZLB(bggzxm, voBggzxx.getBggzlb()));
          poBggzxx.setBggzlb(voBggzxx.getBggzlb());
          poBggzxx.setBggzrq(voBggzxx.getBggzrq() != null ? voBggzxx.getBggzrq() :
                             now.substring(0, 8));
          //变更前内容
          String st[] = this.transBGGZXM(bggzxm.toUpperCase(), bggzqnr);
          if (st != null) {
            poBggzxx.setBggzxm(st[0] != null ? st[0] : bggzxm.toUpperCase());
            poBggzxx.setBggzqnr(st[1] != null ? st[1] : bggzqnr);
            //add by hh20051220 变更更正表中增加变更前,后代码
            poBggzxx.setBggzqdm(st[1] != null ? bggzqnr : null);
          }
          //变更后内容
          st = this.transBGGZXM(bggzxm.toUpperCase(), bggzhnr);
          if (st != null) {
            poBggzxx.setBggzhnr(st[1] != null ? st[1] : bggzhnr);
            //add by hh20051220 变更更正表中增加变更前,后代码
            poBggzxx.setBggzhdm(st[1] != null ? bggzhnr : null);
          }
          //add by hh20051220 如果变更后的省市县区的代码为0开头(外国),则清空
          //胡斌 20110919 判断变更省市县是否是变更成国家，如果是,且变更前不为空，BGGZ表中增加记录
          if (bggzxm.trim().toUpperCase().indexOf("SSXQ") > 0 ) {
            if (bggzhnr != null && bggzhnr.trim().charAt(0) == '0' && bggzqnr != null){
              //CDSSSXQ,JGDSSXQ，由省市县变更为国家代码，则清空变更更正后内容
              poBggzxx.setBggzhdm(null);
              poBggzxx.setBggzhnr(null);
            }else if (bggzqnr != null && bggzqnr.trim().charAt(0) == '0' && bggzhnr != null){
              //CDSSSXQ,JGDSSXQ，由国家代码变更为省市县，则清空变更更正前内容
              poBggzxx.setBggzqdm(null);
              poBggzxx.setBggzqnr(null);
            }
          }

          //add by hh 20051017 在变更更正业务表中增加人员的部分信息
          BeanUtils.copyProperties(poBggzxx, poRyxxNew);

          poBggzxx.setDybz(HjConstant.DYBZ_WDY);
          poBggzxx.setHjywid(hjywid);
          poBggzxx.setSldw(this.getUser().getDwdm());
          poBggzxx.setSlrid(this.getUser().getYhid());
          poBggzxx.setSlsj(now);
          //胡斌 20060530 增加判断变更省市县是否是变更成国家，如果是，变更SSXQ的记录不往BGGZ表中增加记录
          //胡斌 20110929 变更项目包含SSXQ的，变更由（国家到国家）或者（空到国家）的变更更正信息不进行insert
          if (!(bggzxm.trim().toUpperCase().indexOf("SSXQ") > 0
                && ((bggzqnr != null && bggzqnr.trim().charAt(0) == '0'
                && bggzhnr != null && bggzhnr.trim().charAt(0) == '0')
                || (bggzqnr == null
                && bggzhnr != null && bggzhnr.trim().charAt(0) == '0')))){
            super.create(poBggzxx);
          }
          //胡斌 20110919 注释
          //hjyw_bggzxxbDAO.insertHJYW_BGGZXXB(poBggzxx);

          //生成返回信息
          VoBggzfhxx voBggzfhxx = new VoBggzfhxx();
          voBggzfhxx.setBggzid(poBggzxx.getBggzid());
          voBggzfhxx.setOld_rynbid(voBggzxx.getRynbid());
          voBggzfhxx.setRynbid(poRyxxNew.getRynbid());
          voBggzfhxx.setRyid(poRyxxNew.getRyid());
          voBggzfhxx.setGmsfhm(poRyxxNew.getGmsfhm());
          voBggzfhxx.setXm(poRyxxNew.getXm());
          voBggzfhxx.setHhnbid(poRyxxNew.getHhnbid());
          voBggzfhxx.setYhzgx(poRyxxNew.getYhzgx());
          voBggzfhxx.setBggzxm(bggzxm);
          bggzfhxxList.add(voBggzfhxx);
        } //if(voHcygxtzxxEx==null){
      } //for (Iterator iter = bggzxmMap.entrySet().iterator(); iter.hasNext(); )

      //业务限制处理
      VoRhdxx voRhdxx = new VoRhdxx(poRyxxNew, poHxx, poMlpxxxx,
                                    BaseContext.getUser());
      //VoXtywxz voLimit = XtywqxServiceImpl.VerifyBusinessLimit(PublicConstant.GNBH_HJ_BGGZYW, voRhdxx);
      VoXtywxz voLimit = XtywqxServiceImpl.VerifyBggzLimit(PublicConstant.
          GNBH_HJ_BGGZYW, voRhdxx, voBggzxxEx.getBggzxxList());
      if (voLimit.getLimitflag()) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                   "变更更正业务受限，受限信息：" + voLimit.getLimitinfo(), null);
      }
      voRhdxx = null;

      /////////////////////////////////////////////////////////
      //调整变更更正中的户成员关系
      if (yhzgxList.size() > 0) {
        voHcygxtzfhxx = this.adjustHCYGX(hjywid, voSbjbxx,
                                         (VoHcygxtzxxEx[])
                                         yhzgxList.toArray(new VoHcygxtzxxEx[
            yhzgxList.size()]), now);
      }

      //生成返回信息
      voBggzfhxxEx = new VoBggzfhxxEx();
      voBggzfhxxEx.setVoBggzfhxx( (VoBggzfhxx[]) bggzfhxxList.toArray(new
          VoBggzfhxx[bggzfhxxList.size()]));
      voBggzfhxxEx.setVoHcygxtzfhxx(voHcygxtzfhxx);
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return voBggzfhxxEx;
  }

  /**
   * 保存变更更正信息
   * @param hjywid - 户籍业务ID
   * @param voSbjbxx - 申报基本信息
   * @param voBggzxxEx - 变更更正信息
   * @param gnbh - 功能编号
   * @return VoBggzfhxxEx - 变更更正返回信息
   * @throws ServiceException
   * @throws DAOException
   */
  //modi by hh 20060322 增加参数psNow
  public VoBggzfhxxEx saveBGGZXX(Long hjywid, VoSbjbxx voSbjbxx,
                                 VoBggzxxEx voBggzxxEx[], String gnbh,
                                 String psNow) throws
      ServiceException, DAOException {

    VoBggzfhxxEx voBggzfhxxEx = null;
    List bgryfhxxList = new ArrayList();
    List bggzfhxxList = new ArrayList();
    List hcygxtzfhxxList = new ArrayList();
    String now = psNow;
    if (psNow == null || "".equals(psNow)) {
      now = StringUtils.getServiceTime();

    }
    if (voBggzxxEx == null || (voBggzxxEx != null && voBggzxxEx.length <= 0)) {
      return null;
    }

    try {
    	PojoInfo hjxx_czrkjbxxbDAO = DAOFactory.
          createHJXX_CZRKJBXXBDAO();
    	PojoInfo hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();

      /////////////////////////////////////////////////
      //保存变更更正信息
      for (int i = 0; i < voBggzxxEx.length; i++) {
        //是否需要修改常住人口信息表中的对应记录(-1-不修改但处理户成员关系调整)/0-不修改/1-修改)
        if (voBggzxxEx[i].getFlag() != 1) {
          continue;
        }

        //得到人员信息
        PoHJXX_CZRKJBXXB poRyxx = super.get(PoHJXX_CZRKJBXXB.class,
            voBggzxxEx[i].getRynbid());
        if (poRyxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到需要变更更正的人员信息，变更更正业务无法完成。", null);
        }
        //校验人信息的时效性
        this.checkRXX(poRyxx, hjxx_czrkjbxxbDAO, "变更更正业务");
        //历史记录不允许操作
        if (PublicConstant.JLBZ_LS.equals(poRyxx.getJlbz())) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "不允许对历史记录操作(该记录可能在操作过程中被其他用户操作过)，变更更正业务无法完成。", null);
        }
        //人员锁定状态
        if (!HjConstant.RYSDZT_ZC.equals(poRyxx.getRysdzt())) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "人员锁定状态为非正常(被锁定)，变更更正业务无法完成。", null);
        }
        if (!PublicConstant.CXBZ_FCX.equals(poRyxx.getCxbz())) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "人员信息为冲销记录，变更更正业务无法完成。", null);
        }

        //处理变更更正信息
        VoBggzfhxxEx vo = this.disposalBggzxx(hjywid, voSbjbxx, voBggzxxEx[i],
                                              poRyxx, gnbh, now);
        if (vo != null) {
          VoBggzfhxx vob[] = vo.getVoBggzfhxx();
          VoHcygxtzfhxx voh[] = vo.getVoHcygxtzfhxx();
          if (vob != null) {
            for (int k = 0; k < vob.length; k++) {
              bggzfhxxList.add(vob[k]);
            }
          }
          if (voh != null) {
            for (int k = 0; k < voh.length; k++) {
              hcygxtzfhxxList.add(voh[k]);
            }
          }
        } //for (int i = 0; i < voBggzxx.length; i++)

        //保存变更后的人员信息成新记录
        super.update(poRyxx);

        //生成返回信息
        VoBgryfhxx voBgryfhxx = new VoBgryfhxx();
        voBgryfhxx.setHhnbid(poRyxx.getHhnbid());
        voBgryfhxx.setRynbid(poRyxx.getRynbid());
        voBgryfhxx.setRyid(poRyxx.getRyid());
        voBgryfhxx.setOld_rynbid(poRyxx.getRynbid());
        voBgryfhxx.setXm(poRyxx.getXm());
        voBgryfhxx.setGmsfhm(poRyxx.getGmsfhm());
        bgryfhxxList.add(voBgryfhxx);
      }

      ///////////////////////////////////////////
      //生成返回信息
      voBggzfhxxEx = new VoBggzfhxxEx();
      voBggzfhxxEx.setVoBgryfhxx( (VoBgryfhxx[]) bgryfhxxList.toArray(new
          VoBgryfhxx[bgryfhxxList.size()]));
      voBggzfhxxEx.setVoBggzfhxx( (VoBggzfhxx[]) bggzfhxxList.toArray(new
          VoBggzfhxx[bggzfhxxList.size()]));
      voBggzfhxxEx.setVoHcygxtzfhxx( (VoHcygxtzfhxx[]) hcygxtzfhxxList.
                                    toArray(new
                                            VoHcygxtzfhxx[hcygxtzfhxxList.
                                            size()]));
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return voBggzfhxxEx;
  }

  /**
   * 审批模板当前使用数增加1
   * @param spmbid - 审批模板ID
   * @throws DAOException
   * @throws ServiceException
   */
  protected void incSPMBDQSYS(Long spmbid) throws DAOException,
      ServiceException {

    if (spmbid == null) {
      return;
    }

    try {
    	PojoInfo xt_spmbxxbDAO = DAOFactory.createXT_SPMBXXBDAO();

      PoXT_SPMBXXB poSpmbxx = super.get(PoXT_SPMBXXB.class, spmbid);
      if (poSpmbxx != null) {
        long sys = 0;
        if (poSpmbxx.getDqsys() != null &&
            poSpmbxx.getDqsys().trim().length() > 0) {
          sys = Long.parseLong(poSpmbxx.getDqsys().trim());
        }
        sys++;
        if (sys > 999) {
          sys = 999;
        }
        poSpmbxx.setDqsys(String.valueOf(sys));
        super.update(poSpmbxx);
      } //if(poSpmbxx!=null){
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return;
  }

  /**
   * 审批模板当前使用数减少1
   * @param spmbid - 审批模板ID
   * @throws DAOException
   * @throws ServiceException
   */
  protected void decSPMBDQSYS(Long spmbid) throws DAOException,
      ServiceException {
    if (spmbid == null) {
      return;
    }

    try {
    	PojoInfo xt_spmbxxbDAO = DAOFactory.createXT_SPMBXXBDAO();

      PoXT_SPMBXXB poSpmbxx = super.get(PoXT_SPMBXXB.class, spmbid);
      if (poSpmbxx != null) {
        long sys = 0;
        if (poSpmbxx.getDqsys() != null &&
            poSpmbxx.getDqsys().trim().length() > 0) {
          sys = Long.parseLong(poSpmbxx.getDqsys().trim());
        }
        sys--;
        if (sys < 0) {
          sys = 0;
        }
        poSpmbxx.setDqsys(String.valueOf(sys));
        super.update(poSpmbxx);
      } //if(poSpmbxx!=null){
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return;
  }

  /**
   * 修改地信息上的人户信息
   * @param hjywid Long - 户籍业务ID
   * @param voQhtzxx VoQhtzxx[] - 区划调整信息
   * @param old_poMlpxxxx PoHJXX_MLPXXXXB - 旧的门(楼)牌号详细信息
   * @param old_poHxx PoHJXX_HXXB - 旧的户信息
   * @param gnbh String - 功能编号
   * @param czsj String - 操作时间
   * @throws ServiceException
   * @throws DAOException
   * @return PoHJXX_MLPXXXXB
   */
  protected synchronized PoHJXX_MLPXXXXB changeRHXX_DXX_QHTZ(Long hjywid,
      VoQhtzxx voQhtzxx,
      Long mlpnbid,
      Long hhnbid,
      String gnbh, String czsj) throws
      ServiceException, DAOException {

    String strHQL = null;
    PoHJXX_MLPXXXXB poMlpxxxxOld = null;
    PoHJXX_MLPXXXXB poMlpxxxxNew = null;
    PoHJXX_HXXB poHxxOld = null;
    PoHJXX_HXXB poHxxNew =  new PoHJXX_HXXB();
    PoQHTZHHKZB poQhtzhhkz = new PoQHTZHHKZB();

    //数据校验
    if (hjywid == null || voQhtzxx == null || mlpnbid == null ||
        hhnbid == null || gnbh == null || czsj == null) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                 "传入的数据参数不够完整，修改地信息上的人户信息无法完成。", null);
    }

    try {
    	PojoInfo hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
    	PojoInfo hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();
    	PojoInfo hjxx_hxxbDAO = DAOFactory.createHJXX_HXXBDAO();
    	PojoInfo xt_jwhxxbDAO = DAOFactory.createXT_JWHXXBDAO();
    	PojoInfo qhtzhhkzbDAO = DAOFactory.createQHTZHHKZBDAO();

      //得到区划调整的地信息
      poMlpxxxxOld = super.get(PoHJXX_MLPXXXXB.class, mlpnbid);
      //得到区划调整的户信息
      poHxxOld = super.get(PoHJXX_HXXB.class, hhnbid);

      //refresh必须放在事务中间
      super.refresh(poMlpxxxxOld, LockOptions.UPGRADE);

//      //当进行多地区划调整时锁住要调往的所有派出所
//      strHQL = "from PoXT_HHXLB where";
//      for (int i = 0; i < voQhtzxx.length; i++) {
//        if (voQhtzxx[i].getPcs() == null) {
//          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
//                                     "要调往的地信息中的派出所代码不能为空，区划调整业务无法完成。", null);
//        }
//        if (i > 0) {
//          strHQL += "' or dwdm='" + voQhtzxx[i].getPcs().trim();
//        }
//        else {
//          strHQL += " dwdm='" + voQhtzxx[i].getPcs().trim();
//        }
//      }
//      strHQL += "' order by xlid for update";
//      xt_hhxlbDAO.findAllXT_HHXLBs(strHQL);

      //如果区划调整到的地信息存在则引用，否则创建
      VoDzzjxx voDzxx = new VoDzzjxx();
      BeanUtils.copyProperties(voDzxx, voQhtzxx);
      poMlpxxxxNew = this.getDXX(voDzxx);

      if (poMlpxxxxNew == null) {
        poMlpxxxxNew = new PoHJXX_MLPXXXXB();
        BeanUtils.copyProperties(poMlpxxxxNew, poMlpxxxxOld);
        BeanUtils.copyProperties(poMlpxxxxNew, voQhtzxx);

        PoXT_JWHXXB poXT_JWHXXB = (PoXT_JWHXXB) super.get(PoXT_JWHXXB.class,
            poMlpxxxxNew.getJcwh()); //通过居委会代码得到乡镇街道代码
        if (poXT_JWHXXB == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                     "通过居委会代码无法得到乡镇街道代码,区划调整业务无法完成。", null);
        }
        poMlpxxxxNew.setXzjd(poXT_JWHXXB.getXzjddm());

        poMlpxxxxNew.setPxh(this.generatePXH(poMlpxxxxNew.getMlph(),
                                             poMlpxxxxNew.getMlxz()));
        poMlpxxxxNew.setMlpnbid( (Long) hjxx_mlpxxxxbDAO.getId());
        poMlpxxxxNew.setMlpid(poMlpxxxxNew.getMlpnbid());
        poMlpxxxxNew.setQysj(czsj);
        poMlpxxxxNew.setJssj(null);
        poMlpxxxxNew.setCdlb(null);
        poMlpxxxxNew.setJdlb(HjConstant.JDLB_QHTZJL);
        poMlpxxxxNew.setCchjywid(null);
        poMlpxxxxNew.setCjhjywid(hjywid);
        poMlpxxxxNew.setJlbz(PublicConstant.JLBZ_ZX);
        super.create(poMlpxxxxNew);
      }
      else if (!DzConstant.MLPZT_ZC.equals(poMlpxxxxNew.getMlpzt())) {

      }

      //业务限制
      VoHJXX_MLPXXXXB voMlpxxxx = new VoHJXX_MLPXXXXB(poMlpxxxxNew);
      VoXtywxz voLimit = XtywqxServiceImpl.VerifyBusinessLimit(PublicConstant.
          GNBH_DZ_QHTZYW, voMlpxxxx);
      if (voLimit.getLimitflag()) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                   "区划调整业务受限，受限信息：" + voLimit.getLimitinfo(), null);
      }

      //计算区划调整变动范围_Begin
      String qhtz_bdfw = null;
      //本居(村)民委员会内
      if (poMlpxxxxOld.getJcwh() != null &&
          poMlpxxxxOld.getJcwh().equals(poMlpxxxxNew.getJcwh())) {
        qhtz_bdfw = HjConstant.BDFW_BJMWYH;
      }
      //本所内居委会之间
      else if (poMlpxxxxOld.getPcs() != null &&
               poMlpxxxxOld.getPcs().equals(poMlpxxxxNew.getPcs())) {
        qhtz_bdfw = HjConstant.BDFW_BSNJWH;
      }
      //本区(县)内他所(乡)
      else if (poMlpxxxxOld.getSsxq() != null &&
               poMlpxxxxOld.getSsxq().equals(poMlpxxxxNew.getSsxq())) {
        qhtz_bdfw = HjConstant.BDFW_BQNTS;
      }
      //地市内
      else if (poMlpxxxxOld.getSsxq() != null && poMlpxxxxNew.getSsxq() != null &&
               poMlpxxxxOld.getPcs().substring(0, 4).equals(
          poMlpxxxxNew.getSsxq().substring(0, 4))) {
        qhtz_bdfw = HjConstant.BDFW_DSN;
      }
      //省内
      else if (poMlpxxxxOld.getSsxq() != null && poMlpxxxxNew.getSsxq() != null &&
               poMlpxxxxOld.getPcs().substring(0, 2).equals(
          poMlpxxxxNew.getSsxq().substring(0, 2))) {
        qhtz_bdfw = HjConstant.BDFW_SN;
      }
      //国内
      else {
        qhtz_bdfw = HjConstant.BDFW_GN;
      }
      //计算区划调整变动范围_end

      if (PublicConstant.JLBZ_ZX.equals(poHxxOld.getJlbz()) &&
          PublicConstant.CXBZ_FCX.equals(poHxxOld.getCxbz()) &&
          HjConstant.HHZT_ZC.equals(poHxxOld.getHhzt())) {

        //锁定该户信息--不能删除order by ,如果没有order by子句 HIBERNATE会把 for update 加入到 where子句的括号中去，错误语句如: select * from hjtj_ryywbltlxxb where (ryid=2312312123123132 for update)
        strHQL = "from PoHJXX_HXXB where hhnbid='" + poHxxOld.getHhnbid() +
            "' order by hhnbid "; //lock "' order by hhnbid for update";
        super.findAllByHQL(strHQL);

        //判断户号状态是否为正常状态
        //if (HjConstant.HHZT_ZC.equals(poHxxOld.getHhzt())) {//与上面的判断一样，多余了
        //poHxxNew = new PoHJXX_HXXB();

        //根据户号ID，查找户号控制表中有无正在调整中的记录
        strHQL = "from PoQHTZHHKZB where tzqhhid='" + poHxxOld.getHhid() +
            "'";
        List hhidlist = super.findAllByHQL(strHQL);
        if (hhidlist.size() > 0) {
          poQhtzhhkz = (PoQHTZHHKZB) hhidlist.get(0);
          strHQL = "from PoHJXX_HXXB where hhnbid='" +
              poQhtzhhkz.getTzhhhnbid() +
              "' and hhid='" + poQhtzhhkz.getTzhhhid() +
              "' and jlbz='" + PublicConstant.JLBZ_ZX +
              "' and cxbz='" + PublicConstant.CXBZ_FCX +
              "' and hhzt='" + HjConstant.HHZT_ZC + "'";
          List hxxlist = super.findAllByHQL(strHQL);
          if (hxxlist.size() != 1) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_DATASCOPE,
                                       "根据区划调整户号控制表的户号找不到对应存在的户", null);
          }
          poHxxNew = (PoHJXX_HXXB) hxxlist.get(0);
        }
        else {
          //保存新记录
          BeanUtils.copyProperties(poHxxNew, poHxxOld);
          poHxxNew.setHhnbid( (Long) hjxx_hxxbDAO.getId());
          poHxxNew.setHhid( (Long) poHxxNew.getHhnbid());
          poHxxNew.setMlpnbid(poMlpxxxxNew.getMlpnbid());
          if (qhtz_bdfw != null && ! (HjConstant.BDFW_BJMWYH.equals(qhtz_bdfw) ||
                                      HjConstant.BDFW_BSNJWH.equals(qhtz_bdfw))) {
            poHxxNew.setHh(this.assignHH(poMlpxxxxNew.getPcs()));
          }
          poHxxNew.setQysj(czsj);
          poHxxNew.setJssj(null);
          poHxxNew.setJhlb(PublicConstant.GNBH_DZ_QHTZYW.equals(gnbh) ?
                           HjConstant.JHLB_QHTZSC : HjConstant.JHLB_YWSC);
          poHxxNew.setChlb(null);
          poHxxNew.setCchjywid(null);
          poHxxNew.setCjhjywid(hjywid);
          poHxxNew.setJlbz(PublicConstant.JLBZ_ZX);
          super.create(poHxxNew);

          //临时保存区划调整中户号ID
          poQhtzhhkz.setTzqhhid( (Long) poHxxOld.getHhid());
          poQhtzhhkz.setTzhhhid( (Long) poHxxNew.getHhid());
          poQhtzhhkz.setTzqhhnbid( (Long) poHxxOld.getHhnbid());
          poQhtzhhkz.setTzhhhnbid( (Long) poHxxNew.getHhnbid());
          poQhtzhhkz.setTzsj(czsj);
          super.create(poQhtzhhkz);
        }

        //修改该户信息上的人信息
        this.changeRXX_HXX_QHTZ(hjywid, poHxxOld, poHxxNew, poMlpxxxxOld,
                                poMlpxxxxNew, gnbh, qhtz_bdfw, czsj, null);
//        }

        //判断该户上是否还有未进行区划调整的人存在
        strHQL = "select count(*) from PoHJXX_CZRKJBXXB where hhnbid='" +
            poHxxOld.getHhnbid() + "' and jlbz='" + PublicConstant.JLBZ_ZX +
            "' and cxbz='" + PublicConstant.CXBZ_FCX + "' and ryzt='" +
            HjConstant.RYZT_ZC + "'";
        long rxxCount = super.getCount(strHQL); //该户上状态正常的人总数
        if (rxxCount <= 0) {
          //删除该户在区划调整户号控制表中的记录
          super.delete(poQhtzhhkz);

          //置为历史记录
          poHxxOld.setJssj(czsj);
          poHxxOld.setChlb(PublicConstant.GNBH_DZ_QHTZYW.equals(gnbh) ?
                           HjConstant.CHLB_QHTZZX : HjConstant.CHLB_YWZX);
          poHxxOld.setCchjywid(hjywid);
          poHxxOld.setJlbz(PublicConstant.JLBZ_LS);
          //poHxxOld.setHhzt(HjConstant.HHZT_ZX);//将户号状态置为注销
          super.update(poHxxOld);

          //保存户信息为最新记录并注销户(撤消户)
          PoHJXX_HXXB poHxxZX = new PoHJXX_HXXB();
          BeanUtils.copyProperties(poHxxZX, poHxxOld);
          poHxxZX.setHhnbid( (Long) hjxx_hxxbDAO.getId());
          poHxxZX.setHhid((Long)poHxxOld.getHhid());
          poHxxZX.setChlb(null);
          poHxxZX.setJhlb(HjConstant.JHLB_QHTZSC);
          poHxxZX.setCchjywid(null);
          poHxxZX.setCjhjywid(hjywid);
          poHxxZX.setHhzt(HjConstant.HHZT_ZX);
          poHxxZX.setJlbz(PublicConstant.JLBZ_ZX);
          poHxxZX.setQysj(czsj);
          poHxxZX.setJssj(null);
          super.create(poHxxZX);

        }
      }//调整状态正常的户

      //判断该地上是否还有未进行区划调整的户存在
      strHQL = "select count(*) from PoHJXX_HXXB where mlpnbid='" +
          poMlpxxxxOld.getMlpnbid() + "' and jlbz='" + PublicConstant.JLBZ_ZX +
          "' and cxbz='" + PublicConstant.CXBZ_FCX + "' and hhzt='" +
          HjConstant.HHZT_ZC + "'";
      long hxxCount = super.getCount(strHQL); //该地上状态正常的户总数
      if (hxxCount <= 0) {
        //保存地信息为历史记录
        poMlpxxxxOld.setJssj(czsj);
        poMlpxxxxOld.setCdlb(HjConstant.CDLB_QHTZZX);
        poMlpxxxxOld.setCchjywid(hjywid);
        poMlpxxxxOld.setJlbz(PublicConstant.JLBZ_LS);
        super.update(poMlpxxxxOld);

        //保存地信息为最新记录并注销地(撤消地)
        PoHJXX_MLPXXXXB poMlpxxxxZX = new PoHJXX_MLPXXXXB();
        BeanUtils.copyProperties(poMlpxxxxZX, poMlpxxxxOld);
        poMlpxxxxZX.setMlpnbid( (Long) hjxx_mlpxxxxbDAO.getId());
        poMlpxxxxZX.setQysj(czsj);
        poMlpxxxxZX.setJssj(null);
        poMlpxxxxZX.setCdlb(null);
        poMlpxxxxZX.setJdlb(HjConstant.JDLB_QHTZJL);
        poMlpxxxxZX.setCchjywid(null);
        poMlpxxxxZX.setCjhjywid(hjywid);
        poMlpxxxxZX.setMlpzt(DzConstant.MLPZT_CX);
        poMlpxxxxZX.setJlbz(PublicConstant.JLBZ_ZX);
        super.create(poMlpxxxxZX);

      }
    }
    catch (ServiceException ex) {
      //事务回滚
      throw ex;
    }
    catch (DAOException ex) {
      //事务回滚
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return poMlpxxxxNew;
  }

  /**
   * 修改户信息上的人信息
   * @param hjywid Long - 户籍业务ID
   * @param old_poHxx PoHJXX_HXXB - 旧的户信息
   * @param new_poHxx PoHJXX_HXXB - 新的户信息
   * @param old_poMlpxxxx PoHJXX_MLPXXXXB - 旧的门(楼)牌号详细信息
   * @param new_poMlpxxxx PoHJXX_MLPXXXXB - 新的门(楼)牌号详细信息
   * @param gnbh String - 功能编号
   * @param qhtz_bdfw String - 区划调整变动范围
   * @param czsj String - 操作时间
   * @param voSbjbxx VoSbjbxx
   * @throws ServiceException
   * @throws DAOException
   */
  protected synchronized void changeRXX_HXX_QHTZ(Long hjywid,
                                                 PoHJXX_HXXB old_poHxx,
                                                 PoHJXX_HXXB new_poHxx,
                                                 PoHJXX_MLPXXXXB old_poMlpxxxx,
                                                 PoHJXX_MLPXXXXB new_poMlpxxxx,
                                                 String gnbh, String qhtz_bdfw,
                                                 String czsj, VoSbjbxx voSbjbxx) throws
      ServiceException,
      DAOException {

    String strHQL = null;
    List chxxList = new ArrayList();
    List rybdxxList = new ArrayList();
    boolean bAdd = false;
    boolean bDec = false;
    String qhtz_sdkzz = null;
    int qhtz_zdtzrs = 0;

    //数据校验
    if (hjywid == null || old_poHxx == null || new_poHxx == null ||
        old_poMlpxxxx == null || new_poMlpxxxx == null) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                 "传入的数据参数不够完整，修改户信息上的人信息无法完成。", null);
    }

    try {
    	PojoInfo hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
    	PojoInfo hjyw_zzbdxxbDAO = DAOFactory.createHJYW_ZZBDXXBDAO();
    	PojoInfo hjyw_chclxxbDAO = DAOFactory.createHJYW_CHCLXXBDAO();
    	PojoInfo hjyw_hcybdxxbDAO = DAOFactory.createHJYW_HCYBDXXBDAO();

    	PojoInfo xt_jwhxxbDAO = DAOFactory.createXT_JWHXXBDAO();

      //修改该户信息上的人信息 -- 不能删除order by ,如果没有order by子句 HIBERNATE会把 for update 加入到 where子句的括号中去，错误语句如: select * from hjtj_ryywbltlxxb where (ryid=2312312123123132 for update)
      strHQL = "from PoHJXX_CZRKJBXXB where jlbz='" +
          PublicConstant.JLBZ_ZX + "' and hhnbid='" +
          old_poHxx.getHhnbid() + "' and cxbz='" +
          PublicConstant.CXBZ_FCX + "' and ryzt='" +
          HjConstant.RYZT_ZC + "' order by rynbid "; //lock for update
      List ryxxList = super.findAllByHQL(strHQL);

      qhtz_sdkzz = this.getXTKZCS(PublicConstant.XTKZCS_SDRYSFYXQHTZ); //锁定人员是否允许区划调整的标记状态
      qhtz_zdtzrs = Integer.parseInt(getXTKZCS(PublicConstant.
                                               XTKZCS_QHTZMCTZDZDRS)); //区划调整每次调整的最大人数

      if (ryxxList != null && ryxxList.size() > 0) {
        PoHJXX_CZRKJBXXB poHzxx = this.findHZXX(ryxxList);
        //判断该户上总人数是否大于控制参数设定的值
        if (qhtz_zdtzrs == 0) {
          qhtz_zdtzrs = 300;
        }
        if (qhtz_zdtzrs > ryxxList.size()) {
          qhtz_zdtzrs = ryxxList.size();
        }
        for (int k = 0; k < qhtz_zdtzrs; k++) {
          PoHJXX_CZRKJBXXB poRyxx = (PoHJXX_CZRKJBXXB) ryxxList.get(k);
          //判断人员锁定状态是否为锁定状态
          //2006-5-26,胡斌,判断锁定人员是否允许区划调整的控制参数和是否区划调整。"F2101"
          if (! (PublicConstant.GNBH_DZ_QHTZYW.equals(gnbh) &&
                 "0".equals(qhtz_sdkzz.trim()))) {
            if (!HjConstant.RYSDZT_ZC.equals(poRyxx.getRysdzt())) {
              throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                         poRyxx.getXm().trim() + "(" +
                                         poRyxx.getGmsfhm() +
                                         ")的人员状态为锁定，地址修改业务无法完成。", null);
            }
          }
          //判断人员状态是否为正常状态
          if (HjConstant.RYZT_ZC.equals(poRyxx.getRyzt())) {
            //2016-12-18修改
            PoXT_JWHXXB poJwhxxb_QR = (PoXT_JWHXXB) super.get(PoXT_JWHXXB.class, new_poMlpxxxx.getJcwh());
            PoXT_JWHXXB poJwhxxb_QC = (PoXT_JWHXXB) super.get(PoXT_JWHXXB.class, old_poMlpxxxx.getJcwh());

            //置为历史记录
            poRyxx.setQczxlb(HjConstant.ZZBDLB_QT); //填充迁往地
            poRyxx.setQcrq(czsj.substring(0, 8));
            poRyxx.setQwdgjdq(null);
            poRyxx.setQwdssxq(new_poMlpxxxx.getSsxq());
            poRyxx.setQwdxz(this.generateZZ(new_poMlpxxxx,
                                            PublicConstant.XTKZCS_DZPZFS,
                                            PublicConstant.XTKZCS_DZPZFS_QCDXZ));
            poRyxx.setJssj(czsj);
            poRyxx.setCchjywid(hjywid);
            poRyxx.setJlbz(PublicConstant.JLBZ_LS);

            super.update(poRyxx);
            //保存新记录
            PoHJXX_CZRKJBXXB poRyxxNew = new PoHJXX_CZRKJBXXB();
            BeanUtils.copyProperties(poRyxxNew, poRyxx);
            BeanUtils.copyProperties(poRyxxNew, new_poHxx);
            BeanUtils.copyProperties(poRyxxNew, new_poMlpxxxx);
            poRyxxNew.setQczxlb(null);
            poRyxxNew.setQcrq(null);
            poRyxxNew.setQwdgjdq(null);
            poRyxxNew.setQwdssxq(null);
            poRyxxNew.setQwdxz(null);
            //Begin_注销代码原因是地址修改和全户变更都不修改常表何时来本址时间_洪亮_2005/03/17 14:00:00
            //if (PublicConstant.GNBH_DZ_DZXGYW.equals(gnbh)) {
            //  poRyxxNew.setHylbz(HjConstant.ZZBDLB_DZBD); //何因来本址
            // poRyxxNew.setHslbz(czsj.substring(0, 8)); //何时来本址
            // poRyxxNew.setHgjdqlbz(null); //何国家（地区）来本址
            // poRyxxNew.setHsssqlbz(old_poMlpxxxx.getSsxq()); //何省市县（区）来本址
            // poRyxxNew.setHxzlbz(this.generateZZ(old_poMlpxxxx,
            //                                      PublicConstant.XTKZCS_DZPZFS,
            //                                      PublicConstant.
            //                                      XTKZCS_DZPZFS_QCDXZ)); //何详址迁来
            //}
            //End_注销代码原因是地址修改和全户变更都不修改常表何时来本址时间_洪亮_2005/03/17 14:00:00
            poRyxxNew.setRynbid( (Long) hjxx_czrkjbxxbDAO.getId());
            if (new_poMlpxxxx != null) {
              poRyxxNew.setMlpnbid(new_poMlpxxxx.getMlpnbid());
            }
            poRyxxNew.setHhnbid(new_poHxx.getHhnbid());
            poRyxxNew.setJssj(null);
            poRyxxNew.setQysj(czsj);
            poRyxxNew.setXxqysj(czsj);//add by hb 20061027
            poRyxxNew.setYwnr(PublicConstant.GNBH_DZ_QHTZYW.equals(gnbh) ?
                              HjConstant.YWNR_QHTZ : HjConstant.YWNR_HSXBG);
            poRyxxNew.setCchjywid(null);
            poRyxxNew.setCjhjywid(hjywid);
            poRyxxNew.setJlbz(PublicConstant.JLBZ_ZX);
            //add by hh 20060327 区划调整记录变动范围
            poRyxxNew.setBdfw(qhtz_bdfw);
            //2016-12-18修改
            poRyxxNew.setCxfldm(poJwhxxb_QR.getCxfldm());

            super.create(poRyxxNew);

            if(!poJwhxxb_QC.getCxfldm().equals(poJwhxxb_QR.getCxfldm())){
                  String now = StringUtils.getServiceTime();
                  PojoInfo hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();

                  //迁入变化
                  PoHJXX_CXSXBGB log_QR = new PoHJXX_CXSXBGB();
                  log_QR.setBghcxsx(poJwhxxb_QR.getCxfldm());
                  log_QR.setBgqcxsx(poJwhxxb_QC.getCxfldm());
                  log_QR.setCjsj(now);
                  log_QR.setSldw(poJwhxxb_QR.getDwdm());
                  log_QR.setBgqdw(poJwhxxb_QC.getDwdm());
                  log_QR.setRynbid(poRyxxNew.getRynbid());
                  log_QR.setHjywid(hjywid);
                  log_QR.setBgyy(HjConstant.ZZBDLB_DZBD);
                  log_QR.setYwlb("snbd");
                  log_QR.setBz("住址变动,迁入");
                  log_QR.setRkbj("1");
                  log_QR.setSsxq(poRyxxNew.getSsxq());
                  log_QR.setJwhdm(poJwhxxb_QR.getDm());
                  log_QR.setRysl(new Long(1));
                  log_QR.setNyzyrklhczyydm(poRyxxNew.getNyzyrklhczyydm());
                  super.create(log_QR);

                  PoHJXX_CXSXBGB log_QC = new PoHJXX_CXSXBGB();
                  log_QC.setBghcxsx(poJwhxxb_QC.getCxfldm());
                  log_QC.setBgqcxsx(poJwhxxb_QR.getCxfldm());
                  log_QC.setCjsj(now);
                  log_QC.setSldw(poJwhxxb_QC.getDwdm());
                  log_QC.setBgqdw(poJwhxxb_QR.getDwdm());
                  log_QC.setRynbid(poRyxxNew.getRynbid());
                  log_QC.setHjywid(hjywid);
                  log_QC.setBgyy(HjConstant.ZZBDLB_DZBD);
                  log_QC.setYwlb("snbd");
                  log_QC.setBz("住址变动,迁出");
                  log_QC.setRkbj("0");
                  log_QC.setSsxq(poRyxxNew.getSsxq());
                  log_QC.setJwhdm(poJwhxxb_QC.getDm());
                  log_QC.setRysl(new Long(1));
                  super.create(log_QC);
            }


            //区划调整保存住址变动信息
            if (PublicConstant.GNBH_DZ_QHTZYW.equals(gnbh)) {
              PoHJYW_ZZBDXXB poZzbdxx = new PoHJYW_ZZBDXXB();
              poZzbdxx.setZzbdid( (Long) hjyw_zzbdxxbDAO.getId());
              //modi by hh 20060327 住址变动表中需要记录人信息
              //poZzbdxx.setRynbid(poRyxxNew.getRynbid());
              BeanUtils.copyProperties(poZzbdxx, poRyxxNew);
              poZzbdxx.setHb_q(poRyxx.getHb());
              poZzbdxx.setHh_q(poRyxx.getHh());
              poZzbdxx.setHlx_q(poRyxx.getHlx());
              poZzbdxx.setJcwh_q(poRyxx.getJcwh());
              poZzbdxx.setJlx_q(poRyxx.getJlx());
              poZzbdxx.setMlph_q(poRyxx.getMlph());
              poZzbdxx.setMlpid_q(poRyxx.getMlpid());
              poZzbdxx.setMlpnbid_q(poRyxx.getMlpnbid());
              poZzbdxx.setMlxz_q(poRyxx.getMlxz());
              poZzbdxx.setPcs_q(poRyxx.getPcs());
              poZzbdxx.setPxh_q(poRyxx.getPxh());
              poZzbdxx.setXzjd_q(poRyxx.getXzjd());
              poZzbdxx.setSsxq_q(poRyxx.getSsxq());
              poZzbdxx.setZrq_q(poRyxx.getZrq());
              poZzbdxx.setYhhid(poRyxx.getHhid());
              poZzbdxx.setSlsj(czsj);
              poZzbdxx.setSldw(this.getUser().getDwdm());
              poZzbdxx.setSlrid(this.getUser().getYhid());
              if (voSbjbxx != null)
                BeanUtils.copyProperties(poZzbdxx, voSbjbxx); //申报人信息
              poZzbdxx.setCdlb(new_poMlpxxxx.getCdlb());
              poZzbdxx.setJdlb(new_poMlpxxxx.getJdlb());
              poZzbdxx.setCdlb_q(old_poMlpxxxx.getCdlb());
              poZzbdxx.setJdlb_q(old_poMlpxxxx.getJdlb());

              poZzbdxx.setYhhnbid(poRyxx.getHhnbid());
              poZzbdxx.setZzbdlb(HjConstant.ZZBDLB_DZBD);
              poZzbdxx.setZzbdrq(czsj.substring(0, 8));
              poZzbdxx.setQyzbh(null);
              poZzbdxx.setBdfw(qhtz_bdfw);
              poZzbdxx.setHjywid(hjywid);
              poZzbdxx.setHzgmsfhm(poHzxx != null ? poHzxx.getGmsfhm() : null);
              poZzbdxx.setHzxm(poHzxx != null ? poHzxx.getXm() : null);
              poZzbdxx.setCzsm(new Long(0));
              poZzbdxx.setYwlx(PublicConstant.HJYWLS_YWLX_QHTZ);
              poZzbdxx.setCxfldm(poJwhxxb_QR.getCxfldm());
              poZzbdxx.setNyzyrklhczyydm("0");
              poZzbdxx.setCxsxtz_pdbz("0");
              poZzbdxx.setJjqx_pdbz("0");
              poZzbdxx.setZczjyhjzwnys_pdbz("0");

              if(poJwhxxb_QC!=null && poJwhxxb_QR!=null && poJwhxxb_QC.getCxfldm()!=null && poJwhxxb_QR.getCxfldm()!=null && poJwhxxb_QC.getCxfldm().length()>0 && poJwhxxb_QR.getCxfldm().length()>0){
                if (!poJwhxxb_QC.getCxfldm().substring(0,
                    1).equals(poJwhxxb_QR.getCxfldm().substring(0, 1))) {
                  poZzbdxx.setCxsxtz_pdbz("1");
                }
              }

              super.create(poZzbdxx);

              //重号信息消除(注销老的重号信息主要是处理重号所在的单位不同情况)
              strHQL = "from PoHJYW_CHCLXXB as HJYW_CHCLXXB where clfs='" +
                  HjConstant.CHCLFS_WCL + "' and chsfhm='" +
                  poRyxxNew.getGmsfhm() + "' and cxbz='" +
                  PublicConstant.CXBZ_FCX + "' ";
              List xcchxxList = super.findAllByHQL(strHQL);
              if (xcchxxList != null) {
                for (int j = 0; j < xcchxxList.size(); j++) {
                  PoHJYW_CHCLXXB poChclxx = (PoHJYW_CHCLXXB) xcchxxList.get(j);
                  //与别人重
                  if (poRyxxNew.getRyid().equals(poChclxx.getRyid())) {
                    poChclxx.setClfs(HjConstant.CHCLFS_BRQC);
                    poChclxx.setClhjywid(hjywid);
                    super.update(poChclxx);
                  }
                  //别人与自己重
                  if (poRyxxNew.getRyid().equals(poChclxx.getBchryid())) {
                    poChclxx.setClfs(HjConstant.CHCLFS_DFQC);
                    poChclxx.setClhjywid(hjywid);
                    super.update(poChclxx);
                  }
                } //for (int j = 0; j < chxxList.size(); j++)
              }
              //如果是重号则填充重号详细信息(增加新的重号信息主要是处理重号所在的单位不同情况)
              if (this.countCHRYXX(poRyxxNew.getGmsfhm()) > 1) {
                VoChxx ivoChxx = new VoChxx();
                ivoChxx.setBchryid(poRyxxNew.getRyid());
                ivoChxx.setChsfhm(poRyxxNew.getGmsfhm());
                chxxList.add(ivoChxx);
              }
              //add by hh 20060327 区划调整变动范围是派出所内调整的要生成户成员变动信息
              if (qhtz_bdfw != null &&
                  (Integer.parseInt(qhtz_bdfw) >=
                   Integer.parseInt(HjConstant.BDFW_BQNTS))) {
                //生成户成员变动信息
                PoHJYW_HCYBDXXB poHcybdxx_QC = new PoHJYW_HCYBDXXB();
                PoHJYW_HCYBDXXB poHcybdxx_QR = new PoHJYW_HCYBDXXB();
                if (voSbjbxx != null) {
                  BeanUtils.copyProperties(poHcybdxx_QC, voSbjbxx);
                  BeanUtils.copyProperties(poHcybdxx_QR, voSbjbxx);
                }
                //迁出户
                poHcybdxx_QC.setCxbz(PublicConstant.CXBZ_FCX);
                poHcybdxx_QC.setHcybdid( (Long) hjyw_hcybdxxbDAO.getId());
                poHcybdxx_QC.setRynbid(poRyxxNew.getRynbid());
                poHcybdxx_QC.setRyid(poRyxxNew.getRyid());
                poHcybdxx_QC.setYgx(poRyxx.getYhzgx());
                poHcybdxx_QC.setXgx(poRyxxNew.getYhzgx());
                poHcybdxx_QC.setHcybdlx(HjConstant.HCYBDLX_LH);
                poHcybdxx_QC.setHcybdlb(HjConstant.ZZBDLB_DZBD);
                poHcybdxx_QC.setHcybdrq(czsj.substring(0, 8));
                poHcybdxx_QC.setHjywid(hjywid);
                poHcybdxx_QC.setSldw(this.getUser().getDwdm());
                poHcybdxx_QC.setSlrid(this.getUser().getYhid());
                poHcybdxx_QC.setSlsj(czsj);
                //户成员变动信息表中增加人、地信息
                poHcybdxx_QC.setGmsfhm(poRyxx.getGmsfhm());
                poHcybdxx_QC.setXm(poRyxx.getXm());
                poHcybdxx_QC.setXb(poRyxx.getXb());
                poHcybdxx_QC.setMz(poRyxx.getMz());
                poHcybdxx_QC.setCsrq(poRyxx.getCsrq());
                poHcybdxx_QC.setCssj(poRyxx.getCssj());
                poHcybdxx_QC.setCsdssxq(poRyxx.getCsdssxq());
                poHcybdxx_QC.setZpid(poRyxx.getZpid());
                poHcybdxx_QC.setMlpid(poRyxx.getMlpid());
                poHcybdxx_QC.setSsxq(poRyxx.getSsxq());
                poHcybdxx_QC.setJlx(poRyxx.getJlx());
                poHcybdxx_QC.setMlph(poRyxx.getMlph());
                poHcybdxx_QC.setMlxz(poRyxx.getMlxz());
                poHcybdxx_QC.setPcs(poRyxx.getPcs());
                poHcybdxx_QC.setZrq(poRyxx.getZrq());
                poHcybdxx_QC.setXzjd(poRyxx.getXzjd());
                poHcybdxx_QC.setJcwh(poRyxx.getJcwh());
                poHcybdxx_QC.setPxh(poRyxx.getPxh());
                poHcybdxx_QC.setMlpid(poRyxx.getMlpid());
                poHcybdxx_QC.setHhid(poRyxx.getHhid());
                poHcybdxx_QC.setHhnbid(poRyxx.getHhnbid());
                poHcybdxx_QC.setHh(poRyxx.getHh());

                //lzh add here 20070214 添加户类型赋值
                poHcybdxx_QC.setHlx(poRyxxNew.getHlx());

                super.create(poHcybdxx_QC);
                //迁入户
                poHcybdxx_QR.setCxbz(PublicConstant.CXBZ_FCX);
                poHcybdxx_QR.setHcybdid( (Long) hjyw_hcybdxxbDAO.getId());
                poHcybdxx_QR.setRynbid(poRyxxNew.getRynbid());
                poHcybdxx_QR.setRyid(poRyxxNew.getRyid());
                poHcybdxx_QR.setYgx(poRyxx.getYhzgx());
                poHcybdxx_QR.setXgx(poRyxxNew.getYhzgx());
                poHcybdxx_QR.setHcybdlx(HjConstant.HCYBDLX_RH);
                poHcybdxx_QR.setHcybdlb(HjConstant.ZZBDLB_DZBD);
                poHcybdxx_QR.setHcybdrq(czsj.substring(0, 8));
                poHcybdxx_QR.setHjywid(hjywid);
                poHcybdxx_QR.setSldw(this.getUser().getDwdm());
                poHcybdxx_QR.setSlrid(this.getUser().getYhid());
                poHcybdxx_QR.setSlsj(czsj);
                //户成员变动信息表中增加人、地信息
                poHcybdxx_QR.setGmsfhm(poRyxxNew.getGmsfhm());
                poHcybdxx_QR.setXm(poRyxxNew.getXm());
                poHcybdxx_QR.setXb(poRyxxNew.getXb());
                poHcybdxx_QR.setMz(poRyxxNew.getMz());
                poHcybdxx_QR.setCsrq(poRyxxNew.getCsrq());
                poHcybdxx_QR.setCssj(poRyxxNew.getCssj());
                poHcybdxx_QR.setCsdssxq(poRyxxNew.getCsdssxq());
                poHcybdxx_QR.setZpid(poRyxxNew.getZpid());
                poHcybdxx_QR.setMlpid(poRyxxNew.getMlpid());
                poHcybdxx_QR.setSsxq(poRyxxNew.getSsxq());
                poHcybdxx_QR.setJlx(poRyxxNew.getJlx());
                poHcybdxx_QR.setMlph(poRyxxNew.getMlph());
                poHcybdxx_QR.setMlxz(poRyxxNew.getMlxz());
                poHcybdxx_QR.setPcs(poRyxxNew.getPcs());
                poHcybdxx_QR.setZrq(poRyxxNew.getZrq());
                poHcybdxx_QR.setXzjd(poRyxxNew.getXzjd());
                poHcybdxx_QR.setJcwh(poRyxxNew.getJcwh());
                poHcybdxx_QR.setPxh(poRyxxNew.getPxh());
                poHcybdxx_QR.setMlpid(poRyxxNew.getMlpid());
                poHcybdxx_QR.setHhid(poRyxxNew.getHhid());
                poHcybdxx_QR.setHhnbid(poRyxxNew.getHhnbid());
                poHcybdxx_QR.setHh(poRyxxNew.getHh());

                //lzh add here 20070214 添加户类型赋值
                poHcybdxx_QR.setHlx(poRyxxNew.getHlx());

                super.create(poHcybdxx_QR);
              }

              //生成人员变动信息(四变)
              //迁出
              VoRybdxx voRybdxxQC = new VoRybdxx();
              voRybdxxQC.setBdbid(poZzbdxx.getZzbdid());
              voRybdxxQC.setBdfw(poZzbdxx.getBdfw());
              voRybdxxQC.setBdyy(poZzbdxx.getZzbdlb());
              voRybdxxQC.setBdrq(poZzbdxx.getZzbdrq());
              voRybdxxQC.setBdqhb(null);
              voRybdxxQC.setBdq_hhnbid(poZzbdxx.getYhhnbid());
              voRybdxxQC.setBdh_hhnbid(poRyxxNew.getHhnbid());
              voRybdxxQC.setRynbid(poRyxx.getRynbid());
              voRybdxxQC.setRzjs(new Long( -1));

              if (!bDec) {
                bDec = true;
                voRybdxxQC.setHzjs(new Long( -1));
              }
              else {
                voRybdxxQC.setHzjs(new Long(0));
              }
              rybdxxList.add(voRybdxxQC);
              //迁入
              VoRybdxx voRybdxxQR = new VoRybdxx();
              voRybdxxQR.setBdbid(poZzbdxx.getZzbdid());
              voRybdxxQR.setBdfw(poZzbdxx.getBdfw());
              voRybdxxQR.setBdyy(poZzbdxx.getZzbdlb());
              voRybdxxQR.setBdrq(poZzbdxx.getZzbdrq());
              voRybdxxQR.setBdqhb(poRyxx.getHb());
              voRybdxxQR.setBdq_hhnbid(poZzbdxx.getYhhnbid());
              voRybdxxQR.setBdh_hhnbid(poRyxxNew.getHhnbid());
              voRybdxxQR.setRynbid(poRyxxNew.getRynbid());
              voRybdxxQR.setRzjs(new Long(1));
              if (!bAdd) {
                bAdd = true;
                voRybdxxQR.setHzjs(new Long(1));
              }
              else {
                voRybdxxQR.setHzjs(new Long(0));
              }
              rybdxxList.add(voRybdxxQR);
            } //if (PublicConstant.GNBH_DZ_QHTZYW.equals(gnbh))
          } //if(poRyxx.getRyzt().equalsIgnoreCase(HjConstant.RYZT_ZC))
        } //for (int k = 0; k < ryxxList.size(); k++)

        //保存重号信息
        this.saveCHXX(hjywid, chxxList, czsj);

        //////////////////////////////////////////
        //保存人员变动信息(四变)
        PoHJLS_HJYWLSB poHjywlsxx = new PoHJLS_HJYWLSB();
        poHjywlsxx.setHjywid(hjywid);
        poHjywlsxx.setCzsm(new Long(0));
        poHjywlsxx.setYwbz(PublicConstant.GNBH_DZ_QHTZYW);
        poHjywlsxx.setYwlx(PublicConstant.HJYWLS_YWLX_QHTZ);
        poHjywlsxx.setSldw(this.getUser().getDwdm());
        poHjywlsxx.setSlrid(this.getUser().getYhid());
        poHjywlsxx.setSlsj(czsj);
        this.saveRYBDXX(rybdxxList, poHjywlsxx);
      } //if(ryxxList!=null && ryxxList.size() >0)
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return;
  }

  /**
   * 获取weblogic中的JNDI（psJndi)连接connection
   * @param psJndi String
   * @return Connection
   */
  public Connection getConn(String psJndi) {
    return null;
  }

  /**
   * 公民身份号码前6位行政区划校验，跨省迁入人员重号校验
   * @param voQrdjxx VoQrdjxx
   */
  public void parseWSgmsfhm(VoQrdjxx voQrdjxx, VoGmsfhmfpfhxx voGmsfhmfpfhxx) throws
      ServiceException, DAOException, Exception{
	  
    String gmsfhm;
    if (voGmsfhmfpfhxx != null) {
      gmsfhm = voGmsfhmfpfhxx.getGmsfhm();
    }else{
      gmsfhm = voQrdjxx.getGmsfhm();
    }
    String ssxq = gmsfhm.substring(0,6);
    String sql = "from PoXT_XZQHB where dm='"+ssxq+"'";
    String sqlcrcx = "select * from xx_czrk where czrkzxbs is null and czrkgmsfhm='" + gmsfhm +"'";

    //公民身份号码前6位是否存在于行政区划表中 [1-不允许][非1-允许]
    String kzcs1031 = getXtkzcsAsStr("1031");
    if ("1".equals(kzcs1031)){
    	PojoInfo xt_dwxxbDAO = DAOFactory.createXT_DWXXBDAO();
      List lst = super.findAllByHQL(sql);
      if (lst.size() <= 0) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_DATASCOPE,
                                       "该人公民身份号码错误-非法行政区划代码！", null);
      }
    }

    //跨省迁入人员重号校验 [1-启用][非1-不启用]
    String kzcs1030 = getXtkzcsAsStr("1030");
    if ("1".equals(kzcs1030)){
      Connection conn = null;
      PreparedStatement preparedstatement = null;
      ResultSet rs = null;

      try {
        conn = getConn("ydcjjndi");
        if (conn == null) { //如果连接不上数据库
          throw new DAOException(WSErrCode.ERR_ACTION_SERVICE_NOTEXIST,
                                 "无法建立与省库连接", null);
        }
        else {
          /* 设定不自动提交 */
          conn.setAutoCommit(false);

          preparedstatement = conn.prepareStatement(sqlcrcx.toString());
          rs = preparedstatement.executeQuery(sqlcrcx.toString());
          if (rs.next()) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                       "该公民身份号码属于省内重号，不允许迁入！", null);
          }
        }
      }
      catch (ServiceException ex) {
        throw ex;
      }
      catch (DAOException ex) {
        throw ex;
      }
      catch (Exception ex) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
      }
      finally {
        //关闭游标
        DbUtils.close(conn);
        DbUtils.close(rs);
        if (preparedstatement != null) {
          preparedstatement.close();
        }
      }
    }


  }

}
