package com.hzjc.hz2004.service;

import com.hzjc.hz2004.vo.*;
import java.util.*;
import com.hzjc.wsstruts.exception.ServiceException;
import com.hzjc.wsstruts.exception.DAOException;
import com.hzjc.wsstruts.type.*;
import com.hzjc.wsstruts.service.WSService;
import com.hzjc.wsstruts.vo.*;
/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 系统功能菜单操作接口</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public interface XtgnService extends XtService {

  /**
   * 查询系统功能菜单信息
   * @param pxQryXtcs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtgncdxx(VarVO vvQryXtgncd) throws ServiceException,
      DAOException;
//  public List getXtgncdxx(VarVO vvQryXtgncd) throws ServiceException,
//      DAOException;
  //通过字符串查询
  public List getXtgncdxx(String sHqlXtgncd,int nPageOffset,int nPageSize) throws ServiceException,
       DAOException;
   /**
    * 查询系统功能信息
    * @param pxQryXtcs
    * @return
    * @throws ServiceException
    * @throws DAOException
    */
   public List getXtgnxx(VarVO vvQryXtgncd) throws ServiceException,
       DAOException;

}