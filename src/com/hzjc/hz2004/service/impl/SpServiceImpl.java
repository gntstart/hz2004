package com.hzjc.hz2004.service.impl;

import com.hzjc.hz2004.service.SpService;
import com.hzjc.hz2004.service.hzpt.ReceiveStateService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.base.SimpleJson;
import com.hzjc.hz2004.base.login.AuthToken;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.base.proc.CallParameter;
import com.hzjc.hz2004.base.proc.CallProc;
import com.hzjc.hz2004.base.proc.OutCallParameter;
import com.hzjc.hz2004.base.proc.CallParameter.ParameterValueType;
import com.hzjc.hz2004.service.CxService;
import com.hzjc.wsstruts.exception.*;
import com.hzjc.hz2004.dao.*;
import java.util.*;

import javax.annotation.Resource;

import com.hzjc.hz2004.po.*;
import org.apache.commons.logging.*;
import org.springframework.stereotype.Service;

import com.hzjc.hz2004.vo.*;
import com.hzjc.wsstruts.vo.*;
import com.hzjc.util.StringUtils;
import com.hzjc.hz2004.constant.*;
import org.apache.commons.beanutils.BeanUtils;
import com.hzjc.hz2004.common.PID;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.*;
import java.sql.SQLException;

import com.hzjc.hz2004.common.HjCommon;
import com.hzjc.wsstruts.KDSActionProxy;
import com.hzjc.hz2004.service.Hz2004ServiceHelper;
import com.hzjc.util.UUID;

/**
 * 审批业务实现类
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */

