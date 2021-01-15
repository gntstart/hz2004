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
public class XthhxlServiceImpl
    extends XtBaseService

    implements XthhxlService {

  PojoInfo   Xt_hhxlbDao = DAOFactory.createXT_HHXLBDAO();
  PojoInfo   Xt_XtrzbDao = DAOFactory.createXT_XTRZBDAO();
  /**
   * 通过字符串查询信息
   * @param pxQryXtcs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXthhxlxx(String sHqlXthhxl) throws ServiceException,
      DAOException {
    return getXthhxlxx(sHqlXthhxl, PublicConstant.XT_PAGE,
                       PublicConstant.XT_PAGESIZE);
  }

  public List getXthhxlxx(String sHqlXthhxl, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    //执行HQL语句
    List lstXtRtn = null;
    List lstReturn = new ArrayList();
    PoXT_HHXLB poXT_HHXLB = null;
    PoXT_DWXXB poXT_DWXXB = null;
    Object[] objXtRtn = null;
    try {
      lstXtRtn =super.getPageRecords(sHqlXthhxl, new Object[]{}, nPageOffset,
                                                  nPageSize).getList();
      for (int i = 0; i < lstXtRtn.size(); i++) {
        objXtRtn = (Object[]) lstXtRtn.get(i);
        poXT_HHXLB = (PoXT_HHXLB) objXtRtn[0];
        poXT_DWXXB = (PoXT_DWXXB) objXtRtn[1];
        VoXthhxl voXthhxl = new VoXthhxl(poXT_HHXLB, poXT_DWXXB);
        lstReturn.add(voXthhxl);
      }
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw  ex;
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
  public List getXthhxlxx(VarVO vvQryXthhxl) throws ServiceException,
      DAOException {
    List lstXthhxlxx = null;
    int nPageOffset = PublicConstant.XT_PAGE; //分页
    int nPageSize = PublicConstant.XT_PAGESIZE;
    //获取参数信息
    //xlid,dwdm,hhxlid;
    String sXlid = vvQryXthhxl.getVarValue("xlid");
    String sDwdm = vvQryXthhxl.getVarValue("dwdm");
    String sQhdm = vvQryXthhxl.getVarValue("qhdm");
    String sHhxlid = vvQryXthhxl.getVarValue("hhxlid");

    //HQL条件语句
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        "select XT_HHXLB,XT_DWXXB FROM PoXT_HHXLB as XT_HHXLB,PoXT_DWXXB as XT_DWXXB")
        .append(" where XT_HHXLB.dwdm = XT_DWXXB.dm ");
    if ( (sXlid != null) && ! ("".equals(sXlid))) {
      strBufHql.append(" and XT_HHXLB.xlid =").append(sXlid);
    }
    if ( (sDwdm != null) && ! ("".equals(sDwdm))) {
      strBufHql.append(" and XT_HHXLB.dwdm =").append(sDwdm);
    }
    if ( (sQhdm != null) && ! ("".equals(sQhdm))) {
        strBufHql.append(" and XT_DWXXB.qhdm =").append(sQhdm);
    }
    if ( (sHhxlid != null) && ! ("".equals(sHhxlid))) {
      strBufHql.append(" and XT_HHXLB.hhxlid =").append(sHhxlid);
    }
    strBufHql.append(" order by XT_HHXLB.xlid");
    //执行HQL语句
    return getXthhxlxx(strBufHql.toString(), nPageOffset, nPageSize);
  }

  /**
   *增加信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXthhxlxx(VarVO vvXthhxlb) throws ServiceException,
      DAOException {
    ArrayList lstXthhxl = new ArrayList();
    PoXT_HHXLB poXTHHXLB = new PoXT_HHXLB();
    PoXT_HHXLB rtnpoXTHHXLB = new PoXT_HHXLB();
    String sHhxlid = vvXthhxlb.getVarValue("hhxlid");
    List lstXtRtn = new ArrayList();
    //获取参数信息
    //xlid,dwdm,hhxlid;
    try {
      /**
       * 首先判断增加的户号是否已经存在
       */
      String sHql =
          "select XT_HHXLB FROM PoXT_HHXLB as XT_HHXLB where hhxlid ='" +
          sHhxlid + "'";
      lstXtRtn =super.getPageRecords(sHql, new Object[]{}, PublicConstant.XT_PAGE,
                                                  PublicConstant.XT_PAGESIZE).getList();
      if (lstXtRtn != null && lstXtRtn.size() > 0) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                   "户号已经存在", null);
      }
      BeanUtils.populate(poXTHHXLB, vvXthhxlb.getVarmap());
      poXTHHXLB.setXlid( (Long) Xt_hhxlbDao.getId());
      rtnpoXTHHXLB =(PoXT_HHXLB)super.create(poXTHHXLB);
      lstXthhxl.add(rtnpoXTHHXLB);
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex.getCause());
    }
    return lstXthhxl;
  }

  /**
   * 修改信息
   * @param voXtgbcsb
   * @param atUser
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXthhxlxgxx(VarVO vvXthhxlb) throws ServiceException,
      DAOException {
    ArrayList lstXthhxl = new ArrayList();
    String sHql = null;
    PoXT_HHXLB poXTHHXLB = null;
    PoXT_HHXLB rtnpoXTHHXLB = new PoXT_HHXLB();
    //组合poXT_XTCSB
    //xlid,dwdm,hhxlid;
    try {
      poXTHHXLB  = super.get(PoXT_HHXLB.class,Long.valueOf(vvXthhxlb.
          getVarValue("xlid")));
      if (poXTHHXLB == null) {
        return null;
      }
      BeanUtils.populate(poXTHHXLB, vvXthhxlb.getVarmap());
      rtnpoXTHHXLB =      (PoXT_HHXLB)super.update(poXTHHXLB);
      lstXthhxl.add(rtnpoXTHHXLB);
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
    return lstXthhxl;
  }

  /**
   * 删除信息
   * @param voXtxzqhb
   * @param atUser
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXthhxlscxx(VarVO vvXthhxlb) throws ServiceException,
      DAOException {
    String sHql = null;
    PoXT_HHXLB poXThhxlB = new PoXT_HHXLB();
    PoXT_HHXLB rtnpoXThhxlB = new PoXT_HHXLB();
    ArrayList lstXthhxl = new ArrayList();
    int nReturn = PublicConstant.XT_BZ_ERROR;
    //组合poXT_XTCSB
    try {
      BeanUtils.populate(poXThhxlB, vvXthhxlb.getVarmap());
      super.delete(poXThhxlB);
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
