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
 *                 变更控制操作接口</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public interface XtbgkzService extends XtService {

  /**
   *
   * @param pxQryXtbgkz
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtbgdykzxx(String sHqlXtbgkz)throws ServiceException,
    DAOException;
  public List getXtbgdykzxx(String sHqlXtbgkz,int nPageOffset,int nPageSize)throws ServiceException,
     DAOException;
  public List getXtbgdykzxx(VarVO vvHqlQryXtbgkz)throws ServiceException,
      DAOException;

  /**
   *增加变打印更控制
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtbgdykzxx(VarVO vvXTbgkzB) throws ServiceException,DAOException;

  /**
   *修改变更打印控制
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtbgdykzxgxx(VarVO vvXTbgkzB) throws ServiceException,DAOException;

  /**
   *删除变更打印控制
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtbgdykzscxx(VarVO vvXTbgkzB) throws ServiceException,DAOException;
  /**
   *
   * @param pxQryXtbgkz
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtbgspkzxx(String sHqlXtbgkz)throws ServiceException,
    DAOException;
  public List getXtbgspkzxx(String sHqlXtbgkz,int nPageOffset,int nPageSize)throws ServiceException,
     DAOException;
  public List getXtbgspkzxx(VarVO vvHqlQryXtbgkz)throws ServiceException,
      DAOException;

  /**
   *增加变打印更控制
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtbgspkzxx(VarVO vvXTbgkzB) throws ServiceException,DAOException;

  /**
   *修改变更打印控制
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtbgspkzxgxx(VarVO vvXTbgkzB) throws ServiceException,DAOException;

  /**
   *删除变更打印控制
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtbgspkzscxx(VarVO vvXTbgkzB) throws ServiceException,DAOException;
}
