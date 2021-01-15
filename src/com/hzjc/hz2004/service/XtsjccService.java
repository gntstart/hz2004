package com.hzjc.hz2004.service;

import com.hzjc.wsstruts.service.WSService;
import com.hzjc.wsstruts.exception.ServiceException;
import com.hzjc.wsstruts.exception.DAOException;
import java.util.*;

/**
 * 系统数据查错业务
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public interface XtsjccService
    extends WSService {

  /**
   *
   * @param strHQL
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List processChtbyw(String strHQL) throws ServiceException,
      DAOException;

  /**
   *
   * @return - 户籍业务ID
   * @throws DAOException
   * @throws ServiceException
   */
  public Long processGmsfhmwsscyw() throws DAOException, ServiceException;

}