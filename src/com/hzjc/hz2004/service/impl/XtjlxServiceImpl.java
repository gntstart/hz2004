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
import com.hzjc.hz2004.appbase.*;
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
public class XtjlxServiceImpl
    extends XtBaseService

    implements XtjlxService {

  PojoInfo   Xt_jlxbDao = DAOFactory.createXT_JLXXXBDAO();
  PojoInfo   Xt_XtrzbDao = DAOFactory.createXT_XTRZBDAO();
  /**
   *查询信息
   * @param pxQryXtcs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtjlxxx(String sHqlXtjlx) throws ServiceException,
      DAOException {
    //执行HQL语句
    return getXtjlxxx(sHqlXtjlx, PublicConstant.XT_PAGE,
                      PublicConstant.XT_PAGESIZE);

  }

  public List getXtjlxxx(String sHqlXtjlx, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    //执行HQL语句
    List lstXtRtn = null;
    List lstReturn = new ArrayList();
    PoXT_XZQHB poXT_XZQHB = null;
    PoXT_JLXXXB poXT_JLXXXB = null;
    PoXT_XTCSB poXT_XTCSB = null;
    Object[] objXtRtn = null;
    //执行HQL语句
    try {
      lstXtRtn =super.getPageRecords(sHqlXtjlx, new Object[]{}, nPageOffset,
                                                  nPageSize).getList();
      for (int i = 0; i < lstXtRtn.size(); i++) {
        objXtRtn = (Object[]) lstXtRtn.get(i);
        poXT_JLXXXB = (PoXT_JLXXXB) objXtRtn[0];
        poXT_XZQHB = (PoXT_XZQHB) objXtRtn[1];
        poXT_XTCSB = (PoXT_XTCSB) objXtRtn[2];
        VoXtjlx voXtjlx = new VoXtjlx(poXT_JLXXXB, poXT_XZQHB, poXT_XTCSB);
        lstReturn.add(voXtjlx);
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
  public VoQueryResult getXtjlxxx(VarVO vvQryXtjlx, String psWhere,
                                  VoPage voPage) throws ServiceException,
      DAOException {
    VoQueryResult voQueryResult = new VoQueryResult();

    //获取参数信息
    //dm,mc,zwpy,wbpy,qhdm,bz,qybz,bdlx,bdsj;
    String sDm = vvQryXtjlx.getVarValue("jlxdm");
    String sQhdm = vvQryXtjlx.getVarValue("qhdm");
    String sBz = vvQryXtjlx.getVarValue("bz");
    String sQybz = vvQryXtjlx.getVarValue("qybz");
    String sMc = vvQryXtjlx.getVarValue("mc");
    String sZwpy = vvQryXtjlx.getVarValue("zwpy");
    String sWbpy = vvQryXtjlx.getVarValue("wbpy");

    //HQL条件语句
    StringBuffer strBufHql = new StringBuffer();
    StringBuffer strCountHql = new StringBuffer();
    StringBuffer strHql = new StringBuffer();
    strBufHql.append(
        "select XT_JLXXXB,XT_XZQHB,XT_XTCSB from PoXT_JLXXXB as XT_JLXXXB,");//FROM 改为from
    strCountHql.append(" select count(*) from PoXT_JLXXXB as XT_JLXXXB,");
    strHql.append(" PoXT_XZQHB as XT_XZQHB,PoXT_XTCSB as XT_XTCSB ")
        .append(
        " where XT_JLXXXB.qybz = XT_XTCSB.dm and XT_XTCSB.cslb = '9002'")//" where XT_JLXXXB.qybz = XT_XTCSB.dm(+) and XT_XTCSB.cslb(+) = '9002'")
        .append(" and  XT_JLXXXB.qhdm = XT_XZQHB.dm ");

    if ( (sDm != null) && ! ("".equals(sDm))) {
      strHql.append(" and  XT_JLXXXB.dm ='").append(sDm).append("'");
    }
    if ( (sMc != null) && ! ("".equals(sMc))) {
      strHql.append(" and  XT_JLXXXB.mc ='").append(sMc).append("'");
    }
    if ( (sZwpy != null) && ! ("".equals(sZwpy))) {
      strHql.append(" and  XT_JLXXXB.zwpy ='").append(sZwpy).append("'");
    }
    if ( (sWbpy != null) && ! ("".equals(sWbpy))) {
      strHql.append(" and  XT_JLXXXB.wbpy ='").append(sWbpy).append("'");
    }
    if ( (sQhdm != null) && ! ("".equals(sQhdm))) {
      strHql.append(" and  XT_JLXXXB.qhdm ='").append(sQhdm).append("'");
    }
    if ( (sQybz != null) && ! ("".equals(sQybz))) {
      strHql.append(" and  XT_JLXXXB.qybz ='").append(sQybz).append("'");
    }
    if (psWhere != null && !"".equals(psWhere)) {
      strHql.append(" and (" + psWhere + ")");
    }
    strHql.append(" order by XT_JLXXXB.qhdm,XT_JLXXXB.dm");
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
    voQueryResult.setPagelist(getXtjlxxx(strBufHql.toString(),
                                         voPage.getPageindex(),
                                         voPage.getPagesize()));
    voQueryResult.setVotype(VoXtjlx.class);
    return voQueryResult;
  }

  /**
   *增加信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjlxxx(VarVO vvXtjlxb) throws ServiceException, DAOException {
    ArrayList lstXtjlx = new ArrayList();
    PoXT_JLXXXB poXTjlxB = new PoXT_JLXXXB();
    PoXT_JLXXXB rtnpoXTjlxB = new PoXT_JLXXXB();
    String sDm = "", sSelectDm = "", sHql = "";
    List lstXtDm;
    boolean bIsTrue = false;
    long nDm;
    //获取参数信息
    //dm,mc,zwpy,wbpy,qhdm,bz,qybz,bdlx,bdsj
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
      sDm = vvXtjlxb.getVarValue("dm").trim();
      String sQhdm = vvXtjlxb.getVarValue("qhdm").trim();
      //如果代码为空则从数据库中取出最大代码
      if ("".equals(sDm) || sDm == null) {
        sHql = "select max(XT_JLXXXB.dm) FROM PoXT_JLXXXB as XT_JLXXXB" +
            " where substr(dm,0,6) = " +
            sQhdm;//胡斌：获取最大代码
            //this.getUserInfo().getDwdm().substring(0, 6);
        lstXtDm =super.findAllByHQL(sHql);
        if (lstXtDm != null && lstXtDm.size() > 0) {
          sSelectDm = (String) lstXtDm.get(0);
          if (sSelectDm == null || "".equals(sSelectDm)) {
            //胡斌 20060601 如果该区县未设置过街路巷，则取一个最小值：区县代码+000000
            sSelectDm = sQhdm + "000000";
          }
        }
        else {
          sSelectDm = sQhdm + "000000";
        }
        nDm = Long.valueOf(sSelectDm).longValue() + 1;
        sDm = String.valueOf(nDm);
      }
      else if (sDm.length() != 12) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                   "街路巷代码输入错误(长度不是12)", null);
      }
      if (sQhdm.length() != 6) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                   "区划代码输入错误(长度不是6)", null);
      }
      if (!sDm.substring(0, 4).equals(sQhdm.substring(0, 4))) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                   "街路巷代码与区划代码不一致", null);
      }

      if (!bCjyh) {
        //modi by hh 2005-06-29 根据数据范围判断是否可以修改
       List lstSjfw = XtywqxServiceImpl.SelectDataRange(String.valueOf(this.
           getUserInfo().getYhid()), PublicConstant.GNBH_XT_JLXXG);
       if(lstSjfw==null || lstSjfw.size()<=0){
         throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                    "无此数据范围！", null);
       }
       for(int i=0;i<lstSjfw.size();i++){
         if (sDm.substring(0,
                           6).equals( ( (VoXtsjfw) lstSjfw.get(i)).getSjfw().
                                     substring(0,
                                               6))) {
           bIsTrue = true;
           break;
         }
       }
         if (!bIsTrue) {
           throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                      "无权修改非本区划内街路巷！", null);
         }

       /*       if (!sDm.substring(0,
                           6).equals(this.getUserInfo().getDwdm().substring(0,
            6))) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                     "无权增加非本区划内街路巷！", null);
        }
*/
        }

      BeanUtils.populate(poXTjlxB, vvXtjlxb.getVarmap());
      poXTjlxB.setDm(sDm);
      poXTjlxB.setQybz(PublicConstant.XT_BZ_QY); //启用标志
      poXTjlxB.setBdlx(PublicConstant.XT_BDLX_ADD); //变动类型
      poXTjlxB.setBdsj(StringUtils.getServiceTime());
      rtnpoXTjlxB =(PoXT_JLXXXB)super.create(poXTjlxB);
      lstXtjlx.add(rtnpoXTjlxB);
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
    return lstXtjlx;
  }

  /**
   * 修改信息
   * @param voXtgbcsb
   * @param atUser
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjlxxgxx(VarVO vvXtjlxb) throws ServiceException,
      DAOException {
    ArrayList lstXtjlx = new ArrayList();
    PoXT_JLXXXB poXTJLXB = null;
    PoXT_JLXXXB rtnpoXTJLXB = new PoXT_JLXXXB();
    String sDm = vvXtjlxb.getVarValue("dm");
    boolean bIsTrue =false;
    //dm,mc,zwpy,wbpy,qhdm,bz,qybz,bdlx,bdsj;
    try {
      if (!this.VerifyYH(String.valueOf(this.getUserInfo().getYhid()))) {
        //modi by hh 2005-06-29 根据数据范围判断是否可以修改
        List lstSjfw = XtywqxServiceImpl.SelectDataRange(String.valueOf(this.
            getUserInfo().getYhid()), PublicConstant.GNBH_XT_JLXXG);
        if(lstSjfw==null || lstSjfw.size()<=0){
          throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                     "无此数据范围！", null);
        }
        for(int i=0;i<lstSjfw.size();i++){
          if (sDm.substring(0,
                            6).equals( ( (VoXtsjfw) lstSjfw.get(i)).getSjfw().
                                      substring(0,
                                                6))) {
            bIsTrue = true;
            break;
          }
        }
       if(!bIsTrue){
         throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                     "无权修改非本区划内街路巷！", null);
       }

        /*   if (!sDm.substring(0,
                           6).equals(this.getUserInfo().getDwdm().substring(0,
            6))) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                     "无权修改非本区划内街路巷！", null);
                 }
         */
      }

      //System.out.println(vvXtjlxb.getVarValue("dm"));
      poXTJLXB  = super.get(PoXT_JLXXXB.class,sDm);
      if (poXTJLXB == null) {
        return null;
      }
