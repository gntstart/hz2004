package com.hzjc.hz2004.service;

import java.util.Map;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.base.SimpleJson;
import com.hzjc.hz2004.po.PoYDZJ_DBLKZ;
import com.hzjc.hz2004.vo.VoYdslDblkzfhxx;
import com.hzjc.hz2004.vo.VoYdslzfxx;
import com.hzjc.hz2004.vo.VoYdslzfywfhxx;
import com.hzjc.hz2004.vo.VoYdzjlqhqxx;
import com.hzjc.hz2004.vo.VoYdzjlqywfhxx;
import com.hzjc.hz2004.vo.VoYdzjsbxx;
import com.hzjc.hz2004.vo.VoZjClddyxx;
import com.hzjc.hz2004.vo.VoZjdbzfxx;
import com.hzjc.hz2004.vo.VoZjdbzfywfhxx;
import com.hzjc.hz2004.vo.VoZjgsxx;
import com.hzjc.hz2004.vo.VoZjgsywfhxx;
import com.hzjc.hz2004.vo.VoZjlqffxx;
import com.hzjc.hz2004.vo.VoZjlqffywfhxx;
import com.hzjc.hz2004.vo.VoZjshfhxx;
import com.hzjc.hz2004.vo.VoZjshxx;
import com.hzjc.hz2004.vo.VoZjshywfhxx;
import com.hzjc.hz2004.vo.VoZjsjxx;
import com.hzjc.hz2004.vo.VoZjsjywfhxx;
import com.hzjc.hz2004.vo.VoZjslxx;
import com.hzjc.hz2004.vo.VoZjslywfhxx;
import com.hzjc.hz2004.vo.VoZjslzfxx;
import com.hzjc.hz2004.vo.VoZjslzfywfhxx;
import com.hzjc.hz2004.vo.VoZjxhxx;
import com.hzjc.hz2004.vo.VoZjxhywfhxx;
import com.hzjc.hz2004.vo.VoZjysxx;
import com.hzjc.hz2004.vo.VoZjysywfhxx;
import com.hzjc.wsstruts.exception.DAOException;
import com.hzjc.wsstruts.exception.ServiceException;
import com.hzjc.wsstruts.vo.VoPage;
import com.hzjc.wsstruts.vo.VoQueryResult;

/**
 *
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author Kansan Ku(kgb_hz@126.com)
 * @version 1.0
 */
