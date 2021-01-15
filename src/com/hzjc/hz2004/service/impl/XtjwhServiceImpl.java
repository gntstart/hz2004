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
 *                 居委会操作实现</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */
@Service
public class XtjwhServiceImpl
    extends XtBaseService

    implements XtjwhService {

  PojoInfo   Xt_jwhbDao = DAOFactory.createXT_JWHXXBDAO();
  PojoInfo   Xt_XtrzbDao = DAOFactory.createXT_XTRZBDAO();
  /**
   *查询信息
   * @param pxQryXtcs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtjwhxx(String sHqlXtjwh) throws ServiceException,
      DAOException {
    //执行HQL语句
    return getXtjwhxx(sHqlXtjwh, PublicConstant.XT_PAGE,
                      PublicConstant.XT_PAGESIZE);
  }

  public List getXtjwhxx(String sHqlXtjwh, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    //执行HQL语句
    List lstXtRtn = null;
    List lstReturn = new ArrayList();
    PoXT_QYSZB poXT_QYSZB = null;
    PoXT_JWHXXB poXT_JWHXXB = null;
    PoXT_XZJDXXB poXT_XZJDXXB = null;
    PoXT_DWXXB poXT_DWXXB = null;
    PoXT_XTCSB poXT_XTCSB = null;
    Object[] objXtRtn = null;
    //执行HQL语句
    try {
      lstXtRtn =super.getPageRecords(sHqlXtjwh, new Object[]{}, nPageOffset,
                                                  nPageSize).getList();
      for (int i = 0; i < lstXtRtn.size(); i++) {
        objXtRtn = (Object[]) lstXtRtn.get(i);
        poXT_JWHXXB = (PoXT_JWHXXB) objXtRtn[0];
        poXT_DWXXB = (PoXT_DWXXB) objXtRtn[1];
        poXT_XZJDXXB = (PoXT_XZJDXXB) objXtRtn[2];
        poXT_XTCSB = (PoXT_XTCSB) objXtRtn[3];
        VoXtjwhxx voXtjwhxx = new VoXtjwhxx(poXT_JWHXXB, poXT_DWXXB,
                                            poXT_XZJDXXB,
                                            poXT_XTCSB);
        lstReturn.add(voXtjwhxx);
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
  public VoQueryResult getXtjwhxx(VarVO vvQryXtjwh, String psWhere,
                                  VoPage voPage) throws ServiceException,
      DAOException {
    VoQueryResult voQueryResult = new VoQueryResult();
    //获取参数信息
    //dm,mc,zwpy,wbpy,dwdm,xzjddm,bz,qybz,bdlx,bdsj;
    String sDm = vvQryXtjwh.getVarValue("dm");
    String sDwdm = vvQryXtjwh.getVarValue("dwdm");
    String sBz = vvQryXtjwh.getVarValue("bz");
    String sQybz = vvQryXtjwh.getVarValue("qybz");
    String sMc = vvQryXtjwh.getVarValue("mc");
    String sZwpy = vvQryXtjwh.getVarValue("zwpy");
    String sWbpy = vvQryXtjwh.getVarValue("wbpy");
    String sXzjddm = vvQryXtjwh.getVarValue("xzjddm");
    String sMb = vvQryXtjwh.getVarValue("mb");
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
        "select XT_JWHXXB,XT_DWXXB,XT_XZJDXXB,XT_XTCSB from PoXT_JWHXXB as XT_JWHXXB,");//FROM 改为小写
    strCountHql.append(" select count(*) from PoXT_JWHXXB as XT_JWHXXB,");
    strHql.append(" PoXT_DWXXB as XT_DWXXB,PoXT_XZJDXXB as XT_XZJDXXB,PoXT_XTCSB as XT_XTCSB where  ")
        .append(
        " XT_JWHXXB.dwdm = XT_DWXXB.dm and XT_JWHXXB.xzjddm = XT_XZJDXXB.dm")
        .append(
        " and XT_JWHXXB.qybz = XT_XTCSB.dm and XT_XTCSB.cslb = '9002'");//" and XT_JWHXXB.qybz = XT_XTCSB.dm(+) and XT_XTCSB.cslb(+) = '9002'");
    if ( (sDm != null) && ! ("".equals(sDm))) {
      strHql.append(" and  XT_JWHXXB.dm ='").append(sDm).append("'");
    }
    if ( (sMc != null) && ! ("".equals(sMc))) {
      strHql.append(" and  XT_JWHXXB.mc ='").append(sMc).append("'");
    }
    if ( (sZwpy != null) && ! ("".equals(sZwpy))) {
      strHql.append(" and  XT_JWHXXB.zwpy ='").append(sZwpy).append("'");
    }
    if ( (sWbpy != null) && ! ("".equals(sWbpy))) {
      strHql.append(" and  XT_JWHXXB.wbpy ='").append(sWbpy).append("'");
    }
    if ( (sDwdm != null) && ! ("".equals(sDwdm))) {
      strHql.append(" and  XT_JWHXXB.dwdm ='").append(sDwdm).append("'");
    }
    if ( (sXzjddm != null) && ! ("".equals(sXzjddm))) {
      strHql.append(" and  XT_JWHXXB.xzjddm ='").append(sXzjddm).append("'");
    }
    if ( (sBz != null) && ! ("".equals(sBz))) {
      strHql.append(" and  XT_JWHXXB.bz ='").append(sBz).append("'");
    }
    if ( (sQybz != null) && ! ("".equals(sQybz))) {
      strHql.append(" and  XT_JWHXXB.qybz ='").append(sQybz).append("'");
    }

    if (!bCjyh && !"mb".equalsIgnoreCase(sMb)) {
      strHql.append(" and  substr(XT_JWHXXB.dm,0,6) =substr(").append(this.
          getUserInfo().
          getDwdm()).append(",0,6)");
    }
    if (psWhere != null && !"".equals(psWhere)) {
      strHql.append(" and (" + psWhere + ")");
    }
    strHql.append(" order by XT_JWHXXB.dwdm,XT_JWHXXB.xzjddm,XT_JWHXXB.dm");
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
    voQueryResult.setPagelist(getXtjwhxx(strBufHql.toString(),
                                              voPage.getPageindex(),
                                              voPage.getPagesize()));
    voQueryResult.setVotype(VoXtjwhxx.class);
    return voQueryResult;
  }

  /**
   *增加信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjwhxx(VarVO vvXtjwhb) throws ServiceException, DAOException {
    ArrayList lstXtjwh = new ArrayList();
    PoXT_JWHXXB poXTjwhB = new PoXT_JWHXXB();
    PoXT_JWHXXB rtnpoXTjwhB = new PoXT_JWHXXB();
    List lstXtxzjdb;
    String sDm = "", sXzjddm = "";
    String sHql = "";
    String sYhdw = this.getUserInfo().getDwdm();
    boolean bCjyh = false;
    //获取参数信息
    //dm,mc,zwpy,wbpy,dwdm,xzjddm,bz,qybz,bdlx,bdsj;
    try {
      //判断单位代码是否9位
      sDm = vvXtjwhb.getVarValue("dm");
      if (sDm.length() != 15) {
        ;//throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,"居委会代码输入错误(长度不是15)", null);
      }
      //判断单位代码区划代码是否一致
      sXzjddm = vvXtjwhb.getVarValue("xzjddm");
      //if(sXzjddm.length()<=8){
      //  throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
       //                            "乡镇街道代码长度不能少于9位！", null);
     // }

      if (!sDm.substring(0, 4).equals(sXzjddm.substring(0,4))) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                   "居委会代码与乡镇街道代码不一致", null);
      }
      //乡镇街道代码是否存在乡镇街道代码表中
      sHql = "select XT_XZJDXXB FROM PoXT_XZJDXXB as XT_XZJDXXB where dm='" +
          sXzjddm + "'";
      PojoInfo  Xt_xzjdxxbDao = DAOFactory.createXT_XZJDXXBDAO();
      lstXtxzjdb =super.findAllByHQL(sHql);
      if ( (lstXtxzjdb == null) || (lstXtxzjdb.isEmpty())) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, "乡镇街道代码输入错误", null);
      }

      PoXT_XZJDXXB xzjd = (PoXT_XZJDXXB)lstXtxzjdb.get(0);
      String xdm_xzjd = xzjd.getXdm();
      String xdm_jwh = vvXtjwhb.getVarValue("xdm");
      if(xdm_xzjd.length()<9)
        throw new Exception("乡镇街道新代码错误，不能少于9位！");

      if(xdm_jwh.length()<9)
        throw new Exception("居委会新代码错误，不能少于9位！");

      if(!xdm_xzjd.substring(0,9).equals(xdm_jwh.substring(0,9)))
        throw new Exception("居委会新代码和乡镇街道不匹配！");

      //如果不是超级用户，则判断所属单位是否有权限
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
      //判断单位代码区划代码是否一致

      if (!bCjyh) {
        if (!sYhdw.substring(0, 6).equals(sXzjddm.substring(0, 6))) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                     "无权增加非本区划内的居委会！！", null);
        }
      }

      BeanUtils.populate(poXTjwhB, vvXtjwhb.getVarmap());
      poXTjwhB.setQybz(PublicConstant.XT_BZ_QY); //启用标志
      poXTjwhB.setBdlx(PublicConstant.XT_BDLX_ADD); //变动类型
      poXTjwhB.setBdsj(StringUtils.getServiceTime());

      rtnpoXTjwhB =(PoXT_JWHXXB)super.create(poXTjwhB);
      lstXtjwh.add(rtnpoXTjwhB);
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
    return lstXtjwh;
  }

  /**
   * 修改信息
   * @param voXtgbcsb
   * @param atUser
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjwhxgxx(VarVO vvtjwhb) throws ServiceException, DAOException {
    ArrayList lstXtjwh = new ArrayList();
    PoXT_JWHXXB poXTJWHB = null;
    PoXT_JWHXXB rtnpoXTJWHB = new PoXT_JWHXXB();
    //dm,mc,zwpy,wbpy,dwdm,xzjddm,bz,qybz,bdlx,bdsj;
    try {
      poXTJWHB  = super.get(PoXT_JWHXXB.class,vvtjwhb.getVarValue(
          "dm"));
      if (poXTJWHB == null) {
        return null;
      }
      String oldcxfldm = poXTJWHB.getCxfldm(); //原城乡分类代码
      String olddw = poXTJWHB.getDwdm();

      BeanUtils.populate(poXTJWHB, vvtjwhb.getVarmap());

      poXTJWHB.setBdlx(PublicConstant.XT_BDLX_UPDATE); //变动类型
      poXTJWHB.setBdsj(StringUtils.getServiceTime());
      String newcxfldm = poXTJWHB.getCxfldm();//新城乡分类代码
      String newdw = poXTJWHB.getDwdm();

      rtnpoXTJWHB =  (PoXT_JWHXXB)super.update(poXTJWHB);

      //比较新代码
      String xdm_jwh = rtnpoXTJWHB.getXdm();
      if(xdm_jwh.length()<9)
        throw new Exception("居委会新代码不能少于9位！");
      String sHql = "select XT_XZJDXXB FROM PoXT_XZJDXXB as XT_XZJDXXB where xdm='" +
          xdm_jwh.substring(0,9) + "'";
      PojoInfo  Xt_xzjdxxbDao = DAOFactory.createXT_XZJDXXBDAO();
      List lstXtxzjdb =super.findAllByHQL(sHql);
      if ( (lstXtxzjdb == null) || (lstXtxzjdb.isEmpty())) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, "居委会新代码错误，和乡镇街道不匹配！", null);
      }

      if(StringUtils.isEmpty(newcxfldm))
        throw new Exception("居委会必须指定城乡属性！");

      if(oldcxfldm==null) oldcxfldm = "";

      if(!oldcxfldm.equals(newcxfldm)){
        throw new Exception("不能修改城乡属性，请新增居委会并进行区划调整！");
      }

      if(oldcxfldm!=null && oldcxfldm.startsWith("2") && newcxfldm.startsWith("1")){
        //从乡镇改变为城镇，那么记录城乡变更
        PojoInfo  hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
        PojoInfo  hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();
        List list =super.findAllByHQL("select count(a.jcwh)" +
            " from PoHJXX_CZRKJBXXB a where a.jcwh='" + poXTJWHB.getDm() + "' and a.jlbz='1' and a.ryzt='0' and a.cxbz='0'");

        if(list!=null && list.size()>0){
          if(list.get(0)!=null){
            Long sl = Long.valueOf(list.get(0).toString());
            //保持城乡变动历史
            //2015-12-13刘抢阳修改，冗余城乡属性
            PoHJXX_CXSXBGB log = new PoHJXX_CXSXBGB();
            log.setBghcxsx(newcxfldm);
            log.setBgqcxsx(oldcxfldm);
            log.setCjsj(poXTJWHB.getBdsj());
            log.setSldw(newdw);
            log.setBgqdw(olddw);
            log.setRynbid(new Long(0));
            log.setHjywid(new Long(0));
            log.setSsxq(newdw.length()>6?newdw.substring(0,6):newdw);
            log.setJwhdm(poXTJWHB.getDm());
            log.setBgyy(null);
            log.setYwlb("cxsxbg");
            log.setBz("城乡属性变更业务");
            log.setRkbj("1");
            log.setRysl(sl);
            super.create(log);
          }
        }
        // PoHJXX_CZRKJBXXB poRyxx
      }
      lstXtjwh.add(rtnpoXTJWHB);
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
    return lstXtjwh;
  }

  /**
   * 删除信息
   * @param voXtxzqhb
   * @param atUser
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjwhscxx(VarVO vvXtjwhb) throws ServiceException,
      DAOException {
    String sHql = null;
    PoXT_JWHXXB poXTJWHB = null;
    PoXT_JWHXXB rtnpoXTJWHB = new PoXT_JWHXXB();
    ArrayList lstXtjwh = new ArrayList();
    try {
      poXTJWHB  = super.get(PoXT_JWHXXB.class,vvXtjwhb.getVarValue(
          "dm"));
      if (poXTJWHB == null) {
        return null;
      }
      poXTJWHB.setQybz(PublicConstant.XT_BZ_BQY); //启用标志
      poXTJWHB.setBdlx(PublicConstant.XT_BDLX_DELETE); //变动类型
      poXTJWHB.setBdsj(StringUtils.getServiceTime());
      rtnpoXTJWHB =     (PoXT_JWHXXB)super.update(poXTJWHB);
      lstXtjwh.add(rtnpoXTJWHB);
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
    return lstXtjwh;
  }

  /**
   * latest change date
   * @param pxQryXtcs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtjwhzdbdsj() throws ServiceException,
      DAOException {
    //执行HQL语句
    List lstXtRtn = null;
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append("select XT_JWHXXB FROM PoXT_JWHXXB as XT_JWHXXB where ")
        .append(
        " bdsj in (select max(XT_JWHXXB.bdsj) FROM PoXT_JWHXXB as XT_JWHXXB)");
    try {
      lstXtRtn =super.getPageRecords(strBufHql.toString(),new Object[]{},
                                                  PublicConstant.XT_PAGE,
                                                  1).getList();
      if (lstXtRtn == null || lstXtRtn.isEmpty()) {
        String sHql = "select XT_JWHXXB FROM PoXT_JWHXXB as XT_JWHXXB ";
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
