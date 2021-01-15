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

public interface BdxgService
    extends WSService {
  /**
   * 修改迁入业务信息
   * @param poHJYW_QRDJXXB
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List QrywXg(PoHJYW_QRDJXXB poHJYW_QRDJXXB[]) throws ServiceException,
      DAOException;
  /**
   * 修改迁出业务信息
   * @param poHJYW_QCZXXXB
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List QcywXg(PoHJYW_QCZXXXB poHJYW_QCZXXXB[]) throws ServiceException,
      DAOException;
  /**
   * 修改出生业务信息
   * @param poHJYW_CSDJXXB
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List CsywXg(PoHJYW_CSDJXXB poHJYW_CSDJXXB[]) throws ServiceException,
      DAOException;
  /**
   * 修改死亡业务信息
   * @param poHJYW_CSDJXXB
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List SwywXg(PoHJYW_SWZXXXB poHJYW_SWZXXXB[]) throws ServiceException,
      DAOException;
  /**
   * 修改住址变动业务信息
   * @param poHJYW_CSDJXXB
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List ZzbdywXg(PoHJYW_ZZBDXXB poHJYW_ZZBDXXB[]) throws ServiceException,
      DAOException;

}
