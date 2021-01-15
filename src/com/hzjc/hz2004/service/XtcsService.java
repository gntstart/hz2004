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
 *                 系统参数操作接口</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public interface XtcsService extends XtService {

  /**
   *
   * @param pxQryXtcs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtcsxx(String sHqlXtcs)throws ServiceException,
    DAOException;
  public List getXtcsxx(String sHqlXtcs,int nPageOffset,int nPageSize)throws ServiceException,
     DAOException;
  public VoQueryResult getXtcsxx(VarVO vvHqlQryXtcs,String psWhere,VoPage voPage)throws ServiceException,
      DAOException;

  /**
   *增加系统参数
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtcsxx(VarVO vvXTCSB) throws ServiceException,DAOException;

  /**
   *修改系统参数
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtcsxgxx(VarVO vvXTCSB) throws ServiceException,DAOException;

  /**
   *删除系统参数
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtcsscxx(VarVO vvXTCSB) throws ServiceException,DAOException;
  /**
   * latest change date
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtcszdbdsj(VarVO vvXTCSB) throws ServiceException,DAOException;
}