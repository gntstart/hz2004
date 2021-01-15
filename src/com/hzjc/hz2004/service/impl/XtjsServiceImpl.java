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
 *                 用户角色操作实现</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */
@Service
public class XtjsServiceImpl
    extends XtBaseService

    implements XtjsService {

  PojoInfo   Xt_jsxxbDao = DAOFactory.createXT_JSXXBDAO();
  PojoInfo   Xt_XtrzbDao = DAOFactory.createXT_XTRZBDAO();
  PojoInfo   Xt_jsywbbqxbDao = DAOFactory.createXT_JSYWBBQXBDAO();
  PojoInfo   Xt_jszsbbqxbDao = DAOFactory.createXT_JSZSBBQXBDAO();
  PojoInfo   Xt_jsgnqxbDao = DAOFactory.createXT_JSGNQXBDAO();
  PojoInfo   Xt_jscdqxbDao = DAOFactory.createXT_JSCDQXBDAO();
  /**
   * 字符串查询
   * @param strHQL
   * @param nPageOffset
   * @param nPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtjsxx(String strHQL) throws ServiceException,
      DAOException {
    return getXtjsxx(strHQL, PublicConstant.PAGE_DEFAULT_OFFSET,
                     PublicConstant.PAGE_DEFAULT_SIZE);
  }

  public List getXtjsxx(String strHQL, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {

    List lstXtjsxx = null;

    //执行HQL语句
    try {
      lstXtjsxx =super.getPageRecords(strHQL, new Object[]{}, nPageOffset,
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
    return lstXtjsxx;
  }

  /**
   *查询用户角色信息
   * @param iReqPage
   * @param iPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtjsxx(VarVO vvHqlQryXtjs) throws ServiceException,
      DAOException {
    String sJsid = vvHqlQryXtjs.getVarValue("jsid");
    int nPageOffset = PublicConstant.XT_PAGE; //分页
    int nPageSize = PublicConstant.XT_PAGESIZE;
    boolean bCjyh = this.VerifyYH(this.getUserInfo().getYhid().toString());
    //HQL条件语句
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append("select XT_JSXXB FROM PoXT_JSXXB as XT_JSXXB ");
    if (!bCjyh) {
      strBufHql.append(", PoXT_YHJSXXB as XT_YHJSXXB where XT_JSXXB.jsid = ")
          .append("XT_YHJSXXB.jsid and XT_YHJSXXB.yhid =")
          .append(this.getUserInfo().getYhid().toString());
    }
    else {
      strBufHql.append(" where 1=1");
    }
    if ( (sJsid != null) && ! ("".equals(sJsid))) {
      strBufHql.append(" and  XT_JSXXB.jsid =").append(sJsid);
    }

    strBufHql.append(" order by XT_JSXXB.jsid,XT_JSXXB.jsmc");
    return getXtjsxx(strBufHql.toString(), nPageOffset, nPageSize);
  }

  /**
   *增加用户角色信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjsxx(VarVO vvXTJSB) throws ServiceException, DAOException {
    ArrayList lstXtjs = new ArrayList();
    PoXT_JSXXB poXtjsb = new PoXT_JSXXB();
    PoXT_JSXXB rtnpoXTJSB = new PoXT_JSXXB();

    try {
      //执行HQL语句
      BeanUtils.populate(poXtjsb, vvXTJSB.getVarmap());
      poXtjsb.setJsid( (Long) Xt_jsxxbDao.getId());
      rtnpoXTJSB =(PoXT_JSXXB)super.create(poXtjsb);
      lstXtjs.add(rtnpoXTJSB);
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
    return lstXtjs;
  }

  /**
   *修改用户角色信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjsxgxx(VarVO vvXTjsB) throws ServiceException, DAOException {
    ArrayList lstXtjs = new ArrayList();
    PoXT_JSXXB poXtJSb = null;
    PoXT_JSXXB rtnpoXTJSB = new PoXT_JSXXB();
    //获取参数信息,设置PO语句
    try {
      poXtJSb  = super.get(PoXT_JSXXB.class,Long.valueOf(vvXTjsB.getVarValue(
          "jsid")));
      if (poXtJSb == null) {
        return null;
      }
      BeanUtils.populate(poXtJSb, vvXTjsB.getVarmap());
      //执行
      rtnpoXTJSB =      (PoXT_JSXXB)super.update(poXtJSb);
      lstXtjs.add(rtnpoXTJSB);
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
    return lstXtjs;

  }

  /**
   *删除用户角色信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtjsscxx(VarVO vvXTjsB) throws ServiceException, DAOException {
    PoXT_JSXXB poXTJSB = new PoXT_JSXXB();
    PoXT_YHJSXXB poXTYHJSB = new PoXT_YHJSXXB();
    int nReturn = PublicConstant.XT_BZ_ERROR;
    List lstXtyhjs = null;
    List lstXtjs = null;
    //执行HQL语句
    try {
      /**
       * 首先根据用户角色信息判断是否存在
       * 用户有此角色
       */
      PojoInfo  Xt_yhjsxxbDao = DAOFactory.createXT_YHJSXXBDAO();
      StringBuffer strBufHql = new StringBuffer();
      strBufHql.append(
          " select XT_YHJSXXB FROM PoXT_YHJSXXB as XT_YHJSXXB where ")
          .append(" jsid = ").append(vvXTjsB.getVarValue("jsid"));
      lstXtyhjs =super.findAllByHQL(strBufHql.toString());
      if (lstXtyhjs != null && !lstXtyhjs.isEmpty()) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                   "角色正在使用", null);
      }
      /////////////////////////////////////////////////////////////////////////////
      //
      strBufHql.setLength(0);
      strBufHql.append(
          " select XT_JSXXB FROM PoXT_JSXXB as XT_JSXXB where jsmc = '")
          .append(PublicConstant.XT_CJYHMC).append("'")
          .append(" and jsid = ").append(vvXTjsB.getVarValue("jsid"));
      lstXtjs =super.findAllByHQL(strBufHql.toString());
      if (lstXtjs != null && !lstXtjs.isEmpty()) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                   "超级用户角色不能删除", null);
      }
      BeanUtils.populate(poXTJSB, vvXTjsB.getVarmap());
      super.delete(poXTJSB);
      //记录操作日志
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
   * 字符串查询
   * @param strHQL
   * @param nPageOffset
   * @param nPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtjsywbbxx(String strHQL) throws ServiceException,
      DAOException {
    return getXtjsywbbxx(strHQL, PublicConstant.PAGE_DEFAULT_OFFSET,
                         PublicConstant.PAGE_DEFAULT_SIZE);
  }

  public List getXtjsywbbxx(String strHQL, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    List lstXtjsxx = null;
    PoXT_JSYWBBQXB poXT_JSYWBBQXB = null;
    PoXT_YWBBMBXXB poXT_YWBBMBXXB = null;
    PoXT_JSXXB poXT_JSXXB = null;
    Object[] objXtRtn = null;
    List lstXtRtn = new ArrayList();
    //执行HQL语句
    try {
      lstXtjsxx =super.getPageRecords(strHQL,new Object[]{}, nPageOffset,
          nPageSize).getList();
      for (int i = 0; i < lstXtjsxx.size(); i++) {
        objXtRtn = (Object[]) lstXtjsxx.get(i);
        poXT_JSYWBBQXB = (PoXT_JSYWBBQXB) objXtRtn[0];
        poXT_JSXXB = (PoXT_JSXXB) objXtRtn[1];
        poXT_YWBBMBXXB = (PoXT_YWBBMBXXB) objXtRtn[2];
        VoXtjsywbb voXtjsywbbxx = new VoXtjsywbb(poXT_JSYWBBQXB, poXT_JSXXB,
                                                 poXT_YWBBMBXXB);
        lstXtRtn.add(voXtjsywbbxx);
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

  /**
   *查询用户角色业务报表信息
   * @param iReqPage
   * @param iPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtjsywbbxx(VarVO vvHqlQryXtjs) throws ServiceException,
      DAOException {
    String sYwbbqxid = vvHqlQryXtjs.getVarValue("ywbbqxid");
    String sJsid = vvHqlQryXtjs.getVarValue("jsid");
    int nPageOffset = PublicConstant.XT_PAGE; //分页
    int nPageSize = PublicConstant.XT_PAGESIZE;
    //获取参数信息

    //HQL条件语句
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        "select XT_JSYWBBQXB,XT_JSXXB,XT_YWBBMBXXB FROM PoXT_JSYWBBQXB as XT_JSYWBBQXB,")
        .append("PoXT_JSXXB as XT_JSXXB,PoXT_YWBBMBXXB as XT_YWBBMBXXB where ")
        .append(" XT_JSYWBBQXB.jsid = XT_JSXXB.jsid ")
        .append(" and XT_JSYWBBQXB.ywbbid = XT_YWBBMBXXB.ywbbid ");
    if ( (sYwbbqxid != null) && ! ("".equals(sYwbbqxid))) {
      strBufHql.append(" and  XT_JSYWBBQXB.ywbbqxid =").append(sYwbbqxid);
    }
    if ( (sJsid != null) && ! ("".equals(sJsid))) {
      strBufHql.append(" and  XT_JSYWBBQXB.jsid =").append(sJsid);
    }
    strBufHql.append(" order by XT_JSYWBBQXB.jsid");
    return getXtjsywbbxx(strBufHql.toString(), nPageOffset, nPageSize);
  }

  /**
   *增加角色业务报表权限
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjsywbbxx(VarVO vvXTJSB) throws ServiceException,
      DAOException {
    ArrayList lstXtjs = new ArrayList();
    PoXT_JSYWBBQXB poXtjsywbbqxb = new PoXT_JSYWBBQXB();
    PoXT_JSYWBBQXB rtnpoXTJSYWBBQXB = new PoXT_JSYWBBQXB();
    try {
      //执行HQL语句
      BeanUtils.populate(poXtjsywbbqxb, vvXTJSB.getVarmap());
      poXtjsywbbqxb.setYwbbqxid( (Long) Xt_jsywbbqxbDao.getId());

      rtnpoXTJSYWBBQXB =(PoXT_JSYWBBQXB)super.create(poXtjsywbbqxb);
      lstXtjs.add(rtnpoXTJSYWBBQXB);
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
    return lstXtjs;
  }

  /**
   *修改角色业务报表权限
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjsywbbxgxx(VarVO vvXTjsB) throws ServiceException,
      DAOException {
    ArrayList lstXtjs = new ArrayList();
    String sHql = null;
    PoXT_JSYWBBQXB poXtJSYWBBQXb = new PoXT_JSYWBBQXB();
    PoXT_JSYWBBQXB rtnpoXTJSYWBBQXB = new PoXT_JSYWBBQXB();
    //获取参数信息,设置PO语句
    try {
      poXtJSYWBBQXb  = super.get(PoXT_JSYWBBQXB.class,Long.valueOf(
          vvXTjsB.getVarValue("ywbbqxid")));
      if (poXtJSYWBBQXb == null) {
        return null;
      }
      BeanUtils.populate(poXtJSYWBBQXb, vvXTjsB.getVarmap());
      rtnpoXTJSYWBBQXB =      (PoXT_JSYWBBQXB)super.update(poXtJSYWBBQXb);
      lstXtjs.add(rtnpoXTJSYWBBQXB);
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
    return lstXtjs;

  }

  /**
   *删除用户角色业务报表
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtjsywbbscxx(VarVO vvXTjsB) throws ServiceException,
      DAOException {
    PoXT_JSYWBBQXB rtnpoXTJSYWBBQXB = new PoXT_JSYWBBQXB();
    PoXT_JSYWBBQXB poXTJSYWBBQXB = new PoXT_JSYWBBQXB();
    int nReturn = PublicConstant.XT_BZ_ERROR;
    //获取参数信息
    String sYwbbqxid = vvXTjsB.getVarValue("ywbbqxid");
    String sJsid = vvXTjsB.getVarValue("jsid");
    String sYwbbid = vvXTjsB.getVarValue("ywbbid");
    try {
      StringBuffer strBufHql = new StringBuffer();
      strBufHql.append(" from PoXT_JSYWBBQXB where 1=1 ");

      if ( (sYwbbqxid != null) && ! ("".equals(sYwbbqxid))) {
        strBufHql.append(" and  ywbbqxid =").append(sYwbbqxid);
      }
      if ( (sYwbbid != null) && ! ("".equals(sYwbbid))) {
        strBufHql.append(" and  ywbbid =").append(sYwbbid);
      }
      if ( (sJsid != null) && ! ("".equals(sJsid))) {
        strBufHql.append(" and  jsid =").append(sJsid);
      }
      nReturn = super.deleteAll(super.findAllByHQL(strBufHql.toString()));
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
   * 字符串查询
   * @param strHQL
   * @param nPageOffset
   * @param nPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtjszsbbxx(String strHQL) throws ServiceException,
      DAOException {
    return getXtjszsbbxx(strHQL, PublicConstant.XT_PAGE,
                         PublicConstant.XT_PAGESIZE);
  }

  public List getXtjszsbbxx(String strHQL, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    List lstXtjsxx = null;
    PoXT_JSZSBBQXB poXT_JSZSBBQXB = null;
    PoXT_ZSBBMBXXB poXT_ZSBBMBXXB = null;
    PoXT_JSXXB poXT_JSXXB = null;
    Object[] objXtRtn = null;
    List lstXtRtn = new ArrayList();
    //执行HQL语句
    try {
      lstXtjsxx =super.getPageRecords(strHQL, new Object[]{}, nPageOffset,
          nPageSize).getList();
      for (int i = 0; i < lstXtjsxx.size(); i++) {
        objXtRtn = (Object[]) lstXtjsxx.get(i);
        poXT_JSZSBBQXB = (PoXT_JSZSBBQXB) objXtRtn[0];
        poXT_JSXXB = (PoXT_JSXXB) objXtRtn[1];
        poXT_ZSBBMBXXB = (PoXT_ZSBBMBXXB) objXtRtn[2];
        VoXtjszsbb voXtjszsbbxx = new VoXtjszsbb(poXT_JSZSBBQXB, poXT_JSXXB,
                                                 poXT_ZSBBMBXXB);
        lstXtRtn.add(voXtjszsbbxx);
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

  /**
   *查询用户角色报表信息
   * @param iReqPage
   * @param iPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtjszsbbxx(VarVO vvHqlQryXtjs) throws ServiceException,
      DAOException {
    String sZsbbqxid = vvHqlQryXtjs.getVarValue("zsbbqxid");
    String sJsid = vvHqlQryXtjs.getVarValue("jsid");
    int nPageOffset = PublicConstant.XT_PAGE; //分页
    int nPageSize = PublicConstant.XT_PAGESIZE;
    //获取参数信息
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        "select XT_JSZSBBQXB,XT_JSXXB,XT_ZSBBMBXXB FROM PoXT_JSZSBBQXB as XT_JSZSBBQXB,")
        .append("PoXT_JSXXB as XT_JSXXB,PoXT_ZSBBMBXXB as XT_ZSBBMBXXB where ")
        .append(" XT_JSZSBBQXB.jsid = XT_JSXXB.jsid ")
        .append(" and XT_JSZSBBQXB.zsbbmbid = XT_ZSBBMBXXB.zsbbmbid ");
    if ( (sZsbbqxid != null) && ! ("".equals(sZsbbqxid))) {
      strBufHql.append(" and  XT_JSZSBBQXB.zsbbqxid =").append(sZsbbqxid);
    }
    if ( (sJsid != null) && ! ("".equals(sJsid))) {
      strBufHql.append(" and  XT_JSZSBBQXB.jsid =").append(sJsid);
    }
    strBufHql.append(" order by XT_JSZSBBQXB.jsid");
    return getXtjszsbbxx(strBufHql.toString(), nPageOffset, nPageSize);
  }

  /**
   *增加角色报表权限
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjszsbbxx(VarVO vvXTJSB) throws ServiceException,
      DAOException {
    ArrayList lstXtjs = new ArrayList();
    PoXT_JSZSBBQXB poXtjszsbbqxb = new PoXT_JSZSBBQXB();
    PoXT_JSZSBBQXB rtnpoXTJSZSBBQXB = new PoXT_JSZSBBQXB();
    try {
      //执行HQL语句
      BeanUtils.populate(poXtjszsbbqxb, vvXTJSB.getVarmap());
      poXtjszsbbqxb.setZsbbqxid( (Long) Xt_jszsbbqxbDao.getId());

      rtnpoXTJSZSBBQXB =(PoXT_JSZSBBQXB)super.create(poXtjszsbbqxb);
      lstXtjs.add(rtnpoXTJSZSBBQXB);
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
    return lstXtjs;
  }

  /**
   *修改角色报表权限
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjszsbbxgxx(VarVO vvXTjsB) throws ServiceException,
      DAOException {
    ArrayList lstXtjs = new ArrayList();
    PoXT_JSZSBBQXB poXtJSZSBBQXb = new PoXT_JSZSBBQXB();
    PoXT_JSZSBBQXB rtnpoXTJSZSBBQXB = new PoXT_JSZSBBQXB();
    //获取参数信息,设置PO语句
    try {
      poXtJSZSBBQXb  = super.get(PoXT_JSZSBBQXB.class,Long.valueOf(
          vvXTjsB.getVarValue("ywbbqxid")));
      if (poXtJSZSBBQXb == null) {
        return null;
      }
      BeanUtils.populate(poXtJSZSBBQXb, vvXTjsB.getVarmap());
      rtnpoXTJSZSBBQXB =      (PoXT_JSZSBBQXB)super.update(poXtJSZSBBQXb);
      lstXtjs.add(rtnpoXTJSZSBBQXB);
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
    return lstXtjs;

  }

  /**
   *删除用户角色报表
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtjszsbbscxx(VarVO vvXTjsB) throws ServiceException,
      DAOException {
    PoXT_JSZSBBQXB rtnpoXTJSZSBBQXB = new PoXT_JSZSBBQXB();
    PoXT_JSZSBBQXB poXTJSZSBBQXB = new PoXT_JSZSBBQXB();
    int nReturn = PublicConstant.XT_BZ_ERROR;
    //获取参数信息
    String sZsbbqxid = vvXTjsB.getVarValue("zsbbqxid");
    String sJsid = vvXTjsB.getVarValue("jsid");
    String sZsbbmbid = vvXTjsB.getVarValue("Zsbbmbid");
    try {
      StringBuffer strBufHql = new StringBuffer();
      strBufHql.append(" from PoXT_JSZSBBQXB where 1=1 ");

      if ( (sZsbbqxid != null) && ! ("".equals(sZsbbqxid))) {
        strBufHql.append(" and  zsbbqxid =").append(sZsbbqxid);
      }
      if ( (sZsbbmbid != null) && ! ("".equals(sZsbbmbid))) {
        strBufHql.append(" and  zsbbmbid =").append(sZsbbmbid);
      }
      if ( (sJsid != null) && ! ("".equals(sJsid))) {
        strBufHql.append(" and  jsid =").append(sJsid);
      }
      nReturn = super.deleteAll(super.findAllByHQL(strBufHql.toString()));
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
   * 字符串查询
   * @param strHQL
   * @param nPageOffset
   * @param nPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtjsgnqxxx(String strHQL) throws ServiceException,
      DAOException {
    return getXtjsgnqxxx(strHQL, PublicConstant.XT_PAGE,
                         PublicConstant.XT_PAGESIZE);
  }

  public List getXtjsgnqxxx(String strHQL, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    List lstXtjsxx = null;
    PoXT_JSGNQXB poXT_JSGNQXB = null;
    PoXT_XTGNB poXT_XTGNB = null;
    PoXT_JSXXB poXT_JSXXB = null;
    Object[] objXtRtn = null;
    List lstXtRtn = new ArrayList();
    //执行HQL语句
    try {
      lstXtjsxx =super.getPageRecords(strHQL, new Object[]{}, nPageOffset,
          nPageSize).getList();
      for (int i = 0; i < lstXtjsxx.size(); i++) {
        objXtRtn = (Object[]) lstXtjsxx.get(i);
        poXT_JSGNQXB = (PoXT_JSGNQXB) objXtRtn[0];
        poXT_JSXXB = (PoXT_JSXXB) objXtRtn[1];
        poXT_XTGNB = (PoXT_XTGNB) objXtRtn[2];
        VoXtjsgnqx voXtjsgnqxxx = new VoXtjsgnqx(poXT_JSGNQXB, poXT_JSXXB,
                                                 poXT_XTGNB);
        lstXtRtn.add(voXtjsgnqxxx);
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

  /**
   *查询角色功能权限
   * @param iReqPage
   * @param iPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtjsgnqxxx(VarVO vvHqlQryXtjs) throws ServiceException,
      DAOException {
    String sJsgnid = vvHqlQryXtjs.getVarValue("jsgnid");
    String sJsid = vvHqlQryXtjs.getVarValue("jsid");
    int nPageOffset = PublicConstant.XT_PAGE; //分页
    int nPageSize = PublicConstant.XT_PAGESIZE;

    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        "select XT_JSGNQXB,XT_JSXXB,XT_XTGNB FROM PoXT_JSGNQXB as XT_JSGNQXB,")
        .append("PoXT_JSXXB as XT_JSXXB,PoXT_XTGNB as XT_XTGNB where ")
        .append(" XT_JSGNQXB.jsid = XT_JSXXB.jsid ")
        .append(" and XT_JSGNQXB.gnid = XT_XTGNB.gnid ");
    if ( (sJsgnid != null) && ! ("".equals(sJsgnid))) {
      strBufHql.append(" and  XT_JSGNQXB.jsgnid =").append(sJsgnid);
    }
    if ( (sJsid != null) && ! ("".equals(sJsid))) {
      strBufHql.append(" and  XT_JSGNQXB.jsid =").append(sJsid);
    }
    strBufHql.append(" order by XT_JSGNQXB.gnid");
    return getXtjsgnqxxx(strBufHql.toString(), nPageOffset, nPageSize);
  }

  /**
   *增加角色功能权限
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjsgnqxxx(VarVO vvXTJSB) throws ServiceException,
      DAOException {
    ArrayList lstXtjs = new ArrayList();
    PoXT_JSGNQXB poXtjsgnqxb = new PoXT_JSGNQXB();
    PoXT_JSGNQXB rtnpoXTJSGNQXB = new PoXT_JSGNQXB();
    try {
      //执行HQL语句
      BeanUtils.populate(poXtjsgnqxb, vvXTJSB.getVarmap());
      poXtjsgnqxb.setJsgnid( (Long) Xt_jsgnqxbDao.getId());

      rtnpoXTJSGNQXB =(PoXT_JSGNQXB)super.create(poXtjsgnqxb);
      lstXtjs.add(rtnpoXTJSGNQXB);
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
    return lstXtjs;
  }

  /**
   *修改角色功能权限
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjsgnqxxgxx(VarVO vvXTjsB) throws ServiceException,
      DAOException {
    ArrayList lstXtjs = new ArrayList();
    PoXT_JSGNQXB poXtJSGNQXb = new PoXT_JSGNQXB();
    PoXT_JSGNQXB rtnpoXTJSGNQXB = new PoXT_JSGNQXB();
    //获取参数信息,设置PO语句
    try {
      poXtJSGNQXb  = super.get(PoXT_JSGNQXB.class,Long.valueOf(
          vvXTjsB.getVarValue("jsgnid")));
      if (poXtJSGNQXb == null) {
        return null;
      }
      BeanUtils.populate(poXtJSGNQXb, vvXTjsB.getVarmap());
      rtnpoXTJSGNQXB =   (PoXT_JSGNQXB)super.update(poXtJSGNQXb);
      lstXtjs.add(rtnpoXTJSGNQXB);
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
    return lstXtjs;

  }

  /**
   *删除角色功能权限
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtjsgnqxscxx(VarVO vvXTjsB) throws ServiceException,
      DAOException {
    PoXT_JSGNQXB rtnpoXTJSGNQXB = new PoXT_JSGNQXB();
    PoXT_JSGNQXB poXTJSGNQXB = new PoXT_JSGNQXB();
    int nReturn = PublicConstant.XT_BZ_ERROR;
    //获取参数信息
    String sJsgnid = vvXTjsB.getVarValue("jscdid");
    String sJsid = vvXTjsB.getVarValue("jsid");
    String sGnid = vvXTjsB.getVarValue("gnid");
    try {
      StringBuffer strBufHql = new StringBuffer();
      strBufHql.append(" from PoXT_JSGNQXB where 1=1 ");

      if ( (sJsgnid != null) && ! ("".equals(sJsgnid))) {
        strBufHql.append(" and  jsgnid =").append(sJsgnid);
      }
      if ( (sGnid != null) && ! ("".equals(sGnid))) {
        strBufHql.append(" and  gnid =").append(sGnid);
      }
      if ( (sJsid != null) && ! ("".equals(sJsid))) {
        strBufHql.append(" and  jsid =").append(sJsid);
      }
      nReturn = super.deleteAll(super.findAllByHQL(strBufHql.toString()));
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
   * 字符串查询
   * @param strHQL
   * @param nPageOffset
   * @param nPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtjscdqxxx(String strHQL) throws ServiceException,
      DAOException {
    return getXtjscdqxxx(strHQL, PublicConstant.XT_PAGE,
                         PublicConstant.XT_PAGESIZE);
  }

  public List getXtjscdqxxx(String strHQL, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    List lstXtjsxx = null;
    PoXT_JSCDQXB poXT_JSCDQXB = null;
    PoXT_XTGNCDB poXT_XTGNCDB = null;
    PoXT_JSXXB poXT_JSXXB = null;
    Object[] objXtRtn = null;
    List lstXtRtn = new ArrayList();
    //执行HQL语句
    try {
      lstXtjsxx =super.getPageRecords(strHQL, new Object[]{}, nPageOffset,
          nPageSize).getList();
      for (int i = 0; i < lstXtjsxx.size(); i++) {
        objXtRtn = (Object[]) lstXtjsxx.get(i);
        poXT_JSCDQXB = (PoXT_JSCDQXB) objXtRtn[0];
        poXT_JSXXB = (PoXT_JSXXB) objXtRtn[1];
        poXT_XTGNCDB = (PoXT_XTGNCDB) objXtRtn[2];
        VoXtjscdqx voXtjscdqxxx = new VoXtjscdqx(poXT_JSCDQXB, poXT_JSXXB,
                                                 poXT_XTGNCDB);
        lstXtRtn.add(voXtjscdqxxx);
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

  /**
   *查询角色菜单权限
   * @param iReqPage
   * @param iPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtjscdqxxx(VarVO vvHqlQryXtjs) throws ServiceException,
      DAOException {
    String sJscdid = vvHqlQryXtjs.getVarValue("jscdid");
    String sJsid = vvHqlQryXtjs.getVarValue("jsid");
    int nPageOffset = PublicConstant.XT_PAGE; //分页
    int nPageSize = PublicConstant.XT_PAGESIZE;

    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        "select XT_JSCDQXB,XT_JSXXB,XT_XTGNCDB FROM PoXT_JSCDQXB as XT_JSCDQXB,")
        .append("PoXT_JSXXB as XT_JSXXB,PoXT_XTGNCDB as XT_XTGNCDB where ")
        .append(" XT_JSCDQXB.jsid = XT_JSXXB.jsid ")
        .append(" and XT_JSCDQXB.gncdid = XT_XTGNCDB.gncdid ");
    if ( (sJscdid != null) && ! ("".equals(sJscdid))) {
      strBufHql.append(" and  XT_JSCDQXB.jscdid =").append(sJscdid);
    }
    if ( (sJsid != null) && ! ("".equals(sJsid))) {
      strBufHql.append(" and  XT_JSCDQXB.jsid =").append(sJsid);
    }
    strBufHql.append(" order by XT_JSCDQXB.gncdid");

    return getXtjscdqxxx(strBufHql.toString(), nPageOffset, nPageSize);
  }

  /**
   *增加角色菜单权限
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjscdqxxx(VarVO vvXTJSB) throws ServiceException,
      DAOException {
    ArrayList lstXtjs = new ArrayList();
    PoXT_JSCDQXB poXtjscdqxb = new PoXT_JSCDQXB();
    PoXT_JSCDQXB rtnpoXTJSCDQXB = new PoXT_JSCDQXB();
    try {
      //执行HQL语句
      BeanUtils.populate(poXtjscdqxb, vvXTJSB.getVarmap());
      poXtjscdqxb.setJscdid( (Long) Xt_jscdqxbDao.getId());

      rtnpoXTJSCDQXB =(PoXT_JSCDQXB)super.create(poXtjscdqxb);
      lstXtjs.add(rtnpoXTJSCDQXB);
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
    return lstXtjs;
  }

  /**
   *修改角色菜单权限
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjscdqxxgxx(VarVO vvXTjsB) throws ServiceException,
      DAOException {
    ArrayList lstXtjs = new ArrayList();
    PoXT_JSCDQXB poXtJSCDQXb = new PoXT_JSCDQXB();
    PoXT_JSCDQXB rtnpoXTJSCDQXB = new PoXT_JSCDQXB();
    //获取参数信息,设置PO语句
    try {
      poXtJSCDQXb  = super.get(PoXT_JSCDQXB.class,Long.valueOf(
          vvXTjsB.getVarValue("jscdid")));
      if (poXtJSCDQXb == null) {
        return null;
      }
      BeanUtils.populate(poXtJSCDQXb, vvXTjsB.getVarmap());
      rtnpoXTJSCDQXB =      (PoXT_JSCDQXB)super.update(poXtJSCDQXb);
      lstXtjs.add(rtnpoXTJSCDQXB);
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
    return lstXtjs;

  }

  /**
   *删除角色菜单权限
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtjscdqxscxx(VarVO vvXTjsB) throws ServiceException,
      DAOException {
    int nReturn = PublicConstant.XT_BZ_ERROR;
    //获取参数信息
    String sJscdid = vvXTjsB.getVarValue("jscdid");
    String sJsid = vvXTjsB.getVarValue("jsid");
    try {
      StringBuffer strBufHql = new StringBuffer();
      strBufHql.append(" from PoXT_JSCDQXB where 1=1 ");

      if ( (sJscdid != null) && ! ("".equals(sJscdid))) {
        strBufHql.append(" and  jscdid =").append(sJscdid);
      }
      if ( (sJsid != null) && ! ("".equals(sJsid))) {
        strBufHql.append(" and  jsid =").append(sJsid);
      }
      nReturn = super.deleteAll(super.findAllByHQL(strBufHql.toString()));
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
