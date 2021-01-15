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
 *                 迁移设置操作接口</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public interface XtqyszService extends XtService {

  /**
   *
   * @param pxQryXtqysz
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtqyszxx(String sHqlXtqysz)throws ServiceException,
    DAOException;
  public List getXtqyszxx(String sHqlXtqysz,int nPageOffset,int nPageSize)throws ServiceException,
     DAOException;
  public List getXtqyszxx(VarVO vvQryXtqysz)throws ServiceException,
      DAOException;

  /**
   *增加迁移设置
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtqyszxx(VarVO vvXTQYSZB) throws ServiceException,DAOException;

  /**
   *修改迁移设置
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtqyszxgxx(VarVO vvXTQYSZB) throws ServiceException,DAOException;

  /**
   *删除迁移设置
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtqyszscxx(VarVO vvXTQYSZB) throws ServiceException,DAOException;

}
