package com.hzjc.hz2004.service;

import com.hzjc.wsstruts.service.WSService;
import java.util.*;
import java.sql.*;
import com.hzjc.wsstruts.exception.*;
import com.hzjc.hz2004.po.*;
import com.hzjc.hz2004.vo.*;
import com.hzjc.wsstruts.vo.*;

public interface SmkService extends WSService {

  /**
   * 市民卡读卡信息保存
   * @param vodkxx VoSmkdkxxHq[]
   * @throws ServiceException
   * @throws DAOException
   * @return int
   */
  String processSmkdkxxbc(VoSmkdkxxHq[] vodkxx) throws ServiceException,
      DAOException ;

  /**
   * 市民卡读卡日志查询(PoHZSMK_DKRZ)
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  VoQueryResult querySmkdkxxcx(String strHQL, VoPage voPage) throws
      ServiceException, DAOException;

  /**
   * 市民卡写卡信息保存
   * @param strHQL String
   * @param psCdsVo CdsVO[]
   * @throws ServiceException
   * @throws DAOException
   */
  String  processSmkxkxxbc(VoSmkxkrzxx[] voSmkxx) throws
      ServiceException, DAOException;

  /**
   * 市民卡写卡日志查询
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  VoQueryResult querySmkxkxxcx(String strHQL, VoPage voPage) throws
      ServiceException,DAOException;

}
