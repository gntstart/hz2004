package com.hzjc.hz2004.service;

import com.hzjc.wsstruts.service.WSService;
import java.util.*;
import com.hzjc.wsstruts.exception.*;
import com.hzjc.hz2004.po.*;
import com.hzjc.wsstruts.vo.*;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 地址追加操作接口</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public interface DzzjService
    extends WSService {
  /**
   * 注册操作员
   * @param poCzy
   * @param poSb
   * @param psGlyId
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List RegisterOperater(PoDZZJ_CZYXXB poCzy[], PoDZZJ_SBXXB poSb[],
                               String psGlyId) throws ServiceException,
      DAOException;

  /**
   * 操作员认证
   * @param poCzy
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List LoginOperater(PoDZZJ_CZYXXB poCzy[]) throws ServiceException,
      DAOException;

  /**
   * 删除操作员
   * @param poCzy
   * @param psGlyId
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List DeleteOperater(PoDZZJ_CZYXXB poCzy[], String psGlyId) throws
      ServiceException, DAOException;

  /**
   * 修改操作员密码
   * @param poCzy
   * @param psGlyId
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List ModiPassword(PoDZZJ_CZYXXB poCzy[], String psCzyId) throws
      ServiceException, DAOException;
  /**
   * 更换管理员
   * @param poCzy
   * @param psGhCzyId
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List ChangeAdmin(CdsVO cdsVo[]) throws ServiceException, DAOException;
  /**
   * 解锁
   * @param psCzyId
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List FreeLock(List lstCzyId) throws ServiceException, DAOException;

  /**
   * 查询操作员信息
   * @param sSql
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult QryOperater(String psSql, VoPage voPage) throws
      ServiceException, DAOException;

  /**
   *地址追加业务
   * @param cdsVo
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List DzzjYw(CdsVO cdsVo[]) throws ServiceException, DAOException;

  /**
   * 查询地址追加信息
   * @param sSql
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult QryDzzjxx(String psSql, VoPage voPage) throws
      ServiceException, DAOException;
  /**
   * 查询卡体管理日志
   * @param sSql
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult QryCzrzxx(String psSql, VoPage voPage) throws
      ServiceException, DAOException;

}