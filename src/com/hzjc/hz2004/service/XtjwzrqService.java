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
 *                 警务责任区操作接口</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public interface XtjwzrqService extends XtService {

  /**
   *
   * @param pxQryXtcs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtjwzrqxx(String sHqlXtjwzrq)throws ServiceException,DAOException;
  public VoQueryResult getXtjwzrqxx(VarVO vvQryXtjwzrq,String psWhere,VoPage voPage)throws ServiceException,
      DAOException;
  public List getXtjwzrqxx(String sHqlXtjwzrq,int nPageOffset,int nPageSize)throws ServiceException,
       DAOException;

  /**
   *增加
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjwzrqxx(VarVO vvXTJWZRQB) throws ServiceException,DAOException;
  /**
   *修改
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjwzrqxgxx(VarVO vvXTJWZRQB) throws ServiceException,DAOException;
/**
   * 删除
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjwzrqscxx(VarVO vvXTJWZRQB) throws ServiceException,DAOException;
  /**
   * 得到最大变动时间
   * @param lstXTJWZRQB
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtjwzrqzdbdsj() throws ServiceException,DAOException;
}
