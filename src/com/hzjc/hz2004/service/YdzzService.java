package com.hzjc.hz2004.service;

import com.hzjc.wsstruts.service.WSService;
import java.util.*;
import java.sql.*;
import com.hzjc.wsstruts.exception.*;
import com.hzjc.hz2004.po.*;
import com.hzjc.hz2004.vo.*;
import com.hzjc.wsstruts.vo.*;

public interface YdzzService extends WSService{

  /**
   * 浙江异地二代证业务保存
   * @param voYdcjhqxx VoYdcjhqxx[]
   * @throws Exception
   * @return Long
   */
  public String processYdzzxxSave(VoYdcjhqxx[] voYdcjhqxx) throws
      ServiceException, DAOException, Exception;

  /**
   * 浙江异地二代证采集信息查询
   * @param strHQL
   * @param voPage
   * @return VoQueryResult
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryYdcjxx(String sCxqxkz, String strHQL, VoPage voPage) throws
      ServiceException, DAOException, Exception;

  /**
   * 浙江异地二代证采集信息照片查询
   * @param strHQL
   * @param voPage
   * @return VoQueryResult
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryYdcjzpxx(String strHQL, VoPage voPage) throws
      ServiceException, DAOException, Exception ;

  /**
   * 浙江异地二代证信息作废
   * @param strCjid String
   * @throws ServiceException
   * @throws DAOException
   * @throws Exception
   * @return String
   */
  public String processYdzzxxZF(String strCjid) throws
      ServiceException, DAOException, Exception ;

}
