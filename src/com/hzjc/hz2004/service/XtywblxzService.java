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
 *                 业务办理限制操作接口</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public interface XtywblxzService extends XtService {

  /**
   * 查业务办理限制
   * @param pxQryXtywblxz
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtywblxzxx(String sHqlXtywblxz)throws ServiceException,
    DAOException;
  public List getXtywblxzxx(String sHqlXtywblxz,int nPageOffset,int nPageSize)throws ServiceException,
     DAOException;
  public List getXtywblxzxx(VarVO vvQryXtywblxz)throws ServiceException,
      DAOException;

  /**
   *增加业务办理限制
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtywblxzxx(VarVO vvXTywblxzB) throws ServiceException,DAOException;

  /**
   *修改业务办理限制
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtywblxzxgxx(VarVO vvXTywblxzB) throws ServiceException,DAOException;

  /**
   *删除业务办理限制
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtywblxzscxx(VarVO vvXTywblxzB) throws ServiceException,DAOException;


}
