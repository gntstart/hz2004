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
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.base.login.AuthToken;
import com.hzjc.hz2004.constant.*;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 审批流操作实现</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */
@Service
public class XtsplServiceImpl
    extends XtBaseService
    implements XtsplService {

  PojoInfo   Xt_mblcbDao = DAOFactory.createXT_MBLCXXBDAO();
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
  public List getXtsplxx(String strHQL) throws ServiceException,
      DAOException {
    return getXtsplxx(strHQL, PublicConstant.XT_PAGE,
                      PublicConstant.XT_PAGESIZE);
  }

  public List getXtsplxx(String strHQL, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    List lstXtsplxx = null;
    List lstReturn = new ArrayList();
    PoXT_MBLCXXB poXT_MBLCXXB = null;
    PoXT_SPDZB poXT_SPDZBA = null;
    PoXT_SPDZB poXT_SPDZBB = null;
    PoXT_SPMBXXB poXT_SPMBXXB = null;
    Object[] objXtRtn = null;
    //执行HQL语句
    try {
      lstXtsplxx =super.getPageRecords(strHQL, new Object[]{}, nPageOffset,
          nPageSize).getList();
      for (int i = 0; i < lstXtsplxx.size(); i++) {
        objXtRtn = (Object[]) lstXtsplxx.get(i);
        poXT_MBLCXXB = (PoXT_MBLCXXB) objXtRtn[0];
        poXT_SPMBXXB = (PoXT_SPMBXXB) objXtRtn[1];
        poXT_SPDZBA = (PoXT_SPDZB) objXtRtn[2];
        poXT_SPDZBB = (PoXT_SPDZB) objXtRtn[3];
        VoXtsplxx voXtsplxx = new VoXtsplxx(poXT_MBLCXXB, poXT_SPMBXXB,
                                            poXT_SPDZBA, poXT_SPDZBB);
        lstReturn.add(voXtsplxx);
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
   *查询审批流信息
   * @param iReqPage
   * @param iPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtsplxx(VarVO vvQryXtspl) throws ServiceException,
      DAOException {
    int nPageOffset = PublicConstant.XT_PAGE; //分页
    int nPageSize = PublicConstant.PAGE_DEFAULT_SIZE;
    //获取参数信息
    String sMblcid = vvQryXtspl.getVarValue("mblcid");
    String sSpmbid = vvQryXtspl.getVarValue("spmbid");

    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append("select XT_MBLCXXB, XT_SPMBXXB,XT_SPDZBA,XT_SPDZBB ")
        .append("FROM PoXT_MBLCXXB as XT_MBLCXXB,PoXT_SPDZB as XT_SPDZBA,")
        .append("PoXT_SPMBXXB as XT_SPMBXXB,PoXT_SPDZB as XT_SPDZBB  where ")
        .append(" XT_MBLCXXB.spmbid = XT_SPMBXXB.spmbid ")
        .append(" and XT_MBLCXXB.dzid = XT_SPDZBA.dzid")
        .append(" and XT_MBLCXXB.xgdzid = XT_SPDZBB.dzid");// .append(" and XT_MBLCXXB.xgdzid = XT_SPDZBB.dzid(+)");
    if ( (sMblcid != null) && ! ("".equals(sMblcid))) {
      strBufHql.append(" and  XT_MBLCXXB.mblcid =").append(sMblcid);
    }
    if ( (sSpmbid != null) && ! ("".equals(sSpmbid))) {
      strBufHql.append(" and  XT_MBLCXXB.spmbid =").append(sSpmbid);
    }
    return getXtsplxx(strBufHql.toString(), nPageOffset, nPageSize);
  }
  /**
   *增加审批流信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtsplxx(VarVO vvXtsplb) throws ServiceException, DAOException {
    ArrayList lstXtspl = new ArrayList();
    PoXT_MBLCXXB poXTMBLCB = new PoXT_MBLCXXB();
    PoXT_MBLCXXB rtnpoXTMBLCB = new PoXT_MBLCXXB();

    PoXT_MBLCXXB poXtsplxx = new PoXT_MBLCXXB();
    String sHql = "";
    List lstXtsplxx = null,lstXtspdzz = null;
    Long lDBspmbid, lDBdzid;
    String sDBdzz = "", sDBdzbz = "";
    //获取参数信息
    String sSpmbid = vvXtsplb.getVarValue("spmbid");
    String sDzbz = vvXtsplb.getVarValue("dzbz");
    String sDzz = vvXtsplb.getVarValue("dzz");
    String sDzid = vvXtsplb.getVarValue("dzid");
    try {
      BeanUtils.populate(poXTMBLCB, vvXtsplb.getVarmap());
      /**
       * 判断审批动作是否已经存在
       * 同模板ID，同动作ID，同动作值的不允许多条记录存在
       */
      sHql =
          " select XT_MBLCXXB from PoXT_MBLCXXB as XT_MBLCXXB where spmbid =" +
          sSpmbid + " and dzz ='"+sDzz+"' and dzid="+sDzid;
      lstXtspdzz =super.findAllByHQL(sHql);
      if (lstXtspdzz != null && lstXtspdzz.size() > 0) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                    "该动作已经存在", null);
      }

      /**
       * 判断模板开始动作
       */
      if (sSpmbid == null || sDzbz == null || sDzid == null || sDzz == null) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL, "数据输入不完整", null);
      }
      //根据模板ID查询审批流信息
      sHql =
          " select XT_MBLCXXB from PoXT_MBLCXXB as XT_MBLCXXB where spmbid =" +
          sSpmbid;
      lstXtsplxx =super.getPageRecords(sHql,new Object[]{},
          PublicConstant.XT_PAGE, PublicConstant.XT_PAGESIZE).getList();
      /**
       * 如果没有查询到信息，要判断参数的动作标志是不是最初始步,不是则返回，是则继续
       * 如果有记录，则判断参数的动作标志是不是最初始步，是则返回，不是则继续，因为不允许有多个最初始步
       * 如果有记录，模板ID，动作DI，动作值都相等则返回
       */

      if (lstXtsplxx == null || lstXtsplxx.size() <= 0) {

        if (!"0".equals(sDzbz) && !"1".equals(sDzbz)) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "没有最初始步", null);
        }
      }
      else {
        if ("0".equals(sDzbz) || "1".equals(sDzbz)) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "不允许多个最初始步", null);
        }
        poXtsplxx = (PoXT_MBLCXXB) lstXtsplxx.get(0);
        lDBspmbid = poXtsplxx.getSpmbid();
        lDBdzid = poXtsplxx.getDzid();
        sDBdzz = poXtsplxx.getDzz();
        sDBdzbz = poXtsplxx.getDzbz();
        if ( (sSpmbid.equals(String.valueOf(lDBspmbid)))
            && (sDzid.equals(String.valueOf(lDBdzid)))
            && (sDBdzz == sDzz)) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "数据库中已经存在相应数据", null);
        }
