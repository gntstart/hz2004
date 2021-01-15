package com.hzjc.hz2004.service;

import com.hzjc.wsstruts.service.WSService;
import java.util.*;
import com.hzjc.wsstruts.exception.*;
import com.hzjc.hz2004.po.*;
import com.hzjc.wsstruts.vo.*;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 历史信息查询</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public interface LsService
    extends WSService {
  /**
   * 历史迁入登记信息查询
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryOLD_HJYW_QRDJXXB(String strHQL, VoPage voPage)
      throws ServiceException, DAOException;
  public VoQueryResult queryOLD_HJYW_CSDJXXB(String strHQL, VoPage voPage)
      throws ServiceException, DAOException;
  public VoQueryResult queryOLD_HJYW_QCZXXXB(String strHQL, VoPage voPage)
      throws ServiceException, DAOException;
  public VoQueryResult queryOLD_HJYW_SWZXXXB(String strHQL, VoPage voPage)
      throws ServiceException, DAOException;
  public VoQueryResult queryOLD_HJYW_ZZBDXXB(String strHQL, VoPage voPage)
      throws ServiceException, DAOException;
  public VoQueryResult queryOLD_HJYW_HCYBDXXB(String strHQL, VoPage voPage)
      throws ServiceException, DAOException;
  public VoQueryResult queryV_HJYW_BGGZXXB(String strHQL, VoPage voPage)
      throws ServiceException, DAOException;
  public VoQueryResult queryV_HJTJ_RYBDXXB(String strHQL, VoPage voPage)
      throws ServiceException, DAOException;
  public VoQueryResult queryV_HJXX_CZRKJBXXB(String strHQL, VoPage voPage)
      throws ServiceException, DAOException;
}
