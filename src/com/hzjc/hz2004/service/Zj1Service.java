package com.hzjc.hz2004.service;

import com.hzjc.wsstruts.service.WSService;
import com.hzjc.wsstruts.exception.ServiceException;
import com.hzjc.wsstruts.exception.DAOException;
import com.hzjc.hz2004.vo.VoYdzZjslywfhxx;
import com.hzjc.hz2004.vo.VoYdzZjslxx;
import java.util.List;
import com.hzjc.wsstruts.vo.*;

/**
 *
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author Kansan Ku(kgb_hz@126.com)
 * @version 1.0
 */
public interface Zj1Service
    extends ZjService {

  /**
   * 底卡打印业务
   * @param lNbslid
   * @throws ServiceException
   * @throws DAOException
   */
  void processDkdyyw(Long lBzslid) throws
      ServiceException, DAOException;

  /**
   * 处理证件受理业务
   * @param voYdzZjslxx[] - 一代证证件受理信息
   * @return <{com.hzjc.hz2004.vo.VoYdzZjslywfhxx}>
   * @roseuid 40593B850264
   */
  VoYdzZjslywfhxx processZjslyw(VoYdzZjslxx[] voYdzZjslxx) throws
      ServiceException, DAOException;

  /**
   * 一代证受理删除业务
   * @param lBzslid
   * @throws <{ServiceException}>
   * @throws <{DAOException}>
   */
  void processZjslscyw(Long lBzslid) throws ServiceException,
      DAOException;

  /**
   * 得到证件受理信息，证件受理信息由PoZJYW_YDZSLB(一代证证件受理信息)+PoHJXX_MLPXXXX
   * B(地信息)组成。
   *
   * @param PacketXql - WHERE部分的XML描述的查询语言
   * @return <{java.util.List}>
   * @roseuid 405943A20347
   */
  VoQueryResult queryZjslxx(String strHQL, VoPage vopage) throws
      ServiceException,
      DAOException;
}
