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
 *                 历年尾数段操作实现</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */
@Service
public class XtlnwsdServiceImpl
    extends XtBaseService

    implements XtlnwsdService {

  PojoInfo   Xt_lnwsdbDao = DAOFactory.createXT_LNWSDBDAO();
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
  public List getXtlnwsdxx(String strHQL) throws ServiceException,
      DAOException {
    //执行HQL语句
    return getXtlnwsdxx(strHQL, PublicConstant.PAGE_DEFAULT_OFFSET,
                        PublicConstant.PAGE_DEFAULT_SIZE);
  }

  public List getXtlnwsdxx(String strHQL, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    List lstXtlnwsdxx = null;
    List lstReturn = new ArrayList();
    PoXT_LNWSDB poXT_LNWSDB = null;
    PoXT_XZQHB poXT_XZQHB = null;
    PoXT_DWXXB poXT_DWXXB = null;
    Object[] objXtRtn = null;
    //执行HQL语句
    try {
      lstXtlnwsdxx =super.getPageRecords(strHQL, new Object[]{}, nPageOffset,
          nPageSize).getList();
      for (int i = 0; i < lstXtlnwsdxx.size(); i++) {
        objXtRtn = (Object[]) lstXtlnwsdxx.get(i);
        poXT_LNWSDB = (PoXT_LNWSDB) objXtRtn[0];
        poXT_XZQHB = (PoXT_XZQHB) objXtRtn[1];
        poXT_DWXXB = (PoXT_DWXXB) objXtRtn[2];
        VoXtlnwsd voXtlnwsdxx = new VoXtlnwsd(poXT_LNWSDB, poXT_XZQHB,
                                              poXT_DWXXB);
        lstReturn.add(voXtlnwsdxx);
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
   *查询信息
   * @param iReqPage
   * @param iPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtlnwsdxx(VarVO vvQryXtlnwsd) throws ServiceException,
      DAOException {
    int nPageOffset = 0; //分页
    int nPageSize = -1;
    //获取参数信息
    String sWsdid = vvQryXtlnwsd.getVarValue("wsdid");
    String sQhdm = vvQryXtlnwsd.getVarValue("qhdm");
    String sDwdm = vvQryXtlnwsd.getVarValue("dwdm");
    //获取参数信息
    //wsdid,qhdm,dwdm,ksd,jsd,qyrq,bz;
    //HQL条件语句
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        "select XT_LNWSDB,XT_XZQHB,XT_DWXXB FROM PoXT_LNWSDB as XT_LNWSDB,")
        .append(" PoXT_XZQHB as XT_XZQHB,PoXT_DWXXB as XT_DWXXB where ")
        .append(" XT_LNWSDB.qhdm=XT_XZQHB.dm ")
        .append(" and XT_LNWSDB.dwdm=XT_DWXXB.dm ");
    if ( (sWsdid != null) && ! ("".equals(sWsdid))) {
      strBufHql.append(" and  XT_LNWSDB.wsdid =").append(sWsdid);
    }
    if ( (sQhdm != null) && ! ("".equals(sQhdm))) {
      strBufHql.append(" and  XT_LNWSDB.qhdm =").append(sQhdm);
    }
    if ( (sDwdm != null) && ! ("".equals(sDwdm))) {
      strBufHql.append(" and  XT_LNWSDB.dwdm =").append(sDwdm);
    }
    strBufHql.append(
        " order by XT_LNWSDB.qhdm,XT_LNWSDB.dwdm,XT_LNWSDB.qyrq,XT_LNWSDB.ksd");
    return getXtlnwsdxx(strBufHql.toString(), nPageOffset, nPageSize);
  }

  /**
   *增加信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtlnwsdxx(VarVO vvXtlnwsdb) throws ServiceException,
      DAOException {
    ArrayList lstXtlnwsd = new ArrayList();
    PoXT_LNWSDB poXTLNWSDB = new PoXT_LNWSDB();
    PoXT_LNWSDB rtnpoXTLNWSDB = new PoXT_LNWSDB();
    //获取参数信息
    //执行HQL语句
    //wsdid,qhdm,dwdm,ksd,jsd,qyrq,bz;
    try {
      /**
       * 判断行政区划对应的尾数是否已经存在于数据库中
       */
      List lstXtlnwsdxx = null;
      String sKsd = "", sJsd = "";
      PoXT_LNWSDB poXT_LNWSDB = new PoXT_LNWSDB();
      String sParamQhdm = vvXtlnwsdb.getVarValue("qhdm");
      String sParamqyrq = vvXtlnwsdb.getVarValue("qyrq");
      String sParamKsd = vvXtlnwsdb.getVarValue("ksd");
      String sParamJsd = vvXtlnwsdb.getVarValue("jsd");
      if ( (sParamQhdm == null) || ("".equals(sParamQhdm))
          || (sParamKsd == null) || ("".equals(sParamKsd))
          || (sParamJsd == null) || ("".equals(sParamJsd))
          || (sParamqyrq == null) || ("".equals(sParamqyrq))) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL, "数据输入不完整", null);
      }
      /*
            StringBuffer strBufHql = new StringBuffer();
           strBufHql.append("select XT_LNWSDB FROM PoXT_LNWSDB as XT_LNWSDB where ")
                .append(" qhdm ='").append(sParamQhdm).append("'")
                .append(" and qyrq ='").append(sParamqyrq).append("'");
           lstXtlnwsdxx =super.findAllByHQL(strBufHql.toString());
            if (lstXtlnwsdxx != null && lstXtlnwsdxx.size() > 0) { //找到记录
              //判断尾数段是否存在数据库中
              for (int i = 0; i < lstXtlnwsdxx.size(); i++) {
                poXT_LNWSDB = (PoXT_LNWSDB) lstXtlnwsdxx.get(i);
                sKsd = poXT_LNWSDB.getKsd();
                sJsd = poXT_LNWSDB.getJsd();
                //如果参数的开始段和结束段分别在这个范围内则报错
                if ( (sParamKsd.compareTo(sKsd) >= 0 &&
                      sParamKsd.compareTo(sJsd) <= 0)
                    ||
           (sParamJsd.compareTo(sKsd) >= 0 && sParamJsd.compareTo(sJsd) <= 0)) {
                  throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                             "尾数段已经存在", null);
                }
              }
            }
       */
      BeanUtils.populate(poXTLNWSDB, vvXtlnwsdb.getVarmap());
      poXTLNWSDB.setWsdid( (Long) Xt_lnwsdbDao.getId());
      //poXTLNWSDB.setQyrq(StringUtils.formateDate());
      rtnpoXTLNWSDB =(PoXT_LNWSDB)super.create(poXTLNWSDB);
      lstXtlnwsd.add(rtnpoXTLNWSDB);
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
    return lstXtlnwsd;
  }

  /**
   *修改信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtlnwsdxgxx(VarVO vvXtlnwsdb) throws ServiceException,
      DAOException {
    ArrayList lstXtlnwsd = new ArrayList();
    PoXT_LNWSDB poXTLNWSDB = null;
    PoXT_LNWSDB rtnpoXTLNWSDB = new PoXT_LNWSDB();
    //获取参数信息
    //执行HQL语句
    try {
      String sWsdid = vvXtlnwsdb.getVarValue("wsdid");
      poXTLNWSDB  = super.get(PoXT_LNWSDB.class,Long.valueOf(sWsdid));
      if (poXTLNWSDB == null) {
        return null;
      }
      /**
       * 判断尾数是否已经存在
       */
      List lstXtlnwsdxx = null;
      String sKsd = "", sJsd = "";
      PoXT_LNWSDB poXT_LNWSDB = new PoXT_LNWSDB();
      String sParamQhdm = vvXtlnwsdb.getVarValue("qhdm");
      String sParamqyrq = vvXtlnwsdb.getVarValue("qyrq");
      String sParamKsd = vvXtlnwsdb.getVarValue("ksd");
      String sParamJsd = vvXtlnwsdb.getVarValue("jsd");

      if (sParamqyrq == null || "".equals(sParamqyrq)) {
        sParamqyrq = poXTLNWSDB.getQyrq();
      }
      if (sParamKsd == null || "".equals(sParamKsd)) {
        sParamKsd = poXTLNWSDB.getKsd();
      }
      if (sParamJsd == null || "".equals(sParamJsd)) {
        sParamJsd = poXTLNWSDB.getJsd();
      }
      if (sParamQhdm == null || "".equals(sParamQhdm)) {
        sParamQhdm = poXTLNWSDB.getQhdm();

      }
      StringBuffer strBufHql = new StringBuffer();
      strBufHql.append("select XT_LNWSDB FROM PoXT_LNWSDB as XT_LNWSDB where ")
          .append(" qhdm ='").append(sParamQhdm).append("'")
          .append(" and qyrq ='").append(sParamqyrq).append("'")
          .append(" and wsdid <>'").append(sWsdid).append("'");
      lstXtlnwsdxx =super.findAllByHQL(strBufHql.toString());
      if (lstXtlnwsdxx != null && lstXtlnwsdxx.size() > 0) { //找到记录
        //判断尾数段是否存在数据库中
        for (int i = 0; i < lstXtlnwsdxx.size(); i++) {
          poXT_LNWSDB = (PoXT_LNWSDB) lstXtlnwsdxx.get(i);
          sKsd = poXT_LNWSDB.getKsd();
          sJsd = poXT_LNWSDB.getJsd();
          //如果参数的开始段和结束段分别在这个范围内则报错
          if ( (sParamKsd.compareTo(sKsd) >= 0 &&
                sParamKsd.compareTo(sJsd) <= 0)
              ||
              (sParamJsd.compareTo(sKsd) >= 0 && sParamJsd.compareTo(sJsd) <= 0)) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                       "尾数段已经存在", null);
          }
        }

      }

      BeanUtils.populate(poXTLNWSDB, vvXtlnwsdb.getVarmap());
      rtnpoXTLNWSDB =      (PoXT_LNWSDB)super.update(poXTLNWSDB);
      lstXtlnwsd.add(rtnpoXTLNWSDB);
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
    return lstXtlnwsd;

  }

  /**
   *删除信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtlnwsdscxx(VarVO vvXtlnwsdb) throws ServiceException,
      DAOException {
    String sHql = null;
    PoXT_LNWSDB poXTLNWSDB = new PoXT_LNWSDB();
    PoXT_LNWSDB rtnpoXTLNWSDB = new PoXT_LNWSDB();
    PoXT_XTRZB poXTRZB = new PoXT_XTRZB();
    int nReturn = PublicConstant.XT_BZ_ERROR;
    //组合poXT_XTlnwsdB
    try {
      BeanUtils.populate(poXTLNWSDB, vvXtlnwsdb.getVarmap());
      super.delete(poXTLNWSDB);
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
