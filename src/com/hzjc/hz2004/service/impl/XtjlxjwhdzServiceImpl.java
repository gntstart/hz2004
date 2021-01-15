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
 *                 街路巷居委会对照操作实现</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */
@Service
public class XtjlxjwhdzServiceImpl
    extends XtBaseService

    implements XtjlxjwhdzService {

  PojoInfo   Xt_jlxjwhdzbDao = DAOFactory.
      createXT_JLXJWHDZXXBDAO();
  PojoInfo   Xt_XtrzbDao = DAOFactory.createXT_XTRZBDAO();
  /**
   *查询信息
   * @param pxQryXtcs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtjlxjwhdzxx(String sHqlXtjlxjwhdz) throws ServiceException,
      DAOException {
    return getXtjlxjwhdzxx(sHqlXtjlxjwhdz, PublicConstant.XT_PAGE,
                           PublicConstant.XT_PAGESIZE);
  }

  public List getXtjlxjwhdzxx(String sHqlXtjlxjwhdz, int nPageOffset,
                              int nPageSize) throws ServiceException,
      DAOException {
    //执行HQL语句
    List lstXtRtn = null;
    List lstReturn = new ArrayList();
    PoXT_JLXJWHDZXXB poXTJLXJWHDZB = null;
    PoXT_JLXXXB poXTJLXXXB = null;
    PoXT_JWHXXB poXTJWHXXB = null;
    PoXT_XTCSB poXTCSBA = null;
    PoXT_XTCSB poXTCSBB = null;
    Object[] objXtRtn = null;
    try {
      lstXtRtn =super.getPageRecords(sHqlXtjlxjwhdz,new Object[]{},
          nPageOffset, nPageSize).getList();
      for (int i = 0; i < lstXtRtn.size(); i++) {
        objXtRtn = (Object[]) lstXtRtn.get(i);
        poXTJLXJWHDZB = (PoXT_JLXJWHDZXXB) objXtRtn[0];
        poXTJLXXXB = (PoXT_JLXXXB) objXtRtn[1];
        poXTJWHXXB = (PoXT_JWHXXB) objXtRtn[2];
        poXTCSBA = (PoXT_XTCSB) objXtRtn[3];
        poXTCSBB = (PoXT_XTCSB) objXtRtn[4];
        VoXtjlxjwhdz voXtjlxjwhdz = new VoXtjlxjwhdz(poXTJLXJWHDZB, poXTJLXXXB,
            poXTJWHXXB, poXTCSBA, poXTCSBB);
        lstReturn.add(voXtjlxjwhdz);
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
  public VoQueryResult getXtjlxjwhdzxx(String psWhere, VoPage voPage) throws
      ServiceException,
      DAOException {
    //获取参数信息
    //czid,jlxdm,jwhdm,jlxhlx,jlxh,qybz,bdlx,bdsj;
    boolean bCjyh = false;
    //获取用户居委会信息
    VoQueryResult voQueryResult = new VoQueryResult();
    //modi by hh 20050906 将根据用户居委会取对照信息修改为根据数据范围取对照信息
    //List lstYhjwh = new ArrayList();
    List lstYhsjfw = new ArrayList();
    try {
      bCjyh = VerifyYH(String.valueOf(this.getUserInfo().getYhid()));
      if (!bCjyh) {
        //lstYhjwh = getXtyhjwhxx(String.valueOf(this.getUserInfo().getYhid()));
        lstYhsjfw = getXtYhSjfw(String.valueOf(this.getUserInfo().getYhid()));
      }
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    //HQL条件语句
    StringBuffer strBufHql = new StringBuffer();
    StringBuffer strCountHql = new StringBuffer();
    strBufHql.append(
        "select XT_JLXJWHDZXXB,XT_JLXXXB,XT_JWHXXB,XT_XTCSBA,XT_XTCSBB from ")//FROM  改成from 20181123
        .append(" PoXT_XTCSB as XT_XTCSBA,PoXT_XTCSB as XT_XTCSBB,")
        .append(" PoXT_JLXJWHDZXXB as XT_JLXJWHDZXXB,PoXT_JLXXXB as XT_JLXXXB,")
        .append(
        " PoXT_JWHXXB as XT_JWHXXB where  XT_JLXJWHDZXXB.jlxdm = XT_JLXXXB.dm ")
        .append(
        " and XT_JLXJWHDZXXB.qybz = XT_XTCSBA.dm and XT_XTCSBA.cslb = '9002'")//" and XT_JLXJWHDZXXB.qybz = XT_XTCSBA.dm(+) and XT_XTCSBA.cslb(+) = '9002'")
        .append(
        " and XT_JLXJWHDZXXB.jlxhlx = XT_XTCSBB.dm and XT_XTCSBB.cslb = '1059'")//" and XT_JLXJWHDZXXB.jlxhlx = XT_XTCSBB.dm(+) and XT_XTCSBB.cslb(+) = '1059'")
        .append(" and XT_JLXJWHDZXXB.jwhdm = XT_JWHXXB.dm ");
    strCountHql.append("Select count(*) from ")
        .append(" PoXT_XTCSB as XT_XTCSBA,PoXT_XTCSB as XT_XTCSBB,")
        .append(" PoXT_JLXJWHDZXXB as XT_JLXJWHDZXXB,PoXT_JLXXXB as XT_JLXXXB,")
        .append(
        " PoXT_JWHXXB as XT_JWHXXB where  XT_JLXJWHDZXXB.jlxdm = XT_JLXXXB.dm ")
        .append(
        " and XT_JLXJWHDZXXB.qybz = XT_XTCSBA.dm and XT_XTCSBA.cslb = '9002'")//" and XT_JLXJWHDZXXB.qybz = XT_XTCSBA.dm(+) and XT_XTCSBA.cslb(+) = '9002'")
        .append(
        " and XT_JLXJWHDZXXB.jlxhlx = XT_XTCSBB.dm and XT_XTCSBB.cslb = '1059'")//" and XT_JLXJWHDZXXB.jlxhlx = XT_XTCSBB.dm(+) and XT_XTCSBB.cslb(+) = '1059'")
        .append(" and XT_JLXJWHDZXXB.jwhdm = XT_JWHXXB.dm ");

    if (psWhere != null && !"".equals(psWhere)) {
      strBufHql.append(" and " + psWhere);
      strCountHql.append(" and " + psWhere);
    }
    if (!bCjyh) {
      /*
            if (lstYhjwh != null && !lstYhjwh.isEmpty()) {
             strBufHql.append(" and ( 1=2 ");
             strCountHql.append(" and ( 1=2 ");
             for (int i = 0; i < lstYhjwh.size(); i++) {
               VoXtyhjwh voXtjwh = (VoXtyhjwh) lstYhjwh.get(i);
           strBufHql.append(" or  XT_JLXJWHDZXXB.jwhdm ='").append(voXtjwh.getDm().
                   toString()).append(
                   "'");
           strCountHql.append(" or  XT_JLXJWHDZXXB.jwhdm ='").append(voXtjwh.
                   getDm().
                   toString()).append(
                   "'");
             }
             strBufHql.append(" )");
             strCountHql.append(" )");
           }
           else {
             throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                        "数据范围内无居委会信息！", null);
           }
       */
      //modi by hh 20050906 将根据用户居委会取对照信息修改为根据数据范围取对照信息
      if (lstYhsjfw != null && !lstYhsjfw.isEmpty()) {
        strBufHql.append(" and ( 1=2 ");
        strCountHql.append(" and ( 1=2 ");
        PoXT_YHSJFWB poXtyhsjfwb = null;
        String sSjfw = "", sBz = "", sDm = "";
        List lstXtsjfw = null;
        for (int i = 0; i < lstYhsjfw.size(); i++) {
          poXtyhsjfwb = (PoXT_YHSJFWB) lstYhsjfw.get(i);
          sSjfw = poXtyhsjfwb.getSjfw(); //取得数据范围
          lstXtsjfw = XtywqxServiceImpl.TransDataRange(sSjfw); //解析数据范围
          sBz = lstXtsjfw.get(0).toString();
          sDm = lstXtsjfw.get(1).toString();
          if (sBz.equals(PublicConstant.XT_QX_XZQH)) { //如果是省市县区
            strBufHql.append(" or  substr(XT_JWHXXB.dwdm,0,6) ='").append(
                sDm).append("'");
            strCountHql.append(" or  substr(XT_JWHXXB.dwdm,0,6) ='").append(
                sDm).append("'");
        }
          else if (sBz.equals(PublicConstant.XT_QX_PCS)) { //如果是派出所
            strCountHql.append(" or  XT_JWHXXB.dwdm ='").append(
                sDm).append("'");
            strBufHql.append(" or  XT_JWHXXB.dwdm ='").append(
                sDm).append("'");
         }
          else if (sBz.equals(PublicConstant.XT_QX_JWH)) { //如果是居委会
            strBufHql.append(" or  XT_JLXJWHDZXXB.jwhdm ='").append(sDm).append(
                "'");
            strCountHql.append(" or  XT_JLXJWHDZXXB.jwhdm ='").append(sDm).
                append(
                "'");
        }
        }
        strBufHql.append(" )");
        strCountHql.append(" )");
      }
      else {
        throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                   "数据范围内无居委会信息！", null);
      }

    }
    strBufHql.append(" order by XT_JLXJWHDZXXB.jlxdm,XT_JLXJWHDZXXB.jwhdm");
    strCountHql.append(" order by XT_JLXJWHDZXXB.jlxdm,XT_JLXJWHDZXXB.jwhdm");
    //执行HQL语句
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
    voQueryResult.setPagelist(getXtjlxjwhdzxx(strBufHql.toString(),
                                              voPage.getPageindex(),
                                              voPage.getPagesize()));
    voQueryResult.setVotype(VoXtjlxjwhdz.class);
    return voQueryResult;
  }

  /**
   *增加信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjlxjwhdzxx(VarVO vvtjlxjwhdzb) throws ServiceException,
      DAOException {
    ArrayList lstXtjlxjwhdz = new ArrayList();
    PoXT_JLXJWHDZXXB poXTJLXJWHDZB = new PoXT_JLXJWHDZXXB();
    PoXT_JLXJWHDZXXB rtnpoXTJLXJWHDZB = new PoXT_JLXJWHDZXXB();
    //获取参数信息
    //czid,jlxdm,jwhdm,jlxhlx,jlxh,qybz,bdlx,bdsj;
    try {
      BeanUtils.populate(poXTJLXJWHDZB, vvtjlxjwhdzb.getVarmap());
      poXTJLXJWHDZB.setCzid( (Long) Xt_jlxjwhdzbDao.getId());
      poXTJLXJWHDZB.setQybz(PublicConstant.XT_BZ_QY); //启用标志
      poXTJLXJWHDZB.setBdlx(PublicConstant.XT_BDLX_ADD); //变动类型
      poXTJLXJWHDZB.setBdsj(StringUtils.getServiceTime());
      rtnpoXTJLXJWHDZB =(PoXT_JLXJWHDZXXB)super.create(poXTJLXJWHDZB);
      lstXtjlxjwhdz.add(rtnpoXTJLXJWHDZB);
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
    return lstXtjlxjwhdz;
  }

  /**
   * 修改信息
   * @param voXtgbcsb
   * @param atUser
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjlxjwhdzxgxx(VarVO vvXtjlxjwhdzb) throws ServiceException,
      DAOException {
    ArrayList lstXtjlxjwhdz = new ArrayList();
    String sHql = null;
    PoXT_JLXJWHDZXXB poXTJLXJWHDZB = null;
    PoXT_JLXJWHDZXXB rtnpoXTJLXJWHDZB = new PoXT_JLXJWHDZXXB();
    //czid,jlxdm,jwhdm,jlxhlx,jlxh,qybz,bdlx,bdsj;
    try {
      poXTJLXJWHDZB  = super.get(PoXT_JLXJWHDZXXB.class,Long.valueOf(
          vvXtjlxjwhdzb.getVarValue("czid")));
      if (poXTJLXJWHDZB == null) {
        return null;
      }
      BeanUtils.populate(poXTJLXJWHDZB, vvXtjlxjwhdzb.getVarmap());
      poXTJLXJWHDZB.setJlxh(vvXtjlxjwhdzb.getVarValue("jlxh"));
      poXTJLXJWHDZB.setBdlx(PublicConstant.XT_BDLX_UPDATE); //变动类型
      poXTJLXJWHDZB.setBdsj(StringUtils.getServiceTime());
      rtnpoXTJLXJWHDZB =      (PoXT_JLXJWHDZXXB)super.update(poXTJLXJWHDZB);
      lstXtjlxjwhdz.add(rtnpoXTJLXJWHDZB);
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
    return lstXtjlxjwhdz;
  }

  /**
   * 删除信息
   * @param voXtxzqhb
   * @param atUser
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjlxjwhdzscxx(VarVO vvXtjlxjwhdzb) throws ServiceException,
      DAOException {
    String sHql = null;
    PoXT_JLXJWHDZXXB poXTJLXJWHDZB = null;
    PoXT_JLXJWHDZXXB rtnpoXTJLXJWHDZB = new PoXT_JLXJWHDZXXB();
    ArrayList lstXtjlxjwhdz = new ArrayList();
    try {
      poXTJLXJWHDZB  = super.get(PoXT_JLXJWHDZXXB.class,Long.valueOf(
          vvXtjlxjwhdzb.getVarValue("czid")));
      if (poXTJLXJWHDZB == null) {
        return null;
      }
      poXTJLXJWHDZB.setQybz(PublicConstant.XT_BZ_BQY); //启用标志
      poXTJLXJWHDZB.setBdlx(PublicConstant.XT_BDLX_DELETE); //变动类型
      poXTJLXJWHDZB.setBdsj(StringUtils.getServiceTime());
      rtnpoXTJLXJWHDZB =     (PoXT_JLXJWHDZXXB)super.update(poXTJLXJWHDZB);
      lstXtjlxjwhdz.add(rtnpoXTJLXJWHDZB);
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
    return lstXtjlxjwhdz;
  }

  /**
   * latest change date
   * @param pxQryXtcs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtjlxjwhdzzdbdsj() throws ServiceException, DAOException {
    //执行HQL语句
    List lstXtRtn = null;
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        "select XT_JLXJWHDZXXB FROM PoXT_JLXJWHDZXXB as XT_JLXJWHDZXXB where ")
        .append(" bdsj in (select max(XT_JLXJWHDZXXB.bdsj) FROM PoXT_JLXJWHDZXXB as XT_JLXJWHDZXXB )");
    try {
      lstXtRtn =super.getPageRecords(strBufHql.toString(),new Object[]{},
          PublicConstant.XT_PAGE, 1).getList();
      if (lstXtRtn == null || lstXtRtn.isEmpty()) {
        String sHql =
            "select XT_JLXJWHDZXXB FROM PoXT_JLXJWHDZXXB as XT_JLXJWHDZXXB";
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

  /**
   * 查询用户居委会信息
   * @param lstQryXtyh
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtyhjwhxx(String sYhid) throws ServiceException,
      DAOException {
    List lstXtyhjwhxx = new ArrayList();
    List lstXtyhsjfw = null;
    List lstXtsjfw = null;
    PoXT_YHSJFWB poXtyhsjfwb = new PoXT_YHSJFWB();
    String sXqlx = "", sSjfw = "";
    String sBz = "", sDm = "";

    //判断用户是否有角色是超级用户
    List lstXtyhjs = null;
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        " select XT_YHJSXXB from PoXT_YHJSXXB as XT_YHJSXXB,PoXT_JSXXB as XT_JSXXB")
        .append(" where XT_YHJSXXB.jsid =XT_JSXXB.jsid and XT_JSXXB.jsmc = '")
        .append(PublicConstant.XT_CJYHMC).append("'")
        .append(" and XT_YHJSXXB.yhid = ").append(sYhid);
    try {
      PojoInfo  Xt_yhjsxxbDao = DAOFactory.createXT_YHJSXXBDAO();
      lstXtyhjs =super.findAllByHQL(strBufHql.toString());
      if (lstXtyhjs != null && !lstXtyhjs.isEmpty()) {
        poXtyhsjfwb.setYhid(Long.valueOf(sYhid));
        poXtyhsjfwb.setXqlx("1");
        AddJwhtoList(poXtyhsjfwb, getJwhFromDm(sDm, true), lstXtyhjwhxx);
        return lstXtyhjwhxx;
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

    //获取用户数据范围 XtywqxServiceImpl
    try {
      lstXtyhsjfw = getXtYhSjfw(sYhid);
      if (lstXtyhsjfw != null && lstXtyhsjfw.size() > 0) { //找到记录
        for (int i = 0; i < lstXtyhsjfw.size(); i++) {
          poXtyhsjfwb = (PoXT_YHSJFWB) lstXtyhsjfw.get(i);
          sSjfw = poXtyhsjfwb.getSjfw(); //取得数据范围
          lstXtsjfw = XtywqxServiceImpl.TransDataRange(sSjfw); //解析数据范围
          sBz = lstXtsjfw.get(0).toString();
          sDm = lstXtsjfw.get(1).toString();
          if ( (sYhid != null) && !"".equals(sYhid)) {
            poXtyhsjfwb.setYhid(Long.valueOf(sYhid));
            //根据代码取得居委会信息
          }
          AddJwhtoList(poXtyhsjfwb, getJwhFromDm(sDm, false), lstXtyhjwhxx);
        } //for
      } //if
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
    return lstXtyhjwhxx;
  }

  public List getXtYhSjfw(String sYhid) throws ServiceException,
      DAOException {
    List lstXtyhsjfw = null;
    PojoInfo  Xt_yhsjfwbDao = DAOFactory.createXT_YHSJFWBDAO(); //用户数据范围信息

    //从用户数据范围表中取出数据范围
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        "select XT_YHSJFWB from PoXT_YHSJFWB as XT_YHSJFWB where 1=1 ");
    strBufHql.append(" and  XT_YHSJFWB.xqlx ='").append(PublicConstant.
        XQLX_XQN).append("'");
    if ( (sYhid != null) && ! ("".equals(sYhid))) {
      strBufHql.append(" and  XT_YHSJFWB.yhid =").append(sYhid);
    }
    strBufHql.append(" order by XT_YHSJFWB.yhid");
    try {
      lstXtyhsjfw =super.findAllByHQL(strBufHql.toString());
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
    return lstXtyhsjfw;
  }

  public List getJwhFromDm(String sDm, boolean bCjyh) {
    List lstJwh = new ArrayList();
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        " select XT_JWHXXB from PoXT_JWHXXB as XT_JWHXXB where qybz='")
        .append(PublicConstant.QYBZ_QY).append("'");
    //如果代码是居委会代码
    if (sDm.length() == 12 && !bCjyh) {
      strBufHql.append(" and dm = '").append(sDm).append("'");
    }
    else
    if (sDm.length() == 9 && !bCjyh) { //如果是pcs
      strBufHql.append(" and dwdm ='").append(sDm).append("'");
    }
    else
    if (sDm.length() == 6 && !bCjyh) {
      strBufHql.append(" and substr(dwdm,0,6)='").append(sDm).append("'");
    }
    strBufHql.append(" order by dm");
    try {
      PojoInfo  Xt_jwhxxbDao = DAOFactory.createXT_JWHXXBDAO();
      lstJwh =super.findAllByHQL(strBufHql.toString());
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
    return lstJwh;
  }

  public void AddJwhtoList(PoXT_YHSJFWB poXT_YHSJFWB, List lstJwh,
                           List lstXtRtn) {
    PoXT_JWHXXB poXtjwhxxb;
    String sXqlx = poXT_YHSJFWB.getXqlx();
    String sYhid = String.valueOf(poXT_YHSJFWB.getYhid());
    VoXtyhjwh voXtjwh;
    boolean bFlag;
    if (lstJwh != null && lstJwh.size() > 0) { //找到记录
      for (int i = 0; i < lstJwh.size(); i++) {
        poXtjwhxxb = (PoXT_JWHXXB) lstJwh.get(i);
        VoXtyhjwh voXtyhjwh = new VoXtyhjwh(sYhid, sXqlx, poXtjwhxxb);
        bFlag = false;
        for (int j = 0; j < lstXtRtn.size(); j++) {
          voXtjwh = (VoXtyhjwh) lstXtRtn.get(j);
          if (sYhid.equals(voXtjwh.getYhid())
              && sXqlx.equals(voXtjwh.getXqlx())
              && poXtjwhxxb.getDm().equals(voXtjwh.getDm())) {
            bFlag = true;
            break;
          }
        }
        if (!bFlag) {
          lstXtRtn.add(voXtyhjwh);
        }
      } //for
    } //if
  }

}
