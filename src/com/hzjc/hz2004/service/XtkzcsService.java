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
 *                 系统控制参数操作接口</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public interface XtkzcsService extends XtService {

  /**
   *查询系统控制参数信息
   * @param pxQryXtkzcs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtkzcsxx(String sHqlXtkzcs)throws ServiceException,
       DAOException;
  public List getXtkzcsxx(String sHqlXtkzcs,int nPageOffset,int nPageSize)throws ServiceException,
       DAOException;
  public List getXtkzcsxx(VarVO vvQryXtkzcs)throws ServiceException,
      DAOException;

  /**
   *修改系统控制参数
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtkzcsxgxx(VarVO vvXTKZCSB) throws ServiceException,DAOException;

  /**
   * 最大变动时间
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtkzcszdbdsj() throws ServiceException,DAOException;

}
