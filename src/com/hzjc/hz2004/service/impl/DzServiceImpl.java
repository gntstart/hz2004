package com.hzjc.hz2004.service.impl;

import com.hzjc.hz2004.service.DzService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.wsstruts.exception.*;
import com.hzjc.hz2004.dao.*;
import java.util.*;
import com.hzjc.hz2004.po.*;
import org.apache.commons.logging.*;
import org.hibernate.LockOptions;
import org.springframework.stereotype.Service;

import com.hzjc.hz2004.vo.*;
import com.hzjc.wsstruts.vo.*;
import com.hzjc.util.StringUtils;
import com.hzjc.hz2004.constant.*;
import org.apache.commons.beanutils.BeanUtils;
import com.hzjc.hz2004.common.PID;
import java.lang.reflect.*;
import com.hzjc.hz2004.common.HjCommon;

/**
 * 地址业务类(户地业务类)
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
@Service
public class DzServiceImpl
    extends HjCommon
    implements DzService {

  //日志处理
  protected static Log _log = LogFactory.getLog(DzServiceImpl.class);

  /**
   * 处理户关系增加业务
   * @param voHgxzjxx - 户关系增加信息
   * @return - 户关系增加业务返回信息
   * @throws DAOException
   * @throws ServiceException
   */
  public VoHgxzjywfhxx processHgxzjyw(VoHgxzjxx voHgxzjxx[]) throws
      DAOException, ServiceException {

    VoHgxzjywfhxx voHgxzjywfhxx = null;
    VoHgxzjfhxx voHgxzjfhxx[] = null;
    String now = StringUtils.getServiceTime();

    if (voHgxzjxx == null || (voHgxzjxx != null && voHgxzjxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo hjxx_hglgxbDAO = DAOFactory.createHJXX_HGLGXBDAO();

      ///////////////////////////
      //事务开始

      /////////////////////////////////////
      //保存户关系增加信息
      voHgxzjfhxx = new VoHgxzjfhxx[voHgxzjxx.length * 2];
      for (int i = 0; i < voHgxzjxx.length; i++) {

        PoHJXX_HGLGXB poHglgx1 = new PoHJXX_HGLGXB();
        poHglgx1.setGlid( (Long) hjxx_hglgxbDAO.getId());
        poHglgx1.setHhid(voHgxzjxx[i].getHhid());
        poHglgx1.setGlhhid(voHgxzjxx[i].getGlhhid());
        poHglgx1.setGlgx(voHgxzjxx[i].getGlgx());
        poHglgx1.setJljlrid(this.getUserInfo().getYhid());
        poHglgx1.setJljlsj(now);
        poHglgx1.setZt(HjConstant.HGLZT_SGSR);
        super.create(poHglgx1);

        PoHJXX_HGLGXB poHglgx2 = new PoHJXX_HGLGXB();
        poHglgx2.setGlid( (Long) hjxx_hglgxbDAO.getId());
        poHglgx2.setHhid(voHgxzjxx[i].getGlhhid());
        poHglgx2.setGlhhid(voHgxzjxx[i].getHhid());
        poHglgx2.setGlgx(voHgxzjxx[i].getGlgx());
        poHglgx2.setJljlrid(this.getUserInfo().getYhid());
        poHglgx2.setJljlsj(now);
        poHglgx2.setZt(HjConstant.HGLZT_SGSR);
        super.create(poHglgx2);

        //生成返回信息
        voHgxzjfhxx[i * 2] = new VoHgxzjfhxx();
        voHgxzjfhxx[i * 2].setGlid(poHglgx1.getGlid());
        voHgxzjfhxx[i * 2 + 1] = new VoHgxzjfhxx();
        voHgxzjfhxx[i * 2 + 1].setGlid(poHglgx2.getGlid());
      }

      ////////////////////////////
      //生成业务返回信息
      voHgxzjywfhxx = new VoHgxzjywfhxx();
      voHgxzjywfhxx.setVoHgxzjfhxx(voHgxzjfhxx);

      /////////////////////////////
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

    return voHgxzjywfhxx;
  }

  /**
   * 处理户关系删除业务
   * @param voHgxscxx - 户关系删除信息
   * @return - 户关系删除业务返回信息
   * @throws DAOException
   * @throws ServiceException
   */
  public VoHgxscywfhxx processHgxscyw(VoHgxscxx voHgxscxx[]) throws
      DAOException, ServiceException {

    VoHgxscywfhxx voHgxscywfhxx = null;
    List hgxscfhxxList = new ArrayList();
    String strHQL = null;
    String now = StringUtils.getServiceTime();

    if (voHgxscxx == null || (voHgxscxx != null && voHgxscxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo hjxx_hglgxbDAO = DAOFactory.createHJXX_HGLGXBDAO();

      ///////////////////////////
      //事务开始

      /////////////////////////////////////
      //保存户关系删除信息
      for (int i = 0; i < voHgxscxx.length; i++) {

        if (voHgxscxx[i].getGlid() != null) {
          strHQL = "from PoHJXX_HGLGXB where glid=" + voHgxscxx[i].getGlid();
        }
        else
        if (voHgxscxx[i].getGlhhid() != null && voHgxscxx[i].getHhid() != null) {
          strHQL = "from PoHJXX_HGLGXB where (glhhid=" + voHgxscxx[i].getGlhhid() +
              " and hhid=" + voHgxscxx[i].getHhid() + ") or (glhhid=" +
              voHgxscxx[i].getHhid() +
              " and hhid=" + voHgxscxx[i].getGlhhid() + ") ";
        }
        else
        if (voHgxscxx[i].getGlhhid() != null) {
          strHQL = "from PoHJXX_HGLGXB where glhhid=" + voHgxscxx[i].getGlhhid() +
              " or hhid=" + voHgxscxx[i].getGlhhid();
        }
        else
        if (voHgxscxx[i].getHhid() != null) {
          strHQL = "from PoHJXX_HGLGXB where hhid=" + voHgxscxx[i].getHhid() +
              " or glhhid=" + voHgxscxx[i].getHhid();
        }
        else {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "户关系删除信息为空。", null);
        }

        List hglgxList = super.findAllByHQL(strHQL);
        if (hglgxList != null && hglgxList.size() > 0) {
          for (int a = 0; a < hglgxList.size(); a++) {
            PoHJXX_HGLGXB po = (PoHJXX_HGLGXB) hglgxList.get(a);
            //生成返回信息
            VoHgxscfhxx voHgxscfhxx = new VoHgxscfhxx();
            voHgxscfhxx.setGlid(po.getGlid());
            hgxscfhxxList.add(voHgxscfhxx);

            super.delete(po);
          }
        } //if(hglgxList!=null && hglgxList.size() > 0){
      }

      ////////////////////////////
      //生成业务返回信息
      voHgxscywfhxx = new VoHgxscywfhxx();
      voHgxscywfhxx.setVoHgxscfhxx( (VoHgxscfhxx[]) hgxscfhxxList.toArray(new
          VoHgxscfhxx[hgxscfhxxList.size()]));

      /////////////////////////////
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

    return voHgxscywfhxx;
  }

  /**
   * 户关系获取
   * @param strHQL
   * @param voPage
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQueryResult queryHgxxx(String strHQL, VoPage voPage) throws
      DAOException, ServiceException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append(
        "from PoHJXX_HGLGXB as HJXX_HGLGXB,PoHJXX_HXXB as HJXX_HXXB ")
        .append("where HJXX_HGLGXB.glhhid=HJXX_HXXB.hhid ")
        .append("and HJXX_HXXB.jlbz='" + PublicConstant.JLBZ_ZX + "' ")
        .append("and HJXX_HXXB.cxbz='" + PublicConstant.CXBZ_FCX + "' ");
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
    strAllHQL.append("select HJXX_HGLGXB,HJXX_HXXB ").append(strFromHQL.
        toString());

    //debug info
    _log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();

      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(strCountHQL.
            toString()));
      }
      poList = super.getPageRecords(strAllHQL.
          toString(), new Object[]{}, voPage.getPageindex(), voPage.getPagesize()).getList();
    }
    catch (DAOException ex) {
      throw ex;
    }

    //转换成Vo
    if (poList != null && poList.size() > 0) {
      voList = new ArrayList();
      for (int i = 0; i < poList.size(); i++) {
        Object ob[] = (Object[]) poList.get(i);
        PoHJXX_HGLGXB poHglgx = (PoHJXX_HGLGXB) ob[0];
        PoHJXX_HXXB poHxx = (PoHJXX_HXXB) ob[1];

        VoHgxHqFhxx voHgxHqFhxx = new VoHgxHqFhxx(poHglgx, poHxx);
        voList.add(voHgxHqFhxx);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoHgxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * 处理地址冻结增加业务
   * @param voDzdjzjxx[] - 地址冻结增加信息
   * @return VoDzdjzjywfhxx
   */
  public VoDzdjzjywfhxx processDzdjzjyw(VoDzdjzjxx voDzdjzjxx[]) throws
      ServiceException, DAOException {

    VoDzdjzjywfhxx voDzdjzjywfhxx = null;
    VoDzdjzjfhxx voDzdjzjfhxx[] = null;
    Long hjywid = null;
    String now = StringUtils.getServiceTime();

    if (voDzdjzjxx == null || (voDzdjzjxx != null && voDzdjzjxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();
      PojoInfo hjxz_mlpxxdjbDAO = DAOFactory.createHJXZ_MLPXXDJBDAO();

      ///////////////////////////
      //事务开始

      //////////////////////////////
      //得到户籍业务ID
      hjywid = (Long) hjls_hjywlsbDAO.getId();

      /////////////////////////////////////
      //保存地址冻结增加信息
      voDzdjzjfhxx = new VoDzdjzjfhxx[voDzdjzjxx.length];
      for (int i = 0; i < voDzdjzjxx.length; i++) {
        Long mlpdjid = null;

        //保存地址冻结信息的新记录
        PoHJXZ_MLPXXDJB poMlpxxdj = new PoHJXZ_MLPXXDJB();
        try {
          BeanUtils.copyProperties(poMlpxxdj, voDzdjzjxx[i]);
        }
        catch (InvocationTargetException ex1) {
        }
        catch (IllegalAccessException ex1) {
        }
        mlpdjid = (Long) hjxz_mlpxxdjbDAO.getId();
        poMlpxxdj.setMlpdjid(mlpdjid);
        poMlpxxdj.setCjhjywid(hjywid);
        super.create(poMlpxxdj);

        //业务限制
        VoHJXZ_MLPXXDJB voMlpxxdj = new VoHJXZ_MLPXXDJB(poMlpxxdj);
        VoXtywxz voLimit = XtywqxServiceImpl.VerifyBusinessLimit(PublicConstant.
            GNBH_DZ_DZDJZJYW, voMlpxxdj);
        if (voLimit.getLimitflag()) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "地址冻结增加业务受限，受限信息：" + voLimit.getLimitinfo(), null);
        }

        //生成返回信息
        voDzdjzjfhxx[i] = new VoDzdjzjfhxx();
        voDzdjzjfhxx[i].setMlpdjid(mlpdjid);
      }

      ////////////////////////////////////////
      //保存户籍业务流水信息
      this.saveHJYWLSXX(hjywid, PublicConstant.GNBH_DZ_DZDJZJYW,
                        PublicConstant.HJYWLS_YWLX_DZCZ,
                        voDzdjzjxx.length, null, now);

      ////////////////////////////
      //生成业务返回信息
      voDzdjzjywfhxx = new VoDzdjzjywfhxx();
      voDzdjzjywfhxx.setHjywid(hjywid);
      voDzdjzjywfhxx.setVoDzdjzjfhxx(voDzdjzjfhxx);

      /////////////////////////////
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

    return voDzdjzjywfhxx;
  }

  /**
   * 处理地址删除业务
   * @param voDzscxx[] - 地址删除信息
   * @return VoDzscywfhxx
   */
  public VoDzscywfhxx processDzscyw(VoDzscxx voDzscxx[]) throws
      ServiceException, DAOException {

    VoDzscywfhxx voDzscywfhxx = null;
    VoDzscfhxx voDzscfhxx[] = null;
    Long hjywid = null;
    String now = StringUtils.getServiceTime();

    if (voDzscxx == null || (voDzscxx != null && voDzscxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();
      PojoInfo hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();
      PojoInfo hjxx_hxxbDAO = DAOFactory.createHJXX_HXXBDAO();

      ///////////////////////////
      //事务开始

      //////////////////////////////
      //得到户籍业务ID
      hjywid = (Long) hjls_hjywlsbDAO.getId();

      /////////////////////////////////////
      //保存地址删除信息
      voDzscfhxx = new VoDzscfhxx[voDzscxx.length];
      for (int i = 0; i < voDzscxx.length; i++) {
        Long mlpnbid = null;

        //得到地信息
        PoHJXX_MLPXXXXB poMlpxxxx = super.get(PoHJXX_MLPXXXXB.class,
            voDzscxx[i].getMlpnbid());
        if (poMlpxxxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到该地信息，地址删除业务无法完成。", null);
        }
        if (poMlpxxxx.getJlbz().equalsIgnoreCase(PublicConstant.JLBZ_LS)) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "不允许对历史记录进行删除，地址删除业务无法完成。", null);
        }
        if (!poMlpxxxx.getMlpzt().equalsIgnoreCase(DzConstant.MLPZT_ZC)) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "不允许对非正常记录状态进行删除，地址删除业务无法完成。", null);
        }
        //数据范围限制
        List sjfwList = new ArrayList();
        VoXtsjfw voXtsjfw = new VoXtsjfw();
        voXtsjfw.setSjfw(poMlpxxxx.getJcwh());
        voXtsjfw.setSjfwbz(PublicConstant.XT_QX_JWH);
        sjfwList.add(voXtsjfw);
        boolean bLimit = false;
        bLimit = XtywqxServiceImpl.VerifyDataRange(this.getUserInfo().getYhid().
            toString(),
            PublicConstant.GNBH_DZ_DZSCYW, sjfwList);
        if (!bLimit) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
        		  CommonUtil.getSjfwError(sjfwList)  + "，地址删除业务无法完成。", null);
        }

        //判断该地信息是否被户信息引用,如果引用则不能删除
        String strHQL = new String();
        strHQL = "select count(*) from PoHJXX_HXXB where jlbz='" +
            PublicConstant.JLBZ_ZX + "' and mlpnbid=" +
            voDzscxx[i].getMlpnbid().toString();
        if (super.getCount(strHQL) > 0) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "该地址被引用不能作删除操作，地址删除业务无法完成。", null);
        }

        //置地信息为历史
        poMlpxxxx.setJssj(now);
        poMlpxxxx.setCdlb(HjConstant.CDLB_YWZX);
        poMlpxxxx.setCchjywid(hjywid);
        poMlpxxxx.setJlbz(PublicConstant.JLBZ_LS);
        super.update(poMlpxxxx);
        //保存地信息的新记录
        PoHJXX_MLPXXXXB poMlpxxxxNew = new PoHJXX_MLPXXXXB();
        try {
          BeanUtils.copyProperties(poMlpxxxxNew, poMlpxxxx);
        }
        catch (InvocationTargetException ex1) {
        }
        catch (IllegalAccessException ex1) {
        }
        mlpnbid = (Long) hjxx_mlpxxxxbDAO.getId();
        poMlpxxxxNew.setMlpnbid(mlpnbid);
        poMlpxxxxNew.setMlpzt(DzConstant.MLPZT_SC);
        poMlpxxxxNew.setQysj(now);
        poMlpxxxxNew.setJssj(null);
        poMlpxxxxNew.setCdlb(null);
        poMlpxxxxNew.setCdsj(now);
        poMlpxxxxNew.setJdlb(HjConstant.JDLB_YWJL);
        poMlpxxxxNew.setCchjywid(null);
        poMlpxxxxNew.setCjhjywid(hjywid);
        poMlpxxxxNew.setJlbz(PublicConstant.JLBZ_ZX);
        super.create(poMlpxxxxNew);

        //业务限制
        VoHJXX_MLPXXXXB voMlpxxxx = new VoHJXX_MLPXXXXB(poMlpxxxxNew);
        VoXtywxz voLimit = XtywqxServiceImpl.VerifyBusinessLimit(PublicConstant.
            GNBH_DZ_DZSCYW, voMlpxxxx);
        if (voLimit.getLimitflag()) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "地址删除业务受限，受限信息：" + voLimit.getLimitinfo(), null);
        }

        //生成返回信息
        voDzscfhxx[i] = new VoDzscfhxx();
        voDzscfhxx[i].setMlpnbid(poMlpxxxxNew.getMlpnbid());
        voDzscfhxx[i].setOld_mlpnbid(poMlpxxxx.getMlpnbid());
      }

      ////////////////////////////////////////
      //保存户籍业务流水信息
      this.saveHJYWLSXX(hjywid, PublicConstant.GNBH_DZ_DZSCYW,
                        PublicConstant.HJYWLS_YWLX_DZCZ,
                        voDzscxx.length, null, now);

      ////////////////////////////
      //生成业务返回信息
      voDzscywfhxx = new VoDzscywfhxx();
      voDzscywfhxx.setHjywid(hjywid);
      voDzscywfhxx.setVoDzscfhxx(voDzscfhxx);

      /////////////////////////////
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

    return voDzscywfhxx;
  }

  /**
   * 处理地址修改业务
   * @param voDzxgxx[] - 地址修改信息
   * @return VoDzxgywfhxx
   */
  public VoDzxgywfhxx processDzxgyw(VoDzxgxx voDzxgxx[]) throws
      ServiceException, DAOException {

    VoDzxgywfhxx voDzxgywfhxx = null;
    VoDzxgfhxx voDzxgfhxx[] = null;
    Long hjywid = null;
    String now = StringUtils.getServiceTime();
    String strHQL = null;

    if (voDzxgxx == null || (voDzxgxx != null && voDzxgxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();
      PojoInfo hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();

      ///////////////////////////
      //事务开始

      //////////////////////////////
      //得到户籍业务ID
      hjywid = (Long) hjls_hjywlsbDAO.getId();

      /////////////////////////////////////
      //保存地址修改信息
      voDzxgfhxx = new VoDzxgfhxx[voDzxgxx.length];
      for (int i = 0; i < voDzxgxx.length; i++) {

        //得到地信息
        PoHJXX_MLPXXXXB poMlpxxxx = super.get(PoHJXX_MLPXXXXB.class,
            voDzxgxx[i].getMlpnbid());
        if (poMlpxxxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到该地信息，地址修改业务无法完成。", null);
        }
        //Begin_增加于闵红斌(2004/11/02 19:37:00)
        super.refresh(poMlpxxxx, LockOptions.UPGRADE); //Lock
        //End
        if (poMlpxxxx.getJlbz().equalsIgnoreCase(PublicConstant.JLBZ_LS)) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "不允许对历史记录进行修改，地址修改业务无法完成。", null);
        }
        if (!poMlpxxxx.getMlpzt().equalsIgnoreCase(DzConstant.MLPZT_ZC)) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "不允许对非正常记录状态进行修改，地址修改业务无法完成。", null);
        }
        //数据范围限制
        List sjfwList = new ArrayList();
        VoXtsjfw voXtsjfw = new VoXtsjfw();
        voXtsjfw.setSjfw(poMlpxxxx.getJcwh());
        voXtsjfw.setSjfwbz(PublicConstant.XT_QX_JWH);
        sjfwList.add(voXtsjfw);
        boolean bLimit = false;
        bLimit = XtywqxServiceImpl.VerifyDataRange(this.getUserInfo().getYhid().
            toString(),
            PublicConstant.GNBH_DZ_DZXGYW, sjfwList);
        if (!bLimit) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
        		  CommonUtil.getSjfwError(sjfwList)  + "，地址修改业务无法完成。", null);
        }

        //置地信息为历史
        poMlpxxxx.setJssj(now);
        poMlpxxxx.setCdlb(HjConstant.CDLB_YWZX);
        poMlpxxxx.setCchjywid(hjywid);
        poMlpxxxx.setJlbz(PublicConstant.JLBZ_LS);
        super.update(poMlpxxxx);
        //保存地信息的新记录
        PoHJXX_MLPXXXXB poMlpxxxxNew = new PoHJXX_MLPXXXXB();
        BeanUtils.copyProperties(poMlpxxxxNew, poMlpxxxx);
        BeanUtils.copyProperties(poMlpxxxxNew, voDzxgxx[i]);
        poMlpxxxxNew.setPxh(this.generatePXH(poMlpxxxxNew.getMlph(),
                                             poMlpxxxxNew.getMlxz()));
        poMlpxxxxNew.setMlpnbid( (Long) hjxx_mlpxxxxbDAO.getId());
        poMlpxxxxNew.setQysj(now);
        poMlpxxxxNew.setJssj(null);
        poMlpxxxxNew.setCdlb(null);
        poMlpxxxxNew.setJdlb(HjConstant.JDLB_YWJL);
        poMlpxxxxNew.setCchjywid(null);
        poMlpxxxxNew.setCjhjywid(hjywid);
        poMlpxxxxNew.setJlbz(PublicConstant.JLBZ_ZX);
        super.create(poMlpxxxxNew);

        //业务限制
        VoHJXX_MLPXXXXB voMlpxxxx = new VoHJXX_MLPXXXXB(poMlpxxxxNew);
        VoXtywxz voLimit = XtywqxServiceImpl.VerifyBusinessLimit(PublicConstant.
            GNBH_DZ_DZXGYW, voMlpxxxx);
        if (voLimit.getLimitflag()) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "地址修改业务受限，受限信息：" + voLimit.getLimitinfo(), null);
        }

        //修改地信息上的人户信息
        this.changeRHXX_DXX(hjywid, poMlpxxxx,
                            poMlpxxxxNew,
                            PublicConstant.GNBH_DZ_DZXGYW, null, now);

        //生成返回信息
        voDzxgfhxx[i] = new VoDzxgfhxx();
        voDzxgfhxx[i].setMlpnbid(poMlpxxxxNew.getMlpnbid());
        voDzxgfhxx[i].setOld_mlpnbid(poMlpxxxx.getMlpnbid());
      } //for (int i = 0; i < voDzxgxx.length; i++)

      ////////////////////////////////////////
      //保存户籍业务流水信息
      this.saveHJYWLSXX(hjywid, PublicConstant.GNBH_DZ_DZXGYW,
                        PublicConstant.HJYWLS_YWLX_DZCZ,
                        voDzxgxx.length, null, now);

      ////////////////////////////
      //生成业务返回信息
      voDzxgywfhxx = new VoDzxgywfhxx();
      voDzxgywfhxx.setHjywid(hjywid);
      voDzxgywfhxx.setVoDzxgfhxx(voDzxgfhxx);

      /////////////////////////////
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
    catch (InvocationTargetException ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (IllegalAccessException ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (Exception ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return voDzxgywfhxx;
  }

  /**
   * 处理地址增加业务
   * @param voDzzjxx[] - 地址增加信息
   * @return VoDzzjywfhxx
   */
  public VoDzzjywfhxx processDzzjyw(VoDzzjxx voDzzjxx[]) throws
      ServiceException, DAOException {

    VoDzzjywfhxx voDzzjywfhxx = null;
    VoDzzjfhxx voDzzjfhxx[] = null;
    Long hjywid = null;
    String now = StringUtils.getServiceTime();

    if (voDzzjxx == null || (voDzzjxx != null && voDzzjxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();
      PojoInfo hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();
      PojoInfo xt_jwhxxbDAO = DAOFactory.createXT_JWHXXBDAO();

      ///////////////////////////
      //事务开始

      //////////////////////////////
      //得到户籍业务ID
      hjywid = (Long) hjls_hjywlsbDAO.getId();

      /////////////////////////////////////
      //保存地址增加信息
      voDzzjfhxx = new VoDzzjfhxx[voDzzjxx.length];
      for (int i = 0; i < voDzzjxx.length; i++) {
        Long mlpnbid = null;

        if (this.getDXX(voDzzjxx[i]) != null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "地址已经存在，地址增加业务无法完成。", null);
        }

        //保存地信息的新记录
        PoHJXX_MLPXXXXB poMlpxxxx = new PoHJXX_MLPXXXXB();
        BeanUtils.copyProperties(poMlpxxxx, voDzzjxx[i]);
        mlpnbid = (Long) hjxx_mlpxxxxbDAO.getId();
        poMlpxxxx.setMlpnbid(mlpnbid);
        poMlpxxxx.setMlpid(mlpnbid);
        //Begin_修改于闵红斌(2004/11/15 17:25:00)
        /*
                 poMlpxxxx.setXzjd(voDzzjxx[i].getJcwh() != null &&
                          voDzzjxx[i].getJcwh().length() > 9 ?
                          voDzzjxx[i].getJcwh().substring(0, 9) : null);
         */
        PoXT_JWHXXB poXT_JWHXXB = (PoXT_JWHXXB) super.get(PoXT_JWHXXB.class,
            voDzzjxx[i].getJcwh()); //通过居委会代码得到乡镇街道代码
        if (poXT_JWHXXB == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                     "通过居委会代码无法得到乡镇街道代码,地址增加业务无法完成。", null);
        }
        poMlpxxxx.setXzjd(poXT_JWHXXB.getXzjddm());
        //End_修改于闵红斌(2004/11/15 17:25:00)
        poMlpxxxx.setPxh(this.generatePXH(voDzzjxx[i].getMlph(),
                                          voDzzjxx[i].getMlxz()));
        poMlpxxxx.setMlpzt(DzConstant.MLPZT_ZC);

        poMlpxxxx.setQysj(now);
        poMlpxxxx.setJssj(null);
        poMlpxxxx.setJdsj(now);
        poMlpxxxx.setJdlb(HjConstant.JDLB_YWJL);
        poMlpxxxx.setCchjywid(null);
        poMlpxxxx.setCjhjywid(hjywid);
        poMlpxxxx.setJlbz(PublicConstant.JLBZ_ZX);
        super.create(poMlpxxxx);

        //业务限制
        VoHJXX_MLPXXXXB voMlpxxxx = new VoHJXX_MLPXXXXB(poMlpxxxx);
        VoXtywxz voLimit = XtywqxServiceImpl.VerifyBusinessLimit(PublicConstant.
            GNBH_DZ_DZZJYW, voMlpxxxx);
        if (voLimit.getLimitflag()) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "地址增加业务受限，受限信息：" + voLimit.getLimitinfo(), null);
        }

        //生成返回信息
        voDzzjfhxx[i] = new VoDzzjfhxx();
        voDzzjfhxx[i].setMlpnbid(mlpnbid);
      }

      ////////////////////////////////////////
      //保存户籍业务流水信息
      this.saveHJYWLSXX(hjywid, PublicConstant.GNBH_DZ_DZZJYW,
                        PublicConstant.HJYWLS_YWLX_DZCZ,
                        voDzzjxx.length, null, now);

      ////////////////////////////
      //生成业务返回信息
      voDzzjywfhxx = new VoDzzjywfhxx();
      voDzzjywfhxx.setHjywid(hjywid);
      voDzzjywfhxx.setVoDzzjfhxx(voDzzjfhxx);

      /////////////////////////////
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
    catch (InvocationTargetException ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (IllegalAccessException ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (Exception ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return voDzzjywfhxx;
  }

  /**
   * 处理地址注销业务
   * @param voDzzxxx[] - 地址注销信息
   * @return VoDzzxywfhxx
   */
  public VoDzzxywfhxx processDzzxyw(VoDzzxxx voDzzxxx[]) throws
      ServiceException, DAOException {

    VoDzzxywfhxx voDzzxywfhxx = null;
    VoDzzxfhxx voDzzxfhxx[] = null;
    Long hjywid = null;
    String now = StringUtils.getServiceTime();

    if (voDzzxxx == null || (voDzzxxx != null && voDzzxxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();
      PojoInfo hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();
      PojoInfo hjxx_hxxbDAO = DAOFactory.createHJXX_HXXBDAO();

      ///////////////////////////
      //事务开始
      //////////////////////////////
      //得到户籍业务ID
      hjywid = (Long) hjls_hjywlsbDAO.getId();

      /////////////////////////////////////
      //保存地址注销信息
      voDzzxfhxx = new VoDzzxfhxx[voDzzxxx.length];
      for (int i = 0; i < voDzzxxx.length; i++) {
        Long mlpnbid = null;

        //得到地信息
        PoHJXX_MLPXXXXB poMlpxxxx = super.get(PoHJXX_MLPXXXXB.class,
            voDzzxxx[i].getMlpnbid());
        if (poMlpxxxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到该地信息，地址注销业务无法完成。", null);
        }
        if (poMlpxxxx.getJlbz().equalsIgnoreCase(PublicConstant.JLBZ_LS)) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "不允许对历史记录进行注销，地址注销业务无法完成。", null);
        }
        if (!poMlpxxxx.getMlpzt().equalsIgnoreCase(DzConstant.MLPZT_ZC)) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "不允许对非正常记录状态进行注销，地址注销业务无法完成。", null);
        }
        //数据范围限制
        List sjfwList = new ArrayList();
        VoXtsjfw voXtsjfw = new VoXtsjfw();
        voXtsjfw.setSjfw(poMlpxxxx.getJcwh());
        voXtsjfw.setSjfwbz(PublicConstant.XT_QX_JWH);
        sjfwList.add(voXtsjfw);
        boolean bLimit = false;
        bLimit = XtywqxServiceImpl.VerifyDataRange(this.getUserInfo().getYhid().
            toString(),
            PublicConstant.GNBH_DZ_DZZXYW, sjfwList);
        if (!bLimit) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
        		  CommonUtil.getSjfwError(sjfwList)  + "，地址注销业务无法完成。", null);
        }

        //判断该地信息是否被户信息引用,如果引用则不能删除
        String strHQL = new String();
        strHQL = "select count(*) from PoHJXX_HXXB where jlbz='" +
            PublicConstant.JLBZ_ZX + "' and mlpnbid=" +
            voDzzxxx[i].getMlpnbid().toString();
        if (super.getCount(strHQL) > 0) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "该地址被引用不能作注销操作，地址注销业务无法完成。", null);
        }

        //置地信息为历史
        poMlpxxxx.setJssj(now);
        poMlpxxxx.setCdlb(HjConstant.CDLB_YWZX);
        poMlpxxxx.setCchjywid(hjywid);
        poMlpxxxx.setJlbz(PublicConstant.JLBZ_LS);
        super.update(poMlpxxxx);
        //保存地信息的新记录
        PoHJXX_MLPXXXXB poMlpxxxxNew = new PoHJXX_MLPXXXXB();
        BeanUtils.copyProperties(poMlpxxxxNew, poMlpxxxx);
        mlpnbid = (Long) hjxx_mlpxxxxbDAO.getId();
        poMlpxxxxNew.setMlpnbid(mlpnbid);
        poMlpxxxxNew.setCdsj(now);
        poMlpxxxxNew.setMlpzt(DzConstant.MLPZT_CX);
        poMlpxxxxNew.setQysj(now);
        poMlpxxxxNew.setJssj(null);
        poMlpxxxxNew.setCdlb(null);
        poMlpxxxxNew.setJdlb(HjConstant.JDLB_YWJL);
        poMlpxxxxNew.setCchjywid(null);
        poMlpxxxxNew.setCjhjywid(hjywid);
        poMlpxxxxNew.setJlbz(PublicConstant.JLBZ_ZX);
        super.create(poMlpxxxxNew);

        //业务限制
        VoHJXX_MLPXXXXB voMlpxxxx = new VoHJXX_MLPXXXXB(poMlpxxxxNew);
        VoXtywxz voLimit = XtywqxServiceImpl.VerifyBusinessLimit(PublicConstant.
            GNBH_DZ_DZZXYW, voMlpxxxx);
        if (voLimit.getLimitflag()) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "地址注销业务受限，受限信息：" + voLimit.getLimitinfo(), null);
        }

        //生成返回信息
        voDzzxfhxx[i] = new VoDzzxfhxx();
        voDzzxfhxx[i].setMlpnbid(poMlpxxxx.getMlpnbid());
        voDzzxfhxx[i].setOld_mlpnbid(poMlpxxxxNew.getMlpnbid());
      }

      ////////////////////////////////////////
      //保存户籍业务流水信息
      this.saveHJYWLSXX(hjywid, PublicConstant.GNBH_DZ_DZZXYW,
                        PublicConstant.HJYWLS_YWLX_DZCZ,
                        voDzzxxx.length, null, now);

      ////////////////////////////
      //生成业务返回信息
      voDzzxywfhxx = new VoDzzxywfhxx();
      voDzzxywfhxx.setHjywid(hjywid);
      voDzzxywfhxx.setVoDzzxfhxx(voDzzxfhxx);

      /////////////////////////////
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
    catch (InvocationTargetException ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (IllegalAccessException ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (Exception ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return voDzzxywfhxx;
  }

  /**
   * 处理地址冻结删除业务
   * @param voDzdjscxx[] - 地址冻结删除信息
   * @return VoDzdjscywfhxx
   */
  public VoDzdjscywfhxx processDzdjscyw(VoDzdjscxx voDzdjscxx[]) throws
      ServiceException, DAOException {

    VoDzdjscywfhxx voDzdjscywfhxx = null;
    VoDzdjscfhxx voDzdjscfhxx[] = null;
    Long hjywid = null;
    String now = StringUtils.getServiceTime();

    if (voDzdjscxx == null || (voDzdjscxx != null && voDzdjscxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();
      PojoInfo hjxz_mlpxxdjbDAO = DAOFactory.createHJXZ_MLPXXDJBDAO();

      ///////////////////////////
      //事务开始

      //////////////////////////////
      //得到户籍业务ID
      hjywid = (Long) hjls_hjywlsbDAO.getId();

      /////////////////////////////////////
      //保存地址冻结删除信息
      voDzdjscfhxx = new VoDzdjscfhxx[voDzdjscxx.length];
      for (int i = 0; i < voDzdjscxx.length; i++) {
        Long mlpdjid = null;

        //得到地址冻结记录
        PoHJXZ_MLPXXDJB poMlpxxdj = super.get(PoHJXZ_MLPXXDJB.class,
            voDzdjscxx[i].getMlpdjid());
        if (poMlpxxdj == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到该地址冻结信息，地址冻结删除业务无法完成。", null);
        }
        super.delete(poMlpxxdj);

        //业务限制
        VoHJXZ_MLPXXDJB voMlpxxdj = new VoHJXZ_MLPXXDJB(poMlpxxdj);
        VoXtywxz voLimit = XtywqxServiceImpl.VerifyBusinessLimit(PublicConstant.
            GNBH_DZ_DZDJSCYW, voMlpxxdj);
        if (voLimit.getLimitflag()) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "地址冻结删除业务受限，受限信息：" + voLimit.getLimitinfo(), null);
        }

        //生成返回信息
        voDzdjscfhxx[i] = new VoDzdjscfhxx();
        voDzdjscfhxx[i].setMlpdjid(mlpdjid);
      }

      ////////////////////////////////////////
      //保存户籍业务流水信息
      this.saveHJYWLSXX(hjywid, PublicConstant.GNBH_DZ_DZDJSCYW,
                        PublicConstant.HJYWLS_YWLX_DZCZ,
                        voDzdjscxx.length, null, now);

      ////////////////////////////
      //生成业务返回信息
      voDzdjscywfhxx = new VoDzdjscywfhxx();
      voDzdjscywfhxx.setHjywid(hjywid);
      voDzdjscywfhxx.setVoDzdjscfhxx(voDzdjscfhxx);

      /////////////////////////////
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

    return voDzdjscywfhxx;
  }

  /**
   * 处理地址冻结修改业务
   * @param voDzdjxgxx[] - 地址冻结修改信息
   * @return VoDzdjxgywfhxx
   */
  public VoDzdjxgywfhxx processDzdjxgyw(VoDzdjxgxx voDzdjxgxx[]) throws
      ServiceException, DAOException {

    VoDzdjxgywfhxx voDzdjxgywfhxx = null;
    VoDzdjxgfhxx voDzdjxgfhxx[] = null;
    Long hjywid = null;
    String now = StringUtils.getServiceTime();

    if (voDzdjxgxx == null || (voDzdjxgxx != null && voDzdjxgxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();
      PojoInfo hjxz_mlpxxdjbDAO = DAOFactory.createHJXZ_MLPXXDJBDAO();

      ///////////////////////////
      //事务开始

      //////////////////////////////
      //得到户籍业务ID
      hjywid = (Long) hjls_hjywlsbDAO.getId();

      /////////////////////////////////////
      //保存地址冻结修改信息
      voDzdjxgfhxx = new VoDzdjxgfhxx[voDzdjxgxx.length];
      for (int i = 0; i < voDzdjxgxx.length; i++) {
        Long mlpdjid = null;

        //得到地址冻结记录
        PoHJXZ_MLPXXDJB poMlpxxdj = super.get(PoHJXZ_MLPXXDJB.class,
            voDzdjxgxx[i].getMlpdjid());
        if (poMlpxxdj == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到该地址冻结信息，地址冻结修改业务无法完成。", null);
        }
        //保存地址冻结修改信息
        BeanUtils.copyProperties(poMlpxxdj, voDzdjxgxx[i]);
        poMlpxxdj.setXghjywid(hjywid);
        super.update(poMlpxxdj);

        //业务限制
        VoHJXZ_MLPXXDJB voMlpxxdj = new VoHJXZ_MLPXXDJB(poMlpxxdj);
        VoXtywxz voLimit = XtywqxServiceImpl.VerifyBusinessLimit(PublicConstant.
            GNBH_DZ_DZDJXGYW, voMlpxxdj);
        if (voLimit.getLimitflag()) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "地址冻结修改业务受限，受限信息：" + voLimit.getLimitinfo(), null);
        }

        //生成返回信息
        voDzdjxgfhxx[i] = new VoDzdjxgfhxx();
        voDzdjxgfhxx[i].setMlpdjid(mlpdjid);
      }

      ////////////////////////////////////////
      //保存户籍业务流水信息
      this.saveHJYWLSXX(hjywid, PublicConstant.GNBH_DZ_DZDJXGYW,
                        PublicConstant.HJYWLS_YWLX_DZCZ,
                        voDzdjxgxx.length, null, now);

      ////////////////////////////
      //生成业务返回信息
      voDzdjxgywfhxx = new VoDzdjxgywfhxx();
      voDzdjxgywfhxx.setHjywid(hjywid);
      voDzdjxgywfhxx.setVoDzdjxgfhxx(voDzdjxgfhxx);

      /////////////////////////////
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
    catch (InvocationTargetException ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (IllegalAccessException ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (Exception ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return voDzdjxgywfhxx;
  }

  /**
   * 得到地址信息，地址信息由PoHJXX_MLPXXXXB(门（楼）牌详细信息表)组成。
   * @param strHQL
   * @param voPage
   * @return VoQueryResult
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryDzxx(String strHQL, VoPage voPage) throws
      ServiceException, DAOException {

    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poMlpxxList = null;
    List voMlpxxList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append("from PoHJXX_MLPXXXXB as HJXX_MLPXXXXB ")
        .append(
        "where 1=1 ");
    //数据范围begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_DZ_DZXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //居(村)委会
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_MLPXXXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_MLPXXXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //派出所
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_MLPXXXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_MLPXXXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //行政区划
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_MLPXXXXB.ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_MLPXXXXB.ssxq='" +
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
    strAllHQL.append("select HJXX_MLPXXXXB ").append(strFromHQL.toString());

    //debug info
    _log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();


      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(strCountHQL.
            toString()));
      }
      poMlpxxList = super.getPageRecords(strAllHQL.
          toString(),
          new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();
    }
    catch (DAOException ex) {
      throw ex;
    }

    //转换成Vo
    if (poMlpxxList != null && poMlpxxList.size() > 0) {
      voMlpxxList = new ArrayList();
      for (int i = 0; i < poMlpxxList.size(); i++) {
        PoHJXX_MLPXXXXB poHJXX_MLPXXXXB = (PoHJXX_MLPXXXXB) poMlpxxList.get(i);

        VoDzxxHqFhxx voDzxxHqFhxx = new VoDzxxHqFhxx(poHJXX_MLPXXXXB);
        voMlpxxList.add(voDzxxHqFhxx);
      }
    }

    voQueryResult.setPagelist(voMlpxxList);
    voQueryResult.setVotype(VoDzxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * 得到地址冻结信息，地址冻结信息由PoHJXX_MLPXXDJB(门（楼）牌信息冻结表)组成。
   * @param strHQL
   * @param voPage
   * @return VoQueryResult
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryDzdjxx(String strHQL, VoPage voPage) throws
      ServiceException, DAOException {

    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poMlpxxdjList = null;
    List voMlpxxdjList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append("from PoHJXZ_MLPXXDJB as HJXZ_MLPXXDJB ")
        .append("where 1=1 ");
    //数据范围begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_DZ_DZDJXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //居(村)委会
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_MLPXXXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_MLPXXXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        /*
                 //派出所
             else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
         sjfwHQL.append(sjfwHQL.length() > 0 ?
                        "or HJXX_MLPXXXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                        "' " :
                        "HJXX_MLPXXXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                        "' ");
                 }*/
        //行政区划
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_MLPXXXXB.ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_MLPXXXXB.ssxq='" +
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
    strAllHQL.append("select HJXZ_MLPXXDJB ").append(strFromHQL.toString());

    //debug info
    _log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo hjxz_mlpxxdjbDAO = DAOFactory.createHJXZ_MLPXXDJBDAO();


      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(strCountHQL.
            toString()));
      }
      poMlpxxdjList = super.getPageRecords(strAllHQL.
          toString(),
          new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }

    //转换成Vo
    if (poMlpxxdjList != null && poMlpxxdjList.size() > 0) {
      voMlpxxdjList = new ArrayList();
      for (int i = 0; i < poMlpxxdjList.size(); i++) {
        PoHJXZ_MLPXXDJB poHJXZ_MLPXXDJB = (PoHJXZ_MLPXXDJB) poMlpxxdjList.get(i);

        VoDzdjxxHqFhxx voDzdjxxHqFhxx = new VoDzdjxxHqFhxx(poHJXZ_MLPXXDJB);
        voMlpxxdjList.add(voDzdjxxHqFhxx);
      }
    }

    voQueryResult.setPagelist(voMlpxxdjList);
    voQueryResult.setVotype(VoDzdjxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * 处理区划调整业务
   * @param VoQhtzxx[] - 区划调整信息
   * @return VoQhtzywfhxx
   */
  public VoQhtzywfhxx processQhtzyw(VoQhtzxx voQhtzxx[]) throws
      ServiceException, DAOException {

    VoQhtzywfhxx voQhtzywfhxx = null;
    VoQhtzfhxx voQhtzfhxx[] = null;
    Long hjywid = null;
    String now = StringUtils.getServiceTime();
    String strHQL = null;

    if (voQhtzxx == null || (voQhtzxx != null && voQhtzxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();
      PojoInfo hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();
      PojoInfo xt_jwhxxbDAO = DAOFactory.createXT_JWHXXBDAO();
      PojoInfo xt_hhxlbDAO = DAOFactory.createXT_HHXLBDAO();

      ///////////////////////////
      //事务开始

      //当进行多地区划调整时锁住要调往的所有派出所(增加于MHB 2005/09/03 17:50:00)
      strHQL = "from PoXT_HHXLB where";
      for (int i = 0; i < voQhtzxx.length; i++) {
        if (voQhtzxx[i].getPcs() == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "要调往的地信息中的派出所代码不能为空，区划调整业务无法完成。", null);
        }
        if (i > 0) {
          strHQL += "' or dwdm='" + voQhtzxx[i].getPcs().trim();
        }
        else {
          strHQL += " dwdm='" + voQhtzxx[i].getPcs().trim();
        }
      }
      strHQL += "' order by xlid ";//strHQL += "' order by xlid for update";
      super.findAllByHQL(strHQL);

      //////////////////////////////
      //得到户籍业务ID
      hjywid = (Long) hjls_hjywlsbDAO.getId();

      /////////////////////////////////////
      //保存地址修改信息
      voQhtzfhxx = new VoQhtzfhxx[voQhtzxx.length];
      for (int i = 0; i < voQhtzxx.length; i++) {

        //得到地信息
        PoHJXX_MLPXXXXB poMlpxxxx = super.get(PoHJXX_MLPXXXXB.class,
            voQhtzxx[i].getMlpnbid());
        if (poMlpxxxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到该地信息，区划调整业务无法完成。", null);
        }
        //Begin_增加于闵红斌(2004/11/02 19:37:00)
        super.refresh(poMlpxxxx, LockOptions.UPGRADE); //Lock
        //End
        if (PublicConstant.JLBZ_LS.equals(poMlpxxxx.getJlbz())) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "不允许对历史记录进行修改，区划调整业务无法完成。", null);
        }
        if (!DzConstant.MLPZT_ZC.equals(poMlpxxxx.getMlpzt())) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "不允许对非正常记录状态进行修改，区划调整业务无法完成。", null);
        }
        //数据范围限制
        List sjfwList = new ArrayList();
        VoXtsjfw voXtsjfw = new VoXtsjfw();
        voXtsjfw.setSjfw(poMlpxxxx.getJcwh());
        voXtsjfw.setSjfwbz(PublicConstant.XT_QX_JWH);
        sjfwList.add(voXtsjfw);
        boolean bLimit = false;
        bLimit = XtywqxServiceImpl.VerifyDataRange(this.getUserInfo().getYhid().
            toString(),
            PublicConstant.GNBH_DZ_QHTZYW, sjfwList);
        if (!bLimit) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
        		  CommonUtil.getSjfwError(sjfwList)  + "，区划调整业务无法完成。", null);
        }

        //保存地信息为历史记录
        poMlpxxxx.setJssj(now);
        poMlpxxxx.setCdlb(HjConstant.CDLB_QHTZZX);
        poMlpxxxx.setCchjywid(hjywid);
        poMlpxxxx.setJlbz(PublicConstant.JLBZ_LS);
        super.update(poMlpxxxx);
        //保存地信息为最新记录并注销地(撤消地)
        PoHJXX_MLPXXXXB poMlpxxxxZX = new PoHJXX_MLPXXXXB();
        BeanUtils.copyProperties(poMlpxxxxZX, poMlpxxxx);
        poMlpxxxxZX.setMlpnbid( (Long) hjxx_mlpxxxxbDAO.getId());
        poMlpxxxxZX.setQysj(now);
        poMlpxxxxZX.setJssj(null);
        poMlpxxxxZX.setCdlb(null);
        poMlpxxxxZX.setJdlb(HjConstant.JDLB_QHTZJL);
        poMlpxxxxZX.setCchjywid(null);
        poMlpxxxxZX.setCjhjywid(hjywid);
        poMlpxxxxZX.setMlpzt(DzConstant.MLPZT_CX);
        poMlpxxxxZX.setJlbz(PublicConstant.JLBZ_ZX);
        super.create(poMlpxxxxZX);

        //如果区划调整到的地信息存在则引用，否则创建
        VoDzzjxx voDzxx = new VoDzzjxx();
        BeanUtils.copyProperties(voDzxx, voQhtzxx[i]);
        PoHJXX_MLPXXXXB poMlpxxxxNew = this.getDXX(voDzxx);
        if (poMlpxxxxNew == null) {
          poMlpxxxxNew = new PoHJXX_MLPXXXXB();
          BeanUtils.copyProperties(poMlpxxxxNew, poMlpxxxx);
          BeanUtils.copyProperties(poMlpxxxxNew, voQhtzxx[i]);
          //Begin_修改于闵红斌(2004/11/15 17:25:00)
          //poMlpxxxxNew.setXzjd(poMlpxxxxNew.getJcwh() != null &&
          //                     poMlpxxxxNew.getJcwh().length() > 9 ?
          //                     poMlpxxxxNew.getJcwh().substring(0, 9) : null);
          PoXT_JWHXXB poXT_JWHXXB = (PoXT_JWHXXB) super.get(PoXT_JWHXXB.class,
              poMlpxxxxNew.getJcwh()); //通过居委会代码得到乡镇街道代码
          if (poXT_JWHXXB == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                       "通过居委会代码无法得到乡镇街道代码,区划调整业务无法完成。", null);
          }
          poMlpxxxxNew.setXzjd(poXT_JWHXXB.getXzjddm());
          //End_修改于闵红斌(2004/11/15 17:25:00)
          poMlpxxxxNew.setPxh(this.generatePXH(poMlpxxxxNew.getMlph(),
                                               poMlpxxxxNew.getMlxz()));
          poMlpxxxxNew.setMlpnbid( (Long) hjxx_mlpxxxxbDAO.getId());
          poMlpxxxxNew.setMlpid(poMlpxxxxNew.getMlpnbid());
          poMlpxxxxNew.setQysj(now);
          poMlpxxxxNew.setJssj(null);
          poMlpxxxxNew.setCdlb(null);
          poMlpxxxxNew.setJdlb(HjConstant.JDLB_QHTZJL);
          poMlpxxxxNew.setCchjywid(null);
          poMlpxxxxNew.setCjhjywid(hjywid);
          poMlpxxxxNew.setJlbz(PublicConstant.JLBZ_ZX);
          super.create(poMlpxxxxNew);
        }
        else if (!DzConstant.MLPZT_ZC.equals(poMlpxxxxNew.getMlpzt())) {
          //Begin_注销于2005/01/18 10:15:00
          //throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,"区划调整后的地信息已经存在，但门楼牌状态为非正常状态,区划调整业务无法完成。", null);
          //End_注销于2005/01/18 10:15:00
        }

        //业务限制
        VoHJXX_MLPXXXXB voMlpxxxx = new VoHJXX_MLPXXXXB(poMlpxxxxNew);
        VoXtywxz voLimit = XtywqxServiceImpl.VerifyBusinessLimit(PublicConstant.
            GNBH_DZ_QHTZYW, voMlpxxxx);
        if (voLimit.getLimitflag()) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "区划调整业务受限，受限信息：" + voLimit.getLimitinfo(), null);
        }

        //计算区划调整变动范围_Begin
        String qhtz_bdfw = null;
        //本居(村)民委员会内
        if (poMlpxxxx.getJcwh() != null &&
            poMlpxxxx.getJcwh().equals(poMlpxxxxNew.getJcwh())) {
          qhtz_bdfw = HjConstant.BDFW_BJMWYH;
        }
        //本所内居委会之间
        else if (poMlpxxxx.getPcs() != null &&
                 poMlpxxxx.getPcs().equals(poMlpxxxxNew.getPcs())) {
          qhtz_bdfw = HjConstant.BDFW_BSNJWH;
        }
        //本区(县)内他所(乡)
        else if (poMlpxxxx.getSsxq() != null &&
                 poMlpxxxx.getSsxq().equals(poMlpxxxxNew.getSsxq())) {
          qhtz_bdfw = HjConstant.BDFW_BQNTS;
        }
        //地市内
        else if (poMlpxxxx.getSsxq() != null && poMlpxxxxNew.getSsxq() != null &&
                 poMlpxxxx.getPcs().substring(0, 4).equals(
            poMlpxxxxNew.getSsxq().substring(0, 4))) {
          qhtz_bdfw = HjConstant.BDFW_DSN;
        }
        //省内
        else if (poMlpxxxx.getSsxq() != null && poMlpxxxxNew.getSsxq() != null &&
                 poMlpxxxx.getPcs().substring(0, 2).equals(
            poMlpxxxxNew.getSsxq().substring(0, 2))) {
          qhtz_bdfw = HjConstant.BDFW_SN;
        }
        //国内
        else {
          qhtz_bdfw = HjConstant.BDFW_GN;
        }
        //计算区划调整变动范围_end

        //修改地信息上的人户信息
        this.changeRHXX_DXX(hjywid, poMlpxxxx, poMlpxxxxNew,
                            PublicConstant.GNBH_DZ_QHTZYW, qhtz_bdfw, now);

        //生成返回信息
        voQhtzfhxx[i] = new VoQhtzfhxx();
        voQhtzfhxx[i].setMlpnbid(poMlpxxxxNew.getMlpnbid());
        voQhtzfhxx[i].setOld_mlpnbid(poMlpxxxx.getMlpnbid());
      } //for (int i = 0; i < voDzxgxx.length; i++)

      ////////////////////////////////////////
      //保存户籍业务流水信息
      this.saveHJYWLSXX(hjywid, PublicConstant.GNBH_DZ_QHTZYW,
                        PublicConstant.HJYWLS_YWLX_QHTZ,
                        voQhtzxx.length, null, now);

      ////////////////////////////
      //生成业务返回信息
      voQhtzywfhxx = new VoQhtzywfhxx();
      voQhtzywfhxx.setHjywid(hjywid);
      voQhtzywfhxx.setVoQhtzfhxx(voQhtzfhxx);

      /////////////////////////////
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
    catch (InvocationTargetException ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (IllegalAccessException ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (Exception ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return voQhtzywfhxx;
  }

  /**
   * 处理区划调整业务，按户做区划调整
   * 由于processQhtzyw处理大批量数据时会超时，调整处理流程，详见方案说明书
   * @param VoQhtzxx[] - 区划调整信息
   * @return VoQhtzywfhxx
   */
  public VoQhtzywfhxx processQhtzywAHTZ(VoQhtzxx voQhtzxx[]) throws
      ServiceException, DAOException {
    VoQhtzxx voQhtzxx_new = null;
    VoQhtzywfhxx voQhtzywfhxx = null;
    VoQhtzfhxx voQhtzfhxx[] = null;
    PoHJXX_MLPXXXXB poMlpxxxxNew = null;
    PoHJXX_MLPXXXXB poMlpxxxx = null;
    PoHJXX_HXXB poHxx = null;
    Long hjywid = null;//户籍业务ID
    long rxxCount = 0;//记录人总数
    String now = StringUtils.getServiceTime();
    String strHQL = null;

    if (voQhtzxx == null || (voQhtzxx != null && voQhtzxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();
      PojoInfo hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();
      PojoInfo hjxx_hxxbDAO = DAOFactory.createHJXX_HXXBDAO();
      PojoInfo hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();

     

      /////////////////////////////////////
      //保存地址修改信息
      voQhtzfhxx = new VoQhtzfhxx[voQhtzxx.length];
      for (int i = 0; i < voQhtzxx.length; i++) {
    	  //得到户籍业务ID
          hjywid = (Long) hjls_hjywlsbDAO.getId();
        //得到一个传入的区划调整地信息
        voQhtzxx_new = voQhtzxx[i];

        //得到地信息
        poMlpxxxx = super.get(PoHJXX_MLPXXXXB.class,
            voQhtzxx_new.getMlpnbid());
        if (poMlpxxxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到该地信息，区划调整业务无法完成。", null);
        }
        if (PublicConstant.JLBZ_LS.equals(poMlpxxxx.getJlbz())) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "不允许对历史记录进行修改，区划调整业务无法完成。", null);
        }
        if (!DzConstant.MLPZT_ZC.equals(poMlpxxxx.getMlpzt())) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "不允许对非正常记录状态进行修改，区划调整业务无法完成。", null);
        }

        //数据范围限制
        List sjfwList = new ArrayList();
        VoXtsjfw voXtsjfw = new VoXtsjfw();
        voXtsjfw.setSjfw(poMlpxxxx.getJcwh());
        voXtsjfw.setSjfwbz(PublicConstant.XT_QX_JWH);
        sjfwList.add(voXtsjfw);
        boolean bLimit = false;
        bLimit = XtywqxServiceImpl.VerifyDataRange(this.getUserInfo().getYhid().
            toString(),
            PublicConstant.GNBH_DZ_QHTZYW, sjfwList);
        if (!bLimit) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
        		  CommonUtil.getSjfwError(sjfwList)  + "，区划调整业务无法完成。", null);
        }

        //组织HQL语句，查询该地上所有户
        //查询条件:“门楼牌号”
        strHQL = "from PoHJXX_HXXB where mlpnbid='" + poMlpxxxx.getMlpnbid() +
            "' order by hhnbid ";
        //组织HQL语句，查询该地上状态正常户总数，
        //查询条件:“门楼牌号+记录标志为最新+冲消标志为非冲消+户号状态为正常”
