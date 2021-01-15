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
 *                 审批模板操作接口</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public interface XtspmbService extends XtService {

  /**
   *
   * @param pxQryXtspmb
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtspmbxx(String sHqlXtspmb)throws ServiceException,
    DAOException;
  public List getXtspmbxx(String sHqlXtspmb,int nPageOffset,int nPageSize)throws ServiceException,
     DAOException;
  public List getXtspmbxx(VarVO vvQryXtspmb)throws ServiceException,
      DAOException;

  /**
   *增加审批模板
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtspmbxx(VarVO vvXTspmbB) throws ServiceException,DAOException;

  /**
   *修改审批模板
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtspmbxgxx(VarVO vvXTspmbB) throws ServiceException,DAOException;

  /**
   *删除审批模板
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtspmbscxx(VarVO vvXTspmbB) throws ServiceException,DAOException;

}
