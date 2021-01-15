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
 *                 用户IP允许操作接口</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public interface XtyhipyxService extends XtService {

  /**
   * 查询用户IP允许查询
   * @param pxQryXtcs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtyhipyxxx(String sHql)throws ServiceException,
    DAOException;
  public List getXtyhipyxxx(String sHql,int nPageOffset,int nPageSize)throws ServiceException,
     DAOException;
  public List getXtyhipyxxx(VarVO vvQryXtyhipyx)throws ServiceException,
      DAOException;

  /**
   *增加
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtyhipyxxx(VarVO vvXTYHIPYXB) throws ServiceException,DAOException;
  /**
   *修改
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtyhipyxxgxx(VarVO vvXTYHIPYXB) throws ServiceException,DAOException;
/**
   * 删除
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtyhipyxscxx(VarVO vvXTYHIPYXB) throws ServiceException,DAOException;
  public List setXtyhipyxxxNew(VarVO vvXTYHIPYXB) throws ServiceException,DAOException;

}
