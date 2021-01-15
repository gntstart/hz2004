package com.hzjc.hz2004.service.impl;

import com.hzjc.hz2004.service.*;
import java.util.*;
import com.hzjc.wsstruts.exception.*;
import com.hzjc.hz2004.dao.*;
import com.hzjc.hz2004.po.*;
import com.hzjc.hz2004.po.notable.PoOLD_HJYW_CSDJXXB;
import com.hzjc.hz2004.po.notable.PoOLD_HJYW_HCYBDXXB;
import com.hzjc.hz2004.po.notable.PoOLD_HJYW_QCZXXXB;
import com.hzjc.hz2004.po.notable.PoOLD_HJYW_QRDJXXB;
import com.hzjc.hz2004.po.notable.PoOLD_HJYW_SWZXXXB;
import com.hzjc.hz2004.po.notable.PoOLD_HJYW_ZZBDXXB;
import com.hzjc.hz2004.vo.*;
import com.hzjc.hz2004.constant.PublicConstant;
import com.hzjc.util.StringUtils;
import com.hzjc.wsstruts.vo.*;
import com.hzjc.hz2004.constant.ZjConstant;
import com.hzjc.hz2004.constant.HjConstant;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 历史信息查询</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */
@Service
public class LsServiceImpl
    extends ZjBaseService
    implements LsService {
  /**
   * 历史迁入登记信息查询
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryOLD_HJYW_QRDJXXB(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException {

    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append("from PoOLD_HJYW_QRDJXXB as OLD_HJYW_QRDJXXB ")
        .append("where 1=1 ");
    //数据范围begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(super.getUser().getYhid().toString(), PublicConstant.GNBH_HJ_LSQRDJXXHQ);
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
    strAllHQL.append("select OLD_HJYW_QRDJXXB ").append(strFromHQL.toString());

    //debug info
    //_log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo  old_hjyw_qrdjxxbDAO = DAOFactory.createOLD_HJYW_QRDJXXBDAO();


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
    voQueryResult.setPagelist(poList);
    voQueryResult.setVotype(PoOLD_HJYW_QRDJXXB.class);
    return voQueryResult;
  }

  /**
   * 历史出生登记信息查询
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryOLD_HJYW_CSDJXXB(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException {

    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append("from PoOLD_HJYW_CSDJXXB as OLD_HJYW_CSDJXXB ")
        .append("where 1=1 ");
    //数据范围begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_HJ_LSQRDJXXHQ);
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
    strAllHQL.append("select OLD_HJYW_CSDJXXB ").append(strFromHQL.toString());

    //debug info
    //_log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo  old_hjyw_csdjxxbDAO = DAOFactory.createOLD_HJYW_CSDJXXBDAO();


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
    voQueryResult.setPagelist(poList);
    voQueryResult.setVotype(PoOLD_HJYW_CSDJXXB.class);
    return voQueryResult;
  }

  /**
   * 历史迁出注销信息查询
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryOLD_HJYW_QCZXXXB(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException {

    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append("from PoOLD_HJYW_QCZXXXB as OLD_HJYW_QCZXXXB ")
        .append("where 1=1 ");
    //数据范围begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_HJ_LSQRDJXXHQ);
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
    strAllHQL.append("select OLD_HJYW_QCZXXXB ").append(strFromHQL.toString());

    //debug info
    //_log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo  old_hjyw_qczxxxbDAO = DAOFactory.createOLD_HJYW_QCZXXXBDAO();


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
    voQueryResult.setPagelist(poList);
    voQueryResult.setVotype(PoOLD_HJYW_QCZXXXB.class);
    return voQueryResult;
  }

  /**
   * 历史死亡注销信息查询
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryOLD_HJYW_SWZXXXB(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException {

    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append("from PoOLD_HJYW_SWZXXXB as OLD_HJYW_SWZXXXB ")
        .append("where 1=1 ");
    //数据范围begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_HJ_LSQRDJXXHQ);
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
    strAllHQL.append("select OLD_HJYW_SWZXXXB ").append(strFromHQL.toString());

    //debug info
    //_log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo  old_hjyw_swzxxxbDAO = DAOFactory.createOLD_HJYW_SWZXXXBDAO();


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
    voQueryResult.setPagelist(poList);
    voQueryResult.setVotype(PoOLD_HJYW_SWZXXXB.class);
    return voQueryResult;
  }

  /**
   * 历史住址变动信息查询
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryOLD_HJYW_ZZBDXXB(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException {

    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append("from PoOLD_HJYW_ZZBDXXB as OLD_HJYW_ZZBDXXB ")
        .append("where 1=1 ");
    //数据范围begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_HJ_LSQRDJXXHQ);
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
    strAllHQL.append("select OLD_HJYW_ZZBDXXB ").append(strFromHQL.toString());

    //debug info
    //_log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo  old_hjyw_zzbdxxbDAO = DAOFactory.createOLD_HJYW_ZZBDXXBDAO();


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
    voQueryResult.setPagelist(poList);
    voQueryResult.setVotype(PoOLD_HJYW_ZZBDXXB.class);
    return voQueryResult;
  }

  /**
   * 历史户成员信息查询
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryOLD_HJYW_HCYBDXXB(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException {

    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append("from PoOLD_HJYW_HCYBDXXB as OLD_HJYW_HCYBDXXB ")
        .append("where 1=1 ");
    //数据范围begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_HJ_LSQRDJXXHQ);
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
    strAllHQL.append("select OLD_HJYW_HCYBDXXB ").append(strFromHQL.toString());

    //debug info
    //_log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo  old_hjyw_hcybdxxbDAO = DAOFactory.createOLD_HJYW_HCYBDXXBDAO();


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
    voQueryResult.setPagelist(poList);
    voQueryResult.setVotype(PoOLD_HJYW_HCYBDXXB.class);
    return voQueryResult;
  }

  /**
   * 所有变更更正信息查询
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryV_HJYW_BGGZXXB(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException {

    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append("from PoV_HJYW_BGGZXXB as V_HJYW_BGGZXXB ")
        .append("where 1=1 ");
//lzh 只在人口查获查询中提供此接口，为了显示完整的人员信息，屏蔽数据范围
/*    //数据范围begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_HJ_LSQRDJXXHQ);
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
  */
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
    strAllHQL.append("select V_HJYW_BGGZXXB ").append(strFromHQL.toString());

    //debug info
    //_log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo  tempDAO = DAOFactory.createV_HJYW_BGGZXXBDAO();


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
    voQueryResult.setPagelist(poList);
    voQueryResult.setVotype(PoV_HJYW_BGGZXXB.class);
    return voQueryResult;
  }

  /**
   * 所有四变信息查询
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryV_HJTJ_RYBDXXB(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException {

    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append("from PoV_HJTJ_RYBDXXB as V_HJTJ_RYBDXXB ")
        .append("where 1=1 ");
//lzh 只在人口查获查询中提供此接口，为了显示完整的人员信息，屏蔽数据范围
/*    //数据范围begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_HJ_LSQRDJXXHQ);
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
  */
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
    strAllHQL.append("select V_HJTJ_RYBDXXB ").append(strFromHQL.toString());

    //debug info
    //_log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo  tempDAO = DAOFactory.createV_HJTJ_RYBDXXBDAO();


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
    voQueryResult.setPagelist(poList);
    voQueryResult.setVotype(PoV_HJTJ_RYBDXXB.class);
    return voQueryResult;
  }

  /**
   * 所有人员信息查询
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryV_HJXX_CZRKJBXXB(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException {

    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append("from PoV_HJXX_CZRKJBXXB as V_HJXX_CZRKJBXXB ")
        .append("where 1=1 ");

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
    strAllHQL.append("select V_HJXX_CZRKJBXXB ").append(strFromHQL.toString());

    //debug info
    //_log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo  tempDAO = DAOFactory.createV_HJXX_CZRKJBXXBDAO();


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
    voQueryResult.setPagelist(poList);
    voQueryResult.setVotype(PoV_HJXX_CZRKJBXXB.class);
    return voQueryResult;
  }

}