/*        if ("1".equals(sDBdzbz)) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "存在中间步数据", null);
        }
*/      }

      poXTMBLCB.setMblcid( (Long) Xt_mblcbDao.getId());
      rtnpoXTMBLCB =(PoXT_MBLCXXB)super.create(poXTMBLCB);
      lstXtspl.add(rtnpoXTMBLCB);
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
    return lstXtspl;
  }

  /**
   *修改审批流信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtsplxgxx(VarVO vvXtsplb) throws ServiceException,
      DAOException {
    ArrayList lstXtspl = new ArrayList();
    String sHql = null;
    PoXT_MBLCXXB poXTMBLCB = new PoXT_MBLCXXB();
    PoXT_MBLCXXB rtnpoXTMBLCB = new PoXT_MBLCXXB();
    //获取参数信息
    try {
      poXTMBLCB  = super.get(PoXT_MBLCXXB.class,Long.valueOf(vvXtsplb.
          getVarValue("mblcid")));
      if (poXTMBLCB == null) {
        return null;
      }
      BeanUtils.populate(poXTMBLCB, vvXtsplb.getVarmap());
      rtnpoXTMBLCB =  (PoXT_MBLCXXB)super.update(poXTMBLCB);
      lstXtspl.add(rtnpoXTMBLCB);
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
    return lstXtspl;
  }

  /**
   *删除审批流信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtsplscxx(VarVO vvXtsplb) throws ServiceException, DAOException {
    PoXT_MBLCXXB poXTMBLCB = new PoXT_MBLCXXB();
    PoXT_MBLCXXB rtnpoXTMBLCB = new PoXT_MBLCXXB();
    int nReturn = PublicConstant.XT_BZ_ERROR;
    //组合poXT_XTsplB
    //获取参数信息
    String sMblcid = vvXtsplb.getVarValue("mblcid");
    String sSpmbid = vvXtsplb.getVarValue("spmbid");
    StringBuffer sbSql = new StringBuffer();
    List lstXtspmbxx;
    String sUseNum;
    try {
      //判断此审批流是否在使用中
      PojoInfo  Xt_spmbbDao = DAOFactory.createXT_SPMBXXBDAO();
      sbSql.append(
          "select XT_SPMBXXB from PoXT_SPMBXXB as XT_SPMBXXB,PoXT_MBLCXXB")
          .append(" as XT_MBLCXXB where XT_SPMBXXB.spmbid = XT_MBLCXXB.spmbid");
      if (!"".equals(sMblcid)  && sMblcid != null)
          sbSql.append(" and XT_MBLCXXB.mblcid=").append(sMblcid);
      if (!"".equals(sSpmbid)  && sSpmbid != null)
            sbSql.append(" and XT_SPMBXXB.spmbid=").append(sSpmbid);

      lstXtspmbxx =super.findAllByHQL(sbSql.toString());
      sUseNum = ( (PoXT_SPMBXXB) lstXtspmbxx.get(0)).getDqsys().trim();
      if (!sUseNum.equals("0") && (sUseNum != null) && !"".equals(sUseNum)) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                   "模板的当前使用数不为0", null);
      }
      else {
        StringBuffer strBufHql = new StringBuffer();
        strBufHql.append(" from PoXT_MBLCXXB where 1=1 ");

        if ( (sMblcid != null) && ! ("".equals(sMblcid))) {
          strBufHql.append(" and  mblcid =").append(sMblcid);
        }
        if ( (sSpmbid != null) && ! ("".equals(sSpmbid))) {
          strBufHql.append(" and  spmbid =").append(sSpmbid);
        }
        nReturn = super.deleteAll(super.findAllByHQL(strBufHql.toString()));
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
    return nReturn;
  }
  /**
   * 删除什么模板下的所有审批流信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtspmbsplscxx(VarVO vvXtsplb) throws ServiceException, DAOException {
    PoXT_MBLCXXB poXTMBLCB = new PoXT_MBLCXXB();
    PoXT_MBLCXXB rtnpoXTMBLCB = new PoXT_MBLCXXB();
    int nReturn = PublicConstant.XT_BZ_ERROR;
    //组合poXT_XTsplB
    //获取参数信息
    String sSpmbid = vvXtsplb.getVarValue("spmbid");
    StringBuffer sbSql = new StringBuffer();
    List lstXtspmbxx;
    String sUseNum;
    try {
      //判断此审批流是否在使用中
      PojoInfo  Xt_spmbbDao = DAOFactory.createXT_SPMBXXBDAO();
      sbSql.append(
          "select XT_SPMBXXB from PoXT_SPMBXXB as XT_SPMBXXB,PoXT_MBLCXXB")
          .append(" as XT_MBLCXXB where XT_SPMBXXB.spmbid = XT_MBLCXXB.spmbid");
      if (!"".equals(sSpmbid)  && sSpmbid != null)
            sbSql.append(" and XT_SPMBXXB.spmbid=").append(sSpmbid);

      lstXtspmbxx =super.findAllByHQL(sbSql.toString());
      sUseNum = ( (PoXT_SPMBXXB) lstXtspmbxx.get(0)).getDqsys().trim();
      if (!sUseNum.equals("0") && (sUseNum != null) && !"".equals(sUseNum)) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                   "模板的当前使用数不为0", null);
      }
      else {
        StringBuffer strBufHql = new StringBuffer();
        strBufHql.append(" from PoXT_MBLCXXB where 1=1 ");

        if ( (sSpmbid != null) && ! ("".equals(sSpmbid))) {
          strBufHql.append(" and  spmbid =").append(sSpmbid);
        }
        nReturn = super.deleteAll(super.findAllByHQL(strBufHql.toString()));
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
    return nReturn;
  }
}