//        strHQL = "from PoHJXX_HXXB where mlpnbid='" + poMlpxxxx.getMlpnbid() +
//            "' and jlbz='" + PublicConstant.JLBZ_ZX + "' and cxbz='" +
//            PublicConstant.CXBZ_FCX + "' and hhzt='" + HjConstant.HHZT_ZC +
//            "' order by hhnbid ";
        List hxxList = super.findAllByHQL(strHQL);
        if (hxxList != null && hxxList.size() > 0) {
          for (int j = 0; j < hxxList.size(); j++) {
            poHxx = (PoHJXX_HXXB) hxxList.get(j);
            //判断户号状态是否为锁定状态
            if (HjConstant.HHZT_DJBYXXG.equals(poHxx.getHhzt())) {
              throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                         "户信息中的户号状态为锁定不能修改，地址修改业务无法完成。", null);
            }

            do {
              //调整地、户、人
              poMlpxxxxNew = this.changeRHXX_DXX_QHTZ(hjywid, voQhtzxx_new,
                  poMlpxxxx.getMlpnbid(), poHxx.getHhnbid(), PublicConstant.GNBH_DZ_QHTZYW, now);
              strHQL = "select count(*) from PoHJXX_CZRKJBXXB where hhnbid='" +
                  poHxx.getHhnbid() + "' and jlbz='" + PublicConstant.JLBZ_ZX +
                  "' and cxbz='" + PublicConstant.CXBZ_FCX + "' and ryzt='" +
                  HjConstant.RYZT_ZC + "'";
              rxxCount = super.getCount(strHQL); //该户上人员状态正常的人的总数
            }
            while (rxxCount > 0);
          }//for (int j = 0; j < hxxList.size(); j++)

        } //if (hxxList != null && hxxList.size() > 0)
        else {

          //保存地信息为历史记录
          poMlpxxxx.setJssj(now);
          poMlpxxxx.setCdlb(HjConstant.CDLB_QHTZZX);
          poMlpxxxx.setCchjywid(hjywid);
          poMlpxxxx.setJlbz(PublicConstant.JLBZ_LS);
          super.update(poMlpxxxx);

          //保存地信息为最新记录并注销地(撤消地)
          poMlpxxxxNew = new PoHJXX_MLPXXXXB();
          BeanUtils.copyProperties(poMlpxxxxNew, poMlpxxxx);
          poMlpxxxxNew.setMlpnbid( (Long) hjxx_mlpxxxxbDAO.getId());
          poMlpxxxxNew.setQysj(now);
          poMlpxxxxNew.setJssj(null);
          poMlpxxxxNew.setCdlb(null);
          poMlpxxxxNew.setJdlb(HjConstant.JDLB_QHTZJL);
          poMlpxxxxNew.setCchjywid(null);
          poMlpxxxxNew.setCjhjywid(hjywid);
          poMlpxxxxNew.setMlpzt(DzConstant.MLPZT_CX);
          poMlpxxxxNew.setJlbz(PublicConstant.JLBZ_ZX);
          super.create(poMlpxxxxNew);

        }

        //保存户籍业务流水信息
        this.saveHJYWLSXX(hjywid, PublicConstant.GNBH_DZ_QHTZYW,
                          PublicConstant.HJYWLS_YWLX_QHTZ,
                          voQhtzxx.length, null, now);

        //生成返回信息
        voQhtzfhxx[i] = new VoQhtzfhxx();
        voQhtzfhxx[i].setMlpnbid(poMlpxxxxNew.getMlpnbid());
        voQhtzfhxx[i].setOld_mlpnbid(poMlpxxxx.getMlpnbid());
      } //for (int i = 0; i < voDzxgxx.length; i++)

      //生成业务返回信息
      voQhtzywfhxx = new VoQhtzywfhxx();
      voQhtzywfhxx.setHjywid(hjywid);
      voQhtzywfhxx.setVoQhtzfhxx(voQhtzfhxx);

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

    return voQhtzywfhxx;
  }

}
