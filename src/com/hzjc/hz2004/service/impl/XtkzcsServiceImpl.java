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
import com.hzjc.hz2004.common.HjCommon;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 系统控制参数操作实现</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */
@Service
public class XtkzcsServiceImpl
    extends XtBaseService

    implements XtkzcsService {

  PojoInfo   Xt_XtkzcsbDao = DAOFactory.createXT_XTKZCSBDAO();
  PojoInfo   Xt_XtrzbDao = DAOFactory.createXT_XTRZBDAO();

  /**
   * 查询系统控制参数
   * @param sHqlXtkzcs
   * @param nPageOffset
   * @param nPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtkzcsxx(String sHqlXtkzcs) throws ServiceException,
      DAOException {
    return getXtkzcsxx(sHqlXtkzcs, PublicConstant.XT_PAGE,
                       PublicConstant.XT_PAGESIZE);
  }

  public List getXtkzcsxx(String sHqlXtkzcs, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    List lstXtRtn = null;
    try {
      lstXtRtn =super.getPageRecords(sHqlXtkzcs, new Object[]{}, nPageOffset,
          nPageSize).getList();
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
    return lstXtRtn;
  }

  /**
   *查询系统控制参数信息
   * @param iReqPage
   * @param iPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtkzcsxx(VarVO vvQryXtkzcs) throws ServiceException,
      DAOException {
    List lstXtkzcsxx = null;
    int nPageOffset = PublicConstant.XT_PAGE; //分页
    int nPageSize = PublicConstant.XT_PAGESIZE;

    String sCsid = vvQryXtkzcs.getVarValue("csid");
    String sKzlb = vvQryXtkzcs.getVarValue("kzlb");
    String sKzmc = vvQryXtkzcs.getVarValue("kzmc");
    String sKzz = vvQryXtkzcs.getVarValue("kzz");
    String sMrz = vvQryXtkzcs.getVarValue("mrz");
    String sBz = vvQryXtkzcs.getVarValue("bz");
    String sXgbz = vvQryXtkzcs.getVarValue("xgbz");
    String sBdlx = vvQryXtkzcs.getVarValue("bdlx");
    String sBdsj = vvQryXtkzcs.getVarValue("bdsj");

    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        "select XT_XTKZCSB FROM PoXT_XTKZCSB as XT_XTKZCSB where 1=1");

    //HQL条件语句csid，kzlb，kzmc，kzz，mrz，bz，xgbz，bdlx， bdsj
    if ( (sCsid != null) && ! ("".equals(sCsid))) {
      strBufHql.append(" and  csid =").append(sCsid);
    }
    if ( (sKzlb != null) && ! ("".equals(sKzlb))) {
      strBufHql.append(" and  kzlb ='").append(sKzlb).append("'");
    }
    if ( (sKzmc != null) && ! ("".equals(sKzmc))) {
      strBufHql.append(" and  kzmc ='").append(sKzmc).append("'");
    }
    if ( (sKzz != null) && ! ("".equals(sKzz))) {
      strBufHql.append(" and  kzz ='").append(sKzz).append("'");
    }
    if ( (sMrz != null) && ! ("".equals(sMrz))) {
      strBufHql.append(" and  mrz ='").append(sMrz).append("'");
    }
    if ( (sBz != null) && ! ("".equals(sBz))) {
      strBufHql.append(" and  bz ='").append(sBz).append("'");
    }
    if ( (sXgbz != null) && ! ("".equals(sXgbz))) {
      strBufHql.append(" and  xgbz ='").append(sXgbz).append("'");
    }
    if ( (sBdlx != null) && ! ("".equals(sBdlx))) {
      strBufHql.append(" and  bdlx ='").append(sBdlx).append("'");
    }
    if ( (sBdsj != null) && ! ("".equals(sBdsj))) {
      strBufHql.append(" and  bdsj ='").append(sBdsj).append("'");
    }
    strBufHql.append(" order by kzlb");
    //执行HQL语句
    return getXtkzcsxx(strBufHql.toString(), nPageOffset, nPageSize);
  }

  /**
   *修改系统控制参数信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtkzcsxgxx(VarVO vvXtkzcsb) throws ServiceException,
      DAOException {
    ArrayList lstXtkzcs = new ArrayList();
    PoXT_XTKZCSB poXTKZCSB = null;
    PoXT_XTKZCSB rtnpoXTKZCSB = new PoXT_XTKZCSB();
    //执行HQL语句
    try {
      poXTKZCSB  = super.get(PoXT_XTKZCSB.class,Long.valueOf(vvXtkzcsb.
          getVarValue("csid")));
      if (poXTKZCSB == null) { //如果没找到要修改的记录，返回null
        return null;
      }
      BeanUtils.populate(poXTKZCSB, vvXtkzcsb.getVarmap());
      poXTKZCSB.setBdlx(PublicConstant.XT_BDLX_UPDATE); //变动类型
      poXTKZCSB.setBdsj(StringUtils.getServiceTime());

      rtnpoXTKZCSB =      (PoXT_XTKZCSB)super.update(poXTKZCSB);
      lstXtkzcs.add(rtnpoXTKZCSB);
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
    return lstXtkzcs;
  }

  /**
   * latest change date
   * @param sHqlXtkzcs
   * @param nPageOffset
   * @param nPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtkzcszdbdsj() throws ServiceException,
      DAOException {
    List lstXtRtn = null;
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append("select XT_XTKZCSB FROM PoXT_XTKZCSB as XT_XTKZCSB where ")
        .append(
        " bdsj in (select max(XT_XTKZCSB) FROM PoXT_XTKZCSB as XT_XTKZCSB)");
    try {
      lstXtRtn =super.getPageRecords(strBufHql.toString(),new Object[]{},
          PublicConstant.XT_PAGE, 1).getList();
      if (lstXtRtn == null || lstXtRtn.isEmpty()) {
        String sHql =
            "select XT_XTKZCSB FROM PoXT_XTKZCSB as XT_XTKZCSB ";
        lstXtRtn =super.getPageRecords(sHql, new Object[]{}, 0, 1).getList();
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
    return lstXtRtn;
  }

}