/*      if (!this.VerifyYH(String.valueOf(this.getUserInfo().getYhid()))) {
        if (!sDm.substring(0,
                           6).equals(this.getUserInfo().getDwdm().substring(0,
            6))) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                     "无权增加非本区划内街路巷！", null);
        }
      }
*/
      BeanUtils.populate(poXTJLXB, vvXtjlxb.getVarmap());
      poXTJLXB.setBdlx(PublicConstant.XT_BDLX_UPDATE); //变动类型
      poXTJLXB.setBdsj(StringUtils.getServiceTime());
      rtnpoXTJLXB =      (PoXT_JLXXXB)super.update(poXTJLXB);
      lstXtjlx.add(rtnpoXTJLXB);
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
    return lstXtjlx;
  }

  /**
   * 删除信息
   * @param voXtxzqhb
   * @param atUser
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjlxscxx(VarVO vvXtjlxb) throws ServiceException,
      DAOException {
    PoXT_JLXXXB poXTjlxB = null;
    PoXT_JLXXXB rtnpoXTjlxB = new PoXT_JLXXXB();
    ArrayList lstXtjlx = new ArrayList();
    String sDm = vvXtjlxb.getVarValue("dm");
    //执行HQL语句
    try {
      if (!this.VerifyYH(String.valueOf(this.getUserInfo().getYhid()))) {
        if (!sDm.substring(0,
                           6).equals(this.getUserInfo().getDwdm().substring(0,
            6))) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                     "无权删除非本区划内街路巷！", null);
        }
      }
      poXTjlxB  = super.get(PoXT_JLXXXB.class,vvXtjlxb.getVarValue(
          "dm"));
      if (poXTjlxB == null) {
        return null;
      }
      poXTjlxB.setQybz(PublicConstant.XT_BZ_BQY); //启用标志
      poXTjlxB.setBdlx(PublicConstant.XT_BDLX_DELETE); //变动类型
      poXTjlxB.setBdsj(StringUtils.getServiceTime());
      rtnpoXTjlxB =  (PoXT_JLXXXB)super.update(poXTjlxB);
      lstXtjlx.add(rtnpoXTjlxB);
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
    return lstXtjlx;
  }

  /**
   * latest change date
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtjlxzdbdsj() throws ServiceException, DAOException {
    //执行HQL语句
    List lstXtRtn = null;
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(" select XT_JLXXXB FROM PoXT_JLXXXB as XT_JLXXXB where ")
        .append(
        " bdsj in (select max(XT_JLXXXB.bdsj) FROM PoXT_JLXXXB as XT_JLXXXB )");
    try {
      lstXtRtn =super.getPageRecords(strBufHql.toString(),new Object[]{},
                                                  PublicConstant.XT_PAGE,
                                                  1).getList();
      if (lstXtRtn == null || lstXtRtn.isEmpty()) {
        String sHql =
            "select XT_JLXXXB FROM PoXT_JLXXXB as XT_JLXXXB";
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
