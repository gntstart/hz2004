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
 *                 用户信息操作接口</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public interface XtyhService
    extends XtService {

  public List getXtdlyhxx() throws ServiceException,
      DAOException;

  /**
   * 查询用户信息
   * @param pxQryXtyh
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtyhxx(String sHqlXtyh) throws ServiceException,
      DAOException;

  public List getXtyhxx(String sHqlXtyh, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException;

  public VoQueryResult getXtyhxx(VarVO vvQryXtyh,String psWhere,VoPage voPage) throws ServiceException,
      DAOException;
  public List getXtyhmbxx(VarVO vvQryXtyh) throws ServiceException,
      DAOException;

//  public VoQueryResult getXtyhmbxx(VarVO vvXtparam, String psWhere,
 //                                  VoPage voPage) throws ServiceException,
 //     DAOException;

  /**
   *增加用户信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtyhxx(VarVO vvXtyh) throws ServiceException, DAOException;

  /**
   *修改用户信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtyhxgxx(VarVO vvXtyh) throws ServiceException, DAOException;

  /**
   *删除用户信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtyhscxx(VarVO vvXTyhB) throws ServiceException, DAOException;

  /**
   * 查询用户等同权限信息
   * @param pxQryXtyh
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtyhdtqxxx(String sHqlXtyh) throws ServiceException,
      DAOException;

  public List getXtyhdtqxxx(String sHqlXtyh, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException;

  public List getXtyhdtqxxx(VarVO vvQryXtyh) throws ServiceException,
      DAOException;

  /**
   * 增加用户等同权限信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtyhdtqxxx(VarVO vvXtyh) throws ServiceException, DAOException;

  /**
   *删除用户等同权限信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtyhdtqxscxx(VarVO vvXTyhB) throws ServiceException,
      DAOException;

  /**
   * 查询用户动作权限信息
   * @param pxQryXtyh
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtyhdzqxxx(String sHqlXtyh) throws ServiceException,
      DAOException;

  public List getXtyhdzqxxx(String sHqlXtyh, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException;

  public List getXtyhdzqxxx(VarVO vvQryXtyh) throws ServiceException,
      DAOException;

  /**
   * 增加用户动作权限信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtyhdzqxxx(VarVO vvXtyh) throws ServiceException, DAOException;

  /**
   *删除用户动作权限信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtyhdzqxscxx(VarVO vvXTyhB) throws ServiceException,
      DAOException;

  /**
   * 查询用户数据范围权限信息
   * @param pxQryXtyh
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtyhsjfwxx(String sHqlXtyh) throws ServiceException,
      DAOException;

  public List getXtyhsjfwxx(String sHqlXtyh, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException;

  public List getXtyhsjfwxx(VarVO vvQryXtyh) throws ServiceException,
      DAOException;

  /**
   * 增加用户数据范围信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtyhsjfwxx(VarVO vvXtyh) throws ServiceException, DAOException;

  /**
   *删除用户数据范围信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtyhsjfwscxx(VarVO vvXTyhB) throws ServiceException,
      DAOException;

  /**
   * 查询用户角色信息
   * @param pxQryXtyh
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtyhjsxx(String sHqlXtyh) throws ServiceException,
      DAOException;

  public List getXtyhjsxx(String sHqlXtyh, int nPageOffset, int nPageSize) throws
      ServiceException,
      DAOException;

  public List getXtyhjsxx(VarVO vvQryXtyh) throws ServiceException,
      DAOException;

  /**
   *增加用户角色信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtyhjsxx(VarVO vvXtyh) throws ServiceException, DAOException;

  /**
   *修改用户角色信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtyhjsxgxx(VarVO vvXtyh) throws ServiceException, DAOException;

  /**
   *删除用户角色信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtyhjsscxx(VarVO vvXTyhB) throws ServiceException, DAOException;

  /**
   * 获取用户居委会信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtyhjwhxx(VarVO vvXTyhB) throws ServiceException, DAOException;

  /**
   * 获取用户jlx居委会dz信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult getXtyhjlxjwhdzxx(VarVO vvXtparam, String psWhere,
                                   VoPage voPage) throws ServiceException,
      DAOException;

  /**
   * 获取系统服务器时间
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public String getXtfwqsj(VarVO vvXTyhB) throws ServiceException,
      DAOException;

}
