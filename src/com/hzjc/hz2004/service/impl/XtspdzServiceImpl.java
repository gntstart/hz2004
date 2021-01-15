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
 *                 审批动作操作实现</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */
@Service
public class XtspdzServiceImpl
    extends XtBaseService

    implements XtspdzService {

  PojoInfo   Xt_spdzbDao = DAOFactory.createXT_SPDZBDAO();
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
  public List getXtspdzxx(String strHQL) throws ServiceException,
      DAOException {
    //执行HQL语句
    return getXtspdzxx(strHQL, PublicConstant.PAGE_DEFAULT_OFFSET,
                       PublicConstant.PAGE_DEFAULT_SIZE);
  }

  public List getXtspdzxx(String strHQL, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    List lstXtspdzxx = null;
    //执行HQL语句
    try {
      lstXtspdzxx =super.getPageRecords(strHQL, new Object[]{}, nPageOffset,
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
    return lstXtspdzxx;
  }

  /**
   *查询审批动作信息
   * @param iReqPage
   * @param iPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtspdzxx(VarVO vvQryXtspdz) throws ServiceException,
      DAOException {
    int nPageOffset = PublicConstant.XT_PAGE; //分页
    int nPageSize = PublicConstant.XT_PAGESIZE;
    String sDzid = vvQryXtspdz.getVarValue("dzid");
    String sDzmc = vvQryXtspdz.getVarValue("dzmc");
    String sMs = vvQryXtspdz.getVarValue("ms");
    String sQybz = vvQryXtspdz.getVarValue("qybz");
    String sYhid = vvQryXtspdz.getVarValue("yhid");

    //dzid,dzmc,dzsx,ms,qybz;
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append("select XT_SPDZB FROM PoXT_SPDZB as XT_SPDZB where 1=1");

    if ( (sDzid != null) && ! ("".equals(sDzid))) {
      strBufHql.append(" and  dzid =").append(sDzid);
    }
    if ( (sDzmc != null) && ! ("".equals(sDzmc))) {
      strBufHql.append(" and  dzmc =").append(sDzmc);
    }
    if ( (sMs != null) && ! ("".equals(sMs))) {
      strBufHql.append(" and  dzmc =").append(sMs);
    }
    if ( (sQybz != null) && ! ("".equals(sQybz))) {
      strBufHql.append(" and  qybz =").append(sQybz);
    }

    return getXtspdzxx(strBufHql.toString(), nPageOffset, nPageSize);
  }

  /**
   *增加审批动作信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtspdzxx(VarVO vvXtspdzb) throws ServiceException,
      DAOException {
    ArrayList lstXtspdz = new ArrayList();
    PoXT_SPDZB poXTSPDZB = new PoXT_SPDZB();
    PoXT_SPDZB rtnpoXTSPDZB = new PoXT_SPDZB();
    //获取参数信息
    //组合poXT_XTspdzB
    try {
      BeanUtils.populate(poXTSPDZB, vvXtspdzb.getVarmap());
      poXTSPDZB.setQybz(PublicConstant.XT_BZ_QY); //启用标志
      poXTSPDZB.setDzid( (Long) Xt_spdzbDao.getId()); //动作ID
    }
    catch (InvocationTargetException ex1) {
    }
    catch (IllegalAccessException ex1) {
    }
    try {
      rtnpoXTSPDZB =(PoXT_SPDZB)super.create(poXTSPDZB);
      lstXtspdz.add(rtnpoXTSPDZB);
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
    return lstXtspdz;
  }

  /**
   *修改审批动作信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtspdzxgxx(VarVO vvXtspdzb) throws ServiceException,
      DAOException {
    ArrayList lstXtspdz = new ArrayList();
    PoXT_SPDZB poXTSPDZB = null;
    PoXT_SPDZB rtnpoXTSPDZB = new PoXT_SPDZB();
    //获取参数信息
    //组合poXT_XTspdzB
    //执行HQL语句
    try {
      poXTSPDZB  = super.get(PoXT_SPDZB.class,Long.valueOf(vvXtspdzb.
          getVarValue("dzid")));
      if (poXTSPDZB == null) {
        return null;
      }
      BeanUtils.populate(poXTSPDZB, vvXtspdzb.getVarmap());
      rtnpoXTSPDZB =   (PoXT_SPDZB)super.update(poXTSPDZB);
      lstXtspdz.add(rtnpoXTSPDZB);
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
    return lstXtspdz;

  }

  /**
   *删除审批动作信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtspdzscxx(VarVO vvXtspdzb) throws ServiceException,
      DAOException {
    PoXT_SPDZB poXTSPDZB = null;
    PoXT_SPDZB rtnpoXTSPDZB = new PoXT_SPDZB();
    List lstXtRtn = new ArrayList();
    //组合poXT_XTspdzB

    try {
      poXTSPDZB  = super.get(PoXT_SPDZB.class,Long.valueOf(vvXtspdzb.
          getVarValue("dzid")));
      if (poXTSPDZB == null) {
        return null;
      }
      BeanUtils.populate(poXTSPDZB, vvXtspdzb.getVarmap());
      poXTSPDZB.setQybz(PublicConstant.XT_BZ_BQY);
     super.update(poXTSPDZB);
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
