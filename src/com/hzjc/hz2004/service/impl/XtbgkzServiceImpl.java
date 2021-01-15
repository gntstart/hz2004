package com.hzjc.hz2004.service.impl;

import java.util.*;
import com.hzjc.hz2004.dao.*;
import com.hzjc.hz2004.po.*;
import com.hzjc.hz2004.service.*;
import com.hzjc.hz2004.vo.*;
import com.hzjc.wsstruts.exception.*;
import com.hzjc.wsstruts.type.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.hzjc.wsstruts.vo.*;
import java.lang.reflect.*;
import com.hzjc.util.StringUtils;
import com.hzjc.hz2004.base.login.AuthToken;
import com.hzjc.hz2004.constant.*;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 变更控制操作实现</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */
@Service
public class XtbgkzServiceImpl
    extends XtBaseService
    implements XtbgkzService {

	 PojoInfo   Xt_bgdykzbDao = DAOFactory.createHJXZ_BGDYZDKZBDAO();
	 //PojoInfo    Xt_bgspkzbDao = DAOFactory.createHJXZ_BGSPZDKZBDAO();
	 PojoInfo   Xt_XtrzbDao = DAOFactory.createXT_XTRZBDAO();
	 
  /**
   * 字符串查询
   * @param strHQL
   * @param nPageOffset
   * @param nPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtbgdykzxx(String strHQL) throws ServiceException,
      DAOException {
    return getXtbgdykzxx(strHQL, PublicConstant.XT_PAGE,
                         PublicConstant.XT_PAGESIZE);
  }

  public List getXtbgdykzxx(String strHQL, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    List lstXtbgdykzxx = null;
    //执行HQL语句
    try {
      lstXtbgdykzxx =super.getPageRecords(strHQL,new Object[]{},
          nPageOffset, nPageSize).getList();
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
    return lstXtbgdykzxx;
  }

  /**
   *查询变更控制信息
   * @param iReqPage
   * @param iPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtbgdykzxx(VarVO vvHqlQryXtbgkz) throws ServiceException,
      DAOException {
    int nPageOffset = PublicConstant.XT_PAGE; //分页
    int nPageSize = PublicConstant.XT_PAGESIZE;
    //获取参数信息
    //zdkzid,zdmc,zdhy,kzbz

    String sZdkzid = "", sZdmc = "", sZdhy = "";
    String sKzbz = "";

    sZdkzid = vvHqlQryXtbgkz.getVarValue("zdkzid");
    sZdmc = vvHqlQryXtbgkz.getVarValue("zdmc");
    sZdhy = vvHqlQryXtbgkz.getVarValue("zdhy");
    sKzbz = vvHqlQryXtbgkz.getVarValue("kzbz");

    //HQL条件语句
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        "select HJXZ_BGDYZDKZB FROM PoHJXZ_BGDYZDKZB as HJXZ_BGDYZDKZB where 1=1 ");
    if ( (sZdkzid != null) && ! ("".equals(sZdkzid))) {
      strBufHql.append(" and  zdkzid =").append(sZdkzid);
    }
    if ( (sZdmc != null) && ! ("".equals(sZdmc))) {
      strBufHql.append(" and  zdmc =").append(sZdmc);
    }
    if ( (sZdhy != null) && ! ("".equals(sZdhy))) {
      strBufHql.append(" and  zdhy =").append(sZdhy);
    }
    if ( (sKzbz != null) && ! ("".equals(sKzbz))) {
      strBufHql.append(" and  kzbz =").append(sKzbz);
    }

    strBufHql.append(" order by zdmc");
    return getXtbgdykzxx(strBufHql.toString(), nPageOffset, nPageSize);
  }

  /**
   *增加变更打印控制信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtbgdykzxx(VarVO vvXTbgkzB) throws ServiceException,
      DAOException {
    ArrayList lstXtbgkz = new ArrayList();
    PoHJXZ_BGDYZDKZB poXtbgkzb = new PoHJXZ_BGDYZDKZB();
    try {
      //执行
      BeanUtils.populate(poXtbgkzb, vvXTbgkzB.getVarmap());
      poXtbgkzb.setZdkzid( (Long) Xt_bgdykzbDao.getId());
      PoHJXZ_BGDYZDKZB rtnpoXTbgkzB =(PoHJXZ_BGDYZDKZB)super.create(
          poXtbgkzb);
      lstXtbgkz.add(rtnpoXTbgkzB);
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
    return lstXtbgkz;
  }

  /**
   *修改变更打印控制信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtbgdykzxgxx(VarVO vvXTbgkzB) throws ServiceException,
      DAOException {
    ArrayList lstXtbgkz = new ArrayList();
    PoHJXZ_BGDYZDKZB poXtbgkzb = null;
    PoHJXZ_BGDYZDKZB rtnpoXTbgkzB = new PoHJXZ_BGDYZDKZB();
    //获取参数信息,设置PO语句
    try {
      poXtbgkzb  = super.get(PoHJXZ_BGDYZDKZB.class,Long.valueOf(vvXTbgkzB.
          getVarValue("zdkzid")));
      if (poXtbgkzb == null) { //如果查询不到记录
        return null;
      }
      BeanUtils.populate(poXtbgkzb, vvXTbgkzB.getVarmap());
      rtnpoXTbgkzB =(PoHJXZ_BGDYZDKZB)super.update(poXtbgkzb);
      lstXtbgkz.add(rtnpoXTbgkzB);
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
    return lstXtbgkz;

  }

  /**
   *删除变更打印控制信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtbgdykzscxx(VarVO vvXTbgkzB) throws ServiceException,
      DAOException {
    PoHJXZ_BGDYZDKZB rtnpoXTbgkzB = new PoHJXZ_BGDYZDKZB();
    PoHJXZ_BGDYZDKZB poXTbgkzB = new PoHJXZ_BGDYZDKZB();
    int nReturn = PublicConstant.XT_BZ_ERROR;

    //执行HQL语句
    try {
      BeanUtils.populate(poXTbgkzB, vvXTbgkzB.getVarmap());
      super.delete(poXTbgkzB);
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
    return nReturn;
  }

  /**
   * 字符串查询
   * @param strHQL
   * @param nPageOffset
   * @param nPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtbgspkzxx(String strHQL) throws ServiceException,
      DAOException {
    return getXtbgspkzxx(strHQL, PublicConstant.XT_PAGE,
                         PublicConstant.XT_PAGESIZE);
  }

  public List getXtbgspkzxx(String strHQL, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    List lstXtbgspkzxx = null;
    /*PoHJXZ_BGSPZDKZB poXtbgspzdkz = null;
    PoXT_SPMBXXB poXT_SPMBXXB = null;
    Object[] objXtRtn = null;
    List lstReturn = null;
    //执行HQL语句
    try {
      lstXtbgspkzxx =super.getPageRecords(strHQL,
          nPageOffset, nPageSize);
      if (lstXtbgspkzxx != null && !lstXtbgspkzxx.isEmpty()) {
        lstReturn = new ArrayList();
        for (int i = 0; i < lstXtbgspkzxx.size(); i++) {
          objXtRtn = (Object[]) lstXtbgspkzxx.get(i);
          poXtbgspzdkz = (PoHJXZ_BGSPZDKZB) objXtRtn[0];
          poXT_SPMBXXB = (PoXT_SPMBXXB) objXtRtn[1];
          VoXtbgspkz voXtbgspkz = new VoXtbgspkz(poXtbgspzdkz, poXT_SPMBXXB);
          lstReturn.add(voXtbgspkz);
        }

      }
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
*/    return null;
  }

  /**
   *查询变更控制信息
   * @param iReqPage
   * @param iPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtbgspkzxx(VarVO vvHqlQryXtbgkz) throws ServiceException,
      DAOException {
    int nPageOffset = PublicConstant.XT_PAGE; //分页
    int nPageSize = PublicConstant.XT_PAGESIZE;
    //获取参数信息
    //zdkzid,zdmc,zdhy,kzbz

    String sZdkzid = "", sZdmc = "", sZdhy = "";
    String sSpbz = "", sSpmbid = "";

    sZdkzid = vvHqlQryXtbgkz.getVarValue("zdkzid");
    sZdmc = vvHqlQryXtbgkz.getVarValue("zdmc");
    sZdhy = vvHqlQryXtbgkz.getVarValue("zdhy");
    sSpbz = vvHqlQryXtbgkz.getVarValue("spbz");
    sSpmbid = vvHqlQryXtbgkz.getVarValue("spmbid");
    //HQL条件语句
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        "select HJXZ_BGSPZDKZB,XT_SPMBXXB FROM PoHJXZ_BGSPZDKZB as HJXZ_BGSPZDKZB,")
        .append(" PoXT_SPMBXXB as XT_SPMBXXB where ")
        .append(" HJXZ_BGSPZDKZB.spmbid = XT_SPMBXXB.spmbid(+)");
    if ( (sZdkzid != null) && ! ("".equals(sZdkzid))) {
      strBufHql.append(" and  HJXZ_BGSPZDKZB.zdkzid =").append(sZdkzid);
    }
    if ( (sZdmc != null) && ! ("".equals(sZdmc))) {
      strBufHql.append(" and  HJXZ_BGSPZDKZB.zdmc ='").append(sZdmc).append("'");
    }
    if ( (sZdhy != null) && ! ("".equals(sZdhy))) {
      strBufHql.append(" and  HJXZ_BGSPZDKZB.zdhy ='").append(sZdhy).append("'");
    }
    if ( (sSpbz != null) && ! ("".equals(sSpbz))) {
      strBufHql.append(" and  HJXZ_BGSPZDKZB.spbz ='").append(sSpbz).append("'");
    }
    if ( (sSpmbid != null) && ! ("".equals(sSpmbid))) {
      strBufHql.append(" and  HJXZ_BGSPZDKZB.spmbid =").append(sSpmbid);
    }

    strBufHql.append(" order by HJXZ_BGSPZDKZB.zdmc");
    return getXtbgspkzxx(strBufHql.toString(), nPageOffset, nPageSize);
  }

  /**
   *增加变更审批控制信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtbgspkzxx(VarVO vvXTbgkzB) throws ServiceException,
      DAOException {
    ArrayList lstXtbgkz = new ArrayList();
/*    PoHJXZ_BGSPZDKZB poXtbgkzb = new PoHJXZ_BGSPZDKZB();
    PoHJXZ_BGSPZDKZB rtnpoXTbgkzB = new PoHJXZ_BGSPZDKZB();
    try {
      //执行
      BeanUtils.populate(poXtbgkzb, vvXTbgkzB.getVarmap());
      poXtbgkzb.setZdkzid( (Long) Xt_bgdykzbDao.getId());
      rtnpoXTbgkzB =(rtnpoXTbgkzB)super.create(poXtbgkzb);
      lstXtbgkz.add(rtnpoXTbgkzB);
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
*/    return lstXtbgkz;
  }

  /**
   *修改变更打印控制信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtbgspkzxgxx(VarVO vvXTbgkzB) throws ServiceException,
      DAOException {
    ArrayList lstXtbgkz = new ArrayList();
/*    PoHJXZ_BGSPZDKZB poXtbgkzb = null;
    PoHJXZ_BGSPZDKZB rtnpoXTbgkzB = new PoHJXZ_BGSPZDKZB();
    //获取参数信息,设置PO语句
    try {
      poXtbgkzb  = super.get(.class,Long.valueOf(vvXTbgkzB.
          getVarValue("zdkzid")));
      if (poXtbgkzb == null) { //如果查询不到记录
        return null;
      }
      BeanUtils.populate(poXtbgkzb, vvXTbgkzB.getVarmap());
      rtnpoXTbgkzB =      rtnpoXTbgkzB =super.update(poXtbgkzb);
      lstXtbgkz.add(rtnpoXTbgkzB);
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
  */  return lstXtbgkz;

  }

  /**
   *删除变更打印控制信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtbgspkzscxx(VarVO vvXTbgkzB) throws ServiceException,
      DAOException {
/*    PoHJXZ_BGSPZDKZB rtnpoXTbgkzB = new PoHJXZ_BGSPZDKZB();
    PoHJXZ_BGSPZDKZB poXTbgkzB = new PoHJXZ_BGSPZDKZB();
    int nReturn = PublicConstant.XT_BZ_ERROR;

    //执行HQL语句
    try {
      BeanUtils.populate(poXTbgkzB, vvXTbgkzB.getVarmap());
      Xt_bgspkzbDao.deleteHJXZ_BGSPZDKZB(poXTbgkzB);
      nReturn = PublicConstant.XT_BZ_SUCC;
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
 */   return 0;
  }

}
