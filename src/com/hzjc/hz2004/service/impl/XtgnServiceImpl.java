package com.hzjc.hz2004.service.impl;

import java.util.*;

import org.springframework.stereotype.Service;

import com.hzjc.hz2004.dao.*;
import com.hzjc.hz2004.po.*;
import com.hzjc.hz2004.service.*;
import com.hzjc.hz2004.vo.*;
import com.hzjc.wsstruts.exception.*;
import com.hzjc.wsstruts.type.*;
import com.hzjc.hz2004.constant.*;
import com.hzjc.wsstruts.vo.*;

/**
 * <p>Title: HZ2004</p>
 * <p>Description:  系统功能菜单操作实现
 *                  常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */
@Service
public class XtgnServiceImpl
    extends XtBaseService

    implements XtgnService {

  //定义系统功能菜单的dao
  PojoInfo   Xt_XtgncdbDao = DAOFactory.createXT_XTGNCDBDAO();

  /**
   * 查询系统功能菜单信息
   * @param pxQryXtcs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtgncdxx(VarVO vvQryXtcs) throws ServiceException,
      DAOException {
    List lstXtcsxx = null;
    int nPageOffset = PublicConstant.XT_PAGE; //分页
    int nPageSize = PublicConstant.XT_PAGESIZE;
    String sYhid = vvQryXtcs.getVarValue("yhid");
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
      PojoInfo  Xt_yhjsxxbDao = DAOFactory.createXT_YHJSXXBDAO();
      lstXtyhjs =super.findAllByHQL(strBufHql.toString());
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
    //HQL条件增加
    /*    if (bCjyh) {
           sHql = "select XT_XTGNCDB FROM PoXT_XTGNCDB as XT_XTGNCDB";
        }
        else{
          sHql = "select XT_XTGNCDB FROM PoXT_XTGNCDB as XT_XTGNCDB where XT_XTGNCDB.gncdid in (";
          sHql = sHql + " select XT_JSCDQXB.gncdid from PoXT_JSCDQXB as XT_JSCDQXB where  XT_JSCDQXB.jsid in (";
         sHql = sHql + " select XT_YHJSXXB.jsid from PoXT_YHJSXXB as XT_YHJSXXB ";
          sHql = sHql + " where  XT_YHJSXXB.yhid =" + sYhid + "))";
        }
     */
    strBufHql.setLength(0);
    strBufHql.append("select distinct XT_XTGNCDB FROM PoXT_XTGNCDB as XT_XTGNCDB,PoXT_JSCDQXB as XT_JSCDQXB,")
        .append(
        "PoXT_YHJSXXB as XT_YHJSXXB where XT_XTGNCDB.gncdid = XT_JSCDQXB.gncdid(+) ")
        .append(" and XT_JSCDQXB.jsid = XT_YHJSXXB.jsid(+)");
    if (!bCjyh) {
      strBufHql.append(" and XT_YHJSXXB.yhid = ").append(sYhid);
    }
    strBufHql.append(" order by XT_XTGNCDB.gncdid ");
    //执行查询功能菜单
    return getXtgncdxx(strBufHql.toString(), nPageOffset, nPageSize);
  }

  /**  通过字符串查询
   * 查询系统功能菜单信息
   * @param pxQryXtcs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtgncdxx(String sHqlXtgncd, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    //执行查询功能菜单
    List lstXtRtn = null;
    try {
      lstXtRtn =super.getPageRecords(sHqlXtgncd, new Object[]{}, nPageOffset,
          nPageSize).getList();
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
   * 查询系统功能信息
   * @param pxQryXtcs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtgnxx(VarVO vvQryXtcs) throws ServiceException,
      DAOException {
    List lstXtcsxx = null;
    int nPageOffset = PublicConstant.XT_PAGE; //分页
    int nPageSize = PublicConstant.XT_PAGESIZE;
    String sQybz = vvQryXtcs.getVarValue("qybz");

    //HQL条件语句
    StringBuffer strBufHql = new StringBuffer();
    strBufHql.append("select XT_XTGNB FROM PoXT_XTGNB as XT_XTGNB where 1=1 ");
    if ( (sQybz != null) && ! ("".equals(sQybz))) {
      strBufHql.append(" and  qybz ='").append(sQybz).append("'");
    }
    //执行查询功能菜单
    return getXtgnxx(strBufHql.toString(), nPageOffset, nPageSize);
  }

  /**  通过字符串查询
   * 查询系统功能菜单信息
   * @param pxQryXtcs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtgnxx(String sHqlXtgn, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException {
    //执行查询功能菜单
    List lstXtRtn = null;
    try {
      PojoInfo  Xt_XtgnbDao = DAOFactory.createXT_XTGNBDAO();
      lstXtRtn =super.getPageRecords(sHqlXtgn, new Object[]{}, nPageOffset,
                                                  nPageSize).getList();
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
