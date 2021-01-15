package com.hzjc.hz2004.service;

import com.hzjc.hz2004.vo.*;
import java.util.*;
import com.hzjc.hz2004.po.*;
import com.hzjc.wsstruts.exception.ServiceException;
import com.hzjc.wsstruts.exception.DAOException;
import com.hzjc.wsstruts.type.*;
import com.hzjc.wsstruts.vo.*;
import com.hzjc.wsstruts.service.WSService;
import com.hzjc.hz2004.base.login.AuthToken;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 业务权限操作接口</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public interface XtywqxService extends XtService
    {

  /**
   *
   * @param pxQryXtywqx
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtywqxxx(String sHqlXtywqx) throws ServiceException,
      DAOException;

  public List getXtywqxxx(String sHqlXtywqx, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException;

  public List getXtywqxxx(VarVO vvHqlQryXtywqx) throws ServiceException,
      DAOException;

  /**
   *增加业务权限
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtywqxxx(VarVO vvXTywqxB) throws ServiceException,
      DAOException;

  /**
   *修改业务权限
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtywqxxgxx(VarVO vvXTywqxB) throws ServiceException,
      DAOException;

  /**
   *删除业务权限
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtywqxscxx(VarVO vvXTywqxB) throws ServiceException,
      DAOException;

  /**
   * 查询数据范围
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtsjfwxx(VarVO vvXTywqxB) throws ServiceException,
      DAOException;
  /**
   * 根据用户ID和业务名称查询数据范围
   * @param sYwid
   * @param sYwmc
   * @return
   */

  //public  List SelectDataRange(String sYhid, String sYwid) throws ServiceException, DAOException ;
  /**
   * 判断输入的用户和业务是否有LIST中存在的数据范围权限
   * @param sYhid
   * @param lstXtsjfw
   * @return
   */
  //public boolean VerifyDataRange(String sYhid, String sYwid, List lstXtsjfw) throws ServiceException, DAOException ;
  /**
   * 判断输入的用户和业务是否有LIST中存在的数据范围权限
   * @param sYhid
   * @param lstXtsjfw
   * @return
   */
  // public boolean VerifyBusinessLimit(String sYwid, DefaultVO dv) throws ServiceException, DAOException;

  /**
   * count权限查询
   * @throws ServiceException
   * @throws DAOException
   */
  public int isCountZS() throws ServiceException,  DAOException;
}
