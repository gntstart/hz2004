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
import com.hzjc.hz2004.common.HjCommon;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 行政区划操作实现</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */
@Service
public class XtxzqhServiceImpl
    extends XtBaseService
    implements XtxzqhService {

  PojoInfo   Xt_xzqhbDao = DAOFactory.createXT_XZQHBDAO();
  PojoInfo   Xt_XtrzbDao = DAOFactory.createXT_XTRZBDAO();
  /**
   *查询行政区划信息
   * @param pxQryXtcs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtxzqhxx(String sHqlXtxzqh) throws ServiceException,
      DAOException {
    return getXtxzqhxx(sHqlXtxzqh, PublicConstant.XT_PAGE,
                       PublicConstant.XT_PAGESIZE);
  }

  public List getXtxzqhxx(String sHqlXtxzqh, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    //执行HQL语句
    List lstXtRtn = null;
    List lstReturn = new ArrayList();
    PoXT_XZQHB poXT_XTXZQHB = null;
    PoXT_XTCSB poXT_XTCSB = null;
    Object[] objXtRtn = null;
    try {
      lstXtRtn =super.getPageRecords(sHqlXtxzqh,new Object[]{}, nPageOffset,
                                                  nPageSize).getList();
      for (int i = 0; i < lstXtRtn.size(); i++) {
        objXtRtn = (Object[]) lstXtRtn.get(i);
        poXT_XTXZQHB = (PoXT_XZQHB) objXtRtn[0];
        poXT_XTCSB = (PoXT_XTCSB) objXtRtn[1];
        VoXtxzqhxx voXtxzqhxx = new VoXtxzqhxx(poXT_XTXZQHB, poXT_XTCSB);
        lstReturn.add(voXtxzqhxx);
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
   *查询行政区划信息
   * @param pxQryXtcs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult getXtxzqhxx(VarVO vvQryXtxzqh,String psWhere,VoPage voPage) throws ServiceException,
      DAOException {
    List lstXtxzqhxx = null;
    String sQhdm = "", sQybz = "";
    sQhdm = vvQryXtxzqh.getVarValue("qhdm");
    sQybz = vvQryXtxzqh.getVarValue("qybz");
    //获取参数信息

    VoQueryResult voQueryResult = new VoQueryResult();

    //HQL条件增加
    StringBuffer strBufHql = new StringBuffer();
    StringBuffer strCountHql = new StringBuffer();
    StringBuffer strHql = new StringBuffer();
    strBufHql.append(
        "select XT_XZQHB,XT_XTCSB FROM PoXT_XZQHB as XT_XZQHB,PoXT_XTCSB as XT_XTCSB");
    strCountHql.append(" select count(*) from PoXT_XZQHB as XT_XZQHB,PoXT_XTCSB as XT_XTCSB");
     strHql.append(
        " where XT_XZQHB.qybz = XT_XTCSB.dm and XT_XTCSB.cslb = '9002'");//" where XT_XZQHB.qybz = XT_XTCSB.dm(+) and XT_XTCSB.cslb(+) = '9002'");
    if ( (sQhdm != null) && ! ("".equals(sQhdm))) {
      strHql.append(" and  XT_XZQHB.dm ='").append(sQhdm).append("'");
    }
    if ( (sQybz != null) && ! ("".equals(sQybz))) {
      strHql.append(" and  XT_XZQHB.qybz ='").append(sQybz).append("'");
    }
    if (psWhere != null && !"".equals(psWhere)) {
      strHql.append(" and (" + psWhere + ")");
    }
    strHql.append(" order by XT_XZQHB.dm");
    strBufHql.append(strHql);
    strCountHql.append(strHql);

    if (voPage.getRecordcount() == -1) {
      try {
        voQueryResult.setRecordcount(super.getCount(strCountHql.
            toString()));
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
    }
    voQueryResult.setPagelist(getXtxzqhxx(strBufHql.toString(),
                                              voPage.getPageindex(),
                                              voPage.getPagesize()));
    voQueryResult.setVotype(VoXtxzqhxx.class);
    return voQueryResult;
  }

  /**
   *增加信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtxzqhxx(VarVO vvXtxzqhb) throws ServiceException,
      DAOException {
    ArrayList lstXtxzqh = new ArrayList();
    PoXT_XZQHB poXTXZQHB = new PoXT_XZQHB();
    PoXT_XZQHB rtnpoXTXZQHB = new PoXT_XZQHB();
    //执行HQL语句
    try {
      //获取参数信息
      //qhdm,qhmc,qhzwpy,qhwbpy,qybz,bz,bdlx,bdsj
      BeanUtils.populate(poXTXZQHB, vvXtxzqhb.getVarmap());
      poXTXZQHB.setQybz(PublicConstant.XT_BZ_QY); //启用标志
      poXTXZQHB.setBdlx(PublicConstant.XT_BDLX_ADD); //变动类型
      poXTXZQHB.setBdsj(StringUtils.getServiceTime());
      rtnpoXTXZQHB =(PoXT_XZQHB)super.create(poXTXZQHB);
      lstXtxzqh.add(rtnpoXTXZQHB);
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
    return lstXtxzqh;
  }

  /**
   * 修改信息
   * @param voXtgbcsb
   * @param atUser
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtxzqhxgxx(VarVO vvXTXZQHB) throws ServiceException,
      DAOException {
    ArrayList lstXtxzqh = new ArrayList();
    PoXT_XZQHB poXTXZQHB = new PoXT_XZQHB();
    PoXT_XZQHB rtnpoXTXZQHB = new PoXT_XZQHB();
    //组合 //qhdm,qhmc,qhzwpy,qhwbpy,qybz,bz,bdlx,bdsj
    try {
      poXTXZQHB  = super.get(PoXT_XZQHB.class,vvXTXZQHB.getVarValue(
          "dm"));
      if (poXTXZQHB == null) {
        return null;
      }
      BeanUtils.populate(poXTXZQHB, vvXTXZQHB.getVarmap());
      poXTXZQHB.setBdlx(PublicConstant.XT_BDLX_UPDATE); //变动类型
      poXTXZQHB.setBdsj(StringUtils.getServiceTime());
      //执行HQL语句
      rtnpoXTXZQHB =  (PoXT_XZQHB)super.update(poXTXZQHB);
      lstXtxzqh.add(rtnpoXTXZQHB);
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
    return lstXtxzqh;
  }

  /**
   * 删除信息
   * @param voXtxzqhb
   * @param atUser
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtxzqhscxx(VarVO vvXtxzqhb) throws ServiceException,
      DAOException {
    PoXT_XZQHB poXTXZQHB = new PoXT_XZQHB();
    PoXT_XZQHB rtnpoXTXZQHB = new PoXT_XZQHB();
    ArrayList lstXtxzqh = new ArrayList();
    //组合poXT_XTCSB
    //执行HQL语句
    try {
      poXTXZQHB  = super.get(PoXT_XZQHB.class,vvXtxzqhb.getVarValue(
          "dm"));
      if (poXTXZQHB == null) {
        return null;
      }
      poXTXZQHB.setQybz(PublicConstant.XT_BZ_BQY); //启用标志
      poXTXZQHB.setBdlx(PublicConstant.XT_BDLX_DELETE); //变动类型
      poXTXZQHB.setBdsj(StringUtils.getServiceTime());
      rtnpoXTXZQHB =     (PoXT_XZQHB)super.update(poXTXZQHB);
      lstXtxzqh.add(rtnpoXTXZQHB);
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
    return lstXtxzqh;
  }

  /**
   * 得到最后（最大）变动时间记录
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtxzqhzdbdsj() throws ServiceException,
      DAOException {
    //执行HQL语句
    List lstXtRtn = null;
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append("select XT_XZQHB FROM PoXT_XZQHB as XT_XZQHB where ")
        .append(
        " bdsj in (select max(XT_XZQHB.bdsj) FROM PoXT_XZQHB as XT_XZQHB)");
    try {
      lstXtRtn =super.getPageRecords(strBufHql.toString(),new Object[]{},
                                                  PublicConstant.XT_PAGE,1).getList();
      if (lstXtRtn == null || lstXtRtn.isEmpty()) {
        String sHql =
            "select XT_XZQHB FROM PoXT_XZQHB as XT_XZQHB ";
        lstXtRtn =super.getPageRecords(sHql,new Object[]{}, 0, 1).getList();
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
