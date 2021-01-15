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
 *                 业务办理限制操作实现</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */
@Service
public class XtywblxzServiceImpl
    extends XtBaseService

    implements XtywblxzService {

  PojoInfo   Xt_ywblxzbDao = DAOFactory.createHJXZ_YWBLXZXXBDAO();
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
  public List getXtywblxzxx(String strHQL) throws ServiceException,
      DAOException {
    //执行HQL语句
    return getXtywblxzxx(strHQL, PublicConstant.PAGE_DEFAULT_OFFSET,
                         PublicConstant.PAGE_DEFAULT_SIZE);
  }

  public List getXtywblxzxx(String strHQL, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {

    List lstXtywblxzxx = null;
    List lstReturn = new ArrayList();
    PoHJXZ_YWBLXZXXB poXT_YWBLXZXXB = null;
    PoXT_YHXXB poXT_YHXXBA = null;
    PoXT_YHXXB poXT_YHXXBB = null;
    PoXT_SPMBXXB poXT_SPMBXXB = null;
    Object[] objXtRtn = null;
    //执行HQL语句 HJXZ_YWBLXZXXB,XT_YHXXBA,XT_YHXXBB,XT_SPMBXXB
    try {
      lstXtywblxzxx =super.getPageRecords(strHQL,new Object[]{},
          nPageOffset, nPageSize).getList();
      for (int i = 0; i < lstXtywblxzxx.size(); i++) {
        objXtRtn = (Object[]) lstXtywblxzxx.get(i);
        poXT_YWBLXZXXB = (PoHJXZ_YWBLXZXXB) objXtRtn[0];
        poXT_YHXXBA = (PoXT_YHXXB) objXtRtn[1];
        poXT_YHXXBB = (PoXT_YHXXB) objXtRtn[2];
        poXT_SPMBXXB = (PoXT_SPMBXXB) objXtRtn[3];
        VoXtywblxzxx voXtywblxzxx = new VoXtywblxzxx(poXT_YWBLXZXXB,
            poXT_YHXXBA,
            poXT_YHXXBB, poXT_SPMBXXB);
        lstReturn.add(voXtywblxzxx);
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
   *查询业务办理限制信息
   * @param iReqPage
   * @param iPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtywblxzxx(VarVO vvQryXtywblxz) throws ServiceException,
      DAOException {

    int nPageOffset = PublicConstant.XT_PAGE; //分页
    int nPageSize = PublicConstant.XT_PAGESIZE;
    String sXzxxid = vvQryXtywblxz.getVarValue("xzxxid");
    String sXzywlx = vvQryXtywblxz.getVarValue("xzywlx");
    String sQybz = vvQryXtywblxz.getVarValue("qybz");
    //获取参数信息
    //xzxxid，xzmc，xzywlx，cjrid，cjsj，xgrid，xgsj，xzzt，qybz，spmbid;
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append("select HJXZ_YWBLXZXXB,XT_YHXXBA,XT_YHXXBB,XT_SPMBXXB FROM PoHJXZ_YWBLXZXXB as HJXZ_YWBLXZXXB, ")
        .append(
        "PoXT_YHXXB as XT_YHXXBA,PoXT_YHXXB as XT_YHXXBB,PoXT_SPMBXXB as XT_SPMBXXB ")
        .append(" where HJXZ_YWBLXZXXB.cjrid = XT_YHXXBA.yhid")
        .append(" and HJXZ_YWBLXZXXB.xgrid = XT_YHXXBB.yhid(+)")
        .append(" and HJXZ_YWBLXZXXB.spmbid = XT_SPMBXXB.spmbid(+)");
    if ( (sXzxxid != null) && ! ("".equals(sXzxxid))) {
      strBufHql.append(" and  HJXZ_YWBLXZXXB.xzxxid =").append(sXzxxid);
    }
    if ( (sQybz != null) && ! ("".equals(sQybz))) {
      strBufHql.append(" and  HJXZ_YWBLXZXXB.qybz ='").append(sQybz).append("'");
    }
    if ( (sXzywlx != null) && ! ("".equals(sXzywlx))) {
      strBufHql.append(" and  HJXZ_YWBLXZXXB.xzywlx ='").append(sXzywlx).append(
          "'");
    }
    strBufHql.append(" order by HJXZ_YWBLXZXXB.xzywlx");
    return getXtywblxzxx(strBufHql.toString(), nPageOffset, nPageSize);
  }

  /**
   *增加业务办理限制信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtywblxzxx(VarVO vvXtywblxzb) throws ServiceException,
      DAOException {
    ArrayList lstXtywblxz = new ArrayList();
    PoHJXZ_YWBLXZXXB poXTYWBLXZB = new PoHJXZ_YWBLXZXXB();
    PoHJXZ_YWBLXZXXB rtnpoXTYWBLXZB = new PoHJXZ_YWBLXZXXB();
    //获取参数信息
    //xzxxid，xzmc，xzywlx，cjrid，cjsj，xgrid，xgsj，xzzt，qybz，spmbid;
    //组合poXT_XTywblxzB
    //执行HQL语句
    try {
      BeanUtils.populate(poXTYWBLXZB, vvXtywblxzb.getVarmap());
      poXTYWBLXZB.setCjrid(this.getUserInfo().getYhid());
      poXTYWBLXZB.setCjsj(StringUtils.getServiceTime());
      poXTYWBLXZB.setQybz(PublicConstant.XT_BZ_QY);
      poXTYWBLXZB.setXzxxid( (Long) Xt_ywblxzbDao.getId());
      rtnpoXTYWBLXZB =(PoHJXZ_YWBLXZXXB)super.create(poXTYWBLXZB);
      lstXtywblxz.add(rtnpoXTYWBLXZB);
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
    return lstXtywblxz;
  }

  /**
   *修改业务办理限制信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtywblxzxgxx(VarVO vvXtywblxzb) throws ServiceException,
      DAOException {
    ArrayList lstXtywblxz = new ArrayList();
    String sHql = null;
    PoHJXZ_YWBLXZXXB poXTYWBLXZB = new PoHJXZ_YWBLXZXXB();
    PoHJXZ_YWBLXZXXB rtnpoXTYWBLXZB = new PoHJXZ_YWBLXZXXB();
    //获取参数信息
    //xzxxid，xzmc，xzywlx，cjrid，cjsj，xgrid，xgsj，xzzt，qybz，spmbid;
    //执行HQL语句
    try {
      poXTYWBLXZB  = super.get(PoHJXZ_YWBLXZXXB.class,Long.valueOf(
          vvXtywblxzb.getVarValue("xzxxid")));
      if (poXTYWBLXZB == null) {
        return null;
      }
      BeanUtils.populate(poXTYWBLXZB, vvXtywblxzb.getVarmap());
      poXTYWBLXZB.setXgrid(this.getUserInfo().getYhid());
      poXTYWBLXZB.setXgsj(StringUtils.getServiceTime());
      rtnpoXTYWBLXZB =    (PoHJXZ_YWBLXZXXB)super.update(poXTYWBLXZB);
      lstXtywblxz.add(rtnpoXTYWBLXZB);
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
    return lstXtywblxz;

  }

  /**
   *删除业务办理限制信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtywblxzscxx(VarVO vvXtywblxzb) throws ServiceException,
      DAOException {
    PoHJXZ_YWBLXZXXB poXTYWBLXZB = new PoHJXZ_YWBLXZXXB();
    PoHJXZ_YWBLXZXXB rtnpoXTYWBLXZB = new PoHJXZ_YWBLXZXXB();
    List lstXtRtn = null;
    int nReturn = PublicConstant.XT_BZ_ERROR;
    //组合poXT_XTywblxzB
    //执行HQL语句
    try {
      poXTYWBLXZB  = super.get(PoHJXZ_YWBLXZXXB.class,Long.valueOf(
          vvXtywblxzb.getVarValue("xzxxid")));
      if (poXTYWBLXZB == null) {
        return null;
      }
      BeanUtils.populate(poXTYWBLXZB, vvXtywblxzb.getVarmap());
      poXTYWBLXZB.setQybz(PublicConstant.XT_BZ_BQY);
     super.update(poXTYWBLXZB);
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
    return lstXtRtn;

  }

}
