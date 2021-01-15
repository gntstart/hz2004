package com.hzjc.hz2004.service.impl;

import java.util.*;

import com.hzjc.hz2004.dao.*;
import com.hzjc.hz2004.po.*;
import com.hzjc.hz2004.service.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.hzjc.hz2004.vo.*;
import com.hzjc.wsstruts.exception.*;
import com.hzjc.wsstruts.type.*;
import com.hzjc.wsstruts.vo.*;
import java.lang.reflect.*;
import com.hzjc.util.StringUtils;
import com.hzjc.hz2004.base.SpringContextHolder;
import com.hzjc.hz2004.base.login.AuthToken;
import com.hzjc.hz2004.constant.*;
import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 业务权限操作实现</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */
@Service
public class XtywqxServiceImpl
    extends XtBaseService

    implements XtywqxService {

  /**
   * 字符串查询
   * @param strHQL
   * @param nPageOffset
   * @param nPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtywqxxx(String strHQL) throws ServiceException,
      DAOException {
    return getXtywqxxx(strHQL, PublicConstant.XT_PAGE,
                       PublicConstant.XT_PAGESIZE);
  }

  public List getXtywqxxx(String strHQL, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    List lstXtywqxxx = new ArrayList();
    List lstXtRtn = null;
    PoXT_YWQXKZB poXT_YWQXKZB;
    PoXT_XTCSB poXT_XTCSBA;
    PoXT_XTCSB poXT_XTCSBB;
    PoXT_XTCSB poXT_XTCSBC;
    PoXT_XTGNB poXT_XTGNB;
    Object[] objXtRtn = null;
    //执行HQL语句
    try {
      //XT_YWQXKZBDAO Xt_XtywqxbDao = DAOFactory.createXT_YWQXKZBDAO();
      lstXtRtn = super.getPageRecords(strHQL, new Object[]{}, nPageOffset, nPageSize).getList();
    		 // .findOnePageXT_YWQXKZBs(strHQL, nPageOffset,nPageSize);
      for (int i = 0; i < lstXtRtn.size(); i++) {
        objXtRtn = (Object[]) lstXtRtn.get(i);
        poXT_YWQXKZB = (PoXT_YWQXKZB) objXtRtn[0];
        poXT_XTCSBA = (PoXT_XTCSB) objXtRtn[1];
        poXT_XTCSBB = (PoXT_XTCSB) objXtRtn[2];
        poXT_XTCSBC = (PoXT_XTCSB) objXtRtn[3];
        poXT_XTGNB = (PoXT_XTGNB) objXtRtn[4];
        VoXtywqxkz voXtywqxkz = new VoXtywqxkz(poXT_YWQXKZB, poXT_XTCSBA,
                                               poXT_XTCSBB, poXT_XTCSBC,
                                               poXT_XTGNB);
        lstXtywqxxx.add(voXtywqxkz);
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
    return lstXtywqxxx;
  }

  /**
   *查询业务权限信息
   * @param iReqPage
   * @param iPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtywqxxx(VarVO vvHqlQryXtywqx) throws ServiceException,
      DAOException {
    int nPageOffset = PublicConstant.XT_PAGE; //分页
    int nPageSize = PublicConstant.XT_PAGESIZE;
    //获取参数信息
    //qxkzid,ywid,ywmc,xqlx,sjfwxz,yhdtcx;
    String sQxkzid = "", sYwid = "", sYwmc = "";
    String sXqlx = "", sSjfwxz = "", sYhdtcx = "";
    String sGnlx = vvHqlQryXtywqx.getVarValue("gnlx");
    sQxkzid = vvHqlQryXtywqx.getVarValue("qxkzid");
    sYwid = vvHqlQryXtywqx.getVarValue("ywid");
    sYwmc = vvHqlQryXtywqx.getVarValue("ywmc");
    sXqlx = vvHqlQryXtywqx.getVarValue("xqlx");
    sSjfwxz = vvHqlQryXtywqx.getVarValue("sjfwxz");
    sYhdtcx = vvHqlQryXtywqx.getVarValue("yhdtcx");

    //HQL条件语句
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append("select XT_YWQXKZB,XT_XTCSBA,XT_XTCSBB,XT_XTCSBC,XT_XTGNB")
        .append(" FROM PoXT_YWQXKZB as XT_YWQXKZB,PoXT_XTGNB as XT_XTGNB,")
        .append(" PoXT_XTCSB as XT_XTCSBA,PoXT_XTCSB as XT_XTCSBB,")
        .append(
        " PoXT_XTCSB as XT_XTCSBC where XT_YWQXKZB.xqlx = XT_XTCSBA.dm ")
        .append(" and XT_YWQXKZB.ywid  = XT_XTGNB.gnbh")
        .append(
        " and XT_XTCSBA.cslb = '9017' and XT_YWQXKZB.sjfwxz = XT_XTCSBB.dm")
        .append(
        " and XT_XTCSBB.cslb = '9018' and XT_YWQXKZB.yhdtcx = XT_XTCSBC.dm")
        .append(" and XT_XTCSBC.cslb = '9019'");
    if ( (sQxkzid != null) && ! ("".equals(sQxkzid))) {
      strBufHql.append(" and  XT_YWQXKZB.ywqxlb ='").append(sQxkzid).append("'");
    }
    if ( (sYwid != null) && ! ("".equals(sYwid))) {
      strBufHql.append(" and  XT_YWQXKZB.ywid ='").append(sYwid).append("'");
    }
    if ( (sYwmc != null) && ! ("".equals(sYwmc))) {
      strBufHql.append(" and  XT_YWQXKZB.ywmc ='").append(sYwmc).append("'");
    }
    if ( (sXqlx != null) && ! ("".equals(sXqlx))) {
      strBufHql.append(" and XT_YWQXKZB.xqlx ='").append(sXqlx).append("'");
    }
    if ( (sSjfwxz != null) && ! ("".equals(sSjfwxz))) {
      strBufHql.append(" and  XT_YWQXKZB.sjfwxz ='").append(sSjfwxz).append("'");
    }
    if ( (sYhdtcx != null) && ! ("".equals(sYhdtcx))) {
      strBufHql.append(" and  XT_YWQXKZB.yhdtcx ='").append(sYhdtcx).append("'");
    }
    if ( (sGnlx != null) && ! ("".equals(sGnlx))) {
      strBufHql.append(" and  XT_XTGNB.gnlx ='").append(sGnlx).append("'");
    }
    strBufHql.append(" order by XT_YWQXKZB.ywid");
    return getXtywqxxx(strBufHql.toString(), nPageOffset, nPageSize);
  }

  /**
   *增加业务权限信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtywqxxx(VarVO vvXTywqxB) throws ServiceException,
      DAOException {

    ArrayList lstXtywqx = new ArrayList();
    PoXT_YWQXKZB poXtywqxb = new PoXT_YWQXKZB();
    PoXT_YWQXKZB rtnpoXTywqxB = new PoXT_YWQXKZB();
    
    //ObjectUtil.copyInfoByMap(poXtywqxb, vvXTywqxB.getVarmap(), null, null, false);
    
    try {
    	  BeanUtils.populate(poXtywqxb, vvXTywqxB.getVarmap());
      //执行
      PojoInfo Xt_XtywqxbDao = DAOFactory.createXT_YWQXKZBDAO();
      PojoInfo Xt_XtrzbDao = DAOFactory.createXT_XTRZBDAO();
      poXtywqxb.setQxkzid( (Long) Xt_XtywqxbDao.getId());
      rtnpoXTywqxB = (PoXT_YWQXKZB)super.create(poXtywqxb);
      lstXtywqx.add(rtnpoXTywqxB);
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
    return lstXtywqx;
  }

  /**
   *修改业务权限信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtywqxxgxx(VarVO vvXTywqxB) throws ServiceException,
      DAOException {
    ArrayList lstXtywqx = new ArrayList();
    PoXT_YWQXKZB poXtywqxb = null;
    PoXT_YWQXKZB rtnpoXTywqxB = new PoXT_YWQXKZB();
    //获取参数信息,设置PO语句
    try {
      PojoInfo Xt_XtywqxbDao = DAOFactory.createXT_YWQXKZBDAO();
      PojoInfo Xt_XtrzbDao = DAOFactory.createXT_XTRZBDAO();
      poXtywqxb = super.get(PoXT_YWQXKZB.class, Long.valueOf(vvXTywqxB.
          getVarValue("qxkzid")));
      if (poXtywqxb == null) { //如果查询不到记录
        return null;
      }
      BeanUtils.populate(poXtywqxb, vvXTywqxB.getVarmap());
      //执行
      rtnpoXTywqxB = (PoXT_YWQXKZB)super.update(poXtywqxb);

      lstXtywqx.add(rtnpoXTywqxB);
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
    return lstXtywqx;

  }

  /**
   *删除业务权限信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtywqxscxx(VarVO vvXTywqxB) throws ServiceException,
      DAOException {
    PoXT_YWQXKZB rtnpoXTywqxB = new PoXT_YWQXKZB();
    PoXT_YWQXKZB poXTywqxB = new PoXT_YWQXKZB();
    int nReturn = PublicConstant.XT_BZ_ERROR;

    //执行HQL语句
    try {
      PojoInfo Xt_XtywqxbDao = DAOFactory.createXT_YWQXKZBDAO();
      PojoInfo Xt_XtrzbDao = DAOFactory.createXT_XTRZBDAO();

      poXTywqxB.setQxkzid(Long.valueOf(vvXTywqxB.getVarValue("qxkzid")));
      super.delete(poXTywqxB);

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
   * 数据范围信息信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtsjfwxx(VarVO vvXtywqx) throws ServiceException, DAOException {
    /**
     * 获取参数信息
     */
    String sYwid = vvXtywqx.getVarValue("ywid");
    String sYhid = vvXtywqx.getVarValue("yhid");
    List lstXtRtn = new ArrayList();
    lstXtRtn = SelectDataRange(sYhid, sYwid);
    return lstXtRtn;
  }

  /**
   * 根据用户ID和业务名称查询数据范围
   * @param sYwid
   * @param sYwmc
   * @return
   */
  public static List SelectDataRange(String sYhid, String sYwid) throws
      ServiceException, DAOException {
    /**
     * 变量定义
     */
    List lstXtRtn = new ArrayList();
    List lstXtywqxkzb = null;
    List lstXtsjfw = null;
    List lstXtDataRange = null;
    List lstXtdtyh = null;
    PoXT_YWQXKZB poXtywqxb = null;
    PoXT_YHSJFWB poXtyhsjfwb = null;
    PoXT_YHDTQXB poXtyhdtqxb = null;
    String sXqlx = "", sSjfwxz = "", sYhdtcx = "";
    VoXtsjfw voXtsjfw = new VoXtsjfw();
    String sSjfw = "";
    Long lDtyh;
    //判断用户是否有角色是超级用户
    List lstXtyhjs = null;
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        " select XT_YHJSXXB from PoXT_YHJSXXB as XT_YHJSXXB,PoXT_JSXXB as XT_JSXXB")
        .append(" where XT_YHJSXXB.jsid =XT_JSXXB.jsid and XT_JSXXB.jsmc = '")
        .append(PublicConstant.XT_CJYHMC).append("'")
        .append(" and XT_YHJSXXB.yhid = ").append(sYhid);
    try {
      //XT_YHJSXXBDAO Xt_yhjsxxbDao = DAOFactory.createXT_YHJSXXBDAO();
      lstXtyhjs = SpringContextHolder.getCommonService().queryAll(strBufHql.toString());
      if (lstXtyhjs != null && !lstXtyhjs.isEmpty()) {
        return null;
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
    /**
     * 参数数据检测
     */
    if ( (sYhid == null) || ("".equals(sYhid))
        || (sYwid == null) || ("".equals(sYwid))) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL, "参数输入不完整", null);
    }
    /**
     * 根据业务ID判断辖区类型的用户数据范围是否限制，是否用户等同权限
     * 如果用户数据范围不限制，则返回用户的辖区类型
     * 如果用户数据范围限制，根据用户ID，辖区类型查询数据范围
     *  有等同用户权限，返回等同用户的数据范围
     */
    strBufHql.setLength(0);
    strBufHql.append("select XT_YWQXKZB FROM PoXT_YWQXKZB as XT_YWQXKZB where ")
        .append(" ywid ='").append(sYwid).append("'");
    try {
     // XT_YWQXKZBDAO Xt_XtywqxbDao = DAOFactory.createXT_YWQXKZBDAO();
     // XT_YHDTQXBDAO Xt_yhdtqxbDao = DAOFactory.createXT_YHDTQXBDAO();

      lstXtywqxkzb = SpringContextHolder.getCommonService().queryAll(strBufHql.toString());
      if (lstXtywqxkzb == null || lstXtywqxkzb.size() <= 0) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                   "无此" + sYhid + "业务权限控制", null);
      }
      //取出辖区类型，数据范围限制标志，用户等同查询标志
      poXtywqxb = (PoXT_YWQXKZB) lstXtywqxkzb.get(0);
      sXqlx = poXtywqxb.getXqlx().trim();
      sSjfwxz = poXtywqxb.getSjfwxz().trim();
      sYhdtcx = poXtywqxb.getYhdtcx().trim();
      //如果数据范围不限制
      if (PublicConstant.XT_BZ_BXZ.equals(sSjfwxz)) {
        return null;
      }
      //如果数据范围限制
      else {
        //根据用户ID和辖区类型查询用户数据范围
        getYhsjfw(sYhid, sXqlx, lstXtRtn);
        /**
         * 如果等同用户查询则查询等同用户的数据范围
         */
        if (PublicConstant.XT_BZ_QY.equals(sYhdtcx)) {
          //查询等同用户
          String sHql =
              "select XT_YHDTQXB FROM PoXT_YHDTQXB as XT_YHDTQXB where ";
          sHql = sHql + " yhid =" + sYhid;
          lstXtdtyh = SpringContextHolder.getCommonService().queryAll(sHql);
          if (lstXtdtyh != null && lstXtdtyh.size() > 0) { //找到记录
            for (int i = 0; i < lstXtdtyh.size(); i++) {
              poXtyhdtqxb = (PoXT_YHDTQXB) lstXtdtyh.get(i);
              lDtyh = poXtyhdtqxb.getDtyhid();
              getYhsjfw(String.valueOf(lDtyh), sXqlx, lstXtRtn);
            }
          }
        }
      }
      //如果结果集中没有信息，则将无权限的信息增加
      if (lstXtRtn == null || lstXtRtn.size() <= 0) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                   "无此" + sYhid + "业务权限", null);
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
   * 解析数据范围
   * @param sSjfw 格式为  区划代码/派出所代码/居委会代码
   * @return Vo 有字段 辖区类型，数据范围标志，数据范围
   */
  public static List TransDataRange(String sSjfw) {
    List lstXtsjfw = new ArrayList();
    String sSj = "", sSjbz = "";
    String[] sBuff = getStringToBuff(sSjfw, "|");
    if (sBuff[0] != null && !"".equals(sBuff[0])) { //区划代码
      sSjbz = PublicConstant.XT_QX_XZQH;
      sSj = sBuff[0];
    }
    if (sBuff[1] != null && !"".equals(sBuff[1])) { //派出所代码
      sSjbz = PublicConstant.XT_QX_PCS;
      sSj = sBuff[1];
    }
    if (sBuff[2] != null && !"".equals(sBuff[2])) { //居委会代码
      sSjbz = PublicConstant.XT_QX_JWH;
      sSj = sBuff[2];
    }
    lstXtsjfw.add(0, sSjbz);
    lstXtsjfw.add(1, sSj);
    return lstXtsjfw;
  }

  /**
   * 根据用户ID和辖区类型取得用户数据范围
   * @param sYhid
   * @param sXqlx
   * @param lstXtRtn
   * @throws ServiceException
   * @throws DAOException
   */
  public static void getYhsjfw(String sYhid, String sXqlx, List lstXtRtn) throws
      ServiceException, DAOException {

    List lstXtDataRange = null;
    VoXtsjfw voXtsjfw;
    PoXT_YHSJFWB poXtyhsjfwb = new PoXT_YHSJFWB();
    String sSjfw = "";
    List lstXtsjfw = null;

    //根据用户ID和辖区类型查询用户数据范围
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append("select XT_YHSJFWB FROM PoXT_YHSJFWB as XT_YHSJFWB where ")
        .append(" yhid =").append(sYhid)
        .append(" and xqlx ='").append(sXqlx).append("'");

    try {
      //XT_YHSJFWBDAO Xt_yhsjfwDao = DAOFactory.createXT_YHSJFWBDAO();

      lstXtDataRange = SpringContextHolder.getCommonService().queryAll(strBufHql.toString());
      if (lstXtDataRange != null && lstXtDataRange.size() > 0) { //找到记录
        for (int i = 0; i < lstXtDataRange.size(); i++) {
          voXtsjfw = new VoXtsjfw();
          poXtyhsjfwb = (PoXT_YHSJFWB) lstXtDataRange.get(i);
          sSjfw = poXtyhsjfwb.getSjfw(); //取得数据范围
          lstXtsjfw = TransDataRange(sSjfw); //解析数据范围
          voXtsjfw.setSjfwbz(lstXtsjfw.get(0).toString());
          voXtsjfw.setSjfw(lstXtsjfw.get(1).toString());
          voXtsjfw.setXqlx(sXqlx);
          //判断信息是否已经存在于LIST中
          AddVoIntoList(voXtsjfw, lstXtRtn);
          //lstXtRtn.add(voXtsjfw);
        } //for
      } //if
    }
    catch (DAOException ex) {
      throw ex;
    }
  }

  /**
   * 根据分割符将字符串转化为数组
   * @param sStr
   * @param sSplit
   * @return
   */
  public static String[] getStringToBuff(String sStr, String sSplit) {
    String sBuff[] = new String[5];
    StringTokenizer st = new StringTokenizer(sStr, sSplit);
    int i = 0;

    while (st.hasMoreElements()) {
      sBuff[i] = String.valueOf(st.nextElement());
      i++;
    }

    return sBuff;
  }

  /**
   * 将记录增加到LIST中，首先判断是否已经增加
   * @param vx
   * @param lstXt
   * @return
   */
  private static void AddVoIntoList(VoXtsjfw vx, List lstXt) {
    VoXtsjfw voXtsjfw = new VoXtsjfw();
    boolean bFlag = false;
    for (int i = 0; i < lstXt.size(); i++) {
      voXtsjfw = (VoXtsjfw) lstXt.get(i);
      if (vx.getSjfwbz().equals(voXtsjfw.getSjfwbz())
          && vx.getSjfw().equals(voXtsjfw.getSjfw())
          && vx.getXqlx().equals(voXtsjfw.getXqlx())) {
        bFlag = true;
        break;
      }
    }
    //如果不存在，则增加
    if (!bFlag && !"".equals(vx.getSjfwbz())) {
      lstXt.add(vx);
    }
  }

  /**
   * 判断输入的用户和业务是否有LIST中存在的数据范围权限
   * @param sYhid
   * @param lstXtsjfw
   * @return
   */
  public static boolean VerifyDataRange(String sYhid, String sYwid,
                                        List lstXtsjfw) throws
      ServiceException, DAOException {
    VoXtsjfw voXtsjfw = null;
    VoXtsjfw voXt = null;
    List lstXt = null;
    String sSjfwbz = "", sSjfw = "";
    String sParamSjfwbz = "", sParamSjfw = "";
    boolean bFlag = false;
    //判断用户是否有角色是超级用户
    List lstXtyhjs = null;
    List lstXtjwh = null;
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        " select XT_YHJSXXB from PoXT_YHJSXXB as XT_YHJSXXB,PoXT_JSXXB as XT_JSXXB")
        .append(" where XT_YHJSXXB.jsid =XT_JSXXB.jsid and XT_JSXXB.jsmc = '")
        .append(PublicConstant.XT_CJYHMC).append("'")
        .append(" and XT_YHJSXXB.yhid = ").append(sYhid);
    try {
      //XT_YHJSXXBDAO Xt_yhjsxxbDao = DAOFactory.createXT_YHJSXXBDAO();
      lstXtyhjs = SpringContextHolder.getCommonService().queryAll(strBufHql.toString());
      if (lstXtyhjs != null && !lstXtyhjs.isEmpty()) {
        return true;
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
    //取得数据范围

      lstXt = SelectDataRange(sYhid, sYwid);
      //lstXt为空则业务不限制
      if (lstXt == null) {
        return true;
      }
      for (int i = 0; i < lstXtsjfw.size(); i++) { //数据范围可能有多个
        voXtsjfw = (VoXtsjfw) lstXtsjfw.get(i);
        sParamSjfwbz = voXtsjfw.getSjfwbz();
        sParamSjfw = voXtsjfw.getSjfw();
        bFlag = false;
        for (int j = 0; j < lstXt.size(); j++) {
          voXt = (VoXtsjfw) lstXt.get(j);
          sSjfwbz = voXt.getSjfwbz();
          sSjfw = voXt.getSjfw().trim();
          /**
           *如果sParamSjfw的长度小于sSjfw，也就是输入的数据范围的级别大于可用的
           * 数据范围，则返回false,无此数据范围权限
           */
          if (sParamSjfw.length() >= sSjfw.length()) {
            if (sParamSjfw.substring(0, sSjfw.length()).equals(sSjfw)) {
              bFlag = true;
              break;
            }
          }
          /**
           * 如果sparamSjfw 的长度是 12,而sSjfw的长度是9(单位) ,
           * 则要查询单位的居委会信息
           */
          if (sParamSjfw.length() == 12 && sSjfw.length() == 9) {
            strBufHql.setLength(0);
            strBufHql.append(
                " select XT_JWHXXB from PoXT_JWHXXB as XT_JWHXXB where XT_JWHXXB.dwdm ='")
                .append(sSjfw).append("'")
                .append(" and XT_JWHXXB.dm = '").append(sParamSjfw).append("'");
            //XT_JWHXXBDAO Xt_jwhxxbDao = DAOFactory.createXT_JWHXXBDAO();
            lstXtjwh = SpringContextHolder.getCommonService().queryAll(strBufHql.toString());
            if (lstXtjwh != null && !lstXtjwh.isEmpty()) {
              bFlag = true;
              break;
            }
          }
        }
        if (!bFlag) {
          return bFlag;
        }
      }

    return bFlag;
  }

  public static VoXtywxz VerifyBusinessLimit(String sYwid, DefaultVO dv) throws
      ServiceException, DAOException {
    return VerifyBusinessLimit(sYwid, dv, "1");
  }

  public static VoXtywxz VerifyCheckLimit(String sYwid, DefaultVO dv) throws
      ServiceException, DAOException {
    return VerifyBusinessLimit(sYwid, dv, "2");
  }

  /**
   * 根据业务ID和参数VO判断业务是否受限
   * 不包含审批限制信息
   * @param sYwid
   * @param dv
   * @param sFlag 标志是否是审批限制 1:不判断审批限制，2：审批限制
   * @return TRUE受限制，FALSE不受限制
   *         sRtnXzmc:限制名称
   */
  public static VoXtywxz VerifyBusinessLimit(String sYwid, DefaultVO dv,
                                             String sFlag) throws
      ServiceException, DAOException {
    boolean bFlag = false;
    VoXtywxz voXtywxz = new VoXtywxz();
    /**
     * 首先根业务ID取得限制表达式
     */
    List lstXtxzbds = null;
    List lstXtRtn = null;
    String sBds = "", sTransBds = "", sSpmbid = "";
    String sKey = "", sKeyData = "";
    int nKs = 0;
    int nJs = 0;
    try {
      //XT_YHSJFWBDAO Xt_yhsjfwDao = DAOFactory.createXT_YHSJFWBDAO();
      StringBuffer strBufHql = new StringBuffer();
      strBufHql.append(
          "select XT_YWBLXZXXB FROM PoHJXZ_YWBLXZXXB as XT_YWBLXZXXB where  ")
          .append(" xzzt = '0'")
          .append(" and qybz ='").append(PublicConstant.QYBZ_QY).append("'")
          .append(" and  xzywlx ='").append(sYwid).append("'");

      lstXtxzbds = SpringContextHolder.getCommonService().queryAll(strBufHql.toString());
      if (lstXtxzbds == null || lstXtxzbds.isEmpty()) {
        //如果没找到表达式则表示不受限制
        voXtywxz.setLimitflag(false);
        voXtywxz.setLimitinfo("无限制表达式");
        return voXtywxz;
      }
      //假如有多个表达式，循环判断表达式，如果有一个为TRUE，则返回TRUE；
      for (int i = 0; i < lstXtxzbds.size(); i++) {
        sBds = ( (PoHJXZ_YWBLXZXXB) lstXtxzbds.get(i)).getXzbds(); //取出限制表达式
        if (sBds != null && !"".equals(sBds)) { //如果表达式存在，则判断表达式
          /**
           * 根据参数VO替换表达式的参数
           */
          // sTransBds = sBds;
          try {
            nKs = sBds.indexOf("[[");
            nJs = sBds.indexOf("]]");
            while (nKs >= 0) {
              sKey = sBds.substring(nKs + 2, nJs);
              if (dv.hasProperty(sKey)) {
                sKeyData = dv.queryProperty(sKey);
                sBds = sBds.substring(0, nKs) + sKeyData +
                    sBds.substring(nJs + 2);
              }
              else if (sKey.equalsIgnoreCase("age")) {
                if (dv.hasProperty("csrq")) {
                  sBds = sBds.substring(0, nKs) +
                      StringUtils.dateToAgeDay(dv.queryProperty("csrq")) +
                      sBds.substring(nJs + 2);
                }
              }
              nKs = sBds.indexOf("[[", nJs + 2);
              nJs = sBds.indexOf("]]", nJs + 2);
            }
            /**
             *  判断表达式是否为真
             *  为真则表示受限制
             */
            strBufHql.setLength(0);
            strBufHql.append(
                "select XT_YWBLXZXXB FROM PoHJXZ_YWBLXZXXB as XT_YWBLXZXXB where ")
                .append(sBds);
            lstXtRtn = SpringContextHolder.getCommonService().queryAll(strBufHql.toString());
            if (lstXtRtn != null && lstXtRtn.size() > 0) {
              sSpmbid = ( (PoHJXZ_YWBLXZXXB) lstXtxzbds.get(i)).getSpmbid().
                  toString().trim();
              //如果找到信息且不是审批则表达式为tue（受限制）
              if ( (sSpmbid == null || "".equals(sSpmbid) || "0".equals(sSpmbid)) &&
                  sFlag.equals("1")) {
                voXtywxz.setLimitinfo( ( (PoHJXZ_YWBLXZXXB) lstXtxzbds.get(i)).
                                      getXzmc()); //取出限制名称
                voXtywxz.setLimitflag(true);
                return voXtywxz;
              }
              else if (sSpmbid != null && !"".equals(sSpmbid) &&
                       !"0".equals(sSpmbid) &&
                       sFlag.equals("2")) {
                voXtywxz.setLimitinfo( ( (PoHJXZ_YWBLXZXXB) lstXtxzbds.get(i)).
                                      getXzmc()); //取出限制名称
                voXtywxz.setSpmbid(Long.valueOf(sSpmbid));
                voXtywxz.setLimitflag(true);
                return voXtywxz;
              }
            }
          }
          catch (Exception ex) {
        	  throw ex;
            //voXtywxz.setLimitflag(false);
            //voXtywxz.setLimitinfo("限制表达式中字段不正确");
            //return voXtywxz;
          }
        } //if
      } //for
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    voXtywxz.setLimitinfo("条件存在且不受限制");
    voXtywxz.setLimitflag(false);
    return voXtywxz;
  }

  /**
   * 根据变更人员信息和变更信息判断业务是否受限
   * lstBggzxx里主要储存变更更正信息
   * @param sYwid
   * @param dv
   * @param sFlag 标志是否是审批限制 1:不判断审批限制，2：审批限制
   * @return TRUE受限制，FALSE不受限制
   *         sRtnXzmc:限制名称
   */
  //变更更正限制
  public static VoXtywxz VerifyBggzLimit(String sYwid, DefaultVO dvRyxx,
                                         List lstBggzxx) throws
      ServiceException, DAOException {
    return VerifyBggzLimit(sYwid, dvRyxx, lstBggzxx, "1");
  }

  //变更更正审批限制
  public static VoXtywxz VerifyBggzSpLimit(String sYwid, DefaultVO dvRyxx,
                                           List lstBggzxx) throws
      ServiceException, DAOException {
    return VerifyBggzLimit(sYwid, dvRyxx, lstBggzxx, "2");
  }

  /**
   * 业务办理限制模型：
   * 变更更正年龄限制： '[[age]]' >= 20
   * 变更更正出生日期3年内不允许办理姓名变更：'[[bgxm3nbgcsrq]]'='xm'
   * 限制某些人员（如干部身份人员）更正出生日期：'[[gbrybgcsrq]]'='csrq'
   */
  public static VoXtywxz VerifyBggzLimit(String sYwid, DefaultVO dvRyxx,
                                         List lstBggzxx, String sFlag) throws
      ServiceException, DAOException {
    boolean bFlag = false;
    VoXtywxz voXtywxz = new VoXtywxz();
    VoBggzxx voBggzxx;
    /**
     * 首先根业务ID取得限制表达式
     */
    List lstXtxzbds = null;
    List lstXtRtn = null;
    String sBds = "", sTransBds = "", sSpmbid = "", sSpmbdj = "";
    String sKey = "", sKeyData = "", sgXzmc = "", sgSpmbid = "", sgSpmbDj = "";
    String sBggzBds = "";
    String sBggzQxm = "", sBggzHxm = "";
    List lstBggzxm;
    List lstBggzNo = new ArrayList();
    int nKs = 0, nJs = 0;
    int nDh = 0, nEndBds = 0, nXmNo = 0, nXmMax = 0;
    try {
      //XT_YHSJFWBDAO Xt_yhsjfwDao = DAOFactory.createXT_YHSJFWBDAO();
      //HJYW_BGGZXXBDAO hjyw_bggzxxbdao = DAOFactory.createHJYW_BGGZXXBDAO();
      //HJXZ_GBRYXZBDAO hjxz_gbryxzbdao = DAOFactory.createHJXZ_GBRYXZBDAO();
      StringBuffer strBufHql = new StringBuffer();
      strBufHql.append(
          "select XT_YWBLXZXXB FROM PoHJXZ_YWBLXZXXB as XT_YWBLXZXXB where  ")
          .append(" qybz ='").append(PublicConstant.QYBZ_QY).append("'")
          .append(" and  xzywlx ='").append(sYwid).append("'").append(" and  xzzt ='0'");
      lstXtxzbds = SpringContextHolder.getCommonService().queryAll(strBufHql.toString());
      if (lstXtxzbds == null || lstXtxzbds.isEmpty()) {
        //如果没找到表达式则表示不受限制
        voXtywxz.setLimitflag(false);
        voXtywxz.setLimitinfo("无限制表达式");
        return voXtywxz;
      }
      //假如有多个表达式，循环判断表达式，如果有一个为TRUE，则返回TRUE；
      /**取出表达式中变更更正项目的数据，放入lstBggz中
       * lstbggz中存放的List对象(lstBggzxm)
       * lstBggzxm中存放的是每个表达式的变更项目数据
       */
      nXmMax = 0;
      sgSpmbid = "";
      for (int i = 0; i < lstXtxzbds.size(); i++) {
        boolean bisbgxm = false;
        boolean bisbgcsrq = false;
        sBds = ( (PoHJXZ_YWBLXZXXB) lstXtxzbds.get(i)).getXzbds(); //取出限制表达式
        if (sBds != null && !"".equals(sBds)) { //如果表达式存在，则判断表达式
          nXmNo = 0;
          sBggzBds =sBds;
          for (int j = 0; j < lstBggzxx.size(); j++) {
            voBggzxx = ( (VoBggzxx) lstBggzxx.get(j));
            sBds = sBggzBds;
            sBggzBds = sBds.replaceAll("'" + voBggzxx.getBggzxm() + "'",
                                       "'[[bggzxm]]'");
            if (!sBggzBds.equals(sBds)) {
              nXmNo++;
            }
            //hubin 20091224 变更姓名时，判断姓是否有变更,获取变更后姓名
            if ("xm".equals(voBggzxx.getBggzxm())){
              sBggzHxm = voBggzxx.getBggzhnr();
              bisbgxm = true;
            }
            //hubin 20120920 变更中是否存在变更出生日期
            if ("csrq".equals(voBggzxx.getBggzxm())){
              bisbgcsrq = true;
            }

          }
          sBds = sBggzBds;
          /**
           * 根据参数VO替换表达式的参数
           */
          // sTransBds = sBds;
          try {
            nKs = sBds.indexOf("[[");
            nJs = sBds.indexOf("]]");

            while (nKs >= 0) {
              sKey = sBds.substring(nKs + 2, nJs);
              if (!sKey.equalsIgnoreCase("bggzxm")) {
                if (dvRyxx.hasProperty(sKey)) {
                  sKeyData = dvRyxx.queryProperty(sKey);
                  sBds = sBds.substring(0, nKs) + sKeyData +
                      sBds.substring(nJs + 2);
                }
                else if (sKey.equalsIgnoreCase("age")) {
                  if (dvRyxx.hasProperty("csrq")) {
                    //胡斌 20060607 合成限制表达式,取得的CSRQ需要引号
//                    sBds = sBds.substring(0, nKs ) +
//                        StringUtils.dateToAgeDay(dvRyxx.queryProperty("csrq")) +
//                        sBds.substring(nJs + 2);
                    //胡斌 20060818 合成有数值型判断时的表达式时，不能加引号
                    sBds = sBds.substring(0, nKs - 1) + " " +
                        StringUtils.dateToAgeDay(dvRyxx.queryProperty("csrq")) +
                        sBds.substring(nJs + 3);

                  }
                }
                //hb 20091224 变更姓名时，判断姓是否有变更
                else if (sKey.equalsIgnoreCase("bggzq-x")){
                  if (dvRyxx.hasProperty("xm") ) {
                    sKeyData = (dvRyxx.queryProperty("xm")).substring(0,1);
                    sBds = sBds.substring(0, nKs) + sKeyData +
                      sBds.substring(nJs + 2);
                    nJs = nJs - 11+ sKeyData.length();
                  }
                }
                else if (sKey.equalsIgnoreCase("bggzh-x")){
                  if (sBggzHxm != "") {
                    sKeyData = sBggzHxm.substring(0, 1);
                    sBds = sBds.substring(0, nKs) + sKeyData +
                        sBds.substring(nJs + 2);
                    nJs = nJs - 11 + sKeyData.length();
                  }
                }
                //20120903变更更正姓名，查询3年内有无变更出生日期的
                else if (sBggzBds.indexOf("bgxm3nbgcsrq") >= 0) {
                  if (bisbgxm) {
                    List lstBggz = new ArrayList();
                    StringBuffer bggzSQL = new StringBuffer();
                    //变更更正类别:41变更出生日期
                    bggzSQL.append(
                        " select HJYW_BGGZXXB from PoHJYW_BGGZXXB AS HJYW_BGGZXXB where ")
                        .append(" BGGZLB='41' and slsj>TO_CHAR(add_months(sysdate,-36),'YYYYMMDDHH24MISS') ")
                        .append(" and ryid=" + dvRyxx.queryProperty("ryid"));
                    lstBggz = SpringContextHolder.getCommonService().queryAll(bggzSQL.
                        toString());
                    if (lstBggz.size() > 0) {
                      sBds = sBds.replaceAll("bggzxm", "bgxm3nbgcsrq");
                    }
                  }
                }
                //20120920变更更正出生日期，限制干部人员受理出生日期变更
                else if (sBggzBds.indexOf("gbrybgcsrq") >= 0) {
                  if (bisbgcsrq) {
                    List lstGbry = new ArrayList();
                    StringBuffer sSQL = new StringBuffer();
                    sSQL.append(
                        " select HJXZ_GBRYXZB from PoHJXZ_GBRYXZB AS HJXZ_GBRYXZB where ")
                        .append(" ryid=" + dvRyxx.queryProperty("ryid"));
                    lstGbry = SpringContextHolder.getCommonService().queryAll(sSQL.toString());
                    if (lstGbry.size() > 0) {
                      sBds = sBds.replaceAll("bggzxm", "gbrybgcsrq");
                    }
                  }
                }


              }
              nKs = sBds.indexOf("[[", nJs + 2);
              nJs = sBds.indexOf("]]", nJs + 2);
            }
            /**
             *  判断表达式是否为真
             *  为真则表示受限制
             */
            strBufHql.setLength(0);
            strBufHql.append(
                "select XT_YWBLXZXXB FROM PoHJXZ_YWBLXZXXB as XT_YWBLXZXXB where ")
                .append(sBds);
            lstXtRtn = SpringContextHolder.getCommonService().queryAll(strBufHql.toString());
            if (lstXtRtn != null && lstXtRtn.size() > 0) {
              sSpmbid = ( (PoHJXZ_YWBLXZXXB) lstXtxzbds.get(i)).getSpmbid().
                  toString().trim();
              //如果找到信息且不是审批则表达式为tue（受限制）
              if ( (sSpmbid == null || "".equals(sSpmbid) || "0".equals(sSpmbid)) &&
                  sFlag.equals("1")) {
                voXtywxz.setLimitinfo( ( (PoHJXZ_YWBLXZXXB) lstXtxzbds.get(i)).
                                      getXzmc()); //取出限制名称
                voXtywxz.setLimitflag(true);
                return voXtywxz;
              }
              else if ( (sSpmbid != null || !"".equals(sSpmbid)) &&
                       !"0".equals(sSpmbid) && sFlag.equals("2")) {
                if (nXmNo > nXmMax) {
                  sgSpmbid = sSpmbid;
                  sgXzmc = ( (PoHJXZ_YWBLXZXXB) lstXtxzbds.get(i)).getXzmc();
                  sgSpmbDj = GetSpmbDj(sgSpmbid);
                }
                else if (nXmNo == nXmMax) {
                  sSpmbdj = GetSpmbDj(sSpmbid);
                  if (sSpmbdj.compareTo(sgSpmbDj) > 0) {
                    sgSpmbid = sSpmbid;
                    sgXzmc = ( (PoHJXZ_YWBLXZXXB) lstXtxzbds.get(i)).getXzmc();
                    sgSpmbDj = sSpmbdj;
                  }
                }
              }
            }
          }
          catch (Exception ex) {
            voXtywxz.setLimitflag(false);
            voXtywxz.setLimitinfo("限制表达式中字段不正确");
            return voXtywxz;
          }
        } //if
      } //for
      if (!"".equals(sgSpmbid)) {
        voXtywxz.setLimitinfo(sgXzmc);
        voXtywxz.setSpmbid(Long.valueOf(sgSpmbid));
        voXtywxz.setLimitflag(true);
        return voXtywxz;
      }
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    voXtywxz.setLimitinfo("条件存在且不受限制");
    voXtywxz.setLimitflag(false);
    return voXtywxz;
  }

  private static String GetSpmbDj(String sSpmbid) {
    List lstXtspmbDj;
    try {
      //XT_SPMBXXBDAO Xt_spmbBdao = DAOFactory.createXT_SPMBXXBDAO();
      StringBuffer strBufHql = new StringBuffer();
      strBufHql.setLength(0);
      strBufHql.append(
          "select XT_SPMBXXB FROM PoXT_SPMBXXB as XT_SPMBXXB where ")
          .append("spmbid=" + sSpmbid);
      lstXtspmbDj = SpringContextHolder.getCommonService().queryAll(strBufHql.toString());
      if (lstXtspmbDj != null && lstXtspmbDj.size() > 0) {
        return ( (PoXT_SPMBXXB) lstXtspmbDj.get(0)).getMbdj();
      }
    }
    catch (DAOException ex) {
      throw ex;
    }
    return "0";
  }

  /**
   * 判断用户是否有操作此业务的权限
   * @param sYhid
   * @param sYwid
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public static boolean VerifyUserFunction(String sYhid, String sYwid) throws
      ServiceException, DAOException {
    List lstXtyhdt = null;
    String sDtyhid = "";
    Long nDtyhid;
    //参数效验,如果参数输入不完整，则无此权限
    if ( (sYhid == null) || "".equals(sYhid)
        || (sYwid == null) || "".equals(sYwid)) {
      return false;
    }
    //判断用户是否有角色是超级用户
    List lstXtyhjs = null;
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        " select XT_YHJSXXB from PoXT_YHJSXXB as XT_YHJSXXB,PoXT_JSXXB as XT_JSXXB")
        .append(" where XT_YHJSXXB.jsid =XT_JSXXB.jsid and XT_JSXXB.jsmc = '")
        .append(PublicConstant.XT_CJYHMC).append("'")
        .append(" and XT_YHJSXXB.yhid = ").append(sYhid);
    try {
      //XT_YHJSXXBDAO Xt_yhjsxxbDao = DAOFactory.createXT_YHJSXXBDAO();
      lstXtyhjs = SpringContextHolder.getCommonService().queryAll(strBufHql.toString());
      if (lstXtyhjs != null && !lstXtyhjs.isEmpty()) {
        return true;
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
      //如果用户有此功能，则返回
      if (VerifyYhGn(sYhid, sYwid)) {
        return true;
      }
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    //判断用户的等同用户是否有此业务的权限
    strBufHql.setLength(0);
    strBufHql.append("select XT_YHDTQXB from PoXT_YHDTQXB as XT_YHDTQXB where ")
        .append(" XT_YHDTQXB.yhid = ").append(sYhid);
    try {
      //XT_YHDTQXBDAO Xt_yhdtqxbDao = DAOFactory.createXT_YHDTQXBDAO();
      lstXtyhdt = SpringContextHolder.getCommonService().queryAll(strBufHql.toString());
      if (lstXtyhdt == null || lstXtyhdt.isEmpty()) {
        //如果没有找到记录则表示没有等同用户
        return false;
      }
      for (int i = 0; i < lstXtyhdt.size(); i++) {
        sDtyhid = String.valueOf( ( (PoXT_YHDTQXB) lstXtyhdt.get(i)).getDtyhid());
        if (VerifyYhGn(sDtyhid, sYwid)) {
          return true;
        }
      }

    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return true;
  }

  public static boolean VerifyYhGn(String sYhid, String sYwid) throws
      ServiceException, DAOException {
    List lstXtgn = null;

    //判断用户是否有此业务的权限
    try {
      //XT_XTGNBDAO Xt_xtgnbDao = DAOFactory.createXT_XTGNBDAO();
      StringBuffer strBufHql = new StringBuffer();
      strBufHql.append(
          "select XT_YHXXB,XT_YHJSXXB,XT_JSGNQXB,XT_XTGNB FROM PoXT_YHXXB as XT_YHXXB,")
          .append(
          " PoXT_YHJSXXB as XT_YHJSXXB,PoXT_JSGNQXB as XT_JSGNQXB,PoXT_XTGNB as XT_XTGNB ")
          .append(" where XT_YHXXB.yhid = XT_YHJSXXB.yhid ")
          .append(" and XT_JSGNQXB.jsid = XT_YHJSXXB.jsid ")
          .append(" and XT_JSGNQXB.gnid = XT_XTGNB.gnid ")
          .append(" and XT_XTGNB.gnbh ='").append(sYwid).append("'")
          .append(" and XT_YHXXB.yhid =").append(sYhid);
      lstXtgn = SpringContextHolder.getCommonService().queryAll(strBufHql.toString());
      if (lstXtgn != null && !lstXtgn.isEmpty()) {
        //如果找到记录则表示有此功能
        return true;
      }
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return false;
  }

  /**
   * 判断用户IP是否受限制
   * @param sYhid
   * @param sIp
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public static boolean VerifyUserIP(String sYhid, String sIp) throws
      ServiceException, DAOException {
    List lstXtyhip = null;
    //如果表为空则，不限制
    String sHql =
        " select XT_YHIPYXXXB from PoXT_YHIPYXXXB as XT_YHIPYXXXB where yhid = " +
        sYhid;
    try {
      //XT_YHIPYXXXBDAO Xt_yhipyxbDao = DAOFactory.createXT_YHIPYXXXBDAO();
      lstXtyhip = SpringContextHolder.getCommonService().queryAll(sHql);
      if (lstXtyhip == null || lstXtyhip.isEmpty()) {
        return true;
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

    //判断用户是否有角色是超级用户
    List lstXtyhjs = null;
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        " select XT_YHJSXXB from PoXT_YHJSXXB as XT_YHJSXXB,PoXT_JSXXB as XT_JSXXB")
        .append(" where XT_YHJSXXB.jsid =XT_JSXXB.jsid and XT_JSXXB.jsmc = '")
        .append(PublicConstant.XT_CJYHMC).append("'")
        .append(" and XT_YHJSXXB.yhid = ").append(sYhid);
    try {
      //XT_YHJSXXBDAO Xt_yhjsxxbDao = DAOFactory.createXT_YHJSXXBDAO();
      lstXtyhjs = SpringContextHolder.getCommonService().queryAll(strBufHql.toString());
      if (lstXtyhjs != null && !lstXtyhjs.isEmpty()) {
        return true;
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
    /***假如数据库中有YHID为空的地址，则默认所有用户只能用此IP登陆
     *  假如YHID在数据库中而IP不在数据库中，则返回FALSE
     *
     */

    String sCxIp;
    boolean bFlag = false;
    sHql =
        " select XT_YHIPYXXXB.ipdz from PoXT_YHIPYXXXB as XT_YHIPYXXXB where "
        + " yhid is null or yhid='' or yhid ='0'";
    try {
      bFlag = false;
      //XT_YHIPYXXXBDAO Xt_yhipyxbDao = DAOFactory.createXT_YHIPYXXXBDAO();
      lstXtyhip = SpringContextHolder.getCommonService().queryAll(sHql);
      if (lstXtyhip != null && !lstXtyhip.isEmpty()) {
        for (int i = 0; i < lstXtyhip.size(); i++) {
          sCxIp = lstXtyhip.get(i).toString();
          if (sCxIp.equals(sIp)) {
            return true;
          }
        }
        bFlag = true;
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
        " select XT_YHIPYXXXB from PoXT_YHIPYXXXB as XT_YHIPYXXXB where (yhid =")
        .append(sYhid).append(" and ipdz ='").append(sIp).append("') ");
    if (!bFlag) {
      strBufHql.append(" or (").append(sYhid).append(
          " not in (select XT_YHIPYXXXB.yhid from PoXT_YHIPYXXXB as XT_YHIPYXXXB))");

    }
    try {
      //XT_YHIPYXXXBDAO Xt_yhipyxbDao = DAOFactory.createXT_YHIPYXXXBDAO();
      lstXtyhip = SpringContextHolder.getCommonService().queryAll(strBufHql.toString());
      if (lstXtyhip == null || lstXtyhip.isEmpty()) {
        return false;
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
    return true;
  }

  /**
   * 判断用户有无使用COUNT的权限
   */
  public int isCountZS(){
    Long yhid = super.getUser().getYhid() ;
    try {
      if (!XtywqxServiceImpl.VerifyUserFunction(yhid.toString(),
                                                PublicConstant.GNBH_XT_CXJLZS)) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                   "用户[[" + super.getUser().getYhdlm() +
                                   "]]没有显示记录总数功能操作权限", null);
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

    return 0;
  }

}
