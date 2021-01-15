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
 *                 审批动作操作接口</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public interface XtspdzService extends XtService {

  /**
   *
   * @param pxQryXtspdz
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtspdzxx(String sHqlXtspdz)throws ServiceException,
    DAOException;
  public List getXtspdzxx(String sHqlXtspdz,int nPageOffset,int nPageSize)throws ServiceException,
     DAOException;
  public List getXtspdzxx(VarVO vvQryXtspdz)throws ServiceException,
      DAOException;

  /**
   *增加审批动作
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtspdzxx(VarVO vvXTSPDZB) throws ServiceException,DAOException;

  /**
   *修改审批动作
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtspdzxgxx(VarVO voXTSPDZB) throws ServiceException,DAOException;

  /**
   *删除审批动作
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtspdzscxx(VarVO vvXTSPDZB) throws ServiceException,DAOException;

}