@Service
public class SpServiceImpl
    extends HjCommon
    implements SpService {
	@Resource
	private ReceiveStateService receiveStateService;
  //迁移流动原因映射（跨地区迁移用）
  static public Map qyldyyMap = new HashMap();
  //迁出注销类别映射（跨地区迁移用）
  static public Map qczxlbMap = new HashMap();

  //日志处理
  protected static Log _log = LogFactory.getLog(CxServiceImpl.class);

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
      ServiceException, DAOException {
    VoSpfdclxgywfhxx voSpfdclxgywfhxx = null;
    VoSpfdclxgfhxx voSpfdclxgfhxx[] = null;

    if (spywid == null || splx == null) {
      return null;
    }

    try {
      PojoInfo  hjsp_bgspxxbDAO = DAOFactory.createHJSP_BGSPXXBDAO();
      PojoInfo  hjsp_HbbgspsqbDAO = DAOFactory.createHJSP_HBBGSPSQBDAO();
      PojoInfo  hjsp_hjblspsqbDAO = DAOFactory.createHJSP_HJBLSPSQBDAO();
      PojoInfo  hjsp_hjscspsqbDAO = DAOFactory.createHJSP_HJSCSPSQBDAO();
      PojoInfo  hjsp_hjspsqbDAO = DAOFactory.createHJSP_HJSPSQBDAO();
      PojoInfo  hjsp_hjspfdclbDAO = DAOFactory.createHJSP_HJSPFDCLBDAO();

      ////////////////////////////////////////////
      //事务开始

      //修改审批说明
      //迁入审批
      if (HjConstant.SPLX_RCLH.equals(splx) || HjConstant.SPLX_SWQR.equals(splx)) {
        PoHJSP_HJSPSQB poHjspsqxx  = super.get(PoHJSP_HJSPSQB.class,spywid);
        if (poHjspsqxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到迁入审批信息,审批附带材料修改业务无法完成.", null);
        }
        poHjspsqxx.setSpsm(spsm);
       super.update(poHjspsqxx);
      }
      //变更审批
      else if (HjConstant.SPLX_BGGZ.equals(splx)) {
        PoHJSP_BGSPXXB poBgspxx  = super.get(PoHJSP_BGSPXXB.class,spywid);
        if (poBgspxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到变更审批信息,审批附带材料修改业务无法完成.", null);
        }
        poBgspxx.setSpsm(spsm);
       super.update(poBgspxx);
      }
      //户别变更审批
      else if (HjConstant.SPLX_HBBG.equals(splx)) {
        PoHJSP_HBBGSPSQB poHbbgspxx  = super.get(PoHJSP_HBBGSPSQB.class,
            spywid);
        if (poHbbgspxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到户别变更审批信息,审批附带材料修改业务无法完成.", null);
        }
        poHbbgspxx.setSpsm(spsm);
       super.update(poHbbgspxx);
      }
      //户籍补录审批
      else if (HjConstant.SPLX_HJBL.equals(splx)) {
        PoHJSP_HJBLSPSQB poHjblspxx  = super.get(PoHJSP_HJBLSPSQB.class,
            spywid);
        if (poHjblspxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到户籍补录审批信息,审批附带材料修改业务无法完成.", null);
        }
        poHjblspxx.setSpsm(spsm);
       super.update(poHjblspxx);
      }
      //户籍删除审批
      else if (HjConstant.SPLX_HJSC.equals(splx)) {
        PoHJSP_HJSCSPSQB poHjscspxx  = super.get(PoHJSP_HJSCSPSQB.class,
            spywid);
        if (poHjscspxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到户籍删除审批信息,审批附带材料修改业务无法完成.", null);
        }
        poHjscspxx.setSpsm(spsm);
       super.update(poHjscspxx);
      }

      //删除以前的审批附带材料
      String strHQL = "from PoHJSP_HJSPFDCLB where splx='" + splx +
          "' and spywid=" + spywid;
      List hjspfdclxxList =super.findAllByHQL(strHQL);
      if (hjspfdclxxList != null && hjspfdclxxList.size() > 0) {
        for (int i = 0; i < hjspfdclxxList.size(); i++) {
          PoHJSP_HJSPFDCLB po = (PoHJSP_HJSPFDCLB) hjspfdclxxList.get(i);
          super.delete(po);
        }
      }

      //保存修改后的审批附带材料
      if (voSpfdclxx != null && voSpfdclxx.length > 0) {
        voSpfdclxgfhxx = new VoSpfdclxgfhxx[voSpfdclxx.length];
        for (int i = 0; i < voSpfdclxx.length; i++) {
          //保存审批附带材料
          PoHJSP_HJSPFDCLB poHjspfdclxx = new PoHJSP_HJSPFDCLB();
          poHjspfdclxx.setSpclid( (Long) hjsp_hjspfdclbDAO.getId());
          poHjspfdclxx.setSplx(splx);
          poHjspfdclxx.setSpywid(spywid);
          BeanUtils.copyProperties(poHjspfdclxx, voSpfdclxx[i]);
          super.create(poHjspfdclxx);
          //生成返回信息
          voSpfdclxgfhxx[i] = new VoSpfdclxgfhxx();
          voSpfdclxgfhxx[i].setSpclid(poHjspfdclxx.getSpclid());
        }
      }

      //生成业务返回信息
      voSpfdclxgywfhxx = new VoSpfdclxgywfhxx();
      voSpfdclxgywfhxx.setSplx(splx);
      voSpfdclxgywfhxx.setSpywid(spywid);
      voSpfdclxgywfhxx.setVoSpfdclxgfhxx(voSpfdclxgfhxx);

      //////////////////////////////////////////
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

    return voSpfdclxgywfhxx;
  }

  /**
   * 处理准迁证编号回填业务
   * @param voZqzbhhtxx - 准迁证编号回填信息
   * @return - 准迁证编号回填业务返回信息
   * @throws ServiceException
   * @throws DAOException
   */
  public VoZqzbhhtywfhxx processZqzbhhtyw(VoZqzbhhtxx voZqzbhhtxx[]) throws
      ServiceException, DAOException {

    VoZqzbhhtywfhxx voZqzbhhtywfhxx = null;
    VoZqzbhhtfhxx voZqzbhhtfhxx[] = null;
    String strHQL = null;
    String now = StringUtils.getServiceTime();

    if (voZqzbhhtxx == null || (voZqzbhhtxx != null && voZqzbhhtxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo  hjsp_zqzxxbDAO = DAOFactory.createHJSP_ZQZXXBDAO();
      PojoInfo  hjsp_hjspzbDAO = DAOFactory.createHJSP_HJSPZBDAO();

      ////////////////////////////////////////////
      //事务开始

      ////////////////////////////////////////////////
      //处理准迁证编号信息
      voZqzbhhtfhxx = new VoZqzbhhtfhxx[voZqzbhhtxx.length];
      for (int i = 0; i < voZqzbhhtxx.length; i++) {
        //得到准迁证信息
        PoHJSP_ZQZXXB poZqzxx  = super.get(PoHJSP_ZQZXXB.class,voZqzbhhtxx[
            i].getZqid());
        if (poZqzxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到对应的准迁证信息,准迁证编号回填业务无法完成.", null);
        }

        //保存准迁证编号等信息
        poZqzxx.setZqzjlx(voZqzbhhtxx[i].getZqzjlx());
        poZqzxx.setZjbh(voZqzbhhtxx[i].getZjbh());
        poZqzxx.setQfrq(now.substring(0, 8));
        poZqzxx.setCbr(this.getUserInfo().getYhxm());
       super.update(poZqzxx);

        //得到迁入审批子信息
        strHQL = "from PoHJSP_HJSPZB where spywid=" + poZqzxx.getSpywid();
        List qrspzxxList =super.findAllByHQL(strHQL);
        if (qrspzxxList != null && qrspzxxList.size() > 0) {
          for (int a = 0; a < qrspzxxList.size(); a++) {
            PoHJSP_HJSPZB poQrspzxx = (PoHJSP_HJSPZB) qrspzxxList.get(a);
            //保存准迁证编号
            if (poQrspzxx.getGmsfhm().equalsIgnoreCase(poZqzxx.getQyrgmsfhm1())
                || poQrspzxx.getGmsfhm().equalsIgnoreCase(poZqzxx.getQyrgmsfhm2())
                || poQrspzxx.getGmsfhm().equalsIgnoreCase(poZqzxx.getQyrgmsfhm3())
                || poQrspzxx.getGmsfhm().equalsIgnoreCase(poZqzxx.getQyrgmsfhm4())) {
              poQrspzxx.setZqzjlx(poZqzxx.getZqzjlx());
              poQrspzxx.setZjbh(poZqzxx.getZjbh());
             super.update(poQrspzxx);
            }
          }
        }

        //生成返回信息
        voZqzbhhtfhxx[i] = new VoZqzbhhtfhxx();
        voZqzbhhtfhxx[i].setSqrgmsfhm(poZqzxx.getSqrgmsfhm());
        voZqzbhhtfhxx[i].setSqrxm(poZqzxx.getSqrxm());
        voZqzbhhtfhxx[i].setZqid(poZqzxx.getZqid());
        voZqzbhhtfhxx[i].setZqzjlx(poZqzxx.getZqzjlx());
        voZqzbhhtfhxx[i].setZjbh(poZqzxx.getZjbh());
      }

      //////////////////////////////////////////
      //生成业务返回信息
      voZqzbhhtywfhxx = new VoZqzbhhtywfhxx();
      voZqzbhhtywfhxx.setVoZqzbhhtfhxx(voZqzbhhtfhxx);

      //////////////////////////////////////////
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

    return voZqzbhhtywfhxx;
  }

  /**
   * 处理变更审批登记信息
   * @param voSbjbxx - 申报基本信息
   * @param spmbid - 审批模板ID
   * @param spsm - 审批说明
   * @param voBggzxxEx - 变更更正信息
   * @param voSpfdclxx - 审批附带材料信息
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoBgspdjywfhxx processBgspdjyw(VoSbjbxx voSbjbxx, Long spmbid,
                                        String spsm, VoBggzxxEx[] voBggzxxEx,
                                        VoSpfdclxx voSpfdclxx[]) throws
      ServiceException, DAOException {

    VoBgspdjywfhxx voBgspdjywfhxx = null;
    VoBgspdjfhxx voBgspdjfhxx[] = null;
    String strHQL = null;
    String now = StringUtils.getServiceTime();

    //////////////////////////////////////////
    //数据校验
    if (voBggzxxEx == null ||
        (voBggzxxEx != null && voBggzxxEx.length == 0)) {
      return null;
    }

    try {
      PojoInfo  hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
      PojoInfo  hjsp_bgspxxbDAO = DAOFactory.createHJSP_BGSPXXBDAO();
      PojoInfo  hjsp_hjsplsbDAO = DAOFactory.createHJSP_HJSPLSBDAO();
      PojoInfo  hjsp_hjspfdclbDAO = DAOFactory.createHJSP_HJSPFDCLBDAO();
      PojoInfo  xt_mblcxxbDAO = DAOFactory.createXT_MBLCXXBDAO();

      /////////////////////////////
      //事务开始

      ///////////////////////////////
      //保存变更审批登记信息
      voBgspdjfhxx = new VoBgspdjfhxx[voBggzxxEx.length];
      
      for (int i = 0; i < voBggzxxEx.length; i++) {
        VoBggzxxEx voEx = (VoBggzxxEx) voBggzxxEx[i];
        //得到人员信息
        PoHJXX_CZRKJBXXB poRyxx  = super.get(PoHJXX_CZRKJBXXB.class,voEx.
            getRynbid());
        if (poRyxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到对应的人员信息,变更审批登记业务无法完成.", null);
        }
        //校验人信息的时效性
        this.checkRXX(poRyxx, hjxx_czrkjbxxbDAO, "变更审批登记业务");
        //人信息加锁
        poRyxx.setRysdzt(HjConstant.RYSDZT_BGGZSP);
       super.update(poRyxx);
        //得到审批流程信息
        PoXT_MBLCXXB poMblcxx = null;
        if (spmbid != null) {
          strHQL = "from PoXT_MBLCXXB where (dzbz='" + HjConstant.DZBZ_DYB +
              "' or dzbz='" + HjConstant.DZBZ_QSB + "') and spmbid=" + spmbid;
          List mblcxxList =super.findAllByHQL(strHQL);
          if (mblcxxList != null && mblcxxList.size() > 0) {
            poMblcxx = (PoXT_MBLCXXB) mblcxxList.get(0);
          }
          if (poMblcxx == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "找不到对应的人审批流程信息,变更审批登记业务无法完成.", null);
          }
        }
        //保存审批登记子信息
        Long spywid = this.saveBGSPDJXX(spsm, voSbjbxx, poRyxx, poMblcxx,
                                        voEx.getBggzxxList(), now);
        if(voSbjbxx!=null && voSbjbxx.getHzywid()!=null && !voSbjbxx.getHzywid().equals("")){
      	  updateHzyw(voSbjbxx.getHzywid(),0l,spywid);
        }
        //保存审批附带材料
        if (voSpfdclxx != null && voSpfdclxx.length > 0) {
          for (int a = 0; a < voSpfdclxx.length; a++) {
            PoHJSP_HJSPFDCLB poSpfdclxx = new PoHJSP_HJSPFDCLB();
            poSpfdclxx.setSpclid( (Long) hjsp_hjspfdclbDAO.getId());
            poSpfdclxx.setSpywid(spywid);
            poSpfdclxx.setSplx(HjConstant.SPLX_BGGZ);
            poSpfdclxx.setClbh(voSpfdclxx[a].getClbh());
            super.create(poSpfdclxx);
          }
        }

        //保存户籍审批流水信息
        Long splsid = null;
        if (poMblcxx == null) {
          splsid = this.saveHJSPLSXX(spywid, HjConstant.SPLX_BGGZ,
                                     null, HjConstant.CZJG_TY, null, now);
        }

        //审批模板当前使用数增加1
        this.incSPMBDQSYS(spmbid);

        //生成返回信息
        voBgspdjfhxx[i] = new VoBgspdjfhxx();
        voBgspdjfhxx[i].setSplsid(splsid);
        voBgspdjfhxx[i].setSpywid(spywid);
        voBgspdjfhxx[i].setGmsfhm(poRyxx.getGmsfhm());
        voBgspdjfhxx[i].setRyid(poRyxx.getRyid());
        voBgspdjfhxx[i].setRynbid(poRyxx.getRynbid());
        voBgspdjfhxx[i].setXm(poRyxx.getXm());
      } //for (int i = 0; i < voBggzxxEx.length; i++) {
      if(voSbjbxx!=null && voSbjbxx.getHzywid()!=null && !voSbjbxx.getHzywid().equals("")){
    	  autoInsertHzjsbs(voSbjbxx.getHzywid());
      }
      //////////////////////////////////////
      //生成业务返回信息
      voBgspdjywfhxx = new VoBgspdjywfhxx();
      voBgspdjywfhxx.setVoBgspdjfhxx(voBgspdjfhxx);

      ////////////////////////////////////
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

    return voBgspdjywfhxx;
  }

  /**
   * 保存户籍审批流水信息
   * @param spywid - 审批业务ID
   * @param splx - 审批类型
   * @param dzid - 动作ID
   * @param czjg - 操作结果
   * @param czyj - 操作意见
   * @param czsj - 操作时间
   * @return - 审批流水ID
   * @throws DAOException
   * @throws ServiceException
   */
  private Long saveHJSPLSXX(Long spywid, String splx, Long dzid, String czjg,
                            String czyj, String czsj) throws
      DAOException, ServiceException {

    Long splsid = null;

    try {
      PojoInfo  hjsp_hjsplsbDAO = DAOFactory.createHJSP_HJSPLSBDAO();

      //得到审批流水ID
      splsid = (Long) hjsp_hjsplsbDAO.getId();
      //保存户籍审批流水信息
      PoHJSP_HJSPLSB poHjsplsxx = new PoHJSP_HJSPLSB();
      poHjsplsxx.setSplsid(splsid);
      poHjsplsxx.setCzrid(this.getUserInfo().getYhid());
      poHjsplsxx.setCzsj(czsj);
      poHjsplsxx.setSpywid(spywid);
      poHjsplsxx.setCzjg(czjg);
      poHjsplsxx.setCzyj(czyj);
      poHjsplsxx.setDzid(dzid != null ? dzid : new Long(0));
      poHjsplsxx.setSplx(splx);
      super.create(poHjsplsxx);
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return splsid;
  }

  /**
   * 保存准迁证信息
   * @param poHjspsqxx - 户籍审批申报信息PO
   * @return - 准迁ID
   * @throws DAOException
   */
  private Long saveZQZXX(PoHJSP_HJSPSQB poHjspsqxx) throws DAOException {
    String strHQL = null;
    PoHJSP_ZQZXXB poZqzxx = null;

    try {
      PojoInfo  hjsp_zqzxxbDAO = DAOFactory.createHJSP_ZQZXXBDAO();
      PojoInfo  hjsp_hjspzbDAO = DAOFactory.createHJSP_HJSPZBDAO();
      
      //生成迁入地详址(2005/01/17 15:40:00)
      PoHJXX_MLPXXXXB poMlpxxxx = new PoHJXX_MLPXXXXB();
      poMlpxxxx.setSsxq(poHjspsqxx.getQrdqx());
      poMlpxxxx.setPcs(poHjspsqxx.getQrdpcs());
      poMlpxxxx.setXzjd(poHjspsqxx.getQrdxzjd());
      poMlpxxxx.setMlph(poHjspsqxx.getQrdmlph());
      poMlpxxxx.setJlx(poHjspsqxx.getQrdjlx());
      poMlpxxxx.setJcwh(poHjspsqxx.getQrdjwh());
      poMlpxxxx.setZrq(poHjspsqxx.getQrdjwzrq());
      poMlpxxxx.setMlxz(poHjspsqxx.getQrdz());
      String qrdxz = this.generateZZ(poMlpxxxx, PublicConstant.XTKZCS_DZPZFS,
                                     PublicConstant.XTKZCS_DZPZFS_QCDXZ);
      //得到审批子信息(随迁人员)
      strHQL = "from PoHJSP_HJSPZB where spywid=" + poHjspsqxx.getSpywid();
      List hjspzxxList =super.findAllByHQL(strHQL);
      boolean isKDQ = (poHjspsqxx.getKdqqy_qcdqbm()!=null && !poHjspsqxx.getKdqqy_qcdqbm().equals(""));
      if (hjspzxxList != null) {
        while (hjspzxxList.size() > 0) {
          //跨地区迁移
          String zjbh = null;//KDSActionProxy.getXlh("","zqz");
          String zjlx = null;//"1";
          if(isKDQ){
            zjbh = KDSActionProxy.getXlh("","zqz");
            if(zjbh==null || zjbh.trim().equals(""))
              throw new RuntimeException("获取全省统一电子证号异常，请稍候再办理！");

            zjlx = "1";
          }

          //生成准迁证信息
          poZqzxx = new PoHJSP_ZQZXXB();
          poZqzxx.setZqid( (Long) hjsp_zqzxxbDAO.getId());
          poZqzxx.setSpywid(poHjspsqxx.getSpywid());
          poZqzxx.setSqrgmsfhm(poHjspsqxx.getSqrgmsfhm());
          poZqzxx.setSqrxm(poHjspsqxx.getSqrxm());
          poZqzxx.setSqrzzssxq(poHjspsqxx.getSqrzzssxq());
          poZqzxx.setSqrzzxz(poHjspsqxx.getSqrzzxz());
          poZqzxx.setSqrhkdjjg(poHjspsqxx.getSqrhkdjjg());
          poZqzxx.setQyrhkdjjg(poHjspsqxx.getHkszddjjg());
          poZqzxx.setQyrzzssxq(poHjspsqxx.getZzssxq());
          poZqzxx.setQyrzzxz(poHjspsqxx.getZzxz());
          poZqzxx.setQrdhkdjjg(poHjspsqxx.getQrdhkdjjg());
          poZqzxx.setQrdssxq(poHjspsqxx.getQrdqx());
          poZqzxx.setQrdxz(qrdxz);
          poZqzxx.setZqyy(poHjspsqxx.getSqqrly());
          poZqzxx.setPzjg(this.transDWDM(this.getUserInfo().getDwdm()));
          poZqzxx.setBz(poHjspsqxx.getBz());
          poZqzxx.setQyrs("0");
          if (hjspzxxList.size() > 0) {
            PoHJSP_HJSPZB po = (PoHJSP_HJSPZB) hjspzxxList.get(0);
            poZqzxx.setQyrcsrq1(po.getCsrq());
            poZqzxx.setQyrysqrgx1(po.getYsqrgx());
            poZqzxx.setQyrxm1(po.getXm());
            poZqzxx.setQyrxb1(po.getXb());
            poZqzxx.setQyrgmsfhm1(po.getGmsfhm());
            poZqzxx.setQyrs("1");
            hjspzxxList.remove(0);

            if(isKDQ){
              po.setZjbh(zjbh);
              po.setZqzjlx(zjlx);
             super.update(po);
            }
          }
          if (hjspzxxList.size() > 0) {
            PoHJSP_HJSPZB po = (PoHJSP_HJSPZB) hjspzxxList.get(0);
            poZqzxx.setQyrcsrq2(po.getCsrq());
            poZqzxx.setQyrysqrgx2(po.getYsqrgx());
            poZqzxx.setQyrxm2(po.getXm());
            poZqzxx.setQyrxb2(po.getXb());
            poZqzxx.setQyrgmsfhm2(po.getGmsfhm());
            poZqzxx.setQyrs("2");
            hjspzxxList.remove(0);

            if(isKDQ){
              po.setZjbh(zjbh);
              po.setZqzjlx(zjlx);
             super.update(po);
            }
          }
          if (hjspzxxList.size() > 0) {
            PoHJSP_HJSPZB po = (PoHJSP_HJSPZB) hjspzxxList.get(0);
            poZqzxx.setQyrcsrq3(po.getCsrq());
            poZqzxx.setQyrysqrgx3(po.getYsqrgx());
            poZqzxx.setQyrxm3(po.getXm());
            poZqzxx.setQyrxb3(po.getXb());
            poZqzxx.setQyrgmsfhm3(po.getGmsfhm());
            poZqzxx.setQyrs("3");
            hjspzxxList.remove(0);

            if(isKDQ){
              po.setZjbh(zjbh);
              po.setZqzjlx(zjlx);
             super.update(po);
            }
          }
          if (hjspzxxList.size() > 0) {
            PoHJSP_HJSPZB po = (PoHJSP_HJSPZB) hjspzxxList.get(0);
            poZqzxx.setQyrcsrq4(po.getCsrq());
            poZqzxx.setQyrysqrgx4(po.getYsqrgx());
            poZqzxx.setQyrxm4(po.getXm());
            poZqzxx.setQyrxb4(po.getXb());
            poZqzxx.setQyrgmsfhm4(po.getGmsfhm());
            poZqzxx.setQyrs("4");
            hjspzxxList.remove(0);

            if(isKDQ){
              po.setZjbh(zjbh);
              po.setZqzjlx(zjlx);
             super.update(po);
            }
          }

          if(isKDQ){
            poZqzxx.setZjbh(zjbh);
            poZqzxx.setZqzjlx(zjlx);
            poZqzxx.setQfrq(StringUtils.formateDate());
          }
          poZqzxx.setCbr(this.getUserInfo().getYhxm());
          super.create(poZqzxx);
        } //while (hjspzxxList.size() > 0) {
      } //if (hjspzxxList != null) {

    }
    catch (DAOException ex) {
      throw ex;
    }

    return poZqzxx != null ? poZqzxx.getZqid() : null;
  }

  /**
   * 保存变更审批登记信息
   * @param spsm - 审批说明
   * @param voSbjbxx - 申报基本信息
   * @param poRyxx - 人员信息
   * @param poMblcxx - 模板流程信息
   * @param xydzid - 下一动作ID
   * @param vobggzxxList -  变更更正信息List
   * @param czsj - 操作时间
   * @return - 审批业务ID
   */
  private Long saveBGSPDJXX(String spsm, VoSbjbxx voSbjbxx,
                            PoHJXX_CZRKJBXXB poRyxx, PoXT_MBLCXXB poMblcxx,
                            List vobggzxxList, String czsj) {

    Long spywid = null;

    if (poRyxx == null || vobggzxxList == null) {
      return spywid;
    }

    try {
      PojoInfo  hjsp_bgspxxbDAO = DAOFactory.createHJSP_BGSPXXBDAO();
      PojoInfo  hjsp_bgspzbDAO = DAOFactory.createHJSP_BGSPZBDAO();

      //保存变更审批信息
      PoHJSP_BGSPXXB poBgspxx = new PoHJSP_BGSPXXB();
      poBgspxx.setSpywid( (Long) hjsp_bgspxxbDAO.getId());
      poBgspxx.setBgryid(poRyxx.getRyid());
      poBgspxx.setSpmbid(poMblcxx != null ? poMblcxx.getSpmbid() : null);
      poBgspxx.setXydzid(poMblcxx != null ? poMblcxx.getDzid() : null);
      poBgspxx.setLsbz(HjConstant.LSBZ_WLS);
      if (voSbjbxx != null) {
        poBgspxx.setSbrgmsfhm(voSbjbxx.getSbrgmsfhm());
        poBgspxx.setSbryxm(voSbjbxx.getSbryxm());
        poBgspxx.setSbsj(voSbjbxx.getSbsj());
      }
      if (poMblcxx == null) {
        poBgspxx.setSpjg(HjConstant.SPJG_TY);
      }
      else {
        poBgspxx.setSpjg(null);
      }
      poBgspxx.setSpsm(spsm);
      poBgspxx.setSpjg(null);
      poBgspxx.setDjrid(this.getUserInfo().getYhid());
      super.create(poBgspxx);

      //保存变更审批子信息
      for (int i = 0; i < vobggzxxList.size(); i++) {
        VoBggzxx vo = (VoBggzxx) vobggzxxList.get(i);
        PoHJSP_BGSPZB poBgspzxx = new PoHJSP_BGSPZB();
        poBgspzxx.setBgzid( (Long) hjsp_bgspzbDAO.getId());
        poBgspzxx.setSpywid(poBgspxx.getSpywid());
        poBgspzxx.setBggzxm(vo.getBggzxm());
        poBgspzxx.setBglb(vo.getBggzlb());
        poBgspzxx.setBgrq(vo.getBggzrq());
        //变更前内容
        if ("ZP".equalsIgnoreCase(vo.getBggzxm())) {
          //保存照片
          PojoInfo  hjxx_ryzpxxbDAO = DAOFactory.createHJXX_RYZPXXBDAO();
          VoHJXX_RYZPXXB voRyzpxx = new VoHJXX_RYZPXXB();
          voRyzpxx.setZpid( (Long) hjxx_ryzpxxbDAO.getId());
          voRyzpxx.setLrrq(czsj);
          voRyzpxx.setRyid(poRyxx.getRyid());
          voRyzpxx.setXm(poRyxx.getXm());
          voRyzpxx.setGmsfhm(poRyxx.getGmsfhm());
          voRyzpxx.setZp(vo.getZp());
          super.create(voRyzpxx.toPoHJXX_RYZPXXB());

          poBgspzxx.setBghnr(voRyzpxx.getZpid().toString());
          poBgspzxx.setBgqnr(poRyxx.getZpid() != null ?
                             poRyxx.getZpid().toString() : null);
        }
        else {
          poBgspzxx.setBghnr(vo.getBggzhnr());
          poBgspzxx.setBgqnr(BeanUtils.getProperty(poRyxx,
              vo.getBggzxm().toLowerCase()));
        }

        super.create(poBgspzxx);
      } //for(int i=0;i<vobggzxxList.size();i++){

      spywid = poBgspxx.getSpywid();
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (NoSuchMethodException ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (InvocationTargetException ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (IllegalAccessException ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return spywid;
  }

  /**
   * 获取变更审批信息
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryBgspxx(String strHQL, VoPage voPage) throws
      ServiceException, DAOException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append(
        "from PoHJSP_BGSPXXB as HJSP_BGSPXXB,PoHJXX_CZRKJBXXB as HJXX_CZRKJBXXB ")
        .append("where HJSP_BGSPXXB.bgryid=HJXX_CZRKJBXXB.ryid ")
        .append("and HJXX_CZRKJBXXB.jlbz='" + PublicConstant.JLBZ_ZX + "' ")
        .append("and HJXX_CZRKJBXXB.cxbz='" + PublicConstant.CXBZ_FCX + "' ");
    
    //数据范围begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_SP_BGSPXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //居(村)委会
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //派出所
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //行政区划
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.ssxq='" +
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
    strAllHQL.append(
        "select HJSP_BGSPXXB,HJXX_CZRKJBXXB ").
        append(strFromHQL.toString());

    //debug info
    //_log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo  hjsp_bgspxxbDAO = DAOFactory.createHJSP_BGSPXXBDAO();


      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(strCountHQL. toString()));
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
        Object ob[] = (Object[]) poList.get(i);
        PoHJSP_BGSPXXB poBgspxx = (PoHJSP_BGSPXXB) ob[0];
        PoHJXX_CZRKJBXXB poRyxx = (PoHJXX_CZRKJBXXB) ob[1];
        
        PoXT_SPDZB poSpdzxx = null;
        if(poBgspxx.getXydzid()!=null && poBgspxx.getXydzid().longValue()!=0){
        	poSpdzxx = super.get(PoXT_SPDZB.class, poBgspxx.getXydzid());
        }
        
        VoBgspxxHqFhxx voBgspxxHqFhxx = new VoBgspxxHqFhxx(poBgspxx, poRyxx, poSpdzxx);

        voList.add(voBgspxxHqFhxx);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoBgspxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * 获取登录用户能审批的变更审批信息
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryBgspxxByUser(String strHQL, VoPage voPage) throws
      ServiceException, DAOException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append(
        "from PoHJSP_BGSPXXB as HJSP_BGSPXXB,PoHJXX_CZRKJBXXB as HJXX_CZRKJBXXB,PoXT_SPDZB as XT_SPDZB,PoXT_YHDZQXB as XT_YHDZQXB ")
        .append("where HJSP_BGSPXXB.bgryid=HJXX_CZRKJBXXB.ryid ")
        .append("and XT_SPDZB.dzid=HJSP_BGSPXXB.xydzid ")
        .append("and XT_YHDZQXB.dzid=HJSP_BGSPXXB.xydzid ")
        .append("and XT_YHDZQXB.yhid=" + this.getUserInfo().getYhid() + " ")
        .append("and HJXX_CZRKJBXXB.jlbz='" + PublicConstant.JLBZ_ZX + "' ")
        .append("and HJXX_CZRKJBXXB.cxbz='" + PublicConstant.CXBZ_FCX + "' ");

    //数据范围begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_SP_YH_BGSPXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //居(村)委会
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //派出所
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //行政区划
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.ssxq='" +
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
    strAllHQL.append(
        "select HJSP_BGSPXXB,HJXX_CZRKJBXXB,XT_SPDZB ").
        append(strFromHQL.toString());

    //debug info
    //_log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo  hjsp_bgspxxbDAO = DAOFactory.createHJSP_BGSPXXBDAO();


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
        Object ob[] = (Object[]) poList.get(i);
        PoHJSP_BGSPXXB poBgspxx = (PoHJSP_BGSPXXB) ob[0];
        PoHJXX_CZRKJBXXB poRyxx = (PoHJXX_CZRKJBXXB) ob[1];
        PoXT_SPDZB poSpdzxx = (PoXT_SPDZB) ob[2];

        VoBgspxxHqFhxx voBgspxxHqFhxx = new VoBgspxxHqFhxx(poBgspxx, poRyxx,
            poSpdzxx);

        voList.add(voBgspxxHqFhxx);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoBgspxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * 获取变更审批子信息
   * @param spywid - 审批业务ID
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryBgspzxx(Long spywid, VoPage voPage) throws
      ServiceException, DAOException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    if (spywid == null || voPage == null) {
      return null;
    }

    //(Long bgzid, Long spywid, String bggzxm, String bgqnr, String bghnr, String bglb, String bgrq)
    //生成FROM HQL语句
    strFromHQL.append(" from PoHJSP_BGSPZB as HJSP_BGSPZB ,PoXT_SJZDB as XT_SJZDB ")
    			.append(" where upper(HJSP_BGSPZB.bggzxm) = upper(XT_SJZDB.zdmc) and HJSP_BGSPZB.spywid=" + spywid.toString());

    //生成COUNT HQL语句
    if (voPage.getRecordcount() == -1) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //生成SELECT HQL语句
    strAllHQL.append("select new PoHJSP_BGSPZB(HJSP_BGSPZB.bgzid,HJSP_BGSPZB.spywid,XT_SJZDB.zdhy,HJSP_BGSPZB.bgqnr,HJSP_BGSPZB.bghnr,HJSP_BGSPZB.bglb,HJSP_BGSPZB.bgrq) ").
        append(strFromHQL.toString());

    //debug info
    //_log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo  hjsp_bgspzbDAO = DAOFactory.createHJSP_BGSPZBDAO();


      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(
            strCountHQL.toString()));
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
    voQueryResult.setVotype(PoHJSP_BGSPZB.class);
    return voQueryResult;
  }

  /**
   * 处理变更审批审批业务
   * @param voBgspspxx - 变更审批审批信息
   * @return - 变更审批审批业务返回信息
   * @throws DAOException
   * @throws ServiceException
   */
  public VoBgspspywfhxx processBgspspyw(VoBgspspxx voBgspspxx[]) throws
      DAOException, ServiceException {

    VoBgspspywfhxx voBgspspywfhxx = null;
    VoBgspspfhxx voBgspspfhxx[] = null;
    String strHQL = null;
    String now = StringUtils.getServiceTime();

    if (voBgspspxx == null || (voBgspspxx != null && voBgspspxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo  hjsp_bgspxxbDAO = DAOFactory.createHJSP_BGSPXXBDAO();
      PojoInfo  xt_mblcxxbDAO = DAOFactory.createXT_MBLCXXBDAO();
      PojoInfo  hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();

      ////////////////////////////////////
      //事务开始

      /////////////////////////////////////
      //保存（处理）变更审批信息
      voBgspspfhxx = new VoBgspspfhxx[voBgspspxx.length];
      for (int i = 0; i < voBgspspxx.length; i++) {
        //得到变更审批信息
        PoHJSP_BGSPXXB poBgspxx  = super.get(PoHJSP_BGSPXXB.class,
            voBgspspxx[i].getSpywid());
        if (poBgspxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到对应的变更审批信息,变更审批审批业务无法完成.", null);
        }
        //动作校验
        if (! (poBgspxx.getXydzid() != null && voBgspspxx[i].getDzid() != null &&
               poBgspxx.getXydzid().equals(voBgspspxx[i].getDzid()))) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "动作ID出错(可能已审批),变更审批审批业务无法完成.", null);
        }
        //审批用户校验
        strHQL = "select count(*) from PoXT_YHDZQXB where yhid=" +
            this.getUserInfo().getYhid() + " and dzid=" + poBgspxx.getXydzid();
        if (super.getCount(strHQL) <= 0) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "该用户无权审批,变更审批审批业务无法完成.", null);
        }
        //得到审批流程信息
        PoXT_MBLCXXB poMblcxx = null;
        strHQL = "from PoXT_MBLCXXB where spmbid=" + poBgspxx.getSpmbid() +
            " and dzid=" + voBgspspxx[i].getDzid() + " and (dzz='" +
            voBgspspxx[i].getCzjg() + "' or dzbz='" + HjConstant.DZBZ_DYB +
            "') ";
        List mblcxxList =super.findAllByHQL(strHQL);
        if (mblcxxList != null && mblcxxList.size() > 0) {
          poMblcxx = (PoXT_MBLCXXB) mblcxxList.get(0);
        }
        if (poMblcxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到对应的模板流程信息,变更审批审批业务无法完成.", null);
        }
        //保存变更审批信息
        poBgspxx.setXydzid(poMblcxx.getXgdzid());
        if (HjConstant.DZBZ_DYB.equals(poMblcxx.getDzbz()) ||
            HjConstant.DZBZ_JSB.equals(poMblcxx.getDzbz())) {
          poBgspxx.setSpjg(voBgspspxx[i].getCzjg());
          //审批模板当前使用数减少1
          this.decSPMBDQSYS(poBgspxx.getSpmbid());
        }
       super.update(poBgspxx);
        //保存户籍审批流水信息
        Long splsid = this.saveHJSPLSXX(poBgspxx.getSpywid(),
                                        HjConstant.SPLX_BGGZ,
                                        voBgspspxx[i].getDzid(),
                                        voBgspspxx[i].getCzjg(),
                                        voBgspspxx[i].getCzyj(), now);

        //人员锁定状态解锁(处理审批没通过的情况)
        if (poBgspxx.getSpjg() != null &&
            !HjConstant.SPJG_TY.equals(poBgspxx.getSpjg())) {
          strHQL = "from PoHJXX_CZRKJBXXB where ryid=" + poBgspxx.getBgryid() +
              " and jlbz='" + PublicConstant.JLBZ_ZX + "' and cxbz='" +
              PublicConstant.CXBZ_FCX + "' ";
          List ryxxList =super.findAllByHQL(strHQL);
          if (ryxxList != null && ryxxList.size() > 0) {
            PoHJXX_CZRKJBXXB poRyxx = (PoHJXX_CZRKJBXXB) ryxxList.get(0);
            //校验人信息的时效性
            this.checkRXX(poRyxx, hjxx_czrkjbxxbDAO, "变更审批审批业务");
            //保存人信息
            poRyxx.setRysdzt(HjConstant.RYSDZT_ZC);
           super.update(poRyxx);
          }
        }

        //生成返回信息
        voBgspspfhxx[i] = new VoBgspspfhxx();
        voBgspspfhxx[i].setSplsid(splsid);
        voBgspspfhxx[i].setSpywid(poBgspxx.getSpywid());
      } //for (int i = 0; i < voBgspspxx.length; i++) {

      ///////////////////////////////////////
      //生成业务返回信息
      voBgspspywfhxx = new VoBgspspywfhxx();
      voBgspspywfhxx.setVoBgspspfhxx(voBgspspfhxx);

      ////////////////////////////////////
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

    return voBgspspywfhxx;
  }

  /**
   * 变更审批作废业务
   * @param voBgspzfxx - 变更审批作废信息
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoBgspzfywfhxx processBgspzfyw(VoBgspzfxx voBgspzfxx[]) throws
      DAOException, ServiceException {

    VoBgspzfywfhxx voBgspzfywfhxx = null;
    VoBgspzffhxx voBgspzffhxx[] = null;
    String strHQL = null;

    if (voBgspzfxx == null || (voBgspzfxx != null && voBgspzfxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo  hjsp_bgspxxbDAO = DAOFactory.createHJSP_BGSPXXBDAO();
      PojoInfo  hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();

      //////////////////////////////////////////////
      //事务开始

      ////////////////////////////////////////////////
      //处理变更审批作废信息
      voBgspzffhxx = new VoBgspzffhxx[voBgspzfxx.length];
      for (int i = 0; i < voBgspzfxx.length; i++) {
        //得到变更审批信息
        PoHJSP_BGSPXXB poBgspxx  = super.get(PoHJSP_BGSPXXB.class,
            voBgspzfxx[i].getSpywid());
        if (poBgspxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到对应的变更审批信息,变更审批作废业务无法完成.", null);
        }

        //将变更审批信息的审批结果改成“无效”
        poBgspxx.setSpjg(HjConstant.SPJG_WX);
       super.update(poBgspxx);

        //人信息的人员锁定状态改成“正常”，解锁
        PoHJXX_CZRKJBXXB poRyxx = null;
        strHQL = "from PoHJXX_CZRKJBXXB where ryid=" + poBgspxx.getBgryid() +
            " and jlbz='" + PublicConstant.JLBZ_ZX + "' and cxbz='" +
            PublicConstant.CXBZ_FCX + "' ";
        List ryxxList =super.findAllByHQL(strHQL);
        if (ryxxList != null && ryxxList.size() > 0) {
          poRyxx = (PoHJXX_CZRKJBXXB) ryxxList.get(0);
          //校验人信息的时效性
          this.checkRXX(poRyxx, hjxx_czrkjbxxbDAO, "变更审批作废业务");
          //保存人信息
          poRyxx.setRysdzt(HjConstant.RYSDZT_ZC);
         super.update(poRyxx);
        }
        if (poRyxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到对应的人员信息,变更审批作废业务无法完成.", null);
        }

        //生成返回信息
        voBgspzffhxx[i] = new VoBgspzffhxx();
        voBgspzffhxx[i].setRynbid(poRyxx.getRynbid());
        voBgspzffhxx[i].setRyid(poRyxx.getRyid());
        voBgspzffhxx[i].setGmsfhm(poRyxx.getGmsfhm());
        voBgspzffhxx[i].setXm(poRyxx.getXm());
        voBgspzffhxx[i].setYhzgx(poRyxx.getYhzgx());
      }

      /////////////////////////////////////////
      //生成业务返回信息
      voBgspzfywfhxx = new VoBgspzfywfhxx();
      voBgspzfywfhxx.setVoBgspzffhxx(voBgspzffhxx);

      //////////////////////////////////////////////
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

    return voBgspzfywfhxx;
  }

  /**
   * 变更审批结果处理业务
   * @param voBgspjgclxx - 变更审批结果处理信息
   * @return - 变更审批结果处理业务返回信息
   * @throws DAOException
   * @throws ServiceException
   */
  public VoBgspjgclywfhxx processBgspjgclyw(VoBgspjgclxx voBgspjgclxx[]) throws
      DAOException, ServiceException {

    VoBgspjgclywfhxx voBgspjgclywfhxx = null;
    VoBgspjgclfhxx voBgspjgclfhxx[] = null;
    VoSbjbxx voSbjbxx = null;
    List bggzfhxxList = new ArrayList();
    List hcygxtzfhxxList = new ArrayList();
    String strHQL = null;
    Long hjywid = null;
    String now = StringUtils.getServiceTime();

    //////////////////////////////////////
    //数据校验
    if (voBgspjgclxx == null ||
        (voBgspjgclxx != null && voBgspjgclxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo  hjsp_bgspxxbDAO = DAOFactory.createHJSP_BGSPXXBDAO();
      PojoInfo  hjsp_bgspzbDAO = DAOFactory.createHJSP_BGSPZBDAO();
      PojoInfo  hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
      PojoInfo  hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();

      ////////////////////////////////////
      //事务开始

      ////////////////////////////////////////
      //得到户籍业务ID
      hjywid = (Long) hjls_hjywlsbDAO.getId();

      ////////////////////////////////////
      //通过审批结果处理信息生成变更更正信息
      voBgspjgclfhxx = new VoBgspjgclfhxx[voBgspjgclxx.length];
      for (int i = 0; i < voBgspjgclxx.length; i++) {
        //得到变更审批信息
        PoHJSP_BGSPXXB poBgspxx  = super.get(PoHJSP_BGSPXXB.class,
            voBgspjgclxx[i].getSpywid());
        if (poBgspxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到变更审批信息,变更审批结果处理业务无法完成.", null);
        }
        if (!HjConstant.SPJG_TY.equals(poBgspxx.getSpjg())) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "变更审批信息没有通过审批,变更审批结果处理业务无法完成.", null);
        }
        //得到人员信息
        PoHJXX_CZRKJBXXB poRyxx = null;
        strHQL = "from PoHJXX_CZRKJBXXB where ryid=" + poBgspxx.getBgryid() +
            " and jlbz='" + PublicConstant.JLBZ_ZX + "' and cxbz='" +
            PublicConstant.CXBZ_FCX + "' ";
        List ryxxList =super.findAllByHQL(strHQL);
        if (ryxxList != null && ryxxList.size() > 0) {
          poRyxx = (PoHJXX_CZRKJBXXB) ryxxList.get(0);
        }
        if (poRyxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到人员信息,变更审批结果处理业务无法完成.", null);
        }
        //校验人信息的时效性
        this.checkRXX(poRyxx, hjxx_czrkjbxxbDAO, "变更审批结果处理业务");
        //人员锁定状态
        if (!HjConstant.RYSDZT_BGGZSP.equals(poRyxx.getRysdzt())) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "人员锁定状态为非变更审批锁定状态，变更审批结果处理业务无法完成。", null);
        }
        //保存人员信息为历史记录
        poRyxx.setJssj(now);
        poRyxx.setCchjywid(hjywid);
        poRyxx.setRysdzt(HjConstant.RYSDZT_ZC); //解相关审批锁
        poRyxx.setJlbz(PublicConstant.JLBZ_LS);
       super.update(poRyxx);
        //生成变更更正信息(VoBggzxxEx)
        //得到变更审批子信息
        strHQL = "from PoHJSP_BGSPZB where spywid=" + poBgspxx.getSpywid();
        List bgspzxxList =super.findAllByHQL(strHQL);
        List bggzxxList = null;
        if (bgspzxxList != null && bgspzxxList.size() > 0) {
          bggzxxList = new ArrayList();
          for (int z = 0; z < bgspzxxList.size(); z++) {
            PoHJSP_BGSPZB po = (PoHJSP_BGSPZB) bgspzxxList.get(z);
            VoBggzxx vo = new VoBggzxx();
            vo.setBggzhnr(po.getBghnr());
            vo.setBggzlb(po.getBglb());
            vo.setBggzrq(po.getBgrq());
            vo.setBggzxm(po.getBggzxm());
            vo.setRynbid(poRyxx.getRynbid());
            vo.setSbhjywid(poRyxx.getCjhjywid());
            vo.setZp(null);
            bggzxxList.add(vo);
          }
        }
        VoBggzxxEx voBggzxxEx = new VoBggzxxEx();
        voBggzxxEx.setBggzxxList(bggzxxList);
        voBggzxxEx.setRynbid(poRyxx.getRynbid());
        voBggzxxEx.setSbhjywid(poRyxx.getCjhjywid());
        //生成申报基本信息
        voSbjbxx = new VoSbjbxx();
        voSbjbxx.setSbrgmsfhm(poBgspxx.getSbrgmsfhm());
        voSbjbxx.setSbryxm(poBgspxx.getSbryxm());
        voSbjbxx.setSbsj(poBgspxx.getSbsj());
        //生成人员信息(处理变更更正信息)
        PoHJXX_CZRKJBXXB poRyxxNew = new PoHJXX_CZRKJBXXB();
        BeanUtils.copyProperties(poRyxxNew, poRyxx);
        poRyxxNew.setRynbid( (Long) hjxx_czrkjbxxbDAO.getId());
        //处理变更更正信息
        VoBggzfhxxEx vo = this.disposalBggzxx(hjywid, voSbjbxx, voBggzxxEx,
                                              poRyxxNew,
                                              PublicConstant.GNBH_SP_BGSPJGCLYW,now);
        if (vo != null) {
          VoBggzfhxx vob[] = vo.getVoBggzfhxx();
          VoHcygxtzfhxx voh[] = vo.getVoHcygxtzfhxx();
          if (vob != null) {
            for (int k = 0; k < vob.length; k++) {
              bggzfhxxList.add(vob[k]);
            }
          }
          if (voh != null) {
            for (int k = 0; k < voh.length; k++) {
              hcygxtzfhxxList.add(voh[k]);
            }
          }
        } //if(vo!=null)
        //保存变更后的人员信息成新记录
        poRyxxNew.setQysj(now);
        poRyxxNew.setJssj(null);
        poRyxxNew.setXxqysj(now);//add by hb 20061027
        poRyxxNew.setYwnr(HjConstant.YWNR_BGGZ);
        poRyxxNew.setCchjywid(null);
        poRyxxNew.setCjhjywid(hjywid);
        poRyxxNew.setJlbz(PublicConstant.JLBZ_ZX);
        super.create(poRyxxNew);
        //保存变更审批信息(设置落实标志为落实和变更后人员内部ID)
        poBgspxx.setBghrynbid(poRyxxNew.getRynbid());
        poBgspxx.setHjywid(hjywid);
        poBgspxx.setLsbz(HjConstant.LSBZ_YLS);
       super.update(poBgspxx);
        //审批模板当前使用数减少1
        this.decSPMBDQSYS(poBgspxx.getSpmbid());
        //生成返回信息
        voBgspjgclfhxx[i] = new VoBgspjgclfhxx();
        voBgspjgclfhxx[i].setRyid(poRyxxNew.getRyid());
        voBgspjgclfhxx[i].setRynbid(poRyxxNew.getRynbid());
        voBgspjgclfhxx[i].setSpywid(poBgspxx.getSpywid());
      }

      //////////////////////////////////////////////
      //校验户主数量
      Map hhnbidMap = new HashMap();
      for (int h = 0; h < hcygxtzfhxxList.size(); h++) {
        VoHcygxtzfhxx vo = (VoHcygxtzfhxx) hcygxtzfhxxList.get(h);
        hhnbidMap.put(vo.getHhnbid(), null);
      }
      for (Iterator itr = hhnbidMap.keySet().iterator(); itr.hasNext(); ) {
        this.checkHCYXX( (Long) itr.next());
      }

      ////////////////////////////////////////
      //保存户籍业务流水信息
      this.saveHJYWLSXX(hjywid, PublicConstant.GNBH_SP_BGSPJGCLYW,
                        PublicConstant.HJYWLS_YWLX_GR,
                        voBgspjgclxx.length, voSbjbxx, now);

      ////////////////////////////////////
      //生成业务返回信息
      voBgspjgclywfhxx = new VoBgspjgclywfhxx();
      voBgspjgclywfhxx.setHjywid(hjywid);
      voBgspjgclywfhxx.setVoBggzfhxx( (VoBggzfhxx[]) bggzfhxxList.toArray(new
          VoBggzfhxx[bggzfhxxList.size()]));
      voBgspjgclywfhxx.setVoHcygxtzfhxx( (VoHcygxtzfhxx[]) hcygxtzfhxxList.
                                        toArray(new
                                                VoHcygxtzfhxx[hcygxtzfhxxList.
                                                size()]));
      voBgspjgclywfhxx.setVoBgspjgclfhxx(voBgspjgclfhxx);

      ////////////////////////////////////
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

    return voBgspjgclywfhxx;
  }

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
      DAOException, ServiceException {

    VoQrspdjywfhxx voQrspdjywfhxx = null;
    VoQrspdjzfhxx voQrspdjzfhxx[] = null; //迁入审批登记子返回信息(随迁人员登记返回信息)
    String strHQL = null;
    Long spmbid = null;
    String now = StringUtils.getServiceTime();

    if (voQrspdjxx == null) {
      return null;
    }

    try {
      PojoInfo  hjsp_hjspsqbDAO = DAOFactory.createHJSP_HJSPSQBDAO();
      PojoInfo  hjsp_hjspzbDAO = DAOFactory.createHJSP_HJSPZBDAO();
      PojoInfo  hjsp_hjspfdclbDAO = DAOFactory.createHJSP_HJSPFDCLBDAO();
      PojoInfo  xt_mblcxxbDAO = DAOFactory.createXT_MBLCXXBDAO();
      PojoInfo  xt_jwhxxbDAO = DAOFactory.createXT_JWHXXBDAO();
      PojoInfo  hz_js_bsDAO = DAOFactory.createHZ_JS_BSDAO();
      /////////////////////////////////////////////
      //事务开始

      if(qyldyyMap.size()==0){
        String hql = "from PoXT_QYZDYS a where 0=0";
        List list = super.findEntities(hql);
        for(int i=0;i<list.size();i++){
        	PoXT_QYZDYS o = (PoXT_QYZDYS)list.get(i);
          if(o.getZdlx().equalsIgnoreCase("QYLDYY")){
            qyldyyMap.put(o.getYzd(),o.getXzd());
          }else{
            qczxlbMap.put(o.getYzd(),o.getXzd());
          }
        }
      }
      Long spywid = (Long) hjsp_hjspsqbDAO.getId();
      if(voQrspdjxx!=null && voQrspdjxx.getHzywid()!=null && !voQrspdjxx.getHzywid().equals("")){
    	  updateHzyw(voQrspdjxx.getHzywid(),0l,spywid);
    	  autoInsertHzjsbs(voQrspdjxx.getHzywid());
      }

      String Hhnbid_rh = voQrspdjxx.getHhnbid_rh();
      String hb = null;
      if(Hhnbid_rh!=null){
        //入户查找户主户别
        String hql = "from PoHJXX_CZRKJBXXB a where a.hhnbid=" + Hhnbid_rh + " and (yhzgx='01' or yhzgx='02' or yhzgx='03')";
        List list2 = super.findEntities(hql);
        if(list2!=null && list2.size()>0){
          PoHJXX_CZRKJBXXB hz = (PoHJXX_CZRKJBXXB)list2.get(0);
//          hb = hz.getHb();
          /**
         	2018.10.05
         	修改	刁杰
         	暂时使用页面选择的入户后户别
           */
          hb = voQrspdjxx.getQrhhb();
        }
      }

      //////////////////////////////////////////////
      //业务审批限制
      VoXtywxz voLimit = XtywqxServiceImpl.VerifyCheckLimit(PublicConstant.
          GNBH_SP_QRSPDJYW, voQrspdjxx);
      if (voLimit.getLimitflag()) {
        spmbid = voLimit.getSpmbid();
      }

      ///////////////////////////////////////////////
      //得到流程模板信息
      PoXT_MBLCXXB poMblcxx = null;
      if (spmbid != null) {
        strHQL = "from PoXT_MBLCXXB where (dzbz='" + HjConstant.DZBZ_DYB +
            "' or dzbz='" + HjConstant.DZBZ_QSB + "') and spmbid=" + spmbid;
        List mblcxxList =super.findAllByHQL(strHQL);
        if (mblcxxList != null && mblcxxList.size() > 0) {
          poMblcxx = (PoXT_MBLCXXB) mblcxxList.get(0);
        }
        if (poMblcxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到对应的人审批流程信息,迁入审批登记业务无法完成.", null);
        }
      } //if (spmbid != null) {

      ////////////////////////////////////////////////
      //保存主迁人员信息
      PoHJSP_HJSPSQB poHjspsqxx = new PoHJSP_HJSPSQB();
      BeanUtils.copyProperties(poHjspsqxx, voQrspdjxx);
      poHjspsqxx.setSpywid(spywid);
      poHjspsqxx.setLsbz(HjConstant.LSBZ_WLS);
      poHjspsqxx.setDjrid(this.getUserInfo().getYhid());
      poHjspsqxx.setDjsj(now);
      poHjspsqxx.setSpmbid(spmbid);
      poHjspsqxx.setXydzid(poMblcxx != null ? poMblcxx.getDzid() : null); //操作员自审批
      poHjspsqxx.setSpjg(spmbid == null ? HjConstant.SPJG_TY : null); //操作员自审批
      poHjspsqxx.setSpsm(spsm);
      
      //受理日期
      poHjspsqxx.setSlrq(now.substring(0,8));
      
      PoXT_JWHXXB poXT_JWHXXB  = super.get(PoXT_JWHXXB.class,
              poHjspsqxx.getQrdjwh()); //通过居委会代码得到乡镇街道代码
      poHjspsqxx.setCxfldm(poXT_JWHXXB.getCxfldm());

      String new_xzd = null,new_xzd_qczxlb = null;

      if(poHjspsqxx.getKdqqy_qcdqbm()!=null && !"".equals(poHjspsqxx.getKdqqy_qcdqbm())){
        //跨地区迁入
        AuthToken auth = BaseContext.getUser();
        if (auth != null) {
          poHjspsqxx.setKdqqy_czydw(this.getUserInfo().getDwdm());
          poHjspsqxx.setKdqqy_czyxm(this.getUserInfo().getYhxm());
          poHjspsqxx.setKdqqy_fkzt("0");
          
          new_xzd = (String)qyldyyMap.get(poHjspsqxx.getSqqrly());
          new_xzd_qczxlb = (String)qczxlbMap.get(poHjspsqxx.getSqqrly());
        }
      }else{
        //外省迁入
        poHjspsqxx.setKdqqy_czydw(null);
        poHjspsqxx.setKdqqy_czyxm(null);
        poHjspsqxx.setKdqqy_fknr(null);
        poHjspsqxx.setKdqqy_fkzt(null);
        poHjspsqxx.setKdqqy_qcdqbm(null);
        poHjspsqxx.setKdqqy_qyzbh(null);
      }

      if(hb!=null) poHjspsqxx.setQrhhb(hb);
      super.create(poHjspsqxx);

      //保存主迁人信息到随迁人员信息表中
      PoHJSP_HJSPZB poHjspzxx = new PoHJSP_HJSPZB();
      poHjspzxx.setRyid(poHjspsqxx.getRyid());
      poHjspzxx.setXm(poHjspsqxx.getXm());
      poHjspzxx.setXb(poHjspsqxx.getXb());
      poHjspzxx.setCsrq(poHjspsqxx.getCsrq());
      poHjspzxx.setMz(poHjspsqxx.getMz());//add by hubin 20121018
      poHjspzxx.setGmsfhm(poHjspsqxx.getGmsfhm());
      poHjspzxx.setYsqrgx(poHjspsqxx.getYsqrgx());
      poHjspzxx.setYhkqx(poHjspsqxx.getZzssxq());
      poHjspzxx.setYhkszd(poHjspsqxx.getZzxz());
      poHjspzxx.setQrhhb(poHjspsqxx.getQrhhb()); //add by mhb 2004/12/24 12:57:00
      poHjspzxx.setHkxz(poHjspsqxx.getHb());
      poHjspzxx.setGzdw(poHjspsqxx.getYrdwjcyrysj());
      poHjspzxx.setRynbid(poHjspsqxx.getRynbid());
      poHjspzxx.setSpsqzid( (Long) hjsp_hjspzbDAO.getId());
      poHjspzxx.setSpywid(poHjspsqxx.getSpywid());
      poHjspzxx.setKdqqy_fkzt(poHjspsqxx.getKdqqy_fkzt());
      poHjspzxx.setKdqqy_qcdqbm(poHjspsqxx.getKdqqy_qcdqbm());
      poHjspzxx.setKdqqy_qyldyy(new_xzd);
      poHjspzxx.setKdqqy_qclb(new_xzd_qczxlb);
      if(hb!=null) poHjspzxx.setQrhhb(hb);
      super.create(poHjspzxx);

      ///////////////////////////////////////////////
      //保存随迁人员信息
      if (voQrspdjzxx != null && voQrspdjzxx.length > 0) {
        voQrspdjzfhxx = new VoQrspdjzfhxx[voQrspdjzxx.length];
        for (int i = 0; i < voQrspdjzxx.length; i++) {
          poHjspzxx = new PoHJSP_HJSPZB();
          BeanUtils.copyProperties(poHjspzxx, voQrspdjzxx[i]);
          poHjspzxx.setSpsqzid( (Long) hjsp_hjspzbDAO.getId());
          poHjspzxx.setSpywid(poHjspsqxx.getSpywid());
          poHjspzxx.setKdqqy_fkzt(poHjspsqxx.getKdqqy_fkzt());
          poHjspzxx.setKdqqy_qcdqbm(poHjspsqxx.getKdqqy_qcdqbm());
          poHjspzxx.setKdqqy_qyldyy(new_xzd);
          poHjspzxx.setKdqqy_qclb(new_xzd_qczxlb);
          //add by zjm 20200119随迁人的yhkszd取poHjspsqxx的zzxz信息 
          poHjspzxx.setYhkszd(poHjspsqxx.getZzxz());
          if(hb!=null) poHjspzxx.setQrhhb(hb);

          super.create(poHjspzxx);

          //生成随迁人员返回信息
          voQrspdjzfhxx[i] = new VoQrspdjzfhxx();
          voQrspdjzfhxx[i].setGmsfhm(poHjspzxx.getGmsfhm());
          voQrspdjzfhxx[i].setXm(poHjspzxx.getXm());
          voQrspdjzfhxx[i].setSpsqzid(poHjspzxx.getSpsqzid());
        }
      }

      //保存审批附带材料
      if (voSpfdclxx != null && voSpfdclxx.length > 0) {
        for (int i = 0; i < voSpfdclxx.length; i++) {
          PoHJSP_HJSPFDCLB poSpfdclxx = new PoHJSP_HJSPFDCLB();
          poSpfdclxx.setSpclid( (Long) hjsp_hjspfdclbDAO.getId());
          poSpfdclxx.setSpywid(poHjspsqxx.getSpywid());
          poSpfdclxx.setSplx(poHjspsqxx.getSplx());
          poSpfdclxx.setClbh(voSpfdclxx[i].getClbh());
          super.create(poSpfdclxx);
        }
      }

      //操作员自审批
      Long zqid = null;
      boolean flag = true;
      if (spmbid == null) {
        zqid = this.saveZQZXX(poHjspsqxx);
        flag = false;
      }
      
      ///////////////////////////////////////////////////////
      //保存户籍审批流水信息
      Long splsid = null;
      if (spmbid == null) { //操作员自审批
        splsid = this.saveHJSPLSXX(poHjspsqxx.getSpywid(),poHjspsqxx.getSplx(),null,HjConstant.CZJG_TY,null,now);
      }

      //审批模板当前使用数增加1
      this.incSPMBDQSYS(spmbid);

      //////////////////////////////////////////////
      //生成业务返回信息
      voQrspdjywfhxx = new VoQrspdjywfhxx();
      voQrspdjywfhxx.setSplsid(splsid);
      voQrspdjywfhxx.setGmsfhm(poHjspsqxx.getGmsfhm());
      voQrspdjywfhxx.setSpywid(poHjspsqxx.getSpywid());
      voQrspdjywfhxx.setXm(poHjspsqxx.getXm());
      voQrspdjywfhxx.setZqid(zqid);
      voQrspdjywfhxx.setVoQrspdjzfhxx(voQrspdjzfhxx);
      voQrspdjywfhxx.setSpFlag(flag);
      boolean isKDQ = (poHjspsqxx.getKdqqy_qcdqbm()!=null && !poHjspsqxx.getKdqqy_qcdqbm().equals(""));
      if(isKDQ) {
    	  if(CommonUtil.isNotBlank(voQrspdjxx.getHzywid())){
	          List<?> list = findEntities("from PoHZ_ZJ_SB a where a.id=" + voQrspdjxx.getHzywid());
	          if(list.size()>0){
	              PoHZ_ZJ_SB sb = (PoHZ_ZJ_SB)list.get(0);
	              if(sb.getPch()!=null && !sb.getPch().equals("")){
	                //一个批次，统一处理
	                list = findEntities("from PoHZ_ZJ_SB a where a.pch='" + sb.getPch() + "' ");
	                int pccount = list.size();
	                for(int index=0;index<pccount;index++){
	                	PoHZ_ZJ_SB sbx = (PoHZ_ZJ_SB)list.get(index);
	                	PoHZ_ZJ_SB hz_zj_sbTemp = new PoHZ_ZJ_SB();
		            	BeanUtils.copyProperties(hz_zj_sbTemp, sbx);
		            	Long id = (Long)hz_js_bsDAO.getId();
		            	hz_zj_sbTemp.setId(id);
						hz_zj_sbTemp.setLhbs("0");
						hz_zj_sbTemp.setSbzt("0");
						hz_zj_sbTemp.setClbs("0");
						hz_zj_sbTemp.setClsj(null);
						hz_zj_sbTemp.setBlrsfz(this.getUser().getGmsfhm());
						super.create(hz_zj_sbTemp);
						voQrspdjywfhxx.setNewHzywid(id+"");
	                }
	              }else{
	            	PoHZ_ZJ_SB hz_zj_sbTemp = new PoHZ_ZJ_SB();
	            	BeanUtils.copyProperties(hz_zj_sbTemp, sb);
	            	Long id = (Long)hz_js_bsDAO.getId();
	            	hz_zj_sbTemp.setId(id);
					hz_zj_sbTemp.setLhbs("0");
					hz_zj_sbTemp.setSbzt("0");
					hz_zj_sbTemp.setClbs("0");
					hz_zj_sbTemp.setClsj(null);
					hz_zj_sbTemp.setBlrsfz(this.getUser().getGmsfhm());
					super.create(hz_zj_sbTemp);
					voQrspdjywfhxx.setNewHzywid(id+"");
	              }
	          }
	       }
      }
      //////////////////////////////////////////////
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
    /*catch (SQLException ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }*/
    catch (Exception ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return voQrspdjywfhxx;
  }

  /**
   * 迁入审批审批业务
   * @param voQrspspxx - 迁入审批审批信息
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQrspspywfhxx processQrspspyw(VoQrspspxx voQrspspxx[]) throws
      DAOException, ServiceException {

    VoQrspspywfhxx voQrspspywfhxx = null;
    VoQrspspfhxx voQrspspfhxx[] = null;
    String strHQL = null;
    String now = StringUtils.getServiceTime();

    if (voQrspspxx == null || (voQrspspxx != null && voQrspspxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo  hjsp_hjspsqbDAO = DAOFactory.createHJSP_HJSPSQBDAO();
      PojoInfo  hjsp_hjspzbDAO = DAOFactory.createHJSP_HJSPZBDAO();
      PojoInfo  xt_mblcxxbDAO = DAOFactory.createXT_MBLCXXBDAO();

      ////////////////////////////////////
      //事务开始

      /////////////////////////////////////
      //保存（处理）迁入审批信息
      voQrspspfhxx = new VoQrspspfhxx[voQrspspxx.length];
      for (int i = 0; i < voQrspspxx.length; i++) {

        //得到迁入审批信息
        PoHJSP_HJSPSQB poHjspsqxx  = super.get(PoHJSP_HJSPSQB.class,
            voQrspspxx[i].getSpywid());
        if (poHjspsqxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到对应的迁入审批信息,迁入审批审批业务无法完成.", null);
        }
        //动作校验
        if (! (poHjspsqxx.getXydzid() != null && voQrspspxx[i].getDzid() != null &&
               poHjspsqxx.getXydzid().equals(voQrspspxx[i].getDzid()))) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "动作ID出错(可能已审批),迁入审批审批业务无法完成.", null);
        }
        //审批用户校验
        strHQL = "select count(*) from PoXT_YHDZQXB where yhid=" +
            this.getUserInfo().getYhid() + " and dzid=" + poHjspsqxx.getXydzid();
        if (super.getCount(strHQL) <= 0) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "该用户无权审批,迁入审批审批业务无法完成.", null);
        }
        //得到模板流程信息
        PoXT_MBLCXXB poMblcxx = null;
        strHQL = "from PoXT_MBLCXXB where spmbid=" + poHjspsqxx.getSpmbid() +
            " and dzid=" + voQrspspxx[i].getDzid() + " and (dzz='" +
            voQrspspxx[i].getCzjg() + "' or dzbz='" + HjConstant.DZBZ_DYB +
            "') ";
        List mblcxxList =super.findAllByHQL(strHQL);
        if (mblcxxList != null && mblcxxList.size() > 0) {
          poMblcxx = (PoXT_MBLCXXB) mblcxxList.get(0);
        }
        if (poMblcxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到对应的模板流程信息,迁入审批审批业务无法完成.", null);
        }
        //保存迁入审批信息
        Long zqid = null;
        poHjspsqxx.setXydzid(poMblcxx.getXgdzid());
        if (HjConstant.DZBZ_DYB.equals(poMblcxx.getDzbz()) ||
            HjConstant.DZBZ_JSB.equals(poMblcxx.getDzbz())) {
          poHjspsqxx.setSpjg(voQrspspxx[i].getCzjg());
          //审批流程结束并审批通过则生成准迁证信息(2004/10/28)
          if (HjConstant.SPJG_TY.equals(poHjspsqxx.getSpjg())) {
            zqid = this.saveZQZXX(poHjspsqxx);
          }
          //审批模板当前使用数减少1
          this.decSPMBDQSYS(poHjspsqxx.getSpmbid());
        }
       super.update(poHjspsqxx);
        //保存户籍审批流水信息
        Long splsid = this.saveHJSPLSXX(poHjspsqxx.getSpywid(),
                                        poHjspsqxx.getSplx(),
                                        voQrspspxx[i].getDzid(),
                                        voQrspspxx[i].getCzjg(),
                                        voQrspspxx[i].getCzyj(), now);

        //生成返回信息
        voQrspspfhxx[i] = new VoQrspspfhxx();
        voQrspspfhxx[i].setZqid(zqid);
        voQrspspfhxx[i].setSplsid(splsid);
        voQrspspfhxx[i].setSpywid(poHjspsqxx.getSpywid());
      } //for (int i = 0; i < voBgspspxx.length; i++) {

      ///////////////////////////////////////
      //生成业务返回信息
      voQrspspywfhxx = new VoQrspspywfhxx();
      voQrspspywfhxx.setVoQrspspfhxx(voQrspspfhxx);

      ////////////////////////////////////
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

    return voQrspspywfhxx;
  }

  /**
   * 迁入审批作废业务
   * @param voQrspzfxx - 迁入审批作废信息
   * @return - 迁入审批作废业务返回信息
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQrspzfywfhxx processQrspzfyw(VoQrspzfxx voQrspzfxx[]) throws
      DAOException, ServiceException {
    VoQrspzfywfhxx voQrspzfywfhxx = null;
    VoQrspzffhxx voQrspzffhxx[] = null;

    if (voQrspzfxx == null ||
        (voQrspzfxx != null && voQrspzfxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo  hjsp_hjspsqbDAO = DAOFactory.createHJSP_HJSPSQBDAO();

      //////////////////////////////////////////////
      //事务开始

      ////////////////////////////////////////////////
      //处理迁入审批作废信息
      voQrspzffhxx = new VoQrspzffhxx[voQrspzfxx.length];
      for (int i = 0; i < voQrspzfxx.length; i++) {
        //得到迁入审批信息
        PoHJSP_HJSPSQB poQrspxx  = super.get(PoHJSP_HJSPSQB.class,
            voQrspzfxx[i].getSpywid());
        //PoHJSP_BGSPXXB poHJSP_BGSPXXB = super.get(PoHJSP_BGSPXXB.class, voQrspzfxx[i].getSpywid());
        if (poQrspxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到对应的迁入审批信息,迁入审批作废业务无法完成.", null);
        }
        //poHJSP_BGSPXXB.setSpjg(HjConstant.SPJG_WX);
        //super.update(poHJSP_BGSPXXB);
        //将迁入审批信息的审批结果改成“无效”
        poQrspxx.setSpjg(HjConstant.SPJG_WX);
       super.update(poQrspxx);

        //生成返回信息
        voQrspzffhxx[i] = new VoQrspzffhxx();
        voQrspzffhxx[i].setSpywid(poQrspxx.getSpywid());
      }

      /////////////////////////////////////////
      //生成业务返回信息
      voQrspzfywfhxx = new VoQrspzfywfhxx();
      voQrspzfywfhxx.setVoQrspzffhxx(voQrspzffhxx);

      //////////////////////////////////////////////
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

    return voQrspzfywfhxx;
  }

  /**
   * 迁入审批信息获取(主迁人员信息获取)
   * @param strHQL
   * @param voPage
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQueryResult queryQrspxx(String strHQL, VoPage voPage) throws
      DAOException, ServiceException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append(
        "from PoHJSP_HJSPSQB as HJSP_HJSPSQB,PoXT_SPDZB as XT_SPDZB ")
        .append("where HJSP_HJSPSQB.xydzid=XT_SPDZB.dzid(+) ");
    //数据范围begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_SP_QRSPXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //居(村)委会
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJSP_HJSPSQB.qrdjwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJSP_HJSPSQB.qrdjwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //派出所
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJSP_HJSPSQB.qrdpcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJSP_HJSPSQB.qrdpcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //行政区划
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJSP_HJSPSQB.qrdqx='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJSP_HJSPSQB.qrdqx='" +
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
    strAllHQL.append(
        "select HJSP_HJSPSQB,XT_SPDZB ").
        append(strFromHQL.toString());

    //debug info
    //_log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo  hjsp_hjspsqbDAO = DAOFactory.createHJSP_HJSPSQBDAO();


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
        Object ob[] = (Object[]) poList.get(i);
        PoHJSP_HJSPSQB poHjspsqxx = (PoHJSP_HJSPSQB) ob[0];
        PoXT_SPDZB poSpdzxx = (PoXT_SPDZB) ob[1];

        VoQrspxxHqFhxx voQrspxxHqFhxx = new VoQrspxxHqFhxx(poHjspsqxx, poSpdzxx);

        voList.add(voQrspxxHqFhxx);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoQrspxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * 获取登录用户能审批的迁入审批信息(主迁人员信息获取)
   * @param strHQL
   * @param voPage
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQueryResult queryQrspxxByUser(String strHQL, VoPage voPage) throws
      DAOException, ServiceException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append(
        "from PoHJSP_HJSPSQB as HJSP_HJSPSQB,PoXT_SPDZB as XT_SPDZB,PoXT_YHDZQXB as XT_YHDZQXB ")
        .append("where HJSP_HJSPSQB.xydzid=XT_SPDZB.dzid ")
        .append("and XT_YHDZQXB.dzid=HJSP_HJSPSQB.xydzid ")
        .append("and XT_YHDZQXB.yhid=" + this.getUserInfo().getYhid() + " ");
    //数据范围begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_SP_YH_QRSPXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //居(村)委会
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJSP_HJSPSQB.qrdjwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJSP_HJSPSQB.qrdjwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //派出所
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJSP_HJSPSQB.qrdpcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJSP_HJSPSQB.qrdpcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //行政区划
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJSP_HJSPSQB.qrdqx='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJSP_HJSPSQB.qrdqx='" +
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
    strAllHQL.append(
        "select HJSP_HJSPSQB,XT_SPDZB ").
        append(strFromHQL.toString());

    //debug info
    //_log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo  hjsp_hjspsqbDAO = DAOFactory.createHJSP_HJSPSQBDAO();


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
        Object ob[] = (Object[]) poList.get(i);
        PoHJSP_HJSPSQB poHjspsqxx = (PoHJSP_HJSPSQB) ob[0];
        PoXT_SPDZB poSpdzxx = (PoXT_SPDZB) ob[1];

        VoQrspxxHqFhxx voQrspxxHqFhxx = new VoQrspxxHqFhxx(poHjspsqxx, poSpdzxx);

        voList.add(voQrspxxHqFhxx);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoQrspxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * 迁入审批子信息获取(随迁人员信息获取)
   * @param spywid - 审批业务ID
   * @param voPage - 分页信息
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQueryResult queryQrspzxx(Long spywid, VoPage voPage) throws
      DAOException, ServiceException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    if (spywid == null || voPage == null) {
      return null;
    }

    //生成FROM HQL语句
    strFromHQL.append("from PoHJSP_HJSPZB as HJSP_HJSPZB ")
        .append("where HJSP_HJSPZB.spywid=" + spywid.toString());

    //生成COUNT HQL语句
    if (voPage.getRecordcount() == -1) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //生成SELECT HQL语句
    strAllHQL.append("select HJSP_HJSPZB ").
        append(strFromHQL.toString());

    //debug info
    //_log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo  hjsp_hjspzbDAO = DAOFactory.createHJSP_HJSPZBDAO();


      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(
            strCountHQL.toString()));
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
    voQueryResult.setVotype(PoHJSP_HJSPZB.class);
    return voQueryResult;
  }

  /**
   * 变更审批审批信息获取
   * @param strHQL
   * @param voPage
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQueryResult queryBgspspxx(String strHQL, VoPage voPage) throws
      DAOException, ServiceException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append(
        "from PoHJSP_BGSPXXB as HJSP_BGSPXXB,PoHJXX_CZRKJBXXB as HJXX_CZRKJBXXB,PoHJSP_HJSPLSB as HJSP_HJSPLSB,PoXT_SPDZB as XT_SPDZB, PoXT_SPDZB as XYDZ_XT_SPDZB ")
        .append("where HJSP_BGSPXXB.bgryid=HJXX_CZRKJBXXB.ryid ")
        .append("and HJSP_HJSPLSB.spywid=HJSP_BGSPXXB.spywid ")
        .append("and HJSP_HJSPLSB.splx='" + HjConstant.SPLX_BGGZ + "' ")
        .append("and XT_SPDZB.dzid(+)=HJSP_HJSPLSB.dzid ")
        .append("and XYDZ_XT_SPDZB.dzid(+)=HJSP_BGSPXXB.xydzid ")
        .append("and HJXX_CZRKJBXXB.jlbz='" + PublicConstant.JLBZ_ZX + "' ")
        .append("and HJXX_CZRKJBXXB.cxbz='" + PublicConstant.CXBZ_FCX + "' ");
    //数据范围begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_SP_BGSPSPXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //居(村)委会
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //派出所
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //行政区划
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.ssxq='" +
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
    strAllHQL.append(
        "select HJSP_BGSPXXB,HJXX_CZRKJBXXB,HJSP_HJSPLSB,XT_SPDZB,XYDZ_XT_SPDZB ").
        append(strFromHQL.toString());

    //debug info
    //_log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo  hjsp_bgspxxbDAO = DAOFactory.createHJSP_BGSPXXBDAO();


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
        Object ob[] = (Object[]) poList.get(i);
        PoHJSP_BGSPXXB poBgspxx = (PoHJSP_BGSPXXB) ob[0];
        PoHJXX_CZRKJBXXB poRyxx = (PoHJXX_CZRKJBXXB) ob[1];
        PoHJSP_HJSPLSB poHjsplsxx = (PoHJSP_HJSPLSB) ob[2];
        PoXT_SPDZB poSpdzxx = (PoXT_SPDZB) ob[3];
        PoXT_SPDZB poXyspdzxx = (PoXT_SPDZB) ob[4];

        //只返回最后一次变更审批审批记录
        boolean bfind = false;
        for (int a = 0; a < voList.size(); a++) {
          VoBgspspxxHqFhxx vo = (VoBgspspxxHqFhxx) voList.get(a);
          if (poHjsplsxx.getSpywid() != null &&
              poHjsplsxx.getSpywid().equals(vo.getSpywid())) {
            if (poHjsplsxx.getCzsj() != null &&
                poHjsplsxx.getCzsj().compareTo(vo.getCzsj()) > 0) {
              voList.remove(a);
            }
            else {
              bfind = true;
            }
            break;
          }
        } //for(int a=0;a<voList.size();a++){
        if (!bfind) {
          VoBgspspxxHqFhxx voBgspspxxHqFhxx = new VoBgspspxxHqFhxx(poBgspxx,
              poRyxx, poHjsplsxx, poSpdzxx, poXyspdzxx);
          voList.add(voBgspspxxHqFhxx);
        } //if(!bfind){
      } //for (int i = 0; i < poList.size(); i++) {
    } //if (poList != null && poList.size() > 0) {

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoBgspspxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * 迁入审批审批信息获取
   * @param strHQL
   * @param voPage
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQueryResult queryQrspspxx(String strHQL, VoPage voPage) throws
      DAOException, ServiceException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append("from PoHJSP_HJSPSQB as HJSP_HJSPSQB,PoHJSP_HJSPLSB as HJSP_HJSPLSB,PoXT_SPDZB as XYDZ_XT_SPDZB,PoXT_SPDZB as XT_SPDZB ")
        .append("where HJSP_HJSPSQB.spywid=HJSP_HJSPLSB.spywid ")
        .append("and  HJSP_HJSPSQB.splx=HJSP_HJSPLSB.splx ")
        .append("and HJSP_HJSPSQB.xydzid=XYDZ_XT_SPDZB.dzid(+) ")
        .append("and HJSP_HJSPLSB.dzid=XT_SPDZB.dzid(+) ");
    //数据范围begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_SP_QRSPSPXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //居(村)委会
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJSP_HJSPSQB.qrdjwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJSP_HJSPSQB.qrdjwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //派出所
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJSP_HJSPSQB.qrdpcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJSP_HJSPSQB.qrdpcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //行政区划
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJSP_HJSPSQB.qrdqx='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJSP_HJSPSQB.qrdqx='" +
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
    strAllHQL.append(
        "select HJSP_HJSPSQB,HJSP_HJSPLSB,XYDZ_XT_SPDZB,XT_SPDZB ").
        append(strFromHQL.toString());

    //debug info
    //_log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo  hjsp_hjspsqbDAO = DAOFactory.createHJSP_HJSPSQBDAO();


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
        Object ob[] = (Object[]) poList.get(i);
        PoHJSP_HJSPSQB poHjspsqxx = (PoHJSP_HJSPSQB) ob[0];
        PoHJSP_HJSPLSB poHjsplsxx = (PoHJSP_HJSPLSB) ob[1];
        PoXT_SPDZB poXyspdzxx = (PoXT_SPDZB) ob[2];
        PoXT_SPDZB poSpdzxx = (PoXT_SPDZB) ob[3];

        //只返回最后一次迁入审批审批记录
        boolean bfind = false;
        for (int a = 0; a < voList.size(); a++) {
          VoQrspspxxHqFhxx vo = (VoQrspspxxHqFhxx) voList.get(a);
          if (poHjsplsxx.getSpywid() != null &&
              poHjsplsxx.getSpywid().equals(vo.getSpywid())) {
            if (poHjsplsxx.getCzsj() != null &&
                poHjsplsxx.getCzsj().compareTo(vo.getCzsj()) > 0) {
              voList.remove(a);
            }
            else {
              bfind = true;
            }
            break;
          }
        } //for(int a=0;a<voList.size();a++){
        if (!bfind) {
          VoQrspspxxHqFhxx voQrspspxxHqFhxx = new VoQrspspxxHqFhxx(poHjspsqxx,
              poHjsplsxx, poSpdzxx, poXyspdzxx);
          voList.add(voQrspspxxHqFhxx);
        } //if(!bfind){
      } //for (int i = 0; i < poList.size(); i++) {
    } //if (poList != null && poList.size() > 0) {

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoQrspspxxHqFhxx.class);
    return voQueryResult;
  }

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
      DAOException, ServiceException {
    VoHjblspdjywfhxx voHjblspdjywfhxx = null;
    VoHjblspdjfhxx voHjblspdjfhxx[] = null;
    String strHQL = null;
    Long ywxl = null;
    String now = StringUtils.getServiceTime();

    /////////////////////////////////////////
    //数据校验
    if (voHjblspdjxx == null ||
        (voHjblspdjxx != null && voHjblspdjxx.length == 0)) {
      return null;
    }

    try {
      PojoInfo  hjsp_hjsplsbDAO = DAOFactory.createHJSP_HJSPLSBDAO();
      PojoInfo  hjsp_hjspfdclbDAO = DAOFactory.createHJSP_HJSPFDCLBDAO();
      PojoInfo  xt_mblcxxbDAO = DAOFactory.createXT_MBLCXXBDAO();
      PojoInfo  hjsp_hjblspsqbdAO = DAOFactory.createHJSP_HJBLSPSQBDAO();
      PojoInfo  xt_jwhxxbDAO = DAOFactory.createXT_JWHXXBDAO();

      ///////////////////////////////
      //保存户籍补录审批登记信息
      voHjblspdjfhxx = new VoHjblspdjfhxx[voHjblspdjxx.length];
      
      for (int i = 0; i < voHjblspdjxx.length; i++) {
    	  Long spywid =  (Long) hjsp_hjblspsqbdAO.getId();
          /////////////////////////////
          //事务开始
          if(voHjblspdjxx!=null && voHjblspdjxx.length>0 && voHjblspdjxx[i].getHzywid()!=null && !voHjblspdjxx[i].getHzywid().equals("")){
        	  updateHzyw(voHjblspdjxx[i].getHzywid(),0l,spywid);
          }
        //得到审批流程信息
        PoXT_MBLCXXB poMblcxx = null;
        if (spmbid != null) {
          strHQL = "from PoXT_MBLCXXB where (dzbz='" + HjConstant.DZBZ_DYB +
              "' or dzbz='" + HjConstant.DZBZ_QSB + "') and spmbid=" + spmbid;
          List mblcxxList =super.findAllByHQL(strHQL);
          if (mblcxxList != null && mblcxxList.size() > 0) {
            poMblcxx = (PoXT_MBLCXXB) mblcxxList.get(0);
          }
          if (poMblcxx == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "找不到对应的人审批流程信息,变更审批登记业务无法完成.", null);
          }
        }

        //保存户籍补录审批信息
        PoHJSP_HJBLSPSQB poHjblspxx = new PoHJSP_HJBLSPSQB();
        BeanUtils.copyProperties(poHjblspxx, voHjblspdjxx[i]);
        poHjblspxx.setBlyy(voHjblspdjxx[i].getHjbllb());
        poHjblspxx.setRlhbz(voHjblspdjxx[i].getSshhid() != null ?
                            HjConstant.RLHBZ_LH : HjConstant.RLHBZ_RH);
        poHjblspxx.setSpywid(spywid);
        if (ywxl == null) {
          ywxl = poHjblspxx.getSpywid();
        }
        poHjblspxx.setYwxl(ywxl);
        poHjblspxx.setDjrid(this.getUserInfo().getYhid());
        poHjblspxx.setDjsj(now);
        poHjblspxx.setLsbz(HjConstant.LSBZ_WLS);
        poHjblspxx.setXydzid(poMblcxx != null ? poMblcxx.getDzid() : null);
        poHjblspxx.setSpmbid(poMblcxx != null ? poMblcxx.getSpmbid() : null);
        poHjblspxx.setSpjg(poMblcxx != null ? null : HjConstant.SPJG_TY);
        poHjblspxx.setSpsm(spsm);

        PoXT_JWHXXB poJwhxxb  = super.get(PoXT_JWHXXB.class,poHjblspxx.getSsjwh());
        if(poJwhxxb==null)
          throw new ServiceException("没有找到居委会" + poHjblspxx.getSsjwh() + "信息！");
        poHjblspxx.setCxfldm(poJwhxxb.getCxfldm());

        super.create(poHjblspxx);

        //保存审批附带材料
        if (voSpfdclxx != null && voSpfdclxx.length > 0) {
          for (int a = 0; a < voSpfdclxx.length; a++) {
            PoHJSP_HJSPFDCLB poSpfdclxx = new PoHJSP_HJSPFDCLB();
            poSpfdclxx.setSpclid( (Long) hjsp_hjspfdclbDAO.getId());
            poSpfdclxx.setSpywid(poHjblspxx.getSpywid());
            poSpfdclxx.setSplx(HjConstant.SPLX_HJBL);
            poSpfdclxx.setClbh(voSpfdclxx[a].getClbh());
            super.create(poSpfdclxx);
          }
        }

        //保存户籍审批流水信息
        Long splsid = null;
        if (poMblcxx == null) {
          splsid = this.saveHJSPLSXX(poHjblspxx.getSpywid(),
                                     HjConstant.SPLX_HJBL,
                                     null, HjConstant.CZJG_TY, null, now);
        }

        //审批模板当前使用数增加1
        this.incSPMBDQSYS(spmbid);

        //生成返回信息
        voHjblspdjfhxx[i] = new VoHjblspdjfhxx();
        voHjblspdjfhxx[i].setSpywid(poHjblspxx.getSpywid());
      } //for (int i = 0; i < voBggzxxEx.length; i++) {
      if(voHjblspdjxx!=null && voHjblspdjxx.length>=1 && voHjblspdjxx[0].getHzywid()!=null && !voHjblspdjxx[0].getHzywid().equals("")){
    	  autoInsertHzjsbs(voHjblspdjxx[0].getHzywid());
      }
      //////////////////////////////////////
      //生成业务返回信息
      voHjblspdjywfhxx = new VoHjblspdjywfhxx();
      voHjblspdjywfhxx.setVoHjblspdjfhxx(voHjblspdjfhxx);

      ////////////////////////////////////
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

    return voHjblspdjywfhxx;
  }

  /**
   * 户籍补录审批审批业务
   * @param voHjblspspxx - 户籍补录审批审批信息
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoHjblspspywfhxx processHjblspspyw(VoHjblspspxx voHjblspspxx[]) throws
      DAOException, ServiceException {
    VoHjblspspywfhxx voHjblspspywfhxx = null;
    VoHjblspspfhxx voHjblspspfhxx[] = null;
    String strHQL = null;
    String now = StringUtils.getServiceTime();

    //数据校验
    if (voHjblspspxx == null ||
        (voHjblspspxx != null && voHjblspspxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo  hjsp_hjblspsqbDAO = DAOFactory.createHJSP_HJBLSPSQBDAO();
      PojoInfo  xt_mblcxxbDAO = DAOFactory.createXT_MBLCXXBDAO();

      ////////////////////////////////////
      //事务开始

      /////////////////////////////////////
      //保存（处理）户籍补录审批审批信息
      voHjblspspfhxx = new VoHjblspspfhxx[voHjblspspxx.length];
      for (int i = 0; i < voHjblspspxx.length; i++) {

        //得到户籍补录审批信息
        PoHJSP_HJBLSPSQB poHjblspsqxx = super.
            get(PoHJSP_HJBLSPSQB.class,voHjblspspxx[i].getSpywid());
        if (poHjblspsqxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到对应的户籍补录审批信息,户籍补录审批审批业务无法完成.", null);
        }
        //动作校验
        if (! (poHjblspsqxx.getXydzid() != null && voHjblspspxx[i].getDzid() != null &&
               poHjblspsqxx.getXydzid().equals(voHjblspspxx[i].getDzid()))) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "动作ID出错(可能已审批),户籍补录审批审批业务无法完成.", null);
        }
        //审批用户校验
        strHQL = "select count(*) from PoXT_YHDZQXB where yhid=" +
            this.getUserInfo().getYhid() + " and dzid=" +
            poHjblspsqxx.getXydzid();
        if (super.getCount(strHQL) <= 0) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "该用户无权审批,户籍补录审批审批业务无法完成.", null);
        }
        //得到模板流程信息
        PoXT_MBLCXXB poMblcxx = null;
        strHQL = "from PoXT_MBLCXXB where spmbid=" + poHjblspsqxx.getSpmbid() +
            " and dzid=" + voHjblspspxx[i].getDzid() + " and (dzz='" +
            voHjblspspxx[i].getCzjg() + "' or dzbz='" + HjConstant.DZBZ_DYB +
            "') ";
        List mblcxxList =super.findAllByHQL(strHQL);
        if (mblcxxList != null && mblcxxList.size() > 0) {
          poMblcxx = (PoXT_MBLCXXB) mblcxxList.get(0);
        }
        if (poMblcxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到对应的模板流程信息,户籍补录审批审批业务无法完成.", null);
        }
        //保存户籍补录审批审批信息
        Long zqid = null;
        poHjblspsqxx.setXydzid(poMblcxx.getXgdzid());
        if (HjConstant.DZBZ_DYB.equals(poMblcxx.getDzbz()) ||
            HjConstant.DZBZ_JSB.equals(poMblcxx.getDzbz())) {
          poHjblspsqxx.setSpjg(voHjblspspxx[i].getCzjg());
          //审批模板当前使用数减少1
          this.decSPMBDQSYS(poHjblspsqxx.getSpmbid());
        }
       super.update(poHjblspsqxx);
        //保存户籍审批流水信息
        Long splsid = this.saveHJSPLSXX(poHjblspsqxx.getSpywid(),
                                        HjConstant.SPLX_HJBL,
                                        voHjblspspxx[i].getDzid(),
                                        voHjblspspxx[i].getCzjg(),
                                        voHjblspspxx[i].getCzyj(), now);

        //生成返回信息
        voHjblspspfhxx[i] = new VoHjblspspfhxx();
        voHjblspspfhxx[i].setSplsid(splsid);
        voHjblspspfhxx[i].setSpywid(poHjblspsqxx.getSpywid());
      } //for (int i = 0; i < voBgspspxx.length; i++) {

      ///////////////////////////////////////
      //生成业务返回信息
      voHjblspspywfhxx = new VoHjblspspywfhxx();
      voHjblspspywfhxx.setVoHjblspspfhxx(voHjblspspfhxx);

      ////////////////////////////////////
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

    return voHjblspspywfhxx;
  }

  /**
   * 户籍补录审批作废业务
   * @param voHjblspzfxx - 户籍补录审批作废信息
   * @return - 户籍补录审批作废业务返回信息
   * @throws DAOException
   * @throws ServiceException
   */
  public VoHjblspzfywfhxx processHjblspzfyw(VoHjblspzfxx voHjblspzfxx[]) throws
      DAOException, ServiceException {
    VoHjblspzfywfhxx voHjblspzfywfhxx = null;
    VoHjblspzffhxx voHjblspzffhxx[] = null;

    if (voHjblspzfxx == null ||
        (voHjblspzfxx != null && voHjblspzfxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo  hjsp_hjblspsqbDAO = DAOFactory.createHJSP_HJBLSPSQBDAO();

      //////////////////////////////////////////////
      //事务开始

      ////////////////////////////////////////////////
      //处理户籍补录审批作废信息
      voHjblspzffhxx = new VoHjblspzffhxx[voHjblspzfxx.length];
      for (int i = 0; i < voHjblspzfxx.length; i++) {
        //得到户籍补录审批信息
        PoHJSP_HJBLSPSQB poHjblspxx  = super.get(PoHJSP_HJBLSPSQB.class,
            voHjblspzfxx[i].getSpywid());
        if (poHjblspxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到对应的户籍补录审批信息,户籍补录审批作废业务无法完成.", null);
        }

        //将户籍补录审批信息的审批结果改成“无效”
        poHjblspxx.setSpjg(HjConstant.SPJG_WX);
       super.update(poHjblspxx);

        //生成返回信息
        voHjblspzffhxx[i] = new VoHjblspzffhxx();
        voHjblspzffhxx[i].setSpywid(poHjblspxx.getSpywid());
      }

      /////////////////////////////////////////
      //生成业务返回信息
      voHjblspzfywfhxx = new VoHjblspzfywfhxx();
      voHjblspzfywfhxx.setVoHjblspzffhxx(voHjblspzffhxx);

      //////////////////////////////////////////////
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

    return voHjblspzfywfhxx;
  }

  /**
   * 户籍补录审批信息获取
   * @param strHQL
   * @param voPage
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQueryResult queryHjblspxx(String strHQL, VoPage voPage) throws
      DAOException, ServiceException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append(
        "from PoHJSP_HJBLSPSQB as HJSP_HJBLSPSQB ")
        .append("where 0=0 ");
    //数据范围begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_SP_HJBLSPXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //居(村)委会
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJSP_HJBLSPSQB.ssjwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJSP_HJBLSPSQB.ssjwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //派出所
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJSP_HJBLSPSQB.sspcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJSP_HJBLSPSQB.sspcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //行政区划
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJSP_HJBLSPSQB.ssssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJSP_HJBLSPSQB.ssssxq='" +
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
    strAllHQL.append(
        "select HJSP_HJBLSPSQB ").
        append(strFromHQL.toString());

    //debug info
    //_log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo  hjsp_hjblspsqbDAO = DAOFactory.createHJSP_HJBLSPSQBDAO();


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
        PoHJSP_HJBLSPSQB pohjblspxx = (PoHJSP_HJBLSPSQB)poList.get(i);
        PoXT_SPDZB poSpdzxx = null;//(PoXT_SPDZB) ob[1];
        if(pohjblspxx.getXydzid()!=null && pohjblspxx.getXydzid().longValue()!=0){
        	poSpdzxx = super.get(PoXT_SPDZB.class, pohjblspxx.getXydzid());
        }
        VoHjblspxxHqFhxx voHjblspxxHqFhxx = new VoHjblspxxHqFhxx(pohjblspxx,
            poSpdzxx);

        voList.add(voHjblspxxHqFhxx);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoHjblspxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * 获取登录用户能审批的户籍补录审批信息
   * @param strHQL
   * @param voPage
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQueryResult queryHjblspxxByUser(String strHQL, VoPage voPage) throws
      DAOException, ServiceException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append("from PoHJSP_HJBLSPSQB as HJSP_HJBLSPSQB,PoXT_SPDZB as XT_SPDZB,PoXT_YHDZQXB as XT_YHDZQXB ")
        .append("where XT_SPDZB.dzid=HJSP_HJBLSPSQB.xydzid ")
        .append("and XT_YHDZQXB.dzid=HJSP_HJBLSPSQB.xydzid ")
        .append("and XT_YHDZQXB.yhid=" + this.getUserInfo().getYhid() + " ");
    //数据范围begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_SP_YH_HJBLSPXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //居(村)委会
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJSP_HJBLSPSQB.ssjwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJSP_HJBLSPSQB.ssjwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //派出所
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJSP_HJBLSPSQB.sspcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJSP_HJBLSPSQB.sspcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //行政区划
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJSP_HJBLSPSQB.ssssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJSP_HJBLSPSQB.ssssxq='" +
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
    strAllHQL.append(
        "select HJSP_HJBLSPSQB,XT_SPDZB ").
        append(strFromHQL.toString());

    //debug info
    //_log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo  hjsp_hjblspsqbDAO = DAOFactory.createHJSP_HJBLSPSQBDAO();


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
        Object ob[] = (Object[]) poList.get(i);

        PoHJSP_HJBLSPSQB pohjblspxx = (PoHJSP_HJBLSPSQB) ob[0];
        PoXT_SPDZB poSpdzxx = (PoXT_SPDZB) ob[1];

        VoHjblspxxHqFhxx voHjblspxxHqFhxx = new VoHjblspxxHqFhxx(pohjblspxx,
            poSpdzxx);

        voList.add(voHjblspxxHqFhxx);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoHjblspxxHqFhxx.class);
    return voQueryResult;
  }

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
      DAOException, ServiceException {
    VoHjscspdjywfhxx voHjscspdjywfhxx = null;
    VoHjscspdjfhxx voHjscspdjfhxx[] = null;
    String strHQL = null;
    String now = StringUtils.getServiceTime();

    /////////////////////////////////////////
    //数据校验
    if (voHjscspdjxx == null ||
        (voHjscspdjxx != null && voHjscspdjxx.length == 0)) {
      return null;
    }

    try {
      PojoInfo  hjsp_hjsplsbDAO = DAOFactory.createHJSP_HJSPLSBDAO();
      PojoInfo  hjsp_hjspfdclbDAO = DAOFactory.createHJSP_HJSPFDCLBDAO();
      PojoInfo  xt_mblcxxbDAO = DAOFactory.createXT_MBLCXXBDAO();
      PojoInfo  hjsp_hjscspsqbdAO = DAOFactory.createHJSP_HJSCSPSQBDAO();
      PojoInfo  hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();

      
      /////////////////////////////
      //事务开始

      ///////////////////////////////
      //保存户籍删除审批登记信息
      voHjscspdjfhxx = new VoHjscspdjfhxx[voHjscspdjxx.length];
      System.out.println("a");
      for (int i = 0; i < voHjscspdjxx.length; i++) {
    	  Long spywid =  (Long) hjsp_hjscspsqbdAO.getId();
    	  updateHzyw(voHjscspdjxx[i].getHzywid(),0l,spywid);
        //得到人员信息
        PoHJXX_CZRKJBXXB poRyxx = null;
        strHQL = "from PoHJXX_CZRKJBXXB where rynbid=" +
            voHjscspdjxx[i].getRynbid() + " and jlbz='" +
            PublicConstant.JLBZ_ZX + "' and cxbz='" + PublicConstant.CXBZ_FCX +
            "' ";
        List ryxxList =super.findAllByHQL(strHQL);
        if (ryxxList != null && ryxxList.size() > 0) {
          poRyxx = (PoHJXX_CZRKJBXXB) ryxxList.get(0);
        }
        if (poRyxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到对应的人员信息,户籍删除审批登记业务无法完成.", null);
        }
        //校验人信息的时效性
        this.checkRXX(poRyxx, hjxx_czrkjbxxbDAO, "户籍删除审批登记业务");
        //人信息加锁
        poRyxx.setRysdzt(HjConstant.RYSDZT_HJSCSP);
       super.update(poRyxx);

        //得到审批流程信息
        PoXT_MBLCXXB poMblcxx = null;
        if (spmbid != null) {
          strHQL = "from PoXT_MBLCXXB where (dzbz='" + HjConstant.DZBZ_DYB +
              "' or dzbz='" + HjConstant.DZBZ_QSB + "') and spmbid=" + spmbid;
          List mblcxxList =super.findAllByHQL(strHQL);
          if (mblcxxList != null && mblcxxList.size() > 0) {
            poMblcxx = (PoXT_MBLCXXB) mblcxxList.get(0);
          }
          if (poMblcxx == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "找不到对应的人审批流程信息,变更审批登记业务无法完成.", null);
          }
        }

        //保存户籍删除审批信息
        PoHJSP_HJSCSPSQB poHjscspxx = new PoHJSP_HJSCSPSQB();
        BeanUtils.copyProperties(poHjscspxx, voHjscspdjxx[i]);
        poHjscspxx.setBscryid(poRyxx.getRyid());
        poHjscspxx.setCsyy(voHjscspdjxx[i].getHjsclb());
        poHjscspxx.setHjscsm(voHjscspdjxx[i].getHjscsm());//add hubin 20121030
        poHjscspxx.setSpywid(spywid);
        poHjscspxx.setSqyhid(this.getUserInfo().getYhid());
        poHjscspxx.setSqsj(now);
        poHjscspxx.setLsbz(HjConstant.LSBZ_WLS);
        poHjscspxx.setXydzid(poMblcxx != null ? poMblcxx.getDzid() : null);
        poHjscspxx.setSpmbid(poMblcxx != null ? poMblcxx.getSpmbid() : null);
        poHjscspxx.setSpjg(poMblcxx != null ? null : HjConstant.SPJG_TY);
        poHjscspxx.setSpsm(spsm);
        super.create(poHjscspxx);

        //保存审批附带材料
        if (voSpfdclxx != null && voSpfdclxx.length > 0) {
          for (int a = 0; a < voSpfdclxx.length; a++) {
            PoHJSP_HJSPFDCLB poSpfdclxx = new PoHJSP_HJSPFDCLB();
            poSpfdclxx.setSpclid( (Long) hjsp_hjspfdclbDAO.getId());
            poSpfdclxx.setSpywid(poHjscspxx.getSpywid());
            poSpfdclxx.setSplx(HjConstant.SPLX_HJSC);
            poSpfdclxx.setClbh(voSpfdclxx[a].getClbh());
            super.create(poSpfdclxx);
          }
        }

        //保存户籍审批流水信息
        Long splsid = null;
        if (poMblcxx == null) {
          splsid = this.saveHJSPLSXX(poHjscspxx.getSpywid(),
                                     HjConstant.SPLX_HJSC,
                                     null, HjConstant.CZJG_TY, null, now);
        }

        //审批模板当前使用数增加1
        this.incSPMBDQSYS(spmbid);

        //生成返回信息
        voHjscspdjfhxx[i] = new VoHjscspdjfhxx();
        voHjscspdjfhxx[i].setSpywid(poHjscspxx.getSpywid());
      } //for (int i = 0; i < voBggzxxEx.length; i++) {
      if(voHjscspdjxx!=null && voHjscspdjxx.length>=1 && voHjscspdjxx[0].getHzywid()!=null && !voHjscspdjxx[0].getHzywid().equals("")){
        	  autoInsertHzjsbs(voHjscspdjxx[0].getHzywid());
      }
      //////////////////////////////////////
      //生成业务返回信息
      voHjscspdjywfhxx = new VoHjscspdjywfhxx();
      voHjscspdjywfhxx.setVoHjscspdjfhxx(voHjscspdjfhxx);

      ////////////////////////////////////
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

    return voHjscspdjywfhxx;
  }

  /**
   * 户籍删除审批审批业务
   * @param voHjscspspxx - 户籍删除审批审批信息
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoHjscspspywfhxx processHjscspspyw(VoHjscspspxx voHjscspspxx[]) throws
      DAOException, ServiceException {
    VoHjscspspywfhxx voHjscspspywfhxx = null;
    VoHjscspspfhxx voHjscspspfhxx[] = null;
    String strHQL = null;
    String now = StringUtils.getServiceTime();

    //数据校验
    if (voHjscspspxx == null ||
        (voHjscspspxx != null && voHjscspspxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo  hjsp_hjscspsqbDAO = DAOFactory.createHJSP_HJSCSPSQBDAO();
      PojoInfo  xt_mblcxxbDAO = DAOFactory.createXT_MBLCXXBDAO();
      PojoInfo  hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();

      ////////////////////////////////////
      //事务开始

      /////////////////////////////////////
      //保存（处理）户籍删除审批审批信息
      voHjscspspfhxx = new VoHjscspspfhxx[voHjscspspxx.length];
      for (int i = 0; i < voHjscspspxx.length; i++) {

        //得到户籍补录审批信息
        PoHJSP_HJSCSPSQB poHjscspsqxx = super.
            get(PoHJSP_HJSCSPSQB.class,voHjscspspxx[i].getSpywid());
        if (poHjscspsqxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到对应的户籍删除审批信息,户籍删除审批审批业务无法完成.", null);
        }
        //动作校验
        if (! (poHjscspsqxx.getXydzid() != null && voHjscspspxx[i].getDzid() != null &&
               poHjscspsqxx.getXydzid().equals(voHjscspspxx[i].getDzid()))) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "动作ID出错(可能已审批),户籍删除审批审批业务无法完成.", null);
        }
        //审批用户校验
        strHQL = "select count(*) from PoXT_YHDZQXB where yhid=" +
            this.getUserInfo().getYhid() + " and dzid=" +
            poHjscspsqxx.getXydzid();
        if (super.getCount(strHQL) <= 0) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "该用户无权审批,户籍删除审批审批业务无法完成.", null);
        }
        //得到模板流程信息
        PoXT_MBLCXXB poMblcxx = null;
        strHQL = "from PoXT_MBLCXXB where spmbid=" + poHjscspsqxx.getSpmbid() +
            " and dzid=" + voHjscspspxx[i].getDzid() + " and (dzz='" +
            voHjscspspxx[i].getCzjg() + "' or dzbz='" + HjConstant.DZBZ_DYB +
            "') ";
        List mblcxxList =super.findAllByHQL(strHQL);
        if (mblcxxList != null && mblcxxList.size() > 0) {
          poMblcxx = (PoXT_MBLCXXB) mblcxxList.get(0);
        }
        if (poMblcxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到对应的模板流程信息,户籍删除审批审批业务无法完成.", null);
        }
        //保存户籍删除审批审批信息
        Long zqid = null;
        poHjscspsqxx.setXydzid(poMblcxx.getXgdzid());
        if (HjConstant.DZBZ_DYB.equals(poMblcxx.getDzbz()) ||
            HjConstant.DZBZ_JSB.equals(poMblcxx.getDzbz())) {
          poHjscspsqxx.setSpjg(voHjscspspxx[i].getCzjg());
          //审批模板当前使用数减少1
          this.decSPMBDQSYS(poHjscspsqxx.getSpmbid());
        }
       super.update(poHjscspsqxx);
        //保存户籍审批流水信息
        Long splsid = this.saveHJSPLSXX(poHjscspsqxx.getSpywid(),
                                        HjConstant.SPLX_HJSC,
                                        voHjscspspxx[i].getDzid(),
                                        voHjscspspxx[i].getCzjg(),
                                        voHjscspspxx[i].getCzyj(), now);

        //人员锁定状态解锁(处理审批没通过的情况)
        if (poHjscspsqxx.getSpjg() != null &&
            !HjConstant.SPJG_TY.equals(poHjscspsqxx.getSpjg())) {
          strHQL = "from PoHJXX_CZRKJBXXB where ryid=" +
              poHjscspsqxx.getBscryid() + " and jlbz='" +
              PublicConstant.JLBZ_ZX +
              "' and cxbz='" + PublicConstant.CXBZ_FCX + "' ";
          List ryxxList =super.findAllByHQL(strHQL);
          if (ryxxList != null && ryxxList.size() > 0) {
            PoHJXX_CZRKJBXXB poRyxx = (PoHJXX_CZRKJBXXB) ryxxList.get(0);
            //校验人信息的时效性
            this.checkRXX(poRyxx, hjxx_czrkjbxxbDAO, "户籍删除审批审批业务");
            //保存人信息
            poRyxx.setRysdzt(HjConstant.RYSDZT_ZC);
           super.update(poRyxx);
          }
        }

        //生成返回信息
        voHjscspspfhxx[i] = new VoHjscspspfhxx();
        voHjscspspfhxx[i].setSplsid(splsid);
        voHjscspspfhxx[i].setSpywid(poHjscspsqxx.getSpywid());
      } //for (int i = 0; i < voBgspspxx.length; i++) {

      ///////////////////////////////////////
      //生成业务返回信息
      voHjscspspywfhxx = new VoHjscspspywfhxx();
      voHjscspspywfhxx.setVoHjscspspfhxx(voHjscspspfhxx);

      ////////////////////////////////////
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

    return voHjscspspywfhxx;
  }

  /**
   * 户籍删除审批作废业务
   * @param voHjscspzfxx - 户籍删除审批作废信息
   * @return - 户籍删除审批作废业务返回信息
   * @throws DAOException
   * @throws ServiceException
   */
  public VoHjscspzfywfhxx processHjscspzfyw(VoHjscspzfxx voHjscspzfxx[]) throws
      DAOException, ServiceException {
    VoHjscspzfywfhxx voHjscspzfywfhxx = null;
    VoHjscspzffhxx voHjscspzffhxx[] = null;
    String strHQL = null;

    if (voHjscspzfxx == null ||
        (voHjscspzfxx != null && voHjscspzfxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo  hjsp_hjscspsqbDAO = DAOFactory.createHJSP_HJSCSPSQBDAO();
      PojoInfo  hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();

      //////////////////////////////////////////////
      //事务开始

      ////////////////////////////////////////////////
      //处理户籍删除审批作废信息
      voHjscspzffhxx = new VoHjscspzffhxx[voHjscspzfxx.length];
      for (int i = 0; i < voHjscspzfxx.length; i++) {
        //得到户籍删除审批信息
        PoHJSP_HJSCSPSQB poHjscspxx  = super.get(PoHJSP_HJSCSPSQB.class,
            voHjscspzfxx[i].getSpywid());
        if (poHjscspxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到对应的户籍删除审批信息,户籍删除审批作废业务无法完成.", null);
        }

        //将户籍删除审批信息的审批结果改成“无效”
        poHjscspxx.setSpjg(HjConstant.SPJG_WX);
       super.update(poHjscspxx);

        //人信息的人员锁定状态改成“正常”，解锁
        PoHJXX_CZRKJBXXB poRyxx = null;
        strHQL = "from PoHJXX_CZRKJBXXB where ryid=" + poHjscspxx.getBscryid() +
            " and jlbz='" + PublicConstant.JLBZ_ZX + "' and cxbz='" +
            PublicConstant.CXBZ_FCX + "' ";
        List ryxxList =super.findAllByHQL(strHQL);
        if (ryxxList != null && ryxxList.size() > 0) {
          poRyxx = (PoHJXX_CZRKJBXXB) ryxxList.get(0);
          //校验人信息的时效性
          this.checkRXX(poRyxx, hjxx_czrkjbxxbDAO, "户籍删除审批作废业务");
          //保存人信息
          poRyxx.setRysdzt(HjConstant.RYSDZT_ZC);
         super.update(poRyxx);
        }
        if (poRyxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到对应的人员信息,户籍删除审批作废业务无法完成.", null);
        }

        //生成返回信息
        voHjscspzffhxx[i] = new VoHjscspzffhxx();
        voHjscspzffhxx[i].setRynbid(poRyxx.getRynbid());
        voHjscspzffhxx[i].setRyid(poRyxx.getRyid());
        voHjscspzffhxx[i].setGmsfhm(poRyxx.getGmsfhm());
        voHjscspzffhxx[i].setXm(poRyxx.getXm());
        voHjscspzffhxx[i].setYhzgx(poRyxx.getYhzgx());
      }

      /////////////////////////////////////////
      //生成业务返回信息
      voHjscspzfywfhxx = new VoHjscspzfywfhxx();
      voHjscspzfywfhxx.setVoHjscspzffhxx(voHjscspzffhxx);

      //////////////////////////////////////////////
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

    return voHjscspzfywfhxx;
  }

  /**
   * 户籍删除审批信息获取
   * @param strHQL
   * @param voPage
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQueryResult queryHjscspxx(String strHQL, VoPage voPage) throws
      DAOException, ServiceException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append(
        "from PoHJSP_HJSCSPSQB as HJSP_HJSCSPSQB,PoHJXX_CZRKJBXXB as HJXX_CZRKJBXXB ")
        .append("where HJSP_HJSCSPSQB.bscryid=HJXX_CZRKJBXXB.ryid ")
        .append("and HJXX_CZRKJBXXB.jlbz='" + PublicConstant.JLBZ_ZX + "' ")
        .append("and HJXX_CZRKJBXXB.cxbz='" + PublicConstant.CXBZ_FCX + "' ");
    //数据范围begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_SP_HJSCSPXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //居(村)委会
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //派出所
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //行政区划
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.ssxq='" +
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
    strAllHQL.append(
        "select HJSP_HJSCSPSQB,HJXX_CZRKJBXXB ").
        append(strFromHQL.toString());

    //debug info
    //_log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo  hjsp_hjscspsqbDAO = DAOFactory.createHJSP_HJSCSPSQBDAO();


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
        Object ob[] = (Object[]) poList.get(i);
        PoHJSP_HJSCSPSQB poHjscspxx = (PoHJSP_HJSCSPSQB) ob[0];
        PoHJXX_CZRKJBXXB poRyxx = (PoHJXX_CZRKJBXXB) ob[1];
        PoXT_SPDZB poSpdzxx =null;
        if(poHjscspxx.getXydzid()!=null && poHjscspxx.getXydzid().longValue()!=0){
        	poSpdzxx = super.get(PoXT_SPDZB.class, poHjscspxx.getXydzid());
        }
        
        VoHjscspxxHqFhxx voHjscspxxHqFhxx = new VoHjscspxxHqFhxx(poHjscspxx,
            poRyxx, poSpdzxx);

        voList.add(voHjscspxxHqFhxx);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoHjscspxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * 获取登录用户能审批的户籍删除审批信息
   * @param strHQL
   * @param voPage
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQueryResult queryHjscspxxByUser(String strHQL, VoPage voPage) throws
      DAOException, ServiceException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append("from PoHJSP_HJSCSPSQB as HJSP_HJSCSPSQB,PoHJXX_CZRKJBXXB as HJXX_CZRKJBXXB,PoXT_SPDZB as XT_SPDZB,PoXT_YHDZQXB as XT_YHDZQXB ")
        .append("where XT_SPDZB.dzid=HJSP_HJSCSPSQB.xydzid ")
        .append("and XT_YHDZQXB.dzid=HJSP_HJSCSPSQB.xydzid ")
        .append("and HJSP_HJSCSPSQB.bscryid=HJXX_CZRKJBXXB.ryid ")
        .append("and HJXX_CZRKJBXXB.jlbz='" + PublicConstant.JLBZ_ZX + "' ")
        .append("and HJXX_CZRKJBXXB.cxbz='" + PublicConstant.CXBZ_FCX + "' ")
        .append("and XT_YHDZQXB.yhid=" + this.getUserInfo().getYhid() + " ");
    //数据范围begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_SP_YH_HJSCSPXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //居(村)委会
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //派出所
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //行政区划
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.ssxq='" +
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
    strAllHQL.append(
        "select HJSP_HJSCSPSQB,HJXX_CZRKJBXXB,XT_SPDZB ").
        append(strFromHQL.toString());

    //debug info
    //_log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo  hjsp_hjscspsqbDAO = DAOFactory.createHJSP_HJSCSPSQBDAO();


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
        Object ob[] = (Object[]) poList.get(i);
        PoHJSP_HJSCSPSQB poHjscspxx = (PoHJSP_HJSCSPSQB) ob[0];
        PoHJXX_CZRKJBXXB poRyxx = (PoHJXX_CZRKJBXXB) ob[1];
        PoXT_SPDZB poSpdzxx = (PoXT_SPDZB) ob[2];

        VoHjscspxxHqFhxx voHjscspxxHqFhxx = new VoHjscspxxHqFhxx(poHjscspxx,
            poRyxx, poSpdzxx);

        voList.add(voHjscspxxHqFhxx);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoHjscspxxHqFhxx.class);
    return voQueryResult;
  }

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
      DAOException, ServiceException {
    VoHbbgspdjywfhxx voHbbgspdjywfhxx = null;
    VoHbbgspdjfhxx voHbbgspdjfhxx[] = null;
    String strHQL = null;
    String now = StringUtils.getServiceTime();

    /////////////////////////////////////////
    //数据校验
    if (voHbbgspdjxx == null ||
        (voHbbgspdjxx != null && voHbbgspdjxx.length == 0)) {
      return null;
    }

    try {
      PojoInfo  hjsp_hjsplsbDAO = DAOFactory.createHJSP_HJSPLSBDAO();
      PojoInfo  hjsp_hjspfdclbDAO = DAOFactory.createHJSP_HJSPFDCLBDAO();
      PojoInfo  xt_mblcxxbDAO = DAOFactory.createXT_MBLCXXBDAO();
      PojoInfo  hjsp_HbbgspsqbdAO = DAOFactory.createHJSP_HBBGSPSQBDAO();
      PojoInfo  hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();

      /////////////////////////////
      //事务开始

      ///////////////////////////////
      //保存变更审批登记信息
      voHbbgspdjfhxx = new VoHbbgspdjfhxx[voHbbgspdjxx.length];
      for (int i = 0; i < voHbbgspdjxx.length; i++) {
        //得到人员信息
        PoHJXX_CZRKJBXXB poRyxx = null;
        strHQL = "from PoHJXX_CZRKJBXXB where rynbid=" +
            voHbbgspdjxx[i].getRynbid() + " and jlbz='" +
            PublicConstant.JLBZ_ZX + "' and cxbz='" + PublicConstant.CXBZ_FCX +
            "' ";
        List ryxxList =super.findAllByHQL(strHQL);
        if (ryxxList != null && ryxxList.size() > 0) {
          poRyxx = (PoHJXX_CZRKJBXXB) ryxxList.get(0);
        }
        if (poRyxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到对应的人员信息,户别变更审批登记业务无法完成.", null);
        }
        //校验人信息的时效性
        this.checkRXX(poRyxx, hjxx_czrkjbxxbDAO, "户别变更审批登记业务");
        //人信息加锁
        poRyxx.setRysdzt(HjConstant.RYSDZT_HBBGSP);
       super.update(poRyxx);
        //得到审批流程信息
        PoXT_MBLCXXB poMblcxx = null;
        if (spmbid != null) {
          strHQL = "from PoXT_MBLCXXB where (dzbz='" + HjConstant.DZBZ_DYB +
              "' or dzbz='" + HjConstant.DZBZ_QSB + "') and spmbid=" + spmbid;
          List mblcxxList =super.findAllByHQL(strHQL);
          if (mblcxxList != null && mblcxxList.size() > 0) {
            poMblcxx = (PoXT_MBLCXXB) mblcxxList.get(0);
          }
          if (poMblcxx == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "找不到对应的人审批流程信息,变更审批登记业务无法完成.", null);
          }
        }

        //保存户别变更审批信息
        PoHJSP_HBBGSPSQB poHbbgspxx = new PoHJSP_HBBGSPSQB();
        BeanUtils.copyProperties(poHbbgspxx, voHbbgspdjxx[i]);
        poHbbgspxx.setBgryid(poRyxx.getRyid());
        poHbbgspxx.setBgyy(voHbbgspdjxx[i].getHbbglb());
        poHbbgspxx.setSpywid( (Long) hjsp_HbbgspsqbdAO.getId());
        poHbbgspxx.setBgqhb(poRyxx.getHb());
        poHbbgspxx.setDjrid(this.getUserInfo().getYhid());
        poHbbgspxx.setLsbz(HjConstant.LSBZ_WLS);
        poHbbgspxx.setXydzid(poMblcxx != null ? poMblcxx.getDzid() : null);
        poHbbgspxx.setSpmbid(poMblcxx != null ? poMblcxx.getSpmbid() : null);
        poHbbgspxx.setSpjg(poMblcxx != null ? null : HjConstant.SPJG_TY);
        poHbbgspxx.setSpsm(spsm);
        super.create(poHbbgspxx);

        //保存审批附带材料
        if (voSpfdclxx != null && voSpfdclxx.length > 0) {
          for (int a = 0; a < voSpfdclxx.length; a++) {
            PoHJSP_HJSPFDCLB poSpfdclxx = new PoHJSP_HJSPFDCLB();
            poSpfdclxx.setSpclid( (Long) hjsp_hjspfdclbDAO.getId());
            poSpfdclxx.setSpywid(poHbbgspxx.getSpywid());
            poSpfdclxx.setSplx(HjConstant.SPLX_HBBG);
            poSpfdclxx.setClbh(voSpfdclxx[a].getClbh());
            super.create(poSpfdclxx);
          }
        }

        //保存户籍审批流水信息
        Long splsid = null;
        if (poMblcxx == null) {
          splsid = this.saveHJSPLSXX(poHbbgspxx.getSpywid(),
                                     HjConstant.SPLX_HBBG,
                                     null, HjConstant.CZJG_TY, null, now);
        }

        //审批模板当前使用数增加1
        this.incSPMBDQSYS(spmbid);

        //生成返回信息
        voHbbgspdjfhxx[i] = new VoHbbgspdjfhxx();
        voHbbgspdjfhxx[i].setSpywid(poHbbgspxx.getSpywid());
      } //for (int i = 0; i < voBggzxxEx.length; i++) {

      //////////////////////////////////////
      //生成业务返回信息
      voHbbgspdjywfhxx = new VoHbbgspdjywfhxx();
      voHbbgspdjywfhxx.setVoHbbgspdjfhxx(voHbbgspdjfhxx);

      ////////////////////////////////////
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

    return voHbbgspdjywfhxx;
  }

  /**
   * 户别变更审批审批业务
   * @param voHbbgspspxx - 户别变更审批审批信息
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoHbbgspspywfhxx processHbbgspspyw(VoHbbgspspxx voHbbgspspxx[]) throws
      DAOException, ServiceException {
    VoHbbgspspywfhxx voHbbgspspywfhxx = null;
    VoHbbgspspfhxx voHbbgspspfhxx[] = null;
    String strHQL = null;
    String now = StringUtils.getServiceTime();

    //数据校验
    if (voHbbgspspxx == null ||
        (voHbbgspspxx != null && voHbbgspspxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo  hjsp_HbbgspsqbDAO = DAOFactory.createHJSP_HBBGSPSQBDAO();
      PojoInfo  xt_mblcxxbDAO = DAOFactory.createXT_MBLCXXBDAO();
      PojoInfo  hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();

      ////////////////////////////////////
      //事务开始

      /////////////////////////////////////
      //保存（处理）户别变更审批审批信息
      voHbbgspspfhxx = new VoHbbgspspfhxx[voHbbgspspxx.length];
      for (int i = 0; i < voHbbgspspxx.length; i++) {

        //得到户别变更审批信息
        PoHJSP_HBBGSPSQB poHbbgspsqxx = super.
            get(PoHJSP_HBBGSPSQB.class,voHbbgspspxx[i].getSpywid());
        if (poHbbgspsqxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到对应的户别变更审批信息,户别变更审批审批业务无法完成.", null);
        }
        //动作校验
        if (! (poHbbgspsqxx.getXydzid() != null && voHbbgspspxx[i].getDzid() != null &&
               poHbbgspsqxx.getXydzid().equals(voHbbgspspxx[i].getDzid()))) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "动作ID出错(可能已审批),户别变更审批审批业务无法完成.", null);
        }
        //审批用户校验
        strHQL = "select count(*) from PoXT_YHDZQXB where yhid=" +
            this.getUserInfo().getYhid() + " and dzid=" +
            poHbbgspsqxx.getXydzid();
        if (super.getCount(strHQL) <= 0) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "该用户无权审批,户别变更审批审批业务无法完成.", null);
        }
        //得到模板流程信息
        PoXT_MBLCXXB poMblcxx = null;
        strHQL = "from PoXT_MBLCXXB where spmbid=" + poHbbgspsqxx.getSpmbid() +
            " and dzid=" + voHbbgspspxx[i].getDzid() + " and (dzz='" +
            voHbbgspspxx[i].getCzjg() + "' or dzbz='" + HjConstant.DZBZ_DYB +
            "') ";
        List mblcxxList =super.findAllByHQL(strHQL);
        if (mblcxxList != null && mblcxxList.size() > 0) {
          poMblcxx = (PoXT_MBLCXXB) mblcxxList.get(0);
        }
        if (poMblcxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到对应的模板流程信息,户别变更审批审批业务无法完成.", null);
        }
        //保存户别变更审批审批信息
        Long zqid = null;
        poHbbgspsqxx.setXydzid(poMblcxx.getXgdzid());
        if (HjConstant.DZBZ_DYB.equals(poMblcxx.getDzbz()) ||
            HjConstant.DZBZ_JSB.equals(poMblcxx.getDzbz())) {
          poHbbgspsqxx.setSpjg(voHbbgspspxx[i].getCzjg());
          //审批模板当前使用数减少1
          this.decSPMBDQSYS(poHbbgspsqxx.getSpmbid());
        }
       super.update(poHbbgspsqxx);
        //保存户籍审批流水信息
        Long splsid = this.saveHJSPLSXX(poHbbgspsqxx.getSpywid(),
                                        HjConstant.SPLX_HBBG,
                                        voHbbgspspxx[i].getDzid(),
                                        voHbbgspspxx[i].getCzjg(),
                                        voHbbgspspxx[i].getCzyj(), now);

        //人员锁定状态解锁(处理审批没通过的情况)
        if (poHbbgspsqxx.getSpjg() != null &&
            !HjConstant.SPJG_TY.equals(poHbbgspsqxx.getSpjg())) {
          strHQL = "from PoHJXX_CZRKJBXXB where ryid=" + poHbbgspsqxx.getBgryid() +
              " and jlbz='" + PublicConstant.JLBZ_ZX + "' and cxbz='" +
              PublicConstant.CXBZ_FCX + "' ";
          List ryxxList =super.findAllByHQL(strHQL);
          if (ryxxList != null && ryxxList.size() > 0) {
            PoHJXX_CZRKJBXXB poRyxx = (PoHJXX_CZRKJBXXB) ryxxList.get(0);
            //校验人信息的时效性
            this.checkRXX(poRyxx, hjxx_czrkjbxxbDAO, "户别变更审批审批业务");
            //保存人信息
            poRyxx.setRysdzt(HjConstant.RYSDZT_ZC);
           super.update(poRyxx);
          }
        }

        //生成返回信息
        voHbbgspspfhxx[i] = new VoHbbgspspfhxx();
        voHbbgspspfhxx[i].setSplsid(splsid);
        voHbbgspspfhxx[i].setSpywid(poHbbgspsqxx.getSpywid());
      } //for (int i = 0; i < voBgspspxx.length; i++) {

      ///////////////////////////////////////
      //生成业务返回信息
      voHbbgspspywfhxx = new VoHbbgspspywfhxx();
      voHbbgspspywfhxx.setVoHbbgspspfhxx(voHbbgspspfhxx);

      ////////////////////////////////////
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

    return voHbbgspspywfhxx;
  }

  /**
   * 户别变更审批作废业务
   * @param voHbbgspzfxx - 户别变更审批作废信息
   * @return - 户别变更审批作废业务返回信息
   * @throws DAOException
   * @throws ServiceException
   */
  public VoHbbgspzfywfhxx processHbbgspzfyw(VoHbbgspzfxx voHbbgspzfxx[]) throws
      DAOException, ServiceException {
    VoHbbgspzfywfhxx voHbbgspzfywfhxx = null;
    VoHbbgspzffhxx voHbbgspzffhxx[] = null;
    String strHQL = null;

    if (voHbbgspzfxx == null ||
        (voHbbgspzfxx != null && voHbbgspzfxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo  hjsp_hbbgspsqbDAO = DAOFactory.createHJSP_HBBGSPSQBDAO();
      PojoInfo  hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();

      //////////////////////////////////////////////
      //事务开始

      ////////////////////////////////////////////////
      //处理户别变更审批作废信息
      voHbbgspzffhxx = new VoHbbgspzffhxx[voHbbgspzfxx.length];
      for (int i = 0; i < voHbbgspzfxx.length; i++) {
        //得到户别变更审批信息
        PoHJSP_HBBGSPSQB poHbbgspxx  = super.get(PoHJSP_HBBGSPSQB.class,
            voHbbgspzfxx[i].getSpywid());
        if (poHbbgspxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到对应的户别变更审批信息,户别变更审批作废业务无法完成.", null);
        }

        //将户别变更审批信息的审批结果改成“无效”
        poHbbgspxx.setSpjg(HjConstant.SPJG_WX);
       super.update(poHbbgspxx);

        //人信息的人员锁定状态改成“正常”，解锁
        PoHJXX_CZRKJBXXB poRyxx = null;
        strHQL = "from PoHJXX_CZRKJBXXB where ryid=" + poHbbgspxx.getBgryid() +
            " and jlbz='" + PublicConstant.JLBZ_ZX + "' and cxbz='" +
            PublicConstant.CXBZ_FCX + "' ";
        List ryxxList =super.findAllByHQL(strHQL);
        if (ryxxList != null && ryxxList.size() > 0) {
          poRyxx = (PoHJXX_CZRKJBXXB) ryxxList.get(0);
          //校验人信息的时效性
          this.checkRXX(poRyxx, hjxx_czrkjbxxbDAO, "户别变更审批作废业务");
          //保存人信息
          poRyxx.setRysdzt(HjConstant.RYSDZT_ZC);
         super.update(poRyxx);
        }
        if (poRyxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "找不到对应的人员信息,户别变更审批作废业务无法完成.", null);
        }

        //生成返回信息
        voHbbgspzffhxx[i] = new VoHbbgspzffhxx();
        voHbbgspzffhxx[i].setRynbid(poRyxx.getRynbid());
        voHbbgspzffhxx[i].setRyid(poRyxx.getRyid());
        voHbbgspzffhxx[i].setGmsfhm(poRyxx.getGmsfhm());
        voHbbgspzffhxx[i].setXm(poRyxx.getXm());
        voHbbgspzffhxx[i].setYhzgx(poRyxx.getYhzgx());
      }

      /////////////////////////////////////////
      //生成业务返回信息
      voHbbgspzfywfhxx = new VoHbbgspzfywfhxx();
      voHbbgspzfywfhxx.setVoHbbgspzffhxx(voHbbgspzffhxx);

      //////////////////////////////////////////////
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

    return voHbbgspzfywfhxx;
  }

  /**
   * 户别变更审批信息获取
   * @param strHQL
   * @param voPage
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQueryResult queryHbbgspxx(String strHQL, VoPage voPage) throws
      DAOException, ServiceException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append(
        "from PoHJSP_HBBGSPSQB as HJSP_HBBGSPSQB,PoHJXX_CZRKJBXXB as HJXX_CZRKJBXXB ")
        .append("where HJSP_HBBGSPSQB.bgryid=HJXX_CZRKJBXXB.ryid ")
        .append("and HJXX_CZRKJBXXB.jlbz='" + PublicConstant.JLBZ_ZX + "' ")
        .append("and HJXX_CZRKJBXXB.cxbz='" + PublicConstant.CXBZ_FCX + "' ");
    //数据范围begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_SP_HBBGSPXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //居(村)委会
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //派出所
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //行政区划
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.ssxq='" +
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
    strAllHQL.append(
        "select HJSP_HBBGSPSQB,HJXX_CZRKJBXXB ").
        append(strFromHQL.toString());

    //debug info
    //_log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo  hjsp_hbbgspsqbDAO = DAOFactory.createHJSP_HBBGSPSQBDAO();


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
        Object ob[] = (Object[]) poList.get(i);
        PoHJSP_HBBGSPSQB poHbbgspxx = (PoHJSP_HBBGSPSQB) ob[0];
        PoHJXX_CZRKJBXXB poRyxx = (PoHJXX_CZRKJBXXB) ob[1];
        
        PoXT_SPDZB poSpdzxx = null;
        if(poHbbgspxx.getXydzid()!=null && poHbbgspxx.getXydzid().longValue()!=0){
        	poSpdzxx = super.get(PoXT_SPDZB.class, poHbbgspxx.getXydzid());
        }
        
        VoHbbgspxxHqFhxx voHbbgspxxHqFhxx = new VoHbbgspxxHqFhxx(poHbbgspxx,
                poRyxx, poSpdzxx);
            
        
        voList.add(voHbbgspxxHqFhxx);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoHbbgspxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * 获取登录用户能审批的户别变更审批信息
   * @param strHQL
   * @param voPage
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQueryResult queryHbbgspxxByUser(String strHQL, VoPage voPage) throws
      DAOException, ServiceException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append("from PoHJSP_HBBGSPSQB as HJSP_HBBGSPSQB,PoHJXX_CZRKJBXXB as HJXX_CZRKJBXXB,PoXT_SPDZB as XT_SPDZB,PoXT_YHDZQXB as XT_YHDZQXB ")
        .append("where XT_SPDZB.dzid=HJSP_HBBGSPSQB.xydzid ")
        .append("and XT_YHDZQXB.dzid=HJSP_HBBGSPSQB.xydzid ")
        .append("and HJSP_HBBGSPSQB.bgryid=HJXX_CZRKJBXXB.ryid ")
        .append("and HJXX_CZRKJBXXB.jlbz='" + PublicConstant.JLBZ_ZX + "' ")
        .append("and HJXX_CZRKJBXXB.cxbz='" + PublicConstant.CXBZ_FCX + "' ")
        .append("and XT_YHDZQXB.yhid=" + this.getUserInfo().getYhid() + " ");
    //数据范围begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_SP_YH_HBBGSPXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //居(村)委会
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //派出所
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //行政区划
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.ssxq='" +
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
    strAllHQL.append(
        "select HJSP_HBBGSPSQB,HJXX_CZRKJBXXB,XT_SPDZB ").
        append(strFromHQL.toString());

    //debug info
    //_log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo  hjsp_hjscspsqbDAO = DAOFactory.createHJSP_HJSCSPSQBDAO();


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
        Object ob[] = (Object[]) poList.get(i);
        PoHJSP_HBBGSPSQB poHbbgspxx = (PoHJSP_HBBGSPSQB) ob[0];
        PoHJXX_CZRKJBXXB poRyxx = (PoHJXX_CZRKJBXXB) ob[1];
        PoXT_SPDZB poSpdzxx = (PoXT_SPDZB) ob[2];

        VoHbbgspxxHqFhxx voHbbgspxxHqFhxx = new VoHbbgspxxHqFhxx(poHbbgspxx,
            poRyxx, poSpdzxx);

        voList.add(voHbbgspxxHqFhxx);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoHbbgspxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * 户籍删除审批审批信息获取
   * @param strHQL
   * @param voPage
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQueryResult queryHjscspspxx(String strHQL, VoPage voPage) throws
      DAOException, ServiceException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append("from PoHJSP_HJSCSPSQB as HJSP_HJSCSPSQB,PoHJXX_CZRKJBXXB as HJXX_CZRKJBXXB,PoHJSP_HJSPLSB as HJSP_HJSPLSB,PoXT_SPDZB as XYDZ_XT_SPDZB,PoXT_SPDZB as XT_SPDZB ")
        .append("where HJSP_HJSCSPSQB.spywid=HJSP_HJSPLSB.spywid ")
        .append("and HJSP_HJSPLSB.splx='" + HjConstant.SPLX_HJSC + "' ")
        .append("and HJSP_HJSCSPSQB.xydzid=XYDZ_XT_SPDZB.dzid(+) ")
        .append("and HJSP_HJSPLSB.dzid=XT_SPDZB.dzid(+) ")
        .append("and HJSP_HJSCSPSQB.bscryid=HJXX_CZRKJBXXB.ryid ")
        .append("and HJXX_CZRKJBXXB.jlbz='" + PublicConstant.JLBZ_ZX + "' ")
        .append("and HJXX_CZRKJBXXB.cxbz='" + PublicConstant.CXBZ_FCX + "' ");
    //数据范围begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_SP_HJSCSPSPXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //居(村)委会
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //派出所
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //行政区划
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.ssxq='" +
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
    if (voPage.getRecordcount() <= 0) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //生成SELECT HQL语句
    strAllHQL.append(
        "select HJSP_HJSCSPSQB,HJXX_CZRKJBXXB,HJSP_HJSPLSB,XYDZ_XT_SPDZB,XT_SPDZB ").
        append(strFromHQL.toString());

    //debug info
    //_log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo  hjsp_hjscspsqbDAO = DAOFactory.createHJSP_HJSCSPSQBDAO();


      if (voPage.getRecordcount() <= 0) {
        voQueryResult.setRecordcount(super.getCount(
            strCountHQL.
            toString()));
        //voQueryResult.setRecordcount(1000);
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
        Object ob[] = (Object[]) poList.get(i);
        PoHJSP_HJSCSPSQB poHjscspsqxx = (PoHJSP_HJSCSPSQB) ob[0];
        PoHJXX_CZRKJBXXB poRyxx = (PoHJXX_CZRKJBXXB) ob[1];
        PoHJSP_HJSPLSB poHjsplsxx = (PoHJSP_HJSPLSB) ob[2];
        PoXT_SPDZB poXyspdzxx = (PoXT_SPDZB) ob[3];
        PoXT_SPDZB poSpdzxx = (PoXT_SPDZB) ob[4];

        //只返回最后一次户籍删除审批审批记录
        boolean bfind = false;
        for (int a = 0; a < voList.size(); a++) {
          VoHjscspspxxHqFhxx vo = (VoHjscspspxxHqFhxx) voList.get(a);
          if (poHjsplsxx.getSpywid() != null &&
              poHjsplsxx.getSpywid().equals(vo.getSpywid())) {
            if (poHjsplsxx.getCzsj() != null &&
                poHjsplsxx.getCzsj().compareTo(vo.getCzsj()) > 0) {
              voList.remove(a);
            }
            else {
              bfind = true;
            }
            break;
          }
        } //for(int a=0;a<voList.size();a++){
        if (!bfind) {
          VoHjscspspxxHqFhxx voHjscspspxxHqFhxx = new VoHjscspspxxHqFhxx(
              poHjscspsqxx, poRyxx, poHjsplsxx, poSpdzxx, poXyspdzxx);
          voList.add(voHjscspspxxHqFhxx);
        } //if(!bfind){
      } //for (int i = 0; i < poList.size(); i++) {
    } //if (poList != null && poList.size() > 0) {

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoHjscspspxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * 户籍补录审批审批信息获取
   * @param strHQL
   * @param voPage
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQueryResult queryHjblspspxx(String strHQL, VoPage voPage) throws
      DAOException, ServiceException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append("from PoHJSP_HJBLSPSQB as HJSP_HJBLSPSQB,PoHJSP_HJSPLSB as HJSP_HJSPLSB,PoXT_SPDZB as XYDZ_XT_SPDZB,PoXT_SPDZB as XT_SPDZB ")
        .append("where HJSP_HJBLSPSQB.spywid=HJSP_HJSPLSB.spywid ")
        .append("and  HJSP_HJSPLSB.splx='" + HjConstant.SPLX_HJBL + "' ")
        .append("and HJSP_HJBLSPSQB.xydzid=XYDZ_XT_SPDZB.dzid(+) ")
        .append("and HJSP_HJSPLSB.dzid=XT_SPDZB.dzid(+) ");
    //数据范围begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_SP_HJBLSPSPXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //居(村)委会
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJSP_HJBLSPSQB.ssjwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJSP_HJBLSPSQB.ssjwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //派出所
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJSP_HJBLSPSQB.sspcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJSP_HJBLSPSQB.sspcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //行政区划
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJSP_HJBLSPSQB.ssssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJSP_HJBLSPSQB.ssssxq='" +
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
    strAllHQL.append(
        "select HJSP_HJBLSPSQB,HJSP_HJSPLSB,XYDZ_XT_SPDZB,XT_SPDZB ").
        append(strFromHQL.toString());

    //debug info
    //_log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo  hjsp_hjblspsqbDAO = DAOFactory.createHJSP_HJBLSPSQBDAO();


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
        Object ob[] = (Object[]) poList.get(i);
        PoHJSP_HJBLSPSQB poHjblspsqxx = (PoHJSP_HJBLSPSQB) ob[0];
        PoHJSP_HJSPLSB poHjsplsxx = (PoHJSP_HJSPLSB) ob[1];
        PoXT_SPDZB poXyspdzxx = (PoXT_SPDZB) ob[2];
        PoXT_SPDZB poSpdzxx = (PoXT_SPDZB) ob[3];

        //只返回最后一次户籍补录审批审批记录
        boolean bfind = false;
        for (int a = 0; a < voList.size(); a++) {
          VoHjblspspxxHqFhxx vo = (VoHjblspspxxHqFhxx) voList.get(a);
          if (poHjsplsxx.getSpywid() != null &&
              poHjsplsxx.getSpywid().equals(vo.getSpywid())) {
            if (poHjsplsxx.getCzsj() != null &&
                poHjsplsxx.getCzsj().compareTo(vo.getCzsj()) > 0) {
              voList.remove(a);
            }
            else {
              bfind = true;
            }
            break;
          }
        } //for(int a=0;a<voList.size();a++){
        if (!bfind) {
          VoHjblspspxxHqFhxx voHjblspspxxHqFhxx = new VoHjblspspxxHqFhxx(
              poHjblspsqxx,
              poHjsplsxx, poSpdzxx, poXyspdzxx);
          voList.add(voHjblspspxxHqFhxx);
        } //if(!bfind){
      } //for (int i = 0; i < poList.size(); i++) {
    } //if (poList != null && poList.size() > 0) {

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoHjblspspxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * 户别变更审批审批信息获取
   * @param strHQL
   * @param voPage
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public VoQueryResult queryHbbgspspxx(String strHQL, VoPage voPage) throws
      DAOException, ServiceException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append("from PoHJSP_HBBGSPSQB as HJSP_HBBGSPSQB,PoHJXX_CZRKJBXXB as HJXX_CZRKJBXXB,PoHJSP_HJSPLSB as HJSP_HJSPLSB,PoXT_SPDZB as XYDZ_XT_SPDZB,PoXT_SPDZB as XT_SPDZB ")
        .append("where HJSP_HBBGSPSQB.spywid=HJSP_HJSPLSB.spywid ")
        .append("and HJSP_HJSPLSB.splx='" + HjConstant.SPLX_HBBG + "' ")
        .append("and HJSP_HBBGSPSQB.xydzid=XYDZ_XT_SPDZB.dzid(+) ")
        .append("and HJSP_HJSPLSB.dzid=XT_SPDZB.dzid(+) ")
        .append("and HJSP_HBBGSPSQB.bgryid=HJXX_CZRKJBXXB.ryid ")
        .append("and HJXX_CZRKJBXXB.jlbz='" + PublicConstant.JLBZ_ZX + "' ")
        .append("and HJXX_CZRKJBXXB.cxbz='" + PublicConstant.CXBZ_FCX + "' ");
    //数据范围begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_SP_HBBGSPSPXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //居(村)委会
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //派出所
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //行政区划
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.ssxq='" +
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
    strAllHQL.append(
        "select HJSP_HBBGSPSQB,HJXX_CZRKJBXXB,HJSP_HJSPLSB,XYDZ_XT_SPDZB,XT_SPDZB ").
        append(strFromHQL.toString());

    //debug info
    //_log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo  hjsp_hbbgspsqbDAO = DAOFactory.createHJSP_HBBGSPSQBDAO();


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
        Object ob[] = (Object[]) poList.get(i);
        PoHJSP_HBBGSPSQB poHbbgspsqxx = (PoHJSP_HBBGSPSQB) ob[0];
        PoHJXX_CZRKJBXXB poRyxx = (PoHJXX_CZRKJBXXB) ob[1];
        PoHJSP_HJSPLSB poHjsplsxx = (PoHJSP_HJSPLSB) ob[2];
        PoXT_SPDZB poXyspdzxx = (PoXT_SPDZB) ob[3];
        PoXT_SPDZB poSpdzxx = (PoXT_SPDZB) ob[4];

        //只返回最后一次户别变更审批审批记录
        boolean bfind = false;
        for (int a = 0; a < voList.size(); a++) {
          VoHbbgspspxxHqFhxx vo = (VoHbbgspspxxHqFhxx) voList.get(a);
          if (poHjsplsxx.getSpywid() != null &&
              poHjsplsxx.getSpywid().equals(vo.getSpywid())) {
            if (poHjsplsxx.getCzsj() != null &&
                poHjsplsxx.getCzsj().compareTo(vo.getCzsj()) > 0) {
              voList.remove(a);
            }
            else {
              bfind = true;
            }
            break;
          }
        } //for(int a=0;a<voList.size();a++){
        if (!bfind) {
          VoHbbgspspxxHqFhxx voHbbgspspxxHqFhxx = new VoHbbgspspxxHqFhxx(
              poHbbgspsqxx, poRyxx, poHjsplsxx, poSpdzxx, poXyspdzxx);
          voList.add(voHbbgspspxxHqFhxx);
        } //if(!bfind){
      } //for (int i = 0; i < poList.size(); i++) {
    } //if (poList != null && poList.size() > 0) {

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoHbbgspspxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * 查询跨地区审批业务
   * @param hsql String
   * @return List
   */
  public List findKDQSpywList(){
    StringBuffer strAllHQL = new StringBuffer();
    //生成FROM HQL语句
    strAllHQL.append("from PoHJSP_HJSPSQB where kdqqy_fkzt='0' and kdqqy_qcdqbm is not null and spjg='1'");

    //执行DAO查询
    try {
      PojoInfo  hjsp_hjblspsqbDAO = DAOFactory.createHJSP_HJBLSPSQBDAO();
      List list = super.findEntities(strAllHQL.toString());
      return list;
    }catch (DAOException ex) {
      throw ex;
    }
  }

  /**
   * 保存跨地区审批业务到迁出地，一次同步一笔业务（一个批次）
   * @param sp PoHJSP_HJSPSQB
   */
  public void postKDQSpyw(PoHJSP_HJSPSQB sp){
    try {
      PojoInfo  hjsp_hjblspsqbDAO = DAOFactory.createHJSP_HJSPSQBDAO();
      PoHJSP_HJSPSQB e  = super.get(PoHJSP_HJSPSQB.class,sp.getSpywid());
      if(e!=null && e.getKdqqy_fkzt().equals("0")){
        //只有为0的需要同步到异地
        String qyldyy = null;
        String qczxlb = null;

        String hql = "from PoHJSP_HJSPZB where spywid=" + e.getSpywid();
        List list = super.findEntities(hql);
        String sfzh = "",xm="",zqzbh="";
        for(int i=0;i<list.size();i++){
          PoHJSP_HJSPZB zb = (PoHJSP_HJSPZB)list.get(i);
          sfzh += "," + zb.getGmsfhm();
          xm += "," + zb.getXm();
          zqzbh += "," + zb.getZjbh();

          qyldyy = zb.getKdqqy_qyldyy();
          qczxlb = zb.getKdqqy_qclb();
        }

        String qrdz = e.getQrdz();
        hql = "from PoHJSP_ZQZXXB where spywid=" + e.getSpywid();
        List list2 = super.findEntities(hql);
        if(list2!=null && list2.size()>0){
          PoHJSP_ZQZXXB z = (PoHJSP_ZQZXXB)list2.get(0);
          if(z.getQrdxz()!=null){
            qrdz = z.getQrdxz();
          }
        }

        //POST到远程更新
        KDSActionProxy.postKdqSpyw(e, sfzh, xm, zqzbh, qrdz, qyldyy, qczxlb);

        //更新本地
        e.setKdqqy_fkzt("1");
        e.setKdqqy_fksj(StringUtils.formateDateTime());
       super.update(e);

        for(int i=0;i<list.size();i++){
          PoHJSP_HJSPZB zb = (PoHJSP_HJSPZB)list.get(i);
          zb.setKdqqy_fkzt(e.getKdqqy_fkzt());
          zb.setKdqqy_fksj(e.getKdqqy_fksj());
         super.update(zb);
        }
      }

    }
    catch (DAOException ex) {
      ex.printStackTrace();
      throw ex;
    }
  }

  public void syncKdqSpyw(Map params, SimpleJson simpleJson){
    try {
      PojoInfo  hjsp_hjblspsqbDAO = DAOFactory.createHJSP_HJSPSQBDAO();
      String qcsfzh = (String)params.get("qcsfzh");
      String qcxm = (String)params.get("qcxm");
      String dqbm = (String)params.get("dqbm");
      String sqrxm = (String)params.get("sqrxm");
      String sqrsfz = (String)params.get("sqrsfz");
      String czdw = (String)params.get("czdw");
      String czyh = (String)params.get("czyh");
      String ywid = (String)params.get("ywid");
      String ywlx = (String)params.get("ywlx");
      String zqzbh = (String)params.get("zqzbh");
      String qrdz = (String)params.get("qrdz");
      String qrdqh = (String)params.get("qrdqh");
      String qyldyy = (String)params.get("qyldyy");
      String qczxlb = (String)params.get("qczxlb");

      if(qcsfzh!=null && qcxm!=null){
        String[] sfzhs = qcsfzh.split(",");
        String[] xms = qcxm.split(",");
        String[] zqzbhs = zqzbh.split(",");

        String pch = ywid;

        if(sfzhs.length == xms.length){
          for(int i=0;i<sfzhs.length;i++){
            String sfzh = sfzhs[i];
            String xm = xms[i];
            String zqz = zqzbhs[i];

            if(sfzh==null || sfzh.equals(""))
              continue;

            //查询重复，群集下多应用服务器的重复发送问题
            String existsSQL = "from PoHZ_ZJ_SB where pch='" + pch + "' and bsqrsfz='" + sfzh + "'";
            List Elist = super.findEntities(existsSQL);
            if(Elist!=null && Elist.size()>0)
              continue;

            String hql = "from PoHJXX_CZRKJBXXB where gmsfhm='" + sfzh + "' and jlbz='1' and ryzt='0' and cxbz='0'";
            List list = super.findEntities(hql);
            if(list==null || list.size()==0)
              continue;

            PoHJXX_CZRKJBXXB ry = (PoHJXX_CZRKJBXXB)list.get(0);

            PoHZ_ZJ_SB sb = new PoHZ_ZJ_SB();
            //sb.setId();
            sb.setSqrxm(sqrxm);
            sb.setSqrsfz(sqrsfz);
            sb.setYwlb("2");
            sb.setBsqrxm(xm);
            sb.setBsqrsfz(sfzh);
            sb.setCzdw(ry.getPcs());
            sb.setCzyh(czyh);
            sb.setPch(pch);
            sb.setClbs("0");
            sb.setClsj(CommonUtil.getTimestamp(new Date()));
            sb.setCzsj(CommonUtil.getTimestamp(new Date()));
            sb.setSbzt("0");
            sb.setYwlbm("跨地市迁出业务");
            sb.setKdqqy_qrdqbm(dqbm);
            sb.setKdqqy_qrfkzt("0");
            sb.setKdqqy_qrywid(ywid);
            sb.setKdqqy_qrywlx(ywlx);
            sb.setKdqqy_zqzbh(zqz);
            sb.setLhdz(qrdz);
            sb.setKdqqy_qrdqh(qrdqh);
            sb.setKdqqy_qyldyy(qyldyy);
            sb.setKdqqy_qclb(qczxlb);
            sb.setBlrsfz(this.getUser().getGmsfhm());
            super.create(sb);
          }
        }
      }

    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw new RuntimeException("", ex);
    }
  }

  public List findKDQYZSFklist(){
    StringBuffer strAllHQL = new StringBuffer();
    //生成FROM HQL语句
    strAllHQL.append("from PoPOST_KDQCFKB where fkbz='0'");

    //执行DAO查询
    try {
      PojoInfo  hjsp_hjblspsqbDAO = DAOFactory.createHJSP_HJBLSPSQBDAO();
      List list = super.findEntities(strAllHQL.toString());
      return list;
    }
    catch (DAOException ex) {
      throw ex;
    }
  }

  /**
   * 一站式反馈跨地市迁出
   * @param sb PoHZ_ZJ_SB
   */
  public void postKDQCFKB(PoPOST_KDQCFKB e) {
    try {
      PojoInfo  hjsp_hjblspsqbDAO = DAOFactory.createHJSP_HJSPSQBDAO();

      //POST到远程更新
      KDSActionProxy.postKDQCFKB(e);

      //POST成功，更新本地
      e.setFkbz("1");
     super.update(e);

    }
    catch (DAOException ex) {
      ex.printStackTrace();
      throw ex;
    }
  }

  public void syncKdqFkYZS(Map params, SimpleJson simpleJson) {
    try {
      PojoInfo  hjsp_hjblspsqbDAO = DAOFactory.createHJSP_HJSPSQBDAO();
      String spywid = (String) params.get("ywid");
      String qyzbh = (String) params.get("qyzbh");
      String lscxfldm = (String) params.get("lscxfldm");
      String fkzt = "2";

      Long spid = Long.valueOf(spywid);

      PoHJSP_HJSPSQB e  = super.get(PoHJSP_HJSPSQB.class,spid);
      if (e == null)
        return;

      e.setKdqqy_fksj(StringUtils.formateDateTime());
      e.setKdqqy_qyzbh(qyzbh);
      e.setKdqqy_lscxfldm(lscxfldm);
      e.setKdqqy_fkzt(fkzt);

     super.update(e);

      String hql = "from PoHJSP_HJSPZB where spywid=" + e.getSpywid();
      List list = super.findEntities(hql);
      String sfzh = "", xm = "";
      for (int i = 0; i < list.size(); i++) {
        PoHJSP_HJSPZB zb = (PoHJSP_HJSPZB) list.get(i);
        zb.setKdqqy_fkzt(fkzt);
        zb.setKdqqy_fksj(StringUtils.formateDateTime());
        zb.setKdqqy_qyzbh(qyzbh);
        zb.setKdqqy_lscxfldm(lscxfldm);

       super.update(zb);
      }

    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw new RuntimeException("", ex);
    }
  }

  /**
 * 查询户政业务，反馈给迁入地
 * @return List
 */
 public List findKDQHzZjSb(){
   StringBuffer strAllHQL = new StringBuffer();
   //生成FROM HQL语句
   strAllHQL.append("from PoHZ_ZJ_SB where kdqqy_qrfkzt='0' and clbs<>'0' and kdqqy_qrdqbm is not null");

   //执行DAO查询
   try {
     PojoInfo  hjsp_hjblspsqbDAO = DAOFactory.createHJSP_HJBLSPSQBDAO();
     List list = super.findEntities(strAllHQL.toString());
     return list;
   }catch (DAOException ex) {
     throw ex;
   }
 }

/**
 * 反馈跨地市迁出
 * @param sb PoHZ_ZJ_SB
 */
 public void postKDQFk(PoHZ_ZJ_SB sb){
   try {
     PojoInfo  hjsp_hjblspsqbDAO = DAOFactory.createHJSP_HJSPSQBDAO();
       //POST到远程更新
       KDSActionProxy.postKdqSpywFK(sb);

       String hql = "from PoHZ_ZJ_SB where id=" + sb.getId();
       List list = super.findEntities(hql);
       if(list==null || list.size()==0)
         throw new RuntimeException("HZ_ZJ_SB不存在" + sb.getId());

       PoHZ_ZJ_SB e = (PoHZ_ZJ_SB)list.get(0);

       //更新本地
       e.setKdqqy_qrfkzt("1");
       e.setKdqqy_qrfksj(StringUtils.formateDate());
       e.setBlrsfz(this.getUser().getGmsfhm());
      super.update(e);

   }
   catch (DAOException ex) {
     ex.printStackTrace();
     throw ex;
   }
 }

 /**
  * 接收到反馈，持久化
  * @param params Map
  * @param simpleJson SimpleJson
  */
 public void syncKdqFk(Map params, SimpleJson simpleJson) {
   try {
     PojoInfo  hjsp_hjblspsqbDAO = DAOFactory.createHJSP_HJSPSQBDAO();
     String spywid = (String)params.get("ywid");
     String qyzbh = (String)params.get("qyzbh");
     String lscxfldm = (String)params.get("lscxfldm");
     String clbs = (String)params.get("clbs"); //1 正常处理，2 作废
     String fkzt = "2";

     if(clbs==null || clbs.equals("")) clbs = "1";

     Long spid = Long.valueOf(spywid);

     PoHJSP_HJSPSQB e  = super.get(PoHJSP_HJSPSQB.class,spid);
     if(e==null)
       return;//throw new RuntimeException("审批业务[" + spywid + "]不存在！");

     e.setKdqqy_fksj(StringUtils.formateDateTime());
     e.setKdqqy_qyzbh(qyzbh);
     e.setKdqqy_lscxfldm(lscxfldm);
     if(clbs.equals("2")){
       fkzt = "4";
       e.setSpjg("2");
       //迁入地作废业务
     }
     e.setKdqqy_fkzt(fkzt); //作废

    super.update(e);

     String hql = "from PoHJSP_HJSPZB where spywid=" + e.getSpywid();
     List list = super.findEntities(hql);
     String sfzh = "",xm="";
     for(int i=0;i<list.size();i++){
       PoHJSP_HJSPZB zb = (PoHJSP_HJSPZB)list.get(i);
       zb.setKdqqy_fkzt(fkzt);
       zb.setKdqqy_fksj(StringUtils.formateDateTime());
       zb.setKdqqy_qyzbh(qyzbh);
       zb.setKdqqy_lscxfldm(lscxfldm);

      super.update(zb);
     }

   }
   catch (Exception ex) {
     ex.printStackTrace();
     throw new RuntimeException("", ex);
    }
 }

 public void getKdqSpywQrryByHzZjsb(Map params, SimpleJson simpleJson) {
   //HJXX_CZRKJBXXB pohjxx_czr0_
   //    where (pohjxx_czr0_.JLBZ='1' )and(pohjxx_czr0_.HHNBID=3407000001000390158 )and((ryzt='0' )and(cxbz='0' )and(1=1 )) ) where rownum <= ?

   String sql = "from PoHJXX_CZRKJBXXB a where a.jlbz='1' and a.ryzt='0' and a.cxbz='0'";
   String str = (String)params.get("sfz");
   if(str==null || str.equals(""))
     return;

   String s[] = str.split(",");
   sql  += " and (";
   int count=0;
   for(int i=0;i<s.length;i++){
     String sfz = s[i];
     if(sfz==null || sfz.equals(""))
       continue;

       if(count!=0) sql += " or ";

       sql += " a.gmsfhm = '" + sfz + "'";
       count++;
   }
   sql += ")";

   //执行DAO查询
   try {
     PojoInfo  hjsp_hjblspsqbDAO = DAOFactory.createHJSP_HJBLSPSQBDAO();
     List list = super.findEntities(sql);
     long hhid = -1;
     for(int i=0;i<list.size();i++){
       PoHJXX_CZRKJBXXB ry = (PoHJXX_CZRKJBXXB)list.get(i);
       if(i==0){
         hhid = ry.getHhnbid().longValue();
       }else{
         if(hhid!=ry.getHhnbid().longValue()){
           throw new RuntimeException("此批次人员信息不属于同一户，不能初始化！");
         }
       }
     }

     simpleJson.setList(list);

     return;
   }
   catch (DAOException ex) {
     throw ex;
   }
 }

	@Override
	public PoHJXX_CZRKJBXXB setNewHz(VoCzrkdjbHqFhxx voCzrk) {
		
		PoHJXX_CZRKJBXXB xhz = super.get(PoHJXX_CZRKJBXXB.class, voCzrk.getRynbid());
		
		xhz.setYhzgx(voCzrk.getYhzgx());
		if(CommonUtil.isNotEmpty(voCzrk.getHyzk())) {
			xhz.setHyzk(voCzrk.getHyzk());
		}
		
		return (PoHJXX_CZRKJBXXB) super.update(xhz);
	}

	@Override
	public List<PoECHARTSDATA> queryEchartsDateList() {
		String cssql = "";
		String blsql = "";
		String qrsql = "";
		String swsql = "";
		String qcsql = "";
		String scsql = "";
	    StringBuffer zsql = new StringBuffer();
	    zsql.append(" select t1.ssxq as qhdm, substr(t2.mc,4,length(t2.mc)) as qhmc,sum(1)as zrks,count(distinct t1.hhnbid) as zhs, ")
	    .append(" sum(case  when t1.xb ='1'  then 1 else  0  end ) as nxrk,sum(case  when t1.xb ='2'  then 1 else  0  end ) as nvrk, ")
//	    .append(" sum(case  when t1.whcd is null  then 1 else  0  end ) as lrxl_level10,sum(case  when t1.whcd = 90  then 1 else  0  end ) as lrxl_level9, ")
//	    .append(" sum(case  when t1.whcd >=80 and t1.whcd<90  then 1 else  0  end ) as lrxl_level8,sum(case  when t1.whcd >=70 and t1.whcd<80  then 1 else  0  end ) as lrxl_level7, ")
//	    .append(" sum(case  when t1.whcd >=60 and t1.whcd<70  then 1 else  0  end ) as lrxl_level6,sum(case  when t1.whcd >=50 and t1.whcd<60  then 1 else  0  end ) as lrxl_level5, ")
//	    .append(" sum(case  when t1.whcd >=40 and t1.whcd<50  then 1 else  0  end ) as lrxl_level4,sum(case  when t1.whcd >=30 and t1.whcd<40  then 1 else  0  end ) as lrxl_level3, ")
//	    .append(" sum(case  when t1.whcd >=20 and t1.whcd<30  then 1 else  0  end ) as lrxl_level2,sum(case  when t1.whcd >=10 and t1.whcd<20  then 1 else  0  end ) as lrxl_level1, ")
	    .append(" sum(case  when (to_char(sysdate,'yyyyMMdd') - t1.csrq) >=0 and (to_char(sysdate,'yyyyMMdd') - t1.csrq)<200000  then 1 else  0  end ) as nl_level1, ")
	    .append(" sum(case  when (to_char(sysdate,'yyyyMMdd') - t1.csrq) >=200000 and (to_char(sysdate,'yyyyMMdd') - t1.csrq)<400000  then 1 else  0  end ) as nl_level2, ")
	    .append(" sum(case  when (to_char(sysdate,'yyyyMMdd') - t1.csrq) >=400000 and (to_char(sysdate,'yyyyMMdd') - t1.csrq)<600000  then 1 else  0  end ) as nl_level3, ")
	    .append(" sum(case  when (to_char(sysdate,'yyyyMMdd') - t1.csrq) >=600000 and (to_char(sysdate,'yyyyMMdd') - t1.csrq)<800000  then 1 else  0  end ) as nl_level4, ")
	    .append(" sum(case  when (to_char(sysdate,'yyyyMMdd') - t1.csrq) >=800000 then 1 else  0  end ) as nl_level5 ,substr(t1.ssxq, 0, 4)||'00' as ssqhdm ")
	    .append(" from hjxx_czrkjbxxb t1,xt_xzqhb t2,hjxx_hxxb t3 where t1.cxbz = '0' and t1.jlbz = '1' and t1.ryzt = '0' and t1.hhnbid = t3.hhnbid and t3.jlbz = '1' and t3.cxbz = '0' ")
	    .append(" and t1.ssxq = t2.dm and t2.qybz =1 group by t1.ssxq,t2.mc ");
	    List listTemp =  super.executeSqlQuery(zsql.toString(), null);
	    List<PoECHARTSDATA> list = new ArrayList<PoECHARTSDATA>();
	    for(Object obj:listTemp){
			Object[] objs = (Object[])obj;
			PoECHARTSDATA vo=new PoECHARTSDATA();
			vo.setQhdm(objs[0]==null?" ":objs[0].toString());
			vo.setQhmc(objs[1]==null?" ":objs[1].toString());
			vo.setZrks(Integer.parseInt(objs[2].toString()) );
			vo.setZhs(Integer.parseInt(objs[3].toString()) );
			vo.setNxrk(Integer.parseInt(objs[4].toString()) );
			vo.setNvrk(Integer.parseInt(objs[5].toString()) );
//			vo.setLrxl_level10(Integer.parseInt(objs[6].toString()) );
//			vo.setLrxl_level9(Integer.parseInt(objs[7].toString()) );
//			vo.setLrxl_level8(Integer.parseInt(objs[8].toString()) );
//			vo.setLrxl_level7(Integer.parseInt(objs[9].toString()) );
//			vo.setLrxl_level6(Integer.parseInt(objs[10].toString()) );
//			vo.setLrxl_level5(Integer.parseInt(objs[11].toString()) );
//			vo.setLrxl_level4(Integer.parseInt(objs[12].toString()) );
//			vo.setLrxl_level3(Integer.parseInt(objs[13].toString()) );
//			vo.setLrxl_level2(Integer.parseInt(objs[14].toString()) );
//			vo.setLrxl_level1(Integer.parseInt(objs[15].toString()) );
			vo.setNl_level1(Integer.parseInt(objs[6].toString()) );
			vo.setNl_level2(Integer.parseInt(objs[7].toString()) );
			vo.setNl_level3(Integer.parseInt(objs[8].toString()) );
			vo.setNl_level4(Integer.parseInt(objs[9].toString()) );
			vo.setNl_level5(Integer.parseInt(objs[10].toString()) );
			vo.setSsqhdm(objs[11]==null?"0":objs[11].toString());
			if(objs[11]!=null) {
				vo.setSsqhmc(super.get(PoXT_XZQHB.class, objs[11].toString()).getMc().substring(3));
			}
			cssql = " select count(distinct t.rynbid) from hjyw_csdjxxb t, hjxx_czrkjbxxb t1 where t.rynbid = t1.rynbid and substr(t.sbsj,1,4) = Extract(year from sysdate) and t1.ssxq = '"+vo.getQhdm()+"' ";
			blsql = " select count(distinct t.rynbid) from hjyw_hjblxxb t, hjxx_czrkjbxxb t1 where t.rynbid = t1.rynbid and substr(t.sbsj,1,4) = Extract(year from sysdate) and t1.ssxq = '"+vo.getQhdm()+"' ";
			qrsql = " select t3.dm, t3.mc,count(t3.dm) from (select case when substr(t.qcdssxq, 1, 3) = '010' then t.qcdssxq else substr(t.qcdssxq, 1, 2) || '0000' "+
			" end as qhdm from hjyw_qrdjxxb t, hjxx_czrkjbxxb t1 where t.rynbid = t1.rynbid and t.ywlx < '3' and substr(t.sbsj,1,4) = Extract(year from sysdate) and t1.ssxq = '"+vo.getQhdm()+"') u,xt_xzqhb t3 where u.qhdm = t3.dm group by t3.dm,t3.mc   ";
			swsql = " select count(distinct t.rynbid) from hjyw_swzxxxb t, hjxx_czrkjbxxb t1 where t.rynbid = t1.rynbid and substr(t.sbsj,1,4) = Extract(year from sysdate) and t1.ssxq = '"+vo.getQhdm()+"' ";
			qcsql = " select count(distinct t.rynbid) from hjyw_qczxxxb t, hjxx_czrkjbxxb t1 where t.rynbid = t1.rynbid and substr(t.sbsj,1,4) = Extract(year from sysdate) and t.ywlx < '3' and t1.ssxq = '"+vo.getQhdm()+"' ";
			scsql = " select count(distinct t.rynbid) from hjyw_hjscxxb t, hjxx_czrkjbxxb t1 where t.rynbid = t1.rynbid and substr(t.sbsj,1,4) = Extract(year from sysdate) and t1.ssxq = '"+vo.getQhdm()+"' ";
			String xlsql = "select sum(case  when t1.whcd is null  then 1 else  0  end ) as lrxl_level10, " + 
					"sum(case  when t1.whcd = 90  then 1 else  0  end ) as lrxl_level9," + 
					"sum(case  when t1.whcd >=80 and t1.whcd<90  then 1 else  0  end ) as lrxl_level8," + 
					"sum(case  when t1.whcd >=70 and t1.whcd<80  then 1 else  0  end ) as lrxl_level7," + 
					"sum(case  when t1.whcd >=60 and t1.whcd<70  then 1 else  0  end ) as lrxl_level6," + 
					"sum(case  when t1.whcd >=50 and t1.whcd<60  then 1 else  0  end ) as lrxl_level5," + 
					"sum(case  when t1.whcd >=40 and t1.whcd<50  then 1 else  0  end ) as lrxl_level4," + 
					"sum(case  when t1.whcd >=30 and t1.whcd<40  then 1 else  0  end ) as lrxl_level3," + 
					"sum(case  when t1.whcd >=20 and t1.whcd<30  then 1 else  0  end ) as lrxl_level2," + 
					"sum(case  when t1.whcd >=10 and t1.whcd<20  then 1 else  0  end ) as lrxl_level1" + 
					"          from hjyw_qrdjxxb t, hjxx_czrkjbxxb t1" + 
					"         where t.rynbid = t1.rynbid" + 
					"           and t1.ssxq = '"+vo.getQhdm()+"'"+ 
					"           and substr(t.qrrq,1,4) = Extract(year from sysdate)" + 
					"           and t.ywlx < '3'";
			List<?> csobjlist  = executeSqlQuery(cssql, new Object[]{});
			List<?> blobjlist  = executeSqlQuery(blsql, new Object[]{});
			List<?> qrobjlist  = executeSqlQuery(qrsql, new Object[]{});
			List<?> swobjlist  = executeSqlQuery(swsql, new Object[]{});
			List<?> qcobjlist  = executeSqlQuery(qcsql, new Object[]{});
			List<?> scobjlist  = executeSqlQuery(scsql, new Object[]{});
			List xllist =  super.executeSqlQuery(xlsql.toString(), null);
			if(csobjlist==null || csobjlist.size()==0)
				vo.setCslr(0);
			else
				vo.setCslr(Integer.parseInt(csobjlist.get(0).toString()));
			if(blobjlist==null || blobjlist.size()==0)
				vo.setBllr(0);
			else
				vo.setBllr(Integer.parseInt(blobjlist.get(0).toString()));
			if(qrobjlist==null || qrobjlist.size()==0)
				vo.setQrlr(0);
			else
				vo.setQrlr(qrobjlist.size());
				List<PoSSXQQRSFFBB> ssxqqrsffbblist = new ArrayList<PoSSXQQRSFFBB>();
			    for(Object objq :qrobjlist) {
			    	Object[] objTemp = (Object[])objq;
			    	PoSSXQQRSFFBB poSSXQQRSFFBB = new PoSSXQQRSFFBB();
			    	PojoInfo  ssxqsffbbDAO = DAOFactory.createSSXQQRSFFBBDAO();
			    	poSSXQQRSFFBB.setSffbbId((Long)ssxqsffbbDAO.getId());
			    	poSSXQQRSFFBB.setQhdm(objs[0]==null?" ":objs[0].toString());
			    	poSSXQQRSFFBB.setQcdsdm(objTemp[0]==null?" ":objTemp[0].toString());
			    	poSSXQQRSFFBB.setQcdsmc(objTemp[1]==null?"":objTemp[1].toString());
			    	poSSXQQRSFFBB.setCount(Integer.parseInt(objTemp[2].toString()));
			    	ssxqqrsffbblist.add(poSSXQQRSFFBB);
			    }
			    vo.setXzqhList(ssxqqrsffbblist);
			if(swobjlist==null || swobjlist.size()==0)
				vo.setSwlc(0);
			else
				vo.setSwlc(Integer.parseInt(swobjlist.get(0).toString()));
			if(qcobjlist==null || qcobjlist.size()==0)
				vo.setQclc(0);
			else
				vo.setQclc(Integer.parseInt(qcobjlist.get(0).toString()));
			if(scobjlist==null || scobjlist.size()==0)
				vo.setSclc(0);
			else
				vo.setSclc(Integer.parseInt(scobjlist.get(0).toString()));
			if(xllist==null || xllist.size()==0) {
				vo.setLrxl_level10(0);
				vo.setLrxl_level9(0);
				vo.setLrxl_level8(0);
				vo.setLrxl_level7(0);
				vo.setLrxl_level6(0);
				vo.setLrxl_level5(0);
				vo.setLrxl_level4(0);
				vo.setLrxl_level3(0);
				vo.setLrxl_level2(0);
				vo.setLrxl_level1(0);
			}else {
				Object[] xlobj = (Object[]) xllist.get(0);
				vo.setLrxl_level10(Integer.parseInt(xlobj[0]==null?"0":xlobj[0].toString()) );
				vo.setLrxl_level9(Integer.parseInt(xlobj[1]==null?"0":xlobj[1].toString()) );
				vo.setLrxl_level8(Integer.parseInt(xlobj[2]==null?"0":xlobj[2].toString()) );
				vo.setLrxl_level7(Integer.parseInt(xlobj[3]==null?"0":xlobj[3].toString()) );
				vo.setLrxl_level6(Integer.parseInt(xlobj[4]==null?"0":xlobj[4].toString()) );
				vo.setLrxl_level5(Integer.parseInt(xlobj[5]==null?"0":xlobj[5].toString()) );
				vo.setLrxl_level4(Integer.parseInt(xlobj[6]==null?"0":xlobj[6].toString()) );
				vo.setLrxl_level3(Integer.parseInt(xlobj[7]==null?"0":xlobj[7].toString()) );
				vo.setLrxl_level2(Integer.parseInt(xlobj[8]==null?"0":xlobj[8].toString()) );
				vo.setLrxl_level1(Integer.parseInt(xlobj[9]==null?"0":xlobj[9].toString()) );
			}
			list.add(vo);
		}
	    return list;
	  }

	@Override
	public void deleteAllEchartDate() {
		List<PoECHARTSDATA> list = (List<PoECHARTSDATA>) super.getObjectListByHql("/conf/segment/common", "queryEchartsData",null);
		if(list != null) {
			super.deleteAll(list);
		}
		List<PoSSXQQRSFFBB> listsffbb = (List<PoSSXQQRSFFBB>) super.getObjectListByHql("/conf/segment/common", "querySSXQQRSFFBB",null);
		if(listsffbb != null) {
			super.deleteAll(listsffbb);
		}
	}

	@Override
	public void insertAllEchartDate(List list) {
		if(list!=null) {
			//super.createAll(list);
			for(Object obj:list){
				PoECHARTSDATA poECHARTSDATA = (PoECHARTSDATA) obj;
				super.create(poECHARTSDATA);
				super.createAll(poECHARTSDATA.getXzqhList());
			}
		}
		
		
	}
	public void updateHzyw(String hzywid,Long hjywid,Long spywid){
	      if(CommonUtil.isNotBlank(hzywid)){
	          List<?> list = findEntities("from PoHZ_ZJ_SB a where a.id=" + hzywid);
	          String kzz = super.getXTKZCS("10031");
	          String kzz1 = super.getXTKZCS("10032");
	          String kzz2 = super.getXTKZCS("10037");
	          if(list.size()>0){
	              PoHZ_ZJ_SB sb = (PoHZ_ZJ_SB)list.get(0);
	            //1.控制表是否有   2.wx_code是否为空    add by zjm 20191106 
                  if(kzz.equals("1")) {//控制值为1的时候，才会更新航信数据库的HZPT_RECEIVESTATE表的字段
                	  if(CommonUtil.isNotEmpty(sb.getWx_code())) {
                		  updateHzptReceiveState(kzz,sb);
                	  }
                  }
                  if(kzz1.equals("1")) {//控制值为1的时候，才会更新航信数据库的HZPT_RECEIVESTATE表的字段
                	  if(CommonUtil.isNotEmpty(sb.getWx_code())) {
                		  insertEms(kzz1,sb);
                	  }
                  }
	              if(sb.getPch()!=null && !sb.getPch().equals("")){
	                //一个批次，统一处理
	                list = findEntities("from PoHZ_ZJ_SB a where a.pch='" + sb.getPch() + "' and a.clbs='0' ");
	                int pccount = list.size();
	                for(int index=0;index<pccount;index++){
	                  PoHZ_ZJ_SB sbx = (PoHZ_ZJ_SB)list.get(index);
	                  sbx.setClbs("1");
	                  if(hjywid>0) {
	                	 sbx.setHjywid(hjywid);  
	                  }
	                  if(spywid>0) {
	                	 sbx.setSpywid(spywid);  
	                  }
	                  sbx.setBlrsfz(this.getUser().getGmsfhm());
	                  sbx.setHjdpcs(this.getUser().getDwdm());
	                  sbx.setClsj(new java.sql.Timestamp(new Date().getTime()));
	                  update(sbx);
	                }
	              }else{
	                sb.setClbs("1");
	                if(hjywid>0) {
	                	sb.setHjywid(hjywid);
	                }
	                if(spywid>0) {
	                	sb.setSpywid(spywid);
	                }
	                sb.setBlrsfz(this.getUser().getGmsfhm());
	                sb.setHjdpcs(this.getUser().getDwdm());
	                sb.setClsj(new java.sql.Timestamp(new Date().getTime()));
	                update(sb);
	              }
	          }
	       }
	}

	public void updateHzptReceiveState(String kzz,PoHZ_ZJ_SB sbx) {
		String ywlb = sbx.getYwlb();
      String wx_code = sbx.getWx_code();
      PoXT_XTCSB xt_csb = new PoXT_XTCSB();
		if(ywlb.equals("1")) {//出生登记
    	  xt_csb = super.getXTCSB("9038", "1");
      }else if(ywlb.equals("2")) {//迁出注销
    	  xt_csb = super.getXTCSB("9038", "2");
      }else if(ywlb.equals("3")) {//迁入和迁入审批业务
    	  if(sbx.getSbzt().equals("1")) {//迁入审批登记
    		  xt_csb = super.getXTCSB("9038", "3");
    	  }else {//迁入业务
    		  xt_csb = super.getXTCSB("9038", "4");
    	  }
      }else if(ywlb.equals("4")) {//户籍删除
    	  xt_csb = super.getXTCSB("9038", "5");
      }else if(ywlb.equals("5")) {//变更更正
    	  xt_csb = super.getXTCSB("9038", "6");
      }else if(ywlb.equals("6")) {//辅项变更
    	  xt_csb = super.getXTCSB("9038", "7");
      }else if(ywlb.equals("7")) {//死亡注销
    	  xt_csb = super.getXTCSB("9038", "8");
      }else if(ywlb.equals("8")) {//住址变动
    	  xt_csb = super.getXTCSB("9038", "9");
      }else if(ywlb.equals("9")) {//户籍补录
    	  xt_csb = super.getXTCSB("9038", "10");
      }else if(ywlb.equals("16")) {//全户变更
    	  xt_csb = super.getXTCSB("9038", "11");
      }
		if(CommonUtil.isNotEmpty(wx_code)&&(kzz).equals("1")) {
			try {
				receiveStateService.updataReceiveState(wx_code, xt_csb.getMc());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
//	  String kzz1 = super.getXTKZCS("10032");
//	  if(CommonUtil.isNotEmpty(wx_code)&&(kzz1).equals("1")) {
//			try {
//				String organid = super.getXTCSB("9041", "1").getMc();
//				String organname = super.getXTCSB("9041", "2").getMc();
//				String dwdm = super.getUser().getDwdm();
//				receiveStateService.insertEmsSentInfo(wx_code, organid,organname,dwdm); //需审批的可能插两条，暂时注释，迁入登记可能有影响 20200212
//			} catch (Exception e) {
//				throw new ServiceException(e.toString());
//			}
//			
//		}
	}
	public void insertEms(String kzz,PoHZ_ZJ_SB sb) {
		 if(CommonUtil.isNotEmpty(sb.getWx_code())&&(kzz).equals("1")) {
				try {
					String organid = super.getXTCSB("9041", "1").getMc();
					String organname = super.getXTCSB("9041", "2").getMc();
					String dwdm = super.getUser().getDwdm();
					receiveStateService.insertEmsSentInfo(sb, organid,organname,dwdm); //需审批的可能插两条，暂时注释，迁入登记可能有影响 20200212
				} catch (Exception e) {
					throw new ServiceException(e.toString());
				}
				
			}
	}

	private void autoInsertHzjsbs(String hzywid) {
	      if(CommonUtil.isNotBlank(hzywid)){
	          List<?> list = findEntities("from PoHZ_ZJ_SB a where a.id=" + hzywid);
	          String kzz2 = super.getXTKZCS("10037");
	          if(list.size()>0){
	              PoHZ_ZJ_SB sb = (PoHZ_ZJ_SB)list.get(0);
	            //1.控制表是否有   2.wx_code是否为空    add by zjm 20191106 
	              if(sb.getPch()!=null && !sb.getPch().equals("")){
	                //一个批次，统一处理
	                list = findEntities("from PoHZ_ZJ_SB a where a.pch='" + sb.getPch() + "' and a.clbs='0' ");
	                int pccount = list.size();
	                for(int index=0;index<pccount;index++){
	                  PoHZ_ZJ_SB sbx = (PoHZ_ZJ_SB)list.get(index);
	                  if(kzz2.equals("1")) {//add by zjm 20200710 控制值为2的时候，自动生成待办事项
	                	  autoInsertSingleHzjsbs(kzz2,sbx);
	                  }
	                  
	                }
	              }else{
	                if(kzz2.equals("1")) {//add by zjm 20200710 控制值为2的时候，自动生成待办事项
	                	autoInsertSingleHzjsbs(kzz2,sb);
	                  }
	              }
	          }
	       }
	}
	private void autoInsertSingleHzjsbs(String kzz, PoHZ_ZJ_SB sb) {
		 if(CommonUtil.isNotEmpty(sb.getFjid())&&(kzz).equals("1")) {
			 String ywlb = sb.getYwlb();
			 String ywlbH = null;
//			 4. 迁入业务，出生登记，住址变动，变更更正，户籍补录，户籍删除，  副项变更，全项变更，全户变更，这10项业务办理完结后，自动生成的那条待办事项，ywlb自动改为10（打印户口簿）;
//			 5. 迁入登记（省外），办结完后，自动生成的待办事项，ywlb自动改为11（准迁证打印） 
//			 6. 迁出注销，办结完后，自动生成的待办事项，ywlb自动改为12（迁移证打印）
//			 7. 死亡注销，办结完后，自动生成的待办事项，ywlb自动改为14（死亡证明）；
		      if(ywlb.equals("1")) {//出生登记       
		    	  ywlbH="10";
		      }else if(ywlb.equals("2")) {//迁出注销
		    	  ywlbH="12";
		      }else if(ywlb.equals("3")) {//迁入和迁入审批业务
		    	  if(sb.getSbzt().equals("1")) {//迁入审批登记
		    		//迁入登记（省外）  
		    		  if(!sb.getQcdssxq().startsWith("34")) {
		    			  ywlbH="11";
		    		  }
		    	  }else {//迁入业务
		    		  ywlbH="10";
		    	  }
		      }else if(ywlb.equals("4")) {//户籍删除
		    	  ywlbH="10";
		      }else if(ywlb.equals("5")) {//变更更正
		    	  ywlbH="10";
		      }else if(ywlb.equals("6")) {//辅项变更
		    	  ywlbH="10";
		      }else if(ywlb.equals("7")) {//死亡注销
		    	  ywlbH="14";
		      }else if(ywlb.equals("8")) {//住址变动
		    	  ywlbH="10";
		      }else if(ywlb.equals("9")) {//户籍补录
		    	  ywlbH="10";
		      }else if(ywlb.equals("16")) {//全户变更
		    	  ywlbH="10";
		      }else if(ywlb.equals("17")) {//全项变更
		    	  ywlbH="10";
		      }
				try {
					if(ywlbH!=null) {
						PojoInfo  hz_js_bsDAO = DAOFactory.createHZ_JS_BSDAO();
						PoHZ_ZJ_SB hz_zj_sbTemp = new PoHZ_ZJ_SB();
				    	BeanUtils.copyProperties(hz_zj_sbTemp, sb);
				    	Long id = (Long)hz_js_bsDAO.getId();
				    	hz_zj_sbTemp.setId(id);
//				    	hz_zj_sbTemp.setFjid("1");
				    	hz_zj_sbTemp.setClsj(null);
				    	hz_zj_sbTemp.setFjdy("1");
				    	hz_zj_sbTemp.setClbs("0");
				    	hz_zj_sbTemp.setYwlb(ywlbH);
						super.create(hz_zj_sbTemp);
					}
				} catch (Exception e) {
					throw new ServiceException(e.toString());
				}
				
			}
	}	
	@Override
	public void queryEchartsByProcedure() {
		CallProc p=new CallProc();
		p.setProcname("rksj");
		super.executeProcedure(p);
	}
}