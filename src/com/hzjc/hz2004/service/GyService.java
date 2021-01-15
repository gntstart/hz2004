package com.hzjc.hz2004.service;

import com.hzjc.wsstruts.type.PackXQL;
import com.hzjc.wsstruts.exception.ServiceException;
import com.hzjc.wsstruts.exception.DAOException;
import com.hzjc.wsstruts.exception.WSErrCode;
import com.hzjc.wsstruts.service.WSService;
import com.hzjc.wsstruts.vo.*;
import com.hzjc.hz2004.vo.*;

/**
 *公用业务类
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public interface GyService
    extends WSService {

  /**
   *
   * @param hhnbid
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public java.util.List queryHdxx(Long hhnbid) throws ServiceException,
      DAOException;

  /**
   *
   * @param mlpnbid
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public Integer getDzdjztByMlpnbID(Long mlpnbid) throws ServiceException,
      DAOException;

  /**
   *
   * @param rynbid
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public String getRyztByRynbID(Long rynbid) throws ServiceException,
      DAOException;

  /**
   *
   * @param mlpnbid
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public Boolean getDzlxsdztByMlpnbID(Long mlpnbid) throws ServiceException,
      DAOException;

  /**
   *
   * @param ZpID
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoHJXX_RYZPXXB queryRyzp(Long ZpID) throws ServiceException,
      DAOException;

  /**
   *
   * @param hhnbid
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryHcyxxEx(Long hhnbid, String strHQL, VoPage voPage) throws
      ServiceException, DAOException;

  /**
   *
   * @param strHQL
   * @param offset
   * @param pagesize
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryRyxx(String strHQL, VoPage voPage, String ywbz) throws
      ServiceException,
      DAOException;

  /**
   *
   * @param hhnbid
   * @param rynbid
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryHcyxx(Long hhnbid, Long rynbid, String strHQL,
                                  VoPage voPage) throws
      ServiceException,
      DAOException;

  /**
   *
   * @param rynbid
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public Long getGzdxpdByRynbID(Long rynbid) throws ServiceException,
      DAOException;

  /**
   *
   * @param tn
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryTable(String tn, String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException;

}
