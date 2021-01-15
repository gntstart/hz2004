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
import com.hzjc.hz2004.service.impl.XtywqxServiceImpl;
import java.text.*;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 用户信息操作实现</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */
@Service
public class XtyhServiceImpl
    extends XtBaseService
    implements XtyhService {

  PojoInfo   Xt_yhxxbDao = DAOFactory.createXT_YHXXBDAO(); //用户信息
  PojoInfo   Xt_yhdtqxbDao = DAOFactory.createXT_YHDTQXBDAO(); //用户等同权限信息
  PojoInfo   Xt_yhdzqxbDao = DAOFactory.createXT_YHDZQXBDAO(); //用户动作权限信息
  PojoInfo   Xt_yhsjfwbDao = DAOFactory.createXT_YHSJFWBDAO(); //用户数据范围信息
  PojoInfo   Xt_yhjsxxbDao = DAOFactory.createXT_YHJSXXBDAO(); //用户角色信息
  PojoInfo   Xt_XtrzbDao = DAOFactory.createXT_XTRZBDAO();

  public List getXtdlyhxx() throws ServiceException, DAOException {
    PoXT_YHXXB poXtyhxxb = new PoXT_YHXXB();
    List lstXtRtn = new ArrayList();
    try {
      BeanUtils.copyProperties(poXtyhxxb, this.getUserInfo());
      lstXtRtn.add(poXtyhxxb);
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    return lstXtRtn;
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
  public List getXtyhxx(String strHQL) throws ServiceException,
      DAOException {
    //执行HQL语句
    return getXtyhxx(strHQL, PublicConstant.XT_PAGE,
                     PublicConstant.XT_PAGESIZE);
  }

  public List getXtyhxx(String strHQL, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    //执行HQL语句
    List lstXtRtn = null;
    List lstReturn = new ArrayList();
    PoXT_YHXXB poXT_YHXXB = null;
    PoXT_DWXXB poXT_DWXXB = null;
    PoXT_XTCSB poXT_XTCSBA = null;
    PoXT_XTCSB poXT_XTCSBB = null;
    PoXT_XTCSB poXT_XTCSBC = null;
    PoXT_XTCSB poXT_XTCSBD = null;
    Object[] objXtRtn = null;

    try {
      lstXtRtn =super.getPageRecords(strHQL,new Object[]{}, nPageOffset,
                                                  nPageSize).getList();
      for (int i = 0; i < lstXtRtn.size(); i++) {
        objXtRtn = (Object[]) lstXtRtn.get(i);
        poXT_YHXXB = (PoXT_YHXXB) objXtRtn[0];
        if(poXT_YHXXB.getYhdlm().startsWith("ST_"))
          continue;

        poXT_DWXXB = (PoXT_DWXXB) objXtRtn[1];
        poXT_XTCSBA = (PoXT_XTCSB) objXtRtn[2];
        poXT_XTCSBB = (PoXT_XTCSB) objXtRtn[3];
        poXT_XTCSBC = (PoXT_XTCSB) objXtRtn[4];
        poXT_XTCSBD = (PoXT_XTCSB) objXtRtn[5];
        VoXtyhxx voXtyhxx = new VoXtyhxx(poXT_YHXXB, poXT_DWXXB, poXT_XTCSBA,
                                         poXT_XTCSBB, poXT_XTCSBC, poXT_XTCSBD);
        lstReturn.add(voXtyhxx);
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
   *查询用户信息信息
   * @param iReqPage
   * @param iPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult getXtyhxx(VarVO vvQryXtyh,String psWhere,VoPage voPage) throws ServiceException,
      DAOException {
    //获取参数信息
    //获取参数信息
    //yhid,yhdlm,jyh,dwdm,yhxm,yhxb,yhzw,dlkl,yhmj,czmj,yhzt;
    String sYhid = vvQryXtyh.getVarValue("yhid");
    String sYhzt = vvQryXtyh.getVarValue("yhzt");

    VoQueryResult voQueryResult = new VoQueryResult();
    //HQL条件语句
    StringBuffer strBufHql = new StringBuffer();
    StringBuffer strCountHql = new StringBuffer();
    StringBuffer strHql = new StringBuffer();
    strBufHql.append("select XT_YHXXB,XT_DWXXB,XT_XTCSBA,XT_XTCSBB,XT_XTCSBC,XT_XTCSBD FROM PoXT_YHXXB as XT_YHXXB,");
    strCountHql.append(" select count(*) from PoXT_YHXXB as XT_YHXXB,");
    strHql.append(
        " PoXT_DWXXB as XT_DWXXB,PoXT_XTCSB as XT_XTCSBA,PoXT_XTCSB as XT_XTCSBB,")
        .append(" PoXT_XTCSB as XT_XTCSBC,PoXT_XTCSB as XT_XTCSBD")
        .append(" where XT_YHXXB.dwdm(+) = XT_DWXXB.dm")
        .append(
        " and XT_YHXXB.yhxb = XT_XTCSBA.dm(+) and XT_XTCSBA.cslb(+) = '8003'")
        .append(
        " and XT_YHXXB.yhzw = XT_XTCSBB.dm(+) and XT_XTCSBB.cslb(+) = '9009'")
        .append(
        " and XT_YHXXB.yhmj = XT_XTCSBC.dm(+) and XT_XTCSBC.cslb(+) = '9010'")
        .append(
        " and XT_YHXXB.yhzt = XT_XTCSBD.dm(+) and XT_XTCSBD.cslb(+) = '9007'");
    if ( (sYhid != null) && ! ("".equals(sYhid))) {
      strHql.append(" and  XT_YHXXB.yhid =").append(sYhid);
    }
    if ( (sYhzt == null) || ("".equals(sYhzt))) {
      strHql.append(" and  XT_YHXXB.yhzt <>'2'");
    }
    else {
      strHql.append(" and  XT_YHXXB.yhzt ='").append(sYhzt).append("'");

    }
    strHql.append(" order by XT_YHXXB.yhdlm");
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
    voQueryResult.setPagelist(getXtyhxx(strBufHql.toString(),
                                              voPage.getPageindex(),
                                              voPage.getPagesize()));
    voQueryResult.setVotype(PoXT_XTCSB.class);
    return voQueryResult;
 }

  /**
   * 查询用户码表信息
   * @param iReqPage
   * @param iPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtyhmbxx(VarVO vvQryXtyh) throws ServiceException,
      DAOException {
    //获取参数信息
    //yhid,yhdlm,jyh,dwdm,yhxm,yhxb,yhzw,dlkl,yhmj,czmj,yhzt;

    int nPageOffset = PublicConstant.XT_PAGE; //分页
    int nPageSize = PublicConstant.XT_PAGESIZE;
    //HQL条件语句
    StringBuffer strBufHql = new StringBuffer();
    //modi by hh 20050830 码表下载所有用户
 /*
   strBufHql.append(
        "select XT_YHXXB FROM PoV_XT_YHXXB as XT_YHXXB where  XT_YHXXB.yhzt <>'2'")
        .append(" order by XT_YHXXB.mc ");
*/
   strBufHql.append(
     "select XT_YHXXB FROM PoV_XT_YHXXB as XT_YHXXB ")
     .append(" order by XT_YHXXB.mc ");

    List lstXtRtn = null;
    //从数据库中取得数据
    try {
      lstXtRtn =super.findAllByHQL(strBufHql.toString());
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
   *增加用户信息信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtyhxx(VarVO vvXtyhb) throws ServiceException, DAOException {
    ArrayList lstXtyh = new ArrayList();
    PoXT_YHXXB poXTYHXXB = new PoXT_YHXXB();
    PoXT_YHXXB rtnpoXTYHXXB = new PoXT_YHXXB();
    try {
      if (!this.VerifyYH(String.valueOf(this.getUserInfo().getYhid()))) {
        String sYhDwdm = vvXtyhb.getVarValue("dwdm");
        if (!sYhDwdm.substring(0,
                               6).equals(this.getUserInfo().getDwdm().substring(
            0, 6))) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "无增加非本区县用户的权限", null);
        }
      }
      //yhid,yhdlm,jyh,dwdm,yhxm,yhxb,yhzw,dlkl,yhmj,czmj,yhzt;
      //将用户的登陆口令加密
      //poXTYHXXB.setDlkl(DigestUtils.md5Base64(vvXtyhb.getVarValue("dlkl")));
      BeanUtils.populate(poXTYHXXB, vvXtyhb.getVarmap());
      poXTYHXXB.setYhzt(PublicConstant.YHZT_ZC);
      poXTYHXXB.setYhid( (Long) Xt_yhxxbDao.getId());
      //执行HQL语句
      rtnpoXTYHXXB =(PoXT_YHXXB)super.create(poXTYHXXB);
      lstXtyh.add(rtnpoXTYHXXB);
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
    return lstXtyh;
  }

  /**
   *修改用户信息信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtyhxgxx(VarVO vvXtyhb) throws ServiceException, DAOException {
    ArrayList lstXtyh = new ArrayList();
    PoXT_YHXXB poXTYHXXB = new PoXT_YHXXB();
    PoXT_YHXXB rtnpoXTYHXXB = new PoXT_YHXXB();
    boolean bCjyh = false;
    //获取参数信息,执行HQL语句
    //yhid,yhdlm,jyh,dwdm,yhxm,yhxb,yhzw,dlkl,yhmj,czmj,yhzt;
    try {
      bCjyh = this.VerifyYH(String.valueOf(this.getUserInfo().getYhid()));
      poXTYHXXB  = super.get(PoXT_YHXXB.class,Long.valueOf(vvXtyhb.getVarValue(
          "yhid")));
      if (poXTYHXXB == null) {
        return null;
      }
      if (!bCjyh && !poXTYHXXB.getDwdm().substring(0,
          6).equals(this.getUserInfo().getDwdm()
                    .substring(0, 6))) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                   "无修改非区县用户的权限", null);
      }

      BeanUtils.populate(poXTYHXXB, vvXtyhb.getVarmap());
      rtnpoXTYHXXB =    (PoXT_YHXXB)super.update(poXTYHXXB);
      lstXtyh.add(rtnpoXTYHXXB);
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
    return lstXtyh;

  }

  /**
   *删除用户信息信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtyhscxx(VarVO vvXtyhb) throws ServiceException, DAOException {
    PoXT_YHXXB poXTYHXXB = null;
    PoXT_YHXXB rtnpoXTYHXXB = new PoXT_YHXXB();
    int nReturn = PublicConstant.XT_BZ_ERROR;
    List lstXtRtn = new ArrayList();
    List lstXtyh = new ArrayList();
    boolean bCjyh = false;
    //执行HQL语句
    try {
      bCjyh = this.VerifyYH(String.valueOf(this.getUserInfo().getYhid()));

      StringBuffer sbHql = new StringBuffer();
      sbHql.append(
          " select XT_YHXXB FROM PoXT_YHXXB as XT_YHXXB where yhdlm = '")
          .append(PublicConstant.XT_CJYHDLM + "'")
          .append(" and yhid = " + vvXtyhb.getVarValue("yhid"));
      lstXtyh =super.findAllByHQL(sbHql.toString());
      if (lstXtyh != null && !lstXtyh.isEmpty()) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                   "超级用户不能删除", null);
      }
      poXTYHXXB  = super.get(PoXT_YHXXB.class,Long.valueOf(vvXtyhb.getVarValue(
          "yhid")));
      if (poXTYHXXB == null) {
        return null;
      }

      if (!bCjyh && !poXTYHXXB.getDwdm().substring(0,
          6).equals(this.getUserInfo().getDwdm()
                    .substring(0, 6))) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                   "无删除非区县用户的权限", null);
      }

      BeanUtils.populate(poXTYHXXB, vvXtyhb.getVarmap());
      poXTYHXXB.setYhzt(PublicConstant.YHZT_ZX);
      rtnpoXTYHXXB =    (PoXT_YHXXB)super.update(poXTYHXXB);
      lstXtRtn.add(rtnpoXTYHXXB);
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

  /**
   * 查询用户等同权限
   * @param strHQL
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtyhdtqxxx(String strHQL) throws ServiceException,
      DAOException {
    //执行HQL语句
    return getXtyhdtqxxx(strHQL, PublicConstant.PAGE_DEFAULT_OFFSET,
                         PublicConstant.PAGE_DEFAULT_SIZE);
  }

  public List getXtyhdtqxxx(String strHQL, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    List lstXtyhdtqxxx = null;
    List lstReturn = new ArrayList();
    PoXT_YHDTQXB poXT_YHDTQXB = null;
    PoXT_YHXXB poXT_YHXXB = null;
    PoXT_YHXXB poXT_DTYHXXB = null;
    Object[] objXtdtqx = null;
    // PoXT_SPMBXXB poXT_SPMBXXB = new PoXT_SPMBXXB();
    //执行HQL语句
    try {
      lstXtyhdtqxxx =super.getPageRecords(strHQL, new Object[]{}, nPageOffset,
          nPageSize).getList();
      if (lstXtyhdtqxxx != null) {
        for (int i = 0; i < lstXtyhdtqxxx.size(); i++) {
          objXtdtqx = (Object[]) lstXtyhdtqxxx.get(i);
          poXT_YHDTQXB = (PoXT_YHDTQXB) objXtdtqx[0];
          poXT_YHXXB = (PoXT_YHXXB) objXtdtqx[1];
          poXT_DTYHXXB = (PoXT_YHXXB) objXtdtqx[2];
          VoXtyhdtqx voXtyhdtqx = new VoXtyhdtqx(poXT_YHDTQXB, poXT_YHXXB,
                                                 poXT_DTYHXXB);
          lstReturn.add(voXtyhdtqx);
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
    return lstReturn;
  }

  /**
   * 查询用户等同权限信息
   * @param lstQryXtyh
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtyhdtqxxx(VarVO vvQryXtyh) throws ServiceException,
      DAOException {
    //获取参数信息
    //获取参数信息
    //dtqxid,yhid,dtyhid
    String sDtqxid = vvQryXtyh.getVarValue("dtyhid");
    String sYhid = vvQryXtyh.getVarValue("yhid");
    int nPageOffset = PublicConstant.XT_PAGE; //分页
    int nPageSize = PublicConstant.XT_PAGESIZE;
    //HQL条件语句
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        "select XT_YHDTQXB,XT_YHXXBa,XT_YHXXBb FROM PoXT_YHDTQXB as XT_YHDTQXB,")
        .append(" PoXT_YHXXB as XT_YHXXBa,PoXT_YHXXB as XT_YHXXBb ")
        .append(" where XT_YHXXBa.yhid = XT_YHDTQXB.yhid ")
        .append(" and XT_YHXXBb.yhid = XT_YHDTQXB.dtyhid ");
    if ( (sDtqxid != null) && ! ("".equals(sDtqxid))) {
      strBufHql.append(" and  XT_YHDTQXB.dtqxid =").append(sDtqxid);
    }
    if ( (sYhid != null) && ! ("".equals(sYhid))) {
      strBufHql.append(" and  XT_YHDTQXB.yhid =").append(sYhid);
    }
    strBufHql.append(" order by XT_YHDTQXB.dtqxid");
    return getXtyhdtqxxx(strBufHql.toString(), nPageOffset, nPageSize);
  }

  /**
   *增加用户等同权限信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtyhdtqxxx(VarVO vvXtyhb) throws ServiceException,
      DAOException {
    ArrayList lstXtyh = new ArrayList();
    PoXT_YHDTQXB poXTYHDTQXB = new PoXT_YHDTQXB();
    PoXT_YHDTQXB rtnpoXTYHDTQXB = new PoXT_YHDTQXB();
    try {
      String sYhid = vvXtyhb.getVarValue("yhid");
      if (!this.VerifyYH(String.valueOf(this.getUserInfo().getYhid()))) {
        if (!this.GetYHDwdm(sYhid).substring(0,
                                             6).equals(this.getUserInfo().
            getDwdm().substring(0, 6))) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "无增加非本区县用户等同权限的权限", null);
        }
      }
      //dtqxid,yhid,dtyhid
      BeanUtils.populate(poXTYHDTQXB, vvXtyhb.getVarmap());
      poXTYHDTQXB.setDtqxid( (Long) Xt_yhdtqxbDao.getId());
      //执行HQL语句
      rtnpoXTYHDTQXB =(PoXT_YHDTQXB)super.create(poXTYHDTQXB);
      lstXtyh.add(rtnpoXTYHDTQXB);
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
    return lstXtyh;
  }

  /**
   *删除用户等同权限信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtyhdtqxscxx(VarVO vvXtyhb) throws ServiceException,
      DAOException {
    PoXT_YHDTQXB poXTYHDTQXB = new PoXT_YHDTQXB();
    PoXT_YHDTQXB rtnpoXTYHDTQXB = new PoXT_YHDTQXB();
    int nReturn = PublicConstant.XT_BZ_ERROR;

    //执行HQL语句
    try {
      poXTYHDTQXB  = super.get(PoXT_YHDTQXB.class,Long.valueOf(vvXtyhb.
          getVarValue("dtqxid")));
      String sYhid = vvXtyhb.getVarValue("yhid");
      if (!this.VerifyYH(String.valueOf(this.getUserInfo().getYhid()))) {
        if (!this.GetYHDwdm(poXTYHDTQXB.getYhid().toString()).substring(0,
            6).equals(this.getUserInfo().
                      getDwdm().substring(0, 6))) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "无删除非本区县用户等同权限的权限", null);
        }
      }

      //poXTYHDTQXB  = super.get(.class,Long.valueOf(vvXtyhb.
      //    getVarValue("dtqxid")));
      BeanUtils.populate(poXTYHDTQXB, vvXtyhb.getVarmap());
      super.delete(poXTYHDTQXB);
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
   * 查询用户动作权限信息
   * @param strHQL
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtyhdzqxxx(String strHQL) throws ServiceException,
      DAOException {
    //执行HQL语句
    return getXtyhdzqxxx(strHQL, PublicConstant.PAGE_DEFAULT_OFFSET,
                         PublicConstant.PAGE_DEFAULT_SIZE);
  }

  public List getXtyhdzqxxx(String strHQL, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    //执行HQL语句
    List lstXtRtn = new ArrayList();
    List lstXtdzqx = null;
    PoXT_YHDZQXB poXT_YHDZQXB = null;
    PoXT_YHXXB poXT_YHXXB = null;
    PoXT_SPDZB poXT_SPDZB = null;

    try {
      lstXtdzqx =super.getPageRecords(strHQL,new Object[]{}, nPageOffset,
          nPageSize).getList();
      if (lstXtdzqx != null) {
        Object[] objXtdtqx = null;
        for (int i = 0; i < lstXtdzqx.size(); i++) {
          objXtdtqx = (Object[]) lstXtdzqx.get(i);
          poXT_YHDZQXB = (PoXT_YHDZQXB) objXtdtqx[0];
          poXT_YHXXB = (PoXT_YHXXB) objXtdtqx[1];
          poXT_SPDZB = (PoXT_SPDZB) objXtdtqx[2];
          VoXtdzqx voXtdzqx = new VoXtdzqx(poXT_YHDZQXB, poXT_YHXXB, poXT_SPDZB);
          lstXtRtn.add(voXtdzqx);
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
    return lstXtRtn;
  }

  /**
   * 查询用户动作权限信息
   * @param lstQryXtyh
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtyhdzqxxx(VarVO vvQryXtyh) throws ServiceException,
      DAOException {
    //获取参数信息
    //dtqxid,yhid,dtyhid
    String sDzqxid = vvQryXtyh.getVarValue("dzqxid");
    String sYhid = vvQryXtyh.getVarValue("yhid");
    int nPageOffset = PublicConstant.XT_PAGE; //分页
    int nPageSize = PublicConstant.XT_PAGESIZE;
    //HQL条件语句
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        "select XT_YHDZQXB,XT_YHXXB,XT_SPDZB FROM PoXT_YHDZQXB as XT_YHDZQXB,")
        .append(" PoXT_YHXXB as XT_YHXXB,PoXT_SPDZB as XT_SPDZB ")
        .append(" where XT_YHXXB.yhid = XT_YHDZQXB.yhid ")
        .append(" and XT_SPDZB.dzid = XT_YHDZQXB.dzid ");
    if ( (sDzqxid != null) && ! ("".equals(sDzqxid))) {
      strBufHql.append(" and  XT_YHDZQXB.dzqxid =").append(sDzqxid);
    }
    if ( (sYhid != null) && ! ("".equals(sYhid))) {
      strBufHql.append(" and  XT_YHDZQXB.yhid =").append(sYhid);
    }
    strBufHql.append(" order by XT_YHDZQXB.dzqxid");
    return getXtyhdzqxxx(strBufHql.toString(), nPageOffset, nPageSize);
  }

  /**
   *增加用户动作权限信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtyhdzqxxx(VarVO vvXtyhb) throws ServiceException,
      DAOException {
    ArrayList lstXtyh = new ArrayList();
    PoXT_YHDZQXB poXTYHDZQXB = new PoXT_YHDZQXB();
    PoXT_YHDZQXB rtnpoXTYHDZQXB = new PoXT_YHDZQXB();
    try {
      String sYhid = vvXtyhb.getVarValue("yhid");
      if (!this.VerifyYH(String.valueOf(this.getUserInfo().getYhid()))) {
        if (!this.GetYHDwdm(sYhid).substring(0,
                                             6).equals(this.getUserInfo().
            getDwdm().substring(0, 6))) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "无增加非本区县用户动作权限的权限", null);
        }
      }
      //dtqxid,yhid,dtyhid
      BeanUtils.populate(poXTYHDZQXB, vvXtyhb.getVarmap());
      poXTYHDZQXB.setDzqxid( (Long) Xt_yhdzqxbDao.getId());
      //执行HQL语句
      rtnpoXTYHDZQXB =(PoXT_YHDZQXB)super.create(poXTYHDZQXB);
      lstXtyh.add(rtnpoXTYHDZQXB);
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
    return lstXtyh;
  }

  /**
   *删除用户动作权限信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtyhdzqxscxx(VarVO vvXtyhb) throws ServiceException,
      DAOException {
    PoXT_YHDZQXB poXTYHDZQXB = new PoXT_YHDZQXB();
    PoXT_YHDZQXB rtnpoXTYHDZQXB = new PoXT_YHDZQXB();
    int nReturn = PublicConstant.XT_BZ_ERROR;
    //执行HQL语句
    try {
      poXTYHDZQXB  = super.get(PoXT_YHDZQXB.class,Long.valueOf(vvXtyhb.
          getVarValue("dzqxid")));
      String sYhid = vvXtyhb.getVarValue("yhid");
      if (!this.VerifyYH(String.valueOf(this.getUserInfo().getYhid()))) {
        if (!this.GetYHDwdm(poXTYHDZQXB.getYhid().toString()).substring(0,
            6).equals(this.getUserInfo().
                      getDwdm().substring(0, 6))) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "无删除非本区县用户动作权限的权限", null);
        }
      }
      BeanUtils.populate(poXTYHDZQXB, vvXtyhb.getVarmap());
      super.delete(poXTYHDZQXB);
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
   * 查询用户数据范围信息
   * @param strHQL
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtyhsjfwxx(String strHQL) throws ServiceException,
      DAOException {
    //执行HQL语句
    return getXtyhsjfwxx(strHQL, PublicConstant.PAGE_DEFAULT_OFFSET,
                         PublicConstant.PAGE_DEFAULT_SIZE);
  }

  public List getXtyhsjfwxx(String strHQL, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    //执行HQL语句
    List lstXtRtn = new ArrayList();
    List lstXtyhsjfw = null;
    PoXT_YHXXB poXT_YHXXB = null;
    PoXT_YHSJFWB poXT_YHSJFWB = null;
    PoXT_XTCSB poXT_XTCSB = null;
    Object[] objXtdtqx = null;
    try {
      lstXtyhsjfw =super.getPageRecords(strHQL, new Object[]{}, nPageOffset,
          nPageSize).getList();
      if (lstXtyhsjfw != null) {
        for (int i = 0; i < lstXtyhsjfw.size(); i++) {
          objXtdtqx = (Object[]) lstXtyhsjfw.get(i);
          poXT_YHSJFWB = (PoXT_YHSJFWB) objXtdtqx[0];
          poXT_YHXXB = (PoXT_YHXXB) objXtdtqx[1];
          poXT_XTCSB = (PoXT_XTCSB) objXtdtqx[2];
          VoXtyhsjfw voXtyhsjfw = new VoXtyhsjfw(poXT_YHSJFWB, poXT_YHXXB,
                                                 poXT_XTCSB);
          lstXtRtn.add(voXtyhsjfw);
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
    return lstXtRtn;
  }

  /**
   * 查询用户数据范围信息
   * @param lstQryXtyh
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtyhsjfwxx(VarVO vvQryXtyh) throws ServiceException,
      DAOException {
    //获取参数信息
    String sSjfwid = vvQryXtyh.getVarValue("sjfwid"); ;
    String sYhid = vvQryXtyh.getVarValue("yhid"); ;
    //获取参数信息
    //sjfwid,yhid,xqlx,sjfw;

    int nPageOffset = PublicConstant.XT_PAGE; //分页
    int nPageSize = PublicConstant.XT_PAGESIZE;
    //HQL条件语句
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append("select XT_YHSJFWB,XT_YHXXB,XT_XTCSB from PoXT_YHSJFWB as XT_YHSJFWB,PoXT_YHXXB as XT_YHXXB,")
        .append(
        " PoXT_XTCSB as XT_XTCSB where XT_YHXXB.yhid = XT_YHSJFWB.yhid ")
        .append(
        " and XT_YHSJFWB.xqlx = XT_XTCSB.dm  and XT_XTCSB.cslb = '9017'");
    if ( (sSjfwid != null) && ! ("".equals(sSjfwid))) {
      strBufHql.append(" and  XT_YHSJFWB.sjfwid =").append(sSjfwid);
    }
    if ( (sYhid != null) && ! ("".equals(sYhid))) {
      strBufHql.append(" and  XT_YHSJFWB.yhid =").append(sYhid);
    }
    strBufHql.append(" order by XT_YHXXB.yhdlm");
    return getXtyhsjfwxx(strBufHql.toString(), nPageOffset, nPageSize);
  }

  /**
   *增加用户数据范围限信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtyhsjfwxx(VarVO vvXtyhb) throws ServiceException,
      DAOException {
    ArrayList lstXtyh = new ArrayList();
    PoXT_YHSJFWB poXTYHSJFWB = new PoXT_YHSJFWB();
    PoXT_YHSJFWB rtnpoXTYHSJFWB = new PoXT_YHSJFWB();
    String sYhid = vvXtyhb.getVarValue("yhid");
    String sSjfw = vvXtyhb.getVarValue("sjfw");
    String sXqlx = vvXtyhb.getVarValue("xqlx");
    List lstSjfw = new ArrayList();
    List lstSjfwP = null;
    try {
      if (!this.VerifyYH(String.valueOf(this.getUserInfo().getYhid()))) {
        if (!this.GetYHDwdm(sYhid).substring(0,
                                             6).equals(this.getUserInfo().
            getDwdm().substring(0, 6))) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "无增加非本区县用户数据范围的权限", null);
        }
        //判断输入的数据范围是否在用户所具有的数据范围内
        //取得数据范围
        if (!VerifyYhsjfw(this.getUserInfo().getYhid().toString(), sXqlx, sSjfw)) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "增加的数据范围不能超出本用户所具有的数据范围！", null);
        }

      }
      //dtqxid,yhid,dtyhid
      BeanUtils.populate(poXTYHSJFWB, vvXtyhb.getVarmap());
      poXTYHSJFWB.setSjfwid( (Long) Xt_yhsjfwbDao.getId());
      //执行HQL语句
      rtnpoXTYHSJFWB =(PoXT_YHSJFWB)super.create(poXTYHSJFWB);
      lstXtyh.add(rtnpoXTYHSJFWB);
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
    return lstXtyh;
  }

  /**
   *删除用户数据范围信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtyhsjfwscxx(VarVO vvXtyhb) throws ServiceException,
      DAOException {
    PoXT_YHSJFWB poXTYHSJFWB = new PoXT_YHSJFWB();
    PoXT_YHSJFWB rtnpoXTSJFWB = new PoXT_YHSJFWB();
    int nReturn = PublicConstant.XT_BZ_ERROR;
    String sYhid = vvXtyhb.getVarValue("yhid");
    //执行HQL语句
    try {
      poXTYHSJFWB  = super.get(PoXT_YHSJFWB.class,Long.valueOf(vvXtyhb.
          getVarValue("sjfwid")));
      if (!this.VerifyYH(String.valueOf(this.getUserInfo().getYhid()))) {
        if (!this.GetYHDwdm(poXTYHSJFWB.getYhid().toString()).substring(0,
            6).equals(this.getUserInfo().
                      getDwdm().substring(0, 6))) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "无删除非本区县用户数据范围的权限", null);
        }
      }

      BeanUtils.populate(poXTYHSJFWB, vvXtyhb.getVarmap());
      super.delete(poXTYHSJFWB);
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
   * 查询用户角色信息
   * @param strHQL
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtyhjsxx(String strHQL) throws ServiceException,
      DAOException {
    //执行HQL语句
    return getXtyhjsxx(strHQL, PublicConstant.PAGE_DEFAULT_OFFSET,
                       PublicConstant.PAGE_DEFAULT_SIZE);
  }

  public List getXtyhjsxx(String strHQL, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    //执行HQL语句
    PoXT_YHJSXXB poXT_YHJSXXB = null;
    PoXT_YHXXB poXT_YHXXB = null;
    PoXT_JSXXB poXT_JSXXB = null;
    List lstXtyhjsxx = null;
    Object[] objXtRtn = null;
    List lstXtRtn = new ArrayList();
    //执行HQL语句
    try {
      lstXtyhjsxx =super.getPageRecords(strHQL,new Object[]{}, nPageOffset,
          nPageSize).getList();
      if (lstXtyhjsxx != null && !lstXtyhjsxx.isEmpty()) {
        for (int i = 0; i < lstXtyhjsxx.size(); i++) {
          objXtRtn = (Object[]) lstXtyhjsxx.get(i);
          poXT_YHXXB = (PoXT_YHXXB) objXtRtn[0];
          poXT_YHJSXXB = (PoXT_YHJSXXB) objXtRtn[1];
          poXT_JSXXB = (PoXT_JSXXB) objXtRtn[2];
          VoXtyhjsxx voXtyhjsxx = new VoXtyhjsxx(poXT_YHJSXXB, poXT_YHXXB,
                                                 poXT_JSXXB);
          lstXtRtn.add(voXtyhjsxx);
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
    return lstXtRtn;
  }

  /**
   * 查询用户角色信息
   * @param lstQryXtyh
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtyhjsxx(VarVO vvQryXtyh) throws ServiceException,
      DAOException {
    //获取参数信息
    //dtqxid,yhid,dtyhid
    String sYhjsid = vvQryXtyh.getVarValue("yhjsid");
    String sYhid = vvQryXtyh.getVarValue("yhid");

    int nPageOffset = PublicConstant.XT_PAGE; //分页
    int nPageSize = PublicConstant.XT_PAGESIZE;
    //HQL条件语句
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        "select XT_YHXXB,XT_YHJSXXB,XT_JSXXB FROM PoXT_YHXXB as XT_YHXXB,")
        .append(" PoXT_YHJSXXB as XT_YHJSXXB,PoXT_JSXXB as XT_JSXXB ")
        .append(" where XT_YHXXB.yhid = XT_YHJSXXB.yhid ")
        .append(" and XT_JSXXB.jsid = XT_YHJSXXB.jsid ");
    if ( (sYhjsid != null) && ! ("".equals(sYhjsid))) {
      strBufHql.append(" and  XT_YHJSXXB.yhjsid =").append(sYhjsid);
    }
    if ( (sYhid != null) && ! ("".equals(sYhid))) {
      strBufHql.append(" and  XT_YHJSXXB.yhid =").append(sYhid);
    }
    strBufHql.append(" order by XT_YHJSXXB.yhjsid");
    return getXtyhjsxx(strBufHql.toString(), nPageOffset, nPageSize);
  }

  /**
   *增加用户角色信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtyhjsxx(VarVO vvXtyhb) throws ServiceException, DAOException {
    ArrayList lstXtyh = new ArrayList();
    PoXT_YHJSXXB poXTYHJSXXB = new PoXT_YHJSXXB();
    PoXT_YHJSXXB rtnpoXTYHJSXXB = new PoXT_YHJSXXB();
    String sYhid = vvXtyhb.getVarValue("yhid");
    try {
      if (!this.VerifyYH(String.valueOf(this.getUserInfo().getYhid()))) {
        if (!this.GetYHDwdm(sYhid).substring(0,
                                             6).equals(this.getUserInfo().
            getDwdm().substring(0, 6))) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "无增加非本区县用户的权限", null);
        }
      }
      //dtqxid,yhid,dtyhid
      BeanUtils.populate(poXTYHJSXXB, vvXtyhb.getVarmap());
      poXTYHJSXXB.setYhjsid( (Long) Xt_yhjsxxbDao.getId());
      //执行HQL语句
      rtnpoXTYHJSXXB =(PoXT_YHJSXXB)super.create(poXTYHJSXXB);
      lstXtyh.add(rtnpoXTYHJSXXB);
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
    return lstXtyh;
  }

  /**
   *修改用户角色信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtyhjsxgxx(VarVO vvXtyhb) throws ServiceException,
      DAOException {
    ArrayList lstXtyh = new ArrayList();
    PoXT_YHJSXXB poXTYHJSXXB = new PoXT_YHJSXXB();
    PoXT_YHJSXXB rtnpoXTYHJSXXB = new PoXT_YHJSXXB();
    try {
      //dtqxid,yhid,dtyhid
      poXTYHJSXXB  = super.get(PoXT_YHJSXXB.class,Long.valueOf(vvXtyhb.
          getVarValue("yhjsid")));

      if (!this.VerifyYH(String.valueOf(this.getUserInfo().getYhid()))) {
        if (!this.GetYHDwdm(poXTYHJSXXB.getYhid().toString()).substring(0,
            6).equals(this.getUserInfo().
                      getDwdm().substring(0, 6))) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "无修改非本区县用户的权限", null);
        }
      }

      BeanUtils.populate(poXTYHJSXXB, vvXtyhb.getVarmap());
      //执行HQL语句
      rtnpoXTYHJSXXB =     (PoXT_YHJSXXB)super.update(poXTYHJSXXB);
      lstXtyh.add(rtnpoXTYHJSXXB);
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
    return lstXtyh;
  }

  /**
   *删除用户角色信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtyhjsscxx(VarVO vvXtyhb) throws ServiceException,
      DAOException {
    PoXT_YHJSXXB poXTYHJSXXB = new PoXT_YHJSXXB();
    PoXT_YHJSXXB rtnpoXTYHJSXXB = new PoXT_YHJSXXB();
    int nReturn = PublicConstant.XT_BZ_ERROR;
    String sYhid = vvXtyhb.getVarValue("yhid");
    String sYhjsid = vvXtyhb.getVarValue("yhjsid");
    StringBuffer sbHql = new StringBuffer();
    List lstXtyh;
    try {
      sbHql.append(
          "select XT_YHJSXXB FROM PoXT_YHJSXXB as XT_YHJSXXB,PoXT_YHXXB as XT_YHXXB")
          .append(" where XT_YHJSXXB.yhid = XT_YHXXB.yhid")
          .append(" and XT_YHXXB.yhdlm = '" + PublicConstant.XT_CJYHDLM + "'")
          .append(" and XT_YHXXB.yhid = " + sYhid);
      lstXtyh =super.findAllByHQL(sbHql.toString());
      if (lstXtyh != null && !lstXtyh.isEmpty()) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                   "超级用户功能不能删除", null);
      }
      if (!this.VerifyYH(String.valueOf(this.getUserInfo().getYhid()))) {
        if (!this.GetYHDwdm(sYhid).substring(0,
                                             6).equals(this.getUserInfo().
            getDwdm().substring(0, 6))) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "无删除非本区县用户角色的权限", null);
        }
      }

      sbHql.setLength(0);
      sbHql.append(" from PoXT_YHJSXXB where 1=1 ");

      if ( (sYhid != null) && ! ("".equals(sYhid))) {
        sbHql.append(" and  yhid =").append(sYhid);
      }
      if ( (sYhjsid != null) && ! ("".equals(sYhjsid))) {
        sbHql.append(" and  yhjsid =").append(sYhjsid);
      }
      nReturn = super.deleteAll(super.findAllByHQL(sbHql.toString()));
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
   * 查询用户居委会信息
   * @param lstQryXtyh
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtyhjwhxx(VarVO vvQryXtyh) throws ServiceException,
      DAOException {
    List lstXtyhjwhxx = new ArrayList();
    List lstXtyhsjfw = null;
    List lstXtsjfw = null;
    PoXT_YHSJFWB poXtyhsjfwb = new PoXT_YHSJFWB();
    String sYhid = vvQryXtyh.getVarValue("yhid");
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
      lstXtyhsjfw = getXtYhSjfw(vvQryXtyh);
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

  public List getXtYhSjfw(VarVO vvQryXtyh) throws ServiceException,
      DAOException {
    List lstXtyhsjfw = null;
    //获取参数信息
    String sXqlx = vvQryXtyh.getVarValue("xqlx");
    String sYhid = String.valueOf(this.getUserInfo().getYhid());

    //从用户数据范围表中取出数据范围
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        "select XT_YHSJFWB from PoXT_YHSJFWB as XT_YHSJFWB where 1=1 ");
    if ( (sXqlx == null) || ("".equals(sXqlx))) {
      strBufHql.append(" and  XT_YHSJFWB.xqlx ='").append(PublicConstant.
          XQLX_XQN).append("'");
    }
    else {
      strBufHql.append(" and  XT_YHSJFWB.xqlx ='").append(sXqlx).append("'");
    }
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

  /**
   * 根据代码信息获取居委会信息
   * @param sDm
   * @return
   */
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

  /**
   * 将居委会信息添加到LIST中
   */
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

  /**
   * 查询用户街路巷居委会对照信息
   * @param lstQryXtyh
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult getXtyhjlxjwhdzxx(VarVO vvQryXtyh, String psWhere,
                                         VoPage voPage) throws ServiceException,
      DAOException {
    List lstXtyhjlxjwhdzxx = new ArrayList();
    List lstXtyhsjfw = null;
    List lstXtsjfw = null;
    PoXT_YHSJFWB poXtyhsjfwb = new PoXT_YHSJFWB();
    String sXqlx = "", sSjfw = "";
    String sBz = "", sDm = "";
    VoQueryResult voQueryResult = null;

    //判断用户是否有角色是超级用户
    List lstXtyhjs = null;
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        " select XT_YHJSXXB from PoXT_YHJSXXB as XT_YHJSXXB,PoXT_JSXXB as XT_JSXXB")
        .append(" where XT_YHJSXXB.jsid =XT_JSXXB.jsid and XT_JSXXB.jsmc = '")
        .append(PublicConstant.XT_CJYHMC).append("'")
        .append(" and XT_YHJSXXB.yhid = ").append(String.valueOf(this.
        getUserInfo().getYhid()));
    try {
      PojoInfo  Xt_yhjsxxbDao = DAOFactory.createXT_YHJSXXBDAO();
      lstXtyhjs =super.findAllByHQL(strBufHql.toString());
      if (lstXtyhjs != null && !lstXtyhjs.isEmpty()) {
        poXtyhsjfwb.setYhid(Long.valueOf(String.valueOf(this.getUserInfo().
            getYhid())));
        poXtyhsjfwb.setXqlx("1");
        voQueryResult = getJlxJwhDzFromDm(null, true, psWhere, voPage);
        //AddJlxJwhDztoList(poXtyhsjfwb, getJlxJwhDzFromDm(null, true),
        //                  lstXtyhjlxjwhdzxx);
        return voQueryResult;
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
    List lstDm = new ArrayList();
    try {
      lstXtyhsjfw = getXtYhSjfw(vvQryXtyh);
      if (lstXtyhsjfw != null && lstXtyhsjfw.size() > 0) { //找到记录
        for (int i = 0; i < lstXtyhsjfw.size(); i++) {
          poXtyhsjfwb = (PoXT_YHSJFWB) lstXtyhsjfw.get(i);
          sSjfw = poXtyhsjfwb.getSjfw(); //取得数据范围
          sXqlx = poXtyhsjfwb.getXqlx();
          if (PublicConstant.XQLX_XQW.equals(sXqlx)) {
            continue;
          }
          lstXtsjfw = XtywqxServiceImpl.TransDataRange(sSjfw); //解析数据范围
          sBz = lstXtsjfw.get(0).toString();
          sDm = lstXtsjfw.get(1).toString();
          //if ( (sYhid != null) && !"".equals(sYhid)) {
          //  poXtyhsjfwb.setYhid(Long.valueOf(sYhid));
          //根据代码取得居委会信息
          //   AddJlxJwhDztoList(poXtyhsjfwb, getJlxJwhDzFromDm(sDm, false),
          //                 lstXtyhjlxjwhdzxx);
          // }
          lstDm.add(sDm);
        } //for
        voQueryResult = getJlxJwhDzFromDm(lstDm, false, psWhere, voPage);
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
    return voQueryResult;
  }

  /**
   * 根据代码信息获取jlx居委会dz信息
   * @param sDm
   * @return
   */
  public VoQueryResult getJlxJwhDzFromDm(List lstDm, boolean bCjyh,
                                         String psWhere, VoPage voPage) {
    List lstJlxJwhdz = new ArrayList();
    StringBuffer strBufHql = new StringBuffer();
    VoQueryResult voQueryResult = new VoQueryResult();
    StringBuffer strCountHql = new StringBuffer();
    StringBuffer strHql = new StringBuffer();
    strBufHql.append(
        "select XT_JLXJWHDZXXB,XT_JLXXXB,XT_JWHXXB,XT_XTCSBA,XT_XTCSBB FROM ");
    strCountHql.append("select count(*) from ");
    strHql.append(" PoXT_XTCSB as XT_XTCSBA,PoXT_XTCSB as XT_XTCSBB,")
        .append(" PoXT_JLXJWHDZXXB as XT_JLXJWHDZXXB,PoXT_JLXXXB as XT_JLXXXB,")
        .append(
        " PoXT_JWHXXB as XT_JWHXXB where  XT_JLXJWHDZXXB.jlxdm = XT_JLXXXB.dm ")
        .append(
        " and XT_JLXJWHDZXXB.qybz = XT_XTCSBA.dm(+) and XT_XTCSBA.cslb(+) = '9002'")
        .append(
        " and XT_JLXJWHDZXXB.jlxhlx = XT_XTCSBB.dm(+) and XT_XTCSBB.cslb(+) = '1059'")
        .append(" and XT_JLXJWHDZXXB.jwhdm = XT_JWHXXB.dm ")
        .append("and XT_JLXJWHDZXXB.qybz='").append(PublicConstant.QYBZ_QY).
        append("'")
        .append("and XT_JLXXXB.qybz='").append(PublicConstant.QYBZ_QY).append(
        "'")
        .append("and XT_JWHXXB.qybz='").append(PublicConstant.QYBZ_QY).append(
        "'");

    if (psWhere != null && !"".equals(psWhere)) {
      strHql.append(" and " + psWhere);
    }

    /*
        strBufHql.append(
            " select XT_JLXJWHDZXXB ,XT_JWHXXB from PoXT_JLXJWHDZXXB as XT_JLXJWHDZXXB,PoXT_JWHXXB")
            .append(" as XT_JWHXXB  where XT_JWHXXB.dm=XT_JLXJWHDZXXB.jwhdm and XT_JLXJWHDZXXB.qybz='")
            .append(PublicConstant.QYBZ_QY).append("'");
     */
    //如果代码是居委会代码
    if (lstDm != null) {
      strHql.append("and (1=0");
      for (int i = 0; i < lstDm.size(); i++) {
        if ( ( (String) lstDm.get(i)).length() == 12 && !bCjyh) {
          strHql.append(" or XT_JLXJWHDZXXB.jwhdm = '").append( (String)
              lstDm.get(i)).append(
              "'");
        }
        else
        if ( ( (String) lstDm.get(i)).length() == 9 && !bCjyh) { //如果是pcs
          strHql.append(" or XT_JWHXXB.dwdm ='").append( ( (String) lstDm.
              get(i))).append("'");
        }
        else
        if ( ( (String) lstDm.get(i)).length() == 6 && !bCjyh) {
          strHql.append(" or substr(XT_JWHXXB.dwdm,0,6)='").append( ( (
              String) lstDm.get(i))).
              append(
              "'");
        }
      }
      strHql.append(" ) ");
    }
    strHql.append(" order by XT_JLXJWHDZXXB.jwhdm,XT_JLXJWHDZXXB.czid");
    strBufHql.append(strHql.toString());
    strCountHql.append(strHql.toString());
    List lstXtRtn = null;
    PoXT_JLXJWHDZXXB poXTJLXJWHDZB = null;
    PoXT_JLXXXB poXTJLXXXB = null;
    PoXT_JWHXXB poXTJWHXXB = null;
    PoXT_XTCSB poXTCSBA = null;
    PoXT_XTCSB poXTCSBB = null;
    Object[] objXtRtn = null;

    try {
      PojoInfo  Xt_jlxjwhdzxxbDao = DAOFactory.
          createXT_JLXJWHDZXXBDAO();

      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(strCountHql.
            toString()));
      }
      lstXtRtn =super.getPageRecords(strBufHql.
          toString(), new Object[]{}, voPage.getPageindex(),
          voPage.getPagesize()).getList();

      for (int i = 0; i < lstXtRtn.size(); i++) {
        objXtRtn = (Object[]) lstXtRtn.get(i);
        poXTJLXJWHDZB = (PoXT_JLXJWHDZXXB) objXtRtn[0];
        poXTJLXXXB = (PoXT_JLXXXB) objXtRtn[1];
        poXTJWHXXB = (PoXT_JWHXXB) objXtRtn[2];
        poXTCSBA = (PoXT_XTCSB) objXtRtn[3];
        poXTCSBB = (PoXT_XTCSB) objXtRtn[4];
        VoXtjlxjwhdz voXtjlxjwhdz = new VoXtjlxjwhdz(poXTJLXJWHDZB, poXTJLXXXB,
            poXTJWHXXB, poXTCSBA, poXTCSBB);
        lstJlxJwhdz.add(voXtjlxjwhdz);
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
    voQueryResult.setPagelist(lstJlxJwhdz);
    voQueryResult.setVotype(VoXtjlxjwhdz.class);
    return voQueryResult;
  }

  /**
   * 将jlx居委会dz信息添加到LIST中
   */
  public void AddJlxJwhDztoList(PoXT_YHSJFWB poXT_YHSJFWB, List lstJlxJwhdz,
                                List lstXtRtn) {
    PoXT_JLXJWHDZXXB poXtjlxjwhdzxxb;
    VoXtjlxjwhdz voXtjlxjwhdz, voXtlst;
    PoXT_JLXJWHDZXXB poXtlst;
    String sXqlx = poXT_YHSJFWB.getXqlx();
    String sYhid = String.valueOf(poXT_YHSJFWB.getYhid());
    boolean bFlag;
    if (lstJlxJwhdz != null && lstJlxJwhdz.size() > 0) { //找到记录
      for (int i = 0; i < lstJlxJwhdz.size(); i++) {
        voXtjlxjwhdz = (VoXtjlxjwhdz) lstJlxJwhdz.get(i);
        bFlag = false;
        for (int j = 0; j < lstXtRtn.size(); j++) {
          voXtlst = (VoXtjlxjwhdz) lstXtRtn.get(j);
          if (voXtlst.getCzid().equals(voXtjlxjwhdz.getCzid())) {
            bFlag = true;
            break;
          }
        }
        if (!bFlag) {
          lstXtRtn.add(voXtjlxjwhdz);
        }
      } //for
    } //if
  }

  public String getXtfwqsj(VarVO vvQryXtyh) throws ServiceException,
      DAOException {
    String date = null;
    //SimpleDateFormat newFormat;
    String sFormat = vvQryXtyh.getVarValue("format");
    if ( (sFormat != null) && ! ("".equals(sFormat)) && (sFormat.length() > 0)) {
      date = StringUtils.getServiceDate(sFormat);//根据参数取数据库时间
      //newFormat = new SimpleDateFormat(sFormat);
    }
    else {
      date = StringUtils.getServiceDate("yyyy/MM/dd HH:mm:ss");//传入参数没有格式要求时，取yyyy/MM/dd HH:mm:ss
      //newFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    }
    return date;
    //return newFormat.format(new java.util.Date());
    //取数据库时间
/*   String sSql ="",sDate= "";
   List lstDate = null;
   if ( (sFormat != null) && ! ("".equals(sFormat)) && (sFormat.length() > 0)) {
    sSql =" select to_char(sysdate,'"+sFormat+"') from dual; ";
   }
   else {
    sSql =" select to_char(sysdate,'YYYY/MM/DD HH:mm:ss') from dual; ";
  }
  try {
    lstDate =super.findAllByHQL(sSql);
    if ((lstDate == null) || (lstDate.size()<=0)){

      sDate = DateFormat new Date();
    }
    else
      sDate = (String)lstDate.get(0);
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
  return sDate;
*/  }

}
