package com.hzjc.hz2004.service.impl;

import java.util.*;
import com.hzjc.hz2004.dao.*;
import com.hzjc.hz2004.po.*;
import com.hzjc.hz2004.service.*;
import com.hzjc.hz2004.constant.*;
import com.hzjc.hz2004.vo.*;
import com.hzjc.wsstruts.exception.*;
import com.hzjc.wsstruts.type.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.hzjc.wsstruts.vo.*;
import java.lang.reflect.*;
import com.hzjc.hz2004.base.login.AuthToken;
import com.hzjc.util.StringUtils;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 户号序列操作实现</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */
@Service
public class XtslhxlServiceImpl
    extends XtBaseService

    implements XtslhxlService {

  PojoInfo   Xt_slhxlbDao = DAOFactory.createXT_SLHXLBDAO();
  PojoInfo   Xt_XtrzbDao = DAOFactory.createXT_XTRZBDAO();
  /**
   * 通过字符串查询信息
   * @param pxQryXtcs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtslhxlxx(String sHqlXtslhxl) throws ServiceException,
      DAOException {
    return getXtslhxlxx(sHqlXtslhxl, PublicConstant.XT_PAGE,
                        PublicConstant.XT_PAGESIZE);
  }

  public List getXtslhxlxx(String sHqlXtslhxl, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    //执行HQL语句
    List lstXtRtn = null;
    List lstReturn = new ArrayList();
    PoXT_SLHXLB poXT_SLHXLB = null;
    PoXT_DWXXB poXT_DWXXB = null;
    Object[] objXtRtn = null;
    try {
      lstXtRtn =super.getPageRecords(sHqlXtslhxl,new Object[]{}, nPageOffset,
          nPageSize).getList();
      for (int i = 0; i < lstXtRtn.size(); i++) {
        objXtRtn = (Object[]) lstXtRtn.get(i);
        poXT_SLHXLB = (PoXT_SLHXLB) objXtRtn[0];
        poXT_DWXXB = (PoXT_DWXXB) objXtRtn[1];
        VoXtslhxl voXtslhxl = new VoXtslhxl(poXT_SLHXLB, poXT_DWXXB);
        lstReturn.add(voXtslhxl);
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
    return lstReturn;
  }

  /**
   *查询信息
   * @param pxQryXtcs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtslhxlxx(VarVO vvQryXtslhxl) throws ServiceException,
      DAOException {
    List lstXtslhxlxx = null;
    int nPageOffset = PublicConstant.XT_PAGE; //分页
    int nPageSize = PublicConstant.XT_PAGESIZE;
    //获取参数信息
    //xlid,dwdm,slrq,slxlid;
    String sXlid = vvQryXtslhxl.getVarValue("xlid");
    String sDwdm = vvQryXtslhxl.getVarValue("dwdm");
    String sSlrq = vvQryXtslhxl.getVarValue("slrq");
    String sSlhxlid = vvQryXtslhxl.getVarValue("slxlid");

    //HQL条件语句
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        "select XT_SLHXLB,XT_DWXXB FROM PoXT_SLHXLB as XT_SLHXLB,PoXT_DWXXB as XT_DWXXB")
        .append(" where XT_SLHXLB.dwdm= XT_DWXXB.dm ");
    if ( (sXlid != null) && ! ("".equals(sXlid))) {
      strBufHql.append(" and XT_SLHXLB.xlid =").append(sXlid);
    }
    if ( (sDwdm != null) && ! ("".equals(sDwdm))) {
      strBufHql.append(" and XT_SLHXLB.dwdm =").append(sDwdm);
    }
    if ( (sSlhxlid != null) && ! ("".equals(sSlhxlid))) {
      strBufHql.append(" and XT_SLHXLB.slxlid =").append(sSlhxlid);
    }
    if ( (sSlrq != null) && ! ("".equals(sSlrq))) {
      strBufHql.append(" and XT_SLHXLB.slrq =").append(sSlrq);
    }
    strBufHql.append(" order by XT_SLHXLB.xlid");
    //执行HQL语句
    return getXtslhxlxx(strBufHql.toString(), nPageOffset, nPageSize);
  }

  /**
   *增加信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtslhxlxx(VarVO vvXtslhxlb) throws ServiceException,
      DAOException {
    ArrayList lstXtslhxl = new ArrayList();
    PoXT_SLHXLB poXTSLHXLB = new PoXT_SLHXLB();
    PoXT_SLHXLB rtnpoXTSLHXLB = new PoXT_SLHXLB();
    //获取参数信息
    //xlid,dwdm,slhxlid;
    try {
      BeanUtils.populate(poXTSLHXLB, vvXtslhxlb.getVarmap());
      poXTSLHXLB.setXlid( (Long) Xt_slhxlbDao.getId());
      rtnpoXTSLHXLB =(PoXT_SLHXLB)super.create(poXTSLHXLB);
      lstXtslhxl.add(rtnpoXTSLHXLB);
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
    return lstXtslhxl;
  }

  /**
   * 修改信息
   * @param voXtgbcsb
   * @param atUser
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtslhxlxgxx(VarVO vvXtslhxlb) throws ServiceException,
      DAOException {
    ArrayList lstXtslhxl = new ArrayList();
    PoXT_SLHXLB poXTSLHXLB = null;
    PoXT_SLHXLB rtnpoXTSLHXLB = new PoXT_SLHXLB();
    //组合poXT_XTCSB
    //xlid,dwdm,slhxlid;
    try {
      poXTSLHXLB  = super.get(PoXT_SLHXLB.class,Long.valueOf(vvXtslhxlb.
          getVarValue("xlid")));
      if (poXTSLHXLB == null) {
        return null;
      }
      BeanUtils.populate(poXTSLHXLB, vvXtslhxlb.getVarmap());
      rtnpoXTSLHXLB =      (PoXT_SLHXLB)super.update(poXTSLHXLB);
      lstXtslhxl.add(rtnpoXTSLHXLB);
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
    return lstXtslhxl;
  }

  /**
   * 删除信息
   * @param voXtxzqhb
   * @param atUser
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtslhxlscxx(VarVO vvXtslhxlb) throws ServiceException,
      DAOException {
    PoXT_SLHXLB poXTSLHXLB = new PoXT_SLHXLB();
    PoXT_SLHXLB rtnpoXTSLHXLB = new PoXT_SLHXLB();
    ArrayList lstXtslhxl = new ArrayList();
    int nReturn = PublicConstant.XT_BZ_ERROR;
    //组合poXT_XTCSB
    try {
      BeanUtils.populate(poXTSLHXLB, vvXtslhxlb.getVarmap());
      super.delete(poXTSLHXLB);
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
    return nReturn;
  }

}
