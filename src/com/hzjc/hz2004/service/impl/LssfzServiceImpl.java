package com.hzjc.hz2004.service.impl;

import com.hzjc.hz2004.service.*;
import java.util.*;
import com.hzjc.wsstruts.exception.*;
import com.hzjc.hz2004.dao.*;
import com.hzjc.hz2004.po.*;
import com.hzjc.hz2004.vo.*;
import com.hzjc.hz2004.constant.PublicConstant;
import com.hzjc.util.StringUtils;
import com.hzjc.wsstruts.vo.*;
import com.hzjc.hz2004.constant.ZjConstant;
import com.hzjc.hz2004.base.SpringContextHolder;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.constant.HjConstant;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 临时身份证操作实现</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */
@Service
public class LssfzServiceImpl
    extends ZjBaseService
    implements LssfzService {
  /**
   * 临时身份证受理业务
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List LssfzSlyw(CdsVO psCdsVo[]) throws ServiceException,
      DAOException {
    List lstReturn = new ArrayList();
    try {
      PojoInfo  lssfz_slxxbDAO = DAOFactory.createLSSFZ_SLXXBDAO();
      PojoInfo  hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
      PojoInfo  zjyw_slxbDAO = DAOFactory.createZJYW_SLXXBDAO();
      //处理受理信息
      if (psCdsVo != null && psCdsVo.length > 0) { //如果有受理信息
        for (int i = 0; i < psCdsVo.length; i++) {
          //取出人员ID
          String sRyid = (String) psCdsVo[i].getMap().get("ryid");
          ////取出卡号
          //String sKh = (String) psCdsVo[i].getMap().get("lsjmsfzkh");
          //根据人员ID从常表中去信息
          List lstRyxx =super.findAllByHQL(
              " from PoHJXX_CZRKJBXXB where ryid=" + sRyid +
              " and jlbz='1' and ryzt='0' and cxbz='0'");
          if (lstRyxx == null || lstRyxx.size() <= 0) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "没有找到要受理的人员信息,临时身份证无法受理.", null);
          }
          PoHJXX_CZRKJBXXB poRyxx = (PoHJXX_CZRKJBXXB) (lstRyxx.get(0));
          //add by  hh 20051220 增加判断,未受理二代证的不能办理临时身份证
          List lstSlxxb =super.findAllByHQL(
              " from PoZJYW_SLXXB where ryid=" + sRyid +
              " and slzt<'93' and slzt not in('02','10','12','17','23')");
          if(lstSlxxb==null || lstSlxxb.size()<=0){
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "没有找到证件受理信息,未受理证件不允许办理临时身份证.", null);
          }
          //数据范围判断
          String strJcwh = poRyxx == null ? null : poRyxx.getJcwh();
          ///////////////////////////////////////////////////////////////
          //组织参数
          //////////////////////////////////////////////////////////////
          VoXtsjfw vo = new VoXtsjfw();
          vo.setSjfwbz(PublicConstant.XT_QX_JWH);
          vo.setSjfw(strJcwh);
          List lstSjfw = new ArrayList();
          lstSjfw.add(vo);
          //////////////////////////////////////////////////////////////////
          //调用接口
          //////////////////////////////////////////////////////////////////
          boolean bRet = XtywqxServiceImpl.VerifyDataRange(String.valueOf(
              getUserInfo().
              getYhid()), PublicConstant.GNBH_LSSFZ_SLYW, lstSjfw);
          if (!bRet) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_DATASCOPE,
                                       "受理数据范围受到限制，临时身份证受理业务无法完成。", null);
          }
          //重号判断
          List lstChRyxx =super.findAllByHQL(
              " from PoHJXX_CZRKJBXXB where gmsfhm='" + poRyxx.getGmsfhm() +
              "' and jlbz='" + PublicConstant.JLBZ_ZX + "' and ryzt='" +
              HjConstant.RYZT_ZC + "' and cxbz='" + PublicConstant.CXBZ_FCX +
              "'");
          if (lstChRyxx.size() > 1) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "办证人重号[[" + poRyxx.getGmsfhm() +
                                       "]],临时身份证无法受理.", null);
          }
          //判断此人是否有受理信息，
          //每人只允许有一条未打印的受理信息
          List lstSlxx =super.findAllByHQL(
              " from PoLSSFZ_SLXXB where ryid=" + poRyxx.getRyid() +
              " and dybz='0'");
          if (lstSlxx.size() > 0) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "办证人受理过一笔,临时身份证不能同时受理多笔.", null);
          }
          //从系统参数中得到签发机关
          String sQfjg = "";
          VoXT_XTCSB voXtcsxx = getQxqfjgAsVoByMlpnbid(poRyxx.getMlpnbid());
          if (voXtcsxx != null) {
            sQfjg = voXtcsxx.getKzbzb();
          }
          if (sQfjg == null || (sQfjg != null && sQfjg.trim().length() <= 0)) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                       "没有设置该人所在的区县对应签发机关，证件受理业务无法完成。", null);
          }
          //保存受理信息
          PoLSSFZ_SLXXB poSlxxb = new PoLSSFZ_SLXXB();
          BeanUtils.copyProperties(poSlxxb, poRyxx);
          poSlxxb.setQfjg(sQfjg);
          poSlxxb.setZz(joinSfzZz(poRyxx.getRynbid())); //组合身份证地址字段
          poSlxxb.setLsjmsfzkh(null);
          poSlxxb.setYxqxjzrq(null);
          poSlxxb.setYxqxqsrq(null);
          poSlxxb.setCzyid(this.getUserInfo().getYhid());
          poSlxxb.setCzsj(StringUtils.getServiceTime());
          poSlxxb.setCzyip(BaseContext.getUser().getIp());
          poSlxxb.setDybz(ZjConstant.LSSFZ_DYBZ_WDY);
          poSlxxb.setLsslid( (Long) lssfz_slxxbDAO.getId());
                    lstReturn.add(super.create(poSlxxb));
        }
      }
      ////处理结束
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

    return lstReturn;
  }

  /**
   * 临时身份证受理信息查询
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult LsslQry(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException {

    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //生成FROM HQL语句
    strFromHQL.append("from PoLSSFZ_SLXXB as LSSFZ_SLXXB ")
        .append("where 1=1 ");
    //数据范围begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_LSSFZ_SLCX);
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
    strAllHQL.append("select LSSFZ_SLXXB ").append(strFromHQL.toString());

    //debug info
    //_log.info(strAllHQL.toString());

    //执行DAO查询
    try {
      PojoInfo  lssfz_slxxbDAO = DAOFactory.createLSSFZ_SLXXBDAO();


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
    voQueryResult.setVotype(PoLSSFZ_SLXXB.class);
    return voQueryResult;
  }

  /**
   * 临时身份证打印保存
   * @param psCdsVo
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List LssfzDybc(CdsVO psCdsVo[]) throws ServiceException,
      DAOException {
    List lstReturn = new ArrayList();
    PoLSSFZ_SLXXB poReturn = null;
    try {
      PojoInfo  lssfz_slxxbDAO = DAOFactory.createLSSFZ_SLXXBDAO();
      PojoInfo  zjxx_jmsfzxxbDAO = DAOFactory.createZJXX_JMSFZXXBDAO();
      PojoInfo  lssfz_dyxxbDAO = DAOFactory.createLSSFZ_DYXXBDAO();
      //开始事物
      //处理受理信息
      if (psCdsVo != null && psCdsVo.length > 0) { //如果有受理信息
        for (int i = 0; i < psCdsVo.length; i++) {
          //取出临时受理ID
          String sLsslid = (String) psCdsVo[i].getMap().get("lsslid");
          //取出卡号
          String sKh = (String) psCdsVo[i].getMap().get("lsjmsfzkh");
          //根据临时受理ID从临时表中取信息
          PoLSSFZ_SLXXB poSlxxb  = super.get(PoLSSFZ_SLXXB.class,Long.
              valueOf(sLsslid));
          if (poSlxxb == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "没有找到要保存的受理信息,业务无法保存.", null);
          }
          //数据 范围效验
          VoXtsjfw vo = new VoXtsjfw();
          vo.setSjfwbz(PublicConstant.XT_QX_JWH);
          vo.setSjfw(poSlxxb.getJcwh());
          List lstSjfw = new ArrayList();
          lstSjfw.add(vo);
          boolean bRet = XtywqxServiceImpl.VerifyDataRange(String.valueOf(
              getUserInfo().
              getYhid()), PublicConstant.GNBH_LSSFZ_DYBC, lstSjfw);
          if (!bRet) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_DATASCOPE,
                                       "受理数据范围受到限制，临时身份证打印保存无法完成。", null);
          }
          //根据卡号从受理表中获取受理信息
          List lstSlxx =super.findAllByHQL(
              " from PoLSSFZ_SLXXB as LSSFZ_SLXXB where LSJMSFZKH='" + sKh +
              "' and dybz <>'" + ZjConstant.LSSFZ_DYBZ_ZF + "'");

          //判断是否已经打印
          if (poSlxxb.getDybz().equals(ZjConstant.LSSFZ_DYBZ_WDY)) {
            //如果未打印
            //判断卡号是否存在
            if (lstSlxx != null && lstSlxx.size() > 0) {
              //如果卡号已经存在，则不允许再打印
              throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                         "卡号[["+sKh+"]]已经存在,不允许保存.", null);
            }
            //如果卡号不存在，
            //更新受理信息
            poSlxxb.setLsjmsfzkh(sKh);
            poSlxxb.setDybz(ZjConstant.LSSFZ_DYBZ_YDY);
            poSlxxb.setDyrid(this.getUserInfo().getYhid());
            poSlxxb.setDysj(StringUtils.getServiceTime());
            poSlxxb.setDyrip(BaseContext.getUser().getIp());
            poSlxxb.setYxqxqsrq(StringUtils.getServiceDate());
//            Date dt = new java.util.Date();
//            dt.setMonth(dt.getMonth() + 3);
            //add HB 20060914
            //有效期限截止日期
            
            java.util.Calendar c = Calendar.getInstance();
            c.setTime(SpringContextHolder.getCommonService().getSjksj());
            c.add(Calendar.MONDAY, 3);
            
            Date lssfzDate = c.getTime();
            
            poSlxxb.setYxqxjzrq(StringUtils.formatDateBy(lssfzDate,"yyyyMMdd"));
            //在身份证表中增加记录
            PoZJXX_JMSFZXXB poJmsfzxxb = new PoZJXX_JMSFZXXB();
            BeanUtils.copyProperties(poJmsfzxxb, poSlxxb);
            poJmsfzxxb.setZjlb(ZjConstant.ZJ_ZJLB_LSEDZ);
            poJmsfzxxb.setZjzt(ZjConstant.ZJ_ZJZT_ZC);
            poJmsfzxxb.setKtglh(poSlxxb.getLsjmsfzkh());
            poJmsfzxxb.setZjsj(StringUtils.getServiceTime());
            poJmsfzxxb.setZjlx(ZjConstant.SFZXX_ZJLX_LQ);
            poJmsfzxxb.setNbsfzid( (Long) zjxx_jmsfzxxbDAO.getId());
            
            PoZJXX_JMSFZXXB poSfzxxb = (PoZJXX_JMSFZXXB)super.create(
                    poJmsfzxxb);
            
            //更新临时受理表中的记录
            poSlxxb.setNbsfzid(poSfzxxb.getNbsfzid());
            poReturn = (PoLSSFZ_SLXXB)super.update(poSlxxb);
          }
          else
          if (poSlxxb.getDybz().equals(ZjConstant.LSSFZ_DYBZ_YDY)) {
            //如果已经打印
            if (poSlxxb.getLsjmsfzkh().equals(sKh)) {
              //如果卡号相同，则不处理任何信息
            }
            else {
              //如果卡号不相同，
              //检查是否卡号相同
              if (lstSlxx != null && lstSlxx.size() > 0) {
                //如果卡号已经存在，则不允许再打印
                throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                           "卡号已经存在,不允许保存.", null);
              }
              //更新居民身份证信息表中的卡号
              PoZJXX_JMSFZXXB poJmsfzbNew = super.
                  get(PoZJXX_JMSFZXXB.class,poSlxxb.getNbsfzid());
              poJmsfzbNew.setKtglh(sKh);
             super.update(poJmsfzbNew);
              //要记录历史信息
              WriteInfoToLsB(poSlxxb.getLsslid(), poSlxxb.getRyid(),
                             poSlxxb.getLsjmsfzkh(), sKh);
              //更新受理信息表的卡号和打印信息
              poSlxxb.setLsjmsfzkh(sKh);
              poSlxxb.setDysj(StringUtils.getServiceTime());
              poSlxxb.setDyrid(this.getUserInfo().getYhid());
              poSlxxb.setDyrip(BaseContext.getUser().getIp());

              poReturn =(PoLSSFZ_SLXXB)super.update(poSlxxb);
            }
          }
          else {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "作废信息不能保存！", null);
          }
          //保存打印信息
          PoLSSFZ_DYXXB poDyxxb = new PoLSSFZ_DYXXB();
          poDyxxb.setLsslid(poSlxxb.getLsslid());
          poDyxxb.setRyid(poSlxxb.getRyid());
          poDyxxb.setLsjmsfzkh(poSlxxb.getLsjmsfzkh());
          poDyxxb.setCzsj(StringUtils.getServiceTime());
          poDyxxb.setCzyid(this.getUserInfo().getYhid());
          poDyxxb.setCzyip(BaseContext.getUser().getIp());
          poDyxxb.setLsdyid( (Long) lssfz_dyxxbDAO.getId());
          super.create(poDyxxb);

          lstReturn.add(poReturn);
        }
      }
      ////处理结束
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

    return lstReturn;

  }

  public PoLSSFZ_SLLSB WriteInfoToLsB(Long pnLsslid, Long pnRyid,
                                      String psOldKh, String psKh) throws
      ServiceException,
      DAOException {
    PojoInfo  lssfz_sllsbDAO = DAOFactory.createLSSFZ_SLLSBDAO();
    PoLSSFZ_SLLSB poSllsb = new PoLSSFZ_SLLSB();
    poSllsb.setLsslid(pnLsslid);
    poSllsb.setRyid(pnRyid);
    poSllsb.setBghkh(psKh);
    poSllsb.setBgqkh(psOldKh);
    poSllsb.setCzsj(StringUtils.getServiceTime());
    poSllsb.setCzyid(this.getUserInfo().getYhid());
    poSllsb.setCzyip(BaseContext.getUser().getIp());
    poSllsb.setLssllsbid( (Long) lssfz_sllsbDAO.getId());
    return (PoLSSFZ_SLLSB)super.create(poSllsb);
  }

  /**
   * 临时身份证受理信息修改
   * @param psCdsVo
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List LsslxxXg(CdsVO psCdsVo[]) throws ServiceException,
      DAOException {
    List lstReturn = new ArrayList();
    try {
      PojoInfo  lssfz_slxxbDAO = DAOFactory.createLSSFZ_SLXXBDAO();
      PojoInfo  zjxx_jmsfzxxbDAO = DAOFactory.createZJXX_JMSFZXXBDAO();
      //处理修改信息
      if (psCdsVo != null && psCdsVo.length > 0) { //如果有受理信息
        for (int i = 0; i < psCdsVo.length; i++) {
          //取出临时受理ID
          String sLsslid = (String) psCdsVo[i].getMap().get("lsslid");
          ////取出卡号
          String sKh = (String) psCdsVo[i].getMap().get("lsjmsfzkh");
          //根据临时受理ID从临时表中取信息
          PoLSSFZ_SLXXB poSlxxb  = super.get(PoLSSFZ_SLXXB.class,Long.
              valueOf(sLsslid));
          if (poSlxxb == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "没有找到要修改的受理信息,业务无法保存.", null);
          }
          //卡号为空不能修改
          if (poSlxxb.getDybz().equals(ZjConstant.LSSFZ_DYBZ_WDY)) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "受理信息未打印,无卡号信息不能修改业务.", null);
          }
          //数据 范围效验
          VoXtsjfw vo = new VoXtsjfw();
          vo.setSjfwbz(PublicConstant.XT_QX_JWH);
          vo.setSjfw(poSlxxb.getJcwh());
          List lstSjfw = new ArrayList();
          lstSjfw.add(vo);
          boolean bRet = XtywqxServiceImpl.VerifyDataRange(String.valueOf(
              getUserInfo().
              getYhid()), PublicConstant.GNBH_LSSFZ_DYBC, lstSjfw);
          if (!bRet) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_DATASCOPE,
                                       "受理数据范围受到限制，临时身份证修改保存无法完成。", null);
          }
          //根据卡号从受理表中获取受理信息
          List lstSlxx =super.findAllByHQL(
              " from PoLSSFZ_SLXXB as LSSFZ_SLXXB where LSJMSFZKH='" + sKh +
              "' and dybz <>'" + ZjConstant.LSSFZ_DYBZ_ZF + "'");
          //判断卡号是否存在
          if (lstSlxx != null && lstSlxx.size() > 0) {
            //如果卡号已经存在，则不允许再修改
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "卡号已经存在,不允许保存.", null);
          }
          //如果卡号不存在
          //记录历史信息
          WriteInfoToLsB(poSlxxb.getLsslid(), poSlxxb.getRyid(),
                         poSlxxb.getLsjmsfzkh(), sKh);
          //修改身份证信息表中的卡号
          PoZJXX_JMSFZXXB poJmsfzbNew = super.
              get(PoZJXX_JMSFZXXB.class,poSlxxb.getNbsfzid());
          poJmsfzbNew.setKtglh(sKh);
         super.update(poJmsfzbNew);
          //更新受理信息表中的卡号
          poSlxxb.setLsjmsfzkh(sKh);
                   lstReturn.add(super.update(poSlxxb));
        }
      }
      ////处理结束
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
    return lstReturn;
  }

  /**
   * 临时身份证受理信息作废
   * @param psCdsVo
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List LsslxxZf(CdsVO psCdsVo[]) throws ServiceException,
      DAOException {
    List lstReturn = new ArrayList();
    try {
      PojoInfo  lssfz_slxxbDAO = DAOFactory.createLSSFZ_SLXXBDAO();
      //处理作废信息
      if (psCdsVo != null && psCdsVo.length > 0) { //如果有受理信息
        for (int i = 0; i < psCdsVo.length; i++) {
          //取出临时受理ID
          String sLsslid = (String) psCdsVo[i].getMap().get("lsslid");
          //根据临时受理ID从临时表中取信息
          PoLSSFZ_SLXXB poSlxxb  = super.get(PoLSSFZ_SLXXB.class,Long.
              valueOf(sLsslid));
          if (poSlxxb == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "没有找到要作废的受理信息,业务无法保存.", null);
          }
          //非未打印信息不能作废
          if (!poSlxxb.getDybz().equals(ZjConstant.LSSFZ_DYBZ_WDY)) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "状态不是未打印，不能作废.", null);
          }
          //数据 范围效验
          VoXtsjfw vo = new VoXtsjfw();
          vo.setSjfwbz(PublicConstant.XT_QX_JWH);
          vo.setSjfw(poSlxxb.getJcwh());
          List lstSjfw = new ArrayList();
          lstSjfw.add(vo);
          boolean bRet = XtywqxServiceImpl.VerifyDataRange(String.valueOf(
              getUserInfo().
              getYhid()), PublicConstant.GNBH_LSSFZ_DYBC, lstSjfw);
          if (!bRet) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_DATASCOPE,
                                       "受理数据范围受到限制，临时身份证作废保存无法完成。", null);
          }
          //更新受理信息表中的打印状态
          poSlxxb.setDybz(ZjConstant.LSSFZ_DYBZ_ZF);
          poSlxxb.setDyrid(this.getUserInfo().getYhid());
          poSlxxb.setDysj(StringUtils.getServiceTime());
          poSlxxb.setDyrip(BaseContext.getUser().getIp());
                   lstReturn.add(super.update(poSlxxb));
        }
      }
      ////处理结束
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
    return lstReturn;

  }

}
