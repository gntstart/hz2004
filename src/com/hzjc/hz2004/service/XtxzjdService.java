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
 *                 乡镇街道操作接口</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public interface XtxzjdService extends XtService {

  /**
   *
   * @param pxQryXtcs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtxzjdxx(String sHqlXtxzjd)throws ServiceException,DAOException;
  public List getXtxzjdxx(String sHqlXtxzjd,int nPageOffset,int nPageSize)throws ServiceException,
      DAOException;
  public VoQueryResult getXtxzjdxx(VarVO vvQryXtxzjd,String psWhere,VoPage voPage)throws ServiceException,
       DAOException;

  /**
   *增加
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtxzjdxx(VarVO vvXTxzjdB) throws ServiceException,DAOException;
  /**
   *修改
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtxzjdxgxx(VarVO vvXTxzjdB) throws ServiceException,DAOException;
/**
   * 删除
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtxzjdscxx(VarVO vvXTxzjdB) throws ServiceException,DAOException;
  /**
   * firstest change date
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtxzjdzdbdsj() throws ServiceException,DAOException;

}
