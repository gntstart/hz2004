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
 *                 数据配置操作实现</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */
@Service
public class XtsjpzServiceImpl
    extends XtBaseService

    implements XtsjpzService {

  PojoInfo   Xt_sjpzbDao = DAOFactory.createXT_SJPZBDAO();
  PojoInfo   Xt_XtrzbDao = DAOFactory.createXT_XTRZBDAO();
  /**
   *查询信息
   * @param pxQryXtsjpz
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtsjpzxx(String sHqlXtsjpz) throws ServiceException,
      DAOException {
    //执行HQL语句
    return getXtsjpzxx(sHqlXtsjpz, PublicConstant.XT_PAGE,
                       PublicConstant.XT_PAGESIZE);
  }

  public List getXtsjpzxx(String sHqlXtsjpz, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    //执行HQL语句
    List lstXtRtn = null;
    try {
      lstXtRtn =super.getPageRecords(sHqlXtsjpz, new Object[]{}, nPageOffset,
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
   *查询信息
   * @param pxQryXtsjpz
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtsjpzxx(VarVO vvXtsjpz) throws ServiceException,
      DAOException {
    List lstXtsjpzxx = null;
    String sSjpzId = vvXtsjpz.getVarValue("sjpzid");
    String sPzlb = vvXtsjpz.getVarValue("pzlb");
    //获取参数信息
    //zrqdm,dwdm,zrqmc,zrqzwpy,zrqwbpy,qybz,bz,bdlx,bdsj;
    int nPageOffset = PublicConstant.XT_PAGE; //分页
    int nPageSize = PublicConstant.XT_PAGESIZE;
    //HQL条件语句
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append("select XT_SJPZB FROM PoXT_SJPZB as XT_SJPZB where 1=1 ");
    if ( (sSjpzId != null) && ! ("".equals(sSjpzId))) {
      strBufHql.append(" and  sjpzid =").append(sSjpzId);
    }
    if ( (sPzlb != null) && ! ("".equals(sPzlb))) {
      strBufHql.append(" and  pzlb =").append(sPzlb);
    }
    strBufHql.append(" order by pzlb,id");
    return getXtsjpzxx(strBufHql.toString(), nPageOffset, nPageSize);
  }

  /**
   *增加信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtsjpzxx(VarVO vvXtsjpzb) throws ServiceException,
      DAOException {
    ArrayList lstXtsjpz = new ArrayList();
    PoXT_SJPZB poXTSJPZB = new PoXT_SJPZB();
    PoXT_SJPZB rtnpoXTSJPZB = new PoXT_SJPZB();
    //获取参数信息
    //sjpzid,pzlb,pzmc,id,fieldname,displayname,fieldtype,fieldtypename,dsname;
    //readonly,visibletype,fieldlength,displayfieldlength,ischinese,mbmod;
    //allowselfinput,codefield,pyfield,namefield,partmask,lsfield,showls;
    //enablecopy,enabledefaultfilter,enterdropdown,enteruniqueexit,usetablefiltered;
    //useforsfz,conditionoperator,conditionfield,conditiontype,groups;
    try {
      BeanUtils.populate(poXTSJPZB, vvXtsjpzb.getVarmap());
      //poXTSJPZB.setQybz(PublicConstant.XT_BZ_QY); //启用标志
      poXTSJPZB.setBdlx(PublicConstant.XT_BDLX_ADD); //变动类型
      poXTSJPZB.setBdsj(StringUtils.getServiceTime());
      poXTSJPZB.setSjpzid( (Long) Xt_sjpzbDao.getId());
      rtnpoXTSJPZB =(PoXT_SJPZB)super.create(poXTSJPZB);
      lstXtsjpz.add(rtnpoXTSJPZB);
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
    return lstXtsjpz;
  }

  /**
   * 修改信息
   * @param voXtgbsjpzb
   * @param atUser
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtsjpzxgxx(VarVO vvXtsjpzb) throws ServiceException,
      DAOException {
    ArrayList lstXtsjpz = new ArrayList();
    PoXT_SJPZB poXTSJPZB = new PoXT_SJPZB();
    PoXT_SJPZB rtnpoXTSJPZB = new PoXT_SJPZB();
    //执行HQL语句
    try {
      poXTSJPZB  = super.get(PoXT_SJPZB.class,vvXtsjpzb.getVarValue(
          "sjpzid"));
      BeanUtils.populate(poXTSJPZB, vvXtsjpzb.getVarmap());
      //poXTSJPZB.setQybz(PublicConstant.XT_BZ_QY); //启用标志
      poXTSJPZB.setBdlx(PublicConstant.XT_BDLX_UPDATE); //变动类型
      poXTSJPZB.setBdsj(StringUtils.getServiceTime());
      rtnpoXTSJPZB =      (PoXT_SJPZB)super.update(poXTSJPZB);
      lstXtsjpz.add(rtnpoXTSJPZB);
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
    return lstXtsjpz;
  }

  /**
   * 删除信息
   * @param voXtxzqhb
   * @param atUser
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtsjpzscxx(VarVO vvXtsjpzb) throws ServiceException,
      DAOException {
    PoXT_SJPZB poXTSJPZB = new PoXT_SJPZB();
    PoXT_SJPZB rtnpoXTSJPZB = new PoXT_SJPZB();
    ArrayList lstXtsjpz = new ArrayList();
    int nReturn = PublicConstant.XT_BZ_ERROR;
    //执行HQL语句
    try {
      poXTSJPZB.setSjpzid(Long.valueOf(vvXtsjpzb.getVarValue("sjpzid")));
      super.delete(poXTSJPZB);
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

  /**
   * latest change date
   * @param vvXtsjpz
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtsjpzzdbdsj(VarVO vvXtsjpz) throws ServiceException,
      DAOException {
    //执行HQL语句
    List lstXtRtn = new ArrayList();
    List lstXtsjpz = null;
    Object[] objXtRtn = null;
    String sXtPzlb = "", sXtPzmc = "", sXtBdsj = "";
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append("select XT_SJPZB.pzlb,XT_SJPZB.pzmc,max(XT_SJPZB.bdsj) FROM PoXT_SJPZB as XT_SJPZB where 1=1 ");
    String sPzlb = vvXtsjpz.getVarValue("pzlb");
    if ( (sPzlb != null) && ! ("".equals(sPzlb))) {
      strBufHql.append(" and XT_SJPZB.pzlb = '").append(sPzlb).append("'");
    }
    strBufHql.append(" group by XT_SJPZB.pzlb,XT_SJPZB.pzmc order by pzlb ");

    try {
      lstXtsjpz =super.getPageRecords(strBufHql.toString(),new Object[]{}, 
          PublicConstant.XT_PAGE, PublicConstant.XT_PAGESIZE).getList();
      for (int i = 0; i < lstXtsjpz.size(); i++) {
        objXtRtn = (Object[]) lstXtsjpz.get(i);
        sXtPzlb = (String) objXtRtn[0];
        sXtPzmc = (String) objXtRtn[1];
        sXtBdsj = (String) objXtRtn[2];
        VoXtsjpz voXtsjpz = new VoXtsjpz(sXtPzlb, sXtPzmc, sXtBdsj);
        lstXtRtn.add(voXtsjpz);
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