public interface Zj2Service
    extends ZjService {

  /**
   * 派出所证件验收
   * @param voZjysxx
   * @return
   * @throws <{ServiceException}>
   * @throws <{DAOException}>
   */
  VoZjysywfhxx processPcsZjysyw(VoZjysxx[] voZjysxx) throws
      ServiceException, DAOException;

  /**
   * 区县证件验收业务
   * @param voZjysxx
   * @return
   * @throws <{ServiceException}>
   * @throws <{DAOException}>
   */
  VoZjysywfhxx processQxZjysyw(VoZjysxx[] voZjysxx) throws
      ServiceException, DAOException;

  /**
   * 地市证件验收业务
   * @param voZjysxx
   * @return
   * @throws <{ServiceException}>
   * @throws <{DAOException}>
   */
  VoZjysywfhxx processDsZjysyw(VoZjysxx[] voZjysxx) throws
      ServiceException, DAOException;

  /**
   *省厅证件验收业务
   * @param voZjysxx
   * @return
   * @throws <{ServiceException}>
   * @throws <{DAOException}>
   */
  VoZjysywfhxx processStZjysyw(VoZjysxx[] voZjysxx) throws
      ServiceException, DAOException;

  /**
   * 证件受理业务
   * @param voZjslxx[] - 证件受理信息
   * @return <{com.hzjc.hz2004.vo.VoZjslywfhxx}>
   * @roseuid 40594CA00397
   */
  VoZjslywfhxx processZjslyw(VoZjslxx[] voZjslxx) throws
      ServiceException, DAOException;

  /**
   * 处理证件挂失业务
   *
   * @param voZjgsxx[] - 证件挂失信息
   * @return <{com.hzjc.hz2004.vo.VoZjgsywfhxx}>
   * @roseuid 405C544B030D
   */
  VoZjgsywfhxx processZjgsyw(VoZjgsxx[] voZjgsxx) throws
      ServiceException, DAOException;

  /**
   * 处理证件收交业务
   * @param voZjsjxx[] - 证件收交信息
   * @return <{com.hzjc.hz2004.vo.VoZjsjywfhxx}>
   * @roseuid 405C5A050315
   */
  VoZjsjywfhxx processZjsjyw(VoZjsjxx[] voZjsjxx) throws
      ServiceException, DAOException;

  /**
   * 管理证件销毁业务
   *
   * @param voZjxhxx[] - 证件销毁信息
   * @return <{com.hzjc.hz2004.vo.VoZjxhywfhxx}>
   * @roseuid 405C5E35008C
   */
  VoZjxhywfhxx processZjxhyw(VoZjxhxx[] voZjxhxx) throws
      ServiceException, DAOException;

  /**
   * 处理证件领取发放业务
   * @param voZjlqffxx[] - 证件领取发放信息
   * @return <{com.hzjc.hz2004.vo.VoZjlqffywfhxx}>
   * @roseuid 4061309401C6
   */
  VoZjlqffywfhxx processZjlqffyw(VoZjlqffxx[] voZjlqffxx) throws
      ServiceException, DAOException;

  /**
   *
   * @throws ServiceException
   * @throws DAOException
   */
  void processZjsfhyyw() throws
      ServiceException, DAOException;

  /**
   *
   * @param voZjshxx
   * @param voZlkzhkxx
   * @return
   * @throws <{ServiceException}>
   * @throws <{DAOException}>
   */
  VoZjshywfhxx processQxZjshyw(VoZjshxx voZjshxx[]) throws
      ServiceException, DAOException;

  /**
   *
   * @param voZjshxx
   * @param voZlkzhkxx
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  VoZjshywfhxx processDsZjshyw(VoZjshxx voZjshxx[]) throws
      ServiceException, DAOException;

  /**
   * 二代证省厅证件审核业务处理
   * @param voZjshxx
   * @param voZlkzhkxx
   * @return
   * @throws <{ServiceException}>
   * @throws <{DAOException}>
   */
  VoZjshywfhxx processStZjshyw(VoZjshxx voZjshxx[]) throws
      ServiceException, DAOException;

  /**
   *
   * @param arryShqfid
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  VoZjshfhxx[] processQxZjqfyw(VoZjslxx[] arryZjslxxs) throws ServiceException,
      DAOException;

  /**
   * 二代证件催领单打印业务
   * @return
   * @throws <{ServiceException}>
   * @throws <{DAOException}>
   */
  void processZjClddyyw(VoZjClddyxx[] arrayVoZjClddyxx) throws
      ServiceException, DAOException;

  /**
   * 换领二代证查询(催办单打印)
   * @param strHQL - 查询HQL语句
   * @param vopage - 分页信息VO
   * @return
   * @throws <{ServiceException}>
   * @throws <{DAOException}>
   */
  VoQueryResult queryHledzryxx(String strHQL,
                               VoPage vopage) throws ServiceException,
      DAOException;

  /**
   * 查询一代证到期人员（催办单打印）
   * @param strJzrq  - 有效期限截止日期
   * @param strHQL   - 查询条件
   * @param vopage   - 分页信息
   * @return         - 查询结果
   * @throws <{ServiceException}>
   * @throws <{DAOException}>
   */
  VoQueryResult queryYdzdqryxx(String strJzrq, String strHQL,
                               VoPage vopage) throws ServiceException,
      DAOException;

  /**
   * 查询满16岁需申领二代证件人员信息（催办单打印）
   * @param strJzrq - 有效期限截止日期
   * @param strHQL  - 查询条件
   * @param vopage  - 分页信息
   * @return  查询结果
   * @throws <{ServiceException}>
   * @throws <{DAOException}>
   */
  VoQueryResult queryM16ssledzryxx(String strJzrq, String strHQL,
                                   VoPage vopage) throws ServiceException,
      DAOException;

  /**
   * 查询二代证到期人员信息（催办单打印）
   * @param strJzrq - 有效期限截止日期
   * @param strHQL  - 查询条件
   * @param vopage  - 分页信息
   * @return  查询结果
   * @throws <{ServiceException}>
   * @throws <{DAOException}>
   */
  VoQueryResult queryEdzdqryxx(String strJzrq, String strHQL,
                               VoPage vopage) throws ServiceException,
      DAOException;

  /**
   * 得到证件受理信息，证件受理信息由PoZJYW_SLXXB(证件受理信息)+PoHJXX_MLPXXXXB(地信?
   * )组成。
   * @param PacketXql - WHERE部分的XML描述的查询语言
   * @return <{List}>
   * @roseuid 405FA98C0058
   */
  VoQueryResult queryZjslxx(String strHQL, VoPage vopage) throws
      ServiceException,
      DAOException;

  /**
   * 根据查询条件,返回证件的受理状态的分组信息
   * @param strHQL
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  VoQueryResult queryZjslxxGroupBySlzt(String strHQL) throws
      ServiceException,
      DAOException;

  /**
   * 根据查询条件，返回临时证件的打印标志的分组信息
   * @param strHQL String
   * @throws ServiceException
   * @throws DAOException
   */
  VoQueryResult queryLssfzslxxGroupByDybz(String strHQL, String strGroup) throws
      ServiceException,
      DAOException;

  /**
   * 得到证件挂失信息，证件挂失信息是由PoZJYW_GSXXB(证件挂失信息)组成。
   * @param PacketXql - WHERE部分的XML描述的查询语言
   * @return <{List}>
   * @roseuid 4060EB76008D
   */
  VoQueryResult queryZjgsxx(String strHQL, VoPage vopage) throws
      ServiceException,
      DAOException;

  /**
   * 得到证件销毁信息，证件销毁信息是由PoZJYW_XHXXB(证件销毁信息)组成。
   * @param PacketXql - WHERE部分的XML描述的查询语言
   * @return <{List}>
   * @roseuid 4060F14602DB
   */
  VoQueryResult queryZjxhxx(String strHQL, VoPage vopage) throws
      ServiceException,
      DAOException;

  /**
   * 得到证件收交信息，证件收交信息是由PoZJYW_SJXXB(证件收交信息)组成。
   * @param PacketXql - WHERE部分的XML描述的查询语言
   * @return <{List}>
   * @roseuid 4060F4D9029B
   */
  VoQueryResult queryZjsjxx(String strHQL, VoPage vopage) throws
      ServiceException,
      DAOException;

  /**
   * 得到证件投递信息，证件投递信息由PoZJYW_TDXXB(证件投递信息)组成。
   * @param PacketXql - WHERE部分的XML描述的查询语言
   * @return <{List}>
   * @roseuid 4060FB93032D
   */
  VoQueryResult queryZjtdxx(String strHQL, VoPage vopage) throws
      ServiceException,
      DAOException;

  /**
   * 得到证件审核信息，证件审核信息是由PoZJSH_SHQFXXB(证件审核信息)组成。
   * @param PacketXql - WHERE部分的XML描述的查询语言
   * @return <{List}>
   * @roseuid 4061040B0367
   */
  VoQueryResult queryZjshxx(String strHQL, VoPage vopage) throws
      ServiceException,
      DAOException;

  /**
   * 得到证件领取发放信息，证件领取发放信息由PoZJYW_LQFFXXB(领取发放信息)组成。
   * @param PacketXql - WHERE部分的XML描述的查询语言
   * @return <{List}>
   * @roseuid 406106E000C0
   */
  VoQueryResult queryZjlqffxx(String strHQL, VoPage vopage) throws
      ServiceException, DAOException;

  /**
   * 查询证件验收信息，证件验收信息由PoZJYW_YSXXB(证件验收信息)组成。
   * @param PacketXql - WHERE部分的XML描述的查询语言
   * @return <{List}>
   * @roseuid 40610980004E
   */
  VoQueryResult queryZjysxx(String strHQL, VoPage vopage) throws
      ServiceException,
      DAOException;

  /**
   * 通过姓名或身份号码得到居民身份证信息，居民身份证信息由PoZJXX_JMSFZXXB(证件信息)
   * + PoHJXX_MLPXXXXB(地信息)组成。
   * @param Sfhm - 身份号码
   * @param Xm - 姓名
   * @return <{List}>
   * @roseuid 404544D60360
   */
  VoQueryResult queryJmsfzxx(String strHQL, VoPage vopage) throws
      ServiceException,
      DAOException;

  /**
   * 通过姓名或身份号码得到居民身份证信息，居民身份证信息由PoZJXX_JMSFZXXB(证件信息)
   * + PoHJXX_MLPXXXXB(地信息)组成。
   * @param Sfhm - 身份号码
   * @param Xm - 姓名
   * @return <{List}>
   * @roseuid 404544D60360
   */
  VoQueryResult queryZzhkxx(String strHQL, VoPage vopage) throws
      ServiceException,
      DAOException;

  /**
   * 通过姓名或身份号码得到居民身份证信息，居民身份证信息由PoZJXX_JMSFZXXB(证件信息)
   * + PoHJXX_MLPXXXXB(地信息)组成。
   * @param Sfhm - 身份号码
   * @param Xm - 姓名
   * @return <{List}>
   * @roseuid 404544D60360
   */
  VoQueryResult queryZlkzhkxx(String strHQL, VoPage vopage) throws
      ServiceException,
      DAOException;

  /**
   *
   * @param strHQL
   * @param vopage
   * @return
   * @throws <{ServiceException}>
   * @throws <{DAOException}>
   */
  VoQueryResult queryGmsfhmsxmfpxx(String strHQL, VoPage vopage) throws
      ServiceException, DAOException;

  /**
   *
   * @param strHQL
   * @param vopage
   * @return
   * @throws <{ServiceException}>
   * @throws <{DAOException}>
   */
  VoQueryResult queryChclxx(String strHQL, VoPage vopage) throws
      ServiceException, DAOException;

  /**
   * 证件受理作废业务
   * @param VoZjslzfxx - 证件受理作废信息
   * @return
   */
  public VoZjslzfywfhxx processZjslzfyw(VoZjslzfxx VoZjslzfxx[]) throws
      ServiceException, DAOException;

  /**
   * 证件打包作废业务
   * @param VoZjslzfxx - 证件受理作废信息
   * @return
   */
  public VoZjdbzfywfhxx processZjdbzfyw(VoZjdbzfxx voZjdbzfxx[]) throws
      ServiceException, DAOException;

  /**
   *
   * 异地二代证件受理业务
   * @param voZjslxx[] - 证件受理信息数组
   * @return Long
   */
  public Long processYdzjslyw(VoYdzjsbxx[] voYdzjslxx) throws
      ServiceException, DAOException;

  /**
   * 得到异地证件受理信息。
   * @param strHQL  - 查询条件HQL
   * @param vopage  - 分页信息VO
   * @return        - 查询结果VO
   */
  public VoQueryResult queryYdzjsbxx(String strHQL, VoPage vopage) throws
      ServiceException, DAOException;

  /**
   * 异地打包量控制查询。
   * @param strHQL  - 查询条件HQL
   * @param vopage  - 分页信息VO
   * @return        - 查询结果VO
   */
  public VoQueryResult queryDblkz(String strHQL, VoPage voPage) throws
      ServiceException, DAOException;

  /**
   * 异地证件人员照片信息获取(PoYDZJ_SDZP)
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryYdsdzpxx(String strHQL, VoPage voPage) throws
      ServiceException, DAOException;

  /**
   * 异地证件受理作废业务
   * @param VoZjslzfxx - 证件受理作废信息
   * @return
   */
  public VoYdslzfywfhxx processYdslzfyw(VoYdslzfxx voYdslzfxx[]) throws
      ServiceException, DAOException;

  /**
   * 异地证件领取发放提交业务
   * @param voYdzjlqhqxx VoYdzjlqhqxx[]
   * @throws ServiceException
   * @throws DAOException
   * @return VoYdzjlqywfhxx
   */
  public VoYdzjlqywfhxx processYdzjlqffyw(VoYdzjlqhqxx[] voYdzjlqhqxx) throws
      ServiceException, DAOException ;

  /**
   * 异地证件打包量控制删除
   * @param VoZjslzfxx - 打包量控制删除信息
   * @return
   */
  public VoYdslDblkzfhxx[] ProcessDeleteDblkz(VoYdslDblkzfhxx voYdslDblkzfhxx[]) throws
      ServiceException, DAOException;

  /**
   * 异地证件打包量控制增加
   * @param VoZjslzfxx - 打包量控制增加信息
   * @return
   */
  public VoYdslDblkzfhxx[] ProcessAddDblkz(PoYDZJ_DBLKZ[] poYDZJ_DBLKZ) throws
      ServiceException, DAOException;
  /**
   * 异地证件打包量控制修改
   * @param VoZjslzfxx - 打包量控制修改信息
   * @return
   */
  public VoYdslDblkzfhxx[] ProcessModifyDblkz(PoYDZJ_DBLKZ[] poYDZJ_DBLKZ) throws
      ServiceException, DAOException;

  /**
   * 绍兴绿色通道身份证查询。
   * @param strGmsfhm
   * @param strHQL
   * @return
   * @throws DAOException
   */
  int querySxlstdsfzcx(String strGmsfhm, String strHQL) throws ServiceException,DAOException ;
  
  /**
   * 查询户政业务
   * @param param
   * @param sj
   * @return
   */
  public Page queryPoHZ_ZJ_SB(Map param,SimpleJson sj);
  
  /**
   * 删除户政业务
   * @param param
   * @param sj
   * @return
   */
  public long updateHzyw(Map param,SimpleJson sj);
  
  /**
   * 已处理户政业务
   * @param param
   * @param sj
   * @return
   */
  public long dealHzyw(Map param);
  
  public String getKDSQY_CzrkAndDqbm(Map param,SimpleJson sj) throws Exception;

  /**
   * 更新户政业务中间表的评价字段
 * @param params
 * @return
 */
long updateHzywPj(Map param);

/**
 * 户政业务办结
 * @param params
 * @return
 */
long updateHzywBj(ExtMap<String, Object> params);
}
