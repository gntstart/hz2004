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
 *                 警务责任区操作实现</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */
@Service
public class XtjwzrqServiceImpl
    extends XtBaseService

    implements XtjwzrqService {

  PojoInfo   Xt_jwzrqbDao = DAOFactory.createXT_JWZRQXXBDAO();
  PojoInfo   Xt_XtrzbDao = DAOFactory.createXT_XTRZBDAO();
  /**
   *查询信息
   * @param pxQryXtcs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtjwzrqxx(String sHqlXtjwzrq) throws ServiceException,
      DAOException {
    //执行HQL语句
    return getXtjwzrqxx(sHqlXtjwzrq, PublicConstant.XT_PAGE,
                        PublicConstant.XT_PAGESIZE);
  }

  public List getXtjwzrqxx(String sHqlXtjwzrq, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    //执行HQL语句
    List lstXtRtn = null;
    List lstReturn = new ArrayList();
    PoXT_QYSZB poXT_QYSZB = null;
    PoXT_JWZRQXXB poXT_JWZRQB = null;
    PoXT_DWXXB poXT_DWXXB = null;
    PoXT_XTCSB poXT_XTCSB = null;
    Object[] objXtRtn = null;
    //执行HQL语句
    try {
      lstXtRtn =super.getPageRecords(sHqlXtjwzrq,new Object[]{}, nPageOffset,
          nPageSize).getList();
      for (int i = 0; i < lstXtRtn.size(); i++) {
        objXtRtn = (Object[]) lstXtRtn.get(i);
        poXT_JWZRQB = (PoXT_JWZRQXXB) objXtRtn[0];
        poXT_DWXXB = (PoXT_DWXXB) objXtRtn[1];
        poXT_XTCSB = (PoXT_XTCSB) objXtRtn[2];
        VoXtjwzrqxx voXtjwzrqxx = new VoXtjwzrqxx(poXT_JWZRQB, poXT_DWXXB,
                                                  poXT_XTCSB);
        lstReturn.add(voXtjwzrqxx);
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
   * @param pxQryXtcs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult getXtjwzrqxx(VarVO vvQryXtjwzrq, String psWhere,
                                    VoPage voPage) throws ServiceException,
      DAOException {
    List lstXtjwzrqxx = null;
    VoQueryResult voQueryResult = new VoQueryResult();
    //获取参数信息
    //dm,mc,zwpy,wbpy,dwdm,bz,qybz,bdlx,bdsj;
    String sDm = vvQryXtjwzrq.getVarValue("dm");
    String sDwdm = vvQryXtjwzrq.getVarValue("dwdm");
    String sBz = vvQryXtjwzrq.getVarValue("bz");
    String sQybz = vvQryXtjwzrq.getVarValue("qybz");
    String sMc = vvQryXtjwzrq.getVarValue("mc");
    String sZwpy = vvQryXtjwzrq.getVarValue("zwpy");
    String sWbpy = vvQryXtjwzrq.getVarValue("wbpy");
    //HQL条件语句
    StringBuffer strBufHql = new StringBuffer();
    StringBuffer strCountHql = new StringBuffer();
    StringBuffer strHql = new StringBuffer();
    strBufHql.append(
        "select XT_JWZRQXXB,XT_DWXXB,XT_XTCSB FROM PoXT_JWZRQXXB as XT_JWZRQXXB,");
    strCountHql.append(" select count(*) from PoXT_JWZRQXXB as XT_JWZRQXXB,");
    strHql.append(" PoXT_DWXXB as XT_DWXXB,PoXT_XTCSB as XT_XTCSB ")
        .append(
        " where XT_JWZRQXXB.qybz = XT_XTCSB.dm(+) and XT_XTCSB.cslb(+) = '9002'")
        .append(" and XT_DWXXB.dm = XT_JWZRQXXB.dwdm ");
    if ( (sDm != null) && ! ("".equals(sDm))) {
      strHql.append(" and  XT_JWZRQXXB.dm ='").append(sDm).append("'");
    }
    if ( (sMc != null) && ! ("".equals(sMc))) {
      strHql.append(" and  XT_JWZRQXXB.mc ='").append(sMc).append("'");
    }
    if ( (sZwpy != null) && ! ("".equals(sZwpy))) {
      strHql.append(" and  XT_JWZRQXXB.zwpy ='").append(sZwpy).append("'");
    }
    if ( (sWbpy != null) && ! ("".equals(sWbpy))) {
      strHql.append(" and  XT_JWZRQXXB.wbpy ='").append(sWbpy).append("'");
    }
    if ( (sDwdm != null) && ! ("".equals(sDwdm))) {
      strHql.append(" and  XT_JWZRQXXB.dwdm ='").append(sDwdm).append("'");
    }
    if ( (sQybz != null) && ! ("".equals(sQybz))) {
      strHql.append(" and  XT_JWZRQXXB.qybz ='").append(sQybz).append("'");
    }
    if (psWhere != null && !"".equals(psWhere)) {
     strHql.append(" and (" + psWhere + ")");
   }
   strHql.append(" order by XT_JWZRQXXB.dwdm,XT_JWZRQXXB.dm");
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
   voQueryResult.setPagelist(getXtjwzrqxx(strBufHql.toString(),
                                             voPage.getPageindex(),
                                             voPage.getPagesize()));
   voQueryResult.setVotype(VoXtjwzrqxx.class);
   return voQueryResult;
   }

  /**
   *增加信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjwzrqxx(VarVO vvXtjwzrqb) throws ServiceException,
      DAOException {
    ArrayList lstXtjwzrq = new ArrayList();
    PoXT_JWZRQXXB poXTJWZRQB = new PoXT_JWZRQXXB();
    PoXT_JWZRQXXB rtnpoXTJWZRQB = new PoXT_JWZRQXXB();
    List lstXtjwzrqb;
    String sDm = "", sDwdm = "";
    String sHql = "";
    //获取参数信息
    //dm,mc,zwpy,wbpy,dwdm,bz,qybz,bdlx,bdsj;
    try {
      //判断警务责任区代码是否11位
      sDm = vvXtjwzrqb.getVarValue("dm");
      if (sDm.length() != 12) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                   "警务责任区代码输入错误(不等于12)", null);
      }
      //判断警务责任区代码区划代码是否一致
      sDwdm = vvXtjwzrqb.getVarValue("dwdm");
      if (!sDm.substring(0, 9).equals(sDwdm)) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                   "警务责任区代码输入错误(和单位代码不一致)", null);
      }
      //单位代码是否存在单位代码表中
      sHql = "select XT_DWXXB FROM PoXT_DWXXB as XT_DWXXB where dm='" + sDwdm +
          "'";
      PojoInfo  Xt_dwxxbDao = DAOFactory.createXT_DWXXBDAO();
      lstXtjwzrqb =super.findAllByHQL(sHql);
      if ( (lstXtjwzrqb == null) || (lstXtjwzrqb.isEmpty())) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                   "单位代码输入错误(无此单位代码)", null);
      }

      BeanUtils.populate(poXTJWZRQB, vvXtjwzrqb.getVarmap());
      poXTJWZRQB.setQybz(PublicConstant.XT_BZ_QY); //启用标志
      poXTJWZRQB.setBdlx(PublicConstant.XT_BDLX_ADD); //变动类型
      poXTJWZRQB.setBdsj(StringUtils.getServiceTime());

      rtnpoXTJWZRQB =(PoXT_JWZRQXXB)super.create(poXTJWZRQB);
      lstXtjwzrq.add(rtnpoXTJWZRQB);
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
    return lstXtjwzrq;
  }

  /**
   * 修改信息
   * @param voXtgbcsb
   * @param atUser
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjwzrqxgxx(VarVO vvXtjwzrqb) throws ServiceException,
      DAOException {
    ArrayList lstXtjwzrq = new ArrayList();
    PoXT_JWZRQXXB poXTJWZRQB = null;
    PoXT_JWZRQXXB rtnpoXTJWZRQB = new PoXT_JWZRQXXB();
    //组合poXT_XTCSB
    //dm,mc,zwpy,wbpy,dwdm,bz,qybz,bdlx,bdsj;
    try {
      poXTJWZRQB  = super.get(PoXT_JWZRQXXB.class,vvXtjwzrqb.
          getVarValue("dm"));
      if (poXTJWZRQB == null) {
        return null;
      }
      BeanUtils.populate(poXTJWZRQB, vvXtjwzrqb.getVarmap());
      poXTJWZRQB.setBdlx(PublicConstant.XT_BDLX_UPDATE); //变动类型
      poXTJWZRQB.setBdsj(StringUtils.getServiceTime());
      rtnpoXTJWZRQB =      (PoXT_JWZRQXXB)super.update(poXTJWZRQB);
      lstXtjwzrq.add(rtnpoXTJWZRQB);
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
    return lstXtjwzrq;
  }

  /**
   * 删除信息
   * @param voXtxzqhb
   * @param atUser
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjwzrqscxx(VarVO vvXtjwzrqb) throws ServiceException,
      DAOException {
    String sHql = null;
    PoXT_JWZRQXXB poXTJWZRQB = null;
    PoXT_JWZRQXXB rtnpoXTJWZRQB = new PoXT_JWZRQXXB();
    ArrayList lstXtjwzrq = new ArrayList();
    //组合poXT_XTCSB
    try {
      poXTJWZRQB  = super.get(PoXT_JWZRQXXB.class,vvXtjwzrqb.
          getVarValue("dm"));
      if (poXTJWZRQB == null) {
        return null;
      }
      poXTJWZRQB.setQybz(PublicConstant.XT_BZ_BQY); //启用标志
      poXTJWZRQB.setBdlx(PublicConstant.XT_BDLX_DELETE); //变动类型
      poXTJWZRQB.setBdsj(StringUtils.getServiceTime());
      rtnpoXTJWZRQB =   (PoXT_JWZRQXXB)super.update(poXTJWZRQB);
      lstXtjwzrq.add(poXTJWZRQB);
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
    return lstXtjwzrq;
  }

  /**
   * latest change date
   * @param pxQryXtcs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtjwzrqzdbdsj() throws ServiceException,
      DAOException {
    //执行HQL语句
    List lstXtRtn = null;
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        "select XT_JWZRQXXB FROM PoXT_JWZRQXXB as XT_JWZRQXXB where ")
        .append(
        " bdsj in (select max(XT_JWZRQXXB.bdsj) FROM PoXT_JWZRQXXB as XT_JWZRQXXB )");
    try {
      lstXtRtn =super.getPageRecords(strBufHql.toString(),new Object[]{},
          PublicConstant.XT_PAGE, 1).getList();
      if (lstXtRtn == null || lstXtRtn.isEmpty()) {
        String sHql =
            "select XT_JWZRQXXB FROM PoXT_JWZRQXXB as XT_JWZRQXXB ";
        lstXtRtn =super.getPageRecords(sHql,new Object[]{},
            PublicConstant.XT_PAGE, 1).getList();
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
