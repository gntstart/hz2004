package com.hzjc.hz2004.service;

import com.hzjc.wsstruts.exception.ServiceException;
import com.hzjc.wsstruts.exception.DAOException;
import com.hzjc.wsstruts.service.WSService;
import com.hzjc.hz2004.vo.*;

/**
 * 冲销业务接口
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT Corp. 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public interface CxService
    extends WSService {

  /**
   * 户籍恢复业务
   * @param rybdid - 人员变动ID
   * @param sbhjywid - 上笔户籍业务ID
   * @param voBggzxxEx - 变更更正信息
   * @throws ServiceException
   * @throws DAOException
   */
  public VoHjhfywfhxx processHjhfyw(Long rybdid, Long sbhjywid,
                                    VoBggzxxEx voBggzxxEx[]) throws
      ServiceException,
      DAOException;

  /**
   * 户籍注销业务
   * @param rybdid - 人员变动ID
   * @param sbhjywid - 上笔户籍业务ID
   * @param voBggzxxEx - 变更更正信息
   * @throws ServiceException
   * @throws DAOException
   */
  public VoHjzxywfhxx processHjzxyw(Long rybdid, Long sbhjywid,
                                    VoBggzxxEx voBggzxxEx[]) throws
      ServiceException, DAOException;

}