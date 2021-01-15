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
import com.hzjc.hz2004.constant.*;
import com.hzjc.hz2004.common.HjCommon;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 报表操作实现</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */
@Service
public class XtbbServiceImpl  extends HjCommon  implements XtbbService{

  private PojoInfo Xt_ywbbxxbDao = DAOFactory.createXT_YWBBMBXXBDAO();
  private PojoInfo Xt_zsbbmbxxbDao = DAOFactory.createXT_ZSBBMBXXBDAO();
  private PojoInfo Xt_zsbbxxbDao = DAOFactory.createXT_ZSBBXXBDAO();
  
  /**
   * 字符串查询
   * @param strHQL
   * @param nPageOffset
   * @param nPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtywbbxx(String strHQL) throws ServiceException,
      DAOException {
    return getXtywbbxx(strHQL, PublicConstant.XT_PAGE,
                       PublicConstant.XT_PAGESIZE);
  }

  /**
   *
   * @param strHQL
   * @param nPageOffset
   * @param nPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtywbbxx(String strHQL, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    List lstXtbbxx = null;
    //执行HQL语句
    VoXT_YWBBMBXXB voXTYWBBB = new VoXT_YWBBMBXXB();
    PoXT_YHXXB poXTYHXXBa = new PoXT_YHXXB();
    PoXT_YHXXB poXTYHXXBb = new PoXT_YHXXB();
    Object[] objXtRtn = null;
    List lstXtRtn = new ArrayList();
    try {
      lstXtbbxx = super.getPageRecords(strHQL, new Object[]{}, nPageOffset,
          nPageSize).getList();

      for (int i = 0; i < lstXtbbxx.size(); i++) {
        objXtRtn = (Object[]) lstXtbbxx.get(i);
        voXTYWBBB.setYwbbid( (Long) objXtRtn[0]);
        voXTYWBBB.setYwbblb( (String) objXtRtn[1]);
        voXTYWBBB.setBbmc( (String) objXtRtn[2]);
        voXTYWBBB.setJlsj( (String) objXtRtn[3]);
        voXTYWBBB.setJlrid( (Long) objXtRtn[4]);
        voXTYWBBB.setXgsj( (String) objXtRtn[5]);
        voXTYWBBB.setXgrid( (Long) objXtRtn[6]);
        poXTYHXXBa = (PoXT_YHXXB) objXtRtn[7];
        poXTYHXXBb = (PoXT_YHXXB) objXtRtn[8];
        VoXtywbb voXtywbb = new VoXtywbb(voXTYWBBB, poXTYHXXBa, poXTYHXXBb);
        lstXtRtn.add(voXtywbb);
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
   *
   * @param strHQL
   * @param nPageOffset
   * @param nPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtywbbmbxx(String strHQL, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    List lstXtbbxx = null;
    //执行HQL语句
    PoXT_YWBBMBXXB poXTYWBBB = new PoXT_YWBBMBXXB();
    PoXT_YHXXB poXTYHXXBa = new PoXT_YHXXB();
    PoXT_YHXXB poXTYHXXBb = new PoXT_YHXXB();
    Object[] objXtRtn = null;
    List lstXtRtn = new ArrayList();
    try {
      lstXtbbxx = super.getPageRecords(strHQL,new Object[]{}, nPageOffset,
          nPageSize).getList();
      for (int i = 0; i < lstXtbbxx.size(); i++) {
        objXtRtn = (Object[]) lstXtbbxx.get(i);
        poXTYWBBB = (PoXT_YWBBMBXXB) objXtRtn[0];
        poXTYHXXBa = (PoXT_YHXXB) objXtRtn[1];
        poXTYHXXBb = (PoXT_YHXXB) objXtRtn[2];
        VoXT_YWBBMBXXB voXTYWBBB = new VoXT_YWBBMBXXB(poXTYWBBB);
        VoXtywbb voXtywbb = new VoXtywbb(voXTYWBBB, poXTYHXXBa, poXTYHXXBb);
        lstXtRtn.add(voXtywbb);
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
   *查询业务报表信息
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtywbbxx(VarVO vvHqlQryXtbb) throws ServiceException,
      DAOException {
    //获取参数信息
    //ywbbid,ywbblb,bbmc,bbmb,jlsj,scrid,xgsj,xgrid,bbzt;
    int nPageOffset = PublicConstant.XT_PAGE; //分页
    int nPageSize = PublicConstant.XT_PAGESIZE;
    String sYwbbid = vvHqlQryXtbb.getVarValue("ywbbid");
    String sYwbblb = vvHqlQryXtbb.getVarValue("ywbblb");
    String sBbmc = vvHqlQryXtbb.getVarValue("bbmc");
    //String sBBmb = vvHqlQryXtbb.getVarValue("bbmb");
    String sJlsj = vvHqlQryXtbb.getVarValue("jlsj");
    String sScrid = vvHqlQryXtbb.getVarValue("jlrid");
    String sXgsj = vvHqlQryXtbb.getVarValue("xgsj");
    String sXgrid = vvHqlQryXtbb.getVarValue("xgrid");
    String sYhid = vvHqlQryXtbb.getVarValue("yhid");
    String sDtbz = vvHqlQryXtbb.getVarValue("yhid");
    //HQL条件语句
    //判断用户是否有角色是超级用户
    List lstXtyhjs = null;
    boolean bCjyh = false;
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        " select XT_YHJSXXB from PoXT_YHJSXXB as XT_YHJSXXB,PoXT_JSXXB as XT_JSXXB")
        .append(" where XT_YHJSXXB.jsid =XT_JSXXB.jsid and XT_JSXXB.jsmc = '")
        .append(PublicConstant.XT_CJYHMC).append("'")
        .append(" and XT_YHJSXXB.yhid = ").append(sYhid);
    try {
      PojoInfo Xt_yhjsxxbDao = DAOFactory.createXT_YHJSXXBDAO();
      lstXtyhjs = super.findAllByHQL(strBufHql.toString());
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

    strBufHql.setLength(0);
    strBufHql.append(
        "select distinct XT_YWBBMBXXB.ywbbid,XT_YWBBMBXXB.ywbblb,XT_YWBBMBXXB.bbmc,")
        .append(" XT_YWBBMBXXB.jlsj,XT_YWBBMBXXB.jlrid,XT_YWBBMBXXB.xgsj,")
        .append(
        " XT_YWBBMBXXB.xgrid,XT_YHXXBA,XT_YHXXBB FROM PoXT_YWBBMBXXB as XT_YWBBMBXXB,")
        .append(" PoXT_JSYWBBQXB as XT_JSYWBBQXB,PoXT_YHJSXXB as XT_YHJSXXB,")
        .append("PoXT_YHXXB as XT_YHXXBA,PoXT_YHXXB as XT_YHXXBB")
        .append(" where XT_YWBBMBXXB.jlrid =  XT_YHXXBA.yhid")
        .append(" and XT_YWBBMBXXB.ywbbid =  XT_JSYWBBQXB.ywbbid(+)")
        .append(" and XT_JSYWBBQXB.jsid =  XT_YHJSXXB.jsid(+)")
        .append(" and  XT_YWBBMBXXB.xgrid =  XT_YHXXBB.yhid(+)");
    if ( (sYwbbid != null) && ! ("".equals(sYwbbid))) {
      strBufHql.append(" and  XT_YWBBMBXXB.ywbbid =").append(sYwbbid);
    }
    if ( (sYwbblb != null) && ! ("".equals(sYwbblb))) {
      strBufHql.append(" and  XT_YWBBMBXXB.ywbblb ='").append(sYwbblb).append(
          "'");
    }
    if (!bCjyh) {
      strBufHql.append(" and  (XT_YHJSXXB.yhid =").append(sYhid)
          .append(" or XT_YWBBMBXXB.jlrid=").append(sYhid).append(")");
    }

    strBufHql.append(" order by XT_YWBBMBXXB.ywbblb,XT_YWBBMBXXB.bbmc");
    return getXtywbbxx(strBufHql.toString(), nPageOffset, nPageSize);
  }

  /**
   *查询业务报表模板信息
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtywbbmbxx(VarVO vvHqlQryXtbb) throws ServiceException,
      DAOException {
    //获取参数信息
    //ywbbid,ywbblb,bbmc,bbmb,jlsj,scrid,xgsj,xgrid,bbzt;
    int nPageOffset = PublicConstant.XT_PAGE; //分页
    int nPageSize = PublicConstant.XT_PAGESIZE;
    String sYwbbid = vvHqlQryXtbb.getVarValue("ywbbid");
    String sYwbblb = vvHqlQryXtbb.getVarValue("ywbblb");
    String sBbmc = vvHqlQryXtbb.getVarValue("bbmc");
    //String sBBmb = vvHqlQryXtbb.getVarValue("bbmb");
    String sJlsj = vvHqlQryXtbb.getVarValue("jlsj");
    String sScrid = vvHqlQryXtbb.getVarValue("jlrid");
    String sXgsj = vvHqlQryXtbb.getVarValue("xgsj");
    String sXgrid = vvHqlQryXtbb.getVarValue("xgrid");
    String sYhid = vvHqlQryXtbb.getVarValue("yhid");
    //判断用户是否有角色是超级用户
    List lstXtyhjs = null;
    boolean bCjyh = false;
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        " select XT_YHJSXXB from PoXT_YHJSXXB as XT_YHJSXXB,PoXT_JSXXB as XT_JSXXB")
        .append(" where XT_YHJSXXB.jsid =XT_JSXXB.jsid and XT_JSXXB.jsmc = '")
        .append(PublicConstant.XT_CJYHMC).append("'")
        .append(" and XT_YHJSXXB.yhid = ").append(sYhid);
    try {
      PojoInfo Xt_yhjsxxbDao = DAOFactory.createXT_YHJSXXBDAO();
      lstXtyhjs = super.findAllByHQL(strBufHql.toString());
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
    strBufHql.setLength(0);
    strBufHql.append(
        "select XT_YWBBMBXXB,XT_YHXXBA,XT_YHXXBB FROM PoXT_YWBBMBXXB as XT_YWBBMBXXB,")
        // sHql = sHql + " PoXT_JSYWBBQXB as XT_JSYWBBQXB,PoXT_YHJSXXB as XT_YHJSXXB,";
        .append("PoXT_YHXXB as XT_YHXXBA,PoXT_YHXXB as XT_YHXXBB")
        .append(" where XT_YWBBMBXXB.jlrid =  XT_YHXXBA.yhid")
        .append(" and  XT_YWBBMBXXB.xgrid =  XT_YHXXBB.yhid(+)");
    //sHql = sHql + " and XT_YWBBMBXXB.ywbbid =  XT_JSYWBBQXB.ywbbid(+)";
    // sHql = sHql + " and XT_JSYWBBQXB.jsid =  XT_YHJSXXB.jsid(+)";
    if ( (sYwbbid != null) && ! ("".equals(sYwbbid))) {
      strBufHql.append(" and  XT_YWBBMBXXB.ywbbid =").append(sYwbbid);
    }
    if ( (sYwbblb != null) && ! ("".equals(sYwbblb))) {
      strBufHql.append(" and  XT_YWBBMBXXB.ywbblb ='").append(sYwbblb).append(
          "'");
    }
    //   if (!bCjyh) {
    //     sHql = sHql + " and ( XT_YHJSXXB.yhid =" + sYhid + " or XT_YWBBMBXXB.jlrid="+ sYhid + ")";
    //   }

    strBufHql.append(" order by XT_YWBBMBXXB.ywbblb,XT_YWBBMBXXB.bbmc");
    return getXtywbbmbxx(strBufHql.toString(), nPageOffset, nPageSize);
  }

  /**
   *增加业务报表信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtywbbxx(VarVO vvXTbbB) throws ServiceException, DAOException {
    ArrayList lstXtbb = new ArrayList();
    VoXT_YWBBMBXXB voXtywbbb = new VoXT_YWBBMBXXB();
    PoXT_YWBBMBXXB rtnpoXTYWBBB = new PoXT_YWBBMBXXB();
    //ywbbid,ywbblb,bbmc,bbmb,jlsj,scrid,xgsj,xgrid,bbzt;
    try {
      //执行HQL语句
      BeanUtils.populate(voXtywbbb, vvXTbbB.getVarmap());
      System.out.println(Xt_ywbbxxbDao.getId()+"--------");
      voXtywbbb.setYwbbid( (Long) Xt_ywbbxxbDao.getId());
      voXtywbbb.setJlsj(StringUtils.getServiceTime());
      voXtywbbb.setJlrid(this.getUserInfo().getYhid());
      //voXtywbbb.setBbzt(PublicConstant.XT_BZ_QY);
      rtnpoXTYWBBB = (PoXT_YWBBMBXXB)super.create(voXtywbbb.
          toPoXT_YWBBMBXXB());
      lstXtbb.add(rtnpoXTYWBBB);
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
    return lstXtbb;
  }

  /**
   * 修改业务报表信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtywbbxgxx(VarVO vvXTbbB) throws ServiceException,
      DAOException {
    ArrayList lstXtbb = new ArrayList();
    PoXT_YWBBMBXXB poXtywbbb = null;
    VoXT_YWBBMBXXB voXtywbbb=null;
    PoXT_YWBBMBXXB rtnpoXTywbbB = new PoXT_YWBBMBXXB();
    //获取参数信息,设置PO语句
    // ywbbid,ywbblb,bbmc,bbmb,jlsj,scrid,xgsj,xgrid,bbzt;
    try {
      poXtywbbb = super.get(PoXT_YWBBMBXXB.class,Long.valueOf(vvXTbbB.
          getVarValue("ywbbid")));
      if (poXtywbbb == null) {
        return null;
      }
      //voXtywbbb = new VoXT_YWBBMBXXB(poXtywbbb);
      BeanUtils.populate(poXtywbbb, vvXTbbB.getVarmap());
      poXtywbbb.setXgsj(StringUtils.getServiceTime());
      poXtywbbb.setXgrid(this.getUserInfo().getYhid());
      poXtywbbb.setBbmb(vvXTbbB.getVarValue("bbmb")==null?null:vvXTbbB.getVarValue("bbmb").getBytes());
      //poXtywbbb.setBbzt(PublicConstant.XT_BZ_QY);
      //执行
      rtnpoXTywbbB = (PoXT_YWBBMBXXB)super.update(poXtywbbb);
      lstXtbb.add(rtnpoXTywbbB);
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
    return lstXtbb;

  }

  /**
   *删除业务报表模板信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtywbbscxx(VarVO vvXTbbB) throws ServiceException, DAOException {
    PoXT_YWBBMBXXB rtnpoXTywbbB = new PoXT_YWBBMBXXB();
    PoXT_YWBBMBXXB poXTYWBBMBB = new PoXT_YWBBMBXXB();
    List lstXtRtn = new ArrayList();
    int nXtRtn = PublicConstant.XT_BZ_ERROR;
    //组合poXT_XTsplB
    //获取参数信息
    String sYwbbid = vvXTbbB.getVarValue("ywbbid");
    String sYwbblb = vvXTbbB.getVarValue("ywbblb");
    try {
      StringBuffer strBufHql = new StringBuffer();
      strBufHql.append(" from PoXT_YWBBMBXXB where 1=1 ");
      if ( (sYwbbid != null) && ! ("".equals(sYwbbid))) {
        strBufHql.append(" and  ywbbid =").append(sYwbbid);
      }
      if ( (sYwbblb != null) && ! ("".equals(sYwbblb))) {
        strBufHql.append(" and  ywbblb =").append(sYwbblb);
      }
      nXtRtn = super.deleteAll(super.findAllByHQL(strBufHql.toString()));
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
    return nXtRtn;
  }

  /**
   * 查询制试报表模板
   * @param strHQL
   * @param nPageOffset
   * @param nPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtzsbbmbxx(String strHQL) throws ServiceException,
      DAOException {
    return getXtzsbbmbxx(strHQL, PublicConstant.XT_PAGE,
                         PublicConstant.XT_PAGESIZE);
  }

  /**
   *
   * @param strHQL
   * @param nPageOffset
   * @param nPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtzsbbmbxx(String strHQL, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    List lstXtbbxx = null;
    PoXT_ZSBBMBXXB poXTZSBBB = new PoXT_ZSBBMBXXB();
    PoXT_YHXXB poXTYHXXBa = new PoXT_YHXXB();
    PoXT_YHXXB poXTYHXXBb = new PoXT_YHXXB();
    Object[] objXtRtn = null;
    List lstXtRtn = new ArrayList();
    try {
      lstXtbbxx = super.getPageRecords(strHQL, new Object[]{}, nPageOffset,
          nPageSize).getList();
      for (int i = 0; i < lstXtbbxx.size(); i++) {
        objXtRtn = (Object[]) lstXtbbxx.get(i);
        poXTZSBBB = (PoXT_ZSBBMBXXB) objXtRtn[0];
        poXTYHXXBa = (PoXT_YHXXB) objXtRtn[1];
        poXTYHXXBb = (PoXT_YHXXB) objXtRtn[2];
        VoXT_ZSBBMBXXB voXTZSBBB = new VoXT_ZSBBMBXXB(poXTZSBBB);
        VoXtzsbbmbxx voXtzsbb = new VoXtzsbbmbxx(voXTZSBBB, poXTYHXXBa,
                                                 poXTYHXXBb);
        lstXtRtn.add(voXtzsbb);
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
   *
   * @param strHQL
   * @param nPageOffset
   * @param nPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtzsbbnotmbxx(String strHQL, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    List lstXtbbxx = null;
    //执行HQL语句
    VoXT_ZSBBMBXXB voXTZSBBB = new VoXT_ZSBBMBXXB();
    PoXT_YHXXB poXTYHXXBa = new PoXT_YHXXB();
    PoXT_YHXXB poXTYHXXBb = new PoXT_YHXXB();
    Object[] objXtRtn = null;
    List lstXtRtn = new ArrayList();
    try {
      lstXtbbxx = super.getPageRecords(strHQL, new Object[]{}, nPageOffset,
          nPageSize).getList();
      for (int i = 0; i < lstXtbbxx.size(); i++) {
        objXtRtn = (Object[]) lstXtbbxx.get(i);
        voXTZSBBB.setZsbbmbid( (Long) objXtRtn[0]);
        voXTZSBBB.setZsbblb( (String) objXtRtn[1]);
        voXTZSBBB.setBbmbmc( (String) objXtRtn[2]);
        voXTZSBBB.setJlsj( (String) objXtRtn[3]);
        voXTZSBBB.setScrid( (Long) objXtRtn[4]);
        voXTZSBBB.setXgsj( (String) objXtRtn[5]);
        voXTZSBBB.setXgrid( (Long) objXtRtn[6]);
        poXTYHXXBa = (PoXT_YHXXB) objXtRtn[7];
        poXTYHXXBb = (PoXT_YHXXB) objXtRtn[8];
        VoXtzsbbmbxx voXtzsbb = new VoXtzsbbmbxx(voXTZSBBB, poXTYHXXBa,
                                                 poXTYHXXBb);
        lstXtRtn.add(voXtzsbb);
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
   *查询制试报表模板
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtzsbbnotmbxx(VarVO vvHqlQryXtbb) throws ServiceException,
      DAOException {
    //zsbbmbid,zsbblb,bbmbmc,bbmb,jlsj,scrid,xgsj,xgrid;
    int nPageOffset = PublicConstant.XT_PAGE; //分页
    int nPageSize = PublicConstant.XT_PAGESIZE;
    //获取参数信息
    String sZsbbmbid = vvHqlQryXtbb.getVarValue("zsbbmbid");
    String sZsbblb = vvHqlQryXtbb.getVarValue("zsbblb");
    String sBbmc = vvHqlQryXtbb.getVarValue("bbmbmc");
    //String sBBmb = vvHqlQryXtbb.getVarValue("bbmb");
    String sJlsj = vvHqlQryXtbb.getVarValue("jlsj");
    String sScrid = vvHqlQryXtbb.getVarValue("scrid");
    String sXgsj = vvHqlQryXtbb.getVarValue("xgsj");
    String sXgrid = vvHqlQryXtbb.getVarValue("xgrid");
    String sYhid = vvHqlQryXtbb.getVarValue("yhid");
    //判断用户是否有角色是超级用户
    List lstXtyhjs = null;
    boolean bCjyh = false;
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        " select XT_YHJSXXB from PoXT_YHJSXXB as XT_YHJSXXB,PoXT_JSXXB as XT_JSXXB")
        .append(" where XT_YHJSXXB.jsid =XT_JSXXB.jsid and XT_JSXXB.jsmc = '")
        .append(PublicConstant.XT_CJYHMC).append("'")
        .append(" and XT_YHJSXXB.yhid = ").append(sYhid);
    try {
      PojoInfo Xt_yhjsxxbDao = DAOFactory.createXT_YHJSXXBDAO();
      lstXtyhjs = super.findAllByHQL(strBufHql.toString());
      if (lstXtyhjs != null && !lstXtyhjs.isEmpty()) {
        bCjyh = true;
      }
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

    strBufHql.setLength(0);
    strBufHql.append(
        "select distinct XT_ZSBBMBXXB.zsbbmbid,XT_ZSBBMBXXB.zsbblb,XT_ZSBBMBXXB.bbmbmc,")
        .append(" XT_ZSBBMBXXB.jlsj,XT_ZSBBMBXXB.scrid,XT_ZSBBMBXXB.xgsj,")
        .append(
        " XT_ZSBBMBXXB.xgrid,XT_YHXXBA,XT_YHXXBB FROM PoXT_ZSBBMBXXB as XT_ZSBBMBXXB,")
        .append(" PoXT_JSZSBBQXB as XT_JSZSBBQXB,PoXT_YHJSXXB as XT_YHJSXXB,")
        .append("PoXT_YHXXB as XT_YHXXBA,PoXT_YHXXB as XT_YHXXBB")
        .append(" where XT_ZSBBMBXXB.scrid =  XT_YHXXBA.yhid")
        .append(" and  XT_ZSBBMBXXB.xgrid =  XT_YHXXBB.yhid(+)")
        .append(" and XT_ZSBBMBXXB.zsbbmbid =  XT_JSZSBBQXB.zsbbmbid(+)")
        .append(" and XT_JSZSBBQXB.jsid =  XT_YHJSXXB.jsid(+)");
    if ( (sZsbbmbid != null) && ! ("".equals(sZsbbmbid))) {
      strBufHql.append(" and  XT_ZSBBMBXXB.zsbbmbid =").append(sZsbbmbid);
    }
    if ( (sZsbblb != null) && ! ("".equals(sZsbblb))) {
      strBufHql.append(" and  XT_ZSBBMBXXB.zsbblb =").append(sZsbblb);
    }
    if (!bCjyh) {
      strBufHql.append(" and ( XT_YHJSXXB.yhid =").append(sYhid)
          .append(" or XT_ZSBBMBXXB.scrid=").append(sYhid).append(")");
    }
    strBufHql.append(" order by XT_ZSBBMBXXB.zsbblb,XT_ZSBBMBXXB.bbmbmc");
    return getXtzsbbnotmbxx(strBufHql.toString(), nPageOffset, nPageSize);
  }

  /**
   *查询报表模板信息
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtzsbbmbxx(VarVO vvHqlQryXtbb) throws ServiceException,
      DAOException {
    //获取参数信息
    int nPageOffset = PublicConstant.XT_PAGE; //分页
    int nPageSize = PublicConstant.XT_PAGESIZE;

    String sZsbbmbid = vvHqlQryXtbb.getVarValue("zsbbmbid");
    String sZsbblb = vvHqlQryXtbb.getVarValue("zsbblb");
    String sBbmc = vvHqlQryXtbb.getVarValue("bbmbmc");
    //String sBBmb = vvHqlQryXtbb.getVarValue("bbmb");
    String sJlsj = vvHqlQryXtbb.getVarValue("jlsj");
    String sScrid = vvHqlQryXtbb.getVarValue("scrid");
    String sXgsj = vvHqlQryXtbb.getVarValue("xgsj");
    String sXgrid = vvHqlQryXtbb.getVarValue("xgrid");
    String sYhid = vvHqlQryXtbb.getVarValue("yhid");
    //判断用户是否有角色是超级用户
    List lstXtyhjs = null;
    boolean bCjyh = false;
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        " select XT_YHJSXXB from PoXT_YHJSXXB as XT_YHJSXXB,PoXT_JSXXB as XT_JSXXB")
        .append(" where XT_YHJSXXB.jsid =XT_JSXXB.jsid and XT_JSXXB.jsmc = '")
        .append(PublicConstant.XT_CJYHMC).append("'")
        .append(" and XT_YHJSXXB.yhid = ").append(sYhid);
    try {
      PojoInfo Xt_yhjsxxbDao = DAOFactory.createXT_YHJSXXBDAO();
      lstXtyhjs = super.findAllByHQL(strBufHql.toString());
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
    strBufHql.setLength(0);
    strBufHql.append(
        "select XT_ZSBBMBXXB,XT_YHXXBA,XT_YHXXBB FROM PoXT_ZSBBMBXXB as XT_ZSBBMBXXB,")
        //   sHql = sHql + " PoXT_JSZSBBQXB as XT_JSZSBBQXB,PoXT_YHJSXXB as XT_YHJSXXB,";
        .append("PoXT_YHXXB as XT_YHXXBA,PoXT_YHXXB as XT_YHXXBB")
        .append(" where XT_ZSBBMBXXB.scrid =  XT_YHXXBA.yhid")
        .append(" and  XT_ZSBBMBXXB.xgrid =  XT_YHXXBB.yhid(+)");
//    sHql = sHql + " and XT_ZSBBMBXXB.zsbbmbid =  XT_JSZSBBQXB.zsbbmbid(+)";
//    sHql = sHql + " and XT_JSZSBBQXB.jsid =  XT_YHJSXXB.jsid(+)";
    if ( (sZsbbmbid != null) && ! ("".equals(sZsbbmbid))) {
      strBufHql.append(" and  XT_ZSBBMBXXB.zsbbmbid =").append(sZsbbmbid);
    }
    if ( (sZsbblb != null) && ! ("".equals(sZsbblb))) {
      strBufHql.append(" and  XT_ZSBBMBXXB.zsbblb ='").append(sZsbblb).append(
          "'");
    }
//    if (!bCjyh) {
//      sHql = sHql + " and ( XT_YHJSXXB.yhid =" + sYhid + " or XT_ZSBBMBXXB.scrid="+ sYhid + ")";
//    }

    strBufHql.append(" order by XT_ZSBBMBXXB.zsbblb,XT_ZSBBMBXXB.bbmbmc");
    return getXtzsbbmbxx(strBufHql.toString(), nPageOffset, nPageSize);
  }

  /**
   *增加制试报表模板信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtzsbbmbxx(VarVO vvXTbbB) throws ServiceException,
      DAOException {
    ArrayList lstXtbb = new ArrayList();
    VoXT_ZSBBMBXXB voXtzsbbmbb = new VoXT_ZSBBMBXXB();
    PoXT_ZSBBMBXXB rtnpoXTZSBBMBB = new PoXT_ZSBBMBXXB();
    //ywbbid,ywbblb,bbmc,bbmb,jlsj,scrid,xgsj,xgrid,bbzt;
    try {
      BeanUtils.populate(voXtzsbbmbb, vvXTbbB.getVarmap());
      voXtzsbbmbb.setZsbbmbid( (Long) Xt_zsbbmbxxbDao.getId());
      voXtzsbbmbb.setJlsj(StringUtils.getServiceTime());
      voXtzsbbmbb.setScrid(this.getUserInfo().getYhid());
      voXtzsbbmbb.setBbmbmc(vvXTbbB.getVarValue("bbmc"));
      voXtzsbbmbb.setZsbblb(vvXTbbB.getVarValue("bblb"));
      voXtzsbbmbb.setBbmb(vvXTbbB.getVarValue("bbmb"));
      //voXtzsbbmbb.setBbzt(PublicConstant.XT_BZ_QY);
      rtnpoXTZSBBMBB = (PoXT_ZSBBMBXXB)super.create(voXtzsbbmbb.
          toPoXT_ZSBBMBXXB());
      lstXtbb.add(rtnpoXTZSBBMBB);
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
    return lstXtbb;
  }

  /**
   * 修改制试报表模板信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtzsbbmbxgxx(VarVO vvXTbbB) throws ServiceException,
      DAOException {
    ArrayList lstXtbb = null;
    //获取参数信息,设置PO语句
    // ywbbid,ywbblb,bbmc,bbmb,jlsj,scrid,xgsj,xgrid,bbzt;
    try {
      if (vvXTbbB != null && vvXTbbB.getVarValue("zsbbmbid") != null &&
          !"".equals(vvXTbbB.getVarValue("zsbbmbid")) &&
          StringUtils.isNumeric(vvXTbbB.getVarValue("zsbbmbid"))) { //判断传入的参数是否为空

        PoXT_ZSBBMBXXB poXtzsbbmbb = super.get(PoXT_ZSBBMBXXB.class,Long.
            valueOf(vvXTbbB.
                    getVarValue("zsbbmbid")));
        if (poXtzsbbmbb == null) {
          return null;
        }
       // VoXT_ZSBBMBXXB voXtzsbbmbb = new VoXT_ZSBBMBXXB(poXtzsbbmbb);
       // BeanUtils.populate(voXtzsbbmbb, vvXTbbB.getVarmap());

       // voXtzsbbmbb.setXgsj(StringUtils.getServiceTime());
       // voXtzsbbmbb.setXgrid(this.getUserInfo().getYhid());
        //执行
        /*PoXT_ZSBBMBXXB rtnpoXTZSBBMBB = (PoXT_ZSBBMBXXB)super.update(
            voXtzsbbmbb.toPoXT_ZSBBMBXXB());*/
        poXtzsbbmbb.setXgsj(StringUtils.getServiceTime());
        poXtzsbbmbb.setXgrid(this.getUserInfo().getYhid());
        poXtzsbbmbb.setBbmbmc(vvXTbbB.getVarValue("bbmc"));
        poXtzsbbmbb.setBbmb(vvXTbbB.getVarValue("bbmb")==null?null:vvXTbbB.getVarValue("bbmb").getBytes());
        PoXT_ZSBBMBXXB rtnpoXTZSBBMBB = (PoXT_ZSBBMBXXB)super.update(poXtzsbbmbb);
        
