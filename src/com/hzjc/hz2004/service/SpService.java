package com.hzjc.hz2004.service;

import com.hzjc.wsstruts.exception.ServiceException;
import com.hzjc.wsstruts.exception.DAOException;
import com.hzjc.wsstruts.exception.WSErrCode;
import java.util.*;

import com.hzjc.hz2004.base.SimpleJson;
import com.hzjc.hz2004.po.*;
import org.apache.commons.logging.*;
import com.hzjc.wsstruts.service.WSService;
import com.hzjc.hz2004.vo.*;
import com.hzjc.wsstruts.vo.*;

/**
 * 审批业务接口
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT Corp. 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public interface SpService
    extends WSService {

  /**
   * 审批附带材料修改业务
   * @param spywid - 审批业务ID
   * @param splx - 审批类型
   * @param spsm - 审批说明
   * @param voSpfdclxx - 审批附带材料
   * @return - 审批附带材料修改业务返回信息
   * @throws ServiceException
   * @throws DAOException
   */
  public VoSpfdclxgywfhxx processSpfdclxgyw(Long spywid, String splx,
                                            String spsm, VoSpfdclxx voSpfdclxx[]) throws
      ServiceException, DAOException;

  /**
   * 处理准迁证编号回填业务
   * @param voQyzbhhtxx
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoZqzbhhtywfhxx processZqzbhhtyw(VoZqzbhhtxx voZqzbhhtxx[]) throws
      ServiceException, DAOException;

  /**
   * 变更审批登记业务
   * @param voSbjbxx
   * @param spmbid
   * @param spsm
   * @param voBggzxxEx
   * @param voSpfdclxx
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoBgspdjywfhxx processBgspdjyw(VoSbjbxx voSbjbxx, Long spmbid,
                                        String spsm, VoBggzxxEx voBggzxxEx[],
                                        VoSpfdclxx voSpfdclxx[]) throws
      ServiceException,
      DAOException;

  /**
   *
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryBgspxx(String strHQL, VoPage voPage) throws
      ServiceException, DAOException;

  /**
   *
   * @param spywid
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryBgspzxx(Long spywid, VoPage voPage) throws
      ServiceException, DAOException;

  /**
   * 处理变更审批审批业务
   * @param voBgspspxx - 变更审批审批信息
   * @return - 变更审批审批业务返回信息
   * @throws DAOException
   * @throws ServiceException
   */
  public VoBgspspywfhxx processBgspspyw(VoBgspspxx voBgspspxx[]) throws
      DAOException, ServiceException;

  /**
   * 变更审批结果处理业务
   * @param voBgspjgclxx - 变更审批结果处理信息
   * @return - 变更审批结果处理业务返回信息
   * @throws DAOException
   * @throws ServiceException
   */
  public VoBgspjgclywfhxx processBgspjgclyw(VoBgspjgclxx voBgspjgclxx[]) throws
      DAOException, ServiceException;

  /**
   * 迁入审批登记业务
   * @param spsm - 审批说明
   * @param voQrspdjxx - 迁入审批登记信息(主迁人员信息)
   * @param voQrspdjzxx - 迁入审批登记子信息(随迁人员信息)
   * @param voSpfdclxx - 审批附带材料信息
   * @return - 迁入审批登记业务返回信息
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQrspdjywfhxx processQrspdjyw(String spsm, VoQrspdjxx voQrspdjxx,
                                        VoQrspdjzxx voQrspdjzxx[],
                                        VoSpfdclxx voSpfdclxx[]) throws
      DAOException, ServiceException;

  /**
   * 迁入审批信息获取(主迁人员信息获取)
   * @param strHQL
   * @param voPage
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQueryResult queryQrspxx(String strHQL, VoPage voPage) throws
      DAOException, ServiceException;

  /**
   * 迁入审批子信息获取(随迁人员信息获取)
   * @param spywid - 审批业务ID
   * @param voPage - 分页信息
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQueryResult queryQrspzxx(Long spywid, VoPage voPage) throws
      DAOException, ServiceException;

  /**
   * 迁入审批审批业务
   * @param voQrspspxx - 迁入审批审批信息
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQrspspywfhxx processQrspspyw(VoQrspspxx voQrspspxx[]) throws
      DAOException, ServiceException;

  /**
   * 获取登录用户能审批的变更审批信息
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryBgspxxByUser(String strHQL, VoPage voPage) throws
      ServiceException, DAOException;

  /**
   * 获取登录用户能审批的迁入审批信息(主迁人员信息获取)
   * @param strHQL
   * @param voPage
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQueryResult queryQrspxxByUser(String strHQL, VoPage voPage) throws
      DAOException, ServiceException;

  /**
   * 变更审批审批信息获取
   * @param strHQL
   * @param voPage
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQueryResult queryBgspspxx(String strHQL, VoPage voPage) throws
      DAOException, ServiceException;

  /**
   * 迁入审批审批信息获取
   * @param strHQL
   * @param voPage
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQueryResult queryQrspspxx(String strHQL, VoPage voPage) throws
      DAOException, ServiceException;

  /**
   * 处理户籍补录审批登记业务
   * @param spmbid - 审批模板ID
   * @param spsm - 审批说明
   * @param voHjblspdjxx - 户籍补录审批登记信息
   * @param voSpfdclxx - 审批附带材料信息
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoHjblspdjywfhxx processHjblspdjyw(Long spmbid, String spsm,
                                            VoHjblspdjxx voHjblspdjxx[],
                                            VoSpfdclxx voSpfdclxx[]) throws
      DAOException, ServiceException;

  /**
   * 户籍补录审批审批业务
   * @param voHjblspspxx - 户籍补录审批审批信息
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoHjblspspywfhxx processHjblspspyw(VoHjblspspxx voHjblspspxx[]) throws
      DAOException, ServiceException;

  /**
   * 获取登录用户能审批的户籍补录审批信息
   * @param strHQL
   * @param voPage
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQueryResult queryHjblspxxByUser(String strHQL, VoPage voPage) throws
      DAOException, ServiceException;

  /**
   * 户籍补录审批信息获取
   * @param strHQL
   * @param voPage
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQueryResult queryHjblspxx(String strHQL, VoPage voPage) throws
      DAOException, ServiceException;

  /**
   * 处理户籍删除审批登记业务
   * @param spmbid - 审批模板ID
   * @param spsm - 审批说明
   * @param voHjscspdjxx - 户籍删除审批登记信息
   * @param voSpfdclxx - 审批附带材料信息
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoHjscspdjywfhxx processHjscspdjyw(Long spmbid, String spsm,
                                            VoHjscspdjxx voHjscspdjxx[],
                                            VoSpfdclxx voSpfdclxx[]) throws
      DAOException, ServiceException;

  /**
   * 户籍删除审批审批业务
   * @param voHjscspspxx - 户籍删除审批审批信息
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoHjscspspywfhxx processHjscspspyw(VoHjscspspxx voHjscspspxx[]) throws
      DAOException, ServiceException;

  /**
   * 处理户别变更审批登记业务
   * @param spmbid - 审批模板ID
   * @param spsm - 审批说明
   * @param voHbbgspdjxx - 户别变更审批登记信息
   * @param voSpfdclxx - 审批附带材料信息
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoHbbgspdjywfhxx processHbbgspdjyw(Long spmbid, String spsm,
                                            VoHbbgspdjxx voHbbgspdjxx[],
                                            VoSpfdclxx voSpfdclxx[]) throws
      DAOException, ServiceException;

  /**
   * 户别变更审批审批业务
   * @param voHbbgspspxx - 户别变更审批审批信息
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoHbbgspspywfhxx processHbbgspspyw(VoHbbgspspxx voHbbgspspxx[]) throws
      DAOException, ServiceException;

  /**
   * 户籍删除审批信息获取
   * @param strHQL
   * @param voPage
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQueryResult queryHjscspxx(String strHQL, VoPage voPage) throws
      DAOException, ServiceException;

  /**
   * 获取登录用户能审批的户籍删除审批信息
   * @param strHQL
   * @param voPage
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQueryResult queryHjscspxxByUser(String strHQL, VoPage voPage) throws
      DAOException, ServiceException;

  /**
   * 户别变更审批信息获取
   * @param strHQL
   * @param voPage
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQueryResult queryHbbgspxx(String strHQL, VoPage voPage) throws
      DAOException, ServiceException;

  /**
   * 获取登录用户能审批的户别变更审批信息
   * @param strHQL
   * @param voPage
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQueryResult queryHbbgspxxByUser(String strHQL, VoPage voPage) throws
      DAOException, ServiceException;

  /**
   * 户别变更审批审批信息获取
   * @param strHQL
   * @param voPage
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQueryResult queryHbbgspspxx(String strHQL, VoPage voPage) throws
      DAOException, ServiceException;

  /**
   * 户籍补录审批审批信息获取
   * @param strHQL
   * @param voPage
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQueryResult queryHjblspspxx(String strHQL, VoPage voPage) throws
      DAOException, ServiceException;

  /**
   * 户籍删除审批审批信息获取
   * @param strHQL
   * @param voPage
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQueryResult queryHjscspspxx(String strHQL, VoPage voPage) throws
      DAOException, ServiceException;

  /**
   * 变更审批作废业务
   * @param voBgspzfxx - 变更审批作废信息
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoBgspzfywfhxx processBgspzfyw(VoBgspzfxx voBgspzfxx[]) throws
      DAOException, ServiceException;

  /**
   * 户别变更审批作废业务
   * @param voHbbgspzfxx - 户别变更审批作废信息
   * @return - 户别变更审批作废业务返回信息
   * @throws DAOException
   * @throws ServiceException
   */
  public VoHbbgspzfywfhxx processHbbgspzfyw(VoHbbgspzfxx voHbbgspzfxx[]) throws
      DAOException, ServiceException;

  /**
   * 户籍补录审批作废业务
   * @param voHjblspzfxx - 户籍补录审批作废信息
   * @return - 户籍补录审批作废业务返回信息
   * @throws DAOException
   * @throws ServiceException
   */
  public VoHjblspzfywfhxx processHjblspzfyw(VoHjblspzfxx voHjblspzfxx[]) throws
      DAOException, ServiceException;

  /**
   * 户籍删除审批作废业务
   * @param voHjscspzfxx - 户籍删除审批作废信息
   * @return - 户籍删除审批作废业务返回信息
   * @throws DAOException
   * @throws ServiceException
   */
  public VoHjscspzfywfhxx processHjscspzfyw(VoHjscspzfxx voHjscspzfxx[]) throws
      DAOException, ServiceException;

  /**
   * 迁入审批作废业务
   * @param voQrspzfxx - 迁入审批作废信息
   * @return - 迁入审批作废业务返回信息
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQrspzfywfhxx processQrspzfyw(VoQrspzfxx voQrspzfxx[]) throws
      DAOException, ServiceException;

  /**
   * 查询跨地区审批业务，用于同步到异地
   * @param hsql String
   * @return List
   */
  public List findKDQSpywList();

  /**
   * 保存跨地区审批业务到迁出地
   * @param sp PoHJSP_HJSPSQB
   */
  public void postKDQSpyw(PoHJSP_HJSPSQB sp);

  /**
   * 接收到跨地市迁出业务，生成代办事项
   * @param params Map
   * @param simpleJson SimpleJson
   */
  public void syncKdqSpyw(Map params, SimpleJson simpleJson);

  /**
   * 查询户政业务，反馈给迁入地
   * @return List
   */
  public List findKDQHzZjSb();

  /**
   * 反馈跨地市迁出
   * @param sb HzZjSb
   */
  public void postKDQFk(PoHZ_ZJ_SB sb);

  /**
   * 接收到反馈，持久化
   * @param params Map
   * @param simpleJson SimpleJson
   */
  public void syncKdqFk(Map params, SimpleJson simpleJson);

  /**
   * 一站式反馈列表查询（目的是防止事务不一致）
   * @return List
   */
  public List findKDQYZSFklist();

  /**
   * 进行一站式反馈（目的是防止事务不一致）
   * @param sb PoPOST_KDQCFKB
   */
  public void postKDQCFKB(PoPOST_KDQCFKB sb);

  /**
   * 一站式反馈（目的是防止事务不一致）
   * @param params Map
   * @param simpleJson SimpleJson
   */
  public void syncKdqFkYZS(Map params, SimpleJson simpleJson);
  
  /**
   * 重新设置户主
   * @param voCzrk
   * @return
   */
  public PoHJXX_CZRKJBXXB setNewHz(VoCzrkdjbHqFhxx voCzrk);
  /**
   * 查询echarts图标所需数据
   * @return List
   */
  public List<PoECHARTSDATA> queryEchartsDateList();

/**
 * 20190521 zjm
 * 删除echarts表数据
 */
public void deleteAllEchartDate();

/**
 * 20190521 zjm
 * 插入echarts表数据
 * @param list
 */
public void insertAllEchartDate(List list);

/**
 * 20200616
 * 执行存储过程，生成echarts数据，用于大屏展示
 */
public void queryEchartsByProcedure();
  
}
