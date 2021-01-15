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
 *                 街路巷操作接口</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public interface XtjlxjwhdzService extends XtService {

  /**
   * 查询
   * @param pxQryXtcs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtjlxjwhdzxx(String sHqlXtjlxjwhdz)throws ServiceException,
      DAOException;
  public List getXtjlxjwhdzxx(String sHqlXtjlxjwhdz,int nPageOffset,int nPageSize)throws ServiceException,
      DAOException;
  public VoQueryResult getXtjlxjwhdzxx(String psWhere,VoPage voPage)throws ServiceException,
      DAOException;

  /**
   *增加
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjlxjwhdzxx(VarVO vvXTjlxjwhdzB) throws ServiceException,DAOException;
  /**
   *修改
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjlxjwhdzxgxx(VarVO vvXTjlxjwhdzB) throws ServiceException,DAOException;
/**
   * 删除
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtjlxjwhdzscxx(VarVO vvXTjlxjwhdzB) throws ServiceException,DAOException;
  /**
   * latest change date
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtjlxjwhdzzdbdsj() throws ServiceException,DAOException;

}
