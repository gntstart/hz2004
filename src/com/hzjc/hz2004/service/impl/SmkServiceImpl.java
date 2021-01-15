package com.hzjc.hz2004.service.impl;

/**
 * 浙江杭州市民卡业务类
 * <p>Title: </p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

import java.util.*;
import java.sql.*;
import java.io.*;
import javax.sql.DataSource;
import javax.naming.*;
import org.apache.commons.beanutils.*;
import org.springframework.stereotype.Service;

import com.hzjc.wsstruts.common.db.DbUtils;
import com.hzjc.wsstruts.vo.*;
import com.hzjc.wsstruts.exception.*;
import com.hzjc.hz2004.vo.*;
import com.hzjc.hz2004.service.SmkService;
import com.hzjc.util.*;
import com.hzjc.hz2004.dao.*;
import com.hzjc.hz2004.po.*;
import com.hzjc.hz2004.constant.*;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.common.HjCommon;
@Service
public class SmkServiceImpl
    extends HjCommon
    implements SmkService {

  /**
   * 市民卡读卡信息保存，判断是否存在黑名单
   * @param vodkxx VoSmkdkxxHq[]
   * @throws ServiceException
   * @throws DAOException
   * @return int
   */
  public String processSmkdkxxbc(VoSmkdkxxHq[] vodkxx) throws ServiceException,
      DAOException {
    String strReturn = "0";
    String strSQL = "";

    try {
      PojoInfo  hzsmk_dkrzDao = DAOFactory.createHZSMK_DKRZDAO();
      PojoInfo  hzsmk_hmdDao = DAOFactory.createV_HZSMK_HMDDAO();

      //事务开始

      for (int i = 0; i < vodkxx.length; i++) {
        //增加读卡信息
        PoHZSMK_DKRZ poHzsmk_dkrz = new PoHZSMK_DKRZ();
        BeanUtils.copyProperties(poHzsmk_dkrz, vodkxx[i]);
        poHzsmk_dkrz.setDkrzid( (Long) hzsmk_dkrzDao.getId());
        poHzsmk_dkrz.setCzyid(this.getUserInfo().getYhid());
        poHzsmk_dkrz.setDkjqipdz(BaseContext.getUser().getIp());
        poHzsmk_dkrz.setDksj(StringUtils.getServiceTime());
        super.create(poHzsmk_dkrz);

        //判断是否存在黑名单
        String gmsfhm = vodkxx[i].getGmsfhm();
        String kdsbm = vodkxx[i].getKdsbm();
        strSQL = "from PoV_HZSMK_HMD where 1=1 ";
        if (gmsfhm != null && !"".equals(gmsfhm)) {
          strSQL = strSQL + " and gmsfhm ='" + gmsfhm + "'";
        }
        List hmdList =super.findAllByHQL(strSQL);
        if (hmdList.size() != 0) {
          if (kdsbm != null && !"".equals(kdsbm)) {
            strSQL = strSQL + " and kdsbm ='" + kdsbm + "'";
          }
          hmdList =super.findAllByHQL(strSQL);
          if (hmdList.size() > 0) {
            strReturn = "1";
          }
        }
      }

      //事务提交

    }
    catch (DAOException ex) {
      //事务回滚
      throw ex;
    }
    catch (ServiceException ex) {
      //事务回滚
      throw ex;
    }
    catch (Exception ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return strReturn;
  }

  /**
   * 市民卡写卡信息保存
   * @param strHQL String
   * @param psCdsVo CdsVO[]
   * @throws ServiceException
   * @throws DAOException
   */
  public String processSmkxkxxbc(VoSmkxkrzxx[] voSmkxx) throws
      ServiceException,
      DAOException {
    String strReturn = null;

    try {
      PojoInfo  hzsmk_xkrzDao = DAOFactory.createHZSMK_XKRZDAO();

      //事务开始

      for (int i = 0; i < voSmkxx.length; i++) {
        VoSmkxkrzxx voSmkxkxx = voSmkxx[0];

        //增加写卡日志
        PoHZSMK_XKRZ poXkrz = new PoHZSMK_XKRZ();
        BeanUtils.copyProperties(poXkrz, voSmkxkxx);
        poXkrz.setXkrzid( (Long) hzsmk_xkrzDao.getId());
        poXkrz.setXgrid(this.getUserInfo().getYhid()); //修改人
        poXkrz.setXgjqip(BaseContext.getUser().getIp()); //修改机器IP
        poXkrz.setXgsj(StringUtils.getServiceTime()); //修改时间
        super.create(poXkrz);

        //增加修改日志
        if (!voSmkxkxx.getHb().equals(voSmkxkxx.getXgqhb())) { //比对户别
          smkxgrzbc(poXkrz, "hb", voSmkxkxx.getHb(), voSmkxkxx.getXgqhb());
        }
        if (!voSmkxkxx.getCzhkszd().trim().equals(voSmkxkxx.getXgqczhkszd().trim())) { //比对常住人口住址
          smkxgrzbc(poXkrz, "zz", voSmkxkxx.getHb(), voSmkxkxx.getXgqczhkszd());
        }
        if (!voSmkxkxx.getMz().equals(voSmkxkxx.getXgqmz())) { //比对民族
          smkxgrzbc(poXkrz, "mz", voSmkxkxx.getMz(), voSmkxkxx.getXgqmz());
        }
        if (!voSmkxkxx.getCsdssxq().equals(voSmkxkxx.getXgqcsd())) { //比对出生地
          smkxgrzbc(poXkrz, "csdssxq", voSmkxkxx.getCsdssxq(), voSmkxkxx.getXgqcsd());
        }
        if (!voSmkxkxx.getSzcsdm().equals(voSmkxkxx.getXgqcsdm())) { //比对城市代码
          smkxgrzbc(poXkrz, "csdm", voSmkxkxx.getSzcsdm(), voSmkxkxx.getXgqcsdm());
        }

        strReturn = "0";
      }

      //事务提交

    }
    catch (DAOException ex) {
      //事务回滚
      throw ex;
    }
    catch (ServiceException ex) {
      //事务回滚
      throw ex;
    }
    catch (Exception ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return strReturn;
  }

  /**
   * 市民卡读卡日志查询(PoHZSMK_DKRZ)
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult querySmkdkxxcx(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException {

    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append("from PoHZSMK_DKRZ as HZSMK_DKRZ ")
        .append("where 1=1 ");
    //数据范围begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_SMK_DKRZXXCX);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //居(村)委会
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //派出所
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or pcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "pcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //行政区划
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "ssxq='" +
                         voXtsjfw.getSjfw().trim() +
                         "' ");
        }
      } //for(int i = 0; i < sjfwList.size(); i++)
      if (sjfwHQL.length() > 0) {
        strFromHQL.append("and ( ")
            .append(sjfwHQL.toString())
            .append(") ");
      }
    }
    //数据范围end

    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append("and ")
          .append(strHQL);
    }

    //生成COUNT HQL语句
    if (voPage.getRecordcount() == -1) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //生成SELECT HQL语句
    strAllHQL.append("select HZSMK_DKRZ ").append(strFromHQL.toString());

    //debug info
    //_log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo  hzsmk_dkrzbDAO = DAOFactory.createHZSMK_DKRZDAO();


      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(
            strCountHQL.
            toString()));
      }
      poList =super.getPageRecords(strAllHQL.
          toString(),
          new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }

    //转换成Vo
    if (poList != null && poList.size() > 0) {
      voList = new ArrayList();
      for (int i = 0; i < poList.size(); i++) {
        PoHZSMK_DKRZ poHZSMK_DKRZ = (PoHZSMK_DKRZ) poList.get(i);
        voList.add(poHZSMK_DKRZ);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(PoHZSMK_DKRZ.class);
    return voQueryResult;
  }

  /**
   * 市民卡写卡日志查询
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult querySmkxkxxcx(String strHQL, VoPage voPage) throws
      ServiceException,DAOException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append("from PoHZSMK_XKRZ as HZSMK_XKRZ ")
        .append("where 1=1 ");
    //数据范围begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_SMK_XKRZXXCX);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //居(村)委会
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //派出所
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or pcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "pcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //行政区划
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "ssxq='" +
                         voXtsjfw.getSjfw().trim() +
                         "' ");
        }
      } //for(int i = 0; i < sjfwList.size(); i++)
      if (sjfwHQL.length() > 0) {
        strFromHQL.append("and ( ")
            .append(sjfwHQL.toString())
            .append(") ");
      }
    }
    //数据范围end

    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append("and ")
          .append(strHQL);
    }

    //生成COUNT HQL语句
    if (voPage.getRecordcount() == -1) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //生成SELECT HQL语句
    strAllHQL.append("select HZSMK_XKRZ ").append(strFromHQL.toString());

    //debug info
    //_log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo  hzsmk_xkrzbDAO = DAOFactory.createHZSMK_XKRZDAO();


      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(
            strCountHQL.
            toString()));
      }
      poList =super.getPageRecords(strAllHQL.
          toString(),
          new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }

    //转换成Vo
    if (poList != null && poList.size() > 0) {
      voList = new ArrayList();
      for (int i = 0; i < poList.size(); i++) {
        PoHZSMK_XKRZ poHZSMK_XKRZ = (PoHZSMK_XKRZ) poList.get(i);
        voList.add(poHZSMK_XKRZ);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(PoHZSMK_XKRZ.class);
    return voQueryResult;
  }


  /**
   *
   * @param poXkrz PoHZSMK_XKRZ
   * @param xgzdm String
   * @param xgcqn String
   * @param xghnr String
   * @throws ServiceException
   * @throws DAOException
   */
  private void smkxgrzbc(PoHZSMK_XKRZ poXkrz, String xgzdm, String xgqnr,
                         String xghnr) throws  DAOException {
    try {
      PojoInfo  hzsmk_xgrzDao = DAOFactory.createHZSMK_XGRZDAO();

      PoHZSMK_XGRZ pohzsmk_xgrz = new PoHZSMK_XGRZ();
      BeanUtils.copyProperties(pohzsmk_xgrz, poXkrz);
      pohzsmk_xgrz.setXgrzid( (Long) hzsmk_xgrzDao.getId());
//      pohzsmk_xgrz.setXkrzid(poXkrz.getXkrzid());
//      pohzsmk_xgrz.setRynbid(poXkrz.getRynbid());
//      pohzsmk_xgrz.setRyid(poXkrz.getRyid());
//      pohzsmk_xgrz.setGmsfhm(poXkrz.getGmsfhm());
//      pohzsmk_xgrz.setXm(poXkrz.getXm());
//      pohzsmk_xgrz.setSsxq(poXkrz.getSsxq());
//      pohzsmk_xgrz.setJlx(poXkrz.getJlx());
//      pohzsmk_xgrz.setPcs(poXkrz.getPcs());
//      pohzsmk_xgrz.setZrq(poXkrz.getZrq());
//      pohzsmk_xgrz.setXzjd(poXkrz.getXzjd());
//      pohzsmk_xgrz.setJcwh(poXkrz.getJcwh());
      pohzsmk_xgrz.setXgzdm(xgzdm); //修改字段名
      pohzsmk_xgrz.setXgqnr(xgqnr); //修改前内容
      pohzsmk_xgrz.setXghnr(xghnr); //修改后内容

      super.create(pohzsmk_xgrz);
    }
    catch (DAOException ex) {
      //事务回滚
      throw ex;
    }
    catch (Exception ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

  }

}
