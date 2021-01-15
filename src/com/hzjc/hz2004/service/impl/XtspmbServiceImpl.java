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
 *                 审批模板操作实现</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */
@Service
public class XtspmbServiceImpl
    extends XtBaseService

    implements XtspmbService {

  PojoInfo   Xt_spmbbDao = DAOFactory.createXT_SPMBXXBDAO();
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
  public List getXtspmbxx(String strHQL) throws ServiceException,
      DAOException {
    //执行HQL语句
    return getXtspmbxx(strHQL, PublicConstant.XT_PAGE,
                       PublicConstant.XT_PAGESIZE);
  }

  public List getXtspmbxx(String strHQL, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    List lstXtspmbxx = null;
    PoXT_SPMBXXB poXT_SPMBXXB = null;
    PoXT_YHXXB poXT_YHXXBA = null;
    PoXT_YHXXB poXT_YHXXBB = null;
    PoXT_XTCSB poXT_XTCSBA = null;
    PoXT_XTCSB poXT_XTCSBB = null;
    Object[] objXtRtn = null;
    List lstReturn = new ArrayList();
    //执行HQL语句
    try {
      lstXtspmbxx =super.getPageRecords(strHQL, new Object[]{}, nPageOffset,
          nPageSize).getList();
      for (int i = 0; i < lstXtspmbxx.size(); i++) {
        objXtRtn = (Object[]) lstXtspmbxx.get(i);
        poXT_SPMBXXB = (PoXT_SPMBXXB) objXtRtn[0];
        poXT_YHXXBA = (PoXT_YHXXB) objXtRtn[1];
        poXT_YHXXBB = (PoXT_YHXXB) objXtRtn[2];
        poXT_XTCSBA = (PoXT_XTCSB) objXtRtn[3];
        poXT_XTCSBB = (PoXT_XTCSB) objXtRtn[4];
        VoXtspmb voXtspmb = new VoXtspmb(poXT_SPMBXXB, poXT_YHXXBA, poXT_YHXXBB,
                                         poXT_XTCSBA, poXT_XTCSBB);
        lstReturn.add(voXtspmb);
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
   *查询审批模板信息
   * @param iReqPage
   * @param iPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtspmbxx(VarVO vvQryXtspmb) throws ServiceException,
      DAOException {
    //获取参数信息
    int nPageOffset = PublicConstant.XT_PAGE; //分页
    int nPageSize = PublicConstant.XT_PAGESIZE;
    String sSpmbid = vvQryXtspmb.getVarValue("spmbid");
    String sMbmc = vvQryXtspmb.getVarValue("mbmc");
    String sMbdj = vvQryXtspmb.getVarValue("mbdj");
    String sCjrid = vvQryXtspmb.getVarValue("cjrid");
    String sCjsj = vvQryXtspmb.getVarValue("cjsj");
    String sXgrid = vvQryXtspmb.getVarValue("xgrid");
    String sXgsj = vvQryXtspmb.getVarValue("xgsj");
    String sQybz = vvQryXtspmb.getVarValue("qybz");
    String sDqsys = vvQryXtspmb.getVarValue("dqsys");

    //spmbid,mbmc,mbdj,cjrid,cjsj,xgrid,xgsj,qybz,dqsys;
    //HQL条件语句
    //HQL条件增加
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append("select XT_SPMBXXB,XT_YHXXBA,XT_YHXXBB,XT_XTCSBA,XT_XTCSBB FROM PoXT_SPMBXXB as XT_SPMBXXB,")
        .append(
        "PoXT_YHXXB as XT_YHXXBA,PoXT_YHXXB as XT_YHXXBB,PoXT_XTCSB as XT_XTCSBA,")
        .append(" PoXT_XTCSB as XT_XTCSBB where ")
        .append(
        " XT_SPMBXXB.mbdj = XT_XTCSBA.dm and XT_XTCSBA.cslb = '9021'")//" XT_SPMBXXB.mbdj = XT_XTCSBA.dm(+) and XT_XTCSBA.cslb(+) = '9021'")
        .append(
        " and XT_SPMBXXB.qybz = XT_XTCSBB.dm and XT_XTCSBB.cslb = '9002'")//" and XT_SPMBXXB.qybz = XT_XTCSBB.dm(+) and XT_XTCSBB.cslb(+) = '9002'")
        .append(" and XT_SPMBXXB.cjrid = XT_YHXXBA.yhid and XT_SPMBXXB.xgrid = XT_YHXXBB.yhid");//.append(" and XT_SPMBXXB.cjrid = XT_YHXXBA.yhid and XT_SPMBXXB.xgrid = XT_YHXXBB.yhid(+)");
    if ( (sSpmbid != null) && ! ("".equals(sSpmbid))) {
      strBufHql.append(" and  XT_SPMBXXB.spmbid =").append(sSpmbid);
    }
    if ( (sMbmc != null) && ! ("".equals(sMbmc))) {
      strBufHql.append(" and  XT_SPMBXXB.mbmc =").append(sMbmc);
    }
    if ( (sMbdj != null) && ! ("".equals(sMbdj))) {
      strBufHql.append(" and  XT_SPMBXXB.mbdj =").append(sMbdj);
    }
    if ( (sCjrid != null) && ! ("".equals(sCjrid))) {
      strBufHql.append(" and  XT_SPMBXXB.cjrid =").append(sCjrid);
    }
    if ( (sCjsj != null) && ! ("".equals(sCjsj))) {
      strBufHql.append(" and  XT_SPMBXXB.cjsj =").append(sCjsj);
    }
    if ( (sXgrid != null) && ! ("".equals(sXgrid))) {
      strBufHql.append(" and  XT_SPMBXXB.xgrid =").append(sXgrid);
    }
    if ( (sXgsj != null) && ! ("".equals(sXgsj))) {
      strBufHql.append(" and  XT_SPMBXXB.xgsj =").append(sXgsj);
    }
    if ( (sQybz != null) && ! ("".equals(sQybz))) {
      strBufHql.append(" and  XT_SPMBXXB.qybz =").append(sQybz);
    }
    if ( (sDqsys != null) && ! ("".equals(sDqsys))) {
      strBufHql.append(" and  XT_SPMBXXB.dqsys =").append(sDqsys);
    }
    return getXtspmbxx(strBufHql.toString(), nPageOffset, nPageSize);
  }

  /**
   *增加审批模板信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtspmbxx(VarVO vvXtspmbb) throws ServiceException,
      DAOException {
    ArrayList lstXtspmb = new ArrayList();
    PoXT_SPMBXXB poXTSPMBB = new PoXT_SPMBXXB();
    PoXT_SPMBXXB rtnpoXTSPMBB = new PoXT_SPMBXXB();
    //获取参数信息
    try {
      BeanUtils.populate(poXTSPMBB, vvXtspmbb.getVarmap());
      poXTSPMBB.setSpmbid( (Long) Xt_spmbbDao.getId());
      poXTSPMBB.setCjrid(this.getUserInfo().getYhid());
      poXTSPMBB.setCjsj(StringUtils.getServiceTime());
      poXTSPMBB.setQybz(PublicConstant.XT_BZ_QY);
      poXTSPMBB.setDqsys(PublicConstant.XT_SJCSZ);
      rtnpoXTSPMBB =(PoXT_SPMBXXB)super.create(poXTSPMBB);
      lstXtspmb.add(rtnpoXTSPMBB);
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
    return lstXtspmb;
  }

  /**
   *修改审批模板信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtspmbxgxx(VarVO vvXtspmbb) throws ServiceException,
      DAOException {
    ArrayList lstXtspmb = new ArrayList();
    PoXT_SPMBXXB poXTSPMBB = null;
    PoXT_SPMBXXB rtnpoXTSPMBB = new PoXT_SPMBXXB();
    //获取参数信息
    //spmbid,mbmc,mbdj,cjrid,cjsj,xgrid,xgsj,qybz,dqsys;
    try {
      poXTSPMBB  = super.get(PoXT_SPMBXXB.class,Long.valueOf(vvXtspmbb.
          getVarValue("spmbid")));
      if (poXTSPMBB == null) {
        return null;
      }
      BeanUtils.populate(poXTSPMBB, vvXtspmbb.getVarmap());
      poXTSPMBB.setXgrid(this.getUserInfo().getYhid());
      poXTSPMBB.setXgsj(StringUtils.getServiceTime());
      rtnpoXTSPMBB =  (PoXT_SPMBXXB)super.update(poXTSPMBB);
      lstXtspmb.add(rtnpoXTSPMBB);
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, "", ex);
    }
    return lstXtspmb;

  }

  /**
   *删除审批模板信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtspmbscxx(VarVO vvXtspmbb) throws ServiceException,
      DAOException {
    List lstXtRtn = new ArrayList();
    PoXT_SPMBXXB poXTSPMBB = null;
    PoXT_SPMBXXB rtnpoXTspmbB = new PoXT_SPMBXXB();
    //组合poXT_XTspmbB
    try {
      poXTSPMBB  = super.get(PoXT_SPMBXXB.class,Long.valueOf(vvXtspmbb.
          getVarValue("spmbid")));
      if (poXTSPMBB == null) {
        return null;
      }
      poXTSPMBB.setQybz(PublicConstant.XT_BZ_BQY);
      poXTSPMBB =   (PoXT_SPMBXXB)super.update(poXTSPMBB);
      lstXtRtn.add(poXTSPMBB);
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
