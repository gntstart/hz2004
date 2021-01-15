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
import com.hzjc.hz2004.common.HjCommon;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 迁移设置操作实现</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */
@Service
public class XtqyszServiceImpl
    extends XtBaseService

    implements XtqyszService {

  PojoInfo   Xt_qyszbDao = DAOFactory.createXT_QYSZBDAO();
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
  public List getXtqyszxx(String strHQL) throws ServiceException,
      DAOException {
    //执行HQL语句
    return getXtqyszxx(strHQL, PublicConstant.XT_PAGE,
                       PublicConstant.XT_PAGESIZE);
  }

  public List getXtqyszxx(String strHQL, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    List lstXtqyszxx = null;
    List lstReturn = new ArrayList();
    PoXT_QYSZB poXT_QYSZB = null;
    PoXT_XZQHB poXT_XZQHBA = null;
    PoXT_XZQHB poXT_XZQHBB = null;
    PoXT_YHXXB poXT_YHXXBA = null;
    PoXT_YHXXB poXT_YHXXBB = null;
    Object[] objXtRtn = null;
    //执行HQL语句
    try {
      lstXtqyszxx =super.getPageRecords(strHQL, new Object[]{}, nPageOffset,
          nPageSize).getList();
      for (int i = 0; i < lstXtqyszxx.size(); i++) {
        objXtRtn = (Object[]) lstXtqyszxx.get(i);
        poXT_QYSZB = (PoXT_QYSZB) objXtRtn[0];
        poXT_XZQHBA = (PoXT_XZQHB) objXtRtn[1];
        poXT_XZQHBB = (PoXT_XZQHB) objXtRtn[2];
        poXT_YHXXBA = (PoXT_YHXXB) objXtRtn[3];
        poXT_YHXXBB = (PoXT_YHXXB) objXtRtn[4];
        VoXtqysz voXtqyszxx = new VoXtqysz(poXT_QYSZB, poXT_XZQHBA, poXT_XZQHBB,
                                           poXT_YHXXBA, poXT_YHXXBB);
        lstReturn.add(voXtqyszxx);
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
   *查询迁移设置信息
   * @param iReqPage
   * @param iPageSize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtqyszxx(VarVO vvQryXtqysz) throws ServiceException,
      DAOException {

    //获取参数信息
    int nPageOffset = PublicConstant.XT_PAGE; //分页
    int nPageSize = PublicConstant.PAGE_DEFAULT_SIZE;
    //获取参数信息
    String sQhdm = vvQryXtqysz.getVarValue("qhdm");
    String sQybz = vvQryXtqysz.getVarValue("qybz");

    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append(
        "select XT_QYSZB, XT_XZQHBA,XT_XZQHBB,XT_YHXXBA,XT_YHXXBB ")
        .append("FROM PoXT_QYSZB as XT_QYSZB,PoXT_XZQHB as XT_XZQHBA,")
        .append("PoXT_XZQHB as XT_XZQHBB,PoXT_YHXXB as XT_YHXXBA,PoXT_YHXXB as XT_YHXXBB  where ")
        .append(" XT_QYSZB.qhdma = XT_XZQHBA.dm ")
        .append(" and XT_QYSZB.qhdmb = XT_XZQHBB.dm ")
        .append(" and XT_QYSZB.cjrid = XT_YHXXBA.yhid")
        .append(" and XT_QYSZB.xgrid = XT_YHXXBB.yhid(+)");
    if ( (sQhdm != null) && ! ("".equals(sQhdm))) {
      strBufHql.append(" and  (XT_QYSZB.qhdma ='").append(sQhdm).append("'")
          .append(" or XT_QYSZB.qhdmb ='").append(sQhdm).append("')");
    }
    if ( (sQybz != null) && ! ("".equals(sQybz))) {
      strBufHql.append(" and  XT_QYSZB.qybz ='").append(sQybz).append("'");
    }

    return getXtqyszxx(strBufHql.toString(), nPageOffset, nPageSize);
  }

  /**
   *增加迁移设置信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtqyszxx(VarVO vvXtqyszb) throws ServiceException,
      DAOException {
    ArrayList lstXtqysz = new ArrayList();
    PoXT_QYSZB poXTQYSZB = new PoXT_QYSZB();
    PoXT_QYSZB rtnpoXTQYSZB = new PoXT_QYSZB();
    List lstXtqyszxx = null;
    //获取参数信息
    String sQhdma = vvXtqyszb.getVarValue("qhdma");
    String sQhdmb = vvXtqyszb.getVarValue("qhdmb");
    if (sQhdma == null || "".equals(sQhdma) || sQhdmb == null ||
        "".equals(sQhdmb)) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL, "数据输入不完整", null);
    }
    try {
      //判断迁移设置是否增加
      StringBuffer strBufHql = new StringBuffer();
      strBufHql.append("select XT_QYSZB from PoXT_QYSZB as XT_QYSZB where ")
          .append(" (qhdma = '").append(sQhdma).append("' and qhdmb='").append(
          sQhdmb)
          .append("')").append(" or (qhdma = '")
          .append(sQhdmb).append("' and qhdmb='").append(sQhdma).append("')");

      lstXtqyszxx =super.getPageRecords(strBufHql.toString(),new Object[]{},
          PublicConstant.XT_PAGE, PublicConstant.XT_PAGESIZE).getList();
      if (lstXtqyszxx != null && lstXtqyszxx.size() > 0) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                   "数据已经存在", null);
      }

      BeanUtils.populate(poXTQYSZB, vvXtqyszb.getVarmap());
      poXTQYSZB.setQyid( (Long) Xt_qyszbDao.getId());
      poXTQYSZB.setCjrid(this.getUserInfo().getYhid());
      poXTQYSZB.setQybz(PublicConstant.XT_BZ_QY);
      poXTQYSZB.setCjsj(StringUtils.getServiceTime());
      rtnpoXTQYSZB =(PoXT_QYSZB)super.create(poXTQYSZB);
      lstXtqysz.add(rtnpoXTQYSZB);
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
    return lstXtqysz;
  }

  /**
   *修改迁移设置信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtqyszxgxx(VarVO vvXtqyszb) throws ServiceException,
      DAOException {
    ArrayList lstXtqysz = new ArrayList();
    PoXT_QYSZB poXTQYSZB = null;
    PoXT_QYSZB rtnpoXTQYSZB = new PoXT_QYSZB();
    //获取参数信息
    //sqid,qhdm,qybz,bdlx,bdsj;
    //组合poXT_XtbssqB
    //执行HQL语句
    try {
      poXTQYSZB  = super.get(PoXT_QYSZB.class,Long.valueOf(vvXtqyszb.
          getVarValue("qyid")));
      if (poXTQYSZB == null) {
        return null;
      }
      BeanUtils.populate(poXTQYSZB, vvXtqyszb.getVarmap());
      poXTQYSZB.setXgrid(this.getUserInfo().getYhid());
      poXTQYSZB.setXgsj(StringUtils.getServiceTime());
      rtnpoXTQYSZB =   (PoXT_QYSZB)super.update(poXTQYSZB);
      lstXtqysz.add(rtnpoXTQYSZB);
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
    return lstXtqysz;
  }

  /**
   *删除迁移设置信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtqyszscxx(VarVO vvXtqyszb) throws ServiceException,
      DAOException {
    PoXT_QYSZB poXTQYSZB = null;
    PoXT_QYSZB rtnpoXTQYSZB = null;
    List lstXtRtn = new ArrayList();
    //组合poXT_XTqyszB
    try {
      poXTQYSZB  = super.get(PoXT_QYSZB.class,Long.valueOf(vvXtqyszb.
          getVarValue("qyid")));
      if (poXTQYSZB == null) {
        return null;
      }
      poXTQYSZB.setQybz(PublicConstant.XT_BZ_BQY);
      rtnpoXTQYSZB =    (PoXT_QYSZB)super.update(poXTQYSZB);
      lstXtRtn.add(rtnpoXTQYSZB);
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
