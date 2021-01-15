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
 *                 乡镇街道操作实现</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */
@Service
public class XtxzjdServiceImpl
    extends XtBaseService

    implements XtxzjdService {

  PojoInfo   Xt_xzjdbDao = DAOFactory.createXT_XZJDXXBDAO();
  PojoInfo   Xt_XtrzbDao = DAOFactory.createXT_XTRZBDAO();
  /**
   *查询乡镇街道信息
   * @param pxQryXtcs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtxzjdxx(String sHqlXtxzjd) throws ServiceException,
      DAOException {
    //执行HQL语句
    return getXtxzjdxx(sHqlXtxzjd, PublicConstant.XT_PAGE,
                       PublicConstant.XT_PAGESIZE);
  }

  public List getXtxzjdxx(String sHqlXtxzjd, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    //执行HQL语句
    List lstXtRtn = null;
    List lstReturn = new ArrayList();
    PoXT_QYSZB poXT_QYSZB = null;
    PoXT_XZQHB poXT_XZQHB = null;
    PoXT_XTCSB poXT_XTCSB = null;
    PoXT_XZJDXXB poXT_XZJDXXB = null;
    Object[] objXtRtn = null;
    //执行HQL语句
    try {
      lstXtRtn =super.getPageRecords(sHqlXtxzjd, new Object[]{}, nPageOffset,
          nPageSize).getList();
      for (int i = 0; i < lstXtRtn.size(); i++) {
        objXtRtn = (Object[]) lstXtRtn.get(i);
        poXT_XZJDXXB = (PoXT_XZJDXXB) objXtRtn[0];
        poXT_XZQHB = (PoXT_XZQHB) objXtRtn[1];
        poXT_XTCSB = (PoXT_XTCSB) objXtRtn[2];
        VoXtxzjdxx voXtxzjdxx = new VoXtxzjdxx(poXT_XZJDXXB, poXT_XZQHB,
                                               poXT_XTCSB);
        lstReturn.add(voXtxzjdxx);
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
   *查询乡镇街道信息
   * @param pxQryXtcs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult getXtxzjdxx(VarVO vvQryXtxzjd,String psWhere,VoPage voPage) throws ServiceException,
      DAOException {
    List lstXtxzjdxx = null;
    VoQueryResult voQueryResult = new VoQueryResult();
    //获取参数信息
    //dm,mc,zwpy,wbpy,qhdm,bz,qybz,bdlx,bdsj;
    String sDm = vvQryXtxzjd.getVarValue("dm");
    String sQhdm = vvQryXtxzjd.getVarValue("qhdm");
    String sBz = vvQryXtxzjd.getVarValue("bz");
    String sQybz = vvQryXtxzjd.getVarValue("qybz");
    String sMc = vvQryXtxzjd.getVarValue("mc");
    String sZwpy = vvQryXtxzjd.getVarValue("zwpy");
    String sWbpy =vvQryXtxzjd.getVarValue("wbpy");
    String sMb = vvQryXtxzjd.getVarValue("mb");
    boolean bCjyh = false;
    //判断用户是否有角色是超级用户
    List lstXtyhjs = null;
    StringBuffer strBufHql1 = new StringBuffer();
    strBufHql1.append(
        " select XT_YHJSXXB from PoXT_YHJSXXB as XT_YHJSXXB,PoXT_JSXXB as XT_JSXXB")
        .append(" where XT_YHJSXXB.jsid =XT_JSXXB.jsid and XT_JSXXB.jsmc = '")
        .append(PublicConstant.XT_CJYHMC).append("'")
        .append(" and XT_YHJSXXB.yhid = ").append(String.valueOf(this.
        getUserInfo().getYhid()));
    try {
      PojoInfo  Xt_yhjsxxbDao = DAOFactory.createXT_YHJSXXBDAO();
      lstXtyhjs =super.findAllByHQL(strBufHql1.toString());
      if (lstXtyhjs != null && !lstXtyhjs.isEmpty()) {
        bCjyh = true;
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

    //HQL条件语句
    StringBuffer strBufHql = new StringBuffer();
    StringBuffer strCountHql = new StringBuffer();
    StringBuffer strHql = new StringBuffer();
    strBufHql.append(
        "select XT_XZJDXXB,XT_XZQHB,XT_XTCSB from PoXT_XZJDXXB as XT_XZJDXXB,");
    strCountHql.append("select count(*) from PoXT_XZJDXXB as XT_XZJDXXB,");
    strHql.append(" PoXT_XZQHB as XT_XZQHB,PoXT_XTCSB as XT_XTCSB ")
        .append(
        " where XT_XZJDXXB.qybz = XT_XTCSB.dm and XT_XTCSB.cslb = '9002'")//  " where XT_XZJDXXB.qybz = XT_XTCSB.dm(+) and XT_XTCSB.cslb(+) = '9002'")
        .append(" and  XT_XZJDXXB.qhdm = XT_XZQHB.dm ");
    if ( (sDm != null) && ! ("".equals(sDm))) {
      strHql.append(" and  XT_XZJDXXB.dm ='").append(sDm).append("'");
    }
    if ( (sMc != null) && ! ("".equals(sMc))) {
      strHql.append(" and  XT_XZJDXXB.mc ='").append(sMc).append("'");
    }
    if ( (sZwpy != null) && ! ("".equals(sZwpy))) {
      strHql.append(" and  XT_XZJDXXB.zwpy ='").append(sZwpy).append("'");
    }
    if ( (sWbpy != null) && ! ("".equals(sWbpy))) {
      strHql.append(" and  XT_XZJDXXB.wbpy ='").append(sWbpy).append("'");
    }
    if ( (sQhdm != null) && ! ("".equals(sQhdm))) {
      strHql.append(" and  XT_XZJDXXB.qhdm ='").append(sQhdm).append("'");
    }
    if ( (sQybz != null) && ! ("".equals(sQybz))) {
      strHql.append(" and  XT_XZJDXXB.qybz ='").append(sQybz).append("'");
    }
    if (!bCjyh && ! "mb".equalsIgnoreCase(sMb)) {
      strHql.append(" and  substr(XT_XZJDXXB.dm,0,6) =substr(").append(this.
          getUserInfo().getDwdm()).append(",0,6)");
    }
    if (psWhere != null && !"".equals(psWhere)) {
     strHql.append(" and (" + psWhere + ")");
   }
   strHql.append(" order by XT_XZJDXXB.qhdm,XT_XZJDXXB.dm");
   //HQL条件语句
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
   voQueryResult.setPagelist(getXtxzjdxx(strBufHql.toString(),
                                             voPage.getPageindex(),
                                             voPage.getPagesize()));
   voQueryResult.setVotype(VoXtxzjdxx.class);
   return voQueryResult;
  }

  /**
   *增加信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtxzjdxx(VarVO vvXtxzjdb) throws ServiceException,
      DAOException {
    ArrayList lstXtxzjd = new ArrayList();
    PoXT_XZJDXXB poXTXZJDB = new PoXT_XZJDXXB();
    PoXT_XZJDXXB rtnpoXTXZJDB = new PoXT_XZJDXXB();
    //获取参数信息
    //dm,mc,zwpy,wbpy,qhdm,bz,qybz,bdlx,bdsj;
    //执行HQL语句
    boolean bCjyh = false;
    //判断用户是否有角色是超级用户
    List lstXtyhjs = null;
    StringBuffer strBufHql1 = new StringBuffer();
    strBufHql1.append(
        " select XT_YHJSXXB from PoXT_YHJSXXB as XT_YHJSXXB,PoXT_JSXXB as XT_JSXXB")
        .append(" where XT_YHJSXXB.jsid =XT_JSXXB.jsid and XT_JSXXB.jsmc = '")
        .append(PublicConstant.XT_CJYHMC).append("'")
        .append(" and XT_YHJSXXB.yhid = ").append(String.valueOf(this.
        getUserInfo().getYhid()));
    try {
      PojoInfo  Xt_yhjsxxbDao = DAOFactory.createXT_YHJSXXBDAO();
      lstXtyhjs =super.findAllByHQL(strBufHql1.toString());
      if (lstXtyhjs != null && !lstXtyhjs.isEmpty()) {
        bCjyh = true;
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

    try {
      String sDm = vvXtxzjdb.getVarValue("dm");
      if (sDm.length() != 9) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                   "乡镇街道代码输入错误(长度不是9)", null);
      }
      if (!bCjyh) {
        if (!sDm.substring(0,
            6).equals(this.getUserInfo().getDwdm().substring(0, 6))) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                     "无权增加非行政区划内乡镇街道！", null);
        }
      }
      //判断单位代码区划代码是否一致

      String sQhdm = vvXtxzjdb.getVarValue("qhdm");
      if (!sDm.substring(0, 6).equals(sQhdm)) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                   "区划代码与乡镇街道代码不一致", null);
      }


      BeanUtils.populate(poXTXZJDB, vvXtxzjdb.getVarmap());
      poXTXZJDB.setQybz(PublicConstant.XT_BZ_QY); //启用标志
      poXTXZJDB.setBdlx(PublicConstant.XT_BDLX_ADD); //变动类型
      poXTXZJDB.setBdsj(StringUtils.getServiceTime());

      rtnpoXTXZJDB =(PoXT_XZJDXXB)super.create(poXTXZJDB);
      lstXtxzjd.add(rtnpoXTXZJDB);
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
    return lstXtxzjd;
  }

  /**
   * 修改信息
   * @param voXtgbcsb
   * @param atUser
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtxzjdxgxx(VarVO vvXtxzjdb) throws ServiceException,
      DAOException {
    ArrayList lstXtxzjd = new ArrayList();
    PoXT_XZJDXXB poXTXZJDB = null;
    PoXT_XZJDXXB rtnpoXTXZJDB = new PoXT_XZJDXXB();
    //组合poXT_XTCSB
    //dm,mc,zwpy,wbpy,qhdm,bz,qybz,bdlx,bdsj;
    //执行HQL语句
    try {
      poXTXZJDB  = super.get(PoXT_XZJDXXB.class,vvXtxzjdb.
          getVarValue("dm"));
      if (poXTXZJDB == null) {
        return null;
      }
      BeanUtils.populate(poXTXZJDB, vvXtxzjdb.getVarmap());
      poXTXZJDB.setBdlx(PublicConstant.XT_BDLX_UPDATE); //变动类型
      poXTXZJDB.setBdsj(StringUtils.getServiceTime());
      rtnpoXTXZJDB =   (PoXT_XZJDXXB)super.update(poXTXZJDB);
      lstXtxzjd.add(rtnpoXTXZJDB);
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
    return lstXtxzjd;
  }

  /**
   * 删除信息
   * @param voXtxzqhb
   * @param atUser
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtxzjdscxx(VarVO vvXtxzjdb) throws ServiceException,
      DAOException {
    PoXT_XZJDXXB poXTXZJDB = null;
    PoXT_XZJDXXB rtnpoXTXZJDB = new PoXT_XZJDXXB();
    ArrayList lstXtxzjd = new ArrayList();
    //执行HQL语句
    try {
      poXTXZJDB  = super.get(PoXT_XZJDXXB.class,vvXtxzjdb.
          getVarValue("dm"));
      if (poXTXZJDB == null) {
        return null;
      }
      poXTXZJDB.setQybz(PublicConstant.XT_BZ_BQY); //启用标志
      poXTXZJDB.setBdlx(PublicConstant.XT_BDLX_DELETE); //变动类型
      poXTXZJDB.setBdsj(StringUtils.getServiceTime());
      rtnpoXTXZJDB =    (PoXT_XZJDXXB)super.update(poXTXZJDB);
      lstXtxzjd.add(rtnpoXTXZJDB);
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_TRANSACTION, ex);
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    return lstXtxzjd;
  }

  /**
   * latest change date
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtxzjdzdbdsj() throws ServiceException,
      DAOException {
    //执行HQL语句
    List lstXtRtn = null;
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append("select XT_XZJDXXB FROM PoXT_XZJDXXB as XT_XZJDXXB where ")
        .append(
        " bdsj in (select max(XT_XZJDXXB.bdsj) FROM PoXT_XZJDXXB as XT_XZJDXXB)");
    try {
      lstXtRtn =super.getPageRecords(strBufHql.toString(),new Object[]{},
          PublicConstant.XT_PAGE, 1).getList();
      if (lstXtRtn == null || lstXtRtn.isEmpty()) {
        String sHql =
            "select XT_XZJDXXB FROM PoXT_XZJDXXB as XT_XZJDXXB ";
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
