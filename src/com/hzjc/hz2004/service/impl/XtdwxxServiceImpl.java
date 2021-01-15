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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hzjc.wsstruts.vo.*;
import java.lang.reflect.*;
import com.hzjc.hz2004.base.login.AuthToken;
import com.hzjc.util.StringUtils;
import com.hzjc.hz2004.common.HjCommon;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 单位信息操作实现</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */
@Service
public class XtdwxxServiceImpl
    extends XtBaseService

    implements XtdwxxService {

  PojoInfo   Xt_dwxxbDao = DAOFactory.createXT_DWXXBDAO();
  PojoInfo   Xt_XtrzbDao = DAOFactory.createXT_XTRZBDAO();
  /**
   *查询信息
   * @param pxQryXtcs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtdwxx(String sHqlXtdwxx) throws ServiceException,
      DAOException {
    //执行HQL语句
    return getXtdwxx(sHqlXtdwxx, PublicConstant.XT_PAGE,
                     PublicConstant.XT_PAGESIZE);
  }

  public List getXtdwxx(String sHqlXtdwxx, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    //执行HQL语句
    List lstXtRtn = null;
    List lstReturn = new ArrayList();
    PoXT_QYSZB poXT_QYSZB = null;
    PoXT_XZQHB poXT_XZQHB = null;
    PoXT_DWXXB poXT_DWXXB = null;
    PoXT_XTCSB poXT_XTCSBA = null;
    PoXT_XTCSB poXT_XTCSBB = null;
    Object[] objXtRtn = null;
    //执行HQL语句
    try {
      lstXtRtn =super.getPageRecords(sHqlXtdwxx, new Object[]{}, nPageOffset,
                                                  nPageSize).getList();
      for (int i = 0; i < lstXtRtn.size(); i++) {
        objXtRtn = (Object[]) lstXtRtn.get(i);
        poXT_DWXXB = (PoXT_DWXXB) objXtRtn[0];
        poXT_XZQHB = (PoXT_XZQHB) objXtRtn[1];
        poXT_XTCSBA = (PoXT_XTCSB) objXtRtn[2];
        poXT_XTCSBB = (PoXT_XTCSB) objXtRtn[3];
        VoXtdwxx voXtdwxx = new VoXtdwxx(poXT_DWXXB
                                         , poXT_XZQHB, poXT_XTCSBA, poXT_XTCSBB
                                         );
        lstReturn.add(voXtdwxx);
      }
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ResultNotFoundException ex) {
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
  public VoQueryResult getXtdwxx(VarVO vvQryXtdwxx,String psWhere,
                                 VoPage voPage) throws ServiceException,
      DAOException {
    List lstXtdwxx = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    String sDm = vvQryXtdwxx.getVarValue("dm");
    String sQhdm = vvQryXtdwxx.getVarValue("qhdm");
    String sBz = vvQryXtdwxx.getVarValue("bz");
    String sQybz = vvQryXtdwxx.getVarValue("qybz");
    String sMc = vvQryXtdwxx.getVarValue("mc");
    String sZwpy = vvQryXtdwxx.getVarValue("zwpy");
    String sWbpy = vvQryXtdwxx.getVarValue("wbpy");
    String sDwjgdm = vvQryXtdwxx.getVarValue("dwjgdm");
    //dm,mc,zwpy,wbpy,dwjgdm,qhdm,bz,qybz,bdlx,bdsj;

    //HQL条件增加
    StringBuffer strBufHql = new StringBuffer();
    StringBuffer strCountHql = new StringBuffer();
    StringBuffer strHql = new StringBuffer();
    strBufHql.append(
        "select XT_DWXXB,XT_XZQHB,XT_XTCSBA,XT_XTCSBB FROM PoXT_DWXXB as XT_DWXXB,");
    strCountHql.append(" select count(*) from  PoXT_DWXXB as XT_DWXXB,");
    strHql.append(
        " PoXT_XZQHB as XT_XZQHB,PoXT_XTCSB as XT_XTCSBA,PoXT_XTCSB as XT_XTCSBB")
        .append(
        " where XT_DWXXB.dwjb = XT_XTCSBA.dm(+) and XT_XTCSBA.cslb(+) = '9024'")
        .append(
        " and XT_DWXXB.qybz = XT_XTCSBB.dm(+) and XT_XTCSBB.cslb(+) = '9002'")
        .append(" and  XT_DWXXB.qhdm = XT_XZQHB.dm ");

    if ( (sDm != null) && ! ("".equals(sDm))) {
      strHql.append(" and  XT_DWXXB.dm ='").append(sDm).append("'");
    }
    if ( (sMc != null) && ! ("".equals(sMc))) {
      strHql.append(" and  XT_DWXXB.mc ='").append(sMc).append("'");
    }
    if ( (sZwpy != null) && ! ("".equals(sZwpy))) {
      strHql.append(" and  XT_DWXXB.zwpy ='").append(sZwpy).append("'");
    }
    if ( (sWbpy != null) && ! ("".equals(sWbpy))) {
      strHql.append(" and  XT_DWXXB.wbpy ='").append(sWbpy).append("'");
    }
    if ( (sDwjgdm != null) && ! ("".equals(sDwjgdm))) {
      strHql.append(" and  XT_DWXXB.dwjgdm ='").append(sDwjgdm).append("'");
    }
    if ( (sQhdm != null) && ! ("".equals(sQhdm))) {
      strHql.append(" and  XT_DWXXB.qhdm ='").append(sQhdm).append("'");
    }
    if ( (sBz != null) && ! ("".equals(sBz))) {
      strHql.append(" and  XT_DWXXB.bz ='").append(sBz).append("'");
    }
    if ( (sQybz != null) && ! ("".equals(sQybz))) {
      strHql.append(" and  XT_DWXXB.qybz ='").append(sQybz).append("'");
    }
    if (psWhere != null && !"".equals(psWhere)) {
      strHql.append(" and (" + psWhere + ")");
    }
    strHql.append(" order by XT_DWXXB.qhdm,XT_DWXXB.dm");
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
    voQueryResult.setPagelist(getXtdwxx(strBufHql.toString(),
                                              voPage.getPageindex(),
                                              voPage.getPagesize()));
    voQueryResult.setVotype(VoXT_DWXXB.class);
    return voQueryResult;

  }

  /**
   *增加信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtdwxx(MultipartHttpServletRequest logoFile,VarVO vvXtdwxxb) throws ServiceException, DAOException {
    ArrayList lstXtdwxx = new ArrayList();
    PoXT_DWXXB poXTDWXXB = new PoXT_DWXXB();
    PoXT_DWXXB rtnpoXTDWXXB = new PoXT_DWXXB();
    MultipartFile logoFile1 = logoFile.getFile("logoFile");
    List lstXtxzqh;
    String sDwdm = "";
    String sQhdm = "";
    String sHql = "";
    //获取参数信息
    //dm,mc,zwpy,wbpy,dwjgdm,qhdm,bz,qybz,bdlx,bdsj;
    try {
      //判断单位代码是否9位
      sDwdm = vvXtdwxxb.getVarValue("dm");
      if (sDwdm.length() != 9) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                   "单位代码输入错误(不等于9)", null);
      }
      //判断单位代码区划代码是否一致
      sQhdm = vvXtdwxxb.getVarValue("qhdm");
      if (!sDwdm.substring(0, 6).equals(sQhdm)) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                   "单位代码输入错误(和区划代码不一致)", null);
      }
      //区划代码是否存在区划代码表中
      sHql = "select XT_XZQHB FROM PoXT_XZQHB as XT_XZQHB where dm='" + sQhdm +
          "'";
      PojoInfo  Xt_xzqhbDao = DAOFactory.createXT_XZQHBDAO();
      lstXtxzqh =super.findAllByHQL(sHql);
      if ( (lstXtxzqh == null) || (lstXtxzqh.isEmpty())) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                   "行政区划代码输入错误(无此行政区划代码)", null);
      }

      BeanUtils.populate(poXTDWXXB, vvXtdwxxb.getVarmap());
      poXTDWXXB.setQybz(PublicConstant.XT_BZ_QY); //启用标志
      poXTDWXXB.setBdlx(PublicConstant.XT_BDLX_ADD); //变动类型
      poXTDWXXB.setBdsj(StringUtils.getServiceTime());
      poXTDWXXB.setZp(logoFile1.getBytes());
      rtnpoXTDWXXB =(PoXT_DWXXB)super.create(poXTDWXXB);
      lstXtdwxx.add(rtnpoXTDWXXB);
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
    return lstXtdwxx;
  }

  /**
   * 修改信息
   * @param voXtgbcsb
   * @param atUser
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtdwxgxx(MultipartHttpServletRequest logoFile,VarVO vvXtdwxxb) throws ServiceException,
      DAOException {
    ArrayList lstXtdwxx = new ArrayList();
    PoXT_DWXXB poXTDWXXB = null;
    PoXT_DWXXB rtnpoXTDWXXB = new PoXT_DWXXB();
    MultipartFile logoFile1 = logoFile.getFile("logoFile");
    //dm,mc,zwpy,wbpy,dwjgdm,qhdm,bz,qybz,bdlx,bdsj;
    try {
      poXTDWXXB  = super.get(PoXT_DWXXB.class,vvXtdwxxb.getVarValue(
          "dm"));
      if (poXTDWXXB == null) {
        return null;
      }
      BeanUtils.populate(poXTDWXXB, vvXtdwxxb.getVarmap());
      poXTDWXXB.setBdlx(PublicConstant.XT_BDLX_UPDATE); //变动类型
      poXTDWXXB.setBdsj(StringUtils.getServiceTime());
      poXTDWXXB.setZp(logoFile1.getBytes());
      rtnpoXTDWXXB =   (PoXT_DWXXB)super.update(poXTDWXXB);
      lstXtdwxx.add(rtnpoXTDWXXB);
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
    return lstXtdwxx;
  }

  /**
   * 删除信息
   * @param voXtxzqhb
   * @param atUser
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtdwscxx(VarVO vvXtdwxxb) throws ServiceException,
      DAOException {
    PoXT_DWXXB poXTDWXXB = null;
    PoXT_DWXXB rtnpoXTDWXXB = new PoXT_DWXXB();
    ArrayList lstXtdwxx = new ArrayList();
    PoXT_XTRZB poXTRZB = new PoXT_XTRZB();
    int nReturn = PublicConstant.XT_BZ_ERROR;
    //组合poXT_XTCSB
    try {
      poXTDWXXB  = super.get(PoXT_DWXXB.class,vvXtdwxxb.getVarValue(
          "dm"));
      if (poXTDWXXB == null) {
        return null;
      }
      poXTDWXXB.setQybz(PublicConstant.XT_BZ_BQY); //变动类型
      poXTDWXXB.setBdlx(PublicConstant.XT_BDLX_DELETE); //变动类型
      poXTDWXXB.setBdsj(StringUtils.getServiceTime());
      rtnpoXTDWXXB =      (PoXT_DWXXB)super.update(poXTDWXXB);
      lstXtdwxx.add(rtnpoXTDWXXB);
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
    return lstXtdwxx;
  }

  /**
   * 最大变动时间
   * @param sHqlXtdwxx
   * @param nPageOffset
   * @param nPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtdwzdbdsj() throws ServiceException,
      DAOException {
    //执行HQL语句
    List lstXtRtn = null;
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append("select XT_DWXXB FROM PoXT_DWXXB as XT_DWXXB where ")
        .append(
        " bdsj in (select max(XT_DWXXB.bdsj) FROM PoXT_DWXXB as XT_DWXXB)");
    try {
      lstXtRtn =super.getPageRecords(strBufHql.toString(),new Object[]{},
                                                  PublicConstant.XT_PAGE,
                                                  1).getList();
      if (lstXtRtn == null) {
        String sHql =
            "select XT_DWXXB FROM PoXT_DWXXB as XT_DWXXB";
        lstXtRtn =super.getPageRecords(sHql,new Object[]{},
            0, 1).getList();
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

@Override
public List setXtdwxxResume(VarVO vvXtdwxxb) throws ServiceException,
DAOException{
    ArrayList lstXtdwxx = new ArrayList();
    PoXT_DWXXB poXTDWXXB = null;
    PoXT_DWXXB rtnpoXTDWXXB = new PoXT_DWXXB();
    //dm,mc,zwpy,wbpy,dwjgdm,qhdm,bz,qybz,bdlx,bdsj;
    try {
      poXTDWXXB  = super.get(PoXT_DWXXB.class,vvXtdwxxb.getVarValue(
          "dm"));
      if (poXTDWXXB == null) {
        return null;
      }
      BeanUtils.populate(poXTDWXXB, vvXtdwxxb.getVarmap());
      poXTDWXXB.setBdlx(PublicConstant.XT_BDLX_UPDATE); //变动类型
      poXTDWXXB.setBdsj(StringUtils.getServiceTime());

      rtnpoXTDWXXB =   (PoXT_DWXXB)super.update(poXTDWXXB);
      lstXtdwxx.add(rtnpoXTDWXXB);
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
    return lstXtdwxx;
  }
}
