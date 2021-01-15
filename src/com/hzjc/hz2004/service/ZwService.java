package com.hzjc.hz2004.service;

import com.hzjc.wsstruts.service.WSService;
import java.util.*;
import java.sql.*;
import com.hzjc.wsstruts.exception.*;
import com.hzjc.hz2004.po.*;
import com.hzjc.hz2004.vo.*;
import com.hzjc.wsstruts.vo.*;

/**
 * <p>Title: 指纹信息接口类</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: </p>
 * @author hubin
 * @version 1.0
 */
public interface ZwService extends WSService{

  /**
   * 浙江指纹信息保存
   * @param voZwxx voZwxx[]
   * @throws Exception
   * @return Long
   */
  public String processZwxxSave(VoZwTjxx[] voZwtjxx) throws
      ServiceException, DAOException, Exception;

  /**
   * 浙江指纹基本信息查询
   * @param strHQL
   * @param voPage
   * @return VoQueryResult
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryZwjbxx(String strHQL, VoPage voPage) throws
      ServiceException, DAOException, Exception;

  /**
   * 浙江指纹扩展信息查询
   * @param strHQL
   * @param voPage
   * @return VoQueryResult
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryZwkzxx(String strHQL, VoPage voPage) throws
      ServiceException, DAOException, Exception;

  /**
   * 浙江指纹受理信息保存
   * @param VoZwslxx voZwslxx[]
   * @throws Exception
   * @return Long
   */
  public String processZwslxxSave(VoZwslxx[] voZwslxx) throws
      ServiceException, DAOException, Exception;

  /**
   * 浙江指纹受理信息查询
   * @param strHQL
   * @param voPage
   * @return VoQueryResult
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryZwslxx(String strHQL, VoPage voPage) throws
      ServiceException, DAOException, Exception;

  /**
   * 浙江指纹信息作废
   * @param VoZwslxx voZwslxx[]
   * @throws Exception
   * @return Long
   */
  public String processZwxxZF(VoZwZfxx[] voZwzfxx) throws
      ServiceException, DAOException, Exception;
}
