package com.hzjc.hz2004.service;

import com.hzjc.hz2004.vo.*;
import java.util.*;

import org.springframework.web.multipart.MultipartHttpServletRequest;

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
 *                 单位信息操作接口</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public interface XtdwxxService extends XtService {

  /**
   *
   * @param pxQryXtcs
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtdwxx(String sHqlXtdwxx)throws ServiceException,
      DAOException;
  public List getXtdwxx(String sHqlXtdwxx,int nPageOffset,int nPageSize)throws ServiceException,
      DAOException;
  public VoQueryResult getXtdwxx(VarVO vvQryXtdwxx,String psWhere,VoPage voPage)throws ServiceException,
      DAOException;

  /**
   *增加
 * @param logoFile 
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtdwxx(MultipartHttpServletRequest logoFile, VarVO vvXTDWXXB) throws ServiceException,DAOException;
  /**
   *修改
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtdwxgxx(MultipartHttpServletRequest logoFile,VarVO vvXTDWXXB) throws ServiceException,DAOException;
/**
   * 删除
   * @param sParamXml
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List setXtdwscxx(VarVO vvXTDWXXB) throws ServiceException,DAOException;
  /**
   * 最大变动时间
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List getXtdwzdbdsj() throws ServiceException,DAOException;
	/**
	 * 恢复
	 * @param vo
	 * @return
	 */
	public List setXtdwxxResume(VarVO vo) throws ServiceException,DAOException;
}
