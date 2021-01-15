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
 *                 报表操作接口</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public interface XtbbService extends XtService {

  /**
   * 查询业务报表模板
   * @param pxQryXtjs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtywbbxx(String sHqlXtjs)throws ServiceException,
    DAOException;
  public List getXtywbbxx(String sHqlXtjs,int nPageOffset,int nPageSize)throws ServiceException,
     DAOException;
  public List getXtywbbxx(VarVO vvHqlQryXtjs)throws ServiceException,
      DAOException;
  public List getXtywbbmbxx(VarVO vvHqlQryXtjs)throws ServiceException,
       DAOException;

  /**
   * 增加业务报表模板
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtywbbxx(VarVO vvXTJSB) throws ServiceException,DAOException;

  /**
   *修改业务报表模板
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtywbbxgxx(VarVO vvXTjsB) throws ServiceException,DAOException;

  /**
   *删除业务报表模板
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtywbbscxx(VarVO vvXTjsB) throws ServiceException,DAOException;
  /**
   * 查询制试报表模板
   * @param pxQryXtjs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtzsbbmbxx(String sHqlXtjs)throws ServiceException,
    DAOException;
  public List getXtzsbbmbxx(String sHqlXtjs,int nPageOffset,int nPageSize)throws ServiceException,
     DAOException;
  public List getXtzsbbmbxx(VarVO vvHqlQryXtjs)throws ServiceException,
      DAOException;
  public List getXtzsbbnotmbxx(String sHqlXtjs,int nPageOffset,int nPageSize)throws ServiceException,
     DAOException;
  public List getXtzsbbnotmbxx(VarVO vvHqlQryXtjs)throws ServiceException,
      DAOException;

  /**
   * 增加制试报表模板
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtzsbbmbxx(VarVO vvXTJSB) throws ServiceException,DAOException;

  /**
   *修改制试报表模板
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtzsbbmbxgxx(VarVO  vvXTjsB) throws ServiceException,DAOException;

  /**
   *删除制试报表模板
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtzsbbmbscxx(VarVO vvXTjsB) throws ServiceException,DAOException;
  /**
   * 查询制试报表
   * @param pxQryXtjs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtzsbbxx(String sHqlXtjs)throws ServiceException,
    DAOException;
  public List getXtzsbbxx(String sHqlXtjs,int nPageOffset,int nPageSize)throws ServiceException,
     DAOException;
  public List getXtzsbbxx(VarVO vvHqlQryXtjs)throws ServiceException,
      DAOException;
  public List getXtzsbbnotxx(String sHqlXtjs,int nPageOffset,int nPageSize)throws ServiceException,
      DAOException;
   public List getXtzsbbnotxx(VarVO vvHqlQryXtjs)throws ServiceException,
       DAOException;

  /**
   * 增加制试报表模板
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtzsbbxx(VarVO vvXTJSB) throws ServiceException,DAOException;

  /**
   *修改制试报表模板
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtzsbbxgxx(VarVO vvXTjsB) throws ServiceException,DAOException;

  /**
   *删除制试报表模板
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtzsbbscxx(VarVO vvXTjsB) throws ServiceException,DAOException;

}
