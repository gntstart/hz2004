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
 *                 用户角色操作接口</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public interface XtjsService extends XtService {

  /**
   *
   * @param pxQryXtjs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtjsxx(String sHqlXtjs)throws ServiceException,
    DAOException;
  public List getXtjsxx(String sHqlXtjs,int nPageOffset,int nPageSize)throws ServiceException,
     DAOException;
  public List getXtjsxx(VarVO vvHqlQryXtjs)throws ServiceException,
      DAOException;

  /**
   *增加用户角色
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjsxx(VarVO vvXTJSB) throws ServiceException,DAOException;

  /**
   *修改用户角色
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjsxgxx(VarVO vvXTjsB) throws ServiceException,DAOException;

  /**
   *删除用户角色
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtjsscxx(VarVO vvXTjsB) throws ServiceException,DAOException;
  /**
   * 查询角色业务报表权限
   * @param pxQryXtjs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtjsywbbxx(String sHqlXtjs)throws ServiceException,
    DAOException;
  public List getXtjsywbbxx(String sHqlXtjs,int nPageOffset,int nPageSize)throws ServiceException,
     DAOException;
  public List getXtjsywbbxx(VarVO vvHqlQryXtjs)throws ServiceException,
      DAOException;

  /**
   *增加角色业务报表
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjsywbbxx(VarVO vvXTJSB) throws ServiceException,DAOException;

  /**
   *修改角色业务报表
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjsywbbxgxx(VarVO vvXTjsB) throws ServiceException,DAOException;

  /**
   *删除角色业务报表
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtjsywbbscxx(VarVO vvXTjsB) throws ServiceException,DAOException;
  /**
   * 查询角色制式报表权限
   * @param pxQryXtjs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtjszsbbxx(String sHqlXtjs,int nPageOffset,int nPageSize)throws ServiceException,
     DAOException;
  public List getXtjszsbbxx(VarVO vvHqlQryXtjs)throws ServiceException,
      DAOException;

  /**
   *增加角色制式报表
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjszsbbxx(VarVO vvXTJSB) throws ServiceException,DAOException;

  /**
   *修改角色制式报表
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjszsbbxgxx(VarVO vvXTjsB) throws ServiceException,DAOException;

  /**
   *删除角色制式报表
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtjszsbbscxx(VarVO vvXTjsB) throws ServiceException,DAOException;
  /**
    * 查询角色功能权限
    * @param pxQryXtjs
    * @return
    * @throws ServiceException
    * @throws DAOException
    */
   public List getXtjsgnqxxx(String sHqlXtjs)throws ServiceException,
     DAOException;
   public List getXtjsgnqxxx(String sHqlXtjs,int nPageOffset,int nPageSize)throws ServiceException,
      DAOException;
   public List getXtjsgnqxxx(VarVO vvHqlQryXtjs)throws ServiceException,
       DAOException;

   /**
    *增加角色功能权限
    * @param sParamXml
    * @return
    * @throws ServiceException
    * @throws DAOException
    */
   public List setXtjsgnqxxx(VarVO vvXTJSB) throws ServiceException,DAOException;

   /**
    *修改角色功能权限
    * @param sParamXml
    * @return
    * @throws ServiceException
    * @throws DAOException
    */
   public List setXtjsgnqxxgxx(VarVO vvXTjsB) throws ServiceException,DAOException;

   /**
    *删除角色功能权限
    * @param sParamXml
    * @return
    * @throws ServiceException
    * @throws DAOException
    */
   public int setXtjsgnqxscxx(VarVO vvXTjsB) throws ServiceException,DAOException;
   /**
      * 查询角色菜单权限
      * @param pxQryXtjs
      * @return
      * @throws ServiceException
      * @throws DAOException
      */
     public List getXtjscdqxxx(String sHqlXtjs)throws ServiceException,
       DAOException;
     public List getXtjscdqxxx(String sHqlXtjs,int nPageOffset,int nPageSize)throws ServiceException,
        DAOException;
     public List getXtjscdqxxx(VarVO vvHqlQryXtjs)throws ServiceException,
         DAOException;

     /**
      *增加角色菜单权限
      * @param sParamXml
      * @return
      * @throws ServiceException
      * @throws DAOException
      */
     public List setXtjscdqxxx(VarVO vvXTJSB) throws ServiceException,DAOException;

     /**
      *修改角色菜单权限
      * @param sParamXml
      * @return
      * @throws ServiceException
      * @throws DAOException
      */
     public List setXtjscdqxxgxx(VarVO vvXTjsB) throws ServiceException,DAOException;

     /**
      *删除角色菜单权限
      * @param sParamXml
      * @return
      * @throws ServiceException
      * @throws DAOException
      */
     public int setXtjscdqxscxx(VarVO vvXTjsB) throws ServiceException,DAOException;

}
