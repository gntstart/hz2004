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

public interface LssfzService
    extends WSService {
  /**
   * 临时身份证受理业务
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List LssfzSlyw(CdsVO psCdsVo[]) throws ServiceException,
      DAOException;
  /**
   * 临时身份证受理信息查询
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult LsslQry(String strHQL, VoPage voPage) throws ServiceException,
      DAOException;
  /**
   * 临时身份证打印保存
   * @param psCdsVo
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List LssfzDybc(CdsVO psCdsVo[]) throws ServiceException,
      DAOException;
  /**
   * 临时身份证受理信息修改
   * @param psCdsVo
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List LsslxxXg(CdsVO psCdsVo[]) throws ServiceException,
      DAOException;
  /**
   * 临时身份证受理信息作废
   * @param psCdsVo
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List LsslxxZf(CdsVO psCdsVo[]) throws ServiceException,
      DAOException;

}
