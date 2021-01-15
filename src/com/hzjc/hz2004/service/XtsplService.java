package com.hzjc.hz2004.service;

import com.hzjc.hz2004.vo.*;
import java.util.*;
import com.hzjc.hz2004.po.*;
import com.hzjc.wsstruts.exception.ServiceException;
import com.hzjc.wsstruts.exception.DAOException;
import com.hzjc.wsstruts.type.*;
import com.hzjc.wsstruts.vo.*;
import com.hzjc.wsstruts.service.WSService;
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.base.login.AuthToken;
/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 审批流操作接口</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public interface XtsplService extends XtService {

  /**
   *
   * @param pxQryXtspl
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtsplxx(String sHqlXtspl)throws ServiceException,
    DAOException;
  public List getXtsplxx(String sHqlXtspl,int nPageOffset,int nPageSize)throws ServiceException,
     DAOException;
  public List getXtsplxx(VarVO vvQryXtspl)throws ServiceException,
      DAOException;

  /**
   *增加审批流
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtsplxx(VarVO vvXTsplB) throws ServiceException,DAOException;

  /**
   *修改审批流
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtsplxgxx(VarVO vvXTsplB) throws ServiceException,DAOException;

  /**
   *删除审批流
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtsplscxx(VarVO vvXTsplB) throws ServiceException,DAOException;
  /**
   * 删除什么模板下的所有审批流信息
   * @param vo
   * @return
   */
public int setXtspmbsplscxx(VarVO vo); 
}
