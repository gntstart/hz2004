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
 *                 系统参数操作实现</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */
@Service
public class XtcsServiceImpl
    extends XtBaseService

    implements XtcsService {

  PojoInfo   Xt_XtcsbDao = DAOFactory.createXT_XTCSBDAO();
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
  public List getXtcsxx(String strHQL) throws ServiceException,
      DAOException {
    return getXtcsxx(strHQL, PublicConstant.XT_PAGE, PublicConstant.XT_PAGESIZE);
  }

  public List getXtcsxx(String strHQL, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    List lstXtcsxx = null;
    //执行HQL语句
    try {
      lstXtcsxx =super.getPageRecords(strHQL, new Object[]{}, nPageOffset,
          nPageSize).getList();
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
    return lstXtcsxx;
  }

  /**
   *查询系统参数信息
   * @param iReqPage
   * @param iPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult getXtcsxx(VarVO vvHqlQryXtcs, String psWhere,
                                 VoPage voPage) throws ServiceException,
      DAOException {
    VoQueryResult voQueryResult = new VoQueryResult();
    //获取参数信息
    //csid，cslb,dm,mc,zwpy,kzbzb，kzbzc，kzbzd，kzbze,kzbzf,kzbzg

    String sCsid = "", sCslb = "", sDm = "";
    String sMc = "", sZwpy = "", sKzbzb = "";
    String sKzbzc = "", sKzbzd = "", sKzbze = "";
    String sKzbzf = "", sKzbzg = "";

    sCsid = vvHqlQryXtcs.getVarValue("csid");
    sCslb = vvHqlQryXtcs.getVarValue("cslb");
    sDm = vvHqlQryXtcs.getVarValue("dm");
    sMc = vvHqlQryXtcs.getVarValue("mc");
    sZwpy = vvHqlQryXtcs.getVarValue("zwpy");
    sKzbzb = vvHqlQryXtcs.getVarValue("kzbzb");
    sKzbzc = vvHqlQryXtcs.getVarValue("kzbzc");
    sKzbzd = vvHqlQryXtcs.getVarValue("kzbzd");
    sKzbze = vvHqlQryXtcs.getVarValue("kzbze");
    sKzbzf = vvHqlQryXtcs.getVarValue("kzbzf");
    sKzbzg = vvHqlQryXtcs.getVarValue("kzbzg");

    //HQL条件语句
    StringBuffer strBufHql = new StringBuffer();
    StringBuffer strCountHql = new StringBuffer();
    StringBuffer strHql = new StringBuffer();
    strBufHql.append("select XT_XTCSB FROM PoXT_XTCSB as XT_XTCSB where 1=1 ");
    strCountHql.append("select count(*) from PoXT_XTCSB as XT_XTCSB where 1=1 ");
        if ( (sCslb != null) && ! ("".equals(sCslb))) {
      strHql.append(" and  cslb ='").append(sCslb).append("'");
    }
    if ( (sCsid != null) && ! ("".equals(sCsid))) {
      strHql.append(" and  csid =").append(sCsid);
    }
    if ( (sDm != null) && ! ("".equals(sDm))) {
      strHql.append(" and  dm ='").append(sDm).append("'");
    }
    if ( (sMc != null) && ! ("".equals(sMc))) {
      strHql.append(" and  mc ='").append(sMc).append("'");
    }
    if ( (sZwpy != null) && ! ("".equals(sZwpy))) {
      strHql.append(" and  zwpy ='").append(sZwpy).append("'");
    }
    if ( (sKzbzb != null) && ! ("".equals(sKzbzb))) {
      strHql.append(" and  kzbzb ='").append(sKzbzb).append("'");
    }
    if ( (sKzbzc != null) && ! ("".equals(sKzbzc))) {
      strHql.append(" and  kzbzc ='").append(sKzbzc).append("'");
    }
    if ( (sKzbzd != null) && ! ("".equals(sKzbzd))) {
      strHql.append(" and  kzbzd ='").append(sKzbzd).append("'");
    }
    if ( (sKzbze != null) && ! ("".equals(sKzbze))) {
      strHql.append(" and  kzbze ='").append(sKzbze).append("'");
    }
    if ( (sKzbzf != null) && ! ("".equals(sKzbzf))) {
      strHql.append(" and  kzbzf ='").append(sKzbzf).append("'");
    }
    if ( (sKzbzg != null) && ! ("".equals(sKzbzg))) {
      strHql.append(" and  kzbzg ='").append(sKzbzg).append("'");
    }
    if (psWhere != null && !"".equals(psWhere)) {
      strHql.append(" and (" + psWhere + ")");
    }
    strHql.append(" order by cslb,length(dm),dm");
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
    voQueryResult.setPagelist(getXtcsxx(strBufHql.toString(),
                                              voPage.getPageindex(),
                                              voPage.getPagesize()));
    voQueryResult.setVotype(PoXT_XTCSB.class);
    return voQueryResult;
  }

  /**
   *增加系统参数信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtcsxx(VarVO vvXTCSB) throws ServiceException, DAOException {
    ArrayList lstXtcs = new ArrayList();
    PoXT_XTCSB poXtcsb = new PoXT_XTCSB();
    PoXT_XTCSB poXtcsb_u = new PoXT_XTCSB();
    PoXT_XTCSB rtnpoXTCSB = new PoXT_XTCSB();
    String sCslb = vvXTCSB.getVarValue("cslb");
    String sSql = "";
    List lstXtcs_u;
    try {
      BeanUtils.populate(poXtcsb, vvXTCSB.getVarmap());
    }
    catch (InvocationTargetException ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_TRANSACTION, ex);
    }
    catch (IllegalAccessException ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_TRANSACTION, ex);
    }
    try {
      //执行
      poXtcsb.setCsid( (Long) Xt_XtcsbDao.getId());
      poXtcsb.setXgbz(PublicConstant.XT_XGBZ); //修改标志
      poXtcsb.setBdlx(PublicConstant.XT_BDLX_ADD); //变动类型
      poXtcsb.setBdsj(StringUtils.getServiceTime());
      rtnpoXTCSB =(PoXT_XTCSB)super.create(poXtcsb);

      //如果增加的参数类别不是9999，要修改相应的9999的变动时间
      if (!"9999".equals(sCslb)) {
        sSql =
            "select XT_XTCSB FROM PoXT_XTCSB as XT_XTCSB where cslb = '9999' and dm='"
            + sCslb + "'";
        lstXtcs_u =super.findAllByHQL(sSql);
        if (lstXtcs_u != null && lstXtcs_u.size() > 0) {
          poXtcsb_u = (PoXT_XTCSB) lstXtcs_u.get(0);
          poXtcsb_u.setBdlx(PublicConstant.XT_BDLX_UPDATE); //变动类型
          poXtcsb_u.setBdsj(StringUtils.getServiceTime());
         super.update(poXtcsb_u);
        }
      }

      lstXtcs.add(rtnpoXTCSB);
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
    return lstXtcs;
  }

  /**
   *修改系统参数信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtcsxgxx(VarVO vvXTCSB) throws ServiceException, DAOException {
    ArrayList lstXtcs = new ArrayList();
    PoXT_XTCSB poXtcsb = null, poXtcsb_u = null;
    PoXT_XTCSB rtnpoXTCSB = new PoXT_XTCSB();
    String sSql = "";
    List lstXtcs_u;
    //获取参数信息,设置PO语句
    try {
      poXtcsb  = super.get(PoXT_XTCSB.class,Long.valueOf(vvXTCSB.getVarValue(
          "csid")));
      if (poXtcsb == null) { //如果查询不到记录
        return null;
      }
      BeanUtils.populate(poXtcsb, vvXTCSB.getVarmap());
      poXtcsb.setBdlx(PublicConstant.XT_BDLX_UPDATE); //变动类型
      poXtcsb.setBdsj(StringUtils.getServiceTime());
      //执行
      rtnpoXTCSB =  (PoXT_XTCSB)super.update(poXtcsb);
      lstXtcs.add(rtnpoXTCSB);
      String sCslb = poXtcsb.getCslb();
      //如果增加的参数类别不是9999，要修改相应的9999的变动时间
      if (!"9999".equals(sCslb)) {
        sSql =
            "select XT_XTCSB FROM PoXT_XTCSB as XT_XTCSB where cslb = '9999' and dm='"
            + sCslb + "'";
        lstXtcs_u =super.findAllByHQL(sSql);
        if (lstXtcs_u != null && lstXtcs_u.size() > 0) {
          poXtcsb_u = (PoXT_XTCSB) lstXtcs_u.get(0);
          poXtcsb_u.setBdlx(PublicConstant.XT_BDLX_UPDATE); //变动类型
          poXtcsb_u.setBdsj(StringUtils.getServiceTime());
         super.update(poXtcsb_u);
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
    return lstXtcs;

  }

  /**
   *删除系统参数信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtcsscxx(VarVO vvXTCSB) throws ServiceException, DAOException {
    String sHql = null;
    PoXT_XTCSB rtnpoXTCSB = new PoXT_XTCSB();
    PoXT_XTCSB poXTCSB = new PoXT_XTCSB();
    int nReturn = PublicConstant.XT_BZ_ERROR;
    PoXT_XTCSB poXtcsb_c, poXtcsb_u;
    String sSql = "";
    List lstXtcs_u;
    //执行HQL语句
    try {
      poXTCSB.setCsid(Long.valueOf(vvXTCSB.getVarValue("csid")));
      super.delete(poXTCSB);

      poXtcsb_c  = super.get(PoXT_XTCSB.class,Long.valueOf(vvXTCSB.getVarValue(
          "csid")));
      if (poXtcsb_c == null) { //如果查询不到记录
        return PublicConstant.XT_BZ_ERROR;
      }

      String sCslb = poXtcsb_c.getCslb();
      //如果增加的参数类别不是9999，要修改相应的9999的变动时间
      if (!"9999".equals(sCslb)) {
        sSql =
            "select XT_XTCSB FROM PoXT_XTCSB as XT_XTCSB where cslb = '9999' and dm='"
            + sCslb + "'";
        lstXtcs_u =super.findAllByHQL(sSql);
        if (lstXtcs_u != null && lstXtcs_u.size() > 0) {
          poXtcsb_u = (PoXT_XTCSB) lstXtcs_u.get(0);
          poXtcsb_u.setBdlx(PublicConstant.XT_BDLX_UPDATE); //变动类型
          poXtcsb_u.setBdsj(StringUtils.getServiceTime());
         super.update(poXtcsb_u);
        }
      }
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
   * @param strHQL
   * @param nPageOffset
   * @param nPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtcszdbdsj(VarVO vvXTCSB) throws ServiceException,
      DAOException {
    List lstXtcsxx = null;
    //执行HQL语句
    String sCslb = vvXTCSB.getVarValue("cslb");
    String sHqlWhere = "";
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append("select XT_XTCSB FROM PoXT_XTCSB as XT_XTCSB where  1=1 ");
    if ( (sCslb != null) && ! ("".equals(sCslb))) {
      sHqlWhere = " and cslb = '" + sCslb + "'";
    }
    strBufHql.append(sHqlWhere)
        .append(
        " and bdsj in (select max(XT_XTCSB.bdsj) FROM PoXT_XTCSB as XT_XTCSB where 1=1 ")
        .append(sHqlWhere).append(")");
    try {
      lstXtcsxx =super.getPageRecords(strBufHql.toString(),new Object[]{},
          PublicConstant.XT_PAGE,
          PublicConstant.XT_PAGESIZE).getList();
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
    return lstXtcsxx;
  }

}
