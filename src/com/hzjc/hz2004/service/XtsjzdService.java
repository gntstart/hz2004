package com.hzjc.hz2004.service;

import com.hzjc.hz2004.vo.*;
import java.util.*;
import com.hzjc.hz2004.po.*;
import com.hzjc.wsstruts.exception.ServiceException;
import com.hzjc.wsstruts.exception.DAOException;
import com.hzjc.wsstruts.type.*;
import com.hzjc.wsstruts.vo.*;
import com.hzjc.wsstruts.service.WSService;
import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.login.AuthToken;
/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 数据字典操作接口</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public interface XtsjzdService extends XtService {

  /**
   *查询系统控制参数信息
   * @param pxQryXtsjzd
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtsjzdxx(String sHqlXtsjzd)throws ServiceException,
      DAOException;
  public List getXtsjzdxx(String sHqlXtsjzd,int nPageOffset,int nPageSize)throws ServiceException,
      DAOException;
  public VoQueryResult getXtsjzdxx(VarVO vvQryXtsjzd,String psWhere,VoPage voPage)throws ServiceException,
      DAOException;

  /**
    *增加数据字典信息
    * @param pxQryXtsjzd
    * @return
    * @throws ServiceException
    * @throws DAOException
    */
   public List setXtsjzdxx(VarVO vvXTSJZDB)throws ServiceException,
       DAOException;

  /**
   *修改数据字典
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtsjzdxgxx(VarVO vvXTSJZDB) throws ServiceException,DAOException;
  /**
   *删除数据字典信息
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int setXtsjzdscxx(VarVO vvXTSJZDB) throws ServiceException,DAOException;
  /**
   * 最大变动时间
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtsjzdzdbdsj() throws ServiceException,DAOException;
  
	/**
	 * 重载缓存
	 * @param params
	 * @return
	 */
	public boolean reload(ExtMap<String,Object> params);
	
}
