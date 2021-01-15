package com.hzjc.hz2004.service;

import com.hzjc.wsstruts.service.WSService;
import com.hzjc.hz2004.vo.*;
import com.hzjc.wsstruts.vo.*;
import com.hzjc.wsstruts.exception.ServiceException;
import com.hzjc.wsstruts.exception.DAOException;
import com.hzjc.wsstruts.exception.WSErrCode;

/**
 * 地址业务类
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public interface DzService
    extends WSService {

  /**
   *
   * @param voDzdjzjxx
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoDzdjzjywfhxx processDzdjzjyw(VoDzdjzjxx voDzdjzjxx[]) throws
      ServiceException, DAOException;

  /**
   *
   * @param voDzscxx
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoDzscywfhxx processDzscyw(VoDzscxx voDzscxx[]) throws
      ServiceException, DAOException;

  /**
   *
   * @param voDzxgxx
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoDzxgywfhxx processDzxgyw(VoDzxgxx voDzxgxx[]) throws
      ServiceException, DAOException;

  /**
   *
   * @param voDzzjxx
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoDzzjywfhxx processDzzjyw(VoDzzjxx voDzzjxx[]) throws
      ServiceException, DAOException;

  /**
   *
   * @param voDzzxxx
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoDzzxywfhxx processDzzxyw(VoDzzxxx voDzzxxx[]) throws
      ServiceException, DAOException;

  /**
   *
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryDzxx(String strHQL, VoPage voPage) throws
      ServiceException, DAOException;

  /**
   *
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryDzdjxx(String strHQL, VoPage voPage) throws
      ServiceException, DAOException;

  /**
   *
   * @param voDzdjscxx
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoDzdjscywfhxx processDzdjscyw(VoDzdjscxx voDzdjscxx[]) throws
      ServiceException, DAOException;

  /**
   *
   * @param voDzdjxgxx
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoDzdjxgywfhxx processDzdjxgyw(VoDzdjxgxx voDzdjxgxx[]) throws
      ServiceException, DAOException;

  /**
   * 处理区划调整业务
   * @param VoQhtzxx[] - 区划调整信息
   * @return VoQhtzywfhxx
   */
  public VoQhtzywfhxx processQhtzyw(VoQhtzxx voQhtzxx[]) throws
      ServiceException, DAOException;

  /**
   * 处理区划调整业务，按户调整
   * @param VoQhtzxx[] - 区划调整信息
   * @return VoQhtzywfhxx
   */
  public VoQhtzywfhxx processQhtzywAHTZ(VoQhtzxx voQhtzxx[]) throws
      ServiceException, DAOException;

  /**
   *
   * @param strHQL
   * @param voPage
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQueryResult queryHgxxx(String strHQL, VoPage voPage) throws
      DAOException, ServiceException;

  /**
   * 处理户关系删除业务
   * @param voHgxscxx - 户关系删除信息
   * @return - 户关系删除业务返回信息
   * @throws DAOException
   * @throws ServiceException
   */
  public VoHgxscywfhxx processHgxscyw(VoHgxscxx voHgxscxx[]) throws
      DAOException, ServiceException;

  /**
   * 处理户关系增加业务
   * @param voHgxzjxx - 户关系增加信息
   * @return - 户关系增加业务返回信息
   * @throws DAOException
   * @throws ServiceException
   */
  public VoHgxzjywfhxx processHgxzjyw(VoHgxzjxx voHgxzjxx[]) throws
      DAOException, ServiceException;

}
