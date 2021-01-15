package com.hzjc.hz2004.service;

import com.hzjc.wsstruts.exception.ServiceException;
import com.hzjc.wsstruts.exception.DAOException;
import com.hzjc.wsstruts.exception.WSErrCode;
import java.util.*;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.base.SimpleJson;
import com.hzjc.hz2004.po.*;
import org.apache.commons.logging.*;
import com.hzjc.wsstruts.service.WSService;
import com.hzjc.hz2004.vo.*;
import com.hzjc.wsstruts.vo.*;
/**
 * 户籍业务类
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public interface HjService
    extends WSService {

  /**
   *
   * @param voLhhdxx
   * @param voRhhdxx
   * @param voSbjbxx
   * @param voQrdjxx
   * @param voChxx
   * @param voBggzxx
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQrdjywfhxx processQrdjyw(VoLhhdxx voLhhdxx, VoRhhdxx voRhhdxx,
                                    VoSbjbxx voSbjbxx,
                                    VoQrdjxx voQrdjxx[], VoChxx voChxx[],
                                    VoBggzxxEx voBggzxxEx[]) throws
      ServiceException,
      DAOException;

  /**
   *
   * @param voLhhdxx
   * @param voRhhdxx
   * @param voSbjbxx
   * @param voZzbdxx
   * @param voBggzxx
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoZzbdywfhxx processZzbdyw(VoLhhdxx voLhhdxx, VoRhhdxx voRhhdxx,
                                    VoSbjbxx voSbjbxx, VoZzbdxx voZzbdxx[],
                                    VoBggzxxEx voBggzxxEx[]) throws
      ServiceException,
      DAOException;

  /**
   *
   * @param voLhhdxx
   * @param voRhhdxx
   * @param voSbjbxx
   * @param voCsdjxx
 * @param pch 
   * @param voBggzxx
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoCsdjywfhxx processCsdjyw(VoLhhdxx voLhhdxx, VoRhhdxx voRhhdxx,
                                    VoSbjbxx voSbjbxx, VoCsdjxx voCsdjxx[],
                                    VoBggzxxEx voBggzxxEx[]) throws
      ServiceException,
      DAOException;

  /**
   *
   * @param voSbjbxx
   * @param voHjscxx
 * @param pch 
   * @param voBggzxx
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoHjscywfhxx processHjscyw(VoSbjbxx voSbjbxx, VoHjscxx voHjscxx[],
                                    VoBggzxxEx voBggzxxEx[]) throws
      ServiceException,
      DAOException;

  /**
   *
   * @param voRhhdxx
   * @param voLhhdxx
   * @param voSbjbxx
   * @param voHjblxx
   * @param voChxx
   * @param voBggzxx
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoHjblywfhxx processHjblyw(VoRhhdxx voRhhdxx, VoLhhdxx voLhhdxx,
                                    VoSbjbxx voSbjbxx,
                                    VoHjblxx voHjblxx[], VoChxx voChxx[],
                                    VoBggzxxEx voBggzxxEx[]) throws
      ServiceException,
      DAOException;

  /**
   *
   * @param voSbjbxx
   * @param voSwzxxx
   * @param voBggzxx
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoSwzxywfhxx processSwzxyw(VoSbjbxx voSbjbxx, VoSwzxxx voSwzxxx[],
                                    VoBggzxxEx voBggzxxEx[]) throws
      ServiceException,
      DAOException;

  /**
   *
   * @param voSbjbxx
   * @param pch 
   * @param voBggzxx
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoBggzywfhxx processBggzyw(VoSbjbxx voSbjbxx, VoBggzxxEx voBggzxxEx[]) throws
      ServiceException,
      DAOException;

  /**
   *
   * @param voSbjbxx
   * @param voHbbgxx
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoHbbgywfhxx processHbbgyw(VoSbjbxx voSbjbxx, VoHbbgxx voHbbgxx[],
                                    VoBggzxxEx voBggzxxEx[]) throws
      ServiceException,
      DAOException;

  /**
   *
   * @param voSbjbxx
   * @param voQczxxx
   * @param voBggzxx
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQczxywfhxx processQczxyw(VoSbjbxx voSbjbxx, VoQczxxx voQczxxx[],
                                    VoBggzxxEx voBggzxxEx[]) throws
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
  public VoQueryResult querySfhmfpxx(String strHQL, VoPage voPage) throws
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
  public VoQueryResult queryBggzxx(String strHQL, VoPage voPage) throws
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
  public VoQueryResult queryCsdjxx(String strHQL, VoPage voPage) throws
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
  public VoQueryResult queryHjblxx(String strHQL, VoPage voPage) throws
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
  public VoQueryResult queryHcybdxx(String strHQL, VoPage voPage) throws
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
  public VoQueryResult queryHjscxx(String strHQL, VoPage voPage) throws
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
  public VoQueryResult queryQcclxx(String strHQL, VoPage voPage) throws
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
  public VoQueryResult queryQczxxx(String strHQL, VoPage voPage) throws
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
  public VoQueryResult queryQrdjxx(String strHQL, VoPage voPage) throws
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
  public VoQueryResult querySwzxxx(String strHQL, VoPage voPage) throws
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
  public VoQueryResult queryChclxx(String strHQL, VoPage voPage) throws
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
  public VoQueryResult queryZzbdxx(String strHQL, VoPage voPage) throws
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
  public VoQueryResult queryHxx(String strHQL, VoPage voPage) throws
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
  public VoQueryResult queryHbbgxx(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException;

  /**
   *
   * @param voQcclxx
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQcclywfhxx processQcclyw(VoQcclxx voQcclxx[]) throws
      ServiceException,
      DAOException;

  /**
   *
   * @param voRhflxx
   * @return
   */
  public VoRhflywfhxx processRhflyw(VoRhflxx voRhflxx[]) throws
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
  public VoQueryResult queryRhflxx(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException;

  /**
   *
   * @param voSfhmfpblxx
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoSfhmfpblywfhxx processSfhmfpblyw(VoSfhmfpblxx
                                            voSfhmfpblxx[]) throws
      ServiceException,
      DAOException;

  /**
   *
   * @param voSfhmfpscxx
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoSfhmfpscywfhxx processSfhmfpscyw(VoSfhmfpscxx
                                            voSfhmfpscxx[]) throws
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
  public VoQueryResult queryRyzpxx(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException;

  /**
   *
   * @param rynbid
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List queryCzrkdjbxx(Long rynbid) throws ServiceException, DAOException;

  /**
   *
   * @param voQhbgxx
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQhbgywfhxx processQhbgyw(VoSbjbxx voSbjbxx, VoQhbgxx voQhbgxx[]) throws
      ServiceException, DAOException;

  /**
   *
   * @param hjywid
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQyzdyxxHqFhxx queryQyzdyxx(Long hjywid) throws ServiceException,
      DAOException;

  /**
   *
   * @param voQyzbhhtxx
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQyzbhhtywfhxx processQyzbhhtyw(VoQyzbhhtxx voQyzbhhtxx[]) throws
      ServiceException, DAOException;

  /**
   *
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryRybdxx(String strHQL, VoPage voPage) throws
      ServiceException, DAOException;

  /**
   * 拍照保存业务
   * @param voPzxx[] - 拍照信息
   * @return VoPzbcywfhxx - 拍照保存业务返回信息
   * @throws ServiceException
   * @throws DAOException
   */
  public VoPzbcywfhxx processPzbcyw(VoPzxx voPzxx[]) throws ServiceException,
      DAOException;

  /**
   *
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryPzbcxx(String strHQL, VoPage voPage) throws
      ServiceException, DAOException;

  /**
   * 处理照片删除业务
   * @param voZpscxx - 照片删除信息
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoZpscywfhxx processZpscyw(VoZpscxx voZpscxx[]) throws DAOException,
      ServiceException;

  /**
   * 查询拍照日志信息
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryPzrzxx(String strHQL, VoPage voPage) throws
      ServiceException, DAOException;

  /**
   * 查询已处理的拍照日志
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryPzrzxxByYCL(String strHQL, VoPage voPage) throws
      ServiceException, DAOException;

  /**
   * 处理拍照照片删除业务
   * @param voPzzpscxx - 拍照照片删除信息
   * @return voPzzpscywfhxx - 拍照照片删除业务返回信息
   * @throws DAOException
   * @throws ServiceException
   */
  public VoPzzpscywfhxx processPzzpscyw(VoPzzpscxx voPzzpscxx[]) throws
      DAOException, ServiceException;

  /**
   * 打印信息保存
   * @param voHjdyHqFhxx
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public int processDyxxbcyw(VoHjdyHqxx voHjdyHqxx[]) throws
      ServiceException, DAOException;

  /**
   * 打印信息查询
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryDyxx(String strHQL, VoPage voPage) throws
      ServiceException, DAOException;

  /**
   * 历史四变信息查询
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryLssbxx(String strHQL, VoPage voPage) throws
      ServiceException, DAOException;

  /**
   * 历史变更信息查询
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryLsbgxx(String strHQL, VoPage voPage) throws
      ServiceException, DAOException;

  /**
   * 查询结果保存日志信息保存
   * @param voCxbcrzxx - 查询结果保存日志信息
   * @return String
   * @throws DAOException
   * @throws ServiceException
   */
  public String processCxjgbcrz(String gnbh,String cxyj) throws DAOException,
      ServiceException;

  /**
   * 核验身份证号码，返回""表示成功，其它为错误提示
   * @param sfz String
   * @return String
   */
  public void gmsfhmCheck(Map params, SimpleJson simpleJson);
  
	/**
	 * 获取新的身份证号
	 * @param params
	 * @return
	 */
	public VoGmsfhmfpfhxx getSfz(ExtMap<String, Object> params);
	
	/**
	 * 锁定/解锁人员锁定状态
	 * @param params
	 * @return
	 */
	public PoHJXX_CZRKJBXXB processCzrkzt(ExtMap<String,Object> params) ;

}