        lstXtbb = new ArrayList();
        lstXtbb.add(rtnpoXTZSBBMBB);
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
    return lstXtbb;

  }

  /**
   *删除制试报表模板信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtzsbbmbscxx(VarVO vvXTbbB) throws ServiceException,
      DAOException {
    PoXT_ZSBBMBXXB rtnpoXTZSBBMBB = new PoXT_ZSBBMBXXB();
    PoXT_ZSBBMBXXB poXTZSBBMBB = null;
    VoXT_ZSBBMBXXB voXTZSBBMBB;
    List lstXtzsbbmbsc = new ArrayList();
    int nXtRtn = PublicConstant.XT_BZ_ERROR;
    //组合poXT_XTsplB
    //获取参数信息
    String sZsbbmbid = vvXTbbB.getVarValue("zsbbmbid");
    String sZsbblb = vvXTbbB.getVarValue("zsbblb");
    try {
      StringBuffer strBufHql = new StringBuffer();
      strBufHql.append(" from PoXT_ZSBBMBXXB where 1=1 ");

      if ( (sZsbbmbid != null) && ! ("".equals(sZsbbmbid))) {
        strBufHql.append(" and  zsbbmbid =").append(sZsbbmbid);
      }
      if ( (sZsbblb != null) && ! ("".equals(sZsbblb))) {
        strBufHql.append(" and  zsbblb =").append(sZsbblb);
      }
      nXtRtn = super.deleteAll(super.findAllByHQL(strBufHql.toString()));
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
    return nXtRtn;
  }

  /**
   * 查询制试报表
   * @param strHQL
   * @param nPageOffset
   * @param nPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtzsbbxx(String strHQL) throws ServiceException,
      DAOException {
    return getXtzsbbxx(strHQL, PublicConstant.XT_PAGE,
                       PublicConstant.XT_PAGESIZE);
  }

  /**
   *
   * @param strHQL
   * @param nPageOffset
   * @param nPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtzsbbxx(String strHQL, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    List lstXtbbxx = new ArrayList();
    PoXT_ZSBBMBXXB poXT_ZSBBMBXXB = null;
    PoXT_ZSBBXXB poXT_ZSBBXXB = null;
    PoXT_YHXXB poXT_YHXXBA = null;
    PoXT_YHXXB poXT_YHXXBB = null;
    Object[] objXtRtn = null;
    List lstXtRtn = new ArrayList();
    //执行HQL语句
    try {
      lstXtbbxx = super.getPageRecords(strHQL, new Object[]{}, nPageOffset,
          nPageSize).getList();
      if (lstXtbbxx == null) {
        return null;
      }
      for (int i = 0; i < lstXtbbxx.size(); i++) {
        objXtRtn = (Object[]) lstXtbbxx.get(i);
        poXT_ZSBBXXB = (PoXT_ZSBBXXB) objXtRtn[0];
        poXT_ZSBBMBXXB = (PoXT_ZSBBMBXXB) objXtRtn[1];
        poXT_YHXXBA = (PoXT_YHXXB) objXtRtn[2];
        poXT_YHXXBB = (PoXT_YHXXB) objXtRtn[3];
        VoXT_ZSBBXXB voXTZSBBB = new VoXT_ZSBBXXB(poXT_ZSBBXXB);
        VoXtzsbbxx voXtzsbbxx = new VoXtzsbbxx(voXTZSBBB, poXT_ZSBBMBXXB,
                                               poXT_YHXXBA, poXT_YHXXBB);
        lstXtRtn.add(voXtzsbbxx);
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
   *
   * @param strHQL
   * @param nPageOffset
   * @param nPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtzsbbnotxx(String strHQL, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    List lstXtbbxx = new ArrayList();
    VoXT_ZSBBXXB voXTZSBBB = new VoXT_ZSBBXXB();
    PoXT_ZSBBMBXXB poXT_ZSBBMBXXB = new PoXT_ZSBBMBXXB();
    PoXT_ZSBBXXB poXT_ZSBBXXB = null;
    PoXT_YHXXB poXT_YHXXBA = null;
    PoXT_YHXXB poXT_YHXXBB = null;
    Object[] objXtRtn = null;
    List lstXtRtn = new ArrayList();
   
    //执行HQL语句
    try {
      lstXtbbxx = super.getPageRecords(strHQL,new Object[]{}, nPageOffset,
          nPageSize).getList();
      if (lstXtbbxx == null) {
        return null;
      }
      for (int i = 0; i < lstXtbbxx.size(); i++) {
        objXtRtn = (Object[]) lstXtbbxx.get(i);
        voXTZSBBB.setZsbbid( (Long) objXtRtn[0]);
        voXTZSBBB.setZsbbmbid( (Long) objXtRtn[1]);
        voXTZSBBB.setBbmc( (String) objXtRtn[2]);
        voXTZSBBB.setJlsj( (String) objXtRtn[3]);
        voXTZSBBB.setScrid( (Long) objXtRtn[4]);
        voXTZSBBB.setXgsj( (String) objXtRtn[5]);
        voXTZSBBB.setXgrid( (Long) objXtRtn[6]);
        poXT_ZSBBMBXXB.setZsbblb( (String) objXtRtn[7]);
        poXT_ZSBBMBXXB.setBbmbmc( (String) objXtRtn[8]);
        poXT_YHXXBA = (PoXT_YHXXB) objXtRtn[9];
        poXT_YHXXBB = (PoXT_YHXXB) objXtRtn[10];

        VoXtzsbbxx voXtzsbbxx = new VoXtzsbbxx(voXTZSBBB, poXT_ZSBBMBXXB,
                                               poXT_YHXXBA, poXT_YHXXBB);
        lstXtRtn.add(voXtzsbbxx);
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
   *查询制试报表(不要模板)
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtzsbbnotxx(VarVO vvHqlQryXtbb) throws ServiceException,
      DAOException {
    //zsbbmbid,zsbblb,bbmbmc,bbmb,jlsj,scrid,xgsj,xgrid;
    int nPageOffset = PublicConstant.XT_PAGE; //分页
    int nPageSize = PublicConstant.XT_PAGESIZE;
    //获取参数信息
    String sZsbbid = vvHqlQryXtbb.getVarValue("zsbbid");
    String sZsbbmbid = vvHqlQryXtbb.getVarValue("zsbbmbid");
    String sBbmc = vvHqlQryXtbb.getVarValue("bbmc");
    //String sBBmb = vvHqlQryXtbb.getVarValue("bbmb");
    String sJlsj = vvHqlQryXtbb.getVarValue("jlsj");
    String sScrid = vvHqlQryXtbb.getVarValue("scrid");
    String sXgsj = vvHqlQryXtbb.getVarValue("xgsj");
    String sXgrid = vvHqlQryXtbb.getVarValue("xgrid");
    String sQssj = vvHqlQryXtbb.getVarValue("qssj");
    String sJssj = vvHqlQryXtbb.getVarValue("jssj");
    String sZsbblb = vvHqlQryXtbb.getVarValue("zsbblb");
    String sYhid = vvHqlQryXtbb.getVarValue("yhid");
    //判断用户是否有角色是超级用户
    List lstXtyhjs = null;
    boolean bCjyh = false;
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        " select XT_YHJSXXB from PoXT_YHJSXXB as XT_YHJSXXB,PoXT_JSXXB as XT_JSXXB")
        .append(" where XT_YHJSXXB.jsid =XT_JSXXB.jsid and XT_JSXXB.jsmc = '")
        .append(PublicConstant.XT_CJYHMC).append("'")
        .append(" and XT_YHJSXXB.yhid = ").append(sYhid);
    try {
      PojoInfo Xt_yhjsxxbDao = DAOFactory.createXT_YHJSXXBDAO();
      lstXtyhjs = super.findAllByHQL(strBufHql.toString());
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

    strBufHql.setLength(0);
    strBufHql.append(
        "select distinct XT_ZSBBXXB.zsbbid,XT_ZSBBXXB.zsbbmbid,XT_ZSBBXXB.bbmc,")
        .append(
        " XT_ZSBBXXB.jlsj,XT_ZSBBXXB.scrid,XT_ZSBBXXB.xgsj,XT_ZSBBXXB.xgrid,")
        .append(" XT_ZSBBMBXXB.zsbblb,XT_ZSBBMBXXB.bbmbmc,")
        .append(" XT_YHXXBA,XT_YHXXBB form PoXT_ZSBBXXB as XT_ZSBBXXB,")
        .append(" PoXT_JSZSBBQXB as XT_JSZSBBQXB,PoXT_YHJSXXB as XT_YHJSXXB,")
        .append(
        "PoXT_ZSBBMBXXB as XT_ZSBBMBXXB,PoXT_YHXXB as XT_YHXXBA,PoXT_YHXXB as XT_YHXXBB")
        .append(" where XT_ZSBBXXB.zsbbmbid =  XT_ZSBBMBXXB.zsbbmbid")
        .append(" and XT_ZSBBXXB.scrid =  XT_YHXXBA.yhid")
        .append(" and  XT_ZSBBXXB.xgrid =  XT_YHXXBB.yhid(+)")
        .append(" and XT_ZSBBMBXXB.zsbbmbid =  XT_JSZSBBQXB.zsbbmbid(+)")
        .append(" and XT_JSZSBBQXB.jsid =  XT_YHJSXXB.jsid(+)");
    if ( (sZsbbid != null) && ! ("".equals(sZsbbid))) {
      strBufHql.append(" and  XT_ZSBBXXB.zsbbid =").append(sZsbbid);
    }
    if ( (sZsbbmbid != null) && ! ("".equals(sZsbbmbid))) {
      strBufHql.append(" and  XT_ZSBBXXB.zsbbmbid =").append(sZsbbmbid);
    }
    if ( (sQssj != null) && ! ("".equals(sQssj))) {
      strBufHql.append(" and  substr(XT_ZSBBXXB.jlsj,1,8) >='").append(sQssj).
          append("'");
    }
    if ( (sJssj != null) && ! ("".equals(sJssj))) {
      strBufHql.append(" and  substr(XT_ZSBBXXB.jlsj,1,8) <='").append(sJssj).
          append("'");
    }
    if ( (sZsbblb != null) && ! ("".equals(sZsbblb))) {
      strBufHql.append(" and  XT_ZSBBMBXXB.zsbblb='").append(sZsbblb).append(
          "'");
    }
    if (!bCjyh) {
      strBufHql.append(" and ( XT_YHJSXXB.yhid =").append(sYhid).append(
          " or XT_ZSBBXXB.scrid=")
          .append(sYhid).append(")");
    }
    strBufHql.append(" order by XT_ZSBBXXB.zsbbid,XT_ZSBBXXB.bbmc");
    return getXtzsbbnotxx(strBufHql.toString(), nPageOffset, nPageSize);
  }

  /**
   *查询制试报表
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtzsbbxx(VarVO vvHqlQryXtbb) throws ServiceException,
      DAOException {
    //获取参数信息
    //zsbbmbid,zsbblb,bbmc,bbmb,jlsj,scrid,xgsj,xgrid,bbzt;
    int nPageOffset = PublicConstant.XT_PAGE; //分页
    int nPageSize = PublicConstant.XT_PAGESIZE;
    //获取参数信息
    String sZsbbid = vvHqlQryXtbb.getVarValue("zsbbid");
    String sZsbbmbid = vvHqlQryXtbb.getVarValue("zsbbmbid");
    String sQssj = vvHqlQryXtbb.getVarValue("qssj");
    String sJssj = vvHqlQryXtbb.getVarValue("jssj");
    String sZsbblb = vvHqlQryXtbb.getVarValue("zsbblb");
    String sYhid = vvHqlQryXtbb.getVarValue("yhid");
    //判断用户是否有角色是超级用户
    List lstXtyhjs = null;
    boolean bCjyh = false;

    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        " select XT_YHJSXXB from PoXT_YHJSXXB as XT_YHJSXXB,PoXT_JSXXB as XT_JSXXB")
        .append(" where XT_YHJSXXB.jsid =XT_JSXXB.jsid and XT_JSXXB.jsmc = '")
        .append(PublicConstant.XT_CJYHMC).append("'")
        .append(" and XT_YHJSXXB.yhid = ").append(sYhid);
    try {
      PojoInfo Xt_yhjsxxbDao = DAOFactory.createXT_YHJSXXBDAO();
      lstXtyhjs = super.findAllByHQL(strBufHql.toString());
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
    strBufHql.setLength(0);
    strBufHql.append("select XT_ZSBBXXB,XT_ZSBBMBXXB,XT_YHXXBA,XT_YHXXBB form PoXT_ZSBBXXB as XT_ZSBBXXB,")
//    sHql = sHql + " PoXT_JSZSBBQXB as XT_JSZSBBQXB,PoXT_YHJSXXB as XT_YHJSXXB,";
        .append("PoXT_ZSBBMBXXB as XT_ZSBBMBXXB,PoXT_YHXXB as XT_YHXXBA, ")
        .append("PoXT_YHXXB as XT_YHXXBB ")
        .append(" where  XT_ZSBBXXB.zsbbmbid = XT_ZSBBMBXXB.zsbbmbid ")
        .append(" and  XT_ZSBBXXB.scrid = XT_YHXXBA.yhid")
        .append(" and  XT_ZSBBXXB.xgrid = XT_YHXXBB.yhid(+)");
//    sHql = sHql + " and XT_ZSBBMBXXB.zsbbmbid =  XT_JSZSBBQXB.zsbbmbid(+)";
//    sHql = sHql + " and XT_JSYWBBQXB.jsid =  XT_YHJSXXB.jsid(+)";
    if ( (sZsbbid != null) && ! ("".equals(sZsbbid))) {
      strBufHql.append(" and  XT_ZSBBXXB.zsbbid =").append(sZsbbid);
    }
    if ( (sZsbbmbid != null) && ! ("".equals(sZsbbmbid))) {
      strBufHql.append(" and  XT_ZSBBXXB.zsbbmbid =").append(sZsbbmbid);
    }
    if ( (sQssj != null) && ! ("".equals(sQssj))) {
      strBufHql.append(" and  substr(XT_ZSBBXXB.jlsj,1,8) >='").append(sQssj).
          append("'");
    }
    if ( (sJssj != null) && ! ("".equals(sJssj))) {
      strBufHql.append(" and  substr(XT_ZSBBXXB.jlsj,1,8) <='").append(sJssj).
          append("'");
    }
    if ( (sZsbblb != null) && ! ("".equals(sZsbblb))) {
      strBufHql.append(" and  XT_ZSBBMBXXB.zsbblb='").append(sZsbblb).append(
          "'");
    }
    //   if (!bCjyh) {
//      sHql = sHql + " and ( XT_YHJSXXB.yhid =" + sYhid + " or XT_ZSBBXXB.scrid="+ sYhid + ")";
    //   }
    strBufHql.append(" order by XT_ZSBBXXB.zsbbmbid,XT_ZSBBXXB.bbmc");
    return getXtzsbbxx(strBufHql.toString(), nPageOffset, nPageSize);
  }

  /**
   *增加制试报表信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtzsbbxx(VarVO vvXTbbB) throws ServiceException, DAOException {
    ArrayList lstXtbb = new ArrayList();
    VoXT_ZSBBXXB voXtzsbbb = new VoXT_ZSBBXXB();
    PoXT_ZSBBXXB rtnpoXTZSBBB = new PoXT_ZSBBXXB();
    //ywbbid,ywbblb,bbmc,bbmb,jlsj,scrid,xgsj,xgrid,bbzt;
    try {
      //执行HQL语句
      BeanUtils.populate(voXtzsbbb, vvXTbbB.getVarmap());
      voXtzsbbb.setZsbbid( (Long) Xt_zsbbxxbDao.getId());
      voXtzsbbb.setJlsj(StringUtils.getServiceTime());
      voXtzsbbb.setScrid(this.getUserInfo().getYhid());
      voXtzsbbb.setBbmc(vvXTbbB.getVarValue("bbmc"));
      voXtzsbbb.setZsbbmbid(Long.valueOf(vvXTbbB.getVarValue("zsbbmbid")));
      //voXtzsbbb.setBbzt(PublicConstant.XT_BZ_QY);
      rtnpoXTZSBBB = (PoXT_ZSBBXXB)super.create(voXtzsbbb.toPoXT_ZSBBXXB());

      lstXtbb.add(rtnpoXTZSBBB);
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
    return lstXtbb;
  }

  /**
   * 修改制试报表信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtzsbbxgxx(VarVO vvXTbbB) throws ServiceException,
      DAOException {
    ArrayList lstXtbb = new ArrayList();
    PoXT_ZSBBXXB poXtzsbbb = null;
    VoXT_ZSBBXXB voXtzsbbb;
    PoXT_ZSBBXXB rtnpoXTZSBBB = new PoXT_ZSBBXXB();
    //获取参数信息,设置PO语句
    // ywbbid,ywbblb,bbmc,bbmb,jlsj,scrid,xgsj,xgrid,bbzt;
    try {
      poXtzsbbb = super.get(PoXT_ZSBBXXB.class,Long.valueOf(vvXTbbB.
          getVarValue("zsbbid")));
      if (poXtzsbbb == null) {
        return null;
      }

      //voXtzsbbb = new VoXT_ZSBBXXB(poXtzsbbb);
     // BeanUtils.populate(voXtzsbbb, vvXTbbB.getVarmap());
      poXtzsbbb.setXgsj(StringUtils.getServiceTime());
      poXtzsbbb.setXgrid(this.getUserInfo().getYhid());
      poXtzsbbb.setBbmc(vvXTbbB.getVarValue("bbmc"));
      //poXtzsbbb.setBbzt(PublicConstant.XT_BZ_QY);
      //执行
      rtnpoXTZSBBB = (PoXT_ZSBBXXB)super.update(poXtzsbbb);

      lstXtbb.add(rtnpoXTZSBBB);
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
    return lstXtbb;

  }

  /**
   *删除制试报表信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtzsbbscxx(VarVO vvXTbbB) throws ServiceException, DAOException {
    PoXT_ZSBBXXB rtnpoXTZSBBB = new PoXT_ZSBBXXB();
    PoXT_ZSBBXXB poXTZSBBB = new PoXT_ZSBBXXB();
    List lstXtzsbbsc = new ArrayList();
    int nXtRtn = PublicConstant.XT_BZ_ERROR;
    //组合poXT_XTsplB
    //获取参数信息
    String sZsbbmbid = vvXTbbB.getVarValue("zsbbmbid");
    String sZsbbid = vvXTbbB.getVarValue("zsbbid");
    try {
      StringBuffer strBufHql = new StringBuffer();
      strBufHql.append(" from PoXT_ZSBBXXB where 1=1 ");

      if ( (sZsbbmbid != null) && ! ("".equals(sZsbbmbid))) {
        strBufHql.append(" and  zsbbmbid =").append(sZsbbmbid);
      }
      if ( (sZsbbid != null) && ! ("".equals(sZsbbid))) {
        strBufHql.append(" and  zsbbid =").append(sZsbbid);
      }
      nXtRtn = super.deleteAll(super.findAllByHQL(strBufHql.toString()));
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
    return nXtRtn;
  }
}
